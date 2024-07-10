package com.alex;

import com.alex.entity.Data;
import com.alex.repository.DataRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class TestDataFiller {

    @Autowired
    private DataRepository dataRepository;

    @Test
    public void fillDatabase() {

        List<Data> dataList = new ArrayList<>();
        for (int i = 0; i < 500; i++) {
            dataList.add(new Data(new byte[1024 * 1024], LocalDateTime.now()));
        }
        dataRepository.saveAll(dataList);
    }
}
