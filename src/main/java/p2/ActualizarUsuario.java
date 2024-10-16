package p2;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ActualizarUsuario
 */
@WebServlet("/ActualizarUsuario")
public class ActualizarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession sesion = request.getSession(true);
        AccesoBD con = AccesoBD.getInstance(); 
        HttpSession session = request.getSession(true); // Accedemos al entorno de sesión
        UsuarioBD user = new UsuarioBD();
        
       
        	user.setNombre(request.getParameter("nombre"));
	        user.setApellidos(request.getParameter("apellidos"));
	        user.setDireccion(request.getParameter("direccion"));
	        user.setCiudad(request.getParameter("ciudad"));
	        user.setCP(Integer.valueOf(request.getParameter("cp")));
	        String tel = (String) request.getParameter("telefono");
	        user.setTelefono(Integer.valueOf(tel));
	        user.setCorreo((String)session.getAttribute("correo"));
	        con.editarUsuarioBD(user);
	        String mensaje = "Usuario editado con exito"; 
	        
	        sesion.setAttribute("mensaje",mensaje);
	        response.sendRedirect("./html/panelUsuario.jsp");
        	
        }
	
	}

