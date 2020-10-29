package mx.org.ccti.uploadfile.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.org.ccti.uploadfile.bo.UsuarioBO;
import mx.org.ccti.uploadfile.modelo.BaseDatos;
import mx.org.ccti.uploadfile.util.Base64;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BaseDatos baseDatos = new BaseDatos();
		
		// Obtengo la direccion del explorador
		String basePath  = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
		String pathMenu  = basePath + "/Menu.jsp";
		String pathLogin = basePath + "/Login.jsp";
		
		// Obtengo valores de la pagina (request)
		String localidad = request.getParameter("cboLocalidad");
		
		// Genero la pagina para direccionar en el post a la pagina de Menu 
		PrintWriter pagina;
		response.setContentType("text/html");		 
		pagina = response.getWriter();
				
		// Valido que el usaurio exista en la base de datos
		try {
			MessageDigest msgdigst = MessageDigest.getInstance("MD5");
			byte[] passb = msgdigst.digest( request.getParameter("txtContrasena").getBytes() );
	        String pswd = Base64.byteArrayToBase64(passb);
	        pswd = Base64.byteArrayToBase64(passb);
	        
	        // UsuarioBO usaurioBO = baseDatos.getInformacionUsuario( request.getParameter("txtUsuario"), pswd);
	        UsuarioBO usaurioBO = new UsuarioBO();
	        usaurioBO.setUsuario("CLUNA");
	        usaurioBO.setDescripcion("");
	        usaurioBO.setEncontrado(1);
			if ( usaurioBO.getEncontrado() == 1 ) {
				
				// Pongo los datos del usuario en variables de sesion
				request.getSession().setAttribute("LOGINUSER",  usaurioBO.getUsuario());
				request.getSession().setAttribute("NOMBREUSER", usaurioBO.getDescripcion());
				// request.getSession().setAttribute("LOCALIDAD",  localidad);
				
				pagina.println("<html><head><title>Login TMP</title>");
				pagina.println("<script language=\"javascript\">");
					pagina.println("function refrescarPost() {");
						pagina.println("document.frmLoginoTmp.submit();");
					pagina.println("}");
				pagina.println("</script>");
				pagina.println("</head>");
				pagina.println("<body oncontextmenu=\"return false\" onkeydown=\"return false\" onload=\"refrescarPost()\">");
				// pagina.println("<body>");
				pagina.println("<form name=\"frmLoginoTmp\" action=\"" + pathMenu + "\" method=\"post\">");
				pagina.println("<input type=\"hidden\" id=\"hdnLocalidad\" name=\"hdnLocalidad\" value=\"" + request.getSession().getAttribute("LOCALIDAD") + "\">");
				// pagina.println("<input type=\"submit\" value=\"Aceptar\"/>");	
				pagina.println("</form>");
				pagina.println("</body></html>");
				
			} else {
				pagina.println("<html><head><title>Mensajes de Excpecion</title>");
				pagina.println("<script language=\"javascript\">");
					pagina.println("function refrescarPost() {");
						pagina.println("document.frmLoginoTmp.submit();");
					pagina.println("}");
				pagina.println("</script>");
				pagina.println("</head>");
				pagina.println("<body>");
				pagina.println("<form name=\"frmLoginoTmp\" action=\"" + pathLogin + "\" method=\"post\">");
				pagina.println("<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"400px\" align=\"center\">");
				pagina.println("<tr>");
					pagina.println("<td colspan=\"2\" style=\"padding-left:11px; font-family: arial; font-size: 12px; color: #FFFFFF; background-color: #008000; height: 30px\"><b>Mensajes</b></td>");
				pagina.println("</tr>");
				pagina.println("<tr>");
					pagina.println("<td align=\"center\" valign=\"middle\"><img alt=\"\" src=\"img/icono_ayuda.jpg\"></td>");
					pagina.println("<td style=\"padding-left:11px; font-family: arial; font-size: 12px; color: #008000; background-color: #FFFFFF;\">");
						pagina.println("<p><b>El usuario: '" + request.getParameter("txtUsuario").toUpperCase() + "' no se ha encontrado en la base de datos de la aplicación</b></p>");
					pagina.println("</td>");
				pagina.println("</tr>");				
				pagina.println("<tr><td colspan=\"2\"><br></td></tr>");
				pagina.println("<tr>");
				pagina.println("<td colspan=\"2\" align=\"center\" height=\"30px\"><input type=\"button\" value=\"Regresar\" onclick=\"refrescarPost();\"></td>");
				pagina.println("</tr>");		
				pagina.println("</table>");
				pagina.println("</form>");
				pagina.println("</body></html>");
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
               
				
	}

}
