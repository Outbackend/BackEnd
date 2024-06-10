package outBackend.cloudProject.service.memberService;

import org.springframework.stereotype.Service;
import outBackend.cloudProject.domain.Member;
import outBackend.cloudProject.web.dto.MemberRequestDTO;

@Service
public class MemberCommandServiceImpl implements MemberCommandService{
    @Override
    public Member joinMember(MemberRequestDTO.JoinDTO request) {
        return null;
    }
}
