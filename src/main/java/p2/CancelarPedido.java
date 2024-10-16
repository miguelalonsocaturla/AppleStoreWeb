package p2;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class cancelarPedido
 */
@WebServlet("/CancelarPedido")
public class CancelarPedido extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        AccesoBD con = AccesoBD.getInstance(); 
        
        con.cancelarPedidoBD(Integer.parseInt(request.getParameter("codigopedido").toString()));
        response.sendRedirect("./html/pedidosUsuario.jsp");
	}

}
