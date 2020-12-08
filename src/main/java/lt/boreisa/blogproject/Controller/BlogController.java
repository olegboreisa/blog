package lt.boreisa.blogproject.Controller;

import lombok.extern.slf4j.Slf4j;
import lt.boreisa.blogproject.Model.BlogModel;
import lt.boreisa.blogproject.Repository.BlogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@Slf4j
public class BlogController {

    private final BlogRepo blogRepo;

    @Autowired
    public BlogController(BlogRepo blogRepo) {
        this.blogRepo = blogRepo;
    }

    @RequestMapping(path = {"", "/", "/main"})
    public String openMain () {
        return "blog/main";
    }


    // [ADD BLOG]
    @RequestMapping(path = "/add-blog", method = RequestMethod.GET)
    public String goToAddBlog (@ModelAttribute BlogModel blogModel) {
        log.info("blogModel {}", blogModel);
        return "blog/add-blog";
    }
    @RequestMapping(path = "/getMain", method = RequestMethod.POST)
    public String addBlog (@Valid @ModelAttribute BlogModel blogModel, BindingResult bindingResult, @RequestParam("action") String action) {
        log.info("blogModel {}", blogModel);
        if (action.equals("post")) {
            if (bindingResult.hasErrors()) {
                return "blog/add-blog";
            }
            blogRepo.save(blogModel);
            return "redirect:/main";
        }
        else if (action.equals("return")) {
            return "redirect:/main";
        }
        return null;
    }

    // [GET A LIST OF BLOGS]
    @RequestMapping(path = "/review-blog", method = RequestMethod.GET)
    public String goToListOfBlogs (Model model) {
        log.info ("info {}", blogRepo.findAll());
        model.addAttribute("allBlogs", blogRepo.findAll());
        return "blog/list";
    }

    // [EDIT BLOG]
    @RequestMapping(path = "/edit/{id}", method = RequestMethod.GET)
    public String editBlog (@PathVariable Long id, Model model) {
        BlogModel blogModel = blogRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user id: " + id));
        model.addAttribute("blogUpdate", blogModel);
        return "blog/edit-blog";
    }

    @RequestMapping(path = "/edited", method = RequestMethod.POST)
    public String postEditBlog (@Valid @ModelAttribute BlogModel blogModel, BindingResult bindingResult, @RequestParam("action") String action) {
        log.info("blogModelEdit {}", blogModel);
        if (action.equals("update")) {
            if (bindingResult.hasErrors()) {
                return "blog/add-blog";
            }
            blogRepo.save(blogModel);
            return "redirect:/main";
        }
        else if (action.equals("return")) {
            return "redirect:/main";
        }
        return null;
    }

    // [DELETE BLOG]
    @RequestMapping(path = "/delete/{id}", method = RequestMethod.GET)
    public String editBlog (@PathVariable Long id) {
        blogRepo.deleteById(id);
        return "blog/list";
    }
}
