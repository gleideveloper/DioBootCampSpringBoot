package br.com.globallabs.springwebmvc.controller;

import br.com.globallabs.springwebmvc.model.Jedi;
import br.com.globallabs.springwebmvc.repository.JediRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;


@Controller
public class JediController {

    @Autowired
    private JediRepository repository;

    @GetMapping("/jedi")
    public ModelAndView jedi() {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jedi");
        modelAndView.addObject("allJedi", repository.findAll());

        return modelAndView;
    }

    @GetMapping("/search")
    public ModelAndView jediSearch(@RequestParam(value = "name") final String name) {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jedi");
        modelAndView.addObject("allJedi", repository.findByNameContainingIgnoreCase(name));
        return modelAndView;
    }

    @GetMapping("/new-jedi")
    public String newJedi(Model model) {
        model.addAttribute("jedi", new Jedi());
        return "new-jedi";
    }

    @PostMapping("/jedi")
    public String createJedi(@Valid @ModelAttribute Jedi jedi, BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            if (jedi.getId() == null) {
                return "new-jedi";
            } else {
                return "edit-jedi";
            }
        }
        repository.save(jedi);
        redirect.addFlashAttribute("message", "Jedi success registration");

        return "redirect:jedi";
    }

    @GetMapping("/jedi/{id}/delete")
    public String deleteJedi(@PathVariable("id") final Long id, RedirectAttributes redirect) {
        final Optional<Jedi> jedi = repository.findById(id);
        repository.delete(jedi.get());
        redirect.addFlashAttribute("message", "Jedi success deleted");

        return "redirect:/jedi";
    }

    @GetMapping("/jedi/{id}/update")
    public String updateJedi(@PathVariable("id") final Long id, Model model) {

        final Optional<Jedi> jedi = repository.findById(id);

        model.addAttribute("jedi", jedi.get());

        return "edit-jedi";
    }

}
