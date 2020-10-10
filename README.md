# merchant-offer-application

## Brief Description

A Spring Boot application for merchants to place offers to sell their products. The database used is an in-memory embedded H2 database that is injected with data during application start-up.

## High Level Design

![Merchant Offer Application High Level Design](/images/high-level-design.png)

## Endpoints

### POST /offer/post

Endpoint to post an OfferInput Json. This returns a response entity with a message that the order has been successfully made.

#### Sample Json Body

```json

{
	"description" : "Handbag",
	"price" : 13.5,
	"currency" : "GBP",
	"expiryDate" : "20/10/2021"
}

```

### GET /offer/all

Endpoint to query for all the offers available, including flags for cancelled and expired offers.

#### Sample Json Output

```json
[
	{
		"id":1,
		"description":"Handbag",
		"price":10.99,
		"currency":"GBP",
		"expiryDate":"16/09/2019",
		"cancelledFlag":false,
		"expiredFlag":true
	},
	{
		"id":2,
		"description":"Boots",
		"price":14.99,
		"currency":"USD",
		"expiryDate":"16/09/2021",
		"cancelledFlag":false,
		"expiredFlag":false
	}
]
```

### POST /offer/cancel/{id}

Endpoint to cancel the offer of the id passed in as the request parameter (e.g. to cancel order of id 1, POST /offer/cancel/1. Will return an exception response if the order does not exist, has expired or has already been cancelled.

## Functional Requirements

1. The offer the merchant places must have a description, price, currency and an expiry date.
2. The merchant must be able to post an offer onto the software service.
3. The merchant must be able to query for a list of the offers previously made, and they will be able to distinguish expired and cancelled offers from active ones by looking at boolean flags for expired and cancelled fields. (Here, active refers to offers that have not expired or been cancelled).
4. The merchant must be able to cancel offers previously made before the expiry date.
5. If the merchant attempts to delete an offer that has already expired, then an exception response will be returned.
6. If the merchant attempts to delete an offer that has already been cancelled, then an exception response will be returned.
7. If the merchant attempts to delete an offer that does not exist, then an exception response will be returned.

## Key Design Decisions

1. When querying for the list of offers, the merchant will receive a superset of all active and expired/cancelled offers instead of only active ones. This is so that the merchant has a record of what he has cancelled and has expired.
2. The merchant will only be able to delete offers by referring to their Id which can be obtained by querying for the list of offers. This is because the alternative of deleting by description may be faulty as different offers may have the same description.
3. OfferInput and Offer are two separate Java classes as there are potential security with allowing the POST request to input the entire Offer entity, and as such the POST request can only take in an OfferInput, which is then converted into an Offer within the application.

## Assumptions

1. I assume that as far as this exercise is concerned, there is no concern of there being too many records in the database so there is no need for some deletion mechanism if there are too many records.
2. I assume that as far as this exercise is concerned, the indication that an offer has expired will be performed as part of the application and not within the database because the emphasis is on the Java code, though this may be tackled differently otherwise.