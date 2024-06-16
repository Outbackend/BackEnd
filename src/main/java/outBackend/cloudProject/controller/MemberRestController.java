package outBackend.cloudProject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import outBackend.cloudProject.apiPayload.ApiResponse;
import outBackend.cloudProject.converter.MemberConverter;
import outBackend.cloudProject.converter.TokenConverter;
import outBackend.cloudProject.domain.Member;
import outBackend.cloudProject.domain.SkillTag;
import outBackend.cloudProject.dto.TokenResponseDTO;
import outBackend.cloudProject.dto.TokenDto;
import outBackend.cloudProject.security.TokenProvider;
import outBackend.cloudProject.service.memberService.AuthService;
import outBackend.cloudProject.dto.MemberRequestDTO;
import outBackend.cloudProject.dto.MemberResponseDTO;
import outBackend.cloudProject.service.memberService.MemberService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class MemberRestController {

    private final AuthService authService;
    private final MemberService memberService;

    @GetMapping("/{userid}")
    public ApiResponse<MemberResponseDTO.UserPageDTO> userPage(@PathVariable Long userid){

        Member member = memberService.userInfo(userid);

        return ApiResponse.onSuccess(MemberConverter.toUserPageDTO(member));
    }

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

    @DeleteMapping("/")
    public ApiResponse<String> deleteMember(@RequestHeader("accessToken") String accessToken){

        authService.deleteUser(accessToken);
        return ApiResponse.onSuccess("Successfully deleted member");
    }

    @PatchMapping("/")
    public ApiResponse<MemberResponseDTO.UpdateUserResultDTO> updateMember(@RequestHeader("accessToken") String accessToken,
                                      @RequestBody MemberRequestDTO.updateUserDTO request){

        Member member = authService.updateUser(accessToken, request);
        return ApiResponse.onSuccess(MemberConverter.toUpdateUserResultDTO(member));
    }
}
