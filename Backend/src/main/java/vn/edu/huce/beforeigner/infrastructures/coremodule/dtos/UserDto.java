package vn.edu.huce.beforeigner.infrastructures.coremodule.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {

    private String id;
    
    private String username;

    private String fullname;

    private String avatar;
}
