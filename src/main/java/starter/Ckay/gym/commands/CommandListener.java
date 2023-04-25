package starter.Ckay.gym.commands;

import com.google.common.collect.Lists;
import com.mojang.brigadier.Command;

import starter.Ckay.gym.PlasmaGym;
import starter.Ckay.gym.config.PluginConfiguration;
import starter.Ckay.gym.scoreboard.ScoreboardManager;
import net.minecraft.client.gui.screen.report.ChatSelectionScreen.SelectionListWidget.MessageEntry;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.command.CommandSource;
import net.minecraft.entity.boss.BossBar.Style;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registry;
import net.minecraft.registry.tag.TagManagerLoader.RegistryTags;
import net.minecraft.server.world.*;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.ServerMetadata.Players;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.MessageCommand;
import net.minecraft.server.command.CommandManager.CommandParser;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.text.ClickEvent.Action;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent.Message;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.item.v1.FabricItem;
import net.fabricmc.fabric.api.message.v1.ServerMessageEvents;
import net.fabricmc.fabric.api.message.v1.ServerMessageEvents.CommandMessage;
import net.fabricmc.loader.api.FabricLoader;

import java.awt.Color;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.io.*;

import org.apache.http.client.utils.URIBuilder;

//import net.fabricmc.fabric.api.item.inventory.InventoryArchetype;

@SuppressWarnings("unused")
public class CommandListener {
    private PlasmaGym plugin;    
    public static PlayerManager players;
    CommandManager commandMessage;

    public CommandListener(PlasmaGym plugin) {
        this.plugin = plugin;
    }
    
    public void onCommand(ServerMessageEvents.CommandMessage event, ClientPlayerEntity player) throws MalformedURLException {
        
        String commandLabel = event.toString();
        String[] args = event.toString().isEmpty() ? new String[]{} : event.toString().split(" ");
       if (commandLabel.equalsIgnoreCase("gym")) {
            handleGym(player, args);
        } else if ((commandLabel.equalsIgnoreCase("e4")) && (this.plugin.enablee4.equalsIgnoreCase("true"))) {
            handleE4(player, args);
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes","deprecated"})
    private void handleGym(PlayerEntity p, String[] args) throws MalformedURLException {
        if (args.length == 0) {

            for (int i = 1; i <= 32; i++) {
                 int time = plugin.getConfig().getConfig().getNode("config.gym"+i+"cooldowntime").getInt();
            }

        URL myURL1 = new URL("https://discord.gg/GMScHpB");            
        
            // Text link1 = Text.builder("Click Here to join the discord server for support and updates!").onClick(TextActions.openUrl(myURL1)).build();
         String link1 = "\n<a href='https://discord.gg/GMScHpB'>Click Here to join the discord server for support and updates!</a>";
           
            // Text link1 = Text.builder("Click Here to join the discord server for support and updates!").onClick(TextActions.openUrl(myURL1)).build();
            
         //   Text link1 = Text.of("Click Here to join the discord server for support and updates!");
           // Text link1 = Text.add("Click Here to join the discord server for support and updates!").onClick(Action.openUrl(myURL1)).build();           
                
            if (!p.hasPermissionLevel(1) && !p.hasPermissionLevel(2)) {
                //if (!p.hasPermissionLevel(1) && !p.hasPermissionLevel(2)) {
                p.sendMessage(Text.of(Color.HSBtoRGB(218,165,32) + "----- PlasmaGyms -----"));
                p.sendMessage(Text.of(""));
                if (this.plugin.getConfig().getString("config.enableredeem").equalsIgnoreCase("True")) {
                    p.sendMessage(Text.of(Color.GREEN + "/gym redeem" + Color.HSBtoRGB(0,100,0) + " - Whilst holding a Physical Gym Badge in your hand, use this command to turn your physical badge into a virtual one so you can carry on where you left of!"));
                }
                p.sendMessage(Text.of(Color.GREEN + "/gym showcase <true/false>" + Color.HSBtoRGB(0,100,0) + " - Disable or enable being given the badge showcase item!"));
                p.sendMessage(Text.of(Color.GREEN + "/gym list" + Color.HSBtoRGB(0,100,0) + " - Get a list of the gyms and there status."));
                p.sendMessage(Text.of(Color.GREEN + "/gym leaders" + Color.HSBtoRGB(0,100,0) + " - Get a list of the online gym leaders."));
                p.sendMessage(Text.of(Color.GREEN + "/gym rules <GymName>" + Color.HSBtoRGB(0,100,0) + " - Shows all the rules for the specified Gym."));
                p.sendMessage(Text.of(Color.GREEN + "/gym join <GymName>" + Color.HSBtoRGB(0,100,0) + " - Join the queue for the gym you want. Example: /gym join gym1"));
                p.sendMessage(Text.of(Color.GREEN + "/gym <leave | quit> <GymName>" + Color.HSBtoRGB(0,100,0) + " - Quits the gym queue of the specified gym. Example: /gym leave gym1."));
                p.sendMessage(Text.of(Color.GREEN + "/gym <check | position> <GymName>" + Color.HSBtoRGB(0,100,0) + " - Check your position in a queue. Example: /gym check gym1"));
                p.sendMessage(Text.of(Color.GREEN + "/gym see [Player]" + Color.HSBtoRGB(0,100,0) + " - Shows the gym badge case of a specific player."));
                p.sendMessage(Text.of(Color.GREEN + "/gym scoreboard" + Color.HSBtoRGB(0,100,0) + " - Toggle ScoreBoard."));
                p.sendMessage(Text.of(""));
                p.sendMessage(Text.of(Color.HSBtoRGB(218,165,32) + link1));
                p.sendMessage(Text.of(Color.RED + "Plugin Made By " + Color.HSBtoRGB(218,165,32) + "Unova Legends"));
            } else if (p.hasPermissionLevel(1) && !p.hasPermissionLevel(2)) {
                p.sendMessage(Text.of(Color.HSBtoRGB(218,165,32) + "----- PlasmaGyms -----"));
                p.sendMessage(Text.of(""));
                if (this.plugin.getConfig().getString("config.enableredeem").equalsIgnoreCase("True")) {
                    p.sendMessage(Text.of(Color.GREEN + "/gym redeem" + Color.HSBtoRGB(0,100,0) + " - Whilst holding a Physical Gym Badge in your hand, use this command to turn your physical badge into a virtual one so you can carry on where you left of!"));
                }
                p.sendMessage(Text.of(Color.ORANGE +  "/gym showcase <true/false>" + Color.HSBtoRGB(0,100,0) + " - Disable or enable being given the badge showcase item!"));
                p.sendMessage(Text.of(Color.GREEN + "/gym list" + Color.HSBtoRGB(0,100,0) + " - Get a list of the gyms and there status."));
                p.sendMessage(Text.of(Color.GREEN + "/gym leaders" + Color.HSBtoRGB(0,100,0) + " - Get a list of the online gym leaders."));
                p.sendMessage(Text.of(Color.GREEN + "/gym scoreboard" + Color.HSBtoRGB(0,100,0) + " - Toggle ScoreBoard."));
                p.sendMessage(Text.of(Color.GREEN + "/gym rules <GymName>" + Color.HSBtoRGB(0,100,0) + " - Shows all the rules for the specified Gym."));
                p.sendMessage(Text.of(Color.GREEN + "/gym <check | position> <GymName>" + Color.HSBtoRGB(0,100,0) + " - Check your position in a queue. Example: /gym check gym1"));
                p.sendMessage(Text.of(Color.GREEN + "/gym join <GymName>" + Color.HSBtoRGB(0,100,0) + " - Join the queue for the gym you want. Example: /gym join gym1"));
                p.sendMessage(Text.of(Color.GREEN + "/gym <see | check> [Player]" + Color.HSBtoRGB(0,100,0) + " - Shows the gym badge case of a specific player. | = or, you can type see or check."));
                p.sendMessage(Text.of(Color.GREEN + "/gym next <GymName>" + Color.HSBtoRGB(0,100,0) + " - Grabs the first person of the specified gym queue and teleports them to the gym. (It also displays the gym rules for them in chat, so you don't need to)"));
                p.sendMessage(Text.of(Color.GREEN + "/gym remove <GymName>" + Color.HSBtoRGB(0,100,0) + " - Remove's the first person of the specified gym queue (If someone has disconnected and does not relog after a while)"));
                p.sendMessage(Text.of(Color.GREEN + "/gym giveTM <GymName> [Player]" + Color.HSBtoRGB(0,100,0) + " - Give's the TM to the player if they had there inventory full when winning the gym!"));
                p.sendMessage(Text.of(Color.GREEN + "/gym <sq | skip | skipq> <GymName> [Player]" + Color.HSBtoRGB(0,100,0) + " - Skips the players cooldown of the specified gym, most commonly used if they disconnect mid battle and the automatic cooldown apply's"));
                p.sendMessage(Text.of(Color.GREEN + "/gym <winner | win | w> <GymName> [Player]" + Color.HSBtoRGB(0,100,0) + " - Sets the gym challeger to a winner, giving them the badge for the next gym!"));
                p.sendMessage(Text.of(Color.GREEN + "/gym <lost | lose | l> <GymName> [Player]" + Color.HSBtoRGB(0,100,0) + " - Sets the gym challeger to a loser, teleporting them out of the gym and giving them a specific timed cooldown!"));
                p.sendMessage(Text.of(Color.GREEN + "/gym <leave | quit> <GymName>" + Color.HSBtoRGB(0,100,0) + " - Quits the gym queue of the specified gym. Example: /gym leave gym1."));
                p.sendMessage(Text.of(Color.GREEN + "/gym sendrules <GymName> (Username)" + Color.HSBtoRGB(0,100,0) + " - Force shows the specifed gym's rules to the player specifed."));
                p.sendMessage(Text.of(Color.GREEN + "/gym open <GymName>" + Color.HSBtoRGB(0,100,0) + " - Open a particular gym."));
                p.sendMessage(Text.of(Color.GREEN + "/gym close <GymName>" + Color.HSBtoRGB(0,100,0) + " - Close a particular gym."));
                p.sendMessage(Text.of(Color.GREEN + "/gym closeall" + Color.HSBtoRGB(0,100,0) + " - Closes all the gym's you are leader of."));
                p.sendMessage(Text.of(Color.GREEN + "/gym openall" + Color.HSBtoRGB(0,100,0) + " - Opens all the gym's you are leader of."));
                p.sendMessage(Text.of(Color.GREEN + "/gym heal" + Color.HSBtoRGB(0,100,0) + " - Heals your pokemon."));
                p.sendMessage(Text.of(Color.GREEN + "/gym quit" + Color.HSBtoRGB(0,100,0) + " - Force quits the gym battle."));
                p.sendMessage(Text.of(""));
                p.sendMessage(Text.of(Color.HSBtoRGB(218,165,32) + link1));
                p.sendMessage(Text.of(Color.RED + "Plugin Made By " + Color.HSBtoRGB(218,165,32) + "Unova Legends"));
            } else if (p.hasPermissionLevel(1)) {
                p.sendMessage(Text.of(Color.HSBtoRGB(218,165,32) +  "----- PlasmaGyms -----"));
                p.sendMessage(Text.of(""));
                if (this.plugin.getConfig().getString("config.enableredeem").equalsIgnoreCase("True")) {
                    p.sendMessage(Text.of(Color.GREEN + "/gym redeem" + Color.HSBtoRGB(0,100,0) + " - Whilst holding a Physical Gym Badge in your hand, use this command to turn your physical badge into a virtual one so you can carry on where you left off!"));
                }
                p.sendMessage(Text.of(Color.GREEN + "/gym showcase <true/false>" + Color.HSBtoRGB(0,100,0) + " - Disable or enable being given the badge showcase item!"));
                p.sendMessage(Text.of(Color.GREEN + "/gym list" + Color.HSBtoRGB(0,100,0) + " - Get a list of the gyms and there status."));
                p.sendMessage(Text.of(Color.GREEN + "/gym leaders" + Color.HSBtoRGB(0,100,0) + " - Get a list of the online gym leaders."));
                p.sendMessage(Text.of(Color.GREEN + "/gym scoreboard" + Color.HSBtoRGB(0,100,0) + " - Toggle ScoreBoard."));
                p.sendMessage(Text.of(Color.GREEN + "/gym rules <GymName>" + Color.HSBtoRGB(0,100,0) + " - Shows all the rules for the specified Gym."));
                p.sendMessage(Text.of(Color.GREEN + "/gym <check | position> <GymName>" + Color.HSBtoRGB(0,100,0) + " - Check your position in a queue. Example: /gym check gym1"));
                p.sendMessage(Text.of(Color.GREEN + "/gym join <GymName>" + Color.HSBtoRGB(0,100,0) + " - Join the queue for the gym you want. Example: /gym join gym1"));
                p.sendMessage(Text.of(Color.GREEN + "/gym <see | check> [Player]" + Color.HSBtoRGB(0,100,0) + " - Shows the gym badge case of a specific player. | = or, you can type see or check."));
                p.sendMessage(Text.of(Color.GREEN + "/gym next <GymName>" + Color.HSBtoRGB(0,100,0) + " - Grabs the first person of the specified gym queue and teleports them to the gym. (It also displays the gym rules for them in chat, so you don't need to)"));
                p.sendMessage(Text.of(Color.GREEN + "/gym remove <GymName>" + Color.HSBtoRGB(0,100,0) + " - Remove's the first person of the specified gym queue (If someone has disconnected and does not relog after a while)"));
                p.sendMessage(Text.of(Color.GREEN + "/gym giveTM <GymName> [Player]" + Color.HSBtoRGB(0,100,0) + " - Give's the TM to the player if they had there inventory full when winning the gym!"));
                p.sendMessage(Text.of(Color.GREEN + "/gym <winner | win | w> <GymName> [Player]" + Color.HSBtoRGB(0,100,0) + " - Sets the gym challeger to a winner, giving them the badge for the next gym!"));
                p.sendMessage(Text.of(Color.GREEN + "/gym <lost | lose | l> <GymName> [Player]" + Color.HSBtoRGB(0,100,0) + " - Sets the gym challeger to a loser, teleporting them out of the gym and giving them a specific timed minute cooldown!"));
                p.sendMessage(Text.of(Color.GREEN + "/gym givebadge <GymName> [player]" + Color.HSBtoRGB(0,100,0) + " - Need to give badges quickly? Then use this command to give player's there badge's, avoiding the cooldown and having to be in a queue."));
                p.sendMessage(Text.of(Color.GREEN + "/gym delbadge <GymName> [player]" + Color.HSBtoRGB(0,100,0) + " - Need to delete badges quickly? Then use this command to remove player's badge's, avoiding the cooldown and having to be in a queue."));
                p.sendMessage(Text.of(Color.GREEN + "/gym addTM <GymName>" + Color.HSBtoRGB(0,100,0) + " - Adds the item in hand as a TM to the specified gym for the winners to randomly win!"));
                p.sendMessage(Text.of(Color.GREEN + "/gym <leave | quit> <GymName>" + Color.HSBtoRGB(0,100,0) + " - Quits the gym queue of the specified gym. Example: /gym leave gym1."));
                p.sendMessage(Text.of(Color.GREEN + "/gym setwarp <GymName>" + Color.HSBtoRGB(0,100,0) + " - Used for the queue system, set a warp that is only a number. E.G: /gym setwarp 1 in the gym1 challanger spot."));
                p.sendMessage(Text.of(Color.GREEN + "/gym delwarp <GymName>" + Color.HSBtoRGB(0,100,0) + " - Used for the queue system, delete a warp that you no longer need. E.G: /gym delwarp 1 to remove the gym1 teleport."));
                p.sendMessage(Text.of(Color.GREEN + "/gym openall" + Color.HSBtoRGB(0,100,0) + " - Opens all the gym's you are leader of."));
                p.sendMessage(Text.of(Color.GREEN + "/gym closeall" + Color.HSBtoRGB(0,100,0) + " - Closes all Gym's."));
                p.sendMessage(Text.of(Color.GREEN + "/gym warp [warp name]" + Color.HSBtoRGB(0,100,0) + " - Warp to a gym warp! (For testing teleport locations of the queue system)."));
                p.sendMessage(Text.of(Color.GREEN + "/plasmagym" + Color.HSBtoRGB(0,100,0) + " - More admin commands"));
                p.sendMessage(Text.of(""));
                p.sendMessage(Text.of(Color.HSBtoRGB(218,165,32) + link1));
                p.sendMessage(Text.of(Color.RED + "Plugin Made By " + Color.HSBtoRGB(218,165,32) + "Unova Legends"));
            }

            int req = this.plugin.getConfig().getInt("config.prestige_req");

            if ((this.plugin.settings.getBadge().get("Players." + p.getUuid() + ".Badges.gym" + req) != null) && (this.plugin.settings.getBadge().getString("Players." + p.getUuid() + ".Badges.gym" + req).equals("Won"))) {
                p.sendMessage(Text.of(Color.HSBtoRGB(218,165,32) +  "----- Prestige -----"));
                p.sendMessage(Text.of(Color.HSBtoRGB(218,165,32) +  "/gym prestige - Prestige to reset your gym badges and receive rewards!"));
            } else if (args.length == 1) {
            if ((args[0].equalsIgnoreCase("scoreboard")) && (this.plugin.getConfig().getString("config.scoreboard").equals("True"))) {
                if (this.plugin.hashmap.containsKey(p)) {
                    p.sendMessage(Text.of(Color.GRAY +  "Scoreboard - " + Color.RED + "Disabled"));
                    p.setScoreboard(ScoreboardManager.clear);
                    this.plugin.hashmap.remove(p);
                } else {
                    p.setScoreboard(ScoreboardManager.board);
                    this.plugin.hashmap.put(p, null);

                    p.sendMessage(Text.of(Color.GRAY +  "Scoreboard - " + Color.GREEN + "Enabled"));
                    ScoreboardManager.updateScoreboard();
                }
            } else if (args[0].equalsIgnoreCase("1")) {
                p.sendMessage(Text.of(Color.HSBtoRGB(218,165,32) +  "----- Online Gym Leaders -----"));
                p.sendMessage(Text.of(""));                
              //  for (Player staff : Sponge.getServer().getOnlinePlayers()) {
                for (PlayerEntity staff : players.getPlayerList()) {
                    if (p.canSee(staff)) {
                        if (!staff.getName().toString().equalsIgnoreCase("YourMum")) {
                            if (!staff.hasPermissionLevel(999)) {

                                for (int i = 1; i <= 32; i++) {
                                    if ((staff.hasPermissionLevel(i)) && (this.plugin.getConfig().getString("config.gym" + i + "enabled").equalsIgnoreCase("true"))) {
                                        p.sendMessage(Text.of(Color.GREEN + staff.getName().toString() + Color.BLACK + " - " + getConfig().getString(new StringBuilder("config.gym").append(i).append("colour").toString() + "TEXT") + getConfig().getString(new StringBuilder("config.gym").append(i).toString()) + " Gym"));
                                        // p.sendMessage(Text.of(Color.GREEN + "This is Green" + TextSerializers.FORMATTING_CODE.deserialize("&4"+"TEXT").getColor(), " This is Red"));
                                    }
                                }
                            }
                        }
                    }
                }
             } else if (args[0].equalsIgnoreCase("redeem")) {

                 if (this.plugin.getConfig().getString("config.enableredeem").equalsIgnoreCase("True")) {

            //        // Optional<ItemStack> handStack = p.getItemInHand(HandTypes.MAIN_HAND);
            //         ItemStack handStack = p.getStackInHand(Hand.MAIN_HAND);
            //         // if (optStack.isPresent() && optStack.get(keys.item)

            //         for (int i = 1; i <= 32; i++) {
            //             // ItemStack badge = ItemStack.builder()
            //             // .itemType(Sponge.getRegistry().getType(ItemType.class, this.plugin.getConfig().getString("config.gym" + i + "badge")).get())
            //             // .build();

            //             String badgeID = this.plugin.getConfig().getString("config.gym" + i + "badge");

            //             // System.out.println("ForLoop");
            //             RegistryTags itemRegistry;

            //             if (itemRegistry).isPresent()) {

            //                 // System.out.println("Is Present");

            //                 ItemType modItemType = Sponge.getRegistry().getType(ItemType.class, badgeID).get();
            //                 ItemStack modItemStack = ItemStack.of(modItemType, 1);

            //                 // System.out.println("ItemType:" + modItemType);
            //                 // System.out.println("ItemStack:" + modItemStack);

            //                 if (handStack.get().getType() == modItemStack.getType()) {

            //                     // System.out.println("handStack = modItemStack");

            //                     if (this.plugin.settings.getBadge().get("Players." + p.getUuid() + ".Badges.gym" + i) != null) {
            //                         p.sendMessage(Text.of(Color.RED + "You have already redeemed or won this badge!"));
            //                     } else {
            //                         this.plugin.settings.getBadge().set("Players." + p.getUuid() + ".Badges.gym" + i, "Won");
            //                         this.plugin.settings.saveBadges();

            //                         p.sendMessage(Text.of(Color.GREEN + "Suscesfully Redeemed Badge for Gym" + i + "!"));

            //                         if (this.plugin.getConfig().getString("config.takebadge").equalsIgnoreCase("True")) {
            //                             p.setItemInHand(HandTypes.MAIN_HAND, null);
            //                         }
            //                     }
            //                 }
            //             }

                     }
             } else {
                    p.sendMessage(Text.of(Color.RED + "The Redeem Command is currently disabled to prevent duplication of gym badges."));
                }

            } else if (args[0].equalsIgnoreCase("prestige")) {
                int req = getConfig().getInt("config.prestige_req");
                if (!this.plugin.inPrest.contains(p.getUuid())) {
                    if (this.plugin.settings.getBadge().get("Players." + p.getUuid() + ".Badges.gym" + req) != null) {
                        if (this.plugin.settings.getBadge().get("Players." + p.getUuid() + ".Badges.gym" + req).equals("Won")) {
                            this.plugin.inPrest.add(p.getUuid());

                            p.sendMessage(Text.of(Color.RED + "You are about to reset your gym badges! This will delete all previous gym badges and give you money. Also so you can re-do the gym's."));
                            p.sendMessage(Text.of(Color.GREEN + "Are you sure you want to prestige? There is no going back. Type '/gym confirm' if you are sure. And '/gym deny' if you no longer want to prestige!"));
                        } else {
                            p.sendMessage(Text.of(Color.RED + "You have not won Gym" + req + " Badge!"));
                        }
                    } else {
                        p.sendMessage(Text.of(Color.RED + "You have never challenged Gym" + req + "!"));
                    }

                } else {
                    p.sendMessage(Text.of(Color.RED + "You are already in the list to confirm or deny prestiging. Type '/gym confirm' if you are sure. And '/gym deny' if you no longer want to prestige!"));
                }

            } else if (args[0].equalsIgnoreCase("confirm")) {
                int req = getConfig().getInt("config.prestige_req");

                if (this.plugin.inPrest.contains(p.getUuid() )) {
                    if ((this.plugin.settings.getBadge().get("Players." + p.getUuid()  + ".Badges.gym" + req) != null) && (this.plugin.settings.getBadge().get("Players." + p.getUuid()  + ".Badges.gym" + req).equals("Won"))) {
                        for (int i = 1; i <= 32; i++) {
                            this.plugin.settings.getBadge().set("Players." + p.getUuid()  + ".Badges.gym" + i, null);
                        }

                        int fee = getConfig().getInt("config.prestige_pay");


                         this.plugin.getEconomy().getOrCreateAccount(p.getUuid() ).get()
                                 .deposit(this.plugin.getEconomy().getDefaultCurrency(), new BigDecimal(fee), Cause.of(EventContext.builder()
                                         .add(EventContextKeys.PLUGIN, this.plugin.getContainer())
                                         .build(), this.plugin.getContainer()));

                        p.sendMessage(Text.of(Color.HSBtoRGB(218,165,32) +  "You have been payed $" + fee + "! You may now re-do all the gym's!"));
                    }

                    p.sendMessage(Text.of(Color.GREEN + "Deleted all badges you have!"));
                    this.plugin.settings.saveBadges();
                    this.plugin.inPrest.remove(p.getUuid() );
                }

            } else if (args[0].equalsIgnoreCase("deny")) {
                if (this.plugin.inPrest.contains(p.getUuid() )) {
                    this.plugin.inPrest.remove(p.getUuid() );
                    p.sendMessage(Text.of(Color.GREEN + "You have succefully denied prestiging. Your Badges will not be reset."));
                } else {
                    p.sendMessage(Text.of(Color.RED + "You have not done the prestige command! Type '/gym prestige' to get started."));
                }

            } else if (args[0].equals("list")) {
                p.sendMessage(Text.of(Color.HSBtoRGB(218,165,32) +  "----- PlasmaGyms -----"));
                p.sendMessage(Text.of(""));
                for (int i = 1; i <= 32; i++) {
                    if (this.plugin.getConfig().getString("config.gym" + i + "enabled").equalsIgnoreCase("true")) {

                        p.sendMessage(Text.of(getConfig().getString(new StringBuilder("config.gym").append(i).append("colour").toString()) + "TEXT") + getConfig().getString(new StringBuilder("config.gym").append(i).toString()) + " Gym is: " + Color.HSBtoRGB(0,100,0) + getConfig().getString(new StringBuilder("config.gym").append(i).append("stat").toString()) + Color.BLUE + " - " + "Level Cap = " + getConfig().getString(new StringBuilder("config.gym").append(i).append("lvlcap").toString()));
                    }

                }
            } else if ((args[0].equals("open")) && (p.hasPermissionLevel(1))) {
                p.sendMessage(Text.of(Color.DARK_GRAY + "You need to specify a gym to open!"));
                p.sendMessage(Text.of(Color.DARK_GRAY +  "Proper Usage: "+ Color.RED + "/gym open <gym#>"));
            } else if ((args[0].equalsIgnoreCase("see")) || (args[0].equalsIgnoreCase("s"))) {
                commandMessage.executeWithPrefix(p.getCommandSource(), "gym see " + p.getName());
            } else if ((args[0].equals("sendrules")) && (p.hasPermissionLevel(1))) {
                p.sendMessage(Text.of(Color.DARK_GRAY +  "You need to specify a gym to send rules about, as well as a player to send the rules too."));
                p.sendMessage(Text.of(Color.DARK_GRAY +  "Proper Usage: "+ Color.RED + "/gym sendrules <gym#> (username)"));
            } else if ((args[0].equals("close")) && (p.hasPermissionLevel(1))) {
                p.sendMessage(Text.of(Color.DARK_GRAY +  "You need to specify a gym to close!"));
                p.sendMessage(Text.of(Color.DARK_GRAY +  "Proper Usage: "+ Color.RED + "/gym close <gym#>"));
            } else if (args[0].equals("rules")) {
                p.sendMessage(Text.of(Color.DARK_GRAY +  "You need to specify a gym that you want to read the rules of!"));
                p.sendMessage(Text.of(Color.DARK_GRAY +  "Proper Usage: "+ Color.RED + "/gym rules <gym#>"));
            } else if (args[0].equals("join")) {
                p.sendMessage(Text.of(Color.DARK_GRAY +  "You need to specify a gym queue that you want to join!"));
                p.sendMessage(Text.of(Color.DARK_GRAY +  "Proper Usage: "+ Color.RED + "/gym join <gym#>"));
            } else if (args[0].equals("leave")) {
                p.sendMessage(Text.of(Color.DARK_GRAY +  "You need to specify a gym queue that you want to leave!"));
                p.sendMessage(Text.of(Color.DARK_GRAY +  "Proper Usage: "+ Color.RED + "/gym leave <gym#>"));
            } else if ((args[0].equalsIgnoreCase("check")) || (args[0].equalsIgnoreCase("position"))) {
                p.sendMessage(Text.of(Color.DARK_GRAY +  "You need to specify a gym queue position that you want to check!"));
                p.sendMessage(Text.of(Color.DARK_GRAY +  "Proper Usage: "+ Color.RED + "/gym position <gym#>"));
            } else if (args[0].equals("next")) {
                p.sendMessage(Text.of(Color.DARK_GRAY +  "You need to specify a gym to grab the first player of a queue for!"));
                p.sendMessage(Text.of(Color.DARK_GRAY +  "Proper Usage: "+ Color.RED + "/gym next <gym#>"));
            } else if ((args[0].equals("heal")) && (p.hasPermissionLevel(1)) && (this.plugin.enableGymHeal.equalsIgnoreCase("true"))) {
                commandMessage.executeWithPrefix(players.getServer().getCommandSource(), "pokeheal " + p.getName().toString());
                p.sendMessage(Text.of("Your pixelmon have been healed!"));
            } else if ((args[0].equals("heal")) && (p.hasPermissionLevel(1)) && (!this.plugin.enableGymHeal.equalsIgnoreCase("true"))) {
                p.sendMessage(Text.of(Color.RED + "Gym/E4 Leader healing disabled in the plugin config"));
            } else if ((args[0].equals("quit")) && (p.hasPermissionLevel(1))) {
                commandMessage.executeWithPrefix(players.getServer().getCommandSource(), "endbattle");
                p.sendMessage(Text.of("You have successfully quit the battle!"));
            } else if ((args[0].equalsIgnoreCase("closeall"))) {
                if (p.hasPermissionLevel(2)) {
                    p.sendMessage(Text.of(Color.CYAN + "All gyms are now closed."));
                    ServerMessageEvents.GameMessage.notifyAll(Text.of(Color.DARK_GRAY + "[" + Color.CYAN + getConfig().getString("config.title") + Color.DARK_GRAY + "] " + Color.YELLOW + "All gyms are now closed."));

                    getConfig().set("config.gym1stat", "Closed");
                    getConfig().set("config.gym2stat", "Closed");
                    getConfig().set("config.gym3stat", "Closed");
                    getConfig().set("config.gym4stat", "Closed");
                    getConfig().set("config.gym5stat", "Closed");
                    getConfig().set("config.gym6stat", "Closed");
                    getConfig().set("config.gym7stat", "Closed");
                    getConfig().set("config.gym8stat", "Closed");
                    getConfig().set("config.gym9stat", "Closed");
                    getConfig().set("config.gym10stat", "Closed");
                    getConfig().set("config.gym11stat", "Closed");
                    getConfig().set("config.gym12stat", "Closed");
                    getConfig().set("config.gym13stat", "Closed");
                    getConfig().set("config.gym14stat", "Closed");
                    getConfig().set("config.gym15stat", "Closed");
                    getConfig().set("config.gym16stat", "Closed");
                    getConfig().set("config.gym17stat", "Closed");
                    getConfig().set("config.gym18stat", "Closed");
                    getConfig().set("config.gym19stat", "Closed");
                    getConfig().set("config.gym20stat", "Closed");
                    getConfig().set("config.gym21stat", "Closed");
                    getConfig().set("config.gym22stat", "Closed");
                    getConfig().set("config.gym23stat", "Closed");
                    getConfig().set("config.gym24stat", "Closed");
                    getConfig().set("config.gym25stat", "Closed");
                    getConfig().set("config.gym26stat", "Closed");
                    getConfig().set("config.gym27stat", "Closed");
                    getConfig().set("config.gym28stat", "Closed");
                    getConfig().set("config.gym29stat", "Closed");
                    getConfig().set("config.gym30stat", "Closed");
                    getConfig().set("config.gym31stat", "Closed");
                    getConfig().set("config.gym32stat", "Closed");

                    ScoreboardManager.updateScoreboard();
                } else if (p.hasPermissionLevel(1)) {

                    for (int i = 11; i <= 42; i++) {
                        if (p.hasPermissionLevel(i)) {
                            getConfig().set("config.gym" + i + "stat", "Closed");
                            ScoreboardManager.updateScoreboard();
                            p.sendMessage(Text.of(Color.RED + "Gym" + i + " is now Closed."));
                        }

                    }
                }
            } else if (args[0].equalsIgnoreCase("openall")) {

                for (int i = 11; i <= 42; i++) {
                    if (p.hasPermissionLevel(i)) {
                        getConfig().set("config.gym" + i + "stat", "Open");
                        ScoreboardManager.updateScoreboard();
                        p.sendMessage(Text.of(Color.GREEN + "Gym" + i + " is now open."));
                    }

                }
            
        } else if (args.length == 2) {
            if ((args[0].equalsIgnoreCase("admincheck")) && (players.getPlayer(args[1])!=null) )  {
                UUID uuid = players.getPlayer(args[1]).getUuid();
                PlayerEntity playerTarget = players.getPlayer(uuid);
                for (int i = 11; i <= 42; i++) {
                    if (((List) this.plugin.queues.get(Integer.valueOf(i))).contains(uuid)) {
                        p.sendMessage(Text.of(Color.GREEN + playerTarget.getDisplayName().toString() + " is in gym" + i + " queue."));
                    }
                    if (((List) this.plugin.inArena.get(Integer.valueOf(i))).contains(uuid)) {
                        p.sendMessage(Text.of(Color.GREEN + playerTarget.getDisplayName().toString()+ " is in gym" + i + " inArena."));
                    }
                    if (((List) this.plugin.cooldownGym.get(Integer.valueOf(i))).contains(uuid)) {
                        p.sendMessage(Text.of(Color.GREEN + playerTarget.getDisplayName().toString() + " is in gym" + i + " Cooldown."));
                    }
                }
                p.sendMessage(Text.of(Color.HSBtoRGB(218,165,32) +  " " + uuid));
            }
            if (args[0].equalsIgnoreCase("join")) {
                boolean gymName = false;
                int gym = 0;
                outerloop:

                for (int g = 1; g <= 32; g++) {
                    if (this.plugin.getConfig().getString("config.gym" + g).equalsIgnoreCase(args[1])) {
                        gymName = true;
                        gym = g;
                        break outerloop;
                    }
                }

                if (args[1].matches("(gym|GYM)[1-9]?[0-9]") || gymName == true) {

                    if (gymName == true) {
                        // do nothing all good.
                    } else {
                        String gymArg = args[1].replace("gym", "");

                        try {
                            gym = Integer.parseInt(gymArg);

                        } catch (NumberFormatException nfe) {
                            p.sendMessage(Text.of(Color.RED + args[1] + " is not a gym!"));
                            return;
                        }
                    }

                    int i = gym - 1;

                    UUID o = p.getUuid() ;

                    if (!((Map) this.plugin.cooldownTime.get(Integer.valueOf(gym))).containsKey(o)) {
                        if (this.plugin.settings.getBadge().get("Players." + p.getUuid()  + ".Badges.gym" + gym) != null) {
                            if (this.plugin.settings.getBadge().get("Players." + p.getUuid()  + ".Badges.gym" + gym).equals("Won")) {
                                p.sendMessage(Text.of(Color.RED + "You have already completed this gym! You may not do it again!"));
                            }

                        } else if (gym == 1) {
                            if (((List) this.plugin.queues.get(Integer.valueOf(gym))).contains(p.getUuid() )) {
                                p.sendMessage(Text.of(Color.RED + "You are already in this queue, please wait to be teleported."));
                            } else {
                                ((List) this.plugin.queues.get(Integer.valueOf(gym))).add(p.getUuid() );

                                // String.valueOf(this.plugin.getConfig().getString("config.gym1colour"))

                             //   p.sendMessage(Text.of(Color.GREEN + "Added to queue: " + Color.YELLOW + gym + Color.BLACK +" (" + TextSerializers.FORMATTING_CODE.deserialize(String.valueOf(this.plugin.getConfig().getString("config.gym" + gym + "colour")) + "TEXT").getColor() + this.plugin.getConfig().getString("config.gym1") + Color.BLACK, ")"));
                                p.sendMessage(Text.of(Color.HSBtoRGB(218,165,32) +  "You are in position " + ((List) this.plugin.queues.get(Integer.valueOf(gym))).size() + " for the " + getConfig().getString("config.gym1") + " Gym"));
                             //   p.sendMessage(Text.of(Color.GREEN + "Notified gym leaders of gym1" +  Color.BLACK +  " (" + TextSerializers.FORMATTING_CODE.deserialize(this.plugin.getConfig().getString("config.gym" + gym + "colour") + "TEXT").getColor() + this.plugin.getConfig().getString("config.gym1") + Color.BLACK, ")"+ Color.GREEN + " that you are waiting to be battled!"));

                                for (PlayerEntity leader : players.getPlayerList()) {
                                    if (leader.hasPermissionLevel( gym)) {
                                   //     leader.sendMessage(Text.of(Color.BLUE, "A challenger has joined queue " + Color.YELLOW + gym + Color.BLACK + " (" + TextSerializers.FORMATTING_CODE.deserialize(new StringBuilder(this.plugin.getConfig().getString(new StringBuilder("config.gym")
                                     //           .append(gym).append("colour").toString())).toString() + "TEXT").getColor() + this.plugin.getConfig().getString(new StringBuilder("config.gym").append(gym).toString()) + Color.BLACK, ")"+ Color.HSBtoRGB(218,165,32) +  " (" + p.getName() + ")"));

                                        leader.sendMessage(Text.of(Color.BLUE + "Type /gym next gym" + gym + " or " + args[1] + " to teleport them to your gym."));
                                    }

                                }

                            }

                        } else if (this.plugin.getConfig().getString("config.gymrequire").equalsIgnoreCase("False") || this.plugin.settings.getBadge().get("Players." + p.getUuid()  + ".Badges.gym" + i) != null) {
                            if (this.plugin.getConfig().getString("config.gymrequire").equalsIgnoreCase("False") || this.plugin.settings.getBadge().getString("Players." + p.getUuid()  + ".Badges.gym" + i).equals("Won")) {
                                if (((List) this.plugin.queues.get(Integer.valueOf(gym))).contains(p.getUuid() )) {
                                    p.sendMessage(Text.of(Color.RED + "You are already in this queue, please wait to be teleported."));
                                    return;
                                }

                                this.plugin.queues.get(Integer.valueOf(gym)).add(p.getUuid() );

                             //   p.sendMessage(Text.of(Color.GREEN + "Added to queue: " + Color.YELLOW + gym + Color.BLACK + " (" + Serializer.FORMATTING_CODE.deserialize(new StringBuilder(String.valueOf(this.plugin.getConfig().getString(new StringBuilder("config.gym")
                              //          .append(gym).append("colour").toString())))
                               //         .toString() + "TEXT").getColor() + this.plugin.getConfig().getString(new StringBuilder("config.gym").append(gym).toString()) + Color.BLACK, ")"));
                                p.sendMessage(Text.of(Color.HSBtoRGB(218,165,32) +  "You are in position " + ((List) this.plugin.queues.get(Integer.valueOf(gym))).size() + " for the " + getConfig().getString(new StringBuilder("config.gym").append(gym).toString()) + " Gym"));
                            //    p.sendMessage(Text.of(Color.GREEN + "Notified gym leaders of gym" + gym + Color.BLACK + " (" + TextSerializers.FORMATTING_CODE.deserialize(this.plugin.getConfig().getString(new StringBuilder("config.gym").append(gym).append("colour").toString()) + "TEXT").getColor() + this.plugin.getConfig().getString(new StringBuilder("config.gym").append(gym).toString()) + Color.BLACK, ")"+ Color.GREEN + " that you are waiting to be battled!"));
                                
                                for (PlayerEntity leader : players.getPlayerList()) {
                                    if (leader.hasPermissionLevel(gym)) {
                                 //       leader.sendMessage(Text.of(Color.BLUE + "A challenger has joined queue " + Color.YELLOW + gym + Color.BLACK +" (" + TextSerializers.FORMATTING_CODE.deserialize(new StringBuilder(String.valueOf(this.plugin.getConfig()
                                   //             .getString(new StringBuilder("config.gym")
                                     //                   .append(gym).append("colour").toString()))).toString() + "TEXT").getColor() +  this.plugin.getConfig().getString(new StringBuilder("config.gym").append(gym).toString()) + Color.BLACK, ")"+ Color.HSBtoRGB(218,165,32) +  " (", p.getName(), ")"));

                                        leader.sendMessage(Text.of(Color.BLUE + "Type /gym next " + args[1] + " to teleport them to your gym."));
                                    }
                                }
                            } else {
                                p.sendMessage(Text.of(Color.RED + "You cannot join this queue as you have not won the previous badge!"));
                            }
                        } else {
                            p.sendMessage(Text.of(Color.RED + "You cannot join the gym queue for gym" + gym + " because you do not have the previous badge. (gym" + i + ")"));
                        }
                    } else {
                        p.sendMessage(Text.of(Color.RED + "You have to wait " + ((Map) this.plugin.cooldownTime.get(Integer.valueOf(gym))).get(o) + " minutes before you can try gym" + gym + " again."));
                    }

                } else {
                    p.sendMessage(Text.of(Color.RED + "Gym " + args[1] + " is not a valid gym."));
                }

                gymName = false;
            } else if ((args[0].equalsIgnoreCase("check")) || (args[0].equalsIgnoreCase("position"))) {
                boolean gymName = false;
                int gym = 0;
                outerloop:

                for (int g = 1; g <= 32; g++) {
                    if (this.plugin.getConfig().getString("config.gym" + g).equalsIgnoreCase(args[1])) {
                        gymName = true;

                        gym = g;

                        break outerloop;
                    }

                }

                if (args[1].matches("(gym|GYM)[1-9]?[0-9]") || gymName == true) {

                    // int i = gym - 1;

                    if (gymName == true) {
                        // do nothing all good.
                    } else {
                        String gymArg = args[1].replace("gym", "");

                        try {
                            gym = Integer.parseInt(gymArg);
                        } catch (NumberFormatException nfe) {
                            p.sendMessage(Text.of(Color.RED + args[1] + " is not a gym!"));
                            return;
                        }
                    }

                    PlayerEntity playerFirst = null;
                    if (((List) this.plugin.queues.get(Integer.valueOf(gym))).size() > 0) {
                        UUID uuid = (UUID) ((List) this.plugin.queues.get(Integer.valueOf(gym))).get(0);
                        playerFirst = players.getPlayer(uuid);

                        int iu = ((List) this.plugin.queues.get(Integer.valueOf(gym))).indexOf(p.getUuid() ) + 1;

                        if (((List) this.plugin.queues.get(Integer.valueOf(gym))).contains(p.getUuid() )) {
                            p.sendMessage(Text.of(Color.HSBtoRGB(218,165,32) +  "You are currently in position " + iu + " for the " + getConfig().getString(new StringBuilder("config.gym").append(gym).toString()) + " Gym"));
                            p.sendMessage(Text.of(Color.HSBtoRGB(218,165,32) +  "The queue size for the " + getConfig().getString(new StringBuilder("config.gym").append(gym).toString()) + " Gym is " + ((List) this.plugin.queues.get(Integer.valueOf(gym))).size()));
                        } else if ((p.hasPermissionLevel(gym)) && (!((List) this.plugin.queues.get(Integer.valueOf(gym))).contains(p.getUuid() ))) {
                            p.sendMessage(Text.of(Color.HSBtoRGB(218,165,32) +  "The queue size for the " + getConfig().getString(new StringBuilder("config.gym").append(gym).toString()) + " Gym is " + ((List) this.plugin.queues.get(Integer.valueOf(gym))).size()));
                            p.sendMessage(Text.of(Color.HSBtoRGB(218,165,32) +  "The first player of the queue is: (" + playerFirst.getName() + ")"));
                        } else if ((((List) this.plugin.queues.get(Integer.valueOf(gym))).contains(p.getUuid() )) && (p.hasPermissionLevel(gym))) {
                            p.sendMessage(Text.of(Color.HSBtoRGB(218,165,32) +  "The queue size for the " + getConfig().getString(new StringBuilder("config.gym").append(gym).toString()) + " Gym is " + ((List) this.plugin.queues.get(Integer.valueOf(gym))).size()));
                            p.sendMessage(Text.of(Color.HSBtoRGB(218,165,32) +  "The first player of the queue is: (" + playerFirst.getName() + ")"));
                        } else {
                            p.sendMessage(Text.of(Color.RED + "You are not in queue " + args[1]));
                        }
                    } else {
                        p.sendMessage(Text.of(Color.RED + "There is no one in this queue!"));
                    }
                } else {
                    p.sendMessage(Text.of(Color.RED + "Gym " + args[1] + " is not a valid gym."));
                }
                gymName = false;
            } else if ((args[0].equalsIgnoreCase("leave")) || (args[0].equalsIgnoreCase("quit"))) {

                boolean gymName = false;
                int gym = 0;

                outerloop:

                for (int g = 1; g <= 32; g++) {
                    if (this.plugin.getConfig().getString("config.gym" + g).equalsIgnoreCase(args[1])) {
                        gymName = true;

                        gym = g;

                        break outerloop;
                    }

                }

                if (args[1].matches("(gym|GYM)[1-9]?[0-9]") || gymName == true) {

                    // int i = gym - 1;

                    if (gymName == true) {
                        // do nothing all good.
                    } else {
                        String gymArg = args[1].replace("gym", "");

                        try {
                            gym = Integer.parseInt(gymArg);
                        } catch (NumberFormatException nfe) {
                            p.sendMessage(Text.of(Color.RED + args[1] + " is not a gym!"));
                            return;
                        }
                    }

                    if (((List) this.plugin.queues.get(Integer.valueOf(gym))).contains(p.getUuid() )) {
                        if (!((List) this.plugin.inArena.get(Integer.valueOf(gym))).contains(p.getUuid() )) {
                            ((List) this.plugin.queues.get(Integer.valueOf(gym))).remove(p.getUuid() );
                          //  World w = players.getServer().getWorlds(this.plugin.settings.getData().getString("warps.spawn.world")).orElse(null);
                            double x = this.plugin.settings.getData().getDouble("warps.spawn.x");
                            double y = this.plugin.settings.getData().getDouble("warps.spawn.y");
                            double z = this.plugin.settings.getData().getDouble("warps.spawn.z");
                         //   p.setLocation(new Location<>(w, x, y, z));

                            p.sendMessage(Text.of(Color.GREEN + "You have successfully been removed from the gym" + gym + " queue!"));
                        } else {
                            p.sendMessage(Text.of(Color.RED + "You cannot leave the queue as you are in the area!"));
                        }
                    } else {
                        p.sendMessage(Text.of(Color.RED + "You are not in queue " + gym));
                    }
                } else {
                    p.sendMessage(Text.of(Color.RED + "Gym " + args[1] + " is not a valid gym."));
                }
                gymName = false;

            } else if (args[0].equalsIgnoreCase("remove")) {

                boolean gymName = false;
                int gym = 0;

                outerloop:

                for (int g = 1; g <= 32; g++) {
                    if (this.plugin.getConfig().getString("config.gym" + g).equalsIgnoreCase(args[1])) {
                        gymName = true;

                        gym = g;

                        break outerloop;
                    }

                }

                if (args[1].matches("(gym|GYM)[1-9]?[0-9]") || gymName == true) {

                    // int i = gym - 1;

                    if (gymName == true) {
                        // do nothing all good.
                    } else {
                        String gymArg = args[1].replace("gym", "");

                        try {
                            gym = Integer.parseInt(gymArg);
                        } catch (NumberFormatException nfe) {
                            p.sendMessage(Text.of(Color.RED + args[1] + " is not a gym!"));
                            return;
                        }
                    }

                    if ((p.hasPermissionLevel(1)) && ((p.hasPermissionLevel(args[1])) || (p.hasPermissionLevel(2)))) {
                        UUID removeUUID = (UUID) ((List) this.plugin.queues.get(Integer.valueOf(gym))).get(0);
                        PlayerEntity toRemove = players.getPlayer(removeUUID);

                        if (this.plugin.settings.getData().get("warps.spawn") != null) {
                            ((List) this.plugin.queues.get(Integer.valueOf(gym))).remove(0);

                            if (((List) this.plugin.inArena.get(Integer.valueOf(gym))).contains(removeUUID)) {
                                ((List) this.plugin.inArena.get(Integer.valueOf(gym))).remove(removeUUID);
                            }

                          //  World w = playerSearch.getServer().getWorlds(this.plugin.settings.getData().getString("warps.spawn.world")).get();
                            double x = this.plugin.settings.getData().getDouble("warps.spawn.x");
                            double y = this.plugin.settings.getData().getDouble("warps.spawn.y");
                            double z = this.plugin.settings.getData().getDouble("warps.spawn.z");
                       //     toRemove.setLocation(new Location<>(w, x, y, z));
                            toRemove.sendMessage(Text.of(Color.GREEN + "You have been removed from the queue by a gym leader! (" + TextSerializers.FORMATTING_CODE.deserialize(new StringBuilder(String.valueOf(this.plugin.getConfig().getString(new StringBuilder("config.gym").append(gym).append("colour").toString()))).append(TextStyles.BOLD)
                                    .append(this.plugin.getConfig().getString(new StringBuilder("config.gym").append(gym).toString())).append(Color.GREEN)
                                    .append(") You can re-enter the queue, but make sure you are not Afk and co-operate with the gym leader!").toString() + "TEXT").getColor()));

                            p.sendMessage(Text.of(Color.GREEN + "Successfully telported " + toRemove.getName() + " out of your gym!"));
                            p.sendMessage(Text.of(Color.GREEN + "You are now ready for your next battle, type: /gym next gym" + gym));
                        } else {
                            p.sendMessage(Text.of(Color.RED + "Warp point 'spawn' does not exist. Type: /gym setwarp spawn. (this is the teleport location to move challengers out of the gym."));
                        }

                    }

                } else {
                    p.sendMessage(Text.of(Color.RED + "Gym " + args[1] + " is not a valid gym."));
                }

                gymName = false;
            } else if (args[0].equalsIgnoreCase("next")) {

                boolean gymName = false;
                int gym = 0;

                outerloop:

                for (int g = 1; g <= 32; g++) {
                    if (this.plugin.getConfig().getString("config.gym" + g).equalsIgnoreCase(args[1])) {
                        gymName = true;

                        gym = g;

                        break outerloop;
                    }

                }

                if (args[1].matches("(gym|GYM)[1-9]?[0-9]") || gymName == true) {

                    // int i = gym - 1;

                    if (gymName == true) {
                        // do nothing all good.
                    } else {
                        String gymArg = args[1].replace("gym", "");

                        try {
                            gym = Integer.parseInt(gymArg);
                        } catch (NumberFormatException nfe) {
                            p.sendMessage(Text.of(Color.RED + args[1] + " is not a gym!"));
                            return;
                        }
                    }

                    int add = gym + 1;

                    if (p.hasPermissionLevel(1) || p.hasPermissionLevel(2)) {
                        if (p.hasPermissionLevel(gym)) {
                            ServerPlayerEntity playerToTeleport = null;
                            while (playerToTeleport == null) {
                                if (!this.plugin.queues.get(Integer.valueOf(gym)).isEmpty()) {
                                    if (this.plugin.settings.getData().get("warps.gym" + gym) == null) {
                                        p.sendMessage(Text.of(Color.RED + "Warp " + args[1] + " does not exist!"));
                                        return;
                                    }

                                    UUID uuid = (UUID) ((List) this.plugin.queues.get(Integer.valueOf(gym))).get(0);
                                    playerToTeleport = players.getPlayer(uuid);

                                    p.sendMessage(Text.of(Color.GREEN + "Getting first player from queue "+ Color.HSBtoRGB(218,165,32) +  " (" + playerToTeleport.getName() + ")" + Color.YELLOW + gym + Color.BLACK + " (" + new StringBuilder(this.plugin.getConfig().getString(new StringBuilder("config.gym")
                                            .append(gym).append("colour").toString()) + "TEXT") + this.plugin.getConfig().getString(new StringBuilder("config.gym").append(gym).toString()) + Color.BLACK + ")"));
                                    p.sendMessage(Text.of(Color.BLUE + "Make sure you type "+ Color.RED + "/gym lost gym" + gym + " (player) " + Color.BLUE + "or "+ Color.GREEN + "/gym win gym" + gym + " (player)" + Color.BLUE + " after they have won or lost the battle. (They need it to join gym" + add + " queue (If they won.)) And it teleports them out of your gym!"));
                                } else {
                                    p.sendMessage(Text.of(Color.RED + "There are currently no people in the queue " + Color.YELLOW + gym + Color.BLACK + " (" + new StringBuilder(String.valueOf(this.plugin.getConfig().getString(new StringBuilder("config.gym")
                                            .append(gym).append("colour").toString()))).toString() + this.plugin.getConfig().getString(new StringBuilder("config.gym").append(gym).toString()) + Color.BLACK + ")"));

                                    return;
                                }
                            }

                            if (this.plugin.getConfig().getString("config.gymfee").equalsIgnoreCase("True")) {
                                int fee = getConfig().getInt("config.gymfee");
             //                   UniqueAccount account = this.plugin.getEconomy().getOrCreateAccount(playerToTeleport.getUuid());
               //                 UniqueAccount pAccount = this.plugin.getEconomy().getOrCreateAccount(p.getUuid() ).get();
                                CommandManager getCommand;
                 //               if (account.getBalance(this.plugin.getEconomy().getDefaultCurrency()).doubleValue() >= fee) {
                                   // getCommand.sendCommandTree(playerToTeleport, "pay " + p.getName() + " " + getConfig().getString("config.gymfeeamount"));
                                    getCommand.sendCommandTree(playerToTeleport);
                             //       World w = players.getServer().getWorlds(this.plugin.settings.getData().getString("warps.gym" + gym + ".world")).get();

                          //          TransactionResult TookMoney = account.withdraw(this.plugin.getEconomy().getDefaultCurrency(), new BigDecimal(fee), Sponge.getCauseStackManager().getCurrentCause());

                            //        if (TookMoney.getResult() == ResultType.SUCCESS) {
                             //           pAccount.deposit(this.plugin.getEconomy().getDefaultCurrency(), new BigDecimal(fee), Sponge.getCauseStackManager().getCurrentCause());
                                    } else {
                                        // TODO ?
                                        return;
                                    }

                                    double x = this.plugin.settings.getData().getDouble("warps.gym" + gym + ".x");
                                    double y = this.plugin.settings.getData().getDouble("warps.gym" + gym + ".y");
                                    double z = this.plugin.settings.getData().getDouble("warps.gym" + gym + ".z");

                                 //   playerToTeleport.setLocation(new Location<>(w, x, y, z));

                                    playerToTeleport.sendMessage(Text.of(Color.GREEN + "Teleported to " + Color.YELLOW + args[1] + "!"));

                                    playerToTeleport.sendMessage(Text.of(Color.GREEN + "Welcome to " + TextSerializers.FORMATTING_CODE.deserialize(new StringBuilder(String.valueOf(this.plugin.getConfig().getString(new StringBuilder("config.gym").append(gym)
                                            .append("colour").toString()))).toString() + "TEXT").getColor() + this.plugin.getConfig().getString(new StringBuilder("config.gym").append(gym).toString()), " Gym!"));

                                    // playerToTeleport.sendMessage(Text.of(Color.GREEN + "Welcome to " + TextSerializers.FORMATTING_CODE.deserialize(new StringBuilder(String.valueOf(this.plugin.getConfig().getString(new StringBuilder("config.").append(args[1])
                                    // .append("colour").toString()))) +this.plugin.getConfig().getString(new StringBuilder("config.").append(args[1]).toString())).append(" Gym!").toString()));
                                    playerToTeleport.sendMessage(Text.of(Color.HSBtoRGB(218,165,32) +  "----- " + getConfig().getString(new StringBuilder("config.gym").append(gym).toString()) + " Gym Rules -----"));
                                    playerToTeleport.sendMessage(Text.of(""));
                                    playerToTeleport.sendMessage(Text.of(Color.GREEN + "1) " + getConfig().getString(new StringBuilder("config.gym").append(gym).append("rule1").toString())));
                                    playerToTeleport.sendMessage(Text.of(Color.GREEN + "2) " + getConfig().getString(new StringBuilder("config.gym").append(gym).append("rule2").toString())));
                                    playerToTeleport.sendMessage(Text.of(Color.GREEN + "3) " + getConfig().getString(new StringBuilder("config.gym").append(gym).append("rule3").toString())));
                                    playerToTeleport.sendMessage(Text.of(Color.GREEN + "4) " + getConfig().getString(new StringBuilder("config.gym").append(gym).append("rule4").toString())));
                                    playerToTeleport.sendMessage(Text.of(Color.GREEN + "5) " + getConfig().getString(new StringBuilder("config.gym").append(gym).append("rule5").toString())));
                                    // Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "pokeheal " + playerToTeleport.getName().toString());
                                    // Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "pokeheal " + p.getName().toString());
                                    playerToTeleport.sendMessage(Text.of(Color.GREEN + "Your pixelmon have been healed!"));

                                    ((List) this.plugin.inArena.get(Integer.valueOf(gym))).add(playerToTeleport.getUniqueId());
                                } else {
                                    playerToTeleport.sendMessage(Text.of(Color.RED + "You do not have enough money to face the gym!"));
                                    ((List) this.plugin.queues.get(Integer.valueOf(gym))).remove(0);
                                    p.sendMessage(Text.of(Color.RED + "First player did not have enough money, type /gym next gym" + gym + " to get the next player."));
                                    return;
                                }

                            } else {
                                World w = Sponge.getServer().getWorld(this.plugin.settings.getData().getString("warps.gym" + gym + ".world")).get();
                                double x = this.plugin.settings.getData().getDouble("warps.gym" + gym + ".x");
                                double y = this.plugin.settings.getData().getDouble("warps.gym" + gym + ".y");
                                double z = this.plugin.settings.getData().getDouble("warps.gym" + gym + ".z");
                                playerToTeleport.setLocation(new Location<>(w, x, y, z));
                                playerToTeleport.sendMessage(Text.of(Color.GREEN + "Teleported to " + Color.YELLOW + args[1] + "!"));
                                playerToTeleport.sendMessage(Text.of(Color.GREEN + "Welcome to " + TextSerializers.FORMATTING_CODE.deserialize(new StringBuilder(String.valueOf(this.plugin.getConfig().getString(new StringBuilder("config.gym").append(gym).append("colour").toString()))).append(TextStyles.BOLD).append(this.plugin.getConfig().getString(new StringBuilder("config.gym").append(gym).toString())).append(" Gym!").toString() + "TEXT").getColor()));
                                playerToTeleport.sendMessage(Text.of(Color.HSBtoRGB(218,165,32) +  "----- " + getConfig().getString(new StringBuilder("config.gym").append(gym).toString()) + " Gym Rules -----"));
                                playerToTeleport.sendMessage(Text.of(""));
                                playerToTeleport.sendMessage(Text.of(Color.GREEN + "1) " + getConfig().getString(new StringBuilder("config.gym").append(gym).append("rule1").toString())));
                                playerToTeleport.sendMessage(Text.of(Color.GREEN + "2) " + getConfig().getString(new StringBuilder("config.gym").append(gym).append("rule2").toString())));
                                playerToTeleport.sendMessage(Text.of(Color.GREEN + "3) " + getConfig().getString(new StringBuilder("config.gym").append(gym).append("rule3").toString())));
                                playerToTeleport.sendMessage(Text.of(Color.GREEN + "4) " + getConfig().getString(new StringBuilder("config.gym").append(gym).append("rule4").toString())));
                                playerToTeleport.sendMessage(Text.of(Color.GREEN + "5) " + getConfig().getString(new StringBuilder("config.gym").append(gym).append("rule5").toString())));
                                Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "pokeheal " + playerToTeleport.getName().toString());
                                Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "pokeheal " + p.getName().toString());
                                playerToTeleport.sendMessage(Text.of(Color.GREEN + "Your pixelmon have been healed!"));

                                ((List) this.plugin.inArena.get(Integer.valueOf(gym))).add(playerToTeleport.getUniqueId());
                            }

                        } else {
                            p.sendMessage(Text.of(Color.RED + "You do not have permission to open gym" + gym + "!"));
                        }

            } else {
                        p.sendMessage(Text.of(Color.RED + "You are not a gym leader, you do not have permission to do this command!"));
            }

        } else {
                    p.sendMessage(Text.of(Color.RED + "Gym " + args[1] + " is not a valid gym."));
        }
                gymName = false;
        }

            if (args[0].equalsIgnoreCase("showcase")) {
                 if (args[1].equalsIgnoreCase("false")) {
                //     this.plugin.settings.getExtras().set("showcase." + p.getUuid() , "false");
                //     p.sendMessage(Text.of(Color.GREEN + "Gym Badge Showcase will no longer be given to you!"));
                //     this.plugin.settings.saveExtras();
                }

                if (args[1].equalsIgnoreCase("true")) {
                    // this.plugin.settings.getExtras().set("showcase." + p.getUuid() , "true");
                    // p.sendMessage(Text.of(Color.GREEN + "Gym Badge Showcase will now be given to you!"));
                    // this.plugin.settings.saveExtras();
                }
            }

            if ((args[0].equalsIgnoreCase("see")) || (args[0].equalsIgnoreCase("s"))) {
                if (players.getServer().getPlayerManager().getPlayer(args[1]).orElse(null) != null) {
                //    PlayerEntity playerBadges = players.getServer().getPlayer(args[1]).get();

                    // InventoryArchetype invType = null;
                    int width = 0;
                    int updown = 0;

                    for (int j = 1; j <= 32; j++) {

                        int u = j + 1;

                        if (this.plugin.getConfig().getString("config.gym" + j + "enabled").equalsIgnoreCase("True")) {
                            if ((this.plugin.getConfig().getString("config.gym" + u + "enabled")) != null) {
                                if (this.plugin.getConfig().getString("config.gym" + u + "enabled").equalsIgnoreCase("False")) {

                                    int rows = 0;
                                    int slots = 0;

                                    if (j <= 9) {
                                        rows = 1;
                                        slots = 9;
                                    } else if (j >= 10 && j <= 18) {
                                        rows = 2;
                                        slots = 9;
                                    } else if (j >= 19 && j <= 27) {
                                        rows = 3;
                                        slots = 9;
                                    } else if (j >= 28 && j <= 36) {
                                        rows = 4;
                                        slots = 9;
                                    }

                                    updown = rows;
                                    width = slots;

                                }
                            }
                        }

                    }

                    Inventory myInventory_ = Inventory.of(InventoryDOUBLE_CHEST)
                            .property(InventoryTitle.PROPERTY_NAME, InventoryTitle.of(Text.of(Color.GREEN + "Badges!"))).property("inventorydimension", InventoryDimension.of(width, updown))
                            .build(this.plugin);

                    for (int i = 1; i <= 32; i++) {
                        int u = i - 1;

                        if (this.plugin.settings.getBadge().get("Players." + players.getUniqueId() + ".Badges.gym" + i) != null) {
                            if ((this.plugin.settings.getBadge().getString("Players." + playerBadges.getUniqueId() + ".Badges.gym" + i).equalsIgnoreCase("Won")) && (this.plugin.getConfig().getString("config.gym" + i + "badge") != null)) {
                                ItemStack badge = ItemStack.builder()
                                        .itemType(Sponge.getRegistry().getType(ItemType.class, this.plugin.getConfig().getString("config.gym" + i + "badge")).get())
                                        .build();

                                badge.offer(Keys.DISPLAY_NAME, Text.of(Color.RED + TextSerializers.FORMATTING_CODE.deserialize(new StringBuilder(String.valueOf(this.plugin.getConfig().getString(new StringBuilder("config.gym")
                                        .append(i)
                                        .append("colour")
                                        .toString()))).toString()) + this.plugin.getConfig().getString(new StringBuilder("config.gym").append(i).toString()), " Badge!"));

                                if (this.plugin.settings.getLogs().get("Challengers." + playerBadges.getUniqueId() + ".gym" + i) != null) {
                                    badge.offer(Keys.ITEM_LORE, Arrays.asList(new Text[]{Text.of(Color.HSBtoRGB(218,165,32) +  "Date/Time Won: "+ Color.GREEN + this.plugin.settings.getLogs().getString(new StringBuilder("Challengers.").append(playerBadges.getUniqueId())
                                            .append(".").append("gym").append(i).append(".Date/Time Won").toString())), Text.of(Color.HSBtoRGB(218,165,32) +  "Gym Leader: "+ Color.GREEN + this.plugin.settings.getLogs().getString(new StringBuilder("Challengers.")
                                            .append(playerBadges.getUniqueId()).append(".").append("gym").append(i).append(".Gym Leader").toString()))}));

                                    List<Inventory> slots = Lists.newArrayList(myInventory_.slots());
                                    slots.get(u).set(badge);
                                } else {
                                    badge.offer(Keys.ITEM_LORE, Arrays.asList(new Text[]{Text.of(Color.RED + "Gym badge info un-known! (Badge won before feature implemented!)")}));

                                    List<Inventory> slots = Lists.newArrayList(myInventory_.slots());

                                    System.out.println("Slots: " + slots);
                                    System.out.println("u: " + u);
                                    System.out.println("Badge: " + badge);

                                    slots.get(u).set(badge);
                                }
                            }
                        }
                    }

                    if (!this.plugin.hasOpen.contains(p.getName())) {
                   //     this.plugin.hasOpen.add(p.getName());
                        //f
                    }

                    p.openInventory(myInventory_);
                    p.sendMessage(Text.of(Color.GREEN + "Opening " + playerBadges.getName() + "'s badge showcase!"));
                }
            }

            if (args[0].equalsIgnoreCase("sendrules")) {
                if (this.plugin.getConfig().getString("config." + args[1]) != null) {
                    p.sendMessage(Text.of(Color.RED + "You need to specify a player that you want to send the rules to!"));
                    p.sendMessage(Text.of(Color.DARK_GRAY +  "Proper Usage: "+ Color.RED + "/gym sendrules <gym#> (username)"));
                } else {
                    p.sendMessage(Text.of(Color.RED + "There are only 32 gym's, please use a valid gym!"));
                }
            }

            if (args[0].equalsIgnoreCase("rules")) {

                boolean gymName = false;
                int gym = 0;

                outerloop:

                for (int g = 1; g <= 32; g++) {
                    if (this.plugin.getConfig().getString("config.gym" + g).equalsIgnoreCase(args[1])) {
                        gymName = true;

                        gym = g;

                        break outerloop;
                    }

                }

                if (args[1].matches("(gym|GYM)[1-9]?[0-9]") || gymName == true) {

                    // int i = gym - 1;

                    if (gymName == true) {
                        // do nothing all good.
                    } else {
                        String gymArg = args[1].replace("gym", "");

                        try {
                            gym = Integer.parseInt(gymArg);
                        } catch (NumberFormatException nfe) {
                            p.sendMessage(Text.of(Color.RED + args[1] + " is not a gym!"));
                            return;
                        }
                    }

                    if (this.plugin.getConfig().getString("config.gym" + gym) != null) {
                        if (this.plugin.getConfig().getString("config.gym" + gym + "enabled").equalsIgnoreCase("true")) {
                            p.sendMessage(Text.of(Color.HSBtoRGB(218,165,32) +  "----- " + getConfig().getString(new StringBuilder("config.gym").append(gym).toString()) + " Gym Rules -----"));
                            p.sendMessage(Text.of(""));
                            p.sendMessage(Text.of(Color.GREEN + "1) " + getConfig().getString(new StringBuilder("config.gym").append(gym).append("rule1").toString())));
                            p.sendMessage(Text.of(Color.GREEN + "2) " + getConfig().getString(new StringBuilder("config.gym").append(gym).append("rule2").toString())));
                            p.sendMessage(Text.of(Color.GREEN + "3) " + getConfig().getString(new StringBuilder("config.gym").append(gym).append("rule3").toString())));
                            p.sendMessage(Text.of(Color.GREEN + "4) " + getConfig().getString(new StringBuilder("config.gym").append(gym).append("rule4").toString())));
                            p.sendMessage(Text.of(Color.GREEN + "5) " + getConfig().getString(new StringBuilder("config.gym").append(gym).append("rule5").toString())));
                        }
                    } else {
                        p.sendMessage(Text.of(Color.RED + "The gym gym" + gym + " does not exist!"));
                        p.sendMessage(Text.of(Color.RED + "Try /gym rules gym1"));
                    }

                } else {
                    p.sendMessage(Text.of(Color.RED + "Gym " + args[1] + " is not a valid gym."));
                }

                gymName = false;
            }
            if (args[0].equalsIgnoreCase("open")) {

                boolean gymName = false;
                int gym = 0;

                outerloop:

                for (int g = 1; g <= 32; g++) {
                    if (this.plugin.getConfig().getString("config.gym" + g).equalsIgnoreCase(args[1])) {
                        gymName = true;

                        gym = g;

                        break outerloop;
                    }

                }

                if (args[1].matches("(gym|GYM)[1-9]?[0-9]") || gymName == true) {

                    // int i = gym - 1;

                    if (gymName == true) {
                        // do nothing all good.
                    } else {
                        String gymArg = args[1].replace("gym", "");

                        try {
                            gym = Integer.parseInt(gymArg);
                        } catch (NumberFormatException nfe) {
                            p.sendMessage(Text.of(Color.RED + args[1] + " is not a gym!"));
                            return;
                        }
                    }

                    if ((p.hasPermissionLevel(1)) || (p.hasPermissionLevel(2))) {

                        if (this.plugin.getConfig().getString("config.gym" + gym) != null) {
                            if ((p.hasPermissionLevel(gym)) || (p.hasPermissionLevel(2))) {
                                if (this.plugin.getConfig().getString("config.gym" + gym + "stat").equals("Open")) {
                                    p.sendMessage(Text.of(Color.RED + "The " + getConfig().getString(new StringBuilder("config.gym").append(gym).toString()) + " Gym is already open! "));
                                } else if (this.plugin.getConfig().getString("config.gym" + gym + "stat").equals("Closed")) {
                                    if (this.plugin.getConfig().getString("config.gym" + gym + "enabled").equalsIgnoreCase("True")) {
                                     //   MessageChannel.TO_ALL.send(Text.of(Color.DARK_GRAY, "[" + Color.CYAN, getConfig().getString("config.title") + Color.DARK_GRAY, "] " + TextSerializers.FORMATTING_CODE.deserialize(getConfig().getString(new StringBuilder("config.gym")
                                      //          .append(gym).append("colour").toString()) + "TEXT").getColor(), "The " + getConfig().getString(new StringBuilder("config.gym").append(gym).toString()) + " Gym is now "+ Color.GREEN + "Open"));

                                        getConfig().set("config.gym" + gym + "stat", "Open");

                                        URL myURL = new URL("https://forums.spongepowered.org/t/pixelmongym-pixelmonmod-gym-management/17875");
                                      //  Text link = Text.builder("Click here to find the plugin page!").onClick(TextActions.openUrl(myURL)).build();

                                 //       p.sendMessage(Text.of(Color.HSBtoRGB(218,165,32) +  link));

                                        // p.sendMessage(Text.of(Color.HSBtoRGB(218,165,32) +  "The Plugin can be found @ http://dev.bukkit.org/bukkit-plugins/pixelmongym/ "));

                                        if (this.plugin.getConfig().getString("config.scoreboard").equals("True")) {
                                            for (PlayerEntity player1 : players.getPlayerList()) {

                                                // if (this.plugin.hashmap.containsKey(player1)) {
                                                //     p.setScoreboard(ScoreboardManager.clear);
                                                //     this.plugin.hashmap.remove(player1);
                                                // } else {
                                                //     ScoreboardManager.updateScoreboard();
                                                // }
                                            }

                                        }
                                    } else if (this.plugin.getConfig().getString("config.gym" + gym + "enabled").equalsIgnoreCase("False")) {
                                        p.sendMessage(Text.of(Color.RED + "This gym, gym" + gym + " is disabled in the config. Please open an enabled gym."));
                                    }
                                }
                            } else {
                                p.sendMessage(Text.of(Color.RED + "You do not have permission to open gym" + gym));
                            }
                        } else {
                            p.sendMessage(Text.of(Color.RED + "The gym gym" + gym + " does not exist!"));
                            p.sendMessage(Text.of(Color.RED + "Try /gym open gym1"));
                        }
                    } else {
                        p.sendMessage(Text.of(Color.RED + "You do not have permission to open a gym!"));
                    }

                } else {
                    if ((args[1].equalsIgnoreCase("e41")) || (args[1].equalsIgnoreCase("e42")) || (args[1].equalsIgnoreCase("e43")) || (args[1].equalsIgnoreCase("e44"))) {
                        p.sendMessage(Text.of(Color.RED + "To open the Elite 4, type /e4 open e4#. Not /gym open e4#"));
                    } else {
                        p.sendMessage(Text.of(Color.RED + "Gym " + args[1] + " is not a valid gym."));
                    }
                }
                gymName = false;
            } else if (args[0].equalsIgnoreCase("close")) {

                boolean gymName = false;
                int gym = 0;

                outerloop:

                for (int g = 1; g <= 32; g++) {
                    if (this.plugin.getConfig().getString("config.gym" + g).equalsIgnoreCase(args[1])) {
                        gymName = true;

                        gym = g;

                        break outerloop;
                    }

                }

                if (args[1].matches("(gym|GYM)[1-9]?[0-9]") || gymName == true) {

                    // int i = gym - 1;

                    if (gymName == true) {
                        // do nothing all good.
                    } else {
                        String gymArg = args[1].replace("gym", "");

                        try {
                            gym = Integer.parseInt(gymArg);
                        } catch (NumberFormatException nfe) {
                            p.sendMessage(Text.of(Color.RED + args[1] + " is not a gym!"));
                            return;
                        }
                    }

                    if ((p.hasPermissionLevel(1)) || (p.hasPermissionLevel(2))) {
                        if (this.plugin.getConfig().getString("config.gym" + gym) != null) {
                            if ((p.hasPermissionLevel(gym)) || (p.hasPermissionLevel(2))) {

                                if (this.plugin.getConfig().getString("config.gym" + gym + "enabled").equalsIgnoreCase("True")) {
                                    if (this.plugin.getConfig().getString("config.gym" + gym + "stat").equals("Closed")) {
                                        p.sendMessage(Text.of(Color.RED + "The " + getConfig().getString(new StringBuilder("config.gym").append(gym).toString()) + " gym is already Closed!"));
                                    } else if (this.plugin.getConfig().getString("config.gym" + gym + "stat").equals("Open")) {
                                        // MessageChannel.TO_ALL.send(Text.of(Color.DARK_GRAY, "[" + Color.CYAN, getConfig().getString("config.title") + Color.DARK_GRAY, "] " + TextSerializers.FORMATTING_CODE.deserialize(getConfig().getString(new StringBuilder("config.").append(args[1]).append("colour").toString())+"TEXT").getColor(), "The " + getConfig().getString(new StringBuilder("config.").append(args[1]).toString()) + " Gym is now "+ Color.RED + "Closed"));
                                 //       MessageChannel.TO_ALL.send(Text.of(Color.DARK_GRAY, "[" + Color.CYAN, getConfig().getString("config.title") + Color.DARK_GRAY, "] " + TextSerializers.FORMATTING_CODE.deserialize(getConfig().getString(new StringBuilder("config.gym")
                                   //             .append(gym).append("colour").toString()) + "TEXT").getColor(), "The " + getConfig().getString(new StringBuilder("config.gym").append(gym).toString()) + " Gym is now "+ Color.RED + "Closed"));

                                        getConfig().set("config.gym" + gym + "stat", "Closed");

                                        URL myURL = new URL("https://forums.spongepowered.org/t/pixelmongym-pixelmonmod-gym-management/17875");
                                      //  Text link = Text.builder("Click here to find the plugin page!").onClick(TextActions.openUrl(myURL)).build();

                                   //     p.sendMessage(Text.of(Color.HSBtoRGB(218,165,32) +  link));
                                        // p.sendMessage(Text.of(Color.HSBtoRGB(218,165,32) +  "The Plugin can be found @ https://forums.spongepowered.org/t/pixelmongym-v1-3-pixelmonmod-gym-management/17875"));
                                        for (PlayerEntity player1 : players.getPlayerList()) {
                                            // if (this.plugin.hashmap.containsKey(player1)) {
                                            //     p.setScoreboard(ScoreboardManager.clear);
                                            //     this.plugin.hashmap.remove(player1);
                                            // } else {
                                            //     ScoreboardManager.updateScoreboard();
                                            // }
                                        }
                                    }
                                } else {
                                 //   p.sendMessage(Text.of(Color.RED + "The " + args[1] + " gym is not enabled in the config, please close an enabled gym."));
                                }

                            } else {
                            //    p.sendMessage(Text.of(Color.RED + "You do not have permission to close " + args[1]));
                            }
                        } else {
                            p.sendMessage(Text.of(Color.RED + "The gym " + args[1] + " does not exist!"));
                            p.sendMessage(Text.of(Color.RED + "Try /gym close gym1"));
                        }

                    } else {
                        p.sendMessage(Text.of(Color.RED + "You do not have permission to close a gym!"));
                    }

                } else {
                    if ((args[1].equalsIgnoreCase("e41")) || (args[1].equalsIgnoreCase("e42")) || (args[1].equalsIgnoreCase("e43")) || (args[1].equalsIgnoreCase("e44"))) {
                        p.sendMessage(Text.of(Color.RED + "To close the Elite 4, type /e4 close e4#. Not /gym close e4#"));
                    } else {
                        p.sendMessage(Text.of(Color.RED + "Gym " + args[1] + " is not a valid gym."));
                    }
                }
                gymName = false;
            }

            if (args[0].equalsIgnoreCase("setwarp")) {

                boolean gymName = false;
                int gym = 0;

                outerloop:

                for (int g = 1; g <= 32; g++) {
                    if (this.plugin.getConfig().getString("config.gym" + g).equalsIgnoreCase(args[1])) {
                        gymName = true;

                        gym = g;

                        break outerloop;
                    }

                }

                if (args[1].matches("(gym|GYM)[1-9]?[0-9]") || gymName == true) {

                    // int i = gym - 1;

                    if (gymName == true) {
                        // do nothing all good.
                    } else {
                        String gymArg = args[1].replace("gym", "");

                        try {
                            gym = Integer.parseInt(gymArg);
                        } catch (NumberFormatException nfe) {
                            p.sendMessage(Text.of(Color.RED + args[1] + " is not a gym!"));
                            return;
                        }
                    }

                    if (p.hasPermissionLevel(2)) {
                        if (this.plugin.settings.getData().get("warps.gym" + gym) != null) {
                            p.sendMessage(Text.of(Color.RED + "Gym" + gym + " warp already exists. If you want to overwrite it, do /gym delwarp gym" + gym + ". And then re-set the new warp."));
                        } else {
                            this.plugin.settings.getData().set("warps.gym" + gym + ".world", p.getLocation().getExtent().getName());
                            this.plugin.settings.getData().set("warps.gym" + gym + ".x", Double.valueOf(p.getLocation().getX()));
                            this.plugin.settings.getData().set("warps.gym" + gym + ".y", Double.valueOf(p.getLocation().getY()));
                            this.plugin.settings.getData().set("warps.gym" + gym + ".z", Double.valueOf(p.getLocation().getZ()));
                            this.plugin.settings.saveData();
                            p.sendMessage(Text.of(Color.GREEN + "Set warp gym" + gym + "!"));
                        }
                    } else {
                        p.sendMessage(Text.of(Color.RED + "You do not have permission to set a warp!"));
                    }
                } else if (args[1].equalsIgnoreCase("spawn")) {
                    if (p.hasPermissionLevel(2)) {
                        if (this.plugin.settings.getData().get("warps.spawn") != null) {
                            p.sendMessage(Text.of(Color.RED + "Spawn warp already exists. If you want to overwrite it, do /gym delwarp spawn. And then re-set the new warp."));
                        } else {
                            this.plugin.settings.getData().set("warps.spawn.world", p.getLocation().getExtent().getName());
                            this.plugin.settings.getData().set("warps.spawn.x", Double.valueOf(p.getLocation().getX()));
                            this.plugin.settings.getData().set("warps.spawn.y", Double.valueOf(p.getLocation().getY()));
                            this.plugin.settings.getData().set("warps.spawn.z", Double.valueOf(p.getLocation().getZ()));
                            this.plugin.settings.saveData();
                            p.sendMessage(Text.of(Color.GREEN + "Set warp spawn!"));
                        }
                    }
                } else {
                    p.sendMessage(Text.of(Color.RED + "Gym " + args[1] + " is not a valid gym."));
                }
                gymName = false;
            }

            if (args[0].equalsIgnoreCase("delwarp")) {

                boolean gymName = false;
                int gym = 0;

                outerloop:

                for (int g = 1; g <= 32; g++) {
                    if (this.plugin.getConfig().getString("config.gym" + g).equalsIgnoreCase(args[1])) {
                        gymName = true;

                        gym = g;

                        break outerloop;
                    }

                }

                if (args[1].matches("(gym|GYM)[1-9]?[0-9]") || gymName == true) {

                    // int i = gym - 1;

                    if (gymName == true) {
                        // do nothing all good.
                    } else {
                        String gymArg = args[1].replace("gym", "");

                        try {
                            gym = Integer.parseInt(gymArg);
                        } catch (NumberFormatException nfe) {
                            p.sendMessage(Text.of(Color.RED + args[1] + " is not a gym!"));
                            return;
                        }
                    }

                    if (p.hasPermissionLevel(2)) {
                        if (this.plugin.settings.getData().get("warps.gym" + gym) == null) {
                            p.sendMessage(Text.of(Color.RED + "Warp gym" + gym + " does not exist!"));
                            return;
                        }
                        this.plugin.settings.getData().set("warps.gym" + gym, null);
                        this.plugin.settings.saveData();
                        p.sendMessage(Text.of(Color.GREEN + "Removed warp gym" + gym + "!"));
                    } else {
                        p.sendMessage(Text.of(Color.RED + "You do not have permission to delete a warp!"));
                    }
                } else if (args[1].equalsIgnoreCase("spawn")) {
                    if (p.hasPermissionLevel(2)) {
                        if (this.plugin.settings.getData().get("warps.spawn") == null) {
                            p.sendMessage(Text.of(Color.RED + "Warp spawn does not exist!"));
                            return;
                        }
                        this.plugin.settings.getData().set("warps.spawn", null);
                        this.plugin.settings.saveData();
                        p.sendMessage(Text.of(Color.GREEN + "Removed warp spawn!"));
                    }
                } else {
                    p.sendMessage(Text.of(Color.RED + "Gym " + args[1] + " is not a valid gym."));
                }
                gymName = false;
            }

            if (args[0].equalsIgnoreCase("warp")) {

                boolean gymName = false;
                int gym = 0;

                outerloop:

                for (int g = 1; g <= 32; g++) {
                    if (this.plugin.getConfig().getString("config.gym" + g).equalsIgnoreCase(args[1])) {
                        gymName = true;

                        gym = g;

                        break outerloop;
                    }

                }

                if (args[1].matches("(gym|GYM)[1-9]?[0-9]") || gymName == true) {

                    // int i = gym - 1;

                    if (gymName == true) {
                        // do nothing all good.
                    } else {
                        String gymArg = args[1].replace("gym", "");

                        try {
                            gym = Integer.parseInt(gymArg);
                        } catch (NumberFormatException nfe) {
                            p.sendMessage(Text.of(Color.RED + args[1] + " is not a gym!"));
                            return;
                        }
                    }

                    if ((p.hasPermissionLevel(2)) || (p.hasPermissionLevel(gym))) {
                        if (this.plugin.settings.getData().get("warps.gym" + gym) == null) {
                            p.sendMessage(Text.of(Color.RED + "Warp gym" + gym + " does not exist!"));
                            return;
                        }

                        World w = Sponge.getServer().getWorld(this.plugin.settings.getData().getString("warps.gym" + gym + ".world")).orElse(null);
                        double x = this.plugin.settings.getData().getDouble("warps.gym" + gym + ".x");
                        double y = this.plugin.settings.getData().getDouble("warps.gym" + gym + ".y");
                        double z = this.plugin.settings.getData().getDouble("warps.gym" + gym + ".z");
                        p.setLocation(new Location(w, x, y, z));

                        p.sendMessage(Text.of(Color.GREEN + "Teleported to gym" + gym + "!"));
                    } else {
                        p.sendMessage(Text.of(Color.RED + "You do not have permission to warp to a gym!"));
                    }
                } else {
                    p.sendMessage(Text.of(Color.RED + "Gym " + args[1] + " is not a valid gym."));
                }
                gymName = false;
            }

            if (args[0].equalsIgnoreCase("addtm")) {

                boolean gymName = false;
                int gym = 0;

                outerloop:

                for (int g = 1; g <= 32; g++) {
                    if (this.plugin.getConfig().getString("config.gym" + g).equalsIgnoreCase(args[1])) {
                        gymName = true;

                        gym = g;

                        break outerloop;
                    }

                }

                if (args[1].matches("(gym|GYM)[1-9]?[0-9]") || gymName == true) {

                    // int i = gym - 1;

                    if (gymName == true) {
                        // do nothing all good.
                    } else {
                        String gymArg = args[1].replace("gym", "");

                        try {
                            gym = Integer.parseInt(gymArg);
                        } catch (NumberFormatException nfe) {
                            p.sendMessage(Text.of(Color.RED + args[1] + " is not a gym!"));
                            return;
                        }
                    }

                    String tM = p.getItemInHand(HandTypes.MAIN_HAND).get().getType().getId();

                    if (p.hasPermissionLevel(2)) {

                        for (int i = 1; i <= 100; i++) {
                            int u = i - 1;

                            if (i == 1) {
                                if (this.plugin.settings.getExtras().get("tms.gym" + gym + "TM." + i) == null) {
                                    this.plugin.settings.getExtras().set("tms.gym" + gym + "TM." + i, tM);
                                    this.plugin.settings.saveExtras();
                                    break;
                                }
                            } else if (this.plugin.settings.getExtras().get("tms.gym" + gym + "TM." + i) == null && this.plugin.settings.getExtras().get("tms.gym" + gym + "TM." + u) != null) {
                                this.plugin.settings.getExtras().set("tms.gym" + gym + "TM." + i, tM);
                                this.plugin.settings.saveExtras();
                                break;
                            }
                        }

                        p.sendMessage(Text.of(Color.GREEN + "Added item in hand as a TM for gym"));
                    }
                } else {
                    p.sendMessage(Text.of(Color.RED + "Gym " + args[1] + " is not a valid gym."));
                }
                gymName = false;
            }
             else if (args.length == 3) {
                if (args[0].equalsIgnoreCase("sendrules")) {

                boolean gymName = false;
                int gym = 0;

                outerloop:

                for (int g = 1; g <= 32; g++) {
                    if (this.plugin.getConfig().getString("config.gym" + g).equalsIgnoreCase(args[1])) {
                        gymName = true;

                        gym = g;

                        break outerloop;
                    }

                }

                if (args[1].matches("(gym|GYM)[1-9]?[0-9]") || gymName == true) {

                    // int i = gym - 1;

                    if (gymName == true) {
                        // do nothing all good.
                    } else {
                        String gymArg = args[1].replace("gym", "");

                        try {
                            gym = Integer.parseInt(gymArg);
                        } catch (NumberFormatException nfe) {
                            p.sendMessage(Text.of(Color.RED + args[1] + " is not a gym!"));
                            return;
                        }
                    }

                    if (Sponge.getServer().getPlayer(args[2]).orElse(null) != null) {
                        Player playerTarget = Sponge.getServer().getPlayer(args[2]).get();

                        if (this.plugin.getConfig().getString("config.gym" + gym) != null) {
                            if (p.hasPermissionLevel(gym)) {
                                p.sendMessage(Text.of(Color.HSBtoRGB(218,165,32) +  "Sent " + getConfig().getString(new StringBuilder("config.gym").append(gym).toString()) + " Gym Rules to " + playerTarget.getName().toString()));
                                playerTarget.sendMessage(Text.of(Color.HSBtoRGB(218,165,32) +  playerTarget.getName().toString() + ", Make sure you read the " + getConfig().getString(new StringBuilder("config.gym").append(gym).toString()) + " Gym rules propperly before facing the Gym!"));
                                playerTarget.sendMessage(Text.of(Color.GREEN + "1) " + getConfig().getString(new StringBuilder("config.gym").append(gym).append("rule1").toString())));
                                playerTarget.sendMessage(Text.of(Color.GREEN + "2) " + getConfig().getString(new StringBuilder("config.gym").append(gym).append("rule2").toString())));
                                playerTarget.sendMessage(Text.of(Color.GREEN + "3) " + getConfig().getString(new StringBuilder("config.gym").append(gym).append("rule3").toString())));
                                playerTarget.sendMessage(Text.of(Color.GREEN + "4) " + getConfig().getString(new StringBuilder("config.gym").append(gym).append("rule4").toString())));
                                playerTarget.sendMessage(Text.of(Color.GREEN + "5) " + getConfig().getString(new StringBuilder("config.gym").append(gym).append("rule5").toString())));
                            } else {
                                p.sendMessage(Text.of(Color.RED + "You are not gym leader of this gym!"));
                            }
                        } else {
                            p.sendMessage(Text.of(Color.RED + "The gym gym" + gym + " does not exist!"));
                            p.sendMessage(Text.of(Color.RED + "Try /gym sendrules gym1 (player)"));
                        }

                    } else {
                        p.sendMessage(Text.of(Color.RED + "You need to specify a player to send the rules to. Example: /gym sendrules gym1 (player)"));
                    }

                } else {
                    p.sendMessage(Text.of(Color.RED + "Gym " + args[1] + " is not a valid gym."));
                }

                gymName = false;
            }

            if (args[0].equalsIgnoreCase("giveTM")) {

                boolean gymName = false;
                int gym = 0;

                outerloop:

                for (int g = 1; g <= 32; g++) {
                    if (this.plugin.getConfig().getString("config.gym" + g).equalsIgnoreCase(args[1])) {
                        gymName = true;

                        gym = g;

                        break outerloop;
                    }

                }

                if (args[1].matches("(gym|GYM)[1-9]?[0-9]") || gymName == true) {

                    // int i = gym - 1;

                    if (gymName == true) {
                        // do nothing all good.
                    } else {
                        String gymArg = args[1].replace("gym", "");

                        try {
                            gym = Integer.parseInt(gymArg);
                        } catch (NumberFormatException nfe) {
                            p.sendMessage(Text.of(Color.RED + args[1] + " is not a gym!"));
                            return;
                        }
                    }

                    if (Sponge.getServer().getPlayer(args[2]).orElse(null) != null) {
                        Player playerToGive = Sponge.getServer().getPlayer(args[2]).get();

                        if ((p.hasPermissionLevel(gym)) || (p.hasPermissionLevel(2))) {
                            Random random = new Random();

                            int randomNum = random.nextInt(4) + 1;
                            if (this.plugin.settings.getExtras().get("tms.gym" + gym + "TM." + randomNum) != null) {
                                ItemStack tM = ItemStack.builder()
                                        .itemType(Sponge.getRegistry().getType(ItemType.class, this.plugin.settings.getExtras().getString("tms.gym" + gym + "TM." + randomNum)).get())
                                        .build();

                                if (playerToGive.getInventory().offer(tM).getType() != InventoryTransactionResult.Type.SUCCESS) {
                                    playerToGive.sendMessage(Text.of(Color.RED + "Your inventory was full, you did not get given the TM! Tell the gym leader you did not get the TM and empty a space in your inventory now!"));
                                    p.sendMessage(Text.of(Color.RED + "Player did not recieve the TM, there inventory was full. Type /gym giveTM gym" + gym + " (player) when they have an open spot in there inventory"));
                                }
                            } else {
                                int randomNum2 = random.nextInt(29) + 1;
                                if (this.plugin.settings.getExtras().get("tms.gym" + gym + "TM." + randomNum2) != null) {
                                    ItemStack tM = ItemStack.builder()
                                            .itemType(Sponge.getRegistry().getType(ItemType.class, this.plugin.settings.getExtras().getString("tms.gym" + gym + "TM." + randomNum2)).get())
                                            .build();

                                    if (playerToGive.getInventory().offer(tM).getType() != InventoryTransactionResult.Type.SUCCESS) {
                                        playerToGive.sendMessage(Text.of(Color.RED + "Your inventory was full, you did not get given the TM! Tell the gym leader you did not get the TM and empty a space in your inventory now!"));
                                        p.sendMessage(Text.of(Color.RED + "Player did not recieve the TM, there inventory was full. Type /gym giveTM gym" + gym + " (player) when they have an open spot in there inventory"));
                                    }
                                } else {
                                    int randomNum3 = random.nextInt(9) + 1;
                                    if (this.plugin.settings.getExtras().get("tms.gym" + gym + "TM." + randomNum3) != null) {

                                        ItemStack tM = ItemStack.builder()
                                                .itemType(Sponge.getRegistry().getType(ItemType.class, this.plugin.settings.getExtras().getString("tms.gym" + gym + "TM." + randomNum3)).get())
                                                .build();

                                        if (playerToGive.getInventory().offer(tM).getType() != InventoryTransactionResult.Type.SUCCESS) {
                                            playerToGive.sendMessage(Text.of(Color.RED + "Your inventory was full, you did not get given the TM! Tell the gym leader you did not get the TM and empty a space in your inventory now!"));
                                            p.sendMessage(Text.of(Color.RED + "Player did not recieve the TM, there inventory was full. Type /gym giveTM gym" + gym + " (player) when they have an open spot in there inventory"));
                                        }
                                    } else {
                                        int randomNum4 = random.nextInt(1) + 1;
                                        if (this.plugin.settings.getExtras().get("tms.gym" + gym + "TM." + randomNum4) != null) {
                                            ItemStack tM = ItemStack.builder()
                                                    .itemType(Sponge.getRegistry().getType(ItemType.class, this.plugin.settings.getExtras().getString("tms.gym" + gym + "TM." + randomNum4)).get())
                                                    .build();

                                            if (playerToGive.getInventory().offer(tM).getType() != InventoryTransactionResult.Type.SUCCESS) {
                                                playerToGive.sendMessage(Text.of(Color.RED + "Your inventory was full, you did not get given the TM! Tell the gym leader you did not get the TM and empty a space in your inventory now!"));
                                                p.sendMessage(Text.of(Color.RED + "Player did not recieve the TM, there inventory was full. Type /gym giveTM gym" + gym + " (player) when they have an open spot in there inventory"));
                                            }
                                        } else {
                                            int randomNum5 = random.nextInt(0) + 1;
                                            if (this.plugin.settings.getExtras().get("tms.gym" + gym + "TM." + randomNum5) != null) {
                                                ItemStack tM = ItemStack.builder()
                                                        .itemType(Sponge.getRegistry()
                                                                .getType(ItemType.class, this.plugin.settings.getExtras().getString("tms.gym" + gym + "TM." + randomNum5)).get())
                                                        .build();

                                                if (playerToGive.getInventory().offer(tM).getType() != InventoryTransactionResult.Type.SUCCESS) {
                                                    playerToGive.sendMessage(Text.of(Color.RED + "Your inventory was full, you did not get given the TM! Tell the gym leader you did not get the TM and empty a space in your inventory now!"));
                                                    p.sendMessage(Text.of(Color.RED + "Player did not recieve the TM, there inventory was full. Type /gym giveTM gym" + gym + " (player) when they have an open spot in there inventory"));
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else {
                    p.sendMessage(Text.of(Color.RED + "Gym " + args[1] + " is not a valid gym."));
                }
                gymName = false;
            }

            int u;

            //gym win
            if ((args[0].equalsIgnoreCase("winner")) || (args[0].equalsIgnoreCase("win")) || (args[0].equalsIgnoreCase("w"))) {

                boolean gymName = false;
                int gym = 0;

                outerloop:

                for (int g = 1; g <= 32; g++) {
                    if (this.plugin.getConfig().getString("config.gym" + g).equalsIgnoreCase(args[1])) {
                        gymName = true;

                        gym = g;

                        break outerloop;
                    }

                }

                if (args[1].matches("(gym|GYM)[1-9]?[0-9]") || gymName == true) {

                    // int i = gym - 1;

                    if (gymName == true) {
                        // do nothing all good.
                    } else {
                        String gymArg = args[1].replace("gym", "");

                        try {
                            gym = Integer.parseInt(gymArg);
                        } catch (NumberFormatException nfe) {
                            p.sendMessage(Text.of(Color.RED + args[1] + " is not a gym!"));
                            return;
                        }
                    }

                    if (Sponge.getServer().getPlayer(args[2]).orElse(null) != null) {
                        Player playerWinner = Sponge.getServer().getPlayer(args[2]).orElse(null);

                        u = gym + 1;

                        Calendar c = Calendar.getInstance();

                        if ((p.hasPermissionLevel(gym)) || (p.hasPermissionLevel(2))) {
                            Player o = playerWinner;
                            UUID po = playerWinner.getUniqueId();

                            if (((List) this.plugin.queues.get(Integer.valueOf(gym))).contains(o.getUniqueId())) {

                                if (this.plugin.settings.getData().get("warps.spawn") != null) {
                                    World w = Sponge.getServer().getWorld(this.plugin.settings.getData().getString("warps.spawn.world")).orElse(null);
                                    double x = this.plugin.settings.getData().getDouble("warps.spawn.x");
                                    double y = this.plugin.settings.getData().getDouble("warps.spawn.y");
                                    double z = this.plugin.settings.getData().getDouble("warps.spawn.z");
                                    playerWinner.setLocation(new Location(w, x, y, z));
                                    playerWinner.sendMessage(Text.of(Color.GREEN + "Teleported out of " + Color.YELLOW + "gym" + gym + "!"));
                                    playerWinner.sendMessage(Text.of(Color.GREEN + "Congrats, you won the gym" + gym + " badge! (" + TextSerializers.FORMATTING_CODE.deserialize(new StringBuilder(String.valueOf(this.plugin.getConfig().getString(new StringBuilder("config.gym")
                                            .append(gym).append("colour").toString()))).toString()) + this.plugin.getConfig().getString(new StringBuilder("config.gym").append(gym).toString())+ Color.GREEN + ") " + "Now you can join the gym", u + " queue with /gym join gym" + u + ". (" + TextSerializers.FORMATTING_CODE.deserialize(new StringBuilder(String.valueOf(this.plugin.getConfig().getString(new StringBuilder("config.gym")
                                            .append(u).append("colour").toString()))).toString()) + this.plugin.getConfig().getString(new StringBuilder("config.gym").append(u).toString())+ Color.GREEN + ")"));

                                    ((List) this.plugin.inArena.get(Integer.valueOf(gym))).remove(p.getUuid() );
                                    Random random = new Random();
                                    int randomNum = random.nextInt(99) + 1;

                                    if (this.plugin.settings.getExtras().get("tms.gym" + gym + "TM." + randomNum) != null) {
                                        ItemStack tM = ItemStack.builder()
                                                .itemType(Sponge.getRegistry().getType(ItemType.class, this.plugin.settings.getExtras().getString("tms.gym" + gym + "TM." + randomNum)).get())
                                                .build();

                                        if (playerWinner.getInventory().offer(tM).getType() != InventoryTransactionResult.Type.SUCCESS) {
                                            playerWinner.sendMessage(Text.of(Color.RED + "Your inventory was full, you did not get given the TM! Tell the gym leader you did not get the TM and empty a space in your inventory now!"));
                                            p.sendMessage(Text.of(Color.RED + "Player did not recieve the TM, there inventory was full. Type /gym giveTM gym" + gym + " (player) when they have an open spot in there inventory"));
                                        }
                                    } else {
                                        int randomNum2 = random.nextInt(29) + 1;
                                        if (this.plugin.settings.getExtras().get("tms.gym" + gym + "TM." + randomNum2) != null) {
                                            ItemStack tM = ItemStack.builder()
                                                    .itemType(Sponge.getRegistry().getType(ItemType.class, this.plugin.settings.getExtras().getString("tms.gym" + gym + "TM." + randomNum2)).get())
                                                    .build();

                                            if (playerWinner.getInventory().offer(tM).getType() != InventoryTransactionResult.Type.SUCCESS) {
                                                playerWinner.sendMessage(Text.of(Color.RED + "Your inventory was full, you did not get given the TM! Tell the gym leader you did not get the TM and empty a space in your inventory now!"));
                                                p.sendMessage(Text.of(Color.RED + "Player did not recieve the TM, there inventory was full. Type /gym giveTM gym" + gym + " (player) when they have an open spot in there inventory"));
                                            }
                                        } else {
                                            int randomNum3 = random.nextInt(9) + 1;
                                            if (this.plugin.settings.getExtras().get("tms.gym" + gym + "TM." + randomNum3) != null) {
                                                ItemStack tM = ItemStack.builder()
                                                        .itemType(Sponge.getRegistry().getType(ItemType.class, this.plugin.settings.getExtras().getString("tms.gym" + gym + "TM." + randomNum3)).get())
                                                        .build();

                                                if (playerWinner.getInventory().offer(tM).getType() != InventoryTransactionResult.Type.SUCCESS) {
                                                    playerWinner.sendMessage(Text.of(Color.RED + "Your inventory was full, you did not get given the TM! Tell the gym leader you did not get the TM and empty a space in your inventory now!"));
                                                    p.sendMessage(Text.of(Color.RED + "Player did not recieve the TM, there inventory was full. Type /gym giveTM gym" + gym + " (player) when they have an open spot in there inventory"));
                                                }
                                            } else {
                                                int randomNum4 = random.nextInt(1) + 1;
                                                if (this.plugin.settings.getExtras().get("tms.gym" + gym + "TM." + randomNum4) != null) {
                                                    ItemStack tM = ItemStack.builder()
                                                            .itemType(Sponge.getRegistry()
                                                                    .getType(ItemType.class, this.plugin.settings.getExtras().getString("tms.gym" + gym + "TM." + randomNum4)).get())
                                                            .build();

                                                    if (playerWinner.getInventory().offer(tM).getType() != InventoryTransactionResult.Type.SUCCESS) {
                                                        playerWinner.sendMessage(Text.of(Color.RED + "Your inventory was full, you did not get given the TM! Tell the gym leader you did not get the TM and empty a space in your inventory now!"));
                                                        p.sendMessage(Text.of(Color.RED + "Player did not recieve the TM, there inventory was full. Type /gym giveTM gym" + gym + " (player) when they have an open spot in there inventory"));
                                                    }
                                                } else {
                                                    int randomNum5 = 1;

                                                    if (this.plugin.settings.getExtras().get("tms.gym" + gym + "TM." + randomNum5) != null) {
                                                        ItemStack tM = ItemStack.builder()
                                                                .itemType(Sponge.getRegistry().getType(ItemType.class, this.plugin.settings.getExtras().getString("tms.gym" + gym + "TM." + randomNum5)).get())
                                                                .build();

                                                        if (playerWinner.getInventory().offer(tM).getType() != InventoryTransactionResult.Type.SUCCESS) {
                                                            playerWinner.sendMessage(Text.of(Color.RED + "Your inventory was full, you did not get given the TM! Tell the gym leader you did not get the TM and empty a space in your inventory now!"));
                                                            p.sendMessage(Text.of(Color.RED + "Player did not recieve the TM, there inventory was full. Type /gym giveTM gym" + gym + " (player) when they have an open spot in there inventory"));
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }

                                    p.sendMessage(Text.of(Color.GREEN + "Successfully telported " + playerWinner.getName() + " out of your gym!"));
                                    p.sendMessage(Text.of(Color.GREEN + "You are now ready for your next battle, type: /gym next gym" + gym));
                                } else {
                                    p.sendMessage(Text.of(Color.RED + "Warp point 'spawn' does not exist. Type: /gym setwarp spawn. (this is the teleport location to move challengers out of the gym."));
                                }

                                ((List) this.plugin.queues.get(Integer.valueOf(gym))).remove(po);

                                this.plugin.settings.getLogs().set("Leaders." + p.getName() + ".gym" + gym + ".[" + c.getTime() + "]", playerWinner.getName() + " - Won");
                                this.plugin.settings.getLogs().set("Challengers." + playerWinner.getUniqueId() + ".gym" + gym + ".Date/Time Won", "[" + c.getTime() + "]");
                                this.plugin.settings.getLogs().set("Challengers." + playerWinner.getUniqueId() + ".gym" + gym + ".Gym Leader", p.getName());

                                this.plugin.settings.saveLogs();

                                if (this.plugin.getConfig().getString("config.gymsound").equalsIgnoreCase("true")) {
                                    for (Player playersOnline : Sponge.getServer().getOnlinePlayers()) {
                                        playersOnline.playSound(SoundTypes.ENTITY_FIREWORK_LARGE_BLAST, SoundCategories.AMBIENT, playersOnline.getLocation().getPosition(), 30.0, 1.0);
                                        playersOnline.playSound(SoundTypes.ENTITY_FIREWORK_LARGE_BLAST, SoundCategories.AMBIENT, playersOnline.getLocation().getPosition(), 30.0, 1.0);
                                    }
                                }

                                for (int i = 1; i <= 32; i++) {
                                    if (args[1].equalsIgnoreCase("gym" + i)) {
                                        this.plugin.settings.getBadge().set("Players." + playerWinner.getUniqueId() + ".Badges.gym" + gym, "Won");
                                        this.plugin.settings.saveBadges();
                                    }
                                }
                                MessageChannel.TO_ALL.send(Text.of(Color.DARK_GRAY, "[" + Color.CYAN, getConfig().getString("config.title") + Color.DARK_GRAY, "] " + Color.YELLOW, playerWinner.getName() + TextSerializers.FORMATTING_CODE.deserialize(getConfig().getString(new StringBuilder("config.gym")
                                        .append(gym)
                                        .append("colour").toString())), " has won the " + getConfig().getString(new StringBuilder("config.gym").append(gym).toString()) + " Gym Badge!"));

                                if (this.plugin.cooldownTime.get(gym).containsKey(po)) {
                                    this.plugin.cooldownTime.get(gym).remove(po);
                                }

                                if (this.plugin.cooldownTask.get(gym).containsKey(po)) {
                                    this.plugin.cooldownTask.get(gym).remove(po);
                                }

                                if (this.plugin.cooldownGym.get(gym).contains(po)) {
                                    this.plugin.cooldownGym.get(gym).remove(po);
                                }

                                if (this.plugin.cooldownGym.containsKey((gym + 1))) {
                                    if (this.plugin.cooldownTime.get((gym + 1)).containsKey(po)) {
                                        this.plugin.cooldownTime.get((gym + 1)).remove(po);
                                    }

                                    if (this.plugin.cooldownTask.get((gym + 1)).containsKey(po)) {
                                        this.plugin.cooldownTask.get((gym + 1)).remove(po);
                                    }

                                    if (this.plugin.cooldownGym.get((gym + 1)).contains(po)) {
                                        this.plugin.cooldownGym.get((gym + 1)).remove(po);
                                    }
                                }


                            } else {
                                p.sendMessage(Text.of(Color.RED + "Player must be in the queue to win or lose!"));
                            }
                        } else {
                            p.sendMessage(Text.of(Color.RED + "You do not have permission to set winner's of gym" + gym));
                        }
                    }
                } else {
                    p.sendMessage(Text.of(Color.RED + "Gym " + args[1] + " is not a valid gym."));
                }
                gymName = false;
            } else if (args[0].equalsIgnoreCase("givebadge")) {

                boolean gymName = false;
                int gym = 0;

                outerloop:

                for (int g = 1; g <= 32; g++) {
                    if (this.plugin.getConfig().getString("config.gym" + g).equalsIgnoreCase(args[1])) {
                        gymName = true;

                        gym = g;

                        break outerloop;
                    }

                }

                if (args[1].matches("(gym|GYM)[1-9]?[0-9]") || gymName == true) {

                    // int i = gym - 1;

                    if (gymName == true) {
                        // do nothing all good.
                    } else {
                        String gymArg = args[1].replace("gym", "");

                        try {
                            gym = Integer.parseInt(gymArg);
                        } catch (NumberFormatException nfe) {
                            p.sendMessage(Text.of(Color.RED + args[1] + " is not a gym!"));
                            return;
                        }
                    }

                    for (Player playerGive : Sponge.getServer().getOnlinePlayers()) {
                        if (playerGive.getName().equalsIgnoreCase(args[2])) {
                            if (p.hasPermissionLevel(2)) {
                                for (int i = 1; i <= 32; i++) {
                                    this.plugin.settings.getBadge().set("Players." + playerGive.getUniqueId() + ".Badges.gym" + gym, "Won");
                                    this.plugin.settings.saveBadges();
                                }
                                p.sendMessage(Text.of(Color.GREEN + "Gave " + playerGive.getName() + " the gym" + gym + " badge!"));
                            } else {
                                p.sendMessage(Text.of("You do not have permission to give gym badges!"));
                            }
                        }
                    }
                } else {
                    p.sendMessage(Text.of(Color.RED + "Gym " + args[1] + " is not a valid gym."));
                }
                gymName = false;
            } else if (args[0].equalsIgnoreCase("delbadge")) {

                boolean gymName = false;
                int gym = 0;

                outerloop:

                for (int g = 1; g <= 32; g++) {
                    if (this.plugin.getConfig().getString("config.gym" + g).equalsIgnoreCase(args[1])) {
                        gymName = true;

                        gym = g;

                        break outerloop;
                    }

                }

                if (args[1].matches("(gym|GYM)[1-9]?[0-9]") || gymName == true) {

                    // int i = gym - 1;

                    if (gymName == true) {
                        // do nothing all good.
                    } else {
                        String gymArg = args[1].replace("gym", "");

                        try {
                            gym = Integer.parseInt(gymArg);
                        } catch (NumberFormatException nfe) {
                            p.sendMessage(Text.of(Color.RED + args[1] + " is not a gym!"));
                            return;
                        }
                    }

                    for (Player playerTake : Sponge.getServer().getOnlinePlayers()) {
                        if (playerTake.getName().equalsIgnoreCase(args[2])) {
                            if (p.hasPermissionLevel(2)) {
                                if (this.plugin.settings.getBadge().get("Players." + playerTake.getUniqueId() + ".Badges.gym" + gym) != null) {
                                    for (int i = 1; i <= 32; i++) {
                                        if (args[1].equalsIgnoreCase("gym" + i)) {
                                            this.plugin.settings.getBadge().set("Players." + playerTake.getUniqueId() + ".Badges.gym" + gym, null);
                                            this.plugin.settings.saveBadges();

                                            p.sendMessage(Text.of(Color.GREEN + "Deleted the gym" + gym + " badge from " + playerTake.getName()));
                                        }

                                    }
                                } else {
                                    p.sendMessage(Text.of(Color.RED + playerTake.getName() + " does not have the gym, gym" + gym));
                                }
                            } else {
                                p.sendMessage(Text.of("You do not have permission to give gym badges!"));
                            }

                        }

                    }
                } else {
                    p.sendMessage(Text.of(Color.RED + "Gym " + args[1] + " is not a valid gym."));
                }
                gymName = false;
            } else if ((args[0].equalsIgnoreCase("sq")) || (args[0].equalsIgnoreCase("skipq")) || (args[0].equalsIgnoreCase("skip"))) {

                boolean gymName = false;
                int gym = 0;

                outerloop:

                for (int g = 1; g <= 32; g++) {
                    if (this.plugin.getConfig().getString("config.gym" + g).equalsIgnoreCase(args[1])) {
                        gymName = true;

                        gym = g;

                        break outerloop;
                    }

                }

                if (args[1].matches("(gym|GYM)[1-9]?[0-9]") || gymName == true) {

                    // int i = gym - 1;

                    if (gymName == true) {
                        // do nothing all good.
                    } else {
                        String gymArg = args[1].replace("gym", "");

                        try {
                            gym = Integer.parseInt(gymArg);
                        } catch (NumberFormatException nfe) {
                            p.sendMessage(Text.of(Color.RED + args[1] + " is not a gym!"));
                            return;
                        }
                    }

                    if (Sponge.getServer().getPlayer(args[2]).orElse(null) != null) {
                        Player playerSkip = Sponge.getServer().getPlayer(args[2]).get();
                        if ((p.hasPermissionLevel(gym)) || (p.hasPermissionLevel(2))) {
                            UUID po = playerSkip.getUuid() ;

                            ((Map) this.plugin.cooldownTime.get(Integer.valueOf(gym))).remove(po);
                            ((Map) this.plugin.cooldownTask.get(Integer.valueOf(gym))).remove(po);
                            ((List) this.plugin.cooldownGym.get(Integer.valueOf(gym))).remove(po);

                            p.sendMessage(Text.of(Color.GREEN + "Removed " + playerSkip.getName() + "'s cooldown for gym" + gym));
                            playerSkip.sendMessage(Text.of(Color.GREEN + "Removed your cooldown for gym" + gym));
                        }

                    }
                } else {
                    p.sendMessage(Text.of(Color.RED + "Gym " + args[1] + " is not a valid gym."));
                }
                gymName = false;
            } else if ((args[0].equalsIgnoreCase("lost")) || (args[0].equalsIgnoreCase("l")) || (args[0].equalsIgnoreCase("lose"))) {

                boolean gymName = false;
                int gym = 0;

                outerloop:

                for (int g = 1; g <= 32; g++) {
                    if (this.plugin.getConfig().getString("config.gym" + g).equalsIgnoreCase(args[1])) {
                        gymName = true;

                        gym = g;

                        break outerloop;
                    }

                }

                if (args[1].matches("(gym|GYM)[1-9]?[0-9]") || gymName == true) {
                    // int i = gym - 1;

                    if (gymName == true) {
                        // do nothing all good.
                    } else {
                        String gymArg = args[1].replace("gym", "");

                        try {
                            gym = Integer.parseInt(gymArg);
                        } catch (NumberFormatException nfe) {
                            p.sendMessage(Text.of(Color.RED + args[1] + " is not a gym!"));
                            return;
                        }
                    }

                    if (Sponge.getServer().getPlayer(args[2]).orElse(null) != null) {
                        Player playerLost = Sponge.getServer().getPlayer(args[2]).get();

                        if ((p.hasPermissionLevel(gym)) || (p.hasPermissionLevel(2))) {
                            UUID po = playerLost.getUniqueId();
                            // int time = Integer.parseInt(this.plugin.getConfig().getString("config.cooldowntime"));

                            if (((List) this.plugin.queues.get(Integer.valueOf(gym))).contains(po)) {

                                // String gymArg3 = args[1].replace("gym", "");
                                // int gym3 = Integer.parseInt(gymArg3);

                                int gym3 = gym;

                                int time = Integer.parseInt(this.plugin.getConfig().getString("config.gym" + gym3 + "cooldowntime"));

                                if (this.plugin.settings.getData().get("warps.spawn") != null) {
                                    World w = Sponge.getServer().getWorld(this.plugin.settings.getData().getString("warps.spawn.world")).orElse(null);
                                    double x = this.plugin.settings.getData().getDouble("warps.spawn.x");
                                    double y = this.plugin.settings.getData().getDouble("warps.spawn.y");
                                    double z = this.plugin.settings.getData().getDouble("warps.spawn.z");
                                    playerLost.setLocation(new Location(w, x, y, z));
                                    playerLost.sendMessage(Text.of(Color.GREEN + "Teleported out of " + Color.YELLOW + "gym" + gym + "!"));
                                    playerLost.sendMessage(Text.of(Color.GREEN + "Unlucky! you lost that gym battle! (gym" + gym + "). Not to worry, you can retry the gym after your cooldown!"));
                                    playerLost.sendMessage(Text.of(Color.BLUE, "To check how long you have left on your cooldown, type: /gym join gym" + gym));

                                    p.sendMessage(Text.of(Color.GREEN + "Successfully telported " + playerLost.getName() + " out of your gym!"));
                                    p.sendMessage(Text.of(Color.GREEN + "You are now ready for your next battle, type: /gym next gym" + gym));
                                } else {
                                    p.sendMessage(Text.of(Color.RED + "Warp point 'spawn' does not exist. Type: /gym setwarp spawn. (this is the teleport location to move challengers out of the gym."));
                                }

                                // String gymArg3 = args[1].replace("gym", "");
                                // int gym3 = Integer.parseInt(gymArg3);

                                System.out.println(this.plugin.queues);
                                System.out.println(this.plugin.inArena);
                                ((List) this.plugin.queues.get(Integer.valueOf(gym))).remove(po);
                                ((List) this.plugin.inArena.get(Integer.valueOf(gym))).remove(po);
                                // remove player that lost from inArea and queues.
                                System.out.println(this.plugin.queues);
                                System.out.println(this.plugin.inArena);

                                this.plugin.settings.getLogs().set("Leaders." + p.getName() + ".gym" + gym + ".[" + this.plugin.format.format(this.plugin.now) + "]", playerLost.getName() + " - Lost");
                                this.plugin.settings.saveLogs();
                                // set logs as player lost and save
                                // START cooldown
                                ((Map) this.plugin.cooldownTime.get(Integer.valueOf(gym))).put(po, Integer.valueOf(time));
                                // get time from config and put it in cooldownTime

                                ((List) this.plugin.cooldownGym.get(Integer.valueOf(gym))).add(po);
                                // put player in cooldown

                                this.plugin.cooldownTask.get(Integer.valueOf(gym)).put(po, Sponge.getScheduler().createTaskBuilder().execute(() -> {
                                    // run cooldown task
                                    if (this.plugin.cooldownGym.get(gym3).contains(po)) {
                                        // if player is in the cooldown, continue. Else cancel.
                                        this.plugin.cooldownTime.get(gym3).put(po, this.plugin.cooldownTime.get(gym3).get(po) - 1);
                                        // each run, take 1 from time
                                        System.out.println(this.plugin.cooldownTime);

                                        if (this.plugin.cooldownTime.get(gym3).get(po) == 0) {
                                            // if time runs out (equals 0)
                                            this.plugin.cooldownTime.get(gym3).remove(po);
                                            this.plugin.cooldownTask.get(gym3).remove(po);
                                            this.plugin.cooldownGym.get(gym3).remove(po);

                                            System.out.println("Removed Player from cooldown");

//											if (Sponge.getScheduler().getScheduledTasks().contains(po)) {
//												Sponge.getScheduler().getTasksByName(String.valueOf(gym3)).forEach(t -> t.cancel());
//												//Sponge.getScheduler().getTasksByName(String.valueOf(gym3)).contains(po).forEach(t -> t.cancel());
//												System.out.println("Schedular Cancled");
//											}

                                        }
                                    } else {

//										if (Sponge.getScheduler().getScheduledTasks().contains(po)) {
//											Sponge.getScheduler().getTasksByName(String.valueOf(gym3)).forEach(t -> t.cancel());
//											//Sponge.getScheduler().getTasksByName(String.valueOf(gym3)).contains(po).forEach(t -> t.cancel());
//											System.out.println("Schedular Cancled");
//										}

                                    }
                                }).name(String.valueOf(gym)));
                                ((Task.Builder) ((Map) this.plugin.cooldownTask.get(Integer.valueOf(gym))).get(po)).delayTicks(20L).intervalTicks(1200L).submit(PlasmaGym.getInstance());
                                // END of Cooldown
                            } else {
                                p.sendMessage(Text.of(Color.RED + "Player must be in the queue to win or lose!"));
                            }
                        } else {
                            p.sendMessage(Text.of(Color.RED + "You do not have permission to set losers of gym" + gym));
                        }

                    }
                } else {
                    p.sendMessage(Text.of(Color.RED + "Gym " + args[1] + " is not a valid gym."));
                }
                gymName = false;
            }

        }
    }

    private void handleE4(PlayerEntity p, String[] args) throws MalformedURLException {
        if (args.length == 0) {
            if (!p.hasPermissionLevel(3) && !p.hasPermissionLevel(1)) {
                p.sendMessage(Text.of(Color.HSBtoRGB(218,165,32) +  "----- PlasmaGyms -----"));
                p.sendMessage(Text.of(""));
                p.sendMessage(Text.of(Color.GREEN + "/e4 list" + Color.HSBtoRGB(0,100,0) + " - Get a list of the E4 Level's and their status."));
                p.sendMessage(Text.of(Color.GREEN + "/e4 leaders" + Color.HSBtoRGB(0,100,0) + " - Get a list of the online E4 leaders."));
                p.sendMessage(Text.of(Color.GREEN + "/e4 rules <e4#>" + Color.HSBtoRGB(0,100,0) + " - Shows all the rules for the specified E4 Level."));
                p.sendMessage(Text.of(Color.GREEN + "/gym scoreboard" + Color.HSBtoRGB(0,100,0) + " - Toggle ScoreBoard for e4 and Gym."));
                p.sendMessage(Text.of(""));
                p.sendMessage(Text.of(Color.RED + "Plugin Made By "+ Color.HSBtoRGB(218,165,32) +  "ABkayCkay"));
            } else if (p.hasPermissionLevel(3)) {
                p.sendMessage(Text.of(Color.HSBtoRGB(218,165,32) +  "----- PlasmaGyms -----"));
                p.sendMessage(Text.of(""));
                p.sendMessage(Text.of(Color.GREEN + "/e4 list" + Color.HSBtoRGB(0,100,0) + " - Get a list of the E4 level's and their status."));
                p.sendMessage(Text.of(Color.GREEN + "/e4 leaders" + Color.HSBtoRGB(0,100,0) + " - Get a list of the online E4 leaders."));
                p.sendMessage(Text.of(Color.GREEN + "/e4 rules <e4#>" + Color.HSBtoRGB(0,100,0) + " - Shows all the rules for the specified E4 Level."));
                p.sendMessage(Text.of(Color.GREEN + "/e4 open <e4#>" + Color.HSBtoRGB(0,100,0) + " - Open a particular E4 level (e41, e42, e43 or e44)."));
                p.sendMessage(Text.of(Color.GREEN + "/e4 close <e4#>" + Color.HSBtoRGB(0,100,0) + " - Close a particular E4 level (e41, e42, e43 or e44)."));
                p.sendMessage(Text.of(Color.GREEN + "/e4 heal" + Color.HSBtoRGB(0,100,0) + " - Heals your pokemon."));

                p.sendMessage(Text.of(Color.GREEN + "/e4 sendrules <e4#> (username)" + Color.HSBtoRGB(0,100,0) + " - Force quit a e4 battle. In (Username) put either yours or the challengers IGN!"));
                p.sendMessage(Text.of(""));
                p.sendMessage(Text.of(Color.RED + "Plugin Made By "+ Color.HSBtoRGB(218,165,32) +  "ABkayCkay"));
            } else if (p.hasPermissionLevel(2)) {
                p.sendMessage(Text.of(Color.HSBtoRGB(218,165,32) +  "----- PlasmaGyms -----"));
                p.sendMessage(Text.of(""));
                p.sendMessage(Text.of(Color.GREEN + "/e4 list" + Color.HSBtoRGB(0,100,0) + " - Get a list of the E4 Level's and their status."));
                p.sendMessage(Text.of(Color.GREEN + "/e4 leaders" + Color.HSBtoRGB(0,100,0) + " - Get a list of the online E4 leaders."));
                p.sendMessage(Text.of(Color.GREEN + "/e4 rules <e4#>" + Color.HSBtoRGB(0,100,0) + " - Shows all the rules for the specified E4 Level."));
                p.sendMessage(Text.of(Color.GREEN + "/e4 closeall" + Color.HSBtoRGB(0,100,0) + " - Closes all Elite 4 Level's."));
                p.sendMessage(Text.of(""));
                p.sendMessage(Text.of(Color.RED + "Plugin Made By "+ Color.HSBtoRGB(218,165,32) +  "ABkayCkay"));
            }

        } else if (args.length == 1) {
            if (args[0].equals("leaders")) {
                p.sendMessage(Text.of(Color.HSBtoRGB(218,165,32) +  "----- Online E4 Leaders -----"));
                p.sendMessage(Text.of(""));
                
                for (PlayerEntity e4staff : players.getPlayerList()) {
                    if (e4staff.hasPermissionLevel(51)) {
                        if (this.plugin.enablee4.equalsIgnoreCase("true")) {
                         //   p.sendMessage(Text.of(getConfig().getString("config.e41colour")) + e4staff.getName() + " - " + getConfig().getString("config.e41"));
                        }
                    }
                    if (e4staff.hasPermissionLevel(52)) {
                        if (this.plugin.enablee4.equalsIgnoreCase("true")) {
                        //    p.sendMessage(Text.of(TextSerializers.FORMATTING_CODE.deserialize(getConfig().getString("config.e42colour")) + e4staff.getName() + " - " + getConfig().getString("config.e42")));
                        }
                    }
                    if (e4staff.hasPermissionLevel(53)) {
                        if (this.plugin.enablee4.equalsIgnoreCase("true")) {
                       //     p.sendMessage(Text.of(TextSerializers.FORMATTING_CODE.deserialize(getConfig().getString("config.e43colour")) + e4staff.getName() + " - " + getConfig().getString("config.e43")));
                        }
                    }
                    if (e4staff.hasPermissionLevel(54)) {
                        if (this.plugin.enablee4.equalsIgnoreCase("true")) {
                       //     p.sendMessage(Text.of(TextSerializers.FORMATTING_CODE.deserialize(getConfig().getString("config.e44colour")) + e4staff.getName() + " - " + getConfig().getString("config.e44")));
                        }

                    }

                }

            } else if (args[0].equals("list")) {
                p.sendMessage(Text.of(Color.HSBtoRGB(218,165,32) +  "----- PlasmaGyms -----"));
                p.sendMessage(Text.of(""));
                if (this.plugin.enablee4.equalsIgnoreCase("true")) {
               //     p.sendMessage(Text.of(TextSerializers.FORMATTING_CODE.deserialize(getConfig().getString("config.e41colour") + "TEXT").getColor() + getConfig().getString("config.e41") + "  Elite 4 is: " + Color.BLUE, getConfig().getString("config.e41stat")));
                 //   p.sendMessage(Text.of(TextSerializers.FORMATTING_CODE.deserialize(getConfig().getString("config.e42colour") + "TEXT").getColor() + getConfig().getString("config.e42") + "  Elite 4 is: " + Color.BLUE, getConfig().getString("config.e42stat")));
                   // p.sendMessage(Text.of(TextSerializers.FORMATTING_CODE.deserialize(getConfig().getString("config.e43colour") + "TEXT").getColor() + getConfig().getString("config.e43") + "  Elite 4 is: " + Color.BLUE, getConfig().getString("config.e43stat")));
                    //p.sendMessage(Text.of(TextSerializers.FORMATTING_CODE.deserialize(getConfig().getString("config.e44colour") + "TEXT").getColor() + getConfig().getString("config.e44") + "  Elite 4 is: " + Color.BLUE, getConfig().getString("config.e41stat")));
                }

            } else if ((args[0].equals("open")) && (p.hasPermissionLevel(3))) {
                p.sendMessage(Text.of(Color.DARK_GRAY +  "Proper Usage: "+ Color.RED + "/e4 open <e4#>"));
            } else if ((args[0].equals("close")) && (p.hasPermissionLevel(3))) {
                p.sendMessage(Text.of(Color.DARK_GRAY +  "Proper Usage: "+ Color.RED + "/e4 close <e4#>"));
            } else if (args[0].equals("rules")) {
                p.sendMessage(Text.of(Color.DARK_GRAY +  "Proper Usage: "+ Color.RED + "/e4 rules <e4#>"));
            } else if ((args[0].equals("heal")) && (p.hasPermissionLevel(3)) && (this.plugin.enableGymHeal.equalsIgnoreCase("true"))) {
         //       Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "pokeheal " + p.getName().toString());
                p.sendMessage(Text.of("Your pixelmon have been healed!"));
            } else if ((args[0].equals("heal")) && (p.hasPermissionLevel(3)) && (!this.plugin.enableGymHeal.equalsIgnoreCase("true"))) {
                p.sendMessage(Text.of(Color.RED + "Gym/E4 Leader healing disabled in the plugin config"));
            } else if ((args[0].equals("quit")) && (p.hasPermissionLevel(3))) {
                for (PlayerEntity playerTarget : players.getPlayerList()) {
           //         Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "endbattle " + playerTarget.getName().toString());
                    p.sendMessage(Text.of(Color.GREEN + "You have successfully quit the battle!"));
                }

            } else if ((args[0].equalsIgnoreCase("closeall")) && (p.hasPermissionLevel(2))) {
              //  MessageCommand.notifyAll(Text.of(Color.DARK_GRAY + "[" + Color.CYAN + getConfig().getString("config.title") + Color.DARK_GRAY + "] " + Color.YELLOW + "All " + getConfig().getString("config.e4") + " Level's are now closed."));

                getConfig().set("config.e41stat", "Closed");
                getConfig().set("config.e42stat", "Closed");
                getConfig().set("config.e43stat", "Closed");
                getConfig().set("config.e44stat", "Closed");

                ScoreboardManager.updateScoreboard();
            }
        } else if (args.length == 2) {
            if ((args[0].equals("rules")) && (args[1].equals("e41"))) {
                if (this.plugin.enablee4.equalsIgnoreCase("true")) {
                    p.sendMessage(Text.of(Color.HSBtoRGB(218,165,32) +  "----- PlasmaGyms -----"));
                    p.sendMessage(Text.of(""));
                    p.sendMessage(Text.of(Color.GREEN + "1) " + getConfig().getString("config.e41rule1")));
                    p.sendMessage(Text.of(Color.GREEN + "2) " + getConfig().getString("config.e41rule2")));
                    p.sendMessage(Text.of(Color.GREEN + "3) " + getConfig().getString("config.e41rule3")));
                    p.sendMessage(Text.of(Color.GREEN + "4) " + getConfig().getString("config.e41rule4")));
                    p.sendMessage(Text.of(Color.GREEN + "5) " + getConfig().getString("config.e41rule5")));
                }
            } else if ((args[0].equals("rules")) && (args[1].equals("e42"))) {
                if (this.plugin.enablee4.equalsIgnoreCase("true")) {
                    p.sendMessage(Text.of(Color.HSBtoRGB(218,165,32) +  "----- PlasmaGyms -----"));
                    p.sendMessage(Text.of(""));
                    p.sendMessage(Text.of(Color.GREEN + "1) " + getConfig().getString("config.e42rule1")));
                    p.sendMessage(Text.of(Color.GREEN + "2) " + getConfig().getString("config.e42rule2")));
                    p.sendMessage(Text.of(Color.GREEN + "3) " + getConfig().getString("config.e42rule3")));
                    p.sendMessage(Text.of(Color.GREEN + "4) " + getConfig().getString("config.e42rule4")));
                    p.sendMessage(Text.of(Color.GREEN + "5) " + getConfig().getString("config.e42rule5")));
                }
            } else if ((args[0].equals("rules")) && (args[1].equals("e43"))) {
                if (this.plugin.enablee4.equalsIgnoreCase("true")) {
                    p.sendMessage(Text.of(Color.HSBtoRGB(218,165,32) +  "----- PlasmaGyms -----"));
                    p.sendMessage(Text.of(""));
                    p.sendMessage(Text.of(Color.GREEN + "1) " + getConfig().getString("config.e43rule1")));
                    p.sendMessage(Text.of(Color.GREEN + "2) " + getConfig().getString("config.e43rule2")));
                    p.sendMessage(Text.of(Color.GREEN + "3) " + getConfig().getString("config.e43rule3")));
                    p.sendMessage(Text.of(Color.GREEN + "4) " + getConfig().getString("config.e43rule4")));
                    p.sendMessage(Text.of(Color.GREEN + "5) " + getConfig().getString("config.e43rule5")));
                }
            } else if ((args[0].equals("rules")) && (args[1].equals("e44"))) {
                if (this.plugin.enablee4.equalsIgnoreCase("true")) {
                    p.sendMessage(Text.of(Color.HSBtoRGB(218,165,32) +  "----- PlasmaGyms -----"));
                    p.sendMessage(Text.of(""));
                    p.sendMessage(Text.of(Color.GREEN + "1) " + getConfig().getString("config.e44rule1")));
                    p.sendMessage(Text.of(Color.GREEN + "2) " + getConfig().getString("config.e44rule2")));
                    p.sendMessage(Text.of(Color.GREEN + "3) " + getConfig().getString("config.e44rule3")));
                    p.sendMessage(Text.of(Color.GREEN + "4) " + getConfig().getString("config.e44rule4")));
                    p.sendMessage(Text.of(Color.GREEN + "5) " + getConfig().getString("config.e44rule5")));
                }
            }
            if (p.hasPermissionLevel(3)) {
                if ((args[0].equalsIgnoreCase("open")) && (args[1].equalsIgnoreCase("e41")) && (p.hasPermissionLevel(51)) && (this.plugin.enablee4.equalsIgnoreCase("true"))) {
               //     Message.notifyAll(Text.of(Color.DARK_GRAY + "[" + Color.CYAN + getConfig().getString("config.title") + Color.DARK_GRAY + "] " + getConfig().getString("config.e41colour") + "TEXT") + "The " + getConfig().getString("config.e41") + " " + getConfig().getString("config.e4") + " is now "+ Color.GREEN + "Open");
                    getConfig().set("config.e41stat", "Open");

                    URL myURL = new URL("https://forums.spongepowered.org/t/pixelmongym-pixelmonmod-gym-management/17875");
                //    Text link = Text.builder("Click here to find the plugin page!").onClick(TextActions.openUrl(myURL)).build();

                //    p.sendMessage(Text.of(Color.HSBtoRGB(218,165,32) +  link));

                    // p.sendMessage(Text.of(Color.HSBtoRGB(218,165,32) +  "The Plugin can be found @ http://dev.bukkit.org/bukkit-plugins/pixelmongym/ "));

                    if (this.plugin.getConfig().getString("config.scoreboard").equals("True")) {
                        ScoreboardManager.updateScoreboard();
                    }
                }

                if ((args[0].equalsIgnoreCase("open")) && (args[1].equalsIgnoreCase("e42")) && (p.hasPermissionLevel(52)) && (this.plugin.enablee4.equalsIgnoreCase("true"))) {
                  //  Message.TO_ALL.send(Text.of(Color.DARK_GRAY, "[" + Color.CYAN, getConfig().getString("config.title") + Color.DARK_GRAY, "] " + TextSerializers.FORMATTING_CODE.deserialize(getConfig().getString("config.e42colour") + "TEXT").getColor(), "The " + getConfig().getString("config.e42") + " " + getConfig().getString("config.e4") + " is now "+ Color.GREEN + "Open"));
                    getConfig().set("config.e42stat", "Open");

                    URL myURL = new URL("https://forums.spongepowered.org/t/pixelmongym-pixelmonmod-gym-management/17875");
                //    Text link = Text.builder("Click here to find the plugin page!").onClick(TextActions.openUrl(myURL)).build();

               //     p.sendMessage(Text.of(Color.HSBtoRGB(218,165,32) +  link));

                    // p.sendMessage(Text.of(Color.HSBtoRGB(218,165,32) +  "The Plugin can be found @ http://dev.bukkit.org/bukkit-plugins/pixelmongym/ "));

                    if (this.plugin.getConfig().getString("config.scoreboard").equals("True")) {
                        ScoreboardManager.updateScoreboard();
                    }
                }

                if ((args[0].equalsIgnoreCase("open")) && (args[1].equalsIgnoreCase("e43")) && (p.hasPermissionLevel(53)) && (this.plugin.enablee4.equalsIgnoreCase("true"))) {
                   // MessageChannel.TO_ALL.send(Text.of(Color.DARK_GRAY, "[" + Color.CYAN, getConfig().getString("config.title") + Color.DARK_GRAY, "] " + TextSerializers.FORMATTING_CODE.deserialize(getConfig().getString("config.e43colour") + "TEXT").getColor(), "The " + getConfig().getString("config.e43") + " " + getConfig().getString("config.e4") + " is now "+ Color.GREEN + "Open"));
                    getConfig().set("config.e43stat", "Open");

                    URL myURL = new URL("https://forums.spongepowered.org/t/pixelmongym-pixelmonmod-gym-management/17875");
                  //  Text link = Text.builder("Click here to find the plugin page!").onClick(TextActions.openUrl(myURL)).build();

                  //  p.sendMessage(Text.of(Color.HSBtoRGB(218,165,32) +  link));

                    // p.sendMessage(Text.of(Color.HSBtoRGB(218,165,32) +  "The Plugin can be found @ http://dev.bukkit.org/bukkit-plugins/pixelmongym/ "));

                    if (this.plugin.getConfig().getString("config.scoreboard").equals("True")) {
                        ScoreboardManager.updateScoreboard();
                    }
                }

                if ((args[0].equalsIgnoreCase("open")) && (args[1].equalsIgnoreCase("e44")) && (p.hasPermissionLevel(54)) && (this.plugin.enablee4.equalsIgnoreCase("true"))) {
               //     MessageChannel.TO_ALL.send(Text.of(Color.DARK_GRAY, "[" + Color.CYAN, getConfig().getString("config.title") + Color.DARK_GRAY, "] " + TextSerializers.FORMATTING_CODE.deserialize(getConfig().getString("config.e44colour") + "TEXT").getColor(), "The " + getConfig().getString("config.e44") + " " + getConfig().getString("config.e4") + " is now "+ Color.GREEN + "Open"));
                    getConfig().set("config.e44stat", "Open");
                    URL myURL = new URL("https://forums.spongepowered.org/t/pixelmongym-pixelmonmod-gym-management/17875");
                 //   Text link = Text.builder("Click here to find the plugin page!").onClick(TextActions.openUrl(myURL)).build();

                 //   p.sendMessage(Text.of(Color.HSBtoRGB(218,165,32) +  link));
                    // p.sendMessage(Text.of(Color.HSBtoRGB(218,165,32) +  "The Plugin can be found @ http://dev.bukkit.org/bukkit-plugins/pixelmongym/ "));
                    if (this.plugin.getConfig().getString("config.scoreboard").equals("True")) {
                        ScoreboardManager.updateScoreboard();
                    }
                } else if ((args[0].equalsIgnoreCase("close")) && (args[1].equalsIgnoreCase("e41")) && (p.hasPermissionLevel(51)) && (this.plugin.enablee4.equalsIgnoreCase("true"))) {
                 //   MessageChannel.TO_ALL.send(Text.of(Color.DARK_GRAY, "[" + Color.CYAN, getConfig().getString("config.title") + Color.DARK_GRAY, "] " + TextSerializers.FORMATTING_CODE.deserialize(getConfig().getString("config.e41colour") + "TEXT").getColor(), "The " + getConfig().getString("config.e41") + " " + getConfig().getString("config.e4") + " is now "+ Color.RED + "Closed"));
                    getConfig().set("config.e41stat", "Closed");
                    URL myURL = new URL("https://forums.spongepowered.org/t/pixelmongym-pixelmonmod-gym-management/17875");
                //    Text link = Text.builder("Click here to find the plugin page!").onClick(TextActions.openUrl(myURL)).build();

                //    p.sendMessage(Text.of(Color.HSBtoRGB(218,165,32) +  link));
                    // p.sendMessage(Text.of(Color.HSBtoRGB(218,165,32) +  "The Plugin can be found @ http://dev.bukkit.org/bukkit-plugins/pixelmongym/ "));
                    ScoreboardManager.updateScoreboard();
                } else if ((args[0].equalsIgnoreCase("close")) && (args[1].equalsIgnoreCase("e42")) && (p.hasPermissionLevel(52)) && (this.plugin.enablee4.equalsIgnoreCase("true"))) {
                 //   MessageChannel.TO_ALL.send(Text.of(Color.DARK_GRAY, "[" + Color.CYAN, getConfig().getString("config.title") + Color.DARK_GRAY, "] " + TextSerializers.FORMATTING_CODE.deserialize(getConfig().getString("config.e42colour") + "TEXT").getColor(), "The " + getConfig().getString("config.e42") + " " + getConfig().getString("config.e4") + " is now "+ Color.RED + "Closed"));
                    getConfig().set("config.e42stat", "Closed");
                    URL myURL = new URL("https://forums.spongepowered.org/t/pixelmongym-pixelmonmod-gym-management/17875");
                //    Text link = Text.builder("Click here to find the plugin page!").onClick(TextActions.openUrl(myURL)).build();

                 //   p.sendMessage(Text.of(Color.HSBtoRGB(218,165,32) +  link));
                    // p.sendMessage(Text.of(Color.HSBtoRGB(218,165,32) +  "The Plugin can be found @ http://dev.bukkit.org/bukkit-plugins/pixelmongym/ "));
                    ScoreboardManager.updateScoreboard();
                } else if ((args[0].equalsIgnoreCase("close")) && (args[1].equalsIgnoreCase("e43")) && (p.hasPermissionLevel(35)) && (this.plugin.enablee4.equalsIgnoreCase("true"))) {
                 //   MessageChannel.TO_ALL.send(Text.of(Color.DARK_GRAY, "[" + Color.CYAN, getConfig().getString("config.title") + Color.DARK_GRAY, "] " + TextSerializers.FORMATTING_CODE.deserialize(getConfig().getString("config.e43colour") + "TEXT").getColor(), "The " + getConfig().getString("config.e43") + " " + getConfig().getString("config.e4") + " is now "+ Color.RED + "Closed"));
                    getConfig().set("config.e43stat", "Closed");
                    URL myURL = new URL("https://forums.spongepowered.org/t/pixelmongym-pixelmonmod-gym-management/17875");
                //    Text link = Text.builder("Click here to find the plugin page!").onClick(TextActions.openUrl(myURL)).build();

                 //   p.sendMessage(Text.of(Color.HSBtoRGB(218,165,32) +  link));
                    // p.sendMessage(Text.of(Color.HSBtoRGB(218,165,32) +  "The Plugin can be found @ http://dev.bukkit.org/bukkit-plugins/pixelmongym/ "));
                    ScoreboardManager.updateScoreboard();
                } else if ((args[0].equalsIgnoreCase("close")) && (args[1].equalsIgnoreCase("e44")) && (p.hasPermissionLevel(54)) && (this.plugin.enablee4.equalsIgnoreCase("true"))) {
                 //   MessageChannel.TO_ALL.send(Text.of(Color.DARK_GRAY, "[" + Color.CYAN, getConfig().getString("config.title") + Color.DARK_GRAY, "] " + TextSerializers.FORMATTING_CODE.deserialize(getConfig().getString("config.e44colour") + "TEXT").getColor(), "The " + getConfig().getString("config.e44") + " " + getConfig().getString("config.e4") + " is now "+ Color.RED + "Closed"));
                    getConfig().set("config.e44stat", "Closed");
                    URL myURL = new URL("https://forums.spongepowered.org/t/pixelmongym-pixelmonmod-gym-management/17875");
                  //  Text link = Text.builder("Click here to find the plugin page!").onClick(TextActions.openUrl(myURL)).build();

                  //  p.sendMessage(Text.of(Color.HSBtoRGB(218,165,32) +  link));
                    // p.sendMessage(Text.of(Color.HSBtoRGB(218,165,32) +  "The Plugin can be found @ http://dev.bukkit.org/bukkit-plugins/pixelmongym/ "));
                    ScoreboardManager.updateScoreboard();
                }
            }

        } else if (args.length == 3) {
            for (PlayerEntity playerTarget : players.getPlayerList()) {
                // if ((args[0].equalsIgnoreCase("sendrules")) && (playerTarget.getName(args[2])) && (p.hasPermission("plasmagym." + args[1]))) {
                //     p.sendMessage(Text.of(Color.HSBtoRGB(218,165,32) +  "Sent " + getConfig().getString(new StringBuilder("config.").append(args[1]).toString()) + " Elite 4 Rules to " + playerTarget.getName().toString()));
                //     playerTarget.sendMessage(Text.of(Color.HSBtoRGB(218,165,32) +  playerTarget.getName().toString() + ", Make sure you read the " + getConfig().getString(new StringBuilder("config.").append(args[1]).toString()) + " Elite 4 rules properly before facing the Gym!"));
                //     playerTarget.sendMessage(Text.of(Color.GREEN + "1) " + getConfig().getString(new StringBuilder("config.").append(args[1]).append("rule1").toString())));
                //     playerTarget.sendMessage(Text.of(Color.GREEN + "2) " + getConfig().getString(new StringBuilder("config.").append(args[1]).append("rule2").toString())));
                //     playerTarget.sendMessage(Text.of(Color.GREEN + "3) " + getConfig().getString(new StringBuilder("config.").append(args[1]).append("rule3").toString())));
                //     playerTarget.sendMessage(Text.of(Color.GREEN + "4) " + getConfig().getString(new StringBuilder("config.").append(args[1]).append("rule4").toString())));
                //     playerTarget.sendMessage(Text.of(Color.GREEN + "5) " + getConfig().getString(new StringBuilder("config.").append(args[1]).append("rule5").toString())));
                // }
            }
        }
    }

    private PluginConfiguration getConfig() {
        return this.plugin.getConfig();
    }
}
//test
