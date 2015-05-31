package com.yangjianzhou.baobaotao.dao;

import com.yangjianzhou.baobaotao.bean.UserBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public int getMatchCount(String userName, String password) {
		String sqlStr = " SELECT count(*) FROM tbl_user " + " WHERE user_name =? and password=? ";
		return jdbcTemplate.queryForInt(sqlStr, new Object[] { userName, password });
	}

	public UserBean findUserByUserName(final String userName) {
		String sqlStr = " SELECT user_id,user_name,credits " + " FROM tbl_user WHERE user_name =? ";
		final UserBean user = new UserBean();
		jdbcTemplate.query(sqlStr, new Object[] { userName }, new RowCallbackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				user.setUserId(rs.getString("user_id"));
				user.setUserName(userName);
				user.setCredits(rs.getInt("credits"));
			}
		});
		return user;
	}

	public void updateLoginInfo(UserBean user) {
		String sqlStr = " UPDATE tbl_user SET last_visit=?,last_ip=?,credits=? " + " WHERE user_id =?";
		jdbcTemplate.update(sqlStr, new Object[] { user.getLastVisit(), user.getLastIp(), user.getCredits(), user.getUserId() });
	}
}