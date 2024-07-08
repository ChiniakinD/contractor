package org.chiniakin.config;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * Конфигурационный класс для настройки ModelMapper.
 * @author ChiniakinD
 */
@Configuration
public class ModelMapperConfig {

    @Bean
    @Primary
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    /**
     * Создает modelMapper для слияния объектов, который соединяет только не null поля.
     * @return modelMapper.
     */
    @Bean("mergeMapper")
    public ModelMapper mergeMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setSkipNullEnabled(true)
                .setPropertyCondition(Conditions.isNotNull());
        return modelMapper;
    }

}

