package lt.boreisa.blogproject.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BlogController {

    @RequestMapping({"", "/", "/main"})
    public String openMain () {
        return "blog/main";
    }

    @RequestMapping("/add-blog")
    public String goToAddBlog () {
        return "blog/add-blog";
    }
}
