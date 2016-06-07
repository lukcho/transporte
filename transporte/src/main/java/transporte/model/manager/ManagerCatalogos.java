package transporte.model.manager;

import transporte.model.dao.entities.*;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class ManagerCatalogos {

	@EJB
	private ManagerDAO mDAO;

	private static TransCatCab fab_cat;

	private static TransCatDet fab_cati;

	public String tipo;
	String h = "";

	public ManagerCatalogos() {
	}

	// CATALOGO

	/**
	 * buscar todos catalogos
	 * 
	 * @param cat_id
	 * @throws Exception
	 */

	@SuppressWarnings("unchecked")
	public List<TransCatCab> findCatalogo() {
		return mDAO.findWhere(TransCatCab.class, "1=1", null);
	}
	
	/**
	 * buscar catalogos por el catalogo_id
	 * 
	 * @param catId
	 * @throws Exception
	 */

	@SuppressWarnings("unchecked")
	public List<TransCatCab> findCatalogosByCatalogo(Integer catId) {
		return mDAO.findWhere(TransCatCab.class, "o.catcId= '"+catId+"'", null);
	}

	/**
	 * listar todos los catalogos
	 * 
	 * @param id_cat
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<TransCatCab> findAllCatalogos() {
		return mDAO.findAll(TransCatCab.class);
	}

	/**
	 * buscar Catalogo por ID
	 * 
	 * @param id_cat
	 * @throws Exception
	 */
	public TransCatCab CatalogoByID(Integer cat_id) throws Exception {
		return (TransCatCab) mDAO.findById(TransCatCab.class, cat_id);
	}

	/**
	 * Agrega Catalogo
	 * 
	 * @param nombre
	 * @param valor
	 * @throws Exception
	 */
	public void insertarCatalogos(String nombre, String valor) throws Exception {
		TransCatCab cat = new TransCatCab();
		cat.setCatcNombre(nombre);
		cat.setCatcValor(valor);
		mDAO.insertar(cat);
	}

	/**
	 * Cambiar datos de Catalogo
	 * 
	 * @param id_cat
	 * @param nombre
	 * @param valor
	 * @throws Exception
	 */
	public void editarCatalogo(Integer cat_id, String nombre, String valor)
			throws Exception {
		TransCatCab cat = this.CatalogoByID(cat_id);
		cat.setCatcNombre(nombre);
		cat.setCatcValor(valor);
		mDAO.actualizar(cat);
	}

	/**
	 * Cambiar estado Catalogo
	 * 
	 * @param id_cat
	 * @throws Exception
	 */
	public String cambioEstadocat(Integer cat_id) throws Exception {
		String h = "";
		TransCatCab cat = CatalogoByID(cat_id);

		if (cat.getCatcValor().equals("A")) {
			cat.setCatcValor("I");
			h = "Estado del Catalogo Modificado";
		} else if (cat.getCatcValor().equals("I")) {
			cat.setCatcValor("A");
			h = "Estado del Registro Modificado";
		}
		mDAO.actualizar(cat);
		return h;
	}

	/**
	 * Verifica si el Catalogo esta activado
	 * 
	 * @param u
	 *            Catalogo a analizar
	 * @return true o false
	 */
	public boolean escatActivo(TransCatCab u) {
		boolean resp = false;
		if (u.getCatcValor().equals("A")) {
			resp = true;
		}
		return resp;
	}

	// CATALOGOITEMS
	/**
	 * buscar todos catalogosItems
	 * 
	 * @param catId
	 * @throws Exception
	 */

	@SuppressWarnings("unchecked")
	public List<TransCatDet> findCatalogoItemsByCatalogo(Integer catId) {
		return mDAO.findWhere(TransCatDet.class, "o.transCatCab.catcId= "
				+ catId + " and catdIdPadre= 0", null);
	}

	/**
	 * buscar todos catalogosItemsitems
	 * 
	 * @param catId
	 * @throws Exception
	 */

	@SuppressWarnings("unchecked")
	public List<TransCatDet> findCatalogoItemshijosByCatalogo(Integer cati_Id) {
		return mDAO.findWhere(TransCatDet.class, "o.catdIdPadre = " + cati_Id,
				null);
	}

	/**
	 * listar todos los catalogositems
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<TransCatDet> findAllCatalogoItems() {
		return mDAO.findAll(TransCatDet.class);
	}

	/**
	 * buscar Catalogositems por ID
	 * 
	 * @param id_cat
	 * @throws Exception
	 */
	public TransCatDet CatalogoItemsByID(Integer cati_id) throws Exception {
		return (TransCatDet) mDAO.findById(TransCatDet.class, cati_id);
	}

	/**
	 * Agrega Catalogoitems
	 * 
	 * @param cat_id
	 * @param nombre
	 * @param id_padre
	 * @throws Exception
	 */
	public void insertarCatalogoItems(String nombre, Integer id_padre)
			throws Exception {
		TransCatDet cati = new TransCatDet();
		if (id_padre == null) {
			cati.setCatdNombre(nombre);
			cati.setCatdIdPadre(0);
			cati.setTransCatCab(fab_cat);
			cati.setCatdEstado("A");
			mDAO.insertar(cati);
		} else
			cati.setCatdNombre(nombre);
		cati.setCatdIdPadre(id_padre);
		cati.setTransCatCab(fab_cat);
		cati.setCatdEstado("A");
		mDAO.insertar(cati);
	}

	/**
	 * Cambiar datos de CatalogoItems
	 * 
	 * @param id_cat
	 * @param nombre
	 * @param estado
	 * @param id_padre
	 * @throws Exception
	 */
	public void editarCatalogoItems(Integer cati_id, String nombre,
			String estado, Integer id_padre) throws Exception {
		TransCatDet cati = this.CatalogoItemsByID(cati_id);
		if (id_padre == null || id_padre == 0) {
			cati.setCatdNombre(nombre);
			cati.setCatdIdPadre(0);
			cati.setTransCatCab(fab_cat);
			cati.setCatdEstado(estado);
		} else
			cati.setCatdNombre(nombre);
		cati.setCatdIdPadre(id_padre);
		cati.setTransCatCab(fab_cat);
		cati.setCatdEstado(estado);
		mDAO.actualizar(cati);
	}

	/**
	 * Cambiar estado CatalogoItems
	 * 
	 * @param id_cat
	 * @throws Exception
	 */
	public String cambioEstadocati(Integer cati_id) throws Exception {
		String h = "";
		TransCatDet cati = CatalogoItemsByID(cati_id);

		if (cati.getCatdEstado().equals("A")) {
			cati.setCatdEstado("I");
			h = "Estado del Catálogo Modificado";
		} else if (cati.getCatdEstado().equals("I")) {
			cati.setCatdEstado("A");
			h = "Estado del Catálogo Modificado";
		}
		mDAO.actualizar(cati);
		return h;
	}

	/**
	 * Verifica si el CatalogoItems esta activado
	 * 
	 * @param u
	 *            Catalogoitem a analizar
	 * @return true o false
	 */
	public boolean escatiActivo(TransCatDet u) {
		boolean resp = false;
		if (u.getCatdEstado().equals("A")) {
			resp = true;
		}
		return resp;
	}

	/**
	 * Método para asignar el catalogo al un item
	 * 
	 * @param u
	 *            Catalogo a analizar
	 * @return true o false
	 */
	public TransCatCab asignarcatalogo(Integer cat_id) {
		try {
			fab_cat = CatalogoByID(cat_id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fab_cat;
	}

	/**
	 * buscar catalogo_item por ID
	 * 
	 * @param cati_id
	 * @throws Exception
	 */
	public TransCatDet CatalogoItemByID(Integer cati_id) throws Exception {
		return (TransCatDet) mDAO.findById(TransCatDet.class, cati_id);
	}

	/**
	 * Método para asignar el producto a un item
	 * 
	 * @param u
	 *            prodalogo a analizar
	 * @return true o false
	 */
	public TransCatDet asignarcati(Integer cati_id) {
		try {
			fab_cati = CatalogoItemByID(cati_id);
		} catch (Exception e) {
			// TODO Auto-generated prodch block
			e.printStackTrace();
		}
		return fab_cati;
	}
}
