package com.agenda.servicio;

import com.agenda.bean.BuscarColsUBO;
import com.agenda.dao.BuscarColsUDAO;

import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;


@Path("/getBuscarColsU")

public class BuscarColsU_GET {

	Logger log = Logger.getLogger("MyLOG");
	  
	  
	  List<BuscarColsUBO> lista;
	  
	  @Path("{codCompetencia}/{crr_tth}")
	  @GET
	  @Produces({"application/json"})
	  public Response listaColsU(@PathParam("codCompetencia") int codCompetencia, @PathParam("crr_tth") int crr_tth) throws JSONException { 
		String result = "";
	    try {
	      JSONObject jo = new JSONObject();
	      BuscarColsUDAO dao = new BuscarColsUDAO();
	      
	      lista = dao.getBuscarColsUBD(codCompetencia, crr_tth);
	      jo.put("buscarColsU", lista);
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
