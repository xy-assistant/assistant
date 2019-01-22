package com.hpin.assistant.service;

import com.hpin.assistant.dao.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    private static Logger logger = LoggerFactory.getLogger(PersonService.class);

    @Autowired
    PersonRepository personRepository;

    public void queryPerson(String cityKey) {
        String address = this.personRepository.getAddress();
        logger.info("查询到地址为：{}",address);
    }

    public void queryPersonNiMaBi(String cityKey) {
        String address = this.personRepository.getAddress();
        logger.info("查询到地址为：{}",address);
    }
}
