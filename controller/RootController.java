package pl.sda.openweather.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RootController implements Initializable {

    private String finalURL = "http://api.apixu.com/v1/current.json?key=a147a586068c46f2b4e90949191002&q=";

    @FXML
    private Button search;

    @FXML
    private TextField city;

    @FXML
    private TextField temperature;

    @FXML
    private TextField feelTemperature;

   @FXML
   private ImageView ikona;


    public void initialize(URL location, ResourceBundle resources) {

        city.setText("");
//        temperature.setText("6.0");
//        feelTemperature.setText("2.1");

    }

    public void setCity(ActionEvent actionEvent) {

        try {
            URL jsonURL = new URL(finalURL + city.getText());


            ObjectMapper objectMapper = new ObjectMapper();
            Weather weather = objectMapper.readValue(jsonURL, Weather.class);
            feelTemperature.setText(String.valueOf(weather.getCurrent().getFeelslike_c()));
            temperature.setText(String.valueOf(weather.getCurrent().getTemp_c()));
            ikona.setImage(new Image("http:" + weather.getCurrent().getCondition().getIcon()));



        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
