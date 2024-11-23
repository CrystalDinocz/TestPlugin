package org.cyril.testplugin1;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TriggerEvents implements Listener {
    public void menu(PlayerJoinEvent event) {
        ItemMeta itemmeta = event.getPlayer().getInventory().getItem(8).getItemMeta();
        itemmeta.setDisplayName("§aMenu §7(Right Click)");
        List<String> description = new ArrayList<>();
        description.add("§8Rick-click to open the Menu.");
        itemmeta.setLore(description);
        event.getPlayer().getInventory().getItem(8).setItemMeta(itemmeta);
    }
    public void remove(String user) {
        Bukkit.getServer().dispatchCommand(Bukkit.getPlayer(user), "execute as @s run tag @s remove diamond");
        Bukkit.getServer().dispatchCommand(Bukkit.getPlayer(user), "execute as @s run tag @s remove emerald");
        Bukkit.getServer().dispatchCommand(Bukkit.getPlayer(user), "execute as @s run tag @s remove gold");
        Bukkit.getServer().dispatchCommand(Bukkit.getPlayer(user), "execute as @s run tag @s remove iron");
        Bukkit.getServer().dispatchCommand(Bukkit.getPlayer(user),"playsound minecraft:block.lever.click ambient @s ~ ~ ~ 100 2");
    }
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
            if (rc == Material.NETHER_STAR && event.getItem().getItemMeta().getDisplayName().equals("§aMenu §7(Right Click)")) {
                Inventory menu = Bukkit.createInventory(null, 27, "Menu");
                menu.setItem(10, new ItemStack(Material.IRON_INGOT));
                ItemMeta itemmeta1 = menu.getItem(10).getItemMeta();
                itemmeta1.setDisplayName("§7Iron");
                menu.setItem(12, new ItemStack(Material.GOLD_INGOT));
                ItemMeta itemmeta2 = menu.getItem(12).getItemMeta();
                itemmeta2.setDisplayName("§6Gold");
                menu.setItem(14, new ItemStack(Material.EMERALD));
                ItemMeta itemmeta3 = menu.getItem(14).getItemMeta();
                itemmeta3.setDisplayName("§aEmerald");
                menu.setItem(16, new ItemStack(Material.DIAMOND));
                ItemMeta itemmeta4 = menu.getItem(16).getItemMeta();
                itemmeta4.setDisplayName("§bDiamond");
                menu.getItem(10).setItemMeta(itemmeta1);
                menu.getItem(12).setItemMeta(itemmeta2);
                menu.getItem(14).setItemMeta(itemmeta3);
                menu.getItem(16).setItemMeta(itemmeta4);
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
            menu(event);
        } else if (event.getPlayer().getInventory().getItem(8).getType() != Material.NETHER_STAR) {
            String item = String.valueOf(event.getPlayer().getInventory().getItem(8).getType());
            System.out.println(event.getPlayer().getDisplayName() + " mel " + item + " na 9. slotu, davam spravny item.");
            event.getPlayer().getInventory().getItem(8).setType(Material.NETHER_STAR);
            menu(event);
            event.getPlayer().getInventory().getItem(8).setAmount(1);
        } else {
            System.out.println(event.getPlayer().getDisplayName() + " ma spravny item na 9. slotu.");
            menu(event);
            event.getPlayer().getInventory().getItem(8).setAmount(1);
        }
    }
    @EventHandler
    public void onInventoryInteract(InventoryClickEvent event) {
        String name = event.getWhoClicked().getName();
        if(event.getSlot() == 8) {
            event.setCancelled(true);
            event.getCurrentItem().setAmount(1);
        }
        if (event.getInventory().getSize() == 27 && event.getInventory().getItem(16) != null) {
            if(event.getInventory().getItem(16).getItemMeta().getDisplayName().equals("§bDiamond")) {
                try {
                    if (event.getCurrentItem().getType() == Material.DIAMOND) {
                        remove(name);
                        Bukkit.getServer().dispatchCommand(Bukkit.getPlayer(name), "execute as @s run tag @s add diamond");
                        Bukkit.broadcastMessage("Selected: " + event.getCurrentItem().getItemMeta().getDisplayName());
                    }
                    if (event.getCurrentItem().getType() == Material.EMERALD) {
                        remove(name);
                        Bukkit.getServer().dispatchCommand(Bukkit.getPlayer(name), "execute as @s run tag @s add emerald");
                        Bukkit.broadcastMessage("Selected: " + event.getCurrentItem().getItemMeta().getDisplayName());
                    }
                    if (event.getCurrentItem().getType() == Material.GOLD_INGOT) {
                        remove(name);
                        Bukkit.getServer().dispatchCommand(Bukkit.getPlayer(name), "execute as @s run tag @s add gold");
                        Bukkit.broadcastMessage("Selected: " + event.getCurrentItem().getItemMeta().getDisplayName());
                    }
                    if (event.getCurrentItem().getType() == Material.IRON_INGOT) {
                        remove(name);
                        Bukkit.getServer().dispatchCommand(Bukkit.getPlayer(name), "execute as @s run tag @s add iron");
                        Bukkit.broadcastMessage("Selected: " + event.getCurrentItem().getItemMeta().getDisplayName());
                    }
                } catch (NullPointerException ignored) {
                }
                event.setCancelled(true);
            }
        }
    }
    @EventHandler
    public void onHotbarDrop(PlayerDropItemEvent event) {
        event.getItemDrop().setGlowing(true);
        if (event.getItemDrop().getItemStack().getType() == Material.NETHER_STAR){
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
