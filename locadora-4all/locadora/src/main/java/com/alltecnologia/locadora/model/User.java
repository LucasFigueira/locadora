package com.alltecnologia.locadora.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
 

@Entity
@Table(name = "user",uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "email"}, name = "id_email"))
public class User implements Serializable {
 
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", length = 100)
    private int idUser;

    @Column(name = "email", length = 100, unique = true)
    @Email
    private String email;
    
    @Column(name = "password")
    @NotBlank
    private String password;
    
    @Column(name = "name",length =100)
    @NotBlank
    private String name; 

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles; 
 

    public User() {
    }

    public User(User users) { 
        this.email = users.getEmail(); 
        this.name = users.getName(); 
        this.idUser = users.getIdUser();
        this.roles = users.getRoles();
        this.password = users.getPassword();
    }

    

    public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
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
 

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

 
    
}