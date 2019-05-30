package com.ssm.tmall.controller.back;

import com.ssm.tmall.pojo.Category;
import com.ssm.tmall.service.CategoryService;
import com.ssm.tmall.util.ImageUtil;
import com.ssm.tmall.util.Page;
import com.ssm.tmall.util.UploadedImageFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @RequestMapping(value = "admin_category_list")
    public String list(Page page, Model model) {
        List<Category> cs = categoryService.list(page);
        int total = categoryService.total();
        page.setTotal(total);
        model.addAttribute("cs", cs);
        model.addAttribute("page", page);
        return "admin/listCategory";
    }

    @RequestMapping(value = "admin_category_add", method = RequestMethod.POST)
    public String add(Category category, HttpSession session, UploadedImageFile uploadedImageFile)
            throws IOException {
        categoryService.add(category);
        File imagefolder = new File(session.getServletContext().getRealPath("img/category"));
        File file = new File(imagefolder, category.getId() + ".jpg");
        if (!file.getParentFile().exists())
            file.getParentFile().mkdirs();
        uploadedImageFile.getImage().transferTo(file);
        BufferedImage bufferedImage = ImageUtil.change2jpg(file);
        ImageIO.write(bufferedImage, "jpg", file);
        return "redirect:/admin_category_list";
    }

    @RequestMapping(value = "admin_category_delete", method = RequestMethod.GET)
    public String delete(Category category, HttpSession session) {
        categoryService.delete(category);
        File folder = new File(session.getServletContext().getRealPath("img/category"));
        File file = new File(folder, category.getId() + ".jpg");
        if (file.exists())
            file.delete();
        return "redirect:/admin_category_list";
    }

    @RequestMapping(value = "admin_category_edit")
    public String edit(int id, Model model) {
        Category category = categoryService.get(id);
        model.addAttribute("c", category);
        return "admin/editCategory";
    }

    @RequestMapping(value = "admin_category_update", method = RequestMethod.POST)
    public String update(Category category, HttpSession session, UploadedImageFile uploadedImageFile)
            throws IOException {
        categoryService.update(category);
        MultipartFile img = uploadedImageFile.getImage();
        if (img != null && !img.isEmpty()) {
            File imagefolder = new File(session.getServletContext().getRealPath("img/category"));
            if (!imagefolder.exists())
                imagefolder.mkdirs();
            File file = new File(imagefolder, category.getId() + ".jpg");
            img.transferTo(file);
            BufferedImage bufferedImage = ImageUtil.change2jpg(file);
            ImageIO.write(bufferedImage, "jpg", file);
        }
        return "redirect:/admin_category_list";
    }
}
