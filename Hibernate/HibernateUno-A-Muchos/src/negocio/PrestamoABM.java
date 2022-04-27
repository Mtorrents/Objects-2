package negocio;

import dao.PrestamoDao;
import java.time.LocalDate;
import java.util.List;
import datos.Cliente;
import datos.Prestamo;

public class PrestamoABM {
	private PrestamoDao dao = new PrestamoDao();

	public Prestamo traerPrestamo(long idPrestamo) throws Exception{
//Implementar: si el no existe el prestamo lanzar la excepción
		Prestamo p = dao.traer(idPrestamo);
		if(p == null) {
			throw new Exception("El cliente no existe");
		}
		return p;
	}

	public List<Prestamo> traerPrestamo(Cliente c) {
		return dao.traer(c);
	}
	
	public int agregar(LocalDate fecha, double monto, double interes, int cantCuotas, Cliente cliente) {
		Prestamo p = new Prestamo(fecha, monto, interes, cantCuotas, cliente);
		return dao.agregar(p);
	}
	
	public void modificar(Prestamo p) {
		dao.actualizar(p);
	}
	/*
	 * Pendiente implementar Alta, Modificar
	 */
}