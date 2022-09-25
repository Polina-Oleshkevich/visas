package visa.prog.visas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import visa.prog.visas.db.Center;
import visa.prog.visas.db.CenterRepository;
import visa.prog.visas.db.Client;
import visa.prog.visas.db.ClientRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

@Controller
public class CenterController {

    @Autowired
    private CenterRepository centerRepository;

    @GetMapping("/center")
    public String centerMain(Model model) {
        Iterable<Center> centers = centerRepository.findAll();
        model.addAttribute("centers", centers);
        return "center-main";
    }
    @GetMapping("/center/add")
    public String centerAdd(Model model) {
        return "center.add";
    }

    @PostMapping("/center/add")
    public String centerAdd(@RequestParam String address, @RequestParam String phone,
                            @RequestParam String email, @RequestParam String documents, Model model) {
        Center center = new Center(address, phone, email, documents);
        centerRepository.save(center);
        return "redirect:/center";
    }
    @GetMapping("/center/{id}")
    public String centerDetails(@PathVariable(value = "id") long id, Model model) {
        if (!centerRepository.existsById(id)) {
            return "redirect:/center";
        }
        Optional<Center> center = centerRepository.findById(id);
        ArrayList<Center> res = new ArrayList<>();
        center.ifPresent(res::add);
        model.addAttribute("center", res);
        return "center-details";
    }

    @GetMapping("/center/{id}/edit")
    public String centerEdit(@PathVariable(value = "id") long id, Model model) {
        if (!centerRepository.existsById(id)) {
            return "redirect:/client";
        }
        Optional<Center> center = centerRepository.findById(id);
        ArrayList<Center> res = new ArrayList<>();
        center.ifPresent(res::add);
        model.addAttribute("center", res);
        return "center-edit";
    }
    @PostMapping("/center/{id}/edit")
    public String centerUpdate(@PathVariable(value = "id") long id,
                               @RequestParam String address, @RequestParam String phone,
                               @RequestParam String email, @RequestParam String documents, Model model) {
        Center center = centerRepository.findById(id).orElseThrow(RuntimeException::new);
        center.setAddress(address);
        center. setPhone(phone);
        center.setEmail(email);
        center.setDocuments(documents);
        centerRepository.save(center);

        return "redirect:/center";
    }
    @PostMapping("/center/{id}/remove")
    public String centerDelete(@PathVariable(value = "id") long id, Model model) {
        Center center = centerRepository.findById(id).orElseThrow(RuntimeException::new);
        centerRepository.delete(center);

        return "redirect:/center";
    }
}

