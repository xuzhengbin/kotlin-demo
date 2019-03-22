package com.xzb.product.common

/**
 * 公共返回结果
 */
class CommonResult (val retCode: Int, val retMessage: String)

enum class CommonCode(var code: Int) {
    SUCCESS (0),
    FAIL (-1)
}

enum class CommonMessage(var message: String) {
    SUCCESSS ("操作成功"),
    FAIL ("操作失败")
}










