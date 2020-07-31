package com.example.utils;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author wang.zhiqiang
 * @version 1.0
 * @date 2020/7/31 9:09
 * @Desc redis工具类
 */
@Component
public final class RedisUtil {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 给指定key设置过期时间
     *
     * @param key     key
     * @param seconds 过期时间
     * @return 设置过期时间是否成功
     */
    public boolean expire(String key, long seconds) {
        if (key != null && seconds > 0) {
            try {
                redisTemplate.expire(key, seconds, TimeUnit.SECONDS);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 查找指定key的过期时间
     *
     * @param key key
     * @return 返回该key的过期时间，如果没有设置过期时间返回null
     */
    public Long getExpireSeconds(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }


    /**
     * 判断key是否存在
     *
     * @param key key
     * @return 是否存在的bool值
     */
    public boolean hasKey(String key) {
        return Optional.ofNullable(redisTemplate.hasKey(key)).orElse(false);
    }

    public boolean hasHashKey(String key, Object field) {
        return Optional.of(redisTemplate.opsForHash().hasKey(key, field)).orElse(false);
    }

    /**
     * 删除key，可以删除多个
     *
     * @param keys 需要删除的key
     */
    public void del(String... keys) {
        if (keys != null && keys.length > 1) {
            if (keys.length == 1) {
                redisTemplate.delete(keys[0]);
            } else {
                redisTemplate.delete(Arrays.asList(keys));
            }
        }
    }

    /**
     * 删除hash表中的值
     *
     * @param key
     * @param fields
     */
    public void hdel(String key, Object... fields) {
        redisTemplate.opsForHash().delete(key, fields);
    }

    /**
     * 获取string类型的 value
     *
     * @param key key
     * @return string类型的value
     */
    public Object get(String key) {
        return Optional.ofNullable(redisTemplate.opsForValue().get(key)).orElse(null);
    }

    /**
     * set String类型的value
     *
     * @param key   key
     * @param value value
     * @return 是否set成功
     */
    public boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * set String类型的value
     *
     * @param key   key
     * @param value value
     * @return 是否set成功
     */
    public boolean set(String key, Object value, long seconds) {
        try {
            if (seconds > 0) {
                redisTemplate.opsForValue().set(key, value, seconds, TimeUnit.SECONDS);
            } else {
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * HashGet
     *
     * @param key   key 不能是null
     * @param field field 不能是null
     * @return 值
     */
    public Object hget(@NonNull String key, @NonNull String field) {
        return redisTemplate.opsForHash().get(key, field);
    }

    public boolean hset(@NonNull String key, @NonNull String field, @NonNull Object value, long seconds) {
        try {
            redisTemplate.opsForHash().put(key, field, value);
            if (seconds > 0) {
                expire(key, seconds);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean hset(@NonNull String key, @NonNull String field, @NonNull Object value) {
        return hset(key, field, value, 0);
    }

    public Map<Object, Object> hmget(@NonNull String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    public boolean hmset(String key, Map<String, Object> map, long seconds) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            if (seconds > 0) {
                expire(key, seconds);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean hmset(String key, Map<String, Object> map) {
        return hmset(key, map, 0);
    }

    public long sAddMembers(String key, long seconds, Object... members) {
        try {
            return Optional.ofNullable(redisTemplate.opsForSet().add(key, members)).orElse(0L);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            if (seconds > 0) {
                expire(key, seconds);
            }
        }
    }

    public long sAddMembers(String key, Object... members) {
        return sAddMembers(key, 0, members);
    }

    /**
     * 根据key获取set的值
     *
     * @param key key
     * @return set集合
     */
    public Set<Object> sListMembers(String key) {
        try {
            return redisTemplate.opsForSet().members(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean sHasMember(String key, Object value) {
        try {
            return Optional.ofNullable(redisTemplate.opsForSet().isMember(key, value)).orElse(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public long sGetSetSize(String key) {
        return Optional.ofNullable(redisTemplate.opsForSet().size(key)).orElse(0L);
    }

    public long sRemoveMembers(String key, Object members) {
        return Optional.ofNullable(redisTemplate.opsForSet().remove(key, members)).orElse(0L);
    }

    public List<Object> lGet(String key, long start, long end) {
        try {
            return redisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public long lGetListSize(String key) {
        return Optional.ofNullable( redisTemplate.opsForList().size(key)).orElse(0L);
    }

    public boolean lSet(String key, Object value) {
        return lSet(key, value, 0);
    }

    public boolean lSet(String key, Object value, long seconds) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            if (seconds > 0) {
                expire(key, seconds);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean lSet(String key, List<Object> values) {
        return lSet(key, values, 0);
    }

    public boolean lSet(String key, List<Object> values, long seconds) {
        try {
            redisTemplate.opsForList().rightPushAll(key, values);
            if (seconds > 0) {
                expire(key, seconds);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean lUpdateByIndex(String key, Long index, Object value) {
        try {
            redisTemplate.opsForList().set(key, index, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public long lRemove(String key, long count, Object value) {
        return Optional.ofNullable(redisTemplate.opsForList().remove(key, count, value)).orElse(0L);
    }
}
