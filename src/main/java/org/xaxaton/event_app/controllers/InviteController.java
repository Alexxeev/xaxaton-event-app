package org.xaxaton.event_app.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xaxaton.event_app.dto.InviteDTO;
import org.xaxaton.event_app.repo.InviteRepo;

@RestController
@RequestMapping("/invites")
public class InviteController {
    private final InviteRepo inviteRepo;
    private final ModelMapper mapper;

    public InviteController(InviteRepo inviteRepo, ModelMapper mapper) {
        this.inviteRepo = inviteRepo;
        this.mapper = mapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<InviteDTO> getById(@PathVariable("id") int id) {
        var inviteOrNull = inviteRepo.findById(id);
        if (inviteOrNull.isEmpty())
            return ResponseEntity.notFound().build();
        var invite = inviteOrNull.get();
        var inviteDTO = mapper.map(invite, InviteDTO.class);
        return ResponseEntity.ok(inviteDTO);
    }

    @PostMapping("/{id}/accept")
    public ResponseEntity<Void> accept(@PathVariable("id") int id) {
        return setInvitationStatus(id, true);
    }

    @PostMapping("/{id}/reject")
    public ResponseEntity<Void> reject(@PathVariable("id") int id) {
        return setInvitationStatus(id, false);
    }

    private ResponseEntity<Void> setInvitationStatus(int id, boolean accepted) {
        var inviteOrNull = inviteRepo.findById(id);
        if (inviteOrNull.isEmpty())
            return ResponseEntity.notFound().build();
        var invite = inviteOrNull.get();
        invite.setAccepted(accepted);
        inviteRepo.save(invite);
        return ResponseEntity.ok().build();
    }
}
