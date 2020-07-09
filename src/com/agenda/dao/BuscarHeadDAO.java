package com.agenda.dao;

import com.agenda.bean.BuscarHeadBO;
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

public class BuscarHeadDAO extends CerrarConexion{

	Logger log = Logger.getLogger("MyLOG");
	  
	  public BuscarHeadDAO() {}
	  private Vector<BuscarHeadBO> lista;
	  public Vector<BuscarHeadBO> getBuscarHeadBD(int codCompetencia) { Connection cnn = null;
	  
	    CallableStatement cs = null;
	    ResultSet rs = null;
	    ConexionBD con = new ConexionBD();
	    
	    lista = new Vector<BuscarHeadBO>() ;
	    try
	    {
	      cnn = con.abrirConexionAtencion();
	      cs = cnn.prepareCall("{call obtHead(?,?)}");
	      cs.setInt(1, codCompetencia);
	      cs.registerOutParameter(2, OracleTypes.CURSOR);
	      cs.executeQuery();
	      rs = (ResultSet)cs.getObject(2);
	      
	      while (rs.next()) {
	    	BuscarHeadBO bo = new BuscarHeadBO();
	        bo.setCodCompetencia(rs.getInt("COD_COMPETENCIA"));
	        bo.setCrr_tth(rs.getLong("CRR_TTH"));
	        bo.setBddU(rs.getString("BDD_U"));
	        bo.setOwnerU(rs.getString("OWNER_U"));
	        bo.setTablaU(rs.getString("TABLA_U"));
	        bo.setBddO(rs.getString("BDD_O"));
	        bo.setOwnerO(rs.getString("OWNER_O"));
	        bo.setTablaO(rs.getString("TABLA_O"));
	        bo.setFechaCreacion(rs.getString("FEC_CREA"));
	        bo.setFlgActivo(rs.getInt("FLG_ACTIVO"));
	        bo.setFlgGenPk(rs.getInt("FLG_GEN_PK"));
	        bo.setFlgGenMtrel(rs.getInt("FLG_GEN_MTREL"));
	        bo.setOwnerR(rs.getString("OWNER_R"));
	        bo.setTablaR(rs.getString("TABLA_R"));
	        bo.setJerarquia(rs.getInt("JERARQUIA"));
	        bo.setTablaPadreO(rs.getString("TABLA_PADRE_O"));
	        bo.setDblkUnif(rs.getString("DBLK_UNIF"));
	        bo.setDblkBase(rs.getString("DBLK_BASE"));
	        bo.setCondicion(rs.getString("CONDICION"));
	        bo.setCondicion2(rs.getString("CONDICION2"));
	        bo.setFlgGenCond(rs.getInt("FLG_GEN_COND"));
	        bo.setGlsDescripcion(rs.getString("TABLA_PADRE_U"));
	        bo.setTablaPadreU(rs.getString("GLS_DESCRIPCION"));
	        bo.setFlgSinTabBase(rs.getInt("FLG_SIN_TAB_BASE"));
	        lista.add(bo);
	      }
	    } catch (NamingException e) {
	      log.info("Error metodo getBuscarHeadBD " + e.getMessage());
	    } catch (SQLException e) {
	      log.info("Error SQL metodo getBuscarHeadBD " + e.getMessage());
	    } catch (Exception ex) {
	      log.info("Error Creacion Archivo metodo getBuscarHeadBD " + ex.getMessage());
	    } finally {
	      super.cerrarCallable(cs);
	      super.cerrarResultSet(rs);
	      super.cerrarConexion(cnn);
	    }
	    return lista;
	  }
	
}
