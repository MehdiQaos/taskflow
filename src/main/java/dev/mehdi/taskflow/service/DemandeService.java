package dev.mehdi.taskflow.service;

import dev.mehdi.taskflow.domain.model.Demande;

public interface DemandeService {
    void replace(Demande demande);

    void remove(Demande demande);
}
