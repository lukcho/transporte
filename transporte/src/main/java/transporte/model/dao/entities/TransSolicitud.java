package transporte.model.dao.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.sql.Time;


/**
 * The persistent class for the trans_solicitud database table.
 * 
 */
@Entity
@Table(name="trans_solicitud")
@NamedQuery(name="TransSolicitud.findAll", query="SELECT t FROM TransSolicitud t")
public class TransSolicitud implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TRANS_SOLICITUD_SOLID_GENERATOR", sequenceName="SEQ_TRANS_SOLICITUD", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TRANS_SOLICITUD_SOLID_GENERATOR")
	@Column(name="sol_id")
	private Integer solId;

	@Column(name="sol_correo", length=255)
	private String solCorreo;

	@Column(name="sol_estado", columnDefinition="bpchar", length=1)
	private String solEstado;

	@Column(name="sol_fecha")
	private Timestamp solFecha;

	@Column(name="sol_fecha_aprobacion")
	private Timestamp solFechaAprobacion;

	@Column(name="sol_fecha_creacion")
	private Timestamp solFechaCreacion;

	@Column(name="sol_flexibilidad")
	private Boolean solFlexibilidad;

	@Column(name="sol_hora_fin")
	private Time solHoraFin;

	@Column(name="sol_hora_inicio")
	private Time solHoraInicio;

	@Column(name="sol_id_solicitante", length=100)
	private String solIdSolicitante;

	@Column(name="sol_motivo", length=255)
	private String solMotivo;

	@Column(name="sol_observacion", length=255)
	private String solObservacion;

	@Column(name="sol_pasajeros")
	private Integer solPasajeros;

	//bi-directional many-to-one association to TransConductore
	@ManyToOne
	@JoinColumn(name="cond_cedula")
	private TransConductore transConductore;

	//bi-directional many-to-one association to TransFuncionarioConductor
	@ManyToOne
	@JoinColumn(name="fco_id")
	private TransFuncionarioConductor transFuncionarioConductor;

	//bi-directional many-to-one association to TransLugare
	@ManyToOne
	@JoinColumn(name="lug_id_destino")
	private TransLugare transLugare1;

	//bi-directional many-to-one association to TransLugare
	@ManyToOne
	@JoinColumn(name="lug_id_origen")
	private TransLugare transLugare2;

	//bi-directional many-to-one association to TransVehiculo
	@ManyToOne
	@JoinColumn(name="vehi_idplaca")
	private TransVehiculo transVehiculo;

	public TransSolicitud() {
	}

	public Integer getSolId() {
		return this.solId;
	}

	public void setSolId(Integer solId) {
		this.solId = solId;
	}

	public String getSolCorreo() {
		return this.solCorreo;
	}

	public void setSolCorreo(String solCorreo) {
		this.solCorreo = solCorreo;
	}

	public String getSolEstado() {
		return this.solEstado;
	}

	public void setSolEstado(String solEstado) {
		this.solEstado = solEstado;
	}

	public Timestamp getSolFecha() {
		return this.solFecha;
	}

	public void setSolFecha(Timestamp solFecha) {
		this.solFecha = solFecha;
	}

	public Timestamp getSolFechaAprobacion() {
		return this.solFechaAprobacion;
	}

	public void setSolFechaAprobacion(Timestamp solFechaAprobacion) {
		this.solFechaAprobacion = solFechaAprobacion;
	}

	public Timestamp getSolFechaCreacion() {
		return this.solFechaCreacion;
	}

	public void setSolFechaCreacion(Timestamp solFechaCreacion) {
		this.solFechaCreacion = solFechaCreacion;
	}

	public Boolean getSolFlexibilidad() {
		return this.solFlexibilidad;
	}

	public void setSolFlexibilidad(Boolean solFlexibilidad) {
		this.solFlexibilidad = solFlexibilidad;
	}

	public Time getSolHoraFin() {
		return this.solHoraFin;
	}

	public void setSolHoraFin(Time solHoraFin) {
		this.solHoraFin = solHoraFin;
	}

	public Time getSolHoraInicio() {
		return this.solHoraInicio;
	}

	public void setSolHoraInicio(Time solHoraInicio) {
		this.solHoraInicio = solHoraInicio;
	}

	public String getSolIdSolicitante() {
		return this.solIdSolicitante;
	}

	public void setSolIdSolicitante(String solIdSolicitante) {
		this.solIdSolicitante = solIdSolicitante;
	}

	public String getSolMotivo() {
		return this.solMotivo;
	}

	public void setSolMotivo(String solMotivo) {
		this.solMotivo = solMotivo;
	}

	public String getSolObservacion() {
		return this.solObservacion;
	}

	public void setSolObservacion(String solObservacion) {
		this.solObservacion = solObservacion;
	}

	public Integer getSolPasajeros() {
		return this.solPasajeros;
	}

	public void setSolPasajeros(Integer solPasajeros) {
		this.solPasajeros = solPasajeros;
	}

	public TransConductore getTransConductore() {
		return this.transConductore;
	}

	public void setTransConductore(TransConductore transConductore) {
		this.transConductore = transConductore;
	}

	public TransFuncionarioConductor getTransFuncionarioConductor() {
		return this.transFuncionarioConductor;
	}

	public void setTransFuncionarioConductor(TransFuncionarioConductor transFuncionarioConductor) {
		this.transFuncionarioConductor = transFuncionarioConductor;
	}

	public TransLugare getTransLugare1() {
		return this.transLugare1;
	}

	public void setTransLugare1(TransLugare transLugare1) {
		this.transLugare1 = transLugare1;
	}

	public TransLugare getTransLugare2() {
		return this.transLugare2;
	}

	public void setTransLugare2(TransLugare transLugare2) {
		this.transLugare2 = transLugare2;
	}

	public TransVehiculo getTransVehiculo() {
		return this.transVehiculo;
	}

	public void setTransVehiculo(TransVehiculo transVehiculo) {
		this.transVehiculo = transVehiculo;
	}

}