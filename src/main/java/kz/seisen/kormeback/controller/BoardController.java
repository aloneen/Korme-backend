package kz.seisen.kormeback.controller;

import kz.seisen.kormeback.model.Board;
import kz.seisen.kormeback.repository.BoardRepository;
import kz.seisen.kormeback.repository.PinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boards")
public class BoardController {
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private PinRepository pinRepository;

    @GetMapping
    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }

    @PostMapping
    public Board createBoard(@RequestBody Board board) {
        return boardRepository.save(board);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Board> getBoardById(@PathVariable Long id) {
        return boardRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{boardId}/pins/{pinId}")
    public ResponseEntity<Board> addPinToBoard(@PathVariable Long boardId, @PathVariable Long pinId) {
        return boardRepository.findById(boardId)
                .map(board -> pinRepository.findById(pinId)
                        .map(pin -> {
                            board.getPins().add(pin);
                            Board updatedBoard = boardRepository.save(board);
                            return ResponseEntity.ok(updatedBoard);
                        })
                        .orElse(ResponseEntity.notFound().build()))
                .orElse(ResponseEntity.notFound().build());
    }
}