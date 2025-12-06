package com.algowebpro.scm.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScmUserForm {

    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private String about;

}
