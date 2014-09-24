package ana.model.order.mgr;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import ana.database.service.OrderSVC;
import ana.model.auth.properties.ProcessCode;
import ana.model.order.properties.OrderStatus;
import ana.util.converter.BlobProcess;
import ana.view.wcmp.msgpool.MsgPool;

@SuppressWarnings("rawtypes")
public class WCOrderMgr {
	
	private String backString;
	
	public void getCercode(long orderid, String fromid){
		OrderSVC o = new OrderSVC();
		HashMap cercode = o.getCercodeByID(orderid);
		if (!cercode.get("order_wechatid").toString().equals(fromid)) {
			this.setBackString(MsgPool.ERR_INVALID_ID);
			return;
		}
		if (cercode.get("order_cercode") == null) {
			this.setBackString(MsgPool.ERR_NO_ORDER_FOUND);
			return;
		}
		switch((int)cercode.get("order_status")){
		case OrderStatus.FAILED_FIXTIMEOUT:
			this.backString = MsgPool.ERR_ORDER_TIMEOUT_UNFIXED;
			return;
		case OrderStatus.FAILED_GETTIMEOUT:
			this.backString = MsgPool.ERR_ORDER_TIMEOUT_UNFIXED;
			return;
		case OrderStatus.FIXED_RATETIMEOUT:
			this.backString = MsgPool.ERR_ORDER_TIMEOUT_FIXED;
			return;
		case OrderStatus.FIXED_RATED:
			this.backString = MsgPool.ERR_ORDER_ALREADY_DONE;
			return;
		case OrderStatus.FAILED_CANCELED:
			this.backString = MsgPool.ERR_ORDER_ALREADY_CANCELED;
			return;
		default:
			
		}
		
		
		this.backString = MsgPool.ORDER_CERTIFICATION_GET[0]+cercode.get("order_cercode").toString()+MsgPool.ORDER_CERTIFICATION_GET[1];
	}
	
	public void rateOrder(long orderid, String ratecontent, String fromid, int ratelevel){
		OrderSVC o = new OrderSVC();
		int backcode = o.rateOrder(orderid, ratecontent, ratelevel, fromid);
		if (backcode == ProcessCode.DONE){
			this.backString = MsgPool.RATE_UPDATED;
		} else if (backcode == ProcessCode.ERR_NO_SUCH_RECORD){
			this.backString = MsgPool.ERR_NO_ORDER_FOUND;
		} else if (backcode == ProcessCode.ERR_INVALID_ID){
			this.backString = MsgPool.ERR_INVALID_ID;
		} else {
			switch(backcode){
			case OrderStatus.FAILED_FIXTIMEOUT:
				this.backString = MsgPool.ERR_ORDER_TIMEOUT_UNFIXED;
				return;
			case OrderStatus.FAILED_GETTIMEOUT:
				this.backString = MsgPool.ERR_ORDER_TIMEOUT_UNFIXED;
				return;
			case OrderStatus.FIXED_RATETIMEOUT:
				this.backString = MsgPool.ERR_ORDER_TIMEOUT_FIXED;
				return;
			case OrderStatus.FIXED_RATED:
				this.backString = MsgPool.ERR_ORDER_ALREADY_DONE;
				return;
			case OrderStatus.FAILED_CANCELED:
				this.backString = MsgPool.ERR_ORDER_ALREADY_CANCELED;
				return;
			case OrderStatus.PROCESSING_GETTED:
				this.backString = MsgPool.ERR_ORDER_UNDERPROCESS;
				return;
			case OrderStatus.PROCESSING_UNGETTED:
				this.backString = MsgPool.ERR_ORDER_UNACCEPTED;
				return;
			default:
				
			}
		}
			
	}
	
	public void getOrderByID(Long order_id, String fromid){
		List<HashMap> order = new ArrayList<HashMap>();
		OrderSVC o = new OrderSVC();
		order = o.getOrderForWechatSearch(order_id, fromid);
		orderToBackString(order);
	}

	private void orderToBackString(List<HashMap> order){
		String errinfo = order.get(0).get("KeyValue").toString();
		if(!errinfo.equals("none")) {
			this.backString = errinfo;
			return;
		}
		
		StringBuffer backstringbuf = new StringBuffer(MsgPool.ORDER_FOUND+"\r\n");
	
		for (Iterator i = order.iterator(); i.hasNext();){
			HashMap temp = (HashMap) i.next();
			
			if (temp.get("KeyLabel").equals("err")) continue;
			if (temp.get("KeyValue") == null) continue;
			
			if (temp.get("KeyLabel").equals("order_timestamp")||temp.get("KeyLabel").equals("order_resvtime")){
				backstringbuf.append("\r\n"+temp.get("KeyName").toString()+"："+temp.get("KeyValue").toString().substring(0,10));
				continue;
			}
			if (temp.get("KeyLabel").toString().equals("order_description")||temp.get("KeyLabel").toString().equals("order_ratedesc")){
				backstringbuf.append("\r\n"+temp.get("KeyName").toString()+"："+BlobProcess.blobToStr((Blob) temp.get("KeyValue"), null));
				continue;
			}
			if (temp.get("KeyLabel").toString().equals("order_status")){
				backstringbuf.append("\r\n"+temp.get("KeyName").toString()+"：");
				switch((int)temp.get("KeyValue")){
				case OrderStatus.FAILED_CANCELED:
					backstringbuf.append("已取消");
					break;
				case OrderStatus.FAILED_FIXTIMEOUT:
					backstringbuf.append("处理超时");
					break;
				case OrderStatus.FAILED_GETTIMEOUT:
					backstringbuf.append("接单超时");
					break;
				case OrderStatus.FIXED_RATED:
					backstringbuf.append("已评价");
					break;
				case OrderStatus.FIXED_RATETIMEOUT:
					backstringbuf.append("评价超时");
					break;
				case OrderStatus.FIXED_UNRATED:
					backstringbuf.append("待评价");
					break;
				case OrderStatus.PROCESSING_GETTED:
					backstringbuf.append("处理中");
					break;
				case OrderStatus.PROCESSING_UNGETTED:
					backstringbuf.append("未接单");
					break;
				default:
					backstringbuf.append("未知状态");
					break;
				}
				continue;
			}
			if (temp.get("KeyLabel").toString().equals("order_ratelevel")){
				backstringbuf.append("\r\n"+temp.get("KeyName").toString()+"：");
				switch((int)temp.get("KeyValue")){
				case 1:
					backstringbuf.append("非常不满意");
					break;
				case 2:
					backstringbuf.append("不满意");
					break;
				case 3:
					backstringbuf.append("一般");
					break;
				case 4:
					backstringbuf.append("满意");
					break;
				case 5:
					backstringbuf.append("非常满意");
					break;
				default:
					backstringbuf.append("未知评分");
					
				}
				continue;
			}
			
			backstringbuf.append("\r\n"+temp.get("KeyName").toString()+"："+temp.get("KeyValue").toString());
		}
		
		this.backString = backstringbuf.toString();
	}
	
	public void deleteOrder(long order_id, String fromwcid){
		
		OrderSVC o = new OrderSVC();
		int backcode = o.deleteOrderByID(order_id, fromwcid);
		if (backcode == ProcessCode.DONE){
			this.backString = MsgPool.ORDER_DELETED;
		} else if (backcode == ProcessCode.ERR_NO_SUCH_RECORD){
			this.backString = MsgPool.ERR_NO_ORDER_FOUND;
		} else if (backcode == ProcessCode.ERR_INVALID_ID){
			this.backString = MsgPool.ERR_INVALID_ID;
		} else {
			switch(backcode){
			case OrderStatus.FAILED_FIXTIMEOUT:
				this.backString = MsgPool.ERR_ORDER_TIMEOUT_UNFIXED;
				return;
			case OrderStatus.FAILED_GETTIMEOUT:
				this.backString = MsgPool.ERR_ORDER_TIMEOUT_UNFIXED;
				return;
			case OrderStatus.FIXED_RATETIMEOUT:
				this.backString = MsgPool.ERR_ORDER_TIMEOUT_FIXED;
				return;
			case OrderStatus.FIXED_RATED:
				this.backString = MsgPool.ERR_ORDER_ALREADY_DONE;
				return;
			case OrderStatus.FAILED_CANCELED:
				this.backString = MsgPool.ERR_ORDER_ALREADY_CANCELED;
				return;
			default:
				
			}
		}
	}
	
	public String getBackString() {
		return backString;
	}

	public void setBackString(String backString) {
		this.backString = backString;
	}

}
