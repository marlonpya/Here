package connection.test;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import app.ws.dao.DAOConnection;

public class TestConnection {

	@Test
	public void test() throws SQLException {
		DAOConnection conexionDao = new DAOConnection();
		conexionDao.connect();
		ResultSet rs = conexionDao.getQuery("select * from Tb_Tablita");
		
		while (rs.next()) {
			System.out.println("Hay data");
		}
		
	}

}
