package org.xaxaton.event_app.controllers;


import org.springframework.web.bind.annotation.GetMapping;
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
}
