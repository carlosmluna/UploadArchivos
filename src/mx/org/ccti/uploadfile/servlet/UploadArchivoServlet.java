package mx.org.ccti.uploadfile.servlet;

import java.io.*;
import java.util.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 





import mx.org.ccti.uploadfile.controlador.UploadFileControlador;
import mx.org.ccti.uploadfile.util.Constantes;
import mx.org.ccti.uploadfile.util.Utilidades;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UploadArchivoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Utilidades utilidades = new Utilidades();
	Constantes constantes = new Constantes();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Obtengo la direccion del explorador
		String basePath    = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
		String pathConfirm = basePath + "/ConfirmUploadArchivo.jsp";
		String pathConfig  = basePath + "/Configuracion.jsp";
		String pathLogin   = basePath + "/Login.jsp";
		
		// Tomo valores de la pantalla
		String localidad = ""; // request.getSession().getAttribute("LOCALIDAD").toString(); // request.getParameter("hdnLocalidad");				
		UploadFileControlador uploadFileControlador = new UploadFileControlador();		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload   upload  = new ServletFileUpload(factory);	   					
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		response.setContentType("text/html");
		java.io.PrintWriter out = response.getWriter( );
		    
		try { 
			List fileItems = upload.parseRequest(request);	  	
		    Iterator i = fileItems.iterator();	        
		    while ( i.hasNext () ) {
		    	FileItem fi = (FileItem)i.next();
		        if ( !fi.isFormField () ) {
		        	// Get the uploaded file parameters
		        	String rutaArchivo  = fi.getName();
		        	long   sizeInBytes  = fi.getSize(); 
		        	
		        	// Subo la ruta del archivo a una variable de sesion
		        	request.getSession().setAttribute("ARCHIVO",  rutaArchivo);
		        	
		        	// Genero la pagina para direccionar en el post a la pagina de Menu 
		        	PrintWriter pagina;
		        	response.setContentType("text/html");		 
		        	pagina = response.getWriter();
		        	pagina.println("<html><head><title>Login TMP</title>");		
		        	pagina.println("<script language=\"javascript\">");
		        		pagina.println("function refrescarPost() {");
		        			pagina.println("document.frmUploadTmp.submit();");
		        		pagina.println("}");
		        	pagina.println("</script>");  
		        	pagina.println("</head>");
		        	pagina.println("<body oncontextmenu=\"return false\" onkeydown=\"return false\" onload=\"refrescarPost()\">");
		        	// pagina.println("<body>");
		        	pagina.println("<form name=\"frmUploadTmp\" action=\"" + pathConfirm + "\" method=\"post\">");
		        		// pagina.println("<input type=\"hidden\" id=\"hdnLocalidad\"   name=\"hdnLocalidad\"   value=\"" + localidad   + "\">");
		        		pagina.println("<input type=\"hidden\" id=\"hdnPatharchivo\" name=\"hdnPatharchivo\" value=\"" + rutaArchivo + "\">");
		        		// pagina.println("<input type=\"text\" id=\"hdnPatharchivo\" name=\"hdnPatharchivo\" value=\"" + rutaArchivo + "\">");
		        		// pagina.println("<input type=\"text\"   id=\"hdnDescripcion\" name=\"hdnDescripcion\" value=\"" + descripcion + "\">");
		        		pagina.println("<input type=\"submit\" value=\"Aceptar\"/>");	
		        	pagina.println("</form>");
		        	pagina.println("</body></html>");
		        	 
		        }
		    }
		} catch(Exception ex) {
			System.out.println(ex); 
		}   	
		    		
	} //doPost

}
