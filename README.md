# WaffleCorp e-commerce Store

This repo contains the demo code for my talk "Getting Started with Spring for GraphQL". 

## Getting Started with Spring for GraphQL

WaffleCorp is a major provider of breakfast products available direct to consumer or through our strategic partnerships. The current implementation of the e-commerce platform is a monolithic Spring MVC application that serves data through a collection of REST APIs.

Currently, the only provider of the REST API is our e-commerce web application. We've been tasked with opening up our APIs to our new iOS and Android apps, partner microservices, and IoT applications.

The issue we ran into is that a REST API is not a one-size-fits-all approach. We need a more flexible solution to meet the requirements of all of our client applications. This is a perfect use case for the speed and flexibility of GraphQL.

GraphQL is a query language for APIs and a runtime for fulfilling those queries with your existing data. GraphQL provides a complete and understandable description of the data in your API, gives clients the power to ask for exactly what they need and nothing more, makes it easier to evolve APIs over time, and enables powerful developer tools.

In this session, you’ll learn what GraphQL is and why you should consider it in your next project. You’ll learn how to use GraphQL in your Spring Boot applications by leveraging the Spring for GraphQL project. By the end of this session, you’ll understand how to stand up a GraphQL endpoint and request the data you need, and nothing more.

## Resources

- [Learn GraphQL](https://graphql.org/learn/)
- [Spring for GraphQL Project](https://spring.io/projects/spring-graphql)
- [Spring for GraphQL Reference Documentation](https://docs.spring.io/spring-graphql/docs/1.0.0-SNAPSHOT/reference/html/)
- [GraphQL Java](https://www.graphql-java.com/)
- [Slide Deck](./SpringForGraphQL.pdf)
- [Welcome, Spring for GraphQL by Rossen Stoyanchev @ Spring I/O 2022](https://www.youtube.com/watch?v=FMZckqbPGq0)