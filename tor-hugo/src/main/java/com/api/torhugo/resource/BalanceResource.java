package com.api.torhugo.resource;

import com.api.torhugo.domain.dto.BalanceDTO;
import com.api.torhugo.domain.dto.UserDTO;
import com.api.torhugo.domain.enums.TypeBalance;
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
}
