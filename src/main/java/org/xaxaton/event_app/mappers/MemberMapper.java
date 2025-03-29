package org.xaxaton.event_app.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.xaxaton.event_app.dto.MemberDTO;
import org.xaxaton.event_app.models.Member;

@Component
public class MemberMapper extends BaseMapper<Member, MemberDTO> {
    public MemberMapper(ModelMapper modelMapper) {
        super(modelMapper);
    }
}
