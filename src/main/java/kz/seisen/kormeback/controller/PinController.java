package kz.seisen.kormeback.controller;

import kz.seisen.kormeback.model.Pin;
import kz.seisen.kormeback.repository.PinRepository;
import kz.seisen.kormeback.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pins")
public class PinController {
    @Autowired
    private PinRepository pinRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<Pin> getAllPins() {
        return pinRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Pin> createPin(@RequestBody Pin pin) {
        if (pin.getUser() == null || pin.getUser().getId() == null) {
            return ResponseEntity.badRequest().build();
        }
        return userRepository.findById(pin.getUser().getId())
                .map(user -> {
                    pin.setUser(user);
                    Pin savedPin = pinRepository.save(pin);
                    return ResponseEntity.ok(savedPin);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pin> getPinById(@PathVariable Long id) {
        return pinRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}