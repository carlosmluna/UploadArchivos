package mx.com.bimbo.equipos.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import mx.com.bimbo.equipos.modelo.dto.LocalidadDTO;
import mx.com.bimbo.equipos.util.Constantes;

public class CatalogoService {
	Constantes constantes = new Constantes();
	
	public List<LocalidadDTO> buscarLocalidades( ) {		
		System.out.println("Service {} - Se realiza la consulta de 'Localidades' en la Base de datos Mediante WS-Rest");
		List<LocalidadDTO> localidades = new ArrayList<LocalidadDTO>();
		String jsonLocalidad      = "";
		
		try {
            String urlLocalidad = "http://" + constantes.IP_APLICAION + constantes.CAT_CONTEXT + "/catalogo/catlocalidades-consultar";
            System.out.println(urlLocalidad);
            URL url = new URL(urlLocalidad);	//your url i.e fetch data from .
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
                jsonLocalidad = output;
            }
            conn.disconnect();
            
            JsonParser parser = new JsonParser();
            JsonArray gsonArr = parser.parse(jsonLocalidad).getAsJsonArray();
            for (JsonElement obj : gsonArr) {
            	LocalidadDTO localidadDTO = new LocalidadDTO();
            	JsonObject   gsonObj      = obj.getAsJsonObject();
            	                 	
            	localidadDTO.setId_localidad( gsonObj.get("id").getAsInt() );
            	localidadDTO.setLocalidad( gsonObj.get("localidad").getAsString() );
            	localidades.add(localidadDTO);
            }
            /* Gson gson = new Gson(); 
            LocalidadEntity[] localidades = gson.fromJson(jsonLocalidad, LocalidadEntity[].class); */
        } catch (Exception e) {
            System.out.println("Exception in NetClientGet GET - Buscar Localidades: - " + e);
        }
		return localidades;
	}
}
