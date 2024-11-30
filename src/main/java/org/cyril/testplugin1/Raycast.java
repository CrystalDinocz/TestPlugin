package org.cyril.testplugin1;

import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import java.util.*;
import java.util.stream.Collectors;

public class Raycast {
//ABILITIES
    public static void Heal(String name) {
        Player player = Bukkit.getPlayer(name);
        player.addScoreboardTag("healcooldown");
        World world = player.getWorld();
        for (double j = 1; j < 7; j = j + 2) {
            for (double i = 0; i < 44; i = i + 0.5) {
                double x = Math.sin(i);
                double z = Math.cos(i);
                Location clocation = player.getLocation();
                clocation.add(x * j, 0.1, z * j);
                world.spawnParticle(Particle.HAPPY_VILLAGER, clocation, 20, 0, 0, 0, 0);
            }
        }
        for (double i = -6; i <= 6; i = i+ 0.5 ) {
            Location line = player.getLocation();
            line.add(0,0, i);
            world.spawnParticle(Particle.HAPPY_VILLAGER, line, 20, 0, 0, 0, 0);
        }
        for (double i = -6; i <= 6; i = i + 0.5) {
            Location line = player.getLocation();
            line.add(i,0, 0);
            world.spawnParticle(Particle.HAPPY_VILLAGER, line, 20, 0, 0, 0, 0);
        }
        world.spawnParticle(Particle.REVERSE_PORTAL, player.getLocation().add(0,1,0), 200, 0, 0, 0, 1);
        List<Entity> healed = player.getNearbyEntities(6,6,6);
        healed.add(player);
        for (Entity n : healed) {
            if (n instanceof Player) {
                Player healplayer = ((Player) n).getPlayer();
                double currhealth = healplayer.getHealth();
                double healing = ((Player) n).getHealthScale() - currhealth;
                if (currhealth <= 10) {
                    double afterheal = currhealth + 10;
                    ((Player) n).setHealth(afterheal);
                    player.sendMessage("Healed player " + healplayer.getName() + " for " + (float) healing + " hp. " + (float) currhealth + " -> " + (float) afterheal);
                } else {
                    ((Player) n).setHealth(((Player) n).getHealthScale());
                    player.sendMessage("Healed player " + healplayer.getName() + " for " + (float) healing + " hp. " + (float) currhealth + " -> 20.0");
                }
            }
        }
        BukkitTask HealCooldown = new BukkitRunnable() {
            @Override
            public void run() {
                player.removeScoreboardTag("healcooldown");
                player.sendMessage("§aHeal ability is off cooldown.");
            }
        }.runTaskLater(Testplugin1.getInstance(), 150);
    }
    public static void Meteor(String name) {
        Player player = Bukkit.getPlayer(name);
        player.addScoreboardTag("meteorcooldown");
        for (int i = 1; i < 21; i++) {
            try {
                Location targetloc = player.getTargetBlockExact(i).getLocation();
                targetloc.add(0.5,11,0.5);
                FallingBlock meteorblock = player.getWorld().spawnFallingBlock(targetloc, Material.MAGMA_BLOCK, (byte) 0);
                BukkitTask Repeat = new BukkitRunnable() {
                    @Override
                    public void run() {
                        Location meteorlocation = meteorblock.getLocation();
                        player.getWorld().spawnParticle(Particle.LAVA, meteorlocation, 2,0,0,0,0);
                        player.getWorld().spawnParticle(Particle.FLAME, meteorlocation, 1,0,0,0,0);
                        if (meteorlocation.subtract(0,0.8,0).getBlock().getType().isSolid()) {
                            List<Entity> meteorhit = meteorblock.getNearbyEntities(2,2,2);
                            meteorblock.remove();
                            player.getWorld().playSound(player, Sound.ENTITY_GENERIC_EXPLODE, 100,0);
                            player.getWorld().spawnParticle(Particle.EXPLOSION, meteorlocation, 10,1,1,1,0.5);
                            for (Entity n : meteorhit) {
                                if (n instanceof LivingEntity) {
                                    n.setVelocity(new Vector(0, 0.6, 0));
                                    int iframes = ((LivingEntity) n).getMaximumNoDamageTicks();
                                    ((LivingEntity) n).setMaximumNoDamageTicks(0);
                                    player.sendMessage("Hit an entity: " + n.getType());
                                    ((LivingEntity) n).damage(50);
                                    ((LivingEntity) n).setMaximumNoDamageTicks(iframes);
                                }
                            }
                            cancel();
                        }
                    }
                }.runTaskTimer(Testplugin1.getInstance(), 0,1);
                break;
            } catch (NullPointerException e) {
                if(i == 20) {
                    Location airlocation = player.getLocation().add(player.getLocation().getDirection().multiply(i));
                    int y2 = (int) player.getLocation().getY();
                    airlocation.setY(y2+10);
                    double x2 = ((int) airlocation.getX()) + 0.5;
                    double z2 = ((int) airlocation.getZ()) + 0.5;
                    airlocation.setX(x2);
                    airlocation.setZ(z2);
                    FallingBlock meteorblock = player.getWorld().spawnFallingBlock(airlocation, Material.MAGMA_BLOCK, (byte) 0);
                    BukkitTask Repeat = new BukkitRunnable() {
                        @Override
                        public void run() {
                            Location meteorlocation = meteorblock.getLocation();
                            player.getWorld().spawnParticle(Particle.LAVA, meteorlocation, 2,0,0,0,0);
                            player.getWorld().spawnParticle(Particle.FLAME, meteorlocation, 1,0,0,0,0);
                            if (meteorlocation.subtract(0,0.8,0).getBlock().getType().isSolid()) {
                                List<Entity> meteorhit = meteorblock.getNearbyEntities(2,2,2);
                                meteorblock.remove();
                                player.getWorld().playSound(player, Sound.ENTITY_GENERIC_EXPLODE, 100,0);
                                player.getWorld().spawnParticle(Particle.EXPLOSION, meteorlocation, 10,1,1,1,0.5);
                                for (Entity n : meteorhit) {
                                    if (n instanceof LivingEntity) {
                                        n.setVelocity(new Vector(0, 0.6, 0));
                                        int iframes = ((LivingEntity) n).getMaximumNoDamageTicks();
                                        ((LivingEntity) n).setMaximumNoDamageTicks(0);
                                        player.sendMessage("Hit an entity: " + n.getType());
                                        ((LivingEntity) n).damage(50);
                                        ((LivingEntity) n).setMaximumNoDamageTicks(iframes);
                                    }
                                }
                                cancel();
                            }
                        }
                    }.runTaskTimer(Testplugin1.getInstance(), 0,1);
                }
            }
        }
        BukkitTask MeteorCooldown = new BukkitRunnable() {
            @Override
            public void run() {
                player.removeScoreboardTag("meteorcooldown");
                player.sendMessage("§aMeteor ability is off cooldown.");
            }
        }.runTaskLater(Testplugin1.getInstance(), 200);
    }
    public static void ManaLance(String name) {
        Player player = Bukkit.getPlayer(name);
        player.addScoreboardTag("mlcooldown");
        Random random = new Random();
        int rndfire = random.nextInt(100000000);
        String rndftag = "fire" + rndfire;
        List<Entity> blockdetection = new ArrayList<>();
        String fireselector1 = String.format("@e[tag=%s]", rndftag);
        List<Entity> hitentity = new ArrayList<>();
        String hitselector = String.format("@e[type=!player,tag=!%s,distance=..2.5]", rndftag);
        fireball:
        for (double i = 1; i < 49; i = i + 1.5) {
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
        final int[] limit = {1};
        List<Entity> finalHitentity = hitentity;
        BukkitTask ParticleRepeat = new BukkitRunnable() {
            public void run() {
                if (limit[0] > fireray1.size()) {
                    for (Entity n : finalHitentity) {
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
                    cancel();
                } else {
                    String fireselector2 = String.format("@e[tag=!tagged,tag=%s,limit=1,sort=nearest]", rndftag);
                    fireray2.addAll(Bukkit.selectEntities(player, fireselector2));
                    fireray2.get(0).addScoreboardTag("tagged");
                    Location tplocation = fireray2.get(0).getLocation().setDirection(player.getEyeLocation().getDirection());
                    tplocation.add(0, 0.42, 0);
                    double x = tplocation.getX();
                    double y = tplocation.getY();
                    double z = tplocation.getZ();
                    String particle = String.format("particle minecraft:enchanted_hit %s %s %s 0 0 0 0.2 50 normal", x, y, z);
                    String particle2 = String.format("particle crit %s %s %s 0 0 0 0.08 10 normal", x, y, z);
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), particle);
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), particle2);
                    fireray2.get(0).remove();
                    fireray2.removeFirst();
                    limit[0] = limit[0] + 1;
                }
            }
        }.runTaskTimer(Testplugin1.getInstance(), 0, 0);
        BukkitTask MLCooldown = new BukkitRunnable() {
            @Override
            public void run() {
                player.removeScoreboardTag("mlcooldown");
                player.sendMessage("§aMana Lance ability is off cooldown.");
            }
        }.runTaskLater(Testplugin1.getInstance(), 60);
    }
    public static void Slash(String name) {
        Player player = Bukkit.getPlayer(name);
        player.addScoreboardTag("slashcooldown");
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
        BukkitTask SlashCooldown = new BukkitRunnable() {
            @Override
            public void run() {
                player.removeScoreboardTag("slashcooldown");
                player.sendMessage("§aSlash ability is off cooldown.");
            }
        }.runTaskLater(Testplugin1.getInstance(), 40);
    }
//TRIGGER
    public static void Trigger(String name) {
        int i = 0;
        List<Entity> block = (new ArrayList<>());
        if(Bukkit.getPlayer(name).getScoreboardTags().contains("heal")) {
            if(Bukkit.getPlayer(name).getScoreboardTags().contains("healcooldown")) {
                Bukkit.getPlayer(name).sendMessage("§cAbility on cooldown.");
            } else {
                Bukkit.getPlayer(name).playSound(Bukkit.getPlayer(name), Sound.BLOCK_BEACON_POWER_SELECT, SoundCategory.AMBIENT, 100, 1.5F);
                Heal(name);
            }
        }
        if(Bukkit.getPlayer(name).getScoreboardTags().contains("meteor")) {
            if(Bukkit.getPlayer(name).getScoreboardTags().contains("meteorcooldown")) {
                Bukkit.getPlayer(name).sendMessage("§cAbility on cooldown.");
            } else {
                Bukkit.getPlayer(name).playSound(Bukkit.getPlayer(name), Sound.ENTITY_BREEZE_SHOOT, SoundCategory.AMBIENT, 100, 0);
                Meteor(name);
            }
        }
        if(Bukkit.getPlayer(name).getScoreboardTags().contains("slash")) {
            if(Bukkit.getPlayer(name).getScoreboardTags().contains("slashcooldown")) {
                Bukkit.getPlayer(name).sendMessage("§cAbility on cooldown.");
            } else {
                Bukkit.getPlayer(name).playSound(Bukkit.getPlayer(name), Sound.ENTITY_PLAYER_ATTACK_SWEEP, SoundCategory.AMBIENT, 100, 0);
                Slash(name);
            }
        }
        if (Bukkit.getPlayer(name).getScoreboardTags().contains("fireball")) {
            if(Bukkit.getPlayer(name).getScoreboardTags().contains("mlcooldown")) {
                Bukkit.getPlayer(name).sendMessage("§cAbility on cooldown.");
            } else {
                Bukkit.getPlayer(name).playSound(Bukkit.getPlayer(name), Sound.ITEM_FIRECHARGE_USE, SoundCategory.AMBIENT, 100, 0.5F);
                ManaLance(name);
            }
        }
        if (Bukkit.getPlayer(name).getScoreboardTags().contains("raycast")) {
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