package visa.prog.visas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import visa.prog.visas.db.Client;
import visa.prog.visas.db.ClientRepository;
import visa.prog.visas.db.Manager;
import visa.prog.visas.db.ManagerRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

@Controller
public class ManagerController {
    @Autowired
    private ManagerRepository managerRepository;

    @GetMapping("/manager")
    public String managerMain(Model model) {
        Iterable<Manager> managers = managerRepository.findAll();
        model.addAttribute("managers", managers);
        return "manager-main";
    }
    @GetMapping("/manager/add")
    public String managerAdd(Model model) {
        return "manager.add";
    }

    @PostMapping("/manager/add")
    public String managerAdd(@RequestParam String firstName,
                            @RequestParam String lastName, @RequestParam LocalDate dateBirth,
                            @RequestParam LocalDate employmentDate, Model model) {
        Manager manager = new Manager(firstName, lastName, dateBirth, employmentDate);
        managerRepository.save(manager);
        return "redirect:/manager";
    }
    @GetMapping("/manager/{id}")
    public String managerDetails(@PathVariable(value = "id") long id, Model model) {
        if (!managerRepository.existsById(id)) {
            return "redirect:/manager";
        }
        Optional<Manager> manager = managerRepository.findById(id);
        ArrayList<Manager> res = new ArrayList<>();
        manager.ifPresent(res::add);
        model.addAttribute("manager", res);
        return "manager-details";
    }

    @GetMapping("/manager/{id}/edit")
    public String managerEdit(@PathVariable(value = "id") long id, Model model) {
        if (!managerRepository.existsById(id)) {
            return "redirect:/manager";
        }
        Optional<Manager> manager = managerRepository.findById(id);
        ArrayList<Manager> res = new ArrayList<>();
        manager.ifPresent(res::add);
        model.addAttribute("manager", res);
        return "manager-edit";
    }
    @PostMapping("/manager/{id}/edit")
    public String managerUpdate(@PathVariable(value = "id") long id, @RequestParam String firstName,
                                @RequestParam String lastName, @RequestParam LocalDate dateBirth,
                                @RequestParam LocalDate employmentDate, Model model) {
        Manager manager = managerRepository.findById(id).orElseThrow(RuntimeException::new);
        manager.setFirstName(firstName);
        manager.setLastName(lastName);
        manager.setDateBirth(dateBirth);
        manager.setEmploymentDate(employmentDate);
        managerRepository.save(manager);

        return "redirect:/manager";
    }
    @PostMapping("/manager/{id}/remove")
    public String managerDelete(@PathVariable(value = "id") long id, Model model) {
        Manager manager = managerRepository.findById(id).orElseThrow(RuntimeException::new);
        managerRepository.delete(manager);

        return "redirect:/manager";
    }
}
