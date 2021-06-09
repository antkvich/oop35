package by.bsuir.poit.transport.controller;

import by.bsuir.poit.transport.adapter.ScramblerAdapter;
import by.bsuir.poit.transport.exception.PropertiesReadingException;
import by.bsuir.poit.transport.model.Garage;
import by.bsuir.poit.transport.model.Transport;
import by.bsuir.poit.transport.processing.Handler;
import by.bsuir.poit.transport.proxy.CustomSerializationProxy;
import by.bsuir.poit.transport.serialization.BinarySerialization;
import by.bsuir.poit.transport.serialization.CustomSerialization;
import by.bsuir.poit.transport.serialization.Serialization;
import by.bsuir.poit.transport.singleton.DataManager;
import by.bsuir.poit.transport.serialization.JsonSerialization;
import by.bsuir.poit.transport.service.CommonService;
import by.bsuir.poit.transport.service.TransportService;
import by.bsuir.poit.transport.singleton.DataManagerSingleton;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.List;
import java.util.Optional;

public class MainController {

    private final CommonService<Transport> service = TransportService.INSTANCE;

    @FXML
    private TableView<Transport> transportTable;

    @FXML
    private TableColumn<Transport, String> typeColumn;

    @FXML
    private TableColumn<Transport, Long> idColumn;

    @FXML
    private TableColumn<Transport, String> modelColumn;

    @FXML
    private TableColumn<Transport, String> colorColumn;

    @FXML
    private TableColumn<Transport, String> infoColumn;

    private final DataManager dataManager = DataManagerSingleton.getInstance().dataManager;
    private final JsonSerialization jsonSerialization = JsonSerialization.INSTANCE;
    private final Serialization<Garage> customSerialization = new CustomSerializationProxy();
    private final Handler scrambler = new ScramblerAdapter("secret_password");

    @FXML
    void strongEncrypt(ActionEvent event) {
        try {
            String jsonData = fetchJsonData();
            String handledData = scrambler.preprocess(jsonData);
            dataManager.saveToFile(handledData);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void strongDecrypt(ActionEvent event) {
        try {
            String dataFromFile = dataManager.loadFromFile();
            String handledData = scrambler.postprocess(dataFromFile);
            Optional<Garage> garage = jsonSerialization.deserializeFromString(handledData);
            garage.ifPresent(value -> service.addAllToStorage(value.getTransports()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void loadTransportJson(ActionEvent event) {
        try {
            Optional<Garage> deserialized = jsonSerialization.deserialize();
            if (deserialized.isPresent()) {
                List<Transport> transports = deserialized.get().getTransports();
                service.addAllToStorage(transports);
            }
            updateTable();
        } catch (PropertiesReadingException e) {
            System.out.println(e.getMessage());
        }
    }

    public void loadTransportBinary(ActionEvent event) {
        try {
            Optional<Garage> deserialized = BinarySerialization.INSTANCE.deserialize();
            if (deserialized.isPresent()) {
                List<Transport> transports = deserialized.get().getTransports();
                service.addAllToStorage(transports);
            }
            updateTable();
        } catch (PropertiesReadingException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveTransportBinary(ActionEvent event) {
        List<Transport> transports = service.findAll();
        Garage garage = new Garage(transports);
        try {
            BinarySerialization.INSTANCE.serialize(garage);
        } catch (PropertiesReadingException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveTransportCustom(ActionEvent event) {
        List<Transport> transports = service.findAll();
        Garage garage = new Garage(transports);
        try {
            customSerialization.serialize(garage);
        } catch (PropertiesReadingException e) {
            System.out.println(e.getMessage());
        }
    }

    public void loadTransportCustom(ActionEvent event) {
        try {
            Optional<Garage> deserialized = customSerialization.deserialize();
            if (deserialized.isPresent()) {
                List<Transport> transports = deserialized.get().getTransports();
                service.addAllToStorage(transports);
            }
            updateTable();
        } catch (PropertiesReadingException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveTransportJson(ActionEvent event) {
        List<Transport> transports = service.findAll();
        Garage garage = new Garage(transports);
        try {
            jsonSerialization.serialize(garage);
        } catch (PropertiesReadingException e) {
            System.out.println(e.getMessage());
        }
    }

    public void viewSaveWindow() {
        viewWindow("../view/handle/save.fxml");
    }

    public void viewLoadWindow() {
        viewWindow("../view/handle/load.fxml");
    }

    public void viewOtherCreationWindow() {
        viewWindow("../view/creation/otherTransportCreation.fxml");
    }

    public void viewOtherEditWindow() {
        viewWindow("../view/edit/otherTransportEdit.fxml");
    }

    public void viewCarCreationWindow(ActionEvent event) {
        viewWindow("../view/creation/carCreation.fxml");
    }

    public void viewBusCreationWindow(ActionEvent event) {
        viewWindow("../view/creation/busCreation.fxml");
    }

    public void viewBoatCreationWindow(ActionEvent event) {
        viewWindow("../view/creation/boatCreation.fxml");
    }

    public void viewShipCreationWindow(ActionEvent event) {
        viewWindow("../view/creation/shipCreation.fxml");
    }

    public void viewPlaneCreationWindow(ActionEvent event) {
        viewWindow("../view/creation/planeCreation.fxml");
    }

    public void viewHelicopterCreationWindow(ActionEvent event) {
        viewWindow("../view/creation/helicopterCreation.fxml");
    }

    public void viewTransportDeleteWindow(ActionEvent event) {
        viewWindow("../view/deleteTransport.fxml");
    }

    public void viewCarUpdateWindow(ActionEvent event) {
        viewWindow("../view/edit/carEdit.fxml");
    }

    public void viewBusUpdateWindow(ActionEvent event) {
        viewWindow("../view/edit/busEdit.fxml");
    }

    public void viewPlaneUpdateWindow(ActionEvent event) {
        viewWindow("../view/edit/planeEdit.fxml");
    }

    public void viewHelicopterUpdateWindow(ActionEvent event) {
        viewWindow("../view/edit/helicopterEdit.fxml");
    }

    public void viewBoatUpdateWindow(ActionEvent event) {
        viewWindow("../view/edit/boatEdit.fxml");
    }

    public void viewShipUpdateWindow(ActionEvent event) {
        viewWindow("../view/edit/shipEdit.fxml");
    }

    private void viewWindow(String resourcePath) {
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(resourcePath));
            loader.load();
            root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateTable() {
        List<Transport> transports = service.findAll();
        ObservableList<Transport> observableList = FXCollections.observableList(transports);
        setCellValues();
        transportTable.setItems(observableList);
    }

    private void setCellValues() {
        typeColumn.setCellValueFactory(cellData ->
                new ReadOnlyStringWrapper(cellData.getValue().getClass().getSimpleName()));
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        colorColumn.setCellValueFactory(new PropertyValueFactory<>("color"));
        infoColumn.setCellValueFactory(cellData ->
                new ReadOnlyStringWrapper(cellData.getValue().toString()));
    }

    private String fetchJsonData() {
        List<Transport> transports = service.findAll();
        Garage garage = new Garage(transports);
        return jsonSerialization.serializeToString(garage);
    }
}
