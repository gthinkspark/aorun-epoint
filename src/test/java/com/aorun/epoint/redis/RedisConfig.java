//package com.aorun.epoint.redis;
//
//import com.fasterxml.jackson.annotation.JsonAutoDetect;
//import com.fasterxml.jackson.annotation.PropertyAccessor;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//
//@Configuration
//public class RedisConfig {
//
//    @Bean
//    public JedisConnectionFactory jedisConnectionFactory(){
//        JedisConnectionFactory factory = new JedisConnectionFactory();
//        factory.setHostName("10.10.0.95");
//        factory.setPort(6379);
//        return factory;
//    }
//
//        @Bean
//        public RedisTemplate<Object,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
//
//            RedisTemplate<Object, Object> template = new RedisTemplate<>();
//            template.setConnectionFactory(redisConnectionFactory);
//            //使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值
//            Jackson2JsonRedisSerializer<Object> redisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
//            ObjectMapper mapper = new ObjectMapper();
//            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//            mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//            redisSerializer.setObjectMapper(mapper);
//            template.setValueSerializer(redisSerializer);
//
//            //使用StringRedisSerializer来序列化和反序列化redis的key值
//            template.setKeySerializer(new StringRedisSerializer());
//            template.afterPropertiesSet();
//
//            return template;
//        }
//
//
////    @Bean
////    public RedisTemplate<String, String> redisTemplate(
////            RedisConnectionFactory factory) {
////        StringRedisTemplate template = new StringRedisTemplate(factory);
////        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
////        ObjectMapper om = new ObjectMapper();
////        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
////        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
////        jackson2JsonRedisSerializer.setObjectMapper(om);
////        template.setValueSerializer(jackson2JsonRedisSerializer);
////        template.afterPropertiesSet();
////        return template;
////    }
//
//}
