package com.algowebpro.scm.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "scm_contact")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Contact {
    @Id
    private String contactId;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private String picture;
    private String description;

    private boolean favourite = false;

    private String websiteLink;
    private String linkedInLink;
    
   // private List<String> socialLinks = new ArrayList<>();

   @ManyToOne
   private ScmUser scmUser;

    @OneToMany(mappedBy = "contact" ,cascade = CascadeType.ALL,fetch = FetchType.EAGER,orphanRemoval = true)
    private List<SocialLink> socialLinks = new ArrayList<>();

}
