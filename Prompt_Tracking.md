#Prompts utilizados en análisis de requerimientos y riesgos del sistema
1)Estamos creando un sistema para la gestión de docentes y alumnos de la universidad, te presentaré el problema para entender el contexto, luego haremos
un analisis de requerimientos.
La oficina de alumnos enfrenta dificultades por el uso de sistemas viejos, planillas y
procesos manuales. Necesitan centralizar la información académica para mejorar la
gestión de estudiantes, profesores y materias. Además, buscan simplificar la inscripción,
controlar correlatividades, registrar calificaciones y organizar equipos docentes.
A futuro, desean hacer un seguimiento del rendimiento de los alumnos para ver su
progreso, detectar posibles problemas si están en riesgos de abandonar o por el
contrario, si van muy bien, poder ofrecerles algún programa especial.
Entre los desafíos que el sistema debe abordar encontramos que se debe integrar en un
único sistema centralizado toda la información académica, se debe permitir la gestión
completa de estudiantes y profesores, se debe manejar la oferta académica, planes de
estudio y correlatividades. También hay que procurar automatizar la validación de los
requisitos de inscripción, llevar un historial académico de cada estudiante, gestionar la
asignación de docentes y roles en cada cátedra e incluir una herramienta de seguimiento
de progreso estudiantil. Los principales actores del sistema involucran a docentes,
administradores y alumnos. (Iteración 1 de Requirements_Analysis.md)

2)Bien me interesa un SRS , pero antes vamos a iterar sobre los puntos que destacan sobre el proyecto te pasaré mas contexto para ver si hay que modificar o agregar más informacion en el .md que ya tenemos.
Narrativa: Entrevista con la Oficina de Alumnos
Imaginemos la siguiente situación: tu equipo de desarrollo ha sido contactado por la
Oficina de Alumnos de la Universidad, que busca modernizar y optimizar la gestión
de la información académica de sus estudiantes. Tras una primera entrevista
informal, a continuación se presenta un resumen de lo conversado:
"..Mirá, la verdad es que hoy estamos trabajando con planillas, algunos sistemas
viejos que no se conectan entre sí, y además la comunicación con los docentes y
los chicos (estudiantes) se vuelve un desafío constante. Por eso necesitamos algo
que nos centralice la información y nos simplifique la vida."
" Lo principal es poder llevar un buen registro de nuestros estudiantes: sus datos
personales, información de contacto, y si son ingresantes o ya avanzados en la
carrera. También es clave gestionar a nuestros profesores: quiénes son y qué
materias están dictando."
" Otro punto clave es la oferta académica. Necesitamos poder cargar todas las
materias de cada carrera, con sus planes de estudio y, algo súper importante, las
correlatividades. Esto último hoy es un dolor de cabeza: muchas veces un
estudiante quiere inscribirse en una materia y no cumple los requisitos, y no
tenemos forma automática de validar eso."
" Y en cuanto a los estudiantes, nos interesa saber qué materias están cursando en
cada momento, cuáles pueden cursar según su avance en el plan y las correlativas
que ya aprobaron. Además, necesitamos un registro claro de la nota final de
aprobación de cada materia. "" También queremos poder asignar a los docentes a las materias según el período
académico, y saber si son responsables de cátedra, jefes de trabajos prácticos o
ayudantes. Eso nos permitiría organizar mucho mejor los equipos docentes."
" Por último, y más como un objetivo a futuro, nos gustaría contar con una
herramienta que nos permita hacer seguimiento de los estudiantes: ver su progreso,
detectar si están en riesgo de abandonar o, por el contrario, si van muy bien y
podríamos ofrecerles algún programa especial. No sabemos exactamente cómo
sería, pero creemos que un sistema podría ayudarnos."
" Los principales usuarios de este sistema serían: el personal de la Oficina de
Alumnos, como administradores; los estudiantes, para consultar su información; y
los profesores, para cargar notas o consultar listados de sus alumnos." (Iteración 2 sobre Requirements_Analysis)

3)Obtener un documento SRS formal con las especificaciones de los requerimientos obtenidos en los análisis previos.Te comparto algunas historias de usuarios
que entrevistamos para especificar funcionalidades:
Historias de Alumno:
1- "Como Alumno quiero consultar mi historial académico (materias que curso/notas)
para tener un seguimiento de mi progreso estudiantil."
2- "Como Alumno quiero saber qué materias se me permiten cursar después de haber
regularizado/aprobado la materia anterior."
3- "Como Alumno quiero inscribirme a materias con validación automática de
correlatividades."
Historias de Profesor:
1- ”Como profesor, quiero cargar y modificar las notas finales, para que los alumnos
puedan visualizar correctamente su progreso académico”
2- ”Como profesor, quiero comunicarme con alumnos y colegas, para coordinar y
notificar cambios en la materia (parciales, horarios, aulas, consultas).”
3- ”Como profesor, quiero acceder a la información académica de mis alumnos, para
verificar si cumplen con los requisitos para cursar o rendir un examen.”
4- “Como profesor, quiero cargar material en cada materia sin restricciones, para que
mis alumnos sigan el programa de estudio de manera adecuada.”(Obtención de Requirements_Specifications.md"

4) ¿Qué es IEEE 830 / ISO/IEC/IEEE 29148? (Consulta basada en el formato del resultado de Prompt 3.)
5) El proyecto tendrá una pagina web con frontend y un servidor con base de datos en el backend. Yo estoy familiarizado con React Vite
para el frontend, ahora mi pregunta es para el backend.¿Con que lenguage/framework me recomiendas trabajar? tiene que ser Orientado a Objetos,
estoy familiarizado con Java pero quiero saber que posibilidades hay y cuales son las ventajas de cada una.
6)El proyecto tuvo una implementación inicial con Java+Mustache+Maven. Estoy familiarizado con Java y Maven pero no con Mustache. Me explicas un poco que es?
Es conveniente shiftear a otro framework para el frontend como React? (Descartamos shiftear a React para evitar OverEngineering) 
7) Dame un README Simple y llamativo para el repositorio de este proyecto (Obtención de README básico que fue modificado posteriormente por los miembros del equipo).
8) Necesito un analisis de riesgos  en base al siguiene analisis de requerimientos del sistema, considerar los siguientes tipos de riesgos:
- Riesgos técnicos -Riesgos organizacionales - Riesgos de planificación - Riesgos humanos.
Clasifícar por probabilidad e impacto.
# Requerimientos del Sistema

## 1. Problema a Resolver

Actualmente, la gestión académica de la universidad presenta múltiples inconvenientes:

- Uso de **planillas y sistemas aislados**
- Falta de **integración entre módulos**
- Procesos **manuales y propensos a errores**
- Dificultad para validar:
  - Correlatividades
  - Inscripciones
- Baja **transparencia** para estudiantes y docentes
- Comunicación ineficiente entre actores

### Objetivo del sistema

Diseñar e implementar una plataforma web que permita gestionar la información académica de forma:

- Centralizada
- Consistente
- Segura
- Escalable
- Fácil de usar

El sistema deberá cubrir la gestión de estudiantes, docentes, materias, inscripciones y desempeño académico.

---

## 2. Usuarios del Sistema

### 2.1 Estudiante
- Consulta de información académica
- Inscripción a materias
- Visualización de historial y progreso

### 2.2 Profesor
- Consulta de materias asignadas
- Visualización de alumnos
- Carga de notas

### 2.3 Administrador Académico
- Gestión integral del sistema
- Administración de datos académicos

---

## 3. Funcionalidades

### 3.1 Gestión de Estudiantes
- Registro de datos personales
- Información de contacto
- Estado académico (ingresante / avanzado)
- Consulta de historial académico
- Visualización de materias cursadas y aprobadas

---

### 3.2 Gestión de Docentes
- Registro de docentes
- Asignación a materias
- Definición de rol docente:
  - Responsable de cátedra
  - JTP
  - Ayudante

---

### 3.3 Gestión Académica

#### Carreras y Planes de Estudio
- Definición de carreras
- Gestión de planes de estudio
- Asociación de materias a planes

#### Materias
- Alta, baja y modificación
- Asociación a carreras

#### Correlatividades
- Definición de requisitos entre materias
- Validación automática al momento de inscripción

---

### 3.4 Inscripciones y Cursadas
- Inscripción a materias
- Validación de:
  - Correlativas
  - Cupos
- Registro de cursadas activas
- Consulta de estado de inscripción

---

### 3.5 Gestión de Notas
- Registro de notas finales
- Historial académico del estudiante
- Consulta de rendimiento

---

### 3.6 Asignación Académica
- Asignación de docentes a materias
- Asociación a períodos académicos

---

### 3.7 Consultas
- Materias disponibles
- Materias cursadas
- Listado de alumnos por materia
- Información académica del estudiante

---

### 3.8 Funcionalidades Futuras (No Funcionales / Analítica)
- Seguimiento de progreso académico
- Detección de riesgo de abandono
- Identificación de estudiantes destacados

---

## 4. Restricciones Técnicas

- Backend orientado a objetos (requisito académico)
- Arquitectura cliente-servidor
- Persistencia en base de datos relacional
- Aplicación web

---

## 5. Tamaño del Equipo

- 4 Integrantes en el momento que se escribe este documento.

---

## 6. Tecnologías Elegidas y Justificación

### Frontend
**Java+Mustache**
- Motor de plantillas
- HTML dinámico a partir de templates+datos
- Arquitectura de vistas separadas de lógica backend
### Backend
**Java + Maven**
- Orientado a objetos
- Fuertemente tipado
- Conservación de tecnologías usadas en IS-I
- Integración con BD

### Base de Datos
**SQLite**
- Adecuada para proyectos académicos
- Relacional
- Adecuada para integridad referencial

---

## 7. Plazo Estimado

- 3 Meses (Duración de la materia)

---

## 8. Cambios de Alcance

- Evolutivo durante el desarrollo

---

## 9. Problemas Encontrados

- Se documentarán durante el proceso

---

## 10. Organización del Equipo

- Metodología Kanban
- Uso de Git
- Gestión mediante Issues
- Trazabilidad mediante SRS
