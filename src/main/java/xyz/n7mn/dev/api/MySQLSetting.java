package xyz.n7mn.dev.api;

class MySQLSetting {

    private String MySQLServer = "";
    private int MySQLPort = 3306;
    private String MySQLDatabase = "";
    private String MySQLOption = "?allowPublicKeyRetrieval=true&useSSL=false";
    private String Username = "";
    private String Password = "";

    public MySQLSetting(){
        this.MySQLServer = "";
        this.MySQLPort = 3306;
        this.MySQLDatabase = "";
        this.MySQLOption = "?allowPublicKeyRetrieval=true&useSSL=false";
        this.Username = "";
        this.Password = "";
    }

    public MySQLSetting(String mySQLServer, int mySQLPort, String mySQLDatabase, String mySQLOption, String username, String password){
        this.MySQLServer = mySQLServer;
        this.MySQLPort = mySQLPort;
        this.MySQLDatabase = mySQLDatabase;
        this.MySQLOption = mySQLOption;
        this.Username = username;
        this.Password = password;
    }

    public String getMySQLServer() {
        return MySQLServer;
    }

    public int getMySQLPort() {
        return MySQLPort;
    }

    public String getMySQLDatabase(){
        return MySQLDatabase;
    }

    public String getMySQLOption() {
        return MySQLOption;
    }

    public String getUsername() {
        return Username;
    }

    public String getPassword() {
        return Password;
    }
}
