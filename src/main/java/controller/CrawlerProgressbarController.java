package controller;

import java.io.IOException;
import java.math.BigDecimal;

import java.net.URL;

import java.util.ResourceBundle;

import crawler.*;
import javafx.beans.value.ChangeListener;

import javafx.beans.value.ObservableValue;

import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;

import javafx.scene.control.Label;

import javafx.scene.control.ProgressBar;


public class CrawlerProgressbarController implements Initializable {
  @FXML
  private ProgressBar myProgressBar;
  @FXML
  private Label myLabel;
  @FXML
  private Button myButton;
  // The BigDecimal class gives its user complete control over rounding behavior
  BigDecimal progress = new BigDecimal(String.format("%.2f", 0.0));
  int count = 0;

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    myProgressBar.setStyle("-fx-accent: #00FF00;");

  }

  public void increaseProgress() throws IOException, java.text.ParseException {
    // zmyButton.setDisable(true);
    switch (count) {
    }
  }

}