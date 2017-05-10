package by.stadnik.polyvore;


import by.stadnik.polyvore.model.Outfit;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class PolyvoreRunner {

  public static void main(String[] args) {
    PolyvoreClient polyvoreClient = new PolyvoreClient();
    List<String> hrefs = polyvoreClient.retrieveHrefs();
    List<Outfit> outfits = polyvoreClient.retrieveOutfits(hrefs);
    outfits.parallelStream().forEach(PolyvoreRunner::persistToFileSystem);
  }

  public static void persistToFileSystem(Outfit outfit) {
    ObjectMapper om = new ObjectMapper();

    File dir = new File("output\\" + outfit.getOutfitName());
    dir.mkdirs();

    File outputfile = new File(dir, outfit.getOutfitName().trim() + ".png");
    try {

      ImageIO.write(outfit.getMainImage(), "png", outputfile);
      FileUtils.writeStringToFile(new File(dir, "meta.json"), om.writeValueAsString(outfit));

    } catch (Exception e) {
      e.printStackTrace();
    }

    outfit.getItemList().parallelStream().filter(im -> im.getItemImage() != null && im.getItemTitle() != null).forEach(item -> {
      File itemFile = new File(dir, item.getItemTitle().trim() + ".png");
      try {
        ImageIO.write(item.getItemImage(), "png", itemFile);
        FileUtils.writeStringToFile(new File(dir, item.getItemTitle() + ".json"), om.writeValueAsString(item));
      } catch (Exception e) {
        e.printStackTrace();
      }

    });
    System.out.println(outfit.getOutfitName() + " :: saved ____________________");
  }
}


