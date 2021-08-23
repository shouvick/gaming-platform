package com.api.gamingplatform.dto.Game;


import com.api.gamingplatform.entity.PriceType;
import com.api.gamingplatform.validator.ValidPriceTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public class GameRequest {

    @NotBlank(message = "name can not be empty")
    private String name;

    @NotNull(message = "maxConcurrentUsers can not be empty")
    private Long maxConcurrentUsers;

    @Schema(type = "string", allowableValues = { "One", "Five", "Ten"})
    @ValidPriceTypeEnum(enumClass = PriceType.class)
    @NotBlank(message = "perMinuteCost can not be empty")
    private String perMinuteCost;

    @NotNull(message = "Client Id can not be empty")
    private Long clientId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getMaxConcurrentUsers() {
        return maxConcurrentUsers;
    }

    public void setMaxConcurrentUsers(Long maxConcurrentUsers) {
        this.maxConcurrentUsers = maxConcurrentUsers;
    }

    public String getPerMinuteCost() {
        return perMinuteCost;
    }

    public void setPerMinuteCost(String perMinuteCost) {
        this.perMinuteCost = perMinuteCost;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getGameInfo(String message, GameRequest gameRequest) {
        return message + " Name: " + gameRequest.name +"," + "Max Concurrent Users: " + gameRequest.getMaxConcurrentUsers() +"," + "Per Minute Cost: " + gameRequest.getPerMinuteCost() +"," + "Client Id: " + gameRequest.getClientId();
    }
}
