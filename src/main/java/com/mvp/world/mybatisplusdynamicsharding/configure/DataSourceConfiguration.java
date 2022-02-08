package com.mvp.world.mybatisplusdynamicsharding.configure;

import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.baomidou.dynamic.datasource.provider.AbstractDataSourceProvider;
import com.baomidou.dynamic.datasource.provider.DynamicDataSourceProvider;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DataSourceProperty;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DynamicDataSourceAutoConfiguration;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DynamicDataSourceProperties;
import com.baomidou.dynamic.datasource.strategy.DynamicDataSourceStrategy;
import org.apache.shardingsphere.shardingjdbc.jdbc.adapter.AbstractDataSourceAdapter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Map;

/**
 * 动态数据源配置
 *
 * 使用{@link com.baomidou.dynamic.datasource.annotation.DS}注解，切换数据源
 *
 * <code>@DS(DataSourceConfiguration.SHARDING_DATASOURCE_NAME)</code>
 *
 * @author mvp
 * @since 2021/1/26 17:27:59
 */
@Configuration
@AutoConfigureBefore({DynamicDataSourceAutoConfiguration.class, SpringBootConfiguration.class})
public class DataSourceConfiguration {
    /**
     * 分表数据源名称
     */
    private static final String SHARDING_DATASOURCE_NAME = "sharding";

    /**
     * 动态数据源配置项
     */
    @Resource
    private DynamicDataSourceProperties properties;

    /**
     * sharding-jdbc有四种数据源，根据不同业务，选择注入不同的数据源。
     *
     * <p>1. 数据分片数据源名称(默认): shardingDataSource;
     * <p>2. 主从数据源: masterSlaveDataSource;
     * <p>3. 脱敏数据源：encryptDataSource;
     * <p>4. 影子数据源：shadowDataSource
     */
    @Lazy
    @Resource(name = "shardingDataSource")
    AbstractDataSourceAdapter shardingDataSource;
    /**
     * 动态数据源加载器添加sharding-jdbc数据源
     *
     * @return 动态数据源加载器
     */
    @Bean(name = "dynamicDataSourceProvider")
    public DynamicDataSourceProvider dynamicDataSourceProvider() {
        Map<String, DataSourceProperty> dataSourcePropertiesMap = properties.getDatasource();
        return new AbstractDataSourceProvider() {
            @Override
            public Map<String, DataSource> loadDataSources() {
                Map<String, DataSource> dataSourceMap = createDataSourceMap(dataSourcePropertiesMap);
                // 将sharding-jdbc管理的数据源，也交给动态数据源管理
                dataSourceMap.put(SHARDING_DATASOURCE_NAME, shardingDataSource);
                return dataSourceMap;
            }
        };
    }

    @Bean(name = "dynamicDataSourceStrategy")
    public DynamicDataSourceStrategy dynamicDataSourceStrategy() throws Exception {
        return properties.getStrategy().newInstance();
    }

    /**
     * 根据动态数据源加载器，生成动态路由数据源的bean对象。
     * 将动态数据源设置为首选数据源。当spring存在多个数据源时, 自动注入的是首选数据源。
     * 设置为首选数据源之后，就可以支持sharding-jdbc原生的配置方式。
     *
     * @return 动态路由数据源的bean对象
     */
    @Primary
    @Bean
    public DataSource dataSource(@Qualifier("dynamicDataSourceProvider") DynamicDataSourceProvider dynamicDataSourceProvider,
                                 @Qualifier("dynamicDataSourceStrategy") DynamicDataSourceStrategy dynamicDataSourceStrategy) {
        DynamicRoutingDataSource dataSource = new DynamicRoutingDataSource();
        dataSource.setPrimary(properties.getPrimary());
        dataSource.setStrict(properties.getStrict());
        dataSource.setStrategy(dynamicDataSourceStrategy);
        dataSource.setProvider(dynamicDataSourceProvider);
        dataSource.setP6spy(properties.getP6spy());
        dataSource.setSeata(properties.getSeata());
        return dataSource;
    }
}