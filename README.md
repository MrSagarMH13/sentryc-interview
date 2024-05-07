# sentryc-interview

This project includes an API to manage sellers and their producer states. The application is a Spring Boot application with Jakarta EE.

## Prerequisites

- Java 17
- Gradle

The server will start on `http://localhost:8080`.

## API Details

The application contains a GraphQL endpoint. Use the following `curl` command to interact with it.

bash curl --location 'http://localhost:8080/graphql' --header 'Content-Type: application/json' --header 'Authorization: Bearer  ' --data '{"query":"query {\n sellers(filter: {searchByName: "download1"}, page: {page:0, size:100}, sortBy: NAME_ASC) {\n meta {\n page\n size\n totalElements\n totalPages\n }\n data {\n sellerName\n externalId\n producerSellerStates {\n producerId\n producerName\n sellerState\n sellerId\n }\n marketplaceId\n }\n }\n}","variables":{}}'