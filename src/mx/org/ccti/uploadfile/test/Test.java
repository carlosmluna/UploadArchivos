package mx.org.ccti.uploadfile.test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {

	public static void main(String[] args) {
		String vCadena   = "D|000001|GARCIA BADILLO ELIAS|LETRA A-01     ||0006|942|2128|512|0|288|3870|0.00|3870|00000000000000108366207|8800000001310320150000038704|00000000000000108366207||31/03/2015";
		String retCampos = "";
		String archivo   = "";
		int    lenCadena = 0;
		
		String[] ArrCampos = vCadena.split("\\|");
		for (int ind = 0; ind<ArrCampos.length; ind++) {
            retCampos = retCampos + "'" + ArrCampos[ind].trim() + "'" + ",";
        }
		lenCadena = retCampos.length();
		System.out.println("retCampos: " + retCampos.substring(0, lenCadena-1));
		
		String fecha = getFechaActual();
		System.out.println("fecha: " + fecha);	
		
		String horario = getHoraActual();
		System.out.println("horario: " + horario);	
		
		archivo = formatNombrearchFehaHora("01-03-15(Sahagun) Des.txt");
		System.out.println("archivo: " + archivo);
	}

	private static String getFechaActual() {
        Date ahora = new Date();
        SimpleDateFormat formateador = new SimpleDateFormat("ddMMyyyy");
        return formateador.format(ahora);
    }
	
	private static String getHoraActual() {
        Date ahora = new Date();
        SimpleDateFormat formateador = new SimpleDateFormat("hhmmss");
        return formateador.format(ahora);
    }
	
	private static String formatNombrearchFehaHora(String prmNombre) {
		String nombreArch = "";
		String extension  = "";
		String nombFormat = "";
		
		nombreArch = prmNombre.substring(1, prmNombre.lastIndexOf("."));
		extension  = prmNombre.substring(prmNombre.lastIndexOf(".") + 1, prmNombre.length());
		
		nombFormat = nombreArch.trim() + getFechaActual() + "_" + getHoraActual() + extension;
		return nombFormat;
	}
}
