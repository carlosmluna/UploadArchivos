package mx.org.ccti.uploadfile.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import mx.org.ccti.uploadfile.bo.CampoIntegPagoBo;

public class Utilidades {
	
	// Metodo que devuelve la fecha y hora actual en un string
	public String formatNombrearchFehaHora(String prmNombre) {
		String nombreArch = "";
		String extension  = "";
		String nombFormat = "";
		
		nombreArch = prmNombre.substring(1, prmNombre.lastIndexOf("."));
		extension  = prmNombre.substring(prmNombre.lastIndexOf(".") + 1, prmNombre.length());
		
		nombFormat = nombreArch.trim() + "_" + getFechaActual() + "_" + getHoraActual() + "." + extension;
		return nombFormat;
	}
	
	// Metodo que devuelve la fecha actual en un string
	private static String getFechaActual() {
        Date ahora = new Date();
        SimpleDateFormat formateador = new SimpleDateFormat("ddMMyyyy");
        return formateador.format(ahora);
    }
	
	// Metodo que devuelve la hora actual en un string
	private static String getHoraActual() {
        Date ahora = new Date();
        SimpleDateFormat formateador = new SimpleDateFormat("hhmmss");
        return formateador.format(ahora);
    }
		
	// Metodo que le quita el path al archivo seleccionado y devuelve solo el nombre del archivo
	public String depuraNombreArchivo(String prmArchivo) {
		String nombreArchivo = prmArchivo.substring(prmArchivo.lastIndexOf("\\") + 1);
		return nombreArchivo;
	}
	
	// Metodo que regresa los valores de la linea leida del archivo separados por comas para el insert
	public String getCamposDetalleArchivo( String campos, String localidad ) {
		String[] arrCamposArch = null;
		String retCampos = "";
		int    lenCadena = 0;
		int    vind      = 0;
		
		arrCamposArch = campos.split("\\" + Constantes.CHR_SEPARADOR);
        for (vind=0; vind<arrCamposArch.length; vind++) {
        	if (vind != 1) {
        		if (vind == 0) {
        			retCampos = retCampos + "'" + localidad + "',"; } 
        		else {
        			
        			if ( vind >= 6 && vind <= 13 ) {
        				retCampos = retCampos + arrCamposArch[vind].trim() + ",";
        			} else {        			
        				retCampos = retCampos + "'" + arrCamposArch[vind].trim() + "'" + ",";
        			}	
        		}	
        	} else {
        		retCampos = retCampos + "now()," + "'" + arrCamposArch[vind].trim() + "'" + ",";
        	}	
        }
		return retCampos;
	}
	
	
	// Metodo que regresa los valores de la linea leida del archivo separados por comas para el insert
	public String getCamposDetalleArchivoIntegracion( String campos, String localidad, List<CampoIntegPagoBo> lstCamposIntegPago ) {
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd-MM-yyyy");
		String[] arrCamposArch = null;
		String 	 retCampos = "";
		String   tipoCampo = "";
		Date 	 fecha     = null;
		int    	 vind      = 0;
		
		arrCamposArch = campos.split("\\" + Constantes.CHR_SEPARADOR);
	    for (vind=0; vind<arrCamposArch.length; vind++) {	    	
	    	if (vind == 0) {	// Cambio la primera columna del archivo por la localidad
	    		retCampos = retCampos + "'" + localidad + "',"; }
	    	else {
	    		tipoCampo = lstCamposIntegPago.get(vind).getTipoCampo().trim();		// Valido el tipo de dato para poner o no comillas
	    		if ( tipoCampo.equals("chr") ) {
	    			retCampos = retCampos + "'" + arrCamposArch[vind].trim() + "'" + ",";
	    		} else if ( tipoCampo.equals("nbr") ) {
	    			retCampos = retCampos + arrCamposArch[vind].trim() + ",";
	    		} else if ( tipoCampo.equals("dte") ) {
	    			fecha = convierteFecha(arrCamposArch[vind].trim());
					retCampos = retCampos + "'" + fecha.toString() + "'" + ",";    			
	    		}	    		
	    	}	    		       	
	    } //for
	    
		return retCampos;
	}
	
	
	public Date convierteFecha( String fecha ) {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date fechaDate = null;
		
		try {
			fechaDate = formato.parse(fecha); } 
		catch (ParseException e) {
			e.printStackTrace(); }

		return fechaDate;
	}
		
}
