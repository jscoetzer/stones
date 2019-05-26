package za.co.stephanc.stones.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import za.co.stephanc.stones.model.game.Mancala;

@Controller
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/new")
    public String newGame(Model model) {
        //model.addAttribute("uuid", UUID.randomUUID().toString());
        model.addAttribute("uuid", "f691fd55-2e44-4238-8eda-76b2ed53f3e2");
        model.addAttribute("myPlayer", "A");
        return "game";
    }

    @GetMapping("/join")
    public ModelAndView joinGame(@RequestParam String sessionId) {
        logger.info("Joining game session " + sessionId);
        ModelAndView model = new ModelAndView();
        model.addObject("uuid", "f691fd55-2e44-4238-8eda-76b2ed53f3e2");
        model.addObject("myPlayer", "B");
        model.setViewName("game");
        return model;
    }
}
