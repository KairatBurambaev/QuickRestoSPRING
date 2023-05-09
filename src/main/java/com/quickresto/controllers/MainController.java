package com.quickresto.controllers;

import com.quickresto.project.Calculate;
import com.quickresto.project.Table;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Arrays;
import java.util.Map;

@Controller
public class MainController {
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model) {
        Table empty = new Table();
        model.addAttribute("table", empty);
        return "table";
    }
    @RequestMapping(path = "/", method = RequestMethod.POST)
    public String calc(Model model, HttpServletRequest req) {

        Table input = new Table();
        Map<String, String[]> parameterMap = req.getParameterMap();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            System.out.println(entry.getKey() + " = " + Arrays.toString(entry.getValue()));

        }
        for (int i = 0; i < parameterMap.size(); i++) {
            for (int k = 0; k < parameterMap.values().size(); k++) {
                    input.setData(i, k, parameterMap.get(String.valueOf(i))[k]);
                }
            }
        Table result = Calculate.calculate(input);
        model.addAttribute("table", result);
        return "table";
    }
}