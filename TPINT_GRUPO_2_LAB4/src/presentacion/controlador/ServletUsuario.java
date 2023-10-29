package presentacion.controlador;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daoImpl.UsuarioDaoImpl;
import entidades.Usuario;

@WebServlet("/ServletUsuario")
public class ServletUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


    public ServletUsuario() {
        super();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	
		
    }
    
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        
        // Realizar la autenticaci�n ac� 
        
        UsuarioDaoImpl usuarioDao = new UsuarioDaoImpl();
        Usuario usuario = usuarioDao.getUsuarioxUser(userName);

        if (usuario != null) {
            // Comprobar las credenciales y cargar el objeto Usuario desde la base de datos
            if (password.equals(usuario.getContrase�a())) {
                // Guardar el objeto Usuario en la sesi�n
                request.getSession().setAttribute("usuario", usuario);
                
                // Redirigir al men� cliente
                response.sendRedirect("menuCliente.jsp");
            } else {
                // Contrase�a incorrecta, redirigir al formulario de inicio de sesi�n con un mensaje de error
            }
        } else {
            // Usuario no encontrado, redirigir al formulario de inicio de sesi�n con un mensaje de error
        }
    }
    
}