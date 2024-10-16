package p2;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession; // Para acceder al entorno de sesión

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String correo = request.getParameter("correo"); // Nombre del correo
		String contraseña = request.getParameter("contraseña"); // Clave
		HttpSession session = request.getSession(true); // Accedemos al entorno de sesión
		
		AccesoBD con = AccesoBD.getInstance();
		if ((correo != null) && (contraseña != null)) {
		int id = con.ComprobarUsuarioBD(correo,contraseña);
		if (id>0) {
		session.setAttribute("correo",correo);
		session.setAttribute("id",id);
		}
		else {
		session.setAttribute("mensaje","Usuario y/o contraseña incorrectos");
		}
		response.sendRedirect("./html/inicioSesion.jsp");
		} 
	}

}
