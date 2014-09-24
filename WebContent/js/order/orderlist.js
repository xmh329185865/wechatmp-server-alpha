/**
 * 
 */

function showmsg(msg){
	confirm(msg);
}

function changepage(){
	var gotopage = document.getElementById("pagelist").value;
	var urlnow = window.location.href;
	var urlnext = urlnow.replace(/page=\\d/,"page="+gotopage);
	window.location.href = urlnext;
}

function submitordertable(){
	var i = 1;
	var selected = "";
	while(document.getElementById("order"+i)){
		if (document.getElementById("order"+i).checked){
			selected += document.getElementById("order"+i).value + "@";
		}
		i++;
	}
	if (selected == ""){
		alert("\u8bf7\u81f3\u5c11\u9009\u62e9\u4e00\u5355\uff01");
		return false;
	}
	var staffid = prompt("\u8bf7\u8f93\u5165\u60a8\u7684\u5de5\u4f5c\u7f16\u53f7\uff08\u5b66\u53f7\uff09\uff1a");
	if (!staffid){
		return false;
	}
	var idvalidation = /^[1-9]\d*$/;
	if (!idvalidation.test(staffid)) {
		alert("\u8bf7\u8f93\u5165\u6709\u6548\u7684\u7f16\u53f7\uff01");
		return false;
	}
	document.getElementById("adminid").value = staffid;
	document.getElementById("orderlist").value = selected;
	var nowurl = window.location.href;
	
	var formData = $('#orderform').serialize();
	$.ajax({ 

        type : "POST", 
        url  : "http://115.154.109.213/OrderProcessor",  
        cache : false, 
        data : formData, 
        success: (function(code){
        	if($.trim(code)==0){
        		confirm("\u63d0\u4ea4\u6210\u529f\uff01");
        		window.location.href = nowurl;
        	} else {
        		alert("\u63d0\u4ea4\u5931\u8d25\uff01");
        	}
        })

    }); 
}

function refreshpage(){
	//var path = window.location.href;
	//path = path.replace(/\?.*/,"");
	//window.location.href = path;
	document.getElementById("search_id").value="";
	document.getElementById("search_keyword").value="";
	document.getElementById("search_timestamp_start").value="";
	document.getElementById("search_timestamp_end").value="";
	document.getElementById("search_resvtime_start").value="";
	document.getElementById("search_resvtime_end").value="";
	document.getElementById("search_status_unget").checked = false;
	document.getElementById("search_status_procing").checked = false;
	document.getElementById("search_status_done").checked = false;
	document.getElementById("search_status_canceled").checked = false;
	document.getElementById("search_status_timeout").checked = false;
}

function searchsubmit(){
	var search_status = "";
	if (document.getElementById("search_status_unget").checked) search_status += document.getElementById("search_status_unget").value;
	if (document.getElementById("search_status_procing").checked) search_status += document.getElementById("search_status_procing").value;
	if (document.getElementById("search_status_done").checked) search_status += document.getElementById("search_status_done").value;
	if (document.getElementById("search_status_canceled").checked) search_status += document.getElementById("search_status_canceled").value;
	if (document.getElementById("search_status_timeout").checked) search_status += document.getElementById("search_status_timeout").value;
	document.getElementById("search_status").value = search_status;
	document.getElementById("searchform").submit();
}