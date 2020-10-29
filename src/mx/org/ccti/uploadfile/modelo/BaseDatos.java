package mx.org.ccti.uploadfile.modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.csvreader.CsvReader;

import mx.com.bimbo.equipos.modelo.dto.EquipoDTO;
import mx.com.bimbo.equipos.service.EquipoService;
import mx.org.ccti.uploadfile.bo.CampoIntegPagoBo;
import mx.org.ccti.uploadfile.bo.Mensaje;
import mx.org.ccti.uploadfile.bo.UsuarioBO;
import mx.org.ccti.uploadfile.util.Configuracion;
import mx.org.ccti.uploadfile.util.Constantes;
import mx.org.ccti.uploadfile.util.Utilidades;

public class BaseDatos {
	
	Configuracion configuracion = new Configuracion();
	Utilidades    utilidades    = new Utilidades();
	Constantes    constantes    = new Constantes();
	Mensaje       mensaje       = new Mensaje();
	
	// Metodo que genera la secuencia para insertar el nombre del archivo en la BD
	public long getSecuenciaArchivoDatos() {
		Connection conn = null;
		Statement  stmt = null;
		String     secuencia = "0";
		
		try {
			Class.forName(configuracion.JDBC_DRIVER);		
			System.out.println("Connecting to database...");
		    conn = DriverManager.getConnection(configuracion.DB_URL, configuracion.USER, configuracion.PASS);		    
		    
		    stmt = conn.createStatement();	    
		    ResultSet rsSeqTrans = stmt.executeQuery("SELECT nextval('canombrearchivo_id_seq') as nextSequence");
		    while(rsSeqTrans.next()) {
		    	secuencia = rsSeqTrans.getString("nextSequence"); }
		} catch (ClassNotFoundException e) {
			secuencia = "-1";
			e.printStackTrace();
		} catch (SQLException e) {
			secuencia = "-1";
			e.printStackTrace();
		} catch(Exception e) {
			secuencia = "-1";
		    e.printStackTrace();
		} finally { // Se cierra la conexión con la base de datos.
            try {
                if (conn != null) { conn.close(); }
            } catch (SQLException ex) {
            	secuencia = "-1"; 
                System.out.println(ex.getMessage()); }
        } // Finnaly
		
		return Long.parseLong(secuencia);		
	} //getSecuenciaTransaccion
	
					
	//public Mensaje insertaDetalleArchivo( String prmRutaArch, String archivo, String prmDescripcion, String localidad ) {
	public EquipoDTO insertaDetalleArchivo( String prmRutaArch, String localidad ) {
		EquipoDTO  equipoInsrt = null;	
		CsvReader  reader      = null;
		int  vInd              = 0;		
		long numLinea          = 1;
	    						
		try {	    			    
			EquipoDTO equipoDTO = new EquipoDTO();
			
	    	// Leo las lineas del archivo
	    	reader = new CsvReader("C:\\Files\\" + prmRutaArch);
			reader.setDelimiter('|');
		    while ( reader.readRecord() ) {
	    		if ( !reader.get(0).equals("") || reader.get(0) != null ) { // Que el registro no sea blanco o nulo								
					equipoDTO.setEstatus( this.colocaCarcterEspacioURL( reader.get(0).trim() ) );	
					equipoDTO.setRegion( reader.get(1) );	
					equipoDTO.setLocalidad( this.colocaCarcterEspacioURL( reader.get(2) ) );	
					equipoDTO.setBodega( this.colocaCarcterEspacioURL( reader.get(3) ) );	
					equipoDTO.setRazon( this.colocaCarcterEspacioURL( reader.get(4) ) );	
					equipoDTO.setEmpleado( this.colocaCarcterEspacioURL( reader.get(5) ) );
					// Datos del Equipo a Reemplazar
					equipoDTO.setNombre_cambio( this.colocaCarcterEspacioURL( reader.get(6) ) );	
					equipoDTO.setSerie_cambio( this.colocaCarcterEspacioURL( reader.get(7) ) );	
					equipoDTO.setEstatus_cambio( this.colocaCarcterEspacioURL( reader.get(8) ) );	
					equipoDTO.setMarca( this.colocaCarcterEspacioURL( reader.get(9) ) );	
					equipoDTO.setModelo( this.colocaCarcterEspacioURL( reader.get(10) ) );	
					// Datos del Equipo Nuevo
					equipoDTO.setGarantia( this.colocaCarcterEspacioURL( reader.get(11) ) );	
					equipoDTO.setSistema( this.colocaCarcterEspacioURL( reader.get(12) ) );	
					equipoDTO.setNombre_nuevo( this.colocaCarcterEspacioURL( reader.get(13) ) );	
					equipoDTO.setSerie_nuevo( this.colocaCarcterEspacioURL( reader.get(14) ) );	
					equipoDTO.setAlta( this.colocaCarcterEspacioURL( reader.get(15) ) );
					equipoDTO.setMes_renovacion( this.colocaCarcterEspacioURL( reader.get(16) ) );	// 26Oct2020 - Campos Nuevo					
					equipoDTO.setModelo_nuevo( this.colocaCarcterEspacioURL( reader.get(17) ) );
					equipoDTO.setOrden( this.colocaCarcterEspacioURL( reader.get(18) ) );
					equipoDTO.setComentarios( this.colocaCarcterEspacioURL( reader.get(19) ) );
					equipoDTO.setProgramado( this.colocaCarcterEspacioURL( reader.get(20) ) );
					equipoDTO.setProveedor( this.colocaCarcterEspacioURL( reader.get(21) ) );
					equipoDTO.setTicket( this.colocaCarcterEspacioURL( reader.get(22) ) );	// 26Oct2020 - Campos Nuevo	
					equipoDTO.setEstatus_control( this.colocaCarcterEspacioURL( reader.get(23) ) );
					equipoDTO.setComm_control( this.colocaCarcterEspacioURL( reader.get(24) ) );	// 26Oct2020 - Campo Nuevo
					
					EquipoService equipoService = new EquipoService();
					equipoInsrt = equipoService.insertarEquipo(equipoDTO, "FileUpload");
		    	}	
			} // While reader
		       
		} catch(Exception e) {			
		    e.printStackTrace();
		} 
			
		return equipoInsrt;
	} // insertaDetalleArchivo
	
	
	public Mensaje insertaNombreArchivo( long secuencia, String prmArchivo, String prmDescripcion ) {
		Mensaje mensaje = new Mensaje();
		Connection conn = null;
		Statement  stmt = null;
		String     sqlInsert  = "";
		String     codError   = "";
		String     descripErr = "";
										
		try {
			Class.forName(configuracion.JDBC_DRIVER);		
			System.out.println("Connecting to database...");
		    conn = DriverManager.getConnection(configuracion.DB_URL, configuracion.USER, configuracion.PASS);
		    
		    // Inicializo valores
			codError   = constantes.COD_EXITO;	
			descripErr = constantes.DESC_EXITO;
			
			stmt = conn.createStatement();
		    sqlInsert = "INSERT INTO canombrearchivo ( idArchivo, nombreArchivo, descripcion, creadoPor, fechacreacion ) VALUES ( " +
		    		secuencia + ", '" + prmArchivo + "', '" + prmDescripcion + "', '" + "SYSADMIN" + "', now() )";		    
		    System.out.println("sqlInsert: " + sqlInsert);
		    stmt.executeUpdate(sqlInsert);
		    conn.commit();
		} catch (SQLException e) {
			codError   = constantes.COD_ERROR;
			descripErr = "Excepcion SQL al Insertar el nombre del archivo. Por favor contacte a su Administrador "; 
			e.printStackTrace();
		} catch(Exception e) {
			codError = constantes.COD_ERROR;
			descripErr = "Excepcion Generar al al Insertar el nombre del archivo en la BD. Por favor contacte a su Administrador ";  
		    e.printStackTrace();
		} finally {
            stmt = null;					
            try {
            	conn.close();
                if (conn != null) { conn.close(); }
            } catch (SQLException e) {   
            	codError = constantes.COD_ERROR;
            	descripErr = "No fue posible cerrar adecuadamente la transaccion en la base de datos. Por favor contacte a su Administrador "; 
                e.printStackTrace(); }  
        } 
		
		mensaje.setCodigo(codError);
		mensaje.setDescripcion(descripErr);		
		return mensaje;
	} // insertaNombreArchivo
	
	
	public Mensaje eliminaDetalleArchivoDatos( String prmLocalidad ) {
		Mensaje mensaje = new Mensaje();
		Connection conn = null;
		Statement  stmt = null;
		String     sqlDelete  = "";
		String     codError   = "";
		String     descripErr = "";
										
		try {
			Class.forName(configuracion.JDBC_DRIVER);		
			System.out.println("Connecting to database...");
		    conn = DriverManager.getConnection(configuracion.DB_URL, configuracion.USER, configuracion.PASS);
		    
		    // Inicializo valores
			codError   = constantes.COD_EXITO;	
			descripErr = constantes.DESC_EXITO;
			
			stmt = conn.createStatement();
		    sqlDelete = "DELETE FROM " + constantes.TBL_DETARCHV + " WHERE localidad = '" + prmLocalidad + "'"; 
		    System.out.println("sqlDelete: " + sqlDelete);
		    stmt.executeUpdate(sqlDelete);
		    conn.commit();
		} catch (SQLException e) {
			codError   = constantes.COD_ERROR;
			descripErr = "Excepcion SQL al Insertar el nombre del archivo. Por favor contacte a su Administrador "; 
			e.printStackTrace();
		} catch(Exception e) {
			codError = constantes.COD_ERROR;
			descripErr = "Excepcion Generar al al Insertar el nombre del archivo en la BD. Por favor contacte a su Administrador ";  
		    e.printStackTrace();
		} finally {
            stmt = null;					
            try {
            	conn.close();
                if (conn != null) { conn.close(); }
            } catch (SQLException e) {   
            	codError = constantes.COD_ERROR;
            	descripErr = "No fue posible cerrar adecuadamente la transaccion en la base de datos. Por favor contacte a su Administrador "; 
                e.printStackTrace(); }  
        } 
		
		mensaje.setCodigo(codError);
		mensaje.setDescripcion(descripErr);		
		return mensaje;
	} // eliminaDetalleArchivoDatos
	
	
	// Metodo que genera la secuencia para insertar el nombre del archivo en la BD
	public UsuarioBO getInformacionUsuario( String prmUsuario, String prmContrasena ) {
			UsuarioBO  usarioBo  = new UsuarioBO();
			Connection conn      = null;
			Statement  stmt      = null;
			String     secuencia = "0";
			
			try {
				Class.forName(configuracion.JDBC_DRIVER);		
			    conn = DriverManager.getConnection(configuracion.DB_URL, configuracion.USER, configuracion.PASS);		    
			    
			    stmt = conn.createStatement();	    
			    ResultSet rsSeqTrans = stmt.executeQuery("SELECT nombre, apellido FROM causuarios WHERE login = '" + prmUsuario + 
			    		"' AND contrasena = '" + prmContrasena + "'");
			    // System.out.println("SELECT nombre, apellido FROM causuarios WHERE login = '" + prmUsuario + "' AND contrasena = '" + prmContrasena + "'");
			    while(rsSeqTrans.next()) {
			    	usarioBo.setUsuario(prmUsuario);
			    	usarioBo.setDescripcion(rsSeqTrans.getString("nombre") + " " + rsSeqTrans.getString("apellido")); 
			    	usarioBo.setEncontrado(1); }
			} catch (ClassNotFoundException e) {
				usarioBo.setEncontrado(0); 
				e.printStackTrace();
			} catch (SQLException e) {
				usarioBo.setEncontrado(0); 
				e.printStackTrace();
			} catch(Exception e) {
				usarioBo.setEncontrado(0); 
			    e.printStackTrace();
			} finally { // Se cierra la conexión con la base de datos.
	            try {
	                if (conn != null) { conn.close(); }
	            } catch (SQLException ex) {
	            	usarioBo.setEncontrado(0); 
	                System.out.println(ex.getMessage()); }
	        } // Finnaly
			
			return usarioBo;
		} //getSecuenciaTransaccion	
	
	
	public List<CampoIntegPagoBo> conultaCamposArchivoIntegracion( ) {
		List<CampoIntegPagoBo> listaCampos = new ArrayList<CampoIntegPagoBo>();
		
		Connection conn = null;
		Statement  stmt = null;
		String     sqlSqlCampos = "";
		
		try {
			Class.forName(configuracion.JDBC_DRIVER);		
			System.out.println("Connecting to database...");
		    conn = DriverManager.getConnection(configuracion.DB_URL, configuracion.USER, configuracion.PASS);
		    	    
		    // Obtengo la informacion de la tabla 'integracion_pagos'
		    stmt = conn.createStatement();		    
		    sqlSqlCampos = "SELECT id, nombreCampo, campoTbl, tipoCampo  FROM pscampoarchintegrpago  ORDER BY id";
		    System.out.println("SQL: " + sqlSqlCampos);
		    ResultSet rsIntPago = stmt.executeQuery(sqlSqlCampos);
		    
		    // Barro el resulset de Integracion de pagos		    
		    while(rsIntPago.next()) {			   	
		   		CampoIntegPagoBo campoIntegPagoBo = new CampoIntegPagoBo();
		   		
		   		campoIntegPagoBo.setId(rsIntPago.getInt("id"));
		   		campoIntegPagoBo.setNombreCampo(rsIntPago.getString("nombreCampo"));
		   		campoIntegPagoBo.setCampoTbl(rsIntPago.getString("campoTbl"));
		   		campoIntegPagoBo.setTipoCampo(rsIntPago.getString("tipoCampo"));
		    	listaCampos.add(campoIntegPagoBo);			    				    	
			} // While	
		    
		} catch (ClassNotFoundException e) {			
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch(Exception e){		
		    e.printStackTrace();
		} finally { // Se cierra la conexión con la base de datos.
            try {
                if (conn != null) { conn.close(); }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage()); }
        } // Finnaly 
	
		return listaCampos;
	} //conultaCamposArchivoIntegracion
	
	
	// Metodo que genera la secuencia para insertar el nombre del archivo en la BD
	public Long getUltimaSecuenciaDetalleArchivoDatos() {
		Connection conn = null;
		Statement  stmt = null;
		String     secuencia = "0";
		Long       countRegs = 0L;
		
		try {
			Class.forName(configuracion.JDBC_DRIVER);		
			System.out.println("Connecting to database...");
		    conn = DriverManager.getConnection(configuracion.DB_URL, configuracion.USER, configuracion.PASS);		    
		    
		    stmt = conn.createStatement();
		    
		    // Hago el conteo de los registros en la tabla para saber si hago el max + 1 o regreso '0'
		    ResultSet rsCntRegistros = stmt.executeQuery("SELECT Count(1) as countRegs FROM " + constantes.TBL_DETARCHV + " ");
		    while(rsCntRegistros.next()) {
		    	countRegs = rsCntRegistros.getLong("countRegs"); }
		    if ( countRegs > 0 ) {
		    	ResultSet rsSeqTrans = stmt.executeQuery("SELECT Max(id_integracion_pago) + 1 as nextSequence FROM " + constantes.TBL_DETARCHV + " ");
			    while(rsSeqTrans.next()) {
			    	secuencia = rsSeqTrans.getString("nextSequence"); }
		    } else {
		    	secuencia = "0";
		    }
		} catch (ClassNotFoundException e) {
			secuencia = "-1";
			e.printStackTrace();
		} catch (SQLException e) {
			secuencia = "-1";
			e.printStackTrace();
		} catch(Exception e) {
			secuencia = "-1";
		    e.printStackTrace();
		} finally { // Se cierra la conexión con la base de datos.
	           try {
	               if (conn != null) { conn.close(); }
	           } catch (SQLException ex) {
	           	secuencia = "-1"; 
	               System.out.println(ex.getMessage()); }
	       } // Finnaly
		
		return Long.parseLong(secuencia);		
	} //getUltimaSecuenciaDetalleArchivoDatos
	
	
	public String colocaCarcterEspacioURL( String valor ) {
		String strReturnValor = "";
		if (valor!=null) {
			strReturnValor = valor.replace(" ", "%20");
			
			if ( valor.trim().equals("#N/D") ) {
				strReturnValor = ""; 
			}
		}		
		return strReturnValor;
	}
}
