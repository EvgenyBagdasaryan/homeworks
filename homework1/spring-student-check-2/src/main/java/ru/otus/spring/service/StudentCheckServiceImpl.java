package ru.otus.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.ChecksDaoSimple;
import ru.otus.spring.domain.Check;

import java.io.IOException;

@Service
public class StudentCheckServiceImpl implements StudentCheckService {

    private final ChecksDaoSimple dao;

    int numCheck = 0;

    //@Autowired
    public StudentCheckServiceImpl(ChecksDaoSimple dao) {
        this.dao = dao;
    }

    public Check getNextCheck(){

        numCheck++;
        return dao.getCheckByNum(numCheck);
    }
}
