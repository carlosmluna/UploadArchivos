package mx.org.ccti.uploadfile.modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import mx.org.ccti.uploadfile.bo.Mensaje;
import mx.org.ccti.uploadfile.util.Utilidades;

public class Archivos {

	public Mensaje copiarArchivoServer ( String PathArchivo ) { 
				 
	 	Mensaje mensaje         = new Mensaje();
	 	Utilidades utilidades   = new Utilidades();	 	
	 	String nombrArchivo = ""; 
	 	
	 	String nombrArch    = utilidades.depuraNombreArchivo(PathArchivo);
	 	nombrArchivo = utilidades.formatNombrearchFehaHora( nombrArch );
	 	
	 	File origen = new File(PathArchivo);
	 	File destino = new File("c:\\opt\\Archivos\\" + nombrArchivo);
	 		 			
	 	mensaje.setCodigo("0000");
	 	mensaje.setDescripcion("Operacion Exitosa");
	 	
	 	InputStream in;
		try {
			in = new FileInputStream(origen);
	        OutputStream out = new FileOutputStream(destino);
	        byte[] buf = new byte[1024];
	        int len;

	        while ((len = in.read(buf)) > 0) {
	                out.write(buf, 0, len);
	        }

	        in.close();
	        out.close();
		} catch (FileNotFoundException e) {
			mensaje.setCodigo("1111");
			e.printStackTrace();
		} catch (IOException e) {
			mensaje.setCodigo("1111");
			e.printStackTrace();
		}
        	 			
		return mensaje;
	} // end Copiar
	
}
