package ana.control.order;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ana.model.order.mgr.OrderMgr;

/**
 * Servlet implementation class OrderProcessor
 */
@SuppressWarnings({"rawtypes","unchecked"})
@WebServlet("/OrderProcessor")
public class OrderProcessor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderProcessor() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HashMap raworder = new HashMap();
		for(Enumeration<String> e = request.getParameterNames(); e.hasMoreElements();){
			String name = e.nextElement();
			System.out.println(name+"="+request.getParameter(name));
			raworder.put(name, request.getParameter(name));
		}
		
//		String trade_code = request.getParameter("trade_code") == null ? null:request.getParameter("trade_code").toString();
		String adminid = request.getParameter("adminid") == null ? null:request.getParameter("adminid").toString();
		String orderlist = request.getParameter("orderlist") == null ? null:request.getParameter("orderlist").toString();
		
		/*改版后应当制成buffer表，传给IoC反射类*/
		
		new OrderMgr().adminGetOrder(orderlist, adminid);
		
		PrintWriter out = response.getWriter();
		out.print(0);
		out.flush();
	}

}
