package xyz.n7mn.dev.api.data;

import java.util.Date;
import java.util.UUID;

public class BanData {
    private int BanID;
    private UUID BanUser;
    private String Reason;
    private String Area;
    private String IP;
    private Date EndDate;
    private Date ExecuteDate;
    private UUID ExecuteUser;
    private boolean Active;

    public BanData(int banID, UUID banUser, String reason, String area, String ip, Date endDate, Date executeDate, UUID executeUser, boolean active){
        this.BanID = banID;
        this.BanUser = banUser;
        this.Reason = reason;
        this.Area = area;
        this.IP = ip;
        this.EndDate = endDate;
        this.ExecuteDate = executeDate;
        this.ExecuteUser = executeUser;
        this.Active = active;
    }

    public int getBanID() {
        return BanID;
    }

    public UUID getBanUser() {
        return BanUser;
    }

    public String getReason() {
        return Reason;
    }

    public String getArea() {
        return Area;
    }

    public String getIP() {
        return IP;
    }

    public Date getEndDate() {
        return EndDate;
    }

    public Date getExecuteDate() {
        return ExecuteDate;
    }

    public UUID getExecuteUser() {
        return ExecuteUser;
    }

    public boolean isActive() {
        return Active;
    }
}
