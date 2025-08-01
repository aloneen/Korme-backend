package kz.seisen.kormeback.repository;

import kz.seisen.kormeback.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
