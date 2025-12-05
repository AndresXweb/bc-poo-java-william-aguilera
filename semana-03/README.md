# Semana 03 - Encapsulacion y Constructores

## Descripcion

En esta tercera semana refactorice el codigo del sistema ConnectFast aplicando encapsulacion completa y sobrecarga de constructores. El objetivo fue mejorar la robustez del sistema agregando validaciones en todos los setters y creando multiples constructores para facilitar la creacion de objetos.

## Que se mejoro

### Validaciones

Agregue validaciones en todos los setters de todas las clases. Ahora es imposible crear objetos con datos incorrectos porque el sistema valida todo antes de asignar valores. Si algun dato es invalido se lanza una excepcion con un mensaje claro explicando el problema.

Por ejemplo en ServicePlan ahora valida que el codigo empiece con "PLAN-", que la velocidad este entre 10 y 1000 Mbps, y que el precio sea positivo. En Cliente valida que el codigo empiece con "CLI-" y que el nombre tenga minimo 3 caracteres.

### Constructores sobrecargados

Implemente multiples constructores en cada clase para hacer mas facil crear objetos. Por ejemplo ServicePlan tiene 3 constructores: uno completo que recibe todos los parametros, uno basico que solo necesita codigo nombre y velocidad, y uno minimo que solo necesita codigo y nombre.

Los constructores usan this() para llamarse entre ellos y evitar duplicar codigo. El constructor minimo llama al basico, y el basico llama al completo. Solo el constructor completo realmente inicializa los atributos.

### Centralizacion de validaciones

Todos los constructores usan los metodos setter para asignar valores en lugar de asignar directamente a los atributos. Esto garantiza que las validaciones se ejecuten siempre, tanto al crear objetos como al modificarlos despues.

## Archivos modificados

Todos los archivos de semana 02 fueron refactorizados:

ServicePlan.java - Agregue 3 constructores y validaciones en todos los setters

Cliente.java - Agregue 3 constructores y validaciones

Tecnico.java - Agregue 3 constructores y validaciones

Instalacion.java - Agregue 2 constructores y validaciones especiales para objetos

GestorInstalaciones.java - Agregue validaciones en los metodos, especialmente para evitar duplicados

Main.java - Nuevo programa que demuestra los constructores sobrecargados y las validaciones

## Como ejecutar

Desde la carpeta semana-03:

```
javac *.java
java Main
```

El programa demuestra como funcionan los diferentes constructores y muestra ejemplos de validaciones en accion.

## Validaciones implementadas

### ServicePlan
- Codigo debe empezar con "PLAN-"
- Nombre no puede estar vacio
- Velocidad entre 10 y 1000 Mbps
- Canales no negativos
- Precio mayor a cero

### Cliente
- Codigo debe empezar con "CLI-"
- Nombre minimo 3 caracteres
- Si direccion vacia, asigna "Sin direccion"
- Telefono minimo 7 caracteres o "Sin telefono"

### Tecnico
- Codigo debe empezar con "TEC-"
- Nombre minimo 3 caracteres
- Especialidad por defecto "General"
- Zona por defecto "Sin asignar"

### Instalacion
- Codigo debe empezar con "INST-"
- Cliente, plan y tecnico no pueden ser nulos
- Fecha por defecto "Por definir"

### GestorInstalaciones
- Nombre empresa no vacio
- No acepta instalaciones nulas
- No acepta codigos duplicados

## Constructores disponibles

### ServicePlan
1. Constructor completo: codigo, nombre, velocidad, canales, precio
2. Constructor basico: codigo, nombre, velocidad (canales=80, precio=50000)
3. Constructor minimo: codigo, nombre (velocidad=50, canales=80, precio=50000)

### Cliente
1. Constructor completo: codigo, nombre, direccion, telefono
2. Sin telefono: codigo, nombre, direccion
3. Basico: codigo, nombre

### Tecnico
1. Constructor completo: codigo, nombre, especialidad, zona
2. Sin zona: codigo, nombre, especialidad
3. Basico: codigo, nombre

### Instalacion
1. Constructor completo: codigo, cliente, plan, tecnico, fecha
2. Sin fecha: codigo, cliente, plan, tecnico

## Beneficios

El sistema ahora es mucho mas robusto. No se pueden crear objetos con datos incorrectos y todas las operaciones validan los parametros antes de ejecutarse. Los constructores sobrecargados hacen mas comodo trabajar con las clases porque permiten crear objetos con diferentes niveles de informacion.

El uso de this() para encadenar constructores reduce la duplicacion de codigo. Las validaciones centralizadas en los setters garantizan que las reglas se apliquen siempre.

## Diferencias con semana 02

La principal diferencia es que ahora todo se valida. En semana 02 podia crear objetos con datos incorrectos y el sistema los aceptaba. Ahora si intento crear un ServicePlan con velocidad negativa o un Cliente con nombre vacio, el sistema lanza una excepcion.

Tambien agregue flexibilidad con los constructores sobrecargados. Antes solo habia un constructor por clase y siempre tenia que proporcionar todos los datos. Ahora puedo usar el constructor que necesite segun la situacion.

## Manejo de excepciones

El programa Main incluye ejemplos de como se capturan las excepciones cuando se intenta crear objetos con datos invalidos. Uso bloques try-catch para mostrar los mensajes de error sin que el programa se detenga.

## Notas tecnicas

Todos los constructores llaman a los setters en lugar de asignar directamente a los atributos. Esto es importante porque garantiza que las validaciones se ejecuten siempre.

El metodo toString() no se implemento en esta semana porque no era parte de los requisitos, pero se podria agregar facilmente en futuras versiones.

El documento MEJORAS.md explica en detalle todas las mejoras realizadas y los beneficios obtenidos.