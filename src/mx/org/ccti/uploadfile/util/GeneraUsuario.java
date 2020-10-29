package mx.org.ccti.uploadfile.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class GeneraUsuario {

	public static void main(String[] args) {
		System.out.println("El Password es: " + generaPasswordUsuario("asuarez") );
	}
	
	private static String generaPasswordUsuario( String pPassword ) {
		String pswd = "";
		try {
			MessageDigest msgdigst = MessageDigest.getInstance("MD5");
			byte[] passb = msgdigst.digest( pPassword.getBytes() );
	        pswd = Base64.byteArrayToBase64(passb);	       	        
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}    
		return pswd;
	}

}
