package com.xzb.product.server

import com.alibaba.otter.canal.client.CanalConnector

/**
 * 消费者接口
 */
interface consumer {

    /**
     * 启动
     */
    fun start()

    /**
     * 停止
     */
    fun stop()

    /**
     * 消息消费
     */
    fun consumer(connector: CanalConnector, destination: String)
}