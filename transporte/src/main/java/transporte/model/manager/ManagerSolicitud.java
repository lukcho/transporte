package transporte.model.manager;

import transporte.model.dao.entities.*;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class ManagerSolicitud {

	@EJB
	private ManagerDAO mDAO;

	@EJB
	private ManagerGestion mGes;
	
	@EJB
	private ManagerCatalogos mCat;

	private static TransConductore trans_con;
	private static TransLugare trans_lugori;
	private static TransLugare trans_lugdes;
	private static TransVehiculo trans_vehi;
//	private static TransFuncionarioConductor trans_fco;

	private Timestamp fecha_creacion;

	String h = "";

	public ManagerSolicitud() {
	}

	// Solicitud

	/**
	 * buscar todas las solicitudes
	 * 
	 * @throws Exception
	 */

	@SuppressWarnings("unchecked")
	public List<TransSolicitud> findsol() {
		return mDAO.findWhere(TransSolicitud.class, " 1=1 ", null);
	}

	/**
	 * buscar todas las solicitudes
	 * 
	 * @throws Exception
	 */

	@SuppressWarnings("unchecked")
	public List<TransSolicitud> findsolreporte() {
		return mDAO.findWhere(TransSolicitud.class, "o.solEstado not like 'P' ", null);
	}

	
	/**
	 * listar todas las solicitudes
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<TransSolicitud> findAllSolicitudes() {
		return mDAO.findAll(TransSolicitud.class);
	}

	/**
	 * listar todos las solicitudes ordenados por fecha y estado
	 * 
	 * @param prod_id
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<TransSolicitud> findAllSolicitudesOrdenadosaaprorecha() {
		return mDAO.findWhere(TransSolicitud.class,
				" o.solEstado not like 'P' ",
				" o.solFecha desc, o.solHoraInicio desc ");
	}

	/**
	 * listar todos las solicitudes ordenados pendientes por fecha y estado
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<TransSolicitud> findAllSolicitudesOrdenadosapend() {
		return mDAO.findWhere(TransSolicitud.class, " o.solEstado = 'P'",
				" o.solFecha desc, o.solHoraInicio desc ");
	}

	/**
	 * listar todos las solicitudes ordenados por fecha y estado
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<TransSolicitud> findAllSolicitudesOrdenadosapendiente() {
		return mDAO.findWhere(TransSolicitud.class, " o.solEstado = 'P'",
				" o.solFecha desc , o.solHoraInicio desc");
	}

	/**
	 * listar todos las solicitudes ordenadas
	 * 
	 * @param sol_idsolicitante
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<TransSolicitud> findAllSolicitudesOrdenados(
			String sol_idsolicitante) {
		return mDAO.findWhere(TransSolicitud.class, " o.solIdSolicitante = '"
				+ sol_idsolicitante + "' ",
				" o.solFecha desc , o.solHoraInicio desc ");
	}

	/**
	 * listar todos los vehiculos con solicitud
	 * 
	 * @param placa
	 * @param fecha
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<TransSolicitud> findAllVehiculosOcu(String placa,
			Timestamp fecha) {
		return mDAO.findWhere(TransSolicitud.class,
				"o.transVehiculo.vehiIdplaca = '" + placa
						+ "' and o.solFecha = '" + fecha + "' ",
				" o.solFecha desc , o.solHoraInicio desc");
	}

	/**
	 * listar todos los vehiculos conductores
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<TransSolicitud> findAllVehiculoConductor() {
		return mDAO.findAll(TransSolicitud.class);
	}

	/**
	 * listar todos los vehiculos en ordenados
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<TransSolicitud> findAllVehiculosDisponibles() {
		return mDAO.findWhere(TransSolicitud.class, "1=1",
				"o.solFecha desc, o.solHoraInicio desc");
	}

	/**
	 * buscar los vehuculos por ID
	 * 
	 * @param vehi_id
	 * @throws Exception
	 */
	public TransSolicitud solicitudByID(Integer vehi_id) throws Exception {
		return (TransSolicitud) mDAO.findById(TransSolicitud.class, vehi_id);
	}

	/**
	 * listar todos los vehiculos con la fecha
	 * 
	 * @param placa
	 * @param fechai
	 * @param fechaf
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<TransSolicitud> findAllVehiculosfechacond(String placa,
			Timestamp fechai, Timestamp fechaf) {
		return mDAO.findWhere(TransSolicitud.class,
				"o.transVehiculo.vehiIdplaca = '" + placa
						+ "' and  o.solFecha between '" + fechai + "' and  '"
						+ fechaf + "'",
				" o.solFecha desc , o.solHoraInicio desc");
	}

	/**
	 * listar todos los vehiculos con en la fecha
	 * 
	 * @param fechai
	 * @param fechaf
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<TransSolicitud> findAllVehiculosfecha(Timestamp fechai,
			Timestamp fechaf) {
		return mDAO.findWhere(TransSolicitud.class, "  o.solFecha between '"
				+ fechai + "' and  '" + fechaf + "'",
				" o.solFecha desc , o.solHoraInicio desc");
	}
	
	/**
	 * listar todos los vehiculos con en la fecha
	 * 
	 * @param fechai
	 * @param fechaf
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<TransSolicitud> findAllNovedades() {
		return mDAO.findWhere(TransSolicitud.class, "  o.solNovedades not like '' ", " o.solFecha desc" );
	}

	/**
	 * Agrega solicitudes
	 * 
	 * @param sol_fecha
	 * @param usuario_cedula
	 * @param usuario_nombre
	 * @param sol_pasajeros
	 * @param sol_motivo
	 * @param sol_hora_inicio
	 * @param sol_hora_fin
	 * @param sol_flexibilidad
	 * @param sol_fcoid
	 * @param sol_regresorigen
	 * @throws Exception
	 */
	public void insertarSolicitud(Timestamp sol_fecha, String usuario_cedula,String usuario_nombre,
			Integer sol_pasajeros, String sol_motivo, Time sol_hora_inicio,
			Time sol_hora_fin, boolean sol_flexibilidad, String sol_fcoid, boolean sol_regresorigen,String sol_tipovehiculo) throws Exception {
		TransSolicitud sol = new TransSolicitud();
		sol.setSolIdSolicitante(usuario_cedula);
		sol.setSolNomSolicitante(usuario_nombre);
		cargafecha();
		sol.setTransLugare2(trans_lugori);
		sol.setTransLugare1(trans_lugdes);
//		if (!sol_fcoid.equals("Ninguno"))
//			sol.setTransFuncionarioConductor(mGes.conductorfunByID(sol_fcoid));
		sol.setTransVehiculo(trans_vehi);
		sol.setTransConductore(trans_con);
		sol.setSolFechaCreacion(fecha_creacion);
		sol.setSolFecha(sol_fecha);
		sol.setSolPasajeros(sol_pasajeros);
		sol.setSolTipovehiculo(sol_tipovehiculo);
		sol.setSolMotivo(sol_motivo);
		sol.setSolHoraInicio(sol_hora_inicio);
		sol.setSolHoraFin(sol_hora_fin);
		sol.setSolFlexibilidad(sol_flexibilidad);
		sol.setSolEstado("P");
		sol.setSolRegresorigen(sol_regresorigen);
		mDAO.insertar(sol);
	}

	/**
	 * Cargar datos fecha
	 * 
	 * @throws Exception
	 */
	public void cargafecha() {
		Calendar calendar = Calendar.getInstance();
		java.sql.Timestamp ourJavaTimestampObject = new java.sql.Timestamp(
				calendar.getTime().getTime());
		fecha_creacion = ourJavaTimestampObject;
	}

	/**
	 * Cambiar datos de solicitudes
	 * 
	 * @param sol_fecha
	 * @param usuario_cedula
	 * @param usuario_nombre
	 * @param sol_pasajeros
	 * @param sol_motivo
	 * @param sol_hora_inicio
	 * @param sol_hora_fin
	 * @param sol_flexibilidad
	 * @param sol_fcoid
	 * @param sol_regresorigen
	 * @throws Exception
	 */
	public void editarSolicitud(Integer sol_id, Timestamp sol_fecha,
			Integer sol_pasajeros, String sol_motivo, Time sol_hora_inicio,
			Time sol_hora_fin, boolean sol_flexibilidad,
			String sol_observacion, String sol_estado, String sol_fcoid,
			String sol_cond, boolean sol_regresorigen,Timestamp sol_fecha_aprobacion, String[] listado)
			throws Exception {
		TransSolicitud sol = this.solicitudByID(sol_id);
		sol.setTransLugare2(trans_lugori);
		sol.setTransLugare1(trans_lugdes);
//		if (!sol_fcoid.equals("Ninguno"))
//			sol.setTransFuncionarioConductor(mGes.conductorfunByID(sol_fcoid));
//		else
			sol.setTransFuncionarioConductor(null);
		if (!sol_cond.equals("Ninguno"))
			sol.setTransConductore(mGes.conductorByID(sol_cond));
		sol.setTransVehiculo(trans_vehi);
		sol.setSolFecha(sol_fecha);
		sol.setSolPasajeros(sol_pasajeros);
		sol.setSolMotivo(sol_motivo);
		sol.setSolFechaAprobacion(sol_fecha_aprobacion);
		sol.setSolHoraInicio(sol_hora_inicio);
		sol.setSolHoraFin(sol_hora_fin);
		sol.setSolFlexibilidad(sol_flexibilidad);
		sol.setSolObservacion(sol_observacion);
		sol.setSolEstado(sol_estado);
		sol.setSolRegresorigen(sol_regresorigen);
		String novedades="";
		
		System.out.println(listado.length);
		for (String i : listado) {
			novedades+=i+", ";
			System.out.println(novedades);
		}
		sol.setSolNovedades(novedades);
		System.out.println(novedades);
		mDAO.actualizar(sol);
	}
	
	/**
	 * Cambiar datos de solicitudes
	 * 
	 * @param sol_fecha
	 * @param usuario_cedula
	 * @param usuario_nombre
	 * @param sol_pasajeros
	 * @param sol_motivo
	 * @param sol_hora_inicio
	 * @param sol_hora_fin
	 * @param sol_flexibilidad
	 * @param sol_fcoid
	 * @param sol_regresorigen
	 * @throws Exception
	 */
	public void editarSolicitudsn(Integer sol_id, Timestamp sol_fecha,
			Integer sol_pasajeros, String sol_motivo, Time sol_hora_inicio,
			Time sol_hora_fin, boolean sol_flexibilidad,
			String sol_observacion, String sol_estado, String sol_fcoid,
			String sol_cond, boolean sol_regresorigen)
			throws Exception {
		TransSolicitud sol = this.solicitudByID(sol_id);
		sol.setTransLugare2(trans_lugori);
		sol.setTransLugare1(trans_lugdes);
//		if (!sol_fcoid.equals("Ninguno"))
//			sol.setTransFuncionarioConductor(mGes.conductorfunByID(sol_fcoid));
//		else
//			sol.setTransFuncionarioConductor(null);
		if (!sol_cond.equals("Ninguno"))
			sol.setTransConductore(mGes.conductorByID(sol_cond));
		sol.setTransVehiculo(trans_vehi);
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
		sol.setSolRegresorigen(sol_regresorigen);
		mDAO.actualizar(sol);
	}

	/**
	 * Cambiar estado solicitudes
	 * 
	 * @param sol_id
	 * @throws Exception
	 */
	public String cambioEstadoSolicitud(Integer sol_id) throws Exception {
		String h = "";
		TransSolicitud sol = solicitudByID(sol_id);

		if (sol.getSolEstado().equals("P")) {
			sol.setSolEstado("N");
			h = "Estado Modificado";
		} else if (sol.getSolEstado().equals("A")) {
			sol.setSolEstado("N");
			h = "Estado Modificado";
		} else if (sol.getSolEstado().equals("R")) {
			h = "No puede ser anulada la solicitud, se encuentra rechazada";
		} else if (sol.getSolEstado().equals("N")) {
			h = "Ya está anulada la solicitud";

		} else {
			h = "No puede ser anulada la solicitud";
		}
		mDAO.actualizar(sol);
		return h;
	}

	/**
	 * Verifica si el solicitud esta activado
	 * 
	 * @param u
	 *            solicitud a analizar
	 * @return true o false
	 */
	public boolean esSolicitudActivo(TransSolicitud u) {
		boolean resp = false;
		if (u.getSolEstado().equals("A")) {
			resp = true;
		}
		return resp;
	}

	// asignaciones

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

//	/**
//	 * metodo para asignar el conductorfunaiconario
//	 * 
//	 * @param u
//	 *            conductorfunionario a analizar
//	 * @return true o false
//	 */
//	public TransFuncionarioConductor asignarConductorfuncionario(
//			String confun_id) {
//		try {
//			if (!confun_id.isEmpty())
//				trans_fco = mGes.conductorfunByID(confun_id);
//			else
//				trans_fco = null;
//		} catch (Exception e) {
//			// TODO Auto-generated prodch block
//			e.printStackTrace();
//		}
//		return trans_fco;
//	}

	/**
	 * listar todos los conductores funcionarios
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<TransFuncionarioConductor> findAllConductFuncionarios() {
		return mDAO.findAll(TransFuncionarioConductor.class);
	}
}
