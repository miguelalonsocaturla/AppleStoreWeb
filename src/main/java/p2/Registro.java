package p2;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * Servlet implementation class Registro
 */
@WebServlet("/Registro")
public class Registro extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        AccesoBD con = AccesoBD.getInstance(); 
        HttpSession session = request.getSession(true); // Accedemos al entorno de sesión
        UsuarioBD user = new UsuarioBD();
        boolean repetido=false;
        List<UsuarioBD> usuarios = con.obtenerUsuariosBD();
        for(UsuarioBD usuario : usuarios)
        {
        	
        	if(request.getParameter("correo").contentEquals(usuario.getCorreo()) ) {
        		repetido=true;
        	}
        }
       
        if(repetido==true) {
        	String mensaje = "Este correo ya esta asignado a otra cuenta."; 
            session.setAttribute("mensaje",mensaje);
            response.sendRedirect("./html/registro.jsp");
	        
        }
        else  {
        	user.setNombre(request.getParameter("nombre"));
	        user.setApellidos(request.getParameter("apellidos"));
	        user.setDireccion(request.getParameter("direccion"));
	        user.setCiudad(request.getParameter("ciudad"));
	        user.setCP(Integer.valueOf(request.getParameter("CP")));
	        String tel = (String) request.getParameter("telefono");
	        user.setTelefono(Integer.valueOf(tel));
	        user.setCorreo(request.getParameter("correo"));
	        session.setAttribute("correo",request.getParameter("correo"));
	        String claveNueva = request.getParameter("contraseña");
	        user.setContraseña(claveNueva);
	        con.añadirUsuarioBD(user);
	        int id=con.ComprobarUsuarioBD(request.getParameter("correo"), request.getParameter("contraseña"));
	        session.setAttribute("id",id);
	        String mensaje = "Usuario registrado con exito"; 
	        
	        session.setAttribute("mensaje",mensaje);
	        response.sendRedirect("./html/panelUsuario.jsp");
        	
        }
	}

}
