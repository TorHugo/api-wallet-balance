package com.api.torhugo.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "tb_roles")
@Data
public class RoleModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRoles;
    private String authority;
}