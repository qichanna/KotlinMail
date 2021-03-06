package com.kotlin.user.presenter

import com.kotlin.base.ext.execute
import com.kotlin.base.presenter.BasePresenter
import com.kotlin.base.rx.BaseSubscriber
import com.kotlin.base.utils.NetWorkUtils
import com.kotlin.user.presenter.view.RegisterView
import com.kotlin.user.service.UserService
import javax.inject.Inject

class RegisterPresenter @Inject constructor():BasePresenter<RegisterView>() {

    @Inject
    lateinit var userService:UserService

    fun register(mobile:String,verifyCode:String,pwd:String){

        if(!checkNetWork()){
            return
        }
        mView.showLoading()

//        val userService = UserServiceImpl()
        userService.register(mobile,verifyCode,pwd)
                .execute(object : BaseSubscriber<Boolean>(mView){
                    override fun onNext(t: Boolean) {
                        if(t)
                        mView.onRegisterResult("注册成功")
                    }
                },lifecycleProvider)
    }
}