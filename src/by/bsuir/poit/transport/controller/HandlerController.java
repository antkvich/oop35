package by.bsuir.poit.transport.controller;

import by.bsuir.poit.transport.model.Garage;
import by.bsuir.poit.transport.singleton.DataManagerSingleton;
import by.bsuir.poit.transport.model.Transport;
import by.bsuir.poit.transport.processing.HandleManager;
import by.bsuir.poit.transport.processing.Handler;
import by.bsuir.poit.transport.singleton.DataManager;
import by.bsuir.poit.transport.serialization.JsonSerialization;
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

import java.util.List;
import java.util.Optional;

public class HandlerController {

    @FXML
    private ComboBox<String> saveTypeBox;

    @FXML
    private Button saveBtn;

    @FXML
    private ComboBox<String> loadTypeBox;

    @FXML
    private Button loadBtn;

    private final HandleManager handleManager = HandleManager.INSTANCE;
    private final DataManager dataManager = DataManagerSingleton.getInstance().dataManager;
    private final JsonSerialization jsonSerialization = JsonSerialization.INSTANCE;
    private final CommonService<Transport> service = TransportService.INSTANCE;

    @FXML
    void fillLoadComboBox() {
        ObservableList<String> observableList = receiveAvailableHandlers();
        loadTypeBox.setItems(observableList);
    }

    @FXML
    void load(ActionEvent event) {
        String type = loadTypeBox.getValue();
        if (type == null) {
            return;
        }
        try {
            String dataFromFile = dataManager.loadFromFile();
            String handledData = handleManager.handleAfterLoading(type, dataFromFile);
            Optional<Garage> garage = jsonSerialization.deserializeFromString(handledData);
            garage.ifPresent(value -> service.addAllToStorage(value.getTransports()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        hideLoadWindow();
    }

    @FXML
    void save(ActionEvent event) {
        String type = saveTypeBox.getValue();
        if (type == null) {
            return;
        }
        try {
            String jsonData = fetchJsonData();
            String handledData = handleManager.handleBeforeSaving(type, jsonData);
            dataManager.saveToFile(handledData);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        hideSaveWindow();
    }

    @FXML
    void fillSaveComboBox() {
        ObservableList<String> observableList = receiveAvailableHandlers();
        saveTypeBox.setItems(observableList);
    }

    private void hideSaveWindow() {
        Scene scene = saveBtn.getScene();
        scene.getWindow().hide();
    }

    private void hideLoadWindow() {
        Scene scene = loadBtn.getScene();
        scene.getWindow().hide();
    }

    private ObservableList<String> receiveAvailableHandlers() {
        ExtensionLoader<Handler> extensionLoader = handleManager.getExtensionLoader();
        List<String> handlers = extensionLoader.getExtensionNames();
        return FXCollections.observableList(handlers);
    }

    private String fetchJsonData() {
        List<Transport> transports = service.findAll();
        Garage garage = new Garage(transports);
        return jsonSerialization.serializeToString(garage);
    }
}
