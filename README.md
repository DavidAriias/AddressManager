# Cat Gallery

Aplicación Android desarrollada en Kotlin como parte de una prueba técnica.

La aplicación consume una API pública de gatos, muestra un listado de imágenes y permite visualizar el detalle de cada elemento. Adicionalmente, integra el SDK de Urovo para realizar la impresión de imágenes desde la aplicación.


---

# 1. Instrucciones de ejecución

## Requisitos

* Android Studio Koala o superior
* JDK 17
* Android SDK 32
* Compile SDK 32
* Target SDK 32
* Min SDK 27 (Android 8.1)

## Pasos para ejecutar el proyecto

1. Descomprimir el archivo entregado.
2. Abrir Android Studio.
3. Seleccionar **Open Project**.
4. Abrir la carpeta raíz del proyecto.
5. Esperar a que Gradle sincronice todas las dependencias.
6. Ejecutar la aplicación en un emulador Android 8.1 o superior.

## Configuración de API

El proyecto utiliza variables definidas en el archivo `local.properties`.

Antes de ejecutar la aplicación, agregar las siguientes propiedades:

```properties
API_KEY=TU_API_KEY
BASE_URL=https://api.thecatapi.com/v1/
```

La API Key puede obtenerse registrándose en The Cat API.

Estas propiedades son utilizadas para la configuración de acceso a la API y no se incluyen dentro del control de versiones.

## Configuración del SDK de Urovo

La integración del SDK de Urovo se encuentra encapsulada dentro de la capa de infraestructura.

Para facilitar la revisión del código:

* La lógica de impresión se abstrae mediante el contrato `PrinterRepository`.
* La implementación específica de Urovo se encuentra separada del resto de la aplicación.
* Las secciones relacionadas con el SDK están identificadas mediante comentarios para facilitar su localización.
* El proyecto puede ejecutarse en emulador; sin embargo, la impresión física requiere un dispositivo Urovo compatible.

Ubicación principal de la integración:

```text
infrastructure/repositories/UrovoPrinterRepository.kt
```

Ubicación del SDK:

```text
app/libs/
```

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

* Pantallas desarrolladas con Jetpack Compose.
* Componentes reutilizables.
* ViewModels.
* Manejo de estados de UI.

### Domain

Responsable de:

* Entidades.
* Contratos de repositorios.
* Reglas de negocio.

### Infrastructure

Responsable de:

* Consumo de API.
* DTOs.
* Implementaciones de repositorios.
* Integración con SDK de Urovo.

### Config

Responsable de:

* Navegación de la aplicación.
* Inyección de dependencias.
* Configuración general.

---

## Principales decisiones técnicas

### Jetpack Compose

Se eligió Compose para la construcción de la interfaz de usuario por ser la tecnología moderna recomendada para Android.

### MVVM

Permite separar la lógica de presentación de la interfaz y facilita el manejo de estados.

### Arquitectura basada en Clean Architecture

Permite desacoplar la lógica de negocio de las implementaciones concretas de infraestructura como el consumo de API o la impresión mediante Urovo.

### Hilt

Utilizado para la inyección de dependencias y simplificación del ciclo de vida de los componentes.

### Retrofit

Utilizado para la comunicación con la API de gatos.

### Coil

Utilizado para la carga eficiente de imágenes desde URLs.

### Kotlin Coroutines

Utilizadas para la ejecución de operaciones asíncronas como llamadas de red e impresión.

### StateFlow

Utilizado para la gestión reactiva del estado de las pantallas.

---

# 3. Supuestos y limitaciones

## Simplificaciones realizadas

* Se implementó únicamente la funcionalidad requerida para consulta y visualización de gatos.
* La impresión se enfoca en imágenes obtenidas desde la API.
* No se implementó persistencia local.
* No se implementó caché offline.
* Se priorizó la funcionalidad principal solicitada sobre características complementarias.

## Limitaciones

* El emulador permite validar el flujo funcional de la aplicación, pero no la impresión real.
* Algunas funcionalidades del SDK de Urovo no pueden validarse completamente sin hardware físico.

## Consideraciones de arquitectura

Debido al tiempo asignado para la prueba técnica, se implementó una versión simplificada de Clean Architecture.

Se priorizó:

* Correcta separación de responsabilidades.
* Código mantenible.
* Escalabilidad futura.
* Cumplimiento de los requerimientos solicitados.

Sobre la incorporación de capas o funcionalidades adicionales que no formaban parte del alcance solicitado.


---

# 4. Dependencias utilizadas

* Jetpack Compose
* Navigation Compose
* Hilt
* Retrofit
* OkHttp
* Kotlin Coroutines
* StateFlow
* Coil
* Urovo Printer SDK

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
├── infrastructure
│   ├── dto
│   ├── remote
│   └── repositories
│
└── presentation
    ├── screens
    ├── components
    └── viewmodels
```

---

# Funcionalidades implementadas

* Consulta de imágenes de gatos desde API pública.
* Visualización de listado de gatos.
* Pantalla de detalle.
* Navegación entre pantallas.
* Manejo de estados de carga.
* Manejo de errores.
* Impresión de imágenes mediante SDK de Urovo.
* Inyección de dependencias con Hilt.

---

# Autor

David Arias
