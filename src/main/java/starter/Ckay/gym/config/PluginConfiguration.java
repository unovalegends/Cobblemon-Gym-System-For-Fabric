package starter.Ckay.gym.config;

import com.google.common.collect.Lists;
import starter.Ckay.gym.PlasmaGym;

import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;
import org.spongepowered.configurate.yaml.YamlConfigurationLoader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple utility class to manage configurations with a file associated to them.
 */
public class PluginConfiguration {
    private File file;
    private ConfigurationNode config;
    private YamlConfigurationLoader configurationLoader;
    //private LoaderOptions configurationLoader;

    public PluginConfiguration(File file) {
        this.file = file;
        this.load();
    }

    public PluginConfiguration(PlasmaGym plugin, String name) {
        this(new File(plugin.getConfigDir().toFile(), name));
    }

    public void load() {
        try {
            if (!file.isFile()) {
                if (file.getParentFile() != null) {
                    file.getParentFile().mkdirs();
                }
                    
                file.createNewFile();
            }

        configurationLoader = YamlConfigurationLoader.builder().file(file).build();
        config = configurationLoader.load();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void save() {
        try {
            configurationLoader.save(config);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ConfigurationNode getConfig() {
        return config;
    }

    public YamlConfigurationLoader getConfigurationLoader() {
        return configurationLoader;
    }

    public File getFile() {
        return file;
    }

    public String getFileName() {
        return file.getName();
    }

    public int getInt(String path) {
        return this.config.node((Object[]) path.split("\\.")).getInt();
    }

    public double getDouble(String path) {
        return this.config.node((Object[]) path.split("\\.")).getDouble();
    }

    public String getString(String path) {
        return this.config.node((Object[]) path.split("\\.")).getString();
    }

    public boolean getBoolean(String path) {
        return this.config.node((Object[]) path.split("\\.")).getBoolean();
    }


    public Object get(String path) throws SerializationException {
        return this.config.node((Object[]) path.split("\\.")).get(String.class);
    }

    public void set(String path, Object val) throws SerializationException {
        this.config.node((Object[]) path.split("\\.")).set(val);
    }

    public List<String> getStringList(String path) {
        List<String> list = Lists.newArrayList();

        try {
            list = new ArrayList<>(this.config.node((Object[]) path.split("\\.")).getList(String.class));
        } catch (SerializationException e) {
            ;
        }

        return list;
    }
}