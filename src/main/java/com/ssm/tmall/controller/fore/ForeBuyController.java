package com.ssm.tmall.controller.fore;

import com.ssm.tmall.pojo.OrderItem;
import com.ssm.tmall.pojo.User;
import com.ssm.tmall.service.OrderItemService;
import com.ssm.tmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("")
public class ForeBuyController {
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    ProductService productService;

    @RequestMapping("forebuyone")
    public String buyone(@RequestParam("pid") Integer pid,@RequestParam("num") Integer num, HttpSession session){
        User user=(User)session.getAttribute("user");
        List<OrderItem> orderItemList=orderItemService.list(user);
        OrderItem oi;
        boolean flag=false;
        int oiid=0;
        for(OrderItem orderItem:orderItemList)
            if(orderItem.getPid().intValue()==pid.intValue()){
                orderItem.setNumber(orderItem.getNumber()+num);
                orderItemService.update(orderItem);
                oiid=orderItem.getId();
                flag=true;
                break;
            }
        if(flag!=true){
           oi=new OrderItem();
           oi.setNumber(num);
           oi.setPid(pid);
           oi.setUid(user.getId());
           orderItemService.add(oi);
           oiid=oi.getId();
        }
        return "redirect:forebuy?oiid="+oiid;
    }

    @RequestMapping("forebuy")
    public String buy(@RequestParam("oiid") String[] oiid, Model model,HttpSession session){
        List<OrderItem> orderItems=new ArrayList<>();
        int total=0;
        for(String str:oiid){
            int id=Integer.parseInt(str);
            OrderItem orderItem=orderItemService.get(id);
            orderItems.add(orderItem);
            total+=orderItem.getNumber()*orderItem.getProduct().getPromotePrice();
        }
        model.addAttribute("total",total);
        session.setAttribute("ois",orderItems);
        return "fore/buy";
    }
}