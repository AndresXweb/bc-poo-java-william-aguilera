# Semana 06 - Abstraccion e Interfaces

## Descripcion

En esta sexta semana refactorice el sistema ConnectFast aplicando abstraccion mediante clases abstractas e interfaces. El objetivo fue mejorar el diseño aplicando principios SOLID y crear un sistema mas robusto y extensible.

## Cambios Principales

### ServicePlan convertida en Clase Abstracta

La clase ServicePlan que antes era concreta ahora es abstracta. Esto significa que ya no se puede instanciar directamente, solo se pueden crear instancias de los tipos concretos (PlanResidencial, PlanEmpresarial, PlanGamer).

Razon del cambio: En el negocio real no existe un "plan generico", siempre son tipos especificos. Hacer ServicePlan abstracta refleja mejor esta realidad y evita crear instancias sin sentido.

Metodos abstractos agregados:
- obtenerDescripcion(): Cada tipo describe el plan diferente
- obtenerBeneficios(): Los beneficios son especificos del tipo
- calcularCostoInstalacion(): El costo varia segun caracteristicas

### Interfaces Implementadas

**Facturable**
Define el contrato para gestion de facturacion e impuestos. Todos los planes deben poder generar facturas y calcular impuestos.

Metodos:
- generarFactura(): Genera documento completo
- calcularImpuestos(): Calcula IVA (19%)
- obtenerDetalleFacturacion(): Resumen para reportes

**Auditable**
Define el contrato para registro y trazabilidad de cambios. Importante para cumplir regulaciones y control interno.

Metodos:
- registrarCambio(String cambio): Guarda cambio en historial
- obtenerHistorial(): Retorna historial completo
- obtenerUltimoCambio(): Retorna cambio mas reciente

**Promocionable**
Define el contrato para aplicacion de promociones. Solo algunos tipos de plan la implementan.

Metodos:
- esElegiblePromocion(): Verifica elegibilidad
- aplicarPromocion(String codigo): Aplica codigo promocional
- obtenerPromocionesActivas(): Lista promociones disponibles

### Jerarquia Final

```
      <<abstract>>
     ServicePlan
  (implements Facturable, Auditable)
           |
    +------+------+
    |      |      |
PlanRes PlanEmp PlanGamer
    |             |
    +-------------+
    (implements Promocionable)
```

ServicePlan es abstracta e implementa Facturable y Auditable.
PlanResidencial y PlanGamer ademas implementan Promocionable.
PlanEmpresarial no implementa Promocionable porque los contratos empresariales no usan codigos promocionales.

## Estructura de Archivos

```
semana-06/
├── abstractas/
│   └── ServicePlan.java
├── interfaces/
│   ├── Facturable.java
│   ├── Auditable.java
│   └── Promocionable.java
├── implementaciones/
│   ├── PlanResidencial.java
│   ├── PlanEmpresarial.java
│   └── PlanGamer.java
├── Main.java
├── README.md
└── ANALISIS.md
```

## Principios SOLID Aplicados

**Single Responsibility**: Cada interface tiene una responsabilidad unica (facturacion, auditoria, promociones).

**Open/Closed**: Puedo agregar nuevos tipos de plan sin modificar ServicePlan ni las clases existentes.

**Liskov Substitution**: Cualquier subclase puede sustituir a ServicePlan abstracto sin romper el codigo.

**Interface Segregation**: Las interfaces son especificas, ninguna clase implementa metodos innecesarios.

**Dependency Inversion**: El codigo depende de abstracciones (ServicePlan abstracto, interfaces) no de implementaciones concretas.

## Como ejecutar

Desde la carpeta semana-06:

```
javac abstractas/*.java interfaces/*.java implementaciones/*.java Main.java
java Main
```

O si los archivos estan directos en semana-06:

```
javac *.java
java Main
```

## Demostraciones en Main

El programa Main incluye 8 demostraciones:

1. Explicacion de que ServicePlan es abstracta
2. Polimorfismo con clase abstracta
3. Uso de interface Facturable
4. Uso de interface Auditable
5. Uso de interface Promocionable
6. Multiple implementacion de interfaces
7. Metodos abstractos en accion
8. Uso practico combinado

## Beneficios del Nuevo Diseño

### Seguridad en tiempo de compilacion

Antes podia hacer:
```
ServicePlan plan = new ServicePlan("PLAN-999", "Generico", 50, 30000, "Raro");
```

Ahora esto da error de compilacion. Solo se pueden crear tipos concretos:
```
ServicePlan plan = new PlanResidencial(...);
```

### Contratos claros

Las interfaces definen claramente que puede hacer cada tipo de objeto. Si algo es Facturable, se que puedo llamar generarFactura(). No hay ambiguedad.

### Facilidad de extension

Agregar un nuevo tipo de plan es simple:
```java
public class PlanEstudiantil extends ServicePlan implements Promocionable {
    // Implementar metodos abstractos
}
```

No necesito modificar nada del codigo existente.

### Auditoria automatica

Todos los cambios en los planes se registran automaticamente. Los setters llaman a registrarCambio() cuando detectan cambios reales.

## Diferencias con Semana 05

En semana 05 ServicePlan era clase concreta sin restricciones. Ahora es abstracta con metodos que obligan a las subclases a implementar comportamiento especifico.

En semana 05 no habia interfaces. Ahora hay tres interfaces que definen capacidades independientes y permiten multiple implementacion.

En semana 05 no habia registro de cambios. Ahora todos los planes son auditables automaticamente.

## Validaciones

Mantuve todas las validaciones de semanas anteriores. Ademas ahora los cambios se registran automaticamente en el historial cuando se modifican atributos mediante setters.

## Implementacion de Interfaces

ServicePlan implementa Facturable y Auditable a nivel de clase abstracta, por lo que todas las subclases heredan estas implementaciones.

PlanResidencial y PlanGamer adicionalmente implementan Promocionable porque estos tipos de plan si manejan codigos promocionales.

PlanEmpresarial no implementa Promocionable porque los planes empresariales tienen contratos negociados sin promociones publicas.

## Metodos Abstractos vs Concretos

Metodos abstractos (cada subclase implementa diferente):
- obtenerDescripcion()
- obtenerBeneficios()
- calcularCostoInstalacion()

Metodos concretos (todas heredan la misma implementacion):
- mostrarInformacion()
- calcularPrecioConDescuento()
- generarFactura()
- calcularImpuestos()
- registrarCambio()
- obtenerHistorial()

## Notas

Esta semana el enfoque estuvo en mejorar el diseño aplicando abstraccion. El sistema ahora es mas robusto, seguro y facil de extender. Las interfaces permiten definir capacidades independientes de la jerarquia de clases.

El documento ANALISIS.md contiene explicacion detallada de todas las decisiones de diseño, justificacion de clase abstracta vs interface, y como se aplicaron los principios SOLID.