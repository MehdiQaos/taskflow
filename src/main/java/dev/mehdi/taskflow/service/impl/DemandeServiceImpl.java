package dev.mehdi.taskflow.service.impl;

import dev.mehdi.taskflow.domain.model.Demande;
import dev.mehdi.taskflow.domain.model.Task;
import dev.mehdi.taskflow.domain.model.enums.DemandeStatus;
import dev.mehdi.taskflow.domain.model.enums.DemandeType;
import dev.mehdi.taskflow.exception.InvalidRequestException;
import dev.mehdi.taskflow.repository.DemandeRepository;
import dev.mehdi.taskflow.service.DemandeService;
import dev.mehdi.taskflow.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DemandeServiceImpl implements DemandeService {

    private final DemandeRepository demandeRepository;
    private final TaskService taskService;
    @Override
    public void replace(Demande demande) {
        Task replacementTask = demande.getReplacementTask();
        if (replacementTask.getAssignedTo() != null) {
            throw InvalidRequestException.of("replacement task", "replacement task is already assigned to someone");
        }
        int allowedDemands = 2;
        List<Demande> sameDayDemands = demandeRepository
                .findAllByTypeAndCreatedAtIsAfter(DemandeType.TASK_REPLACEMENT, LocalDateTime.now().minusHours(24));
        for (Demande d : sameDayDemands) {
            if (d.getCreatedAt().plusHours(12).isBefore(LocalDateTime.now())) {
                allowedDemands++;
                d.setStatus(DemandeStatus.UNPROCESSED);
            }
        }
        if (sameDayDemands.size() >= allowedDemands) {
            throw InvalidRequestException.of("replacement token", "You can't replace more tasks today");
        }
        demandeRepository.save(demande);
    }

    @Override
    public void remove(Demande demande) {
        if (!demandeRepository.findAllByTypeAndCreatedAtIsAfter(DemandeType.TASK_REMOVAL, LocalDateTime.now().minusDays(30)).isEmpty()) {
            throw InvalidRequestException.of("remove token", "You can't remove more tasks this month");
        }
        Task task = demande.getTask();
        task.setAssignedTo(null);
        taskService.save(task);
        demandeRepository.save(demande);
    }
}
