package com.jafar.bookorganizer.service;

import com.jafar.bookorganizer.api.entity.CurrencyApi;
import com.jafar.bookorganizer.api.entity.JokeApi;
import com.jafar.bookorganizer.cache.AppCache;
import com.jafar.bookorganizer.constants.PlaceHolders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiService {
    @Autowired
    private AppCache appCache;
    @Autowired
    private RestTemplate restTemplate;
    @Value("${api.ninjas.key}")
    private String apiKey;

    public JokeApi getJoke(){
        String url = appCache.appCache.get("jokeapi");
        return restTemplate.getForObject(url, JokeApi.class);
    }

    public String  convertCurrency(String from, String to, double amount){
        String url = appCache.appCache.get("currencyapi").replace(PlaceHolders.From,from).replace(PlaceHolders.TO,to).replace(PlaceHolders.AMOUNT,String.valueOf(amount));
        CurrencyApi response = restTemplate.getForObject(url, CurrencyApi.class);
        return "The converted amount is "+response.getRates().get(to)+ " for the date "+ response.getDate();
    }
}
