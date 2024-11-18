# RMChars - Rick & Morty Characters App
Una aplicación móvil desarrollada en Kotlin que permite explorar y visualizar información sobre los personajes de la serie Rick & Morty.
## Características
- Lista de personajes: Explora personajes con soporte de paginación automática.
- Detalle de personajes: Toca un personaje para ver detalles adicionales.
- Persistencia de datos: Almacena datos localmente para minimizar el consumo de la API.
- Búsqueda: Filtra los personajes por nombre en tiempo real.
- Clean Architecture: Organizado en capas data, domain y presentation.

## Tecnologías utilizadas
### 📱 Presentación
- Jetpack Compose: Para construir una UI declarativa.
- MVVM (Model-View-ViewModel): Gestión de estado y comunicación entre capas.
- Jetpack Navigation: Manejo de navegación entre pantallas con parámetros.
### 🗂 Dominio
Implementación de casos de uso para desacoplar lógica empresarial de las capas de datos y presentación.
### 📦 Datos
- Retrofit: Para consumir el endpoint /characters de la API de Rick & Morty.
- Room: Para persistencia local de los datos y soporte offline.
- Paging 3: Manejo eficiente de grandes volúmenes de datos con soporte de paginación.
### 🔧 Otras tecnologías
- Dagger Hilt: Inyección de dependencias.
- Mockito: Pruebas unitarias.

## Estructura del proyecto
📂 RMChars  
 ├── data/                  # Lógica de acceso a datos  
 │   ├── remote/            # Retrofit y modelos de respuesta  
 │   ├── mapper/            # Mappers entre modelos de respuesta y DTOs  
 │   ├── local/             # Configuración de Room y DAOs  
 │   └── repository/        # Implementación de repositorios  
 ├── domain/                # Lógica de negocio  
 │   ├── model/            # Modelos de dominio  
 │   └── usecase/          # Casos de uso  
 ├── presentation/          # Capa de presentación  
 │   ├── viewmodel/         # ViewModels de Jetpack  
 │   ├── screens/           # Pantallas y componentes de Compose  
 │   └── navigation/        # Configuración de navegación  
 └── tests/                 # Pruebas unitarias con Mockito  

## Documentación oficial

[https://rickandmortyapi.com/documentation](https://rickandmortyapi.com/documentation)

## Arquitectura
El proyecto sigue Clean Architecture, separando responsabilidades en tres capas:

Data: Gestión de fuentes de datos externas (API, base de datos).
Domain: Contiene la lógica empresarial mediante casos de uso.
Presentation: Manejo de la UI y la interacción del usuario, utilizando el patrón MVVM.
### Diagrama de la arquitectura
 Presentation  
     ↕  
  Domain  
     ↕  
   Data
 

## Contribuciones
¡Las contribuciones son bienvenidas! Si tienes alguna idea para mejorar la app, no dudes en abrir un issue o enviar un pull request.
