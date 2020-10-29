<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Carga de Archivos | Seleccionar |</title>	
</head>

<script type="text/javascript">
	function refrescarPost(valor) {
		document.getElementById("hdnAccion").value = valor;
		if ( valor == 'Salir') {
			document.frmAccion.submit();
		} else {
			document.frmUpload.submit();
		}	
	}
	
	function redireccionar() {
		window.location = "http://localhost:8080/UploadArchivos/Menu.jsp";
	}
	
	function showFileName() {
	   var fil = document.getElementById("txtarchivo");
	   alert(fil.value);
	}
</script>

<body background="img/FondoPagina.jpg">
<%	
	String basePath    = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	String pathConfirm = basePath + "/ConfirmUploadArchivo.jsp";
	String pathLogin   = basePath + "/Login.jsp";

	String descUsuario = request.getSession().getAttribute("NOMBREUSER").toString();
	
	String vlocalidad = "";
	String vDescLocalidad = "";
	if (request.getParameter("hdnLocalidad") != null) {	
		vlocalidad = request.getParameter("hdnLocalidad"); 
		
		if (vlocalidad.equals("T")) {
			vDescLocalidad = "TEPEAPULCO";
		} else {
			vDescLocalidad = "CIUDAD SAHAGUN";
		}
	}	
%>
	<form name="frmUpload" action="postfileUpload" method="post" enctype="multipart/form-data"> 
	
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
						<b><a href="#" onclick="refrescarPost('UploadTP');"><img alt="Archivo Tepeapulco" src="img/BtnArchTepeapulco.jpg" height="24px" width="175px"></a></b>
					</td>
				</tr>
				<tr>
					<td height="25px" style="font-family: arial; font-size: 12px; color: #000000; background-color: #FFF8DC;">
						<b><a href="#" onclick="refrescarPost('UploadCS');"><img alt="Archivo Cd. Sahagun" src="img/BtnArchCdSahagun.jpg" height="24px" width="175px"></a></b>
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
				
				<table width="719" align="center" cellpadding="0" cellspacing="0">
				<tr>
					<td align="right" height="25px" style="padding-right: 11px; font-family: arial; font-size: 10px; color: #FFFFFF; background-color: #808080;">
						<b>Bienvenido: <%=descUsuario%></b>
					</td>
				</tr>
				</table>
				
				<br> <!-- Espacio en blanco -->
				
				<!-- Tabla para colocar la carga del archivo -->
				<table width="70%" align="center" border="1" cellpadding="0" cellspacing="0" bordercolor="#008000" bgcolor="#FFFFFF">
				<tr>
					<td align="center" height="30px" style="font-family: arial; font-size: 12px; color: #008000;">
						<p><b>Aqui puede subir un archivo de texto o csv a la aplicación [ <%=vDescLocalidad%> ]</b></p>
					</td>				
				</tr>	
				<tr>
					<td align="center">					
						<br> <!-- Espacio en blanco -->
																		
						<table width="50%" align="center" border="0" cellpadding="0" cellspacing="0">						
						<tr>
							<td align="left" height="35px" style="padding-left:10px; font-family: arial; font-size: 12px; color: #008000;" colspan="2"><b>Archivo:</b></td>				
						</tr>						
						<tr>
							<td align="center" height="35px" style="padding-left:10px;" colspan="2">
							<input type="file" id="txtarchivo" name="txtarchivo" style="width: 430px;"/>
						</td>
						</tr>
						<tr> <td align="left" height="15px"><br></td> </tr>				
						<tr>
							<td align="center" height="35px">
								<input type="button" value="Regresar" onclick="redireccionar();">
								<input type="button" value="Subir Archivo" onclick="refrescarPost('Upload');">
								<!-- <input type="button" value="Ver archivo" onclick="showFileName();"> -->
							</td>
						</tr>				
						</table>
						
						<br> <!-- Espacio en blanco -->																		
					</td>				
				</tr>	
				</table>			
				
				<br><br><br><br><br><br><br><br><br><br> <!-- Espacio en blanco -->
			</td>
		</tr>
		<tr>
			<td colspan="2" bgcolor="#DCDCDC" align="center" valign="middle" height="60px">
				<p><font color="#000000" size="1" face="arial"><b>Gobierno del Municipio de TEPEAPULCO | Aplicaicón de Integraciones S-IT Gob |</b></font></p>
			</td>		 	
		<tr>
		</table>
		
	<input type="hidden" id="hdnLocalidad" name="hdnLocalidad" value="<%=vlocalidad%>">
	</form>		
	
	<form name="frmAccion" method="post" action="postSalirload">	
		<input type="hidden" id="hdnAccion" name="hdnAccion" value="">
	</form>
				
	
</body>
</html>