package com.example.projektpo.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity(
        name = "warnings"
)
public class Warning {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "country", nullable = false, referencedColumnName = "id", unique = true)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Country country;

    @Column(nullable = false)
    private String description;
}
