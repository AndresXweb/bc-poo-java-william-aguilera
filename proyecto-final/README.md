# Proyecto Final: Sistema de Gestión de Telecomunicaciones ConnectFast

## Información del Estudiante

| Campo | Valor |
|-------|-------|
| **Nombre** | William Andres Aguilera Lasprilla |
| **Ficha** | 3228973A |
| **Dominio** | Empresa de Telecomunicaciones "ConnectFast" |
| **Fecha de entrega** | 10/12/2025 |

## Descripción del Sistema

ConnectFast es un sistema de gestión backend diseñado para proveedores de servicios de internet (ISP). Su propósito principal es administrar el ciclo de vida de los planes de servicio que ofrece la compañía, permitiendo centralizar la configuración de reglas de negocio complejas como límites de ancho de banda, acuerdos de nivel de servicio (SLA) para empresas y parámetros de calidad para servicios especializados como gaming.

El sistema resuelve el problema de la inconsistencia en la creación de ofertas comerciales mediante validaciones estrictas en el código. Permite a los administradores crear, consultar, modificar y eliminar planes (CRUD), así como generar reportes estadísticos sobre la oferta actual. Además, el sistema asegura la persistencia de los datos mediante archivos binarios, garantizando que la información del catálogo no se pierda al reiniciar la aplicación.

## Arquitectura del Proyecto

### Diagrama de Clases
*(Nota: El diagrama UML se encuentra en la carpeta docs/ bajo el nombre diagrama-clases.png)*

### Estructura de Paquetes

| Paquete | Contenido | Clases Principales |
|---------|-----------|--------------------|
| `com.connectfast.modelo` | Entidades del negocio y jerarquía de clases | ServicePlan, PlanResidencial, PlanEmpresarial, PlanGamer |
| `com.connectfast.servicio` | Lógica de negocio y persistencia | GestorPlanes |
| `com.connectfast.interfaces` | Contratos de comportamiento | Auditable, Facturable, Promocionable |
| `com.connectfast.excepciones` | Manejo de errores del dominio | PlanInvalidoException, PlanNoEncontradoException, VelocidadInsuficienteException |
| `com.connectfast.util` | Utilidades de entrada/salida | Consola |
| `com.connectfast` | Punto de entrada | Main |

## Aplicación de Conceptos POO

### Encapsulación
Todos los atributos de las clases de modelo están definidos como `private` o `protected`. El acceso se realiza exclusivamente a través de métodos getters y setters que incluyen validaciones de negocio.
* **Ejemplo 1:** En `PlanGamer`, el setter de latencia valida que el valor esté entre 1 y 50 ms.
* **Ejemplo 2:** En `ServicePlan`, se impide la creación de planes con precios negativos o nombres vacíos.

### Herencia
Se implementó una jerarquía donde `ServicePlan` actúa como la clase padre que contiene los atributos comunes (código, nombre, precio, velocidad).
* **Clase Padre:** `ServicePlan`
* **Clases Hijas:** `PlanResidencial`, `PlanEmpresarial`, `PlanGamer`
* **Justificación:** Esta estructura evita la duplicación de código y permite tratar todos los tipos de planes de manera uniforme en las colecciones.

### Polimorfismo
* **Sobrescritura (@Override):** Métodos como `calcularCostoInstalacion()` y `obtenerDescripcion()` tienen implementaciones diferentes en cada clase hija. Por ejemplo, el costo de instalación es gratuito para planes residenciales pero tiene costo para empresariales y gamers.
* **Interfaces:** La implementación de `Promocionable` permite que solo ciertos planes (Residencial y Gamer) respondan al método `aplicarPromocion()`.
* **Colecciones Polimórficas:** El `GestorPlanes` utiliza una lista `List<ServicePlan>` que puede almacenar instancias de cualquiera de las tres subclases.

### Abstracción
* **Clase Abstracta:** `ServicePlan` no puede ser instanciada directamente, obligando a usar las clases concretas. Define métodos abstractos como `obtenerBeneficios()` que obligan a las hijas a definir sus propias características.
* **Interfaces:**
    * `Auditable`: Obliga a registrar un historial de cambios.
    * `Facturable`: Estandariza la generación de facturas.
    * `Promocionable`: Define el comportamiento para aplicar descuentos.

### Excepciones Personalizadas
Todas son de tipo *Checked Exception* (heredan de Exception) para forzar su manejo:
1. `PlanInvalidoException`: Se lanza al intentar crear un objeto con datos que violan las reglas de negocio.
2. `PlanNoEncontradoException`: Se lanza al buscar o eliminar un plan con un código inexistente.
3. `VelocidadInsuficienteException`: Excepción específica lanzada por `PlanGamer` si la velocidad asignada es inferior a 200 Mbps.

### Colecciones y Estructuras de Datos
* **HashMap (`Map<String, ServicePlan>`):** Utilizado para almacenar los planes usando el código como clave. Permite operaciones de búsqueda y validación de duplicados con complejidad O(1) (instantánea).
* **ArrayList (`List<ServicePlan>`):** Utilizado para mantener un historial iterable de los planes, facilitar el ordenamiento y los filtros.

## Funcionalidades del Sistema

### Menú Principal
El sistema cuenta con un menú interactivo en consola con las siguientes opciones:
1. Agregar Nuevo Plan (Residencial, Gamer o Empresarial)
2. Buscar Plan por Código
3. Actualizar Plan (Modificar precio o velocidad)
4. Eliminar Plan
5. Listar Todos los Planes
6. Listar Ordenados por Precio
7. Filtrar por Tipo
8. Ver Estadísticas y Reportes
0. Salir (Guardar y Salir)

### Operaciones Implementadas

| Operación | Descripción | Estado |
|-----------|-------------|--------|
| Agregar Plan | Permite crear cualquiera de los 3 tipos de planes con validación de datos. | Completado |
| Buscar por Código | Recupera la información de un plan instantáneamente. | Completado |
| Actualizar | Modifica atributos mutables y persiste los cambios. | Completado |
| Eliminar | Remueve un plan del sistema previa confirmación. | Completado |
| Listar Ordenado | Muestra el catálogo ordenado por precio ascendente usando Comparable. | Completado |
| Filtrar | Muestra solo los planes de una categoría específica. | Completado |
| Persistencia | Guarda y carga los datos automáticamente en `planes.dat`. | Completado |

## Cómo Ejecutar

### Requisitos
* Java JDK 17 o superior.
* IDE: IntelliJ IDEA (recomendado) o consola de comandos.

### Compilación y Ejecución (Desde Terminal)

1. Navegar a la carpeta del proyecto final:
   ```bash
   cd proyecto-final

2. Compilar el código fuente:
Bash
javac -d bin src/com/connectfast/Main.java src/com/connectfast/*/*.java

3. Ejecutar la aplicación:
Bash
java -cp bin com.connectfast.Main

## Reflexión Personal

### Desafíos Encontrados
El principal reto fue la implementación de la persistencia de datos y asegurar la consistencia entre las dos colecciones utilizadas (HashMap y ArrayList). Sincronizar ambas estructuras al momento de eliminar o cargar datos desde el archivo requirió un análisis cuidadoso del flujo de datos. Otro desafío importante fue la correcta propagación de las excepciones personalizadas desde el modelo hasta la capa de presentación (Main) sin romper la ejecución del programa.

### Aprendizajes Clave
Durante el desarrollo de este proyecto, comprendí la importancia real de las interfaces como contratos de comportamiento, lo que permite desacoplar la lógica. También aprendí que el polimorfismo reduce drásticamente la cantidad de código condicional (if/else) necesario para manejar diferentes tipos de objetos. Finalmente, el uso de HashMaps demostró ser superior para búsquedas rápidas en comparación con iteraciones sobre listas.

### Mejoras Futuras
Si dispusiera de más tiempo, implementaría una base de datos relacional (MySQL) para reemplazar el archivo binario, lo que permitiría consultas más complejas. También agregaría una interfaz gráfica (GUI) con JavaFX para mejorar la experiencia del usuario final.