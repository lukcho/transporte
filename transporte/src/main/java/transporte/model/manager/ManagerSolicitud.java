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
	private static TransLugare trans_lugini;
	private static TransLugare trans_lugfin;
	private static TransVehiculo trans_vehi;

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
	public void insertarSolicitud(Timestamp sol_fecha, Time sol_hora_inicio,Time  sol_hora_fin,String sol_flexibilidad, String sol_observacion) throws Exception {
		TranSolicitud sol = new TranSolicitud();
		sol.setTransConductore(trans_con);
		sol.setTransVehiculo(trans_vehi);
		sol.setTransLugare2(trans_lugini);
		sol.setTransLugare1(trans_lugfin);
		sol.setSolFecha(sol_fecha);
		sol.setSolHoraInicio(sol_hora_inicio);
		sol.setSolHoraFin(sol_hora_fin);
		sol.setSolFlexibilidad(sol_flexibilidad);
		sol.setSolObservacion(sol_observacion);
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
	public void editarSolicitudapro(Integer sol_id,Timestamp sol_fecha, Time sol_hora_inicio,Time  sol_hora_fin,String sol_flexibilidad, String sol_observacion) throws Exception {
		TranSolicitud sol =  this.solicitudByID(sol_id);
		sol.setTransConductore(trans_con);
		sol.setTransVehiculo(trans_vehi);
		sol.setTransLugare2(trans_lugini);
		sol.setTransLugare1(trans_lugfin);
		sol.setSolFecha(sol_fecha);
		java.util.Date actualfecha = new Date();
		Timestamp fecha = new Timestamp(actualfecha.getTime());
		sol.setSolFechaAprobacion(fecha);
		sol.setSolHoraInicio(sol_hora_inicio);
		sol.setSolHoraFin(sol_hora_fin);
		sol.setSolFlexibilidad(sol_flexibilidad);
		sol.setSolObservacion(sol_observacion);
		sol.setSolEstado("A");
		mDAO.actualizar(sol);	
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
	public void editarSolicitudadesa(Integer sol_id,Timestamp sol_fecha, Time sol_hora_inicio,Time  sol_hora_fin,String sol_flexibilidad, String sol_observacion, String sol_estado) throws Exception {
		TranSolicitud sol =  this.solicitudByID(sol_id);
		sol.setTransConductore(trans_con);
		sol.setTransVehiculo(trans_vehi);
		sol.setTransLugare2(trans_lugini);
		sol.setTransLugare1(trans_lugfin);
		//falta la capacidad
		sol.setSolFecha(sol_fecha);
		java.util.Date actualfecha = new Date();
		Timestamp fecha = new Timestamp(actualfecha.getTime());
		sol.setSolFechaAprobacion(fecha);
		sol.setSolHoraInicio(sol_hora_inicio);
		sol.setSolHoraFin(sol_hora_fin);
		sol.setSolFlexibilidad(sol_flexibilidad);
		sol.setSolObservacion(sol_observacion);
		sol.setSolEstado("R");
		mDAO.actualizar(sol);	
	}
	
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
		
		if(sol.getSolEstado().equals("A")){
			sol.setSolEstado("I");
			h="Estado Modificado";
			}
		else if(sol.getSolEstado().equals("I")){
			sol.setSolEstado("A");
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
	public TransLugare asignarlugarini(Integer lug_idini) {
		try {
			trans_lugini = mGes.LugarByID(lug_idini);
		} catch (Exception e) {
			// TODO Auto-generated prodch block
			e.printStackTrace();
		}
		return trans_lugini;
	}
	
	/**
	 * metodo para asignar el lugarfin
	 * 
	 * @param u
	 *            vehiculo a analizar
	 * @return true o false
	 */
	public TransLugare asignarlugarfin(Integer lug_idfin) {
		try {
			trans_lugfin = mGes.LugarByID(lug_idfin);
		} catch (Exception e) {
			// TODO Auto-generated prodch block
			e.printStackTrace();
		}
		return trans_lugfin;
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
