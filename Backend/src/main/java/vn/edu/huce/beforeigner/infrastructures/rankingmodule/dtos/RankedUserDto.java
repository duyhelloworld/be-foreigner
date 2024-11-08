package vn.edu.huce.beforeigner.infrastructures.rankingmodule.dtos;

public class RankedUserDto {

    public Integer rank;

    public Long elo;

    public String username;
    
    public String avatar;

    public RankedUserDto(Integer rank, Long elo, String username, String avatar) {
        this.rank = rank;
        this.elo = elo;
        this.username = username;
        this.avatar = avatar;
    }
}
