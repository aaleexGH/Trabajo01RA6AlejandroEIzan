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

## 2. Los libros y las editoriales

La biblioteca dispone de un catálogo de libros. De cada libro se guarda un identificador interno, el código de catálogo, el título, el año de publicación, el número de páginas y la editorial que publicó la edición.

Cada libro se asocia a una sola editorial. Una editorial puede publicar muchos libros.

### Ejemplo de libros

| **ID** | **Código** | **Título** | **Año** | **Páginas** | **Editorial** |
|---:|---|---|---:|---:|---|
| 1 | LIB001 | Cien años de soledad | 1967 | 496 | Penguin Random House |
| 2 | LIB002 | Don Quijote de la Mancha | 1605 | 1056 | Cátedra |
| 3 | LIB003 | La sombra del viento | 2001 | 576 | Planeta |
| 4 | LIB004 | El principito | 1943 | 96 | Salamandra |
| 5 | LIB005 | 1984 | 1949 | 328 | Alianza Editorial |
| 6 | LIB006 | Antología del cuento hispanoamericano | 2020 | 420 | Alianza Editorial |

### Ejemplo de editoriales

| **ID** | **Nombre** | **País** | **Teléfono** |
|---:|---|---|---|
| 1 | Cátedra | España | 910000101 |
| 2 | Penguin Random House | España | 910000102 |
| 3 | Planeta | España | 930000103 |
| 4 | Salamandra | España | 930000104 |
| 5 | Alianza Editorial | España | 910000105 |

Los nombres se emplean como datos de prueba; las filas no pretenden describir las existencias reales de la biblioteca.

## 3. Los autores

Un libro puede tener uno o varios autores y un autor puede participar en diferentes libros. Por eso, la relación entre libros y autores puede ser de muchos a muchos.

De cada autor se guarda un identificador, su nombre, apellidos, nacionalidad y año de nacimiento. Además, se quiere distinguir si participa como autor principal o colaborador en cada libro.

### Ejemplo de autores

| **ID** | **Nombre** | **Apellidos** | **Nacionalidad** | **Año de nacimiento** |
|---:|---|---|---|---:|
| 1 | Gabriel | García Márquez | Colombiana | 1927 |
| 2 | Miguel de | Cervantes Saavedra | Española | 1547 |
| 3 | Carlos Ruiz | Zafón | Española | 1964 |
| 4 | Antoine | de Saint-Exupéry | Francesa | 1900 |
| 5 | George | Orwell | Británica | 1903 |
| 6 | Julio | Cortázar | Argentina | 1914 |
| 7 | Jorge Luis | Borges | Argentina | 1899 |

La relación libro-autor guardará también el papel del autor en esa obra.

## 4. Los ejemplares y el inventario

La biblioteca no presta únicamente títulos abstractos: presta ejemplares físicos concretos. Cada ejemplar tiene un código de barras propio y está asociado a un libro del catálogo.

De cada ejemplar se guarda:

- Un identificador.
- El libro al que corresponde.
- El código de barras.
- La sección o ubicación.
- El estado actual.

Los estados utilizados en este modelo son `DISPONIBLE`, `PRESTADO`, `REPARACION` y `PERDIDO`.

### Ejemplo de ejemplares

| **ID** | **Código de barras** | **Libro** | **Sección** | **Estado** |
|---:|---|---|---|---|
| 1 | BIB-0001 | Don Quijote de la Mancha | Adultos | PRESTADO |
| 2 | BIB-0002 | Don Quijote de la Mancha | Adultos | DISPONIBLE |
| 3 | BIB-0003 | Cien años de soledad | Adultos | PRESTADO |
| 4 | BIB-0004 | Cien años de soledad | Adultos | DISPONIBLE |
| 5 | BIB-0005 | La sombra del viento | Novela | PRESTADO |
| 6 | BIB-0006 | La sombra del viento | Novela | DISPONIBLE |
| 7 | BIB-0007 | El principito | Infantil | PRESTADO |
| 8 | BIB-0008 | El principito | Infantil | DISPONIBLE |

Un libro puede tener varios ejemplares. El número de ejemplares disponibles se obtiene contando los ejemplares cuyo estado sea `DISPONIBLE`.

## 5. Los lectores

Los lectores registrados pueden llevarse ejemplares en préstamo. De cada lector se guarda un identificador, nombre, apellidos, correo electrónico, teléfono y fecha de alta.

### Ejemplo de lectores

| **ID** | **Nombre** | **Apellidos** | **Correo** | **Teléfono** | **Fecha de alta** |
|---:|---|---|---|---|---|
| 1 | Elena | Ruiz | elena.ruiz@example.com | 600111222 | 2025-09-01 |
| 2 | Andrés | Pérez | andres.perez@example.com | 600333444 | 2025-10-15 |
| 3 | Marta | López | marta.lopez@example.com | 600555666 | 2026-01-12 |
| 4 | Laura | Gómez | laura.gomez@example.com | 600777888 | 2026-03-20 |

## 6. El personal

El personal registra las operaciones de préstamo y ayuda a mantener la información actualizada.

De cada empleado se guarda el DNI, nombre, apellidos, puesto y correo electrónico.

### Ejemplo de empleados

| **DNI** | **Nombre** | **Apellidos** | **Puesto** | **Correo** |
|---|---|---|---|---|
| 12345678A | Ana | Martín López | Bibliotecaria | ana.martin@example.com |
| 87654321B | Carlos | Pérez Sanz | Auxiliar | carlos.perez@example.com |
| 11223344C | Elena | Sanz Ruiz | Bibliotecaria | elena.sanz@example.com |

Un empleado puede registrar muchos préstamos y cada préstamo queda asociado a un único empleado.

## 7. Los préstamos

Cada operación de préstamo registra cuándo sale el material, cuándo está previsto devolverlo, qué lector se lo lleva y qué empleado registra la operación.

De cada préstamo se guarda:

- Un identificador.
- La fecha del préstamo.
- La fecha prevista de devolución.
- El lector.
- El empleado responsable del registro.
- El estado del préstamo.

Los estados del modelo son `ACTIVO`, `DEVUELTO` y `CANCELADO`. La duración de los préstamos que aparece en los ejemplos es ficticia y no representa una condición oficial de la biblioteca.

### Ejemplo de préstamos

| **Préstamo** | **Fecha** | **Devolución prevista** | **Lector** | **Empleado** | **Estado** |
|---:|---|---|---|---|---|
| 1001 | 2026-10-01 | 2026-10-29 | Elena Ruiz | Ana Martín López | ACTIVO |
| 1002 | 2026-08-20 | 2026-09-17 | Andrés Pérez | Carlos Pérez Sanz | ACTIVO |
| 1003 | 2026-09-01 | 2026-09-29 | Elena Ruiz | Ana Martín López | DEVUELTO |
| 1004 | 2026-10-08 | 2026-11-05 | Marta López | Elena Sanz Ruiz | ACTIVO |
| 1005 | 2026-10-10 | 2026-11-07 | Laura Gómez | Ana Martín López | ACTIVO |
| 1006 | 2026-10-09 | 2026-11-06 | Elena Ruiz | Carlos Pérez Sanz | ACTIVO |

Un préstamo puede contener varios ejemplares, por lo que se necesita un detalle de préstamo.

## 8. El detalle de préstamo

Cada línea del detalle relaciona un préstamo con un ejemplar concreto. También permite guardar la fecha en la que se devolvió realmente ese ejemplar.

### Ejemplo de detalle

| **ID** | **Préstamo** | **Ejemplar** | **Fecha de devolución real** |
|---:|---:|---|---|
| 1 | 1001 | BIB-0001 | — |
| 2 | 1002 | BIB-0003 | — |
| 3 | 1003 | BIB-0013 | 2026-09-20 |
| 4 | 1004 | BIB-0005 | — |
| 5 | 1005 | BIB-0007 | — |
| 6 | 1006 | BIB-0011 | — |

Una línea sin fecha de devolución real representa un ejemplar que todavía no se ha registrado como devuelto. El estado del ejemplar debe mantenerse coherente con sus préstamos activos.

## 9. Lo que la biblioteca quiere poder consultar

La base de datos debe ayudar a responder preguntas como estas:

- ¿Qué libros forman parte del catálogo y quién los publicó?
- ¿Qué autores participan en cada libro?
- ¿Cuántos ejemplares hay de cada título y cuántos están disponibles?
- ¿Qué préstamos siguen activos?
- ¿Qué préstamos han superado la fecha prevista de devolución?
- ¿Qué lector tiene más préstamos registrados?
- ¿Qué libros se han prestado más veces?
- ¿Qué empleado ha registrado más préstamos?
- ¿Qué ejemplares corresponden a un préstamo determinado?

## 10. Resumen del encargo

El objetivo es diseñar una base de datos que permita gestionar el catálogo bibliográfico, las editoriales, los autores, los ejemplares físicos, los lectores, los empleados y los préstamos.

La solución deberá identificar las entidades, definir sus relaciones, documentar las claves, crear el script SQL, insertar datos de prueba y comprobar el funcionamiento con consultas.

---

# Apartado 1. Resumen del caso

La Biblioteca de Aragón, en Zaragoza, sirve como contexto para un caso académico de gestión bibliotecaria. El sistema propuesto centraliza la información de libros, autores, editoriales, ejemplares, lectores, empleados y préstamos.

El catálogo describe cada obra y su editorial; la relación entre libros y autores permite registrar obras con varios autores; la tabla de ejemplares identifica cada copia física. Los préstamos relacionan a los lectores y empleados con los ejemplares que salen de la biblioteca.

---