package transporte.controller.gestion;

import transporte.model.dao.entities.Persona;
import transporte.model.manager.ManagerCarga;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ManagerCarga mg = new ManagerCarga();
		try {
			Persona per = mg.funcionarioByDNI("aquina");
			System.out.println(per.getPerNombres());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
