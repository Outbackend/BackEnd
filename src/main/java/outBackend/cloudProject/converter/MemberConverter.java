package outBackend.cloudProject.converter;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import outBackend.cloudProject.domain.Member;
import outBackend.cloudProject.domain.SkillTag;
import outBackend.cloudProject.domain.enums.Authority;
import outBackend.cloudProject.domain.mapping.MemberSkillTag;
import outBackend.cloudProject.security.TokenDto;
import outBackend.cloudProject.web.dto.MemberRequestDTO;
import outBackend.cloudProject.web.dto.MemberResponseDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MemberConverter {

    public static Member toMember(MemberRequestDTO.JoinDTO request, PasswordEncoder passwordEncoder){

        return Member.builder()
                .nickName(request.getNickName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassWord()))
                .memberSkillTagList(new ArrayList<>())
                .authority(Authority.ROLE_USER)
                .build();
    }

    public static MemberResponseDTO.JoinResultDTO toJoinResultDTO(Member member){

        return MemberResponseDTO.JoinResultDTO.builder()
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
        return new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassWord());
    }
}
