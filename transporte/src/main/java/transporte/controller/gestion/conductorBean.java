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
	
	private TransConductore cond;

	//mostrar
	private boolean mostrarcond_id;
	private boolean edicion;
	private boolean ediciontipo;
	
	private List<TransConductore> listaConductores;

	public conductorBean() {
	}

	@PostConstruct
	public void ini() {
		cond_cedula = null;
		cond_estado="A";
		edicion = false;
		ediciontipo = false;
		mostrarcond_id = false;
		listaConductores = managergest.findAllConductores();
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

	public void setListaConductores(List<TransConductore> listaConductores) {
		this.listaConductores = listaConductores;
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
	public String crearConductor() {
		String r = "";
		try {
			if (edicion) {
				managergest.editarConductor(cond_cedula, cond_nombre, cond_apellido, cond_telefono, cond_estado);
				Mensaje.crearMensajeINFO("Actualizado - Modificado");
				r= "conductores?faces-redirect=true";
			} else {
				if (!averiguarConid(cond_cedula)) {
					managergest.insertarConductor(cond_cedula, cond_nombre, cond_apellido, cond_telefono);
					Mensaje.crearMensajeINFO("Registrado - Creado");
					r= "conductores?faces-redirect=true";
				}
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error al crear conductor", null));
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
	public String cargarConductor(TransConductore cond) {
		try {
			cond_cedula=cond.getCondCedula();
			cond_nombre = cond.getCondNombre();
			cond_apellido = cond.getCondApellido();
			cond_telefono = cond.getCondTelefono();
			cond_estado = cond.getCondEstado();
			edicion = true;
			ediciontipo = false;
			return "nconductor?faces-redirect=true";
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
	public String cambiarEstadoCon() {
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("INFORMACION",
					managergest.cambioEstadoConductor(getCond().getCondCedula())));
			getListaConductores().clear();
			getListaConductores().addAll(managergest.findAllConductores());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "";
	}

	public void cambiarEstadoCon(TransConductore cond) {
		setCond(cond);
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
		List<TransConductore> cond = managergest.findAllConductores();
		for (TransConductore y : cond) {
			if (y.getCondCedula().equals(vehi_id)) {
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
