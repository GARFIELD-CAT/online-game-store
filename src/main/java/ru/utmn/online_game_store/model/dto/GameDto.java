package ru.utmn.online_game_store.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameDto {
    private Integer id;
    private String title;
    private String description;
    private String genre;
    private double price;
    private String developerName;
    private LocalDate releaseDate;
    private String platform;
    private boolean isAvailable;
    private double averageUserRating;
    private String ageRating;

    public void setIsAvailable(Boolean value){
        this.isAvailable = value;
    }

    public Boolean getIsAvailable(){
        return isAvailable;
    }
}
