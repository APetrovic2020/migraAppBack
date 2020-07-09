package com.agenda.dao;

import com.agenda.bean.BuscarColsVistaBO;
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

public class BuscarColsVistaDAO extends CerrarConexion{

	Logger log = Logger.getLogger("MyLOG");
	  
	  public BuscarColsVistaDAO() {}
	  private Vector<BuscarColsVistaBO> lista;
	  public Vector<BuscarColsVistaBO> getBuscarColsVistaBD(int codCompetencia, long crr_tth, int flgDbLink) { Connection cnn = null;
	  
	    CallableStatement cs = null;
	    ResultSet rs = null;
	    ConexionBD con = new ConexionBD();
	    
	    lista = new Vector<BuscarColsVistaBO>() ;
	    try
	    {
	      cnn = con.abrirConexionAtencion();
	      cs = cnn.prepareCall("{call obtColsTablaVista(?,?,?,?)}");
	      cs.setInt(1, codCompetencia);
	      cs.setLong(2, crr_tth);
	      cs.setLong(3, flgDbLink);
	      cs.registerOutParameter(4, OracleTypes.CURSOR);
	      cs.executeQuery();
	      rs = (ResultSet)cs.getObject(4);
	      
	      while (rs.next()) {
	    	  BuscarColsVistaBO bo = new BuscarColsVistaBO();
	        bo.setOwner(rs.getString("OWNER"));
	        bo.setTableName(rs.getString("TABLE_NAME"));
	        bo.setColumnName(rs.getString("COLUMN_NAME"));
	        bo.setDataType(rs.getString("DATA_TYPE"));
	        bo.setDataLength(rs.getInt("DATA_LENGTH"));
	        bo.setDataPrecision(rs.getInt("DATA_PRECISION"));
	        bo.setDataScale(rs.getInt("DATA_SCALE"));
	        bo.setNullable(rs.getString("NULLABLE"));
	        
	        lista.add(bo);
	      }
	    } catch (NamingException e) {
	      log.info("Error metodo getBuscarColsVistaBD " + e.getMessage());
	    } catch (SQLException e) {
	      log.info("Error SQL metodo getBuscarColsVistaBD " + e.getMessage());
	    } catch (Exception ex) {
	      log.info("Error Creacion Archivo metodo getBuscarColsVistaBD " + ex.getMessage());
	    } finally {
	      super.cerrarCallable(cs);
	      super.cerrarResultSet(rs);
	      super.cerrarConexion(cnn);
	    }
	    return lista;
	  }
	
}
