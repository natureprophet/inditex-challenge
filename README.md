# Price Calculator

The **Price Calculator** is a fundamental module within the Inditex Prizing application, designed to provide an efficient and user-friendly API for retrieving pricing information based on various parameters. This README offers an overview of the project's purpose, how to get started, key API features, and essential components.

## Table of Contents

- [Project Overview](#project-overview)
- [Getting Started](#getting-started)
- [API Endpoint](#api-endpoint)
- [Parameters](#parameters)
- [Response](#response)
- [Dependencies](#dependencies)
- [Contributing](#contributing)

## Project Overview

The Price Calculator forms an integral part of the Inditex Prizing application, which serves as a comprehensive platform for managing and providing up-to-date pricing information for an array of products and brands. The primary goal of the Price Calculator is to facilitate easy access to pricing data through a well-defined and robust API, enabling various stakeholders to make informed decisions based on real-time price insights.

## Getting Started

To begin using the Price Calculator within the Inditex Prizing application, follow these steps:

1. **Clone the Repository:** Clone the Inditex Prizing repository to your local development environment.

2. **Environment Setup:** Ensure that you have Java, Spring Boot, and any required dependencies installed.

3. **Run the Application:** Launch the Inditex Prizing application. The Price Calculator's API endpoint will be accessible through the defined route.

## API Endpoint

The Price Calculator exposes a RESTful API endpoint to facilitate the retrieval of pricing information. The base URL for the API is `/api/prices`.

Example API Endpoint: `http://localhost:8080/api/prices`

## Parameters

The `getPrices` endpoint supports the following query parameters:

- `date` (String, required): The specific date and time for which pricing information is requested. The expected format is `yyyy-MM-dd'T'HH:mm:ss`.

- `productId` (String, required): The unique identifier of the product for which pricing information is desired.

- `brandId` (String, required): The unique identifier of the brand associated with the product.

## Response

The response of the `getPrices` endpoint is a JSON array containing a list of `PriceDTO` objects. Each `PriceDTO` encapsulates pricing details for a given product and brand at a particular date and time.

Example Response:
```json
[
  {
    "productId": "ABC123",
    "brandId": "XYZ456",
    "price": 19.99,
    "currency": "USD",
    "timestamp": "2023-08-18T12:00:00"
  },
  // More PriceDTO objects...
]