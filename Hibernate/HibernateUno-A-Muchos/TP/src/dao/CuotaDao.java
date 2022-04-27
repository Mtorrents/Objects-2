package dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import datos.Cliente;
import datos.Cuota;
import datos.Prestamo;

public class CuotaDao {
	private static Session session;
	private Transaction tx;

	private void iniciaOperacion() throws HibernateException {
		session = HibernateUtil.getSessionFactory().openSession();
		tx = session.beginTransaction();
	}

	private void manejaExcepcion(HibernateException he) throws HibernateException {
		tx.rollback();
		throw new HibernateException("ERROR en la capa de acceso a datos", he);
	}

	public int agregar(Cuota objeto) {
		int id = 0;
		try {
			iniciaOperacion();
			id = Integer.parseInt(session.save(objeto).toString());
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
		return id;
	}

	public void actualizar(Cuota objeto) throws HibernateException {
		try {
			iniciaOperacion();
			session.update(objeto);
			tx.commit();

		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
	}

	public void eliminar(Cuota objeto) throws HibernateException {
		try {
			iniciaOperacion();
			session.delete(objeto);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
	}

	public Cuota traer(long idCuota) throws HibernateException {
		Cuota objeto = null;
		try {
			iniciaOperacion();
			objeto = (Cuota) session.get(Cuota.class, idCuota);
		} finally {
			session.close();
		}
		return objeto;
	}

	public Cuota traer(int nroCuota) throws HibernateException {
		Cuota objeto = null;
		try {
			iniciaOperacion();
			objeto = (Cuota) session.createQuery("from Cuota c where c.nroCuota=" + nroCuota).uniqueResult();
		} finally {
			session.close();
		}
		return objeto;
	}
	
	@SuppressWarnings("unchecked")
	public List<Cuota> traer() throws HibernateException {
		List<Cuota> lista = null;
		try {
			iniciaOperacion();
			lista = session.createQuery("from Cuota c order by c.cuota").list();
		} finally {
			session.close();
		}
		return lista;
	}
	
	@SuppressWarnings("unchecked")
	public List<Cuota> traerCuotas(Prestamo p) throws HibernateException {
		List<Cuota> lista = null;
		try {
			String HQL= "from Prestamo p inner join fetch p.cliente c where c.idcliente=" + p.getIdPrestamo();
			lista = session.createQuery(HQL).list();
		} finally {
			session.close();
		}
		return lista;
	}
}
