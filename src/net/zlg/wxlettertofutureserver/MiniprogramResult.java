package net.zlg.wxlettertofutureserver;

import java.io.Serializable;

public class MiniprogramResult implements Serializable {
 
	private static final long serialVersionUID = 1L;
 
	/** ��ȡ��openid */
	public String openid;
	/** ��ȡ��unionid */
	public String unionid;
	/** ��ȡ��session_key */
	public String session_key;
	/** ��ȡ����ƾ֤ */
	public String access_token;
	/** ƾ֤��Чʱ�䣬��λ���� */
	public Integer expires_in;
	/** ������ */
	public Integer errcode;
	/** ������Ϣ */
	public String errmsg;
 
    // get set ʡ��

}
