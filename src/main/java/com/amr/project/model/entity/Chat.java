package com.amr.project.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "chat")
@Data
@NoArgsConstructor
public class Chat {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<User> members;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Message> messages;

    private Long hash;

    public Chat(List<User> members) {
        this.members = members;
        this.hash = members.stream().map(e -> e.getId()).map(e -> e.hashCode()).mapToLong(e -> e).sum();
    }
}
