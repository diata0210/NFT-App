package service;

import java.util.List;
import java.util.ArrayList;

import models.ApiModel;
import service.implement.MintedCollectionServiceImp;
import service.implement.RaribleServiceImp;
import service.implement.ImmutableCollectionServiceImp;
import service.implement.EbisuBayServiceImp;

public class GetAllNft {

  private static EbisuBayService ebisuBayService = EbisuBayServiceImp.getInstance();
  private static MintedCollectionService mintedCollectionService = MintedCollectionServiceImp.getInstance();
  private static RaribleService raribleService = RaribleServiceImp.getInstance();
  private static ImmutableCollectionService immutableCollectionService = ImmutableCollectionServiceImp.getInstance();

  public static List<ApiModel> allNft() {
    List<ApiModel> allNft = new ArrayList<>();

    allNft.addAll(ebisuBayService.getAllModels());
    allNft.addAll(mintedCollectionService.getAllModels());
    allNft.addAll(raribleService.getAllModels());
    allNft.addAll(immutableCollectionService.getAllModels());
    return allNft;
  }
}
