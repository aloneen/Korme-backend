package kz.seisen.kormeback.controller;

import kz.seisen.kormeback.model.Pin;
import kz.seisen.kormeback.repository.PinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pins")
public class PinController {
    @Autowired
    private PinRepository pinRepository;

    @GetMapping
    public List<Pin> getAllPins() {
        return pinRepository.findAll();
    }

    @PostMapping
    public Pin createPin(@RequestBody Pin pin) {
        return pinRepository.save(pin);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pin> getPinById(@PathVariable Long id) {
        return pinRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}