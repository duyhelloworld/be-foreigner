package vn.edu.huce.beforeigner.infrastructures.coremodule.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserInfoDto {
    
    private String fullname;

    private String avatar;
    
    private String username;
    
    private String email;
}
