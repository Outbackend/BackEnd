package outBackend.cloudProject.service.memberService;

import outBackend.cloudProject.domain.Member;
import outBackend.cloudProject.web.dto.MemberRequestDTO;

public interface MemberCommandService {
    public Member joinMember(MemberRequestDTO.JoinDTO request);
}
