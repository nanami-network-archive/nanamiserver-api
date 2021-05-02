package xyz.n7mn.dev.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import xyz.n7mn.dev.api.data.RoleData;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Role implements NanamiNetwork {

    private final MySQLSetting sql;

    @Deprecated
    public Role(){
        sql = null;
    }

    public Role(String pluginFolderPass) throws IOException {
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

    public Role(String MySQLServerIP, int MySQLServerPort, String MySQLServerDatabase, String MySQLServerOption, String MySQLServerUsername, String MySQLServerPassword){
        sql = new MySQLSetting(MySQLServerIP, MySQLServerPort, MySQLServerDatabase, MySQLServerOption, MySQLServerUsername, MySQLServerPassword);
    }

    public List<RoleData> getList() throws SQLException, NullPointerException {

        List<RoleData> list = new ArrayList<>();
        Connection con = DriverManager.getConnection("jdbc:mysql://" + sql.getMySQLServer() + ":" + sql.getMySQLPort() + "/" + sql.getMySQLDatabase() + sql.getMySQLOption(), sql.getUsername(), sql.getPassword());

        PreparedStatement statement = con.prepareStatement("SELECT * FROM RoleRankList");
        ResultSet set = statement.executeQuery();

        while (set.next()){
            list.add(new RoleData(
                    UUID.fromString(set.getString("UUID")),
                    set.getString("DiscordRoleID"),
                    set.getString("Name"),
                    set.getInt("Rank")
            ));
        }
        set.close();
        statement.close();
        con.close();

        return list;
    }

    public List<RoleData> getList(UUID UUID) throws SQLException, NullPointerException {
        List<RoleData> list = new ArrayList<>();
        Connection con = DriverManager.getConnection("jdbc:mysql://" + sql.getMySQLServer() + ":" + sql.getMySQLPort() + "/" + sql.getMySQLDatabase() + sql.getMySQLOption(), sql.getUsername(), sql.getPassword());

        PreparedStatement statement = con.prepareStatement("SELECT * FROM RoleRankList WHERE UUID = ?");
        statement.setString(1, UUID.toString());
        ResultSet set = statement.executeQuery();

        while (set.next()){
            list.add(new RoleData(
                    UUID.fromString(set.getString("UUID")),
                    set.getString("DiscordRoleID"),
                    set.getString("Name"),
                    set.getInt("Rank")
            ));
        }
        set.close();
        statement.close();
        con.close();

        return list;
    }

    @Deprecated
    public List<RoleData> getList(int Rank) throws SQLException, NullPointerException {
        List<RoleData> list = new ArrayList<>();
        Connection con = DriverManager.getConnection("jdbc:mysql://" + sql.getMySQLServer() + ":" + sql.getMySQLPort() + "/" + sql.getMySQLDatabase() + sql.getMySQLOption(), sql.getUsername(), sql.getPassword());

        PreparedStatement statement = con.prepareStatement("SELECT * FROM RoleRankList WHERE Rank = ?");
        statement.setInt(1, Rank);
        ResultSet set = statement.executeQuery();

        while (set.next()){
            list.add(new RoleData(
                    UUID.fromString(set.getString("UUID")),
                    set.getString("DiscordRoleID"),
                    set.getString("Name"),
                    set.getInt("Rank")
            ));
        }
        set.close();
        statement.close();
        con.close();

        return list;
    }
}
