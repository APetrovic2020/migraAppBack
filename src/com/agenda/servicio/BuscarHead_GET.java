package com.agenda.servicio;

import com.agenda.bean.BuscarHeadBO;
import com.agenda.dao.BuscarHeadDAO;

import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;


@Path("/getBuscarHead")

public class BuscarHead_GET {

	Logger log = Logger.getLogger("MyLOG");
	  
	  
	  List<BuscarHeadBO> lista;
	  
	  @Path("{codCompetencia}")
	  @GET
	  @Produces({"application/json"})
	  public Response listaTablasHead(@PathParam("codCompetencia") int codCompetencia) throws JSONException { 
		String result = "";
	    try {
	      JSONObject jo = new JSONObject();
	      BuscarHeadDAO dao = new BuscarHeadDAO();
	      
	      lista = dao.getBuscarHeadBD(codCompetencia);
	      jo.put("buscarHead", lista);
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
