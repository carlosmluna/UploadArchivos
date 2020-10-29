package mx.org.ccti.uploadfile.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
 




import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.com.bimbo.equipos.modelo.dto.EquipoDTO;
import mx.org.ccti.uploadfile.bo.Mensaje;
import mx.org.ccti.uploadfile.controlador.UploadFileControlador;
import mx.org.ccti.uploadfile.util.Constantes;
import mx.org.ccti.uploadfile.util.Utilidades;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class ConfirmUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	UploadFileControlador uploadFileControlador = new UploadFileControlador();	
	Utilidades utilidades = new Utilidades();
	Constantes constantes = new Constantes();
	Mensaje    mensaje    = new Mensaje();
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String rutaArchivo = "";
		String nombArchivo = "";
		String localidad   = ""; 
		
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
		String pathMenu = basePath + "/Menu.jsp";
		
		localidad   = ""; //request.getSession().getAttribute("LOCALIDAD").toString(); //localidad request.getParameter("hdnLocalidad");
		rutaArchivo = request.getSession().getAttribute("ARCHIVO").toString(); 	 // request.getParameter("hdnArchivo");
		
		PrintWriter pagina;
		response.setContentType("text/html");		 
		pagina = response.getWriter();
		
		pagina.println("<html><head><title>Mensajes de Excpecion</title>");
		pagina.println("<script language=\"javascript\">");
			pagina.println("function refrescarPost() {");
				pagina.println("document.frmLoginoTmp.submit();");
			pagina.println("}");
		pagina.println("</script>");
		pagina.println("</head>");
		pagina.println("<body>");
		pagina.println("<form name=\"frmLoginoTmp\" action=\"" + pathMenu + "\" method=\"post\">");
		pagina.println("<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"400px\" align=\"center\">");
		pagina.println("<tr>");
			pagina.println("<td colspan=\"2\" style=\"padding-left:11px; font-family: arial; font-size: 12px; color: #FFFFFF; background-color: #008000; height: 30px\"><b>Mensajes</b></td>");
		pagina.println("</tr>");
		
		try {	 						
			// if ( mensaje.getCodigo().equals(constantes.COD_EXITO) ) {
				
				// leo el contenido del archivo
				EquipoDTO equipoDTO = uploadFileControlador.leerCiontenidoArchivo(rutaArchivo, nombArchivo, localidad);
				if ( equipoDTO.getId() != 0 ) {
						
					// Copio el archivo en el servidor
					// mensaje = uploadFileControlador.copiaArchivoServidor( rutaArchivo );
					// if ( mensaje.getCodigo().equals(constantes.COD_EXITO) ) {
						pagina.println("<tr>");
						pagina.println("<td align=\"center\" valign=\"middle\"><img alt=\"\" src=\"img/exito.jpg\" width=\"80px\" height=\"80px\"></td>");
						pagina.println("<td style=\"padding-left:11px; font-family: arial; font-size: 12px; color: #008000; background-color: #FFFFFF;\">");
						pagina.println("<p><b>El archivo se proceso correctamente en la base de datos.</b></p>");
						pagina.println("</td>");
						pagina.println("</tr>");
						System.out.println("Exitoso");
					/* } else {
						pagina.println("<tr>");
						pagina.println("<td align=\"center\" valign=\"middle\"><img alt=\"\" src=\"img/icono_ayuda.jpg\"></td>");
						pagina.println("<td style=\"padding-left:11px; font-family: arial; font-size: 12px; color: #008000; background-color: #FFFFFF;\">");
						pagina.println("<p><b>No fue posible procesar el Archivo seleccionado en la Bse de Datos.</b></p>");
						pagina.println("</td>");
						pagina.println("</tr>");
						System.out.println("Hubo un error al copiar el archivo");
					} */
							
				} /* else {
					pagina.println("<tr>");
					pagina.println("<td align=\"center\" valign=\"middle\"><img alt=\"\" src=\"img/icono_ayuda.jpg\"></td>");
					pagina.println("<td style=\"padding-left:11px; font-family: arial; font-size: 12px; color: #008000; background-color: #FFFFFF;\">");
					pagina.println("<p><b>No fue posible generar el encabezado del archivo en la base de datos</b></p>");
					pagina.println("</td>");
					pagina.println("</tr>");
					System.out.println("Hubo un error al insertar nombre del archivo de datos");			
				} */
						
			/*}  else {
				pagina.println("<tr>");
				pagina.println("<td align=\"center\" valign=\"middle\"><img alt=\"\" src=\"img/icono_ayuda.jpg\"></td>");
				pagina.println("<td style=\"padding-left:11px; font-family: arial; font-size: 12px; color: #008000; background-color: #FFFFFF;\">");
				pagina.println("<p><b>No fue posible leer correctamente la informacion contenida en la base de datos</b></p>");
				pagina.println("</td>");
				pagina.println("</tr>");
				System.out.println("Hubo un error al leer el archivo de datos");
			} */
					
				/* } else {
					pagina.println("<tr>");
					pagina.println("<td align=\"center\" valign=\"middle\"><img alt=\"\" src=\"img/icono_ayuda.jpg\"></td>");
					pagina.println("<td style=\"padding-left:11px; font-family: arial; font-size: 12px; color: #008000; background-color: #FFFFFF;\">");
						pagina.println("<p><b>No fue posible inicializar el repositorio de datos para cargar la informacion seleccionada</b></p>");
					pagina.println("</td>");
					pagina.println("</tr>");
				}	 			
				
			} else {
				pagina.println("<tr>");
				pagina.println("<td align=\"center\" valign=\"middle\"><img alt=\"\" src=\"img/icono_ayuda.jpg\"></td>");
				pagina.println("<td style=\"padding-left:11px; font-family: arial; font-size: 12px; color: #008000; background-color: #FFFFFF;\">");
					pagina.println("<p><b>No fue posible calcular información para generar la informacion en la base de datos</b></p>");
				pagina.println("</td>");
				pagina.println("</tr>");
				System.out.println("Hubo un error al generar la secuencia");
			} */
			
			pagina.println("</tr>");				
			pagina.println("<tr><td colspan=\"2\"><br></td></tr>");
			pagina.println("<tr>");
			pagina.println("<td colspan=\"2\" align=\"center\" height=\"30px\"><input type=\"button\" value=\"Regresar\" onclick=\"refrescarPost();\"></td>");
			pagina.println("</tr>");		
			pagina.println("</table>");
			pagina.println("</form>");
			pagina.println("</body></html>");
			System.out.println("Pagina: " + pagina.toString());
                        
		} catch (Exception e) {
			e.printStackTrace();
		} /* catch (FileUploadException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		} */
	}

}
