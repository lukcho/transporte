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

	@Column(name="fco_correo", length=255)
	private String fcoCorreo;

	@Column(name="fco_direccion", length=255)
	private String fcoDireccion;

	@Column(name="fco_estado", columnDefinition="bpchar", length=1)
	private String fcoEstado;

	@Column(name="fco_gerencia", length=255)
	private String fcoGerencia;

	@Column(name="fco_nombres", length=255)
	private String fcoNombres;

	@Column(name="fco_telefono", length=20)
	private String fcoTelefono;

	//bi-directional many-to-one association to TransSolicitud
	@OneToMany(mappedBy="transFuncionarioConductor")
	private List<TransSolicitud> transSolicituds;

	public TransFuncionarioConductor() {
	}

	public String getFcoId() {
		return this.fcoId;
	}

	public void setFcoId(String fcoId) {
		this.fcoId = fcoId;
	}

	public String getFcoCorreo() {
		return this.fcoCorreo;
	}

	public void setFcoCorreo(String fcoCorreo) {
		this.fcoCorreo = fcoCorreo;
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

	public String getFcoTelefono() {
		return this.fcoTelefono;
	}

	public void setFcoTelefono(String fcoTelefono) {
		this.fcoTelefono = fcoTelefono;
	}

	public List<TransSolicitud> getTransSolicituds() {
		return this.transSolicituds;
	}

	public void setTransSolicituds(List<TransSolicitud> transSolicituds) {
		this.transSolicituds = transSolicituds;
	}

	public TransSolicitud addTransSolicitud(TransSolicitud transSolicitud) {
		getTransSolicituds().add(transSolicitud);
		transSolicitud.setTransFuncionarioConductor(this);

		return transSolicitud;
	}

	public TransSolicitud removeTransSolicitud(TransSolicitud transSolicitud) {
		getTransSolicituds().remove(transSolicitud);
		transSolicitud.setTransFuncionarioConductor(null);

		return transSolicitud;
	}

}