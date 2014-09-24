package ana.model.wcmp.recorder;

import java.util.HashMap;

import ana.database.service.OrderSVC;

@SuppressWarnings("rawtypes")
public class OrderRecorder extends Thread{
	
	private HashMap order;
	
	public OrderRecorder(HashMap order){
		this.order = order;
	}

	public void run(){
		OrderSVC o = new OrderSVC();
		o.addNewBasicOrderToDB(this.order);
	}
}
