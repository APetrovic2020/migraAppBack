package com.agenda.servicio;

import com.agenda.bean.BuscarColsVistaBO;
import com.agenda.dao.BuscarColsVistaDAO;

import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;


@Path("/getBuscarColsVista")

public class BuscarColsVista_GET {

	Logger log = Logger.getLogger("MyLOG");
	  
	  
	  List<BuscarColsVistaBO> lista;
	  
	  @Path("{codCompetencia}/{crr_tth}/{flgDbLink}")
	  @GET
	  @Produces({"application/json"})
	  public Response listaColsVista(@PathParam("codCompetencia") int codCompetencia, @PathParam("crr_tth") int crr_tth, @PathParam("flgDbLink") int flgDbLink) throws JSONException { 
		String result = "";
	    try {
	      JSONObject jo = new JSONObject();
	      BuscarColsVistaDAO dao = new BuscarColsVistaDAO();
	      
	      lista = dao.getBuscarColsVistaBD(codCompetencia, crr_tth, flgDbLink);
	      jo.put("buscarColsVista", lista);
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
