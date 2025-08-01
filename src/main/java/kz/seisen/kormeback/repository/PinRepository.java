package kz.seisen.kormeback.repository;

import kz.seisen.kormeback.model.Pin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PinRepository extends JpaRepository<Pin, Long> {
}
