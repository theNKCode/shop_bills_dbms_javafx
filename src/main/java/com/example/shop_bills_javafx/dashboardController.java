package com.example.shop_bills_javafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class dashboardController implements Initializable {
    @FXML
    private AnchorPane main_form;
    @FXML
    private Button close;

    @FXML
    private Button cust_Btn;

    @FXML
    private Button dash_Btn;

    @FXML
    private BarChart<?, ?> dash_IChart;

    @FXML
    private Label dash_NC;

    @FXML
    private BarChart<?, ?> dash_NOChart;

    @FXML
    private Label dash_Today;

    @FXML
    private AnchorPane dash_form;

    @FXML
    private Label dash_total;

    @FXML
    private Button goods_Btn;

    @FXML
    private Button goods_addBtn;

    @FXML
    private Button goods_clearBtn;

    @FXML
    private Button goods_deleteBtn;

    @FXML
    private AnchorPane goods_form;

    @FXML
    private TextField goods_productID;

    @FXML
    private TextField goods_productName;

    @FXML
    private TextField goods_productPrice;

    @FXML
    private ComboBox<?> goods_productStatus;

    @FXML
    private ComboBox<?> goods_productType;

    @FXML
    private TextField goods_search;

    @FXML
    private Button goods_updateBtn;

    @FXML
    private TableView<items> goodsc_tableView;

    @FXML
    private TableColumn<items, String> goodsc_productID;

    @FXML
    private TableColumn<items, String> goodsc_productName;

    @FXML
    private TableColumn<items, String> goodsc_productPrice;

    @FXML
    private TableColumn<items, String> goodsc_productStatus;

    @FXML
    private TableColumn<items, String> goodsc_productType;

    @FXML
    private Button logout;

    @FXML
    private Button minimize;

    @FXML
    private Button order_Btn;

    @FXML
    private Button order_add;

    @FXML
    private TextField order_amount;

    @FXML
    private AnchorPane order_form;

    @FXML
    private Button order_payBtn;

    @FXML
    private Label order_priceOne;

    @FXML
    private ComboBox<?> order_productID;

    @FXML
    private ComboBox<?> order_productName;

    @FXML
    private Spinner<?> order_quantity;

    @FXML
    private Button order_receiptBtn;

    @FXML
    private Button order_removeBtn;

    @FXML
    private TableView<?> order_tableView;

    @FXML
    private Label order_total;

    @FXML
    private TableColumn<?, ?> orderc_productID;

    @FXML
    private TableColumn<?, ?> orderc_productName;

    @FXML
    private TableColumn<?, ?> orderc_productPrice;

    @FXML
    private TableColumn<?, ?> orderc_productQuantity;

    @FXML
    private TableColumn<?, ?> orderc_productType;

    @FXML
    private Label username;


    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    //    public void dashboardNC() {
//        String sql = "SELECT COUNT(id) FROM product_info";
//
//        int nc = 0;
//
//        connect = database.connectDB();
//
//        try {
//            statement = connect.createStatement();
//            result = statement.executeQuery(sql);
//
//            if (result.next()) {
//                nc = result.getInt("COUNT(id)");
//            }
//
//            dash_NC.setText(String.valueOf(nc));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void dashboardTI() {
//        Date date = new Date();
//        Date sqlDate = new Date(date.getTime());
//
//        String sql = "SELECT SUM(total) FROM product_info WHERE date = '" + sqlDate + "'";
//
//        connect = database.connectDB();
//
//        double ti = 0;
//
//        try {
//            statement = connect.createStatement();
//            result = statement.executeQuery(sql);
//
//            if (result.next()) {
//                ti = result.getDouble("SUM(total)");
//            }
//
//            dash_Today.setText("$" + String.valueOf(ti));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void dashboardTIncome() {
//        String sql = "SELECT SUM(total) FROM product_info";
//
//        connect = database.connectDB();
//
//        double ti = 0;
//
//        try {
//            statement = connect.createStatement();
//            result = statement.executeQuery(sql);
//
//            if (result.next()) {
//                ti = result.getDouble("SUM(total)");
//            }
//            dash_total.setText("$" + String.valueOf(ti));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void dashboardNOCCChart() {
//        try {
//            dash_NOChart.getData().clear();
//
//            String sql = "SELECT date, COUNT(id) FROM product_info GROUP BY date ORDER BY TIMESTAMP(date) ASC LIMIT 5";
//
//            connect = database.connectDB();
//
//            XYChart.Series chart = new XYChart.Series();
//
//            prepare = connect.prepareStatement(sql);
//            result = prepare.executeQuery();
//
//            while (result.next()) {
//                chart.getData().add(new XYChart.Data(result.getString(1), result.getInt(2)));
//            }
//
//            dash_NOCChart.getData().add(chart);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void dashboardICC() {
//        dash_IChart.getData().clear();
//
//        String sql = "SELECT date, SUM(total) FROM product_info GROUP BY date ORDER BY TIMESTAMP(total) ASC LIMIT 7";
//
//        connect = database.connectDB();
//
//        try {
//            XYChart.Series chart = new XYChart.Series();
//
//            prepare = connect.prepareStatement(sql);
//            result = prepare.executeQuery();
//
//            while (result.next()) {
//                chart.getData().add(new XYChart.Data(result.getString(1), result.getDouble(2)));
//            }
//
//            dash_IChart.getData().add(chart);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
    public void goodsAdd() {
        String sql = "INSERT INTO items (product_id, product_name, type, price, status) " + "VALUES(?,?,?,?,?)";

        connect = database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, goods_productID.getText());
            prepare.setString(2, goods_productName.getText());
            prepare.setString(3, (String) goods_productType.getSelectionModel().getSelectedItem());
            prepare.setString(4, goods_productPrice.getText());
            prepare.setString(5, (String) goods_productStatus.getSelectionModel().getSelectedItem());

            Alert alert;

            if (goods_productID.getText().isEmpty() || goods_productName.getText().isEmpty() || goods_productType.getSelectionModel() == null || goods_productPrice.getText().isEmpty() || goods_productStatus.getSelectionModel() == null) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                String checkData = "SELECT product_id FROM items WHERE product_id = '" + goods_productID.getText() + "'";

                connect = database.connectDB();

                statement = connect.createStatement();
                result = statement.executeQuery(checkData);

                if (result.next()) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Product ID: " + goods_productID.getText() + " is already exist!");
                    alert.showAndWait();
                } else {
                    prepare.executeUpdate();

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added!");
                    alert.showAndWait();

                    // TO SHOW THE DATA
                    goodsShowData();
                    // TO CLEAR THE FIELDS
                    goodsClear();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void goodsUpdate() {
        String sql = "UPDATE items SET product_name = '" + goods_productName.getText() + "', type = '" + goods_productType.getSelectionModel().getSelectedItem() + "', price = '" + goods_productPrice.getText() + "', status = '" + goods_productStatus.getSelectionModel().getSelectedItem() + "' WHERE product_id = '" + goods_productID.getText() + "'";

        connect = database.connectDB();

        try {
            Alert alert;

            if (goods_productID.getText().isEmpty() || goods_productName.getText().isEmpty() || goods_productType.getSelectionModel().getSelectedItem() == null || goods_productPrice.getText().isEmpty() || goods_productStatus.getSelectionModel().getSelectedItem() == null) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE Product ID: " + goods_productID.getText() + "?");

                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();

                    statement = connect.createStatement();
                    statement.executeUpdate(sql);

                    // TO SHOW THE DATA
                    goodsShowData();
                    // TO CLEAR THE FIELDS
                    goodsClear();
                } else {
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Cancelled.");
                    alert.showAndWait();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void goodsDelete() {
        String sql = "DELETE FROM items WHERE product_id = '" + goods_productID.getText() + "'";

        connect = database.connectDB();

        try {
            Alert alert;

            if (goods_productID.getText().isEmpty() || goods_productName.getText().isEmpty() || goods_productType.getSelectionModel().getSelectedItem() == null || goods_productPrice.getText().isEmpty() || goods_productStatus.getSelectionModel().getSelectedItem() == null) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to DELETE Product ID: " + goods_productID.getText() + "?");

                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted!");
                    alert.showAndWait();

                    statement = connect.createStatement();
                    statement.executeUpdate(sql);

                    // TO SHOW THE DATA
                    goodsShowData();
                    // TO CLEAR THE FIELDS
                    goodsClear();
                } else {
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Cancelled.");
                    alert.showAndWait();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void goodsClear() {
        goods_productID.setText("");
        goods_productName.setText("");
        goods_productType.getSelectionModel().clearSelection();
        goods_productPrice.setText("");
        goods_productStatus.getSelectionModel().clearSelection();
    }

    public ObservableList<items> goodsListData() {
        ObservableList<items> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM items";

        connect = database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            items cat;

            while (result.next()) {
                cat = new items(result.getString("product_id"), result.getString("product_name"), result.getString("type"), result.getDouble("price"), result.getString("status"));

                listData.add(cat);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    public void goodsSearch() {
        FilteredList<items> filter = new FilteredList<>(goodsList, e -> true);

        goods_search.textProperty().addListener((observabl, newValue, oldValue) -> {
            filter.setPredicate(predicateitems -> {
                if (newValue.isEmpty() || newValue == null) {
                    return true;
                }

                String searchKey = newValue.toLowerCase();

                if (predicateitems.getProductId().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateitems.getName().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateitems.getType().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateitems.getPrice().toString().contains(searchKey)) {
                    return true;
                } else if (predicateitems.getStatus().toLowerCase().contains(searchKey)) {
                    return true;
                } else {
                    return false;
                }
            });
        });

        SortedList<items> sortList = new SortedList<>(filter);

        sortList.comparatorProperty().bind(goodsc_tableView.comparatorProperty());
        goodsc_tableView.setItems(sortList);
    }


    private ObservableList<items> goodsList;

    public void goodsShowData() {
        goodsList = goodsListData();

        goodsc_productID.setCellValueFactory(new PropertyValueFactory<>("productId"));
        goodsc_productName.setCellValueFactory(new PropertyValueFactory<>("name"));
        goodsc_productType.setCellValueFactory(new PropertyValueFactory<>("type"));
        goodsc_productPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        goodsc_productStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        goodsc_tableView.setItems(goodsList);
    }


    public void goodsSelect() {
        items catData = goodsc_tableView.getSelectionModel().getSelectedItem();

        int num = goodsc_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        goods_productID.setText(catData.getProductId());
        goods_productName.setText(catData.getName());
        goods_productPrice.setText(String.valueOf(catData.getPrice()));
    }

    private String[] items = {"Electrical", "Electronics"};

    public void goodsType() {
        List<String> listCat = new ArrayList<>();

        for (String data : items) {
            listCat.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listCat);
        goods_productType.setItems(listData);
    }

    private String[] status = {"Available", "Not Available"};

    public void goodsStatus() {
        List<String> listStatus = new ArrayList<>();

        for (String data : status) {
            listStatus.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listStatus);
        goods_productStatus.setItems(listData);
    }

    //    public void orderAdd() {
//        orderCustomerId();
//        orderTotal();
//
//        String sql = "INSERT INTO product " + "(customer_id, product_id, product_name, type, price, quantity, date) " + "VALUES(?,?,?,?,?,?,?)";
//
//        connect = database.connectDb();
//
//        try {
//            String orderType = "";
//            double orderPrice = 0;
//
//            String checkData = "SELECT * FROM items WHERE product_id = '" + order_productID.getSelectionModel().getSelectedItem() + "'";
//
//            statement = connect.createStatement();
//            result = statement.executeQuery(checkData);
//
//            if (result.next()) {
//                orderType = result.getString("type");
//                orderPrice = result.getDouble("price");
//            }
//
//            prepare = connect.prepareStatement(sql);
//            prepare.setString(1, String.valueOf(customerId));
//            prepare.setString(2, (String) order_productID.getSelectionModel().getSelectedItem());
//            prepare.setString(3, (String) order_productName.getSelectionModel().getSelectedItem());
//            prepare.setString(4, orderType);
//
//            double totalPrice = orderPrice * qty;
//
//            prepare.setString(5, String.valueOf(totalPrice));
//
//            prepare.setString(6, String.valueOf(qty));
//
//            Date date = new Date();
//            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
//
//            prepare.setString(7, String.valueOf(sqlDate));
//
//            prepare.executeUpdate();
//
//            orderDisplayTotal();
//            orderDisplayData();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void orderPay() {
//        orderCustomerId();
//        orderTotal();
//
//        String sql = "INSERT INTO product_info (customer_id, total, date) VALUES(?,?,?)";
//
//        connect = database.connectDb();
//
//        try {
//            Alert alert;
//
//            if (balance == 0 || String.valueOf(balance) == "$0.0" || String.valueOf(balance) == null || totalP == 0 || String.valueOf(totalP) == "$0.0" || String.valueOf(totalP) == null) {
//                alert = new Alert(AlertType.ERROR);
//                alert.setTitle("Error Message");
//                alert.setHeaderText(null);
//                alert.setContentText("Invalid :3");
//                alert.showAndWait();
//            } else {
//                alert = new Alert(AlertType.CONFIRMATION);
//                alert.setTitle("Confirmation Message");
//                alert.setHeaderText(null);
//                alert.setContentText("Are you sure?");
//                Optional<ButtonType> option = alert.showAndWait();
//
//                if (option.get().equals(ButtonType.OK)) {
//                    prepare = connect.prepareStatement(sql);
//                    prepare.setString(1, String.valueOf(customerId));
//                    prepare.setString(2, String.valueOf(totalP));
//
//                    Date date = new Date();
//                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
//
//                    prepare.setString(3, String.valueOf(sqlDate));
//
//                    prepare.executeUpdate();
//
//                    alert = new Alert(AlertType.INFORMATION);
//                    alert.setTitle("Information Message");
//                    alert.setHeaderText(null);
//                    alert.setContentText("Successful!");
//                    alert.showAndWait();
//
//                    order_total.setText("$0.0");
//                    order_balance.setText("$0.0");
//                    order_amount.setText("");
//                } else {
//                    alert = new Alert(AlertType.ERROR);
//                    alert.setTitle("Information Message");
//                    alert.setHeaderText(null);
//                    alert.setContentText("Cancelled!");
//                    alert.showAndWait();
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private double totalP = 0;
//
//    public void orderTotal() {
//        orderCustomerId();
//
//        String sql = "SELECT SUM(price) FROM product WHERE customer_id = " + customerId;
//
//        connect = database.connectDb();
//
//        try {
//            prepare = connect.prepareStatement(sql);
//            result = prepare.executeQuery();
//
//            if (result.next()) {
//                totalP = result.getDouble("SUM(price)");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private double amount;
//    private double balance;
//
//    public void orderAmount() {
//        orderTotal();
//
//        Alert alert;
//
//        if (order_amount.getText().isEmpty() || order_amount.getText() == null || order_amount.getText() == "") {
//            alert = new Alert(AlertType.ERROR);
//            alert.setHeaderText(null);
//            alert.setContentText("Please type the amount!");
//            alert.showAndWait();
//        } else {
//            amount = Double.parseDouble(order_amount.getText());
//
//            if (amount < totalP) {
//                order_amount.setText("");
//            } else {
//                balance = (amount - totalP);
//                order_balance.setText("$" + String.valueOf(balance));
//            }
//        }
//    }
//
//    public void orderDisplayTotal() {
//        orderTotal();
//        order_total.setText("$" + String.valueOf(totalP));
//    }
//
//    public ObservableList<product> orderListData() {
//        orderCustomerId();
//
//        ObservableList<product> listData = FXCollections.observableArrayList();
//
//        String sql = "SELECT * FROM product WHERE customer_id = " + customerId;
//
//        connect = database.connectDb();
//
//        try {
//            prepare = connect.prepareStatement(sql);
//            result = prepare.executeQuery();
//
//            product prod;
//
//            while (result.next()) {
//                prod = new product(result.getInt("id"), result.getString("product_id"), result.getString("product_name"), result.getString("type"), result.getDouble("price"), result.getInt("quantity"));
//
//                listData.add(prod);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return listData;
//    }
//
//    //    LETS CREATE OUR RECEIPT : )
//    public void orderReceipt() {
//        HashMap hash = new HashMap();
//
//        hash.put("data_parameter", customerId);
//
//        try {
//            Alert alert;
//            if (totalP == 0) {
//                alert = new Alert(AlertType.ERROR);
//                alert.setTitle("Error Message");
//                alert.setHeaderText(null);
//                alert.setContentText("Invalid :3");
//                alert.showAndWait();
//            } else {
//                JasperDesign jDesign = JRXmlLoader.load("D:\\Users\\WINDOWS 10\\Downloads\\RestaurantManagementSys\\src\\restaurantmanagementsys\\report.jrxml");
//                JasperReport jReport = JasperCompileManager.compileReport(jDesign);
//                JasperPrint jPrint = JasperFillManager.fillReport(jReport, hash, connect);
//
//                JasperViewer.viewReport(jPrint, false);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void orderRemove() {
//        String sql = "DELETE FROM product WHERE id = " + item;
//
//        connect = database.connectDb();
//
//        try {
//            Alert alert;
//
//            if (item == 0 || String.valueOf(item) == null || String.valueOf(item) == "") {
//                alert = new Alert(AlertType.ERROR);
//                alert.setTitle("Error Message");
//                alert.setHeaderText(null);
//                alert.setContentText("Please select the item first");
//                alert.showAndWait();
//            } else {
//                alert = new Alert(AlertType.CONFIRMATION);
//                alert.setTitle("Confirmation Message");
//                alert.setHeaderText(null);
//                alert.setContentText("Are you sure you want to Remove Item: " + item + "?");
//                Optional<ButtonType> option = alert.showAndWait();
//
//                if (option.get().equals(ButtonType.OK)) {
//                    statement = connect.createStatement();
//                    statement.executeUpdate(sql);
//
//                    alert = new Alert(AlertType.INFORMATION);
//                    alert.setTitle("Information Message");
//                    alert.setHeaderText(null);
//                    alert.setContentText("Successfully Removed!");
//                    alert.showAndWait();
//
//                    orderDisplayData();
//                    orderDisplayTotal();
//
//                    order_amount.setText("");
//                    order_balance.setText("$0.0");
//                } else {
//                    alert = new Alert(AlertType.ERROR);
//                    alert.setTitle("Information Message");
//                    alert.setHeaderText(null);
//                    alert.setContentText("Cancelled!");
//                    alert.showAndWait();
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private int item;
//
//    public void orderSelectData() {
//        product prod = order_tableView.getSelectionModel().getSelectedItem();
//        int num = order_tableView.getSelectionModel().getSelectedIndex();
//
//        if ((num - 1) < -1) {
//            return;
//        }
//
//        item = prod.getId();
//    }
//
//    private ObservableList<product> orderData;
//
//    public void orderDisplayData() {
//        orderData = orderListData();
//
//        order_col_productID.setCellValueFactory(new PropertyValueFactory<>("productId"));
//        order_col_productName.setCellValueFactory(new PropertyValueFactory<>("name"));
//        order_col_tyoe.setCellValueFactory(new PropertyValueFactory<>("type"));
//        order_col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
//        order_col_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
//
//        order_tableView.setItems(orderData);
//    }
//
//    private int customerId;
//
//    public void orderCustomerId() {
//        String sql = "SELECT customer_id FROM product";
//
//        connect = database.connectDb();
//
//        try {
//            prepare = connect.prepareStatement(sql);
//            result = prepare.executeQuery();
//
//            while (result.next()) {
//                customerId = result.getInt("customer_id");
//            }
//
//            String checkData = "SELECT customer_id FROM product_info";
//
//            statement = connect.createStatement();
//            result = statement.executeQuery(checkData);
//
//            int customerInfoId = 0;
//
//            while (result.next()) {
//                customerInfoId = result.getInt("customer_id");
//            }
//
//            if (customerId == 0) {
//                customerId += 1;
//            } else if (customerId == customerInfoId) {
//                customerId += 1;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void orderProductId() {
//        String sql = "SELECT product_id FROM items WHERE status = 'Available'";
//
//        connect = database.connectDb();
//
//        try {
//            prepare = connect.prepareStatement(sql);
//            result = prepare.executeQuery();
//
//            ObservableList listData = FXCollections.observableArrayList();
//
//            while (result.next()) {
//                listData.add(result.getString("product_id"));
//            }
//
//            order_productID.setItems(listData);
//
//            orderProductName();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void orderProductName() {
//        String sql = "SELECT product_name FROM items WHERE product_id = '" + order_productID.getSelectionModel().getSelectedItem() + "'";
//
//        connect = database.connectDb();
//
//        try {
//            prepare = connect.prepareStatement(sql);
//            result = prepare.executeQuery();
//
//            ObservableList listData = FXCollections.observableArrayList();
//
//            while (result.next()) {
//                listData.add(result.getString("product_name"));
//            }
//
//            order_productName.setItems(listData);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private SpinnerValueFactory<Integer> spinner;
//
//    public void orderSpinner() {
//        spinner = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 50, 0);
//
//        order_quantity.setValueFactory(spinner);
//    }
//
//    private int qty;
//
//    public void orderQuantity() {
//        qty = order_quantity.getValue();
//    }
//
    public void switchForm(ActionEvent event) {
        if (event.getSource() == dash_Btn) {
            dash_form.setVisible(true);
            goods_form.setVisible(false);
            order_form.setVisible(false);

            dash_Btn.setStyle("-fx-background-color: #6ea8e1; -fx-text-fill: #fff; -fx-border-width: 0px;");
            goods_Btn.setStyle("-fx-background-color: transparent; -fx-border-width: 1px; -fx-text-fill: #000;");
            order_Btn.setStyle("-fx-background-color: transparent; -fx-border-width: 1px; -fx-text-fill: #000;");

//            dashboardNC();
//            dashboardTI();
//            dashboardTIncome();
//            dashboardNOCCChart();
//            dashboardICC();
        } else if (event.getSource() == goods_Btn) {
            dash_form.setVisible(false);
            goods_form.setVisible(true);
            order_form.setVisible(false);

            goods_Btn.setStyle("-fx-background-color: #6ea8e1; -fx-text-fill: #fff; -fx-border-width: 0px;");
            dash_Btn.setStyle("-fx-background-color: transparent; -fx-border-width: 1px; -fx-text-fill: #000;");
            order_Btn.setStyle("-fx-background-color: transparent; -fx-border-width: 1px; -fx-text-fill: #000;");

            goodsShowData();
            goodsSearch();
        } else if (event.getSource() == order_Btn) {
            dash_form.setVisible(false);
            goods_form.setVisible(false);
            order_form.setVisible(true);

            order_Btn.setStyle("-fx-background-color: #6ea8e1; -fx-text-fill: #fff; -fx-border-width: 0px;");
            goods_Btn.setStyle("-fx-background-color: transparent; -fx-border-width: 1px; -fx-text-fill: #000;");
            dash_Btn.setStyle("-fx-background-color: transparent; -fx-border-width: 1px; -fx-text-fill: #000;");

//            orderProductId();
//            orderProductName();
//            orderSpinner();
//            orderDisplayData();
//            orderDisplayTotal();
        }
    }

    private double x = 0;
    private double y = 0;

    public void logout() {
        try {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to logout?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {
                logout.getScene().getWindow().hide();

                // LINK YOUR LOGIN FORM
                Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);

                root.setOnMousePressed((MouseEvent event) -> {
                    x = event.getSceneX();
                    y = event.getSceneY();
                });

                root.setOnMouseDragged((MouseEvent event) -> {
                    stage.setX(event.getScreenX() - x);
                    stage.setY(event.getScreenY() - y);

                    stage.setOpacity(.8f);
                });

                root.setOnMouseReleased((MouseEvent event) -> {
                    stage.setOpacity(1);
                });

                stage.initStyle(StageStyle.TRANSPARENT);

                stage.setScene(scene);
                stage.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void displayUsername() {
        String user = data.username;
        user = user.substring(0, 1).toUpperCase() + user.substring(1);
        username.setText(user);
    }

    public void close() {
        System.exit(0);
    }

    public void minimize() {
        Stage stage = (Stage) main_form.getScene().getWindow();
        stage.setIconified(true);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        dashboardNC();
//        dashboardTI();
//        dashboardTIncome();
//        dashboardNOCCChart();
//        dashboardICC();
        displayUsername();
        goodsStatus();
        goodsType();
        goodsShowData();
//
//        orderProductId();
//        orderProductName();
//        orderSpinner();
//        orderDisplayData();
//        orderDisplayTotal();
    }
}
