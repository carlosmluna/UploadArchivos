package mx.org.ccti.uploadfile.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class UploadSalirServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Obtengo la direccion del explorador
		String basePath   = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
		String pathUpload = basePath + "/UploadArchivo.jsp";
		String pathLogin  = basePath + "/Login.jsp";
		String localidad  = (String) request.getSession().getAttribute("LOCALIDAD");
		
		PrintWriter pagina;
		response.setContentType("text/html");		 
		pagina = response.getWriter();
		
		String vAccion = request.getParameter("hdnAccion");
		if ( vAccion.equals("UploadTP") || vAccion.equals("UploadCS") )      { 
			// response.sendRedirect(pathUpload);
			pagina.println("<html><head><title>Login TMP</title>");
			pagina.println("<script language=\"javascript\">");
			pagina.println("function refrescarPost() {");
				pagina.println("document.frmMenuTmp.submit();");
			pagina.println("}");
			pagina.println("</script>");
			pagina.println("</head>");
			// pagina.println("<body oncontextmenu=\"return false\" onkeydown=\"return false\" onload=\"refrescarPost()\">");
			pagina.println("<body>");
			pagina.println("<form name=\"frmMenuTmp\" action=\"" + pathUpload + "\" method=\"post\">");
			pagina.println("<input type=\"hidden\" id=\"hdnLocalidad\" name=\"hdnLocalidad\" value=\"" + localidad + "\">");	
			pagina.println("</form>");
			pagina.println("</body></html>");
		} else if ( vAccion.equals("Salir")) {
			request.getSession().invalidate();
			response.sendRedirect(pathLogin);
		}
		
	}

}
