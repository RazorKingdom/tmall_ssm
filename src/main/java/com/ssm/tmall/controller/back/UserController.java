package com.ssm.tmall.controller.back;

import com.ssm.tmall.pojo.User;
import com.ssm.tmall.service.UserService;
import com.ssm.tmall.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "admin_user_list", method = RequestMethod.GET)
    public String list(Page page, Model model) {
        List<User> us = userService.list(page);
        int total=userService.total();
        page.setTotal(total);
        for(User user:us)
            user.setPassword(null);
        model.addAttribute("us",us);
        model.addAttribute("page",page);
        return "admin/listUser";
    }
}
