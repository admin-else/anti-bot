package de.adminelse.antibot.listener;

import com.google.gson.JsonObject;
import de.adminelse.antibot.Anti_bot;
import de.adminelse.antibot.Utils;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.net.InetAddress;
import java.util.Objects;

public class PlayerJoinListener implements Listener {
    public static boolean isBadIp(JsonObject jsonObject) {
        JsonObject security = jsonObject.getAsJsonObject("security");
        boolean isProxy = security.get("proxy").getAsBoolean();
        boolean isVpn = security.get("vpn").getAsBoolean();
        boolean isTor = security.get("tor").getAsBoolean();
        boolean isHosting = security.get("hosting").getAsBoolean();

        return isProxy || isVpn || isTor || isHosting;
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        InetAddress addr = Objects.requireNonNull(player.getAddress()).getAddress();
        JsonObject json =  Utils.getJsonResponse(addr.getHostAddress());
        if(json == null) {
            Anti_bot.getPlugin().getLogger().warning("Failed to fetch ip info for ip "+ addr.getHostAddress()+".");
            return;
        }
        if(isBadIp(json)) {
            Anti_bot.getPlugin().getServer().banIP(addr);
            player.kick(Component.text("Your ip is not allowed to access this server!")); //TODO: make this configurable.
            event.joinMessage(null);
        }
    }
}
