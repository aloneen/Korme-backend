package kz.seisen.kormeback.service;

import kz.seisen.kormeback.model.Pin;
import kz.seisen.kormeback.repository.PinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PinService {
    @Autowired
    private PinRepository pinRepository;

    public Pin createPin(Pin pin) {
        return pinRepository.save(pin);
    }

    public List<Pin> getAllPins() {
        return pinRepository.findAll();
    }

    public Optional<Pin> getPinById(Long id) {
        return pinRepository.findById(id);
    }

    public void deletePin(Long id) {
        pinRepository.deleteById(id);
    }

}