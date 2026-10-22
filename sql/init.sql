CREATE DATABASE IF NOT EXISTS biblioteca CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE biblioteca;

DROP TABLE IF EXISTS libro;

CREATE TABLE libro (
    id VARCHAR(20) PRIMARY KEY,
    titulo VARCHAR(150) NOT NULL,
    autor VARCHAR(100) NOT NULL,
    precio DECIMAL(6,2) NOT NULL CHECK (precio >= 0),
    stock INT NOT NULL CHECK (stock >= 0)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO libro (id, titulo, autor, precio, stock) VALUES
('LIB001', 'Cien años de soledad', 'Gabriel García Márquez', 18.50, 10),
('LIB002', 'El amor en los tiempos del cólera', 'Gabriel García Márquez', 15.20, 6),
('LIB003', 'Don Quijote de la Mancha', 'Miguel de Cervantes Saavedra', 22.00, 4),
('LIB004', '1984', 'George Orwell', 12.95, 15),
('LIB005', 'Rebelión en la granja', 'George Orwell', 10.50, 12),
('LIB006', 'El principito', 'Antoine de Saint-Exupéry', 8.50, 20),
('LIB007', 'La sombra del viento', 'Carlos Ruiz Zafón', 19.90, 8),
('LIB008', 'Crónica de una muerte anunciada', 'Gabriel García Márquez', 9.99, 14);