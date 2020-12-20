# Creating a shortened link
## Request
```shell
curl --location --request POST 'http://localhost:8080/v1/api' \
--header 'Content-Type: application/json' \
--data-raw '{
    "target": "https://google.co.uk"
}'
```
## Response
```json
{
    "target": "https://google.co.uk",
    "alias": "http://localhost:8080/EWQ8n"
}
```
# Get a shortened link
## Request
```shell
curl --location --request GET 'http://localhost:8080/v1/api/EWQ8n'
```
## Response
```json
{
    "target": "https://google.co.uk",
    "alias": "http://localhost:8080/EWQ8n"
}
```
# Delete a shortened link
## Request
```shell
curl --location --request DELETE 'http://localhost:8080/v1/api/EWQ8n'
```
## Response
```json
{
    "target": "https://google.co.uk",
    "alias": "http://localhost:8080/EWQ8n"
}
```

# To use a shortened link
Visit the url provided in the alias field of the `GET`/`POST` response