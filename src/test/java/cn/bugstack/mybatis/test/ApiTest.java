package cn.bugstack.mybatis.test;

import cn.bugstack.mybatis.binding.MapperProxy;
import cn.bugstack.mybatis.binding.MapperProxyFactory;
import cn.bugstack.mybatis.test.dao.IActivityDao;
import cn.bugstack.mybatis.test.dao.IUserDao;
import cn.bugstack.mybatis.test.po.Activity;
import cn.bugstack.mybatis.test.po.User;
import com.alibaba.fastjson.JSON;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ApiTest {

    private Logger logger = LoggerFactory.getLogger(ApiTest.class);

    /**
     * 测试两种使用动态代理的方式 工厂类和处理器类实现复杂的逻辑
     */
    @Test
    public void test_MapperProxyFactory(){

        // 工厂实例  只当IUSerDao解耦 用于生成该接口的代理实例
        MapperProxyFactory<IUserDao> factory = new MapperProxyFactory<>(IUserDao.class);

        // 存储方法命和对应的  模拟数据库操作或者查询结果
        Map<String,String> sqlSession = new HashMap<>();
        sqlSession.put("cn.bugstack.mybatis.test.dao.IUserDao.queryUserName", "模拟执行 Mapper.xml 中 SQL 语句的操作：查询用户姓名");
        sqlSession.put("cn.bugstack.mybatis.test.dao.IUserDao.queryUserAge", "模拟执行 Mapper.xml 中 SQL 语句的操作：查询用户年龄");

        // 调用newInstance方法  传入上面的sqlSession Map 创建一个实现类IUSERDao 接口的代理实例

        IUserDao userDao = factory.newInstance(sqlSession);

        String res = userDao.queryUserName("10001");
        logger.info("测试结果:{}",res);
    }

    /**
     * 直接使用Proxy.newProxyInstance方法 创建代理实例 适用于简单的代理场景
     */
    @Test
    public void test_proxy_class(){

        // 直接使用Proxy类的方法创建一个IUserDao的代理实例 拦截所有方法
        IUserDao userDao =(IUserDao) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                new Class[]{IUserDao.class},(proxy,method,args)->"你被代理");

        String result = userDao.queryUserName("10001");
        System.out.println("测试结果:"+ result);
    }

}
