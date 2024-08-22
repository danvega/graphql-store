# WaffleCorp e-commerce Store

This repo contains the demo code for my talk "Getting Started with Spring for GraphQL". This project is built with
Spring Boot 3.3.3 and the following dependencies:

- Spring Web
- Spring for GraphQL
- Spring Data JPA
- PostgreSQL
- Spring Boot Docker Compose
- Spring Boot Actuator
- More...

## Running the application

There are a few things you need to know about this application to get it up and running. First you will need to have Docker
desktop running to run the application as is. This is because the application uses the Docker Compose Module and will start
any services located in `docker-compose.yml` automatically. If you don't want to use Docker you could remove that
module and then configure each of those services manually.

### GraphiQL UI

The application has a GraphiQL UI that you can use to interact with the GraphQL API. You can access it
at http://localhost:8080/graphiql. Here you can run queries and mutations against the API. Here are a few examples:

```graphql
query findProduct($id:ID) {
    getProduct(id:$id) {
        id
        title
        desc
    }
}
```

```graphql
query {
    allProducts {
        id
        title
        desc
    }
}
```

```graphql
query search {
  search(text: "Waffle") {
    __typename
    ... on Customer {
      id
      firstName
      lastName
      email
    }
    ... on Product {
      id
      title
      desc
    }
  }
}
```

```graphql
mutation {
  createProduct(product: {title:"New Product",desc:"New Product DESC"}) {
    id
    title
    desc
  }
}
```

## Getting Started Demo

There is a `start-here` branch that you can use to follow along with the demo. Here is a rough outline of the steps I will
go through in the demo:

- http://start.spring.io
- Explore the Starting Code
    - Walk through `src/main/resources/graphql/schema.graphqls`
    - Product Controller is empty to start
- Run the application schema mapping inspection report 
- How much of the app are we going to build?
  - ProductController
    - allProducts 
      - `@SchemaMapping` & `@QueryMapping`
    - getProduct
      - @Argument
    - [Method Arguments](https://docs.spring.io/spring-graphql/reference/controllers.html#controllers.schema-mapping.signature)
    - createProduct `@MutationMapping` 
      - Validation
    - orders `@SchemaMapping`
      - Talk about difference between field and data fetcher / Registering Data Fetchers
      - orders could come from a microservice
  - Client App
  - Testing
  - Features (We won't get to cover all of these)
    - Unions
    - Interfaces
    - Batch Mapping
    - Defer
    - Federation
    - Virtual Threads


## Resources

- [Learn GraphQL](https://graphql.org/learn/)
- [Spring for GraphQL Project](https://spring.io/projects/spring-graphql)
- [Spring for GraphQL Reference Documentation](https://docs.spring.io/spring-graphql/reference/index.html)
- [GraphQL Java](https://www.graphql-java.com/)
- [Welcome, Spring for GraphQL by Rossen Stoyanchev @ Spring I/O 2022](https://www.youtube.com/watch?v=FMZckqbPGq0)
- [Observing Spring for GraphQL in Action by Brian Clozel & Rossen Stoyanchev @ Spring I/O 2023](https://www.youtube.com/watch?v=UhgmOOjjjyw)
- [My GraphQL Playlist on YouTube](https://www.youtube.com/playlist?list=PLZV0a2jwt22slmUC9iwGGWfRQRIhs1ELa)

## Acknowledgments

A huge thank you to the Spring team for all the hard work they have put into the Spring for GraphQL project. A special thanks
to both [Brian Clozel](https://github.com/bclozel) and [Rossen Stoyanchev](https://x.com/rstoya05) for all of their help and
guidance on my GraphQL journey.
