package ru.otus.spring.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.spring.dao.ChecksDao;
import ru.otus.spring.dao.ChecksDaoSimple;
import ru.otus.spring.domain.Check;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class StudentCheckServiceImplTest {

    @Mock
    private ChecksDao checksDao;

    private StudentCheckService studentService;

    @BeforeEach
    void setUp() {
        studentService = new StudentCheckServiceImpl((ChecksDaoSimple) checksDao);
    }

    @Test
    void getByName() {

        // TODO: используйте eq() mapper вместо any()
        given(checksDao.getCheckByNum(any()))
                .willReturn(new Check(10, "question text", "answer text"));

        assertThat(studentService.getNextCheck())
                .isNotNull(); // TODO: сравните с помощью equals

    }
}