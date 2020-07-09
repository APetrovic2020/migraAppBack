package com.agenda.servicio;

import com.agenda.bean.BuscarNombreTablaMapBO;
import com.agenda.dao.BuscarNombreTablaMapDAO;

import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;


@Path("/getBuscarNombreTablaMap")

public class BuscarNombreTablaMap_GET {

	Logger log = Logger.getLogger("MyLOG");
	  
	  
	  List<BuscarNombreTablaMapBO> lista;
	  
	  @Path("{codCompetencia}/{crr_tth}")
	  @GET
	  @Produces({"application/json"})
	  public Response listaNombreTablaMap(@PathParam("codCompetencia") int codCompetencia, @PathParam("crr_tth") int crr_tth) throws JSONException { 
		String result = "";
	    try {
	      JSONObject jo = new JSONObject();
	      BuscarNombreTablaMapDAO dao = new BuscarNombreTablaMapDAO();
	      
	      lista = dao.getBuscarNombreTablaMapBD(codCompetencia, crr_tth);
	      jo.put("buscarNombreTablaMap", lista);
	      result = String.valueOf(jo);
	    } catch (JSONException e) {
	      log.info(e.getMessage());
	      result = e.getMessage();
	    } catch (Exception e) {
	      log.info(e.getMessage());
	      result = e.getMessage();
	    }
	    return Response.status(200)
	            .header("Access-Control-Allow-Origin", "*")
	            .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
	            .header("Access-Control-Allow-Credentials", "true")
	            .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
	            .header("Access-Control-Max-Age", "1209600").entity(result).build();
	  }
	
}
