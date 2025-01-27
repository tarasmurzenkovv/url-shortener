# Intro

## Before
You need to spin up the Redis container locally. You can use `docker-compose.yml` for that. 

## How to run locally

Via console in the root directory
```shell
./gradlew bootJar
```

alternatively you can run main class

```kotlin
com.taras.murzenkov.urlshortener.UrlShortenerApplication
```

## End-points
While running app locally, the following end-points are available
1. 
```shell
curl --location 'localhost:8080//api/v1/url/{shortened-value}'
```
will return the full value of the shortened
2.  
    ```shell
    curl --location 'localhost:8080/api/v1/url' \
    --header 'Content-Type: application/json' \
    --data '{
        "url": "url-to-shorten",     
    }'
    ```
will shorten URL provided in request body

### Main assumptions
1. the read-write ratio is skewed toward read operations so that all key-value pairs can be fit into memory
2. no need for ttl
3. the user friendliness of the shortened url is not taken into account

