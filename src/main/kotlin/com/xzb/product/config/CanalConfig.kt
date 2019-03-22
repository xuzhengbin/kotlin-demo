package com.xzb.product.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.beans

internal val CANAL_CONFIG = beans {
    bean<CanalConfig>()
}


@ConfigurationProperties("canal")
class CanalProperties {
    lateinit var ip: String
    var port: Int = 11111
    lateinit var destinations: String
    lateinit var serverMode: String
    lateinit var username: String
    lateinit var password: String
}


@Configuration
@EnableConfigurationProperties(CanalProperties::class)
class CanalConfig (val props: CanalProperties)