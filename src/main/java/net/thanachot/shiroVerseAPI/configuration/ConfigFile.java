package net.thanachot.shiroVerseAPI.configuration;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

/**
 * Manages plugin configuration files.
 */
public class ConfigFile {

    private final Plugin plugin;
    private final File configFile;
    private YamlConfiguration yamlConfiguration;

    /**
     * Creates a new ConfigManager.
     *
     * @param plugin   The plugin instance.
     * @param fileName The name of the configuration file.
     */
    public ConfigFile(Plugin plugin, String fileName) {
        this.plugin = plugin;
        this.configFile = getPluginConfigPath(plugin, fileName);
        this.yamlConfiguration = YamlConfiguration.loadConfiguration(configFile);

        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdirs();
        }
    }

    /**
     * Saves the default configuration file from the plugin's resources.
     * If the file does not exist, it will be created and the in-memory configuration will be reloaded.
     *
     * @return The ConfigFile instance.
     */
    public ConfigFile addDefaultConfig() {
        if (!configFile.exists()) {
            plugin.saveResource(configFile.getName(), false);
            this.yamlConfiguration = YamlConfiguration.loadConfiguration(configFile);
        }
        return this;
    }

    public ConfigFile save() throws IOException {
        getConfig().save(configFile);
        return this;
    }

    /**
     * Reloads the configuration file.
     *
     * @return The ConfigManager instance.
     * @throws IOException                   if an I/O error occurs.
     * @throws InvalidConfigurationException if the configuration is invalid.
     */
    public ConfigFile reload() throws IOException, InvalidConfigurationException {
        getConfig().load(configFile);
        return this;
    }


    /**
     * Reloads the configuration file.
     *
     * @param callback The callback to run after reloading the configuration file.
     * @return The ConfigManager instance.
     * @throws IOException                   if an I/O error occurs.
     * @throws InvalidConfigurationException if the configuration is invalid.
     */
    public ConfigFile reload(Runnable callback) throws IOException, InvalidConfigurationException {
        getConfig().load(configFile);

        if (callback != null) {
            callback.run();
        }

        return this;
    }


    /**
     * Gets the YamlConfiguration instance.
     *
     * @return The YamlConfiguration instance.
     */
    public YamlConfiguration getConfig() {
        return yamlConfiguration;
    }


    /**
     * Sets the YamlConfiguration instance.
     *
     * @param yamlConfiguration The YamlConfiguration instance.
     */
    public void setYamlConfiguration(YamlConfiguration yamlConfiguration) {
        this.yamlConfiguration = yamlConfiguration;
    }

    /**
     * Gets the path to the plugin's configuration file.
     *
     * @param plugin         The plugin instance.
     * @param configFileName The name of the configuration file.
     * @return The path to the plugin's configuration file.
     */
    public File getPluginConfigPath(Plugin plugin, String configFileName) {
        return new File(plugin.getDataFolder(), configFileName);
    }


}
