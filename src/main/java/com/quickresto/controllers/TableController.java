//package com.quickresto.controllers;
//
//import com.quickresto.models.Table;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//
//@Controller
//public class TableController {
//
//    @Autowired
//    private MyRepository repository;
//
//    @PostMapping("/submit")
//    public String submitForm(@ModelAttribute Table form) {
//        repository.save(form);
//        return "success";
//    }
//}
//
