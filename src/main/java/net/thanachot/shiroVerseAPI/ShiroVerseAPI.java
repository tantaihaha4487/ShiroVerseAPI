package net.thanachot.shiroVerseAPI;

import org.bukkit.plugin.java.JavaPlugin;

public final class ShiroVerseAPI extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("ShiroVerseAPI Enabled");
    }

    @Override
    public void onDisable() {
        getLogger().info("ShiroVerseAPI Disabled");
    }
}
