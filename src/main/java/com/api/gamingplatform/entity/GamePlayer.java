package com.api.gamingplatform.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.format.annotation.DateTimeFormat;
import reactor.core.publisher.Mono;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Table
public class GamePlayer extends BaseModel {
    private Long game_id;
    private Long player_id;
    private String session_id;
    private LocalDateTime session_start;
    private LocalDateTime session_end;
    private Boolean status;


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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }

    public LocalDateTime getSession_start() {
        return session_start;
    }

    public void setSession_start(LocalDateTime session_start) {
        this.session_start = session_start;
    }

    public LocalDateTime getSession_end() {
        return session_end;
    }

    public void setSession_end(LocalDateTime session_end) {
        this.session_end = session_end;
    }
}
