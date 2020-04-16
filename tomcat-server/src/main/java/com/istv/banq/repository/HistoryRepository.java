package com.istv.banq.repository;

import com.istv.banq.model.History;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("historyRepository")
@Primary
public interface HistoryRepository extends JpaRepository<History,Integer> {
    History findById(int id);
}

