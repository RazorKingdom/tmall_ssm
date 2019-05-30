package com.ssm.tmall.controller.fore;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class PageController {
    @RequestMapping("registerPage")
    public String registerPage(){
        return "fore/register";
    }

    @RequestMapping("registerSuccessPage")
    public String registerSuccessPPage(){
        return "fore/registerSuccess";
    }

    @RequestMapping("loginPage")
    public String loginPage(){
        return "fore/login";
    }

    @RequestMapping("forealipay")
    public String forealipay(){
        return "fore/alipay";
    }
}
