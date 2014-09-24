<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="ana.database.service.BuildingSVC"%>
<%@ page import="java.util.*"%>
<%@ page import="ana.util.string.*" %>
<%@ page import="ana.util.encrypt.*" %>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="ana.model.order.mgr.OrderMgr" %>
<%@ page import="javax.servlet.http.Cookie" %>
<% 
	this.log(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date().getTime())+" 收到了新的故障单！");
	HashMap raworder = new HashMap();
	for(Enumeration<String> e = request.getParameterNames(); e.hasMoreElements();){
		String name = e.nextElement();
		System.out.println(name+"="+request.getParameter(name));
		raworder.put(name, request.getParameter(name));
	}
	System.out.println();
	OrderMgr om = new OrderMgr();
	long order_id = om.saveNewOrder(raworder);
	Cookie neworder_id = new Cookie("neworder_id",StringEncrypt.encStringByDes(order_id+"", "utf-8"));
	neworder_id.setMaxAge(-1);
	response.addCookie(neworder_id);
	out.println(order_id);
%>