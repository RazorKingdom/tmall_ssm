package com.ssm.tmall.controller.fore;

import com.ssm.tmall.pojo.Product;
import com.ssm.tmall.pojo.PropertyValue;
import com.ssm.tmall.pojo.Review;
import com.ssm.tmall.service.CategoryService;
import com.ssm.tmall.service.ProductService;
import com.ssm.tmall.service.PropertyValueService;
import com.ssm.tmall.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("")
public class ForeProdutController {
    @Autowired
    ProductService productService;
    @Autowired
    PropertyValueService propertyValueService;
    @Autowired
    ReviewService reviewService;
    @Autowired
    CategoryService categoryService;

    @RequestMapping("foreproduct")
    public String product(@RequestParam("pid") int pid, Model model) {
        Product product = productService.get(pid);
        List<Review> reviews = reviewService.list(product);
        List<PropertyValue> propertyValues = propertyValueService.list(pid);
        productService.setSaleAndReviewNumber(product);
        productService.setSingleAndDetailPic(product);

        model.addAttribute("p", product);
        model.addAttribute("pvs", propertyValues);
        model.addAttribute("reviews", reviews);
        return "fore/product";
    }

    @RequestMapping("foresearch")
    public String search(String keyword, Model model) {
        List<Product> products = productService.search(keyword);
        productService.setSaleAndReviewNumber(products);

        model.addAttribute("ps", products);
        return "fore/searchResult";
    }
}
