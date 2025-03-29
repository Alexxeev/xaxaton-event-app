package org.xaxaton.event_app.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.xaxaton.event_app.dto.EventDTO;
import org.xaxaton.event_app.dto.MemberDTO;
import org.xaxaton.event_app.models.Event;
import org.xaxaton.event_app.models.Member;

import java.util.ArrayList;
import java.util.List;

@Component
public class MemberMapper {

    protected final ModelMapper modelMapper;

    public MemberMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    public MemberDTO toDTO(Member member) {
        return modelMapper.map(member, MemberDTO.class);
    }

    public Member toModel(MemberDTO memberDTO) {
        return modelMapper.map(memberDTO, Member.class);
    }

    public List<MemberDTO> toListOfDTOs(List<Member> members) {
        List<MemberDTO> result = new ArrayList<>();

        for (Member member : members) {
            result.add(toDTO(member));
        }

        return result;
    }

    public List<Member> toListOfModels(List<MemberDTO> memberDTOs) {
        List<Member> result = new ArrayList<>();

        for (MemberDTO memberDTO : memberDTOs) {
            result.add(toModel(memberDTO));
        }

        return result;
    }
}
