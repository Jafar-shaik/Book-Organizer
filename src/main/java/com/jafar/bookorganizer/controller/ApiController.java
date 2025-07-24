package com.jafar.bookorganizer.controller;

import com.jafar.bookorganizer.api.entity.JokeApi;
import com.jafar.bookorganizer.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired
    private ApiService apiService;

    @GetMapping("/joke")
    public String getJoke(){
        JokeApi joke = apiService.getJoke();
        String haha = joke.getSetup()+"\n"+joke.getPunchline()+"😂😂";
        return haha;
    }

    @GetMapping("/currency")
    public String convertCurrency(@RequestParam String from, @RequestParam String to, @RequestParam double amount){
        String  result = apiService.convertCurrency(from, to, amount);
        return result;
    }
}
