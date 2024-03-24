package cn.bugstack.mybatis.binding;


import org.apache.ibatis.binding.MapperProxy;

import java.util.Map;

/**
 * 生成代理对象
 * @param <T>
 */
public class MapperProxyFactory<T> {

    private final Class<T> mapperInterface;

    public MapperProxyFactory(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }


    public T newInstance(Map<String,String> sqlSession){
        final MapperProxy<T>
    }

}
