package cn.code4java.springbok.utils;

import cn.hutool.core.lang.TypeReference;
import cn.hutool.json.JSONUtil;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName RedisUtils
 * @Description: Redis工具类
 * @Author fengwensheng
 * @Date 2024/1/2
 * @Version V1.0
 **/
@Component
public class RedisUtils {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 写入一个key
     *
     * @param key
     * @param value
     */
    public void set(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    /**
     * 将对象以json格式存入
     *
     * @param key
     * @param value
     */
    public <T> void setToJson(String key, T value) {
        stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(value));
    }

    /**
     * 写入一个带过期时间的key
     *
     * @param key
     * @param value
     * @param timeout 过期时间
     * @param unit    时间单位
     * @return
     */
    public void set(String key, String value, long timeout, TimeUnit unit) {
        stringRedisTemplate.opsForValue().set(key, value, timeout, unit);
    }

    /**
     * 获取一个key的值
     *
     * @param key
     */
    public String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    /**
     * 获取一个key的值
     *
     * @param <T>
     * @param key
     * @return
     */
    public <T> T getToBean(String key, Class<T> cls) {
        String value = stringRedisTemplate.opsForValue().get(key);
        return StringUtils.isBlank(value) ? null : JSONUtil.toBean(value, cls);
    }

    /**
     * 获取一个key的值
     *
     * @param key
     * @param cls
     * @return
     */
    public <T> List<T> getToBeanList(String key, Class<T> cls) {
        String value = stringRedisTemplate.opsForValue().get(key);
        return StringUtils.isBlank(value) ? null : JSONUtil.toList(value, cls);
    }

    /**
     * 判断一个key是否存在
     *
     * @param key
     * @return
     */
    public boolean hasKey(String key) {
        return stringRedisTemplate.hasKey(key);
    }

    /**
     * 获取键的过期时间，返回秒
     *
     * @param key
     */
    public long getExpire(String key) {
        return stringRedisTemplate.getExpire(key);
    }

    /**
     * 获取键的过期时间，转换时间单位
     *
     * @param key
     */
    public long getExpire(String key, TimeUnit timeUnit) {
        return timeUnit.convert(stringRedisTemplate.getExpire(key), TimeUnit.SECONDS);
    }

    /**
     * 设置过期时间
     *
     * @param key
     * @param timeout 过期时间
     * @param unit    时间单位
     * @return
     */
    public boolean setExpire(String key, long timeout, TimeUnit unit) {
        Boolean expire = stringRedisTemplate.expire(key, timeout, unit);
        return expire;
    }

    /**
     * 设置过期时间，单位：毫秒
     *
     * @param key
     * @param timeout 过期时间
     * @return
     */
    public boolean setExpireByMillis(String key, long timeout) {
        Boolean expire = stringRedisTemplate.expire(key, Duration.ofMillis(timeout));
        return expire;
    }

    /**
     * 设置过期时间，单位：秒
     *
     * @param key
     * @param timeout 过期时间
     * @return
     */
    public boolean setExpireBySecond(String key, long timeout) {
        Boolean expire = stringRedisTemplate.expire(key, Duration.ofSeconds(timeout));
        return expire;
    }

    /**
     * 设置过期时间，单位：小时
     *
     * @param key
     * @param timeout 过期时间
     * @return
     */
    public boolean setExpireByHour(String key, long timeout) {
        Boolean expire = stringRedisTemplate.expire(key, Duration.ofHours(timeout));
        return expire;
    }

    /**
     * 设置过期时间，单位：天
     *
     * @param key
     * @param timeout 过期时间
     * @return
     */
    public boolean setExpireByDay(String key, long timeout) {
        Boolean expire = stringRedisTemplate.expire(key, Duration.ofMinutes(timeout));
        return expire;
    }

    /**
     * 删除key
     *
     * @param key
     * @return
     */
    public boolean delete(String key) {
        return stringRedisTemplate.delete(key);
    }

    /**
     * 指定hashKey写入Map中
     *
     * @param key
     * @param hashKey
     * @param value
     */
    public void setHash(String key, String hashKey, String value) {
        stringRedisTemplate.opsForHash().put(key, hashKey, value);
    }

    /**
     * 写入一个Map结构数据
     *
     * @param key
     * @param map
     */
    public void setHash(String key, Map<String, String> map) {
        stringRedisTemplate.opsForHash().putAll(key, map);
    }

    /**
     * 获取Map结构数据
     *
     * @param key
     * @param hashKey
     */
    public String getHash(String key, String hashKey) {
        return stringRedisTemplate.<String, String>opsForHash().get(key, hashKey);
    }

    /**
     * 获取Map结构数据
     *
     * @param key
     */
    public Map<String, String> entries(String key) {
        return stringRedisTemplate.<String, String>opsForHash().entries(key);
    }
}
