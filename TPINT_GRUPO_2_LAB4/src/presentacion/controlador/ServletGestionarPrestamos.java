package presentacion.controlador;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Cliente;
import entidades.Prestamo;
import entidades.Usuario;
import negocioImpl.PrestamoNegocioImpl;

@WebServlet("/ServletGestionarPrestamos")
public class ServletGestionarPrestamos extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Usuario usuario = new Usuario();
	PrestamoNegocioImpl pneg = new PrestamoNegocioImpl();
    
    public ServletGestionarPrestamos() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		usuario = (Usuario) request.getSession().getAttribute("usuario");  
		request.setAttribute("admin_actual", usuario);
		
		ArrayList<Prestamo> listaPrestamos = pneg.readAllByEstado("Solicitado");
		request.setAttribute("listaPrestamos", listaPrestamos);
		
		RequestDispatcher rd = request.getRequestDispatcher("/gestionPrestamos.jsp");   
	    rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		usuario = (Usuario) request.getSession().getAttribute("usuario");  
		request.setAttribute("admin_actual", usuario);
		
		ArrayList<Prestamo> listaPrestamos = new ArrayList<>();
		listaPrestamos = pneg.readAllByEstado("Solicitado");
		request.setAttribute("listaPrestamos", listaPrestamos);
		
		if(request.getParameter("buttonRechazar") != null || request.getParameter("buttonAprobar") != null) {
			int ID = Integer.parseInt(request.getParameter("ID"));
			Prestamo prestamo = pneg.getPrestamoByID(ID);
			String confirm = null;
			if (request.getParameter("buttonRechazar") != null) {
				confirm = "Esta seguro de que quiere rechazar el prestamo " + prestamo.getId_prestamo() + "?";
				request.setAttribute("confirmRechazar" + ID, confirm);
			} else {
				confirm = "Esta seguro de que quiere aprobar el prestamo " + prestamo.getId_prestamo() + "?";
				request.setAttribute("confirmAprobar" + ID, confirm);
			}
		}
		
		if(request.getParameter("confirmRechazar") != null) {
			int ID = Integer.parseInt(request.getParameter("ID"));
			
			boolean rechazar = pneg.update(ID, "Rechazado");
			request.setAttribute("rechazar", rechazar);
			
			listaPrestamos = pneg.readAllByEstado("Solicitado");
			request.setAttribute("listaPrestamos", listaPrestamos);
		}
		if(request.getParameter("confirmAprobar") != null) {
			int ID = Integer.parseInt(request.getParameter("ID"));
			
			boolean aprobar = pneg.update(ID, "Aprobado");
			request.setAttribute("aprobar", aprobar);
			
			listaPrestamos = pneg.readAllByEstado("Solicitado");
			request.setAttribute("listaPrestamos", listaPrestamos);
		}
		/*
		if(request.getParameter("buttonAprobar") != null) {
			Prestamo prestamo = new Prestamo();
			Prestamo prestamoSeleccionado = (Prestamo) request.getAttribute("prestamo");
			
			prestamo.setId_prestamo(prestamoSeleccionado.getId_prestamo());
			prestamo.setCant_cuotas(prestamoSeleccionado.getCant_cuotas());
			prestamo.setCBU(prestamoSeleccionado.getCBU());
			prestamo.setEstado(prestamoSeleccionado.getEstado());
			prestamo.setFecha_realizacion(prestamoSeleccionado.getFecha_realizacion());
			prestamo.setImporte_con_intereses(prestamoSeleccionado.getImporte_con_intereses());
			prestamo.setImporte_pedido(prestamoSeleccionado.getImporte_pedido());
			prestamo.setMonto_x_mes(prestamoSeleccionado.getMonto_x_mes());
		}
		*/
		RequestDispatcher rd = request.getRequestDispatcher("/gestionPrestamos.jsp");   
	    rd.forward(request, response);
	}

}
