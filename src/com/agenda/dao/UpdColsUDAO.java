package com.agenda.dao;

import com.agenda.bean.RespuestaBO;
import com.agenda.bean.UpdColsUBO;
import com.agenda.conexion.CerrarConexion;
import com.agenda.conexion.ConexionBD;

import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;
import javax.naming.NamingException;

public class UpdColsUDAO
  extends CerrarConexion
{
  public UpdColsUDAO() {}
  
  Logger log = Logger.getLogger("MyLOG");
  
  public RespuestaBO setUpdColsUBD(UpdColsUBO updColsU) {
    Connection cnn = null;
    CallableStatement cs = null;
    ResultSet rs = null;
    ConexionBD con = new ConexionBD();
    RespuestaBO boResp = new RespuestaBO();
    try {
      cnn = con.abrirConexionAtencion();
      cs = cnn.prepareCall("{call UPDCOLSU(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
      cs.setInt(1, updColsU.getCodCompetencia());
      cs.setLong(2, updColsU.getCrr_ttc());
      cs.setLong(3, updColsU.getCrr_tth());
      cs.setString(4, updColsU.getOwnerU());
      cs.setString(5, updColsU.getTablaU());
      cs.setString(6, updColsU.getColumnaU());
      cs.setInt(7, updColsU.getFlgPkU());
      cs.setString(8, updColsU.getTipoU());
      cs.setInt(9, updColsU.getLargoU());
      cs.setInt(10, updColsU.getPrecisionU());
      cs.setInt(11, updColsU.getEscalaU());
      cs.setString(12, updColsU.getForma());
      cs.setString(13, updColsU.getOwnerO());
      cs.setString(14, updColsU.getTablaO());
      cs.setString(15, updColsU.getColumnaO());
      cs.setInt(16, updColsU.getFlgPkO());
      cs.setString(17, updColsU.getTipoO());
      cs.setInt(18, updColsU.getLargoO());
      cs.setInt(19, updColsU.getPrecisionO());
      cs.setLong(20, updColsU.getEscalaO());
      cs.setString(21, updColsU.getFuncion());
      cs.setLong(22, updColsU.getConstanteNum());
      cs.setString(23, updColsU.getConstanteChar());
      cs.setString(24, updColsU.getOwnerMig());
      cs.setInt(25, updColsU.getIdentity());
      cs.registerOutParameter(26, OracleTypes.NUMERIC);
      cs.registerOutParameter(27, OracleTypes.VARCHAR);
      cs.executeQuery();
      boResp.setCodigo(cs.getInt(26));
      boResp.setMensaje(cs.getString(27));
    } catch (NamingException e) {
    	boResp.setMensaje(e.getMessage());
      log.info("Error metodo setUpdColsUBD " + e.getMessage());
    } catch (SQLException e) {
    	boResp.setMensaje(e.getMessage());
      log.info("Error SQL metodo setUpdColsUBD " + e.getMessage());
    } catch (Exception ex) {
    	boResp.setMensaje(ex.getMessage());
      log.info("Error metodo setUpdColsUBD " + ex.getMessage());
    } finally {
      super.cerrarCallable(cs);
      super.cerrarResultSet(rs);
      super.cerrarConexion(cnn);
    }
    return boResp;
  }
}