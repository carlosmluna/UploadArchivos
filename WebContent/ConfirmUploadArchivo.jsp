<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Confirmacion | Carga de Archivos |</title>	
</head>

<script type="text/javascript">
	function redireccionar() {
		window.location = "http://localhost:8080/UploadArchivos/Menu.jsp";
	}
	
	function refrescarPost(valor) {
		document.getElementById("hdnAccion").value = valor;	
		if ( valor == "Salir") {
			document.frmAccionCnfrm.submit();
		} else {
			document.getElementById('srcProcesando').style.display = 'block';
			document.frmConfUpload.submit();
		}				
	}
</script>

<body background="img/FondoPagina.jpg">
<%	
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/LoginTmp.jsp";

	// String descUsuario = request.getSession().getAttribute("NOMBREUSER").toString();

	String vlocalidad = "";
	/* if (request.getParameter("hdnLocalidad") != null) {	
		vlocalidad = request.getParameter("hdnLocalidad"); } */
	
	String vpatharchivo = "";
	if (request.getParameter("hdnPatharchivo") != null) {	
		vpatharchivo = request.getParameter("hdnPatharchivo"); } 
	%>
	
	<form name="frmConfUpload" action="postConfirmUpload" method="post" enctype="multipart/form-data">
	
		<!-- Tabla para poner el frameset de la pagina -->
		<!-- <table width="900px" align="center" border="0" cellpadding="0" cellspacing="0">	
		<tr>
			<td colspan="2" align="center">
				<img alt="Municipio Tepeapulco" src="img/TepeapulcoPleca.jpg" width="900px" height="100px"> 
			</td>		 	
		<tr>
		<tr>
			<td width="175px" valign="top">			
				<br>
				
				<table align="center" cellpadding="0" cellspacing="0" border="0" width="160px">
				<tr>
					<td height="25px" style="padding-left:11px; font-family: arial; font-size: 12px; color: #FFFFFF; background-color: #808080;">
						<b>Subir archivo Pagos</b>
					</td>
				</tr>
				<tr>
					<td height="25px" style="font-family: arial; font-size: 12px; color: #000000; background-color: #FFF8DC;">
						<b>
							<a href="#" onclick="refrescarPost('Upload');">
								<img alt="Archivo Tepeapulco" src="img/BtnArchTepeapulco.jpg" height="24px" width="175px">
							</a>
						</b>
					</td>
				</tr>
				<tr>
					<td height="25px" style="font-family: arial; font-size: 12px; color: #000000; background-color: #FFF8DC;">
						<b>
							<a href="#" onclick="refrescarPost('Upload');">
								<img alt="Archivo Cd. Sahagun" src="img/BtnArchCdSahagun.jpg" height="24px" width="175px">
							</a>
						</b>
					</td>
				</tr>
				<tr><td><br ></td></tr>
				<tr>
					<td height="25px" style="padding-left:11px; font-family: arial; font-size: 12px; color: #FFFFFF; background-color: #808080;">
						<b>Acciones</b>
					</td>
				</tr>
				<tr>
					<td height="25px" style="padding-left:11px; font-family: arial; font-size: 12px; color: #FF0000; background-color: #FFF8DC;">
						<b>
							<a href="#" onclick="refrescarPost('Salir');">
								<img alt="Municipio Tepeapulco" src="img/BtnSalir.jpg" height="24px" width="175px">
							</a>
						</b>
					</td>
				</tr>
				</table>
			
			<br> 	
			</td>
			<td valign="top">
				<br> -->
				
				<!-- Tabla para colocar la carga del archivo -->
				<table width="60%" align="center" border="1" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
				<tr>
					<td align="center" height="30px" style="font-family: arial; font-size: 12px; color: #008000;">
						<p><b>Si la informacion seleccionada es correcta, por favor confirme esta operación presionando 'Subir Archivo', o en su caso 'Regresar'.</b></p>
					</td>				
				</tr>	
				<tr>
					<td align="center">
					
						<br> 
						
						<table width="80%" align="center" border="0" cellpadding="0" cellspacing="0">						
						<tr>
							<td align="center" height="30px" style="font-family: arial; font-size: 12px; color: #008000;"><b>El archivo que ha seleccionado es:</b></td>
						</tr>
						<tr> <td align="left" height="15px"><br></td> </tr>
						<tr>
							<td align="center" height="30px" style="font-family: arial; font-size: 12px; color: #008000;">
								<b><%=vpatharchivo%></b>
							</td>				
						</tr>						
						<tr> <td align="left" height="15px"><br><br></td> </tr>				
						<tr>
							<td align="center" height="30px">
								<input type="button" value="Regresar" onclick="redireccionar();">
								<input type="button" value="Subir Archivo" onclick="refrescarPost('Confirm');"/>
							</td>
						</tr>
						<tr> <td align="left" height="15px"><br></td> </tr>
						<tr> 
							<td align="center">
								<img alt="Espere un minuto" src="img/procesando.gif" width="200px" height="25px" style="display: none;" name="srcProcesando" id="srcProcesando">
							</td>
						</tr>
						<tr> <td align="left" height="15px"><br></td> </tr>						
						</table>
						
						<br> 
																								
					</td>
				</tr>	
				</table>
				
				<!-- <br><br><br><br><br><br><br><br><br> 
			</td>
		</tr> -->
		<!-- <tr>
			<td colspan="2" bgcolor="#DCDCDC" align="center" valign="middle" height="60px">
				<p><font color="#000000" size="1" face="arial"><b>Gobierno del Municipio de TEPEAPULCO | Aplicaicón de Integraciones S-IT Gob |</b></font></p>
			</td>		 	
		<tr> -->
		<!-- </table> -->
		
		<br> 		
		<input type="hidden" id="hdnArchivo" name="hdnArchivo" value="<%=vpatharchivo%>">
	</form>
		
	<form name="frmAccionCnfrm" method="post" action="postSalirConfUpd">		
		<input type="hidden" id="hdnAccion" name="hdnAccion" value="">		
	</form>	
</body>
</html>