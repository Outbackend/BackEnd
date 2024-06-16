package outBackend.cloudProject.service.memberService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import outBackend.cloudProject.domain.Member;
import outBackend.cloudProject.domain.SkillTag;
import outBackend.cloudProject.repository.MemberRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Member userInfo(Long id){

        Member member = memberRepository.findById(id).orElse(null);

        return member;
    }
}
