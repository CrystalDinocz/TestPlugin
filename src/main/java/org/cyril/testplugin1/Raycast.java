package org.cyril.testplugin1;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Raycast {
    public static void Command(String name) {
        int i = 0;
        List<Entity> block = (new ArrayList<>());
        mainloop:
        while(i < 24) {
            block.addAll(Bukkit.selectEntities(Bukkit.getConsoleSender(), "@e[type=minecraft:area_effect_cloud,tag=laser]"));
            for(Entity n : block) {
                if(n.getLocation().getBlock().getType() != Material.AIR) {
                    Bukkit.broadcastMessage("Hit a block " + n.getLocation().getBlock().getType());
                    break mainloop;
                }
            }
            String teleport = String.format("execute at %s positioned ~ ~1.4 ~ run summon minecraft:area_effect_cloud ^ ^ ^%s {Invulnerable:true,NoBasePlate:true,NoGravity:true,Small:true,Tags:[\"laser\"]}", name, String.valueOf(((float) i/2) +1));
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), teleport);
            i = i+1;
            block.clear();
        }
        List<Entity> lasers = Bukkit.selectEntities(Bukkit.getConsoleSender(), "@e[type=minecraft:area_effect_cloud,tag=laser]");
        List<Entity> damage = (new ArrayList<>());
        for(Entity n : lasers) {
            damage.addAll(Bukkit.selectEntities(n, "@e[type=!player,tag=!laser,distance=..1.5]"));
        }
        damage = damage.stream().distinct().collect(Collectors.toList());
        for(Entity n : damage) {
            if(n instanceof LivingEntity) {
                int iframes = ((LivingEntity) n).getMaximumNoDamageTicks();
                ((LivingEntity) n).setMaximumNoDamageTicks(0);
                Bukkit.broadcastMessage(String.valueOf(n));
                ((LivingEntity) n).damage(15);
                ((LivingEntity) n).setMaximumNoDamageTicks(iframes);
            }
        }
        Bukkit.getPlayer(name).playSound(Bukkit.getPlayer(name), Sound.ITEM_FIRECHARGE_USE, SoundCategory.AMBIENT, 100, 1.3F);
        if(Bukkit.getPlayer(name).getScoreboardTags().contains("gold")) {
            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "execute at @e[type=minecraft:area_effect_cloud,tag=laser] run particle minecraft:flame ~ ~ ~ 0 0 0 0 10 normal");
        }
        if(Bukkit.getPlayer(name).getScoreboardTags().contains("iron")) {
            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "execute at @e[type=minecraft:area_effect_cloud,tag=laser] run particle minecraft:crit ~ ~ ~ 0 0 0 0 10 normal");
        }
        if(Bukkit.getPlayer(name).getScoreboardTags().contains("emerald")) {
            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "execute at @e[type=minecraft:area_effect_cloud,tag=laser] run particle minecraft:happy_villager ~ ~ ~ 0 0 0 0 10 normal");
        }
        if(Bukkit.getPlayer(name).getScoreboardTags().contains("diamond")) {
            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "execute at @e[type=minecraft:area_effect_cloud,tag=laser] run particle minecraft:enchanted_hit ~ ~ ~ 0 0 0 0 10 normal");
        }
    }
}