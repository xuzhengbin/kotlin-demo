package com.xzb.product.config

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import javax.sql.DataSource

/**
 * *
 * 多数据源配置
 * @author xuzhengbin
 * @date 2019-03-19
 */
@Configuration
class DataSourcesConfig {
    @Primary
    @Bean(name = ["quickReportDataSource"])
    @ConfigurationProperties(prefix = "spring.datasource.quick-report")
    fun quickReportDataSource() = DruidDataSourceBuilder.create().build()

    @Bean(name = ["quickReportTemplate"])
    fun quickReportTemplate(@Qualifier("quickReportDataSource") datasource: DataSource) = NamedParameterJdbcTemplate(datasource)

    @Bean(name = ["courseStudySourceDataSource"])
    @ConfigurationProperties(prefix = "spring.datasource.course-study")
    fun courseStudySourceDataSource() = DruidDataSourceBuilder.create().build()

    @Bean(name = ["courseStudySourceTemplate"])
    fun courseStudySourceTemplate(@Qualifier("courseStudySourceDataSource") datasource: DataSource) = NamedParameterJdbcTemplate(datasource)


    @Bean(name = ["binLogSourceDataSource"])
    @ConfigurationProperties(prefix = "spring.datasource.report-binlog")
    fun binLogSourceDataSource() = DruidDataSourceBuilder.create().build()

    @Bean(name = ["binLogSourceTemplate"])
    fun binLogSourceTemplate(@Qualifier("binLogSourceDataSource") datasource: DataSource) = NamedParameterJdbcTemplate(datasource)
}