package transporte.model.dao.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the trans_cat_det database table.
 * 
 */
@Entity
@Table(name="trans_cat_det")
@NamedQuery(name="TransCatDet.findAll", query="SELECT t FROM TransCatDet t")
public class TransCatDet implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TRANS_CAT_DET_CATDID_GENERATOR", sequenceName="SEQ_TRANS_CAT_DET", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TRANS_CAT_DET_CATDID_GENERATOR")
	@Column(name="catd_id")
	private Integer catdId;

	@Column(name="catd_estado", columnDefinition="bpchar", length=1)
	private String catdEstado;

	@Column(name="catd_id_padre")
	private Integer catdIdPadre;

	@Column(name="catd_nombre", length=200)
	private String catdNombre;

	//bi-directional many-to-one association to TransCatCab
	@ManyToOne
	@JoinColumn(name="catc_id")
	private TransCatCab transCatCab;

	public TransCatDet() {
	}

	public Integer getCatdId() {
		return this.catdId;
	}

	public void setCatdId(Integer catdId) {
		this.catdId = catdId;
	}

	public String getCatdEstado() {
		return this.catdEstado;
	}

	public void setCatdEstado(String catdEstado) {
		this.catdEstado = catdEstado;
	}

	public Integer getCatdIdPadre() {
		return this.catdIdPadre;
	}

	public void setCatdIdPadre(Integer catdIdPadre) {
		this.catdIdPadre = catdIdPadre;
	}

	public String getCatdNombre() {
		return this.catdNombre;
	}

	public void setCatdNombre(String catdNombre) {
		this.catdNombre = catdNombre;
	}

	public TransCatCab getTransCatCab() {
		return this.transCatCab;
	}

	public void setTransCatCab(TransCatCab transCatCab) {
		this.transCatCab = transCatCab;
	}

}