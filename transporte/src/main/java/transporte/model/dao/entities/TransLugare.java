package transporte.model.dao.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the trans_lugares database table.
 * 
 */
@Entity
@Table(name="trans_lugares")
@NamedQuery(name="TransLugare.findAll", query="SELECT t FROM TransLugare t")
public class TransLugare implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TRANS_LUGARES_LUGID_GENERATOR", sequenceName="SEQ_TRANS_LUGARES", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TRANS_LUGARES_LUGID_GENERATOR")
	@Column(name="lug_id")
	private Integer lugId;

	@Column(name="lug_ciudad", length=100)
	private String lugCiudad;

	@Column(name="lug_estado", columnDefinition="bpchar", length=1)
	private String lugEstado;

	@Column(name="lug_nombre", length=100)
	private String lugNombre;

	//bi-directional many-to-one association to TransSolicitud
	@OneToMany(mappedBy="transLugare1")
	private List<TransSolicitud> TransSolicituds1;

	//bi-directional many-to-one association to TransSolicitud
	@OneToMany(mappedBy="transLugare2")
	private List<TransSolicitud> TransSolicituds2;

	public TransLugare() {
	}

	public Integer getLugId() {
		return this.lugId;
	}

	public void setLugId(Integer lugId) {
		this.lugId = lugId;
	}

	public String getLugCiudad() {
		return this.lugCiudad;
	}

	public void setLugCiudad(String lugCiudad) {
		this.lugCiudad = lugCiudad;
	}

	public String getLugEstado() {
		return this.lugEstado;
	}

	public void setLugEstado(String lugEstado) {
		this.lugEstado = lugEstado;
	}

	public String getLugNombre() {
		return this.lugNombre;
	}

	public void setLugNombre(String lugNombre) {
		this.lugNombre = lugNombre;
	}

	public List<TransSolicitud> getTransSolicituds1() {
		return this.TransSolicituds1;
	}

	public void setTransSolicituds1(List<TransSolicitud> TransSolicituds1) {
		this.TransSolicituds1 = TransSolicituds1;
	}

	public TransSolicitud addTransSolicituds1(TransSolicitud TransSolicituds1) {
		getTransSolicituds1().add(TransSolicituds1);
		TransSolicituds1.setTransLugare1(this);

		return TransSolicituds1;
	}

	public TransSolicitud removeTransSolicituds1(TransSolicitud TransSolicituds1) {
		getTransSolicituds1().remove(TransSolicituds1);
		TransSolicituds1.setTransLugare1(null);

		return TransSolicituds1;
	}

	public List<TransSolicitud> getTransSolicituds2() {
		return this.TransSolicituds2;
	}

	public void setTransSolicituds2(List<TransSolicitud> TransSolicituds2) {
		this.TransSolicituds2 = TransSolicituds2;
	}

	public TransSolicitud addTransSolicituds2(TransSolicitud TransSolicituds2) {
		getTransSolicituds2().add(TransSolicituds2);
		TransSolicituds2.setTransLugare2(this);

		return TransSolicituds2;
	}

	public TransSolicitud removeTransSolicituds2(TransSolicitud TransSolicituds2) {
		getTransSolicituds2().remove(TransSolicituds2);
		TransSolicituds2.setTransLugare2(null);

		return TransSolicituds2;
	}

}