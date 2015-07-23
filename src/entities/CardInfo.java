package entities;

public class CardInfo {
	
	private String cardId;
	
	private String type;
	
	private String bank;
	
	private String bankSite;
	
	private String date;
	
	private float amount;
	
	private int uid;

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getBankSite() {
		return bankSite;
	}

	public void setBankSite(String bankSite) {
		this.bankSite = bankSite;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}
	
}
