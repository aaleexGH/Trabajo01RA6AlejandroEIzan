package modelo;

/**
 * Define la clase libro dentro del paquete modelo, 
 * permitiendo gestionar la informacion basica de libro.
 * 
 * @author Alejandro e Izan
 */

public class Libro {

	/**
	 * Identificador unico del libro
	 */
	private String id;
	/**
	 * titulo del libro
	 */
    private String titulo;
    /**
	 * autor del libro
	 */
    private String autor;
    /**
	 * precio del libro
	 */
    private double precio;
    /**
	 * cantidad de unidades del libro
	 */
    private int stock;
    
    
    
	/**
	 * Constructor para instanciar un nuevo libro
	 * @param id identificador unico del libro
	 * @param titulo titulo del libro
	 * @param autor autor del libro
	 * @param precio precio del libro
	 * @param stock unidades disponibles del libro
	 */
	public Libro(String id, String titulo, String autor, double precio, int stock) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.autor = autor;
		this.precio = precio;
		this.stock = stock;
	}

	/**
	 * Obtiene el indentificador del libro
	 * @return identificador del libro
	 */
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
