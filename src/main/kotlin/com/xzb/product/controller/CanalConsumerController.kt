package com.xzb.product.controller

import com.xzb.product.common.CommonCode
import com.xzb.product.common.CommonMessage
import com.xzb.product.common.CommonResult
import com.xzb.product.tcp.TcpConsumer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CanalConsumerController {

    lateinit var consumer: TcpConsumer @Autowired set

    @GetMapping("/consumer/status")
    @ResponseBody
    fun queryStatus(): Boolean {
        return consumer.isRunning
    }

    @GetMapping("/consumer/start")
    fun start(): CommonResult {
        if (!consumer.isRunning) consumer.start()
        return CommonResult(CommonCode.SUCCESS.code, CommonMessage.SUCCESSS.message)
    }

    @GetMapping("/consumer/stop")
    fun stop(): CommonResult {
        if (consumer.isRunning) consumer.stop()
        return CommonResult(CommonCode.SUCCESS.code, CommonMessage.SUCCESSS.message)
    }
}