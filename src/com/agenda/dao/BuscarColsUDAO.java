package com.agenda.dao;

import com.agenda.bean.BuscarColsUBO;
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

public class BuscarColsUDAO extends CerrarConexion{

	Logger log = Logger.getLogger("MyLOG");
	  
	  public BuscarColsUDAO() {}
	  private Vector<BuscarColsUBO> lista;
	  public Vector<BuscarColsUBO> getBuscarColsUBD(int codCompetencia, long crr_tth) { Connection cnn = null;
	  
	    CallableStatement cs = null;
	    ResultSet rs = null;
	    ConexionBD con = new ConexionBD();
	    
	    lista = new Vector<BuscarColsUBO>() ;
	    try
	    {
	      cnn = con.abrirConexionAtencion();
	      cs = cnn.prepareCall("{call obtColsPorTablaU(?,?,?)}");
	      cs.setInt(1, codCompetencia);
	      cs.setLong(2, crr_tth);
	      cs.registerOutParameter(3, OracleTypes.CURSOR);
	      cs.executeQuery();
	      rs = (ResultSet)cs.getObject(3);
	      
	      while (rs.next()) {
	    	  BuscarColsUBO bo = new BuscarColsUBO();
	        bo.setCodCompetencia(rs.getInt("COD_COMPETENCIA"));
	        bo.setCrr_ttc(rs.getLong("CRR_TTC"));
	        bo.setCrr_tth(rs.getLong("CRR_TTH"));
	        bo.setOwnerU(rs.getString("OWNER_U"));
	        bo.setTablaU(rs.getString("TABLA_U"));
	        bo.setColumnaU(rs.getString("COLUMNA_U"));
	        bo.setFlgPkU(rs.getInt("FLG_PK_U"));
	        bo.setTipoU(rs.getString("TIPO_U"));
	        bo.setLargoU(rs.getInt("LARGO_U"));
	        bo.setPrecisionU(rs.getInt("PRECISION_U"));
	        bo.setEscalaU(rs.getInt("ESCALA_U"));
	        bo.setForma(rs.getString("FORMA"));
	        bo.setOwnerO(rs.getString("OWNER_O"));
	        bo.setTablaO(rs.getString("TABLA_O"));
	        bo.setColumnaO(rs.getString("COLUMNA_O"));
	        bo.setFlgPkO(rs.getInt("FLG_PK_O"));
	        bo.setTipoO(rs.getString("TIPO_O"));
	        bo.setLargoO(rs.getInt("LARGO_O"));
	        bo.setPrecisionO(rs.getInt("PRECISION_O"));
	        bo.setEscalaO(rs.getInt("ESCALA_O"));
	        bo.setFuncion(rs.getString("FUNCION"));
	        bo.setConstanteNum(rs.getInt("CONSTANTE_NUM"));
	        bo.setConstanteChar(rs.getString("CONSTANTE_CHAR"));
	        bo.setFechaCreacion(rs.getString("FEC_CREA"));
	        bo.setOwnerMig(rs.getString("OWNER_MIG"));
	        bo.setIdentity(rs.getInt("IDENTITY"));
	        lista.add(bo);
	      }
	    } catch (NamingException e) {
	      log.info("Error metodo getBuscarColsUBD " + e.getMessage());
	    } catch (SQLException e) {
	      log.info("Error SQL metodo getBuscarColsUBD " + e.getMessage());
	    } catch (Exception ex) {
	      log.info("Error Creacion Archivo metodo getBuscarColsUBD " + ex.getMessage());
	    } finally {
	      super.cerrarCallable(cs);
	      super.cerrarResultSet(rs);
	      super.cerrarConexion(cnn);
	    }
	    return lista;
	  }
	
}
