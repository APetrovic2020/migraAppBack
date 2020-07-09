package com.agenda.servicio;

import com.agenda.bean.InsColsUBO;
import com.agenda.bean.RespuestaBO;
import com.agenda.dao.InsColsUDAO;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/setInsColsU")
public class InsColsU_POST
{
  public InsColsU_POST() {}
  
  Logger log = Logger.getLogger("MyLOG");
  private RespuestaBO respuesta;
  private String mensaje;
  
  @POST
  @Path("/insColsU")
  @Consumes({"application/json"})
  public Response setUpdColsPOST(String colsU) {
    Logger logger = Logger.getLogger("setInsCols");
    logger.info(colsU);
    ObjectMapper map = new ObjectMapper();

    try     {
    	InsColsUBO bo = (InsColsUBO)map.readValue(colsU, InsColsUBO.class);
      
    	InsColsUDAO dao = new InsColsUDAO();
  	
    	respuesta = dao.setInsColsUBD(bo);
    	mensaje = "{\r\n" + "  \"mensaje\": \"" + respuesta.getMensaje() + "\" \r\n" + "}";
           
    	if (respuesta.getCodigo() == 0) {

	        File logDir = new File("/archivos/SIAGJ_GESTION/logs");
	        if (!logDir.exists()) {
	          logDir.mkdir();
	        }
        
	        Handler consoleHandler = new ConsoleHandler();
	        Handler fileHandler = new FileHandler("/archivos/SIAGJ_GESTION/logs/insColsU.log", false);
	        SimpleFormatter simpleFormatter = new SimpleFormatter();
	        
	        fileHandler.setFormatter(simpleFormatter);
	        
	        logger.addHandler(consoleHandler);
	        logger.addHandler(fileHandler);
	        
	        consoleHandler.setLevel(Level.ALL);
	        fileHandler.setLevel(Level.ALL);
	        
    	}
    } catch (JsonParseException e) {
      mensaje = ("{\r\n  \"mensaje\": \"" + e.getMessage() + "\" \r\n" + "}");
      e.printStackTrace();
      log.info(e.getMessage());
    } catch (JsonMappingException e) {
      mensaje = ("{\r\n  \"mensaje\": \"" + e.getMessage() + "\" \r\n" + "}");
      e.printStackTrace();
      log.info(e.getMessage());
    } catch (IOException e) {
      mensaje = ("{\r\n  \"mensaje\": \"" + e.getMessage() + "\" \r\n" + "}");
      e.printStackTrace();
      log.info(e.getMessage());
    }
    if (respuesta.getCodigo() != 0) {
      return Response.status(500)
              .header("Access-Control-Allow-Origin", "*")
              .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
              .header("Access-Control-Allow-Credentials", "true")
              .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
              .header("Access-Control-Max-Age", "1209600").entity(mensaje).build();
    }
    return Response.status(200)
            .header("Access-Control-Allow-Origin", "*")
            .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
            .header("Access-Control-Allow-Credentials", "true")
            .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
            .header("Access-Control-Max-Age", "1209600").entity(mensaje).build();
  }
  
  
  public String getMessage() {
    return mensaje;
  }
  
  public void setMessage(String message) {
    mensaje = message;
  }
}
