package kz.seisen.kormeback.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Entity
@Table(name = "pins")
@Data
public class Pin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String imageUrl; // Store URL to image (e.g., hosted on S3 or local)

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(mappedBy = "pins")
    private List<Board> boards;
}