package dev.harish.sit;

import org.bukkit.plugin.java.JavaPlugin;

public final class Sit extends JavaPlugin {

    private SitManager sitManager;

    @Override
    public void onEnable() {
        sitManager = new SitManager();

        getCommand("sit").setExecutor(new SitCommand(sitManager));
        getServer().getPluginManager().registerEvents(new SitListener(sitManager), this);
        getCommand("sitreload").setExecutor(new SitReloadCommand(this));
        getLogger().info("Sit plugin has been enabled!");
    }

    @Override
    public void onDisable() {
        sitManager.cleanupAll();
        getLogger().info("Sit plugin has been disabled!");
    }

    public SitManager getSitManager() {
        return sitManager;
    }

}
