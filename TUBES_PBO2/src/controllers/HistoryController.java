package controllers;

import DAO.HistoryDao;
import Model.History;
import Util.JDBCConnection;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import java.util.HashMap;
import java.util.Map;

public class HistoryController {

    public TableView<History> HistoryTable;
    public TableColumn<History,Integer> userCol;
    public TableColumn<History,String> winLoseCol;
    public TableColumn<History,String> dateCol;
    private MainController main;

    public void setmain(MainController main) {
        this.main=main;
        HistoryDao historyDao = new HistoryDao();
        ObservableList<History> hList = (ObservableList<History>) historyDao.showData();
        HistoryTable.setItems(hList);
        userCol.setCellValueFactory(data->new SimpleObjectProperty(data.getValue().getNameUser()));
        winLoseCol.setCellValueFactory(data->new SimpleObjectProperty(data.getValue().getWinlose()));
        dateCol.setCellValueFactory(data->new SimpleObjectProperty(data.getValue().getTanggal()));
    }

    public void printHistoryAction(ActionEvent actionEvent) {
        JasperPrint jp;
        Map param = new HashMap();

        try{
            jp = JasperFillManager.fillReport("./Report/TubesPBO.jasper",param,JDBCConnection.getConnection());
            JasperViewer viewer= new JasperViewer(jp,false);
            viewer.setTitle("History");
            viewer.setVisible(true);
        }
        catch (JRException e) {
            System.out.println(e.getMessage());
        }
    }
}
