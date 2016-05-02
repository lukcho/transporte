package transporte.controller.gestion;

import java.util.List;

import transporte.general.connection.SingletonJDBC;
import transporte.model.dao.entities.Novedades;
import transporte.model.manager.ManagerCarga;

public class test {

	public static SingletonJDBC a;
	public static Integer b;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ManagerCarga mg = new ManagerCarga();
		try {
			List<Novedades> nov = mg.FindAllNovedades();
			System.out.println(nov.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
