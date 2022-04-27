package negocio;

import dao.CuotaDao;
import datos.Cuota;

public class CuotaABM {
	private CuotaDao dao = new CuotaDao();
	
	public void modificar(Cuota c) throws Exception {
		if(traer(c.getIdCuota()) == null) {
			throw new Exception("No se puede modificar la cuota. El id no existe");
		}
		dao.actualizar(c);
	}
	
	public Cuota traer(long idCuota) throws Exception {
		Cuota c = dao.traer(idCuota);
		if(c == null) {
			throw new Exception("La cuota no existe");
		}
		return c;
	}
}
