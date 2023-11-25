package com.dpkProjects.user.service.models;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "MICRO_USERS")
public class User {
    @Id
    @Column(name = "ID")
    private String userId;
    private String email;
    @Column(name = "NAME",length = 20)
    private String name;

    @Transient
    private List<Rating> ratings = new ArrayList<Rating>();
}
