package com.agenda.dao;

import com.agenda.bean.BuscarNombreTablaMapBO;
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

public class BuscarNombreTablaMapDAO extends CerrarConexion{

	Logger log = Logger.getLogger("MyLOG");
	  
	  public BuscarNombreTablaMapDAO() {}
	  private Vector<BuscarNombreTablaMapBO> lista;
	  public Vector<BuscarNombreTablaMapBO> getBuscarNombreTablaMapBD(int codCompetencia, long crr_tth) { Connection cnn = null;
	  
	    CallableStatement cs = null;
	    ResultSet rs = null;
	    ConexionBD con = new ConexionBD();
	    lista = new Vector<BuscarNombreTablaMapBO>() ;
	    try
	    {
	      cnn = con.abrirConexionAtencion();
	      cs = cnn.prepareCall("{call obtNombreTablaPar(?,?,?)}");
	      cs.setInt(1, codCompetencia);
	      cs.setLong(2, crr_tth);
	      cs.registerOutParameter(3, OracleTypes.CURSOR);
	      cs.executeQuery();
	      rs = (ResultSet)cs.getObject(3);
	      
	      while (rs.next()) {
	    	BuscarNombreTablaMapBO bo = new BuscarNombreTablaMapBO();
	        bo.setNombreTabla(rs.getString("TABLA_O"));
	        bo.setOwner(rs.getString("OWNER_O"));
	        lista.add(bo);
	      }
	    } catch (NamingException e) {
	      log.info("Error metodo getBuscarNombreTablaMapBD " + e.getMessage());
	    } catch (SQLException e) {
	      log.info("Error SQL metodo getBuscarNombreTablaMapBD " + e.getMessage());
	    } catch (Exception ex) {
	      log.info("Error Creacion Archivo metodo getBuscarNombreTablaMapBD " + ex.getMessage());
	    } finally {
	      super.cerrarCallable(cs);
	      super.cerrarResultSet(rs);
	      super.cerrarConexion(cnn);
	    }
	    return lista;
	  }
	
}
