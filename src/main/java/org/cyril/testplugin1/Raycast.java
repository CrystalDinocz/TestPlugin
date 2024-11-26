package org.cyril.testplugin1;

import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.util.Vector;

import java.util.*;
import java.util.stream.Collectors;

public class Raycast {
    public static void Fireball(String name) throws InterruptedException {
        Player player = Bukkit.getPlayer(name);
        Particle.DustOptions dust = new Particle.DustOptions(Color.fromRGB(255, 0, 0), 1);
        Random random = new Random();
        int rndfire = random.nextInt(100000000);
        String rndftag = "fire" + rndfire;
        List<Entity> blockdetection = new ArrayList<>();
        String fireselector1 = String.format("@e[tag=%s]", rndftag);
        List<Entity> hitentity = new ArrayList<>();
        String hitselector = String.format("@e[type=!player,tag=!%s,distance=..2.5]", rndftag);
        fireball:
        for (int i = 1; i < 49; i++) {
            String firename = (rndftag + "_" + i);
            String fireray = String.format("execute at %s positioned ~ ~1.2 ~ run summon minecraft:armor_stand ^ ^ ^%s {Invisible:true,Invulnerable:true,NoGravity:true,Tags:[\"%s\"],CustomNameVisible:false,CustomName:'{\"text\":\"%s\"}'}", name, ((float) i/2) + 1, rndftag, firename);
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), fireray);
            blockdetection.addAll(Bukkit.selectEntities(Bukkit.getConsoleSender(), fireselector1));
            for (Entity n : blockdetection) {
                hitentity.addAll(Bukkit.selectEntities(n, hitselector));
                if (n.getLocation().getBlock().getType() != Material.AIR) {
                    player.sendMessage("Hit a block. " + n.getLocation().getBlock().getType());
                    break fireball;
                }
            }
            if (!hitentity.isEmpty()) {
                if(hitentity.get(0) instanceof LivingEntity) {
                    break;
                }
            }
        }
        hitentity = hitentity.stream().distinct().collect(Collectors.toList());
        List<Entity> fireray1 = Bukkit.selectEntities(Bukkit.getConsoleSender(), fireselector1);
        List<Entity> fireray2 = new ArrayList<>();
        for (Entity n : fireray1) {
            String fireselector2 = String.format("@e[tag=!tagged,tag=%s,limit=1,sort=nearest]", rndftag);
            fireray2.addAll(Bukkit.selectEntities(player, fireselector2));
            fireray2.get(0).addScoreboardTag("tagged");
            Location tplocation = fireray2.get(0).getLocation().setDirection(player.getEyeLocation().getDirection());
            tplocation.add(0,0.42,0);
            double x = tplocation.getX();
            double y = tplocation.getY();
            double z = tplocation.getZ();
            String particle = String.format("particle minecraft:enchanted_hit %s %s %s 0 0 0 0.2 50 normal", x, y, z);
            String particle2 = String.format("particle crit %s %s %s 0 0 0 0.08 10 normal", x, y, z);
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), particle);
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), particle2);
            fireray2.removeFirst();
            Thread.sleep(15);
        }
        for (Entity n : fireray1) {
            n.remove();
        }
        for (Entity n : hitentity) {
            if(n instanceof LivingEntity) {
                Vector base = Bukkit.getPlayer(name).getEyeLocation().getDirection();
                n.setVelocity(base.multiply(0.8).setY(0.4));
                int iframes = ((LivingEntity) n).getMaximumNoDamageTicks();
                ((LivingEntity) n).setMaximumNoDamageTicks(0);
                Bukkit.getPlayer(name).sendMessage("Hit an entity: " + n.getType());
                ((LivingEntity) n).damage(35);
                ((LivingEntity) n).setMaximumNoDamageTicks(iframes);
            }
        }
    }
    public static void Slash(String name) {
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
    public static void Command(String name) throws InterruptedException {
        int i = 0;
        List<Entity> block = (new ArrayList<>());
        if(Bukkit.getPlayer(name).getScoreboardTags().contains("slash")) {
            Bukkit.getPlayer(name).playSound(Bukkit.getPlayer(name), Sound.ENTITY_PLAYER_ATTACK_SWEEP, SoundCategory.AMBIENT, 100, 0);
            Slash(name);
        }
        else if (Bukkit.getPlayer(name).getScoreboardTags().contains("fireball")) {
            Bukkit.getPlayer(name).playSound(Bukkit.getPlayer(name), Sound.ITEM_FIRECHARGE_USE, SoundCategory.AMBIENT, 100, 0.5F);
            Fireball(name);
        }
        else {
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
            if (Bukkit.getPlayer(name).getScoreboardTags().contains("raycast")) {
                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "execute at @e[type=minecraft:area_effect_cloud,tag=laser] run particle minecraft:flame ~ ~ ~ 0 0 0 0 10 normal");
                Bukkit.getPlayer(name).playSound(Bukkit.getPlayer(name), Sound.ITEM_FIRECHARGE_USE, SoundCategory.AMBIENT, 100, 1.3F);
            }
        }
    }
}