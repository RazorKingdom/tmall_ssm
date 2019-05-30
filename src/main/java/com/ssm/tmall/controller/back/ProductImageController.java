package com.ssm.tmall.controller.back;

import com.ssm.tmall.pojo.Product;
import com.ssm.tmall.pojo.ProductImage;
import com.ssm.tmall.service.CategoryService;
import com.ssm.tmall.service.ProductImageService;
import com.ssm.tmall.service.ProductService;
import com.ssm.tmall.util.ImageUtil;
import com.ssm.tmall.util.UploadedImageFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class ProductImageController {
    @Autowired
    ProductImageService productImageService;
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;

    @RequestMapping(value = "admin_productImage_list", method = RequestMethod.GET)
    public String list(Integer pid, Model model) {
        List<ProductImage> pds = productImageService.list(pid, ProductImageService.type_detail);
        List<ProductImage> pss = productImageService.list(pid, ProductImageService.type_single);
        Product product = productService.get(pid);
        model.addAttribute("p", product);
        model.addAttribute("pisSingle", pss);
        model.addAttribute("pisDetail", pds);
        return "admin/listProductImage";
    }

    @RequestMapping(value = "admin_productImage_delete")
    public String delete(Integer id, HttpSession session) {
        File imageFolder;
        File imageFolder_small = null;
        File imageFolder_middle = null;
        int pid=productImageService.get(id).getPid();

        if (productImageService.get(id).getType().equals(ProductImageService.type_single)) {
            imageFolder = new File(session.getServletContext().getRealPath("img/productSingle"));
            imageFolder_middle = new File(session.getServletContext().getRealPath("img/productSingle_middle"));
            imageFolder_small = new File(session.getServletContext().getRealPath("img/productSingle_small"));
        } else {
            imageFolder = new File(session.getServletContext().getRealPath("img/productDetail"));
        }
        File file = new File(imageFolder, id + ".jpg");
        file.delete();
        if (productImageService.get(id).getType().equals(ProductImageService.type_single)) {
            File file_middle = new File(imageFolder_middle, id + ".jpg");
            File file_small = new File(imageFolder_small, id + ".jpg");
            file_middle.delete();
            file_small.delete();
        }
        productImageService.delete(id);
        return "redirect:admin_productImage_list?pid=" + pid;
    }

    @RequestMapping("admin_productImage_add")
    public String add(ProductImage productImage, HttpSession session, UploadedImageFile uploadedImageFile) {
        productImageService.add(productImage);
        String fileName = productImage.getId() + ".jpg";
        String imageFolder;
        String imageFolder_small = null;
        String imageFolder_middle = null;

        if (ProductImageService.type_single.equals(productImage.getType())) {
            imageFolder = session.getServletContext().getRealPath("img/productSingle");
            imageFolder_small = session.getServletContext().getRealPath("img/productSingle_small");
            imageFolder_middle = session.getServletContext().getRealPath("img/productSingle_middle");
        } else {
            imageFolder = session.getServletContext().getRealPath("img/productDetail");
        }

        File f = new File(imageFolder, fileName);
        if (!f.getParentFile().exists())
            f.getParentFile().mkdirs();
        try {
            ImageUtil.writeImageWithJpg(uploadedImageFile.getImage(), f);

            if (ProductImageService.type_single.equals(productImage.getType())) {
                File f_small = new File(imageFolder_small, fileName);
                File f_middle = new File(imageFolder_middle, fileName);

                ImageUtil.resizeImage(f, 56, 56, f_small);
                ImageUtil.resizeImage(f, 217, 190, f_middle);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:admin_productImage_list?pid=" + productImage.getPid();
    }
}
