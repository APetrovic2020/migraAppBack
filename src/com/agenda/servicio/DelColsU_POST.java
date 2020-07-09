package com.agenda.servicio;

import java.io.IOException;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.agenda.bean.DelColsUBO;
import com.agenda.dao.DelColsUDAO;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Path("/delColsU")

public class DelColsU_POST {
	
	Logger log = Logger.getLogger("MyLOG");
	private DelColsUBO respuesta;
	private String Message;
	
	@POST
	@Path("/jcolsu")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response delSalaPOST(String jcolsu){
		Logger log = Logger.getLogger("MyLOG");
		log.info(jcolsu);
		ObjectMapper map = new ObjectMapper();
		try {
			DelColsUBO bo = map.readValue(jcolsu, DelColsUBO.class);
			
			int codCompetencia = bo.getCodCompetencia();
			long crrTtc = bo.getCrr_ttc();
			
			DelColsUDAO dao = new DelColsUDAO();
			
			respuesta = dao.setDelColsUBD(codCompetencia, crrTtc);
			
			Message = "{\r\n" + "  \"mensaje\": \"" + respuesta.getMensaje() + "\" \r\n" + "}";
			
		} catch (JsonParseException e) {
			Message = "{\r\n" + "  \"mensaje\": \"" + e.getMessage() + "\" \r\n" + "}";
			e.printStackTrace();
			log.info(e.getMessage());
		} catch (JsonMappingException e) {
			Message = "{\r\n" + "  \"mensaje\": \"" + e.getMessage() + "\" \r\n" + "}";
			e.printStackTrace();
			log.info(e.getMessage());
		} catch (IOException e) {
			Message = "{\r\n" + "  \"mensaje\": \"" + e.getMessage()+ "\" \r\n" + "}";
			e.printStackTrace();
			log.info(e.getMessage());
		}
		if(respuesta.getCodigo() < 0 ){
			return Response.status(500)
		            .header("Access-Control-Allow-Origin", "*")
		            .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
		            .header("Access-Control-Allow-Credentials", "true")
		            .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
		            .header("Access-Control-Max-Age", "1209600").entity(Message).build();
		}
	return Response.status(200)
            .header("Access-Control-Allow-Origin", "*")
            .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
            .header("Access-Control-Allow-Credentials", "true")
            .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
            .header("Access-Control-Max-Age", "1209600").entity(Message).build();
	
	}

	public DelColsUBO getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(DelColsUBO respuesta) {
		this.respuesta = respuesta;
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}

}
