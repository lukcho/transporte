package transporte.model.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;

import transporte.general.connection.SingletonJDBC;
import transporte.general.connection.SingletonJDBCtrans;
import transporte.model.dao.entities.Novedades;
import transporte.model.dao.entities.Persona;
import transporte.model.dao.entities.PersonaFuncionario;
import transporte.model.dao.entities.TransFuncionarioConductor;
import transporte.model.dao.entities.TransParametro;
import transporte.model.generic.Funciones;

/**
 * Contiene la lógica de negocio para realizar el proceso de reserva de sitios
 * 
 * @author
 * 
 */

public class ManagerCarga {

	private SingletonJDBC conDao;
	private SingletonJDBCtrans conDaotrans;

	@EJB
	private ManagerDAO mDAO;

	public ManagerCarga() {
		conDao = SingletonJDBC.getInstance();
		conDaotrans = SingletonJDBCtrans.getInstance();
	}

	public static String consultaSQL(String usr) throws Exception {
		String cc = "jdbc:postgresql://10.1.0.158:5432/app_permisos?user=adm_svcyachay&password=_50STg-FGh2h";
		Connection conexion = null;
		Statement comando = null;
		ResultSet resultado = null;
		try {
			Class.forName("org.postgresql.Driver");
			conexion = DriverManager.getConnection(cc);
			comando = conexion.createStatement();
			resultado = comando
					.executeQuery("SELECT per_id FROM app_usuario WHERE usu_login = '"
							+ usr + "';");
			if (resultado.next()) {
				return resultado.getString("per_id");
			} else {
				return null;
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
			if (resultado != null)
				resultado.close();
			if (comando != null)
				comando.close();
			if (conexion != null)
				conexion.close();
		}
	}

	/**
	 * Devuelve un funcionario por dni
	 * 
	 * @param dni
	 * @return Funcionario
	 * @throws Exception
	 */
	public Persona funcionarioByDNI(String usr_login) throws Exception {
		Persona f = null;
		ResultSet consulta = conDao
				.consultaSQL("SELECT p.per_id as dni , p.per_nombres as nombres, "
						+ " p.per_apellidos as apellidos, p.per_correo as correo, "
						+ " f.fun_cargo as cargo, f.fun_jefe_inmediato as jefe, f.fun_gerencia as gerencia, f.fun_direccion as direccion, "
						+ " (select pe.per_nombres||' '||pe.per_apellidos "
						+ " from gen_persona pe where pe.per_id=f.fun_jefe_inmediato) as nombreJefe "
						+ " FROM gen_persona p INNER JOIN gen_usuario u ON p.per_id = u.per_id "
						+ " INNER JOIN gen_funcionario f  on f.per_id = u.per_id "
						+ " WHERE u.usu_login = '" + usr_login + "'");
		if (consulta != null) {
			consulta.next();
			f = new Persona();
			f.setPerDNI(consulta.getString("dni"));
			f.setPerNombres(consulta.getString("nombres"));
			f.setPerApellidos(consulta.getString("apellidos"));
			f.setPerCorreo(consulta.getString("correo"));
			f.setCargo(consulta.getString("cargo"));
			f.setJefeInmediato(consulta.getString("jefe"));
			f.setPerGerencia(consulta.getString("gerencia"));
			f.setPerDireccion(consulta.getString("direccion"));
		}
		return f;
	}

	/**
	 * Devuelve un persona solicitud por dni
	 * 
	 * @param dni
	 * @return Funcionario
	 * @throws Exception
	 */
	public Persona personasolicitudByDNI(String per_id) throws Exception {
		Persona f = null;
		ResultSet consulta = conDao
				.consultaSQL("SELECT f.per_dni as dni ,(select pe.per_nombres from gen_persona pe where pe.per_dni =f.fun_jefe_inmediato) as nombres,"
						+ "	(select pe.per_apellidos from gen_persona pe where pe.per_dni =f.fun_jefe_inmediato) as apellidos,"
						+ "	(select pe.per_apellidos from gen_persona pe where pe.per_dni =f.fun_jefe_inmediato) as correo"
						+ " from gen_persona p, gen_funcionarios_institucion f "
						+ "	 where p.per_dni= f.per_dni "
						+ "	 and p.per_dni='" + per_id + "'");
		
		if (consulta != null) {
			consulta.next();
			f = new Persona();
			f.setPerDNI(consulta.getString("dni"));
			f.setPerNombres(consulta.getString("nombres"));
			f.setPerApellidos(consulta.getString("apellidos"));
			f.setPerCorreo(consulta.getString("correo"));
		}
		return f;
	}

	/**
	 * Devuelve un funcionario por gerencia
	 * 
	 * @param gerencia
	 * @return PersonaFuncionario
	 * @throws Exception
	 */
	public List<PersonaFuncionario> funcionarioByGerencia(String Gerencia)
			throws Exception {
		PersonaFuncionario f = null;
		List<PersonaFuncionario> filterUsers = new ArrayList<PersonaFuncionario>();
		ResultSet consulta = conDao
				.consultaSQL("SELECT p.per_id as dni , p.per_nombres as nombres,  p.per_apellidos as apellidos, p.per_correo as correo, p.per_telefono as telefono, "
						+ " f.fun_cargo as cargo, f.fun_jefe_inmediato as jefe, f.fun_gerencia as gerencia, f.fun_direccion as direccion "
						+ " FROM gen_persona p INNER JOIN gen_usuario u ON p.per_id = u.per_id "
						+ " INNER JOIN gen_funcionario f  on f.per_id = u.per_id "
						+ " WHERE f.fun_gerencia = '" + Gerencia + "'");
		if (consulta != null) {
			while (consulta.next()) {
				f = new PersonaFuncionario();
				f.setPerDNI(consulta.getString("dni"));
				f.setPerNombres(consulta.getString("nombres"));
				f.setPerApellidos(consulta.getString("apellidos"));
				f.setPerCorreo(consulta.getString("correo"));
				f.setCargo(consulta.getString("cargo"));
				f.setJefeInmediato(consulta.getString("jefe"));
				f.setPerGerencia(consulta.getString("gerencia"));
				f.setPerTelefono(consulta.getString("telefono"));
				f.setPerDireccion(consulta.getString("direccion"));
				filterUsers.add(f);
			}
		}
		return filterUsers;
	}

	/**
	 * Devuelve las novedades
	 * 
	 * @return Novedades
	 * @throws Exception
	 */
	public List<Novedades> FindAllNovedades() throws Exception {
		Novedades f = null;
		List<Novedades> filterUsers = new ArrayList<Novedades>();
		ResultSet consulta = conDaotrans
				.consultaSQL("SELECT s.sol_id as numerosolicitud,s.sol_id_solicitante as cedula, s.sol_nom_solicitante as nombresolicitante, "
						+ " (select  lu.lug_nombre from trans_lugares lu where lu.lug_id = s.lug_id_origen)||' - '|| "
						+ " (select lu.lug_nombre from trans_lugares lu where lu.lug_id = s.lug_id_destino) as lugardestino,  s.sol_fecha as fecha, "
						+ " s.sol_fecha_aprobacion as fecha_aprobacion,s.sol_hora_inicio as hora_inicio, s.sol_hora_fin as hora_fin, s.sol_estado as estado, tb.gerencia, tb.direccion, "
						+ " s.sol_novedades as novedades from trans_solicitud s,  "
						+ " dblink('dbname=yachay host= 10.1.0.158 user=adm_bicichay password=y-4IO4SDwu_! port=5432',' "
						+ " SELECT f.per_dni , f.fun_gerencia, f.fun_direccion "
						+ " FROM gen_funcionarios_institucion f ' ) as tb(dni name, gerencia name, direccion name ) "
						+ " where s.sol_id_solicitante = tb.dni "
						+ " and s.sol_novedades not like '' Order by fecha desc ");
		if (consulta != null) {
			while (consulta.next()) {
				f = new Novedades();
				f.setSol_Id(Integer.parseInt(consulta
						.getString("numerosolicitud")));
				f.setSol_usuario_Cedula(consulta.getString("cedula"));
				f.setSol_usuario_Nombre(consulta.getString("nombresolicitante"));
				f.setSol_lugarordes(consulta.getString("lugardestino"));
				f.setSol_Fecha(new Timestamp(Funciones.stringToDate(
						consulta.getString("fecha")).getTime()));
				f.setSol_Fecha_Aprobacion(new Timestamp(Funciones.stringToDate(
						consulta.getString("fecha_aprobacion")).getTime()));
				f.setSol_Hora_Inicio(consulta.getString("hora_inicio"));
				f.setSol_Hora_Fin(consulta.getString("hora_fin"));
				f.setSol_Estado(consulta.getString("estado"));
				f.setSol_Gerencia(consulta.getString("gerencia"));
				f.setSol_Direccion(consulta.getString("direccion"));
				f.setSol_Novedades(consulta.getString("novedades"));
				filterUsers.add(f);
			}
		}
		return filterUsers;
	}

	/**
	 * Devuelve las novedades por las fechas inicio, fin
	 * 
	 * @param fechai
	 * @param fechaf
	 * @return Novedades
	 * @throws Exception
	 */
	public List<Novedades> FindAllNovedadesByFecha(Timestamp fechai,
			Timestamp fechaf) throws Exception {
		Novedades f = null;
		List<Novedades> filterUsers = new ArrayList<Novedades>();
		ResultSet consulta = conDaotrans
				.consultaSQL("SELECT s.sol_id as numerosolicitud,s.sol_id_solicitante as cedula, s.sol_nom_solicitante as nombresolicitante, "
						+ " (select  lu.lug_nombre from trans_lugares lu where lu.lug_id = s.lug_id_origen)||' - '|| "
						+ " (select lu.lug_nombre from trans_lugares lu where lu.lug_id = s.lug_id_destino) as lugardestino,  s.sol_fecha as fecha, "
						+ " s.sol_fecha_aprobacion as fecha_aprobacion,s.sol_hora_inicio as hora_inicio, s.sol_hora_fin as hora_fin, s.sol_estado as estado, tb.gerencia, tb.direccion, "
						+ " s.sol_novedades as novedades from trans_solicitud s,  "
						+ " dblink('dbname=yachay host= 10.1.0.158 user=adm_bicichay password=y-4IO4SDwu_! port=5432',' "
						+ " SELECT f.per_dni , f.fun_gerencia, f.fun_direccion "
						+ " FROM gen_funcionarios_institucion f ' ) as tb(dni name, gerencia name, direccion name ) "
						+ " where s.sol_id_solicitante = tb.dni and s.sol_fecha between "
						+ " '"
						+ fechai
						+ "' and  '"
						+ fechaf
						+ "' "
						+ " and s.sol_novedades not like '' Order by s.sol_fecha desc ");
		if (consulta != null) {
			while (consulta.next()) {
				f = new Novedades();
				f.setSol_Id(Integer.parseInt(consulta
						.getString("numerosolicitud")));
				f.setSol_usuario_Cedula(consulta.getString("cedula"));
				f.setSol_usuario_Nombre(consulta.getString("nombresolicitante"));
				f.setSol_lugarordes(consulta.getString("lugardestino"));
				f.setSol_Fecha(new Timestamp(Funciones.stringToDate(
						consulta.getString("fecha")).getTime()));
				f.setSol_Fecha_Aprobacion(new Timestamp(Funciones.stringToDate(
						consulta.getString("fecha_aprobacion")).getTime()));
				f.setSol_Hora_Inicio(consulta.getString("hora_inicio"));
				f.setSol_Hora_Fin(consulta.getString("hora_fin"));
				f.setSol_Estado(consulta.getString("estado"));
				f.setSol_Gerencia(consulta.getString("gerencia"));
				f.setSol_Direccion(consulta.getString("direccion"));
				f.setSol_Novedades(consulta.getString("novedades"));
				filterUsers.add(f);
			}
		}
		return filterUsers;
	}

	/**
	 * listar todos los conductores funcionarios
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<TransFuncionarioConductor> findAllConductFuncionarios() {
		return mDAO.findAll(TransFuncionarioConductor.class);
	}

	/**
	 * buscar los vehuculos por ID
	 * 
	 * @param vehi_id
	 * @throws Exception
	 */
	public TransParametro parametroByID(String parametro) throws Exception {
		return (TransParametro) mDAO.findById(TransParametro.class, parametro);
	}

}
