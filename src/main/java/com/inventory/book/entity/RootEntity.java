package com.inventory.book.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;

@MappedSuperclass
public class RootEntity {

    @Id
    @GeneratedValue
    @Column()
    private long id;

    @Column()
    private String actor;

    @CreationTimestamp
    @Column(name="date_created")
    private LocalDateTime dateCreated;

    @UpdateTimestamp
    @Column(name = "date_modified")
    private LocalDateTime dateModified;

    @Column(name = "is_deleted")
    private int deleted;

}
