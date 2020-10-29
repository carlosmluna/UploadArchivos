<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Menu Principal | Carga de Archivos |</title>
</head>

<script language="javascript">
	function refrescarPost(valor) {
		if (valor == 'UploadTP') {
			document.getElementById("hdnLocalidad").value = 'T';
		} else {
			if (valor == 'UploadCS') {
				document.getElementById("hdnLocalidad").value = 'S';
			} else {
				document.getElementById("hdnLocalidad").value = '';
			}
		}
		
		document.getElementById("hdnAccion").value = valor;
		document.frmMenu.submit();
	}
</script>

<body background="img/FondoPagina.jpg">
<%		
	/* String vlocalidad = "";
	if (request.getParameter("hdnLocalidad") != null) {	
		vlocalidad = request.getParameter("hdnLocalidad"); 
	}*/
	
	String descUsuario = request.getSession().getAttribute("NOMBREUSER").toString();
	
	String pathFinal  = "";
	String basePath   = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	String pathUpload = basePath + "/UploadArchivo.jsp"; // + "?urlLocalidad=" + vlocalidad;
	String pathConfig = basePath + "/Configuracion.jsp";
	String pathLogin  = basePath + "/Login.jsp?logout=1";
%> 	
	<form name="frmMenu" action="dopostMenu" method="post">		
	
		<!-- Tabla para poner el frameset de la pagina -->
		<table width="900px" align="center" border="0" cellpadding="0" cellspacing="0">	
		<tr>
			<!-- <td align="right" background="img/TepeapulcoPleca.jpg" style="width: 800px; height: 104px"><br/></td> -->
			<td colspan="2" align="center"><img alt="Municipio Tepeapulco" src="img/TepeapulcoPleca.jpg" width="900px" height="100px"> </td>		 	
		<tr>
		<tr>
			<td width="175px" valign="top">			
				<br> <!-- Espacio en blanco -->
				
				<table align="center" cellpadding="0" cellspacing="0" border="0" width="160px">
				<tr>
					<td height="25px" style="padding-left:11px; font-family: arial; font-size: 12px; color: #FFFFFF; background-color: #808080;"><b>Subir archivo Pagos</b></td>
				</tr>
				<tr>
					<td height="25px" style="font-family: arial; font-size: 12px; color: #000000; background-color: #FFF8DC;">
						<b><a href="#" onclick="refrescarPost('UploadTP');">
							<img alt="Archivo Tepeapulco" src="img/BtnArchTepeapulco.jpg" height="24px" width="175px">
						</a></b>
					</td>
				</tr>
				<tr>
					<td height="25px" style="font-family: arial; font-size: 12px; color: #000000; background-color: #FFF8DC;">
						<b><a href="#" onclick="refrescarPost('UploadCS');">
							<img alt="Archivo Cd. Sahagun" src="img/BtnArchCdSahagun.jpg" height="24px" width="175px">
						</a></b>
					</td>
				</tr>
				<tr><td><br ></td></tr>
				<tr>
					<td height="25px" style="padding-left:11px; font-family: arial; font-size: 12px; color: #FFFFFF; background-color: #808080;"><b>Acciones</b></td>
				</tr>
				<tr>
					<td height="25px" style="font-family: arial; font-size: 12px; color: #FF0000; background-color: #FFF8DC;">
						<b><a href="#" onclick="refrescarPost('Salir');"><img alt="Municipio Tepeapulco" src="img/BtnSalir.jpg" height="24px" width="175px"></a></b>
					</td>
				</tr>
				</table>
			
			<br> <!-- Espacio en blanco -->	
			</td>
			<td valign="top">
			
				<br> <!-- Espacio en blanco -->
				<table width="719" align="center" cellpadding="0" cellspacing="0" border="0">
				<tr>
					<td align="right" height="25px" style="padding-right: 11px; font-family: arial; font-size: 10px; color: #FFFFFF; background-color: #808080;">
						<b>Bienvenido: <%=descUsuario%></b>
					</td>
				</tr>
				</table>
				
				<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br> <!-- Espacio en blanco -->
			</td>
		</tr>
		<tr>
			<td colspan="2" bgcolor="#DCDCDC" align="center" valign="middle" height="60px">
				<p><font color="#000000" size="1" face="arial"><b>Gobierno del Municipio de TEPEAPULCO | Aplicaicón de Integraciones S-IT Gob |</b></font></p>
			</td>		 	
		<tr>
		</table> 	    
					
		<input type="hidden" id="hdnLocalidad" name="hdnLocalidad" value="">
		<input type="hidden" id="hdnAccion"    name="hdnAccion"    value="">
	</form>
</body>
</html>