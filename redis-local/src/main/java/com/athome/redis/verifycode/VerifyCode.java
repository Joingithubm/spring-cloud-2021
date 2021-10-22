package com.athome.redis.verifycode;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;

import java.util.Random;

/**
 * @Description:
 * @Author Zengfc
 * @Date 2021/10/12 14:41
 * @Version 1.0
 */
public class VerifyCode {

    final static int VERIFY_CODE_SIZE = 6;


     static Jedis  jedis = null;
     static {
          JedisShardInfo jedisShardInfo = new JedisShardInfo("1.117.48.220");
         jedisShardInfo.setPassword("redisToor");
         jedis = new Jedis(jedisShardInfo);
     }

    public static void main(String[] args) {

        getVerifyCode("135555555");
    }

    public static void getVerifyCode(String phone){
        // 1. 验证手机号
        if (!isCanUse(phone)){
            System.out.println("手机号只能使用三次");
            return;
        }
        // 2. 手机号次数加1;
        incrPhoneCount(phone);

        // 3. 生成验证码
        String verifyCode = generatorVerifyCode();
        jedis.setex(getVerifyCodeKey(phone),2*60,verifyCode);
        System.out.println(verifyCode);
    }

    public static boolean isCanUse(String phone){
        String count = jedis.get(getVerifyPhoneCountKey(phone));
        return (count != null && Integer.valueOf(count) > 3 ) ? false : true;
    }

    public static void incrPhoneCount(String phone){
        jedis.incr(getVerifyPhoneCountKey(phone));
    }

    public static String generatorVerifyCode(){
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < VERIFY_CODE_SIZE; i++) {
            builder.append(random.nextInt(10));
        }
        return builder.toString();
    }

    public static String getVerifyCodeKey(String phone){
        return "Verify:"+phone+"code";
    }

    public static String getVerifyPhoneCountKey(String phone){
        return "Verify:"+phone+"count";
    }
}
