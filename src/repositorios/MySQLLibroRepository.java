package repositorios;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import modelo.Libro;

public class MySQLLibroRepository implements LibroRepository {

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