package org.cyril.testplugin1;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

public class ActionBar {
    public static void Display(String name, int currMana, int maxMana) {
        Player player = Bukkit.getPlayer(name);
        try {
            int maxHP = (int) player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
            int currHP = (int) player.getHealth();
            String message = ("§cHealth: " + currHP + "/" + maxHP + "   §bMana: " + currMana + "/" + maxMana);
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
        } catch (NullPointerException ignore) {
        }
    }
}
