package negocio;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;

import dao.ClienteDao;
import datos.Cliente;
import datos.Prestamo;

public class ClienteABM {

	ClienteDao dao = new ClienteDao();
	
	public Cliente traer(long idCliente) {
		Cliente c = dao.traer(idCliente);
		return c;
	}
	
	public Cliente traer(int dni) {
		Cliente c = dao.traer(dni);
		return c;
	}
	
	public int agregar(String apellido, String nombre, int dni, LocalDate fechaDeNacimiento) throws Exception {
		if(traer(dni) != null) {
			throw new Exception("Ya existe un cliente con ese dni");
		}
		Cliente c = new Cliente(apellido, nombre, dni, fechaDeNacimiento);
		return dao.agregar(c);
	}
	
	public void modificar(Cliente c) throws Exception {
		if(traer(c.getDni()) == null) {
			throw new Exception("No se puede modificar el Cliente. El dni no existe");
		} else if(traer(c.getIdCliente()) == null) {
			throw new Exception("No se puede modificar el Cliente. El id no existe");
		}
		dao.actualizar(c);
	}
	
	public void eliminar(long idCliente) throws Exception {
		Cliente c = dao.traer(idCliente);
		comprobarCliente(c, idCliente);
		dao.eliminar(c);
	}
	
	public List<Cliente> traer() {
		return dao.traer();
	}
	
	public Cliente traerClienteYPrestamos(long idCliente) throws HibernateException, Exception {
		comprobarPrestamos(dao.traerClienteYPrestamos(idCliente).getPrestamos());
		return dao.traerClienteYPrestamos(idCliente);
	}
	
	public static boolean comprobarPrestamos(Set<Prestamo> lstPrestamos) throws Exception {
		if(lstPrestamos.isEmpty()) {
			throw new Exception("El Cliente seleccionado no posee ningun prestamo a su nombre\n");
		}
		return true;
	}
	
	public static boolean comprobarCliente(Cliente cliente, long idCliente) throws Exception {
		if(cliente == null) {
			throw new Exception("No existe tal cliente con el id" + idCliente + "para eliminarlo\n");
		}
		return true;
	}
}
