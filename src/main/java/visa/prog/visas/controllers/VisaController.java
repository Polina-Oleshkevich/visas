package visa.prog.visas.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class VisaController {
    @GetMapping("/visa-type")
    public String visaType(Model model) {
        return "visa-type";
    }
}
