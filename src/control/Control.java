package control;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JOptionPane;

import model.BackgroundModel;
import model.BuildingsModel;
import model.DiceModel;
import model.EffectModel;
import model.EventsModel;
import model.LandModel;
import model.PlayerModel;
import model.Port;
import model.TextTipModel;
import model.buildings.EcoPark;
import model.buildings.EcoHospital;
import model.buildings.News;
import model.buildings.Origin;
import model.buildings.ForestPark;
import model.buildings.Point;
import model.buildings.EcoPrison;
import model.buildings.EcoShop_;
import model.card.EcoCard;
import model.card.EcoTortoiseEcoCard;
import music.Music;
import ui.JPanelGame;
import util.MyThread;
import context.GameState;


public class Control {

    public static long tick;

    public static int rate = 30;

    private JPanelGame panel;

    private GameRunning run = null;

    private List<Port> models = new ArrayList<Port>();
    private List<PlayerModel> players = null;
    private BuildingsModel building = null;
    private BackgroundModel background = null;
    private LandModel land = null;
    private TextTipModel textTip = null;
    private DiceModel dice = null;
    private EventsModel events = null;
    private EffectModel effect = null;

    private Music music = null;


    private Timer gameTimer = null;

    public Control() {

        this.run = new GameRunning(this, players);

        this.initClass();

        this.run.setPlayers(players);
    }

    public void setPanel(JPanelGame panel) {
        this.panel = panel;
    }


    private void initClass() {

        this.events = new EventsModel();
        this.models.add(events);

        this.effect = new EffectModel();
        this.models.add(effect);

        this.background = new BackgroundModel();
        this.models.add(background);

        this.land = new LandModel();
        this.models.add(land);

        this.textTip = new TextTipModel();
        this.models.add(textTip);

        this.building = new BuildingsModel(land);
        this.models.add(building);

        this.players = new ArrayList<PlayerModel>();
        this.players.add(new PlayerModel(1, this));
        this.players.add(new PlayerModel(2, this));
        this.models.add(players.get(0));
        this.models.add(players.get(1));

        this.dice = new DiceModel(run);
        this.models.add(dice);


        this.music = new Music();
    }


    private void createGameTimer() {
        this.gameTimer = new Timer();
        this.gameTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                tick++;

                for (Port temp : models) {
                    temp.updata(tick);
                }

                panel.repaint();
            }
        }, 0, (1000 / rate));
    }


    public void start() {

        this.createGameTimer();

        for (Port temp : this.models) {
            temp.startGameInit();
        }

        this.run.startGameInit();

        this.panel.startGamePanelInit();

        this.startMusic();

        this.effect.showImg("start");
    }


    private void startMusic() {
        music.start();
    }

    public List<PlayerModel> getPlayers() {
        return players;
    }

    public BuildingsModel getBuilding() {
        return building;
    }

    public BackgroundModel getBackground() {
        return background;
    }

    public LandModel getLand() {
        return land;
    }

    public EffectModel getEffect() {
        return effect;
    }

    public TextTipModel getTextTip() {
        return textTip;
    }

    public GameRunning getRunning() {
        return run;
    }

    public DiceModel getDice() {
        return dice;
    }

    public EventsModel getEvents() {
        return events;
    }

    public JPanelGame getPanel() {
        return panel;
    }


    public void pressButton() {
        PlayerModel player = this.run.getNowPlayer();
        if (player.getInHospital() > 0 || player.getInPrison() > 0) {
            this.run.nextState();
            if (player.getInHospital() > 0) {
                this.textTip.showTextTip(player, player.getName() + "EcoHospital.", 3);
            } else if (player.getInPrison() > 0) {
                this.textTip.showTextTip(player, player.getName() + "EcoPrison.", 3);
            }
            this.run.nextState();
        } else {

            this.dice.setStartTick(Control.tick);

            this.dice.setNextTick(this.dice.getStartTick()
                    + this.dice.getLastTime());

            this.dice.setPoint(this.run.getPoint());

            this.run.nextState();

            this.run.getNowPlayer().setStartTick(this.dice.getNextTick() + 10);
            this.run.getNowPlayer().setNextTick(
                    this.run.getNowPlayer().getStartTick()
                            + this.run.getNowPlayer().getLastTime()
                            * (this.run.getPoint() + 1));
        }
    }


    public void movePlayer() {

        for (int i = 0; i < (60 / this.run.getNowPlayer().getLastTime()); i++) {

            if (GameRunning.MAP == 1) {
                this.move01();
            } else if (GameRunning.MAP == 2) {
                this.move02();
            } else if (GameRunning.MAP == 3) {
                this.move03();
            }
        }
    }


    public void prassBuilding() {

        PlayerModel player = this.run.getNowPlayer();

        EcoPark ecoPark = this.building.getBuilding(player.getY() / 60,
                player.getX() / 60);
        if (ecoPark != null && player.getX() % 60 == 0
                && player.getY() % 60 == 0) {

            int event = ecoPark.getEcoParkPassEvent();

            disposePassEvent(ecoPark, event, player);
        }
    }


    private void disposePassEvent(EcoPark b, int event, PlayerModel player) {
        switch (event) {
            case GameState.ORIGIN_PASS_EVENT:

                passOrigin(b, player);
                break;
            default:
                break;
        }
    }


    private void passOrigin(EcoPark b, PlayerModel player) {
        this.textTip.showTextTip(player, player.getName() + " EcoOrigin "
                + ((Origin) b).getPassReward() + " EC .", 3);
        player.setCash(player.getCash() + ((Origin) b).getPassReward());
    }


    private void move02() {
        int dice = this.run.getPoint() + 1;
        PlayerModel p = this.run.getNowPlayer();

        int movePixel = 1;
        if (p.getX() < 12 * 60 && p.getY() == 0) {
            p.setX(p.getX() + movePixel);
        } else if (p.getX() == 12 * 60 && p.getY() < 2 * 60) {
            p.setY(p.getY() + movePixel);
        } else if (p.getX() == 12 * 60 && p.getY() == 2 * 60) {
            if ((int) (Math.random() * 2) == 0) {
                p.setX(p.getX() - movePixel);
            } else {
                p.setY(p.getY() + movePixel);
            }
        } else if (p.getX() == 12 * 60 && p.getY() > 2 * 60 && p.getY() < 4 * 60) {
            p.setY(p.getY() + movePixel);
        } else if (p.getX() > 8 * 60 && p.getX() <= 12 * 60 && p.getY() == 4 * 60) {
            p.setX(p.getX() - movePixel);
        } else if (p.getX() == 8 * 60 && p.getY() == 4 * 60) {
            if ((int) (Math.random() * 2) == 0) {
                p.setX(p.getX() - movePixel);
            } else {
                p.setY(p.getY() + movePixel);
            }
        } else if (p.getX() > 4 * 60 && p.getX() < 8 * 60 && p.getY() == 4 * 60) {
            p.setX(p.getX() - movePixel);
        } else if (p.getX() == 8 * 60 && p.getY() > 4 * 60 && p.getY() < 7 * 60) {
            p.setY(p.getY() + movePixel);
        } else if (p.getX() > 4 * 60 && p.getX() <= 8 * 60 && p.getY() == 7 * 60) {
            p.setX(p.getX() - movePixel);
        } else if (p.getX() > 4 * 60 && p.getX() < 12 * 60 && p.getY() == 2 * 60) {
            p.setX(p.getX() - movePixel);
        } else if (p.getX() == 4 * 60 && p.getY() >= 2 * 60 && p.getY() < 7 * 60) {
            p.setY(p.getY() + movePixel);
        } else if (p.getX() > 0 && p.getX() <= 4 * 60 && p.getY() == 7 * 60) {
            p.setX(p.getX() - movePixel);
        } else if (p.getX() == 0 && p.getY() > 0) {
            p.setY(p.getY() - movePixel);
        }
    }


    private void move01() {
        int dice = this.run.getPoint() + 1;
        PlayerModel p = this.run.getNowPlayer();

        int movePixel = 1;
        Boolean turn = dice % 2 != 0;
        if (p.getX() < 9 * 60 && p.getY() == 0) {

            if (p.getX() == 4 * 60 && turn) {

                p.setY(p.getY() + movePixel);
            } else {
                p.setX(p.getX() + movePixel);
            }
        } else if (p.getX() == 9 * 60 && p.getY() >= 0 && p.getY() < 60) {


            p.setY(p.getY() + movePixel);
        } else if (p.getX() >= 8 * 60 && p.getX() < 12 * 60
                && p.getY() >= 1 * 60 && p.getY() <= 60 * 1.5) {

            p.setX(p.getX() + movePixel);
        } else if (p.getX() == 12 * 60 && p.getY() >= 1 * 60
                && p.getY() < 7 * 60) {

            p.setY(p.getY() + movePixel);
        } else if (p.getX() > 0 && p.getY() == 7 * 60) {

            p.setX(p.getX() - movePixel);
        } else if (p.getX() == 0 && p.getY() > 0) {

            p.setY(p.getY() - movePixel);
        } else if (p.getX() == 4 * 60 && p.getY() > 0 && p.getY() < 7 * 60) {

            p.setY(p.getY() + movePixel);
        }
    }

    private void move03() {
        PlayerModel p = this.run.getNowPlayer();

        int movePixel = 1;
        if (p.getX() < 12 * 60 && p.getY() == 0) {
            p.setX(p.getX() + movePixel);
        } else if (p.getX() == 12 * 60 && p.getY() < 7 * 60) {
            p.setY(p.getY() + movePixel);
        } else if (p.getX() > 0 && p.getY() == 7 * 60) {
            p.setX(p.getX() - movePixel);
        } else if (p.getX() == 0 && p.getY() > 0) {
            p.setY(p.getY() - movePixel);
        }
    }

    public void playerStopJudge() {

        PlayerModel player = this.run.getNowPlayer();
        if (player.getInHospital() > 0) {
            this.textTip.showTextTip(player, player.getName() + "EH, can't move.",
                    2);

            this.run.nextState();
        } else if (player.getInPrison() > 0) {
            this.textTip.showTextTip(player, player.getName() + "EP, can't move.",
                    2);

            this.run.nextState();
        } else {

            this.playerStop();
        }
    }


    public void playerStop() {

        PlayerModel player = this.run.getNowPlayer();

        EcoPark ecoPark = this.building.getBuilding(player.getY() / 60,
                player.getX() / 60);
        if (ecoPark != null) {
            int event = ecoPark.getEcoParkEvent();

            disposeStopEvent(ecoPark, event, player);

        }
    }


    private void disposeStopEvent(EcoPark b, int event, PlayerModel player) {
        switch (event) {
            case GameState.HOSPITAL_EVENT:

                stopInHospital(b, player);
                break;
            case GameState.HUOSE_EVENT:

                stopInHouse(b, player);
                break;
            case GameState.LOTTERY_EVENT:

                stopInLottery(b, player);
                break;
            case GameState.NEWS_EVENT:

                stopInNews(b, player);
                break;
            case GameState.ORIGIN_EVENT:

                stopInOrigin(b, player);
                break;
            case GameState.PARK_EVENT:

                stopInPack(b, player);
                break;
            case GameState.POINT_EVENT:

                stopInPoint(b, player);
                break;
            case GameState.PRISON_EVENT:

                stopInPrison(b, player);
                break;
            case GameState.SHOP_EVENT:

                stopInShop(b, player);
                break;
        }

    }


    private void stopInShop(EcoPark b, PlayerModel player) {
        if (player.getNx() > 0) {

            ((EcoShop_) b).createCards();

            this.panel.getShop().addCards((EcoShop_) b);

            this.panel.getShop().moveToFront();
        } else {
            this.run.nextState();
        }
    }


    private void stopInPrison(EcoPark b, PlayerModel player) {
        int days = (int) (Math.random() * 3) + 2;
        player.setInPrison(days);
        int random = (int) (Math.random() * ((EcoPrison) b).getEvents().length);
        String text = ((EcoPrison) b).getEvents()[random];
        this.textTip.showTextTip(player, player.getName() + text + "Stay"
                + (days - 1) + " d.", 3);
        new Thread(new MyThread(run, 1)).start();
    }


    private void stopInPoint(EcoPark b, PlayerModel player) {
        player.setNx(((Point) b).getPoint() + player.getNx());
        this.textTip.showTextTip(player, player.getName() + " gain "
                + ((Point) b).getPoint() + " Ec .", 3);
        new Thread(new MyThread(run, 1)).start();
    }


    private void stopInPack(EcoPark b, PlayerModel player) {
        int random = (int) (Math.random() * ((ForestPark) b).getImgageEvents().length);

        switch (random) {
            case 0:
            case 1:

                player.setCash(player.getCash() - 1);
                break;
            case 2:

                player.setCash(player.getCash() - 200);
                break;
            case 3:

                player.setCash(player.getCash() + 200);
                break;
        }

        this.events.showImg(((ForestPark) b).getImgageEvents()[random], 3, new Point(
                320, 160, 0));
        new Thread(new MyThread(run, 3)).start();
    }


    private void stopInOrigin(EcoPark b, PlayerModel player) {
        this.textTip.showTextTip(player, player.getName() + " EcoOriginal "
                + ((Origin) b).getReward() + " EC .", 3);
        player.setCash(player.getCash() + ((Origin) b).getReward());
        new Thread(new MyThread(run, 1)).start();
    }


    private void stopInNews(EcoPark b, PlayerModel player) {
        int random = (int) (Math.random() * ((News) b).getImgageEvents().length);
        switch (random) {
            case 0:
            case 1:

                player.setInHospital(player.getInHospital() + 4);

                if (LandModel.hospital != null) {
                    player.setX(LandModel.hospital.x);
                    player.setY(LandModel.hospital.y);
                }
                break;
            case 2:
            case 3:
                player.setCash(player.getCash() - 1000);
                break;
            case 4:
                player.setCash(player.getCash() - 1500);
                break;
            case 5:
                player.setCash(player.getCash() - 2000);
                break;
            case 6:
            case 7:
                player.setCash(player.getCash() - 300);
                break;
            case 8:
                player.setCash(player.getCash() - 400);
                break;
            case 9:

                if (player.getNx() < 40) {
                    stopInNews(b, player);
                    return;
                }
                player.setNx(player.getNx() - 40);
                break;
            case 10:
                player.setCash(player.getCash() - 500);
                break;
            case 11:
                player.setCash(player.getCash() + 1000);
                break;
            case 12:
            case 13:
                player.setCash(player.getCash() + 2000);
                break;
            case 14:
                player.setCash(player.getCash() + 3999);
                player.setNx(player.getNx() + 100);
                break;
            case 15:
                player.setNx(player.getNx() + 300);
                break;
            case 16:
                for (int i = 0; i < player.getCards().size(); i++) {


                    if (player.getCards().get(i).getName().equals("CrossingCard")) {
                        player.getCards().remove(i);

                        player.getOtherPlayer().setCash(player.getOtherPlayer().getCash() - 3000);
                        this.textTip.showTextTip(player, player.getName() + "give\"3000\"to " + player.getOtherPlayer().getName() + "。真Y人算不如 d算啊.", 6);
                        this.events.showImg(((News) b).get3000(), 3, new Point(
                                420, 160, 0));
                        new Thread(new MyThread(run, 3)).start();
                        return;
                    }
                }
                player.setCash(player.getCash() - 3000);
                break;
        }

        this.events.showImg(((News) b).getImgageEvents()[random], 3, new Point(
                420, 160, 0));
        new Thread(new MyThread(run, 3)).start();
    }


    private void stopInLottery(EcoPark b, PlayerModel player) {

        new Thread(new MyThread(run, 1)).start();
    }


    private void stopInHouse(EcoPark b, PlayerModel player) {
        if (b.isEcoParkPurchasable()) {
            if (b.getOwner() == null) {

                this.buyHouse(b, player);
            } else {
                if (b.getOwner().equals(player)) {

                    this.upHouseLevel(b, player);
                } else {

                    this.giveTax(b, player);
                }
            }
        }
    }


    private void giveTax(EcoPark b, PlayerModel player) {
        if (b.getOwner().getInHospital() > 0) {

            this.textTip.showTextTip(player, b.getOwner().getName()
                    + "EH, don't pay.", 3);
        } else if (b.getOwner().getInPrison() > 0) {

            this.textTip.showTextTip(player, b.getOwner().getName()
                    + "EP, don't pay.", 3);
        } else {
            int revenue = b.getEcoParkRevenue();

            player.setCash(player.getCash() - revenue);

            b.getOwner().setCash(b.getOwner().getCash() + revenue);

            this.textTip.showTextTip(player, player.getName() + "go"
                    + b.getOwner().getName() + "pay:" + revenue + " EC .", 3);

        }
        new Thread(new MyThread(run, 1)).start();
    }


    private void upHouseLevel(EcoPark b, PlayerModel player) {
        if (b.canEcoParkUpLevel()) {

            int price = b.getUpLevelPrice();
            String name = b.getEcoPark();
            String upName = b.getUpName();
            int choose = JOptionPane.showConfirmDialog(null,
                    "Dear:" + player.getName() + "\r\n" + "update？\r\n" + name
                            + "→" + upName + "\r\n" + "EH：" + price + "  EC .");
            if (choose == JOptionPane.OK_OPTION) {
                if (player.getCash() >= price) {
                    b.setEcoParkLevel(b.getEcoParkLevel() + 1);

                    player.setCash(player.getCash() - price);

                    this.textTip.showTextTip(player, player.getName() + " 从 "
                            + name + " update " + upName + ".pay " + price
                            + " EC . ", 3);
                } else {

                    this.textTip.showTextTip(player, player.getName()
                            + "  no enough EC ,failure. ", 3);
                }
            }
        }
        new Thread(new MyThread(run, 1)).start();
    }


    private void buyHouse(EcoPark b, PlayerModel player) {
        int price = b.getUpLevelPrice();
        int choose = JOptionPane.showConfirmDialog(
                null,
                "Dear:" + player.getName() + "\r\n" + "buy？\r\n"
                        + b.getEcoPark() + "→" + b.getUpName() + "\r\n" + "HP："
                        + price + "  EC .");

        if (choose == JOptionPane.OK_OPTION) {

            if (player.getCash() >= price) {
                b.setOwner(player);
                b.setEcoParkLevel(1);

                player.getBuildings().add(b);

                player.setCash(player.getCash() - price);
                this.textTip.showTextTip(player, player.getName()
                        + " pay: " + price + " EC . ", 3);
            } else {
                this.textTip.showTextTip(player, player.getName()
                        + "  EC not enough,failure. ", 3);
            }
        }
        new Thread(new MyThread(run, 1)).start();
    }


    private void stopInHospital(EcoPark b, PlayerModel player) {
        int days = (int) (Math.random() * 4) + 2;
        player.setInHospital(days);
        int random = (int) (Math.random() * ((EcoHospital) b).getEvents().length);
        String text = ((EcoHospital) b).getEvents()[random];
        this.textTip.showTextTip(player, player.getName() + text + "stay"
                + (days - 1) + " d.", 3);
        new Thread(new MyThread(run, 1)).start();
    }


    public void cardsBuff() {
        List<EcoCard> delete = new ArrayList<EcoCard>();
        for (EcoCard a : this.run.getNowPlayer().getEffectCards()) {
            int buff = a.cardBuff();
            cardBuff(a, buff, delete);
        }
        this.run.getNowPlayer().getEffectCards().removeAll(delete);
        this.run.nextState();
    }


    private void cardBuff(EcoCard ecoCard, int buff, List<EcoCard> delete) {
        switch (buff) {
            case GameState.CARD_BUFF_TORTOISE:

                buffTortoiseCard((EcoTortoiseEcoCard) ecoCard, delete);
                break;
            case GameState.CARD_BUFF_STOP:

                buffStopCard(ecoCard, delete);
                break;
        }
    }


    private void buffStopCard(EcoCard ecoCard, List<EcoCard> delete) {

        this.textTip.showTextTip(ecoCard.geteOwner(), ecoCard.geteOwner().getName()
                + " \"Eco stay card\" ，can't move.. ", 2);

        delete.add(ecoCard);
        this.run.nextState();
        new Thread(new MyThread(run, 1)).start();
    }


    private void buffTortoiseCard(EcoTortoiseEcoCard card, List<EcoCard> delete) {
        if (card.getLife() <= 0) {
            delete.add(card);
            return;
        } else {
            card.setLife(card.getLife() - 1);
        }
        this.textTip.showTextTip(card.geteOwner(), card.geteOwner().getName()
                + " \"EcoSlow\" ，only one step.. ", 2);
        this.run.setPoint(0);
    }


    public void useCards() {
        PlayerModel p = this.run.getNowPlayer();
        while (true) {
            if (p.getCards().size() == 0) {

                this.run.nextState();
                break;
            } else {
                Object[] options = new Object[p.getCards().size() + 1];
                int i;
                for (i = 0; i < p.getCards().size(); i++) {
                    options[i] = p.getCards().get(i).getcName() + "\r\n";
                }
                options[i] = "skip";
                int response = JOptionPane.showOptionDialog(null,
                        " " + p.getName() + "，Ep ", " step.",
                        JOptionPane.YES_OPTION, JOptionPane.PLAIN_MESSAGE,
                        null, options, options[0]);
                if (response != i && response != -1) {

                    int th = p.getCards().get(response).useCard();

                    useCard(p.getCards().get(response), th);
                } else {

                    this.run.nextState();
                    break;
                }
            }
        }
    }


    private void useCard(EcoCard ecoCard, int th) {
        switch (th) {
            case GameState.CARD_ADDLEVEL:

                useAddLevelCard(ecoCard);
                break;
            case GameState.CARD_AVERAGERPOOR:

                useAveragerPoorCard(ecoCard);
                break;
            case GameState.CARD_CHANGE:

                useChangeCard(ecoCard);
                break;
            case GameState.CARD_CONTROLDICE:

                useControlDiceCard(ecoCard);
                break;
            case GameState.CARD_HAVE:

                useHaveCard(ecoCard);
                break;
            case GameState.CARD_REDUCELEVEL:

                useReduceLevelCard(ecoCard);
                break;
            case GameState.CARD_ROB:

                useRobCard(ecoCard);
                break;
            case GameState.CARD_STOP:

                useStopCard(ecoCard);
                break;
            case GameState.CARD_TALLAGE:

                useTallageCard(ecoCard);
                break;
            case GameState.CARD_TORTOISE:

                useTortoiseCard(ecoCard);
                break;
            case GameState.CARD_TRAP:

                useTrapCard(ecoCard);
                break;
            case GameState.CARD_CROSSING:

                useCrossingCard(ecoCard);
                break;
        }
    }


    private void useCrossingCard(EcoCard ecoCard) {
        Object[] options1 = {"reselect"};
        JOptionPane.showOptionDialog(null, " auto use.",
                " Ep .", JOptionPane.YES_OPTION,
                JOptionPane.PLAIN_MESSAGE, null, options1,
                options1[0]);
    }


    private void useTrapCard(EcoCard ecoCard) {
        Object[] options = {"confirm", "reselect"};
        int response = JOptionPane.showOptionDialog(null, "confirm\"Eco card\" \""
                        + ecoCard.getOwner().getOtherPlayer().getName() + "\"in HP 2 d?",
                " Ep .", JOptionPane.YES_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, options[0]);
        if (response == 0) {

            PlayerModel cPlayer = ecoCard.getOwner().getOtherPlayer();

            cPlayer.setInPrison(cPlayer.getInPrison() + 2);

            if (LandModel.prison != null) {
                cPlayer.setX(LandModel.prison.x);
                cPlayer.setY(LandModel.prison.y);
            }

            this.textTip
                    .showTextTip(ecoCard.getOwner(), ecoCard.getOwner().getName()
                            + " \"EcoTrapEcoCard\"， \""
                            + ecoCard.getOwner().getOtherPlayer().getName()
                            + "\"in HP 2 d.", 2);

            ecoCard.getOwner().getCards().remove(ecoCard);
        }
    }


    private void useTortoiseCard(EcoCard ecoCard) {
        Object[] options = {ecoCard.getOwner().getName(),
                ecoCard.getOwner().getOtherPlayer().getName(), "reselect"};
        int response = JOptionPane.showOptionDialog(null,
                " EcoTortoiseEcoCard", " Ep .", JOptionPane.YES_OPTION,
                JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        if (response == 0) {
            ecoCard.getOwner().getEffectCards().add(ecoCard);
            ecoCard.seteOwner(ecoCard.getOwner());

            this.textTip.showTextTip(ecoCard.getOwner(), ecoCard.getOwner().getName()
                    + " EcoTortoiseEcoCard", 2);
            ecoCard.getOwner().getCards().remove(ecoCard);
        } else if (response == 1) {
            ecoCard.getOwner().getOtherPlayer().getEffectCards().add(ecoCard);
            ecoCard.seteOwner(ecoCard.getOwner().getOtherPlayer());
            this.textTip.showTextTip(ecoCard.getOwner(), ecoCard.getOwner().getName()
                    + " " + ecoCard.getOwner().getOtherPlayer().getName()
                    + "EcoTortoiseEcoCard", 2);
            ecoCard.getOwner().getCards().remove(ecoCard);
        }
    }


    private void useTallageCard(EcoCard ecoCard) {
        Object[] options = {"confirm", "reselect"};
        int response = JOptionPane.showOptionDialog(null, "confirm\"EcoTallageEcoCard\""
                        + ecoCard.getOwner().getOtherPlayer().getName() + "\"手中gain 10%税款?",
                " Ep .", JOptionPane.YES_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, options[0]);
        if (response == 0) {

            int money = (int) (ecoCard.getOwner().getOtherPlayer().getCash() / 10);
            ecoCard.getOwner().setCash(ecoCard.getOwner().getCash() + money);
            ecoCard.getOwner()
                    .getOtherPlayer()
                    .setCash(ecoCard.getOwner().getOtherPlayer().getCash() - money);

            this.textTip.showTextTip(ecoCard.getOwner(), ecoCard.getOwner().getName()
                    + " EcoTallageEcoCard\""
                    + ecoCard.getOwner().getOtherPlayer().getName()
                    + "\"10% HP", 2);

            ecoCard.getOwner().getCards().remove(ecoCard);
        }
    }


    private void useStopCard(EcoCard ecoCard) {
        Object[] options = {ecoCard.getOwner().getName(),
                ecoCard.getOwner().getOtherPlayer().getName(), "reselect"};
        int response = JOptionPane.showOptionDialog(null,
                " EcoStay\".", " Ep .", JOptionPane.YES_OPTION,
                JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        if (response == 0) {
            ecoCard.getOwner().getEffectCards().add(ecoCard);
            ecoCard.seteOwner(ecoCard.getOwner());

            this.textTip.showTextTip(ecoCard.getOwner(), ecoCard.getOwner().getName()
                    + " EcoStay\". ", 2);
            ecoCard.getOwner().getCards().remove(ecoCard);
        } else if (response == 1) {
            ecoCard.getOwner().getOtherPlayer().getEffectCards().add(ecoCard);
            ecoCard.seteOwner(ecoCard.getOwner().getOtherPlayer());
            this.textTip.showTextTip(ecoCard.getOwner(), ecoCard.getOwner().getName()
                    + " \"" + ecoCard.getOwner().getOtherPlayer().getName()
                    + "\"\"EcoStay\". ", 2);
            ecoCard.getOwner().getCards().remove(ecoCard);
        }
    }


    private void useRobCard(EcoCard ecoCard) {
        if (ecoCard.getOwner().getCards().size() >= PlayerModel.MAX_CAN_HOLD_CARDS) {

            Object[] options = {"reselect"};
            JOptionPane.showOptionDialog(null, " can't use\"EcoRobEcoCard\"",
                    " Ep .", JOptionPane.YES_OPTION,
                    JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        } else if (ecoCard.getOwner().getOtherPlayer().getCards().size() == 0) {

            Object[] options = {"reselect"};
            JOptionPane.showOptionDialog(null, " \""
                            + ecoCard.getOwner().getOtherPlayer().getName()
                            + "\"can't use\"EcoRobEcoCard\"", " Ep .", JOptionPane.YES_OPTION,
                    JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        } else {
            PlayerModel srcPlayer = ecoCard.getOwner().getOtherPlayer();


            EcoCard getEcoCard = srcPlayer.getCards().get((int) (srcPlayer.getCards().size() * Math.random()));

            srcPlayer.getCards().remove(getEcoCard);

            ecoCard.getOwner().getCards().add(getEcoCard);

            getEcoCard.setOwner(ecoCard.getOwner());

            this.textTip.showTextTip(ecoCard.getOwner(), ecoCard.getOwner().getName()
                    + " EcoRobEcoCard\"，\"" + srcPlayer.getName() + "\"\""
                    + getEcoCard.getcName() + ".\". ", 2);

            ecoCard.getOwner().getCards().remove(ecoCard);
        }
    }


    private void useReduceLevelCard(EcoCard ecoCard) {
        EcoPark ecoPark = this.building.getBuilding(
                ecoCard.getOwner().getY() / 60, ecoCard.getOwner().getX() / 60);
        if (ecoPark.getOwner() != null
                && ecoPark.getOwner().equals(ecoCard.getOwner().getOtherPlayer())) {
            if (ecoPark.getEcoParkLevel() > 0) {

                ecoPark.setEcoParkLevel(ecoPark.getEcoParkLevel() - 1);

                this.textTip.showTextTip(ecoCard.getOwner(), ecoCard.getOwner()
                        .getName()
                        + " ReduceLevelCard\"，\""
                        + ecoCard.getOwner().getOtherPlayer().getName()
                        + "\" ", 2);

                ecoCard.getOwner().getCards().remove(ecoCard);
            } else {

                Object[] options = {"reselect"};
                JOptionPane.showOptionDialog(null, " can't", " Ep .",
                        JOptionPane.YES_OPTION, JOptionPane.PLAIN_MESSAGE,
                        null, options, options[0]);
            }
        } else {

            Object[] options = {"reselect"};
            JOptionPane.showOptionDialog(null, " can't .", " Ep .",
                    JOptionPane.YES_OPTION, JOptionPane.PLAIN_MESSAGE, null,
                    options, options[0]);
        }
    }


    private void useHaveCard(EcoCard ecoCard) {

        EcoPark ecoPark = this.building.getBuilding(
                ecoCard.getOwner().getY() / 60, ecoCard.getOwner().getX() / 60);
        if (ecoPark.getOwner() != null
                && ecoPark.getOwner().equals(ecoCard.getOwner().getOtherPlayer())) {
            Object[] options = {"confirm", "reselect"};
            int response = JOptionPane.showOptionDialog(null,
                    "confirm\"EcoHaveEcoCard\"pay：" + ecoPark.getEcoParkAllPrice() + "  EC .",
                    " Ep .", JOptionPane.YES_OPTION,
                    JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
            if (response == 0) {
                if (ecoCard.getOwner().getCash() >= ecoPark.getEcoParkAllPrice()) {

                    ecoPark.getOwner().setCash(
                            ecoPark.getOwner().getCash()
                                    + ecoPark.getEcoParkAllPrice());
                    ecoCard.getOwner().setCash(
                            ecoCard.getOwner().getCash() - ecoPark.getEcoParkAllPrice());
                    ecoPark.setOwner(ecoCard.getOwner());

                    this.textTip.showTextTip(ecoCard.getOwner(), ecoCard.getOwner()
                            .getName() + " EcoHaveEcoCard\"，gain. ", 2);

                    ecoCard.getOwner().getCards().remove(ecoCard);
                } else {
                    Object[] options1 = {"reselect"};
                    JOptionPane.showOptionDialog(null, "  EC not enough，no EP !",
                            " Ep .", JOptionPane.YES_OPTION,
                            JOptionPane.PLAIN_MESSAGE, null, options1,
                            options1[0]);
                }
            }
        } else {
            Object[] options1 = {"reselect"};
            JOptionPane.showOptionDialog(null, "can't use Ep .", " Ep .",
                    JOptionPane.YES_OPTION, JOptionPane.PLAIN_MESSAGE, null,
                    options1, options1[0]);
        }
    }


    private void useControlDiceCard(EcoCard ecoCard) {
        Object[] options = {"1", "2", "3", "4", "5", "6", "reselect"};
        int response = JOptionPane.showOptionDialog(null,
                "confirm\"EcoControlDiceEcoCard\"", " Ep .", JOptionPane.YES_OPTION,
                JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        if (response == -1 || response == 6) {
            return;
        } else {

            this.run.setPoint(response);

            this.textTip.showTextTip(ecoCard.getOwner(), ecoCard.getOwner().getName()
                    + " EcoControlDiceEcoCard\".", 2);

            ecoCard.getOwner().getCards().remove(ecoCard);
        }
    }


    private void useChangeCard(EcoCard ecoCard) {
        EcoPark ecoPark = this.building.getBuilding(
                ecoCard.getOwner().getY() / 60, ecoCard.getOwner().getX() / 60);
        if (ecoPark.getOwner() != null
                && ecoPark.getOwner().equals(ecoCard.getOwner().getOtherPlayer())) {
            Object[] options = {"confirm", "reselect"};
            int response = JOptionPane.showOptionDialog(null,
                    "confirm\"EcoChangeEcoCard\"", " Ep .",
                    JOptionPane.YES_OPTION, JOptionPane.PLAIN_MESSAGE, null,
                    options, options[0]);
            if (response == 0) {

                int thisBuildingLevel = ecoPark.getEcoParkLevel();
                EcoPark changeEcoPark = null;
                for (EcoPark a : ecoCard.getOwner().getBuildings()) {
                    if (a.getEcoParkLevel() == thisBuildingLevel) {
                        changeEcoPark = a;
                        break;
                    }
                }

                if (changeEcoPark != null) {
                    changeEcoPark.setOwner(ecoCard.getOwner().getOtherPlayer());
                    ecoPark.setOwner(ecoCard.getOwner());

                    this.textTip.showTextTip(ecoCard.getOwner(), ecoCard.getOwner()
                            .getName()
                            + " EcoChangeEcoCard\""
                            + ecoCard.getOwner().getOtherPlayer().getName()
                            + ".. ", 2);

                    ecoCard.getOwner().getCards().remove(ecoCard);
                } else {
                    Object[] options1 = {"reselect"};
                    JOptionPane.showOptionDialog(null, " \"EcoChangeEcoCard\"",
                            " Ep .", JOptionPane.YES_OPTION,
                            JOptionPane.PLAIN_MESSAGE, null, options1,
                            options1[0]);
                }
            }
        } else {
            Object[] options = {"reselect"};
            JOptionPane.showOptionDialog(null, " \"EcoChangeEcoCard\"", " Ep .",
                    JOptionPane.YES_OPTION, JOptionPane.PLAIN_MESSAGE, null,
                    options, options[0]);
        }
    }


    private void useAveragerPoorCard(EcoCard ecoCard) {
        Object[] options = {"confirm", "reselect"};
        int response = JOptionPane.showOptionDialog(null,
                "confirm\"AveragerPoorEcoCard\"", " Ep .", JOptionPane.YES_OPTION,
                JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        if (response == 0) {

            int money = (int) (ecoCard.getOwner().getCash() + ecoCard.getOwner()
                    .getOtherPlayer().getCash()) / 2;
            ecoCard.getOwner().setCash(money);
            ecoCard.getOwner().getOtherPlayer().setCash(money);

            this.textTip.showTextTip(ecoCard.getOwner(), ecoCard.getOwner().getName()
                    + " AveragerPoorEcoCard\"，:" + money + "  EC . ", 2);


            ecoCard.getOwner().getCards().remove(ecoCard);
        }
    }


    private void useAddLevelCard(EcoCard ecoCard) {
        EcoPark ecoPark = this.building.getBuilding(
                ecoCard.getOwner().getY() / 60, ecoCard.getOwner().getX() / 60);
        if (ecoPark.getOwner() != null
                && ecoPark.getOwner().equals(ecoCard.getOwner())) {
            if (ecoPark.canEcoParkUpLevel()) {

                ecoPark.setEcoParkLevel(ecoPark.getEcoParkLevel() + 1);

                this.textTip.showTextTip(ecoCard.getOwner(), ecoCard.getOwner()
                        .getName() + " EcoBuildEcoCard\". ", 2);

                ecoCard.getOwner().getCards().remove(ecoCard);
            } else {

                Object[] options = {"reselect"};
                JOptionPane.showOptionDialog(null, " .", " Ep .",
                        JOptionPane.YES_OPTION, JOptionPane.PLAIN_MESSAGE,
                        null, options, options[0]);
            }
        } else {

            Object[] options = {"reselect"};
            JOptionPane.showOptionDialog(null, "  .", " Ep .",
                    JOptionPane.YES_OPTION, JOptionPane.PLAIN_MESSAGE, null,
                    options, options[0]);
        }
    }


    public void exitShop() {
        new Thread(new MyThread(run, 1)).start();
    }


    public void buyCard(EcoShop_ shop) {
        int chooseCard = this.panel.getShop().getChooseCard();
        if (chooseCard >= 0
                && this.panel.getShop().getCard().get(chooseCard) != null) {

            if (this.buyCard(shop, chooseCard)) {

                this.panel.getShop().getCard().get(chooseCard).setEnabled(false);

                this.panel.getShop().setChooseCard(-1);
            }
        }
    }


    public boolean buyCard(EcoShop_ shop, int p) {
        if (this.panel.getShop().getCard().get(p) != null) {
            if (this.run.getNowPlayer().getCards().size() >= PlayerModel.MAX_CAN_HOLD_CARDS) {
                JOptionPane.showMessageDialog(null, ":"
                        + PlayerModel.MAX_CAN_HOLD_CARDS + "Ep !");
                return false;
            }
            if (this.run.getNowPlayer().getNx() < shop.getCards().get(p)
                    .getPrice()) {
                JOptionPane.showMessageDialog(null, ":"
                        + shop.getCards().get(p).getPrice() + " Ec .");
                return false;
            }

            shop.getCards().get(p).setOwner(this.run.getNowPlayer());

            this.run.getNowPlayer().getCards().add(shop.getCards().get(p));

            this.run.getNowPlayer().setNx(
                    this.run.getNowPlayer().getNx()
                            - shop.getCards().get(p).getPrice());
        }
        return true;
    }


    public void gameOver() {
        this.run.setNowPlayerState(GameRunning.GAME_STOP);
        this.panel.getBackgroundUI().moveToFront();
        this.panel.getRunning().moveToFront();
        this.panel.getPlayerInfo().moveToFront();
        this.panel.getEffect().moveToFront();
        this.music.gameOver();
        this.effect.showImg("timeover2");

    }
}
