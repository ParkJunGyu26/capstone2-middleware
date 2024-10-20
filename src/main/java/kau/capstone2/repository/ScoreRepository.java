package kau.capstone2.repository;

import kau.capstone2.entity.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {
    Optional<Score> findByPlayerId(String playerId);  // playerId의 타입을 String으로 수정
}
