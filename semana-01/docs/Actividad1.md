
# Actividad 1: Identificación de Objetos

**Estudiante:** William Andres Aguilera Lasprilla  
**Ficha:** 3228973A  
**Dominio:** Empresa de Telecomunicaciones "ConnectFast"  
**Semana:** 01 - Introducción al Paradigma Orientado a Objetos


## Objeto 1: Plan de Servicio

### ¿Qué es?
Un paquete de servicios de telecomunicaciones que se ofrece a los clientes, que incluye internet, telefonía y/o televisión con características y precios específicos.

### Características (Atributos)
- Código del plan (ej: "PLAN-001")
- Nombre del plan (ej: "Básico Hogar")
- Velocidad de internet en Mbps (ej: 50, 100, 200)
- Cantidad de canales de TV incluidos (ej: 0, 80, 150)
- Precio mensual (ej: $45,000)
- Tipo de cliente (residencial o corporativo)
- Incluye telefonía fija (sí/no)
- Estado del plan (activo, descontinuado, promocional)

### Comportamientos (Métodos)
- Activar plan para un nuevo cliente
- Suspender temporalmente el servicio
- Cambiar o actualizar a otro plan
- Calcular descuento por promoción
- Generar factura mensual
- Mostrar información detallada del plan
- Verificar disponibilidad en zona específica

---

## Objeto 2: Cliente

### ¿Qué es?
Una persona natural o jurídica que contrata los servicios de telecomunicaciones de ConnectFast.

### Características (Atributos)
- Código de cliente (ej: "CLI-12345")
- Nombre completo o razón social
- Tipo de documento (CC, NIT, CE)
- Número de documento
- Dirección de instalación
- Localidad (ej: Usaquén, Suba)
- Teléfono de contacto
- Correo electrónico
- Fecha de vinculación
- Estado del cliente (activo, suspendido, retirado)
- Tipo de cliente (residencial o corporativo)
- Plan contratado actual

### Comportamientos (Métodos)
- Registrar nuevo cliente
- Actualizar datos personales
- Cambiar dirección de servicio
- Consultar historial de pagos
- Solicitar soporte técnico
- Renovar contrato
- Cancelar servicio
- Generar certificado de cliente activo


## Objeto 3: Técnico Instalador

### ¿Qué es?
Un empleado especializado de ConnectFast responsable de realizar instalaciones, mantenimientos y reparaciones de los servicios de telecomunicaciones.

### Características (Atributos)
- Código de técnico (ej: "TEC-001")
- Nombre completo
- Número de cédula
- Especialidad (fibra óptica, telefonía, TV digital)
- Zona de cobertura asignada (ej: Usaquén, Chapinero)
- Vehículo asignado (placa)
- Teléfono celular
- Estado actual (disponible, en servicio, no disponible)
- Cantidad de servicios realizados hoy
- Calificación promedio (de 1 a 5)
- Fecha de ingreso a la empresa

### Comportamientos (Métodos)
- Asignar orden de trabajo
- Iniciar instalación de servicio
- Completar mantenimiento preventivo
- Reportar falla o incidente
- Actualizar estado de disponibilidad
- Registrar materiales utilizados
- Finalizar orden de servicio
- Solicitar soporte de segundo nivel


## Objeto 4: Orden de Servicio

### ¿Qué es?
Un registro de solicitud de trabajo técnico que puede ser una instalación nueva, mantenimiento, reparación o retiro de servicio para un cliente específico.

### Características (Atributos)
- Número de orden (ej: "OS-2025-0001")
- Código de cliente
- Tipo de servicio (instalación, mantenimiento, reparación, retiro)
- Dirección de ejecución
- Fecha de solicitud
- Fecha programada
- Fecha de ejecución real
- Técnico asignado
- Prioridad (baja, media, alta, urgente)
- Estado (pendiente, asignada, en proceso, completada, cancelada)
- Observaciones del cliente
- Observaciones del técnico
- Tiempo estimado en horas
- Materiales requeridos

### Comportamientos (Métodos)
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


## Objeto 5: Factura

### ¿Qué es?
Un documento contable que registra los cargos mensuales por los servicios de telecomunicaciones prestados a un cliente, incluyendo el plan contratado y servicios adicionales.

### Características (Atributos)
- Número de factura (ej: "FAC-2025-001234")
- Código de cliente
- Período facturado (mes/año)
- Fecha de emisión
- Fecha de vencimiento
- Plan de servicio facturado
- Valor del plan base
- Cargos adicionales (instalación, equipo, llamadas)
- Descuentos aplicados
- Subtotal
- Impuestos (IVA)
- Total a pagar
- Estado de pago (pendiente, pagada, vencida, anulada)
- Método de pago (efectivo, transferencia, tarjeta)
- Fecha de pago realizado

### Comportamientos (Métodos)
- Generar factura mensual automática
- Calcular valor total con impuestos
- Aplicar descuentos promocionales
- Registrar pago recibido
- Generar recibo de pago
- Enviar recordatorio de pago
- Marcar como factura vencida
- Anular factura (con motivo)
- Generar duplicado de factura
- Consultar estado de cuenta del cliente
- Programar pago automático
