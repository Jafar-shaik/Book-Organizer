package com.jafar.bookorganizer.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "book_api_config")
@Data
public class ConfigApiEntity {
    private String key;
    private String value;
}
