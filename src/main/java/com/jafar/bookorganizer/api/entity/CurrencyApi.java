package com.jafar.bookorganizer.api.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
public class CurrencyApi {
    private String base;
    private String date;
    private Map<String , Double> rates;
}
