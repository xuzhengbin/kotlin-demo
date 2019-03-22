package com.xzb.product.server

import com.xzb.product.config.CanalProperties
import com.xzb.product.kafka.KafkaConsumer
import com.xzb.product.tcp.TcpConsumer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component

/**
 * 消费者启动服务
 */
@Component
@Order(1)
class CanalConsumerStart : ApplicationRunner {

    lateinit var properteis: CanalProperties @Autowired set
    lateinit var tcpConsumer: TcpConsumer @Autowired set
    lateinit var kafkaConsumer: KafkaConsumer @Autowired set

    override fun run(args: ApplicationArguments?) {
        if (properteis.serverMode == "tcp") {
            tcpConsumer.start()
        } else {
            kafkaConsumer.start()
        }
    }
}