<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Ingreso a la aplicacion | Subir Archivos |</title>
</head>

<script type="text/javascript">
function valorCombo () {
	var valor = document.getElementById("cboLocalidad").value;
	document.getElementById("hdnLocalidad").value = valor;
}

function validaCampos() {
	var blnMensaje = 0;
	var campo = '';
	
	if (blnMensaje == 0 && document.getElementById("txtUsuario").value.trim().length == 0) {
		blnMensaje = 1;
		campo = 'USUARIO';
    }	
	if (blnMensaje == 0 && document.getElementById("txtContrasena").value.trim().length == 0) {
		blnMensaje = 1;
		campo = 'CONTRASENA';
    }	
	/*if (blnMensaje == 0 && document.getElementById("cboLocalidad").value == 0) {
		blnMensaje = 1;
		campo = 'LOCALIDAD';
    }*/	
	if (blnMensaje == 1) {
		alert('Todos los campos del formulario son requeridos, por favor verifique la informacion que ha capturado en el campo ' + '[' + campo + ']');
		return false
    } else {
    	return true
    }
}
</script>
<body background="img/FondoPagina.jpg">
<%
	String basePath = request.getScheme() + "://" +request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/Menu.jsp";
%> 
	<form name="frmLogin" action="dopostlogin" method="post">
		
		<table width="900px" align="center" border="0" cellpadding="0" cellspacing="0">	
		<tr>
			<!-- <td align="right" background="img/TepeapulcoPleca.jpg" style="width: 800px; height: 104px"><br/></td> -->
			<td colspan="1" align="center"><img alt="Municipio Tepeapulco" src="img/TepeapulcoPleca.jpg" width="900px" height="100px"> </td>		 	
		<tr>
		<tr>
			<!-- <td width="175px" valign="top">			
				<br> 
				
				<table align="center" cellpadding="0" cellspacing="0" border="0" width="160px">
				<tr>
					<td height="25px" style="padding-left:11px; font-family: arial; font-size: 12px; color: #FFFFFF; background-color: #808080;"><b>Página Actual</b></td>
				</tr>
				<tr>
					<td height="25px" style="padding-left:11px; font-family: arial; font-size: 12px; color: #000000; background-color: #FFF8DC;"><b>Login</b></td>
				</tr>
				</table>
			
			<br> 	
			</td>  -->
			<td valign="top">
				<br><br><br><br><br><br> <!-- Espacio en blanco -->
				
					<table width="350px" border="1" cellpadding="0" cellspacing="0" align="center" bgcolor="#FFFFFF">
					<tr>
						<td>
										
							<table width="300px" border="0" cellpadding="0" cellspacing="0" align="center">
							<tr><td colspan="2" height="15px"><br/></td></tr>
							<tr>
								<td height="35px" style="padding-left:10px;">Usuario:</td>
								<td height="35px" style="padding-left:10px;"><input type="text" value="" id="txtUsuario" name="txtUsuario"></td>
							</tr>
							<tr>
								<td height="35px" style="padding-left:10px;">Contraseña:</td>
								<td height="35px" style="padding-left:10px;"><input type="password" value="" id="txtContrasena" name="txtContrasena"></td>
							</tr>
							<!-- <tr>
								<td height="35px" style="padding-left:10px;">Localidad:</td>
								<td height="35px" style="padding-left:10px;">
									<select id="cboLocalidad" name="cboLocalidad" style="width: 150px;" onchange="valorCombo();">
										<option value="0">Seleccione un valor</option>
									   	<option value="T">Tepeapulco</option> 
									   	<option value="S">Ciudad Sahagun</option>
									</select> 
								</td>
							</tr> -->				
							<tr>
								<td height="35px"><br/></td>
								<td align="center"><input type="submit" value="Ingreesar" onclick="return validaCampos();"></td>
							</tr>
							<tr><td colspan="2" height="15px"><br/></td></tr>
							</table>
														
						</td>
					</tr>
					</table>
					
					<!-- Tabla para colocar la carga del archivo -->
					<table width="70%" align="center" border="1" cellpadding="0" cellspacing="0" bordercolor="#008000" bgcolor="#FFFFFF">
					<tr>
						<td align="center" height="30px" style="font-family: arial; font-size: 12px; color: #191970;">
							<p><b>Aqui puede subir un archivo para actualizar la informacion del Equipo [Nuevos]</b></p>
						</td>				
					</tr>	
					<tr>
						<td align="center">					
							<br> <!-- Espacio en blanco -->
																			
							<table width="50%" align="center" border="0" cellpadding="0" cellspacing="0">						
							<tr>
								<td align="left" height="35px" style="padding-left:10px; font-family: arial; font-size: 12px; color: #008000;" colspan="2">
									<b>Archivo:</b>
								</td>				
							</tr>						
							<tr>
								<td align="center" height="35px" style="padding-left:10px;" colspan="2">
								<input type="file" id="txtarchivo" name="txtarchivo" style="width: 430px;"/>
							</td>
							</tr>
							<tr> <td align="left" height="15px"><br></td> </tr>				
							<tr>
								<td align="center" height="35px">
									<input type="button" value="Subir Archivo" onclick="refrescarPost('Upload');">
									<!-- <input type="button" value="Regresar" onclick="redireccionar();"> -->
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
    	
    	<input type="hidden" id="hdnLocalidad" name="hdnLocalidad" value="0">    	    						
	</form>
</body>
</html>