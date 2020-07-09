package com.agenda.dao;

import com.agenda.bean.BuscarTablasUnifBO;
import com.agenda.conexion.CerrarConexion;
import com.agenda.conexion.ConexionBD;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Logger;

import javax.naming.NamingException;

import oracle.jdbc.OracleTypes;

public class BuscarTablasUnifDAO extends CerrarConexion{

	Logger log = Logger.getLogger("MyLOG");
	  
	  public BuscarTablasUnifDAO() {}
	  private Vector<BuscarTablasUnifBO> lista;
	  public Vector<BuscarTablasUnifBO> getBuscarTablasUnifBD() { Connection cnn = null;
	  
	    CallableStatement cs = null;
	    ResultSet rs = null;
	    ConexionBD con = new ConexionBD();
	    lista = new Vector<BuscarTablasUnifBO>() ;
	    try
	    {
	      cnn = con.abrirConexionAtencion();
	      cs = cnn.prepareCall("{call obtTablasUnif(?)}");
	      cs.registerOutParameter(1, OracleTypes.CURSOR);
	      cs.executeQuery();
	      rs = (ResultSet)cs.getObject(1);
	      
	      while (rs.next()) {
	    	BuscarTablasUnifBO bo = new BuscarTablasUnifBO();
	        bo.setNombreTabla(rs.getString("NOMBRE_TABLA"));
	        bo.setOwner(rs.getString("OWNER"));
	        lista.add(bo);
	      }
	    } catch (NamingException e) {
	      log.info("Error metodo getBuscarTablasUnifBD " + e.getMessage());
	    } catch (SQLException e) {
	      log.info("Error SQL metodo getBuscarTablasUnifBD " + e.getMessage());
	    } catch (Exception ex) {
	      log.info("Error Creacion Archivo metodo getBuscarTablasUnifBD " + ex.getMessage());
	    } finally {
	      super.cerrarCallable(cs);
	      super.cerrarResultSet(rs);
	      super.cerrarConexion(cnn);
	    }
	    return lista;
	  }
	
}
