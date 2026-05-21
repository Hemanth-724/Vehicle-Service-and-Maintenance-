#!/usr/bin/env bash
set -euo pipefail

BASE_URL=${BASE_URL:-http://localhost:8080}

command -v jq >/dev/null 2>&1 || { echo "jq is required. Install jq and re-run."; exit 1; }

echo "Base URL: $BASE_URL"

echo "\n1) Create customer"
cust_resp=$(curl -s -X POST "$BASE_URL/customers" -H "Content-Type: application/json" -d '{"name":"Alice","phoneNumber":"1234567890","email":"alice@example.com"}')
echo "$cust_resp" | jq .
customerId=$(echo "$cust_resp" | jq -r '.customerId')
echo "Created customerId=$customerId"

echo "\n2) Create vehicle"
veh_resp=$(curl -s -X POST "$BASE_URL/vehicles" -H "Content-Type: application/json" -d "{\"vehicleNumber\":\"KA01AB1234\",\"brand\":\"Toyota\",\"model\":\"Corolla\",\"customerId\":${customerId}}")
echo "$veh_resp" | jq .
vehicleId=$(echo "$veh_resp" | jq -r '.vehicleId')
echo "Created vehicleId=$vehicleId"

echo "\n3) Create booking"
now_iso=$(date -u +"%Y-%m-%dT%H:%M:%S")
book_resp=$(curl -s -X POST "$BASE_URL/bookings" -H "Content-Type: application/json" -d "{\"bookingDate\":\"${now_iso}\",\"serviceType\":\"Oil Change\",\"vehicleId\":${vehicleId}}")
echo "$book_resp" | jq .
bookingId=$(echo "$book_resp" | jq -r '.bookingId')
echo "Created bookingId=$bookingId"

echo "\n4) Update booking status to CONFIRMED"
upd_resp=$(curl -s -X PUT "$BASE_URL/bookings/status/${bookingId}" -H "Content-Type: application/json" -d '{"bookingStatus":"CONFIRMED"}')
echo "$upd_resp" | jq .

echo "\n5) Complete service"
today=$(date -u +"%Y-%m-%d")
complete_resp=$(curl -s -X POST "$BASE_URL/services/complete" -H "Content-Type: application/json" -d "{\"bookingId\":${bookingId},\"serviceDate\":\"${today}\",\"completionDate\":\"${today}\",\"remarks\":\"Replaced oil\"}")
echo "$complete_resp" | jq .

echo "\n6) List all services"
curl -s "$BASE_URL/services" | jq .

echo "\nSmoke tests completed."
