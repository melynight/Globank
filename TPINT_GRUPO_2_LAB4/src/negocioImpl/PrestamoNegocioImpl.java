package negocioImpl;

import java.util.ArrayList;
import dao.PrestamoDao;
import daoImpl.PrestamoDaoImpl;
import entidades.Cuenta;
import entidades.Prestamo;
import negocio.PrestamoNegocio;

public class PrestamoNegocioImpl implements PrestamoNegocio{

	PrestamoDao pdao = new PrestamoDaoImpl();
	
	@Override
	public boolean insert(Prestamo prestamo) {
		
		boolean filas = false;
		
		filas = pdao.insert(prestamo);
		
		return filas;
	}

	@Override
	public boolean delete(Prestamo prestamo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Prestamo> readAll() {
		return pdao.readAll();
	}
	
	public int getUltimoID() {
		
		return pdao.getUltimoID();
	
	}
	
	public float calcularImporteConIntereses(float importe_pedido, int cant_cuotas) {
		
		float importe_con_intereses = 0;
		float cuota_1 = 3;
		float cuota_3 = 5;
		float cuota_6 = 10;
		float cuota_12 = 15;
		float cuota_18 = 25;
	
		switch(cant_cuotas){
		
		case 1:
			importe_con_intereses = (importe_pedido*cuota_1/100) + importe_pedido;
			break;
		case 3: 
			importe_con_intereses = (importe_pedido*cuota_3/100) + importe_pedido;
			break;
		case 6:
			importe_con_intereses = (importe_pedido*cuota_6/100) + importe_pedido;
			break;
		case 12:
			importe_con_intereses = (importe_pedido*cuota_12/100) + importe_pedido;
		break;
		case 18:
			importe_con_intereses = (importe_pedido*cuota_18/100) + importe_pedido;
			break;
		
	
		}
		
		
		return importe_con_intereses;
	}
	
	public float calcularMontoxMes(int cant_cuotas, float importe_con_intereses) {
		

		return importe_con_intereses/cant_cuotas;
		
	
		
	}
	
	public ArrayList<Prestamo> getPrestamoxCuentas (ArrayList <Cuenta> cuentasCliente) {
		
		
		return pdao.getPrestamoxCuentas(cuentasCliente);
		
	}
	

	public ArrayList<Prestamo> getPrestamosxCBU (String CBU, ArrayList <Cuenta> cuentasCliente){
		
		
		for(Cuenta cuenta : cuentasCliente) {
			
			if(cuenta.getCBU().compareTo(CBU) == 0) { //validacion de que el cbu que consulte sea del cliente
				
				return pdao.getPrestamosxCBU(CBU);
			}
		}
		


		return null;
		}



	public ArrayList<Prestamo> readAllByEstado(String estado){
		return pdao.readAllByEstado(estado);
	}

	
	
	public Prestamo getPrestamoByID (int idPrestamo){
		
		return pdao.getPrestamoByID(idPrestamo);



	}

	@Override
	public boolean update(int prestamo, String estado) {
		boolean update = false;
		update = pdao.update(prestamo, estado);
		return update;

	}

	@Override
	public ArrayList<Float> cantPrestamosXEstado() {
		ArrayList<Prestamo> listaAprobado = pdao.readAllByEstado("Aprobado");
		ArrayList<Prestamo> listaRechazado = pdao.readAllByEstado("Rechazado");
		ArrayList<Prestamo> listaSolicitados = pdao.readAllByEstado("Solicitado");
		
		int cantAprobados = listaAprobado.size(), cantRechazados = listaRechazado.size(), cantSolicitados = listaSolicitados.size();
		int total = cantAprobados + cantRechazados + cantSolicitados;
		float porcentajeAprobados = ((float)cantAprobados/total)*100;
		float porcentajeRechazados = ((float)cantRechazados/total)*100;
		float porcentajeSolicitados = ((float)cantSolicitados/total)*100;
		
		ArrayList<Float> cantPrestamos = new ArrayList<>();
		cantPrestamos.add(porcentajeAprobados);
		cantPrestamos.add(porcentajeRechazados);
		cantPrestamos.add(porcentajeSolicitados);
		
		return cantPrestamos;
	}
}
