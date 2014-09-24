package ana.view.wcmp.msgpool;

import ana.server.properties.ServerProperties;

public class MsgPool {
	
	public static final String ERR_UNKNOWN_CONTENT = "非常抱歉，暂时还无法识别您的消息，您可以回复“帮助”获取使用方法";
	public static final String ERR_CONTENT = "请您正确填写信息！";
	public static final String ERR_UNMATCHED_CONTENT = "抱歉，您未填写全部信息，或填写了过多的信息，系统无法为您提交，请您重新填写。";
	public static final String INNER_ERR = "服务器内部错误！";
	public static final String ERR_INVALID_ID = "身份信息不符，请您确认是否使用申报故障的微信号进行操作。";
	public static final String ERR_NO_ORDER_FOUND = "未查询到对应单号，请您确认提交的故障单号是否正确。";
	public static final String ERR_ORDER_ALREADY_DONE = "您提交的故障报修单已成功处理，无法再操作。您可以回复“故障单查询#故障单号#”查询报修单信息！";
	public static final String ERR_ORDER_ALREADY_CANCELED = "您提交的故障报修单已取消，无法再操作。您可以回复“故障单查询#故障单号#”查询报修单信息！";
	public static final String ERR_OUT_OF_RANGE = "抱歉，您所在的楼不在受理范围内，请您重新确认填写的信息，您可以回复“受理范围”获取所有受理故障申报的楼号。";
	public static final String ERR_ORDER_TIMEOUT_UNFIXED = "您提交的故障报修单已过期，很抱歉没能为您及时处理，您可以直接联系网络中心电话：\r\n82668828"
			+ "\r\n获取帮助，或重新提交故障单，我们会第一时间为您处理！";	
	public static final String ERR_ORDER_TIMEOUT_FIXED = "您提交的故障报修单由于评价超时已超过有效时限，无法再操作。您可以回复“故障单查询#故障单号#”查询报修单信息！";
	public static final String ERR_ORDER_UNACCEPTED = "对不起，您的故障单尚未被接单，目前仍无法评价，请您耐心等候，我们的网管人员会尽快接单并与您联系！\r\n取消报修，请回复“取消#故障单号”，取消后您申报的故障单将失效！";
	public static final String ERR_ORDER_UNDERPROCESS = "对不起，您的故障单尚在处理进程中，目前仍无法评价，请您耐心等候，我们的网管人员会尽快帮您解决问题！\r\n您可以回复“故障单查询#故障单号#”查询报修单信息！";
	public static final String ERR_INVALID_RATELEVEL = "评分信息有误，请确认您输入的是1~5的数字分值";
	public static final String ERR_BIZ_NOT_FOUND = "当前业务未开通，请确认正确填写了操作的名称！";
	public static final String ERR_ADMIN_NOT_FOUND = "您不是网管人员，无法执行此操作。若尚未登记微信号，请回复@我是网管#学号#进行登记；若您尚未注册网管人员身份，请联系管理员注册！";
	
	public static final String SERVICE_RANGE[] = {"现受理网络故障报修的宿舍楼列表","兴庆校区：\r\n东1、东2、东3、东6、东7、东8、东9、东10、东11、东12、东13、东14、东15、东16、东17、东18、东19、东20、东21"
			+ "；西1、西2、 西3、西4、西5、西6、 西7、西8、西9、西10、 西11、西12、西13、西14、 西15、西16、西17、西18、 西19、西20。","化工教学二区：\r\n职1、职3、职4。"
			,"雁塔校区：\r\n西校区1、西校区2、西校区3、西校区4、西校区5、西校区6、西校区7、西校区8、西校区10、西校区U型楼南、西校区U型楼北、西校区U型楼东、西校区留学生、西校区教学4。","二附院"};
	public static final String DECLARE_FAULT = "非常抱歉由于网络故障给您带来了不便,请您<a href=\"http://"+ServerProperties.webrootpath+ServerProperties.orderhandin_path+"?order_wechatid=:VOPENID\">点击此链接</a>进入信息填写页面填写提交报修信息，我们的网管人员会尽快为您处理您的故障！";
//	public static final String DECLARE_FAULT = "非常抱歉由于网络故障给您带来了不便，请您按如下格式填写报修信息：\r\n\r\n故障申报#姓名#联系电话#楼号#宿舍号#遇到故障的ip地址#故障描述#\r\n\r\n"
//			+ "例如：\r\n故障申报#张三#13012345678#东1#101#115.154.100.100#网络不通，提示连接受限#\r\n\r\n您可以回复“受理范围”查询目前支持报修的楼号，我们会尽快指派相应的学生网管为您提供帮助";
	public static final String HOW_TO_RATE = "欢迎使用故障处理评价反馈功能,您可以回复“维修评价#故障单号#评价内容#表现评分#”对我们的表现进行评价，评分采用五分制，1~5对应“非常不满意”~“非常满意”。例如：\r\n\r\n维修评价#10001#问题成功解决，处理得很及时，十分满意！#5#"
			+ "\r\n\r\n请留意，您只能通过报修时使用的微信号完成故障的评价，评价完成后故障单将失效。";
	
	public static final String TITLE_HELP_WELCOME = "欢迎使用西交网管协会微信智能服务平台！";
	public static final String TITLE_DECLARE_FAULT = "网络出现疑难故障？让我们帮您解决！请回复“我要报修”获取故障申报的方法。";
	public static final String TITLE_ACCEPT_RANGE = "想知道我们的管辖范围，以及申报故障时如何填写楼号？请回复“受理范围”获取所有受理报修的楼号列表 。";
	public static final String TITLE_SEARCH_ORDER = "需要查询故障单？实时跟踪您的故障单信息与处理情况，请回复“故障单查询#故障单号#”进行查询，例如：\r\n故障单查询#10001";
	public static final String TITLE_UPDATE_ORDER = "发现提交的故障单信息有误？您可以回复“我要改单”查询故障单修改方式。";
	public static final String TITLE_HOW_TO_RATE = "故障处理完后，希望您能给我们留下宝贵的意见和建议，帮助我们做的更好！请回复“我要评价”获取评价方式。";
	public static final String TITLE_GET_CERTIFACATION = "需要评价凭证，请回复“凭证索取#故障单号#”索要您的凭证。";
	public static final String TITLE_DELETE_ORDER = "如您需要取消故障申报，请回复“取消报修#故障单号#”";
	
	public static final String[] SUB_WELCOME = {"欢迎关注西安交通大学网络管理协会！我们将竭诚为您服务！","您可以随时发送“帮助”获取使用指南。"};

	public static final String[] ORDER_UPDATED = {"您的故障申报已经提交成功，我们的后台工作人员会尽快为您处理！请牢记您的故障单号："
			,"回复“我要查单”获取查询方法，实时获取您的故障单状态！\r\n故障处理完毕后请记得给我们评价哦~(回复“我要评价”获取评价方法)"
			+ "请留意，您所提交的故障单有效期为一周，逾期若故障仍未解决，请您直接联系网络中心或网管人员解决，或重新提交故障单。"
			+ "\r\n感谢您对我们工作的支持！"};
	public static final String[] ORDER_CERTIFICATION_GET = {"请您点击下面的链接获取您的凭证：\r\n"
			+ "<a href=\"http://api.k780.com:88/?app=qr.get&data=", "&level=H&size=20\">点击这里</a>"
			+ "\r\n您的凭证有效期限为一周，一周后故障单与凭证都将失效，届时如故障仍存在，请重新提交故障单或直接联系相关网管解决，感谢您对我们工作的支持！"};
	public static final String RATE_UPDATED = "评价已经成功提交，您可以回复“故障单查询#故障单号#”查询结果！";
	public static final String ORDER_FOUND = "为您找到了故障单！信息如下：";
	public static final String ORDER_DELETED = "取消故障报修成功，您提交的该条报修将不再有效！";
	public static final String ADMIN_BIZ_DONE = "业务成功办理！";


}
