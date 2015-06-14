package com.yangjianzhou.baobaotao.dao;

import com.ibatis.sqlmap.client.SqlMapClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by yangjianzhou on 15-6-11.
 */

public abstract  class BaseDao<T> {

    @Autowired
    protected   SqlMapClient sqlMapClient ;

    public  abstract  T  getById(String id) ;

    public  abstract  void  deleteById(String id) ;

    public  abstract List<T> getAll();

    public abstract void  insert(T t);

    public  abstract  void update(T t);

}
