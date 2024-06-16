package outBackend.cloudProject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import outBackend.cloudProject.apiPayload.ApiResponse;
import outBackend.cloudProject.converter.MemberConverter;
import outBackend.cloudProject.domain.Member;
import outBackend.cloudProject.security.TokenDto;
import outBackend.cloudProject.service.memberService.AuthService;
import outBackend.cloudProject.dto.MemberRequestDTO;
import outBackend.cloudProject.dto.MemberResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class MemberRestController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ApiResponse<MemberResponseDTO.JoinResultDTO> join(@RequestBody MemberRequestDTO.JoinDTO request){

        Member member = authService.signup(request);
        return ApiResponse.onSuccess(MemberConverter.toJoinResultDTO(member));
    }

    @PostMapping("/login")
    public ApiResponse<MemberResponseDTO.LoginResultDTO> login(@RequestBody MemberRequestDTO.LoginDTO request){

        TokenDto tokenDto = authService.login(request);
        return ApiResponse.onSuccess(MemberConverter.toLoginResultDTO(tokenDto));
    }
}
