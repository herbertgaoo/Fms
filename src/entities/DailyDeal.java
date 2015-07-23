package entities;

public class DailyDeal {
	
	private String deal_id;
	
	private String deal_date;
	
	private String methods;
	
	private float amount;
	
	private String comment;
	
	private int uid;

	public String getDeal_date() {
		return deal_date;
	}

	public void setDeal_date(String deal_date) {
		this.deal_date = deal_date;
	}

	public String getMethods() {
		return methods;
	}

	public void setMethods(String methods) {
		this.methods = methods;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getDeal_id() {
		return deal_id;
	}

	public void setDeal_id(String deal_id) {
		this.deal_id = deal_id;
	}
	
}
