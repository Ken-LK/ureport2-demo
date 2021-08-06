package com.bestway.ureport.datasource;

import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.baomidou.dynamic.datasource.ds.ItemDataSource;
import com.bstek.ureport.definition.datasource.BuildinDatasource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * @Type PersonDataSource.java
 * @author: Ken
 * @date: 2021/8/6 16:57
 * @description: person数据源
 */
@Component
@Slf4j
public class PersonDataSource  implements BuildinDatasource {

    private final String DATA_SOURCE_NAME = "person";

    @Autowired
    private DataSource dataSource;

    @Override
    public String name() {

        return DATA_SOURCE_NAME;
    }

    @Override
    public Connection getConnection() {

        try {
            DynamicRoutingDataSource ds = (DynamicRoutingDataSource) dataSource;
            ItemDataSource dataSourceAlarm = (ItemDataSource) ds.getDataSource(DATA_SOURCE_NAME);
            DataSource realDataSource = dataSourceAlarm.getRealDataSource();
            return realDataSource.getConnection();
        } catch (Exception e) {
            log.error("数据源:{}连接失败  {}", DATA_SOURCE_NAME, e);
        }
        return null;
    }
}
