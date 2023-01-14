package com.flowershop.message.domain;

public class MessageVo {
	private int msg_no; 
	private String msg_send; //작성자 
	private String msg_regdate; //작성날짜
	private String msg_get; //받는사람
	private String msg_desc;
	private String msg_name;
	private String msg_sendyn;
	private String msg_getyn;
	private String user_id;
	private String msg_readyn;
	
	public int getMsg_no() {
		return msg_no;
	}
	public void setMsg_no(int msg_no) {
		this.msg_no = msg_no;
	}
	public String getMsg_send() {
		return msg_send;
	}
	public void setMsg_send(String msg_send) {
		this.msg_send = msg_send;
	}
	public String getMsg_regdate() {
		return msg_regdate;
	}
	public void setMsg_regdate(String msg_regdate) {
		this.msg_regdate = msg_regdate;
	}
	public String getMsg_get() {
		return msg_get;
	}
	public void setMsg_get(String msg_get) {
		this.msg_get = msg_get;
	}
	public String getMsg_desc() {
		return msg_desc;
	}
	public void setMsg_desc(String msg_desc) {
		this.msg_desc = msg_desc;
	}
	public String getMsg_name() {
		return msg_name;
	}
	public void setMsg_name(String msg_name) {
		this.msg_name = msg_name;
	}
	public String getMsg_sendyn() {
		return msg_sendyn;
	}
	public void setMsg_sendyn(String msg_sendyn) {
		this.msg_sendyn = msg_sendyn;
	}
	public String getMsg_getyn() {
		return msg_getyn;
	}
	public void setMsg_getyn(String msg_getyn) {
		this.msg_getyn = msg_getyn;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	public String getMsg_readyn() {
		return msg_readyn;
	}
	public void setMsg_readyn(String msg_readyn) {
		this.msg_readyn = msg_readyn;
	}
	@Override
	public String toString() {
		return "MessageVo [msg_no=" + msg_no + ", msg_send=" + msg_send + ", msg_regdate=" + msg_regdate + ", msg_get="
				+ msg_get + ", msg_desc=" + msg_desc + ", msg_name=" + msg_name + ", msg_sendyn=" + msg_sendyn
				+ ", msg_getyn=" + msg_getyn + ", user_id=" + user_id + ", msg_readyn=" + msg_readyn + "]";
	}
	
}
