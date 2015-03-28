package eronisko.minecraft.plugin.firstplugin;

import org.bukkit.Bukkit;
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
}
