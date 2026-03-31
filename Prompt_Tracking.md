# Prompts utilizados en análisis de requerimientos y riesgos del sistema

## 1) Introducción del contexto y obtención del Requirements_Analysis.md

*Inicio del prompt*
Estamos creando un sistema para la gestión de docentes y alumnos de la universidad, te presentaré el problema para entender el contexto, luego haremos
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
administradores y alumnos.
*Fin del prompt*

## 2) Iteración 1 sobre Requirements_Analysis.md

*Inicio del prompt*
Bien me interesa un SRS , pero antes vamos a iterar sobre los puntos que destacan sobre el proyecto te pasaré mas contexto para ver si hay que modificar o agregar más informacion en el Requirements_Analysis.md que ya tenemos.
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
los profesores, para cargar notas o consultar listados de sus alumnos." 
*Fin del Prompt*

## 3)Iteración sobre contexto y obtención de Requirements_Specifications.md
*Inicio del prompt*
Obtener un documento SRS formal con las especificaciones de los requerimientos obtenidos en los análisis previos. Te comparto algunas historias de
usuarios que entrevistamos para especificar funcionalidades puntuales:
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
mis alumnos sigan el programa de estudio de manera adecuada.”
*Fin del prompt*

## 4) Consulta basada en el formato del resultado de Prompt 3.
*Inicio del prompt*
¿Qué es IEEE 830 / ISO/IEC/IEEE 29148?
*Fin del prompt*

## 5) Consulta sobre framework/tecnologías a utilizar, descartamos shiftear a React para el frontend y asi evitar OverEngineering 

*Inicio del prompt*
El proyecto tendrá una pagina web con frontend y un servidor con base de datos en el backend. Yo estoy familiarizado con React Vite
para el frontend, ahora mi pregunta es para el backend.¿Con que lenguage/framework me recomiendas trabajar? tiene que ser Orientado a Objetos,
estoy familiarizado con Java pero quiero saber que posibilidades hay y cuales son las ventajas de cada una. ¿Es conveniente shiftear a otro framework
para el frontend como React?
*Fin del prompt*

## 6) Constulta sobre framework de Java + Mustache

*Inicio del prompt*
El proyecto tuvo una implementación inicial con Java+Mustache+Maven. Estoy familiarizado con Java y Maven pero no con Mustache. ¿Me explicas un poco
que es?
*Fin del prompt*

## 7) Obtencion de README.md báisco para presentación de proyecto en GitHub 
### (El REARME.md fue modificado posteriormente por los miembros del equipo).

*Inicio del prompt*
Dame un README Simple y llamativo para el repositorio de este proyecto.
*Fin del prompt*
## 8) Iteración 2 sobre Requirements_Analisis.md y obtención de risk_analisys.md 
### (El risk_analysis.md fue modificado posteriormente para agregar los riesgos identificados por el equipo).

*Inicio del prompt*
Necesito un analisis de riesgos  en base al siguiene analisis de requerimientos del sistema, considerar los siguientes tipos de riesgos:
- Riesgos técnicos -Riesgos organizacionales - Riesgos de planificación - Riesgos humanos.
Clasifícar por probabilidad e impacto.
**Aquí se introdujo la ultima versión del Requirements_Analysis.md para una iteración directa con el con
texto ya desarrollado (Ver Requirements_Analysis.md)**
*Fin del Prompt*
