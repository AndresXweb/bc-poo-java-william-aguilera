# Actividad 2: Comparación de Paradigmas

**Estudiante:** William Andres Aguilera Lasprilla  
**Ficha:** 3228973A  
**Dominio:** Empresa de Telecomunicaciones "ConnectFast"  
**Semana:** 01 - Introducción al Paradigma Orientado a Objetos


## Introducción

Este documento compara cómo se manejaría la información de un **Plan de Servicio** de ConnectFast utilizando dos paradigmas de programación diferentes: **Programación Estructurada** y **Programación Orientada a Objetos (POO)**.


## 1. Programación Estructurada

### Concepto
La programación estructurada se basa en el uso de **variables sueltas** y **funciones independientes** para manipular datos. Los datos y las operaciones están separados.

### Ejemplo: Manejo de un Plan de Servicio

#### Declaración de Variables (Datos)
```
// Variables para el Plan 1
String planCode1 = "PLAN-001"
String planName1 = "Básico Hogar"
int speedMbps1 = 50
int tvChannels1 = 80
double monthlyPrice1 = 45000
String clientType1 = "Residencial"
boolean hasPhone1 = true

// Variables para el Plan 2
String planCode2 = "PLAN-002"
String planName2 = "Premium Hogar"
int speedMbps2 = 200
int tvChannels2 = 150
double monthlyPrice2 = 85000
String clientType2 = "Residencial"
boolean hasPhone2 = true

// Variables para el Plan 3
String planCode3 = "PLAN-003"
String planName3 = "Empresarial Plus"
int speedMbps3 = 500
int tvChannels3 = 100
double monthlyPrice3 = 250000
String clientType3 = "Corporativo"
boolean hasPhone3 = true
```

#### Funciones (Operaciones)
```
// Función para mostrar información de un plan
function showPlanInfo(code, name, speed, channels, price, type, phone) {
    print("Código: " + code)
    print("Plan: " + name)
    print("Velocidad: " + speed + " Mbps")
    print("Canales TV: " + channels)
    print("Precio: $" + price)
    print("Tipo Cliente: " + type)
    print("Incluye Teléfono: " + (phone ? "Sí" : "No"))
}

// Función para calcular descuento
function calculateDiscount(price, discountPercent) {
    return price - (price * discountPercent / 100)
}

// Función para activar un plan
function activatePlan(code, clientCode) {
    print("Activando plan " + code + " para cliente " + clientCode)
    // Lógica de activación
}

// Uso de las funciones
showPlanInfo(planCode1, planName1, speedMbps1, tvChannels1, monthlyPrice1, clientType1, hasPhone1)
showPlanInfo(planCode2, planName2, speedMbps2, tvChannels2, monthlyPrice2, clientType2, hasPhone2)

newPrice1 = calculateDiscount(monthlyPrice1, 10)
activatePlan(planCode1, "CLI-12345")
```

### Características de la Programación Estructurada

**Ventajas:**
- Simple y directa para programas pequeños
- Fácil de entender para principiantes
- Menos conceptos abstractos

**Desventajas:**
- **Datos y funciones están separados**: No hay relación clara entre ellos
- **Muchas variables sueltas**: Para 3 planes necesitamos 21 variables diferentes
- **Difícil de mantener**: Si agregamos un atributo, debemos modificar todas las variables
- **Propenso a errores**: Podemos pasar parámetros en orden incorrecto
- **No escalable**: Con 100 planes sería inmanejable
- **Código repetitivo**: Debemos duplicar variables para cada plan


## 2. Programación Orientada a Objetos (POO)

### Concepto
La POO se basa en el uso de **clases** y **objetos** que agrupan datos (atributos) y operaciones (métodos) relacionados en una sola unidad coherente.

### Ejemplo: Manejo de un Plan de Servicio

#### Definición de la Clase (Plantilla)
```
class ServicePlan {
    // Atributos (Datos del objeto)
    String planCode
    String planName
    int speedMbps
    int tvChannels
    double monthlyPrice
    String clientType
    boolean hasPhone
    
    // Constructor (Inicializa el objeto)
    ServicePlan(code, name, speed, channels, price, type, phone) {
        this.planCode = code
        this.planName = name
        this.speedMbps = speed
        this.tvChannels = channels
        this.monthlyPrice = price
        this.clientType = type
        this.hasPhone = phone
    }
    
    // Métodos (Operaciones del objeto)
    method showInfo() {
        print("Código: " + this.planCode)
        print("Plan: " + this.planName)
        print("Velocidad: " + this.speedMbps + " Mbps")
        print("Canales TV: " + this.tvChannels)
        print("Precio: $" + this.monthlyPrice)
        print("Tipo Cliente: " + this.clientType)
        print("Incluye Teléfono: " + (this.hasPhone ? "Sí" : "No"))
    }
    
    method calculateDiscount(discountPercent) {
        return this.monthlyPrice - (this.monthlyPrice * discountPercent / 100)
    }
    
    method activate(clientCode) {
        print("Activando plan " + this.planCode + " para cliente " + clientCode)
        // Lógica de activación
    }
}
```

#### Creación y Uso de Objetos
```
// Crear objetos (Instancias de la clase)
plan1 = new ServicePlan("PLAN-001", "Básico Hogar", 50, 80, 45000, "Residencial", true)
plan2 = new ServicePlan("PLAN-002", "Premium Hogar", 200, 150, 85000, "Residencial", true)
plan3 = new ServicePlan("PLAN-003", "Empresarial Plus", 500, 100, 250000, "Corporativo", true)

// Usar los métodos de cada objeto
plan1.showInfo()
plan2.showInfo()

newPrice = plan1.calculateDiscount(10)
plan1.activate("CLI-12345")
```

### Características de la Programación Orientada a Objetos

**Ventajas:**
- **Datos y métodos están unidos**: Cada plan es una unidad coherente
- **Organización clara**: Solo 3 objetos en lugar de 21 variables
- **Fácil de mantener**: Si agregamos un atributo, solo modificamos la clase
- **Menos propenso a errores**: Los métodos ya conocen sus datos
- **Escalable**: Crear 100 planes es igual de simple que crear 3
- **Reutilizable**: La clase puede usarse en todo el proyecto
- **Modelo del mundo real**: Los objetos representan entidades reales

**Desventajas:**
- Requiere entender conceptos adicionales (clase, objeto, instancia)
- Puede ser más complejo inicialmente para principiantes


## 3. Comparación Directa

| Aspecto | Programación Estructurada | Programación Orientada a Objetos |
|---------|---------------------------|-----------------------------------|
| **Organización** | Variables sueltas y funciones separadas | Clase que agrupa datos y métodos |
| **Para 3 planes** | 21 variables independientes | 3 objetos de una clase |
| **Agregar atributo** | Modificar todas las variables y funciones | Modificar solo la clase |
| **Relación datos-operaciones** | Separada (hay que pasarlos) | Unida (el objeto los conoce) |
| **Escalabilidad** | Difícil (crece linealmente) | Fácil (misma complejidad) |
| **Mantenimiento** | Complejo y propenso a errores | Simple y organizado |
| **Representación** | Abstracta (solo variables) | Natural (objetos del mundo real) |
| **Reutilización** | Limitada (copiar y pegar) | Alta (usar la clase) |


## 4. Ejemplo Práctico Completo

### Escenario Real en ConnectFast
Un cliente quiere comparar 3 planes y calcular el precio con descuento del 15% para el que elija.

#### Solución con Programación Estructurada
```
// 21 variables para 3 planes
planCode1 = "PLAN-001", planName1 = "Básico", speedMbps1 = 50, ...
planCode2 = "PLAN-002", planName2 = "Premium", speedMbps2 = 200, ...
planCode3 = "PLAN-003", planName3 = "Empresarial", speedMbps3 = 500, ...

// Mostrar todos (llamar función 3 veces con 7 parámetros cada vez)
showPlanInfo(planCode1, planName1, speedMbps1, tvChannels1, monthlyPrice1, clientType1, hasPhone1)
showPlanInfo(planCode2, planName2, speedMbps2, tvChannels2, monthlyPrice2, clientType2, hasPhone2)
showPlanInfo(planCode3, planName3, speedMbps3, tvChannels3, monthlyPrice3, clientType3, hasPhone3)

// Cliente elige el plan 2, calcular descuento
finalPrice = calculateDiscount(monthlyPrice2, 15)
```

#### Solución con POO
```
// 3 objetos de la misma clase
plan1 = new ServicePlan("PLAN-001", "Básico", 50, 80, 45000, "Residencial", true)
plan2 = new ServicePlan("PLAN-002", "Premium", 200, 150, 85000, "Residencial", true)
plan3 = new ServicePlan("PLAN-003", "Empresarial", 500, 100, 250000, "Corporativo", true)

// Mostrar todos (cada objeto se muestra a sí mismo)
plan1.showInfo()
plan2.showInfo()
plan3.showInfo()

// Cliente elige el plan 2, calcular descuento
finalPrice = plan2.calculateDiscount(15)
```

**Resultado:** El código con POO es más limpio, más corto y más fácil de entender y mantener.


## Conclusión

La **Programación Orientada a Objetos** ofrece una forma más **natural**, **organizada** y **escalable** de modelar sistemas como ConnectFast. Al agrupar datos y comportamientos relacionados en clases y objetos, el código se vuelve más:

- **Mantenible**: Cambios centralizados en la clase
- **Comprensible**: Los objetos reflejan entidades del mundo real
- **Escalable**: Agregar más planes no aumenta la complejidad
- **Reutilizable**: La clase puede usarse en múltiples contextos
- **Seguro**: Menos errores al pasar parámetros

Para sistemas empresariales como ConnectFast, con cientos de clientes, planes, técnicos y servicios, la POO es la opción más adecuada.
