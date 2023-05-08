package com.quickresto.controllers;

import com.quickresto.project.Calculate;
import com.quickresto.project.Table;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model) {
        Table input = new Table();
        Table result = Calculate.calculate(input);
//        String result1 = result.getData(1, 'A');

        model.addAttribute("title", result);

        return "home";
    }

}