package com.ssm.tmall.controller.back;

import com.ssm.tmall.pojo.Category;
import com.ssm.tmall.pojo.Property;
import com.ssm.tmall.service.CategoryService;
import com.ssm.tmall.service.PropertyService;
import com.ssm.tmall.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("")
public class PropertyController {
    @Autowired
    PropertyService propertyService;
    @Autowired
    CategoryService categoryService;

    @RequestMapping(value = "admin_property_list", method = RequestMethod.GET)
    public String list(Integer cid,Model model,Page page) {
        List<Property> ps = propertyService.list(cid, page);
        Category category = categoryService.get(cid);
        int total = propertyService.total(cid);
        page.setTotal(total);
        page.setParam("&cid=" + cid);
        model.addAttribute("ps", ps);
        model.addAttribute("c", category);
        model.addAttribute("page", page);
        return "admin/listProperty";
    }

    @RequestMapping(value = "admin_property_add",method = RequestMethod.POST)
    public String add(Property property){
        propertyService.add(property);
        return "redirect:admin_property_list?cid="+property.getCid();
    }

    @RequestMapping(value="admin_property_delete")
    public  String delete(Integer id){
        int cid=propertyService.get(id).getCid();
        propertyService.delete(id);
        return "redirect:admin_property_list?cid="+cid;
    }

    @RequestMapping(value = "admin_property_edit")
    public String edit(Model model,Integer id){
        Property property=propertyService.get(id);
        Category category=categoryService.get(property.getCid());
        property.setCategory(category);
        model.addAttribute("p",property);
        return "admin/editProperty";
    }

    @RequestMapping(value = "admin_property_update",method = RequestMethod.POST)
    public String update(Property property){
        propertyService.update(property);
        return "redirect:admin_property_list?cid="+property.getCid();
    }
}
