# Sistema de Gestión de Biblioteca — Trabajo01RA6

**Módulo**: Desarrollo de Interfaces  
**Autores**: Alejandro e Izan  
**Versión**: 1.0.0  
**Manual completo de despliegue**: [docs/manual_despliegue.pdf](docs/manual_despliegue.pdf)

---

## Descripción del Proyecto

Aplicación de consola en Java diseñada para la gestión integral de un catálogo de libros con arquitectura de doble repositorio intercambiable:
- **Base de datos relacional**: MySQL Server (mediante JDBC y `.env`).
- **Almacenamiento plano**: Fichero secuencial delimitado por `^` (`libros.txt`).

Incluye funciones de consulta global, filtrado por criterios, inserción con validaciones, borrado y sincronización bidireccional de datos entre ambos soportes.

---

## Prerrequisitos del Sistema

Antes de desplegar, asegúrese de contar con las siguientes herramientas configuradas en las variables del sistema (`PATH`):
- **Java Development Kit (JDK)**: Versión 17 o superior.
- **MySQL Server**: Versión 8.0 o superior en ejecución en el puerto `3306`.
- **Cliente de base de datos**: MySQL Command Line Client o MySQL Workbench.

---

## Guía Rápida de Despliegue (Quick Start)

Siga estos 4 pasos desde la terminal de comandos de Windows (`cmd`):

### 1. Clonar o situarse en la raíz del proyecto
```cmd
cd "C:\Desarrollo Interfaces\Trabajo01RA6AlejandroEIzan"
