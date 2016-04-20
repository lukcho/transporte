package transporte.model.dao.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the trans_conductores database table.
 * 
 */
@Entity
@Table(name="trans_conductores")
@NamedQuery(name="TransConductore.findAll", query="SELECT t FROM TransConductore t")
public class TransConductore implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="cond_cedula", length=100)
	private String condCedula;

	@Column(name="cond_apellido", length=100)
	private String condApellido;

	@Column(name="cond_correo", length=255)
	private String condCorreo;

	@Column(name="cond_estado", columnDefinition="bpchar", length=1)
	private String condEstado;

	@Column(name="cond_nombre", length=100)
	private String condNombre;

	@Column(name="cond_telefono", length=10)
	private String condTelefono;

	//bi-directional many-to-one association to TransSolicitud
	@OneToMany(mappedBy="transConductore")
	private List<TransSolicitud> transSolicituds;

	public TransConductore() {
	}

	public String getCondCedula() {
		return this.condCedula;
	}

	public void setCondCedula(String condCedula) {
		this.condCedula = condCedula;
	}

	public String getCondApellido() {
		return this.condApellido;
	}

	public void setCondApellido(String condApellido) {
		this.condApellido = condApellido;
	}

	public String getCondCorreo() {
		return this.condCorreo;
	}

	public void setCondCorreo(String condCorreo) {
		this.condCorreo = condCorreo;
	}

	public String getCondEstado() {
		return this.condEstado;
	}

	public void setCondEstado(String condEstado) {
		this.condEstado = condEstado;
	}

	public String getCondNombre() {
		return this.condNombre;
	}

	public void setCondNombre(String condNombre) {
		this.condNombre = condNombre;
	}

	public String getCondTelefono() {
		return this.condTelefono;
	}

	public void setCondTelefono(String condTelefono) {
		this.condTelefono = condTelefono;
	}

	public List<TransSolicitud> getTransSolicituds() {
		return this.transSolicituds;
	}

	public void setTransSolicituds(List<TransSolicitud> transSolicituds) {
		this.transSolicituds = transSolicituds;
	}

	public TransSolicitud addTransSolicitud(TransSolicitud transSolicitud) {
		getTransSolicituds().add(transSolicitud);
		transSolicitud.setTransConductore(this);

		return transSolicitud;
	}

	public TransSolicitud removeTransSolicitud(TransSolicitud transSolicitud) {
		getTransSolicituds().remove(transSolicitud);
		transSolicitud.setTransConductore(null);

		return transSolicitud;
	}

}