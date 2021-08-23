package com.api.gamingplatform.entity;


import org.springframework.data.annotation.Id;
import java.io.Serializable;


public abstract class BaseModel implements Serializable {
    @Id
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
