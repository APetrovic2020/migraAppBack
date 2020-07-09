package com.agenda.dao;

import com.agenda.bean.InsColsUBO;
import com.agenda.bean.RespuestaBO;
import com.agenda.conexion.CerrarConexion;
import com.agenda.conexion.ConexionBD;

import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;
import javax.naming.NamingException;

public class InsColsUDAO
  extends CerrarConexion
{
  public InsColsUDAO() {}
  
  Logger log = Logger.getLogger("MyLOG");
  
  public RespuestaBO setInsColsUBD(InsColsUBO insColsU) {
    Connection cnn = null;
    CallableStatement cs = null;
    ResultSet rs = null;
    ConexionBD con = new ConexionBD();
    RespuestaBO boResp = new RespuestaBO();
    try {
      cnn = con.abrirConexionAtencion();
      cs = cnn.prepareCall("{call INSCOLSU(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
      cs.setInt(1, insColsU.getCodCompetencia());
      cs.setLong(2, insColsU.getCrr_tth());
      cs.setString(3, insColsU.getOwnerU());
      cs.setString(4, insColsU.getTablaU());
      cs.setString(5, insColsU.getColumnaU());
      cs.setInt(6, insColsU.getFlgPkU());
      cs.setString(7, insColsU.getTipoU());
      cs.setInt(8, insColsU.getLargoU());
      cs.setInt(9, insColsU.getPrecisionU());
      cs.setInt(10, insColsU.getEscalaU());
      cs.setString(11, insColsU.getForma());
      cs.setString(12, insColsU.getOwnerO());
      cs.setString(13, insColsU.getTablaO());
      cs.setString(14, insColsU.getColumnaO());
      cs.setInt(15, insColsU.getFlgPkO());
      cs.setString(16, insColsU.getTipoO());
      cs.setInt(17, insColsU.getLargoO());
      cs.setInt(18, insColsU.getPrecisionO());
      cs.setLong(19, insColsU.getEscalaO());
      cs.setString(20, insColsU.getFuncion());
      cs.setLong(21, insColsU.getConstanteNum());
      cs.setString(22, insColsU.getConstanteChar());
      cs.setString(23, insColsU.getOwnerMig());
      cs.setInt(24, insColsU.getIdentity());
      cs.registerOutParameter(25, OracleTypes.NUMERIC);
      cs.registerOutParameter(26, OracleTypes.VARCHAR);
      cs.executeQuery();
      boResp.setCodigo(cs.getInt(25));
      boResp.setMensaje(cs.getString(26));
    } catch (NamingException e) {
    	boResp.setMensaje(e.getMessage());
      log.info("Error metodo setInsColsUBD " + e.getMessage());
    } catch (SQLException e) {
    	boResp.setMensaje(e.getMessage());
      log.info("Error SQL metodo setInsColsUBD " + e.getMessage());
    } catch (Exception ex) {
    	boResp.setMensaje(ex.getMessage());
      log.info("Error metodo setInsColsUBD " + ex.getMessage());
    } finally {
      super.cerrarCallable(cs);
      super.cerrarResultSet(rs);
      super.cerrarConexion(cnn);
    }
    return boResp;
  }
}