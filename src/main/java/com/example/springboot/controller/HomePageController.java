package com.example.springboot.controller;

import com.example.springboot.service.sell.IGoodService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

@Controller
public class HomePageController {

    @Resource
    private IGoodService goodService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Model model) {
        model.addAttribute("goods", goodService.findGoodsByPage(1,5));
        return "home";
    }
}
