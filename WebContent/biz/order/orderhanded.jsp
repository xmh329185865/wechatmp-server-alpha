<%@ page language="java" isErrorPage="true" pageEncoding="utf-8" contentType="text/html;charset=utf-8" %>
<%@ page import="java.util.*"%>
<%@ page import="ana.database.service.BuildingSVC"%>
<%@ page import="ana.database.service.OrderSVC"%>
<%@ page import="ana.model.order.mgr.OrderMgr"%>
<%@ page import="ana.view.order.Orderhanded"%>
<%@ page import="ana.database.service.StaffSVC"%>
<%@ page import="ana.view.dbmapping.*"%>
<%@ page import="ana.util.converter.*"%>
<%@ page import="ana.util.string.*" %>
<%@ page import="ana.util.encrypt.*" %>
<%@ page import="ana.util.loader.*" %>
<%@ page import="ana.server.properties.ServerProperties" %>
<%@ page import="ana.model.qrcode.GenerateQRFile" %>
<%@ page import="ana.model.order.properties.OrderStatus" %>
<%@ page import="javax.servlet.http.Cookie" %>


<%
	Cookie cookies[]=request.getCookies();
	Long order_id = null; 
	for(int i=0; i<cookies.length; i++) {
	    // 获得具体的Cookie
	    Cookie cookie = cookies[i];
	    if(cookie.getName().equalsIgnoreCase("neworder_id")){
	    	order_id = new Long(StringEncrypt.decStringByDes(cookie.getValue(),"utf-8"));
		    cookie.setMaxAge(0);
		    response.addCookie(cookie);
		    break;
	    }
	    	
	 }
	if (order_id==null){
%>
		您没有提交故障单，请通过正确的途径访问……
<%
	} else {	
	
		HashMap<String,String> order = new OrderMgr().getOrderByIDnoNULLS(order_id);
		
		if (order==null) {
			%>系统错误，查无此单…… <% 
			return;
		}
		
		String qrcercode_path = "";//设置缺省地址指向一张缺省图片
		if (order.containsKey("order_cercode")) {
			new GenerateQRFile().stringToQR(order.get("order_cercode"), getServletContext().getRealPath("/")+ServerProperties.orderqrcercode_path, order.get("order_id")+".jpg");
			qrcercode_path = ServerProperties.orderqrcercode_path+"/"+order.get("order_id")+".jpg";
		}
		if (order.containsKey("order_wechatid")) order.remove("order_wechatid");
		if (order.containsKey("order_status")) 
			order.put("order_status", OrderStatus.statusWrapper(Integer.parseInt(order.get("order_status").toString())));
		
		List<HashMap> orderinfo = new OrderMapping().getMappedOrder(order);
		
		int listsize = orderinfo.size();
		for (int i = 0; i < listsize - 1; i++) {  
            for (int j = 1; j < listsize - i; j++) {  
                HashMap a = new HashMap();  
                if ((new Integer(orderinfo.get(j - 1).get("KeyPriority").toString())).compareTo((new Integer(orderinfo.get(j).get("KeyPriority").toString()))) > 0) { // 比较两个整数的大小  
  
                    a = orderinfo.get(j - 1);  
                    orderinfo.set((j - 1), orderinfo.get(j));  
                    orderinfo.set(j, a);  
                }  
            }  
        }
%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>故障单回执</title>
	<link href="/css/order/orderhanded.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="exlist">
<div class="exlist_title" align="center">您的报修已经成功提交！</div>
	<fieldset>
		<legend>报修信息回单</legend>
		<span class="red">&nbsp;&nbsp;&nbsp;&nbsp;*请牢记您的故障单号，便于日后查询核对使用！</span> 
<%		for(Iterator<HashMap> i = orderinfo.iterator(); i.hasNext();){
			HashMap temp = i.next();
			
			if(temp.get("KeyLabel").toString().equalsIgnoreCase("order_supadmin")){
				String val = temp.get("KeyValue").toString();
				List<String> admins = StringUtil.tokenizedStringToList(StringUtil.tokenInitializer(val, "@"), "@");%>
			<div class="row">
				<label style="font-weight:bold"><%=temp.get("KeyName").toString() %>:</label>
				<span class="red">(您可以即刻联系网管人员获取帮助！)</span>
			</div>
			<div>			
<%				if(admins.size()<=0){%>
				<div align="center">
					<label style="font-size:14">非常抱歉，暂未能为您找到负责该区域的网管人员，您可以联系网络中心：82668828，获取更多帮助</label>
				</div>
<%				} else {%>
					<%=new Orderhanded().getAdminsContactByStuID(admins) %>
<%				}
			} else if(temp.get("KeyLabel").toString().equalsIgnoreCase("order_cercode")) {%>
			<div class="row">
				<label style="font-weight:bold"><%=temp.get("KeyName").toString() %>:</label>
				<div align="center">
					<img src="<%=qrcercode_path%>"/>
				</div>
			</div>		
<%			}else {%>
			<div class="row">
				<label style="font-weight:bold"><%=temp.get("KeyName").toString() %>:</label>
				<label><%=temp.get("KeyValue").toString() %></label>
			</div>
<%			}
		}%>
	</fieldset>              
</div>
</body>
</html>
<%}%>
