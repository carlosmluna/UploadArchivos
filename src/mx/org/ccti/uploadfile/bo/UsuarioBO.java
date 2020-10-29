package mx.org.ccti.uploadfile.bo;

public class UsuarioBO {
	// ***** Atributos *****
	private String usuario;
	private String descripcion;
	private int    encontrado;
	
	// ***** Metodos Getters y Setters *****
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getEncontrado() {
		return encontrado;
	}
	public void setEncontrado(int encontrado) {
		this.encontrado = encontrado;
	}
	
}
