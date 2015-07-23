package entities;

public class BorrowingRecord {
	
	private String deal_id;
	
	private String deal_date;
	
	private float amount;
	
	private String borrower;
	
	private String transactor;
	
	private String tof;
	
	private int uid;

	public String getDeal_id() {
		return deal_id;
	}

	public void setDeal_id(String deal_id) {
		this.deal_id = deal_id;
	}

	public String getDeal_date() {
		return deal_date;
	}

	public void setDeal_date(String deal_date) {
		this.deal_date = deal_date;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String getBorrower() {
		return borrower;
	}

	public void setBorrower(String borrower) {
		this.borrower = borrower;
	}

	public String getTransactor() {
		return transactor;
	}

	public void setTransactor(String transactor) {
		this.transactor = transactor;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getTof() {
		return tof;
	}

	public void setTof(String tof) {
		this.tof = tof;
	}
	
	
}
