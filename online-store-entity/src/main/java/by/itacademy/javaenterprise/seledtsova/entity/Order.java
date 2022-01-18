package by.itacademy.javaenterprise.seledtsova.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @SequenceGenerator(name = "order_id_seq", sequenceName = "order_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_id_seq")
    @Column(updatable = false, nullable = false)
    private Long id;
    @Column(name = "created_by")
    private LocalDate createdBy;

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.MERGE,
            optional = false
    )
    @JoinColumn(name = "status_id", nullable = false)
    private Status status;

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.MERGE,
            optional = false
    )
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "orders_item",
            joinColumns = {@JoinColumn(name = "order_id")},
            inverseJoinColumns = {@JoinColumn(name = "item_id")}
    )
    @ToString.Exclude
    private Set<Item> items = new HashSet<>();
}

