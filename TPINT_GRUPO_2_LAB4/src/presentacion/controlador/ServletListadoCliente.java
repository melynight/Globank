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
import entidades.Usuario;
import negocioImpl.ClienteNegocioImpl;

@WebServlet("/ServletListadoCliente")
public class ServletListadoCliente extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	Usuario usuario = new Usuario();
	
	ClienteNegocioImpl cneg = new ClienteNegocioImpl();
	
    public ServletListadoCliente() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<Cliente> listaClientes = cneg.readAllActivos();
		request.setAttribute("listaClientes", listaClientes);
		
		usuario = (Usuario) request.getSession().getAttribute("usuario");  
		request.setAttribute("admin_actual", usuario);
		
		String dni = request.getParameter("DNI"); 
		Cliente cliente = cneg.getClientexDNI(dni);
		if (cliente != null) {
		    request.setAttribute("clienteDNI", cliente);}
		
		RequestDispatcher rd = request.getRequestDispatcher("/listadoCliente.jsp");   
	    rd.forward(request, response);
	}
		
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		usuario = (Usuario) request.getSession().getAttribute("usuario");  
		request.setAttribute("admin_actual", usuario);
		
		if(request.getParameter("todos") != null) {
			ArrayList<Cliente> listaClientes = cneg.readAll();
			request.setAttribute("listaClientes", listaClientes);
		} else if (request.getParameter("activos") != null) {
			ArrayList<Cliente> listaClientes = cneg.readAllActivos();
			request.setAttribute("listaClientes", listaClientes);
		} else if (request.getParameter("inactivos") != null) {
			ArrayList<Cliente> listaClientes = cneg.readAllInactivos();
			request.setAttribute("listaClientes", listaClientes);
		}
		
		String errorMessage= "";
		
		if (request.getParameter("btnBuscarXDNI") != null) {
			ArrayList<Cliente> lista = new ArrayList<Cliente>();
			String dni = request.getParameter("DNI"); 
			if (cneg.getClientexDNI(dni) != null) {
				Cliente cliente = cneg.getClientexDNI(dni);
				lista.add(cliente);
			    request.setAttribute("listaClientes", lista);
			    
		} else {
			errorMessage="El DNI ingresado no existe";
			request.setAttribute("errorMessage", errorMessage);
		    request.setAttribute("listaClientes", lista);

		}
	}
			
		RequestDispatcher rd = request.getRequestDispatcher("/listadoCliente.jsp");   
	    rd.forward(request, response);
	}
}
