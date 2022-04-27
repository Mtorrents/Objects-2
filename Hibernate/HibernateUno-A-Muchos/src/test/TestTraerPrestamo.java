package test;

import java.util.List;
import datos.Cliente;
import datos.Prestamo;
import negocio.ClienteABM;
import negocio.PrestamoABM;

public class TestTraerPrestamo {
	public static void main(String[] args) throws Exception {
		PrestamoABM prestamoABM = new PrestamoABM();
		long idPrestamo = 1;
		System.out.println("\n---> TraerPrestamo idPrestamo=" + idPrestamo + "\n\n");
		Prestamo p = prestamoABM.traerPrestamo(idPrestamo);
		System.out.println(p + "\nPertenece a " + p.getCliente());
		ClienteABM clienteABM = new ClienteABM();
		int dni = 14000000;
		Cliente c = clienteABM.traer(dni);
		System.out.println("\n---> TraerPrestamos del Cliente=" + c + "\n\n");
		List<Prestamo> prestamos = prestamoABM.traerPrestamo(c);
//implementar Si este cliente no tiene prestamos otorgados imprima el mensaje
		if(prestamos.size() == 0) {
			throw new Exception("El cliente no tiene prestamos otorgados");
		}
		for (Prestamo o : prestamos)
			System.out.println(o + "\nPertenece a" + o.getCliente());
	}
}
