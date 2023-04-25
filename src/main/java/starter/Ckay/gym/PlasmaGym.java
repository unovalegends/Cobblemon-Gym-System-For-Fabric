package starter.Ckay.gym;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.inject.Inject;
import starter.Ckay.gym.commands.CommandListener;
import starter.Ckay.gym.commands.PlasmaGymAdminCommand;
import starter.Ckay.gym.config.PluginConfiguration;
import starter.Ckay.gym.config.SettingsManager;
import starter.Ckay.gym.scoreboard.ScoreboardManager;
import starter.Ckay.gym.utils.Utils;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.block.BlockSnapshot;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.block.entity.BlockEntity;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.config.ConfigDir;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.data.type.HandTypes;
import org.spongepowered.api.effect.sound.SoundCategories;
import org.spongepowered.api.effect.sound.SoundTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.block.InteractBlockEvent;
import org.spongepowered.api.event.filter.cause.Root;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.event.item.inventory.InteractInventoryEvent;
import org.spongepowered.api.event.network.ClientConnectionEvent;
import org.spongepowered.api.event.service.ChangeServiceProviderEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.Inventory;
import org.spongepowered.api.item.inventory.InventoryArchetypes;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.item.inventory.property.InventoryTitle;
import org.spongepowered.plugin.metadata.*;
import org.spongepowered.plugin.PluginContainer;
import org.spongepowered.api.scheduler.Task;
import org.spongepowered.api.service.economy.EconomyService;
import org.spongepowered.api.data.type;
import org.spongepowered.api.text.channel.MessageChannel;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.text.format.TextStyles;
import org.spongepowered.api.text.serializer.TextSerializers;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.*;

@Plugin(id = "plasmagym", name = "PlasmaGym", version = "2.7")
public class PlasmaGym {
    public static Inventory inventory;
    public static Map<String, Inventory> myInventories = Maps.newHashMap();
    private static PlasmaGym instance;
    public final Map<Player, List<BlockSnapshot>> hashmap = Maps.newHashMap();
    public Map<Integer, List<UUID>> queues;
    public Map<Integer, List<UUID>> inArena;
    public List<UUID> inPrest;
    public Map<Integer, List<UUID>> cooldownGym;
    public Map<Integer, Map<UUID, Integer>> cooldownTime;
    public Map<Integer, Map<UUID, Task.Builder>> cooldownTask;
    public Map<Player, ItemStack[]> invsave = Maps.newHashMap();
    public String enablegym1;
    public String enable2;
    public String enable3;
    public String enable4;
    public String enable5;
    public String enable6;
    public String enable7;
    public String enable8;
    public String enable9;
    public String enablegym10;
    public String enablegym11;
    public String enablegym12;
    public String enablegym13;
    public String enablegym14;
    public String enablegym15;
    public String enablegym16;
    public String enablegym17;
    public String enablegym18;
    public String enablegym19;
    public String enable20;
    public String enable21;
    public String enable22;
    public String enable23;
    public String enable24;
    public String enable25;
    public String enable26;
    public String enable27;
    public String enable28;
    public String enable29;
    public String enable30;
    public String enable31;
    public String enable32;
    public String enablee4;
    public String enableGymHeal;
    public Date now;
    public SimpleDateFormat format;
    public SettingsManager settings;
    public List<String> hasOpen = Lists.newArrayList();
    Location<World> gym1loc,
            iceloc,
            grassloc,
            waterloc,
            electricloc,
            fireloc,
            poisonloc,
            psychicloc,
            rockleaderloc,
            iceleaderloc,
            grassleaderloc,
            waterleaderloc,
            electricleaderloc,
            fireleaderloc,
            poisonleaderloc, psychicleaderloc;
    boolean spawnperm;
    boolean tpaperm;
    boolean homeperm;
    boolean backperm;
    boolean warpperm;
    boolean tpacceptperm;
    boolean randomtpperm;
    boolean showcase;
    @Inject
    private Logger logger;
    @Inject
    @ConfigDir(sharedRoot = false)
    private Path configDir;
    @Inject
    private PluginContainer container;
    private PluginConfiguration config;
    private PluginConfiguration players;
    private EconomyService economy;

    public static PlasmaGym getInstance() {
        return instance;
    }

    public Logger getLogger() {
        return logger;
    }

    public Path getConfigDir() {
        return configDir;
    }

    public PluginContainer getContainer() {
        return container;
    }

    public PluginConfiguration getConfig() {
        return config;
    }

    public PluginConfiguration getPlayersConfig() {
        return players;
    }

    public EconomyService getEconomy() {
        return economy;
    }

    @Listener
    public void onEnable(GameInitializationEvent event) {
        instance = this;

        initVars();

        // Initialize Inventory using Builder
        inventory = Inventory.builder()
                .of(InventoryArchetypes.DOUBLE_CHEST)
                .property(InventoryTitle.PROPERTY_NAME, InventoryTitle.of(Text.of(TextColors.GREEN, "Badges!")))
                .build(this);

        this.settings.setup(this);
        this.settings.setupBadges(this);
        this.settings.setupLog(this);
        this.settings.setupExtra(this);
        this.settings.setupSigns(this);

        this.queues = new HashMap<>();

        for (int i = 1; i <= 32; i++) {
            this.queues.put(Integer.valueOf(i), new ArrayList<>());
        }

        this.cooldownGym = new HashMap<>();

        for (int i = 1; i <= 32; i++) {
            this.cooldownGym.put(Integer.valueOf(i), new ArrayList<>());
        }

        this.cooldownTask = new HashMap<>();

        for (int i = 1; i <= 32; i++) {
            this.cooldownTask.put(Integer.valueOf(i), new HashMap<>());
        }

        this.cooldownTime = new HashMap<>();

        for (int i = 1; i <= 32; i++) {
            this.cooldownTime.put(Integer.valueOf(i), new HashMap<>());
        }

        this.inArena = new HashMap<>();

        for (int i = 1; i <= 32; i++) {
            this.inArena.put(Integer.valueOf(i), new ArrayList<>());
        }

        this.inPrest = new ArrayList<>();

        this.settings.saveBadges();
        this.settings.saveExtras();
        this.settings.saveData();
        this.settings.saveLogs();

        // Register Listeners
        Sponge.getEventManager().registerListeners(this, new CommandListener(this));
        Sponge.getEventManager().registerListeners(this, new SignListener(this));

        // Register Commands
        CommandSpec pixelGymCommandSpec = CommandSpec.builder()
                .description(Text.of("PlasmaGym Command"))
                .permission("plasmagym.admin")
                .arguments(GenericArguments.optional(GenericArguments.remainingJoinedStrings(Text.of("args"))))
                .executor(new PlasmaGymAdminCommand(this))
                .build();

        Sponge.getCommandManager().register(this, pixelGymCommandSpec, Lists.newArrayList("plasmagym"));

        Sponge.getScheduler().createTaskBuilder().execute(() -> {
            PlasmaGym.this.updateSigns();
        }).intervalTicks(20L).submit(this);

        if (getConfig().getString("config.enableshowcase").equalsIgnoreCase("True")) {
            showcase = true;
        } else if (getConfig().getString("config.enableshowcase").equalsIgnoreCase("False")) {
            showcase = false;
        }

//		for (int i = 1; i <= 32; i++) {
//    		
//    		int u = i +1;
//
//    		if (this.getConfig().getString("config.gym"+i+"enabled").equalsIgnoreCase("True")) {
//    			if (i == 32) {
//    				if ((this.settings.getBadge().get("Players." + p.getUniqueId() +".Badges.gym"+i) != null)) {
//    					if (this.settings.getBadge().get("Players." + p.getUniqueId() + ".Badges.gym"+i).equals("Won")) {
//        				//ParticleEffect.FIREWORKS_SPARK.display(1, 1, 1, 0, 10, p.getLocation(), 100);
//        				//ParticleEffect.FIREWORKS_SPARK.display(offsetX, offsetY, offsetZ, speed, amount, center, range);
//        				}
//    				}
//    				
//    			}
//    			else {
//    			if (this.getConfig().getString("config.gym"+u+"enabled").equalsIgnoreCase("False")) {
//    				
//    				if ((this.settings.getBadge().get("Players." + p.getUniqueId() +".Badges.gym"+i) != null)) {
//    					if (this.settings.getBadge().get("Players." + p.getUniqueId() + ".Badges.gym"+i).equals("Won")) {
//    						//ParticleEffect.FIREWORKS_SPARK.display(1, 1, 1, 0, 10, p.getLocation(), 100);
//    						}
//    					}
//    				}
//    			
//    			}
//    		}
//
//    	}

        for (int i = 1; i <= 32; i++) {
            getConfig().set("config.gym" + i + "stat", "Closed");
        }

    }


    private void initVars() {
        this.spawnperm = false;
        this.tpaperm = false;
        this.homeperm = false;
        this.backperm = false;
        this.warpperm = false;
        this.tpacceptperm = false;
        this.randomtpperm = false;

        Utils.saveResource(this, "config.yml", false);
        this.config = new PluginConfiguration(this, "config.yml");

        this.settings = SettingsManager.getInstance();

        this.now = new Date();
        this.format = new SimpleDateFormat("dd-MM-yyyy (HH:mm:ss)");

        this.enablegym1 = this.getConfig().getString("config.gym1enabled");
        this.enable2 = this.getConfig().getString("config.gym2enabled");
        this.enable3 = this.getConfig().getString("config.gym3enabled");
        this.enable4 = this.getConfig().getString("config.gym4enabled");
        this.enable5 = this.getConfig().getString("config.gym5enabled");
        this.enable6 = this.getConfig().getString("config.gym6enabled");
        this.enable7 = this.getConfig().getString("config.gym7enabled");
        this.enable8 = this.getConfig().getString("config.gym8enabled");
        this.enable9 = this.getConfig().getString("config.gym9enabled");
        this.enablegym10 = this.getConfig().getString("config.gym10enabled");
        this.enablegym11 = this.getConfig().getString("config.gym11enabled");
        this.enablegym12 = this.getConfig().getString("config.gym12enabled");
        this.enablegym13 = this.getConfig().getString("config.gym13enabled");
        this.enablegym14 = this.getConfig().getString("config.gym14enabled");
        this.enablegym15 = this.getConfig().getString("config.gym15enabled");
        this.enablegym16 = this.getConfig().getString("config.gym16enabled");
        this.enablegym17 = this.getConfig().getString("config.gym17enabled");
        this.enablegym18 = this.getConfig().getString("config.gym18enabled");
        this.enablegym19 = this.getConfig().getString("config.gym19enabled");
        this.enable20 = this.getConfig().getString("config.gym20enabled");
        this.enable21 = this.getConfig().getString("config.gym21enabled");
        this.enable22 = this.getConfig().getString("config.gym22enabled");
        this.enable23 = this.getConfig().getString("config.gym23enabled");
        this.enable24 = this.getConfig().getString("config.gym24enabled");
        this.enable25 = this.getConfig().getString("config.gym25enabled");
        this.enable26 = this.getConfig().getString("config.gym26enabled");
        this.enable27 = this.getConfig().getString("config.gym27enabled");
        this.enable28 = this.getConfig().getString("config.gym28enabled");
        this.enable29 = this.getConfig().getString("config.gym29enabled");
        this.enable30 = this.getConfig().getString("config.gym30enabled");
        this.enable31 = this.getConfig().getString("config.gym31enabled");
        this.enable32 = this.getConfig().getString("config.gym32enabled");
        this.enableGymHeal = this.getConfig().getString("config.gymhealing");
        this.enablee4 = this.getConfig().getString("config.e4enabled");
    }

    @Listener
    public void onGameStarted(GameStartedServerEvent event) {
        new ScoreboardManager(this);
    }

    @Listener
    public void setupEconomy(ChangeServiceProviderEvent event) {
        if (event.getNewProviderRegistration().getService().equals(EconomyService.class)) {
            economy = (EconomyService) event.getNewProvider();
        }
    }

    public boolean removeSign(Location<World> l) {
        PluginConfiguration config = this.settings.getSigns();
        List<String> list = config.getStringList("status");

        if (list == null) {
            return false;
        }

        for (int i = 0; i < list.size(); i++) {
            String s = (String) list.get(i);
            String[] a = s.split(", ");
            int x = Integer.parseInt(a[0]);
            int y = Integer.parseInt(a[1]);
            int z = Integer.parseInt(a[2]);
            World w = Sponge.server().getWorld(a[3]).orElse(null);

            if ((l.getExtent() == w) && (l.getBlockX() == x) && (l.getBlockY() == y) && (l.getBlockZ() == z)) {
                list.remove(i);
                config.save();

                return true;
            }
        }

        List<String> list2 = config.getStringList("leader");

        if (list2 == null) {
            return false;
        }

        for (int i = 0; i < list.size(); i++) {
            String s = (String) list2.get(i);
            String[] a = s.split(", ");
            int x = Integer.parseInt(a[0]);
            int y = Integer.parseInt(a[1]);
            int z = Integer.parseInt(a[2]);
            World w = Sponge.getServer().getWorld(a[3]).orElse(null);

            if ((l.getExtent() == w) && (l.getBlockX() == x) && (l.getBlockY() == y) && (l.getBlockZ() == z)) {
                list2.remove(i);
                config.save();

                return true;
            }
        }

        return false;
    }

    public void updateSigns() {
        PluginConfiguration config = this.settings.getSigns();
        List<String> list = config.getStringList("status");

        if ((list == null) || (list.size() == 0)) {
            return;
        }

        for (String s : list) {
            String[] a = s.split(", ");
            int x = Integer.parseInt(a[0]);
            int y = Integer.parseInt(a[1]);
            int z = Integer.parseInt(a[2]);
            World w = Sponge.getServer().getWorld(a[3]).orElse(null);

            String gym = "gym" + a[4];
            Location<World> l = new Location<>(w, x, y, z);

            if ((l.getBlock().getType() != BlockTypes.WALL_SIGN) && (l.getBlock().getType() != BlockTypes.STANDING_SIGN)) {
                removeSign(l);
            } else {
                Sign sign = (Sign) l.getTileEntity().get();
                List<Text> lines = sign.get(Keys.SIGN_LINES).get();

                lines.set(0, TextSerializers.FORMATTING_CODE.deserialize(getConfig().getString(new StringBuilder("config.").append(gym).append("colour").toString()) + getConfig()
                        .getString(new StringBuilder("config.").append(gym).toString()) + " Gym"));

                if (getConfig().getString("config." + gym + "stat").equalsIgnoreCase("Open")) {
                    lines.set(1, TextSerializers.FORMATTING_CODE.deserialize("&3" + getConfig().getString(new StringBuilder("config.").append(gym).append("stat").toString())));
                } else if (getConfig().getString("config." + gym + "stat").equalsIgnoreCase("Closed")) {
                    lines.set(1, TextSerializers.FORMATTING_CODE.deserialize("&4" + getConfig().getString(new StringBuilder("config.").append(gym).append("stat").toString())));
                }

                //lines.set(1, TextSerializers.FORMATTING_CODE.deserialize((getConfig().getString("config." + gym + "stat").equalsIgnoreCase("Open") ? TextColors.GREEN : TextColors.RED) + getConfig().getString(new StringBuilder("config.").append(gym).append("stat").toString())));


                lines.set(2, TextSerializers.FORMATTING_CODE.deserialize("&9Level Cap: " + getConfig().getString(new StringBuilder("config.").append(gym).append("lvlcap").toString())));

                sign.offer(Keys.SIGN_LINES, lines);
            }
        }

        List<String> list2 = config.getStringList("leader");

        if (list2 == null) {
            return;
        }
        for (String s : list2) {
            String[] a = s.split(", ");
            int x = Integer.parseInt(a[0]);
            int y = Integer.parseInt(a[1]);
            int z = Integer.parseInt(a[2]);
            World w = Sponge.getServer().getWorld(a[3]).orElse(null);
            String gym = "gym" + a[4];
            Location<World> l = new Location<>(w, x, y, z);

            if ((l.getBlock().getType() != BlockTypes.WALL_SIGN) && (l.getBlock().getType() != BlockTypes.STANDING_SIGN)) {
                removeSign(l);
            } else if (getSignNumber(l) == 1) {
                System.out.println("UpdateSign: Sign (1) == 1");
                List<Sign> signsList = new ArrayList<>();

                for (String signs : list2) {
                    String[] a2 = signs.split(", ");
                    int x2 = Integer.parseInt(a2[0]);
                    int y2 = Integer.parseInt(a2[1]);
                    int z2 = Integer.parseInt(a2[2]);
                    World w2 = Sponge.getServer().getWorld(a2[3]).orElse(null);
                    String gym2 = "gym" + a2[4];
                    Location<World> l2 = new Location<>(w2, x2, y2, z2);

                    if ((l.getBlock().getType() != BlockTypes.WALL_SIGN) && (l.getBlock().getType() != BlockTypes.STANDING_SIGN)) {
                        removeSign(l);
                    } else if (gym2.equalsIgnoreCase(gym)) {
                        // System.out.println("UpdateSign: gym2 = gym");
                        // System.out.println("Distance:" + l.getPosition().distance(l2.getPosition()));

                        if (l.getPosition().distance(l2.getPosition()) < 1.1D) {
                            // System.out.println("SNumber: " + getSignNumber(l2));
                            if (getSignNumber(l2) > 1) {
                                // System.out.println("SNumber2: " + getSignNumber(l2));
                                // System.out.println("UpdateSign: Sign Number (l2) > 1");
                                Sign sign2 = (Sign) l.getTileEntity().get();
                                List<Text> lines = sign2.get(Keys.SIGN_LINES).get();

                                lines.set(0, TextSerializers.FORMATTING_CODE.deserialize(getConfig()
                                        .getString(new StringBuilder("config.").append(gym).append("colour").toString()) + getConfig()
                                        .getString(new StringBuilder("config.").append(gym).toString()) + " Leaders"));

                                signsList.add(sign2);
                                sign2.offer(Keys.SIGN_LINES, lines);
                            }
                        }
                    }
                }
                Sign[] signsArray = new Sign[signsList.size()];
                for (int i = 0; i < signsList.size(); i++) {
                    signsArray[i] = ((Sign) signsList.get(i));
                }
                updateLeaderSign(l, gym, signsArray);
            }
        }
    }

    public int getSignNumber(Location<World> l) {
        PluginConfiguration config = this.settings.getSigns();
        List<String> list = config.getStringList("leader");

        if ((list == null) || (list.size() == 0)) {
            return 0;
        }

        for (String s : list) {
            String[] a = s.split(", ");
            int x = Integer.parseInt(a[0]);
            int y = Integer.parseInt(a[1]);
            int z = Integer.parseInt(a[2]);
            World w = Sponge.getServer().getWorld(a[3]).orElse(null);

            if ((l.getExtent() == w) && (l.getBlockX() == x) && (l.getBlockY() == y) && (l.getBlockZ() == z)) {
                return Integer.parseInt(a[5]);
            }
        }
        return 0;
    }

    public void updateLeaderSign(Location<World> l, String gym, Sign[] otherSigns) {
        int line = 1;
        int sign = 1;
        Sign firstSign = (Sign) l.getTileEntity().get();
        Sign selectedSign = firstSign;
        List<Text> lines = firstSign.get(Keys.SIGN_LINES).get();

        if ((otherSigns == null) || (otherSigns.length == 0)) {
            for (Player staff : Sponge.getServer().getOnlinePlayers()) {
                if ((otherSigns == null) || (otherSigns.length == 0)) {
                    if (line > 3) {
                        break;
                    }

                    if (staff.hasPermission("plasmagym" + lines.get(1).toPlain())) {
                        lines.set(line, Text.of(staff.getName().toString()));
                        line++;
                    }

                    firstSign.offer(Keys.SIGN_LINES, lines);
                }
            }
        } else {
            for (Player staff : Sponge.getServer().getOnlinePlayers()) {
                if (line > 3) {
                    line = 0;
                    sign++;

                    // selectedSign.update();
                    selectedSign = null;

                    Sign[] arrayOfSign;
                    int j = (arrayOfSign = otherSigns).length;
                    for (int i = 0; i < j; i++) {
                        Sign s = arrayOfSign[i];
                        if (getSignNumber(s.getLocation()) == sign) {
                            selectedSign = s;
                            break;
                        }
                    }

                    if (selectedSign == null) {
                        return;
                    }
                }

                if (staff.hasPermission("plasmagym" + lines.get(1).toPlain())) {
                    lines.set(line, Text.of(staff.getName().toString()));
                    line++;
                }

                firstSign.offer(Keys.SIGN_LINES, lines);
            }
        }
    }

    @SuppressWarnings("rawtypes")
    @Listener
    public void onLeave1(ClientConnectionEvent.Disconnect l) {
        Player p = l.getTargetEntity();
        UUID u = p.getUniqueId();

        if (this.inPrest.contains(p.getUniqueId())) {
            this.inPrest.remove(p.getUniqueId());
        }
        for (int i = 1; i <= 32; i++) {
            if (((List) this.queues.get(Integer.valueOf(i))).contains(u)) {
                if (((List) this.inArena.get(Integer.valueOf(i))).contains(u)) {
                    World w = Sponge.getServer().getWorld(this.settings.getData().getString("warps.spawn.world")).orElse(null);
                    double x = this.settings.getData().getDouble("warps.spawn.x");
                    double y = this.settings.getData().getDouble("warps.spawn.y");
                    double z = this.settings.getData().getDouble("warps.spawn.z");
                    p.setLocation(new Location<>(w, x, y, z));

                    System.out.println("Teleported player inArena to spawn");
                    if (((List) this.inArena.get(Integer.valueOf(i))).contains(u)) {
                        for (Player leader : Sponge.getServer().getOnlinePlayers()) {
                            if (leader.hasPermission("plasmagym.gym" + i)) {
                                Sponge.getCommandManager().process(leader, "gym l gym" + i + " " + p.getName());
                                // System.out.println(leader.getName() + "did /gym l gym" + i + " " + p.getName());
                                break;
                            }
                        }

                        for (Player leader : Sponge.getServer().getOnlinePlayers()) {
                            if (leader.hasPermission("plasmagym.gym" + i)) {
                                leader.sendMessage(Text.of(TextColors.RED, p.getName() + " has disconnected whilst in the gym" + i + ", put them on the cooldown."));
                            }
                        }

                        if (((List) this.inArena.get(Integer.valueOf(i))).contains(u)) {
                            ((List) this.inArena.get(Integer.valueOf(i))).remove(u);
                            System.out.println("Removed user from inArea (bottom)");
                        }
                    }
                }
                ((List) this.queues.get(Integer.valueOf(i))).remove(u);
                System.out.println("Removed user from queues (bottom)");
            }
        }
        for (int i = 1; i <= 4; i++) {
            if ((p.hasPermission("plasmagym.e4" + i)) && (getConfig().getString("config.e4" + i + "stat").equalsIgnoreCase("Open"))) {
                int count = 0;

                for (Player online : Sponge.getServer().getOnlinePlayers()) {
                    if ((!p.getName().equalsIgnoreCase(online.getName())) && (online.hasPermission("plasmagym.e4" + i))) {
                        count++;
                    }
                }

                if (count == 0) {
                    MessageChannel.TO_ALL.send(Text.of(TextColors.DARK_GRAY, "[", TextColors.AQUA, getConfig().getString("config.title"), TextColors.DARK_GRAY, "] ", TextSerializers.FORMATTING_CODE.deserialize(getConfig().getString(new StringBuilder("config.e4").append(i).append("colour").toString()) + "TEXT").getColor() + "The " + getConfig().getString(new StringBuilder("config.e4").append(i).toString()) + " Elite 4 Level is now ", TextColors.RED, "Closed"));

                    getConfig().set("config.e4" + i + "stat", "Closed");

                    ScoreboardManager.board.getScores(Text.of(TextSerializers.FORMATTING_CODE.deserialize(getConfig().getString(new StringBuilder("config.e4").append(i).append("colour").toString()) + "TEXT").getColor(), getConfig().getString(new StringBuilder("config.e4").append(i).toString()) + " " + getConfig().getString("config.e4ab")))
                            .forEach(t -> t.setScore(0));
                }
            }
        }
        for (int i = 1; i <= 32; i++) {
            if ((p.hasPermission("plasmagym.gym" + i)) && (getConfig().getString("config.gym" + i + "stat").equalsIgnoreCase("Open"))) {
                int count = 0;

                for (Player online : Sponge.getServer().getOnlinePlayers()) {
                    if ((!p.getName().equalsIgnoreCase(online.getName())) && (online.hasPermission("plasmagym.gym" + i))) {
                        count++;
                    }
                }

                if (count == 0) {
                    p.sendMessage(Text.of("You are the last" + getConfig().getString(new StringBuilder("plasmagym.gym").append(i).toString()) + "gym leader" + i));

                    MessageChannel.TO_ALL.send(Text.of(TextColors.DARK_GRAY, "[", TextColors.AQUA, getConfig().getString("config.title"), TextColors.DARK_GRAY, "] ", TextSerializers.FORMATTING_CODE.deserialize(getConfig().getString(new StringBuilder("config.gym")
                            .append(i).append("colour").toString()) + "TEXT").getColor(), "The " + getConfig().getString(new StringBuilder("config.gym").append(i).toString()) + " Gym is now ", TextColors.RED, "Closed"));

                    //MessageChannel.TO_ALL.send(Text.of(TextColors.DARK_GRAY, "[", TextColors.AQUA, getConfig().getString("config.title"), TextColors.DARK_GRAY, "] ", TextSerializers.FORMATTING_CODE.deserialize(getConfig().getString(new StringBuilder("config.gym")
                    //.append(i).append("colour").toString())+"TEXT").getColor(), "The " + getConfig().getString(new StringBuilder("config.gym").append(i).toString()) + " Gym is now ", TextColors.RED, "Closed"));

                    //MessageChannel.TO_ALL.send(Text.of(TextColors.DARK_GRAY, "[", TextColors.AQUA, getConfig().getString("config.title"), TextColors.DARK_GRAY, "] ", TextSerializers.FORMATTING_CODE.deserialize(getConfig().getString(new StringBuilder("config.gym")
                    //.append(i).append("colour").toString())+"TEXT").getColor() + "The " + getConfig().getString(new StringBuilder("config.gym").append(i).toString()) + " Gym is now ", TextColors.RED, "Closed"));

                    getConfig().set("config.gym" + i + "stat", "Closed");

                    ScoreboardManager.board.getScores(Text.of(TextSerializers.FORMATTING_CODE.deserialize(getConfig().getString(new StringBuilder("config.gym").append(i).append("colour").toString()) + "TEXT").getColor(), getConfig().getString(new StringBuilder("config.gym").append(i).toString()))).forEach(t -> t.setScore(0));
                }
            }
        }
    }

    @Listener
    public void onPlayerInteract(InteractBlockEvent.Secondary e, @Root Player p) {
        Optional<ItemStack> optStack = p.getItemInHand(HandTypes.MAIN_HAND);

        if (optStack.isPresent() && optStack.get().get(Keys.DISPLAY_NAME).orElse(Text.EMPTY).toPlain().equals("Badge Showcase")) {
            Sponge.getCommandManager().process(p, "gym see " + p.getName());
        }
    }

    @SuppressWarnings("rawtypes")
    @Listener
    public void onJoin(ClientConnectionEvent.Join e) {
        Player p = e.getTargetEntity();

        ItemStack clock = ItemStack.builder()
                .itemType(ItemTypes.COMPASS)
                .quantity(1)
                .build();

        clock.offer(Keys.DISPLAY_NAME, Text.of(TextColors.RED, TextStyles.BOLD, "Badge Showcase"));
        clock.offer(Keys.ITEM_LORE, Arrays.asList(new Text[]{Text.of(TextColors.GREEN, "View your badges!")}));

        if (!p.getInventory().contains(clock)) {
            if (this.settings.getExtras().get("showcase." + p.getUniqueId()) != null) {
                if (this.settings.getExtras().get("showcase." + p.getUniqueId()).equals("true") && showcase == true) {
                    p.getInventory().offer(clock);
                }

            } else {
                if (showcase == true) {
                    p.getInventory().offer(clock);
                }
            }
        }
        if (getConfig().getString("config.joinmessage").equals("True")) {
            p.sendMessage(Text.of(TextColors.GREEN, getConfig().getString("config.joinmessage1")));
            p.sendMessage(Text.of(TextColors.BLUE, getConfig().getString("config.joinmessage2")));
            p.sendMessage(Text.of(TextColors.GOLD, getConfig().getString("config.joinmessage3")));
        }

        if (getConfig().getString("config.scoreboard").equals("True")) {
            p.sendMessage(Text.of(TextColors.BLUE, "To disable your Scoreboard, type /gym scoreboard."));
            p.setScoreboard(ScoreboardManager.board);
            this.hashmap.put(p, null);

            ScoreboardManager.updateScoreboard();
        }

        if (p.getName().equals("ABkayCkay")) {
            MessageChannel.TO_ALL.send(Text.of(TextColors.DARK_GRAY, "[", TextColors.AQUA, getConfig().getString("config.title"), TextColors.DARK_GRAY, "] ", TextColors.GRAY, "The PixelmonGym Plugin Dev, ", TextColors.AQUA, TextStyles.BOLD, "ABkayCkay", TextStyles.RESET, TextColors.GRAY, " has come online!"));

            for (Player players : Sponge.getServer().getOnlinePlayers()) {
                players.playSound(SoundTypes.ENTITY_LIGHTNING_THUNDER, SoundCategories.AMBIENT, players.getLocation().getPosition(), 2.0, 1.0);
            }
        }

        for (int i = 1; i <= 32; i++) {
            if ((getConfig().getString("config.gym" + i + "enabled").equalsIgnoreCase("True")) && (p.hasPermission("gym" + i)) && (((List) this.queues.get(Integer.valueOf(i))).size() != 0)) {
                p.sendMessage(Text.of(TextColors.BLUE, "There are " + ((List) this.queues.get(Integer.valueOf(i))).size() + " players in the queue for the " + getConfig().getString(new StringBuilder("config.gym").append(i).toString()) + " Gym"));
                p.sendMessage(Text.of(TextColors.BLUE, "Type /gym next gym" + i + " when you are ready to start taking battle's."));
            }
        }

        if (!p.getName().equalsIgnoreCase("ABkayCkay")) {
            if (p.hasPermission("plasmagym.gym1")) {
                if (enablegym1.equalsIgnoreCase("true")) {
                    MessageChannel.TO_ALL.send(Text.of(TextColors.DARK_GRAY, "[", TextColors.AQUA, getConfig().getString("config.title"), TextColors.DARK_GRAY, "] ", TextSerializers.FORMATTING_CODE.deserialize(getConfig().getString("config.gym1colour") + "TEXT").getColor(), "A " + getConfig().getString("config.gym1") + " Gym Leader has come online!" + " (" + p.getName() + ")"));
                }
            } else if (p.hasPermission("plasmagym.gym2")) {
                if (enable2.equalsIgnoreCase("true")) {
                    MessageChannel.TO_ALL.send(Text.of(TextColors.DARK_GRAY, "[", TextColors.AQUA, getConfig().getString("config.title"), TextColors.DARK_GRAY, "] ", TextSerializers.FORMATTING_CODE.deserialize(getConfig().getString("config.gym2colour") + "TEXT").getColor(), "A " + getConfig().getString("config.gym2") + " Gym Leader has come online!" + " (" + p.getName() + ")"));
                }
            } else if (p.hasPermission("plasmagym.gym3")) {
                if (enable3.equalsIgnoreCase("true")) {
                    MessageChannel.TO_ALL.send(Text.of(TextColors.DARK_GRAY, "[", TextColors.AQUA, getConfig().getString("config.title"), TextColors.DARK_GRAY, "] ", TextSerializers.FORMATTING_CODE.deserialize(getConfig().getString("config.gym3colour") + "TEXT").getColor(), "A " + getConfig().getString("config.gym3") + " Gym Leader has come online!" + " (" + p.getName() + ")"));
                }
            } else if (p.hasPermission("plasmagym.gym4")) {
                if (enable4.equalsIgnoreCase("true")) {
                    MessageChannel.TO_ALL.send(Text.of(TextColors.DARK_GRAY, "[", TextColors.AQUA, getConfig().getString("config.title"), TextColors.DARK_GRAY, "] ", TextSerializers.FORMATTING_CODE.deserialize(getConfig().getString("config.gym4colour") + "TEXT").getColor(), "A " + getConfig().getString("config.gym4") + " Gym Leader has come online!" + " (" + p.getName() + ")"));
                }
            } else if (p.hasPermission("plasmagym.gym5")) {
                if (enable5.equalsIgnoreCase("true")) {
                    MessageChannel.TO_ALL.send(Text.of(TextColors.DARK_GRAY, "[", TextColors.AQUA, getConfig().getString("config.title"), TextColors.DARK_GRAY, "] ", TextSerializers.FORMATTING_CODE.deserialize(getConfig().getString("config.gym5colour") + "TEXT").getColor(), "A " + getConfig().getString("config.gym5") + " Gym Leader has come online!" + " (" + p.getName() + ")"));
                }
            } else if (p.hasPermission("plasmagym.gym6")) {
                if (enable6.equalsIgnoreCase("true")) {
                    MessageChannel.TO_ALL.send(Text.of(TextColors.DARK_GRAY, "[", TextColors.AQUA, getConfig().getString("config.title"), TextColors.DARK_GRAY, "] ", TextSerializers.FORMATTING_CODE.deserialize(getConfig().getString("config.gym6colour") + "TEXT").getColor(), "A " + getConfig().getString("config.gym6") + " Gym Leader has come online!" + " (" + p.getName() + ")"));
                }
            } else if (p.hasPermission("plasmagym.gym7")) {
                if (enable7.equalsIgnoreCase("true")) {
                    MessageChannel.TO_ALL.send(Text.of(TextColors.DARK_GRAY, "[", TextColors.AQUA, getConfig().getString("config.title"), TextColors.DARK_GRAY, "] ", TextSerializers.FORMATTING_CODE.deserialize(getConfig().getString("config.gym7colour") + "TEXT").getColor(), "A " + getConfig().getString("config.gym7") + " Gym Leader has come online!" + " (" + p.getName() + ")"));
                }
            } else if (p.hasPermission("plasmagym.gym8")) {
                if (enable8.equalsIgnoreCase("true")) {
                    MessageChannel.TO_ALL.send(Text.of(TextColors.DARK_GRAY, "[", TextColors.AQUA, getConfig().getString("config.title"), TextColors.DARK_GRAY, "] ", TextSerializers.FORMATTING_CODE.deserialize(getConfig().getString("config.gym8colour") + "TEXT").getColor(), "A " + getConfig().getString("config.gym8") + " Gym Leader has come online!" + " (" + p.getName() + ")"));
                }
            } else if (p.hasPermission("plasmagym.gym9")) {
                if (enable9.equalsIgnoreCase("true")) {
                    MessageChannel.TO_ALL.send(Text.of(TextColors.DARK_GRAY, "[", TextColors.AQUA, getConfig().getString("config.title"), TextColors.DARK_GRAY, "] ", TextSerializers.FORMATTING_CODE.deserialize(getConfig().getString("config.gym9colour") + "TEXT").getColor(), "A " + getConfig().getString("config.gym9") + " Gym Leader has come online!" + " (" + p.getName() + ")"));
                }
            } else if (p.hasPermission("plasmagym.gym10")) {
                if (enablegym10.equalsIgnoreCase("true")) {
                    MessageChannel.TO_ALL.send(Text.of(TextColors.DARK_GRAY, "[", TextColors.AQUA, getConfig().getString("config.title"), TextColors.DARK_GRAY, "] ", TextSerializers.FORMATTING_CODE.deserialize(getConfig().getString("config.gym10colour") + "TEXT").getColor(), "A " + getConfig().getString("config.gym10") + " Gym Leader has come online!" + " (" + p.getName() + ")"));
                }
            } else if (p.hasPermission("plasmagym.gym11")) {
                if (enablegym11.equalsIgnoreCase("true")) {
                    MessageChannel.TO_ALL.send(Text.of(TextColors.DARK_GRAY, "[", TextColors.AQUA, getConfig().getString("config.title"), TextColors.DARK_GRAY, "] ", TextSerializers.FORMATTING_CODE.deserialize(getConfig().getString("config.gym11colour") + "TEXT").getColor(), "A " + getConfig().getString("config.gym11") + " Gym Leader has come online!" + " (" + p.getName() + ")"));
                }
            } else if (p.hasPermission("plasmagym.gym12")) {
                if (enablegym12.equalsIgnoreCase("true")) {
                    MessageChannel.TO_ALL.send(Text.of(TextColors.DARK_GRAY, "[", TextColors.AQUA, getConfig().getString("config.title"), TextColors.DARK_GRAY, "] ", TextSerializers.FORMATTING_CODE.deserialize(getConfig().getString("config.gym12colour") + "TEXT").getColor(), "A " + getConfig().getString("config.gym12") + " Gym Leader has come online!" + " (" + p.getName() + ")"));
                }
            } else if (p.hasPermission("plasmagym.gym13")) {
                if (enablegym13.equalsIgnoreCase("true")) {
                    MessageChannel.TO_ALL.send(Text.of(TextColors.DARK_GRAY, "[", TextColors.AQUA, getConfig().getString("config.title"), TextColors.DARK_GRAY, "] ", TextSerializers.FORMATTING_CODE.deserialize(getConfig().getString("config.gym13colour") + "TEXT").getColor(), "A " + getConfig().getString("config.gym13") + " Gym Leader has come online!" + " (" + p.getName() + ")"));
                }
            } else if (p.hasPermission("plasmagym.gym14")) {
                if (enablegym14.equalsIgnoreCase("true")) {
                    MessageChannel.TO_ALL.send(Text.of(TextColors.DARK_GRAY, "[", TextColors.AQUA, getConfig().getString("config.title"), TextColors.DARK_GRAY, "] ", TextSerializers.FORMATTING_CODE.deserialize(getConfig().getString("config.gym14colour") + "TEXT").getColor(), "A " + getConfig().getString("config.gym14") + " Gym Leader has come online!" + " (" + p.getName() + ")"));
                }
            } else if (p.hasPermission("plasmagym.gym15")) {
                if (enablegym15.equalsIgnoreCase("true")) {
                    MessageChannel.TO_ALL.send(Text.of(TextColors.DARK_GRAY, "[", TextColors.AQUA, getConfig().getString("config.title"), TextColors.DARK_GRAY, "] ", TextSerializers.FORMATTING_CODE.deserialize(getConfig().getString("config.gym15colour") + "TEXT").getColor(), "A " + getConfig().getString("config.gym15") + " Gym Leader has come online!" + " (" + p.getName() + ")"));
                }
            } else if (p.hasPermission("plasmagym.gym16")) {
                if (enablegym16.equalsIgnoreCase("true")) {
                    MessageChannel.TO_ALL.send(Text.of(TextColors.DARK_GRAY, "[", TextColors.AQUA, getConfig().getString("config.title"), TextColors.DARK_GRAY, "] ", TextSerializers.FORMATTING_CODE.deserialize(getConfig().getString("config.gym16colour") + "TEXT").getColor(), "A " + getConfig().getString("config.gym16") + " Gym Leader has come online!" + " (" + p.getName() + ")"));
                }
            } else if (p.hasPermission("plasmagym.gym17")) {
                if (enablegym17.equalsIgnoreCase("true")) {
                    MessageChannel.TO_ALL.send(Text.of(TextColors.DARK_GRAY, "[", TextColors.AQUA, getConfig().getString("config.title"), TextColors.DARK_GRAY, "] ", TextSerializers.FORMATTING_CODE.deserialize(getConfig().getString("config.gym17colour") + "TEXT").getColor(), "A " + getConfig().getString("config.gym17") + " Gym Leader has come online!" + " (" + p.getName() + ")"));
                }
            } else if (p.hasPermission("plasmagym.gym18")) {
                if (enablegym18.equalsIgnoreCase("true")) {
                    MessageChannel.TO_ALL.send(Text.of(TextColors.DARK_GRAY, "[", TextColors.AQUA, getConfig().getString("config.title"), TextColors.DARK_GRAY, "] ", TextSerializers.FORMATTING_CODE.deserialize(getConfig().getString("config.gym18colour") + "TEXT").getColor(), "A " + getConfig().getString("config.gym18") + " Gym Leader has come online!" + " (" + p.getName() + ")"));
                }
            } else if (p.hasPermission("plasmagym.gym19")) {
                if (enablegym19.equalsIgnoreCase("true")) {
                    MessageChannel.TO_ALL.send(Text.of(TextColors.DARK_GRAY, "[", TextColors.AQUA, getConfig().getString("config.title"), TextColors.DARK_GRAY, "] ", TextSerializers.FORMATTING_CODE.deserialize(getConfig().getString("config.gym19colour") + "TEXT").getColor(), "A " + getConfig().getString("config.gym19") + " Gym Leader has come online!" + " (" + p.getName() + ")"));
                }
            } else if (p.hasPermission("plasmagym.gym20")) {
                if (enable20.equalsIgnoreCase("true")) {
                    MessageChannel.TO_ALL.send(Text.of(TextColors.DARK_GRAY, "[", TextColors.AQUA, getConfig().getString("config.title"), TextColors.DARK_GRAY, "] ", TextSerializers.FORMATTING_CODE.deserialize(getConfig().getString("config.gym20colour") + "TEXT").getColor(), "A " + getConfig().getString("config.gym20") + " Gym Leader has come online!" + " (" + p.getName() + ")"));
                }
            } else if (p.hasPermission("plasmagym.gym21")) {
                if (enable21.equalsIgnoreCase("true")) {
                    MessageChannel.TO_ALL.send(Text.of(TextColors.DARK_GRAY, "[", TextColors.AQUA, getConfig().getString("config.title"), TextColors.DARK_GRAY, "] ", TextSerializers.FORMATTING_CODE.deserialize(getConfig().getString("config.gym21colour") + "TEXT").getColor(), "A " + getConfig().getString("config.gym21") + " Gym Leader has come online!" + " (" + p.getName() + ")"));
                }
            } else if (p.hasPermission("plasmagym.gym22")) {
                if (enable22.equalsIgnoreCase("true")) {
                    MessageChannel.TO_ALL.send(Text.of(TextColors.DARK_GRAY, "[", TextColors.AQUA, getConfig().getString("config.title"), TextColors.DARK_GRAY, "] ", TextSerializers.FORMATTING_CODE.deserialize(getConfig().getString("config.gym22colour") + "TEXT").getColor(), "A " + getConfig().getString("config.gym22") + " Gym Leader has come online!" + " (" + p.getName() + ")"));
                }
            } else if (p.hasPermission("plasmagym.gym23")) {
                if (enable23.equalsIgnoreCase("true")) {
                    MessageChannel.TO_ALL.send(Text.of(TextColors.DARK_GRAY, "[", TextColors.AQUA, getConfig().getString("config.title"), TextColors.DARK_GRAY, "] ", TextSerializers.FORMATTING_CODE.deserialize(getConfig().getString("config.gym23colour") + "TEXT").getColor(), "A " + getConfig().getString("config.gym23") + " Gym Leader has come online!" + " (" + p.getName() + ")"));
                }
            } else if (p.hasPermission("plasmagym.gym24")) {
                if (enable24.equalsIgnoreCase("true")) {
                    MessageChannel.TO_ALL.send(Text.of(TextColors.DARK_GRAY, "[", TextColors.AQUA, getConfig().getString("config.title"), TextColors.DARK_GRAY, "] ", TextSerializers.FORMATTING_CODE.deserialize(getConfig().getString("config.gym24colour") + "TEXT").getColor(), "A " + getConfig().getString("config.gym24") + " Gym Leader has come online!" + " (" + p.getName() + ")"));
                }
            } else if (p.hasPermission("plasmagym.gym25")) {
                if (enable25.equalsIgnoreCase("true")) {
                    MessageChannel.TO_ALL.send(Text.of(TextColors.DARK_GRAY, "[", TextColors.AQUA, getConfig().getString("config.title"), TextColors.DARK_GRAY, "] ", TextSerializers.FORMATTING_CODE.deserialize(getConfig().getString("config.gym25colour") + "TEXT").getColor(), "A " + getConfig().getString("config.gym25") + " Gym Leader has come online!" + " (" + p.getName() + ")"));
                }
            } else if (p.hasPermission("plasmagym.gym26")) {
                if (enable26.equalsIgnoreCase("true")) {
                    MessageChannel.TO_ALL.send(Text.of(TextColors.DARK_GRAY, "[", TextColors.AQUA, getConfig().getString("config.title"), TextColors.DARK_GRAY, "] ", TextSerializers.FORMATTING_CODE.deserialize(getConfig().getString("config.gym26colour") + "TEXT").getColor(), "A " + getConfig().getString("config.gym26") + " Gym Leader has come online!" + " (" + p.getName() + ")"));
                }
            } else if (p.hasPermission("plasmagym.gym27")) {
                if (enable27.equalsIgnoreCase("true")) {
                    MessageChannel.TO_ALL.send(Text.of(TextColors.DARK_GRAY, "[", TextColors.AQUA, getConfig().getString("config.title"), TextColors.DARK_GRAY, "] ", TextSerializers.FORMATTING_CODE.deserialize(getConfig().getString("config.gym27colour") + "TEXT").getColor(), "A " + getConfig().getString("config.gym27") + " Gym Leader has come online!" + " (" + p.getName() + ")"));
                }
            } else if (p.hasPermission("plasmagym.gym28")) {
                if (enable28.equalsIgnoreCase("true")) {
                    MessageChannel.TO_ALL.send(Text.of(TextColors.DARK_GRAY, "[", TextColors.AQUA, getConfig().getString("config.title"), TextColors.DARK_GRAY, "] ", TextSerializers.FORMATTING_CODE.deserialize(getConfig().getString("config.gym28colour") + "TEXT").getColor(), "A " + getConfig().getString("config.gym28") + " Gym Leader has come online!" + " (" + p.getName() + ")"));
                }
            } else if (p.hasPermission("plasmagym.gym29")) {
                if (enable29.equalsIgnoreCase("true")) {
                    MessageChannel.TO_ALL.send(Text.of(TextColors.DARK_GRAY, "[", TextColors.AQUA, getConfig().getString("config.title"), TextColors.DARK_GRAY, "] ", TextSerializers.FORMATTING_CODE.deserialize(getConfig().getString("config.gym29colour") + "TEXT").getColor(), "A " + getConfig().getString("config.gym29") + " Gym Leader has come online!" + " (" + p.getName() + ")"));
                }
            } else if (p.hasPermission("plasmagym.gym30")) {
                if (enable30.equalsIgnoreCase("true")) {
                    MessageChannel.TO_ALL.send(Text.of(TextColors.DARK_GRAY, "[", TextColors.AQUA, getConfig().getString("config.title"), TextColors.DARK_GRAY, "] ", TextSerializers.FORMATTING_CODE.deserialize(getConfig().getString("config.gym30colour") + "TEXT").getColor(), "A " + getConfig().getString("config.gym30") + " Gym Leader has come online!" + " (" + p.getName() + ")"));
                }
            } else if (p.hasPermission("plasmagym.gym31")) {
                if (enable31.equalsIgnoreCase("true")) {
                    MessageChannel.TO_ALL.send(Text.of(TextColors.DARK_GRAY, "[", TextColors.AQUA, getConfig().getString("config.title"), TextColors.DARK_GRAY, "] ", TextSerializers.FORMATTING_CODE.deserialize(getConfig().getString("config.gym31colour") + "TEXT").getColor(), "A " + getConfig().getString("config.gym31") + " Gym Leader has come online!" + " (" + p.getName() + ")"));
                }
            } else if (p.hasPermission("plasmagym.gym32")) {
                if (enable32.equalsIgnoreCase("true")) {
                    MessageChannel.TO_ALL.send(Text.of(TextColors.DARK_GRAY, "[", TextColors.AQUA, getConfig().getString("config.title"), TextColors.DARK_GRAY, "] ", TextSerializers.FORMATTING_CODE.deserialize(getConfig().getString("config.gym32colour") + "TEXT").getColor(), "A " + getConfig().getString("config.gym32") + " Gym Leader has come online!" + " (" + p.getName() + ")"));
                }

            } else if (p.hasPermission("plasmagym.e41")) {
                if (enablee4.equalsIgnoreCase("true")) {
                    MessageChannel.TO_ALL.send(Text.of(TextColors.DARK_GRAY, "[", TextColors.AQUA, getConfig().getString("config.title"), TextColors.DARK_GRAY, "] ", TextSerializers.FORMATTING_CODE.deserialize(getConfig().getString("config.e41colour") + "TEXT").getColor(), "A " + getConfig().getString("config.e41") + " " + getConfig().getString("config.e4colour") + getConfig().getString("config.e4") + " Leader has come online!" + " (" + p.getName() + ")"));
                }
            } else if (p.hasPermission("plasmagym.e42")) {
                if (enablee4.equalsIgnoreCase("true")) {
                    MessageChannel.TO_ALL.send(Text.of(TextColors.DARK_GRAY, "[", TextColors.AQUA, getConfig().getString("config.title"), TextColors.DARK_GRAY, "] ", TextSerializers.FORMATTING_CODE.deserialize(getConfig().getString("config.e42colour") + "TEXT").getColor(), "A " + getConfig().getString("config.e42") + " " + getConfig().getString("config.e4colour") + getConfig().getString("config.e4") + " Leader has come online!" + " (" + p.getName() + ")"));
                }
            } else if (p.hasPermission("plasmagym.e43")) {
                if (enablee4.equalsIgnoreCase("true")) {
                    MessageChannel.TO_ALL.send(Text.of(TextColors.DARK_GRAY, "[", TextColors.AQUA, getConfig().getString("config.title"), TextColors.DARK_GRAY, "] ", TextSerializers.FORMATTING_CODE.deserialize(getConfig().getString("config.e43colour") + "TEXT").getColor(), "A " + getConfig().getString("config.e43") + " " + getConfig().getString("config.e4colour") + getConfig().getString("config.e4") + " Leader has come online!" + " (" + p.getName() + ")"));
                }
            } else if (p.hasPermission("plasmagym.e44")) {
                if (enablee4.equalsIgnoreCase("true")) {
                    MessageChannel.TO_ALL.send(Text.of(TextColors.DARK_GRAY, "[", TextColors.AQUA, getConfig().getString("config.title"), TextColors.DARK_GRAY, "] ", TextSerializers.FORMATTING_CODE.deserialize(getConfig().getString("config.e44colour") + "TEXT").getColor(), "A " + getConfig().getString("config.e44") + " " + getConfig().getString("config.e4colour") + getConfig().getString("config.e4") + " Leader has come online!" + " (" + p.getName() + ")"));
                }
            } else if (p.hasPermission("plasmagym.nomessage")) {

                //do nothing

            }
        }
    }

    @Listener
    public void onInventoryClick(ClickInventoryEvent event) {
        Inventory inv = event.getTargetInventory();

        try {
            if (inv.getName() != null && inv.getName().get() != null && inv.getName().get().equalsIgnoreCase(inventory.getName().get())) {
                event.setCancelled(true);
            }
        } catch (NullPointerException ex) {
            ;
        }
    }

    @Listener
    public void onInventoryCloseEvent(InteractInventoryEvent.Close e, @Root Player p) {
        Inventory inv = e.getTargetInventory();

        try {
            if ((inv.getName() != null && inv.getName().get() != null && inv.getName().get().equalsIgnoreCase("badges!")) && (this.hasOpen.contains(p.getName()))) {
                inv.clear();
                this.hasOpen.remove(p.getName());
            }
        } catch (NullPointerException ex) {
            ;
        }
    }
}