package by.itacademy.javaenterprise.seledtsova.controllers;


import by.itacademy.javaenterprise.seledtsova.service.SomeApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
    @Autowired
    private SomeApplicationService applicationService;

    @GetMapping("/")
    public ModelAndView modelAndView() {
        ModelAndView view = new ModelAndView();
        view.setViewName("index");
        view.addObject("controllerVariable", "Model and view usage");
        view.addObject("appName", applicationService.getApplicationName());
        return view;
    }
}
