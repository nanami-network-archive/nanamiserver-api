package xyz.n7mn.dev.api.data;

import java.util.UUID;

public class RoleData {

    private UUID UUID;
    private String DiscordRoleID;
    private String RoleName;
    private int RoleRank;

    public RoleData(UUID uuid, String discordRoleID, String roleName, int roleRank){
        this.UUID = uuid;
        this.DiscordRoleID = discordRoleID;
        this.RoleName = roleName;
        this.RoleRank = roleRank;
    }

    public UUID getUUID() {
        return UUID;
    }

    public String getDiscordRoleID() {
        return DiscordRoleID;
    }

    public String getRoleName() {
        return RoleName;
    }

    public int getRoleRank() {
        return RoleRank;
    }
}
