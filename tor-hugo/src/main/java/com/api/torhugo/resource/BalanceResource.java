package com.api.torhugo.resource;

import com.api.torhugo.model.dto.BalanceDTO;
import com.api.torhugo.model.dto.DeleteBalanceDTO;
import com.api.torhugo.model.dto.LsBalanceDTO;
import com.api.torhugo.model.dto.UserDTO;
import com.api.torhugo.model.enums.TypeBalance;
import com.api.torhugo.service.BalanceService;
import com.api.torhugo.util.ApplicationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/balance")
public class BalanceResource {

    @Autowired
    private BalanceService service;

    @Autowired
    private ApplicationResponse response;

    @PostMapping()
    public ResponseEntity<BalanceDTO> createdBalance(
            @RequestBody final BalanceDTO balanceDTO){
        final BalanceDTO balance = service.createdBalance(balanceDTO, balanceDTO.getIdWallet());
        return ResponseEntity.status(response.created).body(balance);
    }

    @PostMapping("/list")
    public ResponseEntity<List<BalanceDTO>> createdLsBalance(
            @RequestBody final LsBalanceDTO lsBalanceDTO){

        final List<BalanceDTO> lsBalance = service.createdLsBalance(lsBalanceDTO);
        return ResponseEntity.status(response.created).body(lsBalance);
    }

    @GetMapping("/{idWallet}")
    public ResponseEntity<List<BalanceDTO>> findAllByWalletId(
            @PathVariable final Long idWallet){
        List<BalanceDTO> dto = service.findAll(idWallet);
        return ResponseEntity.status(response.successful).body(dto);
    }

    @GetMapping("/movement/{idWallet}")
    public ResponseEntity<UserDTO> findAllDepositByWalletId(
            @PathVariable final Long idWallet,
            @RequestParam final TypeBalance typeBalance){
        UserDTO dto = service.findAllMoviment(idWallet, typeBalance);
        return ResponseEntity.status(response.successful).body(dto);
    }

    @PutMapping()
    public ResponseEntity<BalanceDTO> update(
            @RequestBody final BalanceDTO dto){

        BalanceDTO balance = service.updateBalance(dto.getIdBalance(), dto);
        return ResponseEntity.status(response.successful).body(balance);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<BalanceDTO> delete(
            @PathVariable final Long id){
        service.delete(id);
        return ResponseEntity.status(response.noContent).build();
    }

    @DeleteMapping()
    public ResponseEntity<UserDTO> delete(
            @RequestBody final DeleteBalanceDTO deleteBalanceDTO){
        service.delete(deleteBalanceDTO);
        return ResponseEntity.status(response.noContent).build();
    }
}
