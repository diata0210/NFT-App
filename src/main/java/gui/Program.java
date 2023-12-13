package gui;

import repository.Repository;
import repository.implement.*;

public class Program {
  private Repository[] repositories = {
      CoinDeskRepositoryImp.getInstance(),
  };

  public Program() {
    for (Repository repo : repositories) {
      repo.loadData();
    }
  }
}
