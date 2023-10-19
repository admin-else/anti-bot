package de.adminelse.antibot;

import de.adminelse.antibot.listener.PlayerJoinListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Anti_bot extends JavaPlugin {
    static Anti_bot plugin;
    @Override
    public void onEnable() {
        getLogger().info("wtfff");
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        plugin = this;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Anti_bot getPlugin() {
        return plugin;
    }
}
