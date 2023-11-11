package negocio;

import java.util.ArrayList;

import entidades.Cuenta;
import entidades.Prestamo;

public interface PrestamoNegocio {
	
	public boolean insert (Prestamo prestamo);
	public boolean delete (Prestamo prestamo);
	public ArrayList<Prestamo> readAll();
	public boolean update (Prestamo prestamo);
	public float calcularImporteConIntereses(float importe_pedido, int cant_cuotas);
	public float calcularMontoxMes(int cant_cuotas, float importe_con_intereses);
	public ArrayList<Prestamo> getPrestamoxCuentas(ArrayList<Cuenta> cuentasCliente);
<<<<<<< HEAD
	public ArrayList<Prestamo> getPrestamosxCBU (String CBU, ArrayList <Cuenta> cuentasCliente);
=======
	public ArrayList<Prestamo> readAllByEstado(String estado);
>>>>>>> b8e7ea639bb6c7d018e220d5024bb4dd2cfca2ec
}
