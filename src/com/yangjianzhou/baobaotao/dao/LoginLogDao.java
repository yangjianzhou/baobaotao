package com.yangjianzhou.baobaotao.dao;

import com.yangjianzhou.baobaotao.bean.LoginLogBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LoginLogDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void insertLoginLog(LoginLogBean loginLogBean) {
		String sqlStr = "INSERT INTO tbl_login_log(login_log_id,user_id,ip,login_time) " + "VALUES(?,?,?,?)";
		Object[] args = {loginLogBean.getLoginLogId(), loginLogBean.getUserId(), loginLogBean.getIp(), loginLogBean.getLoginDate() };
		jdbcTemplate.update(sqlStr, args);
	}
}