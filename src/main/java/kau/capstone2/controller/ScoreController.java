package kau.capstone2.controller;

import kau.capstone2.dto.ScoreUpdateRequest;
import kau.capstone2.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/score")
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    @PostMapping("/update")
    public ResponseEntity<String> updateScore(@RequestBody ScoreUpdateRequest request) {
        scoreService.updateScore(request.getPlayerId(), request.getScore());
        return ResponseEntity.ok("Score updated successfully");
    }

    @GetMapping("/{playerId}")
    public ResponseEntity<Map<String, Integer>> getScore(@PathVariable String playerId) {
        int score = scoreService.getScoreOrCreateIfNotExist(playerId);
        Map<String, Integer> response = new HashMap<>();
        response.put("score", score);
        return ResponseEntity.ok(response);
    }
}
