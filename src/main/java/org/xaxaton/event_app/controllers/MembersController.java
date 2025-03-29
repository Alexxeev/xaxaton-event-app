package org.xaxaton.event_app.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xaxaton.event_app.dto.EventDTO;
import org.xaxaton.event_app.dto.MemberDTO;
import org.xaxaton.event_app.mappers.MemberMapper;
import org.xaxaton.event_app.models.Member;
import org.xaxaton.event_app.repo.MemberRepo;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/members")
public class MembersController {

    private final MemberRepo memberRepo;
    private final MemberMapper memberMapper;

    public MembersController(MemberRepo memberRepo, MemberMapper memberMapper) {
        this.memberRepo = memberRepo;
        this.memberMapper = memberMapper;
    }

    @GetMapping
    public ResponseEntity<List<MemberDTO>> getAll() {
        List<Member> members = memberRepo.findAll();
        List<MemberDTO> dtos = memberMapper.toListOfDTOs(members);
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<MemberDTO> getById(@PathVariable("memberId") int memberId) {
        Optional<Member> optionalMember = memberRepo.findById(memberId);
        if (optionalMember.isEmpty())
            return ResponseEntity.notFound().build();
        MemberDTO memberDTO = memberMapper.toDTO(optionalMember.get());
        return ResponseEntity.ok(memberDTO);
    }

    @PostMapping
    public ResponseEntity<MemberDTO> createNew(@RequestBody MemberDTO memberDTO) {
        Member member =memberMapper.toModel(memberDTO);
        if (memberRepo.existsByName(member.getName()))
            return ResponseEntity.badRequest().build();
        Member saved =memberRepo.save(member);
        MemberDTO savedDTO =memberMapper.toDTO(saved);
        return ResponseEntity.ok(savedDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MemberDTO> updateById(
            @PathVariable("id") int id,
            @RequestBody MemberDTO memberDTO) {
        if (memberRepo.existsByName(memberDTO.getName()))
            return ResponseEntity.badRequest().build();
        var member =memberMapper.toModel(memberDTO);
        member.setId(id);
        member =memberRepo.save(member);
        MemberDTO updatedDTO =memberMapper.toDTO(member);
        return ResponseEntity.ok(updatedDTO);
    }
}
