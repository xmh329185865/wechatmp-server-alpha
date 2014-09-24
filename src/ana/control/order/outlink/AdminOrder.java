package ana.control.order.outlink;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ana.model.auth.outlink.Authrizor;
import ana.view.order.outlink.Orderlist;

/**
 * Servlet implementation class Orderhandin
 */
@WebServlet("/AdminOrder")
public class AdminOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminOrder() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("gbk");
		String sig_get = request.getParameter("sig")==null ? "":request.getParameter("sig").toString();
		String url = request.getParameter("url")==null ? "":request.getParameter("url").toString();

		String sig = Authrizor.getLinkCode();
		if (!sig.equalsIgnoreCase(sig_get)){
			PrintWriter out = response.getWriter();
			out.print("Invalid Request!");
			out.flush();
			return;
		}
		
		int startpage = request.getParameter("page") == null ? 1:Integer.parseInt(request.getParameter("page").toString());
		int pagelim = request.getParameter("pagelim") == null ? 10:Integer.parseInt(request.getParameter("pagelim").toString());
		HashMap<String,String> searchreq = new HashMap<String,String>(); 
		searchreq.put("search_id", request.getParameter("search_id") == null ? "":request.getParameter("search_id"));
		searchreq.put("search_keyword", request.getParameter("search_keyword") == null ? "":request.getParameter("search_keyword"));
		searchreq.put("search_timestamp_start", request.getParameter("search_timestamp_start") == null ? "":request.getParameter("search_timestamp_start"));
		searchreq.put("search_timestamp_end", request.getParameter("search_timestamp_end") == null ? "":request.getParameter("search_timestamp_end"));
		searchreq.put("search_resvtime_start", request.getParameter("search_resvtime_start") == null ? "":request.getParameter("search_resvtime_start"));
		searchreq.put("search_resvtime_end", request.getParameter("search_resvtime_end") == null ? "":request.getParameter("search_resvtime_end"));
		searchreq.put("search_status", request.getParameter("search_status") == null ? "":request.getParameter("search_status"));
		
		response.setCharacterEncoding("gbk");
		Orderlist o = new Orderlist();
		o.setPageurl(url);
		o.setCurrentpage(startpage);
		o.setPagelim(pagelim);
		o.setSearchreq(searchreq);
		o.setSearchForm();
		
		o.setOrderContent();
		
		PrintWriter out = response.getWriter();
		out.print(o.outputHTMLPage());
		out.flush();
	}

}
