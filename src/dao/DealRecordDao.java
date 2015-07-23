package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import db.DBConnection;
import entities.DailyDeal;

public class DealRecordDao {

	Connection con = null;
	PreparedStatement pst = null;

	public boolean addRecord(DailyDeal dealRecord, String str) {
		String sql = null;
		if (str.equals("income")) {
			sql = "INSERT INTO income (deal_date,methods,amount,comment,userid)values(?,?,?,?,?)";
		} else {
			sql = "INSERT INTO spending (deal_date,methods,amount,comment,userid)values(?,?,?,?,?)";
		}
		try {
			con = new DBConnection().getConnection();
			pst = con.prepareStatement(sql);
			pst.setString(1, dealRecord.getDeal_date());
			pst.setString(2, dealRecord.getMethods());
			pst.setString(3, dealRecord.getAmount() + "");
			pst.setString(4, dealRecord.getComment());
			pst.setString(5, dealRecord.getUid() + "");

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

	public ArrayList queRecord(String string, int uid) {
		ArrayList recordList = new ArrayList();
		DailyDeal deal = null;
		String sql = null;
		if (string.equals("income")) {
			sql = "SELECT * FROM income WHERE userid=?";
		} else {
			sql = "SELECT * FROM spending WHERE userid=?";
		}
		try {
			con = new DBConnection().getConnection();
			pst = con.prepareStatement(sql);
			pst.setString(1, uid + "");

			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				deal = new DailyDeal();
				deal.setDeal_id(rs.getString("deal_id"));
				deal.setDeal_date(rs.getString("deal_date").substring(0, 10));
				deal.setMethods(rs.getString("methods"));
				deal.setAmount(rs.getFloat("amount"));
				deal.setComment(rs.getString("comment"));
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

	public boolean delRecord(String str, String deal_id, int uid) {
		String sql = null;
		if (str.equals("income")) {
			sql = "DELETE FROM income WHERE deal_id=? and userid=?";
		} else {
			sql = "DELETE FROM spending WHERE deal_id=? and userid=?";
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

	public ArrayList queOfDate(String startDate, String endDate, int i) {
		DailyDeal deal = null;
		ArrayList records = new ArrayList();
		String sql = "SELECT * FROM income WHERE deal_date>? AND deal_date<? AND userid=?";
		try {
			con = new DBConnection().getConnection();
			pst = con.prepareStatement(sql);
			pst.setString(1, startDate);
			pst.setString(2, endDate);
			pst.setString(3, i + "");

			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				deal = new DailyDeal();
				deal.setDeal_id(rs.getString("deal_id"));
				deal.setDeal_date(rs.getString("deal_date").substring(0, 10));
				deal.setMethods(rs.getString("methods"));
				deal.setAmount(rs.getFloat("amount"));
				deal.setComment(rs.getString("comment"));
				records.add(deal);
			}
			rs.close();
			pst.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return queOfDate(records, startDate, endDate, i);
	}

	public ArrayList queOfDate(ArrayList list, String startDate,
			String endDate, int i) {
		String sql = "SELECT * FROM spending WHERE deal_date>? AND deal_date<? AND userid=?";
		DailyDeal deal = null;
		try {
			con = new DBConnection().getConnection();
			pst = con.prepareStatement(sql);
			pst.setString(1, startDate);
			pst.setString(2, endDate);
			pst.setString(3, i + "");

			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				deal = new DailyDeal();
				deal.setDeal_id(rs.getString("deal_id"));
				deal.setDeal_date(rs.getString("deal_date").substring(0, 10));
				deal.setMethods(rs.getString("methods"));
				deal.setAmount(rs.getFloat("amount"));
				deal.setComment(rs.getString("comment"));
				list.add(deal);
			}
			rs.close();
			pst.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public float[] counter(String start, String end, int uid) {
		float[] data = new float[4];
		String[] sql = {
				"SELECT SUM(amount) as sum,userid FROM"
						+ " (SELECT * FROM income WHERE [deal_date]>? AND [deal_date]<?)"
						+ " GROUP BY userid HAVING userid=?",
				"SELECT SUM(amount) as sum,userid FROM"
						+ " (SELECT * FROM spending WHERE [deal_date]>? AND [deal_date]<?)"
						+ " GROUP BY userid HAVING userid=?",
				"SELECT SUM(amount) as sum,userid FROM"
						+ " (SELECT * FROM borrow WHERE [deal_date]>? AND [deal_date]<?)"
						+ " GROUP BY userid HAVING userid=?",
				"SELECT SUM(amount) as sum,userid FROM"
						+ " (SELECT * FROM repayment WHERE [deal_date]>? AND [deal_date]<?)"
						+ " GROUP BY userid HAVING userid=?", };
		try {
			for (int i = 0; i < sql.length; i++) {
				con = new DBConnection().getConnection();
				pst = con.prepareStatement(sql[i]);
				pst.setString(1, start);
				pst.setString(2, end);
				pst.setString(3, uid + "");

				ResultSet rs = pst.executeQuery();
				if (rs.next()) {
					data[i] = rs.getFloat("sum");
				}
				rs.close();
				pst.close();
				con.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
}
