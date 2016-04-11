package transporte.model.dao.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the trans_vehiculo database table.
 * 
 */
@Entity
@Table(name="trans_vehiculo")
@NamedQuery(name="TransVehiculo.findAll", query="SELECT t FROM TransVehiculo t")
public class TransVehiculo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="vehi_idplaca", length=100)
	private String vehiIdplaca;

	@Column(name="vehi_capacidad")
	private Integer vehiCapacidad;

	@Column(name="vehi_estado", columnDefinition="bpchar", length=1)
	private String vehiEstado;

	@Column(name="vehi_estado__funcional", columnDefinition="bpchar", length=1)
	private String vehiEstadoFuncional;

	@Column(name="vehi_marca", length=100)
	private String vehiMarca;

	@Column(name="vehi_modelo", length=100)
	private String vehiModelo;

	@Column(name="vehi_nombre", length=100)
	private String vehiNombre;

	@Column(name="vehi_tipo", length=100)
	private String vehiTipo;

	//bi-directional many-to-one association to TransSolicitud
	@OneToMany(mappedBy="transVehiculo")
	private List<TransSolicitud> TransSolicituds;

	public TransVehiculo() {
	}

	public String getVehiIdplaca() {
		return this.vehiIdplaca;
	}

	public void setVehiIdplaca(String vehiIdplaca) {
		this.vehiIdplaca = vehiIdplaca;
	}

	public Integer getVehiCapacidad() {
		return this.vehiCapacidad;
	}

	public void setVehiCapacidad(Integer vehiCapacidad) {
		this.vehiCapacidad = vehiCapacidad;
	}

	public String getVehiEstado() {
		return this.vehiEstado;
	}

	public void setVehiEstado(String vehiEstado) {
		this.vehiEstado = vehiEstado;
	}

	public String getVehiEstadoFuncional() {
		return this.vehiEstadoFuncional;
	}

	public void setVehiEstadoFuncional(String vehiEstadoFuncional) {
		this.vehiEstadoFuncional = vehiEstadoFuncional;
	}

	public String getVehiMarca() {
		return this.vehiMarca;
	}

	public void setVehiMarca(String vehiMarca) {
		this.vehiMarca = vehiMarca;
	}

	public String getVehiModelo() {
		return this.vehiModelo;
	}

	public void setVehiModelo(String vehiModelo) {
		this.vehiModelo = vehiModelo;
	}

	public String getVehiNombre() {
		return this.vehiNombre;
	}

	public void setVehiNombre(String vehiNombre) {
		this.vehiNombre = vehiNombre;
	}

	public String getVehiTipo() {
		return this.vehiTipo;
	}

	public void setVehiTipo(String vehiTipo) {
		this.vehiTipo = vehiTipo;
	}

	public List<TransSolicitud> getTransSolicituds() {
		return this.TransSolicituds;
	}

	public void setTransSolicituds(List<TransSolicitud> TransSolicituds) {
		this.TransSolicituds = TransSolicituds;
	}

	public TransSolicitud addTransSolicitud(TransSolicitud TransSolicitud) {
		getTransSolicituds().add(TransSolicitud);
		TransSolicitud.setTransVehiculo(this);

		return TransSolicitud;
	}

	public TransSolicitud removeTransSolicitud(TransSolicitud TransSolicitud) {
		getTransSolicituds().remove(TransSolicitud);
		TransSolicitud.setTransVehiculo(null);

		return TransSolicitud;
	}

}