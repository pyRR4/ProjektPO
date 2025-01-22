package com.example.projektpo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
@Entity
@NoArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private int id;

    @Column(nullable = false)
    private int consulateEmployeeId;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Timestamp timestamp;

    public Message(String content) {
        this.consulateEmployeeId = 13;
        this.content = content;
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }

}

