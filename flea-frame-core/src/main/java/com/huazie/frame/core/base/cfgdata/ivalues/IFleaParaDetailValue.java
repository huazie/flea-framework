package com.huazie.frame.core.base.cfgdata.ivalues;

import java.io.Serializable;

/**
 * <p> 静态参数配置类接口 </p>
 * <p>（用于放置常量和属性的get和set方法） </p>
 * 
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 *
 */
public interface IFleaParaDetailValue extends Serializable{

	String S_PARA_ID = "paraId";
	String S_PARA_TYPE = "paraType";
	String S_PARA_CODE = "paraCode";
	String S_PARA_NAME = "paraName";
	String S_PARA1 = "para1";
	String S_PARA2 = "para2";
	String S_PARA3 = "para3";
	String S_PARA4 = "para4";
	String S_PARA5 = "para5";
	String S_PARA_STATE = "paraState";
	String S_PARA_DESC = "paraDesc";
	
	int PARA_STATE_BE_DELETED = 0;	// 删除
	int PARA_STATE_IN_USE = 1;		// 在用
	
	String getParaId();
	
	void setParaId(String paraId);
	
	String getParaType();
	
	void setParaType(String paraType);
	
	String getParaCode();
	
	void setParaCode(String paraCode);
	
	String getParaName();
	
	void setParaName(String paraName);
	
	String getPara1();
	
	void setPara1(String para1);
	
	String getPara2();
	
	void setPara2(String para2);
	
	String getPara3();
	
	void setPara3(String para3);
	
	String getPara4();
	
	void setPara4(String para4);
	
	String getPara5();
	
	void setPara5(String para5);
	
	int getParaState();
	
	void setParaState(int paraState);
	
	String getParaDesc();
	
	void setParaDesc(String paraDesc);
}
