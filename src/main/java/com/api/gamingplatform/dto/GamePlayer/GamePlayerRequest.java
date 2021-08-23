package com.api.gamingplatform.dto.GamePlayer;




import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class GamePlayerRequest implements Serializable {

    @NotNull(message = "game_id can not be empty")
    private Long game_id;
    @NotNull(message = "player_id can not be empty")
    private Long player_id;


    public Long getGame_id() {
        return game_id;
    }

    public void setGame_id(Long game_id) {
        this.game_id = game_id;
    }

    public Long getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(Long player_id) {
        this.player_id = player_id;
    }






}
