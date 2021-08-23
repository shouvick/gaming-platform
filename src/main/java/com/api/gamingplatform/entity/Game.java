package com.api.gamingplatform.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotNull;


@Table
public class Game extends BaseModel {
    private String name;
    private Long maxConcurrentUsers;
    private PriceType perMinuteCost;
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

    public PriceType getPerMinuteCost() {
        return perMinuteCost;
    }

    public void setPerMinuteCost(PriceType perMinuteCost) {
        this.perMinuteCost = perMinuteCost;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }
}
