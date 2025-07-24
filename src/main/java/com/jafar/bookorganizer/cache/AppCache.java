package com.jafar.bookorganizer.cache;

import com.jafar.bookorganizer.entity.ConfigApiEntity;
import com.jafar.bookorganizer.repository.ConfigApiEntityRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class AppCache {
    @Autowired
    private ConfigApiEntityRepository configApiEntityRepository;

    public Map<String , String > appCache;

    @PostConstruct
    public void init(){
        appCache=new HashMap<>();
        List<ConfigApiEntity> all = configApiEntityRepository.findAll();
        for (ConfigApiEntity configApiEntity : all) {
            appCache.put(configApiEntity.getKey(), configApiEntity.getValue());
        }
    }

}
