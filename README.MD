# Bootcamp POO Java - William Andres Aguilera Lasprilla

## Información del Proyecto

**Estudiante:** William Andres Aguilera Lasprilla  
**Ficha:** 3228973A  
**Dominio:** Empresa de Telecomunicaciones "ConnectFast"  
**Ubicación:** Bogotá, Usaquén  

ConnectFast es un proveedor de servicios de internet fibra óptica, telefonía fija y televisión digital que opera en Bogotá. La empresa cuenta con 20 técnicos instaladores, 15 asesores de soporte y cobertura en 40 localidades de la ciudad, atendiendo aproximadamente 8,000 clientes activos.

---

## Estructura del Repositorio

```
bc-poo-java-william-aguilera/
├── proyecto-final/          Sistema integrado final (Semana 09)
│   ├── README.md
│   ├── src/
│   └── docs/
├── semana-01/               Introducción a POO
├── semana-02/               Encapsulación y relaciones
├── semana-03/               Constructores y validaciones
├── semana-04/               Herencia
├── semana-05/               Polimorfismo
├── semana-06/               Abstracción e interfaces
├── semana-07/               Paquetes y excepciones
├── semana-08/               Colecciones avanzadas
└── README.md                Este archivo
```

---

## Progreso del Bootcamp

### Semana 01: Introducción a POO
**Estado:** Completada  
**Concepto:** Clases y objetos básicos  
**Implementación:** Se crearon las clases fundamentales ServicePlan y Cliente. Se identificaron los objetos principales del dominio y se implementaron instancias básicas sin encapsulamiento. Se realizó un análisis comparativo entre programación estructurada y orientada a objetos.

### Semana 02: Encapsulación Básica
**Estado:** Completada  
**Concepto:** Modificadores de acceso y ArrayList  
**Implementación:** Se agregó encapsulamiento con private, getters y setters. Se introdujeron las clases Tecnico e Instalacion para modelar las relaciones entre entidades. Se implementó GestorInstalaciones usando ArrayList para gestionar múltiples instalaciones.

### Semana 03: Encapsulación Avanzada
**Estado:** Completada  
**Concepto:** Constructores sobrecargados y validaciones  
**Implementación:** Cada clase recibió múltiples constructores con diferentes combinaciones de parámetros. Se agregaron validaciones exhaustivas en todos los setters que lanzan IllegalArgumentException cuando los datos son inválidos. Los códigos de entidades siguen el formato PLAN-, CLI-, TEC-, INST-.

### Semana 04: Herencia
**Estado:** Completada  
**Concepto:** Jerarquías de clases  
**Implementación:** Se convirtió ServicePlan en clase padre y se crearon tres subclases: PlanResidencial (para hogares), PlanEmpresarial (para empresas) y PlanGamer (para gaming). Cada subclase hereda atributos y métodos comunes, pero implementa comportamiento específico mediante sobrescritura.

### Semana 05: Polimorfismo
**Estado:** Completada  
**Concepto:** Sobrecarga y sobrescritura  
**Implementación:** Se implementó sobrecarga en GestorPlanes con tres versiones del método buscarPlan (por código, por rango de precio, por velocidad). Los métodos calcularPrecioConDescuento y mostrarInformacion se sobrescribieron en cada subclase para aplicar lógica específica.

### Semana 06: Abstracción
**Estado:** Completada  
**Concepto:** Clases abstractas e interfaces  
**Implementación:** ServicePlan se convirtió en clase abstracta con tres métodos abstractos obligatorios. Se crearon tres interfaces: Facturable (facturación), Auditable (trazabilidad) y Promocionable (descuentos). ServicePlan implementa Facturable y Auditable, mientras que PlanResidencial y PlanGamer implementan además Promocionable.

### Semana 07: Paquetes y Excepciones
**Estado:** Completada  
**Concepto:** Organización modular y manejo de errores  
**Implementación:** Todo el código se reorganizó en paquetes com.connectfast.modelo, com.connectfast.servicio, com.connectfast.excepciones e com.connectfast.interfaces. Se crearon tres excepciones personalizadas tipo checked: PlanNoDisponibleException, VelocidadInsuficienteException y ContratoInvalidoException. Todos los métodos críticos declaran y manejan estas excepciones apropiadamente.

### Semana 08: Colecciones Avanzadas
**Estado:** Completada  
**Concepto:** Estructuras de datos eficientes  
**Implementación:** Se migró de ArrayList a HashMap para búsquedas en tiempo constante O(1). Se implementaron colecciones genéricas con tipos específicos. Se agregaron métodos de búsqueda, filtrado y ordenamiento usando streams y comparadores personalizados.

### Semana 09: Proyecto Final Integrador
**Estado:** Completada  
**Concepto:** Consolidación completa  
**Implementación:** Integración de todos los conceptos aprendidos en un sistema funcional completo. Incluye documentación técnica detallada, diagramas UML de clases y casos de uso, y manual de usuario. El sistema permite gestionar clientes, planes, instalaciones y facturación de manera integral.

---

## Ejecutar el Proyecto Final

El directorio proyecto-final contiene la versión más completa del sistema con todos los conceptos integrados.

**Requisitos:**
- Java JDK 17 o superior instalado
- Variable de entorno JAVA_HOME configurada

**Compilación y ejecución desde terminal:**

```bash
cd proyecto-final

javac -d bin src/com/connectfast/Main.java src/com/connectfast/*/*.java

java -cp bin com.connectfast.Main
```

**Ejecución desde IntelliJ IDEA:**
1. Abrir el proyecto en IntelliJ IDEA
2. Marcar la carpeta src como Sources Root
3. Ejecutar Main.java con clic derecho > Run

---

## Arquitectura del Sistema ConnectFast

### Jerarquía de Clases

El sistema se estructura alrededor de ServicePlan como clase abstracta padre:

```
ServicePlan (abstracta)
    |
    +-- PlanResidencial (Internet + TV + Telefonía)
    +-- PlanEmpresarial (Internet simétrico + IP fija + SLA)
    +-- PlanGamer (Ultra velocidad + Baja latencia)
```

Cada tipo de plan tiene características y reglas de negocio específicas. Los planes residenciales y gamer permiten promociones, mientras que los empresariales tienen contratos negociados directamente.

### Validaciones Implementadas

El sistema valida exhaustivamente todos los datos ingresados:

- Códigos de entidades deben seguir formatos específicos (PLAN-XXX, CLI-XXX, etc.)
- Velocidades de internet entre 10 y 1000 Mbps según disponibilidad de zona
- Precios dentro de rangos establecidos por tipo de plan
- Duración de contratos entre 1 y 36 meses
- Disponibilidad de cupos antes de agregar nuevos clientes

Cuando se detectan datos inválidos, el sistema lanza excepciones descriptivas que indican exactamente qué campo tiene problemas y por qué.

### Gestión de Excepciones

Se implementaron tres excepciones personalizadas tipo checked que representan errores recuperables del negocio:

**PlanNoDisponibleException:** Indica que un plan solicitado no está disponible, ya sea porque el cupo está lleno, el plan fue descontinuado o no existe en el sistema. Permite al usuario intentar con otro plan.

**VelocidadInsuficienteException:** Se lanza cuando la velocidad solicitada excede la capacidad de infraestructura disponible en la zona del cliente. Incluye información sobre la velocidad máxima disponible para ofrecer alternativas.

**ContratoInvalidoException:** Señala problemas en los datos del contrato como formato de código incorrecto, duración inválida o precios fuera de rango. Identifica específicamente qué campo tiene el error.

---

## Sobre ConnectFast

ConnectFast diferencia sus servicios según el tipo de cliente:

**Planes Residenciales:** Diseñados para hogares y familias. Incluyen paquetes de internet de diferentes velocidades, canales de televisión y opción de telefonía fija. Los precios son accesibles y frecuentemente tienen promociones estacionales.

**Planes Empresariales:** Orientados a PyMEs y grandes empresas. Ofrecen internet simétrico garantizado, direcciones IP fijas, acuerdos de nivel de servicio (SLA) personalizados y soporte técnico prioritario 24/7. Los contratos se negocian individualmente según necesidades.

**Planes Gamer:** Especializados para jugadores competitivos y streamers. Garantizan latencia ultra baja (menor a 10ms), velocidades superiores a 100 Mbps y ancho de banda dedicado. Incluyen router gaming de alta gama y priorización de tráfico para aplicaciones de gaming.

La empresa mantiene infraestructura de fibra óptica propia en Bogotá con nodos distribuidos estratégicamente. El equipo técnico realiza instalaciones profesionales y el centro de soporte opera continuamente para resolver incidencias.

---

## Tecnologías y Herramientas

**Lenguaje:** Java SE 17  
**IDE:** IntelliJ IDEA Community Edition  
**Control de versiones:** Git  
**Repositorio:** GitHub  
**Documentación:** Markdown, Javadoc  
**Diagramas:** PlantUML, Draw.io  

---

## Repositorio

https://github.com/AndresXweb/bc-poo-java-william-aguilera

---

Fecha de actualización: Diciembre 2024