package org.example.newsfeedPractice.member.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.newsfeedPractice.follow.entity.Follow;

import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String name;
    private String password;

    @OneToMany(mappedBy = "follower")
    private List<Follow> followings;

    @OneToMany(mappedBy = "following")
    private List<Follow> followers;

    public Member(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public void updateProfile(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public void updatePassword(String newPassword) {
        this.password = newPassword;
    }
}