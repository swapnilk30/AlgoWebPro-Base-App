package com.algowebpro.scm.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.internal.bytebuddy.agent.builder.AgentBuilder.PoolStrategy.Eager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.algowebpro.scm.enums.Providers;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "scm_users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScmUser implements UserDetails{

    @Id
    private String userId;

    @Column(name = "user_name",nullable = false)
    private String name;
    @Column(unique = true,nullable = false)
    private String email;
    private String password;

    //@Column(length = 10000)
    private String about;

    //@Column(length = 10000)
    private String profilePic;

    private String phoneNumber;

    @Builder.Default
    private boolean enabled = true;
    private boolean emailVerified = false;
    private boolean phoneVerified = false;

    //GOOGLE , FACEBOOK, GITHUB
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private Providers provider = Providers.SELF;
    private String providerUserId;

    @OneToMany(mappedBy = "scmUser" ,cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
    private List<Contact> contacts = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roleList = new ArrayList<>();


    // ========== UserDetails Interface Methods ==========

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<SimpleGrantedAuthority> roles = roleList.stream().map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList());
        return roles;
    }

    @Override
    public String getUsername() {
         // Username is typically the email for authentication
        return this.email;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() {
        // Return true if account is not expired
        return true; // Or implement your own logic
    }

    @Override
    public boolean isAccountNonLocked() {
        // Return true if account is not locked
        return true; // Or implement your own logic
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // Return true if credentials are not expired
        return true; // Or implement your own logic
    }

    @Override
    public boolean isEnabled() {
        // Return enabled status
        return this.enabled;
    }


}
