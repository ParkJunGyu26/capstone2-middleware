package kau.capstone2.service;

import kau.capstone2.entity.Score;
import kau.capstone2.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ScoreService {

    @Autowired
    private ScoreRepository scoreRepository;

    // 점수 업데이트 로직
    @Transactional
    public void updateScore(String playerId, int newScore) {
        Optional<Score> existingScore = scoreRepository.findByPlayerId(playerId);

        if (existingScore.isPresent()) {
            // 기존 점수에 새로운 점수를 더함
            Score scoreEntity = existingScore.get();
            scoreEntity.setScore(scoreEntity.getScore() + newScore);  // 기존 점수에 누적
            scoreRepository.save(scoreEntity);
        } else {
            // 존재하지 않으면 새로 생성
            Score newScoreEntity = new Score(null, playerId, newScore);
            scoreRepository.save(newScoreEntity);
        }
    }

    // playerId로 점수를 조회하는 로직
    @Transactional
    public int getScoreOrCreateIfNotExist(String playerId) {
        return scoreRepository.findByPlayerId(playerId)
                .map(Score::getScore)
                .orElseGet(() -> {
                    // 존재하지 않으면 새로 생성하고 score는 0으로 설정
                    Score newScoreEntity = new Score(null, playerId, 0);
                    scoreRepository.save(newScoreEntity);
                    return 0;
                });
    }
}

