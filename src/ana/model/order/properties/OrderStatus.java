package ana.model.order.properties;

public class OrderStatus {
	
	public static final int PROCESSING_UNGETTED = 1;
	public static final int PROCESSING_GETTED = 2;
	public static final int FIXED_UNRATED = 3;
	public static final int FIXED_RATED = 0;
	public static final int FAILED_GETTIMEOUT = 4;
	public static final int FAILED_FIXTIMEOUT = 5;
	public static final int FIXED_RATETIMEOUT = 6;
	public static final int FAILED_CANCELED = 9;

	public static String statusWrapper(int order_status){
		switch(order_status){
		case PROCESSING_UNGETTED:
			return "未接单";
		case PROCESSING_GETTED:
			return "处理中";
		case FIXED_UNRATED:
			return "已修复（未评价）";
		case FIXED_RATED:
			return "已修复（已评价）";
		case FAILED_GETTIMEOUT:
			return "接单超时";
		case FAILED_FIXTIMEOUT:
			return "维修超时";
		case FIXED_RATETIMEOUT:
			return "评价超时";
		case FAILED_CANCELED:
			return "已取消";
		default:
			return "未知状态:"+order_status;
		}
	}
	
}
