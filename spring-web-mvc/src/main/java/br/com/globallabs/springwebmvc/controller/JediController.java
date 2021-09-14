package br.com.globallabs.springwebmvc.controller;

import br.com.globallabs.springwebmvc.model.Jedi;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class JediController {
    @GetMapping("/jedi")
    public ModelAndView jedi(){
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jedi");

        final Jedi luke = new Jedi("Luke", "Skywalker");
        modelAndView.addObject("allJedi", List.of(luke));

        return modelAndView;
    }

    @GetMapping("/new-jedi")
    public ModelAndView newJedi(){
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("new-jedi");

        modelAndView.addObject("jedi", new Jedi());

        return modelAndView;
    }
}
