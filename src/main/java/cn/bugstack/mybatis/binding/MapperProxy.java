package cn.bugstack.mybatis.binding;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * 映射器代理类
 */
public class MapperProxy<T> implements InvocationHandler, Serializable {

    private static final long serialVersionUID = -6424540398559729838L;// 版本控制 序列化对象需要


    private Map<String,String> sqlSession; // 一个映射  用于存储SQL语句或者数据库操作与对应的处理逻辑

    private final Class<T> mapperInterface;// 存储该代理实例所代表的接口的class对象

    public MapperProxy(Map<String, String> sqlSession, Class<T> mapperInterface) {
        this.sqlSession = sqlSession;
        this.mapperInterface = mapperInterface;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if(Object.class.equals(method.getDeclaringClass())){
            return method.invoke(this,args);
        }else{
            return "你被代理 "+ sqlSession.get(mapperInterface.getName() + "." + method.getName());
        }
    }
}
