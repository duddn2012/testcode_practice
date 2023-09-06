package com.lets.git.it.letsgitit.repository;

import com.lets.git.it.letsgitit.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findAll();
    Board save(Board board);
    void deleteById(Long id);
}
