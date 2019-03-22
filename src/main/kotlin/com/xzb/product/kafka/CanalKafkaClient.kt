package com.xzb.product.kafka

import com.alibaba.otter.canal.client.CanalConnector
import com.xzb.product.server.consumer
import org.springframework.stereotype.Component

@Component
class KafkaConsumer: consumer {

    override fun start() {
    }

    override fun stop() {
    }

    override fun consumer(connector: CanalConnector, destination: String) {
    }
}