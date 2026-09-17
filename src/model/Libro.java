package model;

public class Libro {
	protected String id;
	protected String libro;
	protected String autor;
	protected double precio;
	protected int stock;
	
	public Libro(String id, String libro, String autor, double precio, int stock) {
		super();
		this.id = id;
		this.libro = libro;
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

	public String getLibro() {
		return libro;
	}

	public void setLibro(String libro) {
		this.libro = libro;
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

	@Override
	public String toString() {
		return "Libro [id=" + id + ", libro=" + libro + ", autor=" + autor + ", precio=" + precio + ", stock=" + stock
				+ "]";
	}
	
	
	
}
