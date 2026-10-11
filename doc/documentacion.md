# Fase 3. Diseño y documentación de la base de datos de la Biblioteca de Aragón



| | |
|---|---|
| **Fase** | 3. Diseño y documentación de la base de datos |
| **Alumnos** | Alejandro e Izan |

---

## Índice

- [Parte 1. El encargo: caso de estudio](#parte-1-el-encargo-caso-de-estudio)
- [Apartado 1. Resumen del caso](#apartado-1-resumen-del-caso)
- [Apartado 2. Análisis del caso](#apartado-2-análisis-del-caso)
- [Apartado 3. Reglas de negocio](#apartado-3-reglas-de-negocio)
- [Apartado 4. Diagrama entidad-relación](#apartado-4-diagrama-entidad-relación)
- [Apartado 5. Modelo lógico](#apartado-5-modelo-lógico)
- [Apartado 6. Script SQL ejecutable](#apartado-6-script-sql-ejecutable)
- [Apartado 7. Diccionario de datos](#apartado-7-diccionario-de-datos)
- [Apartado 8. Decisiones de diseño](#apartado-8-decisiones-de-diseño)
- [Apartado 9. Datos de prueba](#apartado-9-datos-de-prueba)
- [Apartado 10. Consultas de prueba](#apartado-10-consultas-de-prueba)
- [Apartado 11. Limitaciones y mejoras futuras](#apartado-11-limitaciones-y-mejoras-futuras)

---

# Parte 1. El encargo: caso de estudio

> La biblioteca necesita organizar su catálogo, controlar los ejemplares físicos y registrar los préstamos. El objetivo del caso es identificar qué información debe guardarse y cómo se relaciona.

## 1. La biblioteca

La **Biblioteca de Aragón, en Zaragoza**, sirve como contexto institucional para este caso académico. Para el ejercicio se plantea un sistema ficticio que centraliza los datos bibliográficos, los ejemplares disponibles y los préstamos a lectores.

La gestión necesita conocer qué libros forman parte del catálogo, cuántos ejemplares existen de cada título, dónde están ubicados y cuáles se encuentran prestados, disponibles o en reparación.

