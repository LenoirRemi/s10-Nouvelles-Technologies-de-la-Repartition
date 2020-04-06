package com.istv.banq.service;

import com.istv.banq.model.History;
import com.istv.banq.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("historyService")
public class HistoryService {

    private HistoryRepository historyRepository;

    @Autowired
    public HistoryService(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    public List<History> listAll() {
        List<History> histories = new ArrayList<>();
        historyRepository.findAll().forEach(histories::add);

        return histories;
    }
}
