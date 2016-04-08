package transporte.controller.gestion;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;


import org.primefaces.context.RequestContext;


import transporte.model.dao.entities.TransConductore;
import transporte.model.dao.entities.TransLugare;
import transporte.model.dao.entities.TransVehiculo;
import transporte.model.generic.Mensaje;
import transporte.model.manager.ManagerGestion;

@SessionScoped
@ManagedBean
public class lugarBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private ManagerGestion managergest;

	// VEHICULO
	private String vehi_id;
	private String vehi_nombre;
	private String vehi_marca;
	private String vehi_modelo;
	private String vehi_tipo;
	private Integer vehi_capacidad;
	private String vehi_estado;
	private String vehi_estado_funcional;
	
	private TransVehiculo vehi;
	private TransConductore cond;
	private TransLugare lug;

	//mmostrar
	private boolean mostrarvehi_id;
	private boolean edicion;
	private boolean ediciontipo;
	private boolean verhorario;
	
	private List<TransVehiculo> listaVehiculo;
	private List<TransLugare> listaLugares;
	private List<TransConductore> listaConductores;

	public lugarBean() {
	}

	@PostConstruct
	public void ini() {
		vehi_id = null;
		vehi_estado_funcional = "A";
		vehi_estado="A";
		vehi_capacidad = 0;
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

	public Integer getVehi_capacidad() {
		return vehi_capacidad;
	}

	public void setVehi_capacidad(Integer vehi_capacidad) {
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

	public List<TransLugare> getListaLugares() {
		return listaLugares;
	}

	public void setListaLugares(List<TransLugare> listaLugares) {
		this.listaLugares = listaLugares;
	}

	public List<TransConductore> getListaConductores() {
		return listaConductores;
	}

	public void setListaConductores(List<TransConductore> listaConductores) {
		this.listaConductores = listaConductores;
	}
	
	public TransVehiculo getVehi() {
		return vehi;
	}

	public void setVehi(TransVehiculo vehi) {
		this.vehi = vehi;
	}

	public TransConductore getCond() {
		return cond;
	}

	public void setCond(TransConductore cond) {
		this.cond = cond;
	}

	public TransLugare getLug() {
		return lug;
	}

	public void setLug(TransLugare lug) {
		this.lug = lug;
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
		String r = "";
		try {
			if (edicion) {
				managergest.editarVehiculo(vehi_id, vehi_nombre, vehi_marca, vehi_modelo, vehi_tipo, vehi_capacidad, vehi_estado, vehi_estado_funcional);
				Mensaje.crearMensajeINFO("Actualizado - Modificado");
			} else {
				if (!averiguarVehiid(vehi_id)) {
					managergest.insertarVehiculo(vehi_id, vehi_nombre, vehi_marca, vehi_modelo, vehi_tipo, vehi_capacidad);
					Mensaje.crearMensajeINFO("Registrado - Creado");
				}
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error al crear vehiculo", null));
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e
							.getMessage(), null));
		}
		return r;
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
	public String cargarProducto(TransVehiculo vehi) {
		try {
			vehi_id=vehi.getVehiIdplaca();
			vehi_nombre = vehi.getVehiNombre();
			vehi_marca = vehi.getVehiMarca();
			vehi_modelo = vehi.getVehiModelo();
			vehi_tipo = vehi.getVehiTipo();
			vehi_capacidad = vehi.getVehiCapacidad();
			vehi_estado = vehi.getVehiEstado();
			vehi_estado_funcional = vehi.getVehiEstadoFuncional();
			edicion = true;
			ediciontipo = false;
			return "nvehiculo?faces-redirect=true";
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
								"El codigo del producto existe.", null));
			}
		}
		if (t == 0) {
			r = false;
		}
		return r;
	}

	// conductores
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
	public String crearConductor() {
		String r = "";
		try {
			if (edicion) {
				managergest.editarVehiculo(vehi_id, vehi_nombre, vehi_marca, vehi_modelo, vehi_tipo, vehi_capacidad, vehi_estado, vehi_estado_funcional);
				Mensaje.crearMensajeINFO("Actualizado - Modificado");
			} else {
				if (!averiguarVehiid(vehi_id)) {
					managergest.insertarVehiculo(vehi_id, vehi_nombre, vehi_marca, vehi_modelo, vehi_tipo, vehi_capacidad);
					Mensaje.crearMensajeINFO("Registrado - Creado");
				}
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error al crear vehiculo", null));
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e
							.getMessage(), null));
		}
		return r;
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
	public String cargarConductor(TransVehiculo vehi) {
		try {
			vehi_id=vehi.getVehiIdplaca();
			vehi_nombre = vehi.getVehiNombre();
			vehi_marca = vehi.getVehiMarca();
			vehi_modelo = vehi.getVehiModelo();
			vehi_tipo = vehi.getVehiTipo();
			vehi_capacidad = vehi.getVehiCapacidad();
			vehi_estado = vehi.getVehiEstado();
			vehi_estado_funcional = vehi.getVehiEstadoFuncional();
			edicion = true;
			ediciontipo = false;
			return "nvehiculo?faces-redirect=true";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * activar y desactivar estado conductor
	 * 
	 * @param vehi_id
	 * @throws Exception
	 */
	public String cambiarEstadoCon() {
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

	public void cambiarEstadoCon(TransVehiculo vehi) {
		setVehi(vehi);
		RequestContext.getCurrentInstance().execute("PF('ce').show();");
		System.out.println("holi");

	}
	
	/**
	 * metodo para conocer el conductor si esta usado
	 * 
	 */
	public boolean averiguarConid(String vehi_id) {
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
								"El codigo del producto existe.", null));
			}
		}
		if (t == 0) {
			r = false;
		}
		return r;
	}
}
