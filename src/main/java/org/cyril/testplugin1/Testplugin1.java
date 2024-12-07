package org.cyril.testplugin1;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class Testplugin1 extends JavaPlugin {
    private static Testplugin1 instance;
    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        System.out.println("\nTest Plugin\nON");
        getServer().getPluginManager().registerEvents(new TriggerEvents(), this);
        for(Player n : Bukkit.getOnlinePlayers()) {
            n.removeScoreboardTag("manaregen");
            n.removeScoreboardTag("cooldown");
            n.removeScoreboardTag("gsinair");
            Mana.ManaSB(n.getName());
        }
    }
    public static Testplugin1 getInstance() {
        return instance;
    }
}
