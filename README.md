# Persons Microservice
> Microservicio encargado de centralizar el ciclo de vida y la información base de los (actores,directores,etc) dentro del ecosistema del sistema. Todo ello dentro de una arquitectura por Capas por su facil aprendizaje

## Implementaciones
  * **Spring boot Actuator**
  * **SpringDoc OpenAPI**
  * **Spring Boot DevTools (Solo sera para entorno de desarrollo)**
  * **Lombok**
  * **Spring Data MongoDB**
  * **Validation**
---

## Flujo del funcionamiento
<img width="1484" height="596" alt="image" src="https://github.com/user-attachments/assets/d550b698-2a96-4d50-9b13-c83088e98ddf" />


1. **Peticion HTTP GET:** El cliente realiza una peticion `HTTP POST` para crear una nueva persona (actor)
2. **Controller:** El Controller recibe la peticion y le redirige la logica de negocio al `Persons Service`
3. **Service → SlugUtil:** El Service llama al SlugUtil para transformar el `name` en el `slug`
4. **Service → Repository:** El Service realiza una comprobacion de si existe el slug dentro de la database
   * 4.2. **Repository → Database:** Spring Data MongoDb ejecuta un `db.persons.countDocuments({slug: "ryan-gosling"},{limit : 1}) > 0`
   * 4.4. **Service → Mapper:** Cuando no exista el `slug` transforma el `personRequest` en  `Person` junto al slug nuevo
   * 4.6. **Service → Repository:** Llama al `PersonsRepository` para guardar la nueva persona
   * 4.8 **Repository → Database:** Spring Data MongoDB ejecuta un `db.persons.insert(JSON)`
8. **Service → Controller:** El Service devuelve un `PersonResponseDto`
9. **Controller → Cliente:** El `PersonsController` devuelve la respuesta en un JSON con un codigo **201 CREATED**  

---

## Uso de OpenAPI/Swagger
> Gracias a la documentacion de OpenAPI y el refuerzo de las herramientas de Swagger tenemos un vistazo en detalle sobre los endpoints, parametros, respuestas, modelos, etc.
> 
> Para ello solo debemos escribir en el navegador `IP DE PERSONS-SERVICE:PORT/swagger-ui.html`
<img width="1845" height="901" alt="image" src="https://github.com/user-attachments/assets/7ebb8d41-1c87-4776-ba7d-3b93950dd587" />

---

## Contenerización con Docker & Cloud Native Builds
En este entorno, cada microservicio debe ser agnóstico a la máquina donde se ejecuta. Para ello recurrimos a **Docker**, abstrayendo el código, entorno de ejecución y dependencias en imágenes ligeras.

Para optimizar la generación de imágenes y evitar mantener archivos `Dockerfile` redundantes, delegué la responsabilidad a <a href="https://cloud.google.com/blog/products/application-development/introducing-jib-build-java-docker-images-better">**Google Jib**</a>. Herramienta que compila de forma nativa imágenes optimizadas para Java directamente hacia el daemon de Docker sin necesidad de configuraciones complejas.

Basta con incluir el plugin en el `pom.xml` y ejecutar:

```bash
mvn compile jib:dockerBuild
```
