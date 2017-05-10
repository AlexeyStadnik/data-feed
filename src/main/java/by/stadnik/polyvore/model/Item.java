package by.stadnik.polyvore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import java.util.List;

import java.awt.image.BufferedImage;


public class Item {

  private String itemTitle;
  @JsonIgnore
  private String itemRef;
  @JsonIgnore
  private BufferedImage itemImage;
  @JsonIgnore
  private Integer itemLikes;
  private List<String> categories;
  private String desc;

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

  public List<String> getCategories() {
    return categories;
  }

  public void setCategories(List<String> categories) {
    this.categories = categories;
  }

  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }
}
