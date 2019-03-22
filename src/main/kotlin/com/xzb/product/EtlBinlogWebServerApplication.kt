package com.xzb.product

import com.xzb.product.config.CANAL_CONFIG
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication


@SpringBootApplication
@EnableConfigurationProperties
class EtlBinlogWebServerApplication


fun main(args: Array<String>) {
    runApplication<EtlBinlogWebServerApplication>(*args) {
        addInitializers(CANAL_CONFIG)
    }
}