package kz.seisen.kormeback.controller;

import kz.seisen.kormeback.model.Pin;
import kz.seisen.kormeback.repository.PinRepository;
import kz.seisen.kormeback.repository.UserRepository;
import kz.seisen.kormeback.service.PinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;



@RestController
@RequestMapping("/api/pins")
public class PinController {
    @Autowired
    private PinService pinService;

    @PostMapping
    public ResponseEntity<Pin> createPin(@RequestBody Pin pin) {
        return ResponseEntity.ok(pinService.createPin(pin));
    }

    @GetMapping
    public ResponseEntity<List<Pin>> getAllPins() {
        return ResponseEntity.ok(pinService.getAllPins());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pin> getPinById(@PathVariable Long id) {
        Optional<Pin> pin = pinService.getPinById(id);
        return pin.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePin(@PathVariable Long id) {
        pinService.deletePin(id);
        return ResponseEntity.noContent().build();
    }
}