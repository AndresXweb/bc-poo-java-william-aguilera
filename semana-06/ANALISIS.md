# Analisis de Diseño - Semana 06: ConnectFast

## 1. Identificacion de Abstracciones

### Clase Abstracta: ServicePlan

**Por que es abstracta?**

ServicePlan se convirtio en clase abstracta porque representa un concepto general que no deberia instanciarse directamente. En el negocio real de ConnectFast no existe un "plan generico", siempre son tipos especificos: Residencial, Empresarial o Gamer.

Razones para hacerla abstracta:
- No tiene sentido crear un ServicePlan que no sea de un tipo concreto
- Todos los planes comparten atributos comunes (codigo, nombre, velocidad, precio)
- Cada tipo de plan tiene implementacion especifica de ciertos comportamientos
- Obliga a las subclases a implementar metodos criticos

**Que comportamiento comun tiene?**

Todos los planes comparten:
- Atributos basicos: codigo, nombre, velocidad, precio mensual
- Gestion de facturacion (implements Facturable)
- Registro de cambios (implements Auditable)
- Calculo de precio con descuento (metodo concreto heredable)
- Validaciones de datos

**Que comportamiento varia en las subclases?**

Cada tipo de plan debe implementar obligatoriamente:
- `obtenerDescripcion()`: Cada plan se describe de forma diferente
- `obtenerBeneficios()`: Los beneficios son especificos del tipo
- `calcularCostoInstalacion()`: El costo varia segun el tipo y caracteristicas

### Jerarquia

```
      <<abstract>>
     ServicePlan
  (implements Facturable, Auditable)
           |
    +------+------+
    |      |      |
PlanResid PlanEmp PlanGamer
    |             |
    +------+------+
         |
    (implements Promocionable)
```

**Subclases concretas:**
- PlanResidencial: Para hogares, con TV y telefonia
- PlanEmpresarial: Para empresas, con IP fija y SLA
- PlanGamer: Para gaming, con baja latencia

---

## 2. Interfaces Implementadas

### Interface 1: Facturable

**Capacidad que define:** Gestion de facturacion e impuestos

**Metodos:**
- `generarFactura()`: Genera documento de factura completo
- `calcularImpuestos()`: Calcula IVA (19%)
- `obtenerDetalleFacturacion()`: Resumen para reportes

**Clases que la implementan:**
- ServicePlan (abstracta): Todos los planes son facturables

**Por que tiene sentido:**
Todos los planes generan facturas y deben calcular impuestos. Es una capacidad fundamental del negocio que aplica a todos los tipos de plan sin excepcion.

### Interface 2: Auditable

**Capacidad que define:** Registro y trazabilidad de cambios

**Metodos:**
- `registrarCambio(String cambio)`: Guarda un cambio en el historial
- `obtenerHistorial()`: Retorna historial completo
- `obtenerUltimoCambio()`: Retorna el cambio mas reciente

**Clases que la implementan:**
- ServicePlan (abstracta): Todos los planes deben ser auditables

**Por que tiene sentido:**
Para cumplir regulaciones y control interno, ConnectFast debe mantener registro de todos los cambios en los planes (cambios de precio, velocidad, caracteristicas). Esto es requerimiento legal y operativo.

### Interface 3: Promocionable

**Capacidad que define:** Aplicacion de promociones y descuentos

**Metodos:**
- `esElegiblePromocion()`: Verifica si aplica para promociones
- `aplicarPromocion(String codigo)`: Aplica codigo de promocion
- `obtenerPromocionesActivas()`: Lista promociones disponibles

**Clases que la implementan:**
- PlanResidencial: Planes para hogares con promociones frecuentes
- PlanGamer: Planes gaming con promociones especiales

**Por que NO la implementa PlanEmpresarial:**
Los planes empresariales tienen contratos negociados directamente sin promociones publicas. Sus descuentos se manejan por volumen y contrato, no por codigos promocionales.

---

## 3. Decisiones de Diseño

### Por que Clase Abstracta vs Interface?

**Elegi clase abstracta para ServicePlan porque:**

1. **Relacion "es-un" clara**: Un PlanResidencial ES-UN ServicePlan
2. **Comparte estado (atributos)**: Todos tienen codigo, nombre, velocidad, precio
3. **Comportamiento comun implementable**: Validaciones, calculo de IVA, gestion de historial
4. **Jerarquia natural**: Los planes se organizan naturalmente en una estructura de herencia

**Elegi interfaces para Facturable, Auditable y Promocionable porque:**

1. **Definen capacidades independientes**: No son tipos, son comportamientos
2. **No comparten estado**: Solo definen contratos de metodos
3. **Multiple implementacion**: ServicePlan implementa Facturable Y Auditable simultaneamente
4. **Pueden aplicar a clases no relacionadas**: En el futuro, Instalacion tambien podria ser Facturable

### Ventajas del diseño actual

**Antes (Semana 05):**
```java
ServicePlan plan = new ServicePlan("PLAN-999", "Generico", 50, 30000, "Raro");
// Esto compilaba pero no tenia sentido en el negocio
```

**Despues (Semana 06):**
```java
ServicePlan plan = new ServicePlan(...); // Error de compilacion
// Solo se pueden crear tipos concretos
ServicePlan plan = new PlanResidencial(...); // Correcto
```

**Beneficios:**
- Imposible crear planes sin tipo definido
- Obliga a implementar metodos criticos
- Codigo mas seguro y robusto
- Mejor refleja el modelo de negocio real

---

## 4. Principios SOLID Aplicados

### Single Responsibility Principle (SRP)

**Aplicacion:**
- ServicePlan: Responsable de datos basicos del plan
- Facturable: Responsable solo de facturacion
- Auditable: Responsable solo de auditoria
- Promocionable: Responsable solo de promociones

Cada interfaz tiene una unica responsabilidad bien definida. No hay mezcla de conceptos.

### Open/Closed Principle (OCP)

**Aplicacion:**
El sistema esta abierto a extension pero cerrado a modificacion:

**Agregar nuevo tipo de plan:**
```java
public class PlanEstudiantil extends ServicePlan implements Promocionable {
    // No necesito modificar ServicePlan ni las otras clases
}
```

**Agregar nueva capacidad:**
```java
public interface Renovable {
    void renovar(int meses);
}
// Puedo agregar esta interface sin tocar codigo existente
```

### Liskov Substitution Principle (LSP)

**Aplicacion:**
Cualquier subclase puede sustituir a ServicePlan sin romper el codigo:

```java
public void procesarPlan(ServicePlan plan) {
    plan.obtenerDescripcion();
    plan.calcularCostoInstalacion();
}

// Funciona con cualquier subclase
procesarPlan(new PlanResidencial(...));
procesarPlan(new PlanEmpresarial(...));
procesarPlan(new PlanGamer(...));
```

Todas las subclases respetan el contrato de ServicePlan.

### Interface Segregation Principle (ISP)

**Aplicacion:**
Las interfaces son especificas y ninguna clase esta obligada a implementar metodos innecesarios:

- Facturable: Solo 3 metodos relacionados con facturacion
- Auditable: Solo 3 metodos relacionados con auditoria
- Promocionable: Solo 3 metodos relacionados con promociones

PlanEmpresarial NO implementa Promocionable porque no lo necesita. No hay interfaces "gordas" que obliguen a implementar metodos vacios.

### Dependency Inversion Principle (DIP)

**Aplicacion:**
El codigo de alto nivel depende de abstracciones, no de implementaciones concretas:

```java
public void generarReporte(ServicePlan[] planes) {  // Depende de abstraccion
    for (ServicePlan plan : planes) {
        // Funciona sin saber el tipo concreto
    }
}

public void facturar(Facturable item) {  // Depende de interface
    item.generarFactura();
}
```

No dependemos de PlanResidencial o PlanEmpresarial especificamente, sino de las abstracciones.

---

## 5. Mejoras Logradas

### Antes (Semana 05)

**Problemas:**
- ServicePlan era clase concreta sin restricciones
- Se podia instanciar directamente (sin sentido en el negocio)
- No habia interfaces para definir capacidades
- Codigo duplicado en gestion de facturacion
- Sin registro de cambios
- Sin separacion clara de responsabilidades

**Codigo tipico:**
```java
ServicePlan plan = new ServicePlan("PLAN-999", "Generico", 50, 30000, "Raro");
// Esto funcionaba pero no deberia ser posible
```

### Despues (Semana 06)

**Soluciones:**
- ServicePlan es abstracta: Solo tipos concretos
- Interfaces definen contratos claros
- Facturacion centralizada en interface
- Auditoria automatica de cambios
- Responsabilidades bien separadas
- Codigo mas mantenible y extensible

**Codigo mejorado:**
```java
ServicePlan plan = new PlanResidencial(...);  // Solo tipos concretos
Facturable f = plan;  // Uso polim orfico de interface
f.generarFactura();
```

**Ventajas especificas:**
1. Imposible crear instancias sin sentido
2. Todas las subclases deben implementar metodos criticos
3. Interfaces permiten multiple implementacion
4. Facil agregar nuevos tipos sin modificar existentes
5. Auditoria automatica de todos los cambios
6. Mejor organizacion del codigo
7. Mas facil de probar y mantener

---

## 6. Diagrama de Clases

```
    <<interface>>      <<interface>>      <<interface>>
    Facturable         Auditable         Promocionable
         |                  |                  |
         +--------+---------+                  |
                  |                            |
            <<abstract>>                       |
           ServicePlan                         |
         (implements Facturable, Auditable)    |
                  |                            |
      +-----------+-----------+                |
      |           |           |                |
PlanResidencial  PlanEmpresarial  PlanGamer   |
      |                              |         |
      +------------------------------+---------+
              (implements Promocionable)
```

**Leyenda:**
- Lineas solidas: Herencia (extends)
- Lineas punteadas: Implementacion (implements)
- <<abstract>>: Clase abstracta
- <<interface>>: Interface

---

## 7. Desafios y Soluciones

### Desafio 1: Decidir que va en la clase abstracta vs subclases

**Problema:** No estaba claro que metodos debian ser abstractos y cuales concretos en ServicePlan.

**Solucion:** Analice el negocio real:
- Metodos abstractos: Comportamiento que VARIA entre tipos (descripcion, beneficios, costo instalacion)
- Metodos concretos: Comportamiento COMUN a todos (facturacion, validaciones, calculo IVA)

### Desafio 2: Decidir cuando usar interface vs ampliar jerarquia

**Problema:** ¿Promocionable debia ser interface o crear subclase PlanPromocionable?

**Solucion:** Elegi interface porque:
- No todos los planes son promocionables (Empresarial no lo es)
- Es una capacidad, no un tipo
- Permite que clases no relacionadas tambien sean promocionables en el futuro

### Desafio 3: Evitar duplicacion en implementaciones de interfaces

**Problema:** Cada subclase tendria que implementar Facturable y Auditable repetidamente.

**Solucion:** ServicePlan abstracta implementa las interfaces, las subclases heredan la implementacion. Solo implementan Promocionable cuando aplica.

### Desafio 4: Mantener registro de cambios automaticamente

**Problema:** Facil olvidar llamar registrarCambio() cuando se modifican atributos.

**Solucion:** Los setters llaman automaticamente a registrarCambio() cuando detectan un cambio real de valor.

---

## 8. Proximos Pasos

**Mejoras futuras posibles:**

1. Agregar interface Cancelable para planes que permiten cancelacion
2. Crear clase Contrato que use ServicePlan
3. Implementar patron Strategy para calculos de descuento
4. Agregar interface Renovable para renovaciones automaticas
5. Crear sistema de notificaciones cuando cambien precios
6. Implementar persistencia de historial en base de datos

**Nuevos tipos de plan:**
- PlanEstudiantil: Con descuentos especiales
- PlanFamiliar: Para multiples usuarios
- PlanTemporal: Para eventos especificos

Todos estos se pueden agregar sin modificar el codigo existente (Open/Closed Principle).