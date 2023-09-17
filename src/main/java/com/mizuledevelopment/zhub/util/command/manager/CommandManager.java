/*
 * This file is part of zHub, licensed under the GPLv3 License.
 *
 * Copyright (c) 2023 Mizule Development
 * Copyright (c) 2023 contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.mizuledevelopment.zhub.util.command.manager;

import com.mizuledevelopment.zhub.util.command.Command;
import com.mizuledevelopment.zhub.util.command.adapter.CommandAdapter;
import org.bukkit.command.PluginCommand;

import java.util.ArrayList;
import java.util.List;

public class CommandManager {

    private final PluginCommand pluginCommand;
    private final List<Command> commands = new ArrayList<>();

    public CommandManager(final PluginCommand pluginCommand) {
        this.pluginCommand = pluginCommand;
    }

    public void addSubCommand(final Command command) {
        this.commands.add(command);
    }

    public void setCommand(final List<Command> commandList) {
        this.commands.addAll(commandList);
    }

    public void registerCommands() {
        final CommandAdapter adapter = new CommandAdapter(this.commands);
        this.pluginCommand.setExecutor(adapter);
        this.pluginCommand.setTabCompleter(adapter);
    }

    public List<Command> getRegisteredSubCommands() {
        return this.commands;
    }
}
