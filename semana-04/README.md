# Semana 04 - Herencia

## Descripcion

En esta cuarta semana implemente herencia en el sistema ConnectFast identificando que los diferentes tipos de planes de servicio comparten caracteristicas comunes pero tienen atributos y comportamientos especificos. Cree una jerarquia donde ServicePlan es la clase padre y PlanResidencial, PlanEmpresarial y PlanGamer son las subclases.

## Jerarquia Implementada

La jerarquia se basa en que todos los planes de ConnectFast comparten codigo, nombre, velocidad y precio, pero cada tipo tiene caracteristicas adicionales segun el cliente al que va dirigido.

ServicePlan es la clase padre que contiene los atributos y metodos comunes a todos los planes. Las tres subclases heredan estos atributos y metodos, agregando sus propias caracteristicas y sobrescribiendo algunos metodos para implementar comportamiento especifico.

## Archivos Creados

ServicePlan.java - Clase padre refactorizada con atributos protected para que las subclases puedan accederlos

PlanResidencial.java - Subclase para planes de hogar con canales TV y telefonia

PlanEmpresarial.java - Subclase para planes de empresa con IP fija y SLA

PlanGamer.java - Subclase para planes gaming con baja latencia y ancho dedicado

Main.java - Programa que demuestra polimorfismo con array de ServicePlan

JERARQUIA.md - Documento con diagrama y explicacion detallada de la jerarquia

## Cambios Principales

### ServicePlan (Padre)

Cambie los atributos de private a protected para que las subclases puedan acceder a ellos directamente. Agregue el atributo tipoPlan para identificar el tipo. Los metodos mostrarInformacion y calcularPrecioConDescuento se pueden sobrescribir en las subclases.

### PlanResidencial

Esta subclase usa extends ServicePlan y agrega canalesTV, incluyeTelefonia y tipoContenido. En el constructor llama a super() para inicializar los atributos heredados. Sobrescribe mostrarInformacion para agregar info de canales y telefonia. Sobrescribe calcularPrecioConDescuento para aplicar 10% de descuento en contratos anuales.

### PlanEmpresarial

Hereda de ServicePlan y agrega ipFija, horasSoporte y nivelSLA. El constructor usa super() para inicializar atributos del padre. Sobrescribe mostrarInformacion para mostrar caracteristicas empresariales. Sobrescribe calcularPrecioConDescuento con descuentos especiales: 15% anual o 8% semestral.

### PlanGamer

Extiende ServicePlan agregando latenciaMaxima, anchoDedicado y servidoresGaming. Constructor con super(). Sobrescribe mostrarInformacion para mostrar caracteristicas gaming. Sobrescribe calcularPrecioConDescuento con 5% de descuento anual (menor porque son planes premium).

## Polimorfismo

En Main cree un array de tipo ServicePlan que contiene objetos de los tres tipos de subclases. Cuando recorro el array y llamo a metodos como mostrarInformacion o calcularPrecioConDescuento, Java ejecuta automaticamente la version correcta segun el tipo real del objeto.

Esto demuestra polimorfismo porque la misma llamada plan.mostrarInformacion() ejecuta codigo diferente dependiendo si el objeto es PlanResidencial, PlanEmpresarial o PlanGamer.

## Uso de super

Todas las subclases usan super() en sus constructores para llamar al constructor de ServicePlan e inicializar los atributos heredados. Por ejemplo:

```
public PlanResidencial(...) {
    super(planCode, planName, speedMbps, monthlyPrice, "Residencial");
    // luego inicializa atributos propios
}
```

Tambien uso super.mostrarInformacion() en los metodos sobrescritos para llamar primero al metodo del padre y luego agregar info adicional.

## Uso de Override

Todos los metodos sobrescritos tienen la anotacion @Override para indicar que estoy sobrescribiendo un metodo del padre. Esto ayuda a evitar errores de escritura en el nombre del metodo.

## Uso de instanceof

En Main uso instanceof para verificar el tipo real de un objeto y hacer casting cuando necesito acceder a metodos especificos de una subclase:

```
if (plan instanceof PlanResidencial) {
    PlanResidencial pr = (PlanResidencial) plan;
    pr.obtenerBeneficios();
}
```

## Como ejecutar

Desde la carpeta semana-04:

```
javac *.java
java Main
```

El programa muestra:
- Catalogo completo de planes con sus caracteristicas
- Calculo de precios con descuentos especificos por tipo
- Conteo de planes por categoria
- Beneficios especificos de cada plan
- Busqueda del plan mas rapido

## Conceptos Aplicados

Herencia: Las subclases heredan atributos y metodos de ServicePlan usando extends

Polimorfismo: Un array de ServicePlan contiene objetos de diferentes tipos

Sobrescritura: Los metodos mostrarInformacion y calcularPrecioConDescuento se comportan diferente en cada subclase

Protected: Los atributos del padre son accesibles en las subclases

Super: Se usa para llamar al constructor y metodos del padre

Override: Marca metodos que sobrescriben metodos del padre

Instanceof: Verifica el tipo real de un objeto

Casting: Convierte de tipo padre a tipo hijo para acceder a metodos especificos

## Beneficios

Con herencia evito duplicar el codigo de atributos y metodos comunes. Si necesito cambiar algo en todos los planes solo lo modifico en ServicePlan. Puedo agregar nuevos tipos de plan facilmente sin modificar codigo existente. El polimorfismo permite tratar todos los planes de forma uniforme cuando es necesario pero mantener comportamiento especifico cuando se requiere.

## Diferencias con semanas anteriores

En semanas anteriores todas las clases eran independientes. Ahora con herencia ServicePlan es la clase base y los tipos especificos heredan de ella. Esto reduce duplicacion de codigo y permite polimorfismo.

Los atributos cambiaron de private a protected en ServicePlan para que las subclases puedan acceder. Los constructores de las subclases deben llamar obligatoriamente a super() para inicializar atributos heredados.

## Validaciones

Mantuve todas las validaciones de semana 03. Los setters del padre validan los atributos heredados. Los setters de las subclases validan sus atributos propios. Por ejemplo en PlanEmpresarial valido que horasSoporte este entre 0 y 24.

## Notas

Esta jerarquia tiene sentido en el negocio real porque los proveedores de internet realmente manejan diferentes tipos de planes segun el segmento de cliente. La implementacion con herencia refleja esta estructura del mundo real.