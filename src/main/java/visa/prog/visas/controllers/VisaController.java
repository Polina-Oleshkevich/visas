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
import visa.prog.visas.db.Visa;
import visa.prog.visas.db.VisaRepository;

import java.util.ArrayList;
import java.util.Optional;

@Controller

public class VisaController {

    @Autowired
    private VisaRepository visaRepository;

    @GetMapping("/visa")
    public String visaMain(Model model) {
        Iterable<Visa> visas = visaRepository.findAll();
        model.addAttribute("visas", visas);
        return "visa-main";
    }
    @GetMapping("/visa/add")
    public String visaAdd(Model model) {
        return "visa.add";
    }

    @PostMapping("/visa/add")
    public String visaAdd(@RequestParam String visaType, @RequestParam Integer visaTerm,
                            @RequestParam String purpose, @RequestParam String country,
                          @RequestParam Integer price, Model model) {
        Visa visa = new Visa(visaType, visaTerm, purpose, country, price);
        visaRepository.save(visa);
        return "redirect:/visa";
    }
    @GetMapping("/visa/{id}")
    public String visaDetails(@PathVariable(value = "id") long id, Model model) {
        if (!visaRepository.existsById(id)) {
            return "redirect:/visa";
        }
        Optional<Visa> visa = visaRepository.findById(id);
        ArrayList<Visa> res = new ArrayList<>();
        visa.ifPresent(res::add);
        model.addAttribute("visa", res);
        return "visa-details";
    }

    @GetMapping("/visa/{id}/edit")
    public String visaEdit(@PathVariable(value = "id") long id, Model model) {
        if (!visaRepository.existsById(id)) {
            return "redirect:/visa";
        }
        Optional<Visa> visa = visaRepository.findById(id);
        ArrayList<Visa> res = new ArrayList<>();
        visa.ifPresent(res::add);
        model.addAttribute("visa", res);
        return "visa-edit";
    }
    @PostMapping("/visa/{id}/edit")
    public String visaUpdate(@PathVariable(value = "id") long id,
                             @RequestParam String visaType, @RequestParam Integer visaTerm,
                             @RequestParam String purpose, @RequestParam String country,
                             @RequestParam Integer price, Model model) {
        Visa visa = visaRepository.findById(id).orElseThrow(RuntimeException::new);
        visa.setVisaType(visaType);
        visa. setVisaTerm(visaTerm);
        visa. setPurpose(purpose);
        visa.setCountry(country);
        visa.setPrice(price);
        visaRepository.save(visa);

        return "redirect:/visa";
    }
    @PostMapping("/visa/{id}/remove")
    public String visaDelete(@PathVariable(value = "id") long id, Model model) {
        Visa visa = visaRepository.findById(id).orElseThrow(RuntimeException::new);
        visaRepository.delete(visa);

        return "redirect:/visa";
    }
}