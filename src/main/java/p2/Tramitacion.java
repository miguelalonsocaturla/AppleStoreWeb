package p2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * Servlet implementation class Tramitacion
 */
@WebServlet("/Tramitacion")
public class Tramitacion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 HttpSession session = request.getSession(true);
         @SuppressWarnings("unchecked")
		ArrayList<ProductoBD> carro = (ArrayList<ProductoBD>) session.getAttribute("carro");
         AccesoBD con = AccesoBD.getInstance();
         List<PedidoBD> pedidos = con.obtenerPedidosBD();
         
         
         int codigopedido = pedidos.size()+1;
         PedidoBD pedido = new PedidoBD();
         pedido.setCodigo(codigopedido);
         pedido.setPersona((int)session.getAttribute("id"));
         pedido.setEstado("En proceso");
         pedido.setImporte(Integer.parseInt(request.getParameter("preciototal").toString()));
         pedido.setFecha(java.time.LocalDate.now().toString());
         
         session.setAttribute("IDPedido", pedido.getCodigo());
         con.nuevoPedido(pedido);
         
         for(int i = 0; i < carro.size(); i++)
         {
             ProductoBD producto = new ProductoBD();
             producto = carro.get(i);
             DetallePedidoBD detallepedido = new DetallePedidoBD();
             detallepedido.setCodigo_pedido(codigopedido);
             detallepedido.setCodigo_producto(producto.getCodigo());
             detallepedido.setPrecio_unitario(Math.round(producto.getPrecio()/producto.getUnidades()));
             detallepedido.setUnidades(producto.getUnidades());
             con.nuevoDetallePedido(detallepedido);
             con.ajusteStock(producto.getCodigo(),producto.getUnidades());
         }
         
         session.setAttribute("codigoPedido", codigopedido);
         response.sendRedirect("./html/pedidoFinalizado.jsp");
	}

}
