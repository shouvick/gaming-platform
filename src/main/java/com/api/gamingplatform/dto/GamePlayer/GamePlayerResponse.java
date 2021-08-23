package com.api.gamingplatform.dto.GamePlayer;


import java.io.Serializable;


public class GamePlayerResponse implements Serializable {
    private String sessionId;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
