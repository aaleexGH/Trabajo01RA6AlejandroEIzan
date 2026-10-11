# Base de datos de Librería General Zaragoza

## Índice
1. [Resumen del caso](#1-resumen-del-caso)
2. [Análisis del caso](#2-análisis-del-caso)
3. [Reglas de negocio](#3-reglas-de-negocio)
4. [Diagrama entidad-relación](#4-diagrama-entidad-relación)
5. [Modelo lógico](#5-modelo-lógico)
6. [Script SQL](#6-script-sql-schemasql)
7. [Diccionario de datos](#7-diccionario-de-datos)
8. [Decisiones de diseño](#8-decisiones-de-diseño)
9. [Datos de prueba](#9-datos-de-prueba)
10. [Consultas de prueba](#10-consultas-de-prueba)
11. [Limitaciones y mejoras futuras](#11-limitaciones-y-mejoras-futuras)

---

## 1. Resumen del caso
La Librería General Zaragoza cuenta con tres sucursales (Centro, Delicias y Universidad) que actualmente gestionan su inventario y ventas mediante hojas de cálculo independientes. Esto genera desajustes en el stock y dificulta la consulta global de disponibilidad. Se necesita una base de datos centralizada que permita registrar el catálogo de libros, controlar el inventario exacto por sucursal, gestionar el personal, registrar a los clientes (socios y no socios) y llevar un control preciso de los pedidos y la facturación real.