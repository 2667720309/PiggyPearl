package com.pig.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author Blackke
 */
@Component
public class RedisUtils {
    /**
     * 注入静态 static 变量
     * 1.问题：直接 @Autowired注入静态变量，会导致空指针错误
     * 2.原因：static属于类的属性，在类初始化时就完成创建了。但是 @Autowired 在对象生成时才注入，因此空指针null
     * 3.解决办法：static声明变量，设置其非 static 的 set方法，并使用@Autowired/@Value标注，解决问题。
     */
    private static RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        RedisUtils.redisTemplate = redisTemplate;
    }

    /**
     * 指定目标缓存失效时间(秒)，默认永久有效
     *
     * @param key  key
     * @param time (time<=0不改变过期时间)
     * @return true 成功 false失败
     */
    public static boolean expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据key 获取过期时间（秒）
     *
     * @param key key
     * @return 时间(秒)
     * 1.The command returns -2 if the key does not exist.
     * 2.The command returns -1 if the key exists but has no associated expire.
     * 3.The command returns -3 if exception is occured
     */
    public static long getExpire(String key) {
        try {
            return redisTemplate.getExpire(key, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
            return -3;
        }
    }

    /**
     * 判断key是否存在
     *
     * @param key key
     * @return true 成功 false失败
     */
    public static boolean hasKey(String key) {
        try {
            return Boolean.TRUE.equals(redisTemplate.hasKey(key));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 设置缓存数据
     *
     * @param key   K
     * @param value V
     */
    public static boolean put(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取缓存数据
     *
     * @param key K
     * @return OBJ
     */
    public static Object get(String key) {
        try {
            System.out.println("获取到的key值：" + redisTemplate.opsForValue().get(key));
            return redisTemplate.opsForValue().get(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 设置缓存数据，并设置过期时间
     *
     * @param key   K
     * @param value V
     * @param time  时间（秒） 注意若time<=0，则设置无期限
     * @return true 成功 false失败
     */
    public static boolean put(String key, Object value, long time) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                redisTemplate.opsForValue().set(key, value);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除目标缓存
     *
     * @param key K
     * @return true 成功 false失败
     */
    public static boolean del(String key) {
        try {
            return Boolean.TRUE.equals(redisTemplate.delete(key));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * hashGet
     *
     * @param key  键 mapName
     * @param item 项 mapItem
     * @return OBJ
     */
    public static Object hashGet(String key, String item) {
        return redisTemplate.opsForHash().get(key, item);
    }

    /**
     * 获取hashKey对应的所有键值
     *
     * @param key 键
     * @return 对应的多个键值
     */
    public static Map<Object, Object> hashGet(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @return true 成功 false失败
     */
    public static boolean hashSet(String key, String item, Object value) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @param time  时间(秒) 注意:如果已存在的hash表有时间,这里将会替换原有的时间
     * @return true 成功 false失败
     */
    public static boolean hashSet(String key, String item, Object value, long time) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除hash表中的值
     *
     * @param key  键 不能为null
     * @param item 项 可以使多个 不能为null
     */
    public static void hashDel(String key, Object... item) {
        redisTemplate.opsForHash().delete(key, item);
    }

    /**
     * 判断hash表中是否有该项的值
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return true 存在 false不存在
     */
    public static boolean hashHasKey(String key, String item) {
        return redisTemplate.opsForHash().hasKey(key, item);
    }

    /**
     * hash递增
     * 如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 INCRBY 命令
     *
     * @param key  键
     * @param item 项
     * @param by   要增加几(大于0)
     * @return L
     */
    public static long hashIncr(String key, String item, long by) {
        return redisTemplate.opsForHash().increment(key, item, by);
    }

    /**
     * hash递减
     *
     * @param key  键
     * @param item 项
     * @param by   要减少记(小于0)
     * @return L
     */
    public static long hashDecr(String key, String item, long by) {
        return redisTemplate.opsForHash().increment(key, item, -by);
    }

    /**
     * 清空redis缓存
     *
     * @return The number of keys that were removed.
     */
    public static long flushDB() {
        try {
            Set<String> keys = redisTemplate.keys("*");
            return redisTemplate.delete(keys);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
