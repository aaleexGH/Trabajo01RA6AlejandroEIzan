# Fase 3

|                      |                                                                   |
| -------------------- | ----------------------------------------------------------------- |
| **Trabajo**          | Documentación del sistema                                         |
| **Alumnos**          | Alejandro e Izan                                                  |

---

## Índice

* [Parte 1. El encargo: caso de estudio](#parte-1-el-encargo-caso-de-estudio)
* [Apartado 1. Resumen del caso](#apartado-1-resumen-del-caso)
* [Apartado 2. Analisis del caso](#apartado-2-analisis-del-caso)
* [Apartado 3. Reglas de negocio](#apartado-3-reglas-de-negocio)
* [Apartado 4. Diagrama entidad-relacion](#apartado-4-diagrama-entidad-relacion)
* [Apartado 5. Modelo lógico](#apartado-5-modelo-lógico)
* [Apartado 6. Script SQL ejecutable](#apartado-6-script-sql-ejecutable)
* [Apartado 7. Diccionario de datos](#apartado-7-diccionario-de-datos)
* [Apartado 8. Decisiones de diseño](#apartado-8-decisiones-de-diseño)
* [Apartado 9. Datos de prueba](#apartado-9-datos-de-prueba)
* [Apartado 10. Consultas de prueba](#apartado-10-consultas-de-prueba)
* [Apartado 11. Limitaciones y mejoras futuras](#apartado-11-limitaciones-y-mejoras-futuras)

---

# Parte 1. El encargo: caso de estudio

> Este es el encargo de cliente utilizado para diseñar la base de datos. El caso de estudio de **Cines Palafox es ficticio** y se ha creado exclusivamente con fines académicos.

---

## 1. El cine

**Cines Palafox** dispone de varias salas de proyección y ofrece películas de diferentes géneros y clasificaciones por edades.

Actualmente, parte de la información se gestiona mediante archivos independientes, lo que dificulta conocer rápidamente la programación, las entradas vendidas y la ocupación de las salas.

La dirección quiere disponer de una base de datos centralizada para organizar toda la información.

---

## 2. Las películas

El cine mantiene un catálogo de películas.

De cada película se guarda:

* Un identificador.
* El título.
* La duración en minutos.
* El género.
* La clasificación por edades.
* El año de estreno.

Una misma película puede proyectarse en diferentes sesiones.

Ejemplo:

| **ID** | **Título**     | **Duración** | **Género**      | **Clasificación**  | **Año** |
| -----: | -------------- | -----------: | --------------- | ------------------ | ------: |
|      1 | Interstellar   |      169 min | Ciencia Ficción | +12                |    2014 |
|      2 | Dune Parte Dos |      166 min | Ciencia Ficción | +12                |    2024 |
|      3 | Coco           |      105 min | Animación       | Todos los públicos |    2017 |
|      4 | Oppenheimer    |      180 min | Drama           | +16                |    2023 |

Una película puede tener muchas sesiones, pero cada sesión corresponde a una única película.

---

## 3. Las salas

El cine dispone de diferentes salas de proyección.

De cada sala se guarda:

* Un identificador.
* El número de sala.
* El nombre.
* La capacidad.
* El tipo de sala.

Existen diferentes tipos de sala, como salas normales, premium o 3D.

Ejemplo:

| **ID** | **Número** | **Nombre**     | **Capacidad** | **Tipo** |
| -----: | ---------: | -------------- | ------------: | -------- |
|      1 |          1 | Sala Principal |           150 | Normal   |
|      2 |          2 | Sala Premium   |           100 | Premium  |
|      3 |          3 | Sala 3D        |           120 | 3D       |

Una sala puede tener muchas sesiones, pero cada sesión se realiza en una única sala.

---

## 4. Las sesiones

Una sesión representa un pase concreto de una película.

De cada sesión se guarda:

* Un identificador.
* La fecha.
* La hora de inicio.
* La hora de finalización.
* La película proyectada.
* La sala donde tiene lugar.

Ejemplo:

| **Sesión** | **Película**   | **Sala**       | **Fecha**  | **Hora** |
| ---------: | -------------- | -------------- | ---------- | -------- |
|          1 | Interstellar   | Sala Principal | 10/10/2026 | 18:00    |
|          2 | Interstellar   | Sala Premium   | 10/10/2026 | 21:30    |
|          3 | Dune Parte Dos | Sala 3D        | 10/10/2026 | 19:00    |
|          4 | Coco           | Sala Principal | 11/10/2026 | 17:00    |
|          5 | Oppenheimer    | Sala Premium   | 11/10/2026 | 21:30    |

Una película puede tener muchas sesiones y una sala puede tener muchas sesiones.

---

## 5. Los empleados

Los Cines Palafox cuentan con trabajadores encargados de la atención al público y de las operaciones de venta.

De cada empleado se guarda:

* El DNI.
* El nombre.
* Los apellidos.
* El puesto.
* El correo electrónico.

Los puestos pueden ser, por ejemplo, taquillero o encargado.

Ejemplo:

| **DNI**   | **Nombre** | **Apellidos** | **Puesto** | **Correo**                                    |
| --------- | ---------- | ------------- | ---------- | --------------------------------------------- |
| 12345678A | Javier     | López García  | Taquillero | [javier@palafox.es](mailto:javier@palafox.es) |
| 87654321B | Ana        | Martín Pérez  | Encargada  | [ana@palafox.es](mailto:ana@palafox.es)       |
| 11223344C | Carlos     | Ruiz Sánchez  | Taquillero | [carlos@palafox.es](mailto:carlos@palafox.es) |

Un empleado puede atender muchas compras, pero cada compra es atendida por un único empleado.

---

## 6. Los clientes

Los clientes pueden registrarse en el sistema para realizar compras de entradas.

De cada cliente se guarda:

* Un identificador.
* El nombre.
* Los apellidos.
* El correo electrónico.
* El teléfono.

Ejemplo:

| **ID** | **Nombre** | **Apellidos** | **Correo**                                  | **Teléfono** |
| -----: | ---------- | ------------- | ------------------------------------------- | ------------ |
|      1 | Marta      | García López  | [marta@gmail.com](mailto:marta@gmail.com)   | 600111222    |
|      2 | Pablo      | Ruiz Martín   | [pablo@gmail.com](mailto:pablo@gmail.com)   | 600333444    |
|      3 | Laura      | Sánchez Pérez | [laura@gmail.com](mailto:laura@gmail.com)   | 600555666    |
|      4 | Daniel     | Moreno Díaz   | [daniel@gmail.com](mailto:daniel@gmail.com) | 600777888    |

Un cliente puede realizar varias compras y cada compra pertenece a un único cliente.

---
