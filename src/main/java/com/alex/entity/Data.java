package com.alex.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Data {
    @Id
    @SequenceGenerator(
            name = "data_sequence",
            sequenceName = "data_sequence",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "data_sequence"
    )
    private int id;

    private byte[] data;

    private LocalDateTime modifyAt;

    public Data(byte[] data, LocalDateTime modifyAt) {
        this.data = data;
        this.modifyAt = modifyAt;
    }

    public Data() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getModifyAt() {
        return modifyAt;
    }

    public void setModifyAt(LocalDateTime modifyAt) {
        this.modifyAt = modifyAt;
    }

}
