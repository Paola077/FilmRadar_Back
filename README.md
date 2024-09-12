#FILMRADAR BACKEND 🎥

Este es el backend de **FILMRADAR**, una plataforma para ver el raking de las películas, gestionar reseñas y listas favoritas. La aplición se ha desarrollado utilizando Spring Boot para el backend y **PostgreSQL como base de datos.
**FilmRadar** interactúa con la API de **TMDB** para obtener información sobre las películas.

##FUNCIONALIDADES

* Puntuación de películas: Los usuarios registrados pueden puntuar las películas en una escala del 1 al 10.
* Creación de reseñas: Los usuarios registrados pueden añadir reseñas si así lo desean.
* Eliminacioón de reseñas: Los usuarios pueden eliminar su reseña si asi lo deseam.
* Películas favoritas: Los usuarios pueden agregar y gestionar una lista de películas favoritas.

  ##TECNOLOGÍAS UTILIZADAS 🛠️
* Java 17
* Spring Boot
* 
  * Spring Web (para la API REST)
  * Spring Data JPA (para la interacción con la base de datos)
  * Spring Security (para autenticación y autorización)

* PostgreSQL (base de datos)
* JWT (para autenticación basada en tokens)
* JUnit 5 y Mockito (para pruebas unitarias)
 
  ##Configuración

  * Clona este repositorio en tu maquina local: https://github.com/Paola077/FilmRadar_Back.git

  * Configura la base de datos PostgreSQL.

  * Crea una base de datos llamada film_radar.
    
  * Configura las credenciales de la base de datos en el archivo application.properties.

  # src/main/resources/application.properties:

    spring.datasource.url=jdbc:postgresql://localhost:5432/film_radar
    spring.datasource.username=tu_usuario
    spring.datasource.password=tu_password
    
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true

##Endpoints principales

Autenticación:

    POST /auth/logIn: Iniciar sesión de usuario y obtener token JWT.
    POST /auth/signIn: Registro de un nuevo usuario.

Películas:

    GET /discover/movie: Obtiene todas las películas de la base de datos.
    GET /auth/all/movies: Obtiene todas las películas disponibles públicamente.
    GET /auth/search/title: Búsqueda de películas por título.
    GET /auth/search/genre: Búsqueda de películas por género.
    GET /auth/search/year: Búsqueda de películas por año.

Reseñas:

    GET /auth/movies/{movieId}/reviews: Obtiene las reseñas de una película específica.
    POST /api/{movieId}/addReview: Agregar una reseña a una película (requiere autenticación).

Calificaciones:

    POST /api/{movieId}/rate: Calificar una película (requiere autenticación).

Favoritos:

    POST /api/{movieId}/favorites: Añadir una película a favoritos (requiere autenticación).

Autor

  [Paola Perdomo] https://github.com/Paola077
  
