package starter.Ckay.gym.scoreboard;

import net.minecraft.entity.boss.BossBar.Color;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.ScoreboardObjective;
import net.minecraft.scoreboard.ScoreboardPlayerScore;
import net.minecraft.server.PlayerManager;
import net.minecraft.text.Text;
import starter.Ckay.gym.PlasmaGym;
import starter.Ckay.gym.config.PluginConfiguration;

public class ScoreboardManager {
    public static Scoreboard board, clear;
    public static ScoreboardObjective none, obj;
    public static PlayerManager players;
    public static ScoreboardPlayerScore gym1, gym2, gym3, gym4, gym5, gym6, gym7, gym8, gym9, gym10, gym11, gym12, gym13, gym14, gym15, gym16, gym17, gym18, gym19, gym20, gym21, gym22, gym23, gym24, gym25, gym26, gym27, gym28, gym29, gym30, gym31, gym32, e41, e42, e43, e44, leaders;

    private PlasmaGym plugin;

    public ScoreboardManager(PlasmaGym plugin) {
        this.plugin = plugin;

        this.init();
    }

    public static void removeScores(ScoreboardPlayerScore score) {
        obj.getScoreboard().resetPlayerScore(score.getPlayerName(), obj);
    }

    public static void addAndUpdateScore(ScoreboardPlayerScore score, int val) {
        if (obj.getScoreboard().getPlayerScore(score.getPlayerName(), obj) == null) {
            // System.out.println(score.getName().getChildren().size());
            ScoreboardPlayerScore sc = obj.getScoreboard().getPlayerScore(score.getPlayerName(), obj);;

            sc.setScore(val);
        } else {
            score.setScore(val);
        }
    }

    public static void updateScoreboard() {
        for (PlayerEntity online : players.getPlayerList()) {
            online.setScore(ScoreboardManager.board.getPlayerScore(online.getEntityName(), obj).getScore());
        }

        if ((getConfig().getString("config.e41stat").equals("Open")) && (PlasmaGym.getInstance().enablee4.equalsIgnoreCase("true"))) {
            ScoreboardManager.addAndUpdateScore(ScoreboardManager.e41, 101);
        } else {
            ScoreboardManager.removeScores(ScoreboardManager.e41);
        }

        if ((getConfig().getString("config.e42stat").equals("Open")) && (PlasmaGym.getInstance().enablee4.equalsIgnoreCase("true"))) {
            ScoreboardManager.addAndUpdateScore(ScoreboardManager.e42, 102);
        } else {
            ScoreboardManager.removeScores(ScoreboardManager.e42);
        }

        if ((getConfig().getString("config.e43stat").equals("Open")) && (PlasmaGym.getInstance().enablee4.equalsIgnoreCase("true"))) {
            ScoreboardManager.addAndUpdateScore(ScoreboardManager.e43, 103);
        } else {
            ScoreboardManager.removeScores(ScoreboardManager.e43);
        }

        if ((getConfig().getString("config.e44stat").equals("Open")) && (PlasmaGym.getInstance().enablee4.equalsIgnoreCase("true"))) {
            ScoreboardManager.addAndUpdateScore(ScoreboardManager.e44, 104);
        } else {
            ScoreboardManager.removeScores(ScoreboardManager.e44);
        }

        if ((getConfig().getString("config.gym1stat").equals("Open")) && (PlasmaGym.getInstance().enablegym1.equalsIgnoreCase("true"))) {
            ScoreboardManager.addAndUpdateScore(ScoreboardManager.gym1, 1);
        } else {
            ScoreboardManager.removeScores(ScoreboardManager.gym1);
        }

        if ((getConfig().getString("config.gym2stat").equals("Open")) && (PlasmaGym.getInstance().enable2.equalsIgnoreCase("true"))) {
            ScoreboardManager.addAndUpdateScore(ScoreboardManager.gym2, 2);
        } else {
            ScoreboardManager.removeScores(ScoreboardManager.gym2);
        }

        if ((getConfig().getString("config.gym3stat").equals("Open")) && (PlasmaGym.getInstance().enable3.equalsIgnoreCase("true"))) {
            ScoreboardManager.addAndUpdateScore(ScoreboardManager.gym3, 3);
        } else {
            ScoreboardManager.removeScores(ScoreboardManager.gym3);
        }

        if ((getConfig().getString("config.gym4stat").equals("Open")) && (PlasmaGym.getInstance().enable4.equalsIgnoreCase("true"))) {
            ScoreboardManager.addAndUpdateScore(ScoreboardManager.gym4, 4);
        } else {
            ScoreboardManager.removeScores(ScoreboardManager.gym4);
        }

        if ((getConfig().getString("config.gym5stat").equals("Open")) && (PlasmaGym.getInstance().enable5.equalsIgnoreCase("true"))) {
            ScoreboardManager.addAndUpdateScore(ScoreboardManager.gym5, 5);
        } else {
            ScoreboardManager.removeScores(ScoreboardManager.gym5);
        }

        if ((getConfig().getString("config.gym6stat").equals("Open")) && (PlasmaGym.getInstance().enable6.equalsIgnoreCase("true"))) {
            ScoreboardManager.addAndUpdateScore(ScoreboardManager.gym6, 6);
        } else {
            ScoreboardManager.removeScores(ScoreboardManager.gym6);
        }

        if ((getConfig().getString("config.gym7stat").equals("Open")) && (PlasmaGym.getInstance().enable7.equalsIgnoreCase("true"))) {
            ScoreboardManager.addAndUpdateScore(ScoreboardManager.gym7, 7);
        } else {
            ScoreboardManager.removeScores(ScoreboardManager.gym7);
        }

        if ((getConfig().getString("config.gym8stat").equals("Open")) && (PlasmaGym.getInstance().enable8.equalsIgnoreCase("true"))) {
            ScoreboardManager.addAndUpdateScore(ScoreboardManager.gym8, 8);
        } else {
            ScoreboardManager.removeScores(ScoreboardManager.gym8);
        }

        if ((getConfig().getString("config.gym9stat").equals("Open")) && (PlasmaGym.getInstance().enable9.equalsIgnoreCase("true"))) {
            ScoreboardManager.addAndUpdateScore(ScoreboardManager.gym9, 9);
        } else {
            ScoreboardManager.removeScores(ScoreboardManager.gym9);
        }

        if ((getConfig().getString("config.gym10stat").equals("Open")) && (PlasmaGym.getInstance().enablegym10.equalsIgnoreCase("true"))) {
            ScoreboardManager.addAndUpdateScore(ScoreboardManager.gym10, 10);
        } else {
            ScoreboardManager.removeScores(ScoreboardManager.gym10);
        }

        if ((getConfig().getString("config.gym11stat").equals("Open")) && (PlasmaGym.getInstance().enablegym11.equalsIgnoreCase("true"))) {
            ScoreboardManager.addAndUpdateScore(ScoreboardManager.gym11, 11);
        } else {
            ScoreboardManager.removeScores(ScoreboardManager.gym11);
        }

        if ((getConfig().getString("config.gym12stat").equals("Open")) && (PlasmaGym.getInstance().enablegym12.equalsIgnoreCase("true"))) {
            ScoreboardManager.addAndUpdateScore(ScoreboardManager.gym12, 12);
        } else {
            ScoreboardManager.removeScores(ScoreboardManager.gym12);
        }

        if ((getConfig().getString("config.gym13stat").equals("Open")) && (PlasmaGym.getInstance().enablegym13.equalsIgnoreCase("true"))) {
            ScoreboardManager.addAndUpdateScore(ScoreboardManager.gym13, 13);
        } else {
            ScoreboardManager.removeScores(ScoreboardManager.gym13);
        }

        if ((getConfig().getString("config.gym14stat").equals("Open")) && (PlasmaGym.getInstance().enablegym14.equalsIgnoreCase("true"))) {
            ScoreboardManager.addAndUpdateScore(ScoreboardManager.gym14, 14);
        } else {
            ScoreboardManager.removeScores(ScoreboardManager.gym14);
        }

        if ((getConfig().getString("config.gym15stat").equals("Open")) && (PlasmaGym.getInstance().enablegym15.equalsIgnoreCase("true"))) {
            ScoreboardManager.addAndUpdateScore(ScoreboardManager.gym15, 15);
        } else {
            ScoreboardManager.removeScores(ScoreboardManager.gym15);
        }

        if ((getConfig().getString("config.gym16stat").equals("Open")) && (PlasmaGym.getInstance().enablegym16.equalsIgnoreCase("true"))) {
            ScoreboardManager.addAndUpdateScore(ScoreboardManager.gym16, 16);
        } else {
            ScoreboardManager.removeScores(ScoreboardManager.gym16);
        }

        if ((getConfig().getString("config.gym17stat").equals("Open")) && (PlasmaGym.getInstance().enablegym17.equalsIgnoreCase("true"))) {
            ScoreboardManager.addAndUpdateScore(ScoreboardManager.gym17, 17);
        } else {
            ScoreboardManager.removeScores(ScoreboardManager.gym17);
        }

        if ((getConfig().getString("config.gym18stat").equals("Open")) && (PlasmaGym.getInstance().enablegym18.equalsIgnoreCase("true"))) {
            ScoreboardManager.addAndUpdateScore(ScoreboardManager.gym18, 18);
        } else {
            ScoreboardManager.removeScores(ScoreboardManager.gym18);
        }

        if ((getConfig().getString("config.gym19stat").equals("Open")) && (PlasmaGym.getInstance().enablegym19.equalsIgnoreCase("true"))) {
            ScoreboardManager.addAndUpdateScore(ScoreboardManager.gym19, 19);
        } else {
            ScoreboardManager.removeScores(ScoreboardManager.gym19);
        }

        if ((getConfig().getString("config.gym20stat").equals("Open")) && (PlasmaGym.getInstance().enable20.equalsIgnoreCase("true"))) {
            ScoreboardManager.addAndUpdateScore(ScoreboardManager.gym20, 20);
        } else {
            ScoreboardManager.removeScores(ScoreboardManager.gym20);
        }

        if ((getConfig().getString("config.gym21stat").equals("Open")) && (PlasmaGym.getInstance().enable21.equalsIgnoreCase("true"))) {
            ScoreboardManager.addAndUpdateScore(ScoreboardManager.gym21, 21);
        } else {
            ScoreboardManager.removeScores(ScoreboardManager.gym21);
        }

        if ((getConfig().getString("config.gym22stat").equals("Open")) && (PlasmaGym.getInstance().enable22.equalsIgnoreCase("true"))) {
            ScoreboardManager.addAndUpdateScore(ScoreboardManager.gym22, 22);
        } else {
            ScoreboardManager.removeScores(ScoreboardManager.gym22);
        }

        if ((getConfig().getString("config.gym23stat").equals("Open")) && (PlasmaGym.getInstance().enable23.equalsIgnoreCase("true"))) {
            ScoreboardManager.addAndUpdateScore(ScoreboardManager.gym23, 23);
        } else {
            ScoreboardManager.removeScores(ScoreboardManager.gym23);
        }

        if ((getConfig().getString("config.gym24stat").equals("Open")) && (PlasmaGym.getInstance().enable24.equalsIgnoreCase("true"))) {
            ScoreboardManager.addAndUpdateScore(ScoreboardManager.gym24, 24);
        } else {
            ScoreboardManager.removeScores(ScoreboardManager.gym24);
        }

        if ((getConfig().getString("config.gym25stat").equals("Open")) && (PlasmaGym.getInstance().enable25.equalsIgnoreCase("true"))) {
            ScoreboardManager.addAndUpdateScore(ScoreboardManager.gym25, 25);
        } else {
            ScoreboardManager.removeScores(ScoreboardManager.gym25);
        }

        if ((getConfig().getString("config.gym26stat").equals("Open")) && (PlasmaGym.getInstance().enable26.equalsIgnoreCase("true"))) {
            ScoreboardManager.addAndUpdateScore(ScoreboardManager.gym26, 26);
        } else {
            ScoreboardManager.removeScores(ScoreboardManager.gym26);
        }

        if ((getConfig().getString("config.gym27stat").equals("Open")) && (PlasmaGym.getInstance().enable27.equalsIgnoreCase("true"))) {
            ScoreboardManager.addAndUpdateScore(ScoreboardManager.gym27, 27);
        } else {
            ScoreboardManager.removeScores(ScoreboardManager.gym27);
        }

        if ((getConfig().getString("config.gym28stat").equals("Open")) && (PlasmaGym.getInstance().enable28.equalsIgnoreCase("true"))) {
            ScoreboardManager.addAndUpdateScore(ScoreboardManager.gym28, 28);
        } else {
            ScoreboardManager.removeScores(ScoreboardManager.gym28);
        }

        if ((getConfig().getString("config.gym29stat").equals("Open")) && (PlasmaGym.getInstance().enable29.equalsIgnoreCase("true"))) {
            ScoreboardManager.addAndUpdateScore(ScoreboardManager.gym29, 29);
        } else {
            ScoreboardManager.removeScores(ScoreboardManager.gym29);
        }

        if ((getConfig().getString("config.gym30stat").equals("Open")) && (PlasmaGym.getInstance().enable30.equalsIgnoreCase("true"))) {
            ScoreboardManager.addAndUpdateScore(ScoreboardManager.gym30, 30);
        } else {
            ScoreboardManager.removeScores(ScoreboardManager.gym30);
        }

        if ((getConfig().getString("config.gym31stat").equals("Open")) && (PlasmaGym.getInstance().enable31.equalsIgnoreCase("true"))) {
            ScoreboardManager.addAndUpdateScore(ScoreboardManager.gym31, 31);
        } else {
            ScoreboardManager.removeScores(ScoreboardManager.gym31);
        }

        if ((getConfig().getString("config.gym32stat").equals("Open")) && (PlasmaGym.getInstance().enable32.equalsIgnoreCase("true"))) {
            ScoreboardManager.addAndUpdateScore(ScoreboardManager.gym32, 32);
        } else {
            ScoreboardManager.removeScores(ScoreboardManager.gym32);
        }
    }

    public static PluginConfiguration getConfig() {
        return PlasmaGym.getInstance().getConfig();
    }

    private void init() {
        none.setDisplayName(Text.of("test"));                

        obj.setDisplayName(Text.of(Color.GREEN + "Open Gyms"));

        //gym1 = obj.getOrCreateScore(Text.of(plugin.getConfig().getString("config.gym2colour") + "TEXT") + plugin.getConfig().getString("config.gym2") + " ");
    
        gym1.getScoreboard().getPlayerScore(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym1"), obj);        
        board.updateObjective(gym1.getScoreboard().getObjective(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym1")));
        board.setObjectiveSlot(0, gym1.getScoreboard().getObjective(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym1")));

        gym2.getScoreboard().getPlayerScore(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym2"), obj);
        board.updateObjective(gym2.getScoreboard().getObjective(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym2")));
        board.setObjectiveSlot(1, gym2.getScoreboard().getObjective(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym2")));

        gym3.getScoreboard().getPlayerScore(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym3"), obj);
        board.updateObjective(gym3.getScoreboard().getObjective(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym3")));
        board.setObjectiveSlot(2, gym3.getScoreboard().getObjective(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym3")));
        
        gym4.getScoreboard().getPlayerScore(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym4"), obj);
        board.updateObjective(gym4.getScoreboard().getObjective(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym4")));
        board.setObjectiveSlot(3, gym4.getScoreboard().getObjective(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym4")));
        
        gym5.getScoreboard().getPlayerScore(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym5"), obj);
        board.updateObjective(gym5.getScoreboard().getObjective(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym5")));
        board.setObjectiveSlot(4, gym5.getScoreboard().getObjective(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym5")));
        
        gym6.getScoreboard().getPlayerScore(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym6"), obj);
        board.updateObjective(gym6.getScoreboard().getObjective(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym6")));
        board.setObjectiveSlot(5, gym6.getScoreboard().getObjective(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym6")));
        
        gym7.getScoreboard().getPlayerScore(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym7"), obj);
        board.updateObjective(gym7.getScoreboard().getObjective(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym7")));
        board.setObjectiveSlot(6, gym7.getScoreboard().getObjective(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym7")));
        
        gym8.getScoreboard().getPlayerScore(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym8"), obj);
        board.updateObjective(gym8.getScoreboard().getObjective(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym8")));
        board.setObjectiveSlot(7, gym8.getScoreboard().getObjective(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym8")));
        
        gym9.getScoreboard().getPlayerScore(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym9"), obj);
        board.updateObjective(gym9.getScoreboard().getObjective(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym9")));
        board.setObjectiveSlot(8, gym9.getScoreboard().getObjective(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym9")));
        
        gym10.getScoreboard().getPlayerScore(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym10"), obj);
        board.updateObjective(gym10.getScoreboard().getObjective(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym10")));
        board.setObjectiveSlot(9, gym10.getScoreboard().getObjective(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym10")));
        
        gym11.getScoreboard().getPlayerScore(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym11"), obj);
        board.updateObjective(gym11.getScoreboard().getObjective(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym11")));
        board.setObjectiveSlot(10, gym11.getScoreboard().getObjective(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym11")));
        
        gym12.getScoreboard().getPlayerScore(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym12"), obj);
        board.updateObjective(gym12.getScoreboard().getObjective(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym12")));
        board.setObjectiveSlot(11, gym12.getScoreboard().getObjective(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym12")));
        
        gym13.getScoreboard().getPlayerScore(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym13"), obj);
        board.updateObjective(gym13.getScoreboard().getObjective(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym13")));
        board.setObjectiveSlot(12, gym13.getScoreboard().getObjective(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym13")));
        
        gym14.getScoreboard().getPlayerScore(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym14"), obj);
        board.updateObjective(gym14.getScoreboard().getObjective(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym14")));
        board.setObjectiveSlot(13, gym14.getScoreboard().getObjective(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym14")));
        
        gym15.getScoreboard().getPlayerScore(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym15"), obj);
        board.updateObjective(gym15.getScoreboard().getObjective(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym15")));
        board.setObjectiveSlot(14, gym15.getScoreboard().getObjective(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym15")));
        
        gym16.getScoreboard().getPlayerScore(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym16"), obj);
        board.updateObjective(gym16.getScoreboard().getObjective(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym16")));
        board.setObjectiveSlot(15, gym16.getScoreboard().getObjective(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym16")));
        
        gym17.getScoreboard().getPlayerScore(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym17"), obj);
        board.updateObjective(gym17.getScoreboard().getObjective(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym17")));
        board.setObjectiveSlot(16, gym17.getScoreboard().getObjective(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym17")));
        
        gym18.getScoreboard().getPlayerScore(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym18"), obj);
        board.updateObjective(gym18.getScoreboard().getObjective(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym18")));
        board.setObjectiveSlot(17, gym18.getScoreboard().getObjective(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym18")));
        
        gym19.getScoreboard().getPlayerScore(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym19"), obj);
        board.updateObjective(gym19.getScoreboard().getObjective(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym19")));
        board.setObjectiveSlot(18, gym19.getScoreboard().getObjective(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym19")));
        
        gym20.getScoreboard().getPlayerScore(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym20"), obj);
        board.updateObjective(gym20.getScoreboard().getObjective(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym20")));
        board.setObjectiveSlot(19, gym20.getScoreboard().getObjective(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym20")));
        
        gym21.getScoreboard().getPlayerScore(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym21"), obj);
        board.updateObjective(gym21.getScoreboard().getObjective(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym21")));
        board.setObjectiveSlot(20, gym21.getScoreboard().getObjective(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym21")));
        
        gym22.getScoreboard().getPlayerScore(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym22"), obj);
        board.updateObjective(gym22.getScoreboard().getObjective(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym22")));
        board.setObjectiveSlot(21, gym22.getScoreboard().getObjective(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym22")));
        
        gym23.getScoreboard().getPlayerScore(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym23"), obj);
        board.updateObjective(gym23.getScoreboard().getObjective(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym23")));
        board.setObjectiveSlot(22, gym23.getScoreboard().getObjective(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym23")));
        
        gym24.getScoreboard().getPlayerScore(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym24"), obj);
        board.updateObjective(gym24.getScoreboard().getObjective(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym24")));
        board.setObjectiveSlot(23, gym24.getScoreboard().getObjective(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym24")));
        
        gym25.getScoreboard().getPlayerScore(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym25"), obj);
        board.updateObjective(gym25.getScoreboard().getObjective(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym25")));
        board.setObjectiveSlot(24, gym25.getScoreboard().getObjective(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym25")));
        
        gym26.getScoreboard().getPlayerScore(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym26"), obj);
        board.updateObjective(gym26.getScoreboard().getObjective(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym26")));
        board.setObjectiveSlot(25, gym26.getScoreboard().getObjective(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym26")));
        
        gym27.getScoreboard().getPlayerScore(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym27"), obj);
        board.updateObjective(gym27.getScoreboard().getObjective(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym27")));
        board.setObjectiveSlot(26, gym27.getScoreboard().getObjective(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym27")));
        
        gym28.getScoreboard().getPlayerScore(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym28"), obj);
        board.updateObjective(gym28.getScoreboard().getObjective(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym28")));
        board.setObjectiveSlot(27, gym28.getScoreboard().getObjective(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym28")));
        
        gym29.getScoreboard().getPlayerScore(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym29"), obj);
        board.updateObjective(gym29.getScoreboard().getObjective(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym29")));
        board.setObjectiveSlot(28, gym29.getScoreboard().getObjective(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym29")));
        
        gym30.getScoreboard().getPlayerScore(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym30"), obj);
        board.updateObjective(gym30.getScoreboard().getObjective(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym30")));
        board.setObjectiveSlot(29, gym30.getScoreboard().getObjective(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym30")));
        
        gym31.getScoreboard().getPlayerScore(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym31"), obj);
        board.updateObjective(gym31.getScoreboard().getObjective(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym31")));
        board.setObjectiveSlot(30, gym31.getScoreboard().getObjective(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym31")));
        
        gym32.getScoreboard().getPlayerScore(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym32"), obj);
        board.updateObjective(gym32.getScoreboard().getObjective(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym32")));
        board.setObjectiveSlot(31, gym32.getScoreboard().getObjective(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.gym32")));
        

        e41.getScoreboard().getPlayerScore(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.e41colour") + "TEXT") + plugin.getConfig().getString("config.e41") + " " + plugin.getConfig().getString("config.e4ab"),obj);
        board.updateObjective(e41.getScoreboard().getObjective(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.e41")));
        board.setObjectiveSlot(32, e41.getScoreboard().getObjective(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.e41")));
        
        e42.getScoreboard().getPlayerScore(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.e42colour") + "TEXT") + plugin.getConfig().getString("config.e42") + " " + plugin.getConfig().getString("config.e4ab"),obj);
        board.updateObjective(e42.getScoreboard().getObjective(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.e42")));
        board.setObjectiveSlot(33, e42.getScoreboard().getObjective(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.e42")));
        
        e43.getScoreboard().getPlayerScore(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.e43colour") + "TEXT") + plugin.getConfig().getString("config.e43") + " " + plugin.getConfig().getString("config.e4ab"),obj);
        board.updateObjective(e43.getScoreboard().getObjective(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.e43")));
        board.setObjectiveSlot(34, e43.getScoreboard().getObjective(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.e43")));
        
        e44.getScoreboard().getPlayerScore(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.e44colour") + "TEXT") + plugin.getConfig().getString("config.e44") + " " + plugin.getConfig().getString("config.e4ab"),obj);
        board.updateObjective(e44.getScoreboard().getObjective(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.e44")));
        board.setObjectiveSlot(35, e44.getScoreboard().getObjective(obj.getScoreboard().getObjective(plugin.getConfig().getString("config.gym1colour") + "TEXT") + plugin.getConfig().getString("config.e44")));
        

    }
}
