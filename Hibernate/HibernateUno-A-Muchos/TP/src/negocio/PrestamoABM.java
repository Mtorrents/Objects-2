package negocio;
import dao.PrestamoDao;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import datos.Cliente;
import datos.Cuota;
import datos.Prestamo;

public class PrestamoABM {
	private PrestamoDao dao=new PrestamoDao();
	
	public Prestamo traerPrestamo(long idPrestamo) throws Exception{	
		
		Prestamo p =dao.traer(idPrestamo);
		if(p == null)
			throw new Exception("No existe ningun prestamo con el id: " + idPrestamo + "\n\n");
		return p;
	}
	
	
	public List<Prestamo> traerPrestamo(Cliente c) throws Exception{
		comprobarPrestamos(dao.traer(c));
		return dao.traer(c);
	}
	public int agregar(LocalDate fecha, double monto, double interes, int cantCuotas, Cliente cliente) {
		Prestamo p = new Prestamo(fecha, monto, interes, cantCuotas, cliente);
		double amortizacion, saldoPendiente = monto, cuota, deuda, interesCuota, punitorios = 0;
		LocalDate fecha1 = LocalDate.now().plusMonths(1);
		Set<Cuota> listaCuotas = new LinkedHashSet<Cuota>();
		for (int i = 1; i <= cantCuotas; i++) {
			if (i == 1) {
				amortizacion = (saldoPendiente * interes) / (Math.pow(1 + interes, cantCuotas) - 1);
				interesCuota = saldoPendiente * interes;
				cuota = amortizacion + interesCuota;
				deuda = saldoPendiente - amortizacion;
				saldoPendiente -= amortizacion;
				listaCuotas.add(new Cuota(i, fecha1, saldoPendiente, amortizacion, interesCuota, cuota, deuda,punitorios, fecha1, p));
			}else {
				amortizacion = (saldoPendiente * interes) / (Math.pow(1 + interes, cantCuotas - 1) - 1);
				interesCuota = saldoPendiente * interes;
				cuota = amortizacion + interesCuota;
				deuda = saldoPendiente - amortizacion;
				saldoPendiente -= amortizacion;
				fecha1 = fecha1.plusMonths(1);
				listaCuotas.add(new Cuota(i, fecha1, saldoPendiente, amortizacion, interesCuota, cuota, deuda, punitorios,  fecha1, p));
			}

		}
		return dao.agregar(p, listaCuotas);
	}
	
	public static boolean comprobarPrestamos(List<Prestamo> lstPrestamos) throws Exception {
		if(lstPrestamos.isEmpty())
			throw new Exception("El Cliente seleccionado no posee ningun prestamo a su nombre\n");
		
		
		return true;
	}
	
}