# Semana 08: Colecciones y Generics - ConnectFast

## 👤 Información del Estudiante
- **Nombre**: William Andres Aguilera Lasprilla
- **Ficha**: 3228973A
- **Dominio**: Empresa de Telecomunicaciones "ConnectFast"
- **Fecha**: 10/12/2025

## 📝 Descripción del Proyecto
Esta versión marca la refactorización completa del sistema para migrar de estructuras ineficientes (como arrays o ArrayListes simples) a **Colecciones profesionales** (`HashMap` y `ArrayList`) utilizando **Generics**.

El gestor de planes ha sido optimizado para realizar búsquedas instantáneas (O(1)) por código de plan, además de incorporar métodos avanzados de filtrado y estadísticas para análisis de negocio.

## 📦 Colecciones Utilizadas y Generics

El componente clave refactorizado es `GestorPlanes.java`.

| Estructura | Declaración | Propósito | Eficiencia |
| :--- | :--- | :--- | :--- |
| **`HashMap`** | `Map<String, ServicePlan>` | Almacena los planes por su código (`planCode`) para realizar **búsquedas O(1)**. | Búsqueda Instantánea |
| **`ArrayList`** | `List<ServicePlan>` | Se mantiene como historial para la **iteración ordenada** (filtrado, estadísticas) y para la demostración de Generics. | Iteración Ordenada |

**Generics:** Se usa la sintaxis `List<ServicePlan>` y `Map<String, ServicePlan>` en todas las declaraciones de colecciones para garantizar la seguridad de tipos (`type safety`) y evitar *warnings* de compilación.

## 🔍 Operaciones Implementadas

La clase `GestorPlanes` ahora incluye las siguientes operaciones de negocio y estadísticas (Ejercicio 3):

### CRUD Mejorado
* **Agregar Plan:** Implementa validación de duplicados utilizando `planesPorCodigo.containsKey()` (O(1)).
* **Buscar Plan:** Implementa la búsqueda directa usando `planesPorCodigo.get(codigo)` (O(1)).
* **Eliminar Plan:** Sincroniza la eliminación en ambas colecciones (`HashMap` y `ArrayList`).

### Filtrado y Estadísticas
* **`filtrarPorTipo(String tipo)`**: Retorna una lista de planes que coinciden con la categoría dada.
* **`calcularIngresoMensualTotal()`**: Suma el precio mensual de todos los planes activos.
* **`calcularPromedioMensual()`**: Calcula el precio promedio de todos los planes.
* **`obtenerPlanMasRapido()`**: Identifica el plan con la mayor velocidad (`speedMbps`).
* **`contarPlanesPorTipo()`**: Retorna un `Map<String, Integer>` con el conteo de cuántos planes hay de cada tipo (Residencial, Gamer, Empresarial).

## 🚀 Cómo Ejecutar

El sistema se ejecuta mediante un menú interactivo en la consola, que demuestra cada funcionalidad (Ejercicio 4).

### Desde IntelliJ IDEA:
1.  Verifique que la carpeta `src` de `semana-08` esté marcada como `Source Root`.
2.  Haga clic derecho sobre `Main.java`.
3.  Seleccione **`Run 'Main.main()'`**.

### Captura de Pantalla (Salida de Carga Inicial y Menú)
La ejecución inicial demuestra la carga de datos exitosa:

```text
C:\...\semana-08 com.connectfast.Main
🚀 Iniciando Gestor de Planes ConnectFast...
✅ PLAN REGISTRADO: Básico Hogar (Código: PLAN-100)
✅ PLAN REGISTRADO: Gamer Pro (Código: PLAN-G500)
✅ PLAN REGISTRADO: Emprendedor (Código: PLAN-E200)
✅ PLAN REGISTRADO: Premium Plus (Código: PLAN-300)

========= GESTOR DE PLANES CONNECTFAST (S08) =========
1. Agregar Plan
2. Buscar Plan por Código (Búsqueda O(1))
3. Eliminar Plan
4. Mostrar Catálogo Completo
5. FILTRAR Planes por Tipo (Residencial, Empresarial, Gamer)
6. MOSTRAR Estadísticas Avanzadas
0. Salir
=====================================================
Ingrese una opción: