package ana.view.order.outlink;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Iterator;
import java.util.Set;

import ana.model.order.mgr.OrderMgr;
import ana.model.order.properties.OrderStatus;
import ana.server.properties.ServerProperties;
import ana.util.string.StringUtil;
import ana.view.htmlwriter.exceptions.ColumnNotMatchedException;
import ana.view.htmlwriter.exceptions.InputValueNotMatchedException;
import ana.view.htmlwriter.exceptions.InvalidRowException;
import ana.view.htmlwriter.exceptions.NoSuchTagException;
import ana.view.htmlwriter.exceptions.TagnumOutOfBoundsException;
import ana.view.htmlwriter.htmltags.HTMLTag;
import ana.view.htmlwriter.htmltags.extended.HTMLDiv;
import ana.view.htmlwriter.htmltags.extended.HTMLTable;
import ana.view.htmlwriter.page.HTMLPage;

public class Orderlist extends HTMLPage{ 
	
	private HTMLTable ordertable = new HTMLTable(new String[]{"<b>ID</b>","<b>报修人</b>","<b>位置</b>","<b>IP地址</b>","<b>联系电话</b>",
			"<b>报修时间</b>","<b>预约时间</b>","<b>故障描述</b>","<b>应接网管</b>","<b>接单网管</b>","<b>进度</b>","<b>选中</b>"});
	private HTMLTag form = new HTMLTag("form");
	private HTMLTable searchtable = new HTMLTable(2);
	private HTMLTag form_search = new HTMLTag("form");
	private List<HTMLDiv> divStructure = new ArrayList<HTMLDiv>();
	private HTMLDiv areadiv = new HTMLDiv();
	private HTMLDiv searchdiv = new HTMLDiv();
	private HTMLDiv tablediv = new HTMLDiv();
	private HTMLDiv pagediv = new HTMLDiv();
	private HTMLDiv submitdiv = new HTMLDiv();
	
	private HTMLTag prepage = new HTMLTag("a","&nbsp;上一页&nbsp;");
	private HTMLTag nextpage = new HTMLTag("a","&nbsp;下一页&nbsp;");
	private HTMLTag firstpage = new HTMLTag("a","&nbsp;首页&nbsp;");
	private HTMLTag lastpage = new HTMLTag("a","&nbsp;末页&nbsp;");
	private List<HTMLTag> pagelist = new ArrayList<HTMLTag>();
	private HTMLTag pagecounter = new HTMLTag("span");
	
	private int currentpage;
	private int allpage;
	private int pagelim;
	
	private String pageurl;
	private HashMap<String,String> searchreq = new HashMap<String,String>();
	
	public Orderlist(){
		super();
		
		try{
			
			HTMLTag head = this.getHead();
			
			this.setTitle("故障单列表");
			this.setCharset("gbk");
			HashMap<String,String> meta1 = new HashMap<String,String>();
			meta1.put("http-equiv", "Pragma");
			meta1.put("content", "no-cache");
			head.addSubTag(new HTMLTag("meta",meta1));
			
			HashMap<String,String> meta2 = new HashMap<String,String>();
			meta2.put("http-equiv", "Content-Type");
			meta2.put("content", "text/html");
			head.addSubTag(new HTMLTag("meta",meta2));
			
			this.addImportedCss("http://"+ServerProperties.webrootpath+"/css/order/orderlist.css");
			this.addImportedScript("http://"+ServerProperties.webrootpath+"/js/order/orderlist.js", "text/javascript","gbk");
			this.addImportedScript("http://"+ServerProperties.webrootpath+"/js/common/jquery-1.8.2.min.js", "text/javascript");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.areadiv.appendInnerHTML("故障管理 &gt; 故障列表 &gt;"
					+ "<a href=\"#\">ALL</a> | "
					+ "<a href=\"#\">西校区教学区</a> | "
					+ "<a href=\"#\">东校区学生宿舍</a> | "
					+ "<a href=\"#\">西校区学生宿舍</a> | "
					+ "<a href=\"#\">家属区PPPOE</a> | "
					+ "<a href=\"#\">其他</a> <br><br>");
		this.areadiv.addOrSetAttr("align", "center");
		this.areadiv.setTagClass("areadiv");
		this.divStructure.add(this.areadiv);
		
		HTMLTag th1 = new HTMLTag("th","<b>高级搜索</b>");
		th1.addOrSetAttr("colspan", 2+"");
		HTMLTag tr11 = new HTMLTag("tr");
		tr11.addSubTag(th1);
		try {
			this.searchtable.getSubTag("tbody").addSubTag(tr11);
		} catch (NoSuchTagException e) {
			e.printStackTrace();
		}
		try {
			this.searchtable.addRow(new String[]{"<b>条件</b>","<b>查找值</b>"}, null);
			
			List<HTMLTag> row1 = new ArrayList<HTMLTag>();
			HTMLTag row1_td1 = new HTMLTag("td");
			row1_td1.addSubTag(new HTMLTag("span","故障单编号"));
			row1.add(row1_td1);
			HTMLTag row1_td2 = new HTMLTag("td");
			HashMap<String,String> id_input= new HashMap<String,String>();
			id_input.put("id", "search_id");
			id_input.put("name", "search_id");
			id_input.put("type", "number");
			id_input.put("min", "1");
			row1_td2.addSubTag(new HTMLTag("input",id_input));
			row1.add(row1_td2);
			this.searchtable.addRow(row1, null);
			
			List<HTMLTag> row2 = new ArrayList<HTMLTag>();
			HTMLTag row2_td1 = new HTMLTag("td");
			row2_td1.addSubTag(new HTMLTag("span","关键字"));
			row2.add(row2_td1);
			HTMLTag row2_td2 = new HTMLTag("td");
			HashMap<String,String> keyword_input= new HashMap<String,String>();
			keyword_input.put("id", "search_keyword");
			keyword_input.put("name", "search_keyword");
			keyword_input.put("type", "text");
			row2_td2.addSubTag(new HTMLTag("input",keyword_input));
			row2.add(row2_td2);
			this.searchtable.addRow(row2, null);
			
			List<HTMLTag> row3 = new ArrayList<HTMLTag>();
			HTMLTag row3_td1 = new HTMLTag("td");
			row3_td1.addSubTag(new HTMLTag("span","按下单时间搜索"));
			row3.add(row3_td1);
			HTMLTag row3_td2 = new HTMLTag("td");
			HashMap<String,String> start_timestamp_input= new HashMap<String,String>();
			start_timestamp_input.put("id", "search_timestamp_start");
			start_timestamp_input.put("name", "search_timestamp_start");
			start_timestamp_input.put("type", "datetime-local");
			row3_td2.addSubTag(new HTMLTag("input",start_timestamp_input));
			row3_td2.addSubTag(new HTMLTag("span","至"));
			HashMap<String,String> end_timestamp_input= new HashMap<String,String>();
			end_timestamp_input.put("id", "search_timestamp_end");
			end_timestamp_input.put("name", "search_timestamp_end");
			end_timestamp_input.put("type", "datetime-local");
			row3_td2.addSubTag(new HTMLTag("input",end_timestamp_input));
			row3.add(row3_td2);
			this.searchtable.addRow(row3, null);
			
			List<HTMLTag> row4 = new ArrayList<HTMLTag>();
			HTMLTag row4_td1 = new HTMLTag("td");
			row4_td1.addSubTag(new HTMLTag("span","按预约时间搜索"));
			row4.add(row4_td1);
			HTMLTag row4_td2 = new HTMLTag("td");
			HashMap<String,String> start_resvtime_input= new HashMap<String,String>();
			start_resvtime_input.put("id", "search_resvtime_start");
			start_resvtime_input.put("name", "search_resvtime_start");
			start_resvtime_input.put("type", "datetime-local");
			row4_td2.addSubTag(new HTMLTag("input",start_resvtime_input));
			row4_td2.addSubTag(new HTMLTag("span","至"));
			HashMap<String,String> end_resvtime_input= new HashMap<String,String>();
			end_resvtime_input.put("id", "search_resvtime_end");
			end_resvtime_input.put("name", "search_resvtime_end");
			end_resvtime_input.put("type", "datetime-local");
			row4_td2.addSubTag(new HTMLTag("input",end_resvtime_input));
			row4.add(row4_td2);
			this.searchtable.addRow(row4, null);
			
			List<HTMLTag> row5 = new ArrayList<HTMLTag>();
			HTMLTag row5_td1 = new HTMLTag("td");
			row5_td1.addSubTag(new HTMLTag("span","过滤指定的进度"));
			row5.add(row5_td1);
			HTMLTag row5_td2 = new HTMLTag("td");
			HashMap<String,String> status_input_unget = new HashMap<String,String>();
			status_input_unget.put("id", "search_status_unget");
			status_input_unget.put("name", "search_status_unget");
			status_input_unget.put("type", "checkbox");
			status_input_unget.put("value", OrderStatus.PROCESSING_UNGETTED+"@");
			row5_td2.addSubTag(new HTMLTag("input",status_input_unget));
			row5_td2.addSubTag(new HTMLTag("span","未接单&nbsp;&nbsp;"));
			HashMap<String,String> status_input_procing = new HashMap<String,String>();
			status_input_procing.put("id", "search_status_procing");
			status_input_procing.put("name", "search_status_procing");
			status_input_procing.put("type", "checkbox");
			status_input_procing.put("value", OrderStatus.PROCESSING_GETTED+"@");
			row5_td2.addSubTag(new HTMLTag("input",status_input_procing));
			row5_td2.addSubTag(new HTMLTag("span","处理中&nbsp;&nbsp;"));
			HashMap<String,String> status_input_done = new HashMap<String,String>();
			status_input_done.put("id", "search_status_done");
			status_input_done.put("name", "search_status_done");
			status_input_done.put("type", "checkbox");
			status_input_done.put("value", OrderStatus.FIXED_RATED+"@"+OrderStatus.FIXED_UNRATED+"@"+OrderStatus.FIXED_RATETIMEOUT+"@");
			row5_td2.addSubTag(new HTMLTag("input",status_input_done));
			row5_td2.addSubTag(new HTMLTag("span","已修复&nbsp;&nbsp;"));
			HashMap<String,String> status_input_canceled = new HashMap<String,String>();
			status_input_canceled.put("id", "search_status_canceled");
			status_input_canceled.put("name", "search_status_canceled");
			status_input_canceled.put("type", "checkbox");
			status_input_canceled.put("value", OrderStatus.FAILED_CANCELED+"@");
			row5_td2.addSubTag(new HTMLTag("input",status_input_canceled));
			row5_td2.addSubTag(new HTMLTag("span","已取消&nbsp;&nbsp;"));
			HashMap<String,String> status_input_timeout = new HashMap<String,String>();
			status_input_timeout.put("id", "search_status_timeout");
			status_input_timeout.put("name", "search_status_timeout");
			status_input_timeout.put("type", "checkbox");
			status_input_timeout.put("value", OrderStatus.FAILED_GETTIMEOUT+"@"+OrderStatus.FAILED_FIXTIMEOUT+"@");
			row5_td2.addSubTag(new HTMLTag("input",status_input_timeout));
			row5_td2.addSubTag(new HTMLTag("span","已超时&nbsp;&nbsp;"));
			HashMap<String,String> status_input = new HashMap<String,String>();
			status_input.put("id", "search_status");
			status_input.put("name", "search_status");
			status_input.put("type", "hidden");
			status_input.put("value", "");
			row5_td2.addSubTag(new HTMLTag("input",status_input));
			row5.add(row5_td2);
			this.searchtable.addRow(row5, null);
			
			List<HTMLTag> row_sesub = new ArrayList<HTMLTag>();
			HTMLTag td_sesub = new HTMLTag("td");
			td_sesub.addOrSetAttr("colspan", 2+"");
			td_sesub.setTagClass("searchbutton");
			HTMLTag input_se = new HTMLTag("input");
			input_se.addOrSetAttr("value", "搜索");
			input_se.addOrSetAttr("type", "button");
			input_se.addOrSetAttr("onclick", "searchsubmit()");
			HTMLTag input_re = new HTMLTag("input");
			input_re.addOrSetAttr("value", "清空条件");
			input_re.addOrSetAttr("type", "button");
			input_re.addOrSetAttr("onclick", "refreshpage()");
			td_sesub.addSubTag(input_se);
			td_sesub.addSubTag(input_re);
			row_sesub.add(td_sesub);
			this.searchtable.addRow(row_sesub, null);
			
		} catch (ColumnNotMatchedException | InputValueNotMatchedException e) {
			e.printStackTrace();
		}
		
		
		this.searchtable.setTagClass("searchtable");
		this.form_search.addSubTag(this.searchtable);
		this.form_search.addOrSetAttr("method", "post");
		this.form_search.addOrSetAttr("action", "#");
		this.form_search.addOrSetAttr("id", "searchform");
		this.searchdiv.addSubTag(this.form_search);
		this.searchdiv.setTagClass("searchdiv");
		this.divStructure.add(this.searchdiv);
		
		this.ordertable.setID("ordertable");
		this.ordertable.addOrSetAttr("name", "ordertable");
		this.ordertable.setTagClass("ordertable");
		this.tablediv.addSubTag(this.ordertable);
		this.tablediv.addOrSetAttr("align", "center");
		this.tablediv.setTagClass("tablediv");
		this.divStructure.add(this.tablediv);
		
		this.currentpage = 1;
		this.pagediv.setTagClass("pagediv");
		this.pagediv.addOrSetAttr("align", "left");
		this.divStructure.add(this.pagediv);
		
		HTMLTag input1 = new HTMLTag("input");
		input1.addOrSetAttr("id", "trade_code");
		input1.addOrSetAttr("name", "trade_code");
		input1.addOrSetAttr("value", "0001");
		input1.addOrSetAttr("type", "hidden");
		HTMLTag input3 = new HTMLTag("input");
		input3.addOrSetAttr("id", "orderlist");
		input3.addOrSetAttr("name", "orderlist");
		input3.addOrSetAttr("value", "");
		input3.addOrSetAttr("type", "hidden");
		HTMLTag input4 = new HTMLTag("input");
		input4.addOrSetAttr("id", "adminid");
		input4.addOrSetAttr("name", "adminid");
		input4.addOrSetAttr("value", "");
		input4.addOrSetAttr("type", "hidden");
		HTMLTag input2 = new HTMLTag("input");
		input2.addOrSetAttr("value", "批量接单");
		input2.addOrSetAttr("type", "button");
		input2.addOrSetAttr("onclick", "submitordertable()");
		this.form.addOrSetAttr("method", "post");
		this.form.addOrSetAttr("action", ServerProperties.webrootpath+ServerProperties.order_processor);
		this.form.addOrSetAttr("id", "orderform");
		this.form.addSubTag(input1);
		this.form.addSubTag(input3);
		this.form.addSubTag(input4);
		this.form.addSubTag(input2);
		this.submitdiv.addOrSetAttr("align", "center");
		this.submitdiv.setTagClass("submitdiv");
		this.submitdiv.addSubTag(this.form);
		this.divStructure.add(this.submitdiv);
		
		this.getBodyContent().addAll(this.divStructure);
	}

	public HTMLTable getOrdertable() {
		return ordertable;
	}

	public void setOrdertable(HTMLTable ordertable) {
		this.ordertable = ordertable;
	}
	
	public void setOrderRows(List<HashMap<String,String>> orders){
		OrderMgr om = new OrderMgr();
		for (int i = orders.size()<=this.pagelim?orders.size()-1:this.pagelim-1; i >= 0; i--){
			HashMap<String,String> thisorder = orders.get(i);
			HTMLTag td_id = new HTMLTag("td",thisorder.get("order_id"));
			HTMLTag td_cname = new HTMLTag("td",thisorder.get("client_name"));
			HTMLTag td_caddr = new HTMLTag("td",thisorder.get("client_building")+"-"+thisorder.get("client_room"));
			HTMLTag td_cip = new HTMLTag("td",thisorder.get("order_faultip"));
			HTMLTag td_cell = new HTMLTag("td",thisorder.get("client_cell"));
			String otime="",rtime="";
			if(thisorder.get("order_timestamp") != null){
				otime = thisorder.get("order_timestamp").length() <= 16 ? thisorder.get("order_timestamp"):thisorder.get("order_timestamp").substring(0,16);
			}
			if(thisorder.get("order_resvtime") != null){
				rtime = thisorder.get("order_resvtime").length() <= 16 ? thisorder.get("order_resvtime"):thisorder.get("order_resvtime").substring(0,16);
			}
			HTMLTag td_otime = new HTMLTag("td",otime);
			HTMLTag td_rtime = new HTMLTag("td",rtime);
			
			HTMLTag descmsg = new HTMLTag("a","点击查看");
			descmsg.addOrSetAttr("href", "javascript:void(0)");
			descmsg.addOrSetAttr("onclick", "showmsg(\'单号:"+thisorder.get("order_id")+"\\r\\n故障描述：\\r\\n"+thisorder.get("order_description")+"\')");
			HTMLTag td_desc = new HTMLTag("td");
			td_desc.addSubTag(descmsg);
			
			List<String> supadmin = StringUtil.tokenizedStringToList(thisorder.get("order_supadmin"),"@");
			StringBuffer supadminname = new StringBuffer();
			for (Iterator<String> ii = supadmin.iterator(); ii.hasNext();){
				String thisname = om.getAdminNameByStuID(ii.next());
				if (thisname != null) supadminname.append(thisname+"、");
			}
			String supad = "";
			if(supadminname.length()>0) supad = supadminname.toString().substring(0, supadminname.length()-1);
			HTMLTag td_supad = new HTMLTag("td");
			HTMLTag supadmsg = new HTMLTag("a","点击查看");
			supadmsg.addOrSetAttr("href", "javascript:void(0)");
			supadmsg.addOrSetAttr("onclick", "showmsg(\'单号:"+thisorder.get("order_id")+"\\r\\n应接网管：\\r\\n"+supad+"\')");
			td_supad.addSubTag(supadmsg);
			
			HTMLTag td_nowad = new HTMLTag("td",om.getAdminNameByStuID(thisorder.get("order_nowadmin")));
			HTMLTag td_stat = new HTMLTag("td",OrderStatus.statusWrapper(Integer.parseInt(thisorder.get("order_status"))));
			HashMap<String,String> attr = new HashMap<String,String>();
			attr.put("type", "checkbox");
			attr.put("id", "order"+(i+1));
			attr.put("value", thisorder.get("order_id"));
			if (Integer.parseInt(thisorder.get("order_status")) != OrderStatus.PROCESSING_UNGETTED) attr.put("disabled", "disabled");
			HTMLTag chkbox = new HTMLTag("input", attr);
			HTMLTag td_sel = new HTMLTag("td");
			td_sel.addSubTag(chkbox);
			HTMLTag tr = new HTMLTag("tr");
			tr.addSubTag(td_id);
			tr.addSubTag(td_cname);
			tr.addSubTag(td_caddr);
			tr.addSubTag(td_cip);
			tr.addSubTag(td_cell);
			tr.addSubTag(td_otime);
			tr.addSubTag(td_rtime);
			tr.addSubTag(td_desc);
			tr.addSubTag(td_supad);
			tr.addSubTag(td_nowad);
			tr.addSubTag(td_stat);
			tr.addSubTag(td_sel);
			tr.setTagClass("line");
			try {
				this.ordertable.addRow(tr,1);
			} catch (InvalidRowException e) {
				e.printStackTrace();
			} catch (ColumnNotMatchedException e) {
				e.printStackTrace();
			} catch (TagnumOutOfBoundsException e) {
				e.printStackTrace();
			}
		}
	}
	
	private String urlProcess(int pagepointer){
		StringBuffer backurl = new StringBuffer(this.pageurl+"?&page="+pagepointer + "&pagelim="+this.pagelim);
		Set<String> sreqname = this.searchreq.keySet();
		for (Iterator<String> i = sreqname.iterator(); i.hasNext();){
			String reqname = i.next();
			backurl.append("&"+reqname+"="+this.searchreq.get(reqname));
		}
		return backurl.toString();
	}
	
	public void setPaginition(){
		this.allpage = this.getAllPage();
		this.firstpage.addOrSetAttr("href", this.urlProcess(1));
		this.lastpage.addOrSetAttr("href", this.urlProcess(this.allpage));
		if (this.currentpage > 1){
			this.prepage.addOrSetAttr("href", this.urlProcess(this.currentpage-1));
		}
		if (this.currentpage < this.allpage){
			this.nextpage.addOrSetAttr("href", this.urlProcess(this.currentpage+1));
		}
		this.pagecounter.setInnerHTML("第"+this.currentpage+"页/共"+this.allpage+"页");
		
		int pagemin,pagemax;
		if (this.currentpage - 3 <= 0){
			pagemin = 1;
			if (this.currentpage + 3 >= this.allpage) {
				pagemax = this.allpage;
			} else {
				pagemax = 7 > this.allpage ? this.allpage : 7;
			}
		} else {
			if (this.currentpage + 3 > this.allpage) {
				pagemin = this.allpage - 6 < 1 ? 1 : this.allpage - 6;
				pagemax = this.allpage;
			} else {
				pagemin = this.currentpage - 3;
				pagemax = this.currentpage + 3;
			}
		}
		
		for (int i = pagemin; i <= pagemax; i++){
			if (this.currentpage == i) {
				this.pagelist.add(new HTMLTag("span","&nbsp;"+i+"&nbsp;"));
				continue;
			}
			HTMLTag pagenum = new HTMLTag("a");
			pagenum.addOrSetAttr("href", this.urlProcess(i));
			pagenum.setInnerHTML("&nbsp;"+i+"&nbsp;");
			this.pagelist.add(pagenum);
		}
		
		this.pagediv.addSubTag(this.firstpage);
		this.pagediv.addSubTag(this.prepage);
		this.pagediv.getSubTags().addAll(this.pagelist);
		this.pagediv.addSubTag(this.nextpage);
		this.pagediv.addSubTag(this.lastpage);
		this.pagediv.addSubTag(this.pagecounter);
	}

	public void setSearchForm(){
		this.form_search.addOrSetAttr("action", this.pageurl);
		try {
			this.searchtable.getRow(2).getSubTags().get(1).getSubTag("input").addOrSetAttr("value", this.searchreq.get("search_id"));
			this.searchtable.getRow(3).getSubTags().get(1).getSubTag("input").addOrSetAttr("value", this.searchreq.get("search_keyword"));
			this.searchtable.getRow(4).getSubTags().get(1).getSubTagByID("search_timestamp_start").addOrSetAttr("value", this.searchreq.get("search_timestamp_start").replace(" ", "T"));
			this.searchtable.getRow(4).getSubTags().get(1).getSubTagByID("search_timestamp_end").addOrSetAttr("value", this.searchreq.get("search_timestamp_end").replace(" ", "T"));
			this.searchtable.getRow(5).getSubTags().get(1).getSubTagByID("search_resvtime_start").addOrSetAttr("value", this.searchreq.get("search_resvtime_start").replace(" ", "T"));
			this.searchtable.getRow(5).getSubTags().get(1).getSubTagByID("search_resvtime_end").addOrSetAttr("value", this.searchreq.get("search_resvtime_end").replace(" ", "T"));
			String statusfileter = this.searchreq.get("search_status");
			if (statusfileter.contains(OrderStatus.PROCESSING_UNGETTED+"@")){
				this.searchtable.getRow(6).getSubTags().get(1).getSubTagByID("search_status_unget").addOrSetAttr("checked", "checked");
			}
			if (statusfileter.contains(OrderStatus.PROCESSING_GETTED+"@")){
				this.searchtable.getRow(6).getSubTags().get(1).getSubTagByID("search_status_procing").addOrSetAttr("checked", "checked");
			}
			if (statusfileter.contains(OrderStatus.FIXED_RATED+"@"+OrderStatus.FIXED_UNRATED+"@"+OrderStatus.FIXED_RATETIMEOUT+"@")){
				this.searchtable.getRow(6).getSubTags().get(1).getSubTagByID("search_status_done").addOrSetAttr("checked", "checked");
			}
			if (statusfileter.contains(OrderStatus.FAILED_CANCELED+"@")){
				this.searchtable.getRow(6).getSubTags().get(1).getSubTagByID("search_status_canceled").addOrSetAttr("checked", "checked");
			}
			if (statusfileter.contains(OrderStatus.FAILED_GETTIMEOUT+"@"+OrderStatus.FAILED_FIXTIMEOUT+"@")){
				this.searchtable.getRow(6).getSubTags().get(1).getSubTagByID("search_status_timeout").addOrSetAttr("checked", "checked");
			}
		} catch (NoSuchTagException e) {
			e.printStackTrace();
		} catch (TagnumOutOfBoundsException e) {
			e.printStackTrace();
		}
	}
	
	public void setOrderContent(){
		this.setPaginition();
		List<HashMap<String,String>> orders = new OrderMgr().getOrderlist(this.currentpage, this.pagelim, this.searchreq);
		this.setOrderRows(orders);
		
	}

	public int getCurrentpage() {
		return this.currentpage;
	}

	public void setCurrentpage(int currentpage) {
		this.currentpage = currentpage;
	}
	
	public int getAllPage(){
		int count = new OrderMgr().getOrderCount(this.searchreq);
		if (count%this.pagelim!=0) return (count/this.pagelim)+1;
		else return count/this.pagelim;
	}

	public int getPagelim() {
		return pagelim;
	}

	public void setPagelim(int pagelim) {
		this.pagelim = pagelim;
	}

	public String getPageurl() {
		return pageurl;
	}

	public void setPageurl(String pageurl) {
		this.pageurl = pageurl;
	}

	public HashMap<String,String> getSearchreq() {
		return searchreq;
	}

	public void setSearchreq(HashMap<String,String> searchreq) {
		this.searchreq = searchreq;
	}

	public HTMLTable getSearchtable() {
		return searchtable;
	}

	public void setSearchtable(HTMLTable searchtable) {
		this.searchtable = searchtable;
	}

	public HTMLTag getForm_search() {
		return form_search;
	}

	public void setForm_search(HTMLTag form_search) {
		this.form_search = form_search;
	}

}
