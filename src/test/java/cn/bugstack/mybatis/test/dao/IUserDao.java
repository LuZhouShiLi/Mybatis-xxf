package cn.bugstack.mybatis.test.dao;

import cn.bugstack.mybatis.test.po.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IUserDao {

    String queryUserName(String uId);

    Integer queryUserAge(String uId);
}
