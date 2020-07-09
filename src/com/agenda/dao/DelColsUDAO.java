package com.agenda.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.naming.NamingException;

import oracle.jdbc.OracleTypes;

import com.agenda.bean.DelColsUBO;
import com.agenda.conexion.CerrarConexion;
import com.agenda.conexion.ConexionBD;

public class DelColsUDAO extends CerrarConexion{
	
Logger log = Logger.getLogger("MyLOG");
	
	public DelColsUBO setDelColsUBD(int codCompetencia, long crr_ttc){
		Connection cnn = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		ConexionBD con = new ConexionBD();
		DelColsUBO bo = new DelColsUBO();
		try{
			cnn = con.abrirConexionAtencion();
			cs = cnn.prepareCall("{call delColsU(?,?,?,?)}");
			cs.setInt(1, codCompetencia);
			cs.setLong(2, crr_ttc);
			cs.registerOutParameter(3, OracleTypes.NUMERIC);		
			cs.registerOutParameter(4, OracleTypes.VARCHAR);
			cs.executeQuery();
			bo.setCodigo(cs.getInt(3));
			bo.setMensaje(cs.getString(4));
		}catch(NamingException e) {
			bo.setMensaje(e.getMessage());
			log.info("Error metodo setDelColsUBD " + e.getMessage());
		}catch (SQLException e) {
			bo.setMensaje(e.getMessage());
			log.info("Error SQL metodo setDelColsUBD " + e.getMessage());
		}catch(Exception ex){
			bo.setMensaje(ex.getMessage());
			log.info("Error metodo setDelColsUBD " + ex.getMessage());
		}finally {
			super.cerrarCallable(cs);
			super.cerrarResultSet(rs);
			super.cerrarConexion(cnn);
		}
		return bo;
	}

}
