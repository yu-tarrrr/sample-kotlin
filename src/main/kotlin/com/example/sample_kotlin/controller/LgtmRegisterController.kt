package com.example.sample_kotlin.controller

import com.example.sample_kotlin.controller.resources.LgtmEntity
import com.example.sample_kotlin.controller.resources.RegisterForm
import com.example.sample_kotlin.infrastructure.LgtmRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import java.util.*

@Controller
@RequestMapping("/register")
class LgtmRegisterController (var lgtmRepository: LgtmRepository){

    /**
     * 登録フォーム取得用
     */
    @GetMapping
    fun getRegisterForm(registerForm: RegisterForm, model: Model): String {
        model.addAttribute(registerForm)
        return "register"
    }

    @PostMapping(name = "/register")
    fun post(registerForm: RegisterForm,model: Model) : String{

        // 登録用のインスタンスの作成
        var lgtmEntity = LgtmEntity()
        lgtmEntity.apply {
            imageUrl = registerForm.imageUrl
            imageName = registerForm.imageName
            imageLgtmUrl = "[![LGTM](${registerForm.imageUrl})](${registerForm.imageUrl})"
            updateDatetime = Calendar.getInstance().time
        }

        // 登録処理
        try {
            lgtmRepository.save(lgtmEntity)
        } catch (ex : Exception) {
            model.addAttribute("error", ex.message)
        }
        model.addAttribute(registerForm)
        return "redirect:register"
    }
}