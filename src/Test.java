import ana.view.order.outlink.Orderlist;


public class Test {
	
	public static void main(String[] args){
		Orderlist o = new Orderlist();
		o.setCurrentpage(1);
		System.out.println(o.outputHTMLPage());
	}

}
