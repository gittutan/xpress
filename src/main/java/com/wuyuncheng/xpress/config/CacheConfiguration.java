//package com.wuyuncheng.xpress.config;
//
//import com.fasterxml.jackson.annotation.JsonAutoDetect;
//import com.fasterxml.jackson.annotation.PropertyAccessor;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.boot.autoconfigure.AutoConfigureAfter;
//import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
//import org.springframework.cache.CacheManager;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.cache.interceptor.KeyGenerator;
//import org.springframework.context.annotation.AdviceMode;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.cache.RedisCacheConfiguration;
//import org.springframework.data.redis.cache.RedisCacheManager;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.RedisSerializationContext;
//import org.springframework.data.redis.serializer.RedisSerializer;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//
//import java.time.Duration;
//
//@Configuration
//@EnableCaching(mode = AdviceMode.PROXY) // 启用 Spring Cache
//@AutoConfigureAfter(RedisAutoConfiguration.class)
//public class CacheConfiguration {
//
//    @Bean
//    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
//        RedisTemplate<String, Object> template = new RedisTemplate<>();
//        template.setConnectionFactory(connectionFactory);
//        // 使用 StringRedisSerializer 来序列化和反序列化 Redis 的 key 值
//        template.setKeySerializer(new StringRedisSerializer());
//        template.setValueSerializer(jackson2JsonRedisSerializer());
//        template.setHashKeySerializer(new StringRedisSerializer());
//        template.setHashValueSerializer(jackson2JsonRedisSerializer());
//        template.afterPropertiesSet();
//        return template;
//    }
//
//    @Bean
//    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
//        // 生成一个默认配置，通过 config 对象即可对缓存进行自定义配置
//        RedisCacheConfiguration config = RedisCacheConfiguration
//                .defaultCacheConfig()
//                // 设置缓存的默认过期时间，也是使用 Duration 设置
//                .entryTtl(Duration.ofMinutes(10))
//                // 设置 key 为 String 序列化
//                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
//                // 设置 value 为 json 序列化
//                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer()))
//                // 不缓存空值
//                .disableCachingNullValues();
//        // 使用自定义的缓存配置初始化一个 CacheManager
//        RedisCacheManager redisCacheManager = RedisCacheManager.builder(redisConnectionFactory)
//                .cacheDefaults(config)
//                .build();
//        return redisCacheManager;
//    }
//
//    @Bean
//    public RedisSerializer<Object> jackson2JsonRedisSerializer() {
//        // 使用 Jackson2JsonRedisSerializer 来序列化和反序列化 Redis 的 value 值
//        Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer(Object.class);
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        serializer.setObjectMapper(mapper);
//        return serializer;
//    }
//
//    @Bean
//    public KeyGenerator keyGenerator() {
//        return (target, method, objects) -> {
//            StringBuilder sb = new StringBuilder();
//            sb.append(target.getClass().getName());
//            sb.append("::" + method.getName() + ":");
//            for (Object obj : objects) {
//                sb.append(obj.toString());
//            }
//            return sb.toString();
//        };
//    }
//
//}
