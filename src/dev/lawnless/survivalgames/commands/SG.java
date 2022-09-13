package dev.lawnless.survivalgames.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SG implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("sg")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (args.length == 0) {
                    player.sendMessage("§6/sg join <arena name>");
                    player.sendMessage("§6/sg leave");
                }
            } else {
                sender.sendMessage("§cYou need to be player!");
            }
        }
        return false;
    }
}
