package by.bsuir.poit.transport.controller;

import by.bsuir.poit.transport.service.TransportService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.regex.Pattern;

public class DeleteController {

    private static final String INT_NUMBER_REGEXP = "\\d+";

    @FXML
    private TextField transportId;

    @FXML
    private Button transportDeleteBtn;

    @FXML
    void deleteTransportById(ActionEvent event) {
        boolean correctInput = Pattern.matches(INT_NUMBER_REGEXP, transportId.getText());
        if (!correctInput) {
            return;
        }
        long id = Long.parseLong(transportId.getText());
        TransportService.INSTANCE.delete(id);
        transportDeleteBtn.getScene().getWindow().hide();
    }
}
