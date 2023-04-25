package starter.Ckay.gym;

import starter.Ckay.gym.config.PluginConfiguration;
import net.minecraft.world.event.GameEvent.Message;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.item.v1.FabricItem;
import net.fabricmc.fabric.api.message.v1.ServerMessageEvents;
import net.fabricmc.fabric.api.message.v1.ServerMessageEvents.CommandMessage;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.MessageCommand;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import com.mojang.brigadier.tree.CommandNode;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.server.world.*;
import net.minecraft.world.World;
import net.fabricmc.fabric.api.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SignListener {
    private PlasmaGym plugin;

    public SignListener(PlasmaGym plugin) {
        this.plugin = plugin;
    }

    public boolean setStatusSign(Location<World> l, String gym) {
        PluginConfiguration config = this.plugin.settings.getSigns();
        List<String> list = config.getStringList("status");

        if (list == null) {
            list = new ArrayList<>();
        }

        list.add(l.getBlockX() + ", " + l.getBlockY() + ", " + l.getBlockZ() + ", " + l.getExtent().getName() + ", " + gym);
        config.set("status", list);
        config.save();

        PlasmaGym.getInstance().updateSigns();
        return true;
    }

    public boolean setLeaderSign(Location<World> l, String gym, int number) {
        PluginConfiguration config = this.plugin.settings.getSigns();
        List<String> list = config.getStringList("leader");

        if (list == null) {
            list = new ArrayList<>();
        }

        list.add(l.getBlockX() + ", " + l.getBlockY() + ", " + l.getBlockZ() + ", " + l.getExtent().getName() + ", " + gym + ", " + number);

        config.set("leader", list);
        config.save();

        PlasmaGym.getInstance().updateSigns();
        return true;
    }

    @Listener
    public void onSignChange(ChangeSignEvent e, @Root Player p) {
        List<String> line = e.getText().lines().get()
                .stream()
                .map(t -> t.toPlain())
                .collect(Collectors.toList());

        if ((line.get(0).equalsIgnoreCase("[GymStatus]")) && (p.hasPermission("plasmagym.admin"))) {
            if (line.get(1).startsWith("gym")) {
                String gym = line.get(1).replace("gym", "");
                int gymNumber = 0;
                try {
                    gymNumber = Integer.parseInt(gym);
                } catch (NumberFormatException ex) {
                    p.sendMessage(Text.of("Use \"gym + number\" on line 2!"));
                }

                setStatusSign(e.getTargetTile().getLocation(), Integer.toString(gymNumber));
            }
        } else if ((line.get(0).equalsIgnoreCase("[GymLeaders]")) && (p.hasPermission("plasmagym.admin"))) {
            setLeaderSign(e.getTargetTile().getLocation(), line.get(1), 1);
        } else if ((line.get(0).startsWith("[GymLeaders")) && (line.get(0).endsWith("]")) && (p.hasPermission("plasmagym.admin"))) {
            try {
                setLeaderSign(e.getTargetTile().getLocation(), line.get(1), Integer.parseInt(line.get(0).replace("[GymLeaders", "").replace("]", "")));
            } catch (NumberFormatException ex) {
                p.sendMessage(Text.of(TextColors.RED, "Use [GymLeaders + number] on line 1!"));
            }
        }
    }
}
