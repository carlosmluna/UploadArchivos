package mx.org.ccti.uploadfile.bo;

public class CampoIntegPagoBo {
	// Atributos
	private int 	id;
	private String 	nombreCampo;
	private String  campoTbl;
	private String 	tipoCampo;
	
	// Metodos Gettes y setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombreCampo() {
		return nombreCampo;
	}
	public void setNombreCampo(String nombreCampo) {
		this.nombreCampo = nombreCampo;
	}
	public String getCampoTbl() {
		return campoTbl;
	}
	public void setCampoTbl(String campoTbl) {
		this.campoTbl = campoTbl;
	}
	public String getTipoCampo() {
		return tipoCampo;
	}
	public void setTipoCampo(String tipoCampo) {
		this.tipoCampo = tipoCampo;
	}
}
