package app.ws.host;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.XsonNamespaceContext;

import com.google.gson.Gson;
import com.sun.org.glassfish.gmbal.ParameterNames;

import app.ws.beans.BDepartamento;
import app.ws.dao.DAOConnection;

/**
 * 
 * @author Unkwonm Perú
 * 
 * http://localhost:port/WSAlphaBusiness/rest/webservice/accion
 *
 */

@Path("/webservice")
public class WebService {
	
	DAOConnection dao = new DAOConnection();

	@GET
	@Path("/departamentos")
	@Produces(MediaType.APPLICATION_JSON)
	public String getDepartamentos() {
		List<BDepartamento> lDepartamentos = new ArrayList<BDepartamento>();
		try {
			dao.connect();
			ResultSet rs = dao.getQuery("Select * From Tb_Gerencia");
			while (rs.next()) {
				BDepartamento objDepartamento = new BDepartamento();
				objDepartamento.setCodigo(rs.getInt(1));
				objDepartamento.setDescripcion(rs.getString(2));
				lDepartamentos.add(objDepartamento);
			}
		} catch (SQLException ex) {
			System.out.println("Error: " + ex.getMessage());
		}
		return new Gson().toJson(lDepartamentos);
	}
	
	@GET
	@Path("/gerencias")
	@Produces(MediaType.APPLICATION_JSON)
	public String getGerencias(){
		List<BDepartamento> listDepas = new ArrayList<BDepartamento>();
		try{
			dao.connect();
			ResultSet rs = dao.getQuery("select * from Tb_Gerencia");
			while(rs.next()){
				BDepartamento objNuevo = new BDepartamento();
				objNuevo.setCodigo(rs.getInt(1));
				objNuevo.setDescripcion(rs.getString(2));
				listDepas.add(objNuevo);
			}
		}catch(SQLException ex){
			System.out.println("Fail : " + ex.getMessage());
		}
		return new Gson().toJson(listDepas);		
	}
	
	@GET
	@Path("/getnombre/{nombre}")	
	@Produces(MediaType.TEXT_PLAIN)
	public String getMensaje(@PathParam("nombre")String nombre){
		return "ponte a trabajar " + nombre;
	}
	
}