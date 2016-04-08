package transporte.model.dao.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the trans_funcionario_conductor database table.
 * 
 */
@Entity
@Table(name="trans_funcionario_conductor")
@NamedQuery(name="TransFuncionarioConductor.findAll", query="SELECT t FROM TransFuncionarioConductor t")
public class TransFuncionarioConductor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="fco_id", length=20)
	private String fcoId;

	@Column(name="fco_direccion", length=200)
	private String fcoDireccion;

	@Column(name="fco_estado", columnDefinition="bpchar", length=1)
	private String fcoEstado;

	@Column(name="fco_gerencia", length=200)
	private String fcoGerencia;

	@Column(name="fco_nombres", length=100)
	private String fcoNombres;

	//bi-directional many-to-one association to TranSolicitud
	@OneToMany(mappedBy="transFuncionarioConductor")
	private List<TranSolicitud> tranSolicituds;

	public TransFuncionarioConductor() {
	}

	public String getFcoId() {
		return this.fcoId;
	}

	public void setFcoId(String fcoId) {
		this.fcoId = fcoId;
	}

	public String getFcoDireccion() {
		return this.fcoDireccion;
	}

	public void setFcoDireccion(String fcoDireccion) {
		this.fcoDireccion = fcoDireccion;
	}

	public String getFcoEstado() {
		return this.fcoEstado;
	}

	public void setFcoEstado(String fcoEstado) {
		this.fcoEstado = fcoEstado;
	}

	public String getFcoGerencia() {
		return this.fcoGerencia;
	}

	public void setFcoGerencia(String fcoGerencia) {
		this.fcoGerencia = fcoGerencia;
	}

	public String getFcoNombres() {
		return this.fcoNombres;
	}

	public void setFcoNombres(String fcoNombres) {
		this.fcoNombres = fcoNombres;
	}

	public List<TranSolicitud> getTranSolicituds() {
		return this.tranSolicituds;
	}

	public void setTranSolicituds(List<TranSolicitud> tranSolicituds) {
		this.tranSolicituds = tranSolicituds;
	}

	public TranSolicitud addTranSolicitud(TranSolicitud tranSolicitud) {
		getTranSolicituds().add(tranSolicitud);
		tranSolicitud.setTransFuncionarioConductor(this);

		return tranSolicitud;
	}

	public TranSolicitud removeTranSolicitud(TranSolicitud tranSolicitud) {
		getTranSolicituds().remove(tranSolicitud);
		tranSolicitud.setTransFuncionarioConductor(null);

		return tranSolicitud;
	}

}