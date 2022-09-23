package visa.prog.visas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import visa.prog.visas.db.Detail;
import visa.prog.visas.db.DetailRepository;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class DetailHomeController {

    @Autowired
    private DetailRepository detailRepository;

    @GetMapping("/detail")
    public String detailMain(Model model) {
        Iterable<Detail> details = detailRepository.findAll();
        model.addAttribute("details", details);
        return "detail-main";
    }
    @GetMapping("/detail/add")
    public String detailAdd(Model model) {
        return "detail.add";
    }

    @PostMapping("/detail/add")
    public String detailAdd(@RequestParam String title, Model model) {
        Detail detail = new Detail(title);
        detailRepository.save(detail);
        return "redirect:/detail";
    }
    @GetMapping("/detail/{id}")
    public String detailDetails(@PathVariable(value = "id") long id, Model model) {
        if (!detailRepository.existsById(id)) {
            return "redirect:/detail";
        }
        Optional<Detail> detail = detailRepository.findById(id);
        ArrayList<Detail> res = new ArrayList<>();
        detail.ifPresent(res::add);
        model.addAttribute("detail", res);
        return "detail-details";
    }

    @GetMapping("/detail/{id}/edit")
    public String detailEdit(@PathVariable(value = "id") long id, Model model) {
        if (!detailRepository.existsById(id)) {
            return "redirect:/detail";
        }
        Optional<Detail> detail = detailRepository.findById(id);
        ArrayList<Detail> res = new ArrayList<>();
        detail.ifPresent(res::add);
        model.addAttribute("detail", res);
        return "detail-edit";
    }
    @PostMapping("/detail/{id}/edit")
    public String detailUpdate(@PathVariable(value = "id") long id, @RequestParam String title, Model model) {
        Detail detail = detailRepository.findById(id).orElseThrow(RuntimeException::new);
        detail.setTitle(title);
        detailRepository.save(detail);

        return "redirect:/detail";
    }
    @PostMapping("/detail/{id}/remove")
    public String detailDelete(@PathVariable(value = "id") long id, Model model) {
        Detail detail = detailRepository.findById(id).orElseThrow(RuntimeException::new);
        detailRepository.delete(detail);

        return "redirect:/detail";
    }
}


