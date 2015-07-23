package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import db.DBConnection;
import entities.*;

public class BorrowRecordDao {
	Connection con = null;
	PreparedStatement pst = null;

	public ArrayList queRecord(String string, int uid) {
		ArrayList recordList = new ArrayList();
		BorrowingRecord deal = null;
		String sql = null;
		if (string.equals("borrow")) {
			sql = "SELECT * FROM borrow WHERE userid=?";
		} else {
			sql = "SELECT * FROM repayment WHERE userid=?";
		}
		try {
			con = new DBConnection().getConnection();
			pst = con.prepareStatement(sql);
			pst.setString(1, uid + "");

			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				deal = new BorrowingRecord();
				deal.setDeal_id(rs.getString("deal_id"));
				deal.setDeal_date(rs.getString("deal_date").substring(0, 10));
				deal.setAmount(rs.getFloat("amount"));
				deal.setBorrower(rs.getString("borrower"));
				deal.setTransactor(rs.getString("transactor"));
				deal.setTof(rs.getString("tof"));
				recordList.add(deal);
			}
			rs.close();
			pst.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return recordList;
	}

	public boolean addRecord(BorrowingRecord dealRecord, String str) {
		String sql = null;
		if (str.equals("borrow")) {
			sql = "INSERT INTO borrow (deal_date,amount,borrower,transactor,tof,userid)values(?,?,?,?,?,?)";
		} else {
			sql = "INSERT INTO repayment (deal_date,amount,borrower,transactor,tof,userid)values(?,?,?,?,?,?)";
		}
		try {
			con = new DBConnection().getConnection();
			pst = con.prepareStatement(sql);
			pst.setString(1, dealRecord.getDeal_date());
			pst.setString(2, dealRecord.getAmount() + "");
			pst.setString(3, dealRecord.getBorrower());
			pst.setString(4, dealRecord.getTransactor());
			pst.setString(5, dealRecord.getTof());
			pst.setString(6, dealRecord.getUid() + "");

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

	public boolean delRecord(String str, String deal_id, int uid) {
		String sql = null;
		if (str.equals("borrow")) {
			sql = "DELETE FROM borrow WHERE deal_id=? and userid=?";
		} else {
			sql = "DELETE FROM repayment WHERE deal_id=? and userid=?";
		}
		try {
			con = new DBConnection().getConnection();
			pst = con.prepareStatement(sql);
			pst.setString(1, deal_id);
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

	public boolean modRecord(String str, BorrowingRecord record) {
		String sql = null;
		if (str.equals("borrow")) {
			sql = "UPDATE borrow SET deal_date=?,amount=?,borrower=?,transactor=?,tof=? " +
					"WHERE userid=? AND deal_id=?";
		} else {
			sql = "UPDATE repayment SET deal_date=?,amount=?,borrower=?,transactor=?,tof=? " +
					"WHERE userid=? AND deal_id=?";
		}
		try {
			con = new DBConnection().getConnection();
			pst = con.prepareStatement(sql);
			pst.setString(1, record.getDeal_date());
			pst.setString(2, record.getAmount() + "");
			pst.setString(3, record.getBorrower());
			pst.setString(4, record.getTransactor());
			pst.setString(5, record.getTof());
			pst.setString(6, record.getUid()+"");
			pst.setString(7, record.getDeal_id()+"");

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
