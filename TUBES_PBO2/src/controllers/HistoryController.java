package controllers;

import DAO.HistoryDao;
import Model.History;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class HistoryController {

    public TableView<History> HistoryTable;
    public TableColumn<History,Integer> userCol;
    public TableColumn<History,String> winLoseCol;
    public TableColumn<History,String> dateCol;
    private MainController main;

    public void setmain(MainController mainController) {
        this.main=main;
        HistoryDao historyDao = new HistoryDao();
        ObservableList<History> hList = (ObservableList<History>) historyDao.showData();
        HistoryTable.setItems(hList);
        userCol.setCellValueFactory(data->new SimpleObjectProperty(data.getValue().getNameUser()));
        winLoseCol.setCellValueFactory(data->new SimpleObjectProperty(data.getValue().getWinlose()));
        dateCol.setCellValueFactory(data->new SimpleObjectProperty(data.getValue().getTanggal()));
    }
}
