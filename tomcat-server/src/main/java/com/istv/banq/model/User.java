package com.istv.banq.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

import org.springframework.data.rest.core.annotation.RestResource;

@Entity
@RestResource
@Table(name = "USER")
@JacksonXmlRootElement(localName = "User")
public class User implements UserDetails, Serializable {
    @Id
    @GeneratedValue(generator = "USER_ID")
    @Column(name = "USER_ID", nullable=false, unique=true)
    @JacksonXmlProperty(isAttribute = true)
    private int id;
    @Column(name="USER_EMAIL", length=50, nullable=false, unique=true)
    @Email(message = "*Merci d'insérer un email valide")
    @NotEmpty(message = "*Ce champs est obligatoire")
    @JacksonXmlProperty
    private String email;
    @Column(name = "USER_BALANCE", nullable = false, columnDefinition = "float(10,2) default 0.00")
    @JacksonXmlProperty
    private float balance;
    @Column(name="USER_PASSWORD", length=200, nullable=false)
    @NotEmpty(message = "*Ce champs est obligatoire")
    private String password;
    @Column(name="USER_NAME", length=50, nullable=false)
    @NotEmpty(message = "*Ce champs est obligatoire")
    private String name;
    @Column(name="USER_LASTNAME", length=50, nullable=false)
    @NotEmpty(message = "*Ce champs est obligatoire")
    private String lastName;
    @Column(name="USER_ACTIVE", length=5, nullable=false)
    private int active;
    @ManyToMany
    @JoinTable(
            name = "USER_ROLE",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    private Set<Role> roles;

    public User(){

    }

    public User(int id, String email, String password, String name, String lastName, int active, float balance) {
        this.id = id;
        this.balance = balance;
        this.email = email;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.active = active;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", active=" + active +
                ", roles=" +
                '}';
    }

}
