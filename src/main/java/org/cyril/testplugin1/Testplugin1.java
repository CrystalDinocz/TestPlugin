package org.cyril.testplugin1;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public final class Testplugin1 extends JavaPlugin {
    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("\nTest Plugin\nON");
        getServer().getPluginManager().registerEvents(new TriggerEvents(), this);
    }
}
