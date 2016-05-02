package transporte.model.dao.entities;

import java.util.Date;

public class Novedades {

	private Integer sol_Id;
	private String sol_usuario_Cedula;
	private String sol_usuario_Nombre;
	private String sol_lugarordes;
	private Date sol_Fecha;
	private Date sol_Fecha_Aprobacion;
	private String sol_Hora_Fin;
	private String sol_Hora_Inicio;
	private String sol_Estado;
	private String sol_Gerencia;
	private String sol_Direccion;
	private String sol_Novedades;
	
	public Novedades(){
		
	}
	
	public Novedades(Integer sol_Id){
		this.sol_Id = sol_Id;
	}
	
	public Novedades(Integer solId,String sol_usuario_Cedula, String sol_usuario_Nombre,
			String sol_lugarordes, Date solFecha, Date solFecha_Aprobacion,String sol_Hora_Inicio, String sol_Hora_Fin, 
			String sol_Estado, String sol_Gerencia, String sol_Direccion, String solNovedades) {
		this.sol_Id = solId;
		this.sol_usuario_Cedula = sol_usuario_Cedula;
		this.sol_usuario_Nombre = sol_usuario_Nombre;
		this.sol_lugarordes = sol_lugarordes;
		this.sol_Fecha = solFecha;
		this.sol_Fecha_Aprobacion = solFecha_Aprobacion;
		this.sol_Hora_Fin = sol_Hora_Fin;
		this.sol_Hora_Inicio = sol_Hora_Inicio;
		this.sol_Estado = sol_Estado;
		this.sol_Gerencia = sol_Gerencia;
		this.sol_Direccion = sol_Direccion;
		this.sol_Novedades = solNovedades;
	}

	public Integer getSol_Id() {
		return sol_Id;
	}

	public void setSol_Id(Integer sol_Id) {
		this.sol_Id = sol_Id;
	}

	public String getSol_usuario_Cedula() {
		return sol_usuario_Cedula;
	}

	public void setSol_usuario_Cedula(String sol_usuario_Cedula) {
		this.sol_usuario_Cedula = sol_usuario_Cedula;
	}

	public String getSol_usuario_Nombre() {
		return sol_usuario_Nombre;
	}

	public void setSol_usuario_Nombre(String sol_usuario_Nombre) {
		this.sol_usuario_Nombre = sol_usuario_Nombre;
	}

	public String getSol_lugarordes() {
		return sol_lugarordes;
	}

	public void setSol_lugarordes(String sol_lugarordes) {
		this.sol_lugarordes = sol_lugarordes;
	}

	public Date getSol_Fecha() {
		return sol_Fecha;
	}

	public void setSol_Fecha(Date sol_Fecha) {
		this.sol_Fecha = sol_Fecha;
	}

	public Date getSol_Fecha_Aprobacion() {
		return sol_Fecha_Aprobacion;
	}

	public void setSol_Fecha_Aprobacion(Date sol_Fecha_Aprobacion) {
		this.sol_Fecha_Aprobacion = sol_Fecha_Aprobacion;
	}

	public String getSol_Hora_Fin() {
		return sol_Hora_Fin;
	}

	public void setSol_Hora_Fin(String sol_Hora_Fin) {
		this.sol_Hora_Fin = sol_Hora_Fin;
	}

	public String getSol_Hora_Inicio() {
		return sol_Hora_Inicio;
	}

	public void setSol_Hora_Inicio(String sol_Hora_Inicio) {
		this.sol_Hora_Inicio = sol_Hora_Inicio;
	}

	public String getSol_Estado() {
		return sol_Estado;
	}

	public void setSol_Estado(String sol_Estado) {
		this.sol_Estado = sol_Estado;
	}

	public String getSol_Gerencia() {
		return sol_Gerencia;
	}

	public void setSol_Gerencia(String sol_Gerencia) {
		this.sol_Gerencia = sol_Gerencia;
	}

	public String getSol_Direccion() {
		return sol_Direccion;
	}

	public void setSol_Direccion(String sol_Direccion) {
		this.sol_Direccion = sol_Direccion;
	}

	public String getSol_Novedades() {
		return sol_Novedades;
	}

	public void setSol_Novedades(String sol_Novedades) {
		this.sol_Novedades = sol_Novedades;
	}
}
