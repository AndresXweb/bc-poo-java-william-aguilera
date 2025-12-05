# Mejoras Semana 03

## Encapsulacion Aplicada

### ServicePlan
Los atributos ya eran privados desde semana 02, en esta semana agregue validaciones en todos los setters para asegurar que los datos sean correctos.

Validaciones implementadas:
- El codigo del plan debe empezar con "PLAN-", si no lanza excepcion
- El nombre no puede estar vacio
- La velocidad debe estar entre 10 y 1000 Mbps
- Los canales no pueden ser negativos
- El precio debe ser mayor a cero

### Cliente
Los atributos ya estaban encapsulados. Agregue validaciones para asegurar datos correctos.

Validaciones implementadas:
- El codigo debe empezar con "CLI-"
- El nombre no puede estar vacio y debe tener minimo 3 caracteres
- Si la direccion esta vacia, se asigna "Sin direccion" por defecto
- El telefono debe tener minimo 7 caracteres, si esta vacio se asigna "Sin telefono"

### Tecnico
Similar a las otras clases, agregue validaciones en los setters.

Validaciones implementadas:
- El codigo debe empezar con "TEC-"
- El nombre debe tener minimo 3 caracteres
- Si la especialidad esta vacia, se asigna "General"
- Si la zona esta vacia, se asigna "Sin asignar"

### Instalacion
Esta clase tiene validaciones especiales porque maneja objetos de otras clases.

Validaciones implementadas:
- El codigo debe empezar con "INST-"
- El cliente, plan y tecnico no pueden ser nulos
- Si la fecha esta vacia, se asigna "Por definir"

### GestorInstalaciones
Agregue validaciones en los métodos para evitar errores.

Validaciones implementadas:
- El nombre de la empresa no puede estar vacio
- No se pueden agregar instalaciones nulas
- No se pueden agregar instalaciones con codigo duplicado
- Los metodos de busqueda validan que el parametro no sea nulo

## Constructores Sobrecargados

### ServicePlan
Implemente 3 constructores diferentes:

Constructor completo: Recibe los 5 parametros (codigo, nombre, velocidad, canales, precio)

Constructor basico: Recibe 3 parametros (codigo, nombre, velocidad). Los canales se establecen en 80 y el precio en 50000 por defecto.

Constructor minimo: Recibe solo codigo y nombre. La velocidad se establece en 50 Mbps, canales en 80 y precio en 50000.

Los constructores usan this() para llamarse entre ellos y evitar repetir codigo.

### Cliente
Implemente 3 constructores:

Constructor completo: Recibe los 4 parametros (codigo, nombre, direccion, telefono)

Constructor sin telefono: Recibe 3 parametros. El telefono se establece como "Sin telefono"

Constructor basico: Recibe solo codigo y nombre. La direccion se establece como "Sin direccion" y el telefono como "Sin telefono"

### Tecnico
Tambien tiene 3 constructores:

Constructor completo: Recibe los 4 parametros

Constructor sin zona: Recibe codigo, nombre y especialidad. La zona se establece como "Sin asignar"

Constructor basico: Recibe solo codigo y nombre. La especialidad se establece como "General"

### Instalacion
Tiene 2 constructores:

Constructor completo: Recibe codigo, cliente, plan, tecnico y fecha

Constructor sin fecha: Recibe todo excepto la fecha. La fecha se establece como "Por definir"

## Uso de this() en constructores

Todos los constructores sobrecargados usan this() para llamar a otros constructores de la misma clase. Esto evita duplicar codigo y hace el mantenimiento mas facil. Por ejemplo en ServicePlan:

El constructor minimo llama al constructor basico con this(codigo, nombre, 50)
El constructor basico llama al constructor completo con this(codigo, nombre, velocidad, 80, 50000)
El constructor completo es el unico que realmente inicializa los atributos

## Validaciones en constructores

Todos los constructores usan los metodos setter para asignar valores en lugar de asignar directamente a los atributos. Esto garantiza que las validaciones se ejecuten siempre, incluso cuando se crea el objeto.

Por ejemplo en ServicePlan:
setPlanCode(planCode) valida el formato antes de asignar
setSpeedMbps(speedMbps) valida el rango antes de asignar
setMonthlyPrice(monthlyPrice) valida que sea positivo antes de asignar

Si alguna validacion falla, se lanza IllegalArgumentException y el objeto no se crea.

## Beneficios Logrados

Con estas mejoras el sistema es mucho mas robusto:

1. Es imposible crear objetos con datos invalidos porque los constructores validan todo

2. Si intento cambiar un atributo a un valor incorrecto, el setter lo detecta y lanza excepcion

3. Los constructores sobrecargados hacen mas facil crear objetos porque no siempre necesito todos los datos

4. El codigo es mas limpio porque los constructores se llaman entre si con this()

5. Las validaciones estan centralizadas en los setters, no duplicadas en cada constructor

6. El gestor ya no permite agregar instalaciones duplicadas

7. Todos los errores dan mensajes claros para saber que esta mal

## Ejemplo de uso

Antes de estas mejoras podia hacer:
ServicePlan plan = new ServicePlan("001", "", -50, -10, -1000);

Y el objeto se creaba con datos completamente invalidos.

Ahora si intento eso:
Lanza excepcion porque el codigo no empieza con "PLAN-"
Lanza excepcion porque el nombre esta vacio
Lanza excepcion porque la velocidad esta fuera de rango
Lanza excepcion porque el precio es negativo

El sistema obliga a usar datos correctos desde el principio.

## Conclusiones

Esta semana mejore significativamente la calidad del codigo. Ya no es posible crear objetos con datos incorrectos y el sistema valida todo antes de permitir operaciones. Los constructores sobrecargados hacen mas comodo trabajar con las clases porque no siempre necesito proporcionar todos los datos.

El uso de this() para llamar constructores desde otros constructores reduce la duplicacion de codigo y hace el mantenimiento mas simple. Si necesito cambiar como se inicializa un objeto, solo tengo que modificar el constructor completo.

Las validaciones centralizadas en los setters garantizan que las reglas se apliquen siempre, tanto al crear objetos como al modificarlos despues.