package com.ssm.tmall.controller.back;

import com.ssm.tmall.pojo.Product;
import com.ssm.tmall.pojo.PropertyValue;
import com.ssm.tmall.service.CategoryService;
import com.ssm.tmall.service.ProductService;
import com.ssm.tmall.service.PropertyService;
import com.ssm.tmall.service.PropertyValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("")
public class PropertyValueController {
    @Autowired
    PropertyValueService propertyValueService;
    @Autowired
    PropertyService propertyService;
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;

    @RequestMapping(value = "admin_propertyValue_edit")
    public String edit(Model model, int pid) {
        Product product = productService.get(pid);
        propertyValueService.init(product);
        List<PropertyValue> pvs = propertyValueService.list(pid);
        model.addAttribute("p",product);
        model.addAttribute("pvs",pvs);
        return "admin/editPropertyValue";
    }

    @RequestMapping(value="admin_propertyValue_update")
    @ResponseBody
    public String update(PropertyValue propertyValue){
        propertyValueService.update(propertyValue);
        return "success";
    }
}
