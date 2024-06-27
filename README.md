![Java](https://raw.githubusercontent.com/David-Albarracin/README_MATERIALS/main/java.png)

Este es un proyecto realizado por Edgar David Albarracin Y Jorge Luis Osorio Silva.

## Agencia Vuelos Globales

# Requerimientos de entrega

1. Implementar Arquitectura hexagonal y vertical Slicing
2. Publicar el desarrollo del proyecto en un repositorio
3. El repositorio debe tener Readme donde se explique las tecnologías usadas en el desarrollo de la solución.
4. El repositorio debe contener los archivos SQL de creación de la base de datos e inserciones de parámetros necesarias para el funcionamiento de la aplicación.

### Introducción

En el mundo actual, el desarrollo de software es crucial para asegurar el funcionamiento eficiente y seguro de las aerolíneas. La tecnología ha transformado la operativa aérea, abarcando desde la gestión de vuelos y mantenimiento de aeronaves hasta la experiencia del cliente. En una industria donde la puntualidad, la seguridad y la satisfacción del cliente son esenciales, el software se convierte en un aliado indispensable.

Las aerolíneas enfrentan diversos desafíos que requieren soluciones tecnológicas avanzadas. Estos incluyen la gestión de grandes volúmenes de datos, la optimización de rutas de vuelo, el mantenimiento predictivo de aeronaves y la mejora de la experiencia del pasajero. Para abordar estos retos, las aerolíneas necesitan sistemas de software robustos, integrados y escalables.

### Caso Agencia Vuelos Globales

**Agencia:** Vuelos Globales

**Descripción:** Operador internacional con una flota diversa, múltiples aerolíneas asociadas y una extensa red de aeropuertos y destinos. Requiere una base de datos robusta para gestionar reservas de vuelos, mantenimiento de aviones y administración de tripulación.

### Evaluación de Base de Datos Existente

Se debe analizar la base de datos actual de Vuelos Globales para determinar si debe ser mantenida o reemplazada por una nueva solución.

### Arquitectura Hexagonal y Vertical Slicing

En este proyecto implementamos una arquitectura hexagonal y vertical slicing. Esto nos permite estructurar las carpetas de la siguiente manera:

- **airlines/**: Interfaces de usuario para el módulo de Aerolíneas.
  - **application/**: Lógica de aplicación, coordinando las acciones del dominio.
    - **domain/**: Lógica de negocio y reglas del dominio.
      - **model/**: Define las entidades y objetos de valor del dominio.
      - **repository/**: Define las interfaces para acceder a los datos del dominio.
    - **infrastructure/**: Implementación de servicios y acceso a datos.
      - **in/**: Interfaces y adaptadores para entrada de datos.
      - **out/**: Interfaces y adaptadores para salida de datos.

### Funcionamiento de las Tablas

Cada carpeta corresponde a una tabla de la base de datos, separada en aplicaciones para la lógica, dominio para las reglas de negocio, y la infraestructura para la implementación de servicios y acceso a datos.

### Front-end con Swing

Utilizamos Swing para el diseño simple del front-end, optimizando la visualización de tablas y consultas a la base de datos.

### Dificultades

Durante el desarrollo, enfrentamos dificultades principalmente en:

- Conexión de consultas a la base de datos y entrega de información al front-end.
- Ajuste de modelos con los repositorios.
- Mantenimiento de una arquitectura hexagonal.


