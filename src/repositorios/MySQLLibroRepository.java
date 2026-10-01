package repositorios;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import modelo.Libro;

/**
 * Implementación de la interfaz {@link LibroRepository} para la gestión de 
 * persistencia en una base de datos MySQL.
 * <p>
 * Gestiona las conexiones de forma independiente en cada método mediante 
 * bloques try-with-resources y previene la inyección SQL utilizando 
 * sentencias preparadas.
 * 
 * @author Alejandro e Izan
 */

public class MySQLLibroRepository implements LibroRepository {

	/**
     * Mapea los datos de la fila actual de un objeto {@link ResultSet} instanciando 
     * un nuevo objeto {@link Libro}.
     * 
     * @param rs el conjunto de resultados posicionado en la fila a mapear
     * @return un objeto {@link Libro} con los atributos extraídos de la base de datos
     * @throws SQLException si ocurre un error al acceder a las columnas o extraer sus tipos de datos
     */
	
    private Libro mapResultSetToLibro(ResultSet rs) throws SQLException {
        return new Libro(
            rs.getString("id"),
            rs.getString("titulo"),
            rs.getString("autor"),
            rs.getDouble("precio"),
            rs.getInt("stock")
        );
    }

    @Override
    public List<Libro> findAll() {
        List<Libro> libros = new ArrayList<>();
        String sql = "SELECT * FROM libros";
        
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
             
            while (rs.next()) {
                libros.add(mapResultSetToLibro(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener los libros: " + e.getMessage());
        }
        return libros;
    }

    @Override
    public List<Libro> findByTitulo(String titulo) {
        List<Libro> libros = new ArrayList<>();
        String sql = "SELECT * FROM libros WHERE LOWER(titulo) LIKE ?";
        
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
             
            ps.setString(1, "%" + titulo.toLowerCase() + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    libros.add(mapResultSetToLibro(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar por título: " + e.getMessage());
        }
        return libros;
    }

    @Override
    public List<Libro> findByAutor(String autor) {
        List<Libro> libros = new ArrayList<>();
        String sql = "SELECT * FROM libros WHERE LOWER(autor) LIKE ?";
        
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
             
            ps.setString(1, "%" + autor.toLowerCase() + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    libros.add(mapResultSetToLibro(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar por autor: " + e.getMessage());
        }
        return libros;
    }

    @Override
    public List<Libro> findByRangoPrecio(double min, double max) {
        List<Libro> libros = new ArrayList<>();
        String sql = "SELECT * FROM libros WHERE precio >= ? AND precio <= ?";
        
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
             
            ps.setDouble(1, min);
            ps.setDouble(2, max);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    libros.add(mapResultSetToLibro(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar por precio: " + e.getMessage());
        }
        return libros;
    }

    @Override
    public List<Libro> findByStockMinimo(int stockMinimo) {
        List<Libro> libros = new ArrayList<>();
        String sql = "SELECT * FROM libros WHERE stock >= ?";
        
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
             
            ps.setInt(1, stockMinimo);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    libros.add(mapResultSetToLibro(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar por stock: " + e.getMessage());
        }
        return libros;
    }

    /**
     * Inserta un nuevo registro en la tabla de la base de datos mapeando los atributos del objeto.
     * Utiliza los parámetros de una sentencia preparada para almacenar id, título, autor, precio y stock.
     * 
     * @param libro el objeto de modelo con los datos a insertar
     */
    
    @Override
    public void insertar(Libro libro) {
        String sql = "INSERT INTO libros (id, titulo, autor, precio, stock) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
             
            ps.setString(1, libro.getId());
            ps.setString(2, libro.getTitulo());
            ps.setString(3, libro.getAutor());
            ps.setDouble(4, libro.getPrecio());
            ps.setInt(5, libro.getStock());
            
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al insertar el libro: " + e.getMessage());
        }
    }

    /**
     * Elimina el registro correspondiente a la base de datos buscando por el identificador único.
     * Ejecuta una sentencia {@code DELETE FROM} directa.
     * 
     * @param id el identificador único asociado al libro que se desea eliminar
     */
    
    @Override
    public void eliminarPorId(String id) {
        String sql = "DELETE FROM libros WHERE id = ?";
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
             
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al eliminar el libro: " + e.getMessage());
        }
    }

    /**
     * Reemplaza todos los datos actuales de la tabla por los de la lista proporcionada.
     * Realiza un vaciado total previo de la tabla ejecutando una consulta de borrado,
     * para luego iterar y registrar cada objeto mediante el método {@link #insertar(Libro)}.
     * 
     * @param libros la lista completa que conformará el nuevo estado de la base de datos
     */
    
    @Override
    public void guardarTodos(List<Libro> libros) {
        String sqlDelete = "DELETE FROM libros";
        try (Connection con = ConexionDB.conectar();
             PreparedStatement psDelete = con.prepareStatement(sqlDelete)) {
             
            psDelete.executeUpdate();
            for (Libro libro : libros) {
                insertar(libro);
            }
        } catch (SQLException e) {
            System.err.println("Error en la copia a MySQL: " + e.getMessage());
        }
    }
}