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
    @Transactional
    public void process() {

        LocalDateTime localDateTime = LocalDateTime.now();

        Pageable pageRequest = PageRequest.of(0, 5, Sort.by("id").ascending());

        Page<Data> dataPage;

        int processedRows = 0;

        do {
            dataPage = dataRepository.findAll(pageRequest);

            List<Data> dataList = dataPage.getContent();

            dataList.forEach(data -> data.setModifyAt(localDateTime));

            dataRepository.saveAll(dataList);

            processedRows += dataList.size();

            if (processedRows >= 300) {
                throw new RuntimeException("Processed more than 300 rows");
            }

            pageRequest = pageRequest.next();

        } while (dataPage.hasNext());
    }

}
