package by.itacademy.javaenterprise.seledtsova.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "review")
public class Review {

    @Id
    @SequenceGenerator(name = "review_id_seq", sequenceName = "review_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "review_id_seq")
    @Column(updatable = false, nullable = false)
    private Long id;
    @Column(name = "review_body")
    private String reviewBody;
    @Column(name = "created_by")
    private LocalDate createdBy;
    @Column(name = "is_shown")
    private Boolean isVisible;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
