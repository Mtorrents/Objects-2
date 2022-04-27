package funciones;

import java.time.LocalDate;
import java.time.LocalTime;

public class Funciones {

	public static boolean esBisiesto(int anio) {
		boolean resultado = false;
		if (anio % 4 == 0 && ((anio % 100 != 0) || (anio % 400 == 0))) {
			resultado = true;
		}
		return resultado;
	}

	public static int traerAnio(LocalDate f) {
		return f.getYear();
	}

	public static int traerMes(LocalDate f) {
		return f.getMonthValue();
	}

	public static int traerDia(LocalDate f) {
		return f.getDayOfMonth();
	}

	public static boolean esFechaValida(int anio, int mes, int dia) {
		boolean resultado = false;
		if (mes >= 0 && mes <= 11) {
			if (dia > 0 && dia < 31) {
				resultado = true;
			}
		}
		resultado = true;

		if (esBisiesto(anio) == true) {
			if (dia == 29 && mes == 02) {
				resultado = true;
			}
		}
		return resultado;
	}

	public static LocalDate traerFecha(int anio, int mes, int dia) {
		LocalDate fecha = null;
		if (esFechaValida(anio, mes, dia)) {
			fecha = fecha.of(anio, mes, dia);
		} else {
			fecha = null;
		}
		return fecha;
	}

	public static LocalDate traerFecha(String fecha) {
		return traerFecha(Integer.parseInt(fecha.substring(6, 10)), Integer.parseInt(fecha.substring(3, 5)),
				Integer.parseInt(fecha.substring(0, 2)));
	}

	public static String traerFechaCorta(LocalDate fecha) {
		String dia = String.valueOf(fecha.getDayOfWeek());
		String mes = String.valueOf(fecha.getMonthValue());
		String anio = String.valueOf(fecha.getYear());
		return dia + "/" + mes + "/" + anio;
	}

	public static String traerFechaCortaHora(LocalDate fecha, LocalTime hora) {
		String traerFechaCortaHora = traerFechaCorta(fecha) + " " + hora.getHour() + ":"
				+ hora.getMinute() + ":" + hora.getSecond() + ":" + hora.getNano();
		return traerFechaCortaHora;

	}

	public static boolean esDiaHabil(LocalDate fecha) {
		boolean resultado = true;
		if (fecha.getDayOfWeek().getValue() == 1 || fecha.getDayOfWeek().getValue() == 7) {
			resultado = false;
		}
		return resultado;
	}

	public static String traerDiaDeLaSemana(LocalDate fecha) {
		return fecha.getDayOfWeek().getValue() + "";
	}

	public static String traerMesEnLetras(LocalDate fecha) {
		int mes = fecha.getMonthValue();
		String month = "";
		switch (mes) {
		case 0: {
			month = "Enero";
			break;
		}
		case 1: {
			month = "Febrero";
			break;
		}
		case 2: {
			month = "Marzo";
			break;
		}
		case 3: {
			month = "Abril";
			break;
		}
		case 4: {
			month = "Mayo";
			break;
		}
		case 5: {
			month = "Junio";
			break;
		}
		case 6: {
			month = "Julio";
			break;
		}
		case 7: {
			month = "Agosto";
			break;
		}
		case 8: {
			month = "Septiembre";
			break;
		}
		case 9: {
			month = "Octubre";
			break;
		}
		case 10: {
			month = "Noviembre";
			break;
		}
		case 11: {
			month = "Diciembre";
			break;
		}
		}
		return month;
	}
}