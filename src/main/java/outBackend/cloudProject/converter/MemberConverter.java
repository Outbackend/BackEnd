package outBackend.cloudProject.converter;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import outBackend.cloudProject.domain.Member;
import outBackend.cloudProject.domain.enums.Authority;
import outBackend.cloudProject.dto.TokenDto;
import outBackend.cloudProject.dto.MemberRequestDTO;
import outBackend.cloudProject.dto.MemberResponseDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MemberConverter {

    public static Member toMember(MemberRequestDTO.JoinDTO request, PasswordEncoder passwordEncoder){

        return Member.builder()
                .nickName(request.getNickName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .memberSkillTagList(new ArrayList<>())
                .authority(Authority.ROLE_USER)
                .build();
    }

    public static MemberResponseDTO.UserPageDTO toUserPageDTO(Member member){

        List<String> skillTagList = member.getMemberSkillTagList().stream()
                .map(memberSkillTag -> {
                    return memberSkillTag.getSkillTag().getName();
                }).collect(Collectors.toList());

        List<String> projectList = member.getMemberProjectList().stream()
                .map(memberProject -> {
                    return memberProject.getProject().getTitle();
                }).collect(Collectors.toList());

        return MemberResponseDTO.UserPageDTO.builder()
                .id(member.getId())
                .nickName(member.getNickName())
                .intro(member.getIntro())
                .about(member.getAbout())
                .skillTagList(skillTagList)
                .projectList(projectList)
                .build();
    }

    public static MemberResponseDTO.JoinResultDTO toJoinResultDTO(Member member){

        return MemberResponseDTO.JoinResultDTO.builder()
                .id(member.getId())
                .nickName(member.getNickName())
                .build();
    }

    public static MemberResponseDTO.LoginResultDTO toLoginResultDTO(TokenDto tokenDto){

        return MemberResponseDTO.LoginResultDTO.builder()
                .accessToken(tokenDto.getAccessToken())
                .refreshToken(tokenDto.getRefreshToken())
                .build();
    }

    public static UsernamePasswordAuthenticationToken toAuthentication(MemberRequestDTO.LoginDTO loginRequest){
        return new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
    }

    public static MemberResponseDTO.UpdateUserResultDTO toUpdateUserResultDTO(Member member){

        List<String> skillTagList = member.getMemberSkillTagList().stream()
                .map(memberSkillTag -> {
                    return memberSkillTag.getSkillTag().getName();
                }).collect(Collectors.toList());

        return MemberResponseDTO.UpdateUserResultDTO.builder()
                .nickName(member.getNickName())
                .intro(member.getIntro())
                .about(member.getAbout())
                .skillTagList(skillTagList)
                .build();
    }
}
