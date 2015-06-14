package com.yangjianzhou.baobaotao.dao;

import com.yangjianzhou.baobaotao.entity.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserDao extends  BaseDao<User>{

/*	@Autowired
	public SqlMapClient  sqlMapClient ;*/

	@Override
	public User getById(String id) {
		return null;
	}

	@Override
	public void deleteById(String id) {

	}

	@Override
	public List<User> getAll() {
		return null;
	}

	@Override
	public void insert(User user) {

	}

	@Override
	public void update(User user) {

	}
/*	@Autowired
    private JdbcTemplate jdbcTemplate;

	public int getMatchCount(String userName, String password) {
		String sqlStr = " SELECT count(*) FROM tbl_user " + " WHERE user_name =? and password=? ";
		return jdbcTemplate.queryForInt(sqlStr, new Object[] { userName, password });
	}

	public User findUserByUserName(final String userName) {
		String sqlStr = " SELECT user_id,user_name,credits " + " FROM tbl_user WHERE user_name =? ";
		final User user = new User();
		jdbcTemplate.query(sqlStr, new Object[] { userName }, new RowCallbackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				user.setUserId(rs.getString("user_id"));
				user.setUserName(userName);
				user.setCredits(rs.getInt("credits"));
			}
		});
		return user;
	}

	public void updateLoginInfo(User user) {
		String sqlStr = " UPDATE tbl_user SET last_visit=?,last_ip=?,credits=? " + " WHERE user_id =?";
		jdbcTemplate.update(sqlStr, new Object[] { user.getLastVisit(), user.getLastIp(), user.getCredits(), user.getUserId() });
	}*/

    public int getMatchCount(String username, String password) throws Exception {
        Map<String, String> paramater = new HashMap<String, String>();
        paramater.put("username", username);
        paramater.put("password", password);
        return (int) sqlMapClient.queryForObject("user_hand.countUserByUserNameAndPassword", paramater);

    }

    public User getUserByUserName(String username) throws Exception {
        return (User) sqlMapClient.queryForObject("user_hand.queryByUserName", username);
    }

    public void updateLoginInfo(User user) throws Exception {
        sqlMapClient.update("user_hand.updateUserInfo", user);
    }

	public  List<User>  queryUsersByUserName(String userName) throws  Exception{
		return  sqlMapClient.queryForList("user_hand.queryUsersByUserName",userName);
	}
/*
	public SqlMapClient getSqlMapClient() {
		return sqlMapClient;
	}

	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}*/
}
