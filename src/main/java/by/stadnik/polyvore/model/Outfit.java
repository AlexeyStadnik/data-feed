package by.stadnik.polyvore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.awt.image.BufferedImage;
import java.util.List;

public class Outfit {

  private String outfitName;
  private Integer likes;
  @JsonIgnore
  private BufferedImage mainImage;
  @JsonIgnore
  private List<Item> itemList;

  public String getOutfitName() {
    return outfitName;
  }

  public void setOutfitName(String outfitName) {
    this.outfitName = outfitName;
  }

  public Integer getLikes() {
    return likes;
  }

  public void setLikes(Integer likes) {
    this.likes = likes;
  }

  public BufferedImage getMainImage() {
    return mainImage;
  }

  public void setMainImage(BufferedImage mainImage) {
    this.mainImage = mainImage;
  }

  public List<Item> getItemList() {
    return itemList;
  }

  public void setItemList(List<Item> itemList) {
    this.itemList = itemList;
  }
}
