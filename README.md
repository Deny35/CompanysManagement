# Company Management API

This project is a RESTful API designed to manage companies, their contact persons, and associated meetings. It provides functionalities such as:
- **CRUD operations** for companies, contact persons, and meetings.
- **Google Maps API integration** for displaying and filtering companies by geographical proximity.
- **Geospatial data support** using the GEOGRAPHY(Point, 4326) data type for precise location handling.
- **Liquibase integration** for database schema versioning and migration management.
- Comprehensive exception handling, DTO usage, and mapping for clean, maintainable, and scalable code.

### Technologies and Libraries Used:
- **Backend**: Java 21, Spring Boot (Spring Data JPA, Spring Web, Spring Validation)
- **Database**: PostgreSQL with PostGIS extension for geospatial data
- **Database Management**: Liquibase for schema migrations
- **Mapping**: MapStruct for DTO-to-entity conversions
- **Integration**: Google Maps API for geolocation and filtering

[//]: # (- **Testing**: JUnit 5, Mockito for unit and integration testing)
- **Build Tool**: Maven for dependency management
- **Version Control**: Git with a focus on CI/CD best practices

This project is still under active development and aims to provide a solid foundation for more complex business management systems. It adheres to modern software development practices, emphasizing modularity, clarity, and extensibility.
