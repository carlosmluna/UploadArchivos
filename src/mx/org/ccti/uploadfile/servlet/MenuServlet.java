package mx.org.ccti.uploadfile.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/MenuServlet")
public class MenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		// Obtengo la direccion del explorador
		String basePath   = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
		String pathUpload = basePath + "/UploadArchivo.jsp";
		String pathConfig = basePath + "/Configuracion.jsp";
		String pathLogin  = basePath + "/Login.jsp";
				
		// Tomo valores de la pantalla
		String metodo    = request.getParameter("hdnAccion");
		String localidad = request.getParameter("hdnLocalidad");  //request.getSession().getAttribute("LOCALIDAD").toString();
		
		request.getSession().setAttribute("LOCALIDAD",  localidad);
		
		// Genero la pagina para direccionar en el post a la pagina de Menu 
		PrintWriter pagina;
		response.setContentType("text/html");		 
		pagina = response.getWriter();
				
		if ( metodo.equals("UploadTP") || metodo.equals("UploadCS") )      { 
			// response.sendRedirect(pathUpload);
			pagina.println("<html><head><title>Login TMP</title>");
			pagina.println("<script language=\"javascript\">");
			pagina.println("function refrescarPost() {");
				pagina.println("document.frmMenuTmp.submit();");
			pagina.println("}");
			pagina.println("</script>");
			pagina.println("</head>");
			pagina.println("<body oncontextmenu=\"return false\" onkeydown=\"return false\" onload=\"refrescarPost()\">");
			// pagina.println("<body>");
			pagina.println("<form name=\"frmMenuTmp\" action=\"" + pathUpload + "\" method=\"post\">");
			pagina.println("<input type=\"hidden\" id=\"hdnLocalidad\" name=\"hdnLocalidad\" value=\"" + localidad + "\">");	
			pagina.println("</form>");
			pagina.println("</body></html>");
		}
		else if ( metodo.equals("Config") ) { response.sendRedirect(pathConfig);  }		
		else if ( metodo.equals("Salir") )  {
			request.getSession().invalidate();
			response.sendRedirect(pathLogin);   
		}
		
	}

}
