package org.xaxaton.event_app.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xaxaton.event_app.dto.MemberDTO;
import org.xaxaton.event_app.mappers.BaseMapper;
import org.xaxaton.event_app.models.Member;
import org.xaxaton.event_app.repo.BaseRepo;
import org.xaxaton.event_app.repo.MemberRepo;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MembersController extends BaseController<Member, MemberDTO> {

    protected final MemberRepo repo;
    protected final BaseMapper<Member, MemberDTO> mapper;

    public MembersController(MemberRepo repo, BaseMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }


    @GetMapping
    public List<MemberDTO> getAll() {
        return getAll(MemberDTO.class);
    }
}
