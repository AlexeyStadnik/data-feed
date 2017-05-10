package by.stadnik.polyvore;


import by.stadnik.polyvore.model.Outfit;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PolyvoreRunner {

  private static String[] criterias =
      {"String Bikinis", "Peep Toe Heels", "Frayed Dresses", "Baby Blue Dresses", "Beach Dresses", "Eyelet Tops", "Mom Jeans", "Pink and Red Outfits", "Linen Dresses", "Spring-to-Summer Layering"}; //"Yellow Sunglasses", "Blind Date", "Pink Handbags", "Statement Fashion", "Leggings and Sandals", "One-Shoulder Dresses", "Casual Weekend Looks", "Blogger Style", "Art Museum Visit", "Rose-Gold Dresses", "Dinner Party", "Feather Accents", "Statement Shoes", "Girly-Girl Outfits", "Beach Day", "Birthday Party", "White Graduation Dresses", "Translucent Sunglasses", "Airport Chic", "Colorful Slides", "Gray T-Shirts", "Cropped Jeans", "Star and Stripes", "Floral Tops", "Fuchsia Skirts", "Navy Outfits", "Bold Accessories", "Trench Coats", "Tangerine", "Bold Patterns", "Top Handle Bags", "Grocery Shopping"};

  public static void main(String[] args) {
    PolyvoreClient polyvoreClient = new PolyvoreClient();
    List<String> hrefs = new ArrayList<>();
    for(String criteria : Arrays.asList(criterias)) {
    hrefs.addAll(polyvoreClient.retrieveHrefs(criteria));
    }
    List<Outfit> outfits = polyvoreClient.retrieveOutfits(hrefs);
    //outfits.parallelStream().forEach(PolyvoreRunner::persistToFileSystem);
  }

  public static void persistToFileSystem(Outfit outfit) {
    ObjectMapper om = new ObjectMapper();

    File dir = new File("output/" + outfit.getOutfitName().replaceAll("/", "").trim().replaceAll(" ", ""));
    dir.mkdirs();

    File outputfile = new File(dir, "outfit.png");
    try {

      ImageIO.write(outfit.getMainImage(), "png", outputfile);
      FileUtils.writeStringToFile(new File(dir, "outfit.json"), om.writeValueAsString(outfit));

    } catch (Exception e) {
      e.printStackTrace();
    }

    outfit.getItemList().parallelStream().filter(im -> im.getItemImage() != null && im.getItemTitle() != null).forEach(item -> {
      File itemFile = new File(dir, item.getItemTitle().trim().replaceAll("/", "").trim().replaceAll(" ", "") + ".png");
      try {
        ImageIO.write(item.getItemImage(), "png", itemFile);
        FileUtils.writeStringToFile(new File(dir, item.getItemTitle().replaceAll("/", "").trim().replaceAll(" ", "") + ".json"), om.writeValueAsString(item));
      } catch (Exception e) {
        e.printStackTrace();
      }

    });
    System.out.println(outfit.getOutfitName() + " :: saved ____________________");
  }
}


