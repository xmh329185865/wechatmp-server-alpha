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
			return "δ�ӵ�";
		case PROCESSING_GETTED:
			return "������";
		case FIXED_UNRATED:
			return "���޸���δ���ۣ�";
		case FIXED_RATED:
			return "���޸��������ۣ�";
		case FAILED_GETTIMEOUT:
			return "�ӵ���ʱ";
		case FAILED_FIXTIMEOUT:
			return "ά�޳�ʱ";
		case FIXED_RATETIMEOUT:
			return "���۳�ʱ";
		case FAILED_CANCELED:
			return "��ȡ��";
		default:
			return "δ֪״̬:"+order_status;
		}
	}
	
}
