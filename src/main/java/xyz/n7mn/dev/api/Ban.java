package xyz.n7mn.dev.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import xyz.n7mn.dev.api.data.BanData;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Ban implements NanamiNetwork {

    private final MySQLSetting sql;

    @Deprecated
    public Ban(){
        sql = null;
    }

    public Ban(String pluginFolderPass) throws IOException {
        File file = new File(pluginFolderPass + "/nanaapi-config.json");

        if (!file.exists()){
            Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
            String s = gson.toJson(new MySQLSetting());

            PrintWriter writer = new PrintWriter(pluginFolderPass + "/nanaapi-config.json");
            writer.print(s);
            writer.close();

            throw new FileNotFoundException(pluginFolderPass + "/nanaapi-config.json");
        }

        StringBuilder jsonText = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
        String text;
        while ((text = reader.readLine()) != null) {
            jsonText.append(text);
        }

        sql = new Gson().fromJson(jsonText.toString(), MySQLSetting.class);
    }

    public Ban(String MySQLServerIP, int MySQLServerPort, String MySQLServerDatabase, String MySQLServerOption, String MySQLServerUsername, String MySQLServerPassword){
        sql = new MySQLSetting(MySQLServerIP, MySQLServerPort, MySQLServerDatabase, MySQLServerOption, MySQLServerUsername, MySQLServerPassword);
    }

    @Deprecated
    public List<BanData> getList() throws SQLException, NullPointerException {

        List<BanData> list = new ArrayList<>();
        Connection con = DriverManager.getConnection("jdbc:mysql://" + sql.getMySQLServer() + ":" + sql.getMySQLPort() + "/" + sql.getMySQLDatabase() + sql.getMySQLOption(), sql.getUsername(), sql.getPassword());

        PreparedStatement statement = con.prepareStatement("SELECT * FROM BanList");
        ResultSet set = statement.executeQuery();
        while (set.next()){
            list.add(new BanData(
                    set.getInt("BanID"),
                    UUID.fromString(set.getString(" UserUUID")),
                    set.getString("Reason"),
                    set.getString("Area"),
                    set.getString("IP"),
                    set.getDate("EndDate"),
                    set.getDate("ExecuteDate"),
                    UUID.fromString(set.getString("ExecuteUserUUID")),
                    set.getBoolean("Active")
            ));
        }
        set.close();
        statement.close();
        con.close();

        return list;
    }

    public List<BanData> getList(boolean isActiveOnly) throws SQLException, NullPointerException {

        List<BanData> list = new ArrayList<>();
        Connection con = DriverManager.getConnection("jdbc:mysql://" + sql.getMySQLServer() + ":" + sql.getMySQLPort() + "/" + sql.getMySQLDatabase() + sql.getMySQLOption(), sql.getUsername(), sql.getPassword());

        PreparedStatement statement;
        if (isActiveOnly){
            statement = con.prepareStatement("SELECT * FROM BanList WHERE Active = 1");
        } else {
            statement = con.prepareStatement("SELECT * FROM BanList");
        }
        ResultSet set = statement.executeQuery();
        while (set.next()){
            list.add(new BanData(
                    set.getInt("BanID"),
                    UUID.fromString(set.getString(" UserUUID")),
                    set.getString("Reason"),
                    set.getString("Area"),
                    set.getString("IP"),
                    set.getDate("EndDate"),
                    set.getDate("ExecuteDate"),
                    UUID.fromString(set.getString("ExecuteUserUUID")),
                    set.getBoolean("Active")
            ));
        }
        set.close();
        statement.close();
        con.close();

        return list;
    }

    @Deprecated
    public List<BanData> getList(UUID UserUUID) throws SQLException, NullPointerException {

        List<BanData> list = new ArrayList<>();
        Connection con = DriverManager.getConnection("jdbc:mysql://" + sql.getMySQLServer() + ":" + sql.getMySQLPort() + "/" + sql.getMySQLDatabase() + sql.getMySQLOption(), sql.getUsername(), sql.getPassword());

        PreparedStatement statement = con.prepareStatement("SELECT * FROM BanList WHERE UserUUID = ?");
        statement.setString(1, UserUUID.toString());
        ResultSet set = statement.executeQuery();
        while (set.next()){
            list.add(new BanData(
                    set.getInt("BanID"),
                    UUID.fromString(set.getString(" UserUUID")),
                    set.getString("Reason"),
                    set.getString("Area"),
                    set.getString("IP"),
                    set.getDate("EndDate"),
                    set.getDate("ExecuteDate"),
                    UUID.fromString(set.getString("ExecuteUserUUID")),
                    set.getBoolean("Active")
            ));
        }
        set.close();
        statement.close();
        con.close();

        return list;
    }

    public List<BanData> getList(UUID UserUUID, boolean isActiveOnly) throws SQLException, NullPointerException {

        List<BanData> list = new ArrayList<>();
        Connection con = DriverManager.getConnection("jdbc:mysql://" + sql.getMySQLServer() + ":" + sql.getMySQLPort() + "/" + sql.getMySQLDatabase() + sql.getMySQLOption(), sql.getUsername(), sql.getPassword());

        PreparedStatement statement;
        if (isActiveOnly){
            statement = con.prepareStatement("SELECT * FROM BanList WHERE UserUUID = ? AND Active = 1");
        } else {
            statement = con.prepareStatement("SELECT * FROM BanList WHERE UserUUID = ?");
        }
        statement.setString(1, UserUUID.toString());
        ResultSet set = statement.executeQuery();
        while (set.next()){
            list.add(new BanData(
                    set.getInt("BanID"),
                    UUID.fromString(set.getString(" UserUUID")),
                    set.getString("Reason"),
                    set.getString("Area"),
                    set.getString("IP"),
                    set.getDate("EndDate"),
                    set.getDate("ExecuteDate"),
                    UUID.fromString(set.getString("ExecuteUserUUID")),
                    set.getBoolean("Active")
            ));
        }
        set.close();
        statement.close();
        con.close();

        return list;
    }
}
