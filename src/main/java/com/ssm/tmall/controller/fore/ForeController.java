package com.ssm.tmall.controller.fore;

import com.ssm.tmall.pojo.Category;
import com.ssm.tmall.pojo.User;
import com.ssm.tmall.service.CategoryService;
import com.ssm.tmall.service.ProductService;
import com.ssm.tmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("")
public class ForeController {
    @Autowired
    UserService userService;
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;

    @RequestMapping("forehome")
    public String forehome(Model model) {
        List<Category> cs = categoryService.listAll();
        productService.fill(cs);
        productService.fillByRow(cs);
        model.addAttribute("cs", cs);
        return "fore/home";
    }

    @RequestMapping("foreregister")
    public String register(User user, Model model) {
        user.setName(HtmlUtils.htmlEscape(user.getName()));
        if (userService.isExist(user.getName())) {
            model.addAttribute("msg", "用户名已经被使用,不能使用");
            model.addAttribute("user", null);
            return "fore/register";
        }
        userService.add(user);
        return "redirect:registerSuccessPage";
    }

    @RequestMapping("forelogin")
    public String login(@RequestParam("name") String name, @RequestParam("password")
            String password, HttpSession httpSession, Model model) {
        HtmlUtils.htmlEscape(name);
        User user = userService.loginVeri(name, password);
        if (user == null) {
            model.addAttribute("msg", "账号密码错误");
            return "fore/login";
        }
        httpSession.setAttribute("user", user);
        return "redirect:forehome";
    }

    @RequestMapping("forelogout")
    public String logout(HttpSession httpSession) {
        httpSession.removeAttribute("user");
        return "redirect:forehome";
    }

    @RequestMapping("forecheckLogin")
    @ResponseBody
    public String checklogin(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null)
            return "fail";
        return "success";
    }

    @RequestMapping("foreloginAjax")
    @ResponseBody
    public String loginAjax(@RequestParam("name") String name, @RequestParam("password") String password, HttpSession session
            ) {
        User user=userService.loginVeri(name,password);
        if(user==null)
            return "fail";
        session.setAttribute("user",user);
        return "success";
    }
}
