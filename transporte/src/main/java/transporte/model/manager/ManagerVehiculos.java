package transporte.model.manager;

import transporte.model.dao.entities.*;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class ManagerVehiculos{

	@EJB
	private ManagerDAO mDAO;

	String h="";		
		
	public ManagerVehiculos() {
	}
	
	// Vehiculo
	
	/**
	 * buscar todos vehiculos
	 * @throws Exception
	 */	
	
	@SuppressWarnings("unchecked")
	public List<TransVehiculo> findprod() {
		return mDAO.findWhere(TransVehiculo.class, "1=1", null);
	}

	/**
	 * listar todos los vehiculos
	 * @param prod_id
	 * @throws Exception
	 */	
	@SuppressWarnings("unchecked") 
	public List<TransVehiculo> findAllVehiculos() {
		return mDAO.findAll(TransVehiculo.class);
	}

	/**
	 * buscar vehiculos por ID
	 * @param prod_id
	 * @throws Exception
	 */
	public TransVehiculo vehiculoByID(String vehi_id) throws Exception {
		return (TransVehiculo) mDAO.findById(TransVehiculo.class, vehi_id);
	}
	
	/**
	 * Agrega vehiculo
	 * @param pro_id
	 * @param prodfoto_id
	 * @param pro_nombre
	 * @param pro_descripcion
	 * @param pro_costo
	 * @param pro_precio
	 * @param pro_stock
	 * @param pro_estado
	 * @param pro_estado_fun
	 * @throws Exception
	 */
	public void insertarVehiculo(String vehi_id,String vehi_nombre, String vehi_marca, String vehi_modelo,String vehi_tipo ,Integer capacidad) throws Exception {
		TransVehiculo vehi = new TransVehiculo();
		vehi.setVehiIdplaca(vehi_id);
		vehi.setVehiNombre(vehi_nombre);
		vehi.setVehiMarca(vehi_marca);
		vehi.setVehiModelo(vehi_modelo);
		vehi.setVehiTipo(vehi_tipo);
		vehi.setVehiCapacidad(capacidad);
		vehi.setVehiEstado("A");
		vehi.setVehiEstadoFuncional("A");
		mDAO.insertar(vehi);		
	}

	/**
	 * Cambiar datos de vehiculo
	 * @param pro_id
	 * @param prodfoto_id
	 * @param pro_nombre
	 * @param pro_descripcion
	 * @param pro_costo
	 * @param pro_precio
	 * @param pro_stock
	 * @param pro_estado
	 * @param pro_estado_fun
	 * @throws Exception
	 */	
	public void editarVehiculo(String vehi_id,String vehi_nombre, String vehi_marca, String vehi_modelo,String vehi_tipo ,Integer capacidad,String vehi_estado, String vehi_estado_funcional) throws Exception {
		TransVehiculo vehi =  this.vehiculoByID(vehi_id);
//		vehi.setVehiIdplaca(vehi_id);
		vehi.setVehiNombre(vehi_nombre);
		vehi.setVehiMarca(vehi_marca);
		vehi.setVehiModelo(vehi_modelo);
		vehi.setVehiTipo(vehi_tipo);
		vehi.setVehiCapacidad(capacidad);
		vehi.setVehiEstado(vehi_estado);
		vehi.setVehiEstadoFuncional(vehi_estado_funcional);
		mDAO.actualizar(vehi);	
	}
	
	/**
	 * Cambiar estado vehiculo
	 * @param id_prod
	 * @param nombre
	 * @param apellido
	 * @param correo
	 * @throws Exception
	 */	
	public String cambioEstadoVerhiculo(String vehi_id) throws Exception{
		String h="";
		TransVehiculo vehi = vehiculoByID(vehi_id);						
		
		if(vehi.getVehiEstado().equals("A")){
			vehi.setVehiEstado("I");
			h="Estado Modificado";
			}
		else if(vehi.getVehiEstado().equals("I")){
			vehi.setVehiEstado("A");
			h="Estado Modificado";
			}
		mDAO.actualizar(vehi);
		return h;
		}		
	
	/**
	 * Verifica si el vehiculo esta activado
	 * @param u vehiculo a analizar
	 * @return true o false
	 */
	public boolean esvehiActivo(TransVehiculo u){
		boolean  resp = false;
		if(u.getVehiEstado().equals("A")){
			resp = true;
		}
		return resp;
	}
	
	//CONDUCTORES
	/**
	 * listar todos los conductores
	 * @param prod_id
	 * @throws Exception
	 */	
	@SuppressWarnings("unchecked") 
	public List<TransConductore> findAllConductores() {
		return mDAO.findAll(TransConductore.class);
	}

	/**
	 * buscar conductores por ID
	 * @param prod_id
	 * @throws Exception
	 */
	public TransConductore conductorByID(String con_id) throws Exception {
		return (TransConductore) mDAO.findById(TransConductore.class, con_id);
	}
	
	/**
	 * Agrega conductores
	 * @param pro_id
	 * @param prodfoto_id
	 * @param pro_nombre
	 * @param pro_descripcion
	 * @param pro_costo
	 * @param pro_precio
	 * @param pro_stock
	 * @param pro_estado
	 * @param pro_estado_fun
	 * @throws Exception
	 */
	public void insertarConductor(String con_cedid,String con_nombre, String con_apellido, String con_telefono) throws Exception {
		TransConductore con = new TransConductore();
		con.setCondCedula(con_cedid);
		con.setCondNombre(con_nombre);
		con.setCondApellido(con_apellido);
		con.setCondTelefono(con_telefono);
		con.setCondEstado("A");
		mDAO.insertar(con);		
	}

	/**
	 * Cambiar datos de conductores
	 * @param pro_id
	 * @param prodfoto_id
	 * @param pro_nombre
	 * @param pro_descripcion
	 * @param pro_costo
	 * @param pro_precio
	 * @param pro_stock
	 * @param pro_estado
	 * @param pro_estado_fun
	 * @throws Exception
	 */	
	public void editarConductor(String con_cedid,String con_nombre, String con_apellido, String con_telefono,String con_estado) throws Exception {
		TransConductore con =  this.conductorByID(con_cedid);
//		con.setCondCedula(con_cedid);
		con.setCondNombre(con_nombre);
		con.setCondApellido(con_apellido);
		con.setCondTelefono(con_telefono);
		con.setCondEstado(con_estado);
		mDAO.actualizar(con);	
	}
	
	/**
	 * Cambiar estado conductores
	 * @param id_prod
	 * @param nombre
	 * @param apellido
	 * @param correo
	 * @throws Exception
	 */	
	public String cambioEstadoConductor(String con_id) throws Exception{
		String h="";
		TransConductore vehi = conductorByID(con_id);						
		
		if(vehi.getCondEstado().equals("A")){
			vehi.setCondEstado("I");
			h="Estado Modificado";
			}
		else if(vehi.getCondEstado().equals("I")){
			vehi.setCondEstado("A");
			h="Estado Modificado";
			}
		mDAO.actualizar(vehi);
		return h;
		}		
	
	/**
	 * Verifica si el conductores esta activado
	 * @param u conductores a analizar
	 * @return true o false
	 */
	public boolean esConActivo(TransConductore u){
		boolean  resp = false;
		if(u.getCondEstado().equals("A")){
			resp = true;
		}
		return resp;
	}
	
	//LUGARES
	/**
	 * listar todos los Lugares
	 * @param prod_id
	 * @throws Exception
	 */	
	@SuppressWarnings("unchecked") 
	public List<TransLugare> findAllLugares() {
		return mDAO.findAll(TransLugare.class);
	}

	/**
	 * buscar conductores por ID
	 * @param prod_id
	 * @throws Exception
	 */
	public TransLugare LugarByID(String lug_id) throws Exception {
		return (TransLugare) mDAO.findById(TransLugare.class, lug_id);
	}
	
	/**
	 * Agrega conductores
	 * @param pro_id
	 * @param prodfoto_id
	 * @param pro_nombre
	 * @param pro_descripcion
	 * @param pro_costo
	 * @param pro_precio
	 * @param pro_stock
	 * @param pro_estado
	 * @param pro_estado_fun
	 * @throws Exception
	 */
	public void insertarLugar(String lug_nombre, String lug_ciudad) throws Exception {
		TransLugare lug = new TransLugare();
		lug.setLugNombre(lug_nombre);
		lug.setLugCiudad(lug_ciudad);
		lug.setLugEstado("A");
		mDAO.insertar(lug);		
	}

	/**
	 * Cambiar datos de conductores
	 * @param pro_id
	 * @param prodfoto_id
	 * @param pro_nombre
	 * @param pro_descripcion
	 * @param pro_costo
	 * @param pro_precio
	 * @param pro_stock
	 * @param pro_estado
	 * @param pro_estado_fun
	 * @throws Exception
	 */	
	public void editarLugar(String lug_id,String lug_nombre, String lug_ciudad,String lug_estado) throws Exception {
		TransLugare lug =  this.LugarByID(lug_id);
//		con.setCondCedula(con_cedid);
		lug.setLugNombre(lug_nombre);
		lug.setLugCiudad(lug_ciudad);
		lug.setLugEstado(lug_estado);
		mDAO.actualizar(lug);	
	}
	
	/**
	 * Cambiar estado conductores
	 * @param id_prod
	 * @param nombre
	 * @param apellido
	 * @param correo
	 * @throws Exception
	 */	
	public String cambioEstadoLugar(String lug_id) throws Exception{
		String h="";
		TransLugare vehi = LugarByID(lug_id);						
		
		if(vehi.getLugEstado().equals("A")){
			vehi.setLugEstado("I");
			h="Estado Modificado";
			}
		else if(vehi.getLugEstado().equals("I")){
			vehi.setLugEstado("A");
			h="Estado Modificado";
			}
		mDAO.actualizar(vehi);
		return h;
		}		
	
	/**
	 * Verifica si el conductores esta activado
	 * @param u conductores a analizar
	 * @return true o false
	 */
	public boolean esLugActivo(TransLugare u){
		boolean  resp = false;
		if(u.getLugEstado().equals("A")){
			resp = true;
		}
		return resp;
	}
	
	

}
