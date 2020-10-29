package mx.com.bimbo.equipos.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;

import mx.com.bimbo.equipos.modelo.dto.EquipoDTO;
import mx.com.bimbo.equipos.util.Constantes;
import mx.com.bimbo.equipos.util.Utilidades;

public class EquipoService {
	Constantes constantes = new Constantes();

	public EquipoDTO insertarEquipo( EquipoDTO equipoModelo, String suaurio ) {		
		System.out.println("Service {} - Se realiza la Insercion del Registro 'Equipo' en la Base de datos Mediante WS-Rest");
		EquipoDTO  equipo = new EquipoDTO();
		Utilidades utils  = new Utilidades();
		String jsonEquipo       = "";
		String uriUpdateEquipo = "";
		
		try {
			// uriUpdateEquipo = "http://" + constantes.IP_APLICAION + constantes.EQP_CONTEXT + "/equipos/equipos-insertar" + 
			uriUpdateEquipo = "http://localhost:8060/equipos/equipos-insertar" +
					// Informacion de los primeros campos
					"?est=" 	+ equipoModelo.getEstatus()   	+ 
					"&reg=" 	+ equipoModelo.getRegion()    	+	  
					"&loc=" 	+ equipoModelo.getLocalidad() 	+ 
					"&bodg=" 	+ equipoModelo.getBodega()    	+ 
					"&raz=" 	+ equipoModelo.getRazon()  		+ 
					// Informacion del Equipo a Reemplazar
					"&empl=" 	+ utils.colocaCarcterEspacioURL( equipoModelo.getEmpleado()  ) 			+ 
					"&nomb1="   + utils.colocaCarcterEspacioURL( equipoModelo.getNombre_cambio() )  	+	 
					"&serie1="  + utils.colocaCarcterEspacioURL( equipoModelo.getSerie_cambio() )		+					
					"&est1="    + utils.colocaCarcterEspacioURL( equipoModelo.getEstatus_cambio() ) 	+ 
					"&marca="   + utils.colocaCarcterEspacioURL( equipoModelo.getMarca() )         	 	+
					"&model="   + utils.colocaCarcterEspacioURL( equipoModelo.getModelo() )         	+ 
					// Informacion del Equipo Nuevo
					"&nomb2=" 	+ utils.colocaCarcterEspacioURL( equipoModelo.getNombre_nuevo() )   	+ 
					"&serie2=" 	+ utils.colocaCarcterEspacioURL( equipoModelo.getSerie_nuevo() )    	+	 
					"&alta="    + utils.colocaCarcterEspacioURL( equipoModelo.getAlta() ) 				+
					"&model2=" 	+ utils.colocaCarcterEspacioURL( equipoModelo.getModelo_nuevo() )   	+
					"&grnt=" 	+ utils.colocaCarcterEspacioURL( equipoModelo.getGarantia() )	    	+
					"&so=" 		+ utils.colocaCarcterEspacioURL( equipoModelo.getSistema() )        	+
					"&orden=" 	+ utils.colocaCarcterEspacioURL( equipoModelo.getOrden() )          	+	 
					"&comm=" 	+ utils.colocaCarcterEspacioURL( equipoModelo.getComentarios() )		+
					"&fProg=" 	+ utils.colocaCarcterEspacioURL( equipoModelo.getProgramado() )	    	+
					"&prov=" 	+ utils.colocaCarcterEspacioURL( equipoModelo.getProveedor() )	    	+
					// 26Oct2020 - Campos Nuevos
					"&mesR="    + utils.colocaCarcterEspacioURL( equipoModelo.getMes_renovacion() )  	+ 
					"&tkt="     + utils.colocaCarcterEspacioURL( equipoModelo.getTicket() ) 		 	+ 
					"&comCtl="  + utils.colocaCarcterEspacioURL( equipoModelo.getComm_control() ) 	 	+ 
					// Informacion Adicional 
					"&estCtl="  + utils.colocaCarcterEspacioURL( equipoModelo.getEstatus_control() );
			
			// uriUpdateEquipo = "http://localhost:8060/equipos/equipos-actualizar?id=5&est=3&reg=6&loc=1&bodg=7&razon=13&empl=1232164&serie1=MXL225085S&nomb1=Juan%20Carlos%20Andres%20Fierro%20Ortiz&est1=Missing&marca=Hewlett-Packard&modelo=8200%20ELITE&nomb2=CARLOS%20LUNA%20FERNANDEZ&serie2=7654321&model2=DELL%20VOSTRO%20123&orden=4499001&accn=2&fProg=05-ENE-2020&prov=LBG&comm=Equipo%20sale%20del%20proyecto%20ya%20que%20esta%20en%20Missng&arch=ArchivoPrueba.txt&garant=31-ENE-2023&so=Windos%207%20Professional%20Edition&usr=CLUNA";
			System.out.println("uriUpdateEquipo: " + uriUpdateEquipo);
			
            URL url = new URL( uriUpdateEquipo );	//your url i.e fetch data from .
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP Error code : " + conn.getResponseCode());
            }
            
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(in);  
            String output;
            while ((output = br.readLine()) != null) {
                System.out.println(output);
                jsonEquipo = output;
            }
            conn.disconnect();
            
            // equipo = utils.convierteJsonToObject_Equipo( jsonEquipo );  
            Gson gson = new Gson(); 
            equipo = gson.fromJson(jsonEquipo, EquipoDTO.class); 
            
        } catch (Exception e) {
            System.out.println("Exception in NetClientGet GET - Insertar Informacion del Equipo: - " + uriUpdateEquipo + " - " + e);
        }
		return equipo;
	}
}
