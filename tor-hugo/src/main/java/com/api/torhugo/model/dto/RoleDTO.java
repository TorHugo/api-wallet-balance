package com.api.torhugo.model.dto;

import com.api.torhugo.model.entity.RoleModel;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long idRole;
    private String authority;

    public RoleDTO(RoleModel entity){
        idRole = entity.getIdRoles();
        authority = entity.getAuthority();
    }
}
