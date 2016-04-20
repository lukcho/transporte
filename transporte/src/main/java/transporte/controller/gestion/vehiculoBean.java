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
import transporte.model.dao.entities.TransVehiculo;
import transporte.model.generic.Funciones;
import transporte.model.generic.Mensaje;
import transporte.model.manager.ManagerGestion;

@SessionScoped
@ManagedBean
public class vehiculoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private ManagerGestion managergest;

	// VEHICULO
	private String vehi_id;
	private String vehi_nombre;
	private String vehi_marca;
	private String vehi_modelo;
	private String vehi_tipo;
	private String vehi_capacidad;
	private String vehi_estado;
	private String vehi_estado_funcional;
	
	private TransVehiculo vehi;

	//mmostrar
	private boolean mostrarvehi_id;
	private boolean edicion;
	private boolean ediciontipo;
	private boolean verhorario;
	
	private List<TransVehiculo> listaVehiculo;
	
	@Inject
	SesionBean ms;
	private String usuario;

	public vehiculoBean() {
	}

	@PostConstruct
	public void ini() {
		usuario = ms.validarSesion("trans_vehiculos.xhtml");
		vehi_id = null;
		vehi_estado_funcional = "A";
		vehi_estado="A";
		vehi_capacidad = null;
		edicion = false;
		ediciontipo = false;
		mostrarvehi_id = false;
		listaVehiculo = managergest.findAllVehiculos();
	}
	
	public String getVehi_id() {
		return vehi_id;
	}

	public void setVehi_id(String vehi_id) {
		this.vehi_id = vehi_id;
	}

	public String getVehi_nombre() {
		return vehi_nombre;
	}

	public void setVehi_nombre(String vehi_nombre) {
		this.vehi_nombre = vehi_nombre;
	}

	public String getVehi_marca() {
		return vehi_marca;
	}

	public void setVehi_marca(String vehi_marca) {
		this.vehi_marca = vehi_marca;
	}

	public String getVehi_modelo() {
		return vehi_modelo;
	}

	public void setVehi_modelo(String vehi_modelo) {
		this.vehi_modelo = vehi_modelo;
	}

	public String getVehi_tipo() {
		return vehi_tipo;
	}

	public void setVehi_tipo(String vehi_tipo) {
		this.vehi_tipo = vehi_tipo;
	}

	public String getVehi_capacidad() {
		return vehi_capacidad;
	}
	
	public void setVehi_capacidad(String vehi_capacidad) {
		this.vehi_capacidad = vehi_capacidad;
	}
	
	public String getVehi_estado() {
		return vehi_estado;
	}

	public void setVehi_estado(String vehi_estado) {
		this.vehi_estado = vehi_estado;
	}

	public String getVehi_estado_funcional() {
		return vehi_estado_funcional;
	}

	public void setVehi_estado_funcional(String vehi_estado_funcional) {
		this.vehi_estado_funcional = vehi_estado_funcional;
	}

	public boolean isMostrarvehi_id() {
		return mostrarvehi_id;
	}

	public void setMostrarvehi_id(boolean mostrarvehi_id) {
		this.mostrarvehi_id = mostrarvehi_id;
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

	public List<TransVehiculo> getListaVehiculo() {
		return listaVehiculo;
	}

	public void setListaVehiculo(List<TransVehiculo> listaVehiculo) {
		this.listaVehiculo = listaVehiculo;
	}
	
	//metodo para listar los VEHICULOS
	public List<TransVehiculo> ListaVehiculosSin(){			
		List<TransVehiculo> a = managergest.findAllVehiculos();
		List<TransVehiculo> l1 = new ArrayList<TransVehiculo>();			
		for (TransVehiculo t : a ){								
				if(!t.getVehiIdplaca().equals("Ninguno")){
						l1.add(t);
			}		
		}
		return l1;
	}

	public TransVehiculo getVehi() {
		return vehi;
	}

	public void setVehi(TransVehiculo vehi) {
		this.vehi = vehi;
	}
	
	//VEHICULO
	/**
	 * accion para invocar el manager y crear vehiculo o editar el vehiculo
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
	public String crearVehiculo() {
		try {
			if (edicion) {
				Integer capa = Integer.parseInt(vehi_capacidad);
				managergest.editarVehiculo(vehi_id.trim(), vehi_nombre.trim(), vehi_marca.trim(), vehi_modelo.trim(), 
						vehi_tipo.trim(), capa, vehi_estado, vehi_estado_funcional);
				Mensaje.crearMensajeINFO("Actualizado - Modificado");
				vehi_id= null;
				vehi_nombre = null;
				vehi_marca = null;
				vehi_modelo = null;
				vehi_tipo = null;
				vehi_capacidad = null;
				vehi_estado = "A";
				vehi_estado_funcional = "A";
				ediciontipo = false;
				mostrarvehi_id= false;
				edicion = false;
				verhorario=false;
				getListaVehiculo().clear();
				getListaVehiculo().addAll(managergest.findAllVehiculos());
			} else {
					Integer capa = Integer.parseInt(vehi_capacidad);
					managergest.insertarVehiculo(vehi_id.trim(), vehi_nombre.trim(), vehi_marca.trim(), vehi_modelo.trim(), vehi_tipo.trim(), capa);
					Mensaje.crearMensajeINFO("Registrado - Creado");
					vehi_id= null;
					vehi_nombre = null;
					vehi_marca = null;
					vehi_modelo = null;
					vehi_tipo = null;
					vehi_capacidad = null;
					vehi_estado = "A";
					vehi_estado_funcional = "A";
					ediciontipo = false;
					mostrarvehi_id= false;
					edicion = false;
					verhorario=false;
					getListaVehiculo().clear();
					getListaVehiculo().addAll(managergest.findAllVehiculos());
			}
			return "trans_vehiculos?faces-redirect=true";
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error al crear veh�culo", null));
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e
							.getMessage(), null));
			return "";
		}
	}
	
	public void abrirDialog(){
		if(edicion == true)
		{
				RequestContext.getCurrentInstance().execute("PF('gu').show();");
		}else
		if (!averiguarVehiid(vehi_id)) {
				RequestContext.getCurrentInstance().execute("PF('gu').show();");
		}
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
	public String cargarVehiculo(TransVehiculo vehi) {
		try {
			vehi_id=vehi.getVehiIdplaca();
			vehi_nombre = vehi.getVehiNombre();
			vehi_marca = vehi.getVehiMarca();
			vehi_modelo = vehi.getVehiModelo();
			vehi_tipo = vehi.getVehiTipo();
			vehi_capacidad = vehi.getVehiCapacidad().toString();
			vehi_estado = vehi.getVehiEstado();
			vehi_estado_funcional = vehi.getVehiEstadoFuncional();
			edicion = true;
			mostrarvehi_id = true;
			ediciontipo = false;
			return "trans_nvehiculo?faces-redirect=true";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * activar y desactivar estado vehiculo
	 * 
	 * @param vehi_id
	 * @throws Exception
	 */
	public String cambiarEstadoVehi() {
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("INFORMACION",
					managergest.cambioEstadoVerhiculo(getVehi().getVehiIdplaca())));
			getListaVehiculo().clear();
			getListaVehiculo().addAll(managergest.findAllVehiculos());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "";
	}

	public void cambiarEstadovehi(TransVehiculo vehi) {
		setVehi(vehi);
		RequestContext.getCurrentInstance().execute("PF('ce').show();");
		System.out.println("holi");

	}
	
	/**
	 * metodo para conocer el prodid si esta usado
	 * 
	 */
	public boolean averiguarVehiid(String vehi_id) {
		Integer t = 0;
		boolean r = false;
		List<TransVehiculo> pro = managergest.findAllVehiculos();
		for (TransVehiculo y : pro) {
			if (y.getVehiIdplaca().equals(vehi_id)) {
				System.out.println("si entra1");
				t = 1;
				r = true;
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"El codigo del Veh�culo existe.", null));
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
	 * Lista de estadosfuncionales
	 * 
	 * @return lista de items de estadosfuncionales
	 */
	public List<SelectItem> getlistEstadosfuncional() {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		lista.add(new SelectItem(Funciones.estadoActivo, Funciones.estadoActivo
				+ " : " + Funciones.valorEstadoActivo));
		lista.add(new SelectItem(Funciones.estadoInactivo,
				Funciones.estadoInactivo + " : "
						+ Funciones.valorEstadoInactivo));
		return lista;
	}

	
	/**
	 * Redirecciona a la pagina de creacion de vehiculos
	 * 
	 * @return
	 */
	public String nuevoVehiculo() {
		vehi_id= null;
		vehi_nombre = null;
		vehi_marca = null;
		vehi_modelo = null;
		vehi_tipo = null;
		vehi_capacidad = null;
		vehi_estado = "A";
		vehi_estado_funcional = "A";
		ediciontipo = false;
		mostrarvehi_id= false;
		edicion = false;
		verhorario=false;
		return "trans_nvehiculo?faces-redirect=true";
	}
	
	/**
	 * limpia la informacion de horario
	 * 
	 * @return
	 * @throws Exception
	 */
	public String volverVehiculo() throws Exception {
		// limpiar datos
		vehi_id= null;
		vehi_nombre = null;
		vehi_marca = null;
		vehi_modelo = null;
		vehi_tipo = null;
		vehi_capacidad = null;
		vehi_estado = "A";
		vehi_estado_funcional = "A";
		ediciontipo = false;
		mostrarvehi_id= false;
		edicion = false;
		getListaVehiculo().clear();
		getListaVehiculo().addAll(managergest.findAllVehiculos());
		return "trans_vehiculos?faces-redirect=true";
	}
}
