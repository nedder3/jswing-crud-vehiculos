# JSwing CRUD Vehículos

<!-- Optional logo/banner: place it in docs/assets/ and reference it here (supports light/dark on GitHub). -->
<!-- <p align="center"><img src="docs/assets/banner.png" alt="JSwing CRUD Vehículos" width="720"/></p> -->

[![standard-readme compliant](https://img.shields.io/badge/readme%20style-standard-brightgreen.svg?style=flat-square)](https://github.com/RichardLitt/standard-readme)
[![license](https://img.shields.io/badge/license-MIT-blue.svg?style=flat-square)](LICENSE)
[![version](https://img.shields.io/badge/version-1.0.0-blue.svg?style=flat-square)](https://github.com/nedder3/jswing-crud-vehiculos/releases)
[![language](https://img.shields.io/badge/language-Java%2017-ffca28.svg?style=flat-square)]()
[![framework](https://img.shields.io/badge/framework-Swing-000000.svg?style=flat-square)]()
[![build](https://img.shields.io/badge/build-Maven-ffca28.svg?style=flat-square)]()

> Aplicación de escritorio para gestión de vehículos con interfaz Swing, persistencia en memoria y arquitectura layered.

## Table of contents

- [Background](#background)
- [Features](#features)
- [Tech stack](#tech-stack)
- [Quick Start](#quick-start)
- [Installation](#installation)
- [Usage](#usage)
- [Architecture and diagrams](#architecture-and-diagrams)
- [Contributing](#contributing)
- [License](#license)

## Background

Sistema de gestión de vehículos para demostración de arquitectura layered en Java Swing. Implementa un CRUD completo con interfaz gráfica, persistencia en memoria y pruebas unitarias.

## Features

- **Interfaz gráfica:** Tabla de vehículos con formulario de edición
- **Persistencia:** Almacenamiento en memoria con ArrayList
- **Arquitectura:** Layered (Model/DAO/Service/UI)
- **Pruebas:** TDD con JUnit

## Tech stack

| Layer | Technology | Notes |
|------|-----------|-------|
| Language | Java 17 | LTS |
| Framework | Swing | Desktop UI |
| Build | Maven | Dependency management |
| Tests | JUnit 5 | TDD |

## Quick Start

```bash
# Clone the repository
git clone git@github.com:nedder3/jswing-crud-vehiculos.git
cd jswing-crud-vehiculos

# Build and run
mvn clean package
java -jar target/jswing-crud-vehiculos-1.0.0.jar
```

## Installation

```bash
# Prerequisites
- Java 17
- Maven 3.9.9

# Build
mvn clean package
```

## Usage

```java
// Ejemplo de uso del DAO
AutomovilDao dao = new AutomovilDao();
Automovil auto = new Automovil("Toyota", "Corolla", "1.8L", "Blanco", "ABC123", 4);
// Crear
Automovil creado = dao.crear(auto);

// Leer
List<Automovil> todos = dao.leerTodos();

// Actualizar
creado.setModelo("Camry");
dao.actualizar(creado);

// Eliminar
dao.eliminar(creado.getId());
```

## Architecture and diagrams

Cy generates the flow/mechanism diagrams in [`diagramas/`](diagramas/). They are
viewed in Obsidian (canvas/mermaid) or rendered from the vault. One figure = one
assertion.

Directory tree:

```text
src/
  main/
    java/
      com/
        tienda/
          automoviles/
            model/        # Entidades
            dao/          # Acceso a datos
            service/      # Lógica de negocio
            ui/           # Interfaz gráfica
  test/
    java/
      com/
        tienda/
          automoviles/
            dao/          # Pruebas del DAO
```

```mermaid
flowchart TD
  U[User] -->|acts on| SHELL[MainApp / Frame]
  SHELL -->|inicia operacion| AutomovilService
  AutomovilForm -->|lee input/escribe estado| AutomovilService
  AutomovilService -->|delega CRUD| AutomovilDao
  AutomovilDao -->|lee/escribe/actualiza| Memory[(ArrayList<Automovil>)]
  Memory -->|retorna datos| AutomovilDao
  AutomovilDao -->|retorna objeto/lista| AutomovilService
  AutomovilService -->|actualiza vista| MainApp
  AutomovilService -->|actualiza tabla| CatalogoPanel
```

## Contributing

Read [CONTRIBUTING.md](CONTRIBUTING.md). Key rules:
- Conversational commits with scope: `feat(jswing): ...`, `fix(jswing): ...`, `docs(jswing): ...`
- TDD: tests before declaring done.
- JSDoc on every public symbol.

See [CHANGELOG.md](CHANGELOG.md) for the change history.

## License

[MIT](LICENSE) © Ariel Jaime