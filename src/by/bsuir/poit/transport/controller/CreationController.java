package by.bsuir.poit.transport.controller;

import by.bsuir.poit.transport.factory.impl.BoatFactory;
import by.bsuir.poit.transport.factory.impl.BusFactory;
import by.bsuir.poit.transport.factory.impl.CarFactory;
import by.bsuir.poit.transport.factory.impl.HelicopterFactory;
import by.bsuir.poit.transport.factory.impl.PlaneFactory;
import by.bsuir.poit.transport.factory.impl.ShipFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.regex.Pattern;

public class CreationController {

    private static final String DOUBLE_NUMBER_REGEXP = "^[0-9]+(\\.[0-9]{1,2})?$";
    private static final String INT_NUMBER_REGEXP = "\\d+";

    @FXML
    private TextField carModelInput;

    @FXML
    private TextField carColorInput;

    @FXML
    private TextField carWheelsCountInput;

    @FXML
    private TextField accelerationTimeInput;

    @FXML
    private Button carCreateBtn;

    @FXML
    private TextField busModelInput;

    @FXML
    private TextField busColorInput;

    @FXML
    private TextField busWheelsCountInput;

    @FXML
    private TextField passengerCapacityInput;

    @FXML
    private Button busCreateBtn;

    @FXML
    private TextField boatModelInput;

    @FXML
    private TextField boatColorInput;

    @FXML
    private TextField boatLengthInput;

    @FXML
    private TextField liftingCapacityInput;

    @FXML
    private Button boatCreateBtn;

    @FXML
    private TextField shipModelInput;

    @FXML
    private TextField shipColorInput;

    @FXML
    private TextField shipLengthInput;

    @FXML
    private TextField displacementInput;

    @FXML
    private Button shipCreateBtn;

    @FXML
    private TextField planeModelInput;

    @FXML
    private TextField planeColorInput;

    @FXML
    private TextField planeRangeOfFlightInput;

    @FXML
    private TextField wingspanInput;

    @FXML
    private Button planeCreateBtn;

    @FXML
    private TextField helicopterModelInput;

    @FXML
    private TextField helicopterColorInput;

    @FXML
    private TextField helicopterRangeOfFlightInput;

    @FXML
    private TextField bladesTwistInput;

    @FXML
    private Button helicopterCreateBtn;

    @FXML
    void createCar(ActionEvent event) {
        boolean correctInput = checkInput(carColorInput.getText(), carModelInput.getText(),
                carWheelsCountInput.getText(), accelerationTimeInput.getText());
        boolean numberCorrectInput = Pattern.matches(DOUBLE_NUMBER_REGEXP, accelerationTimeInput.getText())
                && Pattern.matches(INT_NUMBER_REGEXP, carWheelsCountInput.getText());
        if (!correctInput || !numberCorrectInput) {
            return;
        }
        CarFactory.INSTANCE.create(carColorInput.getText(), carModelInput.getText(),
                Integer.valueOf(carWheelsCountInput.getText()), Double.valueOf(accelerationTimeInput.getText()));
        carCreateBtn.getScene().getWindow().hide();
    }

    @FXML
    void createBus(ActionEvent event) {
        boolean correctInput = checkInput(busColorInput.getText(), busModelInput.getText(),
                busWheelsCountInput.getText(), passengerCapacityInput.getText());
        boolean numberCorrectInput = Pattern.matches(INT_NUMBER_REGEXP, passengerCapacityInput.getText())
                && Pattern.matches(INT_NUMBER_REGEXP, busWheelsCountInput.getText());
        if (!correctInput || !numberCorrectInput) {
            return;
        }
        BusFactory.INSTANCE.create(busColorInput.getText(), busModelInput.getText(),
                Integer.valueOf(busWheelsCountInput.getText()), Integer.valueOf(passengerCapacityInput.getText()));
        busCreateBtn.getScene().getWindow().hide();
    }

    @FXML
    void createBoat(ActionEvent event) {
        boolean correctInput = checkInput(boatColorInput.getText(), boatModelInput.getText(),
                boatLengthInput.getText(), liftingCapacityInput.getText());
        boolean numberCorrectInput = Pattern.matches(DOUBLE_NUMBER_REGEXP, boatLengthInput.getText())
                && Pattern.matches(DOUBLE_NUMBER_REGEXP, liftingCapacityInput.getText());
        if (!correctInput || !numberCorrectInput) {
            return;
        }
        BoatFactory.INSTANCE.create(boatColorInput.getText(), boatModelInput.getText(),
                Double.valueOf(boatLengthInput.getText()), Double.valueOf(liftingCapacityInput.getText()));
        boatCreateBtn.getScene().getWindow().hide();
    }

    @FXML
    void createShip(ActionEvent event) {
        boolean correctInput = checkInput(shipColorInput.getText(), shipModelInput.getText(),
                shipLengthInput.getText(), displacementInput.getText());
        boolean numberCorrectInput = Pattern.matches(DOUBLE_NUMBER_REGEXP, shipLengthInput.getText())
                && Pattern.matches(DOUBLE_NUMBER_REGEXP, displacementInput.getText());
        if (!correctInput || !numberCorrectInput) {
            return;
        }
        ShipFactory.INSTANCE.create(shipColorInput.getText(), shipModelInput.getText(),
                Double.valueOf(shipLengthInput.getText()), Double.valueOf(displacementInput.getText()));
        shipCreateBtn.getScene().getWindow().hide();
    }

    @FXML
    void createPlane(ActionEvent event) {
        boolean correctInput = checkInput(planeColorInput.getText(), planeModelInput.getText(),
                planeRangeOfFlightInput.getText(), wingspanInput.getText());
        boolean numberCorrectInput = Pattern.matches(DOUBLE_NUMBER_REGEXP, planeRangeOfFlightInput.getText())
                && Pattern.matches(DOUBLE_NUMBER_REGEXP, wingspanInput.getText());
        if (!correctInput || !numberCorrectInput) {
            return;
        }
        PlaneFactory.INSTANCE.create(planeColorInput.getText(), planeModelInput.getText(),
                Double.valueOf(planeRangeOfFlightInput.getText()), Double.valueOf(wingspanInput.getText()));
        planeCreateBtn.getScene().getWindow().hide();
    }

    @FXML
    void createHelicopter(ActionEvent event) {
        boolean correctInput = checkInput(helicopterColorInput.getText(), helicopterModelInput.getText(),
                helicopterRangeOfFlightInput.getText(), bladesTwistInput.getText());
        boolean numberCorrectInput = Pattern.matches(DOUBLE_NUMBER_REGEXP, helicopterRangeOfFlightInput.getText())
                && Pattern.matches(DOUBLE_NUMBER_REGEXP, bladesTwistInput.getText());
        if (!correctInput || !numberCorrectInput) {
            return;
        }
        HelicopterFactory.INSTANCE.create(helicopterColorInput.getText(), helicopterModelInput.getText(),
                Double.valueOf(helicopterRangeOfFlightInput.getText()), Double.valueOf(bladesTwistInput.getText()));
        helicopterCreateBtn.getScene().getWindow().hide();
    }

    private boolean checkInput(String... params) {
        for (String param : params) {
            if (param == null || param.equals("")) {
                return false;
            }
        }
        return true;
    }
}
