package com.forhub.api.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
@Data
@Entity(name = "User")
@Table(name = "users")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    @OneToMany(mappedBy = "author")
    private Set<Topic> topics;

    @OneToMany(mappedBy = "author")
    private Set<Response> responses;

    @OneToMany(mappedBy = "instructor")
    private Set<Course> courses;
}