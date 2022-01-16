package kr.hungrybrothres.data.repository

import kr.hungrybrothres.data.remote.subscribe.SubscribeService
import javax.inject.Inject

class SubscribeRepositoryImpl @Inject constructor(private val subscribeService: SubscribeService) :
    SubscribeService {
}