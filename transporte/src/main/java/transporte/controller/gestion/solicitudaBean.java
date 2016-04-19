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
import transporte.model.dao.entities.Persona;
import transporte.model.dao.entities.TransSolicitud;
import transporte.model.dao.entities.TransConductore;
import transporte.model.dao.entities.TransFuncionarioConductor;
import transporte.model.dao.entities.TransLugare;
import transporte.model.dao.entities.TransVehiculo;
import transporte.model.generic.Funciones;
import transporte.model.generic.Mensaje;
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

	// private transolicitante solicitante;
	private TransLugare lugorigen;
	private TransLugare lugdestino;
	private TransFuncionarioConductor fco_id;
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

	// fechas
	private Date date;
	private Date fecha;
	private Time horainiciotiemp;
	private Time horafintiemp;

	private Date fi;
	private Date ff;

	// flexibilidad cambio hora
	private boolean horamostrar;

	@Inject
	SesionBean ms;

	private String usuario;

	private Persona per;

	public solicitudaBean() {
	}

	@PostConstruct
	public void ini() {
		mc = new ManagerCarga();
		usuario = ms.validarSesion("trans_solicitudesa.xhtml");
		sol_conductor = "Ninguno";
		sol_vehi = "";
		sol_estadonombre = "";
		sol_fcoid = "Ninguno";
		sol_hora_inicio = null;
		sol_hora_fin = null;
		sol_id = null;
		sol_estado = "P";
		sol_flexibilidad = false;
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
		;
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

	public TransFuncionarioConductor getFco_id() {
		return fco_id;
	}

	public void setFco_id(TransFuncionarioConductor fco_id) {
		this.fco_id = fco_id;
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

	// SOLICITUDES
	/**
	 * accion para invocar el manager y crear solicitud o editar el solicitud
	 * 
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
	public String crearSolicitud() {
		try {
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
				managersol.editarSolicitud(sol_id, sol_fecha, pasajeros,
						sol_motivo.trim(), horainiciotiemp, horafintiemp,
						sol_flexibilidad, sol_observacion.trim(), sol_estado,
						sol_fcoid, sol_conductor);
				Mensaje.crearMensajeINFO("Actualizado - Modificado");
				sol_id = null;
				sol_usuario_cedula = usuario;
				date = new Date();
				sol_id_origen = null;
				sol_id_destino = null;
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
				sol_observacion = null;
				sol_estado = "P";
				edicion = true;
				infomostrar = false;
				ediciontipo = false;
				sol_hora_inicio = null;
				sol_hora_fin = null;
				horainiciotiemp = null;
				horafintiemp = null;
				guardaredicion = false;
				getListaSolicitudespend().clear();
				getListaSolicitudespend().addAll(
						managersol.findAllSolicitudesOrdenadosapendiente());
				getListaSolicitudaprorecha().clear();
				getListaSolicitudaprorecha().addAll(
						managersol.findAllSolicitudesOrdenadosaaprorecha());

			} else {
				managersol.insertarSolicitud(sol_fecha, sol_usuario_cedula,
						pasajeros, sol_motivo.trim(), horainiciotiemp,
						horafintiemp, sol_flexibilidad, sol_fcoid);
				Mensaje.crearMensajeINFO("Registrado - Creado");
				sol_id = null;
				date = new Date();
				// sol_idsolicitante = sol.getSolicitante();
				sol_usuario_cedula = usuario;
				sol_id_origen = null;
				sol_id_destino = null;
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
				sol_observacion = null;
				sol_estado = "P";
				edicion = true;
				infomostrar = false;
				ediciontipo = false;
				sol_hora_inicio = null;
				sol_hora_fin = null;
				horainiciotiemp = null;
				horafintiemp = null;
				guardaredicion = false;
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

	public void abrirDialog() {
		DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		try {
			horainiciotiemp = new java.sql.Time(formatter
					.parse(sol_hora_inicio).getTime());
			horafintiemp = new java.sql.Time(formatter.parse(sol_hora_fin)
					.getTime());
			System.out.println(sol_fcoid + " ghfghgfhfhg  " + sol_conductor);
			if (sol_fcoid.equals("Ninguno") && sol_conductor.equals("Ninguno")) {
				System.out.println("entra aca1");
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage(
						"Debe seleccionar un conductor o funcionario", " "));
			} else if (!sol_fcoid.equals("Ninguno") && !sol_conductor.equals("Ninguno")) {
				System.out.println("entra aca2");
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(
						null,
						new FacesMessage(
								"Debe seleccionar solo un conductor o funcionario",
								" "));
			} else if (horafintiemp.getTime() <= horainiciotiemp.getTime()) {
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
	public String cargarSolicitud(TransSolicitud sol) {
		String r = "";
		try {
			sol_id = sol.getSolId();
			// sol_idsolicitante = sol.getSolicitante();
			sol_usuario_cedula = sol.getSolIdSolicitante();
			sol_id_origen = sol.getTransLugare2().getLugId();
			sol_id_destino = sol.getTransLugare1().getLugId();
			if (sol.getTransFuncionarioConductor() == null)
				sol_fcoid = "Ninguno";
			else
				sol_fcoid = sol.getTransFuncionarioConductor().getFcoId();
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
			sol_hora_inicio = sol.getSolHoraInicio().toString();
			sol_hora_fin = sol.getSolHoraFin().toString();
			sol_flexibilidad = sol.getSolFlexibilidad();
			sol_observacion = sol.getSolObservacion();
			sol_estado = sol.getSolEstado();
			System.out.println(sol_flexibilidad);
			if (sol_flexibilidad == true) {
				horamostrar = false;
			} else
				horamostrar = true;
			edicion = true;
			ediciontipo = false;
			guardaredicion = false;
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
	public String cargarSolicitudvalidada(TransSolicitud sol) {
		String r = "";
		try {
			sol_id = sol.getSolId();
			// sol_idsolicitante = sol.getSolicitante();
			sol_usuario_cedula = sol.getSolIdSolicitante();
			sol_id_origen = sol.getTransLugare2().getLugId();
			sol_id_destino = sol.getTransLugare1().getLugId();
			if (sol.getTransFuncionarioConductor() == null)
				sol_fcoid = "Ninguno";
			else
				sol_fcoid = sol.getTransFuncionarioConductor().getFcoId();
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
			sol_hora_inicio = sol.getSolHoraInicio().toString();
			sol_hora_fin = sol.getSolHoraFin().toString();
			sol_flexibilidad = sol.getSolFlexibilidad();
			sol_observacion = sol.getSolObservacion();
			sol_estado = sol.getSolEstado();
			System.out.println(sol_flexibilidad);
			if (sol.getSolEstado().equals("P")) {
				infomostrar = false;
			} else {
				infomostrar = true;
			}
			horamostrar = true;
			edicion = true;
			ediciontipo = false;
			guardaredicion = false;
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
		System.out.println("holi");

	}

	/**
	 * metodo para conocer el prodid si esta usado
	 * 
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

	/**
	 * metodo para mostrar los conductorefunacionario en solicitud Me llaMO lkc
	 * ELECTROFLOGEAUR JAJAJ
	 */
	public List<SelectItem> getListaConductorfuncionario() {
		List<SelectItem> listadoSI = new ArrayList<SelectItem>();
		listadoSI.add(new SelectItem("Ninguno", "Ninguno"));
		for (TransFuncionarioConductor t : managersol
				.findAllConductFuncionarios()) {
			if (!t.getFcoEstado().equals("I")) {
				if (per.getPerGerencia().equals(t.getFcoGerencia()))
					listadoSI.add(new SelectItem(t.getFcoId(), t
							.getFcoNombres() + " - " + t.getFcoGerencia()));
			}
		}

		return listadoSI;
	}

	/**
	 * metodo para mostrar los vehiculos en solicitud
	 * 
	 */
	public List<SelectItem> getListaVehiculo() {
		List<SelectItem> listadoSI = new ArrayList<SelectItem>();
		for (TransVehiculo t : managergest.findAllVehiculos()) {
			if (!t.getVehiEstado().equals("I")) {
				listadoSI.add(new SelectItem(t.getVehiIdplaca(), t
						.getVehiIdplaca() + " " + t.getVehiNombre()));
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
		managersol.asignarvehiculo(sol_vehi);
		return "";
	}

	/**
	 * metodo para reporte del vehiculo
	 * 
	 */
	public String reporteVehiculoConductor() {
		String r = "";
		System.out.println("Si entraaaaaaa");
		if (sol_vehi == null) {
			System.out.println("Es null");
			getListavehiculoconductbuscar().clear();
			getListavehiculoconductbuscar().addAll(
					managersol.findAllVehiculoConductor());
			r = "";
		} else {
			System.out.println("No es null");
			getListavehiculoconductbuscar().clear();
			getListavehiculoconductbuscar().addAll(
					managersol.findAllVehiculosfechacond(sol_vehi,
							new Timestamp(fi.getTime()),
							new Timestamp(ff.getTime())));
		}
		return r;
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
		sol_id_origen = null;
		sol_id_destino = null;
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
		sol_observacion = null;
		sol_estado = "P";
		infomostrar = false;
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
		usuario = ms.validarSesion("trans_solicitudesa.xhtml");
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
		sol_hora_inicio = null;
		sol_hora_fin = null;
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
	 * @return
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
	 * @return
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
	 * @return
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
	 * metodo para listar reporte vahiculo conductor
	 * 
	 * @return
	 */
	public List<TransSolicitud> getListavehiculoconductbuscar() {
		List<TransSolicitud> l1 = new ArrayList<TransSolicitud>();
		if (sol_vehi == null) {
			System.out.println("Es null");
			List<TransSolicitud> a = managersol.findAllVehiculoConductor();
			for (TransSolicitud t : a)
				l1.add(t);
		} else {
			List<TransSolicitud> a = managersol.findAllVehiculosfechacond(
					sol_vehi, new Timestamp(fi.getTime()),
					new Timestamp(ff.getTime()));
			for (TransSolicitud t : a) {
				l1.add(t);
			}
		}
		return l1;
	}

	public void BuscarPersona() {

		try {
			System.out.println(usuario);
			per = mc.funcionarioByDNI(usuario);
			sol_usuario_cedula = per.getPerNombres() + " "
					+ per.getPerApellidos();
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
		lista.add(new SelectItem(Funciones.hora_5, Funciones.hora_5));
		lista.add(new SelectItem(Funciones.hora_6, Funciones.hora_6));
		lista.add(new SelectItem(Funciones.hora_7, Funciones.hora_7));
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
		lista.add(new SelectItem(Funciones.hora_18, Funciones.hora_18));
		lista.add(new SelectItem(Funciones.hora_19, Funciones.hora_19));
		lista.add(new SelectItem(Funciones.hora_20, Funciones.hora_20));
		lista.add(new SelectItem(Funciones.hora_21, Funciones.hora_21));
		lista.add(new SelectItem(Funciones.hora_22, Funciones.hora_22));
		return lista;
	}

}
