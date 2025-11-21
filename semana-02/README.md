# Semana 02 - Clases y Objetos Avanzadas

## Descripción

En esta segunda semana expandi el sistema de ConnectFast agregando nuevas clases y aplicando conceptos de relaciones entre objetos. Ahora el sistema puede gestionar instalaciones completas relacionando clientes, planes y técnicos mediante una clase gestora que usa ArrayList.

## Contenido

### Clases actualizadas

**ServicePlan.java**
Actualicé la clase del plan de servicio agregando encapsulamiento con atributos privados y sus respectivos getters y setters. Ahora cumple con las buenas prácticas de POO.

**Cliente.java**
Similar a ServicePlan, agregué private a los atributos y cree los métodos getter y setter para cada uno. También modifiqué el atributo planContratado por telefono para que tenga más sentido.

### Clases nuevas

**Tecnico.java**
Esta clase representa a los técnicos instaladores de ConnectFast. Tiene información como código, nombre, especialidad y zona donde trabaja. Incluye un método para asignar instalaciones.

**Instalacion.java**
Esta es la clase más importante que agregué porque relaciona las otras clases. Una instalación tiene un cliente, un plan de servicio y un técnico asignado. También tiene métodos para completar la instalación y calcular el costo total.

**GestorInstalaciones.java**
Clase que maneja todas las instalaciones usando ArrayList. Permite agregar instalaciones, mostrarlas todas, contarlas, buscar por código y filtrar las que están pendientes. También calcula el ingreso total de todas las instalaciones.

**Main.java**
Programa principal que demuestra todo el sistema funcionando. Crea objetos de todas las clases, los relaciona y usa el gestor para manejarlos.

## Como ejecutar

Desde la carpeta semana-02:

```
javac *.java
java Main
```

## Relaciones implementadas

El sistema ahora tiene relaciones entre las clases de esta forma:

- Una Instalacion tiene un Cliente (el cliente que contrata)
- Una Instalacion tiene un ServicePlan (el plan que se instala)
- Una Instalacion tiene un Tecnico (quien realiza la instalación)
- GestorInstalaciones maneja una lista de Instalaciones usando ArrayList

## Funcionalidades principales

El sistema permite:

- Registrar clientes, planes y técnicos
- Crear instalaciones relacionando los objetos anteriores
- Agregar instalaciones al gestor
- Mostrar todas las instalaciones registradas
- Completar una instalación (cambiar su estado)
- Ver solo las instalaciones pendientes
- Buscar una instalación por su código
- Calcular el costo total de una instalación (plan + instalación)
- Calcular el ingreso total de todas las instalaciones

## Cambios respecto a semana 01

La principal diferencia con la semana anterior es el uso de encapsulamiento. Ahora todos los atributos son privados y se accede a ellos mediante getters y setters. También agregué relaciones entre clases, lo que hace el sistema más completo y realista.

Otra diferencia importante es el uso de ArrayList en lugar de crear objetos sueltos. Esto permite manejar cualquier cantidad de instalaciones de forma dinámica.

## Conceptos aplicados

En esta semana apliqué:

- Encapsulamiento (private, getters, setters)
- Composición de objetos
- ArrayList para colecciones dinámicas
- Relaciones entre clases
- Métodos que operan sobre otros objetos

## Detalles técnicos

**ServicePlan**: 5 atributos privados, constructor, 10 métodos

**Cliente**: 4 atributos privados, constructor, 8 métodos

**Tecnico**: 4 atributos privados, constructor, 9 métodos

**Instalacion**: 6 atributos privados (3 son objetos de otras clases), constructor, 13 métodos

**GestorInstalaciones**: ArrayList de instalaciones, 6 métodos de gestión

## Notas

El código está organizado para que sea fácil de entender y mantener. Todas las clases tienen encapsulamiento y los métodos tienen nombres descriptivos. El programa Main muestra todas las funcionalidades implementadas.

Para futuras versiones se podría agregar validaciones más complejas, persistencia de datos y más métodos de búsqueda y filtrado.