package com.senla.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class MainMapper extends ModelMapper {

    @Bean
    public MainMapper modelMapper() {
        MainMapper mapper = new MainMapper();
        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT) //строгая стратегия соответствия
                .setFieldMatchingEnabled(true)                  //сопоставление полей
                .setSkipNullEnabled(true)   //пропуск нулловых полей
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);   //приватный уровень доступа к полям
        return mapper;
    }

}
