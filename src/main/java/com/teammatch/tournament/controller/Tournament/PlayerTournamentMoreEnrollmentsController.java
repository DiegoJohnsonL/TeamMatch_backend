package com.teammatch.tournament.controller.Tournament;

import com.teammatch.tournament.domain.model.TournamentMoreEnrollment;
import com.teammatch.tournament.domain.service.TournamentMoreEnrollmentService;
import com.teammatch.tournament.resource.Tournament.SaveTournamentMoreEnrollmentResource;
import com.teammatch.tournament.resource.Tournament.TournamentMoreEnrollmentResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Tournament More Enrollment", description = "Tournament More Enrollment API")
@RestController
@CrossOrigin
@RequestMapping("/api")
public class PlayerTournamentMoreEnrollmentsController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private TournamentMoreEnrollmentService tournamentMoreEnrollmentService;

    @GetMapping("/player/{playerId}/tournament-more-enrollments")
    public Page<TournamentMoreEnrollmentResource> getAllTournamentMoreEnrollmentsByPlayerId(
            @PathVariable(name = "playerId") Long playerId,
            Pageable pageable) {
        Page<TournamentMoreEnrollment> tournamentMoreEnrollmentPage = tournamentMoreEnrollmentService.getAllTournamentMoreEnrollmentsByPlayerId(playerId, pageable);
        List<TournamentMoreEnrollmentResource> resources = tournamentMoreEnrollmentPage.getContent()
                .stream().map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());

    }



    private TournamentMoreEnrollment convertToEntity(SaveTournamentMoreEnrollmentResource resource) {
        return mapper.map(resource, TournamentMoreEnrollment.class);
    }

    private TournamentMoreEnrollmentResource convertToResource(TournamentMoreEnrollment entity) {
        return mapper.map(entity, TournamentMoreEnrollmentResource.class);
    }


}
