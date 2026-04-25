``` mermaid
classDiagram
    class Persona {
        +String dni
        +String nombre
        +String apellido
        +String celular
        +Date fecha_nacimiento
    }
    class Docente {
        +String titulo
        +String universidad_grad
        +String cargo
    }
    class Estudiante {
        +String localidad_nac
        +String localidad_res
        +String familiar_contacto
        +String cel_contacto
    }
    class Administrador {
        +String nombre_usuario
        +String email
    }
    class Periodo {
        +Date fecha_inicio
        +Date fecha_fin
    }
    class Inscripcion_Materia {
        +Date fecha
    }
    class Examen {
        +Date fecha
        +Float nota
    }
    class Carrera {
        +String nombre
    }
    class Inscripcion {
        +Date fecha
    }
    class Materia {
        +int codigo
        +String nombre
        +String programa
        +int horas
    }
    class Plan_Estudio {
        +String version
        +String plan
    }
    class Requisito {
        Condicion condicion
    }
    class Condicion {
        <<enumeration>>
        REGULAR
        APROBADO
    }

    %% Herencia
    Administrador --|> Persona
    Docente --|> Persona
    Estudiante --|> Persona

    %% Relaciones
    Estudiante "1" -- "*" Inscripcion_Materia
    Inscripcion_Materia "*" -- "1" Materia
    Plan_Estudio "1..*" *-- "1..*" Materia
    Carrera "1" -- "1..*" Plan_Estudio

    %% Relación de Docentes/Materias
    Docente "1" -- "1..*" Periodo
    Periodo "1..*" -- "1" Materia

    %% Relación Materias rendidas
    Estudiante "1" -- "*" Examen
    Examen "*" -- "1" Materia
    
    %% Relación de Correlatividades
    Materia "1" -- "*" Requisito
    Requisito "*" -- "1" Materia

    %% Relación de Inscripción en Carreras
    Estudiante "1" -- "1..*" Inscripcion
    Inscripcion "*" -- "1" Carrera
```
