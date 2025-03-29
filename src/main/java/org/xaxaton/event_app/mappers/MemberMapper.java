package org.xaxaton.event_app.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.xaxaton.event_app.dto.EventDTO;
import org.xaxaton.event_app.dto.MemberDTO;
import org.xaxaton.event_app.models.Event;
import org.xaxaton.event_app.models.Member;

@Component
public class MemberMapper extends BaseMapper<Member, MemberDTO> {
    public MemberMapper(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    public MemberDTO toDTO(Member member) {
        return modelMapper.map(member, MemberDTO.class);
    }

    @Override
    public Member toModel(MemberDTO memberDTO) {
        return modelMapper.map(memberDTO, Member.class);
    }
}
