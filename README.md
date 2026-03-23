# 🎓 Sistema de Gestión Académica

Plataforma web para la **gestión integral de información académica** en universidades.
Permite administrar estudiantes, docentes, materias e inscripciones de forma **centralizada, segura y eficiente**.

---

## 🚀 Features principales

* 👨‍🎓 Gestión de estudiantes
* 👨‍🏫 Gestión de docentes
* 📚 Administración de materias y planes de estudio
* 🔗 Validación automática de correlatividades
* 📝 Inscripción a materias
* 📊 Registro de notas e historial académico
* 🔐 Sistema de autenticación y roles

---

## 🧠 Problema que resuelve

Actualmente muchas instituciones trabajan con:

* Planillas manuales
* Sistemas desconectados
* Procesos propensos a errores

Este sistema propone una solución moderna que:

* Centraliza la información
* Reduce errores administrativos
* Mejora la transparencia para estudiantes y docentes

---

## 🛠️ Tecnologías utilizadas

### Backend / Web

* Java
* Mustache (renderizado de vistas en servidor)
* Maven

### Base de datos

* SQLite

### Infraestructura

* Docker (opcional)

---

## 🏗️ Arquitectura

El sistema sigue una arquitectura **MVC (Model-View-Controller)** con separación en capas:

* **Controller** → Manejo de requests HTTP y navegación
* **Service** → Lógica de negocio
* **Repository** → Acceso a datos (persistencia)
* **View (Mustache)** → Renderizado de HTML en el servidor

---

## 👥 Usuarios del sistema

* **Estudiantes** → inscripción y consultas académicas
* **Docentes** → gestión de alumnos y carga de notas
* **Administradores** → control total del sistema

---

## ⚙️ Funcionalidades clave

* Inscripción a materias con validación de correlativas
* Gestión de cupos
* Historial académico por estudiante
* Asignación de docentes a materias
* Consultas académicas

---

## 📦 Instalación (modo desarrollo)

```bash
# Clonar repositorio
git clone <repo-url>

cd <project-folder>

# Ejecutar aplicación
mvn clean compile exec:java
```

La aplicación estará disponible en:
👉 http://localhost:8080

---

## 🐳 Uso con Docker (opcional)

```bash
docker-compose up --build
```

---

## 📌 Estado del proyecto

🚧 En desarrollo — Etapa de análisis y especificación de requerimientos (SRS).

---

## 🎯 Roadmap

* [ ] Sistema de analítica académica
* [ ] Detección de riesgo de abandono
* [ ] Dashboard de rendimiento

---

## 🤝 Contribución

Proyecto académico. Contribuciones por parte de alumnos integrantes del grupo con asesoramiento docente.

---

## 📄 Licencia

Uso educativo
