package visa.prog.visas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import visa.prog.visas.db.Blog;
import visa.prog.visas.db.BlogRepository;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class BlogController {

    @Autowired
    private BlogRepository blogRepository;

    @GetMapping("/blog")
    public String blogMain(Model model) {
      Iterable<Blog> blogs = blogRepository.findAll();
      model.addAttribute("blogs", blogs);
        return "blog-main";
    }
    @GetMapping("/blog/add")
    public String blogAdd(Model model) {
        return "blog.add";
    }

    @PostMapping("/blog/add")
    public String blogAdd(@RequestParam String title, @RequestParam String anons, @RequestParam String full_text, Model model) {
        Blog blog = new Blog(title, anons, full_text);
        blogRepository.save(blog);
        return "redirect:/blog";
    }
    @GetMapping("/blog/{id}")
    public String blogDetails(@PathVariable(value = "id") long id, Model model) {
        if (!blogRepository.existsById(id)) {
            return "redirect:/blog";
        }
        Optional<Blog> blog = blogRepository.findById(id);
        ArrayList<Blog> res = new ArrayList<>();
        blog.ifPresent(res::add);
        model.addAttribute("blog", res);
        return "blog-details";
    }

    @GetMapping("/blog/{id}/edit")
    public String blogEdit(@PathVariable(value = "id") long id, Model model) {
        if (!blogRepository.existsById(id)) {
            return "redirect:/blog";
        }
        Optional<Blog> blog = blogRepository.findById(id);
        ArrayList<Blog> res = new ArrayList<>();
        blog.ifPresent(res::add);
        model.addAttribute("blog", res);
        return "blog-edit";
    }
    @PostMapping("/blog/{id}/edit")
    public String blogUpdate(@PathVariable(value = "id") long id, @RequestParam String title, @RequestParam String anons, @RequestParam String full_text, Model model) {
        Blog blog = blogRepository.findById(id).orElseThrow(RuntimeException::new);
        blog.setTitle(title);
        blog.setTitle(anons);
        blog.setTitle(full_text);
        blogRepository.save(blog);

        return "redirect:/blog";
    }
    @PostMapping("/blog/{id}/remove")
    public String blogDelete(@PathVariable(value = "id") long id, Model model) {
        Blog blog = blogRepository.findById(id).orElseThrow(RuntimeException::new);
        blogRepository.delete(blog);

        return "redirect:/blog";
    }
}


