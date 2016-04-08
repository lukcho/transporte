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

	//bi-directional many-to-one association to TranSolicitud
	@OneToMany(mappedBy="transLugare1")
	private List<TranSolicitud> tranSolicituds1;

	//bi-directional many-to-one association to TranSolicitud
	@OneToMany(mappedBy="transLugare2")
	private List<TranSolicitud> tranSolicituds2;

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

	public List<TranSolicitud> getTranSolicituds1() {
		return this.tranSolicituds1;
	}

	public void setTranSolicituds1(List<TranSolicitud> tranSolicituds1) {
		this.tranSolicituds1 = tranSolicituds1;
	}

	public TranSolicitud addTranSolicituds1(TranSolicitud tranSolicituds1) {
		getTranSolicituds1().add(tranSolicituds1);
		tranSolicituds1.setTransLugare1(this);

		return tranSolicituds1;
	}

	public TranSolicitud removeTranSolicituds1(TranSolicitud tranSolicituds1) {
		getTranSolicituds1().remove(tranSolicituds1);
		tranSolicituds1.setTransLugare1(null);

		return tranSolicituds1;
	}

	public List<TranSolicitud> getTranSolicituds2() {
		return this.tranSolicituds2;
	}

	public void setTranSolicituds2(List<TranSolicitud> tranSolicituds2) {
		this.tranSolicituds2 = tranSolicituds2;
	}

	public TranSolicitud addTranSolicituds2(TranSolicitud tranSolicituds2) {
		getTranSolicituds2().add(tranSolicituds2);
		tranSolicituds2.setTransLugare2(this);

		return tranSolicituds2;
	}

	public TranSolicitud removeTranSolicituds2(TranSolicitud tranSolicituds2) {
		getTranSolicituds2().remove(tranSolicituds2);
		tranSolicituds2.setTransLugare2(null);

		return tranSolicituds2;
	}

}