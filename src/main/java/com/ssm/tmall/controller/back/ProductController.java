package com.ssm.tmall.controller.back;

import com.ssm.tmall.pojo.Category;
import com.ssm.tmall.pojo.Product;
import com.ssm.tmall.service.CategoryService;
import com.ssm.tmall.service.ProductService;
import com.ssm.tmall.service.PropertyValueService;
import com.ssm.tmall.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    PropertyValueService propertyValueService;

    @RequestMapping(value = "admin_product_list")
    public String list(Integer cid, Page page, Model model){
        List<Product> ps=productService.list(cid,page);
        int total=productService.total(cid);
        Category category=categoryService.get(cid);
        page.setTotal(total);
        page.setParam("&cid="+cid);
        model.addAttribute("page",page);
        model.addAttribute("ps",ps);
        model.addAttribute("c",category);
        return "admin/listProduct";
    }

    @RequestMapping(value = "admin_product_add")
    public String add(Product product){
        productService.add(product);
        return "redirect:admin_product_list?cid="+product.getCid();
    }

    @RequestMapping(value = "admin_product_delete")
    public String delete(int id){
        int cid=productService.get(id).getCid();
        productService.delete(id);
        return "redirect:admin_product_list?cid="+cid;
    }

    @RequestMapping(value = "admin_product_edit")
    public String edit(int id,Model model){
        Product product=productService.get(id);
        model.addAttribute("p",product);
        return "admin/editProduct";
    }

    @RequestMapping(value="admin_product_update")
    public String update(Product product){
        productService.update(product);
        return "redirect:admin_product_list?cid="+product.getCid();
    }
}
