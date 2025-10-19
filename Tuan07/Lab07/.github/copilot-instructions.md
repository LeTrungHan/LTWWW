# Lab07 - Spring Boot JPA Project

## Project Overview
This is a Spring Boot 3.5.6 application using Java 17, set up as a lab assignment (Lab07) for a web technologies course (LTWWW - Lập Trình Web và WWW).

## Technology Stack
- **Framework**: Spring Boot 3.5.6 with Spring Data JPA, Spring Web, Validation
- **Database**: MariaDB (configured via `mariadb-java-client`)
- **Build Tool**: Maven with wrapper scripts (`mvnw`, `mvnw.cmd`)
- **Dev Tools**: Spring Boot DevTools (hot reload enabled), Lombok (code generation)
- **Java Version**: 17

## Project Structure & Conventions
- **Base Package**: `iuh.fit.se.lab07`
- **Layer Architecture** (currently scaffolded but empty):
  - `entities/` - JPA entity classes (use Lombok annotations)
  - `repositories/` - Spring Data JPA repositories (extend `JpaRepository<T, ID>`)
  - `serverces/` - ⚠️ **Note typo**: Service layer (should be "services" but is "serverces")
  - `controllers/` - REST controllers (use `@RestController` for APIs)

## Development Workflow
- **Run application**: `./mvnw spring-boot:run` (Unix) or `mvnw.cmd spring-boot:run` (Windows)
- **Build**: `./mvnw clean package`
- **Test**: `./mvnw test`
- **Hot Reload**: Enabled via Spring Boot DevTools - changes to classes trigger automatic restart

## Configuration
- Database configuration goes in `src/main/resources/application.properties`
- Currently only defines `spring.application.name=Lab07`
- Add MariaDB datasource properties:
  ```properties
  spring.datasource.url=jdbc:mariadb://localhost:3306/db_name
  spring.datasource.username=your_username
  spring.datasource.password=your_password
  spring.jpa.hibernate.ddl-auto=update
  ```

## Lombok Configuration
- Lombok is configured in Maven with annotation processor path
- Use `@Data`, `@Entity`, `@NoArgsConstructor`, `@AllArgsConstructor` on entities
- Lombok is excluded from the final JAR build

## Code Patterns for This Project
1. **Entity Classes**: Place in `entities/`, use JPA annotations (`@Entity`, `@Id`, `@GeneratedValue`) with Lombok
2. **Repositories**: Extend `JpaRepository<Entity, IdType>` in `repositories/`
3. **Services**: Implement business logic in `serverces/` (mind the typo)
4. **Controllers**: Use `@RestController` + `@RequestMapping` in `controllers/` for REST endpoints
5. **Validation**: Use JSR-380 annotations (`@Valid`, `@NotNull`, etc.) on entities and DTOs

## Important Notes
- The `serverces/` directory has a typo - it should be "services" but maintain consistency with existing structure
- Application uses Spring Boot 3.x, which requires Jakarta EE imports (e.g., `jakarta.persistence.*` not `javax.persistence.*`)
- DevTools is included - application will auto-restart on classpath changes during development
