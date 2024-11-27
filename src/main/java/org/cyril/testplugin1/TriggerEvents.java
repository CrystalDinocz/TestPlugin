package org.cyril.testplugin1;

import org.bukkit.*;
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
    public void remove(InventoryClickEvent event) {
        event.getWhoClicked().removeScoreboardTag("raycast");
        event.getWhoClicked().removeScoreboardTag("slash");
        event.getWhoClicked().removeScoreboardTag("fireball");
        event.getWhoClicked().removeScoreboardTag("meteor");
        Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked(), Sound.BLOCK_LEVER_CLICK, SoundCategory.AMBIENT, 100, 2);
    }
    @EventHandler
    public void onRightClick(PlayerInteractEvent event) throws InterruptedException {
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
                menu.setItem(10, new ItemStack(Material.FIRE_CHARGE));
                ItemMeta itemmeta1 = menu.getItem(10).getItemMeta();
                itemmeta1.setDisplayName("§bMana Lance");
                menu.setItem(12, new ItemStack(Material.IRON_SWORD));
                ItemMeta itemmeta2 = menu.getItem(12).getItemMeta();
                itemmeta2.setDisplayName("§cSlash");
                menu.setItem(14, new ItemStack(Material.MAGMA_BLOCK));
                ItemMeta itemmeta3 = menu.getItem(14).getItemMeta();
                itemmeta3.setDisplayName("§6Meteor");
                menu.setItem(16, new ItemStack(Material.BARRIER));
                ItemMeta itemmeta4 = menu.getItem(16).getItemMeta();
                itemmeta4.setDisplayName("§4§kWIPAL");
                menu.setItem(22, new ItemStack(Material.CARROT_ON_A_STICK));
                ItemMeta itemmeta5 = menu.getItem(22).getItemMeta();
                itemmeta5.setDisplayName("§6Raycast");
                menu.getItem(10).setItemMeta(itemmeta1);
                menu.getItem(12).setItemMeta(itemmeta2);
                menu.getItem(14).setItemMeta(itemmeta3);
                menu.getItem(16).setItemMeta(itemmeta4);
                menu.getItem(22).setItemMeta(itemmeta5);
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
        if (event.getInventory().getSize() == 27 && event.getInventory().getItem(10) != null) {
            if(event.getInventory().getItem(10).getItemMeta().getDisplayName().equals("§bMana Lance")) {
                try {
                    if (event.getCurrentItem().getType() == Material.BARRIER) {
                        event.getWhoClicked().sendMessage("§4Work in Progress");
                        Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked(), Sound.BLOCK_BAMBOO_WOOD_DOOR_OPEN, SoundCategory.AMBIENT, 100, 0.5F);
                    }
                    if (event.getCurrentItem().getType() == Material.CARROT_ON_A_STICK) {
                        remove(event);
                        event.getWhoClicked().getScoreboardTags().add("raycast");
                        event.getWhoClicked().sendMessage("Selected: " + event.getCurrentItem().getItemMeta().getDisplayName());
                    }if (event.getCurrentItem().getType() == Material.MAGMA_BLOCK) {
                        remove(event);
                        event.getWhoClicked().getScoreboardTags().add("meteor");
                        event.getWhoClicked().sendMessage("Selected: " + event.getCurrentItem().getItemMeta().getDisplayName());
                    }
                    if (event.getCurrentItem().getType() == Material.IRON_SWORD) {
                        remove(event);
                        event.getWhoClicked().getScoreboardTags().add("slash");
                        event.getWhoClicked().sendMessage("Selected: " + event.getCurrentItem().getItemMeta().getDisplayName());
                    }
                    if (event.getCurrentItem().getType() == Material.FIRE_CHARGE) {
                        remove(event);
                        event.getWhoClicked().getScoreboardTags().add("fireball");
                        event.getWhoClicked().sendMessage("Selected: " + event.getCurrentItem().getItemMeta().getDisplayName());
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
        Location blockpos = event.getBlock().getLocation();
        int x = (int) blockpos.getX();
        int y = (int) blockpos.getY();
        int z = (int) blockpos.getZ();
        event.getPlayer().sendMessage("Breaking block " + event.getBlock().getType() + " at x=" + x + ", y=" + y + ", z=" + z + " for player " + event.getPlayer().getName());
    }
}
