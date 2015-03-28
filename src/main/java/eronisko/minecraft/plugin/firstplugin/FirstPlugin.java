package eronisko.minecraft.plugin.firstplugin;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class FirstPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Broadcast a messages to all players on the server
        for (Player player: Bukkit.getServer().getOnlinePlayers()) {
            player.sendMessage("Hello, " + player.getDisplayName());
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("shout")) {
            if (!(sender instanceof Player)) {
                // This command can only be run by a player.
                return false;
            }

            if (args.length < 1) {
                sender.sendMessage("What do you want to shout out?");
                return false;
            }

            Player player = (Player) sender;

            // Broadcast a messages to all players on the server
            for (Player receiver: Bukkit.getServer().getOnlinePlayers()) {
                String message = StringUtils.join(args, " ");
                receiver.sendMessage(player.getDisplayName() + ": " + message);
            }
            return true;

        }
        return false;
    }
}
