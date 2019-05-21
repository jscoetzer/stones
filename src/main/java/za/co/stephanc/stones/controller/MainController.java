package za.co.stephanc.stones.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.UUID;

@Controller
public class MainController {

    @GetMapping("/")
    public String index(Model model) {
        //model.addAttribute("uuid", UUID.randomUUID().toString());
        model.addAttribute("uuid", "f691fd55-2e44-4238-8eda-76b2ed53f3e2");
        return "index";
    }
}
