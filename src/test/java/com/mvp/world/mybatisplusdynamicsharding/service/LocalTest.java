package com.mvp.world.mybatisplusdynamicsharding.service;

import org.apache.shardingsphere.core.strategy.keygen.SnowflakeShardingKeyGenerator;
import org.junit.Test;

import java.util.Properties;
import java.util.UUID;

public class LocalTest {

    @Test
    public void snowIdGeneratorTest() {
        SnowflakeShardingKeyGenerator snowflakeShardingKeyGenerator = new SnowflakeShardingKeyGenerator();
        Properties properties = new Properties();
        properties.setProperty("worker.id", "125");
        snowflakeShardingKeyGenerator.setProperties(properties);
        Comparable<?> comparable = snowflakeShardingKeyGenerator.generateKey();
        System.out.println(comparable);
    }

    @Test
    public void test() {
        String originUUID  = UUID.randomUUID().toString();
        System.out.println("originUUID=" + originUUID + ", length=" + originUUID.length());
        String uuid = originUUID.replaceAll("-","");
        System.out.println("uuid=" + uuid  + ", length=" + uuid.length());
    }
}
