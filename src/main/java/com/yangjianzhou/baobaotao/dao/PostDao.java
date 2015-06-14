package com.yangjianzhou.baobaotao.dao;

import com.yangjianzhou.baobaotao.entity.Post;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by yangjianzhou on 15-6-14.
 */
@Repository
public class PostDao extends BaseDao<Post> {
    @Override
    public Post getById(String id) {
        return null;
    }

    @Override
    public void deleteById(String id) {

    }

    @Override
    public List<Post> getAll() {
        return null;
    }

    @Override
    public void insert(Post post) {

    }

    @Override
    public void update(Post post) {

    }
}
