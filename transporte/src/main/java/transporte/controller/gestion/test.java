package transporte.controller.gestion;

import java.util.List;

import transporte.general.connection.SingletonJDBC;
import transporte.model.dao.entities.PersonaFuncionario;
import transporte.model.manager.ManagerCarga;

public class test {

	public static SingletonJDBC a;
	public static Integer b;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ManagerCarga mg = new ManagerCarga();
		try {
	//		List<PersonaFuncionario> pret = mg.funcionarioByGerencia("GERENCIA DE TECNOLOGÍAS");
			System.out.println(a.consultaSQL("SELECT max(o.solId) FROM trans_solicitud"));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
