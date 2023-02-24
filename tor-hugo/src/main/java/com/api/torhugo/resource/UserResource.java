package com.api.torhugo.resource;

import com.api.torhugo.model.dto.UserDTO;
import com.api.torhugo.service.UserService;
import com.api.torhugo.util.ApplicationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserResource {
    @Autowired
    private UserService service;

    @Autowired
    private ApplicationResponse response;

    @PostMapping
    public ResponseEntity<UserDTO> createdUser(
            @RequestBody final UserDTO userDTO){
        final UserDTO user = service.createUser(userDTO);
        return ResponseEntity.status(response.created).body(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(
            @PathVariable final Long id){
        UserDTO dto = service.findById(id);
        return ResponseEntity.status(response.successful).body(dto);
    }

    @GetMapping()
    public ResponseEntity<List<UserDTO>> findAll(){
        List<UserDTO> dto = service.findAll();
        return ResponseEntity.status(response.successful).body(dto);
    }

    @PutMapping()
    public ResponseEntity<UserDTO> update(
            @RequestBody final UserDTO dto){

        UserDTO userDTO = service.updateUser(dto.getIdUser(), dto);
        return ResponseEntity.status(response.successful).body(userDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<UserDTO> delete(
            @PathVariable final Long id){
        service.delete(id);

        return ResponseEntity.status(response.noContent).build();
    }
}
