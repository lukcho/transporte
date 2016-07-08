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

import org.primefaces.context.RequestContext;
import transporte.model.dao.entities.TransCatCab;
import transporte.model.dao.entities.TransCatDet;
import transporte.model.generic.Funciones;
import transporte.model.manager.ManagerCatalogos;
import transporte.model.generic.Mensaje;

@SessionScoped
@ManagedBean
public class catalogosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private ManagerCatalogos managercat;

	// CATALOGO
	private Integer cat_id;// para la seleccion de categoria
	private Integer cati_id;// para la seleccion de categoria
	private Integer cati_idhijo;// para la seleccion de categoriahijo
	private Integer cati_idpadre;// para la seleccion de categoriahijo

	private String cati_nombre;
	private String cati_estado;

	private boolean mostrarpro_id;
	private boolean edicion;
	private boolean ediciontipo;

	private TransCatCab fabcat;
	private TransCatDet fabcati;

	private List<TransCatCab> listaCatalogo;
	private List<TransCatDet> listaCatalogoItems;

	public catalogosBean() {
	}

	@PostConstruct
	public void ini() {
		cat_id = 0;
		cati_nombre = "";
		cati_estado = "A";
		edicion = false;
		ediciontipo = false;
		listaCatalogoItems = managercat.findAllCatalogoItems();
		listaCatalogo = managercat.findAllCatalogos();
	}

	public TransCatCab getFabcat() {
		return fabcat;
	}

	public void setFabcat(TransCatCab fabcat) {
		this.fabcat = fabcat;
	}

	public TransCatDet getFabcati() {
		return fabcati;
	}

	public void setFabcati(TransCatDet fabcati) {
		this.fabcati = fabcati;
	}

	public Integer getCati_idpadre() {
		return cati_idpadre;
	}

	public void setCati_idpadre(Integer cati_idpadre) {
		this.cati_idpadre = cati_idpadre;
	}

	public Integer getCat_id() {
		return cat_id;
	}

	public void setCat_id(Integer cat_id) {
		this.cat_id = cat_id;
	}

	public String getCati_nombre() {
		return cati_nombre;
	}

	public void setCati_nombre(String cati_nombre) {
		this.cati_nombre = cati_nombre;
	}

	public String getCati_estado() {
		return cati_estado;
	}

	public void setCati_estado(String cati_estado) {
		this.cati_estado = cati_estado;
	}

	public List<TransCatCab> getListaCatalogo() {
		return listaCatalogo;
	}

	public List<TransCatDet> getListaCatalogoItems() {
		return listaCatalogoItems;
	}

	public boolean isMostrarpro_id() {
		return mostrarpro_id;
	}

	public void setMostrarpro_id(boolean mostrarpro_id) {
		this.mostrarpro_id = mostrarpro_id;
	}

	public Integer getCati_idhijo() {
		return cati_idhijo;
	}

	public void setCati_idhijo(Integer cati_idhijo) {
		this.cati_idhijo = cati_idhijo;
	}

	public boolean isEdiciontipo() {
		return ediciontipo;
	}

	public void setEdiciontipo(boolean ediciontipo) {
		this.ediciontipo = ediciontipo;
	}

	public boolean isEdicion() {
		return edicion;
	}

	public void setEdicion(boolean edicion) {
		this.edicion = edicion;
	}

	public Integer getCati_id() {
		return cati_id;
	}

	public void setCati_id(Integer cati_id) {
		this.cati_id = cati_id;
	}

	// CATALOGO y CATALOGO ITEMS

	/**
	 * Nuevo catalogo item
	 * 
	 * @return
	 */
	public String nuevoCatalogoitem() {
		edicion = false;
		cati_nombre = "";
		cati_estado = "A";
		cati_id = 0;
		return "ncatalogo?faces-redirect=true";
	}

	/**
	 * accion para invocar el manager y crear catalogo o editar el catalogo
	 * 
	 * @throws Exception
	 */
	public String crearCatalogo() {
		String r = "";
		try {
			if (edicion) {
				managercat.editarCatalogoItems(cati_id, cati_nombre.trim(),
						cati_estado, cati_idpadre);
				Mensaje.crearMensajeINFO("Actualizado - Modificado");
				r = "catalogos?faces-redirect=true";
				getListaCatalogoItems().clear();
				getListaCatalogoItems().addAll(
						managercat.findAllCatalogoItems());
			} else {
				managercat.insertarCatalogoItems(cati_nombre, cati_idpadre);
				Mensaje.crearMensajeINFO("Registrado - Creado");
				getListaCatalogoItems().clear();
				getListaCatalogoItems().addAll(
						managercat.findAllCatalogoItems());
			}
			r = "catalogos?faces-redirect=true";

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error al crear catalogo item", null));
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
	 * @param cati
	 * @throws Exception
	 */
	public String cargarCatalogoItem(TransCatDet cati) {
		try {
			cati_id = cati.getCatdId();
			cati_nombre = cati.getCatdNombre();
			cati_estado = cati.getCatdEstado();
			cat_id = cati.getTransCatCab().getCatcId();
			cati_idhijo = cati.getTransCatCab().getCatcId();
			cati_idpadre = cati.getCatdIdPadre();
			asignarCat();
			asignarCatItemitem();
			asignarCatItem();
			edicion = true;
			return "ncatalogo?faces-redirect=true";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * metodo para mostrar los Catalogositems
	 * 
	 */
	public List<SelectItem> getListaCategoriatodos() {
		List<SelectItem> listadoSI = new ArrayList<SelectItem>();
		listadoSI.add(new SelectItem(0, "Seleccionar"));
		for (TransCatDet t : managercat.findAllCatalogoItems()) {
			listadoSI.add(new SelectItem(t.getCatdNombre()));
		}
		return listadoSI;
	}

	/**
	 * metodo para mostrar los Catalogositems
	 * 
	 * @return categoria
	 */
	public List<SelectItem> getListaCategoria() {
		List<SelectItem> listadoSI = new ArrayList<SelectItem>();
		listadoSI.add(new SelectItem(0, "Seleccionar"));
		for (TransCatCab t : managercat.findAllCatalogos()) {
			listadoSI.add(new SelectItem(t.getCatcId(), t.getCatcNombre()));
		}
		return listadoSI;
	}

	/**
	 * metodo para mostrar los Catalogositems
	 * 
	 * @return catalogositems
	 * 
	 */
	public List<SelectItem> getListaCatalogoitem() {
		List<SelectItem> listadoSI = new ArrayList<SelectItem>();
		listadoSI.add(new SelectItem(0, "Seleccionar"));
		for (TransCatDet t : managercat.findCatalogoItemsByCatalogo(cat_id)) {
			listadoSI.add(new SelectItem(t.getCatdId(), t.getCatdNombre()));
		}
		return listadoSI;
	}

	/**
	 * metodo para mostrar los Catalogositemsitems
	 * 
	 * @return catalogositemsitems
	 */
	public List<SelectItem> getListaCatalogoitemitems() {
		List<SelectItem> listadoSI = new ArrayList<SelectItem>();
		if (cat_id != 0) {
			listadoSI.add(new SelectItem(0, "Seleccionar"));
			for (TransCatDet t : managercat
					.findCatalogoItemshijosByCatalogo(cati_idpadre)) {
				listadoSI.add(new SelectItem(t.getCatdId(), t.getCatdNombre()));
			}
		}
		return listadoSI;
	}

	/**
	 * metodo para asignar el catalogo al producto
	 * 
	 */
	public String asignarCat() {
		managercat.asignarcatalogo(cat_id);
		return "";
	}

	/**
	 * metodo para asignar el catalogoitem
	 * 
	 */
	public String asignarCatItem() {
		managercat.asignarcati(cati_idpadre);
		return "";
	}

	/**
	 * metodo para asignar el catalogoitem
	 * 
	 */
	public String asignarCatItemitem() {
		managercat.asignarcati(cati_idhijo);
		return "";
	}

	/**
	 * activar y desactivar estado
	 * 
	 * @throws Exception
	 */
	public String cambiarEstado() {
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(
					null,
					new FacesMessage("INFORMACIÓN", managercat
							.cambioEstadocati(getFabcati().getCatdId())));
			getListaCatalogoItems().clear();
			getListaCatalogoItems().addAll(managercat.findAllCatalogoItems());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "";
	}

	/**
	 * metodo para cambiar esl estado del catdet
	 * 
	 * @param cat
	 * @throws Exception
	 */
	public void cambiarEstadocati(TransCatDet cat) {
		setFabcati(cat);
		RequestContext.getCurrentInstance().execute("PF('ce').show();");
	}

	/**
	 * metodo para conocer el prodid si esta usado
	 * 
	 * @param nombreid
	 */
	public boolean averiguarCatId(String nombreid) {
		Integer t = 0;
		boolean r = false;
		List<TransCatDet> pro = managercat.findAllCatalogoItems();
		for (TransCatDet y : pro) {
			if (y.getCatdId().equals(nombreid)) {
				System.out.println("si entra1");
				t = 1;
				r = true;
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"El nombre del catalogo del producto existe.",
								null));
			}
		}
		if (t == 0) {
			r = false;
		}
		return r;
	}

	// ESTADOS

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

	// /**
	// * Lista de listatipo
	// *
	// * @return lista de items de tiposcata
	// */
	// public List<SelectItem> getlistTipo() {
	// List<SelectItem> lista = new ArrayList<SelectItem>();
	// lista.add(new SelectItem(Funciones.estadoProducto,
	// Funciones.estadoProducto + " : "
	// + Funciones.valorEstadoProducto));
	// lista.add(new SelectItem(Funciones.estadoServicio,
	// Funciones.estadoServicio + " : "
	// + Funciones.valorEstadoServicio));
	// return lista;
	// }

	/**
	 * limpia la informacion
	 * 
	 * @return
	 */
	public String salir() {
		// limpiar datos
		edicion = false;
		ediciontipo = false;
		getListaCatalogoItems().clear();
		getListaCatalogoItems().addAll(managercat.findAllCatalogoItems());
		return "catalogos?faces-redirect=true";
	}

	/**
	 * abrir el dialogo
	 * 
	 * @return
	 */
	public void abrirDialog() {
		if (edicion == true) {
			RequestContext.getCurrentInstance().execute("PF('gu').show();");
		} else if (!averiguarCatId(cati_nombre))
			RequestContext.getCurrentInstance().execute("PF('gu').show();");
	}
}
