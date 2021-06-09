package by.bsuir.poit.transport.controller;

import by.bsuir.poit.transport.factory.TransportFactory;
import by.bsuir.poit.transport.factory.impl.ExtensionTransportFactory;
import by.bsuir.poit.transport.model.Transport;
import by.bsuir.poit.transport.service.CommonService;
import by.bsuir.poit.transport.service.TransportService;
import by.bsuir.poit.transport.util.ExtensionLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.util.List;
import java.util.Optional;

public class DynamicController {

    @FXML
    private ComboBox<String> typeBox;

    @FXML
    private TextField createModelFiled;

    @FXML
    private TextField createColorField;

    @FXML
    private TextField editModelField;

    @FXML
    private TextField editColorField;

    @FXML
    private Button updateBtn;

    @FXML
    private Button createBtn;

    @FXML
    private TextField editIdField;

    private final TransportFactory<Transport> transportFactory = ExtensionTransportFactory.INSTANCE;
    private final CommonService<Transport> service = TransportService.INSTANCE;

    public void fillCreateComboBox() {
        ObservableList<String> observableList = receiveAvailableTransport();
        typeBox.setItems(observableList);
    }

    public void createTransport(ActionEvent event) {
        String type = typeBox.getValue();
        String model = createModelFiled.getText();
        String color = createColorField.getText();
        if (!(type == null || model.equals("") || color.equals(""))) {
            transportFactory.create(type, model, color);
            Scene scene = createBtn.getScene();
            scene.getWindow().hide();
        }
    }

    @FXML
    void updateTransport(ActionEvent event) {
        Long id = extractLong();
        if (id == null) {
            return;
        }
        String model = editModelField.getText();
        String color = editColorField.getText();
        Optional<Transport> transportOptional = service.findById(id);
        if (model.equals("") || color.equals("") || !transportOptional.isPresent()) {
            return;
        }
        updateEntity(model, color, transportOptional.get());
        Scene scene = updateBtn.getScene();
        scene.getWindow().hide();
    }

    private Long extractLong() {
        long id;
        try {
            id = Long.parseLong(editIdField.getText());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return id;
    }

    private void updateEntity(String model, String color, Transport transport) {
        String className = transport.getClass().getSimpleName();
        transportFactory.create(className, model, color, transport.getId());
        service.delete(transport.getId());
    }

    private ObservableList<String> receiveAvailableTransport() {
        ExtensionLoader<Transport> transportLoader = ExtensionTransportFactory.INSTANCE.getTransportLoader();
        List<String> transports = transportLoader.getExtensionNames();
        return FXCollections.observableList(transports);
    }

}