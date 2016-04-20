package transporte.controller.gestion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
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
import transporte.model.dao.entities.PersonaFuncionario;
import transporte.model.dao.entities.TransFuncionarioConductor;
import transporte.model.generic.Funciones;
import transporte.model.generic.Mensaje;
import transporte.model.manager.ManagerCarga;
import transporte.model.manager.ManagerGestion;

@SessionScoped
@ManagedBean
public class conductorfunBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private ManagerGestion managergest;

	private ManagerCarga mangcar;

	// VEHICULO
	private String condf_cedula;
	private String condf_nombre;
	private String condf_gerencia;
	private String condf_direccion;
	private String condf_estado;
	private String condf_telefono;
	private String condf_correo;

	private TransFuncionarioConductor condf;

	// mostrar
	private boolean mostrarcondf_id;
	private boolean edicion;
	private boolean ediciontipo;

	private boolean ocultarbusqueda;

	private List<TransFuncionarioConductor> listaConductoresFun;

	private List<PersonaFuncionario> listafuncionariodebase;

	private HashMap<String, PersonaFuncionario> hashpersonfun;

	@Inject
	SesionBean ms;
	private String usuario;

	private Persona per;
	private PersonaFuncionario perfun;

	public conductorfunBean() {
	}

	@PostConstruct
	public void ini() {
		mangcar = new ManagerCarga();
		usuario = ms.validarSesion("trans_conductoresfun.xhtml");
 		condf_cedula = null;
		condf_estado = "A";
		condf_direccion = "";
		condf_telefono = "";
		condf_correo = "";
		condf_gerencia = "";
		edicion = false;
		ediciontipo = false;
		ocultarbusqueda = true;
		mostrarcondf_id = false;
		listaConductoresFun = managergest.findAllConductoresFuncionarios();
		hashpersonfun = new HashMap<String, PersonaFuncionario>();

	}

	public String getCondf_correo() {
		return condf_correo;
	}

	public void setCondf_correo(String condf_correo) {
		this.condf_correo = condf_correo;
	}

	public String getCondf_telefono() {
		return condf_telefono;
	}

	public void setCondf_telefono(String condf_telefono) {
		this.condf_telefono = condf_telefono;
	}

	public boolean isOcultarbusqueda() {
		return ocultarbusqueda;
	}

	public void setOcultarbusqueda(boolean ocultarbusqueda) {
		this.ocultarbusqueda = ocultarbusqueda;
	}

	public List<PersonaFuncionario> getListafuncionariodebase() {
		return listafuncionariodebase;
	}

	public void setListafuncionariodebase(
			List<PersonaFuncionario> listafuncionariodebase) {
		this.listafuncionariodebase = listafuncionariodebase;
	}

	public PersonaFuncionario getPerfun() {
		return perfun;
	}

	public void setPerfun(PersonaFuncionario perfun) {
		this.perfun = perfun;
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
		String r="";
		try {
			if (edicion) {
				managergest.editarConductorFun(condf_cedula.trim(),condf_correo.trim(),condf_telefono.trim(), condf_estado);
				getListaConductoresFun().clear();
				getListaConductoresFun().addAll(
						managergest.findAllConductoresFuncionarios());
				Mensaje.crearMensajeINFO("Actualizado - Modificado");
				r= "trans_conductoresfun?faces-redirect=true";
			} else {
				if (averiguarConFunId(perfun.getPerDNI())==false)
				{
				managergest.insertarConductorFun(perfun);
				getListaConductoresFun().clear();
				getListaConductoresFun().addAll(
						managergest.findAllConductoresFuncionarios());
				Mensaje.crearMensajeINFO("Registrado - Creado");
				r= "trans_conductoresfun?faces-redirect=true";
				}
				else
				{
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_INFO,
									"El conductor funcionario existe.", null));
					r="";
				}

			}
			return r;
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

	public void abrirDialog() {
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
			condf_telefono = condf.getFcoTelefono();
			condf_correo = condf.getFcoCorreo();
			edicion = true;
			mostrarcondf_id = true;
			ediciontipo = false;
			ocultarbusqueda = false;
			System.out.println(usuario);
			per = mangcar.funcionarioByDNI(usuario);
	//		listafuncionariodebase = mangcar.funcionarioByGerencia(perfun.getPerGerencia());
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
		List<TransFuncionarioConductor> cond = managergest
				.findAllConductoresFuncionarios();
		for (TransFuncionarioConductor y : cond) {
			if (y.getFcoId().equals(conf_id)) {
				System.out.println("si entra1");
				t = 1;
				r = true;
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
		try {
			condf_cedula = null;
			condf_nombre = null;
			condf_gerencia = null;
			condf_direccion = null;
			condf_estado = "A";
			ediciontipo = false;
			mostrarcondf_id = false;
			ocultarbusqueda = true;
			edicion = false;
			ediciontipo = true;
			BuscarPersona();
			System.out.println(usuario);
			per = mangcar.funcionarioByDNI(usuario);
			listafuncionariodebase = mangcar.funcionarioByGerencia(per
					.getPerGerencia());
			hashpersonfun.clear();
			for (PersonaFuncionario p : listafuncionariodebase) {
				hashpersonfun.put(p.getPerDNI(), p);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		ocultarbusqueda = true;
		ediciontipo = false;
		mostrarcondf_id = false;
		edicion = false;
		getListaConductoresFun().clear();
		getListaConductoresFun().addAll(
				managergest.findAllConductoresFuncionarios());
		return "trans_conductoresfun?faces-redirect=true";
	}

	/**
	 * Setea los datos del BEAN al cambiar de usuario
	 */
	public void liberarDatos() {
		condf_cedula = null;
		condf_nombre = null;
		condf_gerencia = null;
		condf_direccion = null;
	}

	public void asignarFuncionario() {
		perfun = hashpersonfun.get(getCondf_cedula());
	}
	
	public void BuscarPersona() {
		try {
			per = mangcar.funcionarioByDNI(usuario);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
