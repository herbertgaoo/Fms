package dao;

import java.sql.*;
import db.*;
import entities.UserInfo;

public class UserDao {

	Connection con = null;
	PreparedStatement pst = null;

	public int checkLogin(String uName, String pWord) {
		int uid = 0;
		String sql = "SELECT * FROM account WHERE username=? AND password=?";
		try {
			con = new DBConnection().getConnection();
			pst = con.prepareStatement(sql);
			pst.setString(1, uName);
			pst.setString(2, pWord);

			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				uid = rs.getInt("id");
				rs.close();
				pst.close();
				con.close();
				return uid;
			}
			rs.close();
			pst.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return 0;
	}

	public boolean newUser(String uName, String pWord) {
		String sql = "INSERT INTO account(username,[password]) VALUES(?,?)";
		try {
			con = new DBConnection().getConnection();
			pst = con.prepareStatement(sql);
			pst.setString(1, uName);
			pst.setString(2, pWord);

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

	public boolean changePassword(int uid, String pWord, String newPW) {
		String sql = "UPDATE account SET password=? where id=? and password=?";
		try {
			con = new DBConnection().getConnection();
			pst = con.prepareStatement(sql);
			pst.setString(1, newPW);
			pst.setString(2, uid + "");
			pst.setString(3, pWord);

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

	public UserInfo getUserInfo(int uid) {
		UserInfo ui = new UserInfo();
		String sql = "SELECT * FROM userinfo WHERE userid=?";
		try {
			con = new DBConnection().getConnection();
			pst = con.prepareStatement(sql);
			pst.setString(1, uid + "");

			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				ui.setName(rs.getString("name"));
				ui.setBirthday(rs.getString("birthday").substring(0, 10));
				ui.setCompany(rs.getString("company"));
				ui.setPhone(rs.getString("phone"));
				rs.close();
				pst.close();
				con.close();
				return ui;
			}
			rs.close();
			pst.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	public boolean modifyUserInfo(UserInfo ui, String action) {
		String sql = null;
		if (action.equals("save")) {
			sql = "INSERT INTO userinfo(name,birthday,company,phone,userid) VALUES(?,?,?,?,?)";
		} else {
			sql = "UPDATE userinfo SET name=?,birthday=?,company=?,phone=? where userid=?";
		}
		try {
			con = new DBConnection().getConnection();
			pst = con.prepareStatement(sql);
			pst.setString(1, ui.getName());
			pst.setString(2, ui.getBirthday());
			pst.setString(3, ui.getCompany());
			pst.setString(4, ui.getPhone());
			pst.setString(5, ui.getUid() + "");

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
