package transporte.controller.gestion;

import java.io.Serializable;
import java.util.ArrayList;
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
import transporte.model.dao.entities.TransConductore;
import transporte.model.generic.Funciones;
import transporte.model.generic.Mensaje;
import transporte.model.manager.ManagerGestion;

@SessionScoped
@ManagedBean
public class conductorBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private ManagerGestion managergest;

	// VEHICULO
	private String cond_cedula;
	private String cond_nombre;
	private String cond_apellido;
	private String cond_telefono;
	private String cond_estado;
	private String cond_estadonombre;
	private String cond_correo;

	private TransConductore cond;

	// mostrar
	private boolean mostrarcond_id;
	private boolean edicion;
	private boolean ediciontipo;

	private List<TransConductore> listaConductores;

	private String usuario;

	@Inject
	SesionBean ms;

	public conductorBean() {
	}

	@PostConstruct
	public void ini() {
		cond_cedula = null;
		cond_estado = "A";
		cond_estadonombre = "";
		cond_correo = "";
		edicion = false;
		ediciontipo = false;
		mostrarcond_id = false;
		usuario = ms.validarSesion("trans_conductores.xhtml");
		listaConductores = managergest.findAllConductores();
	}

	public String getUsuario() {
		return usuario;
	}

	public String getCond_correo() {
		return cond_correo;
	}

	public void setCond_correo(String cond_correo) {
		this.cond_correo = cond_correo;
	}

	public ManagerGestion getManagergest() {
		return managergest;
	}

	public void setManagergest(ManagerGestion managergest) {
		this.managergest = managergest;
	}

	public String getCond_cedula() {
		return cond_cedula;
	}

	public void setCond_cedula(String cond_cedula) {
		this.cond_cedula = cond_cedula;
	}

	public String getCond_nombre() {
		return cond_nombre;
	}

	public void setCond_nombre(String cond_nombre) {
		this.cond_nombre = cond_nombre;
	}

	public String getCond_apellido() {
		return cond_apellido;
	}

	public void setCond_apellido(String cond_apellido) {
		this.cond_apellido = cond_apellido;
	}

	public String getCond_telefono() {
		return cond_telefono;
	}

	public void setCond_telefono(String cond_telefono) {
		this.cond_telefono = cond_telefono;
	}

	public String getCond_estado() {
		return cond_estado;
	}

	public void setCond_estado(String cond_estado) {
		this.cond_estado = cond_estado;
	}

	public String getCond_estadonombre() {
		return cond_estadonombre;
	}

	public void setCond_estadonombre(String cond_estadonombre) {
		this.cond_estadonombre = cond_estadonombre;
	}

	public TransConductore getCond() {
		return cond;
	}

	public void setCond(TransConductore cond) {
		this.cond = cond;
	}

	public boolean isMostrarcond_id() {
		return mostrarcond_id;
	}

	public void setMostrarcond_id(boolean mostrarcond_id) {
		this.mostrarcond_id = mostrarcond_id;
	}

	public boolean isEdicion() {
		return edicion;
	}

	public void setEdicion(boolean edicion) {
		this.edicion = edicion;
	}

	public boolean isEdiciontipo() {
		return ediciontipo;
	}

	public void setEdiciontipo(boolean ediciontipo) {
		this.ediciontipo = ediciontipo;
	}

	public List<TransConductore> getListaConductores() {
		return listaConductores;
	}

	/**
	 * metodo para listar los conductores
	 * 
	 * @return
	 */
	public List<TransConductore> ListaConductoresSin() {
		List<TransConductore> a = managergest.findAllConductores();
		List<TransConductore> l1 = new ArrayList<TransConductore>();
		for (TransConductore t : a) {
			if (!t.getCondCedula().equals("Ninguno")) {
				l1.add(t);
			}
		}
		return l1;
	}

	/**
	 * metodo para editar la lista de los conductores
	 * 
	 * @return
	 */
	public void setListaConductores(List<TransConductore> listaConductores) {
		this.listaConductores = listaConductores;
	}

	/**
	 * accion para invocar el manager y crear conductor o editar el conductor
	 * 
	 * @param cond_cedula
	 * @param cond_nombre
	 * @param cond_apellido
	 * @param cond_telefono
	 * @param cond_correo
	 * @param pro_precio
	 * @param cond_estado
	 * @throws Exception
	 */
	public String crearConductor() {
		try {
			if (edicion) {
				managergest.editarConductor(cond_cedula.trim(),
						cond_nombre.trim(), cond_apellido.trim(),
						cond_telefono.trim(), cond_correo.trim(), cond_estado);
				getListaConductores().clear();
				getListaConductores().addAll(managergest.findAllConductores());
				Mensaje.crearMensajeINFO("Actualizado - Modificado");
			} else {

				managergest.insertarConductor(cond_cedula.trim(),
						cond_nombre.trim(), cond_apellido.trim(),
						cond_telefono.trim(), cond_correo.trim());
				getListaConductores().clear();
				getListaConductores().addAll(managergest.findAllConductores());
				Mensaje.crearMensajeINFO("Registrado - Creado");
			}
			return "trans_conductores?faces-redirect=true";
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error al crear conductor", null));
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e
							.getMessage(), null));
			return "";
		}
	}

	/**
	 * accion para cargar los datos del conductor en el formulario
	 * 
	 * @param cond_cedula
	 * @param cond_nombre
	 * @param cond_apellido
	 * @param cond_telefono
	 * @param cond_estado
	 * @param cond_correo
	 * @throws Exception
	 */
	public String cargarConductor(TransConductore cond) {
		try {
			cond_cedula = cond.getCondCedula();
			cond_nombre = cond.getCondNombre();
			cond_apellido = cond.getCondApellido();
			cond_telefono = cond.getCondTelefono();
			cond_estado = cond.getCondEstado();
			cond_correo = cond.getCondCorreo();
			edicion = true;
			mostrarcond_id = true;
			ediciontipo = false;
			return "trans_nconductor?faces-redirect=true";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * activar y desactivar estado conductor
	 * 
	 * @throws Exception
	 */
	public String cambiarEstadoCondu() {
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(
					null,
					new FacesMessage("INFORMACION", managergest
							.cambioEstadoConductor(getCond().getCondCedula())));
			getListaConductores().clear();
			getListaConductores().addAll(managergest.findAllConductores());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "";
	}

	/**
	 * metodo para cambiar el estado del conductor
	 * 
	 * @throws Exception
	 */
	public void cambiarEstadoCon(TransConductore cond) {
		setCond(cond);
		RequestContext.getCurrentInstance().execute("PF('ce').show();");
		System.out.println("holi");

	}

	/**
	 * metodo para conocer el conductor si esta usado
	 * 
	 */
	public boolean averiguarConid(String cond_id) {
		Integer t = 0;
		boolean r = false;
		List<TransConductore> cond = managergest.findAllConductores();
		for (TransConductore y : cond) {
			if (y.getCondCedula().equals(cond_id)) {
				System.out.println("si entra1");
				t = 1;
				r = true;
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"La cédula del conductor existe.", null));
			}
		}
		if (t == 0) {
			r = false;
		}
		return r;
	}

	/**
	 * metodo para conocer el correo si esta usado
	 * 
	 */
	public boolean averiguarCorr(String cond_correo) {
		Integer t = 0;
		boolean r = false;
		List<TransConductore> cond = managergest.findAllConductores();
		for (TransConductore y : cond) {
			if (y.getCondCorreo().equals(cond_correo)) {
				System.out.println("si entra1");
				t = 1;
				r = true;
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"El correo del conductor existe.", null));
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
		lista.add(new SelectItem(Funciones.estadoActivo, Funciones.estadoActivo
				+ " : " + Funciones.valorEstadoActivo));
		lista.add(new SelectItem(Funciones.estadoInactivo,
				Funciones.estadoInactivo + " : "
						+ Funciones.valorEstadoInactivo));
		return lista;
	}

	/**
	 * Redirecciona a la pagina de creacion de conductores
	 * 
	 * @return
	 */
	public String nuevoConductor() {
		cond_cedula = null;
		cond_nombre = null;
		cond_correo = null;
		cond_apellido = null;
		cond_telefono = null;
		cond_estado = "A";
		ediciontipo = false;
		mostrarcond_id = false;
		edicion = false;
		return "trans_nconductor?faces-redirect=true";
	}

	/**
	 * limpia la informacion del conductor
	 * 
	 * @return
	 * @throws Exception
	 */
	public String volverConductor() throws Exception {
		// limpiar datos
		cond_cedula = null;
		cond_nombre = null;
		cond_apellido = null;
		cond_correo = null;
		cond_telefono = null;
		cond_estado = "A";
		ediciontipo = false;
		mostrarcond_id = false;
		edicion = false;
		getListaConductores().clear();
		getListaConductores().addAll(managergest.findAllConductores());
		return "trans_conductores?faces-redirect=true";
	}

	/**
	 * abre el dialogo
	 * 
	 * @throws Exception
	 */
	public void abrirDialog() {
		if (edicion == true) {
			RequestContext.getCurrentInstance().execute("PF('gu').show();");
		} else if (!averiguarConid(cond_cedula)) {
			if (!averiguarCorr(cond_correo))
				RequestContext.getCurrentInstance().execute("PF('gu').show();");
		}
	}
	
}
