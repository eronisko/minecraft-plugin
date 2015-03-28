package eronisko.minecraft.plugin.firstplugin;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Set;

public class FirstPlugin extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Broadcast a messages to all players on the server
        for (Player player: Bukkit.getServer().getOnlinePlayers()) {
            player.sendMessage("Hello, " + player.getDisplayName());
        }

        //Register events
        getServer().getPluginManager().registerEvents(this, this);
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

    @EventHandler
    public void onPlayerInteractBlock(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (player.getItemInHand().getType() == Material.STICK) {
            // Creates a bolt of lightning at a given location. In this case, that location is where the player is looking.
            player.getWorld().strikeLightning(player.getTargetBlock((Set<Material>) null, 10).getLocation());
        }
    }
}
