package ana.model.wcmp.recorder;

import java.util.HashMap;

import ana.database.service.Wechat_msgrecSVC;

public class MsgRecorder extends Thread{

	@SuppressWarnings("rawtypes")
	private HashMap newmsg = new HashMap();
	
	@SuppressWarnings("rawtypes")
	public MsgRecorder(HashMap newmsg){
		this.newmsg = newmsg;
	}
	
	public void run(){
		Wechat_msgrecSVC msgsvc = new Wechat_msgrecSVC(this.newmsg);
		msgsvc.sortAndSave();
	}
}
