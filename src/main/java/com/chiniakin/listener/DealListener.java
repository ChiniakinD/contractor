package com.chiniakin.listener;

import com.chiniakin.service.interfaces.ContractorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class DealListener {

    private final ObjectMapper objectMapper;

    private final ContractorService contractorService;

    @RabbitListener(queues = {"deals_contractor_queue", "deals_contractor_dead_queue"})
    public void deal(String message) {
        RabbitTask rabbitTask = getMessage(message);
        contractorService.setMainBorrower(rabbitTask.getContractorId(), rabbitTask.getActiveMainBorrower());
    }

    private RabbitTask getMessage(String message) {
        try {
            return objectMapper.readValue(message, RabbitTask.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
