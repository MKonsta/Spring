package com.kos.jdbcVsSpringData;

import com.kos.jdbcVsSpringData.service.PersonService;
import com.kos.jdbcVsSpringData.service.SimplePersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EnvironmentContext {

    @Autowired
    PersonService personService;

    @Autowired
    SimplePersonService simplePersonService;


    @EventListener(ApplicationReadyEvent.class)
    public void contextRefreshedEvent() {

        System.out.println("EnvironmentContext.contextRefreshedEvent finished!");

        //ID - Integer. Sequence DB side generated
        personService.startSpringDataBatch();
        personService.startJdbcBatch();

        //ID - UUID. Code side generation
        simplePersonService.startJdbcBatch();
        simplePersonService.startSpringDataBatch();

    }


}