package transporte.controller.gestion;

import java.util.List;

import transporte.model.dao.entities.PersonaFuncionario;
import transporte.model.manager.ManagerCarga;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ManagerCarga mg = new ManagerCarga();
		try {
			List<PersonaFuncionario> pret = mg.funcionarioByGerencia("GERENCIA DE TECNOLOGÍAS");
			System.out.println(pret.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
