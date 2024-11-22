package org.cyril.testplugin1;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerHarvestBlockEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

import java.awt.dnd.DropTarget;
import java.util.Objects;

public class TriggerEvents implements Listener {
    public void remove(String user) {
        Bukkit.getServer().dispatchCommand(Bukkit.getPlayer(user), "execute as @s run tag @s remove diamond");
        Bukkit.getServer().dispatchCommand(Bukkit.getPlayer(user), "execute as @s run tag @s remove emerald");
        Bukkit.getServer().dispatchCommand(Bukkit.getPlayer(user), "execute as @s run tag @s remove gold");
        Bukkit.getServer().dispatchCommand(Bukkit.getPlayer(user), "execute as @s run tag @s remove iron");
        Bukkit.getServer().dispatchCommand(Bukkit.getPlayer(user),"playsound minecraft:block.lever.click ambient @s ~ ~ ~ 100 2");
    }
    int imp = 10;
    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        String action = String.valueOf(event.getAction());
        if (event.hasItem()) {
            Material rc = Objects.requireNonNull(event.getItem()).getType();
            if (rc == Material.STICK) {
                if (action != "LEFT_CLICK_AIR" && action != "LEFT_CLICK_BLOCK") {
                    Raycast.Command(String.valueOf(event.getPlayer().getDisplayName()));
                }
            }
            if (rc == Material.NETHER_STAR) {
                Inventory menu = Bukkit.createInventory(null, 27, "Menu");
                menu.setItem(10, new ItemStack(Material.IRON_INGOT));
                menu.setItem(12, new ItemStack(Material.GOLD_INGOT));
                menu.setItem(14, new ItemStack(Material.EMERALD));
                menu.setItem(16, new ItemStack(Material.DIAMOND));
                event.getPlayer().openInventory(menu);
            }
        }
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        System.out.println(event.getPlayer().getDisplayName() + " Joined.");
        if (event.getPlayer().getInventory().getItem(8) == null) {
            System.out.println(event.getPlayer().getDisplayName() + " nema nic na 9. slotu, davam spravny item.");
            event.getPlayer().getInventory().setItem(8, new ItemStack(Material.NETHER_STAR));
        } else if (event.getPlayer().getInventory().getItem(8).getType() != Material.NETHER_STAR) {
            String item = String.valueOf(event.getPlayer().getInventory().getItem(8).getType());
            System.out.println(event.getPlayer().getDisplayName() + " mel " + item + " na 9. slotu, davam spravny item.");
            event.getPlayer().getInventory().getItem(8).setType(Material.NETHER_STAR);
            event.getPlayer().getInventory().getItem(8).setAmount(1);
        } else {
            System.out.println(event.getPlayer().getDisplayName() + " ma spravny item na 9. slotu.");
            event.getPlayer().getInventory().getItem(8).setAmount(1);
        }
    }
    @EventHandler
    public void onInventoryInteract(InventoryClickEvent event) {
        String name = event.getWhoClicked().getName();
        if(event.getSlot() == 8) {
            Bukkit.broadcastMessage("Hey, don't do that!");
            event.setCancelled(true);
            event.getCurrentItem().setAmount(1);
        }
        if (event.getInventory().getSize() == 27) {
            try {
                if (event.getCurrentItem().getType() == Material.DIAMOND) {
                    remove(name);
                    Bukkit.getServer().dispatchCommand(Bukkit.getPlayer(name), "execute as @s run tag @s add diamond");
                    Bukkit.broadcastMessage("Selected: " + event.getCurrentItem().getType());
                }
                if (event.getCurrentItem().getType() == Material.EMERALD) {
                    remove(name);
                    Bukkit.getServer().dispatchCommand(Bukkit.getPlayer(name), "execute as @s run tag @s add emerald");
                    Bukkit.broadcastMessage("Selected: " + event.getCurrentItem().getType());
                }
                if (event.getCurrentItem().getType() == Material.GOLD_INGOT) {
                    remove(name);
                    Bukkit.getServer().dispatchCommand(Bukkit.getPlayer(name), "execute as @s run tag @s add gold");
                    Bukkit.broadcastMessage("Selected: " + event.getCurrentItem().getType());
                }
                if (event.getCurrentItem().getType() == Material.IRON_INGOT) {
                    remove(name);
                    Bukkit.getServer().dispatchCommand(Bukkit.getPlayer(name), "execute as @s run tag @s add iron");
                    Bukkit.broadcastMessage("Selected: " + event.getCurrentItem().getType());
                }
            } catch (NullPointerException ignored) {
            }
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void onHotbarDrop(PlayerDropItemEvent event) {
        event.getItemDrop().setGlowing(true);
        if (event.getItemDrop().getItemStack().getType() == Material.NETHER_STAR){
            Bukkit.broadcastMessage("Hey, don't do that!");
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (event.getPlayer().getItemInHand().getType() == Material.NETHER_STAR){
            event.setCancelled(true);
        }
    }
}
