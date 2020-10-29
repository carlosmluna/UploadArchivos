package mx.org.ccti.uploadfile.controlador;

import mx.com.bimbo.equipos.modelo.dto.EquipoDTO;
import mx.org.ccti.uploadfile.bo.Mensaje;
import mx.org.ccti.uploadfile.modelo.BaseDatos;
import mx.org.ccti.uploadfile.modelo.Archivos;
import mx.org.ccti.uploadfile.util.Constantes;

public class UploadFileControlador {

	Constantes constantes = new Constantes();
	BaseDatos  baseDatos  = new BaseDatos();
	Archivos   archivos   = new Archivos();
	Mensaje    mensaje    = new Mensaje();
	
	public long generarSecuenciaArchivos() {
		long secuencia = baseDatos.getSecuenciaArchivoDatos();
		return secuencia;
	}
	
	public Mensaje eliminaDetalleArchivo( String prmLocalidad ) {
		mensaje = baseDatos.eliminaDetalleArchivoDatos( prmLocalidad );
		return mensaje;
	}
	
	public EquipoDTO leerCiontenidoArchivo( String prmRutaArch, String Archivo, String localidad ) {
	    // mensaje = baseDatos.insertaDetalleArchivo( prmRutaArch, Archivo, "Esto es una prueba", localidad);
		EquipoDTO equipoDTO = baseDatos.insertaDetalleArchivo( prmRutaArch, localidad);
		return equipoDTO;
	}
	
	public Mensaje insertaNombreArchivo ( long secuencia, String Archivo, String localidad ) {
		mensaje = baseDatos.insertaNombreArchivo(secuencia, Archivo, "Archivo generado por la aplicacion UPLOAD FILES.");
		return mensaje;
	}
			
    public Mensaje copiaArchivoServidor( String prmRutaArchivo ) {
    	mensaje = archivos.copiarArchivoServer( prmRutaArchivo );
    	return mensaje;
    }
}
