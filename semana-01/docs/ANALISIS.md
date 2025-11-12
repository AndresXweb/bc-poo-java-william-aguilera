# Análisis del Dominio: Empresa de Telecomunicaciones ConnectFast

**Estudiante:** William Andres Aguilera Lasprilla  
**Ficha:** 3228973A  
**Semana:** 01 - Introducción al Paradigma Orientado a Objetos

## 1. Identificación de Objetos

### Objeto 1: Plan de Servicio

#### ¿Qué es?
Un paquete de servicios de telecomunicaciones que se ofrece a los clientes, que incluye internet, telefonía y/o televisión con características y precios específicos.

#### Características (Atributos)
- Código del plan
- Nombre del plan
- Velocidad de internet en Mbps
- Cantidad de canales de TV incluidos
- Precio mensual
- Tipo de cliente
- Incluye telefonía fija
- Estado del plan

#### Comportamientos (Métodos)
- Activar plan para un nuevo cliente
- Suspender temporalmente el servicio
- Cambiar o actualizar a otro plan
- Calcular descuento por promoción
- Generar factura mensual
- Mostrar información detallada del plan
- Verificar disponibilidad en zona específica

### Objeto 2: Cliente

#### ¿Qué es?
Una persona natural o jurídica que contrata los servicios de telecomunicaciones de ConnectFast.

#### Características (Atributos)
- Código de cliente
- Nombre completo o razón social
- Tipo de documento
- Número de documento
- Dirección de instalación
- Localidad
- Teléfono de contacto
- Correo electrónico
- Fecha de vinculación
- Estado del cliente
- Tipo de cliente
- Plan contratado actual

#### Comportamientos (Métodos)
- Registrar nuevo cliente
- Actualizar datos personales
- Cambiar dirección de servicio
- Consultar historial de pagos
- Solicitar soporte técnico
- Renovar contrato
- Cancelar servicio
- Generar certificado de cliente activo

### Objeto 3: Técnico Instalador

#### ¿Qué es?
Un empleado especializado de ConnectFast responsable de realizar instalaciones, mantenimientos y reparaciones de los servicios de telecomunicaciones.

#### Características (Atributos)
- Código de técnico
- Nombre completo
- Número de cédula
- Especialidad
- Zona de cobertura asignada
- Vehículo asignado
- Teléfono celular
- Estado actual
- Cantidad de servicios realizados hoy
- Calificación promedio
- Fecha de ingreso a la empresa

#### Comportamientos (Métodos)
- Asignar orden de trabajo
- Iniciar instalación de servicio
- Completar mantenimiento preventivo
- Reportar falla o incidente
- Actualizar estado de disponibilidad
- Registrar materiales utilizados
- Finalizar orden de servicio
- Solicitar soporte de segundo nivel

### Objeto 4: Orden de Servicio

#### ¿Qué es?
Un registro de solicitud de trabajo técnico que puede ser una instalación nueva, mantenimiento, reparación o retiro de servicio para un cliente específico.

#### Características (Atributos)
- Número de orden
- Código de cliente
- Tipo de servicio
- Dirección de ejecución
- Fecha de solicitud
- Fecha programada
- Fecha de ejecución real
- Técnico asignado
- Prioridad
- Estado
- Observaciones del cliente
- Observaciones del técnico
- Tiempo estimado en horas
- Materiales requeridos

#### Comportamientos (Métodos)
- Crear nueva orden de servicio
- Asignar técnico disponible
- Reagendar fecha de ejecución
- Iniciar ejecución de trabajo
- Registrar avance del servicio
- Completar orden exitosamente
- Cancelar orden con motivo
- Generar reporte de ejecución
- Notificar al cliente sobre estado
- Solicitar aprobación de materiales adicionales

### Objeto 5: Factura

#### ¿Qué es?
Un documento contable que registra los cargos mensuales por los servicios de telecomunicaciones prestados a un cliente, incluyendo el plan contratado y servicios adicionales.

#### Características (Atributos)
- Número de factura
- Código de cliente
- Período facturado
- Fecha de emisión
- Fecha de vencimiento
- Plan de servicio facturado
- Valor del plan base
- Cargos adicionales
- Descuentos aplicados
- Subtotal
- Impuestos
- Total a pagar
- Estado de pago
- Método de pago
- Fecha de pago realizado

#### Comportamientos (Métodos)
- Generar factura mensual automática
- Calcular valor total con impuestos
- Aplicar descuentos promocionales
- Registrar pago recibido
- Generar recibo de pago
- Enviar recordatorio de pago
- Marcar como factura vencida
- Anular factura
- Generar duplicado de factura
- Consultar estado de cuenta del cliente
- Programar pago automático


## 2. Comparación de Paradigmas

### Programación Estructurada

La programación estructurada se basa en el uso de variables sueltas y funciones independientes para manipular datos. Los datos y las operaciones están separados.

#### Ejemplo con variables sueltas
```
String planCode1 = "PLAN-001"
String planName1 = "Basico Hogar"
int speedMbps1 = 50
double monthlyPrice1 = 45000

String planCode2 = "PLAN-002"
String planName2 = "Premium Hogar"
int speedMbps2 = 200
double monthlyPrice2 = 85000
```

#### Funciones separadas
```
function showPlanInfo(code, name, speed, price) {
    print("Codigo: " + code)
    print("Plan: " + name)
    print("Velocidad: " + speed + " Mbps")
    print("Precio: $" + price)
}

function calculateDiscount(price, percent) {
    return price - (price * percent / 100)
}
```

#### Desventajas
- Muchas variables sueltas difíciles de manejar
- Datos y funciones están separados
- Propenso a errores al pasar parámetros
- No escalable para muchos objetos
- Código repetitivo

### Programación Orientada a Objetos

La POO agrupa datos y operaciones relacionados en una sola unidad llamada clase.

#### Definición de clase
```
class ServicePlan {
    String planCode
    String planName
    int speedMbps
    double monthlyPrice
    
    ServicePlan(code, name, speed, price) {
        this.planCode = code
        this.planName = name
        this.speedMbps = speed
        this.monthlyPrice = price
    }
    
    method showInfo() {
        print("Codigo: " + this.planCode)
        print("Plan: " + this.planName)
        print("Velocidad: " + this.speedMbps + " Mbps")
        print("Precio: $" + this.monthlyPrice)
    }
    
    method calculateDiscount(percent) {
        return this.monthlyPrice - (this.monthlyPrice * percent / 100)
    }
}
```

#### Uso de objetos
```
plan1 = new ServicePlan("PLAN-001", "Basico Hogar", 50, 45000)
plan2 = new ServicePlan("PLAN-002", "Premium Hogar", 200, 85000)

plan1.showInfo()
plan2.showInfo()
```

#### Ventajas
- Datos y métodos unidos en una unidad
- Organización clara y lógica
- Fácil de mantener y escalar
- Representa mejor el mundo real
- Menos propenso a errores

### Comparación Directa

| Aspecto | Programación Estructurada | POO |
|---------|---------------------------|-----|
| Organización | Variables y funciones separadas | Clase que agrupa datos y métodos |
| Escalabilidad | Difícil | Fácil |
| Mantenimiento | Complejo | Simple |
| Representación | Abstracta | Natural |
| Reutilización | Limitada | Alta |


## 3. Decisiones de Diseño

### ¿Por qué ServicePlan como clase principal?

El Plan de Servicio es el núcleo del negocio de ConnectFast. Es el producto que se ofrece a los clientes y contiene las características principales que definen lo que la empresa vende. Todos los demás objetos del sistema se relacionan de alguna manera con los planes de servicio.

### ¿Por qué Cliente como clase secundaria?

Cliente fue seleccionado como clase secundaria porque tiene una relación directa e importante con ServicePlan. Un cliente contrata un plan específico, lo que hace que esta relación sea fundamental para el negocio. Además, es una clase simple pero completa que permite demostrar los conceptos básicos de POO sin complicaciones.

### Atributos seleccionados para ServicePlan

- **planCode:** Identificador único necesario para diferenciar planes en el sistema
- **planName:** Nombre comercial que el cliente reconoce
- **speedMbps:** Característica técnica principal que define el servicio de internet
- **tvChannels:** Característica del servicio de televisión incluido
- **monthlyPrice:** Información crucial para el cliente y para facturación

Estos atributos cubren las necesidades básicas de información que un cliente y el sistema necesitan conocer sobre un plan.

### Atributos seleccionados para Cliente

- **codigoCliente:** Identificador único en el sistema
- **nombreCompleto:** Información básica de identificación
- **direccion:** Necesaria para la prestación del servicio
- **planContratado:** Establece la relación con ServicePlan

### Relación entre clases

La relación entre Cliente y ServicePlan se implementó mediante el atributo `planContratado` en Cliente, que almacena el código del plan. Esta es una relación simple pero efectiva que demuestra cómo los objetos se relacionan entre sí en un sistema real.

### Métodos implementados

**ServicePlan:**
- `showInfo()`: Muestra toda la información del plan de manera organizada
- `calcularPrecioConImpuesto()`: Realiza un cálculo útil que sería necesario en el sistema real

**Cliente:**
- `mostrarInformacion()`: Presenta los datos del cliente de forma clara

Estos métodos son simples pero funcionales y demuestran el comportamiento básico que los objetos deben tener.

## 4. Dificultades Encontradas

Durante el desarrollo de esta primera semana, las principales dificultades fueron:

1. **Decidir qué atributos incluir:** Había muchas características posibles para un plan de servicio, pero se seleccionaron las más relevantes para mantener la simplicidad.

2. **Establecer la relación entre clases:** Inicialmente no estaba claro cómo conectar Cliente con ServicePlan de manera simple. Se optó por usar el código del plan como String por simplicidad.

3. **Mantener el código simple:** Siendo la primera semana, fue importante no complicar el código con validaciones o lógica avanzada, manteniendo el enfoque en los conceptos básicos de POO.

## Conclusión

Este análisis demuestra cómo los conceptos de POO se aplican a un dominio real como ConnectFast. La identificación de objetos del mundo real y su traducción a clases de Java permite crear sistemas más organizados, mantenibles y escalables que con programación estructurada tradicional.