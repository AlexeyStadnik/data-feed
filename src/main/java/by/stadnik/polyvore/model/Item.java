package by.stadnik.polyvore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.awt.image.BufferedImage;


public class Item {

  private String itemTitle;
  private String itemRef;
  @JsonIgnore
  private BufferedImage itemImage;
  private Integer itemLikes;

  public String getItemTitle() {
    return itemTitle;
  }

  public void setItemTitle(String itemTitle) {
    this.itemTitle = itemTitle;
  }

  public String getItemRef() {
    return itemRef;
  }

  public void setItemRef(String itemRef) {
    this.itemRef = itemRef;
  }

  public BufferedImage getItemImage() {
    return itemImage;
  }

  public void setItemImage(BufferedImage itemImage) {
    this.itemImage = itemImage;
  }

  public Integer getItemLikes() {
    return itemLikes;
  }

  public void setItemLikes(Integer itemLikes) {
    this.itemLikes = itemLikes;
  }

}
