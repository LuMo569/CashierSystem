package com.example.cashiersystem.Model;

import com.example.cashiersystem.Views.AccountType;
import com.example.cashiersystem.Views.ViewFactory;

import java.sql.ResultSet;

public class Model {
    private static Model model;
    private final ViewFactory viewFactory;
    private final DatabaseDriver databaseDriver;
    private AccountType loginAccountType = AccountType.WAITER;

    // Waiter Data Section
    private final Waiter waiter;
    private boolean waiterLoginSuccessFlag;
    private final Order order;
    private final Report report;
    private final Reservation reservation;


    // Admin Data Section
    private final Admin admin;
    private boolean adminLoginSuccessFlag;
    private final Item item;
    private final LogEntry logEntry;

    private Model() {
        this.viewFactory = new ViewFactory();
        this.databaseDriver = new DatabaseDriver();

        // Waiter Data Section
        this.waiterLoginSuccessFlag = false;
        this.waiter = new Waiter("", "", -1);
        this.order = new Order(-1, -1, -1, null, null, null);
        this.report = new Report(null,null, null, null , null, null, null);
        this.reservation = new Reservation(0, "", "", null, 0, "", null);

        // Admin Data Section
        this.adminLoginSuccessFlag = false;
        this.admin = new Admin("", "", -1);
        this.item = new Item(0, "", 0d);
        this.logEntry = new LogEntry(null, "");
    }

    public static synchronized Model getInstance() {
        if (model == null) {
            model = new Model();
        }
        return model;
    }

    public ViewFactory getViewFactory() {
        return viewFactory;
    }

    public DatabaseDriver getDatabaseDriver() {
        return databaseDriver;
    }

    /*
     * ------------------------- Utility Method Section -------------------------
     * */

    public AccountType getLoginAccountType() {return this.loginAccountType;}
    public void setLoginAccountType(AccountType loginAccountType) {this.loginAccountType = loginAccountType;}

    public void evaluateLoginCred(String username, String password, AccountType accountType) {
        ResultSet rs = databaseDriver.getLoginData(username, password, accountType.name());

        try {
            if (rs != null && rs.isBeforeFirst() && rs.next()) {
                if (accountType == AccountType.ADMIN) {
                    // Admin-Daten setzen
                    this.admin.nameProperty().set(rs.getString("username"));
                    this.admin.passwordProperty().set(rs.getString("password"));
                    this.admin.AdminIdProperty().set(rs.getInt("id"));

                    Model.getInstance().setAdminLoginSuccessFlag(true);
                } else if (accountType == AccountType.WAITER) {
                    // Waiter-Daten setzen
                    this.waiter.nameProperty().set(rs.getString("username"));
                    this.waiter.passwordProperty().set(rs.getString("password"));
                    this.waiter.waiterIdProperty().set(rs.getInt("id"));

                    Model.getInstance().setWaiterLoginSuccessFlag(true);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    * ------------------------- Waiter Method Section -------------------------
    * */
    public boolean getWaiterLoginSuccessFlag() {return this.waiterLoginSuccessFlag;}
    public void setWaiterLoginSuccessFlag(boolean flag) {this.waiterLoginSuccessFlag = flag;}

    public Waiter getWaiter() {
        return waiter;
    }

    public Order getOrder() {
        return order;
    }

    public Report getReport() {
        return report;
    }

    public Reservation getReservation() {
        return reservation;
    }

        /*
     * ------------------------- Admin Method Section -------------------------
     * */

    public boolean getAdminLoginSuccessFlag() {return this.adminLoginSuccessFlag;}
    public void setAdminLoginSuccessFlag(boolean flag) {this.adminLoginSuccessFlag = flag;}

    public Item getItem() {
        return item;
    }

}
