package transporte.controller.gestion;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import org.primefaces.context.RequestContext;

import tranporte.controller.access.SesionBean;
import transporte.model.dao.entities.Novedades;
import transporte.model.dao.entities.Persona;
import transporte.model.dao.entities.TransSolicitud;
import transporte.model.dao.entities.TransConductore;
//import transporte.model.dao.entities.TransFuncionarioConductor;
import transporte.model.dao.entities.TransLugare;
import transporte.model.dao.entities.TransVehiculo;
import transporte.model.generic.Funciones;
import transporte.model.generic.Mensaje;
import transporte.model.manager.ManagerBuscar;
import transporte.model.manager.ManagerCarga;
import transporte.model.manager.ManagerGestion;
import transporte.model.manager.ManagerSolicitud;

@SessionScoped
@ManagedBean
public class solicitudaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private ManagerGestion managergest;

	@EJB
	private ManagerSolicitud managersol;

	private ManagerCarga mc;

	// SOLICITUD
	private Integer sol_id;
	private Timestamp sol_fecha;
	private Timestamp sol_fecha_aprobacion;
	private String sol_pasajeros;
	private String sol_motivo;
	private String sol_tipovehiculo;
	private String sol_hora_inicio;
	private String sol_hora_fin;
	private boolean sol_flexibilidad;
	private String sol_observacion;
	private String sol_estado;
	private String sol_estadonombre;
	private Integer sol_id_origen;
	private Integer sol_id_destino;
	private String sol_fcoid;
	private String sol_vehi;
	private String sol_conductor;
	private String sol_usuario_cedula;
	private String sol_usuario_nombre;
	private String sol_correo;
	private String sol_novedades;
	private boolean sol_regresorigen;
	private String sol_correojefeinmediato;
	private String cedula;
	// private transolicitante solicitante;
	private TransLugare lugorigen;
	private TransLugare lugdestino;
	// private TransFuncionarioConductor fco_id;
	private TransVehiculo vehi_idplaca;
	private TransConductore cond_cedula;

	private TransSolicitud soli;

	// mmostrar
	private boolean edicion;
	private boolean infomostrar;
	private boolean ediciontipo;
	private boolean guardaredicion;

	private List<TransSolicitud> listaSolicitudespend;
	private List<TransSolicitud> listaSolicitudaprorecha;
	private List<TransSolicitud> listaVehiculoCond;
	private List<TransSolicitud> listareporte;

	// fechas
	private Date date;
	private Date fecha;
	private Time horainiciotiemp;
	private Time horafintiemp;

	private Date fi;
	private Date ff;

	// flexibilidad cambio hora
	private boolean horamostrar;
	private boolean verregresorigen;
	private boolean mostrarnovedades;

	private String usuario;

	// intereses de usuario
	private String[] arrayNovedades;
	private List<Novedades> listaNovedades;

	@Inject
	SesionBean ms;

	@EJB
	private ManagerBuscar mb;

	private Persona per;
	private Persona per1;

	public solicitudaBean() {
	}

	@PostConstruct
	public void ini() {
		mc = new ManagerCarga();
		sol_conductor = "Ninguno";
		sol_vehi = "";
		sol_correo = "";
		sol_novedades = "";
		sol_correojefeinmediato = "";
		sol_estadonombre = "";
		verregresorigen = true;
		mostrarnovedades = false;
		sol_fcoid = "Ninguno";
		sol_hora_inicio = null;
		sol_hora_fin = null;
		sol_tipovehiculo = null;
		;
		sol_id = null;
		sol_estado = "P";
		sol_flexibilidad = false;
		sol_regresorigen = false;
		sol_pasajeros = null;
		edicion = false;
		infomostrar = false;
		horamostrar = false;
		ediciontipo = false;
		guardaredicion = true;
		fi = new Date();
		ff = new Date();
		listaSolicitudespend = managersol
				.findAllSolicitudesOrdenadosapendiente();
		listaSolicitudaprorecha = managersol
				.findAllSolicitudesOrdenadosaaprorecha();
		listaVehiculoCond = managersol.findAllVehiculosfechacond(sol_vehi,
				new Timestamp(fi.getTime()), new Timestamp(ff.getTime()));
		listareporte = new ArrayList<TransSolicitud>();
		listaNovedades = new ArrayList<Novedades>();
		usuario = ms.validarSesion("trans_solicitudesa.xhtml");
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getSol_correojefeinmediato() {
		return sol_correojefeinmediato;
	}

	public void setSol_correojefeinmediato(String sol_correojefeinmediato) {
		this.sol_correojefeinmediato = sol_correojefeinmediato;
	}

	public boolean isMostrarnovedades() {
		return mostrarnovedades;
	}

	public void setMostrarnovedades(boolean mostrarnovedades) {
		this.mostrarnovedades = mostrarnovedades;
	}

	public String[] getArrayNovedades() {
		return arrayNovedades;
	}

	public void setArrayNovedades(String[] arrayNovedades) {
		this.arrayNovedades = arrayNovedades;
	}

	public boolean isVerregresorigen() {
		return verregresorigen;
	}

	public void setVerregresorigen(boolean verregresorigen) {
		this.verregresorigen = verregresorigen;
	}

	public String getUsuario() {
		return usuario;
	}

	public List<TransSolicitud> getListareporte() {
		return listareporte;
	}

	public List<Novedades> getListaNovedades() {
		return listaNovedades;
	}

	public String getSol_estadonombre() {
		return sol_estadonombre;
	}

	public void setSol_estadonombre(String sol_estadonombre) {
		this.sol_estadonombre = sol_estadonombre;
	}

	public Date getFi() {
		return fi;
	}

	public void setFi(Date fi) {
		this.fi = fi;
	}

	public Date getFf() {
		return ff;
	}

	public void setFf(Date ff) {
		this.ff = ff;
	}

	public List<TransSolicitud> getListaVehiculoCond() {
		return listaVehiculoCond;
	}

	public void setListaVehiculoCond(List<TransSolicitud> listaVehiculoCond) {
		this.listaVehiculoCond = listaVehiculoCond;
	}

	public List<TransSolicitud> getListaSolicitudaprorecha() {
		return listaSolicitudaprorecha;
	}

	public void setListaSolicitudaprorecha(
			List<TransSolicitud> listaSolicitudaprorecha) {
		this.listaSolicitudaprorecha = listaSolicitudaprorecha;
	}

	public Time getHorainiciotiemp() {
		return horainiciotiemp;
	}

	public boolean isHoramostrar() {
		return horamostrar;
	}

	public void setHoramostrar(boolean horamostrar) {
		this.horamostrar = horamostrar;
	}

	public boolean isGuardaredicion() {
		return guardaredicion;
	}

	public void setGuardaredicion(boolean guardaredicion) {
		this.guardaredicion = guardaredicion;
	}

	public void setHorainiciotiemp(Time horainiciotiemp) {
		this.horainiciotiemp = horainiciotiemp;
	}

	public Time getHorafintiemp() {
		return horafintiemp;
	}

	public void setHorafintiemp(Time horafintiemp) {
		this.horafintiemp = horafintiemp;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Date getDate() {
		date = new Date();
		return date;
	}

	public TransSolicitud getSoli() {
		return soli;
	}

	public void setSoli(TransSolicitud soli) {
		this.soli = soli;
	}

	public String getSol_conductor() {
		return sol_conductor;
	}

	public void setSol_conductor(String sol_conductor) {
		this.sol_conductor = sol_conductor;
	}

	public Integer getSol_id_origen() {
		return sol_id_origen;
	}

	public void setSol_id_origen(Integer sol_id_origen) {
		this.sol_id_origen = sol_id_origen;
	}

	public Integer getSol_id_destino() {
		return sol_id_destino;
	}

	public void setSol_id_destino(Integer sol_id_destino) {
		this.sol_id_destino = sol_id_destino;
	}

	public String getSol_fcoid() {
		return sol_fcoid;
	}

	public void setSol_fcoid(String sol_fcoid) {
		this.sol_fcoid = sol_fcoid;
	}

	public String getSol_vehi() {
		return sol_vehi;
	}

	public void setSol_vehi(String sol_vehi) {
		this.sol_vehi = sol_vehi;
	}

	public String getSol_usuario_cedula() {
		return sol_usuario_cedula;
	}

	public void setSol_usuario_cedula(String sol_usuario_cedula) {
		this.sol_usuario_cedula = sol_usuario_cedula;
	}

	public String getSol_usuario_nombre() {
		return sol_usuario_nombre;
	}

	public void setSol_usuario_nombre(String sol_usuario_nombre) {
		this.sol_usuario_nombre = sol_usuario_nombre;
	}

	public Integer getSol_id() {
		return sol_id;
	}

	public void setSol_id(Integer sol_id) {
		this.sol_id = sol_id;
	}

	public Timestamp getSol_fecha() {
		return sol_fecha;
	}

	public void setSol_fecha(Timestamp sol_fecha) {
		this.sol_fecha = sol_fecha;
	}

	public Timestamp getSol_fecha_aprobacion() {
		return sol_fecha_aprobacion;
	}

	public void setSol_fecha_aprobacion(Timestamp sol_fecha_aprobacion) {
		this.sol_fecha_aprobacion = sol_fecha_aprobacion;
	}

	public String getSol_pasajeros() {
		return sol_pasajeros;
	}

	public void setSol_pasajeros(String sol_pasajeros) {
		this.sol_pasajeros = sol_pasajeros;
	}

	public String getSol_motivo() {
		return sol_motivo;
	}

	public void setSol_motivo(String sol_motivo) {
		this.sol_motivo = sol_motivo;
	}

	public String getSol_hora_inicio() {
		return sol_hora_inicio;
	}

	public void setSol_hora_inicio(String sol_hora_inicio) {
		this.sol_hora_inicio = sol_hora_inicio;
	}

	public String getSol_hora_fin() {
		return sol_hora_fin;
	}

	public void setSol_hora_fin(String sol_hora_fin) {
		this.sol_hora_fin = sol_hora_fin;
	}

	public boolean isSol_flexibilidad() {
		return sol_flexibilidad;
	}

	public void setSol_flexibilidad(boolean sol_flexibilidad) {
		this.sol_flexibilidad = sol_flexibilidad;
	}

	public boolean isSol_regresorigen() {
		return sol_regresorigen;
	}

	public void setSol_regresorigen(boolean sol_regresorigen) {
		this.sol_regresorigen = sol_regresorigen;
	}

	public String getSol_observacion() {
		return sol_observacion;
	}

	public void setSol_observacion(String sol_observacion) {
		this.sol_observacion = sol_observacion;
	}

	public String getSol_estado() {
		return sol_estado;
	}

	public void setSol_estado(String sol_estado) {
		this.sol_estado = sol_estado;
	}

	public TransLugare getLugorigen() {
		return lugorigen;
	}

	public void setLugorigen(TransLugare lugorigen) {
		this.lugorigen = lugorigen;
	}

	public TransLugare getLugdestino() {
		return lugdestino;
	}

	public void setLugdestino(TransLugare lugdestino) {
		this.lugdestino = lugdestino;
	}

	public TransVehiculo getVehi_idplaca() {
		return vehi_idplaca;
	}

	public void setVehi_idplaca(TransVehiculo vehi_idplaca) {
		this.vehi_idplaca = vehi_idplaca;
	}

	public TransConductore getCond_cedula() {
		return cond_cedula;
	}

	public void setCond_cedula(TransConductore cond_cedula) {
		this.cond_cedula = cond_cedula;
	}

	public List<TransSolicitud> getListaSolicitudespend() {
		return listaSolicitudespend;
	}

	public void setListaSolicitudespend(
			List<TransSolicitud> listaSolicitudespend) {
		this.listaSolicitudespend = listaSolicitudespend;
	}

	public boolean isEdicion() {
		return edicion;
	}

	public void setEdicion(boolean edicion) {
		this.edicion = edicion;
	}

	public boolean isInfomostrar() {
		return infomostrar;
	}

	public void setInfomostrar(boolean infomostrar) {
		this.infomostrar = infomostrar;
	}

	public boolean isEdiciontipo() {
		return ediciontipo;
	}

	public void setEdiciontipo(boolean ediciontipo) {
		this.ediciontipo = ediciontipo;
	}

	public String getSol_novedades() {
		return sol_novedades;
	}

	public void setSol_novedades(String sol_novedades) {
		this.sol_novedades = sol_novedades;
	}

	public String getSol_tipovehiculo() {
		return sol_tipovehiculo;
	}

	public void setSol_tipovehiculo(String sol_tipovehiculo) {
		this.sol_tipovehiculo = sol_tipovehiculo;
	}

	// public TransFuncionarioConductor getFco_id() {
	// return fco_id;
	// }
	//
	// public void setFco_id(TransFuncionarioConductor fco_id) {
	// this.fco_id = fco_id;
	// }

	// SOLICITUDES
	/**
	 * accion para invocar el manager y crear solicitud o editar el solicitud
	 * 
	 * @param sol_id
	 * @param sol_fecha
	 * @param sol_motivo
	 * @param horainiciotiemp
	 * @param horafintiemp
	 * @param sol_flexibilidad
	 * @param sol_observacion
	 * @param sol_estado
	 * @param sol_fcoid
	 * @param sol_conductor
	 * @param sol_regresorigen
	 * @param sol_fecha_aprobacion
	 * @param arrayNovedades
	 * @throws Exception
	 */
	public String crearSolicitud() {
		try {
			BuscarPersonasolicitud();
			String mensaje = "";
			String mensajejefe = "";
			String mensajeconductor = "";
			sol_fecha = new Timestamp(fecha.getTime());
			Integer pasajeros;
			pasajeros = Integer.parseInt(sol_pasajeros);
			DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
			horainiciotiemp = new java.sql.Time(formatter
					.parse(sol_hora_inicio).getTime());
			horafintiemp = new java.sql.Time(formatter.parse(sol_hora_fin)
					.getTime());
			if (edicion) {
				asignarLugarDestino();
				asignarLugarOrigen();
				asignarVehiculo();
				if (arrayNovedades != null) {
					managersol.editarSolicitud(sol_id, sol_fecha, pasajeros,
							sol_motivo.trim(), horainiciotiemp, horafintiemp,
							sol_flexibilidad, sol_observacion.trim(),
							sol_estado, sol_fcoid, sol_conductor,
							sol_regresorigen, sol_fecha_aprobacion,
							arrayNovedades);
					Mensaje.crearMensajeINFO("Actualizado - Modificado");

					sol_novedades = managersol.solicitudByID(sol_id)
							.getSolNovedades();
					if (sol_estado.equals("P"))
						sol_estadonombre = "Pendiente";
					else if (sol_estado.equals("N"))
						sol_estadonombre = "Anulado";
					else if (sol_estado.equals("A"))
						sol_estadonombre = "Aprobado";
					else if (sol_estado.equals("R"))
						sol_estadonombre = "Rechazado";
					mensaje = "<!DOCTYPE html><html lang='es'><head><meta http-equiv='Content-Type' content='text/html; charset=utf-8' />"
							+ "<meta name='viewport' content='width=device-width'></head><body>"
							+ "Estimado(a) Solicitante: "
							+ Funciones.utf8Sting(sol_usuario_nombre)
							+ ",<br/>"
							+ "Le notificamos que su solicitud de Transporte fue: "
							+ Funciones.utf8Sting(sol_estadonombre)
							+ ", <br/><br/>"
							+ "y contiene las siguientes novedades: <br/><br/>"
							+ " "
							+ sol_novedades
							+ "<br/><br/>"
							+ "De la siguiente solicitud: <br/><br/>"
							+ "Número de Solicitud: "
							+ Funciones.utf8Sting(sol_id.toString())
							+ "<br/>"
							+ "Fecha de Petición: "
							+ Funciones.dateToString(sol_fecha)
							+ "<br/>"
							+ "Lugar Origen y Destino: "
							+ managergest.LugarByID(sol_id_origen)
									.getLugNombre()
							+ " - "
							+ managergest.LugarByID(sol_id_destino)
									.getLugNombre()
							+ "<br/>"
							+ "Hora Origen y Destino: "
							+ horainiciotiemp.toString()
							+ " - "
							+ horafintiemp.toString()
							+ "<br/>"
							+ "Número de Pasajeros: "
							+ sol_pasajeros.toString()
							+ "<br/>"
							+ "Nombre del Conductor: "
							+ Funciones.utf8Sting(managergest.conductorByID(
									sol_conductor).getCondNombre())
							+ " "
							+ Funciones.utf8Sting(managergest.conductorByID(
									sol_conductor).getCondApellido())
							+ "<br/>"
							+ "Correo del Conductor: "
							+ Funciones.utf8Sting(managergest.conductorByID(
									sol_conductor).getCondCorreo())
							+ "<br/>"
							+ "Número de teléfono: "
							+ Funciones.utf8Sting(managergest.conductorByID(
									sol_conductor).getCondTelefono())
							+ "<br/>"
							+ "Vehículo con Placas: "
							+ Funciones.utf8Sting(managergest.vehiculoByID(
									sol_vehi).getVehiIdplaca())
							+ " - "
							+ Funciones.utf8Sting(managergest.vehiculoByID(
									sol_vehi).getVehiNombre())
							+ " "
							+ Funciones.utf8Sting(managergest.vehiculoByID(
									sol_vehi).getVehiMarca())
							+ " "
							+ Funciones.utf8Sting(managergest.vehiculoByID(
									sol_vehi).getVehiModelo())
							+ "<br/><br/>"
							+ "Observaciónes siguientes: "
							+ Funciones.utf8Sting(sol_observacion)
							+ "<br/><br/>"
							+ "Se recuerda que el automóvil solo esperará 10 minutos a partir de la hora del inicio de la solicitud, favor estar atentos y puntuales.<br/>"
							+ "<br/>Atentamente,<br/>Sistema de Gestión de Transportes Yachay."
							+ "<br/><em><strong>NOTA:</strong> Este correo es generado automáticamente por el sistema favor no responder al mismo.</em></body></html>";

					mensajejefe = "<!DOCTYPE html><html lang='es'><head><meta http-equiv='Content-Type' content='text/html; charset=utf-8' />"
							+ "<meta name='viewport' content='width=device-width'></head><body>"
							+ "Estimado(a): "
							+ Funciones.utf8Sting(per1.getPerNombres())
							+ "  "
							+ Funciones.utf8Sting(per1.getPerApellidos())
							+ ",<br/>"
							+ "Le notificamos que la solicitud de Transporte de "
							+ Funciones.utf8Sting(sol_usuario_nombre)
							+ " fue "
							+ Funciones.utf8Sting(sol_estadonombre)
							+ ", <br/><br/>"
							+ "y contiene las siguientes novedades: <br/><br/>"
							+ " "
							+ sol_novedades
							+ "<br/><br/>"
							+ "De la siguiente solicitud: <br/><br/>"
							+ "Número de Solicitud: "
							+ Funciones.utf8Sting(sol_id.toString())
							+ "<br/>"
							+ "Fecha de Petición: "
							+ Funciones.dateToString(sol_fecha)
							+ "<br/>"
							+ "Lugar Origen y Destino: "
							+ managergest.LugarByID(sol_id_origen)
									.getLugNombre()
							+ " - "
							+ managergest.LugarByID(sol_id_destino)
									.getLugNombre()
							+ "<br/>"
							+ "Hora Origen y Destino: "
							+ horainiciotiemp.toString()
							+ " - "
							+ horafintiemp.toString()
							+ "<br/>"
							+ "Número de Pasajeros: "
							+ sol_pasajeros.toString()
							+ "<br/>"
							+ "Nombre del Conductor: "
							+ Funciones.utf8Sting(managergest.conductorByID(
									sol_conductor).getCondNombre())
							+ " "
							+ Funciones.utf8Sting(managergest.conductorByID(
									sol_conductor).getCondApellido())
							+ "<br/>"
							+ "Correo del Conductor: "
							+ Funciones.utf8Sting(managergest.conductorByID(
									sol_conductor).getCondCorreo())
							+ "<br/>"
							+ "Número de teléfono: "
							+ Funciones.utf8Sting(managergest.conductorByID(
									sol_conductor).getCondTelefono())
							+ "<br/>"
							+ "Vehículo con Placas: "
							+ Funciones.utf8Sting(managergest.vehiculoByID(
									sol_vehi).getVehiIdplaca())
							+ " - "
							+ Funciones.utf8Sting(managergest.vehiculoByID(
									sol_vehi).getVehiNombre())
							+ " "
							+ Funciones.utf8Sting(managergest.vehiculoByID(
									sol_vehi).getVehiMarca())
							+ " "
							+ Funciones.utf8Sting(managergest.vehiculoByID(
									sol_vehi).getVehiModelo())
							+ "<br/><br/>"
							+ "Observaciónes siguientes: "
							+ Funciones.utf8Sting(sol_observacion)
							+ "<br/><br/>"
							+ "Se recuerda que el automóvil solo esperará 10 minutos a partir de la hora del inicio de la solicitud, favor estar atentos y puntuales.<br/>"
							+ "<br/>Atentamente,<br/>Sistema de Gestión de Transportes Yachay."
							+ "<br/><em><strong>NOTA:</strong> Este correo es generado automáticamente por el sistema favor no responder al mismo.</em></body></html>";

					System.out.println(sol_correojefeinmediato);

					// Mail.generateAndSendEmail(sol_correojefeinmediato,
					// "Respuesta de Vehículo", mensaje);
					//
					// Mail.generateAndSendEmail(sol_correo,
					// "Respuesta de Vehículo", mensaje);

					mb.envioMailWS(sol_correojefeinmediato,
							"Respuesta de Vehículo", mensajejefe);
					mb.envioMailWS(sol_correo, "Respuesta de Vehículo", mensaje);

					sol_id = null;
					sol_usuario_cedula = usuario;
					sol_usuario_nombre = usuario;
					date = new Date();
					sol_id_origen = null;
					sol_id_destino = null;
					sol_estadonombre = "Pendiente";
					sol_fcoid = "Ninguno";
					sol_vehi = null;
					sol_conductor = "Ninguno";
					sol_fecha = null;
					sol_fecha_aprobacion = null;
					sol_pasajeros = null;
					sol_motivo = null;
					sol_hora_inicio = null;
					sol_hora_fin = null;
					sol_flexibilidad = false;
					sol_correojefeinmediato = "";
					sol_observacion = null;
					sol_estado = "P";
					edicion = true;
					sol_regresorigen = false;
					mensaje = "";
					mensajeconductor = "";
					mensajejefe = "";
					infomostrar = false;
					sol_correo = "";
					ediciontipo = false;
					sol_hora_inicio = null;
					sol_hora_fin = null;
					horainiciotiemp = null;
					horafintiemp = null;
					guardaredicion = false;
					arrayNovedades = null;
					getListaSolicitudespend().clear();
					getListaSolicitudespend().addAll(
							managersol.findAllSolicitudesOrdenadosapendiente());
					getListaSolicitudaprorecha().clear();
					getListaSolicitudaprorecha().addAll(
							managersol.findAllSolicitudesOrdenadosaaprorecha());
				} else {
					managersol.editarSolicitudsn(sol_id, sol_fecha, pasajeros,
							sol_motivo.trim(), horainiciotiemp, horafintiemp,
							sol_flexibilidad, sol_observacion.trim(),
							sol_estado, sol_fcoid, sol_conductor,
							sol_regresorigen);
					Mensaje.crearMensajeINFO("Actualizado - Modificado");
					if (sol_estado.equals("P"))
						sol_estadonombre = "Pendiente";
					else if (sol_estado.equals("N"))
						sol_estadonombre = "Anulado";
					else if (sol_estado.equals("A"))
						sol_estadonombre = "Aprobado";
					else if (sol_estado.equals("R"))
						sol_estadonombre = "Rechazado";
					if (sol_fcoid.equals("Ninguno")) {
						mensaje = "<!DOCTYPE html><html lang='es'><head><meta http-equiv='Content-Type' content='text/html; charset=utf-8' />"
								+ "<meta name='viewport' content='width=device-width'></head><body>"
								+ "Estimado(a) Solicitante: "
								+ Funciones.utf8Sting(sol_usuario_nombre)
								+ ",<br/>"
								+ "Le notificamos que su solicitud de Transporte fue: "
								+ Funciones.utf8Sting(sol_estadonombre)
								+ ", <br/><br/>"
								+ "Número de Solicitud: "
								+ Funciones.utf8Sting(sol_id.toString())
								+ "<br/>"
								+ "Fecha de Petición: "
								+ Funciones.dateToString(sol_fecha)
								+ "<br/>"
								+ "Lugar Origen y Destino: "
								+ managergest.LugarByID(sol_id_origen)
										.getLugNombre()
								+ " - "
								+ managergest.LugarByID(sol_id_destino)
										.getLugNombre()
								+ "<br/>"
								+ "Hora Origen y Destino: "
								+ horainiciotiemp.toString()
								+ " - "
								+ horafintiemp.toString()
								+ "<br/>"
								+ "Número de Pasajeros: "
								+ sol_pasajeros.toString()
								+ "<br/>"
								+ "Nombre del Conductor: "
								+ Funciones.utf8Sting(managergest
										.conductorByID(sol_conductor)
										.getCondNombre())
								+ " "
								+ Funciones.utf8Sting(managergest
										.conductorByID(sol_conductor)
										.getCondApellido())
								+ "<br/>"
								+ "Correo del Conductor: "
								+ Funciones.utf8Sting(managergest
										.conductorByID(sol_conductor)
										.getCondCorreo())
								+ "<br/>"
								+ "Número de teléfono: "
								+ Funciones.utf8Sting(managergest
										.conductorByID(sol_conductor)
										.getCondTelefono())
								+ "<br/>"
								+ "Tipo de Automóvil: "
								+ Funciones.utf8Sting(sol_tipovehiculo)
								+ "<br/>"
								+ "Vehículo con Placas: "
								+ Funciones.utf8Sting(managergest.vehiculoByID(
										sol_vehi).getVehiIdplaca())
								+ " - "
								+ Funciones.utf8Sting(managergest.vehiculoByID(
										sol_vehi).getVehiNombre())
								+ " "
								+ Funciones.utf8Sting(managergest.vehiculoByID(
										sol_vehi).getVehiMarca())
								+ " "
								+ Funciones.utf8Sting(managergest.vehiculoByID(
										sol_vehi).getVehiModelo())
								+ "<br/><br/>"
								+ "Observaciónes: "
								+ Funciones.utf8Sting(sol_observacion)
								+ "<br/><br/>"
								+ "Se recuerda que el automóvil solo esperará 10 minutos a partir de la hora del inicio de la solicitud, favor estar atentos y puntuales.<br/>"
								+ "<br/>Atentamente,<br/>Sistema de Gestión de Transportes Yachay."
								+ "<br/><em><strong>NOTA:</strong> Este correo es generado automáticamente por el sistema favor no responder al mismo.</em></body></html>";

						mensajeconductor = "<!DOCTYPE html><html lang='es'><head><meta http-equiv='Content-Type' content='text/html; charset=utf-8' />"
								+ "<meta name='viewport' content='width=device-width'></head><body>"
								+ "Estimado(a) "
								+ managergest.conductorByID(sol_conductor)
										.getCondNombre()
								+ " "
								+ Funciones.utf8Sting(managergest
										.conductorByID(sol_conductor)
										.getCondApellido())
								+ "<br/>"
								+ "Le notificamos que tiene una solicitud de Transporte por atender  <br/><br/>"
								+ "Número de Solicitud: "
								+ Funciones.utf8Sting(sol_id.toString())
								+ "<br/>"
								+ "Fecha de Petición: "
								+ Funciones.dateToString(sol_fecha)
								+ "<br/>"
								+ "Lugar Origen y Destino: "
								+ managergest.LugarByID(sol_id_origen)
										.getLugNombre()
								+ " - "
								+ managergest.LugarByID(sol_id_destino)
										.getLugNombre()
								+ "<br/>"
								+ "Hora Origen y Destino: "
								+ horainiciotiemp.toString()
								+ " - "
								+ horafintiemp.toString()
								+ "<br/>"
								+ "Número de Pasajeros: "
								+ sol_pasajeros.toString()
								+ "<br/>"
								+ "Tipo de Automóvil: "
								+ Funciones.utf8Sting(sol_tipovehiculo)
								+ "<br/>"
								+ "Vehículo con Placas: "
								+ Funciones.utf8Sting(managergest.vehiculoByID(
										sol_vehi).getVehiIdplaca())
								+ " - "
								+ Funciones.utf8Sting(managergest.vehiculoByID(
										sol_vehi).getVehiNombre())
								+ " "
								+ Funciones.utf8Sting(managergest.vehiculoByID(
										sol_vehi).getVehiMarca())
								+ " "
								+ Funciones.utf8Sting(managergest.vehiculoByID(
										sol_vehi).getVehiModelo())
								+ "<br/><br/>"
								+ "Observaciónes: "
								+ Funciones.utf8Sting(sol_observacion)
								+ "<br/><br/>"
								+ "Se recuerda que el automóvil solo esperará 10 minutos a partir de la hora del inicio de la solicitud, favor estar atentos y puntuales.<br/>"
								+ "<br/>Atentamente,<br/>Sistema de gestión de Transportes Yachay."
								+ "<br/><em><strong>NOTA:</strong> Este correo es generado automáticamente por el sistema favor no responder al mismo.</em></body></html>";

						// Mail.generateAndSendEmail(sol_correo,
						// "Respuesta de Vehículo", mensaje);
						// Mail.generateAndSendEmail(
						// managergest.conductorByID(sol_conductor)
						// .getCondCorreo(),
						// "Solicitud de Vehículo", mensajeconductor);

						mb.envioMailWS(sol_correo, "Respuesta de Vehículo",
								mensaje);
						mb.envioMailWS(
								Funciones.utf8Sting(managergest.conductorByID(
										sol_conductor).getCondCorreo()),
								"Solicitud de Vehículo", mensajeconductor);

						sol_id = null;
						sol_usuario_cedula = usuario;
						sol_usuario_nombre = usuario;
						date = new Date();
						sol_id_origen = null;
						sol_id_destino = null;
						sol_estadonombre = "Pendiente";
						sol_fcoid = "Ninguno";
						sol_vehi = null;
						sol_conductor = "Ninguno";
						sol_tipovehiculo=null;
						sol_fecha = null;
						sol_fecha_aprobacion = null;
						sol_pasajeros = null;
						sol_motivo = null;
						sol_hora_inicio = null;
						sol_hora_fin = null;
						sol_flexibilidad = false;
						sol_correojefeinmediato = "";
						sol_observacion = null;
						sol_estado = "P";
						edicion = true;
						sol_regresorigen = false;
						mensaje = "";
						mensajeconductor = "";
						infomostrar = false;
						sol_correo = "";
						ediciontipo = false;
						sol_hora_inicio = null;
						sol_hora_fin = null;
						horainiciotiemp = null;
						horafintiemp = null;
						guardaredicion = false;
						arrayNovedades = null;
						getListaSolicitudespend().clear();
						getListaSolicitudespend()
								.addAll(managersol
										.findAllSolicitudesOrdenadosapendiente());
						getListaSolicitudaprorecha().clear();
						getListaSolicitudaprorecha()
								.addAll(managersol
										.findAllSolicitudesOrdenadosaaprorecha());
					} else {
						// mensaje =
						// "<!DOCTYPE html><html lang='es'><head><meta http-equiv='Content-Type' content='text/html; charset=utf-8' />"
						// +
						// "<meta name='viewport' content='width=device-width'></head><body>"
						// + "Estimado(a) Solicitante: "
						// + Funciones.utf8Sting(sol_usuario_nombre)
						// + ",<br/>"
						// + "Le notificamos que su solitud de Transporte fue: "
						// + Funciones.utf8Sting(sol_estadonombre)
						// + ", <br/><br/>"
						// + "Número de Solicitud: "
						// + Funciones.utf8Sting(sol_id.toString())
						// + "<br/>"
						// + "Fecha de Petición: "
						// + Funciones.dateToString(sol_fecha)
						// + "<br/>"
						// + "Lugar Origen y Destino: "
						// + managergest.LugarByID(sol_id_origen)
						// .getLugNombre()
						// + " - "
						// + managergest.LugarByID(sol_id_destino)
						// .getLugNombre()
						// + "<br/>"
						// + "Hora Origen y Destino: "
						// + horainiciotiemp.toString()
						// + " - "
						// + horafintiemp.toString()
						// + "<br/>"
						// + "Número de Pasajeros: "
						// + sol_pasajeros.toString()
						// + "<br/>"
						// + "Nombre del Conductor Funcionario: "
						// + Funciones.utf8Sting(managergest
						// .conductorfunByID(sol_fcoid)
						// .getFcoNombres())
						// + "<br/>"
						// + "Correo del Conductor Funcionario: "
						// + Funciones.utf8Sting(managergest
						// .conductorfunByID(sol_fcoid)
						// .getFcoCorreo())
						// + "<br/>"
						// + "Número de teléfono: "
						// + Funciones.utf8Sting(managergest
						// .conductorfunByID(sol_fcoid)
						// .getFcoTelefono())
						// + "<br/>"
						// + "Vehículo con Placas: "
						// + Funciones.utf8Sting(managergest.vehiculoByID(
						// sol_vehi).getVehiIdplaca())
						// + " - "
						// + Funciones.utf8Sting(managergest.vehiculoByID(
						// sol_vehi).getVehiNombre())
						// + " "
						// + Funciones.utf8Sting(managergest.vehiculoByID(
						// sol_vehi).getVehiMarca())
						// + " "
						// + Funciones.utf8Sting(managergest.vehiculoByID(
						// sol_vehi).getVehiModelo())
						// + "<br/><br/>"
						// + "Observaciónes: "
						// + Funciones.utf8Sting(sol_observacion)
						// + "<br/><br/>"
						// +
						// "Nota: Se recuerda que el automovil solo esperará 10 minutos a partir de la hora del inicio de la solicitud, favor estar atentos y puntuales.<br/>"
						// +
						// "<br/>Atentamente,<br/>Sistema de gestión de Transportes Yachay.</body></html>";
						//
						// mensajeconductor =
						// "<!DOCTYPE html><html lang='es'><head><meta http-equiv='Content-Type' content='text/html; charset=utf-8' />"
						// +
						// "<meta name='viewport' content='width=device-width'></head><body>"
						// + "Estimado(a) "
						// + Funciones.utf8Sting(managergest
						// .conductorfunByID(sol_fcoid)
						// .getFcoNombres())
						// + "<br/>"
						// +
						// "Le notificamos que tiene una solicitud de Transporte por atender  <br/><br/>"
						// + "Número de Solicitud: "
						// + Funciones.utf8Sting(sol_id.toString())
						// + "<br/>"
						// + "Fecha de Petición: "
						// + Funciones.dateToString(sol_fecha)
						// + "<br/>"
						// + "Lugar Origen y Destino: "
						// + managergest.LugarByID(sol_id_origen)
						// .getLugNombre()
						// + " - "
						// + managergest.LugarByID(sol_id_destino)
						// .getLugNombre()
						// + "<br/>"
						// + "Hora Origen y Destino: "
						// + horainiciotiemp.toString()
						// + " - "
						// + horafintiemp.toString()
						// + "<br/>"
						// + "Número de Pasajeros: "
						// + sol_pasajeros.toString()
						// + "<br/>"
						// + "Vehículo con Placas: "
						// + Funciones.utf8Sting(managergest.vehiculoByID(
						// sol_vehi).getVehiIdplaca())
						// + " - "
						// + Funciones.utf8Sting(managergest.vehiculoByID(
						// sol_vehi).getVehiNombre())
						// + " "
						// + Funciones.utf8Sting(managergest.vehiculoByID(
						// sol_vehi).getVehiMarca())
						// + " "
						// + Funciones.utf8Sting(managergest.vehiculoByID(
						// sol_vehi).getVehiModelo())
						// + "<br/><br/>"
						// + "Observaciónes: "
						// + Funciones.utf8Sting(sol_observacion)
						// + "<br/><br/>"
						// +
						// "Nota: Se recuerda que el automovil solo esperará 10 minutos a partir de la hora del inicio de la solicitud, favor estar atentos y puntuales.<br/>"
						// +
						// "<br/>Atentamente,<br/>Sistema de gestión de Transportes Yachay.</body></html>";
						//
						// Mail.generateAndSendEmail(sol_correo,
						// "Respuesta de Vehículo", mensaje);
						// Mail.generateAndSendEmail(
						// managergest.conductorfunByID(sol_fcoid)
						// .getFcoCorreo(),
						// "Solicitud de Vehículo", mensajeconductor);
					}

					sol_id = null;
					sol_usuario_cedula = usuario;
					date = new Date();
					sol_id_origen = null;
					sol_id_destino = null;
					sol_estadonombre = "Pendiente";
					sol_fcoid = "Ninguno";
					sol_vehi = null;
					sol_conductor = "Ninguno";
					sol_fecha = null;
					sol_fecha_aprobacion = null;
					sol_pasajeros = null;
					sol_motivo = null;
					sol_tipovehiculo=null;
					sol_hora_inicio = null;
					sol_hora_fin = null;
					sol_flexibilidad = false;
					sol_observacion = null;
					sol_estado = "P";
					sol_novedades = "";
					edicion = true;
					sol_regresorigen = false;
					mensaje = "";
					mensajeconductor = "";
					mensajejefe = "";
					infomostrar = false;
					sol_correo = "";
					ediciontipo = false;
					sol_hora_inicio = null;
					sol_hora_fin = null;
					horainiciotiemp = null;
					horafintiemp = null;
					guardaredicion = false;
					arrayNovedades = null;
					getListaSolicitudespend().clear();
					getListaSolicitudespend().addAll(
							managersol.findAllSolicitudesOrdenadosapendiente());
					getListaSolicitudaprorecha().clear();
					getListaSolicitudaprorecha().addAll(
							managersol.findAllSolicitudesOrdenadosaaprorecha());
				}

			} else {
				managersol.insertarSolicitud(sol_fecha, sol_usuario_cedula,
						sol_usuario_nombre, pasajeros, sol_motivo.trim(),
						horainiciotiemp, horafintiemp, sol_flexibilidad,
						sol_fcoid, sol_regresorigen,sol_tipovehiculo);
				Mensaje.crearMensajeINFO("Registrado - Creado");
				sol_id = null;
				date = new Date();
				// sol_idsolicitante = sol.getSolicitante();
				sol_usuario_cedula = usuario;
				sol_usuario_nombre = usuario;
				sol_id_origen = null;
				sol_id_destino = null;
				sol_fcoid = "Ninguno";
				sol_vehi = null;
				sol_conductor = "Ninguno";
				sol_fecha = null;
				sol_fecha_aprobacion = null;
				sol_pasajeros = null;
				sol_motivo = null;
				sol_tipovehiculo=null;
				sol_hora_inicio = null;
				sol_hora_fin = null;
				sol_flexibilidad = false;
				sol_observacion = null;
				sol_estado = "P";
				sol_correo = "";
				edicion = true;
				infomostrar = false;
				ediciontipo = false;
				sol_hora_inicio = null;
				sol_hora_fin = null;
				horainiciotiemp = null;
				horafintiemp = null;
				guardaredicion = false;
				sol_regresorigen = false;
				sol_novedades = "";
				arrayNovedades = null;
				getListaSolicitudespend().clear();
				getListaSolicitudespend().addAll(
						managersol.findAllSolicitudesOrdenadosapendiente());
				getListaSolicitudaprorecha().clear();
				getListaSolicitudaprorecha().addAll(
						managersol.findAllSolicitudesOrdenadosaaprorecha());
			}
			return "trans_solicitudesa?faces-redirect=true";

		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error al crear solicitud", null));
			return "";
		}
	}

	/**
	 * metodo para abrir el dialogo
	 * 
	 * @throws Exception
	 */
	public void abrirDialog() {
		DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		try {
			horainiciotiemp = new java.sql.Time(formatter
					.parse(sol_hora_inicio).getTime());
			horafintiemp = new java.sql.Time(formatter.parse(sol_hora_fin)
					.getTime());
			if (sol_fcoid.equals("Ninguno") && sol_conductor.equals("Ninguno")) {
				System.out.println("entra aca1");
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage(
						"Debe seleccionar un conductor o funcionario", " "));
			} else if (!sol_fcoid.equals("Ninguno")
					&& !sol_conductor.equals("Ninguno")) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(
						null,
						new FacesMessage(
								"Debe seleccionar solo un conductor o funcionario",
								" "));
			} else if (horafintiemp.getTime() < horainiciotiemp.getTime()) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage(
						"Error..!!! verifique su horario", ""));
			} else {
				RequestContext.getCurrentInstance().execute("PF('gu').show();");
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * JAVA.DATE TO SQL.TIME
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public Time fechaAtiempo(Date fecha) {
		DateFormat dateFormatH = new SimpleDateFormat("HH:mm");
		String hora = dateFormatH.format(fecha).toString();
		String[] array = hora.split(":");
		Time resp = new Time(Integer.parseInt(array[0]),
				Integer.parseInt(array[1]), 00);
		return resp;
	}

	/**
	 * accion para cargar los datos en el formulario
	 * 
	 * @param sol_id
	 * @param sol_fecha
	 * @param sol_motivo
	 * @param horainiciotiemp
	 * @param horafintiemp
	 * @param sol_flexibilidad
	 * @param sol_observacion
	 * @param sol_estado
	 * @param sol_fcoid
	 * @param sol_conductor
	 * @param sol_regresorigen
	 * @param sol_fecha_aprobacion
	 * @param arrayNovedades
	 * @throws Exception
	 */
	public String cargarSolicitud(TransSolicitud sol) {
		String r = "";
		try {
			sol_id = sol.getSolId();
			// sol_idsolicitante = sol.getSolicitante();
			sol_usuario_cedula = sol.getSolIdSolicitante();
			sol_usuario_nombre = sol.getSolNomSolicitante();
			sol_id_origen = sol.getTransLugare2().getLugId();
			sol_id_destino = sol.getTransLugare1().getLugId();
			BuscarPersona();
			// if (sol.getTransFuncionarioConductor() == null)
			// sol_fcoid = "Ninguno";
			// else
			// sol_fcoid = sol.getTransFuncionarioConductor().getFcoId();
			if (sol.getTransVehiculo() == null)
				sol_vehi = "";
			else
				sol_vehi = sol.getTransVehiculo().getVehiIdplaca();
			if (sol.getTransConductore() == null) {
				sol_conductor = "Ninguno";
			} else {
				sol_conductor = sol.getTransConductore().getCondCedula();
			}
			fecha = sol.getSolFecha();
			sol_fecha_aprobacion = sol.getSolFechaAprobacion();
			sol_pasajeros = sol.getSolPasajeros().toString();
			sol_motivo = sol.getSolMotivo();
			sol_tipovehiculo=sol.getSolTipovehiculo();
			sol_hora_inicio = sol.getSolHoraInicio().toString();
			sol_hora_fin = sol.getSolHoraFin().toString();
			sol_flexibilidad = sol.getSolFlexibilidad();
			sol_observacion = sol.getSolObservacion();
			sol_estado = sol.getSolEstado();
			sol_regresorigen = sol.getSolRegresorigen();
			regresoOrigen();
			if (sol_flexibilidad == true) {
				horamostrar = false;
			} else
				horamostrar = true;
			edicion = true;
			ediciontipo = false;
			guardaredicion = false;
			mostrarnovedades = false;
			if (sol.getSolEstado().equals("P"))
				sol_estadonombre = "Pendiente";
			else if (sol.getSolEstado().equals("N"))
				sol_estadonombre = "Anulado";
			else if (sol.getSolEstado().equals("A"))
				sol_estadonombre = "Aprobado";
			else if (sol.getSolEstado().equals("R"))
				sol_estadonombre = "Rechazado";
			r = "trans_nsolicituda?faces-redirect=true";
			return r;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * accion para cargar los datos en el formulario
	 * 
	 * @param sol_id
	 * @param sol_fecha
	 * @param sol_motivo
	 * @param horainiciotiemp
	 * @param horafintiemp
	 * @param sol_flexibilidad
	 * @param sol_observacion
	 * @param sol_estado
	 * @param sol_fcoid
	 * @param sol_conductor
	 * @param sol_regresorigen
	 * @param sol_fecha_aprobacion
	 * @param arrayNovedades
	 * @throws Exception
	 */
	public String cargarSolicitudvalidada(TransSolicitud sol) {
		String r = "";
		try {
			sol_id = sol.getSolId();
			// sol_idsolicitante = sol.getSolicitante();
			sol_usuario_cedula = sol.getSolIdSolicitante();
			sol_usuario_nombre = sol.getSolNomSolicitante();
			sol_id_origen = sol.getTransLugare2().getLugId();
			sol_id_destino = sol.getTransLugare1().getLugId();
			// if (sol.getTransFuncionarioConductor() == null)
			// sol_fcoid = "Ninguno";
			// else
			// sol_fcoid = sol.getTransFuncionarioConductor().getFcoId();
			if (sol.getTransVehiculo() == null)
				sol_vehi = "";
			else
				sol_vehi = sol.getTransVehiculo().getVehiIdplaca();
			if (sol.getTransConductore() == null) {
				sol_conductor = "Ninguno";
			} else {
				sol_conductor = sol.getTransConductore().getCondCedula();
			}
			fecha = sol.getSolFecha();
			sol_fecha_aprobacion = sol.getSolFechaAprobacion();
			sol_pasajeros = sol.getSolPasajeros().toString();
			sol_motivo = sol.getSolMotivo();
			sol_tipovehiculo=sol.getSolTipovehiculo();
			sol_hora_inicio = sol.getSolHoraInicio().toString();
			sol_hora_fin = sol.getSolHoraFin().toString();
			sol_flexibilidad = sol.getSolFlexibilidad();
			sol_observacion = sol.getSolObservacion();
			sol_estado = sol.getSolEstado();
			sol_novedades = sol.getSolNovedades();
			sol_regresorigen = sol.getSolRegresorigen();
			if (sol.getSolEstado().equals("P")) {
				infomostrar = false;
			} else {
				infomostrar = true;
			}
			horamostrar = true;
			edicion = true;
			ediciontipo = false;
			guardaredicion = false;
			mostrarnovedades = true;
			sol_regresorigen = true;
			if (sol.getSolEstado().equals("P"))
				sol_estadonombre = "Pendiente";
			else if (sol.getSolEstado().equals("N"))
				sol_estadonombre = "Anulado";
			else if (sol.getSolEstado().equals("A"))
				sol_estadonombre = "Aprobado";
			else if (sol.getSolEstado().equals("R"))
				sol_estadonombre = "Rechazado";
			r = "trans_nsolicituda?faces-redirect=true";
			return r;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * activar y desactivar estado Solicitud
	 * 
	 * @param vehi_id
	 * @throws Exception
	 */
	public String cambiarEstadoSoli() {
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(
					null,
					new FacesMessage("INFORMACION", managersol
							.cambioEstadoSolicitud(getSoli().getSolId())));
			getListaSolicitudespend().clear();
			getListaSolicitudespend().addAll(
					managersol.findAllSolicitudesOrdenadosapendiente());
			getListaSolicitudaprorecha().clear();
			getListaSolicitudaprorecha().addAll(
					managersol.findAllSolicitudesOrdenadosaaprorecha());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "";
	}

	public void cambiarEstadoSoli(TransSolicitud soli) {
		setSoli(soli);
		RequestContext.getCurrentInstance().execute("PF('ce').show();");
	}

	/**
	 * metodo para conocer el soli_id si esta usado
	 * 
	 * @param sol_id
	 */
	public boolean averiguarSoliid(Integer soli_id) {
		Integer t = 0;
		boolean r = false;
		List<TransSolicitud> soli = managersol
				.findAllSolicitudesOrdenadosapendiente();
		for (TransSolicitud y : soli) {
			if (y.getSolId().equals(soli_id)) {
				System.out.println("si entra1");
				t = 1;
				r = true;
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"El codigo del solicitud existe.", null));
			}
		}
		if (t == 0) {
			r = false;
		}
		return r;
	}

	/**
	 * Lista de estados
	 * 
	 * @return lista de items de estados
	 */
	public List<SelectItem> getlistEstados() {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		lista.add(new SelectItem(Funciones.estadoAprobado,
				Funciones.estadoAprobado + " : "
						+ Funciones.valorEstadoAprobado));
		lista.add(new SelectItem(Funciones.estadoRechazado,
				Funciones.estadoRechazado + " : "
						+ Funciones.valorEstadoRechazado));
		lista.add(new SelectItem(Funciones.estadoAnulado,
				Funciones.estadoAnulado + " : " + Funciones.valorEstadoAnulado));
		return lista;
	}

	/**
	 * metodo para mostrar los lugaresorigen en solicitud
	 * 
	 */
	public List<SelectItem> getListaOrigen() {
		List<SelectItem> listadoSI = new ArrayList<SelectItem>();
		for (TransLugare t : managergest.findAllLugares()) {
			if (!t.getLugEstado().equals("I")) {
				listadoSI.add(new SelectItem(t.getLugId(), t.getLugNombre()
						+ " - " + t.getLugCiudad()));
			}
		}
		return listadoSI;
	}

	/**
	 * metodo para mostrar los lugaresdestino en solicitud
	 * 
	 */
	public List<SelectItem> getListaDestino() {
		List<SelectItem> listadoSI = new ArrayList<SelectItem>();
		for (TransLugare t : managergest.findAllLugares()) {
			if (!t.getLugEstado().equals("I")) {
				listadoSI.add(new SelectItem(t.getLugId(), t.getLugNombre()
						+ " - " + t.getLugCiudad()));
			}
		}
		return listadoSI;
	}

	/**
	 * metodo para mostrar los conductores en solicitud
	 * 
	 */
	public List<SelectItem> getListaConductor() {
		List<SelectItem> listadoSI = new ArrayList<SelectItem>();
		listadoSI.add(new SelectItem("Ninguno", "Ninguno"));
		for (TransConductore t : managergest.findAllConductores()) {
			if (!t.getCondEstado().equals("I")) {
				listadoSI.add(new SelectItem(t.getCondCedula(), t
						.getCondNombre() + " " + t.getCondApellido()));
			}
		}
		return listadoSI;
	}

	// /**
	// * metodo para mostrar los conductorefunacionario en solicitud Me llaMO
	// lkc
	// * ELECTROFLOGEAUR JAJAJ
	// */
	// public List<SelectItem> getListaConductorfuncionario() {
	// List<SelectItem> listadoSI = new ArrayList<SelectItem>();
	// listadoSI.add(new SelectItem("Ninguno", "Ninguno"));
	// for (TransFuncionarioConductor t : managersol
	// .findAllConductFuncionarios()) {
	// if (!t.getFcoEstado().equals("I")) {
	// if (per.getPerGerencia().equals(t.getFcoGerencia()))
	// listadoSI.add(new SelectItem(t.getFcoId(), t
	// .getFcoNombres() + " - " + t.getFcoGerencia()));
	// }
	// }
	//
	// return listadoSI;
	// }

	/**
	 * metodo para mostrar los vehiculos en solicitud
	 * 
	 */
	public List<SelectItem> getListaVehiculo() {
		List<SelectItem> listadoSI = new ArrayList<SelectItem>();
		for (TransVehiculo t : managergest.findAllVehiculos()) {
			if (!t.getVehiEstado().equals("I")) {
				listadoSI.add(new SelectItem(t.getVehiIdplaca(), t
						.getVehiIdplaca()
						+ " - "
						+ t.getVehiNombre()
						+ " Capacidad: " + t.getVehiCapacidad()));
			}
		}
		return listadoSI;
	}

	/**
	 * metodo para asignar el condutor a solicitud
	 * 
	 */
	public String asignarConductor() {
		System.out.println("conductor: " + sol_conductor);
		if (sol_conductor == null)
			sol_conductor = "";
		else
			managersol.asignarConductor(sol_conductor);
		return "";
	}

	/**
	 * metodo para asignar el lugarorigen a solicitud
	 * 
	 */
	public String asignarLugarOrigen() {
		managersol.asignarlugarini(sol_id_origen);
		return "";
	}

	/**
	 * metodo para asignar el lugarorigen a solicitud
	 * 
	 */
	public String asignarLugarDestino() {
		managersol.asignarlugarfin(sol_id_destino);
		return "";
	}

	/**
	 * metodo para asignar el vehiculo a solicitud
	 * 
	 */
	public String asignarVehiculo() {
		TransVehiculo ve;
		try {
			ve = managergest.vehiculoByID(sol_vehi);
			if (Integer.parseInt(sol_pasajeros) <= ve.getVehiCapacidad())
				managersol.asignarvehiculo(sol_vehi);
			else {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(
						null,
						new FacesMessage(
								"El número pasajeros exceden la capacidad del vehículo",
								" "));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "";
	}

	/**
	 * metodo para reporte del vehiculo
	 * 
	 */
	public void reporteVehiculoConductor() {
		if (sol_vehi == null) {
			System.out.println("Es null");
			System.out.println("fi: " + new Timestamp(fi.getTime()));
			System.out.println("ff: " + new Timestamp(ff.getTime()));
			getListareporte().clear();
			getListareporte().addAll(
					managersol.findAllVehiculosfecha(
							new Timestamp(fi.getTime()),
							new Timestamp(ff.getTime())));
			getListareporte().size();
		} else {
			System.out.println("No es null");
			System.out.println("fi: " + new Timestamp(fi.getTime()));
			System.out.println("ff: " + new Timestamp(ff.getTime()));
			getListareporte().clear();
			getListareporte().addAll(
					managersol.findAllVehiculosfechacond(sol_vehi,
							new Timestamp(fi.getTime()),
							new Timestamp(ff.getTime())));
			getListareporte().size();

		}
	}

	/**
	 * metodo para reporte del vehiculo
	 * 
	 */
	public void reporteFindAllVehiculo() {
		getListareporte().clear();
		getListareporte().addAll(managersol.findsolreporte());
		getListareporte().size();
	}

	/**
	 * limpia la informacion de horario
	 * 
	 * @return
	 * @throws Exception
	 */
	public String volverSolicitud() throws Exception {
		// limpiar datos
		sol_id = null;
		date = new Date();
		horamostrar = false;
		// sol_idsolicitante = sol.getSolicitante();
		sol_usuario_cedula = usuario;
		sol_usuario_nombre = usuario;
		sol_id_origen = null;
		sol_id_destino = null;
		sol_fcoid = "Ninguno";
		sol_vehi = null;
		sol_conductor = "Ninguno";
		sol_fecha = null;
		sol_fecha_aprobacion = null;
		sol_pasajeros = null;
		sol_tipovehiculo=null;
		sol_motivo = null;
		sol_hora_inicio = null;
		sol_hora_fin = null;
		sol_regresorigen = false;
		sol_flexibilidad = false;
		sol_novedades = "";
		sol_observacion = null;
		sol_estado = "P";
		infomostrar = false;
		sol_correo = "";
		edicion = true;
		ediciontipo = false;
		sol_hora_inicio = null;
		sol_hora_fin = null;
		horainiciotiemp = null;
		horafintiemp = null;
		getListaSolicitudespend().clear();
		getListaSolicitudespend().addAll(
				managersol.findAllSolicitudesOrdenadosapendiente());
		getListaSolicitudaprorecha().clear();
		getListaSolicitudaprorecha().addAll(
				managersol.findAllSolicitudesOrdenadosaaprorecha());
		return "trans_solicitudesa?faces-redirect=true";
	}

	/**
	 * Redirecciona a la pagina de creacion de vehiculos
	 * 
	 * @return
	 */
	public String nuevoSolicitud() {
		sol_id = null;
		date = new Date();
		fecha = date;
		// sol_idsolicitante = sol.getSolicitante();
		BuscarPersona();
		sol_id_origen = null;
		sol_id_destino = null;
		sol_fcoid = "Ninguno";
		sol_vehi = null;
		sol_conductor = "Ninguno";
		sol_fecha = null;
		sol_fecha_aprobacion = null;
		sol_pasajeros = null;
		sol_motivo = null;
		sol_tipovehiculo=null;
		sol_hora_inicio = null;
		sol_hora_fin = null;
		sol_novedades = "";
		sol_correo = "";
		sol_regresorigen = false;
		sol_flexibilidad = false;
		sol_observacion = null;
		sol_estado = "P";
		infomostrar = false;
		edicion = true;
		ediciontipo = false;
		sol_hora_inicio = null;
		sol_hora_fin = null;
		horainiciotiemp = null;
		horafintiemp = null;
		edicion = false;
		mostrarnovedades = true;
		sol_correo = "";
		date = new Date();
		return "trans_nsolicituda?faces-redirect=true";
	}

	/**
	 * metodo para listar los registros
	 * 
	 * @return
	 */
	public List<TransSolicitud> getListaSolicitudDesc() {
		List<TransSolicitud> a = managersol
				.findAllSolicitudesOrdenadosapendiente();
		List<TransSolicitud> l1 = new ArrayList<TransSolicitud>();
		for (TransSolicitud t : a) {
			l1.add(t);

		}
		return l1;
	}

	/**
	 * metodo para listar los vehiculos que tienen solicitud por fecha
	 * 
	 * @return listavehiculosocu
	 */
	public List<TransSolicitud> getListaVehiOcu() {
		List<TransSolicitud> a = managersol.findAllVehiculosOcu(sol_vehi,
				new Timestamp(fecha.getTime()));
		List<TransSolicitud> l1 = new ArrayList<TransSolicitud>();
		for (TransSolicitud t : a) {
			l1.add(t);

		}
		return l1;
	}

	/**
	 * metodo para listar los registros disponibles
	 * 
	 * @return listavehiculos
	 */
	public List<TransSolicitud> getListVehiculosDisponible() {
		List<TransSolicitud> a = managersol
				.findAllSolicitudesOrdenadosapendiente();
		List<TransSolicitud> l1 = new ArrayList<TransSolicitud>();
		for (TransSolicitud t : a) {
			if (t.getSolEstado().equals("A")
					&& t.getSolFecha().equals(sol_fecha)) {
				l1.add(t);
			}
		}
		return l1;
	}

	/**
	 * metodo para listar reporte vahiculo conductor
	 * 
	 * @return listavehpiculoconductor
	 */
	public List<TransSolicitud> getListavehiculoconduct() {
		getListaVehiculoCond().clear();
		getListaVehiculoCond().addAll(managersol.findAllVehiculoConductor());
		List<TransSolicitud> a = managersol.findAllVehiculoConductor();
		List<TransSolicitud> l1 = new ArrayList<TransSolicitud>();
		for (TransSolicitud t : a) {
			l1.add(t);

		}
		return l1;
	}

	/**
	 * Metodo para buscar a la persona
	 * 
	 * @throws Exception
	 */
	public void BuscarPersona() {

		try {
			cedula = ManagerCarga.consultaSQL(usuario);
			System.out.println(usuario);
			// per = mc.funcionarioByDNI(usuario);
			per = mb.buscarPersonaWSReg(cedula);
			sol_usuario_nombre = per.getPerNombres() + " "
					+ per.getPerApellidos();
			sol_usuario_cedula = per.getPerDNI();
			sol_correo = per.getPerCorreo();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Metodo para buscar a la personasolicitud
	 * 
	 * @throws Exception
	 */
	public void BuscarPersonasolicitud() {

		try {
			System.out.println(sol_usuario_cedula);
			// per = mc.funcionarioByDNI(usuario);
			per = mb.buscarPersonaWSReg(sol_usuario_cedula);

			// per = mc.personasolicitudByDNI(sol_usuario_cedula);
			sol_usuario_nombre = per.getPerNombres() + " "
					+ per.getPerApellidos();
			sol_usuario_cedula = per.getPerDNI();
			sol_correo = per.getPerCorreo();
			per1 = mc.personasolicitudByDNI(sol_usuario_cedula);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * metodo para reporte de novedades
	 * 
	 */
	public void reporteNovedades() {
		try {
			System.out.println("fi: " + new Timestamp(fi.getTime()));
			System.out.println("ff: " + new Timestamp(ff.getTime()));
			getListaNovedades().clear();
			getListaNovedades().addAll(
					mc.FindAllNovedadesByFecha(new Timestamp(fi.getTime()),
							new Timestamp(ff.getTime())));
			System.out.println("entra con:" + listaNovedades.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * metodo para reporte de novedades
	 * 
	 */
	public void reporteAllNovedades() {
		try {
			getListaNovedades().clear();
			getListaNovedades().addAll(mc.FindAllNovedades());
			System.out.println("entra con:" + listaNovedades.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Lista de horas
	 * 
	 * @return lista de items de horas
	 */
	public List<SelectItem> getlistHoras() {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		lista.add(new SelectItem(Funciones.hora_8, Funciones.hora_8));
		lista.add(new SelectItem(Funciones.hora_9, Funciones.hora_9));
		lista.add(new SelectItem(Funciones.hora_10, Funciones.hora_10));
		lista.add(new SelectItem(Funciones.hora_11, Funciones.hora_11));
		lista.add(new SelectItem(Funciones.hora_12, Funciones.hora_12));
		lista.add(new SelectItem(Funciones.hora_13, Funciones.hora_13));
		lista.add(new SelectItem(Funciones.hora_14, Funciones.hora_14));
		lista.add(new SelectItem(Funciones.hora_15, Funciones.hora_15));
		lista.add(new SelectItem(Funciones.hora_16, Funciones.hora_16));
		lista.add(new SelectItem(Funciones.hora_17, Funciones.hora_17));
		return lista;
	}
	
	/**
	 * Lista de vehiculos
	 * 
	 * @return lista de items de vehiculos
	 */
	public List<SelectItem> getlistVehiculo() {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		lista.add(new SelectItem(Funciones.automovil, Funciones.automovil));
		lista.add(new SelectItem(Funciones.camioneta, Funciones.camioneta));
		return lista;
	}

	/**
	 * limpia la informacion de horario
	 * 
	 * @return
	 * @throws Exception
	 */
	public String volver() throws Exception {
		// limpiar datos
		getListareporte().clear();
		getListaNovedades().clear();
		return "index?faces-redirect=true";
	}

	/**
	 * Metodo para observar el regreso y origen
	 * 
	 * @throws Exception
	 */
	public void regresoOrigen() {
		try {
			System.out.println(sol_regresorigen);
			System.out.println("verregresorigen " + verregresorigen);
			System.out.println("edicion " + edicion);
			if (sol_regresorigen == true) {
				verregresorigen = false;
				System.out.println("entra1");
			} else {
				System.out.println("entra2");
				verregresorigen = true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * metodo para asignar el lugarorigen a solicitud
	 * 
	 */
	public String asignarHoraFin() {
		sol_hora_fin = sol_hora_inicio;
		return "";
	}
}
