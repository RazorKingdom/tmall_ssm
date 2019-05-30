package com.ssm.tmall.controller.back;

import com.ssm.tmall.pojo.Order;
import com.ssm.tmall.service.OrderService;
import com.ssm.tmall.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("")
public class OrderController {
    @Autowired
    OrderService orderService;

    @RequestMapping(value="admin_order_list",method = RequestMethod.GET)
    public String list(Page page, Model model){
        List<Order> orders=orderService.list(page);
        int total=orderService.total();
        page.setTotal(total);
        model.addAttribute("page",page);
        model.addAttribute("os",orders);
        return "admin/listOrder";
    }

    @RequestMapping(value="admin_order_delivery")
    public String delivery(int id){
        Order order=orderService.get(id);
        order.setDeliveryDate(new Date());
        order.setStatus(OrderService.waitConfirm);
        orderService.update(order);
        return "redirect:admin_order_list";
    }
}
