package test;

import java.time.LocalDate;

import datos.Cliente;
import negocio.ClienteABM;
import negocio.PrestamoABM;

public class TestAgregarClienteYPrestamo {

	public static void main(String[] args) {
		long idCliente = 2;
		ClienteABM cliAbm = new ClienteABM();
		PrestamoABM preAbm = new PrestamoABM();
		Cliente c = cliAbm.traer(idCliente);
		double monto = 5000;
		double interes = 0.10;
		int cantCuotas = 24;
		try {
			preAbm.agregar(LocalDate.now(), monto, interes, cantCuotas, c);
			System.out.println("Prestamo asignado a " + c.getNombre());
		}catch(Exception e) {
			System.out.println("Excepcion: " + e.getMessage());
		}
	}
}
