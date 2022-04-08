package test;

import datos.Cliente;
import negocio.ClienteABM;

public class TestActualizarCliente {
	public static void main(String[] args) {
		ClienteABM abm = new ClienteABM();
		long id = 1;
// traer el obj a modificar
		Cliente c = abm.traer(id);
		System.out.println("Cliente a Modificar -->" + c);
// modificar por set los atributos
		c.setDni(39642511);
		try {
			abm.modificar(c);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} // update del objeto
		int dni = 39642511;
		Cliente cModif = abm.traer(dni);
		System.out.println("Cliente Modificado -->" + cModif);
	}
}
