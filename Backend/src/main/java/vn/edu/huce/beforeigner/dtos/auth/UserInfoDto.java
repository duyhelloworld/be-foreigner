package vn.edu.huce.beforeigner.dtos.auth;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(value = Include.NON_EMPTY)
public class UserInfoDto {
    
    private String userId;
    
    private String fullname;

    private String avatar;
    
    private String username;
    
    private String email;

}
