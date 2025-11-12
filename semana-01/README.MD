# Semana 01 - Introducción a POO

## Descripción

Implementación de las clases básicas del dominio ConnectFast: ServicePlan (Plan de Servicio) y Cliente. Se crearon objetos de ambas clases y se demostró su funcionamiento mediante un programa principal que muestra la información de 5 planes diferentes y 3 clientes registrados.

## Objetivos Cumplidos

-  Identificar 5 objetos principales del dominio de telecomunicaciones
-  Comparar programación estructurada vs POO
-  Implementar clase ServicePlan con atributos, constructor y métodos
-  Implementar clase Cliente como clase secundaria
-  Crear programa Main con múltiples instancias
-  Demostrar relación entre clases
-  Documentar análisis del dominio

## Archivos Entregados

- **src/ServicePlan.java** - Clase principal que representa un plan de servicio con 5 atributos, constructor y 2 métodos
- **src/Cliente.java** - Clase secundaria que representa un cliente con 4 atributos, constructor y 1 método
- **src/Main.java** - Programa de demostración que crea 5 planes y 3 clientes
- **docs/ANALISIS.md** - Análisis completo del dominio incluyendo identificación de objetos y comparación de paradigmas

## Instrucciones de Ejecución

```bash
cd src
javac *.java
java Main
```

## Salida Esperada

```
CONNECTFAST - PLANES DE SERVICIO    

 PLAN DE SERVICIO 
Codigo: PLAN-001
Nombre: Basico Hogar
Velocidad: 50 Mbps
Canales TV: 80
Precio mensual: $45000.0

 PLAN DE SERVICIO 
Codigo: PLAN-002
Nombre: Premium Hogar
Velocidad: 200 Mbps
Canales TV: 150
Precio mensual: $85000.0

 PLAN DE SERVICIO 
Codigo: PLAN-003
Nombre: Empresarial Basico
Velocidad: 100 Mbps
Canales TV: 100
Precio mensual: $120000.0

 PLAN DE SERVICIO 
Codigo: PLAN-004
Nombre: Empresarial Plus
Velocidad: 500 Mbps
Canales TV: 150
Precio mensual: $250000.0

 PLAN DE SERVICIO 
Codigo: PLAN-005
Nombre: Gamer Ultra
Velocidad: 300 Mbps
Canales TV: 120
Precio mensual: $150000.0

   CALCULO DE PRECIO CON IMPUESTO      

Plan: Basico Hogar
Precio con IVA (19%): $53550.0

Plan: Premium Hogar
Precio con IVA (19%): $101150.0

        CLIENTES REGISTRADOS           

 INFORMACION DEL CLIENTE 
Codigo: CLI-001
Nombre: Juan Perez
Direccion: Calle 123 #45-67, Usaquen
Plan contratado: PLAN-001

 INFORMACION DEL CLIENTE
Codigo: CLI-002
Nombre: Maria Garcia
Direccion: Carrera 7 #89-12, Chapinero
Plan contratado: PLAN-002

 INFORMACION DEL CLIENTE 
Codigo: CLI-003
Nombre: Carlos Rodriguez
Direccion: Avenida 68 #23-45, Suba
Plan contratado: PLAN-005

 RELACION CLIENTE - PLAN             

El cliente Juan Perez tiene contratado el plan: PLAN-001
El cliente Maria Garcia tiene contratado el plan: PLAN-002
El cliente Carlos Rodriguez tiene contratado el plan: PLAN-005
```

## Conceptos Aplicados

- **Clases y Objetos:** Definición de clases ServicePlan y Cliente, creación de múltiples objetos
- **Atributos:** Declaración de características de los objetos
- **Constructores:** Inicialización de objetos con valores específicos
- **Métodos:** Implementación de comportamientos (showInfo, calcularPrecioConImpuesto, mostrarInformacion)
- **Encapsulación básica:** Agrupación de datos y métodos relacionados
- **Relación entre clases:** Conexión entre Cliente y ServicePlan mediante atributo planContratado

## Aprendizajes

Esta primera semana permitió comprender los conceptos fundamentales de la Programación Orientada a Objetos aplicados a un caso real. Se logró identificar objetos del mundo real y modelarlos como clases en Java, demostrando las ventajas de POO sobre la programación estructurada tradicional.

## Notas

- El código está implementado de forma básica sin uso de modificadores de acceso avanzados
- Las clases no incluyen getters y setters por simplicidad (se implementarán en semanas posteriores)
- La relación entre Cliente y ServicePlan es simple usando el código del plan como String