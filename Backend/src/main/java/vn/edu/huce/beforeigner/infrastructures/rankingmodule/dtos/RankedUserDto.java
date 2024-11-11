package vn.edu.huce.beforeigner.infrastructures.rankingmodule.dtos;

public class RankedUserDto {

    public Integer userRank;

    public Long elo;

    public String username;
    
    public String avatar;

    public RankedUserDto(Integer userRank, Long elo, String username, String avatar) {
        this.userRank = userRank;
        this.elo = elo;
        this.username = username;
        this.avatar = avatar;
    }
}
