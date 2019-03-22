package com.xzb.product.tcp

import com.alibaba.otter.canal.client.CanalConnector
import com.xzb.product.config.CanalProperties
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.net.InetSocketAddress
import com.alibaba.otter.canal.client.CanalConnectors
import com.xzb.product.server.consumer
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import kotlin.concurrent.thread

/**
* @Description:    TCP消费者
* @Author:         xuzhengbin
* @CreateDate:     2019/3/19 22:25
*/
@Component
class TcpConsumer: consumer {
    val logger: Logger = LoggerFactory.getLogger(TcpConsumer::class.java)

    lateinit var properties: CanalProperties @Autowired set

    @Autowired
    @Qualifier("quickReportTemplate")
    lateinit var jdbcTemplate: NamedParameterJdbcTemplate set

    val batchSize = 1000
    var emptyCount = 0
    @Volatile
    var isRunning: Boolean = false
    var clientMap: HashMap<String, CanalConnector> = HashMap()

    override fun start() {
        properties.destinations.split(",").map { destination -> {
            thread(name = destination, start = true) {
                val connector = CanalConnectors.newSingleConnector(
                    InetSocketAddress(
                        properties.ip,
                        properties.port
                    ), destination, properties.username, properties.password
                )

                consumer(connector, destination)
                clientMap[destination] to connector
            }
        } }

        isRunning = true

        logger.info("consumer start .....")
    }

    override fun stop() {
        isRunning = false
        logger.info("consumer stop get message ....")
    }

    override fun consumer(connector: CanalConnector, destination: String) {
        try {
            connector.connect()
            connector.subscribe("$destination\\..*")
            connector.rollback()
            while (isRunning) {
                val message = connector.getWithoutAck(batchSize) // 获取指定数量的数据
                val batchId = message.id
                val size = message.entries.size
                if (batchId.toInt() == -1 || size == 0) {
                    emptyCount++
                    println("empty count : $emptyCount")
                    try {
                        Thread.sleep(1000)
                    } catch (e: InterruptedException) {
                        logger.error("something goes error", e)
                    }

                } else {
                    emptyCount = 0
                    println("message: $message")
//                    jdbcTemplate.execute(sql)
                }

                connector.ack(batchId) // 提交确认
            }
        } finally {
            connector.disconnect()
            logger.error("canal client disconnect")
        }
    }
}