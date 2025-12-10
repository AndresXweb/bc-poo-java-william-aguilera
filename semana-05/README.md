# Semana 05 - Polimorfismo

## Descripcion

En esta quinta semana implemente polimorfismo avanzado en ConnectFast aplicando sobrecarga de metodos, sobrescritura con @Override, metodos polimorficos y demostracion completa de dynamic binding. El objetivo fue hacer el sistema mas flexible y extensible.

## Conceptos Implementados

### Sobrecarga (Overloading)

Implemente tres versiones del metodo buscarPlan en la clase GestorPlanes:

buscarPlan(String codigo) - Busca un plan especifico por codigo
buscarPlan(double precioMin, double precioMax) - Busca planes en rango de precio
buscarPlan(int velocidadMinima) - Busca planes con velocidad minima

Estas tres versiones tienen el mismo nombre pero diferentes parametros. El compilador selecciona automaticamente cual ejecutar segun los argumentos que se pasen.

### Sobrescritura (Overriding)

Las tres subclases (PlanResidencial, PlanEmpresarial, PlanGamer) sobrescriben dos metodos de ServicePlan:

mostrarInformacion() - Cada tipo muestra su informacion especifica ademas de la basica

calcularPrecioConDescuento(int meses) - Cada tipo aplica su propia politica de descuentos

Todos los metodos sobrescritos usan la anotacion @Override y llaman a super cuando es necesario.

### Metodos Polimorficos

Cree varios metodos en GestorPlanes que aceptan ServicePlan como parametro y funcionan con cualquier subclase:

procesarPlan(ServicePlan plan) - Procesa cualquier tipo de plan
calcularPrecioAnual(ServicePlan plan) - Calcula precio anual de cualquier plan
compararPlanes(ServicePlan plan1, ServicePlan plan2) - Compara dos planes
generarReporte() - Genera reporte de todos los planes
obtenerPlanMasRapido() - Encuentra el plan con mayor velocidad

Estos metodos demuestran polimorfismo porque aceptan la clase padre pero funcionan correctamente con cualquier subclase.

### Dynamic Binding

En el Main demostre dynamic binding creando ArrayList de tipo ServicePlan que contiene objetos de diferentes tipos. Cuando llamo a metodos como mostrarInformacion o calcularPrecioConDescuento, Java ejecuta automaticamente la version correcta segun el tipo real del objeto.

## Archivos Creados

ServicePlan.java - Clase padre (copiada de semana 04)

PlanResidencial.java - Subclase con sobrescritura (copiada de semana 04)

PlanEmpresarial.java - Subclase con sobrescritura (copiada de semana 04)

PlanGamer.java - Subclase con sobrescritura (copiada de semana 04)

GestorPlanes.java - Clase NUEVA con sobrecarga y metodos polimorficos

Main.java - Demo completa de polimorfismo

POLIMORFISMO.md - Documento con analisis detallado

## Archivo GestorPlanes

Esta es la clase nueva de esta semana. Contiene:

Sobrecarga: Tres versiones del metodo buscarPlan
Metodos polimorficos: procesarPlan, calcularPrecioAnual, compararPlanes, generarReporte
ArrayList polimorfico: Almacena planes de diferentes tipos
Uso de instanceof: Para verificar tipos y hacer casting cuando es necesario

## Main - Demostraciones

El programa Main incluye 9 demostraciones diferentes:

1. Sobrecarga: Llama a las tres versiones de buscarPlan
2. Metodos polimorficos: Procesa planes individuales
3. ArrayList polimorfico: Recorre lista con diferentes tipos
4. Dynamic binding: Muestra como se ejecutan metodos segun tipo real
5. Calculos polimorficos: Calcula precios anuales
6. Comparacion: Compara planes de diferentes tipos
7. Reporte general: Estadisticas de todos los planes
8. Plan mas rapido: Busca el plan con mayor velocidad
9. instanceof y casting: Accede a metodos especificos de cada tipo

## Como ejecutar

Desde la carpeta semana-05:

```
javac *.java
java Main
```

El programa muestra todas las demostraciones de polimorfismo con salida clara y organizada.

## Salida Esperada

El programa muestra:

Proceso de agregar planes al gestor
Demostracion de sobrecarga con tres busquedas diferentes
Procesamiento polimorfico de planes individuales
Recorrido de ArrayList polimorfico
Dynamic binding mostrando tipo real vs tipo de variable
Calculos de precios anuales con descuentos diferentes
Comparaciones entre planes
Reporte con estadisticas
Busqueda del plan mas rapido
Uso de instanceof para acceder a metodos especificos

## Diferencias con Semana 04

En semana 04 implemente herencia basica y polimorfismo simple con array polimorfico. En semana 05 agregue:

Sobrecarga de metodos (no existia antes)
Clase GestorPlanes con metodos polimorficos (nueva)
Mas demostraciones de dynamic binding
Uso de instanceof y casting
Analisis completo en POLIMORFISMO.md

La jerarquia de clases se mantuvo igual (ServicePlan como padre con tres subclases) pero ahora aprovecho mejor el polimorfismo.

## Beneficios del Polimorfismo

Con polimorfismo el codigo es:

Mas flexible: Un metodo funciona con todos los tipos
Mas extensible: Puedo agregar nuevos tipos sin modificar codigo existente
Mas mantenible: Cambios en tipos especificos no afectan el resto
Mas limpio: Elimina if-else para verificar tipos

## Validaciones

Mantuve todas las validaciones de semanas anteriores. GestorPlanes agrega validacion para no aceptar planes nulos y verifica parametros en los metodos de busqueda.

## Sobrecarga vs Sobrescritura

Sobrecarga: Mismo nombre, diferentes parametros, misma clase, compilacion
Sobrescritura: Mismo nombre, mismos parametros, clase padre e hija, ejecucion

Ambas son formas de polimorfismo pero funcionan diferente. La sobrecarga se decide en tiempo de compilacion y la sobrescritura en tiempo de ejecucion (dynamic binding).

## instanceof y Casting

Use instanceof para verificar el tipo real de un objeto cuando necesito acceder a metodos que solo existen en una subclase especifica. Por ejemplo obtenerBeneficios() solo existe en las subclases, no en ServicePlan.

Despues de verificar con instanceof hago casting para convertir de ServicePlan al tipo especifico y poder llamar al metodo.

## Notas

El polimorfismo es uno de los pilares de POO y hace el codigo mucho mas flexible. En este proyecto me permitio crear metodos que funcionan con cualquier tipo de plan sin necesidad de escribir codigo separado para cada uno.

El documento POLIMORFISMO.md contiene analisis detallado con ejemplos de codigo y explicaciones de como funciona cada concepto.