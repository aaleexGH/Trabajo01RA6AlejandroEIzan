package repositorios;

import java.util.List;

import modelo.Libro;

public interface LibroRepository {
    List<Libro> findAll();
    List<Libro> findByTitulo(String titulo);
    List<Libro> findByAutor(String autor);
    List<Libro> findByRangoPrecio(double min, double max);
    List<Libro> findByStockMinimo(int stockMinimo);
    void insertar(Libro libro);    
    void eliminarPorId(String id);
    void guardarTodos(List<Libro> libros);
}
