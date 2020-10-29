package mx.org.ccti.uploadfile.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FechaTest {

	public static void main(String[] args) {
		Date fecha = convierteFecha("31/03/2015");
		System.out.println(fecha.toString());
	}
	
	
	public static Date convierteFecha( String fecha ) {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date fechaDate = null;
		
		try {
			fechaDate = formato.parse(fecha); } 
		catch (ParseException e) {
			e.printStackTrace(); }

		return fechaDate;
	}

}
