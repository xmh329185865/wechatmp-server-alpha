package ana.server.init;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ana.model.auth.check.WCMPChecker;
import ana.server.context.ServContext;

/**
 * Servlet implementation class initservlet
 */
@WebServlet(name="Initializer" ,urlPatterns={"/Initializer"},loadOnStartup=99) 
public class initservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public initservlet() {
        super();

        new HibernateInit().init();
        
        System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date())+" "+"初始化参数类："+ServContext.class);
        ServContext.globalinit();
        System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date())+" "+"初始化验证类："+WCMPChecker.class);
        WCMPChecker.globalinit();
        
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
		// TODO Auto-generated method stub
	}

}
