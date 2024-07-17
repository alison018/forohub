# ForoHub API

ForoHub es una API REST para la gestión de usuarios, tópicos, cursos y respuestas en una plataforma de foros. Esta API está construida utilizando Spring Boot y sigue las mejores prácticas de desarrollo.

## Requisitos Previos

- Java 17 o superior
- Maven 4 o superior
- MySQL 8 o superior

## Tecnologías Utilizadas

- Spring Boot 3
- Spring Data JPA
- Spring Security
- JWT (JSON Web Tokens)
- Lombok
- Flyway Migration
- MySQL
- SpringDoc OpenAPI

## Configuración del Proyecto

### Clonar el Repositorio

```bash
git clone https://github.com/alison018/ForoHub.git
cd ForoHub
```

## Configuración de Base de Datos
- Crear una base de datos en MySQL llamada foro_hub.
- Configurar las credenciales de la base de datos en el archivo application.properties:

```application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/foro_hub
spring.datasource.username=TU_USUARIO
spring.datasource.password=TU_CONTRASEÑA
spring.jpa.hibernate.ddl-auto=update
spring.flyway.enabled=true

```
# Endpoints Principales

## Autenticación
- **POST /login**: Autentica un usuario y retorna un JWT.

## Usuarios
- **POST /users**: Crea un nuevo usuario.
- **GET /users**: Obtiene una lista de todos los usuarios.
- **GET /users/{id}**: Obtiene un usuario por ID.
- **PUT /users/{id}**: Actualiza un usuario existente.
- **DELETE /users/{id}**: Elimina un usuario por ID.

## Tópicos
- **POST /topics**: Crea un nuevo tópico.
- **GET /topics**: Obtiene una lista de todos los tópicos.
- **GET /topics/top10**: Obtiene los 10 primeros tópicos.
- **POST /topics/course/year**: Obtiene tópicos por curso y año.
- **GET /topics/{id}**: Obtiene un tópico por ID.
- **PUT /topics/{id}**: Actualiza un tópico existente.
- **DELETE /topics/{id}**: Elimina un tópico por ID.

## Cursos
- **POST /courses**: Crea un nuevo curso.
- **GET /courses**: Obtiene una lista de todos los cursos.
- **GET /courses/active**: Obtiene una lista de cursos activos.
- **GET /courses/instructor/{instructorId}**: Obtiene cursos por instructor.
- **GET /courses/search**: Busca cursos por palabra clave.
- **GET /courses/{id}**: Obtiene un curso por ID.
- **PUT /courses/{id}**: Actualiza un curso existente.
- **DELETE /courses/{id}**: Elimina un curso por ID.

## Respuestas
- **POST /responses**: Crea una nueva respuesta.
- **GET /responses**: Obtiene una lista de todas las respuestas.
- **GET /responses/{id}**: Obtiene una respuesta por ID.
- **PUT /responses/{id}**: Actualiza una respuesta existente.
- **DELETE /responses/{id}**: Elimina una respuesta por ID.

## Status
- **GET /status**: Obtiene todos los valores de estado posibles.

# Documentación de la API
La documentación de la API está disponible en `/swagger-ui.html` después de iniciar la aplicación.

# Contribuciones
Las contribuciones son bienvenidas. Por favor, abre un issue o envía un pull request para contribuir al proyecto.

# Licencia
Este proyecto está licenciado bajo la Licencia Apache 2.0. Ver el archivo LICENSE para más detalles.


