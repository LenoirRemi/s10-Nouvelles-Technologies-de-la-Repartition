package com.istv.banq.service;

import com.istv.banq.model.History;
import com.istv.banq.model.User;
import com.istv.banq.repository.HistoryRepository;
import com.istv.banq.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("historyService")
public class HistoryService {

    @Autowired
    private HistoryRepository historyRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    public HistoryService(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    public List<History> listAll() {
        List<History> histories = new ArrayList<>();
        historyRepository.findAll().forEach(histories::add);

        return histories;
    }

    public void saveHistory(List<User> users){
        History history = new History();
        for (User user:users) {
            if (history.getUser1() == null) {
                history.setUser1(userRepository.findById(user.getId()));
            }else{
                history.setUser2(userRepository.findById(user.getId()));
            }
            if (user.getBalance() > 0){
                history.setBalance(user.getBalance());
            }
        }
        historyRepository.save(history);
    }

}
