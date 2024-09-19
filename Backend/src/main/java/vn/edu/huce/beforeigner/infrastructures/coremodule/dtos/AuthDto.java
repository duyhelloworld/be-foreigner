package vn.edu.huce.beforeigner.infrastructures.coremodule.dtos;

import lombok.Builder;

@Builder
public class AuthDto {
    
    public String access;

    public String refresh;
}
