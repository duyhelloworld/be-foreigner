package vn.edu.huce.beforeigner.domains.leaderboard;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Setter;
import vn.edu.huce.beforeigner.domains.core.User;
import lombok.NoArgsConstructor;
import lombok.Getter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class LeaderBoardUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne
    @JoinColumn(nullable = false, unique = false)
    private User user;
    
    @Column(nullable = false)
    private Integer userRank;
    
    @Column(nullable = false)
    private Integer elo;

    @ManyToOne
    @JoinColumn(nullable = false)
    private LeaderBoard leaderBoard;
}
