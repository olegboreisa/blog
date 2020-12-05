package lt.boreisa.blogproject.Controller;

import lombok.extern.slf4j.Slf4j;
import lt.boreisa.blogproject.Model.BlogModel;
import lt.boreisa.blogproject.Repository.BlogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

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
}
