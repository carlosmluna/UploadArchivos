package mx.org.ccti.uploadfile.util;

public class Constantes {
	
	// Caracteres
	public static final String CHR_VACIO      = "";
	public static final String CHR_SEPARADOR  = "|";
	
	// Codigos de errores
	public static final String COD_EXITO      = "0000";  		// Operacion Exitosa
	public static final String COD_ERROR      = "1111";  		// Operacion No Exitosa
	
	// Descripciones de los Codigos de errores
	public static final String DESC_EXITO     = "Operacion Exitosa";	
	public static final String DESC_ERROR     = "Operacion No Exitosa";
	
	// SQL's
	public static final String TBL_DETARCHV   = "integracion_pagos_des"; //"cadetallearchivo";
	/* public static final String INS_DETARCHV   = "(" + 
			"idArchivo,         localidad, 	    fecha_creacion, cuenta,         nombre_razon_social, " +
            "domicilio,         rfc,       	    concepto,       cuota_anual,    rezagos, "             +
            "recargos,          iva,            derechos_sda,   subtotal,       descuento, "           +
            "importe_neto,      linea_captura1, linea_captura2, linea_captura3, linea_captura4, "      +
            "fecha_vencimineto, creadoPor,      fecha_creacion ) "; */
	
	public static final String INS_DETARCHV   = "(" + 
			"localidad,      cuenta, 		 nombre_razon_social, domicilio,      rfc, 				 concepto, 	cuota_anual,    " +
			"rezagos,        recargos,       iva, 				  derechos_sda,   subtotal,          descuento, importe_neto,   " + 
			"linea_captura1, linea_captura2, linea_captura3, 	  linea_captura4, fecha_vencimineto, fecha_creacion, " +
			"id_integracion_pago ) ";
	
	// Localidades
	public static final String LOC_SAHAGUN      = "CIUDAD SAHAGUN";
	public static final String LOC_TEPEAPLCO    = "TEPEAPULCO";
	public static final String LOC_CVESAHAGUN   = "S";
	public static final String LOC_CVETEPEAPLCO = "T";
	
	// Escritura Paginas
	public static final String HTML_HIDDEN_DPST  = "<input type=\"hidden\" value=\"";
	public static final String HTML_TDRESP_DGET1 = "<td width=\"50%\" height=\"30px\" style=\"padding-left:10px;\"><font size=\"2\" face=\"arial\" color=\"#FFFFFF\">";
	public static final String HTML_TDRESP_DGET2 = "<td width=\"50%\" style=\"padding-left:10px;\"><font size=\"2\" face=\"arial\" color=\"#FFFFFF\">";
	public static final String HTML_TDFIN        = "</font></td>";
	
	// Rutas de archivos y otros
	public static final String RUTA_ARCHSRV      = "/opt/archivos/";
	public static final String URL_CONFCARGGA    = "http://192.168.2.109:8080/UploadArchivo/UploadArchivo/Menu.jsp";
	
}
