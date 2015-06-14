package com.yangjianzhou.baobaotao.dao;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.yangjianzhou.baobaotao.entity.LoginLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LoginLogDao extends BaseDao<LoginLog>{
/*	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void insertLoginLog(LoginLog loginLog) {
		String sqlStr = "INSERT INTO tbl_login_log(login_log_id,user_id,ip,login_time) " + "VALUES(?,?,?,?)";
		Object[] args = {loginLog.getLoginLogId(), loginLog.getUserId(), loginLog.getIp(), loginLog.getLoginDate() };
		jdbcTemplate.update(sqlStr, args);
	}*/

	public  void  insertLoginLog(LoginLog loginLog) throws Exception{
		sqlMapClient.insert("login_log_hand.insert", loginLog);
	}

	@Override
	public LoginLog getById(String id) {
		return null;
	}

	@Override
	public void deleteById(String id) {

	}

	@Override
	public List<LoginLog> getAll() {
		return null;
	}

	@Override
	public void insert(LoginLog loginLog) {

	}

	@Override
	public void update(LoginLog loginLog) {

	}
}