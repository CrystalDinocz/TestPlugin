package org.cyril.testplugin1;

import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Raycast {
    public static void Summon(String name) {
        World world = Bukkit.getPlayer(name).getWorld();
        Vector vector = Bukkit.getPlayer(name).getEyeLocation().getDirection().setY(0);
        Location rawlocation = Bukkit.getPlayer(name).getLocation();
        rawlocation.setDirection(vector);
        Location location = rawlocation.add(vector);
        Entity entity = world.spawnEntity(location, EntityType.ARMOR_STAND);
        entity.setGravity(false);
        entity.setInvulnerable(true);
        Random random = new Random();
        int rndslash = random.nextInt(100000000);
        String rndtagbase = ("slashbase" + rndslash);
        String rndtagslash = ("slash" + rndslash);
        System.out.println(name + " " + rndtagslash);
        System.out.println(name + " " + rndtagbase);
        entity.addScoreboardTag(rndtagbase);
        entity.setVisibleByDefault(false);
        for(int i = 0; i < 21; i++) {
            double x = (-2 + ((float) i / 5));
            // y = -((x^2)/2) + 1
            float a = -((float) 1/2);
            double y = ((x * x) * a) + 2;
            String slash = String.format("execute at @e[type=armor_stand,tag=%s] run summon minecraft:area_effect_cloud ^%s ^ ^%s {Invisible:true,NoGravity:true,Tags:[\"%s\"]}", rndtagbase, x, y, rndtagslash);
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), slash);
        }
        entity.remove();
        String slashselector = String.format("@e[tag=%s]", rndtagslash);
        List<Entity> slashes = Bukkit.selectEntities(Bukkit.getConsoleSender(), slashselector);
        List<Entity> slashed = new ArrayList<>();
        Particle.DustOptions dust = new Particle.DustOptions(Color.fromRGB(255, 255, 255), 2);
        for(Entity n : slashes) {
            String slashdetect = String.format("@e[type=!player,tag=!%s,tag=!%s,distance=..1.6]", rndtagslash, rndtagbase);
            slashed.addAll(Bukkit.selectEntities(n, slashdetect));
            Location particlelocation = n.getLocation().add(0,1,0);
            Bukkit.getPlayer(name).getWorld().spawnParticle(Particle.DUST, particlelocation, 5, dust);
        }
        slashed = slashed.stream().distinct().collect(Collectors.toList());
        for(Entity n : slashed) {
            if(n instanceof LivingEntity) {
                Vector base = Bukkit.getPlayer(name).getEyeLocation().getDirection();
                n.setVelocity(base.multiply(1.3).setY(0.6));
                int iframes = ((LivingEntity) n).getMaximumNoDamageTicks();
                ((LivingEntity) n).setMaximumNoDamageTicks(0);
                Bukkit.getPlayer(name).sendMessage("Hit an entity: " + n.getType());
                ((LivingEntity) n).damage(25);
                ((LivingEntity) n).setMaximumNoDamageTicks(iframes);
            }
        }
    }
    public static void Command(String name) {
        int i = 0;
        List<Entity> block = (new ArrayList<>());
        if(Bukkit.getPlayer(name).getScoreboardTags().contains("slash")) {
            Bukkit.getPlayer(name).playSound(Bukkit.getPlayer(name), Sound.ENTITY_PLAYER_ATTACK_SWEEP, SoundCategory.AMBIENT, 100, 0);
            Summon(name);
        } else {
            mainloop:
            while (i < 24) {
                block.addAll(Bukkit.selectEntities(Bukkit.getConsoleSender(), "@e[type=minecraft:area_effect_cloud,tag=laser]"));
                for (Entity n : block) {
                    if (n.getLocation().getBlock().getType() != Material.AIR) {
                        Bukkit.getPlayer(name).sendMessage("Hit a block " + n.getLocation().getBlock().getType());
                        break mainloop;
                    }
                }
                String teleport = String.format("execute at %s positioned ~ ~1.4 ~ run summon minecraft:area_effect_cloud ^ ^ ^%s {Invulnerable:true,NoBasePlate:true,NoGravity:true,Small:true,Tags:[\"laser\"]}", name, String.valueOf(((float) i / 2) + 1));
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), teleport);
                i = i + 1;
                block.clear();
            }
            List<Entity> lasers = Bukkit.selectEntities(Bukkit.getConsoleSender(), "@e[type=minecraft:area_effect_cloud,tag=laser]");
            List<Entity> damage = (new ArrayList<>());
            for (Entity n : lasers) {
                damage.addAll(Bukkit.selectEntities(n, "@e[type=!player,tag=!laser,distance=..1.5]"));
            }
            damage = damage.stream().distinct().collect(Collectors.toList());
            for (Entity n : damage) {
                if (n instanceof LivingEntity) {
                    int iframes = ((LivingEntity) n).getMaximumNoDamageTicks();
                    ((LivingEntity) n).setMaximumNoDamageTicks(0);
                    Bukkit.getPlayer(name).sendMessage("Hit an entity: " + n.getType());
                    ((LivingEntity) n).damage(15);
                    ((LivingEntity) n).setMaximumNoDamageTicks(iframes);
                }
            }
            if (Bukkit.getPlayer(name).getScoreboardTags().contains("gold")) {
                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "execute at @e[type=minecraft:area_effect_cloud,tag=laser] run particle minecraft:flame ~ ~ ~ 0 0 0 0 10 normal");
                Bukkit.getPlayer(name).playSound(Bukkit.getPlayer(name), Sound.ITEM_FIRECHARGE_USE, SoundCategory.AMBIENT, 100, 1.3F);
            }
            if (Bukkit.getPlayer(name).getScoreboardTags().contains("iron")) {
                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "execute at @e[type=minecraft:area_effect_cloud,tag=laser] run particle minecraft:crit ~ ~ ~ 0 0 0 0 10 normal");
                Bukkit.getPlayer(name).playSound(Bukkit.getPlayer(name), Sound.ITEM_FIRECHARGE_USE, SoundCategory.AMBIENT, 100, 1.3F);
            }
            if (Bukkit.getPlayer(name).getScoreboardTags().contains("emerald")) {
                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "execute at @e[type=minecraft:area_effect_cloud,tag=laser] run particle minecraft:happy_villager ~ ~ ~ 0 0 0 0 10 normal");
                Bukkit.getPlayer(name).playSound(Bukkit.getPlayer(name), Sound.ITEM_FIRECHARGE_USE, SoundCategory.AMBIENT, 100, 1.3F);
            }
            if (Bukkit.getPlayer(name).getScoreboardTags().contains("diamond")) {
                Bukkit.getPlayer(name).playSound(Bukkit.getPlayer(name), Sound.ITEM_FIRECHARGE_USE, SoundCategory.AMBIENT, 100, 1.3F);
                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "execute at @e[type=minecraft:area_effect_cloud,tag=laser] run particle minecraft:enchanted_hit ~ ~ ~ 0 0 0 0 10 normal");
            }
        }
    }
}