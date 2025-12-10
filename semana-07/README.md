# Semana 07: Paquetes y Excepciones - ConnectFast

## 👤 Información del Estudiante
- **Nombre**: William Andres Aguilera Lasprilla
- **Ficha**: 3228973A
- **Dominio**: Empresa de Telecomunicaciones "ConnectFast"
- **Fecha**: 09/12/2025

## 📝 Descripción del Proyecto
Esta versión refactoriza el sistema ConnectFast para implementar una **arquitectura profesional** basada en paquetes, separando el modelo, el servicio y las excepciones.

El foco principal fue la **implementación de excepciones verificadas (Checked Exceptions)** para forzar el manejo de errores críticos de negocio (como datos inválidos o reglas incumplidas) mediante bloques `try-catch`, asegurando la robustez y la integridad de los datos de los planes.

## 📦 Estructura de Paquetes
src/ └── com/ └── connectfast/ ├── modelo/ # Entidades: ServicePlan, PlanResidencial, PlanEmpresarial, PlanGamer. ├── servicio/ # Lógica de Negocio: GestorPlanes (Contiene la lógica de negocio). ├── interfaces/ # Contratos: Facturable, Auditable, Promocionable. ├── excepciones/ # Errores del Dominio: PlanInvalidoException, PlanNoEncontradoException, etc. └── Main.java # Punto de entrada y demostración de casos de prueba.

## ⚠️ Excepciones Personalizadas (Checked)

Se crearon excepciones que extienden de `Exception`, obligando al uso de `throws` en los métodos críticos.

### 1. PlanInvalidoException
- **Tipo**: Checked (extends Exception)
- **Cuándo se lanza**: Cuando los datos básicos del plan son incorrectos.
- **Ejemplo**: Intentar asignar un precio mensual negativo o un código sin el prefijo `PLAN-`.

### 2. PlanNoEncontradoException
- **Tipo**: Checked (extends Exception)
- **Cuándo se lanza**: Se lanza desde `GestorPlanes` al buscar un plan por código que no existe en la colección.
- **Ejemplo**: Búsqueda de un plan inexistente (`gestor.buscarPlan("PLAN-999")`).

### 3. VelocidadInsuficienteException
- **Tipo**: Checked (extends Exception)
- **Cuándo se lanza**: Regla de negocio específica en `PlanGamer.java`.
- **Ejemplo**: Intentar crear un `PlanGamer` con menos de 200 Mbps (requisito mínimo de calidad).

## 🔧 Cambios Aplicados desde Semana 06
* **Reorganización**: El código fue movido de la raíz a la estructura de paquetes `com.connectfast.*`.
* **Abstracción de Errores**: Se reemplazó el uso de `IllegalArgumentException` (Unchecked) por nuestras excepciones Checked personalizadas.
* **Firma de Métodos**: Los constructores y setters de `ServicePlan` y sus subclases ahora declaran `throws PlanInvalidoException`.
* **Manejo en Main**: El `Main.java` usa bloques `try-catch` para manejar todos los fallos, asegurando la estabilidad del programa.

## 🚀 Cómo Ejecutar

### Desde terminal:
```bash
cd semana-07
# Compilación: Crea los archivos .class en la carpeta 'bin'
javac -d bin src/com/connectfast/*/*.java src/com/connectfast/*.java 

# Ejecución: Usa la ruta de clases para cargar el Main
java -cp bin com.connectfast.Main