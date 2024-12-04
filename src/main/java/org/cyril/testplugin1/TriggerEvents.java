package org.cyril.testplugin1;

import org.bukkit.*;
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
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TriggerEvents implements Listener {
//GUI's
    public void cheatGUI(InventoryClickEvent event) {
        Inventory cheatGUI= Bukkit.createInventory(null, 27, "Menu");
        List<String> Lore = new ArrayList<>();
        cheatGUI.setItem(10, new ItemStack(Material.GOLDEN_APPLE));
        Lore.addFirst("§8Set your gamemode to Creative.");
        ItemMeta itemMeta1 = cheatGUI.getItem(10).getItemMeta();
        itemMeta1.setLore(Lore);
        Lore.clear();
        itemMeta1.setDisplayName("§eCreative");
        cheatGUI.setItem(12, new ItemStack(Material.APPLE));
        Lore.addFirst("§8Set your gamemode to Survival.");
        ItemMeta itemMeta2 = cheatGUI.getItem(12).getItemMeta();
        itemMeta2.setLore(Lore);
        Lore.clear();
        itemMeta2.setDisplayName("§aSurvival");
        cheatGUI.setItem(14, new ItemStack(Material.RED_CONCRETE));
        Lore.addFirst("§8Remove 1 Health.");
        ItemMeta itemMeta3 = cheatGUI.getItem(10).getItemMeta();
        itemMeta3.setLore(Lore);
        Lore.clear();
        itemMeta3.setDisplayName("§4-1HP");
        cheatGUI.setItem(16, new ItemStack(Material.LIME_CONCRETE));
        Lore.addFirst("§8Add 1 Health.");
        ItemMeta itemMeta4 = cheatGUI.getItem(10).getItemMeta();
        itemMeta4.setLore(Lore);
        Lore.clear();
        itemMeta4.setDisplayName("§a+1HP");
        cheatGUI.getItem(10).setItemMeta(itemMeta1);
        cheatGUI.getItem(12).setItemMeta(itemMeta2);
        cheatGUI.getItem(14).setItemMeta(itemMeta3);
        cheatGUI.getItem(16).setItemMeta(itemMeta4);
        event.getWhoClicked().openInventory(cheatGUI);
    }
    public void mainGUI(PlayerInteractEvent event) {
        Inventory mainGUI= Bukkit.createInventory(null, 27, "Menu");
        List<String> Lore = new ArrayList<>();
        mainGUI.setItem(10, new ItemStack(Material.STONE_SWORD));
        Lore.addFirst("§8Select your class.");
        ItemMeta itemMeta1 = mainGUI.getItem(10).getItemMeta();
        itemMeta1.setLore(Lore);
        Lore.clear();
        itemMeta1.setDisplayName("§6Class");
        mainGUI.setItem(12, new ItemStack(Material.BLAZE_POWDER));
        Lore.addFirst("§8Select your ability.");
        ItemMeta itemMeta2 = mainGUI.getItem(12).getItemMeta();
        itemMeta2.setLore(Lore);
        Lore.clear();
        itemMeta2.setDisplayName("§9Abilities");
        mainGUI.setItem(14, new ItemStack(Material.PLAYER_HEAD));
        Lore.addFirst("§8Display your profile stats.");
        SkullMeta skullMeta = (SkullMeta) mainGUI.getItem(14).getItemMeta();
        skullMeta.setLore(Lore);
        Lore.clear();
        skullMeta.setOwningPlayer(event.getPlayer());
        skullMeta.setDisplayName("§aYour Profile");
        mainGUI.setItem(16, new ItemStack(Material.COMMAND_BLOCK));
        Lore.addFirst("§8Opens a cheat menu.");
        ItemMeta itemMeta3 = mainGUI.getItem(16).getItemMeta();
        itemMeta3.setLore(Lore);
        Lore.clear();
        itemMeta3.setDisplayName("§cCheat Menu");
        mainGUI.getItem(10).setItemMeta(itemMeta1);
        mainGUI.getItem(12).setItemMeta(itemMeta2);
        mainGUI.getItem(14).setItemMeta(skullMeta);
        mainGUI.getItem(16).setItemMeta(itemMeta3);
        event.getPlayer().openInventory(mainGUI);
    }
    public void classGUI(InventoryClickEvent event) {
        Inventory classGUI = Bukkit.createInventory(null, 27, "Class Menu");
        List<String> Lore = new ArrayList<>();
        classGUI.setItem(10, new ItemStack(Material.IRON_SWORD));
        Lore.addFirst("§8Select the Warrior class.");
        ItemMeta itemMeta1 = classGUI.getItem(10).getItemMeta();
        itemMeta1.setLore(Lore);
        Lore.clear();
        itemMeta1.setDisplayName("§cWarrior");
        classGUI.setItem(13, new ItemStack(Material.BLAZE_ROD));
        Lore.addFirst("§8Select the Mage class.");
        ItemMeta itemMeta2 = classGUI.getItem(13).getItemMeta();
        itemMeta2.setLore(Lore);
        Lore.clear();
        itemMeta2.setDisplayName("§5Mage");
        classGUI.setItem(16, new ItemStack(Material.BOW));
        Lore.addFirst("§8Select the Ranger class.");
        ItemMeta itemMeta3 = classGUI.getItem(16).getItemMeta();
        itemMeta3.setLore(Lore);
        Lore.clear();
        itemMeta3.setDisplayName("§2Ranger");
        classGUI.getItem(10).setItemMeta(itemMeta1);
        classGUI.getItem(13).setItemMeta(itemMeta2);
        classGUI.getItem(16).setItemMeta(itemMeta3);
        event.getWhoClicked().openInventory(classGUI);
    }
    public void warriorGUI(InventoryClickEvent event) {
        Inventory warriorGUI = Bukkit.createInventory(null, 27, "Ability Menu");
        List<String> Lore = new ArrayList<>();
        warriorGUI.setItem(10, new ItemStack(Material.IRON_SWORD));
        Lore.addFirst("§8This ability has a 2 second cooldown.");
        ItemMeta itemMeta1 = warriorGUI.getItem(10).getItemMeta();
        itemMeta1.setLore(Lore);
        Lore.clear();
        itemMeta1.setDisplayName("§cSlash");
        warriorGUI.setItem(12, new ItemStack(Material.MACE));
        Lore.addFirst("§8This ability has a 10 second cooldown.");
        ItemMeta itemMeta2 = warriorGUI.getItem(12).getItemMeta();
        itemMeta2.setLore(Lore);
        Lore.clear();
        itemMeta2.setDisplayName("§2Ground Slam");
        warriorGUI.setItem(14, new ItemStack(Material.BARRIER));
        Lore.addFirst("§8Work in Progress.");
        ItemMeta itemMeta3 = warriorGUI.getItem(14).getItemMeta();
        itemMeta3.setLore(Lore);
        Lore.clear();
        itemMeta3.setDisplayName("§4§kWIPAFDSK");
        warriorGUI.setItem(16, new ItemStack(Material.BARRIER));
        Lore.addFirst("§8Work in Progress.");
        ItemMeta itemMeta4 = warriorGUI.getItem(16).getItemMeta();
        itemMeta4.setLore(Lore);
        Lore.clear();
        itemMeta4.setDisplayName("§4§kWIPAFDSK");
        warriorGUI.getItem(10).setItemMeta(itemMeta1);
        warriorGUI.getItem(12).setItemMeta(itemMeta2);
        warriorGUI.getItem(14).setItemMeta(itemMeta3);
        warriorGUI.getItem(16).setItemMeta(itemMeta4);
        event.getWhoClicked().openInventory(warriorGUI);
    }
    public void mageGUI(InventoryClickEvent event) {
        Inventory mageGUI = Bukkit.createInventory(null, 27, "Ability Menu");
        List<String> Lore = new ArrayList<>();
        mageGUI.setItem(10, new ItemStack(Material.ECHO_SHARD));
        Lore.addFirst("§8This ability has a 3 second cooldown.");
        ItemMeta itemMeta1 = mageGUI.getItem(10).getItemMeta();
        itemMeta1.setLore(Lore);
        Lore.clear();
        itemMeta1.setDisplayName("§bMana Lance");
        mageGUI.setItem(12, new ItemStack(Material.BARRIER));
        Lore.addFirst("§8Work in Progress.");
        ItemMeta itemMeta2 = mageGUI.getItem(12).getItemMeta();
        itemMeta2.setLore(Lore);
        Lore.clear();
        itemMeta2.setDisplayName("§4§kWIPAFDSK");
        mageGUI.setItem(14, new ItemStack(Material.MAGMA_BLOCK));
        Lore.addFirst("§8This ability has a 10 second cooldown.");
        ItemMeta itemMeta3 = mageGUI.getItem(14).getItemMeta();
        itemMeta3.setLore(Lore);
        Lore.clear();
        itemMeta3.setDisplayName("§6Meteor");
        mageGUI.setItem(16, new ItemStack(Material.GOLDEN_APPLE));
        Lore.addFirst("§8This ability has a 7.5 second cooldown.");
        ItemMeta itemMeta4 = mageGUI.getItem(16).getItemMeta();
        itemMeta4.setLore(Lore);
        Lore.clear();
        itemMeta4.setDisplayName("§aHeal");
        mageGUI.getItem(10).setItemMeta(itemMeta1);
        mageGUI.getItem(12).setItemMeta(itemMeta2);
        mageGUI.getItem(14).setItemMeta(itemMeta3);
        mageGUI.getItem(16).setItemMeta(itemMeta4);
        event.getWhoClicked().openInventory(mageGUI);
    }
    public void rangerGUI(InventoryClickEvent event) {
        Inventory rangerGUI = Bukkit.createInventory(null, 27, "Ability Menu");
        List<String> Lore = new ArrayList<>();
        rangerGUI.setItem(10, new ItemStack(Material.BARRIER));
        Lore.addFirst("§8Work in Progress.");
        ItemMeta itemMeta1 = rangerGUI.getItem(10).getItemMeta();
        itemMeta1.setLore(Lore);
        Lore.clear();
        itemMeta1.setDisplayName("§4§kWIPAFDSK");
        rangerGUI.setItem(12, new ItemStack(Material.BARRIER));
        Lore.addFirst("§8Work in Progress.");
        ItemMeta itemMeta2 = rangerGUI.getItem(12).getItemMeta();
        itemMeta2.setLore(Lore);
        Lore.clear();
        itemMeta2.setDisplayName("§4§kWIPAFDSK");
        rangerGUI.setItem(14, new ItemStack(Material.BARRIER));
        Lore.addFirst("§8Work in Progress.");
        ItemMeta itemMeta3 = rangerGUI.getItem(14).getItemMeta();
        itemMeta3.setLore(Lore);
        Lore.clear();
        itemMeta3.setDisplayName("§4§kWIPAFDSK");
        rangerGUI.setItem(16, new ItemStack(Material.BARRIER));
        Lore.addFirst("§8Work in Progress.");
        ItemMeta itemMeta4 = rangerGUI.getItem(16).getItemMeta();
        itemMeta4.setLore(Lore);
        Lore.clear();
        itemMeta4.setDisplayName("§4§kWIPAFDSK");
        rangerGUI.getItem(10).setItemMeta(itemMeta1);
        rangerGUI.getItem(12).setItemMeta(itemMeta2);
        rangerGUI.getItem(14).setItemMeta(itemMeta3);
        rangerGUI.getItem(16).setItemMeta(itemMeta4);
        event.getWhoClicked().openInventory(rangerGUI);
    }
//MISC
    public void menu(PlayerJoinEvent event) {
        ItemMeta itemmeta = event.getPlayer().getInventory().getItem(8).getItemMeta();
        itemmeta.setDisplayName("§aMenu §7(Right Click)");
        List<String> description = new ArrayList<>();
        description.add("§8Rick-click to open the Menu.");
        itemmeta.setLore(description);
        event.getPlayer().getInventory().getItem(8).setItemMeta(itemmeta);
    }
    public void removeAbility(InventoryClickEvent event) {
        event.getWhoClicked().removeScoreboardTag("raycast");
        event.getWhoClicked().removeScoreboardTag("slash");
        event.getWhoClicked().removeScoreboardTag("fireball");
        event.getWhoClicked().removeScoreboardTag("meteor");
        event.getWhoClicked().removeScoreboardTag("heal");
        event.getWhoClicked().removeScoreboardTag("groundslam");
        Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked(), Sound.BLOCK_LEVER_CLICK, SoundCategory.AMBIENT, 100, 2);
    }
    public void removeClass(InventoryClickEvent event) {
        event.getWhoClicked().removeScoreboardTag("warrior");
        event.getWhoClicked().removeScoreboardTag("mage");
        event.getWhoClicked().removeScoreboardTag("ranger");
        //ABILITY RESET
        event.getWhoClicked().removeScoreboardTag("raycast");
        event.getWhoClicked().removeScoreboardTag("slash");
        event.getWhoClicked().removeScoreboardTag("fireball");
        event.getWhoClicked().removeScoreboardTag("meteor");
        event.getWhoClicked().removeScoreboardTag("heal");
        event.getWhoClicked().removeScoreboardTag("groundslam");
        Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked(), Sound.BLOCK_LEVER_CLICK, SoundCategory.AMBIENT, 100, 2);
    }
//EVENT TRIGGERS
    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        String action = String.valueOf(event.getAction());
        if (event.hasItem()) {
            Material rc = Objects.requireNonNull(event.getItem()).getType();
            if (rc == Material.STICK) {
                if (action != "LEFT_CLICK_AIR" && action != "LEFT_CLICK_BLOCK") {
                    Raycast.Trigger(String.valueOf(event.getPlayer().getDisplayName()));
                }
            }
            if (rc == Material.NETHER_STAR && event.getItem().getItemMeta().getDisplayName().equals("§aMenu §7(Right Click)")) {
                mainGUI(event);
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
        Player player = Bukkit.getPlayer(name);
        if(event.getSlot() == 8) {
            event.setCancelled(true);
            event.getCurrentItem().setAmount(1);
        }
        if (event.getInventory().getSize() == 27 && event.getInventory().getItem(10) != null) {
            if(event.getInventory().getItem(10).getItemMeta().getDisplayName().equals("§4§kWIPAFDSK")) {
                try {
                    if (event.getCurrentItem().getType() == Material.BARRIER) {
                        event.getWhoClicked().sendMessage("§4Work in Progress");
                        Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked(), Sound.BLOCK_BAMBOO_WOOD_DOOR_OPEN, SoundCategory.AMBIENT, 100, 0.5F);
                        event.getWhoClicked().closeInventory();
                    }
                } catch (NullPointerException ignored) {
                }
                event.setCancelled(true);
            }
            if(event.getInventory().getItem(10).getItemMeta().getDisplayName().equals("§cSlash")) {
                try {
                    if (event.getCurrentItem().getType() == Material.BARRIER) {
                        event.getWhoClicked().sendMessage("§4Work in Progress");
                        Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked(), Sound.BLOCK_BAMBOO_WOOD_DOOR_OPEN, SoundCategory.AMBIENT, 100, 0.5F);
                        event.getWhoClicked().closeInventory();
                    }
                    if (event.getCurrentItem().getType() == Material.IRON_SWORD) {
                        removeAbility(event);
                        event.getWhoClicked().getScoreboardTags().add("slash");
                        event.getWhoClicked().sendMessage("Selected: " + event.getCurrentItem().getItemMeta().getDisplayName());
                        event.getWhoClicked().closeInventory();
                    }
                    if (event.getCurrentItem().getType() == Material.MACE) {
                        removeAbility(event);
                        event.getWhoClicked().getScoreboardTags().add("groundslam");
                        event.getWhoClicked().sendMessage("Selected: " + event.getCurrentItem().getItemMeta().getDisplayName());
                        event.getWhoClicked().closeInventory();
                    }
                } catch (NullPointerException ignored) {
                }
                event.setCancelled(true);
            }
            if(event.getInventory().getItem(10).getItemMeta().getDisplayName().equals("§bMana Lance")) {
                try {
                    if (event.getCurrentItem().getType() == Material.BARRIER) {
                        event.getWhoClicked().sendMessage("§4Work in Progress");
                        Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked(), Sound.BLOCK_BAMBOO_WOOD_DOOR_OPEN, SoundCategory.AMBIENT, 100, 0.5F);
                        event.getWhoClicked().closeInventory();
                    }
                    if (event.getCurrentItem().getType() == Material.MAGMA_BLOCK) {
                        removeAbility(event);
                        event.getWhoClicked().getScoreboardTags().add("meteor");
                        event.getWhoClicked().sendMessage("Selected: " + event.getCurrentItem().getItemMeta().getDisplayName());
                        event.getWhoClicked().closeInventory();
                    }
                    if (event.getCurrentItem().getType() == Material.ECHO_SHARD) {
                        removeAbility(event);
                        event.getWhoClicked().getScoreboardTags().add("fireball");
                        event.getWhoClicked().sendMessage("Selected: " + event.getCurrentItem().getItemMeta().getDisplayName());
                        event.getWhoClicked().closeInventory();
                    }
                    if (event.getCurrentItem().getType() == Material.GOLDEN_APPLE) {
                        removeAbility(event);
                        event.getWhoClicked().getScoreboardTags().add("heal");
                        event.getWhoClicked().sendMessage("Selected: " + event.getCurrentItem().getItemMeta().getDisplayName());
                        event.getWhoClicked().closeInventory();
                    }
                } catch (NullPointerException ignored) {
                }
                event.setCancelled(true);
            }
            if(event.getInventory().getItem(10).getItemMeta().getDisplayName().equals("§6Class")) {
                try {
                    if(event.getCurrentItem().getType() == Material.STONE_SWORD) {
                        classGUI(event);
                        Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked(), Sound.BLOCK_LEVER_CLICK, SoundCategory.AMBIENT, 100, 2);
                    }
                    if(event.getCurrentItem().getType() == Material.BLAZE_POWDER) {
                        if(event.getWhoClicked().getScoreboardTags().contains("warrior")) {
                            warriorGUI(event);
                        } else if(event.getWhoClicked().getScoreboardTags().contains("mage")) {
                            mageGUI(event);
                        } else if(event.getWhoClicked().getScoreboardTags().contains("ranger")) {
                            rangerGUI(event);
                        } else {
                            event.getWhoClicked().sendMessage("§cYou need to select a class.");
                        }
                        Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked(), Sound.BLOCK_LEVER_CLICK, SoundCategory.AMBIENT, 100, 2);
                    }
                    if(event.getCurrentItem().getType() == Material.PLAYER_HEAD) {
                        event.getWhoClicked().sendMessage("Profile");
                        Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked(), Sound.BLOCK_LEVER_CLICK, SoundCategory.AMBIENT, 100, 2);
                    }
                    if(event.getCurrentItem().getType() == Material.COMMAND_BLOCK) {
                        cheatGUI(event);
                        Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked(), Sound.BLOCK_LEVER_CLICK, SoundCategory.AMBIENT, 100, 2);
                    }
                } catch (NullPointerException ignored) {
                }
                event.setCancelled(true);
            }
            if(event.getInventory().getItem(10).getItemMeta().getDisplayName().equals("§eCreative")) {
                try {
                    if(event.getCurrentItem().getType() == Material.GOLDEN_APPLE) {
                        event.getWhoClicked().setGameMode(GameMode.CREATIVE);
                        Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked(), Sound.BLOCK_LEVER_CLICK, SoundCategory.AMBIENT, 100, 2);
                        player.closeInventory();
                    }
                    if(event.getCurrentItem().getType() == Material.APPLE) {
                        event.getWhoClicked().setGameMode(GameMode.SURVIVAL);
                        Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked(), Sound.BLOCK_LEVER_CLICK, SoundCategory.AMBIENT, 100, 2);
                        player.closeInventory();
                    }
                    if(event.getCurrentItem().getType() == Material.RED_CONCRETE) {
                        player.setHealthScale(player.getHealthScale() - 1);
                        Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked(), Sound.BLOCK_LEVER_CLICK, SoundCategory.AMBIENT, 100, 2);
                    }
                    if(event.getCurrentItem().getType() == Material.LIME_CONCRETE) {
                        player.setHealthScale(player.getHealthScale() + 1);
                        Bukkit.getPlayer(event.getWhoClicked().getName()).playSound(event.getWhoClicked(), Sound.BLOCK_LEVER_CLICK, SoundCategory.AMBIENT, 100, 2);
                    }
                } catch (NullPointerException ignored) {
                }
                event.setCancelled(true);
            }
            if(event.getInventory().getItem(10).getItemMeta().getDisplayName().equals("§cWarrior")) {
                try {
                    if (event.getCurrentItem().getType() == Material.IRON_SWORD) {
                        removeClass(event);
                        event.getWhoClicked().getScoreboardTags().add("warrior");
                        event.getWhoClicked().sendMessage("Selected: " + event.getCurrentItem().getItemMeta().getDisplayName());
                        event.getWhoClicked().closeInventory();
                    }
                    if (event.getCurrentItem().getType() == Material.BLAZE_ROD) {
                        removeClass(event);
                        event.getWhoClicked().getScoreboardTags().add("mage");
                        event.getWhoClicked().sendMessage("Selected: " + event.getCurrentItem().getItemMeta().getDisplayName());
                        event.getWhoClicked().closeInventory();
                    }
                    if (event.getCurrentItem().getType() == Material.BOW) {
                        removeClass(event);
                        event.getWhoClicked().getScoreboardTags().add("ranger");
                        event.getWhoClicked().sendMessage("Selected: " + event.getCurrentItem().getItemMeta().getDisplayName());
                        event.getWhoClicked().closeInventory();
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
