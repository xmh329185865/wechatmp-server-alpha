package ana.view.wcmp.msgpool;

import ana.server.properties.ServerProperties;

public class MsgPool {
	
	public static final String ERR_UNKNOWN_CONTENT = "�ǳ���Ǹ����ʱ���޷�ʶ��������Ϣ�������Իظ�����������ȡʹ�÷���";
	public static final String ERR_CONTENT = "������ȷ��д��Ϣ��";
	public static final String ERR_UNMATCHED_CONTENT = "��Ǹ����δ��дȫ����Ϣ������д�˹������Ϣ��ϵͳ�޷�Ϊ���ύ������������д��";
	public static final String INNER_ERR = "�������ڲ�����";
	public static final String ERR_INVALID_ID = "�����Ϣ����������ȷ���Ƿ�ʹ���걨���ϵ�΢�źŽ��в�����";
	public static final String ERR_NO_ORDER_FOUND = "δ��ѯ����Ӧ���ţ�����ȷ���ύ�Ĺ��ϵ����Ƿ���ȷ��";
	public static final String ERR_ORDER_ALREADY_DONE = "���ύ�Ĺ��ϱ��޵��ѳɹ������޷��ٲ����������Իظ������ϵ���ѯ#���ϵ���#����ѯ���޵���Ϣ��";
	public static final String ERR_ORDER_ALREADY_CANCELED = "���ύ�Ĺ��ϱ��޵���ȡ�����޷��ٲ����������Իظ������ϵ���ѯ#���ϵ���#����ѯ���޵���Ϣ��";
	public static final String ERR_OUT_OF_RANGE = "��Ǹ�������ڵ�¥��������Χ�ڣ���������ȷ����д����Ϣ�������Իظ�������Χ����ȡ������������걨��¥�š�";
	public static final String ERR_ORDER_TIMEOUT_UNFIXED = "���ύ�Ĺ��ϱ��޵��ѹ��ڣ��ܱ�Ǹû��Ϊ����ʱ����������ֱ����ϵ�������ĵ绰��\r\n82668828"
			+ "\r\n��ȡ�������������ύ���ϵ������ǻ��һʱ��Ϊ������";	
	public static final String ERR_ORDER_TIMEOUT_FIXED = "���ύ�Ĺ��ϱ��޵��������۳�ʱ�ѳ�����Чʱ�ޣ��޷��ٲ����������Իظ������ϵ���ѯ#���ϵ���#����ѯ���޵���Ϣ��";
	public static final String ERR_ORDER_UNACCEPTED = "�Բ������Ĺ��ϵ���δ���ӵ���Ŀǰ���޷����ۣ��������ĵȺ����ǵ�������Ա�ᾡ��ӵ���������ϵ��\r\nȡ�����ޣ���ظ���ȡ��#���ϵ��š���ȡ�������걨�Ĺ��ϵ���ʧЧ��";
	public static final String ERR_ORDER_UNDERPROCESS = "�Բ������Ĺ��ϵ����ڴ�������У�Ŀǰ���޷����ۣ��������ĵȺ����ǵ�������Ա�ᾡ�����������⣡\r\n�����Իظ������ϵ���ѯ#���ϵ���#����ѯ���޵���Ϣ��";
	public static final String ERR_INVALID_RATELEVEL = "������Ϣ������ȷ�����������1~5�����ַ�ֵ";
	public static final String ERR_BIZ_NOT_FOUND = "��ǰҵ��δ��ͨ����ȷ����ȷ��д�˲��������ƣ�";
	public static final String ERR_ADMIN_NOT_FOUND = "������������Ա���޷�ִ�д˲���������δ�Ǽ�΢�źţ���ظ�@��������#ѧ��#���еǼǣ�������δע��������Ա��ݣ�����ϵ����Աע�ᣡ";
	
	public static final String SERVICE_RANGE[] = {"������������ϱ��޵�����¥�б�","����У����\r\n��1����2����3����6����7����8����9����10����11����12����13����14����15����16����17����18����19����20����21"
			+ "����1����2�� ��3����4����5����6�� ��7����8����9����10�� ��11����12����13����14�� ��15����16����17����18�� ��19����20��","������ѧ������\r\nְ1��ְ3��ְ4��"
			,"����У����\r\n��У��1����У��2����У��3����У��4����У��5����У��6����У��7����У��8����У��10����У��U��¥�ϡ���У��U��¥������У��U��¥������У����ѧ������У����ѧ4��","����Ժ"};
	public static final String DECLARE_FAULT = "�ǳ���Ǹ����������ϸ��������˲���,����<a href=\"http://"+ServerProperties.webrootpath+ServerProperties.orderhandin_path+"?order_wechatid=:VOPENID\">���������</a>������Ϣ��дҳ����д�ύ������Ϣ�����ǵ�������Ա�ᾡ��Ϊ���������Ĺ��ϣ�";
//	public static final String DECLARE_FAULT = "�ǳ���Ǹ����������ϸ��������˲��㣬���������¸�ʽ��д������Ϣ��\r\n\r\n�����걨#����#��ϵ�绰#¥��#�����#�������ϵ�ip��ַ#��������#\r\n\r\n"
//			+ "���磺\r\n�����걨#����#13012345678#��1#101#115.154.100.100#���粻ͨ����ʾ��������#\r\n\r\n�����Իظ�������Χ����ѯĿǰ֧�ֱ��޵�¥�ţ����ǻᾡ��ָ����Ӧ��ѧ������Ϊ���ṩ����";
	public static final String HOW_TO_RATE = "��ӭʹ�ù��ϴ������۷�������,�����Իظ���ά������#���ϵ���#��������#��������#�������ǵı��ֽ������ۣ����ֲ�������ƣ�1~5��Ӧ���ǳ������⡱~���ǳ����⡱�����磺\r\n\r\nά������#10001#����ɹ����������úܼ�ʱ��ʮ�����⣡#5#"
			+ "\r\n\r\n�����⣬��ֻ��ͨ������ʱʹ�õ�΢�ź���ɹ��ϵ����ۣ�������ɺ���ϵ���ʧЧ��";
	
	public static final String TITLE_HELP_WELCOME = "��ӭʹ����������Э��΢�����ܷ���ƽ̨��";
	public static final String TITLE_DECLARE_FAULT = "����������ѹ��ϣ������ǰ����������ظ�����Ҫ���ޡ���ȡ�����걨�ķ�����";
	public static final String TITLE_ACCEPT_RANGE = "��֪�����ǵĹ�Ͻ��Χ���Լ��걨����ʱ�����д¥�ţ���ظ�������Χ����ȡ���������޵�¥���б� ��";
	public static final String TITLE_SEARCH_ORDER = "��Ҫ��ѯ���ϵ���ʵʱ�������Ĺ��ϵ���Ϣ�봦���������ظ������ϵ���ѯ#���ϵ���#�����в�ѯ�����磺\r\n���ϵ���ѯ#10001";
	public static final String TITLE_UPDATE_ORDER = "�����ύ�Ĺ��ϵ���Ϣ���������Իظ�����Ҫ�ĵ�����ѯ���ϵ��޸ķ�ʽ��";
	public static final String TITLE_HOW_TO_RATE = "���ϴ������ϣ�����ܸ��������±��������ͽ��飬�����������ĸ��ã���ظ�����Ҫ���ۡ���ȡ���۷�ʽ��";
	public static final String TITLE_GET_CERTIFACATION = "��Ҫ����ƾ֤����ظ���ƾ֤��ȡ#���ϵ���#����Ҫ����ƾ֤��";
	public static final String TITLE_DELETE_ORDER = "������Ҫȡ�������걨����ظ���ȡ������#���ϵ���#��";
	
	public static final String[] SUB_WELCOME = {"��ӭ��ע������ͨ��ѧ�������Э�ᣡ���ǽ��߳�Ϊ������","��������ʱ���͡���������ȡʹ��ָ�ϡ�"};

	public static final String[] ORDER_UPDATED = {"���Ĺ����걨�Ѿ��ύ�ɹ������ǵĺ�̨������Ա�ᾡ��Ϊ���������μ����Ĺ��ϵ��ţ�"
			,"�ظ�����Ҫ�鵥����ȡ��ѯ������ʵʱ��ȡ���Ĺ��ϵ�״̬��\r\n���ϴ�����Ϻ���ǵø���������Ŷ~(�ظ�����Ҫ���ۡ���ȡ���۷���)"
			+ "�����⣬�����ύ�Ĺ��ϵ���Ч��Ϊһ�ܣ�������������δ���������ֱ����ϵ�������Ļ�������Ա������������ύ���ϵ���"
			+ "\r\n��л�������ǹ�����֧�֣�"};
	public static final String[] ORDER_CERTIFICATION_GET = {"���������������ӻ�ȡ����ƾ֤��\r\n"
			+ "<a href=\"http://api.k780.com:88/?app=qr.get&data=", "&level=H&size=20\">�������</a>"
			+ "\r\n����ƾ֤��Ч����Ϊһ�ܣ�һ�ܺ���ϵ���ƾ֤����ʧЧ����ʱ������Դ��ڣ��������ύ���ϵ���ֱ����ϵ������ܽ������л�������ǹ�����֧�֣�"};
	public static final String RATE_UPDATED = "�����Ѿ��ɹ��ύ�������Իظ������ϵ���ѯ#���ϵ���#����ѯ�����";
	public static final String ORDER_FOUND = "Ϊ���ҵ��˹��ϵ�����Ϣ���£�";
	public static final String ORDER_DELETED = "ȡ�����ϱ��޳ɹ������ύ�ĸ������޽�������Ч��";
	public static final String ADMIN_BIZ_DONE = "ҵ��ɹ�����";


}
