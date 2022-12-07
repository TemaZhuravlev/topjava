curl commands for example

Get All Meals

curl localhost:8080/topjava/rest/meals

Get Meals 100005

curl localhost:8080/topjava/rest/meals/100005

Meals filtered

curl 'localhost:8080/topjava/rest/meals/filter?startDate=2020-01-30&startTime=07:00:00&endDate=2020-01-30&endTime=23:00:00'

Delete Meal

curl -X DELETE localhost:8080/topjava/rest/meals/100005

Create Meal

curl -X POST -d '{"dateTime":"2020-01-02T12:00","description":"Lunch created","calories":1300}' -H 'Content-Type:application/json;charset=UTF-8' localhost:8080/topjava/rest/meals

Update Meal

curl -X PUT -d '{"dateTime":"2020-01-30T13:00","description":"Lunch update","calories":1700}' -H 'Content-Type:application/json;charset=UTF-8' localhost:8080/topjava/rest/meals/100004