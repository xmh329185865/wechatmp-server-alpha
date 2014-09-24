<%@ page language="java" isErrorPage="true" pageEncoding="utf-8" contentType="text/html;charset=utf-8" %>
<%@ page import="java.util.*"%>
<%@ page import="ana.view.order.Orderhandin"%>

<%	String order_wechatid = request.getParameter("order_wechatid")==null?"":request.getParameter("order_wechatid").toString(); 
	Orderhandin ohinview = new Orderhandin();%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>故障单提交</title>
	<script src="/js/common/jquery-1.8.2.min.js" type="text/javascript"></script>
	<script src="/js/common/jquery.form.js" type="text/javascript"></script>
    <script type="text/javascript" src="/js/common/jquery-ui.min.js"></script>
    <script src="/js/timepicker/jquery-ui-timepicker-addon.js" type="text/javascript"></script>
    
    <script src="/js/timepicker/jquery.ui.datepicker-zh-CN.js.js" type="text/javascript" charset="gb2312"></script>
    <script src="/js/timepicker/jquery-ui-timepicker-zh-CN.js" type="text/javascript"></script>
    
    <link href="/css/imported/jquery-ui-timepicker-addon.css" type="text/css" />
    <link href="/css/imported/demos.css" rel="stylesheet" type="text/css" />
    <link type="text/css" href="/css/imported/jquery-ui.css" rel="stylesheet" />
	<!--
	<script type="text/javascript">
        jQuery(function () {
            //设置时间
            jQuery('#order_resvtime').datetimepicker({
                timeFormat: "HH:mm:ss",
                dateFormat: "yy-mm-dd"
            });

        });
    </script>
      -->
	
	<script>
	$(function(){
	 $("div").click(function(){
	$(this).addClass("select");		
	    });
	})
	
	</script>
	
	<script type="text/javascript">
		/**验证姓名**/
		function checkName(){
			var contact_name = $("#client_name").val();
			if(contact_name==''){
				$('#clientnameTs').text("请填写姓名！");
				$('#clientnameTs').attr("class","red");
				return false;
			}else{
				$('#clientnameTs').text("");
				$('#clientnameTs').attr("class","");
			}
			return true;
		}
		
		/**验证ip地址**/
		function checkIP(){
	        var ip = $("#order_faultip").val();
			var checkip=/^([1-9]|[1-9]\d|1\d{2}|2[0-4]\d|25[0-5])(\.(\d|[1-9]\d|1\d{2}|2[0-4]\d|25[0-5])){3}$/;
			if(ip!='' && !checkip.test(ip)) {
				$('#ipTs').text("请填写有效的IP地址!");
			 	$('#ipTs').attr("class","red");
				return false;
			}else if (ip == ''){
				$('#ipTs').text("请填写IP地址!");
			 	$('#ipTs').attr("class","red");
			 	return false;
			} else {
				$('#ipTs').text("");
				$('#ipTs').attr("class","");
			}
			return true;
		}
		
		/**验证手机号码**/
		function checkCellPhone(){
	        var tele = $("#client_cell").val();
			var chekcell=/^[1][358][0-9]{9}$/;
			if(tele!='' && !chekcell.test(tele)) {
				$('#cellphoneTs').text("请填写有效的手机号!");
			 	$('#cellphoneTs').attr("class","red");
				return false;
			}else if (tele == ''){
				$('#cellphoneTs').text("请填写手机号码!");
			 	$('#cellphoneTs').attr("class","red");
			 	return false;
			} else {
				$('#cellphoneTs').text("");
				$('#cellphoneTs').attr("class","");
			}
			return true;
		}
		
		/**验证预约时间**/
		function checkResv(){
			var order_resvtime = $("#order_resvtime").val();
			if(order_resvtime==''){
				$('#resvtimeTs').text("请选择维修时间！");
				$('#resvtimeTs').attr("class","red");
				return false;
			}
			var order_resvtime = order_resvtime.replace(/-/g, "/");
			var resvdate=new Date(Date.parse(order_resvtime));
			if(resvdate < new Date()){
				$('#resvtimeTs').text("预约维修时间应在当前时间之后！");
				$('#resvtimeTs').attr("class","red");
				return false;
			}
			$('#resvtimeTs').text("");
			$('#resvtimeTs').attr("class","");

			return true;
		}
		
		/**验证描述内容**/
		function checkDesc(){
			var desc = $("#order_description").val();
			if(desc==''){
				$('#descTs').text("请填写故障描述！");
				$('#descTs').attr("class","red");
				return false;
			}else{
				$('#descTs').text("");
				$('#descTs').attr("class","");
			}
			return true;
		}
		
		/**验证描述内容**/
		function checkAddr(){
			var room = $("#client_room").val();
			if(room==''){
				$('#addrTs').text("请填写房号！");
				$('#addrTs').attr("class","red");
				return false;
			}else{
				$('#addrTs').text("");
				$('#addrTs').attr("class","");
			}
			return true;
		}
		
		/**验证输入项**/
		function addOrderCheck(){

			if(!checkName()) {
				return false;
			} else if(!checkIP()) {
				return false;
			} else if(!checkCellPhone()) {
				return false;
			} else if(!checkAddr()) {
				return false;
	        } else if(!checkResv()) {
				return false;
	        } else if(!checkDesc()){
	        	return false;
	        } else {
	        	var formData = $('#neworder').serialize(); 

	            //.serialize() 方法创建以标准 URL 编码表示的文本字符串

	            $.ajax({ 

	                type : "POST", 
	                url  : "ajaxOrderHandin.jsp",  
	                cache : false, 
	                data : formData, 
	                success: (function(order_id){
        	        	if($.trim(order_id)>0){
        	        		alert("提交成功！");
        	        		window.location.href="orderhanded.jsp";
        	        	} else {
        	        		alert("提交失败！");
        	        	}
        	        })

	            }); 
	        	
	        }
		}
	</script>
	
	
	<style>
	<!--
	body{
		background-image:url(wood-bg2.jpg);
	}
	
	.exlist{
		background-color:#f6f6f6 ;
		margin:30px auto;
		padding:5px;
		width:450px;
		min-height:200px;
		height:auto;
		font-family:Microsoft YaHei;
		-webkit-box-shadow:4px 4px 5px #333;/*webkit*/ 
		-moz-box-shadow:4px 4px 5px #333;/*firefox*/     
		box-shadow:4px 4px 5px #333;/*operaæie9*/ 
	}
	
	#title{
		width:200px;
		margin:20px auto;
	}
	
	#title legend{
		font-size:26px;
	}
	
	div.exlist_title{
		background-color:#f6f6f6;
		width:680px;
		height:20px;
	}
	
	div.exlist_title img{
		float:right;
		margin:-15px 10px;
	}
	
	/*-----------form-----------*/
	
	fieldset{
		width:90%;
		border:1px dashed #666;
		margin:40px auto;
	}
	 
	legend{
		background-color:#f6f6f6;
		height:120px;
		height:30px;
		color:#630;
		font-weight:bolder;
		font-size:20px;
		line-height:30px;
		margin:-20px 10px 10px;
		padding:0 10px;
		
	}
	div.row{
		margin:5px;
		padding:10px;
	}
	 
	div.row label{
		height:20px;
		font-size:16px;
		line-height:20px;
		margin:0 10px;
		vertical-align:top;
		font-weight:bold;
	}
	 
	input.txt{
		background-color:#f6f6f6;
		color:#333;
		width:150px;
		height:20px;
		margin:0 10px;
		font-size:12px;
		line-height:20px;
		border:none;
		border-bottom:1px solid #565656;
	}
	
	textarea{
		background-color:#f6f6f6;
		color:#333;
		font-size:12px;
		line-height:20px;
		border:none;
		border-bottom:1px solid #565656;
		border-top:1px solid #565656;
	
	}
	 
	input.txt:focus{
		color:#333;
		background-color: #fafafa;
		border-bottom:1px solid #F00;
	}
	 
	select{
		background-color:#f6f6f6;
		color:#333;
		width:100px;
		font-size:12px;
		border:none;
		border-bottom:1px solid #565656;
	}
	
	option{
		text-align:center;
	}
	
	input.btn{
		width:50px;
		height:20px;
		color:#000008B;
		background-color: transparent;
		border:0;
		padding:0;
	}
	
	.red{ color:#FF0000; }
	
	-->
	</style>
</head>
<body>
<div class="exlist">
       <form id="neworder" name="neworder" method="post" action="ajaxOrderHandin.jsp">
           <fieldset>
           <legend>报修信息</legend>
                   <div class="row">
                   <label>1.&nbsp;报修人姓名:</label>
                   <input id="client_name" name="client_name" style="width:100px" class="txt" type="text" value="" onblur="checkName();"/>
                   <span id="clientnameTs"></span>
                   </div>
                   <div class="row">
                   <label>2.&nbsp;故障IP:</label><input id="order_faultip" name="order_faultip" class="txt" type="text" onblur="checkIP();" onkeyup="value=this.value.replace(/[^0-9\.\(\)]/gi,'')"/>
                   <span id="ipTs"></span>
                   </div>
                   <div class="row">
                   <label>3.&nbsp;联系电话:</label><input id="client_cell" name="client_cell" class="txt" type="text" onblur="checkCellPhone();"/>
                   <span id="cellphoneTs"></span>
                   </div>
                   <div class="row">
                   <label>4.&nbsp;宿舍楼号:</label>
					<%=ohinview.getBuildingList() %>
                   </div>
                   <div class="row">
                   <label>5.&nbsp;宿舍房号:</label><input id='client_room' name='client_room' class="txt" style="width:80px"  type="text" onblur="checkAddr();" />
                   <span id="addrTs"></span>
                   </div>
                   <div class="row">
                   <label>6.&nbsp;预约维修时间:</label><input id="order_resvtime" name="order_resvtime" class="txt" style="width:200px" type="datetime-local" />
                   <span id="resvtimeTs"></span>
                   </div>
                   <div class="row">
	                   <label>7.&nbsp;故障描述:</label>
	                   <textarea id="order_description" name="order_description" cols="25" rows="5" onblur="checkDesc();"></textarea>
                   </div>
                   <div align="center">
                   	   <span id="descTs"></span>
                   </div>
                   <input type="hidden" id="order_wechatid" name="order_wechatid" value="<%=order_wechatid %>"/>
           </fieldset>
           <div align="center">
				<input type="button" name="sub" value="提交" onclick="addOrderCheck();"/> 
		   </div>
        </form>
</div>
</body>
</html>
