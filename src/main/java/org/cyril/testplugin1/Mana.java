package org.cyril.testplugin1;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;

public class Mana {
    public static void ManaDisplay(Scoreboard sbmana, String name, int maxMana) {
        Player player = Bukkit.getPlayer(name);
        BukkitRunnable ManaActionBar = new BukkitRunnable() {
            @Override
            public void run() {
                try {
                    player.getScoreboard().getObjective("Mana").getScore("Mana:");
                } catch(NullPointerException e) {
                    player.setScoreboard(sbmana);
                }
                if(player.getScoreboardTags().contains("classswitch")) {
                    player.removeScoreboardTag("manadisplay");
                    cancel();
                }
                int currMana = player.getScoreboard().getObjective("Mana").getScore("Mana:").getScore();
                ActionBar.Display(name, currMana, maxMana);
            }
        };
        if (!player.getScoreboardTags().contains("manadisplay")) {
            player.addScoreboardTag("manadisplay");
            ManaActionBar.runTaskTimer(Testplugin1.getInstance(), 0,0);
        }
    }
    public static void ManaSB(String name) {
        Player player = Bukkit.getPlayer(name);
        int maxMana = 100;
        if(player.getScoreboardTags().contains("mage")) {
            maxMana = maxMana + 30;
        }
        ScoreboardManager SBM = Bukkit.getScoreboardManager();
        Scoreboard sbmana = SBM.getNewScoreboard();
        Objective Mana = sbmana.registerNewObjective("Mana", "dummy");
        Mana.setDisplayName(ChatColor.AQUA + "Mana");
        Mana.setDisplaySlot(DisplaySlot.SIDEBAR);
        Score playerMana = Mana.getScore("Mana:");
        int finalMaxMana = maxMana;
        ManaDisplay(sbmana ,name, finalMaxMana);
        BukkitRunnable ManaRegen = new BukkitRunnable() {
            @Override
            public void run() {
                try {
                    player.getScoreboard().getObjective("Mana").getScore("Mana:");
                } catch(NullPointerException e) {
                    player.setScoreboard(sbmana);
                }
                if(player.getScoreboardTags().contains("classswitch")) {
                    player.removeScoreboardTag("classswitch");
                    player.removeScoreboardTag("manaregen");
                    cancel();
                    ManaSB(name);
                }
                int currmana = player.getScoreboard().getObjective("Mana").getScore("Mana:").getScore();
                if(currmana >= finalMaxMana) {
                    playerMana.setScore(finalMaxMana);
                    player.setScoreboard(sbmana);
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
