package com.example.sample_kotlin.controller

import com.example.sample_kotlin.controller.resources.RegisterForm
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/register")
class LgtmRegisterController {

    /**
     * 登録フォーム取得用
     */
    @GetMapping
    fun getRegisterForm(registerForm: RegisterForm, model: Model): String {
        model.addAttribute(registerForm)
        return "register"
    }

    @PostMapping("/register")
    fun post(registerForm: RegisterForm,model: Model) : String{

        model.addAttribute(registerForm)
        return "redirect:register"
    }
}