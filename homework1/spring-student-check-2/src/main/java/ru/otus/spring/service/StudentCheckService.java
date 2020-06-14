package ru.otus.spring.service;

import ru.otus.spring.domain.Check;

import java.io.IOException;

public interface StudentCheckService {
    Check getNextCheck();
}
