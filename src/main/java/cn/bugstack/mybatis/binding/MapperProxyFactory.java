package cn.bugstack.mybatis.binding;


//import org.apache.ibatis.binding.MapperProxy;

import java.lang.reflect.Proxy;
import java.util.Map;

/**
 * 生成代理对象  映射器代理工厂 工厂模式 动态创建实现指定接口的代理实例
 * @param <T>
 */
public class MapperProxyFactory<T> {

    // 存储一个类的class对象 表示代理工厂为哪一个接口创建代理实例
    private final Class<T> mapperInterface;// 存储一个类的class对象 表示代理工厂将为哪一个接口创建代理实例

    // 指定代理工厂将为哪一个接口创建代理实例
    public MapperProxyFactory(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    /**
     * 调用处理器 处理对代理对象的所有方法调用 第一个参数存储sql和处理逻辑
     * @param sqlSession
     * @return
     */
    public T newInstance(Map<String,String> sqlSession){
        // 创建一个实例 用于处理对代理对象的所有被方法调用 构造函数接受一个参数 sql Session是一个映射  用于存储SQL语句和对应的处理逻辑
        final MapperProxy<T> mapperProxy = new MapperProxy<>(sqlSession,mapperInterface);

        // 创建代理实例 第一个类加载器 定义代理类 第二个参数 表示代理类实现哪一个接口  第三个参数 实现接口实例 用于处理对代理实例的调用
        return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(),new Class[]{mapperInterface},mapperProxy);
    }

}
