package com.agenda.conexion;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class ConexionBD extends  CapturaExcepcion{
	
	private static final long serialVersionUID = -9101661324440780629L;
	private String error;
	
	
	public Connection abrirConexionAtencion() throws NamingException, SQLException, Exception{
				
		//BD LOCAL
//		String url = "jdbc:oracle:thin:@localhost:1521:xe";
//		String usuario = "hr"; //"hr AS SYSDBA";
//		String password = "hr";
		//BD QA PJUD
		String url = "jdbc:oracle:thin:@unifdesa.bdd.pjud:1503:UNIFDESA";
		String usuario = "MIGRAX_APL";
		String password = "Migra#202004";
		
		DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
		Connection conn=null;
		
		conn = DriverManager.getConnection(url, usuario, password);
	    return conn;
	}
	
	public Connection abrirConexionPenal() throws NamingException, SQLException, Exception{
		
		//BD QA PJUD
		String url = "jdbc:oracle:thin:@unifdesa.bdd.pjud:1503:UNIFDESA";
		String usuario = "MIGRAX";
		String password = "Migra#202004";
		
		DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
		Connection conn=null;
		
		conn = DriverManager.getConnection(url, usuario, password);
	    return conn;
	}
	
	
	/*public Connection abrirConexionAtencion()
		    throws SQLException, NamingException, IOException
		  {
		    Connection conn = null;
		    try
		    {
		      Context initContext = new InitialContext();
		      
		      DataSource ds = (DataSource)initContext.lookup("jdbc/ds_agendapenal");
		      
		      return ds.getConnection();
		    }
		    catch (NamingException ex)
		    {
		      System.err.println(ex);
		      
		      return conn;
		    }
		    catch (SQLException ex)
		    {
		      System.err.println(ex);
		      
		      return conn;
		    }
		    catch (SecurityException e)
		    {
		      e.printStackTrace();
		    }
		    return conn;
		  }*/
	
	
	public String getError() {
		return error;
	}
	
	public static void main(String[] args) {

	}

}
