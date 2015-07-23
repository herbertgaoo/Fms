package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import db.DBConnection;
import entities.*;

public class BankAccountDao {
	Connection con = null;
	PreparedStatement pst = null;

	public boolean addAccount(BankAccount ba) {
		String sql = "INSERT INTO bankaccount VALUES(?,?,?,?,?,?,?)";
		try {
			con = new DBConnection().getConnection();
			pst = con.prepareStatement(sql);
			pst.setString(1, ba.getAccount());
			pst.setString(2, ba.getType());
			pst.setString(3, ba.getBank());
			pst.setString(4, ba.getSite());
			pst.setString(5, ba.getDate());
			pst.setString(6, ba.getAmount() + "");
			pst.setString(7, ba.getUid() + "");

			int rs = pst.executeUpdate();
			if (rs == 1) {
				pst.close();
				con.close();
				return true;
			}
			pst.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	public boolean delAccount(String account, int uid) {
		String sql = "DELETE FROM bankaccount WHERE account=? AND userid=?";
		try {
			con = new DBConnection().getConnection();
			pst = con.prepareStatement(sql);
			pst.setString(1, account);
			pst.setString(2, uid + "");

			int rs = pst.executeUpdate();
			if (rs == 1) {
				pst.close();
				con.close();
				return true;
			}
			pst.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	public ArrayList queAccount(int uid) {
		BankAccount ba = null;
		ArrayList accounts = new ArrayList();
		String sql = "SELECT * FROM bankaccount WHERE userid=?";
		try {
			con = new DBConnection().getConnection();
			pst = con.prepareStatement(sql);
			pst.setString(1, uid + "");

			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				ba = new BankAccount();
				ba.setAccount(rs.getString("account"));
				ba.setBank(rs.getString("bank"));
				ba.setSite(rs.getString("site"));
				ba.setType(rs.getString("type"));
				ba.setDate(rs.getString("open_date").substring(0, 10));
				ba.setAmount(rs.getFloat("amount"));
				accounts.add(ba);
			}
			rs.close();
			pst.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return accounts;
	}

	public boolean modAccount(BankAccount ba) {
		String sql = "UPDATE bankaccount SET type=?,bank=?,site=?,open_date=?,amount=? WHERE account=? AND userid=?";
		try {
			con = new DBConnection().getConnection();
			pst = con.prepareStatement(sql);
			pst.setString(1, ba.getType());
			pst.setString(2, ba.getBank());
			pst.setString(3, ba.getSite());
			pst.setString(4, ba.getDate());
			pst.setString(5, ba.getAmount() + "");
			pst.setString(6, ba.getAccount());
			pst.setString(7, ba.getUid() + "");

			int rs = pst.executeUpdate();
			if (rs == 1) {
				pst.close();
				con.close();
				return true;
			}
			pst.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	public ArrayList initAccount(int i) {
		ArrayList accounts = new ArrayList();
		String account = null;
		String sql = "SELECT account FROM bankaccount WHERE userid=?";
		try {
			con = new DBConnection().getConnection();
			pst = con.prepareStatement(sql);
			pst.setString(1, i + "");

			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				account = rs.getString("account");
				accounts.add(account);
			}
			rs.close();
			pst.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return accounts;
	}

	public ArrayList queRecords(String account, int uid) {
		DealRecords dr = null;
		ArrayList records = new ArrayList();
		String sql = "SELECT * FROM dealrecords WHERE userid=? AND account=?";
		try {
			con = new DBConnection().getConnection();
			pst = con.prepareStatement(sql);
			pst.setString(1, uid + "");
			pst.setString(2, account.trim());

			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				dr = new DealRecords();
				dr.setAccount(rs.getString("account"));
				dr.setDate(rs.getString("deal_date").substring(0, 10));
				dr.setRecordID(rs.getString("id"));
				dr.setAmount(rs.getFloat("amount"));
				dr.setType(rs.getString("type"));
				records.add(dr);
			}
			rs.close();
			pst.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return records;
	}

	public boolean addRecords(DealRecords record) {
		String sql = "INSERT INTO dealrecords(deal_date,type,amount,userid,account) VALUES(?,?,?,?,?)";
		try {
			con = new DBConnection().getConnection();
			pst = con.prepareStatement(sql);
			pst.setString(1, record.getDate());
			pst.setString(2, record.getType());
			pst.setString(3, record.getAmount()+"");
			pst.setString(4, record.getUid()+"");
			pst.setString(5, record.getAccount());

			int rs = pst.executeUpdate();
			if (rs == 1) {
				pst.close();
				con.close();
				return true;
			}
			pst.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	
	public boolean delRecords(String account) {
		String sql = "DELETE FROM dealrecords WHERE id=?";
		try {
			con = new DBConnection().getConnection();
			pst = con.prepareStatement(sql);
			pst.setString(1, account);

			int rs = pst.executeUpdate();
			if (rs == 1) {
				pst.close();
				con.close();
				return true;
			}
			pst.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
}
