package by.bsuir.poit.transport.singleton;

public final class DataManagerSingleton {
    private static DataManagerSingleton instance;
    public DataManager dataManager;

    private DataManagerSingleton(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public static DataManagerSingleton getInstance() {
        if (instance == null) {
            instance = new DataManagerSingleton(new DataManager());
        }
        return instance;
    }
}