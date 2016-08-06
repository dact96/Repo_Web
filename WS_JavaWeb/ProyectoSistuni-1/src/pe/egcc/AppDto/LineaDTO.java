package pe.egcc.AppDto;

public class LineaDTO {

	// atributos
    private int idlinea;
    private String nombre;
    
    public LineaDTO() {
		// TODO Auto-generated constructor stub
	}

	public LineaDTO(int idlinea, String nombre) {
		super();
		this.idlinea = idlinea;
		this.nombre = nombre;
	}

	public int getIdlinea() {
		return idlinea;
	}

	public void setIdlinea(int idlinea) {
		this.idlinea = idlinea;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
    
   
}
