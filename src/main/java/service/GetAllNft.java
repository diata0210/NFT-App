package service;

import java.util.List;
import java.util.ArrayList;
import models.ApiModel;
import service.implement.MintedCollectionServiceImp;
import service.implement.RaribleServiceImp;
import service.implement.EbisuBaySeviceImp;
import service.implement.ImmutableCollectionServiceImp;

public class GetAllNft {

    public static List<ApiModel> allNft() {
        List<ApiModel> allNft = new ArrayList<>();
        EbisuBaySeviceImp ebisuBayService = EbisuBaySeviceImp.getInstance();
        MintedCollectionServiceImp mintedCollectionService = MintedCollectionServiceImp.getInstance();
        RaribleServiceImp raribleService = RaribleServiceImp.getInstance();
        ImmutableCollectionServiceImp immutableCollectionService = ImmutableCollectionServiceImp.getInstance();
        allNft.addAll(ebisuBayService.getAllModels());
        allNft.addAll(mintedCollectionService.getAllModels());
        allNft.addAll(raribleService.getAllModels());
        allNft.addAll(immutableCollectionService.getAllModels());
        return allNft;
    }

    public static void main(String[] args) {
        List<ApiModel> allNft =allNft();
        for (ApiModel nft : allNft) {
            System.out.println(nft);
        }
    }
}
