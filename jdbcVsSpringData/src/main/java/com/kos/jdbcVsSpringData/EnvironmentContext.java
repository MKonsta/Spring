package com.kos.jdbcVsSpringData;

import com.kos.jdbcVsSpringData.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EnvironmentContext {

    @Autowired
    PersonService personService;


    @EventListener(ApplicationReadyEvent.class)
    public void contextRefreshedEvent() {

        System.out.println("EnvironmentContext.contextRefreshedEvent finished!");

        personService.startSpringDataBatch();
        personService.startJdbcBatch();

    }


}