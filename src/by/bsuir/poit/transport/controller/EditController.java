package by.bsuir.poit.transport.controller;

import by.bsuir.poit.transport.model.Transport;
import by.bsuir.poit.transport.model.impl.Boat;
import by.bsuir.poit.transport.model.impl.Bus;
import by.bsuir.poit.transport.model.impl.Car;
import by.bsuir.poit.transport.model.impl.Helicopter;
import by.bsuir.poit.transport.model.impl.Plane;
import by.bsuir.poit.transport.model.impl.Ship;
import by.bsuir.poit.transport.service.CommonService;
import by.bsuir.poit.transport.service.TransportService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.regex.Pattern;

public class EditController {

    private static final String DOUBLE_NUMBER_REGEXP = "^[0-9]+(\\.[0-9]{1,2})?$";
    private static final String INT_NUMBER_REGEXP = "\\d+";
    private static final CommonService<Transport> service = TransportService.INSTANCE;

    @FXML
    private TextField carIdInput;

    @FXML
    private TextField carModelInput;

    @FXML
    private TextField carColorInput;

    @FXML
    private TextField carWheelsCountInput;

    @FXML
    private TextField accelerationTimeInput;

    @FXML
    private Button carUpdateBtn;

    @FXML
    private TextField busModelInput;

    @FXML
    private TextField busColorInput;

    @FXML
    private TextField busWheelsCountInput;

    @FXML
    private TextField passengerCapacityInput;

    @FXML
    private TextField busIdInput;

    @FXML
    private Button busUpdateBtn;

    @FXML
    private TextField boatModelInput;

    @FXML
    private TextField boatColorInput;

    @FXML
    private TextField boatLengthInput;

    @FXML
    private TextField liftingCapacityInput;

    @FXML
    private Button boatUpdateBtn;

    @FXML
    private TextField boatIdInput;

    @FXML
    private TextField shipModelInput;

    @FXML
    private TextField shipColorInput;

    @FXML
    private TextField shipLengthInput;

    @FXML
    private TextField displacementInput;

    @FXML
    private Button shipUpdateBtn;

    @FXML
    private TextField shipIdInput;

    @FXML
    private TextField helicopterModelInput;

    @FXML
    private TextField helicopterColorInput;

    @FXML
    private TextField helicopterRangeOfFlightInput;

    @FXML
    private TextField bladesTwistInput;

    @FXML
    private Button helicopterUpdateBtn;

    @FXML
    private TextField helicopterIdInput;

    @FXML
    private TextField planeModelInput;

    @FXML
    private TextField planeColorInput;

    @FXML
    private TextField planeRangeOfFlightInput;

    @FXML
    private TextField wingspanInput;

    @FXML
    private Button planeUpdateBtn;

    @FXML
    private TextField planeIdInput;

    @FXML
    void updateCar(ActionEvent event) {
        boolean correctInput = checkInput(carIdInput.getText(), carColorInput.getText(), carModelInput.getText(),
                carWheelsCountInput.getText(), accelerationTimeInput.getText());
        boolean numberCorrectInput = Pattern.matches(DOUBLE_NUMBER_REGEXP, accelerationTimeInput.getText())
                && Pattern.matches(INT_NUMBER_REGEXP, carWheelsCountInput.getText())
                && Pattern.matches(INT_NUMBER_REGEXP, carIdInput.getText());
        if (!correctInput || !numberCorrectInput) {
            return;
        }
        Car updatedCar = new Car(Long.parseLong(carIdInput.getText()), carColorInput.getText(), carModelInput.getText(),
                Integer.parseInt(carWheelsCountInput.getText()), Double.parseDouble(accelerationTimeInput.getText()));
        service.update(updatedCar);
        carUpdateBtn.getScene().getWindow().hide();
    }

    @FXML
    void updateBus(ActionEvent event) {
        boolean correctInput = checkInput(busIdInput.getText(), busColorInput.getText(), busModelInput.getText(),
                busWheelsCountInput.getText(), passengerCapacityInput.getText());
        boolean numberCorrectInput = Pattern.matches(INT_NUMBER_REGEXP, passengerCapacityInput.getText())
                && Pattern.matches(INT_NUMBER_REGEXP, busWheelsCountInput.getText())
                && Pattern.matches(INT_NUMBER_REGEXP, busIdInput.getText());
        if (!correctInput || !numberCorrectInput) {
            return;
        }
        Bus updatedBus = new Bus(Long.parseLong(busIdInput.getText()), busColorInput.getText(), busModelInput.getText(),
                Integer.parseInt(busWheelsCountInput.getText()), Integer.parseInt(passengerCapacityInput.getText()));
        service.update(updatedBus);
        busUpdateBtn.getScene().getWindow().hide();
    }

    @FXML
    void updateBoat(ActionEvent event) {
        boolean correctInput = checkInput(boatIdInput.getText(), boatColorInput.getText(), boatModelInput.getText(),
                boatLengthInput.getText(), liftingCapacityInput.getText());
        boolean numberCorrectInput = Pattern.matches(DOUBLE_NUMBER_REGEXP, boatLengthInput.getText())
                && Pattern.matches(DOUBLE_NUMBER_REGEXP, liftingCapacityInput.getText())
                && Pattern.matches(INT_NUMBER_REGEXP, boatIdInput.getText());
        if (!correctInput || !numberCorrectInput) {
            return;
        }
        Boat updatedBoat = new Boat(Long.parseLong(boatIdInput.getText()), boatColorInput.getText(),
                boatModelInput.getText(), Double.parseDouble(boatLengthInput.getText()),
                Double.parseDouble(liftingCapacityInput.getText()));
        service.update(updatedBoat);
        boatUpdateBtn.getScene().getWindow().hide();
    }

    @FXML
    void updateShip(ActionEvent event) {
        boolean correctInput = checkInput(shipIdInput.getText(), shipColorInput.getText(), shipModelInput.getText(),
                shipLengthInput.getText(), displacementInput.getText());
        boolean numberCorrectInput = Pattern.matches(DOUBLE_NUMBER_REGEXP, shipLengthInput.getText())
                && Pattern.matches(DOUBLE_NUMBER_REGEXP, displacementInput.getText())
                && Pattern.matches(INT_NUMBER_REGEXP, shipIdInput.getText());
        if (!correctInput || !numberCorrectInput) {
            return;
        }
        Ship updatedShip = new Ship(Long.parseLong(shipIdInput.getText()), shipColorInput.getText(),
                shipModelInput.getText(), Double.parseDouble(shipLengthInput.getText()),
                Double.parseDouble(displacementInput.getText()));
        service.update(updatedShip);
        shipUpdateBtn.getScene().getWindow().hide();
    }

    @FXML
    void updateHelicopter(ActionEvent event) {
        boolean correctInput = checkInput(helicopterIdInput.getText(), helicopterColorInput.getText(),
                helicopterModelInput.getText(),
                helicopterRangeOfFlightInput.getText(), bladesTwistInput.getText());
        boolean numberCorrectInput = Pattern.matches(DOUBLE_NUMBER_REGEXP, helicopterRangeOfFlightInput.getText())
                && Pattern.matches(DOUBLE_NUMBER_REGEXP, bladesTwistInput.getText())
                && Pattern.matches(INT_NUMBER_REGEXP, helicopterIdInput.getText());
        if (!correctInput || !numberCorrectInput) {
            return;
        }
        Helicopter updatedHelicopter = new Helicopter(Integer.parseInt(helicopterIdInput.getText()),
                helicopterColorInput.getText(), helicopterModelInput.getText(),
                Double.parseDouble(helicopterRangeOfFlightInput.getText()),
                Double.parseDouble(bladesTwistInput.getText()));
        service.update(updatedHelicopter);
        helicopterUpdateBtn.getScene().getWindow().hide();
    }

    @FXML
    void updatePlane(ActionEvent event) {
        boolean correctInput = checkInput(planeIdInput.getText(), planeColorInput.getText(), planeModelInput.getText(),
                planeRangeOfFlightInput.getText(), wingspanInput.getText());
        boolean numberCorrectInput = Pattern.matches(DOUBLE_NUMBER_REGEXP, planeRangeOfFlightInput.getText())
                && Pattern.matches(DOUBLE_NUMBER_REGEXP, wingspanInput.getText())
                && Pattern.matches(INT_NUMBER_REGEXP, planeIdInput.getText());
        if (!correctInput || !numberCorrectInput) {
            return;
        }
        Plane updatedPlane = new Plane(Long.parseLong(planeIdInput.getText()), planeColorInput.getText(),
                planeModelInput.getText(), Double.parseDouble(planeRangeOfFlightInput.getText()),
                Double.parseDouble(wingspanInput.getText()));
        service.update(updatedPlane);
        planeUpdateBtn.getScene().getWindow().hide();
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
