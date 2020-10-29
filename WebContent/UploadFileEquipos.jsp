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
	
	function refrescarPost() {
		alert('Haciendo Submitt');
		document.frmUpload.submit();
	}
	
	function msg() {
		alert("Hello world!");
	}
</script>
<body background="img/FondoPagina.jpg">

	<!-- <form name="frmLogin" action="dopostlogin" method="post"> -->
	<form name="frmUpload" action="postfileUpload" method="post" enctype="multipart/form-data">
		
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
					<td align="left" height="35px" style="padding-left:10px; font-family: arial; font-size: 12px; color: #191970;" colspan="2">
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
						<input type="button" value="Cargar Archivo Equipos" onclick="refrescarPost()">
						<!-- <input type="button" value="Regresar" onclick="redireccionar();"> -->
						<!-- <input type="button" value="Ver archivo" onclick="showFileName();"> -->
					</td>
				</tr>				
				</table>
				
				<br> <!-- Espacio en blanco -->																		
			</td>				
		</tr>	
		</table> 	    	
    	
    	<input type="hidden" id="hdnLocalidad" name="hdnLocalidad" value="0">    	    						
	</form>
</body>
</html>