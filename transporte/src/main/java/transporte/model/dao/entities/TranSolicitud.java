package transporte.model.dao.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.sql.Time;


/**
 * The persistent class for the tran_solicitud database table.
 * 
 */
@Entity
@Table(name="tran_solicitud")
@NamedQuery(name="TranSolicitud.findAll", query="SELECT t FROM TranSolicitud t")
public class TranSolicitud implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TRAN_SOLICITUD_SOLID_GENERATOR", sequenceName="SEQ_TRANS_SOLICITUD", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TRAN_SOLICITUD_SOLID_GENERATOR")
	@Column(name="sol_id")
	private Integer solId;

	@Column(name="sol_estado", columnDefinition="bpchar", length=1)
	private String solEstado;

	@Column(name="sol_fecha")
	private Timestamp solFecha;

	@Column(name="sol_fecha_aprobacion")
	private Timestamp solFechaAprobacion;

	@Column(name="sol_flexibilidad", columnDefinition="bpchar", length=1)
	private String solFlexibilidad;

	@Column(name="sol_hora_fin")
	private Time solHoraFin;

	@Column(name="sol_hora_inicio")
	private Time solHoraInicio;

	@Column(name="sol_id_solicitante", length=20)
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

	public TranSolicitud() {
	}

	public Integer getSolId() {
		return this.solId;
	}

	public void setSolId(Integer solId) {
		this.solId = solId;
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

	public String getSolFlexibilidad() {
		return this.solFlexibilidad;
	}

	public void setSolFlexibilidad(String solFlexibilidad) {
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