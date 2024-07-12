package com.alex.service.impl;

import com.alex.entity.Data;
import com.alex.repository.DataRepository;
import com.alex.service.DataService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DefaultDataService implements DataService {

    private final DataRepository dataRepository;

    public DefaultDataService(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    @Override
    public void process() {

        LocalDateTime localDateTime = LocalDateTime.now();

        Pageable pageRequest = PageRequest.of(0, 50, Sort.by("id").ascending());

        Page<Data> dataPage;

        do {
            dataPage = dataRepository.findAll(pageRequest);

            List<Data> dataList = dataPage.getContent();

            dataList.forEach(data -> data.setModifyAt(LocalDateTime.now()));

            dataRepository.saveAll(dataList);

            pageRequest = pageRequest.next();

        } while (dataPage.hasNext());
    }

}
