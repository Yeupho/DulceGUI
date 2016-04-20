package sample;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RetrieveDrinks {


    public static void getData(ObservableList<ObservableList> data, String SQL) throws SQLException {

        Connection c;
        c = DBconnect.connect();

        // ResultSet
        ResultSet rs = c.createStatement().executeQuery(SQL);

        while (rs.next()) {
            // Iterate Row
            ObservableList<String> row = FXCollections.observableArrayList();
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                // Iterate Column
                String notthere = "---";
                rs.getString(i);
                if (rs.wasNull()) {
                    row.add(notthere);
                } else {
                    row.add(rs.getString(i));
                }
            }
            System.out.println("Row added " + row);
            data.add(row);

        }
    }

    public static void getDataCosts(ArrayList<Double> data, String SQL) throws SQLException {

        Connection c;
        c = DBconnect.connect();

        // ResultSet
        ResultSet rs = c.createStatement().executeQuery(SQL);

        while (rs.next()) {
            // Iterate Row
            ObservableList<Double> row = FXCollections.observableArrayList();
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                // Iterate Column
                Double notthere = 0.0;
                rs.getDouble(i);
                if (rs.wasNull()) {
                    row.add(notthere);
                } else {
                    row.add(rs.getDouble(i));
                    data.add(rs.getDouble(i));
                }
            }
            System.out.println("Row added " + row);
        }


    }


    public static void getDataID(ArrayList<Integer> data, String SQL) throws SQLException {

        Connection c;
        c = DBconnect.connect();

        // ResultSet
        ResultSet rs = c.createStatement().executeQuery(SQL);

        while (rs.next()) {
            // Iterate Row
            ObservableList<Integer> row = FXCollections.observableArrayList();
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                // Iterate Column
                int notthere = 0;
                rs.getDouble(i);
                if (rs.wasNull()) {
                    row.add(notthere);
                } else {
                    row.add(rs.getInt(i));
                    data.add(rs.getInt(i));
                }
            }
            System.out.println("Row added " + row);


        }


    }
}