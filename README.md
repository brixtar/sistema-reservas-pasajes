# Sistema de Gestión y Reservas de Pasajes - El Buen Suceso

Sistema de escritorio integral desarrollado en **Java (Swing)** y persistencia **MySQL** diseñado para la administración operativa y comercial del transporte de pasajeros de media y larga distancia.

## 🚀 Características Principales

- **Gestión de Pasajeros:** Alta, modificación, historial de viajes y búsqueda rápida por documento.
- **Administración de Viajes y Rutas:** Programación de salidas, control de destinos, frecuencias y asignación de unidades.
- **Control de Flota / Vehículos:** Registro de unidades, capacidad de asientos y estado operativo.
- **Venta y Reserva de Pasajes:** Asignación visual de butacas, confirmación de reservas y emisión de comprobantes.
- **Informes y Reportes:** Generación e impresión de reportes de recaudación, listas de pasajeros por viaje y comisiones.

## 🛠️ Tecnologías Utilizadas

- **Lenguaje:** Java (Java SE / Swing)
- **Base de Datos:** MySQL
- **Conectividad:** JDBC (MySQL Connector)
- **Generación de Reportes:** JasperReports / Impresión gráfica Java

## 📂 Estructura del Proyecto

```
Reservas-El-Buen-Suceso/
├── database/     # Controladores de conexión JDBC y consultas SQL
├── funciones/    # Utilidades de formateo, validaciones y lógica auxiliar
├── imagenes/     # Recursos gráficos e íconos de la interfaz
├── modelos/      # Modelos de datos (Pasajero, Viaje, Vehículo, etc.)
├── reportes/     # Plantillas y generadores de informes impresos
├── screens/      # Vistas modulares por dominio (pasajeros, vehículos, viajes, informes)
└── sistema/      # Pantalla principal, autenticación y arranque
```

## ⚙️ Requisitos de Ejecución

1. Java Development Kit (JDK 8 o superior).
2. Servidor MySQL activo (ej. XAMPP / WampServer) con la base de datos `viajes`.
