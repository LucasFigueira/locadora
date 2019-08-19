package com.alltecnologia.locadora.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
 

@Entity
@Table(name = "role")
@SequenceGenerator(name = "ROLE_SEQ", sequenceName = "ROLE_SEQ", initialValue = 1, allocationSize = 1)
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "ROLE_SEQ")
    @Column(name = "role_id")
    private int roleId;

    @Column(name = "role")
    private String role;
 
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
