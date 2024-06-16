package outBackend.cloudProject.converter;

import outBackend.cloudProject.dto.TokenRequestDTO;
import outBackend.cloudProject.dto.TokenResponseDTO;

public class TokenConverter {

    public static TokenRequestDTO toTokenRequest(String accessToken, String refreshToken){
        return TokenRequestDTO.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public static TokenResponseDTO toTokenResponse(String accessToken, String refreshToken){
        return TokenResponseDTO.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
