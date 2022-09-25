package visa.prog.visas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import visa.prog.visas.db.Client;
import visa.prog.visas.db.ClientRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

@Controller
public class ClientController {

        @Autowired
        private ClientRepository clientRepository;

        @GetMapping("/client")
        public String clientMain(Model model) {
            Iterable<Client> clients = clientRepository.findAll();
            model.addAttribute("clients", clients);
            return "client-main";
        }
        @GetMapping("/client/add")
        public String clientAdd(Model model) {
            return "client.add";
        }

        @PostMapping("/client/add")
        public String clientAdd(@RequestParam String firstName,
                                @RequestParam String lastName, @RequestParam LocalDate dateBirth,
                                @RequestParam String address, @RequestParam String phone,
                                @RequestParam String email, @RequestParam String documents, Model model) {
            Client client = new Client(firstName, lastName, dateBirth, address, phone, email, documents);
            clientRepository.save(client);
            return "redirect:/client";
        }
        @GetMapping("/client/{id}")
        public String clientDetails(@PathVariable(value = "id") long id, Model model) {
            if (!clientRepository.existsById(id)) {
                return "redirect:/client";
            }
            Optional<Client> client = clientRepository.findById(id);
            ArrayList<Client> res = new ArrayList<>();
            client.ifPresent(res::add);
            model.addAttribute("client", res);
            return "client-details";
        }

        @GetMapping("/client/{id}/edit")
        public String clientEdit(@PathVariable(value = "id") long id, Model model) {
            if (!clientRepository.existsById(id)) {
                return "redirect:/client";
            }
            Optional<Client> client = clientRepository.findById(id);
            ArrayList<Client> res = new ArrayList<>();
            client.ifPresent(res::add);
            model.addAttribute("client", res);
            return "client-edit";
        }
        @PostMapping("/client/{id}/edit")
        public String clientUpdate(@PathVariable(value = "id") long id, @RequestParam String firstName,
                                   @RequestParam String lastName, @RequestParam LocalDate dateBirth,
                                   @RequestParam String address, @RequestParam String phone,
                                   @RequestParam String email, @RequestParam String documents, Model model) {
            Client client = clientRepository.findById(id).orElseThrow(RuntimeException::new);
            client.setFirstName(firstName);
            client.setLastName(lastName);
            client.setDateBirth(dateBirth);
            client.setAddress(address);
            client. setPhone(phone);
            client.setEmail(email);
            client.setDocuments(documents);
            clientRepository.save(client);

            return "redirect:/client";
        }
        @PostMapping("/client/{id}/remove")
        public String clientDelete(@PathVariable(value = "id") long id, Model model) {
            Client client = clientRepository.findById(id).orElseThrow(RuntimeException::new);
            clientRepository.delete(client);

            return "redirect:/client";
        }
}


