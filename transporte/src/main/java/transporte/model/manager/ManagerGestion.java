package transporte.model.manager;

import transporte.model.dao.entities.*;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class ManagerGestion {

	@EJB
	private ManagerDAO mDAO;

	String h = "";

	public ManagerGestion() {
	}

	// Vehiculo

	/**
	 * buscar todos vehiculos
	 * 
	 * @throws Exception
	 */

	@SuppressWarnings("unchecked")
	public List<TransVehiculo> findprod() {
		return mDAO.findWhere(TransVehiculo.class, "1=1", null);
	}

	/**
	 * listar todos los vehiculos
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<TransVehiculo> findAllVehiculos() {
		return mDAO.findAll(TransVehiculo.class);
	}

	/**
	 * buscar vehiculos por ID
	 * 
	 * @param vehi_id
	 * @throws Exception
	 */
	public TransVehiculo vehiculoByID(String vehi_id) throws Exception {
		return (TransVehiculo) mDAO.findById(TransVehiculo.class, vehi_id);
	}

	/**
	 * Agrega vehiculo
	 * 
	 * @param vehi_id
	 * @param vehi_nombre
	 * @param vehi_marca
	 * @param vehi_modelo
	 * @param vehi_tipo
	 * @param capacidad
	 * @throws Exception
	 */
	public void insertarVehiculo(String vehi_id, String vehi_nombre,
			String vehi_marca, String vehi_modelo, String vehi_tipo,
			Integer capacidad) throws Exception {
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
	 * 
	 * @param vehi_id
	 * @param vehi_nombre
	 * @param vehi_marca
	 * @param vehi_modelo
	 * @param vehi_tipo
	 * @param capacidad
	 * @throws Exception
	 */
	public void editarVehiculo(String vehi_id, String vehi_nombre,
			String vehi_marca, String vehi_modelo, String vehi_tipo,
			Integer capacidad, String vehi_estado, String vehi_estado_funcional)
			throws Exception {
		TransVehiculo vehi = this.vehiculoByID(vehi_id);
		// vehi.setVehiIdplaca(vehi_id);
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
	 * 
	 * @param vehi_id
	 * @throws Exception
	 */
	public String cambioEstadoVerhiculo(String vehi_id) throws Exception {
		String h = "";
		TransVehiculo vehi = vehiculoByID(vehi_id);

		if (vehi.getVehiEstado().equals("A")) {
			vehi.setVehiEstado("I");
			h = "Estado Modificado";
		} else if (vehi.getVehiEstado().equals("I")) {
			vehi.setVehiEstado("A");
			h = "Estado Modificado";
		}
		mDAO.actualizar(vehi);
		return h;
	}

	/**
	 * Verifica si el vehiculo esta activado
	 * 
	 * @param u
	 *            vehiculo a analizar
	 * @return true o false
	 */
	public boolean esvehiActivo(TransVehiculo u) {
		boolean resp = false;
		if (u.getVehiEstado().equals("A")) {
			resp = true;
		}
		return resp;
	}

	// CONDUCTORES
	/**
	 * listar todos los conductores
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<TransConductore> findAllConductores() {
		return mDAO.findAll(TransConductore.class);
	}

	/**
	 * buscar conductores por ID
	 * 
	 * @param con_id
	 * @throws Exception
	 */
	public TransConductore conductorByID(String con_id) throws Exception {
		return (TransConductore) mDAO.findById(TransConductore.class, con_id);
	}

	/**
	 * Agrega conductor
	 * 
	 * @param con_cedid
	 * @param con_nombre
	 * @param con_apellido
	 * @param con_telefono
	 * @param con_correo
	 * @throws Exception
	 */
	public void insertarConductor(String con_cedid, String con_nombre,
			String con_apellido, String con_telefono, String con_correo)
			throws Exception {
		TransConductore con = new TransConductore();
		con.setCondCedula(con_cedid);
		con.setCondNombre(con_nombre);
		con.setCondApellido(con_apellido);
		con.setCondTelefono(con_telefono);
		con.setCondCorreo(con_correo);
		con.setCondEstado("A");
		mDAO.insertar(con);
	}

	/**
	 * Cambiar datos de conductores
	 * 
	 * @param con_cedid
	 * @param con_nombre
	 * @param con_apellido
	 * @param con_telefono
	 * @param con_correo
	 * @throws Exception
	 */
	public void editarConductor(String con_cedid, String con_nombre,
			String con_apellido, String con_telefono, String con_correo,
			String con_estado) throws Exception {
		TransConductore con = this.conductorByID(con_cedid);
		// con.setCondCedula(con_cedid);
		con.setCondNombre(con_nombre);
		con.setCondApellido(con_apellido);
		con.setCondTelefono(con_telefono);
		con.setCondCorreo(con_correo);
		con.setCondEstado(con_estado);
		mDAO.actualizar(con);
	}

	/**
	 * Cambiar estado conductores
	 * 
	 * @param id_prod
	 * @throws Exception
	 */
	public String cambioEstadoConductor(String con_id) throws Exception {
		String h = "";
		TransConductore con = conductorByID(con_id);

		if (con.getCondEstado().equals("A")) {
			con.setCondEstado("I");
			h = "Estado Modificado";
		} else if (con.getCondEstado().equals("I")) {
			con.setCondEstado("A");
			h = "Estado Modificado";
		}
		mDAO.actualizar(con);
		return h;
	}

	/**
	 * Verifica si el conductores esta activado
	 * 
	 * @param u
	 *            conductores a analizar
	 * @return true o false
	 */
	public boolean esConActivo(TransConductore u) {
		boolean resp = false;
		if (u.getCondEstado().equals("A")) {
			resp = true;
		}
		return resp;
	}

	// //CONDUCTORES FUNCIONARIOS
	// /**
	// * listar todos los conductores funcionarios
	// *
	// * @throws Exception
	// */
	// @SuppressWarnings("unchecked")
	// public List<TransFuncionarioConductor> findAllConductoresFuncionarios() {
	// return mDAO.findAll(TransFuncionarioConductor.class);
	// }
	//
	// /**
	// * buscar conductores funcionarios por ID
	// *
	// * @param conf_id
	// * @throws Exception
	// */
	// public TransFuncionarioConductor conductorfunByID(String conf_id) throws
	// Exception {
	// if(conf_id == "Ninguno"){
	//
	// }
	// else{
	// }
	// return (TransFuncionarioConductor)
	// mDAO.findById(TransFuncionarioConductor.class, conf_id);
	// }
	//
	// /**
	// * Agrega conductores funcionarios
	// *
	// * @param per
	// * @throws Exception
	// */
	// public void insertarConductorFun(PersonaFuncionario per) throws Exception
	// {
	// TransFuncionarioConductor conf = new TransFuncionarioConductor();
	// conf.setFcoId(per.getPerDNI());
	// conf.setFcoNombres(per.getPerNombres()+" "+per.getPerApellidos());
	// conf.setFcoGerencia(per.getPerGerencia());
	// conf.setFcoDireccion(per.getPerDireccion());
	// conf.setFcoTelefono(per.getPerTelefono());
	// conf.setFcoCorreo(per.getPerCorreo());
	// conf.setFcoEstado("A");
	// mDAO.insertar(conf);
	// }
	//
	// /**
	// * Cambiar datos de conductores funcionarios
	// *
	// * @param per
	// * @throws Exception
	// */
	// public void editarConductorFun(String cedula,String correo, String
	// telefono, String Estado) throws Exception {
	// TransFuncionarioConductor conf = this.conductorfunByID(cedula);
	// conf.setFcoCorreo(correo);
	// conf.setFcoTelefono(telefono);
	// conf.setFcoEstado(Estado);
	// mDAO.actualizar(conf);
	// }
	//
	// /**
	// * Cambiar estado conductores funcionarios
	// *
	// * @param conf_id
	// * @throws Exception
	// */
	// public String cambioEstadoConductorFun(String conf_id) throws Exception{
	// String h="";
	// TransFuncionarioConductor conf = conductorfunByID(conf_id);
	//
	// if(conf.getFcoEstado().equals("A")){
	// conf.setFcoEstado("I");
	// h="Estado Modificado";
	// }
	// else if(conf.getFcoEstado().equals("I")){
	// conf.setFcoEstado("A");
	// h="Estado Modificado";
	// }
	// mDAO.actualizar(conf);
	// return h;
	// }
	//
	// /**
	// * Verifica si el conductores esta activado
	// *
	// * @param u conductor funcionario a analizar
	// * @return true o false
	// */
	// public boolean esConFunActivo(TransFuncionarioConductor u){
	// boolean resp = false;
	// if(u.getFcoEstado().equals("A")){
	// resp = true;
	// }
	// return resp;
	// }
	//
	// LUGARES
	/**
	 * listar todos los Lugares
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<TransLugare> findAllLugares() {
		return mDAO.findAll(TransLugare.class);
	}

	/**
	 * buscar conductores por ID
	 * 
	 * @param lug_id
	 * @throws Exception
	 */
	public TransLugare LugarByID(Integer lug_id) throws Exception {
		return (TransLugare) mDAO.findById(TransLugare.class, lug_id);
	}

	/**
	 * Agrega lugar
	 * 
	 * @param lug_nombre
	 * @param lug_ciudad
	 * @throws Exception
	 */
	public void insertarLugar(String lug_nombre, String lug_ciudad)
			throws Exception {
		TransLugare lug = new TransLugare();
		lug.setLugNombre(lug_nombre);
		lug.setLugCiudad(lug_ciudad);
		lug.setLugEstado("A");
		mDAO.insertar(lug);
	}

	/**
	 * Cambiar datos de lugar
	 * 
	 * @param lug_id
	 * @param lug_nombre
	 * @param lug_ciudad
	 * @param lug_estado
	 * @throws Exception
	 */
	public void editarLugar(Integer lug_id, String lug_nombre,
			String lug_ciudad, String lug_estado) throws Exception {
		TransLugare lug = this.LugarByID(lug_id);
		lug.setLugNombre(lug_nombre);
		lug.setLugCiudad(lug_ciudad);
		lug.setLugEstado(lug_estado);
		mDAO.actualizar(lug);
	}

	/**
	 * Cambiar estado lugar
	 * 
	 * @param lug_id
	 * @throws Exception
	 */
	public String cambioEstadoLugar(Integer lug_id) throws Exception {
		String h = "";
		TransLugare vehi = LugarByID(lug_id);

		if (vehi.getLugEstado().equals("A")) {
			vehi.setLugEstado("I");
			h = "Estado Modificado";
		} else if (vehi.getLugEstado().equals("I")) {
			vehi.setLugEstado("A");
			h = "Estado Modificado";
		}
		mDAO.actualizar(vehi);
		return h;
	}

	/**
	 * Verifica si el lugar esta activado
	 * 
	 * @param u
	 *            lugar a analizar
	 * @return true o false
	 */
	public boolean esLugActivo(TransLugare u) {
		boolean resp = false;
		if (u.getLugEstado().equals("A")) {
			resp = true;
		}
		return resp;
	}
}
