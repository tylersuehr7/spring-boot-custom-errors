# Using Custom Errors in API

Uses conventional HTTP response codes to indicate the success or failure of an API
request. In general, codes in the 2xx range indicate success. Codes in the 4xx range
indicate an error that failed given the information provided. Codes in the 5xx range
indicate an error with the backend server (which should be rare.)

- `200 OK` - Everything worked as expected
- `400 BAD REQUEST` - Request unacceptable; often due to missing parameter
- `401 UNAUTHORIZED` - TBD
- `402 REQUEST FAILED` - Request acceptable but processing it failed
- `403 FORBIDDEN` - TBD
- `404 NOT FOUND` - Requested resource does not exist
- `429 TOO MANY REQUESTS` - Requested resource rate limit was exceeded
- `5XX` - Something went wrong on server (these are really rare)

### Additional Custom Error Codes
- `0` - Not a custom error
- `1` - Contains a localized error message
- `2` - Login token was missing
- `3` - Login token failed verification
- `4` - Login token expired, but can be refreshed
- `5` - Login token expired, but cannot be refreshed

```json
{
  "code": 402,
  "error": "Request Failed",
  "message": "Something went wrong!",
  "path": "/v1/sign-up",
  "timestamp": "2021-06-22T20:26:18+00:00",
  "customCode": 0
}
```