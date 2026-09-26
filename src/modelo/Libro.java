package modelo;

public class Libro {

	private String id;
    private String titulo;
    private String autor;
    private double precio;
    private int stock;
    
	public Libro(String id, String titulo, String autor, double precio, int stock) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.autor = autor;
		this.precio = precio;
		this.stock = stock;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	
    public String toCSV() {
        return id + "^" + titulo + "^" + autor + "^" + precio + "^" + stock;
    }

    public static Libro fromCSV(String linea) {
        String[] partes = linea.split("\\^");
        return new Libro(
            partes[0], 
            partes[1], 
            partes[2], 
            Double.parseDouble(partes[3]), 
            Integer.parseInt(partes[4])
        );
    }

	@Override
	public String toString() {
		return "ID Libro: " + id + " | Titulo: " + titulo + " | Autor: " + autor + " | Precio: " + precio + " € | Stock: " + stock;
	}
    
    
	
}
