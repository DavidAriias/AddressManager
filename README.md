# Address Manager

Aplicación Android desarrollada en Kotlin como parte de una prueba técnica.

La aplicación permite cargar un catálogo de direcciones desde un archivo CSV, almacenarlas localmente mediante Room y gestionar su consulta y edición a través de una interfaz desarrollada con Jetpack Compose.


---

# 1. Instrucciones de ejecución

## Requisitos

* Android Studio Koala o superior
* JDK 17
* Android SDK compatible con el proyecto

## Pasos para ejecutar el proyecto

1. Descomprimir el archivo entregado.
2. Abrir Android Studio.
3. Seleccionar **Open Project**.
4. Abrir la carpeta raíz del proyecto.
5. Esperar a que Gradle sincronice las dependencias.
6. Ejecutar la aplicación en un emulador Android o dispositivo físico.

## Inicialización de datos

La base de datos se inicializa automáticamente durante la primera ejecución de la aplicación.

Los registros son cargados desde el archivo CSV incluido dentro del proyecto y posteriormente almacenados en Room para su consulta y modificación.

---

# 2. Descripción de la solución

## Arquitectura utilizada

Se utilizó una arquitectura inspirada en Clean Architecture junto con el patrón MVVM.

Debido al tiempo disponible para la prueba técnica, se optó por una versión simplificada de la arquitectura, priorizando:

* Separación de responsabilidades.
* Mantenibilidad.
* Escalabilidad.
* Facilidad de lectura del código.

La aplicación se encuentra organizada en las siguientes capas:

### Presentation

Responsable de:

* Pantallas Compose.
* Componentes reutilizables.
* ViewModels.
* Manejo de estados de UI.

### Domain

Responsable de:

* Entidades.
* Contratos de repositorios.
* Reglas de negocio.

### Infraestructure

Responsable de:

* Base de datos Room.
* DAOs.
* Implementaciones de repositorios.
* Lectura del archivo CSV.

### Config

Responsable de:

* Inyección de dependencias.
* Navegación.
* Configuración general de la aplicación.

---

## Principales decisiones técnicas

### MVVM

Permite desacoplar la lógica de presentación de la interfaz gráfica y facilita la mantenibilidad del proyecto.

### Repository Pattern

Centraliza el acceso a datos y desacopla la lógica de negocio de las fuentes de información.

### Room

Se utilizó para garantizar persistencia local, consultas eficientes y almacenamiento de cambios realizados por el usuario.

### Kotlin Coroutines y Flow

Permiten ejecutar operaciones asíncronas y reflejar cambios automáticamente en la interfaz de usuario.

### Jetpack Compose

Se utilizó para desarrollar una interfaz moderna y declarativa.

### Hilt

Facilita la inyección de dependencias y simplifica la configuración de los componentes.

---

# 3. Funcionalidades implementadas

* Carga inicial de direcciones desde archivo CSV.
* Persistencia local mediante Room.
* Visualización del catálogo de direcciones.
* Filtrado por ciudad.
* Filtrado por estado/provincia.
* Edición de ciudad.
* Edición de estado/provincia.
* Actualización automática de fecha de modificación.
* Persistencia de cambios entre ejecuciones.
* Actualización reactiva de la interfaz mediante Flow.

---

# 4. Supuestos y limitaciones

## Simplificaciones realizadas

* Se implementaron únicamente las funcionalidades requeridas para la prueba técnica.
* La carga del archivo CSV se ejecuta únicamente durante la primera ejecución.
* No se implementó sincronización con servicios externos.

## Limitaciones

* La aplicación opera completamente de forma local.
* No existe sincronización remota de datos.


---

# 5. Dependencias utilizadas

* Jetpack Compose
* Room
* Hilt
* Navigation Compose
* Kotlin Coroutines
* Kotlin Flow

---

# Estructura del proyecto

```text
app
│
├── config
│   ├── di
│   └── navigation
│
├── domain
│   ├── entities
│   └── repositories
│
├── infraestructure
│   ├── database
│   ├── dao
│   ├── datasource
│   └── repositories
│
└── presentation
    ├── screens
    ├── components
    └── viewmodels
```

---

# Autor

David Arias
