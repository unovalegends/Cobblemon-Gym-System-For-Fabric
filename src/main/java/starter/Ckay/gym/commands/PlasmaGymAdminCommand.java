 package starter.Ckay.gym.commands;

import com.google.common.collect.Sets;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContextBuilder;

import org.spongepowered.api.command.*;

import org.spongepowered.api.command.exception.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandCause;
import org.spongepowered.api.command.parameter.CommandContext;
import org.spongepowered.api.command.CommandExecutor;

import starter.Ckay.gym.PlasmaGym;
import net.minecraft.advancement.criterion.Criterion.ConditionsContainer;
import net.minecraft.advancement.criterion.Criterion;
import net.minecraft.command.CommandSource;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.MessageCommand;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import com.mojang.brigadier.tree.CommandNode;

import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.service.permission.PermissionService;
import org.spongepowered.api.service.permission.Subject;
import org.spongepowered.api.service.permission.SubjectCollection;

import static net.minecraft.server.command.CommandManager.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.util.TriState;
import net.minecraft.server.world.*;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.ServerMetadata.Players;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import java.util.regex.*;

import java.util.Optional;
import java.util.Objects;
import net.minecraft.entity.Entity;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

@SuppressWarnings("unused")
public class PlasmaGymAdminCommand implements ModInitializer  {
    PlasmaGym plugin;    
    PlayerManager player;
    PlayerEntity playerInstance;
    ServerPlayerEntity serverPlayer;
    CommandManager.CommandParser getPermissions;

    public PlasmaGymAdminCommand(PlasmaGym plasmaGym) {
        this.plugin = plasmaGym;
    }
	
 @Override
    public void onInitialize() {
      CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(literal("gymadmin").requires(source -> source.hasPermissionLevel(2))
          .executes(context -> {
        
        context.getSource().sendMessage(Text.literal(TextColor.parse("GREEN") + "/plasmagym reload" +  TextColor.parse("DARK_GREEN") + " - Reloads the plugin config."));
        context.getSource().sendMessage(Text.literal(TextColor.parse("GREEN") +  "/plasmagym checkconfig" +  TextColor.parse("DARK_GREEN") +" - Checks the currently set config settings."));
        context.getSource().sendMessage(Text.literal(TextColor.parse("GREEN") +  "/plasmagym addleader <GymName/e4#> (Username)" +  TextColor.parse("DARK_GREEN") +" - Add a gym leader to a specific gym or elite 4 level. E.G: /plasmagym addleader Gym1 ABkayCkay"));
        context.getSource().sendMessage(Text.literal(TextColor.parse("GREEN") +  "/plasmagym delleader <GymName/e4#> (Username)" +  TextColor.parse("DARK_GREEN") +" - Remove a gym leader from a specific gym or elite 4 level. E.G: /plasmagym delleader Gym1 ABkayCkay"));
        context.getSource().sendMessage(Text.literal(TextColor.parse("GREEN") +  "/plasmagym setlevel <GymName> (level)" +  TextColor.parse("DARK_GREEN") +" - Sets the level of the specified gym. (Modify's the config)"));
        context.getSource().sendMessage(Text.literal(TextColor.parse("GREEN") +  "/plasmagym setrule <GymName> <rule#> (rule)" +  TextColor.parse("DARK_GREEN") +" - Sets the rules of the specified gym. (Modify's the config) E.G: /plasmagym setrule gym1 1 No_Potions"));
        context.getSource().sendMessage(Text.literal(TextColor.parse("GREEN") +  "/gym closeall" +  TextColor.parse("DARK_GREEN") +" - Closes all Gym's."));
        context.getSource().sendMessage(Text.literal(TextColor.parse("GREEN") +  "/e4 closeall" +  TextColor.parse("DARK_GREEN") +" - Closes all Elite 4 level's."));
   
        return 1;
      }
      )));
    
      CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(literal("plasmagym").then(literal("reload").requires(source -> source.hasPermissionLevel(2))
          .executes(context -> {
        
            this.plugin.getConfig().load();
            context.getSource().sendMessage(Text.literal(TextColor.parse("DARK_GRAY") + "[" + TextColor.parse("AQUA") + this.plugin.getConfig().getString("config.title") + TextColor.parse("DARK_GRAY") + "] " + TextColor.parse("GREEN") + "Config Reloaded!"));
   
        return 1;
      }
      ))));

      CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(literal("checkconfig")
        .requires(source -> source.hasPermissionLevel(2))
            .executes(context -> {
    
        context.getSource().sendMessage(Text.literal(TextColor.parse("GOLD") + "======= Current PixelGym Config settings ======="));
        context.getSource().sendMessage(Text.literal(" "));
        context.getSource().sendMessage(Text.literal(TextColor.parse("GREEN") +  "Player Join Messages: " + (TextColor.parse("GOLD") + this.plugin.getConfig().getString("config.joinmessage"))));
        context.getSource().sendMessage(Text.literal(TextColor.parse("GREEN") +  "Scoreboard Active: " + (TextColor.parse("GOLD") + this.plugin.getConfig().getString("config.scoreboard"))));
        context.getSource().sendMessage(Text.literal(TextColor.parse("GREEN") +  "Gym/E4 Leader healing: " + (TextColor.parse("GOLD") + this.plugin.getConfig().getString("config.gymhealing"))));
        context.getSource().sendMessage(Text.literal(TextColor.parse("GREEN") +  "Elite 4 Enabled: " + (TextColor.parse("GOLD") + this.plugin.getConfig().getString("config.e4enabled"))));
        context.getSource().sendMessage(Text.literal(TextColor.parse("GREEN") +  "Give Leaders Pokemon: " + (TextColor.parse("GOLD") + this.plugin.getConfig().getString("config.giveleaderpokemon"))));
    return 1;
  }
  )));

  CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(literal("addleader")
        .requires(source -> source.hasPermissionLevel(2))
            .executes(context -> {

    context.getSource().sendMessage(Text.literal("Called //addleader with no gym. You need to enter a gym eg. gym14"));
    return 1;
}
)));   
  
  CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(literal("addleader")
    .then(literal(("gym|GYM[1-9]?[0-9]*"))
        .requires(source -> source.hasPermissionLevel(2))
            .executes(context -> {
    
        boolean gymName = false;
        int gym = 0;
        String arg = context.getInput();
        String[] args = arg.isEmpty() ? new String[]{} : arg.split(" ");

        outerloop:

        for (int g = 1; g <= 20; g++) {
            if (this.plugin.getConfig().getString("config.gym" + g).equalsIgnoreCase(args[1])) {
                gymName = true;

                gym = g;

                break outerloop;
            }
        }
        
        if (args[1].matches("gym|GYM[1-9]?[0-9]") || gymName == true) {

            if (gymName == true) {
                //do nothing all good.
            } else {
                String gymArg = args[1].replace("gym", "");

                try {
                    gym = Integer.parseInt(gymArg);
                } catch (NumberFormatException nfe) {
                    context.getSource().sendMessage(Text.literal(TextColor.parse("RED") + args[1] + " is not a gym!"));
                    return 1;
                }
            }
           
            if (player.getPlayer(args[2])!= null) {

                serverPlayer = player.getPlayer(args[2]);

                 SubjectCollection sc = playerInstance.get;
                 Optional<Subject> subjO = sc.getSubject(playerInstance.getUuid());
                // Subject subject = subjO.orElse(sc.getDefaults());

                
                Optional<String> value = Options.get(player, "prefix");
                //String gym = args[1].replace("gym", "");

                // if (this.plugin.getConfig().getString("config.enablegroup").equals("True")) {
                //     // TODO Make sure getSubjectIdentifier() is correct
                //     if (playerInstance.get.getParents().stream().filter(t -> t.getSubjectIdentifier().equals(this.plugin.getConfig().getString("config.globalgroupname")))
                //             .findAny().isPresent()) {
                //         playerInstance.sendMessage(Text.of(TextColors.RED, "Player is already in this group, giving them the gym permission node. (plasmagym.gym" + gym + ")"));
                         subject.getSubjectData().setPermission(Sets.newHashSet(), "plasmagym.gym" + gym, TriState.TRUE);
                //     } else {
                //         String gymgroup = this.plugin.getConfig().getString("config.globalgroupname");

                //         SubjectCollection sc2 = permService.getGroupSubjects();
                //         Optional<Subject> subjO2 = sc.getSubject(gymgroup);

                //         subject.getSubjectData().addParent(Sets.newHashSet(), subjO2.orElse(sc2.getDefaults()).asSubjectReference());
                //         subject.getSubjectData().setPermission(Sets.newHashSet(), "plasmagym.gym" + gym, Tristate.TRUE);
                //     }
                // } else {
                //     subject.getSubjectData().setPermission(Sets.newHashSet(), "plasmagym.gym" + gym, Tristate.TRUE);
                //     subject.getSubjectData().setPermission(Sets.newHashSet(), "plasmagym.leader", Tristate.TRUE);
                // }

                if (this.plugin.getConfig().getString("config.giveleaderpokemon").equals("True")) {
                    //to do, find out how to give pokemon via command
                    //Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "pokegive " + playerTarget.getName().toString() + " " + this.plugin.getConfig().getString(new StringBuilder("config.gym").append(gym).append("poke1").toString()) + " lvl" + this.plugin.getConfig().getString(new StringBuilder("config.gym").append(gym).append("poke1lvl").toString()));
                    context.getSource().sendMessage(Text.literal("pokegive " + playerInstance.getName().toString() + " " + this.plugin.getConfig().getString(new StringBuilder("config.gym").append(gym).append("poke1").toString()) + " lvl" + this.plugin.getConfig().getString(new StringBuilder("config.gym").append(gym).append("poke1lvl").toString())));
                    context.getSource().sendMessage(Text.literal("pokegive " + playerInstance.getName().toString() + " " + this.plugin.getConfig().getString(new StringBuilder("config.gym").append(gym).append("poke2").toString()) + " lvl" + this.plugin.getConfig().getString(new StringBuilder("config.gym").append(gym).append("poke2lvl").toString())));
                    context.getSource().sendMessage(Text.literal("pokegive " + playerInstance.getName().toString() + " " + this.plugin.getConfig().getString(new StringBuilder("config.gym").append(gym).append("poke3").toString()) + " lvl" + this.plugin.getConfig().getString(new StringBuilder("config.gym").append(gym).append("poke3lvl").toString())));
                    context.getSource().sendMessage(Text.literal("pokegive " + playerInstance.getName().toString() + " " + this.plugin.getConfig().getString(new StringBuilder("config.gym").append(gym).append("poke4").toString()) + " lvl" + this.plugin.getConfig().getString(new StringBuilder("config.gym").append(gym).append("poke4lvl").toString())));
                    context.getSource().sendMessage(Text.literal("pokegive " + playerInstance.getName().toString() + " " + this.plugin.getConfig().getString(new StringBuilder("config.gym").append(gym).append("poke5").toString()) + " lvl" + this.plugin.getConfig().getString(new StringBuilder("config.gym").append(gym).append("poke5lvl").toString())));
                    context.getSource().sendMessage(Text.literal("pokegive " + playerInstance.getName().toString() + " " + this.plugin.getConfig().getString(new StringBuilder("config.gym").append(gym).append("poke6").toString()) + " lvl" + this.plugin.getConfig().getString(new StringBuilder("config.gym").append(gym).append("poke6lvl").toString())));
                }

                playerInstance.sendMessage(Text.literal(TextColor.parse("DARK_GRAY") + "[" + TextColor.parse("AQUA") + this.plugin.getConfig().getString("config.title") + TextColor.parse("DARK_GRAY") + "] " + this.plugin.getConfig().getString(new StringBuilder("config.gym").append(gym).append("colour").toString()) + "TEXT") + "You have been set as a " + this.plugin.getConfig().getString(new StringBuilder("config.gym").append(gym).toString()) + " Leader!");
                playerInstance.sendMessage(Text.literal(TextColor.parse("DARK_GRAY") + "[" + TextColor.parse("AQUA") + this.plugin.getConfig().getString("config.title") + TextColor.parse("DARK_GRAY") + "] " + this.plugin.getConfig().getString(new StringBuilder("config.gym").append(gym).append("colour").toString()) + "TEXT") + "Try /gym or /e4 to see your new commands!");
                context.getSource().sendMessage(Text.literal(TextColor.parse("DARK_GRAY") + "[" + TextColor.parse("AQUA") + this.plugin.getConfig().getString("config.title") + TextColor.parse("DARK_GRAY") + "] " + this.plugin.getConfig().getString(new StringBuilder("config.gym")
                        .append(gym).append("colour").toString()) + "TEXT") + playerInstance.getName().toString() + " has successfully been added as a " + this.plugin.getConfig().getString(new StringBuilder("config.").append(args[1]).toString()) + " leader!");

            }
        }
        
        return 1;
    }))));
        
    
            
    CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(literal("delleader")
        .then(literal(("gym|GYM[1-9]?[0-9]*"))
        .requires(source -> source.hasPermissionLevel(2))
            .executes(context -> {
            
            String arg = context.getInput();
            String[] args = arg.isEmpty() ? new String[]{} : arg.split(" ");
            playerInstance = player.getPlayer(args[2]);
            boolean gymName = false;
            int gym = 0;
            boolean all = false;

            outerloop:

            for (int g = 1; g <= 20; g++) {
                if (this.plugin.getConfig().getString("config.gym" + g).equalsIgnoreCase(args[1])) {
                    gymName = true;
                    gym = g;
                    break outerloop;
                }
            }

            if (args[1].matches("(gym|GYM)[1-9]?[0-9]") || args[1].equalsIgnoreCase("all") || gymName == true) {
            //int i = gym - 1;

            if (gymName == true) {
                //do nothing all good.
            } else if (args[1].equalsIgnoreCase("all")) {
                //do nothing all good
            } else {
                String gymArg = args[1].replace("gym", "");
                try {
                    gym = Integer.parseInt(gymArg);
                } catch (NumberFormatException nfe) {
                    context.getSource().sendMessage(Text.literal(TextColor.parse("RED") + args[1] + " is not a gym!"));
                    return 1;
                }
            }
        }
        
            if (args[2].equals(playerInstance.getName())) {

                if (this.plugin.getConfig().getString("config.enablegroup").equals("True")) {
                        if (playerInstance.hasPermissionLevel(gym +10)){
                            if (!args[1].equalsIgnoreCase("all")) {
                                subject.getSubjectData().setPermission(Sets.newHashSet(), "plasmagym.gym" + gym, Tristate.FALSE);
                            }

                            outerloop:

                            for (int i = 11; i <= 30; i++) {
                                if (playerInstance.hasPermissionLevel( i)) {
                                    if (args[1].equalsIgnoreCase("all")) {
                                        subject.getSubjectData().setPermission(Sets.newHashSet(), "plasmagym.gym" + i, Tristate.FALSE);
                                    } else {
                                        break outerloop;
                                    }
                                } else {

                                    // SubjectCollection sc2 = permService.getGroupSubjects();
                                    // Optional<Subject> subjO2 = sc.getSubject(this.plugin.getConfig().getString("config.globalgroupname"));

                                    // subject.getSubjectData().removeParent(Sets.newHashSet(), subjO2.orElse(sc2.getDefaults()).asSubjectReference());

                                }
                            }
                        } else {
                            context.getSource().sendMessage(Text.literal(TextColor.parse("RED") + playerInstance.getName().toString() + " is not in the gym leader group!"));

                            outerloop:

                            for (int i = 11; i <= 30; i++) {
                                if (playerInstance.hasPermissionLevel(i)) {
                                    if (args[1].equalsIgnoreCase("all")) {
                                        subject.getSubjectData().setPermission(Sets.newHashSet(), "plasmagym.gym" + i, Tristate.FALSE);
                             //           playerInstance.getSubjectData().setPermission(Sets.newHashSet(), "plasmagym.gym" + i, Tristate.FALSE);
                                    } else {
                                        break outerloop;
                                    }
                                } else {
                                    playerInstance.getSubjectData().setPermission(Sets.newHashSet(), "plasmagym.leader", Tristate.FALSE);
                                }
                            }
                        }
                    } else {
                        subject.getSubjectData().setPermission(Sets.newHashSet(), "plasmagym.gym" + gym, Tristate.FALSE);

                        outerloop:

                        for (int i = 1; i <= 32; i++) {
                            if (playerInstance.hasPermission("plasmagym.gym" + i)) {
                                if (args[1].equalsIgnoreCase("all")) {
                                    subject.getSubjectData().setPermission(Sets.newHashSet(), "plasmagym.gym" + i, Tristate.FALSE);
                                } else {
                                    break outerloop;
                                }
                            } else {
                                subject.getSubjectData().setPermission(Sets.newHashSet(), "plasmagym.leader", Tristate.FALSE);
                            }
                        }
                    }


//                     if (args[1].equalsIgnoreCase("all")) {
//                         playerInstance.sendMessage(Text.of(TextColors.RED, "You have been removed from all gyms!"));
//                         context.getSource().sendMessage(Text.literal(TextColor.parse("GREEN") +  "Removed all of " + playerInstance.getName() + "'s gym permissions!"));
//                     } else {
//                         playerInstance.sendMessage(Text.of(TextColors.DARK_GRAY, "[" + TextColor.parse("AQUA, this.plugin.getConfig().getString("config.title") + TextColor.parse("DARK_GRAY, "] ", TextSerializers.FORMATTING_CODE.deserialize(this.plugin.getConfig()
//                                 .getString(new StringBuilder("config.gym").append(gym)
//                                         .append("colour").toString()) + "TEXT").getColor(), "You have been removed from being a " + this.plugin.getConfig().getString(new StringBuilder("config.gym").append(gym).toString()) + " Leader!"));
//                         p.sendMessage(Text.of(TextColors.DARK_GRAY, "[" + TextColor.parse("AQUA, this.plugin.getConfig().getString("config.title") + TextColor.parse("DARK_GRAY, "] ", TextSerializers.FORMATTING_CODE.deserialize(this.plugin.getConfig().getString(new StringBuilder("config.gym").append(gym).append("colour").toString()) + "TEXT").getColor(), playerInstance.getName().toString() + " has successfully been removed as a " + this.plugin.getConfig().getString(new StringBuilder("config.gym").append(gym).toString()) + " leader!"));

//                     }
//                 }
//             } else {
//                 p.sendMessage(Text.of(TextColors.RED, "You do not have permission to use this command"));
//             }
//         } else if (((args[1].equalsIgnoreCase("e41")) || (args[1].equalsIgnoreCase("all") || (args[1].equalsIgnoreCase("e42")) || (args[1].equalsIgnoreCase("e43")) || (args[1].equalsIgnoreCase("e44"))) && (args[2].equals(playerInstance.getName())))) {
//             if (this.plugin.getConfig().getString("config.enablegroup").equals("True")) {
//                 // TODO Make sure getSubjectIdentifier() is correct
//                 if (subject.getSubjectData().getParents(Sets.newHashSet()).stream().filter(t -> t.getSubjectIdentifier().equals(this.plugin.getConfig().getString("config.globale4groupname"))).findAny().isPresent()) {
//                     subject.getSubjectData().setPermission(Sets.newHashSet(), "plasmagym." + args[1], Tristate.FALSE);
//                     //subject.getSubjectData().removeParent(Sets.newHashSet(), permService.getGroupSubjects().get(this.plugin.getConfig().getString("config.globale4groupname")));

//                     outerloop:

//                     for (int i = 1; i <= 4; i++) {
//                         if (playerInstance.hasPermission("plasmagym.e4" + i)) {
//                             if (args[1].equalsIgnoreCase("all")) {
//                                 subject.getSubjectData().setPermission(Sets.newHashSet(), "plasmagym.e4" + i, Tristate.FALSE);
//                             } else {
//                                 break outerloop;
//                             }
//                         } else {

//                             SubjectCollection sc2 = permService.getGroupSubjects();
//                             Optional<Subject> subjO2 = sc.getSubject(this.plugin.getConfig().getString("config.globale4groupname"));

//                             subject.getSubjectData().removeParent(Sets.newHashSet(), subjO2.orElse(sc2.getDefaults()).asSubjectReference());

//                         }
//                     }
//                 } else {
//                     p.sendMessage(Text.of(TextColors.RED, playerInstance.getName() + " is not in the e4 leader group! Removing there permission nodes."));
//                     subject.getSubjectData().setPermission(Sets.newHashSet(), "plasmagym." + args[1], Tristate.FALSE);

//                     outerloop:

//                     for (int i = 1; i <= 4; i++) {
//                         if (playerInstance.hasPermission("plasmagym.e4" + i)) {
//                             if (args[1].equalsIgnoreCase("all")) {
//                                 subject.getSubjectData().setPermission(Sets.newHashSet(), "plasmagym.gym" + i, Tristate.FALSE);
//                             } else {
//                                 break outerloop;
//                             }
//                         } else {
//                             playerInstance.getSubjectData().setPermission(Sets.newHashSet(), "plasmagym.e4leader", Tristate.FALSE);
//                         }
//                     }

//                 }
//             } else {
//                 subject.getSubjectData().setPermission(Sets.newHashSet(), "plasmagym." + args[1], Tristate.FALSE);

//                 outerloop:

//                 for (int i = 1; i <= 4; i++) {
//                     if (playerInstance.hasPermission("plasmagym.e4" + i)) {
//                         if (args[1].equalsIgnoreCase("all")) {
//                             subject.getSubjectData().setPermission(Sets.newHashSet(), "plasmagym.gym" + i, Tristate.FALSE);
//                         } else {
//                             break outerloop;
//                         }
//                     } else {
//                         subject.getSubjectData().setPermission(Sets.newHashSet(), "plasmagym.e4leader", Tristate.FALSE);
//                     }
//                 }
//             }

//             if (args[1].equalsIgnoreCase("all")) {
//                 playerInstance.sendMessage(Text.of(TextColors.RED, "You have been removed from all e4's!"));
//                 context.getSource().sendMessage(Text.literal(TextColor.parse("GREEN") +  "Removed all of " + playerInstance.getName() + "'s e4 permissions!"));
//             } else {

//                 playerInstance.sendMessage(Text.of(TextColors.DARK_GRAY, "[" + TextColor.parse("AQUA, this.plugin.getConfig().getString("config.title") + TextColor.parse("DARK_GRAY, "] ", TextSerializers.FORMATTING_CODE.deserialize(this.plugin.getConfig().getString(new StringBuilder("config.").append(args[1])
//                         .append("colour").toString()) + "TEXT").getColor(), "You have been removed from being a " + this.plugin.getConfig().getString(new StringBuilder("config.").append(args[1]).toString()) + " Leader!"));
//                 p.sendMessage(Text.of(TextColors.DARK_GRAY, "[" + TextColor.parse("AQUA, this.plugin.getConfig().getString("config.title") + TextColor.parse("DARK_GRAY, "] ", TextSerializers.FORMATTING_CODE.deserialize(this.plugin.getConfig().getString(new StringBuilder("config.").append(args[1])
//                         .append("colour").toString()) + "TEXT").getColor(), playerInstance.getName().toString() + " has successfully been removed as a " + this.plugin.getConfig().getString(new StringBuilder("config.").append(args[1]).toString()) + " leader!"));
//             }
//         } else {
//             p.sendMessage(Text.of(TextColors.RED, "Gym " + args[1] + " is not a valid gym."));
//         }
//         gymName = false;
//     }
//     if (args[0].equalsIgnoreCase("setlevel")) {

//         boolean gymName = false;
//         int gym = 0;

//         outerloop:

//         for (int g = 1; g <= 32; g++) {
//             if (this.plugin.getConfig().getString("config.gym" + g).equalsIgnoreCase(args[1])) {
//                 gymName = true;

//                 gym = g;

//                 break outerloop;
//             }

//         }

//         if (args[1].matches("(gym|GYM)[1-9]?[0-9]") || gymName == true) {


//             //int i = gym - 1;

//             if (gymName == true) {
//                 //do nothing all good.
//             } else {
//                 String gymArg = args[1].replace("gym", "");

//                 try {
//                     gym = Integer.parseInt(gymArg);
//                 } catch (NumberFormatException nfe) {
//                     p.sendMessage(Text.of(TextColors.RED, args[1] + " is not a gym!"));
//                     return CommandResult.empty();
//                 }
//             }

//             if (args[2] != null) {
//                 this.plugin.getConfig().set("config.gym" + gym + "lvlcap", args[2]);
//                 this.plugin.getConfig().save();
//                 p.sendMessage(Text.of(TextColors.GREEN + "Level cap of gym" + gym + " set!"));
//             }
//         } else {
//             p.sendMessage(Text.of(TextColors.RED, "Gym " + args[1] + " is not a valid gym."));
//         }
//         gymName = false;
//     }


// } else if ((args.length == 4) && (args[0].equalsIgnoreCase("setrule"))) {

//     boolean gymName = false;
//     int gym = 0;

//     outerloop:

//     for (int g = 1; g <= 32; g++) {
//         if (this.plugin.getConfig().getString("config.gym" + g).equalsIgnoreCase(args[1])) {
//             gymName = true;

//             gym = g;

//             break outerloop;
//         }

//     }

//     if (args[1].matches("(gym|GYM)[1-9]?[0-9]") || gymName == true) {


//         //int i = gym - 1;

//         if (gymName == true) {
//             //do nothing all good.
//         } else {
//             String gymArg = args[1].replace("gym", "");

//             try {
//                 gym = Integer.parseInt(gymArg);
//             } catch (NumberFormatException nfe) {
//                 p.sendMessage(Text.of(TextColors.RED, args[1] + " is not a gym!"));
//                 return CommandResult.empty();
//             }
//         }

//         String ruleArg = args[2];
//         int rule = 0;

//         try {
//             rule = Integer.parseInt(ruleArg);
//         } catch (NumberFormatException nfe) {
//             p.sendMessage(Text.of(TextColors.RED, args[2] + " is not an integer!"));
//             return CommandResult.empty();
//         }

//         if ((rule <= 5) && (rule >= 1)) {
//             String message = args[3].replace("_", " ");

//             this.plugin.getConfig().set("config.gym" + gym + "rule" + rule, message);
//             this.plugin.getConfig().save();
//             ;
//         }

//     } else {
//         p.sendMessage(Text.of(TextColors.RED, "Gym " + args[1] + " is not a valid gym."));
//     }
//     gymName = false;

// }
        
    return 1;
  }
//         } else if (args.length == 3) {
//             if (args[0].equalsIgnoreCase("addleader")) {

//                 boolean gymName = false;
//                 int gym = 0;

//                 outerloop:

//                 for (int g = 1; g <= 32; g++) {
//                     if (this.plugin.getConfig().getString("config.gym" + g).equalsIgnoreCase(args[1])) {
//                         gymName = true;

//                         gym = g;

//                         break outerloop;
//                     }

//                 }

//                 if (args[1].matches("(gym|GYM)[1-9]?[0-9]") || gymName == true) {


//                     //int i = gym - 1;

 
//   ))));

            return 1;
    }
))));  
            
//             if (args[0].equalsIgnoreCase("delleader")) {

//                 Player playerInstance = Sponge.getServer().getPlayer(args[2]).orElse(null);

//                 SubjectCollection sc = playerInstance.getContainingCollection();
//                 Optional<Subject> subjO = sc.getSubject(playerInstance.getIdentifier());
//                 Subject subject = subjO.orElse(sc.getDefaults());

//                 //Subject subject = playerInstance.getContainingCollection().get(playerInstance.getIdentifier());


//                 PermissionService permService = Sponge.getServiceManager().provide(PermissionService.class).get();

//                 boolean gymName = false;
//                 int gym = 0;
//                 boolean all = false;

//                 outerloop:

//                 for (int g = 1; g <= 32; g++) {
//                     if (this.plugin.getConfig().getString("config.gym" + g).equalsIgnoreCase(args[1])) {
//                         gymName = true;

//                         gym = g;

//                         break outerloop;
//                     }

//                 }

//                 if (args[1].matches("(gym|GYM)[1-9]?[0-9]") || args[1].equalsIgnoreCase("all") || gymName == true) {


//                     //int i = gym - 1;

//                     if (gymName == true) {
//                         //do nothing all good.
//                     } else if (args[1].equalsIgnoreCase("all")) {
//                         //do nothing all good
//                     } else {
//                         String gymArg = args[1].replace("gym", "");

//                         try {
//                             gym = Integer.parseInt(gymArg);
//                         } catch (NumberFormatException nfe) {
//                             p.sendMessage(Text.of(TextColors.RED, args[1] + " is not a gym!"));
//                             return CommandResult.empty();
//                         }
//                     }

//                     if (p.hasPermission("plasmagym.admin")) {
//                         if (args[2].equals(playerInstance.getName())) {

//                             if (this.plugin.getConfig().getString("config.enablegroup").equals("True")) {
//                                 // TODO Make sure getSubjectIdentifier() is correct
//                                 if (subject.getSubjectData().getParents(Sets.newHashSet()).stream().filter(t -> t.getSubjectIdentifier().equals(this.plugin.getConfig().getString("config.globalgroupname"))).findAny().isPresent()) {
//                                     if (!args[1].equalsIgnoreCase("all")) {
//                                         subject.getSubjectData().setPermission(Sets.newHashSet(), "plasmagym.gym" + gym, Tristate.FALSE);
//                                     }

//                                     outerloop:

//                                     for (int i = 1; i <= 32; i++) {
//                                         if (playerInstance.hasPermission("plasmagym.gym" + i)) {
//                                             if (args[1].equalsIgnoreCase("all")) {
//                                                 subject.getSubjectData().setPermission(Sets.newHashSet(), "plasmagym.gym" + i, Tristate.FALSE);
//                                             } else {
//                                                 break outerloop;
//                                             }
//                                         } else {

//                                             SubjectCollection sc2 = permService.getGroupSubjects();
//                                             Optional<Subject> subjO2 = sc.getSubject(this.plugin.getConfig().getString("config.globalgroupname"));

//                                             subject.getSubjectData().removeParent(Sets.newHashSet(), subjO2.orElse(sc2.getDefaults()).asSubjectReference());

//                                         }
//                                     }
//                                 } else {
//                                     p.sendMessage(Text.of(TextColors.RED, playerInstance.getName() + " is not in the gym leader group! Removing there permission nodes."));
//                                     subject.getSubjectData().setPermission(Sets.newHashSet(), "plasmagym.gym" + gym, Tristate.FALSE);

//                                     outerloop:

//                                     for (int i = 1; i <= 32; i++) {
//                                         if (playerInstance.hasPermission("plasmagym.gym" + i)) {
//                                             if (args[1].equalsIgnoreCase("all")) {
//                                                 subject.getSubjectData().setPermission(Sets.newHashSet(), "plasmagym.gym" + i, Tristate.FALSE);
//                                             } else {
//                                                 break outerloop;
//                                             }
//                                         } else {
//                                             playerInstance.getSubjectData().setPermission(Sets.newHashSet(), "plasmagym.leader", Tristate.FALSE);
//                                         }
//                                     }
//                                 }
//                             } else {
//                                 subject.getSubjectData().setPermission(Sets.newHashSet(), "plasmagym.gym" + gym, Tristate.FALSE);

//                                 outerloop:

//                                 for (int i = 1; i <= 32; i++) {
//                                     if (playerInstance.hasPermission("plasmagym.gym" + i)) {
//                                         if (args[1].equalsIgnoreCase("all")) {
//                                             subject.getSubjectData().setPermission(Sets.newHashSet(), "plasmagym.gym" + i, Tristate.FALSE);
//                                         } else {
//                                             break outerloop;
//                                         }
//                                     } else {
//                                         subject.getSubjectData().setPermission(Sets.newHashSet(), "plasmagym.leader", Tristate.FALSE);
//                                     }
//                                 }
//                             }

//                             if (args[1].equalsIgnoreCase("all")) {
//                                 playerInstance.sendMessage(Text.of(TextColors.RED, "You have been removed from all gyms!"));
//                                 context.getSource().sendMessage(Text.literal(TextColor.parse("GREEN") +  "Removed all of " + playerInstance.getName() + "'s gym permissions!"));
//                             } else {
//                                 playerInstance.sendMessage(Text.of(TextColors.DARK_GRAY, "[" + TextColor.parse("AQUA, this.plugin.getConfig().getString("config.title") + TextColor.parse("DARK_GRAY, "] ", TextSerializers.FORMATTING_CODE.deserialize(this.plugin.getConfig()
//                                         .getString(new StringBuilder("config.gym").append(gym)
//                                                 .append("colour").toString()) + "TEXT").getColor(), "You have been removed from being a " + this.plugin.getConfig().getString(new StringBuilder("config.gym").append(gym).toString()) + " Leader!"));
//                                 p.sendMessage(Text.of(TextColors.DARK_GRAY, "[" + TextColor.parse("AQUA, this.plugin.getConfig().getString("config.title") + TextColor.parse("DARK_GRAY, "] ", TextSerializers.FORMATTING_CODE.deserialize(this.plugin.getConfig().getString(new StringBuilder("config.gym").append(gym).append("colour").toString()) + "TEXT").getColor(), playerInstance.getName().toString() + " has successfully been removed as a " + this.plugin.getConfig().getString(new StringBuilder("config.gym").append(gym).toString()) + " leader!"));

//                             }
//                         }
//                     } else {
//                         p.sendMessage(Text.of(TextColors.RED, "You do not have permission to use this command"));
//                     }
//                 } else if (((args[1].equalsIgnoreCase("e41")) || (args[1].equalsIgnoreCase("all") || (args[1].equalsIgnoreCase("e42")) || (args[1].equalsIgnoreCase("e43")) || (args[1].equalsIgnoreCase("e44"))) && (args[2].equals(playerInstance.getName())))) {
//                     if (this.plugin.getConfig().getString("config.enablegroup").equals("True")) {
//                         // TODO Make sure getSubjectIdentifier() is correct
//                         if (subject.getSubjectData().getParents(Sets.newHashSet()).stream().filter(t -> t.getSubjectIdentifier().equals(this.plugin.getConfig().getString("config.globale4groupname"))).findAny().isPresent()) {
//                             subject.getSubjectData().setPermission(Sets.newHashSet(), "plasmagym." + args[1], Tristate.FALSE);
//                             //subject.getSubjectData().removeParent(Sets.newHashSet(), permService.getGroupSubjects().get(this.plugin.getConfig().getString("config.globale4groupname")));

//                             outerloop:

//                             for (int i = 1; i <= 4; i++) {
//                                 if (playerInstance.hasPermission("plasmagym.e4" + i)) {
//                                     if (args[1].equalsIgnoreCase("all")) {
//                                         subject.getSubjectData().setPermission(Sets.newHashSet(), "plasmagym.e4" + i, Tristate.FALSE);
//                                     } else {
//                                         break outerloop;
//                                     }
//                                 } else {

//                                     SubjectCollection sc2 = permService.getGroupSubjects();
//                                     Optional<Subject> subjO2 = sc.getSubject(this.plugin.getConfig().getString("config.globale4groupname"));

//                                     subject.getSubjectData().removeParent(Sets.newHashSet(), subjO2.orElse(sc2.getDefaults()).asSubjectReference());

//                                 }
//                             }
//                         } else {
//                             p.sendMessage(Text.of(TextColors.RED, playerInstance.getName() + " is not in the e4 leader group! Removing there permission nodes."));
//                             subject.getSubjectData().setPermission(Sets.newHashSet(), "plasmagym." + args[1], Tristate.FALSE);

//                             outerloop:

//                             for (int i = 1; i <= 4; i++) {
//                                 if (playerInstance.hasPermission("plasmagym.e4" + i)) {
//                                     if (args[1].equalsIgnoreCase("all")) {
//                                         subject.getSubjectData().setPermission(Sets.newHashSet(), "plasmagym.gym" + i, Tristate.FALSE);
//                                     } else {
//                                         break outerloop;
//                                     }
//                                 } else {
//                                     playerInstance.getSubjectData().setPermission(Sets.newHashSet(), "plasmagym.e4leader", Tristate.FALSE);
//                                 }
//                             }

//                         }
//                     } else {
//                         subject.getSubjectData().setPermission(Sets.newHashSet(), "plasmagym." + args[1], Tristate.FALSE);

//                         outerloop:

//                         for (int i = 1; i <= 4; i++) {
//                             if (playerInstance.hasPermission("plasmagym.e4" + i)) {
//                                 if (args[1].equalsIgnoreCase("all")) {
//                                     subject.getSubjectData().setPermission(Sets.newHashSet(), "plasmagym.gym" + i, Tristate.FALSE);
//                                 } else {
//                                     break outerloop;
//                                 }
//                             } else {
//                                 subject.getSubjectData().setPermission(Sets.newHashSet(), "plasmagym.e4leader", Tristate.FALSE);
//                             }
//                         }
//                     }

//                     if (args[1].equalsIgnoreCase("all")) {
//                         playerInstance.sendMessage(Text.of(TextColors.RED, "You have been removed from all e4's!"));
//                         context.getSource().sendMessage(Text.literal(TextColor.parse("GREEN") +  "Removed all of " + playerInstance.getName() + "'s e4 permissions!"));
//                     } else {

//                         playerInstance.sendMessage(Text.of(TextColors.DARK_GRAY, "[" + TextColor.parse("AQUA, this.plugin.getConfig().getString("config.title") + TextColor.parse("DARK_GRAY, "] ", TextSerializers.FORMATTING_CODE.deserialize(this.plugin.getConfig().getString(new StringBuilder("config.").append(args[1])
//                                 .append("colour").toString()) + "TEXT").getColor(), "You have been removed from being a " + this.plugin.getConfig().getString(new StringBuilder("config.").append(args[1]).toString()) + " Leader!"));
//                         p.sendMessage(Text.of(TextColors.DARK_GRAY, "[" + TextColor.parse("AQUA, this.plugin.getConfig().getString("config.title") + TextColor.parse("DARK_GRAY, "] ", TextSerializers.FORMATTING_CODE.deserialize(this.plugin.getConfig().getString(new StringBuilder("config.").append(args[1])
//                                 .append("colour").toString()) + "TEXT").getColor(), playerInstance.getName().toString() + " has successfully been removed as a " + this.plugin.getConfig().getString(new StringBuilder("config.").append(args[1]).toString()) + " leader!"));
//                     }
//                 } else {
//                     p.sendMessage(Text.of(TextColors.RED, "Gym " + args[1] + " is not a valid gym."));
//                 }
//                 gymName = false;
//             }
//             if (args[0].equalsIgnoreCase("setlevel")) {

//                 boolean gymName = false;
//                 int gym = 0;

//                 outerloop:

//                 for (int g = 1; g <= 32; g++) {
//                     if (this.plugin.getConfig().getString("config.gym" + g).equalsIgnoreCase(args[1])) {
//                         gymName = true;

//                         gym = g;

//                         break outerloop;
//                     }

//                 }

//                 if (args[1].matches("(gym|GYM)[1-9]?[0-9]") || gymName == true) {


//                     //int i = gym - 1;

//                     if (gymName == true) {
//                         //do nothing all good.
//                     } else {
//                         String gymArg = args[1].replace("gym", "");

//                         try {
//                             gym = Integer.parseInt(gymArg);
//                         } catch (NumberFormatException nfe) {
//                             p.sendMessage(Text.of(TextColors.RED, args[1] + " is not a gym!"));
//                             return CommandResult.empty();
//                         }
//                     }

//                     if (args[2] != null) {
//                         this.plugin.getConfig().set("config.gym" + gym + "lvlcap", args[2]);
//                         this.plugin.getConfig().save();
//                         p.sendMessage(Text.of(TextColors.GREEN + "Level cap of gym" + gym + " set!"));
//                     }
//                 } else {
//                     p.sendMessage(Text.of(TextColors.RED, "Gym " + args[1] + " is not a valid gym."));
//                 }
//                 gymName = false;
//             }


//         } else if ((args.length == 4) && (args[0].equalsIgnoreCase("setrule"))) {

//             boolean gymName = false;
//             int gym = 0;

//             outerloop:

//             for (int g = 1; g <= 32; g++) {
//                 if (this.plugin.getConfig().getString("config.gym" + g).equalsIgnoreCase(args[1])) {
//                     gymName = true;

//                     gym = g;

//                     break outerloop;
//                 }

//             }

//             if (args[1].matches("(gym|GYM)[1-9]?[0-9]") || gymName == true) {


//                 //int i = gym - 1;

//                 if (gymName == true) {
//                     //do nothing all good.
//                 } else {
//                     String gymArg = args[1].replace("gym", "");

//                     try {
//                         gym = Integer.parseInt(gymArg);
//                     } catch (NumberFormatException nfe) {
//                         p.sendMessage(Text.of(TextColors.RED, args[1] + " is not a gym!"));
//                         return CommandResult.empty();
//                     }
//                 }

//                 String ruleArg = args[2];
//                 int rule = 0;

//                 try {
//                     rule = Integer.parseInt(ruleArg);
//                 } catch (NumberFormatException nfe) {
//                     p.sendMessage(Text.of(TextColors.RED, args[2] + " is not an integer!"));
//                     return CommandResult.empty();
//                 }

//                 if ((rule <= 5) && (rule >= 1)) {
//                     String message = args[3].replace("_", " ");

//                     this.plugin.getConfig().set("config.gym" + gym + "rule" + rule, message);
//                     this.plugin.getConfig().save();
//                     ;
//                 }

//             } else {
//                 p.sendMessage(Text.of(TextColors.RED, "Gym " + args[1] + " is not a valid gym."));
//             }
//             gymName = false;

//         }

//         return CommandResult.empty();
    
     
// };
    

}}
