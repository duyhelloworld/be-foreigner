package vn.edu.huce.beforeigner.dtos.auth;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthDto {
    
    private String token;

}
