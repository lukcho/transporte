package transporte.model.manager;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import transporte.general.connection.SingletonJDBC;
import transporte.model.dao.entities.Datos;
import transporte.model.dao.entities.Persona;
import transporte.model.dao.entities.PersonaFuncionario;

/**
 * Contiene la lógica de negocio para realizar el proceso de reserva de sitios
 * 
 * @author
 * 
 */
public class ManagerCarga {

	private SingletonJDBC conDao;

	public ManagerCarga() {
		conDao = SingletonJDBC.getInstance();
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
	 * Devuelve un funcionario por dni
	 * 
	 * @param dni
	 * @return Funcionario
	 * @throws Exception
	 */
	public  List<PersonaFuncionario> funcionarioByGerencia(String Gerencia) throws Exception {
		PersonaFuncionario f = null;
		List<PersonaFuncionario> filterUsers = new ArrayList<PersonaFuncionario>();
		ResultSet consulta = conDao
				.consultaSQL("SELECT p.per_id as dni , p.per_nombres as nombres,  p.per_apellidos as apellidos, p.per_correo as correo, p.per_telefono as telefono, "
						+ " f.fun_cargo as cargo, f.fun_jefe_inmediato as jefe, f.fun_gerencia as gerencia, f.fun_direccion as direccion "
						+ " FROM gen_persona p INNER JOIN gen_usuario u ON p.per_id = u.per_id "
						+ " INNER JOIN gen_funcionario f  on f.per_id = u.per_id "
						+ " WHERE f.fun_gerencia = '" + Gerencia + "'");
		if (consulta != null) {
			while(consulta.next()){
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
	
	
	public List<Datos> estadoEvaluaciones(String periodo, String gerencia, String direccion, String estado) throws Exception{
		  List<Datos> listDatos = new ArrayList<Datos>();
		  ResultSet consulta =  conDao.consultaSQL("select te.evaluado, per.per_apellidos||' '||per.per_nombres as nevaluado,"
		    + " te.evaluador, (select pe.per_apellidos||' '||pe.per_nombres from gen_persona pe "
		    + " where pe.per_id= te.evaluador) as nevaluador , fu.fun_gerencia as gerencia, fu.fun_direccion as direccion,"
		    + " fu.fun_tipo as tipoF, fu.fun_tipo_evaluacion as tipoE, te.categoria, fu.fun_jefe_inmediato as jefe,"
		    + " (select p.per_apellidos||' '||p.per_nombres from gen_persona p where p.per_id =fu.fun_jefe_inmediato )as nombreJefe,"
		    + " te.estado from gen_persona per, gen_funcionario fu,"
		    + " dblink(dbname=evaper_yachay port=5432 host= 10.1.0.158 user=adm_svcyachay password=_50STg-FGh2h , "
		    + " 'SELECT ee.prd_id, ee.per_id_encuestado, ee.per_id_encuestador, ee.evf_categoria, ee.evf_estado, "
		    + " (case when evf_estado=''E'' then ''EVALUACIÓN REALIZADA'' else ''POR EVALUAR'' end ) as estado"
		    + " from evaper_evaluacionfuncionario ee ')"
		    + " AS te(periodo name, evaluado name, evaluador name, categoria name, idestado name, estado name) "
		    + " where per.per_id= fu.per_id and per.per_id= te.evaluado"
		    + " and fu.fun_gerencia like '"+gerencia+"' " 
		    + " and fu.fun_direccion like '"+direccion+"'"
		    + " and te.periodo='"+periodo+"'"
		    + " and te.idestado like '"+estado+"' ;");
		  if(consulta!=null){
		   while(consulta.next()){
		    Datos d = new Datos();
//		    d.setDniEvaluado(consulta.getString("evaluado"));
//		    d.setnEvaluado(consulta.getString("nevaluado"));
//		    d.setDniEvaluador(consulta.getString("evaluador"));
//		    d.setnEvaluador(consulta.getString("nevaluador"));
//		    d.setGerencia(consulta.getString("gerencia"));
//		    d.setDireccion(consulta.getString("direccion"));
//		    d.setTipo(consulta.getString("tipoF"));
//		    d.setTipoEvaluacion(consulta.getString("tipoE"));
//		    d.setCategoria(consulta.getString("categoria"));
//		    d.setJefeInmediato(consulta.getString("jefe"));
//		    d.setNombreJefe(consulta.getString("nombreJefe"));
//		    d.setEstadoEv(consulta.getString("estado"));
//		    listDatos.add(d);
		    d=null;
		   }
		  }
		  else throw new Exception("No se ha podido obtener información.");
		  return listDatos;
		 }
	
//	select s.sol_id as numerosolicitud, s.sol_nom_solicitante as nombresolicitante, 
//	(select  lu.lug_nombre from trans_lugares lu where lu.lug_id = s.lug_id_origen)||' '||(select lu.lug_nombre from trans_lugares lu where lu.lug_id = s.lug_id_destino) as lugardestino,  s.sol_fecha as fecha, 
//	s.sol_fecha_aprobacion as fecha_aprobacion, s.sol_estado as estado, 
//	s.sol_novedades as novedades from trans_solicitud s
//	select 	te.evaluado, per.per_apellidos||' '||per.per_nombres as nevaluado,
//    te.evaluador, (select pe.per_apellidos||' '||pe.per_nombres from gen_persona pe 
//    where pe.per_id= te.evaluador) as nevaluador , fu.fun_gerencia as gerencia, fu.fun_direccion as direccion,
//    fu.fun_tipo as tipoF, fu.fun_tipo_evaluacion as tipoE, te.categoria, fu.fun_jefe_inmediato as jefe,
//    (select p.per_apellidos||' '||p.per_nombres from gen_persona p where p.per_id =fu.fun_jefe_inmediato )as nombreJefe,
//    te.estado from gen_persona per, gen_funcionario fu,
//    dblink(dbname=evaper_yachay port=5432 host= 10.1.0.158 user=adm_svcyachay password=_50STg-FGh2h , 
//    SELECT ee.prd_id, ee.per_id_encuestado, ee.per_id_encuestador, ee.evf_categoria, ee.evf_estado, 
//    (case when evf_estado=''E'' then ''EVALUACIÓN REALIZADA'' else ''POR EVALUAR'' end ) as estado
//    from evaper_evaluacionfuncionario ee)
//    AS te(periodo name, evaluado name, evaluador name, categoria name, idestado name, estado name) 
//    where per.per_id= fu.per_id and per.per_id= te.evaluado
//    and fu.fun_gerencia like 'gerencia' 
//    and fu.fun_direccion like 'direccion'
//    and te.periodo='periodo'
//    and te.idestado like 'estado';
//
//
//select s.sol_id as numerosolicitud, s.sol_nom_solicitante as nombresolicitante, 
//(select  lu.lug_nombre from trans_lugares lu where lu.lug_id = s.lug_id_origen)||' '||(select lu.lug_nombre from trans_lugares lu where lu.lug_id = s.lug_id_destino) as lugardestino,  s.sol_fecha as fecha, 
//s.sol_fecha_aprobacion as fecha_aprobacion, s.sol_estado as estado, 
//s.sol_novedades as novedades from trans_solicitud s
//
//dblink(dbname=evaper_yachay port=5432 host= 10.1.0.158 user=adm_svcyachay password=_50STg-FGh2h , 
//SELECT ee.prd_id, ee.per_id_encuestado, ee.per_id_encuestador, ee.evf_categoria, ee.evf_estado, 
//    (case when evf_estado=''E'' then ''EVALUACIÓN REALIZADA'' else ''POR EVALUAR'' end ) as estado
//    from evaper_evaluacionfuncionario ee)
//    AS te(periodo name, evaluado name, evaluador name, categoria name, idestado name, estado name) 
//    where per.per_id= fu.per_id and per.per_id= te.evaluado
//    and fu.fun_gerencia like 'gerencia' 
//    and fu.fun_direccion like 'direccion'
//    and te.periodo='periodo'
//    and te.idestado like 'estado';
//
//
//dblink(dbname=evaper_yachay host= '10.1.0.158' user=adm_svcyachay password='_50STg-FGh2h' port='5432', 
//SELECT p.per_id as dni , p.per_nombres as nombres, 
//				 p.per_apellidos as apellidos, p.per_correo as correo, 
//				 f.fun_cargo as cargo, f.fun_jefe_inmediato as jefe, f.fun_gerencia as gerencia, f.fun_direccion as direccion, 
//				 (select pe.per_nombres||' '||pe.per_apellidos 
//				 from gen_persona pe where pe.per_id=f.fun_jefe_inmediato) as nombreJefe 
//				 FROM gen_persona p INNER JOIN gen_usuario u ON p.per_id = u.per_id 
//				 INNER JOIN gen_funcionario f  on f.per_id = u.per_id 
//				 WHERE u.usu_login = 'aquina';
}
