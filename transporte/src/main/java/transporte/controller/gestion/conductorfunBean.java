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
import transporte.model.dao.entities.TransFuncionarioConductor;
import transporte.model.generic.Funciones;
import transporte.model.generic.Mensaje;
import transporte.model.manager.ManagerGestion;

@SessionScoped
@ManagedBean
public class conductorfunBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private ManagerGestion managergest;

	// VEHICULO
	private String condf_cedula;
	private String condf_nombre;
	private String condf_gerencia;
	private String condf_direccion;
	private String condf_estado;

	private TransFuncionarioConductor condf;

	// mostrar
	private boolean mostrarcondf_id;
	private boolean edicion;
	private boolean ediciontipo;

	private List<TransFuncionarioConductor> listaConductoresFun;

	@Inject
	SesionBean ms;
	private String usuario;

	public conductorfunBean() {
	}

	@PostConstruct
	public void ini() {
		usuario = ms.validarSesion("trans_conductoresfun.xhtml");
		condf_cedula = null;
		condf_estado = "A";
		condf_direccion = "";
		condf_gerencia = "";
		edicion = false;
		ediciontipo = false;
		mostrarcondf_id = false;
		listaConductoresFun = managergest.findAllConductoresFuncionarios();
	}

	public ManagerGestion getManagergest() {
		return managergest;
	}

	public void setManagergest(ManagerGestion managergest) {
		this.managergest = managergest;
	}

	public String getCondf_cedula() {
		return condf_cedula;
	}

	public void setCondf_cedula(String condf_cedula) {
		this.condf_cedula = condf_cedula;
	}

	public String getCondf_nombre() {
		return condf_nombre;
	}

	public void setCondf_nombre(String condf_nombre) {
		this.condf_nombre = condf_nombre;
	}

	public String getCondf_gerencia() {
		return condf_gerencia;
	}

	public void setCondf_gerencia(String condf_gerencia) {
		this.condf_gerencia = condf_gerencia;
	}

	public String getCondf_direccion() {
		return condf_direccion;
	}

	public void setCondf_direccion(String condf_direccion) {
		this.condf_direccion = condf_direccion;
	}

	public String getCondf_estado() {
		return condf_estado;
	}

	public void setCondf_estado(String condf_estado) {
		this.condf_estado = condf_estado;
	}

	public TransFuncionarioConductor getCondf() {
		return condf;
	}

	public void setCondf(TransFuncionarioConductor condf) {
		this.condf = condf;
	}

	public boolean isMostrarcondf_id() {
		return mostrarcondf_id;
	}

	public void setMostrarcondf_id(boolean mostrarcondf_id) {
		this.mostrarcondf_id = mostrarcondf_id;
	}

	public List<TransFuncionarioConductor> getListaConductoresFun() {
		return listaConductoresFun;
	}

	public void setListaConductoresFun(
			List<TransFuncionarioConductor> listaConductoresFun) {
		this.listaConductoresFun = listaConductoresFun;
	}

	// metodo para listar los conductores
	public List<TransFuncionarioConductor> ListaConductoresFunSin() {
		List<TransFuncionarioConductor> a = managergest
				.findAllConductoresFuncionarios();
		List<TransFuncionarioConductor> l1 = new ArrayList<TransFuncionarioConductor>();
		for (TransFuncionarioConductor t : a) {
			if (!t.getFcoId().equals("Ninguno")) {
				l1.add(t);
			}
		}
		return l1;
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

	/**
	 * accion para invocar el manager y crear conductor o editar el conductor
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
	public String crearConductorFun() {
		try {
			if (edicion) {
				managergest.editarConductorFun(condf_cedula.trim(), condf_nombre.trim(),
						condf_gerencia.trim(), condf_direccion.trim(), condf_estado);
				getListaConductoresFun().clear();
				getListaConductoresFun().addAll(
						managergest.findAllConductoresFuncionarios());
				Mensaje.crearMensajeINFO("Actualizado - Modificado");
			} else {
				if (!averiguarConFunId(condf_cedula)) {
					managergest.insertarConductorFun(condf_cedula.trim(),
							condf_nombre.trim(), condf_gerencia.trim(), condf_direccion.trim());
					getListaConductoresFun().clear();
					getListaConductoresFun().addAll(
							managergest.findAllConductoresFuncionarios());
					Mensaje.crearMensajeINFO("Registrado - Creado");
				}
			}
			return "trans_conductoresfun?faces-redirect=true";
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error al crear conductor funcionario", null));
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e
							.getMessage(), null));
			return "";
		}
	}
	
	public void abrirDialog(){
		RequestContext.getCurrentInstance().execute("PF('gu').show();");
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
	public String cargarConductorFun(TransFuncionarioConductor condf) {
		try {
			condf_cedula = condf.getFcoId();
			condf_nombre = condf.getFcoNombres();
			condf_direccion = condf.getFcoDireccion();
			condf_gerencia = condf.getFcoGerencia();
			condf_estado = condf.getFcoEstado();
			edicion = true;
			mostrarcondf_id = true;
			ediciontipo = false;
			return "trans_nconductorfun?faces-redirect=true";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * activar y desactivar estado conductor
	 * 
	 * @param cond_cedula
	 * @throws Exception
	 */
	public String cambiarEstadoConduFun() {
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(
					null,
					new FacesMessage("INFORMACION", managergest
							.cambioEstadoConductorFun(getCondf().getFcoId())));
			getListaConductoresFun().clear();
			getListaConductoresFun().addAll(
					managergest.findAllConductoresFuncionarios());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "";
	}

	public void cambiarEstadoConFun(TransFuncionarioConductor condf) {
		setCondf(condf);
		RequestContext.getCurrentInstance().execute("PF('ce').show();");
		System.out.println("holi");

	}

	/**
	 * metodo para conocer el conductor si esta usado
	 * 
	 */
	public boolean averiguarConFunId(String conf_id) {
		Integer t = 0;
		boolean r = false;
		List<TransConductore> cond = managergest.findAllConductores();
		for (TransConductore y : cond) {
			if (y.getCondCedula().equals(conf_id)) {
				System.out.println("si entra1");
				t = 1;
				r = true;
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"El codigo del conductor funcionario existe.",
								null));
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
	public String nuevoConductorFun() {
		condf_cedula = null;
		condf_nombre = null;
		condf_gerencia = null;
		condf_direccion = null;
		condf_estado = "A";
		ediciontipo = false;
		mostrarcondf_id = false;
		edicion = false;
		return "trans_nconductorfun?faces-redirect=true";
	}

	/**
	 * limpia la informacion de horario
	 * 
	 * @return
	 * @throws Exception
	 */
	public String volverConductorFun() throws Exception {
		// limpiar datos
		condf_cedula = null;
		condf_nombre = null;
		condf_gerencia = null;
		condf_direccion = null;
		condf_estado = "A";
		ediciontipo = false;
		mostrarcondf_id = false;
		edicion = false;
		getListaConductoresFun().clear();
		getListaConductoresFun().addAll(
				managergest.findAllConductoresFuncionarios());
		return "trans_conductoresfun?faces-redirect=true";
	}
}
