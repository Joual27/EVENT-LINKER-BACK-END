package org.youcode.EventLinkerAPI.user;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.youcode.EventLinkerAPI.DM.DM;
import org.youcode.EventLinkerAPI.message.Message;
import org.youcode.EventLinkerAPI.review.Review;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Inheritance(strategy = InheritanceType.JOINED)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
public abstract class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;
    private LocalDateTime createdAt;

    @ToString.Exclude
    @OneToMany(mappedBy = "reviewer")
    private List<Review> writtenReviews;

    @ToString.Exclude
    @OneToMany(mappedBy = "reviewee")
    private List<Review> receivedReviews;

    @ManyToMany(mappedBy = "users")
    @EqualsAndHashCode.Exclude
    private Set<DM> dms;

    @OneToMany(mappedBy = "user")
    private List<Message> messages;

    @Override
    public List<GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_"+ getUserRole()));
    }

    public abstract String getUserRole();

    public String getUsernameField(){
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
