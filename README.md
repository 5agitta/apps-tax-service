# apps-tax-service

This microservice is responsible for all the tax calculation related process. It calculates tax, saves it to db and also fetches previously calculated tax records.

# API Endpoint Documentation

## Yearly Tax Information

This endpoint allows you to retrieve yearly tax information based on the provided request data.

### Endpoint

- **HTTP Method:** POST
- **URL:** `/tax/yearly`

### Request

#### Request Body

- **Media Type:** JSON
- **Schema:**

```json
{
  "year": 2018,
  "income": 1000000,
  "city": "string",
  "gender": "string",
  "age": 0,
  "etin": "string"
}
```

Properties
year (Type: Integer, Required: true) - The year for which you want to calculate the tax.


income (Type: Integer, Required: true) - The total income of the taxpayer.
city (Type: String, Required: true) - The city of residence.
gender (Type: String, Required: true) - The gender of the taxpayer.
age (Type: Integer, Required: true) - The age of the taxpayer.
etin (Type: String, Required: true) - The taxpayer's Electronic Taxpayer Identification Number.
Sample Request

- POST /tax/yearly
- Content-Type: application/json
```json
{
    "year": 2018,
    "income": 1000000,
    "city": "New York",
    "gender": "Male",
    "age": 30,
    "etin": "ABC12345"
}
```
