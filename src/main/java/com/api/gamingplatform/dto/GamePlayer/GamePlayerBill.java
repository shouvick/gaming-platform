package com.api.gamingplatform.dto.GamePlayer;

import com.api.gamingplatform.entity.Game;
import com.api.gamingplatform.entity.GamePlayer;
import com.api.gamingplatform.entity.PriceType;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GamePlayerBill implements Serializable {
    private Long id;
    private String sessionStart;
    private String sessionEnd;
    private Long sessionTimeInMinutes;
    private Integer perMinCost;
    private Double bill;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSessionStart() {
        return sessionStart;
    }

    public void setSessionStart(String sessionStart) {
        this.sessionStart = sessionStart;
    }

    public String getSessionEnd() {
        return sessionEnd;
    }

    public void setSessionEnd(String sessionEnd) {
        this.sessionEnd = sessionEnd;
    }

    public Long getSessionTimeInMinutes() {
        return sessionTimeInMinutes;
    }

    public void setSessionTimeInMinutes(Long sessionTimeInMinutes) {
        this.sessionTimeInMinutes = sessionTimeInMinutes;
    }

    public Integer getPerMinCost() {
        return perMinCost;
    }

    public void setPerMinCost(Integer perMinCost) {
        this.perMinCost = perMinCost;
    }

    public Double getBill() {
        return bill;
    }

    public void setBill(Double bill) {
        this.bill = bill;
    }


    public static GamePlayerBill from(GamePlayer gamePlayer, GamePlayerBill gamePlayerBill, Game game) {
        gamePlayerBill.setId(gamePlayer.getId());
        gamePlayerBill.setSessionStart(gamePlayer.getSession_start().toString());
        gamePlayerBill.setSessionEnd(gamePlayer.getSession_end().toString());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime start = LocalDateTime.parse(gamePlayer.getSession_start().toString(), formatter);
        LocalDateTime end = LocalDateTime.parse(gamePlayer.getSession_end().toString(), formatter);
        Duration duration = Duration.between(start, end);
        gamePlayerBill.setSessionTimeInMinutes(duration.toMinutes());
        gamePlayerBill.setPerMinCost(PriceType.getFromPriceType(game.getPerMinuteCost().toString()));
        long totalTime = duration.toMinutes();
        double totalBill = totalTime * PriceType.getFromPriceType(game.getPerMinuteCost().toString());
        gamePlayerBill.setBill(totalBill);
        return gamePlayerBill;
    }




}
