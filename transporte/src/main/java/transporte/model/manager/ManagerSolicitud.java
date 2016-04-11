package transporte.model.manager;

import transporte.model.dao.entities.*;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class ManagerSolicitud{

	@EJB
	private ManagerDAO mDAO;
	@EJB
	private ManagerGestion mGes;
	
	private static TransConductore trans_con;
	private static TransLugare trans_lugori;
	private static TransLugare trans_lugdes;
	private static TransVehiculo trans_vehi;
	private static TransFuncionarioConductor trans_fco;

	String h="";		
		
	public ManagerSolicitud() {
	}
	
	// Solicitud
	
	/**
	 * buscar todos solicitudes
	 * @throws Exception
	 */	
	
	@SuppressWarnings("unchecked")
	public List<TranSolicitud> findsol() {
		return mDAO.findWhere(TranSolicitud.class, "1=1", null);
	}

	/**
	 * listar todos los solicitudes
	 * @param prod_id
	 * @throws Exception
	 */	
	@SuppressWarnings("unchecked") 
	public List<TranSolicitud> findAllSolicitudes() {
		return mDAO.findAll(TranSolicitud.class);
	}

	/**
	 * buscar solicitudes por ID
	 * @param prod_id
	 * @throws Exception
	 */
	public TranSolicitud solicitudByID(Integer vehi_id) throws Exception {
		return (TranSolicitud) mDAO.findById(TranSolicitud.class, vehi_id);
	}
	
	/**
	 * Agrega solicitudes
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
	public void insertarSolicitud(Timestamp sol_fecha,Integer sol_pasajeros,String sol_motivo, Time sol_hora_inicio,Time  sol_hora_fin,String sol_flexibilidad, String sol_observacion) throws Exception {
		TranSolicitud sol = new TranSolicitud();
		//sol.setSolIdSolicitante(solIdSolicitante);
		sol.setTransLugare2(trans_lugori);
		sol.setTransLugare1(trans_lugdes);
		sol.setTransFuncionarioConductor(trans_fco);
		sol.setTransVehiculo(trans_vehi);
		sol.setTransConductore(trans_con);
		sol.setSolFecha(sol_fecha);
		sol.setSolPasajeros(sol_pasajeros);
		sol.setSolMotivo(sol_motivo);
		sol.setSolHoraInicio(sol_hora_inicio);
		sol.setSolHoraFin(sol_hora_fin);
		sol.setSolFlexibilidad(sol_flexibilidad);
		sol.setSolEstado("P");
		mDAO.insertar(sol);		
	}
	
	/**
	 * Cambiar datos de solicitudes
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
	public void editarSolicitud(Integer sol_id,Timestamp sol_fecha,Integer sol_pasajeros,String sol_motivo, Time sol_hora_inicio,Time  sol_hora_fin,String sol_flexibilidad, String sol_observacion, String sol_estado) throws Exception {
		TranSolicitud sol =  this.solicitudByID(sol_id);
		sol.setTransLugare2(trans_lugori);
		sol.setTransLugare1(trans_lugdes);
		sol.setTransFuncionarioConductor(trans_fco);
		sol.setTransVehiculo(trans_vehi);
		sol.setTransConductore(trans_con);
		sol.setSolFecha(sol_fecha);
		sol.setSolPasajeros(sol_pasajeros);
		sol.setSolMotivo(sol_motivo);
		java.util.Date actualfecha = new Date();
		Timestamp fecha = new Timestamp(actualfecha.getTime());
		sol.setSolFechaAprobacion(fecha);
		sol.setSolHoraInicio(sol_hora_inicio);
		sol.setSolHoraFin(sol_hora_fin);
		sol.setSolFlexibilidad(sol_flexibilidad);
		sol.setSolObservacion(sol_observacion);
		sol.setSolEstado(sol_estado);
		mDAO.actualizar(sol);	
	}

//	/**
//	 * Cambiar datos de solicitudes
//	 * @param pro_id
//	 * @param prodfoto_id
//	 * @param pro_nombre
//	 * @param pro_descripcion
//	 * @param pro_costo
//	 * @param pro_precio
//	 * @param pro_stock
//	 * @param pro_estado
//	 * @param pro_estado_fun
//	 * @throws Exception
//	 */	
//	public void editarSolicitudapro(Integer sol_id,Timestamp sol_fecha, Time sol_hora_inicio,Time  sol_hora_fin,String sol_flexibilidad, String sol_observacion) throws Exception {
//		TranSolicitud sol =  this.solicitudByID(sol_id);
//		sol.setTransConductore(trans_con);
//		sol.setTransVehiculo(trans_vehi);
//		sol.setTransLugare2(trans_lugori);
//		sol.setTransLugare1(trans_lugdes);
//		sol.setSolFecha(sol_fecha);
//		java.util.Date actualfecha = new Date();
//		Timestamp fecha = new Timestamp(actualfecha.getTime());
//		sol.setSolFechaAprobacion(fecha);
//		sol.setSolHoraInicio(sol_hora_inicio);
//		sol.setSolHoraFin(sol_hora_fin);
//		sol.setSolFlexibilidad(sol_flexibilidad);
//		sol.setSolObservacion(sol_observacion);
//		sol.setSolEstado("A");
//		mDAO.actualizar(sol);	
//	}
//	
//	/**
//	 * Cambiar datos de solicitudes
//	 * @param pro_id
//	 * @param prodfoto_id
//	 * @param pro_nombre
//	 * @param pro_descripcion
//	 * @param pro_costo
//	 * @param pro_precio
//	 * @param pro_stock
//	 * @param pro_estado
//	 * @param pro_estado_fun
//	 * @throws Exception
//	 */	
//	public void editarSolicitudadesa(Integer sol_id,Timestamp sol_fecha, Time sol_hora_inicio,Time  sol_hora_fin,String sol_flexibilidad, String sol_observacion, String sol_estado) throws Exception {
//		TranSolicitud sol =  this.solicitudByID(sol_id);
//		sol.setTransConductore(trans_con);
//		sol.setTransVehiculo(trans_vehi);
//		sol.setTransLugare2(trans_lugori);
//		sol.setTransLugare1(trans_lugdes);
//		//falta la capacidad
//		sol.setSolFecha(sol_fecha);
//		java.util.Date actualfecha = new Date();
//		Timestamp fecha = new Timestamp(actualfecha.getTime());
//		sol.setSolFechaAprobacion(fecha);
//		sol.setSolHoraInicio(sol_hora_inicio);
//		sol.setSolHoraFin(sol_hora_fin);
//		sol.setSolFlexibilidad(sol_flexibilidad);
//		sol.setSolObservacion(sol_observacion);
//		sol.setSolEstado("R");
//		mDAO.actualizar(sol);	
//	}
	
	/**
	 * Cambiar estado solicitudes
	 * @param id_prod
	 * @param nombre
	 * @param apellido
	 * @param correo
	 * @throws Exception
	 */	
	public String cambioEstadoSolicitud(Integer sol_id) throws Exception{
		String h="";
		TranSolicitud sol = solicitudByID(sol_id);						
		
		if(sol.getSolEstado().equals("P")){
			sol.setSolEstado("A");
			h="Estado Modificado";
			}
		else if(sol.getSolEstado().equals("R")){
			sol.setSolEstado("A");
			h="Estado Modificado";
			}
		else if(sol.getSolEstado().equals("A")){
			sol.setSolEstado("R");
			h="Estado Modificado";
			}
		mDAO.actualizar(sol);
		return h;
		}		
	
	/**
	 * Verifica si el solicitudes esta activado
	 * @param u solicitudes a analizar
	 * @return true o false
	 */
	public boolean esSolicitudActivo(TranSolicitud u){
		boolean  resp = false;
		if(u.getSolEstado().equals("A")){
			resp = true;
		}
		return resp;
	}
	
	//asignaciones
	
	/**
	 * metodo para asignar el vehiculo
	 * 
	 * @param u
	 *            vehiculo a analizar
	 * @return true o false
	 */
	public TransVehiculo asignarvehiculo(String vehi_id) {
		try {
			trans_vehi = mGes.vehiculoByID(vehi_id);
		} catch (Exception e) {
			// TODO Auto-generated prodch block
			e.printStackTrace();
		}
		return trans_vehi;
	}
	
	/**
	 * metodo para asignar el lugarinicio
	 * 
	 * @param u
	 *            vehiculo a analizar
	 * @return true o false
	 */
	public TransLugare asignarlugarini(Integer lug_idori) {
		try {
			trans_lugori = mGes.LugarByID(lug_idori);
		} catch (Exception e) {
			// TODO Auto-generated prodch block
			e.printStackTrace();
		}
		return trans_lugori;
	}
	
	/**
	 * metodo para asignar el lugarfin
	 * 
	 * @param u
	 *            vehiculo a analizar
	 * @return true o false
	 */
	public TransLugare asignarlugarfin(Integer lug_iddes) {
		try {
			trans_lugdes = mGes.LugarByID(lug_iddes);
		} catch (Exception e) {
			// TODO Auto-generated prodch block
			e.printStackTrace();
		}
		return trans_lugdes;
	}
	
	/**
	 * metodo para asignar el conductor
	 * 
	 * @param u
	 *            conductor a analizar
	 * @return true o false
	 */
	public TransConductore asignarConductor(String con_id) {
		try {
			trans_con = mGes.conductorByID(con_id);
		} catch (Exception e) {
			// TODO Auto-generated prodch block
			e.printStackTrace();
		}
		return trans_con;
	}
	
}
