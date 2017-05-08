package by.stadnik.polyvore.model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.List;

public class Outfit {

    private String outfitName;
    private Integer likes;

    private BufferedImage mainImage;
    private List<Item> itemList;

    public Outfit() {
    }

    public Outfit(String outfitName, Integer likes, BufferedImage mainImage) {
        this.outfitName = outfitName;
        this.likes = likes;
        this.mainImage = mainImage;
    }

    public Outfit(String outfitName, Integer likes, BufferedImage mainImage, List<Item> itemList) {
        this.outfitName = outfitName;
        this.likes = likes;
        this.mainImage = mainImage;
        this.itemList = itemList;
    }

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
