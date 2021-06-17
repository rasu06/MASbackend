package com.example.appuser;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@Entity      // @Entity will make this a table in our database
@Table(name = "app_user")
public class AppUser implements UserDetails {     // CONCEPT OF INTERFACE==>If I want a class AppUser to have some methods to available outside and want to protect some methods.Then we can add non-protective methods to an Interface And then create objects of those Interface by their Reference(UserDetails) and Memory of subclass(AppUser).By this, we can protect methods of AppUser.
   @SequenceGenerator(
           name="student_sequence",
           sequenceName = "student_sequence",
           allocationSize = 1
   )
   @Id
   @GeneratedValue(
           strategy= GenerationType.SEQUENCE,
           generator = "student_sequence"
   )
    private Long id;
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String mobile;
    private String password;
    private Boolean locked=false;      //check weather account is locked or not
    private Boolean enabled=false;
    @Enumerated(EnumType.STRING)
    private AppUserRole appUserRole;

    public AppUser(){

    }
    public AppUser(String firstname, String lastname, String username, String email, String mobile, String password, AppUserRole appUserRole) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
        this.appUserRole = appUserRole;
    }



                               //----------------Authnetication stuffs-----------------------//
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       /* SimpleGrantedAuthority authority=new SimpleGrantedAuthority(appUserRole.name());
        return Collections.singletonList(authority);*/
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    /*@Override
    public  String getEmail(){return email;}*/

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
