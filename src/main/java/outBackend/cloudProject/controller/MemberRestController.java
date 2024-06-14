package outBackend.cloudProject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import outBackend.cloudProject.apiPayload.ApiResponse;
import outBackend.cloudProject.converter.MemberConverter;
import outBackend.cloudProject.converter.TokenConverter;
import outBackend.cloudProject.domain.Member;
import outBackend.cloudProject.dto.TokenResponseDTO;
import outBackend.cloudProject.dto.TokenDto;
import outBackend.cloudProject.service.memberService.AuthService;
import outBackend.cloudProject.dto.MemberRequestDTO;
import outBackend.cloudProject.dto.MemberResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class MemberRestController {

    private final AuthService authService;

//    @GetMapping
//    public ApiResponse<MemberResponseDTO.UserPageDTO> userPage(@RequestHeader("accessToken") String accessToken){
//
//    }

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

    @PostMapping("/reissue")
    public ApiResponse<TokenResponseDTO> reissue(@RequestHeader("accessToken") String accessToken,
                                                 @RequestHeader("refreshToken") String refreshToken){

        TokenDto tokenDto = authService.reissue(TokenConverter.toTokenRequest(accessToken, refreshToken));
        return ApiResponse.onSuccess(TokenConverter.toTokenResponse(accessToken, refreshToken));
    }
}
