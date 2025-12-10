# Jerarquia de Clases - ConnectFast

## Diagrama

```
              ServicePlan
                   |
      +------------+------------+
      |            |            |
PlanResidencial PlanEmpresarial PlanGamer
```

## Justificacion

Identifique que en ConnectFast existen diferentes tipos de planes de servicio que comparten características comunes pero tienen atributos y comportamientos especificos segun el tipo de cliente al que van dirigidos.

Todos los planes tienen codigo, nombre, velocidad y precio mensual, pero cada tipo tiene caracteristicas adicionales:

- Los planes residenciales se enfocan en contenido familiar con canales de TV y telefonia
- Los planes empresariales incluyen IP fija, SLA garantizado y soporte prioritario
- Los planes gamer priorizan baja latencia y ancho de banda dedicado

La relacion "es-un" se cumple claramente:
- Un PlanResidencial ES-UN ServicePlan
- Un PlanEmpresarial ES-UN ServicePlan
- Un PlanGamer ES-UN ServicePlan

## Atributos Heredados (protected)

Todos los tipos de plan heredan de ServicePlan:

- planCode (String): Codigo unico del plan
- planName (String): Nombre comercial del plan
- speedMbps (int): Velocidad de internet en Mbps
- monthlyPrice (double): Precio mensual base
- tipoPlan (String): Tipo de plan (Residencial, Empresarial, Gamer)

## Atributos Propios de Cada Subclase

### PlanResidencial
- canalesTV (int): Cantidad de canales de television incluidos
- incluyeTelefonia (boolean): Si incluye servicio de telefonia
- tipoContenido (String): Tipo de contenido ("Familiar")

### PlanEmpresarial
- ipFija (boolean): Si incluye direccion IP fija
- horasSoporte (int): Horas de soporte tecnico disponible
- nivelSLA (String): Nivel de acuerdo de servicio (Basico, Premium, Enterprise)

### PlanGamer
- latenciaMaxima (int): Latencia maxima garantizada en ms
- anchoDedicado (boolean): Si tiene ancho de banda dedicado
- servidoresGaming (String): Estado de servidores gaming

## Metodos Heredados

Todos los planes heredan estos metodos de ServicePlan:

- mostrarInformacion(): Muestra informacion basica del plan
- calcularPrecioConDescuento(int meses): Calcula precio total con descuentos
- calcularPrecioConImpuesto(): Calcula precio con IVA
- Getters y setters de atributos comunes

## Metodos Sobrescritos

### mostrarInformacion()

Cada tipo de plan sobrescribe este metodo para agregar su informacion especifica:

**PlanResidencial:** Muestra canales TV, telefonia y tipo de contenido ademas de la info base

**PlanEmpresarial:** Muestra IP fija, horas de soporte y nivel SLA ademas de la info base

**PlanGamer:** Muestra latencia, ancho dedicado y servidores gaming ademas de la info base

Todos llaman primero a super.mostrarInformacion() para mostrar la info heredada y luego agregan la propia.

### calcularPrecioConDescuento(int meses)

Cada tipo tiene politica de descuentos diferente:

**PlanResidencial:**
- 10% de descuento si contrata 12 meses o mas
- Sin descuento para contratos menores

**PlanEmpresarial:**
- 15% de descuento para contratos de 12 meses o mas
- 8% de descuento para contratos de 6 a 11 meses
- Sin descuento para contratos menores

**PlanGamer:**
- 5% de descuento para contratos de 12 meses (son planes premium)
- Sin descuento para contratos menores

## Metodos Propios

Cada subclase tiene metodos adicionales:

**PlanResidencial.obtenerBeneficios():** Retorna descripcion de beneficios para familias

**PlanEmpresarial.obtenerBeneficios():** Retorna descripcion de beneficios empresariales

**PlanGamer.obtenerBeneficios():** Retorna descripcion de beneficios para gaming

## Polimorfismo Aplicado

En el Main se demuestra polimorfismo creando un array de tipo ServicePlan que contiene objetos de los tres tipos:

```java
ServicePlan[] catalogo = new ServicePlan[5];
catalogo[0] = new PlanResidencial(...);
catalogo[1] = new PlanResidencial(...);
catalogo[2] = new PlanEmpresarial(...);
catalogo[3] = new PlanEmpresarial(...);
catalogo[4] = new PlanGamer(...);
```

Cuando se llama a metodos como mostrarInformacion() o calcularPrecioConDescuento(), Java ejecuta automaticamente la version correcta segun el tipo real del objeto, no el tipo de la variable.

Esto permite:
- Tratar todos los planes de forma uniforme
- Mantener el comportamiento especifico de cada tipo
- Agregar nuevos tipos sin modificar codigo existente

## Uso de instanceof

Para acceder a metodos especificos de cada subclase se usa instanceof y casting:

```java
if (plan instanceof PlanResidencial) {
    PlanResidencial pr = (PlanResidencial) plan;
    pr.obtenerBeneficios();
}
```

## Beneficios de esta Jerarquia

1. Reutilizacion de codigo: Los atributos y metodos comunes estan en ServicePlan

2. Mantenimiento facil: Cambios en comportamiento comun se hacen solo en ServicePlan

3. Extensibilidad: Se pueden agregar nuevos tipos de plan facilmente

4. Polimorfismo: Permite tratar todos los planes uniformemente cuando es necesario

5. Especializacion: Cada tipo puede tener comportamiento propio cuando lo necesita