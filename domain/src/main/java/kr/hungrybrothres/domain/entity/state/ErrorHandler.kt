package kr.hungrybrothres.domain.entity.state

interface ErrorHandler {
    fun errorHandle(throwable: Throwable): Message
}