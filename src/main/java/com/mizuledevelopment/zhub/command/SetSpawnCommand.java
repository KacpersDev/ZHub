package com.mizuledevelopment.zhub.command;

import cloud.commandframework.CommandManager;
import cloud.commandframework.meta.CommandMeta;
import com.mizuledevelopment.zhub.config.impl.ConfigFile;
import com.mizuledevelopment.zhub.util.color.Color;
import com.mizuledevelopment.zhub.zHub;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;

public class SetSpawnCommand extends BaseCommand {

    private final ConfigFile config;
    private final ConfigFile messages;

    public SetSpawnCommand(final zHub plugin) {
        this.config = plugin.config("config");
        this.messages = plugin.config("messages");
    }

    @Override
    public void register(final CommandManager<CommandSender> commandManager) {
        commandManager.command(commandManager.commandBuilder("setspawn")
            .permission("zhub.command.setspawn")
            .meta(CommandMeta.DESCRIPTION, "Set the spawn")
            .senderType(Player.class)
            .handler(context -> {
                final Player player = (Player) context.getSender();
                final ConfigurationNode section = this.config.section("spawn.location");
                final var location = player.getLocation();
                try {
                    section.node("worldId").set(location.getWorld().getUID().toString());
                    section.node("world").set(location.getWorld().getName());
                    section.node("x").set(location.getBlockX());
                    section.node("y").set(location.getBlockY());
                    section.node("z").set(location.getBlockZ());
                    section.node("pitch").set(location.getPitch());
                    section.node("yaw").set(location.getYaw());
                    this.config.save();
                } catch (SerializationException e) {
                    throw new RuntimeException(e);
                }
                player.sendMessage(Color.translate(this.messages.getString("messages.setspawn", "set spawn")));
            })
        );
    }
}
