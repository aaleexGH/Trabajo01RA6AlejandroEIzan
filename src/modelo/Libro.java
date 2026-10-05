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

	/**
     * actualiza el identificador del libro
     * 
     * @param id nuevo identificador del libro
     */
	public void setId(String id) {
		this.id = id;
	}
	
	
	/**
     * Obtiene el título del libro
     * 
     * @return título del libro
     */
	public String getTitulo() {
		return titulo;
	}
	
	/**
     * establece el título del libro.
     * 
     * @param titulo nuevo título del libro.
     */

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	/**
     * obtiene el autor del libro.
     * 
     * @return nombre del autor.
     */
	public String getAutor() {
		return autor;
	}

	/**
     * Establece el autor del libro.
     * 
     * @param autor nuevo nombre del autor.
     */
	public void setAutor(String autor) {
		this.autor = autor;
	}
	
	/**
     * Obtiene el precio del libro.
     * 
     * @return precio en euros.
     */
	public double getPrecio() {
		return precio;
	}
	
	/**
     * Establece el precio del libro.
     * 
     * @param precio nuevo precio del libro.
     */

	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	/**
     * Obtiene el stock disponible del libro.
     * 
     * @return unidades disponibles.
     */

	public int getStock() {
		return stock;
	}
	
	/**
     * Establece el stock disponible del libro.
     * 
     * @param stock  cantidad de unidades en stock.
     */

	public void setStock(int stock) {
		this.stock = stock;
	}
	
	/**
     * Convierte los atributos del objeto Libro a una cadena formateada en CSV,
     * utilizando el caracter '^' como delimitador.
     * 
     * @return Una representación en {@code String} con el formato {@code id^titulo^autor^precio^stock}.
     */
    public String toCSV() {
        return id + "^" + titulo + "^" + autor + "^" + precio + "^" + stock;
    }

    /**
     * Crea un objeto {@link Libro} a partir de una línea formateada en CSV
     * delimitada por el caracter '^'.
     * 
     * @param linea Cadena de texto con los atributos del libro separados por '^'.
     * @return Una nueva instancia de {@code Libro} con los datos extraídos.*/
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
    
    /**
     * Devuelve una cadena de texto legible con la información relevante del libro.
     * 
     * @return Formato visual con ID, Título, Autor, Precio (en €) y Stock.
     */

	@Override
	public String toString() {
		return "ID Libro: " + id + " | Titulo: " + titulo + " | Autor: " + autor + " | Precio: " + precio + " € | Stock: " + stock;
	}
    
    
	
}
