package com.examportal.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long UserRoleId;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
    @ManyToOne
    private Role role;

    @Override
    public String toString() {
        return "UserRole{" +
                "UserRoleId=" + UserRoleId +
                ", user=" + user +
                ", role=" + role +
                '}';
    }

    public Long getUserRoleId() {
        return UserRoleId;
    }

    public void setUserRoleId(Long userRoleId) {
        UserRoleId = userRoleId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return role;
    }
}
