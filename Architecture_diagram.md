``` mermaid
flowchart TD
    UI["Interfaz de Usuario"]

    subgraph S1 ["Gestión de Administradores"]
        CU["Crear Usuario"]
        EU["Editar Usuario"]
    end

    subgraph S2 ["Gestión de Docentes"]
        CD["Crear Docente"]
        ED["Editar Docente"]

    end

    subgraph S3 ["Gestión de Estudiantes"]
        CE["Crear Estudiante"]
        EE["Editar Estudiante"]
    end

    subgraph S4 ["Gestión de Materias"]
        CM["Crear Materia"]
        EM["Editar Materia"]
    end

    subgraph S5 ["Gestión de Carreras"]
        CC["Crear Carrera"]
        EC["Editar Carrera"]
    end

    subgraph S6["Gestión de Planes de Estudio"]
        CPE["Crear Plan de Estudio"]
        EPE["Editar Plan de Estudio"]
    end

    DB["Base de Datos"]

    UI --> S1
    UI --> S2
    UI --> S3
    UI --> S4
    UI --> S5
    UI --> S6

    S1 --> DB
    S2 --> DB
    S3 --> DB
    S4 --> DB
    S5 --> DB
    S6 --> DB

```