package by.stadnik.polyvore;


import by.stadnik.polyvore.model.Outfit;

import java.util.List;

public class PolyvoreRunner {

  public static void main(String[] args) {
    PolyvoreClient polyvoreClient = new PolyvoreClient();
    List<String> hrefs = polyvoreClient.retrieveHrefs();
    List<Outfit> outfits = polyvoreClient.retrieveOutfits(hrefs);
    outfits.forEach(outfit -> System.out.print(outfit.getOutfitName()));
  }
}


