package visa.prog.visas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import visa.prog.visas.db.Blog;
import visa.prog.visas.db.BlogRepository;

@Controller
public class BlogController {

    @Autowired
    private BlogRepository blogRepository;

    @GetMapping("/blog")
    public String blog(Model model) {
      Iterable<Blog> blogs = blogRepository.findAll();
      model.addAttribute("blogs", blogs);
        return "blog-main";
    }
    @GetMapping("/blog/add")
    public String blogAdd(Model model) {
        return "blog.add";
    }

    @PostMapping("/blog/add")
    public String blogPostAdd(@RequestParam String title, @RequestParam String anons,@RequestParam String full_text, Model model) {
        Blog blog = new Blog(title, anons, full_text);
        blogRepository.save(blog);
        return "redirect:/";
    }
}
