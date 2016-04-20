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
import transporte.model.generic.Funciones;
import transporte.model.dao.entities.TransLugare;
import transporte.model.generic.Mensaje;
import transporte.model.manager.ManagerGestion;

@SessionScoped
@ManagedBean
public class lugarBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private ManagerGestion managergest;

	// Lugares
	private Integer lug_id;
	private String lug_nombre;
	private String lug_ciudad;
	private String lug_estado;
	
	private TransLugare lug;

	//mmostrar
	private boolean mostrarlug_id;
	private boolean edicion;
	private boolean ediciontipo;
	private boolean verhorario;
	
	private List<TransLugare> listaLugares;

	@Inject
	SesionBean ms;
	private String usuario;
	
	public lugarBean() {
	}

	@PostConstruct
	public void ini() {
		usuario = ms.validarSesion("trans_lugares.xhtml");
		usuario = ms.validarSesion("trans_nlugar.xhtml");
		lug_id = null;
		lug_estado="A";
		lug_nombre="";
		lug_ciudad="";
		edicion = false;
		ediciontipo = false;
		mostrarlug_id = false;
		listaLugares = managergest.findAllLugares();
	}
	
	public Integer getLug_id() {
		return lug_id;
	}
	
	public void setLug_id(Integer lug_id) {
		this.lug_id = lug_id;
	}

	public String getLug_nombre() {
		return lug_nombre;
	}

	public void setLug_nombre(String lug_nombre) {
		this.lug_nombre = lug_nombre;
	}

	public String getLug_ciudad() {
		return lug_ciudad;
	}

	public void setLug_ciudad(String lug_ciudad) {
		this.lug_ciudad = lug_ciudad;
	}

	public String getLug_estado() {
		return lug_estado;
	}

	public void setLug_estado(String lug_estado) {
		this.lug_estado = lug_estado;
	}

	public boolean isMostrarlug_id() {
		return mostrarlug_id;
	}

	public void setMostrarlug_id(boolean mostrarlug_id) {
		this.mostrarlug_id = mostrarlug_id;
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

	public boolean isVerhorario() {
		return verhorario;
	}

	public void setVerhorario(boolean verhorario) {
		this.verhorario = verhorario;
	}

	public List<TransLugare> getListaLugares() {
		return listaLugares;
	}

	public void setListaLugares(List<TransLugare> listaLugares) {
		this.listaLugares = listaLugares;
	}
	
	public TransLugare getLug() {
		return lug;
	}

	public void setLug(TransLugare lug) {
		this.lug = lug;
	}
	
	//VEHICULO
	/**
	 * accion para invocar el manager y crear Lugar o editar el Lugar
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
	public String crearLugar() {
		try {
			if (edicion) {
				managergest.editarLugar(lug_id, lug_nombre.trim(), lug_ciudad.trim(), lug_estado.trim());
				getListaLugares().clear();
				getListaLugares().addAll(managergest.findAllLugares());
				Mensaje.crearMensajeINFO("Actualizado - Modificado");
				lug_id=null;
				lug_nombre=null;
				lug_ciudad=null;
				lug_estado="A";
			} else {
					managergest.insertarLugar(lug_nombre.trim(), lug_ciudad.trim());
					Mensaje.crearMensajeINFO("Registrado - Creado");
					getListaLugares().clear();
					getListaLugares().addAll(managergest.findAllLugares());
					lug_id=null;
					lug_nombre=null;
					lug_ciudad=null;
					lug_estado="A";
				}
			return "trans_lugares?faces-redirect=true";
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error al crear vehiculo", null));
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
	public String cargarLugar(TransLugare lug) {
		try {
			lug_id=lug.getLugId();
			lug_nombre = lug.getLugNombre();
			lug_ciudad = lug.getLugCiudad();
			lug_estado = lug.getLugEstado();
			edicion = true;
			ediciontipo = false;
			return "trans_nlugar?faces-redirect=true";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * activar y desactivar estado Lugar
	 * 
	 * @param vehi_id
	 * @throws Exception
	 */
	public String cambiarEstadoLugar() {
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("INFORMACION",
					managergest.cambioEstadoLugar(getLug().getLugId())));
			getListaLugares().clear();
			getListaLugares().addAll(managergest.findAllLugares());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "";
	}

	public void cambiarEstadoLugara(TransLugare cond) {
		setLug(cond);
		RequestContext.getCurrentInstance().execute("PF('ce').show();");
		System.out.println("holi");

	}
	
	/**
	 * metodo para conocer el lug_id si esta usado
	 * 
	 */
	public boolean averiguarLugarid(Integer lug_id) {
		Integer t = 0;
		boolean r = false;
		List<TransLugare> lug = managergest.findAllLugares();
		for (TransLugare y : lug) {
			if (y.getLugId().equals(lug_id)) {
				System.out.println("si entra1");
				t = 1;
				r = true;
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"El codigo del lugar existe.", null));
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
	 * Redirecciona a la pagina de creacion de lugares
	 * 
	 * @return
	 */
	public String nuevoLugar() {
		lug_id=null;
		lug_nombre=null;
		lug_ciudad=null;
		lug_estado="A";
		verhorario = false;
		mostrarlug_id = false;
		edicion = false;
		return "trans_nlugar?faces-redirect=true";
	}
	
	
	/**
	 * limpia la informacion de lugar
	 * 
	 * @return
	 * @throws Exception
	 */
	public String volverLugar() throws Exception {
		// limpiar datos
		lug_id= null;
		lug_nombre = null;
		lug_ciudad = null;
		lug_estado = "A";
		ediciontipo = false;
		mostrarlug_id= false;
		edicion = false;
		getListaLugares().clear();
		getListaLugares().addAll(managergest.findAllLugares());
		return "trans_lugares?faces-redirect=true";
	}
}
