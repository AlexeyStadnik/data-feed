package by.stadnik.polyvore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.awt.image.BufferedImage;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Outfit {

  private String outfitName;
  private Integer likes;
  @JsonIgnore
  private BufferedImage mainImage;
  @JsonIgnore
  private List<Item> itemList;
}
