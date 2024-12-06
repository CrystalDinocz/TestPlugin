package org.cyril.testplugin1;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;

public class Mana {
    public static void ManaSB(String name) {
        Player player = Bukkit.getPlayer(name);
        ScoreboardManager SBM = Bukkit.getScoreboardManager();
        Scoreboard sbmana = SBM.getNewScoreboard();
        Objective Mana = sbmana.registerNewObjective("Mana", "dummy");
        Mana.setDisplayName(ChatColor.AQUA + "Mana");
        Mana.setDisplaySlot(DisplaySlot.SIDEBAR);
        Score playerMana = Mana.getScore("Mana:");
        BukkitRunnable ManaRegen = new BukkitRunnable() {
            @Override
            public void run() {
                int currmana = player.getScoreboard().getObjective("Mana").getScore("Mana:").getScore();
                if(currmana >= 100) {
                    player.removeScoreboardTag("manaregen");
                    cancel();
                } else {
                    playerMana.setScore(currmana + 1);
                    player.setScoreboard(sbmana);
                }
            }
        };
        if(!player.getScoreboardTags().contains("manaregen")) {
            player.addScoreboardTag("manaregen");
            ManaRegen.runTaskTimer(Testplugin1.getInstance(),35,2);
        }
    }
}
