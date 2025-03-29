package org.xaxaton.event_app.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xaxaton.event_app.dto.MemberDTO;
import org.xaxaton.event_app.mappers.MemberMapper;
import org.xaxaton.event_app.models.Member;
import org.xaxaton.event_app.repo.MemberRepo;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MembersController extends BaseController<Member, MemberDTO, MemberRepo, MemberMapper> {

    public MembersController(MemberRepo repo, MemberMapper mapper) {
        super(repo, mapper);
    }

    @GetMapping
    public List<MemberDTO> getAll() {
        List<Member> members = repo.findAll();
        List<MemberDTO> dtos = mapper.toListOfDTOs(members);
        return dtos;
    }

    @PostMapping
    public ResponseEntity<MemberDTO> createNew(@RequestBody MemberDTO memberDTO) {
        Member member = mapper.toModel(memberDTO);
        if (repo.existsByName(member.getName()))
            return ResponseEntity.badRequest().build();
        Member saved = repo.save(member);
        MemberDTO savedDTO = mapper.toDTO(saved);
        return ResponseEntity.ok(savedDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MemberDTO> updateById(
            @PathVariable("id") int id,
            @RequestBody MemberDTO memberDTO) {
        if (repo.existsByName(memberDTO.getName()))
            return ResponseEntity.badRequest().build();
        var member = mapper.toModel(memberDTO);
        member.setId(id);
        member = repo.save(member);
        MemberDTO updatedDTO = mapper.toDTO(member);
        return ResponseEntity.ok(updatedDTO);
    }
}
