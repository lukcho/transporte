package transporte.model.dao.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the trans_cat_cab database table.
 * 
 */
@Entity
@Table(name="trans_cat_cab")
@NamedQuery(name="TransCatCab.findAll", query="SELECT t FROM TransCatCab t")
public class TransCatCab implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TRANS_CAT_CAB_CATCID_GENERATOR", sequenceName="SEQ_TRANS_CAT_CAB", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TRANS_CAT_CAB_CATCID_GENERATOR")
	@Column(name="catc_id")
	private Integer catcId;

	@Column(name="catc_nombre", length=200)
	private String catcNombre;

	@Column(name="catc_valor", columnDefinition="bpchar", length=4)
	private String catcValor;

	//bi-directional many-to-one association to TransCatDet
	@OneToMany(mappedBy="transCatCab")
	private List<TransCatDet> transCatDets;

	public TransCatCab() {
	}

	public Integer getCatcId() {
		return this.catcId;
	}

	public void setCatcId(Integer catcId) {
		this.catcId = catcId;
	}

	public String getCatcNombre() {
		return this.catcNombre;
	}

	public void setCatcNombre(String catcNombre) {
		this.catcNombre = catcNombre;
	}

	public String getCatcValor() {
		return this.catcValor;
	}

	public void setCatcValor(String catcValor) {
		this.catcValor = catcValor;
	}

	public List<TransCatDet> getTransCatDets() {
		return this.transCatDets;
	}

	public void setTransCatDets(List<TransCatDet> transCatDets) {
		this.transCatDets = transCatDets;
	}

	public TransCatDet addTransCatDet(TransCatDet transCatDet) {
		getTransCatDets().add(transCatDet);
		transCatDet.setTransCatCab(this);

		return transCatDet;
	}

	public TransCatDet removeTransCatDet(TransCatDet transCatDet) {
		getTransCatDets().remove(transCatDet);
		transCatDet.setTransCatCab(null);

		return transCatDet;
	}

}