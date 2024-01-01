package dev.mehdi.taskflow.controller;

import dev.mehdi.taskflow.domain.model.Demande;
import dev.mehdi.taskflow.dto.demande.DemandeRequestDto;
import dev.mehdi.taskflow.mapper.demande.DemandeRequestMapper;
import dev.mehdi.taskflow.service.DemandeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/demandes")
@RequiredArgsConstructor
public class DemandeController {

    private final DemandeService demandeService;
    private final DemandeRequestMapper demandeRequestMapper;

    @PostMapping("replace")
    public ResponseEntity<Boolean> replace(@RequestBody DemandeRequestDto demandeRequestDto) {
        Demande demande = demandeRequestMapper.toDemande(demandeRequestDto);
        demandeService.replace(demande);
        return ResponseEntity.ok(true);
    }

    @PostMapping("remove")
    public ResponseEntity<Boolean> delete(@RequestBody DemandeRequestDto demandeRequestDto) {
        Demande demande = demandeRequestMapper.toDemande(demandeRequestDto);
        demandeService.remove(demande);
        return ResponseEntity.ok(true);
    }
}
