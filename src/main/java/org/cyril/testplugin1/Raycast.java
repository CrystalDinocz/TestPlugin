package org.cyril.testplugin1;

import org.bukkit.Bukkit;

public class Raycast {
    public static void Command(String name) {
        int i = 0;
        while(i < 15) {
            String teleport = String.format("execute if entity @s[tag=!block,tag=!entity] as @s run summon minecraft:area_effect_cloud ^ ^1 ^%s {Invulnerable:true,NoBasePlate:true,NoGravity:true,Small:true,Tags:[\"laser\"]}", String.valueOf(i+1));
            Bukkit.getServer().dispatchCommand(Bukkit.getPlayer(name), teleport);
            Bukkit.getServer().dispatchCommand(Bukkit.getPlayer(name), "execute at @e[type=minecraft:area_effect_cloud,tag=laser] if entity @e[type=!player,tag=!laser,distance=..1.5,limit=100] run tag @e[distance=..1.5,limit=100,type=!player,tag=!laser] add damage");
            Bukkit.getServer().dispatchCommand(Bukkit.getPlayer(name), "execute at @e[type=minecraft:area_effect_cloud,tag=laser] if entity @e[type=!player,tag=!laser,distance=..1.5,limit=100] run tag @s add entity");
            Bukkit.getServer().dispatchCommand(Bukkit.getPlayer(name), "execute at @e[type=minecraft:area_effect_cloud,tag=laser] unless block ~ ~ ~ minecraft:air run tag @s add block");
            i = i+1;
        }
        Bukkit.getServer().dispatchCommand(Bukkit.getPlayer(name),"playsound minecraft:item.firecharge.use ambient @s ~ ~ ~ 1000 1.3");
        Bukkit.getServer().dispatchCommand(Bukkit.getPlayer(name), "execute if entity @s[tag=gold] at @e[type=minecraft:area_effect_cloud,tag=laser] run particle minecraft:flame ~ ~0.5 ~ 0 0 0 0 10 normal");
        Bukkit.getServer().dispatchCommand(Bukkit.getPlayer(name), "execute if entity @s[tag=iron] at @e[type=minecraft:area_effect_cloud,tag=laser] run particle minecraft:crit ~ ~0.5 ~ 0 0 0 0 10 normal");
        Bukkit.getServer().dispatchCommand(Bukkit.getPlayer(name), "execute if entity @s[tag=emerald] at @e[type=minecraft:area_effect_cloud,tag=laser] run particle minecraft:happy_villager ~ ~0.5 ~ 0 0 0 0 10 normal");
        Bukkit.getServer().dispatchCommand(Bukkit.getPlayer(name), "execute if entity @s[tag=diamond] at @e[type=minecraft:area_effect_cloud,tag=laser] run particle minecraft:soul_fire_flame ~ ~0.5 ~ 0 0 0 0 10 normal");
        Bukkit.getServer().dispatchCommand(Bukkit.getPlayer(name), "execute as @s run kill @e[type=minecraft:area_effect_cloud,tag=laser]");
        Bukkit.getServer().dispatchCommand(Bukkit.getPlayer(name), "execute as @e[type=!minecraft:player,tag=damage] run damage @s 5");
        Bukkit.getServer().dispatchCommand(Bukkit.getPlayer(name), "execute as @s run tag @e remove damage");
        Bukkit.getServer().dispatchCommand(Bukkit.getPlayer(name), "execute as @s run tag @s remove block");
        Bukkit.getServer().dispatchCommand(Bukkit.getPlayer(name), "execute as @s run tag @s remove entity");
    }
}
