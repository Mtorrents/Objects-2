package test;

import java.time.LocalDate;

import datos.Cliente;
import negocio.ClienteABM;

public class TestAgregarCliente {

	public static void main(String[] args) {
		String apellido = "Torrents";
		String nombre = "Martin";
		int documento = 12345678;
		LocalDate fechaDeNacimiento = LocalDate.now();
		ClienteABM abm = new ClienteABM();
		try {
			abm.agregar(apellido, nombre, documento, fechaDeNacimiento);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
