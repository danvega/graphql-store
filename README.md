# WaffleCorp e-commerce Store

This repo contains the demo code for my talk "Getting Started with Spring for GraphQL". This project is built with
Spring Boot 3.2 M1 and the following dependencies: 

- Spring Web
- Spring for GraphQL
- Spring Data JPA
- Spring Security 
- Spring Boot Docker Compose
- PostgreSQL
- Spring Boot Actuator 
- More...

## Running the application

There are a few things you need to know about this application to get it up and running. First you will need to have Docker
desktop running to run the application as is. This is because the application uses the Docker Compose Module and will start
any services located in `docker-compose.yml` automatically. If you don't want to use Docker you could remove that module and then configure each of those services manually.

## Resources

- [Learn GraphQL](https://graphql.org/learn/)
- [Spring for GraphQL Project](https://spring.io/projects/spring-graphql)
- [Spring for GraphQL Reference Documentation](https://docs.spring.io/spring-graphql/docs/1.0.0-SNAPSHOT/reference/html/)
- [GraphQL Java](https://www.graphql-java.com/)
- [Slide Deck](./SpringForGraphQL.pdf)
- [Welcome, Spring for GraphQL by Rossen Stoyanchev @ Spring I/O 2022](https://www.youtube.com/watch?v=FMZckqbPGq0)
- [Observing Spring for GraphQL in Action by Brian Clozel & Rossen Stoyanchev @ Spring I/O 2023](https://www.youtube.com/watch?v=UhgmOOjjjyw)
- [My GraphQL Playlist on YouTube](https://www.youtube.com/playlist?list=PLZV0a2jwt22slmUC9iwGGWfRQRIhs1ELa)