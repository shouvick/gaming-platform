package com.api.gamingplatform.dto.Player;



import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


public class PlayerRequest implements Serializable {
    @NotBlank(message = "firstName can not be empty")
    private String firstName;
    @NotBlank(message = "lastName can not be empty")
    private String lastName;
    @NotNull(message = "age can not be empty")
    private Integer age;
    @NotBlank(message = "nationality can not be empty")
    private String nationality;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }


    public String getPlayerInfo (String message,PlayerRequest playerRequest) {
        return message + " FirstName: " + playerRequest.getFirstName() +"," + "LastName: " + playerRequest.getLastName() +"," + "Age: " + playerRequest.getAge() +"," + "Nationality: " + playerRequest.getNationality();
    }
}
