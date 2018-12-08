/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import ai.abstraction.AbstractAction;
import ai.abstraction.Harvest;
import ai.abstraction.HeavyDefense;
import ai.abstraction.HeavyRush;
import ai.abstraction.LightRush;
import ai.abstraction.RangedRush;
import ai.abstraction.WorkerRush;
import ai.abstraction.pathfinding.AStarPathFinding;
import ai.abstraction.pathfinding.PathFinding;
import ai.core.AI;
import ai.core.ParameterSpecification;
import myAI.Context;
import rts.GameState;
import rts.PhysicalGameState;
import rts.Player;
import rts.PlayerAction;
import rts.units.Unit;
import rts.units.UnitType;
import rts.units.UnitTypeTable;

/**
 *
 * @author santi
 */
public class myPhysicalGameStateJFrame extends JFrame {
    myPhysicalGameStatePanel panel = null;
    JTextArea editor0 = new JTextArea();
    JTextArea editor1 = new JTextArea();
    JTextArea editor2 = new JTextArea();
    JTextArea editor3 = new JTextArea();
    JTextArea editor4 = new JTextArea();
    
    public myPhysicalGameStateJFrame(String title, int dx, int dy, myPhysicalGameStatePanel a_panel) {
        super(title);
        panel = a_panel;
        setLayout(new GridLayout(1,2));
        add(panel); //Adiciona o mapa ao lado esquerdo
        JPanel ladoDireito = new JPanel();
        ladoDireito.setLayout(new GridLayout(3,1));
        
        JTabbedPane tabbedPane = new JTabbedPane();
               
        editor0.setTabSize(4);
        editor1.setTabSize(4);
        editor2.setTabSize(4);
        editor3.setTabSize(4);
        editor4.setTabSize(4);
        editor0.setBorder(BorderFactory.createCompoundBorder(
                panel.getBorder(), 
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        editor1.setBorder(BorderFactory.createCompoundBorder(
                panel.getBorder(), 
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        editor2.setBorder(BorderFactory.createCompoundBorder(
                panel.getBorder(), 
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        editor3.setBorder(BorderFactory.createCompoundBorder(
                panel.getBorder(), 
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        editor4.setBorder(BorderFactory.createCompoundBorder(
                panel.getBorder(), 
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        editor0.setText("package myAI;\r\n" + 
        		"import ai.abstraction.AbstractAction;\r\n" + 
        		"import ai.abstraction.AbstractionLayerAI;\r\n" + 
        		"import ai.abstraction.Harvest;\r\n" + 
        		"import ai.abstraction.pathfinding.AStarPathFinding;\r\n" + 
        		"import ai.abstraction.pathfinding.PathFinding;\r\n" + 
        		"import ai.core.AI;\r\n" + 
        		"import ai.core.ParameterSpecification;\r\n" + 
        		"\r\n" + 
        		"import java.util.ArrayList;\r\n" + 
        		"import java.util.LinkedList;\r\n" + 
        		"import java.util.List;\r\n" + 
        		"import java.util.Random;\r\n" + 
        		"\r\n" + 
        		"import rts.GameState;\r\n" + 
        		"import rts.PhysicalGameState;\r\n" + 
        		"import rts.Player;\r\n" + 
        		"import rts.PlayerAction;\r\n" + 
        		"import rts.units.Unit;\r\n" + 
        		"import rts.units.UnitType;\r\n" + 
        		"import rts.units.UnitTypeTable;"
		+ "\n"
		+ "public class MyNewAI_0 extends AbstractionLayerAI {\n" + 
		"Random r = new Random();\r\n" + 
		"    protected UnitTypeTable utt;\r\n" + 
		"    UnitType workerType;\r\n" + 
		"    UnitType baseType;\r\n" + 
		"\r\n" + 
		"    // Strategy implemented by this class:\r\n" + 
		"    // If we have more than 1 \"Worker\": send the extra workers to attack to the nearest enemy unit\r\n" + 
		"    // If we have a base: train workers non-stop\r\n" + 
		"    // If we have a worker: do this if needed: build base, harvest resources\r\n" + 
		"    public MyNewAI_0(UnitTypeTable a_utt) {\r\n" + 
		"        this(a_utt, new AStarPathFinding());\r\n" + 
		"    }\r\n" + 
		"\r\n" + 
		"        \r\n" + 
		"    public MyNewAI_0(UnitTypeTable a_utt, PathFinding a_pf) {\r\n" + 
		"        super(a_pf);\r\n" + 
		"        reset(a_utt);\r\n" + 
		"    }\r\n" + 
		"    \r\n" + 
		"    public void reset() {\r\n" + 
		"    	super.reset();\r\n" + 
		"    }\r\n" + 
		"    \r\n" + 
		"    public void reset(UnitTypeTable a_utt)  \r\n" + 
		"    {\r\n" + 
		"        utt = a_utt;\r\n" + 
		"        if (utt!=null) {\r\n" + 
		"            workerType = utt.getUnitType(\"Worker\");\r\n" + 
		"            baseType = utt.getUnitType(\"Base\");\r\n" + 
		"        }\r\n" + 
		"    }   \r\n" + 
		"    \r\n" + 
		"    \r\n" + 
		"    public AI clone() {\r\n" + 
		"        return new MyNewAI_0(utt, pf);\r\n" + 
		"    }\r\n" + 
		"\t//Construtor necessario para criacao de classe em tempo de execucao\n" + 
		"\tpublic MyNewAI_0() {\n" + 
		"\t\tsuper(new AStarPathFinding());\n" + 
		"\t\treset(Context.getInstance().getUtt());\r\n" +
		"\t}\n" +
		"    \r\n" + 
		"    public PlayerAction getAction(int player, GameState gs) {\r\n" + 
		"        PhysicalGameState pgs = gs.getPhysicalGameState();\r\n" + 
		"        Player p = gs.getPlayer(player);\r\n" + 
		"        PlayerAction pa = new PlayerAction();\r\n" + 
		"//        System.out.println(\"LightRushAI for player \" + player + \" (cycle \" + gs.getTime() + \")\");\r\n" + 
		"                \r\n" + 
		"        // behavior of bases:\r\n" + 
		"        for(Unit u:pgs.getUnits()) {\r\n" + 
		"            if (u.getType()==baseType && \r\n" + 
		"                u.getPlayer() == player && \r\n" + 
		"                gs.getActionAssignment(u)==null) {\r\n" + 
		"                baseBehavior(u,p,pgs);\r\n" + 
		"            }\r\n" + 
		"        }\r\n" + 
		"\r\n" + 
		"        // behavior of melee units:\r\n" + 
		"        for(Unit u:pgs.getUnits()) {\r\n" + 
		"            if (u.getType().canAttack && !u.getType().canHarvest && \r\n" + 
		"                u.getPlayer() == player && \r\n" + 
		"                gs.getActionAssignment(u)==null) {\r\n" + 
		"                meleeUnitBehavior(u,p,gs);\r\n" + 
		"            }        \r\n" + 
		"        }\r\n" + 
		"\r\n" + 
		"        // behavior of workers:\r\n" + 
		"        List<Unit> workers = new LinkedList<Unit>();\r\n" + 
		"        for(Unit u:pgs.getUnits()) {\r\n" + 
		"            if (u.getType().canHarvest && \r\n" + 
		"                u.getPlayer() == player) {\r\n" + 
		"                workers.add(u);\r\n" + 
		"            }        \r\n" + 
		"        }\r\n" + 
		"        workersBehavior(workers,p,gs);\r\n" + 
		"        \r\n" + 
		"                \r\n" + 
		"        return translateActions(player,gs);\r\n" + 
		"    }\r\n" + 
		"    \r\n" + 
		"    \r\n" + 
		"    public void baseBehavior(Unit u,Player p, PhysicalGameState pgs) {\r\n" + 
		"        if (p.getResources()>=workerType.cost) train(u, workerType);\r\n" + 
		"    }\r\n" + 
		"    \r\n" + 
		"    public void meleeUnitBehavior(Unit u, Player p, GameState gs) {\r\n" + 
		"        PhysicalGameState pgs = gs.getPhysicalGameState();\r\n" + 
		"        Unit closestEnemy = null;\r\n" + 
		"        int closestDistance = 0;\r\n" + 
		"        for(Unit u2:pgs.getUnits()) {\r\n" + 
		"            if (u2.getPlayer()>=0 && u2.getPlayer()!=p.getID()) { \r\n" + 
		"                int d = Math.abs(u2.getX() - u.getX()) + Math.abs(u2.getY() - u.getY());\r\n" + 
		"                if (closestEnemy==null || d<closestDistance) {\r\n" + 
		"                    closestEnemy = u2;\r\n" + 
		"                    closestDistance = d;\r\n" + 
		"                }\r\n" + 
		"            }\r\n" + 
		"        }\r\n" + 
		"        if (closestEnemy!=null) {\r\n" + 
		"            attack(u,closestEnemy);\r\n" + 
		"        }\r\n" + 
		"    }\r\n" + 
		"    \r\n" + 
		"    public void workersBehavior(List<Unit> workers,Player p, GameState gs) {\r\n" + 
		"        PhysicalGameState pgs = gs.getPhysicalGameState();\r\n" + 
		"        int nbases = 0;\r\n" + 
		"        int resourcesUsed = 0;\r\n" + 
		"        Unit harvestWorker = null;\r\n" + 
		"        List<Unit> freeWorkers = new LinkedList<Unit>();\r\n" + 
		"        freeWorkers.addAll(workers);\r\n" + 
		"        \r\n" + 
		"        if (workers.isEmpty()) return;\r\n" + 
		"        \r\n" + 
		"        for(Unit u2:pgs.getUnits()) {\r\n" + 
		"            if (u2.getType() == baseType && \r\n" + 
		"                u2.getPlayer() == p.getID()) nbases++;\r\n" + 
		"        }\r\n" + 
		"        \r\n" + 
		"        List<Integer> reservedPositions = new LinkedList<Integer>();\r\n" + 
		"        if (nbases==0 && !freeWorkers.isEmpty()) {\r\n" + 
		"            // build a base:\r\n" + 
		"            if (p.getResources()>=baseType.cost + resourcesUsed) {\r\n" + 
		"                Unit u = freeWorkers.remove(0);\r\n" + 
		"                buildIfNotAlreadyBuilding(u,baseType,u.getX(),u.getY(),reservedPositions,p,pgs);\r\n" + 
		"                resourcesUsed+=baseType.cost;\r\n" + 
		"            }\r\n" + 
		"        }\r\n" + 
		"        \r\n" + 
		"        if (freeWorkers.size()>0) harvestWorker = freeWorkers.remove(0);\r\n" + 
		"        \r\n" + 
		"        // harvest with the harvest worker:\r\n" + 
		"        if (harvestWorker!=null) {\r\n" + 
		"            Unit closestBase = null;\r\n" + 
		"            Unit closestResource = null;\r\n" + 
		"            int closestDistance = 0;\r\n" + 
		"            for(Unit u2:pgs.getUnits()) {\r\n" + 
		"                if (u2.getType().isResource) { \r\n" + 
		"                    int d = Math.abs(u2.getX() - harvestWorker.getX()) + Math.abs(u2.getY() - harvestWorker.getY());\r\n" + 
		"                    if (closestResource==null || d<closestDistance) {\r\n" + 
		"                        closestResource = u2;\r\n" + 
		"                        closestDistance = d;\r\n" + 
		"                    }\r\n" + 
		"                }\r\n" + 
		"            }\r\n" + 
		"            closestDistance = 0;\r\n" + 
		"            for(Unit u2:pgs.getUnits()) {\r\n" + 
		"                if (u2.getType().isStockpile && u2.getPlayer()==p.getID()) { \r\n" + 
		"                    int d = Math.abs(u2.getX() - harvestWorker.getX()) + Math.abs(u2.getY() - harvestWorker.getY());\r\n" + 
		"                    if (closestBase==null || d<closestDistance) {\r\n" + 
		"                        closestBase = u2;\r\n" + 
		"                        closestDistance = d;\r\n" + 
		"                    }\r\n" + 
		"                }\r\n" + 
		"            }\r\n" + 
		"            if (closestResource!=null && closestBase!=null) {\r\n" + 
		"                AbstractAction aa = getAbstractAction(harvestWorker);\r\n" + 
		"                if (aa instanceof Harvest) {\r\n" + 
		"                    Harvest h_aa = (Harvest)aa;\r\n" + 
		"                    if (h_aa.getTarget() != closestResource || h_aa.getBase()!=closestBase) harvest(harvestWorker, closestResource, closestBase);\n" + 
		"                } else {\r\n" + 
		"                    harvest(harvestWorker, closestResource, closestBase);\r\n" + 
		"                }\r\n" + 
		"            }\r\n" + 
		"        }\r\n" + 
		"        \r\n" + 
		"        for(Unit u:freeWorkers) meleeUnitBehavior(u, p, gs);\r\n" + 
		"        \r\n" + 
		"    }\r\n" + 
		"    \r\n" + 
		"    \r\n" + 
		"    @Override\r\n" + 
		"    public List<ParameterSpecification> getParameters()\r\n" + 
		"    {\r\n" + 
		"        List<ParameterSpecification> parameters = new ArrayList<>();\r\n" + 
		"        \r\n" + 
		"        parameters.add(new ParameterSpecification(\"PathFinding\", PathFinding.class, new AStarPathFinding()));\r\n" + 
		"\r\n" + 
		"        return parameters;\r\n" + 
		"    }"
		+ "}");

        editor1.setText("package myAI;\r\n" + 
        		"import ai.abstraction.AbstractAction;\r\n" + 
        		"import ai.abstraction.AbstractionLayerAI;\r\n" + 
        		"import ai.abstraction.Harvest;\r\n" + 
        		"import ai.abstraction.pathfinding.AStarPathFinding;\r\n" + 
        		"import ai.abstraction.pathfinding.PathFinding;\r\n" + 
        		"import ai.core.AI;\r\n" + 
        		"import ai.core.ParameterSpecification;\r\n" + 
        		"\r\n" + 
        		"import java.util.ArrayList;\r\n" + 
        		"import java.util.LinkedList;\r\n" + 
        		"import java.util.List;\r\n" + 
        		"import java.util.Random;\r\n" + 
        		"\r\n" + 
        		"import rts.GameState;\r\n" + 
        		"import rts.PhysicalGameState;\r\n" + 
        		"import rts.Player;\r\n" + 
        		"import rts.PlayerAction;\r\n" + 
        		"import rts.units.Unit;\r\n" + 
        		"import rts.units.UnitType;\r\n" + 
        		"import rts.units.UnitTypeTable;"
        		+ "\n"
        		+ "public class MyNewAI_1 extends AbstractionLayerAI {\n" + 
        		"Random r = new Random();\r\n" + 
        		"    protected UnitTypeTable utt;\r\n" + 
        		"    UnitType workerType;\r\n" + 
        		"    UnitType baseType;\r\n" + 
        		"    UnitType barracksType;\r\n" + 
        		"    UnitType lightType;\r\n" + 
        		"\r\n" + 
        		"    // Strategy implemented by this class:\r\n" + 
        		"    // If we have any \"light\": send it to attack to the nearest enemy unit\r\n" + 
        		"    // If we have a base: train worker until we have 1 workers\r\n" + 
        		"    // If we have a barracks: train light\r\n" + 
        		"    // If we have a worker: do this if needed: build base, build barracks, harvest resources\r\n" + 
        		"\r\n" + 
        		"    public MyNewAI_1(UnitTypeTable a_utt) {\r\n" + 
        		"        this(a_utt, new AStarPathFinding());\r\n" + 
        		"    }\r\n" + 
        		"    \r\n" + 
        		"    \r\n" + 
        		"    public MyNewAI_1(UnitTypeTable a_utt, PathFinding a_pf) {\r\n" + 
        		"        super(a_pf);\r\n" + 
        		"        reset(a_utt);\r\n" + 
        		"    }\r\n" + 
        		"\t//Construtor necessario para criacao de classe em tempo de execucao\n" + 
        		"\tpublic MyNewAI_1() {\n" + 
        		"\t\tsuper(new AStarPathFinding());\n" + 
        		"\t\treset(Context.getInstance().getUtt());\r\n" +
        		"\t}\n" +
        		"\r\n" + 
        		"    public void reset() {\r\n" + 
        		"    	super.reset();\r\n" + 
        		"    }\r\n" + 
        		"    \r\n" + 
        		"    public void reset(UnitTypeTable a_utt)  \r\n" + 
        		"    {\r\n" + 
        		"        utt = a_utt;\r\n" + 
        		"        workerType = utt.getUnitType(\"Worker\");\r\n" + 
        		"        baseType = utt.getUnitType(\"Base\");\r\n" + 
        		"        barracksType = utt.getUnitType(\"Barracks\");\r\n" + 
        		"        lightType = utt.getUnitType(\"Light\");\r\n" + 
        		"    }   \r\n" + 
        		"    \r\n" + 
        		"\r\n" + 
        		"    public AI clone() {\r\n" + 
        		"        return new MyNewAI_1(utt, pf);\r\n" + 
        		"    }\r\n" + 
        		"\r\n" + 
        		"    /*\r\n" + 
        		"        This is the main function of the AI. It is called at each game cycle with the most up to date game state and\r\n" + 
        		"        returns which actions the AI wants to execute in this cycle.\r\n" + 
        		"        The input parameters are:\r\n" + 
        		"        - player: the player that the AI controls (0 or 1)\r\n" + 
        		"        - gs: the current game state\r\n" + 
        		"        This method returns the actions to be sent to each of the units in the gamestate controlled by the player,\r\n" + 
        		"        packaged as a PlayerAction.\r\n" + 
        		"     */\r\n" + 
        		"    public PlayerAction getAction(int player, GameState gs) {\r\n" + 
        		"        PhysicalGameState pgs = gs.getPhysicalGameState();\r\n" + 
        		"        Player p = gs.getPlayer(player);\r\n" + 
        		"//        System.out.println(\"LightRushAI for player \" + player + \" (cycle \" + gs.getTime() + \")\");\r\n" + 
        		"\r\n" + 
        		"        // behavior of bases:\r\n" + 
        		"        for (Unit u : pgs.getUnits()) {\r\n" + 
        		"            if (u.getType() == baseType\r\n" + 
        		"                    && u.getPlayer() == player\r\n" + 
        		"                    && gs.getActionAssignment(u) == null) {\r\n" + 
        		"                baseBehavior(u, p, pgs);\r\n" + 
        		"            }\r\n" + 
        		"        }\r\n" + 
        		"\r\n" + 
        		"        // behavior of barracks:\r\n" + 
        		"        for (Unit u : pgs.getUnits()) {\r\n" + 
        		"            if (u.getType() == barracksType\r\n" + 
        		"                    && u.getPlayer() == player\r\n" + 
        		"                    && gs.getActionAssignment(u) == null) {\r\n" + 
        		"                barracksBehavior(u, p, pgs);\r\n" + 
        		"            }\r\n" + 
        		"        }\r\n" + 
        		"\r\n" + 
        		"        // behavior of melee units:\r\n" + 
        		"        for (Unit u : pgs.getUnits()) {\r\n" + 
        		"            if (u.getType().canAttack && !u.getType().canHarvest\r\n" + 
        		"                    && u.getPlayer() == player\r\n" + 
        		"                    && gs.getActionAssignment(u) == null) {\r\n" + 
        		"                meleeUnitBehavior(u, p, gs);\r\n" + 
        		"            }\r\n" + 
        		"        }\r\n" + 
        		"\r\n" + 
        		"        // behavior of workers:\r\n" + 
        		"        List<Unit> workers = new LinkedList<Unit>();\r\n" + 
        		"        for (Unit u : pgs.getUnits()) {\r\n" + 
        		"            if (u.getType().canHarvest\r\n" + 
        		"                    && u.getPlayer() == player) {\r\n" + 
        		"                workers.add(u);\r\n" + 
        		"            }\r\n" + 
        		"        }\r\n" + 
        		"        workersBehavior(workers, p, pgs);\r\n" + 
        		"\r\n" + 
        		"        // This method simply takes all the unit actions executed so far, and packages them into a PlayerAction\r\n" + 
        		"        return translateActions(player, gs);\r\n" + 
        		"    }\r\n" + 
        		"\r\n" + 
        		"    public void baseBehavior(Unit u, Player p, PhysicalGameState pgs) {\r\n" + 
        		"        int nworkers = 0;\r\n" + 
        		"        for (Unit u2 : pgs.getUnits()) {\r\n" + 
        		"            if (u2.getType() == workerType\r\n" + 
        		"                    && u2.getPlayer() == p.getID()) {\r\n" + 
        		"                nworkers++;\r\n" + 
        		"            }\r\n" + 
        		"        }\r\n" + 
        		"        if (nworkers < 1 && p.getResources() >= workerType.cost) {\r\n" + 
        		"            train(u, workerType);\r\n" + 
        		"        }\r\n" + 
        		"    }\r\n" + 
        		"\r\n" + 
        		"    public void barracksBehavior(Unit u, Player p, PhysicalGameState pgs) {\r\n" + 
        		"        if (p.getResources() >= lightType.cost) {\r\n" + 
        		"            train(u, lightType);\r\n" + 
        		"        }\r\n" + 
        		"    }\r\n" + 
        		"\r\n" + 
        		"    public void meleeUnitBehavior(Unit u, Player p, GameState gs) {\r\n" + 
        		"        PhysicalGameState pgs = gs.getPhysicalGameState();\r\n" + 
        		"        Unit closestEnemy = null;\r\n" + 
        		"        int closestDistance = 0;\r\n" + 
        		"        for (Unit u2 : pgs.getUnits()) {\r\n" + 
        		"            if (u2.getPlayer() >= 0 && u2.getPlayer() != p.getID()) {\r\n" + 
        		"                int d = Math.abs(u2.getX() - u.getX()) + Math.abs(u2.getY() - u.getY());\r\n" + 
        		"                if (closestEnemy == null || d < closestDistance) {\r\n" + 
        		"                    closestEnemy = u2;\r\n" + 
        		"                    closestDistance = d;\r\n" + 
        		"                }\r\n" + 
        		"            }\r\n" + 
        		"        }\r\n" + 
        		"        if (closestEnemy != null) {\r\n" + 
        		"//            System.out.println(\"LightRushAI.meleeUnitBehavior: \" + u + \" attacks \" + closestEnemy);\r\n" + 
        		"            attack(u, closestEnemy);\r\n" + 
        		"        }\r\n" + 
        		"    }\r\n" + 
        		"\r\n" + 
        		"    public void workersBehavior(List<Unit> workers, Player p, PhysicalGameState pgs) {\r\n" + 
        		"        int nbases = 0;\r\n" + 
        		"        int nbarracks = 0;\r\n" + 
        		"\r\n" + 
        		"        int resourcesUsed = 0;\r\n" + 
        		"        List<Unit> freeWorkers = new LinkedList<Unit>();\r\n" + 
        		"        freeWorkers.addAll(workers);\r\n" + 
        		"\r\n" + 
        		"        if (workers.isEmpty()) {\r\n" + 
        		"            return;\r\n" + 
        		"        }\r\n" + 
        		"\r\n" + 
        		"        for (Unit u2 : pgs.getUnits()) {\r\n" + 
        		"            if (u2.getType() == baseType\r\n" + 
        		"                    && u2.getPlayer() == p.getID()) {\r\n" + 
        		"                nbases++;\r\n" + 
        		"            }\r\n" + 
        		"            if (u2.getType() == barracksType\r\n" + 
        		"                    && u2.getPlayer() == p.getID()) {\r\n" + 
        		"                nbarracks++;\r\n" + 
        		"            }\r\n" + 
        		"        }\r\n" + 
        		"\r\n" + 
        		"        List<Integer> reservedPositions = new LinkedList<Integer>();\r\n" + 
        		"        if (nbases == 0 && !freeWorkers.isEmpty()) {\r\n" + 
        		"            // build a base:\r\n" + 
        		"            if (p.getResources() >= baseType.cost + resourcesUsed) {\r\n" + 
        		"                Unit u = freeWorkers.remove(0);\r\n" + 
        		"                buildIfNotAlreadyBuilding(u,baseType,u.getX(),u.getY(),reservedPositions,p,pgs);\r\n" + 
        		"                resourcesUsed += baseType.cost;\r\n" + 
        		"            }\r\n" + 
        		"        }\r\n" + 
        		"\r\n" + 
        		"        if (nbarracks == 0) {\r\n" + 
        		"            // build a barracks:\r\n" + 
        		"            if (p.getResources() >= barracksType.cost + resourcesUsed && !freeWorkers.isEmpty()) {\r\n" + 
        		"                Unit u = freeWorkers.remove(0);\r\n" + 
        		"                buildIfNotAlreadyBuilding(u,barracksType,u.getX(),u.getY(),reservedPositions,p,pgs);\r\n" + 
        		"                resourcesUsed += barracksType.cost;\r\n" + 
        		"            }\r\n" + 
        		"        }\r\n" + 
        		"\r\n" + 
        		"\r\n" + 
        		"        // harvest with all the free workers:\r\n" + 
        		"        for (Unit u : freeWorkers) {\r\n" + 
        		"            Unit closestBase = null;\r\n" + 
        		"            Unit closestResource = null;\r\n" + 
        		"            int closestDistance = 0;\r\n" + 
        		"            for (Unit u2 : pgs.getUnits()) {\r\n" + 
        		"                if (u2.getType().isResource) {\r\n" + 
        		"                    int d = Math.abs(u2.getX() - u.getX()) + Math.abs(u2.getY() - u.getY());\r\n" + 
        		"                    if (closestResource == null || d < closestDistance) {\r\n" + 
        		"                        closestResource = u2;\r\n" + 
        		"                        closestDistance = d;\r\n" + 
        		"                    }\r\n" + 
        		"                }\r\n" + 
        		"            }\r\n" + 
        		"            closestDistance = 0;\r\n" + 
        		"            for (Unit u2 : pgs.getUnits()) {\r\n" + 
        		"                if (u2.getType().isStockpile && u2.getPlayer()==p.getID()) {\r\n" + 
        		"                    int d = Math.abs(u2.getX() - u.getX()) + Math.abs(u2.getY() - u.getY());\r\n" + 
        		"                    if (closestBase == null || d < closestDistance) {\r\n" + 
        		"                        closestBase = u2;\r\n" + 
        		"                        closestDistance = d;\r\n" + 
        		"                    }\r\n" + 
        		"                }\r\n" + 
        		"            }\r\n" + 
        		"            if (closestResource != null && closestBase != null) {\r\n" + 
        		"                AbstractAction aa = getAbstractAction(u);\r\n" + 
        		"                if (aa instanceof Harvest) {\r\n" + 
        		"                    Harvest h_aa = (Harvest)aa;\r\n" + 
        		"                    if (h_aa.getTarget() != closestResource || h_aa.getBase()!=closestBase) harvest(u, closestResource, closestBase);\r\n" + 
        		"                } else {\r\n" + 
        		"                    harvest(u, closestResource, closestBase);\r\n" + 
        		"                }\r\n" + 
        		"            }\r\n" + 
        		"        }\r\n" + 
        		"    }\r\n" + 
        		"\r\n" + 
        		"    \r\n" + 
        		"    @Override\r\n" + 
        		"    public List<ParameterSpecification> getParameters()\r\n" + 
        		"    {\r\n" + 
        		"        List<ParameterSpecification> parameters = new ArrayList<>();\r\n" + 
        		"        \r\n" + 
        		"        parameters.add(new ParameterSpecification(\"PathFinding\", PathFinding.class, new AStarPathFinding()));\r\n" + 
        		"\r\n" + 
        		"        return parameters;\r\n" + 
        		"    }" + 
        		"}");
        
        editor2.setText("package myAI;\r\n" + 
        		"import ai.abstraction.AbstractAction;\r\n" + 
        		"import ai.abstraction.AbstractionLayerAI;\r\n" + 
        		"import ai.abstraction.Harvest;\r\n" + 
        		"import ai.abstraction.pathfinding.AStarPathFinding;\r\n" + 
        		"import ai.abstraction.pathfinding.PathFinding;\r\n" + 
        		"import ai.core.AI;\r\n" + 
        		"import ai.core.ParameterSpecification;\r\n" + 
        		"\r\n" + 
        		"import java.util.ArrayList;\r\n" + 
        		"import java.util.LinkedList;\r\n" + 
        		"import java.util.List;\r\n" + 
        		"import java.util.Random;\r\n" + 
        		"\r\n" + 
        		"import rts.GameState;\r\n" + 
        		"import rts.PhysicalGameState;\r\n" + 
        		"import rts.Player;\r\n" + 
        		"import rts.PlayerAction;\r\n" + 
        		"import rts.units.Unit;\r\n" + 
        		"import rts.units.UnitType;\r\n" + 
        		"import rts.units.UnitTypeTable;"
        		+ "\n"
        		+ "public class MyNewAI_2 extends AbstractionLayerAI {\n" + 
        		"Random r = new Random();\r\n" + 
        		"    protected UnitTypeTable utt;\r\n" + 
        		"    UnitType workerType;\r\n" + 
        		"    UnitType baseType;\r\n" + 
        		"    UnitType barracksType;\r\n" + 
        		"    UnitType rangedType;\r\n" + 
        		"\r\n" + 
        		"    // If we have any \"light\": send it to attack to the nearest enemy unit\r\n" + 
        		"    // If we have a base: train worker until we have 1 workers\r\n" + 
        		"    // If we have a barracks: train light\r\n" + 
        		"    // If we have a worker: do this if needed: build base, build barracks, harvest resources\r\n" + 
        		"    public MyNewAI_2(UnitTypeTable a_utt) {\r\n" + 
        		"        this(a_utt, new AStarPathFinding());\r\n" + 
        		"    }\r\n" + 
        		"\r\n" + 
        		"\r\n" + 
        		"    public MyNewAI_2(UnitTypeTable a_utt, PathFinding a_pf) {\r\n" + 
        		"        super(a_pf);\r\n" + 
        		"        reset(a_utt);\r\n" + 
        		"    }\r\n" + 
        		"\t//Construtor necessario para criacao de classe em tempo de execucao\n" + 
        		"\tpublic MyNewAI_2() {\n" + 
        		"\t\tsuper(new AStarPathFinding());\n" +
        		"\t\treset(Context.getInstance().getUtt());\r\n" +
        		"\t}\n" +
        		"\r\n" + 
        		"    public void reset() {\r\n" + 
        		"    	super.reset();\r\n" + 
        		"    }\r\n" + 
        		"    \r\n" + 
        		"    public void reset(UnitTypeTable a_utt) {\r\n" + 
        		"        utt = a_utt;\r\n" + 
        		"        workerType = utt.getUnitType(\"Worker\");\r\n" + 
        		"        baseType = utt.getUnitType(\"Base\");\r\n" + 
        		"        barracksType = utt.getUnitType(\"Barracks\");\r\n" + 
        		"        rangedType = utt.getUnitType(\"Ranged\");\r\n" + 
        		"    }\r\n" + 
        		"\r\n" + 
        		"    public AI clone() {\r\n" + 
        		"        return new MyNewAI_2(utt, pf);\r\n" + 
        		"    }\r\n" + 
        		"\r\n" + 
        		"    public PlayerAction getAction(int player, GameState gs) {\r\n" + 
        		"        PhysicalGameState pgs = gs.getPhysicalGameState();\r\n" + 
        		"        Player p = gs.getPlayer(player);\r\n" + 
        		"//        System.out.println(\"LightRushAI for player \" + player + \" (cycle \" + gs.getTime() + \")\");\r\n" + 
        		"\r\n" + 
        		"        // behavior of bases:\r\n" + 
        		"        for (Unit u : pgs.getUnits()) {\r\n" + 
        		"            if (u.getType() == baseType\r\n" + 
        		"                    && u.getPlayer() == player\r\n" + 
        		"                    && gs.getActionAssignment(u) == null) {\r\n" + 
        		"                baseBehavior(u, p, pgs);\r\n" + 
        		"            }\r\n" + 
        		"        }\r\n" + 
        		"\r\n" + 
        		"        // behavior of barracks:\r\n" + 
        		"        for (Unit u : pgs.getUnits()) {\r\n" + 
        		"            if (u.getType() == barracksType\r\n" + 
        		"                    && u.getPlayer() == player\r\n" + 
        		"                    && gs.getActionAssignment(u) == null) {\r\n" + 
        		"                barracksBehavior(u, p, pgs);\r\n" + 
        		"            }\r\n" + 
        		"        }\r\n" + 
        		"\r\n" + 
        		"        // behavior of melee units:\r\n" + 
        		"        for (Unit u : pgs.getUnits()) {\r\n" + 
        		"            if (u.getType().canAttack && !u.getType().canHarvest\r\n" + 
        		"                    && u.getPlayer() == player\r\n" + 
        		"                    && gs.getActionAssignment(u) == null) {\r\n" + 
        		"                meleeUnitBehavior(u, p, gs);\r\n" + 
        		"            }\r\n" + 
        		"        }\r\n" + 
        		"\r\n" + 
        		"        // behavior of workers:\r\n" + 
        		"        List<Unit> workers = new LinkedList<Unit>();\r\n" + 
        		"        for (Unit u : pgs.getUnits()) {\r\n" + 
        		"            if (u.getType().canHarvest\r\n" + 
        		"                    && u.getPlayer() == player) {\r\n" + 
        		"                workers.add(u);\r\n" + 
        		"            }\r\n" + 
        		"        }\r\n" + 
        		"        workersBehavior(workers, p, pgs);\r\n" + 
        		"\r\n" + 
        		"\r\n" + 
        		"        return translateActions(player, gs);\r\n" + 
        		"    }\r\n" + 
        		"\r\n" + 
        		"    public void baseBehavior(Unit u, Player p, PhysicalGameState pgs) {\r\n" + 
        		"        int nworkers = 0;\r\n" + 
        		"        for (Unit u2 : pgs.getUnits()) {\r\n" + 
        		"            if (u2.getType() == workerType\r\n" + 
        		"                    && u2.getPlayer() == p.getID()) {\r\n" + 
        		"                nworkers++;\r\n" + 
        		"            }\r\n" + 
        		"        }\r\n" + 
        		"        if (nworkers < 1 && p.getResources() >= workerType.cost) {\r\n" + 
        		"            train(u, workerType);\r\n" + 
        		"        }\r\n" + 
        		"    }\r\n" + 
        		"\r\n" + 
        		"    public void barracksBehavior(Unit u, Player p, PhysicalGameState pgs) {\r\n" + 
        		"        if (p.getResources() >= rangedType.cost) {\r\n" + 
        		"            train(u, rangedType);\r\n" + 
        		"        }\r\n" + 
        		"    }\r\n" + 
        		"\r\n" + 
        		"    public void meleeUnitBehavior(Unit u, Player p, GameState gs) {\r\n" + 
        		"        PhysicalGameState pgs = gs.getPhysicalGameState();\r\n" + 
        		"        Unit closestEnemy = null;\r\n" + 
        		"        int closestDistance = 0;\r\n" + 
        		"        for (Unit u2 : pgs.getUnits()) {\r\n" + 
        		"            if (u2.getPlayer() >= 0 && u2.getPlayer() != p.getID()) {\r\n" + 
        		"                int d = Math.abs(u2.getX() - u.getX()) + Math.abs(u2.getY() - u.getY());\r\n" + 
        		"                if (closestEnemy == null || d < closestDistance) {\r\n" + 
        		"                    closestEnemy = u2;\r\n" + 
        		"                    closestDistance = d;\r\n" + 
        		"                }\r\n" + 
        		"            }\r\n" + 
        		"        }\r\n" + 
        		"        if (closestEnemy != null) {\r\n" + 
        		"//            System.out.println(\"LightRushAI.meleeUnitBehavior: \" + u + \" attacks \" + closestEnemy);\r\n" + 
        		"            attack(u, closestEnemy);\r\n" + 
        		"        }\r\n" + 
        		"    }\r\n" + 
        		"\r\n" + 
        		"    public void workersBehavior(List<Unit> workers, Player p, PhysicalGameState pgs) {\r\n" + 
        		"        int nbases = 0;\r\n" + 
        		"        int nbarracks = 0;\r\n" + 
        		"\r\n" + 
        		"        int resourcesUsed = 0;\r\n" + 
        		"        List<Unit> freeWorkers = new LinkedList<Unit>();\r\n" + 
        		"        freeWorkers.addAll(workers);\r\n" + 
        		"\r\n" + 
        		"        if (workers.isEmpty()) {\r\n" + 
        		"            return;\r\n" + 
        		"        }\r\n" + 
        		"\r\n" + 
        		"        for (Unit u2 : pgs.getUnits()) {\r\n" + 
        		"            if (u2.getType() == baseType\r\n" + 
        		"                    && u2.getPlayer() == p.getID()) {\r\n" + 
        		"                nbases++;\r\n" + 
        		"            }\r\n" + 
        		"            if (u2.getType() == barracksType\r\n" + 
        		"                    && u2.getPlayer() == p.getID()) {\r\n" + 
        		"                nbarracks++;\r\n" + 
        		"            }\r\n" + 
        		"        }\r\n" + 
        		"\r\n" + 
        		"        List<Integer> reservedPositions = new LinkedList<Integer>();\r\n" + 
        		"        if (nbases == 0 && !freeWorkers.isEmpty()) {\r\n" + 
        		"            // build a base:\r\n" + 
        		"            if (p.getResources() >= baseType.cost + resourcesUsed) {\r\n" + 
        		"                Unit u = freeWorkers.remove(0);\r\n" + 
        		"                buildIfNotAlreadyBuilding(u,baseType,u.getX(),u.getY(),reservedPositions,p,pgs);\r\n" + 
        		"                resourcesUsed += baseType.cost;\r\n" + 
        		"            }\r\n" + 
        		"        }\r\n" + 
        		"\r\n" + 
        		"        if (nbarracks == 0 && !freeWorkers.isEmpty()) {\r\n" + 
        		"            // build a barracks:\r\n" + 
        		"            if (p.getResources() >= barracksType.cost + resourcesUsed) {\r\n" + 
        		"                Unit u = freeWorkers.remove(0);\r\n" + 
        		"                buildIfNotAlreadyBuilding(u,barracksType,u.getX(),u.getY(),reservedPositions,p,pgs);\r\n" + 
        		"                resourcesUsed += barracksType.cost;\r\n" + 
        		"            }\r\n" + 
        		"        }\r\n" + 
        		"\r\n" + 
        		"\r\n" + 
        		"        // harvest with all the free workers:\r\n" + 
        		"        for (Unit u : freeWorkers) {\r\n" + 
        		"            Unit closestBase = null;\r\n" + 
        		"            Unit closestResource = null;\r\n" + 
        		"            int closestDistance = 0;\r\n" + 
        		"            for (Unit u2 : pgs.getUnits()) {\r\n" + 
        		"                if (u2.getType().isResource) {\r\n" + 
        		"                    int d = Math.abs(u2.getX() - u.getX()) + Math.abs(u2.getY() - u.getY());\r\n" + 
        		"                    if (closestResource == null || d < closestDistance) {\r\n" + 
        		"                        closestResource = u2;\r\n" + 
        		"                        closestDistance = d;\r\n" + 
        		"                    }\r\n" + 
        		"                }\r\n" + 
        		"            }\r\n" + 
        		"            closestDistance = 0;\r\n" + 
        		"            for (Unit u2 : pgs.getUnits()) {\r\n" + 
        		"                if (u2.getType().isStockpile && u2.getPlayer()==p.getID()) {\r\n" + 
        		"                    int d = Math.abs(u2.getX() - u.getX()) + Math.abs(u2.getY() - u.getY());\r\n" + 
        		"                    if (closestBase == null || d < closestDistance) {\r\n" + 
        		"                        closestBase = u2;\r\n" + 
        		"                        closestDistance = d;\r\n" + 
        		"                    }\r\n" + 
        		"                }\r\n" + 
        		"            }\r\n" + 
        		"            if (closestResource != null && closestBase != null) {\r\n" + 
        		"                AbstractAction aa = getAbstractAction(u);\r\n" + 
        		"                if (aa instanceof Harvest) {\r\n" + 
        		"                    Harvest h_aa = (Harvest)aa;\r\n" + 
        		"                    if (h_aa.getTarget() != closestResource || h_aa.getBase()!=closestBase) harvest(u, closestResource, closestBase);\n" + 
        		"                } else {\r\n" + 
        		"                    harvest(u, closestResource, closestBase);\r\n" + 
        		"                }\r\n" + 
        		"            }\r\n" + 
        		"        }\r\n" + 
        		"    }\r\n" + 
        		"\r\n" + 
        		"   \r\n" + 
        		"    @Override\r\n" + 
        		"    public List<ParameterSpecification> getParameters()\r\n" + 
        		"    {\r\n" + 
        		"        List<ParameterSpecification> parameters = new ArrayList<>();\r\n" + 
        		"        \r\n" + 
        		"        parameters.add(new ParameterSpecification(\"PathFinding\", PathFinding.class, new AStarPathFinding()));\r\n" + 
        		"\r\n" + 
        		"        return parameters;\r\n" + 
        		"    }" + 
        		"}");
        
        editor3.setText("package myAI;\r\n" + 
        		"import ai.abstraction.AbstractAction;\r\n" + 
        		"import ai.abstraction.AbstractionLayerAI;\r\n" + 
        		"import ai.abstraction.Harvest;\r\n" + 
        		"import ai.abstraction.pathfinding.AStarPathFinding;\r\n" + 
        		"import ai.abstraction.pathfinding.PathFinding;\r\n" + 
        		"import ai.core.AI;\r\n" + 
        		"import ai.core.ParameterSpecification;\r\n" + 
        		"\r\n" + 
        		"import java.util.ArrayList;\r\n" + 
        		"import java.util.LinkedList;\r\n" + 
        		"import java.util.List;\r\n" + 
        		"import java.util.Random;\r\n" + 
        		"\r\n" + 
        		"import rts.GameState;\r\n" + 
        		"import rts.PhysicalGameState;\r\n" + 
        		"import rts.Player;\r\n" + 
        		"import rts.PlayerAction;\r\n" + 
        		"import rts.units.Unit;\r\n" + 
        		"import rts.units.UnitType;\r\n" + 
        		"import rts.units.UnitTypeTable;"
        		+ "\n"
        		+ "public class MyNewAI_3 extends AbstractionLayerAI {\n" + 
        		"Random r = new Random();\r\n" + 
        		"    protected UnitTypeTable utt;\r\n" + 
        		"    UnitType workerType;\r\n" + 
        		"    UnitType baseType;\r\n" + 
        		"    UnitType barracksType;\r\n" + 
        		"    UnitType heavyType;\r\n" + 
        		"\r\n" + 
        		"    // Strategy implemented by this class:\r\n" + 
        		"    // If we have any \"heavy\": send it to attack to the nearest enemy unit\r\n" + 
        		"    // If we have a base: train worker until we have 1 workers\r\n" + 
        		"    // If we have a barracks: train heavy\r\n" + 
        		"    // If we have a worker: do this if needed: build base, build barracks, harvest resources\r\n" + 
        		"\r\n" + 
        		"    public MyNewAI_3(UnitTypeTable a_utt) {\r\n" + 
        		"        this(a_utt, new AStarPathFinding());\r\n" + 
        		"    }\r\n" + 
        		"    \r\n" + 
        		"    \r\n" + 
        		"    public MyNewAI_3(UnitTypeTable a_utt, PathFinding a_pf) {\r\n" + 
        		"        super(a_pf);\r\n" + 
        		"        reset(a_utt);\r\n" + 
        		"    }\r\n" + 
        		"\t//Construtor necessario para criacao de classe em tempo de execucao\n" + 
        		"\tpublic MyNewAI_3() {\n" + 
        		"\t\tsuper(new AStarPathFinding());\n" + 
        		"\t\treset(Context.getInstance().getUtt());\r\n" +
        		"\t}\n" +
        		"\r\n" + 
        		"    public void reset() {\r\n" + 
        		"    	super.reset();\r\n" + 
        		"    }\r\n" + 
        		"    \r\n" + 
        		"    public void reset(UnitTypeTable a_utt)  \r\n" + 
        		"    {\r\n" + 
        		"        utt = a_utt;\r\n" + 
        		"        workerType = utt.getUnitType(\"Worker\");\r\n" + 
        		"        baseType = utt.getUnitType(\"Base\");\r\n" + 
        		"        barracksType = utt.getUnitType(\"Barracks\");\r\n" + 
        		"        heavyType = utt.getUnitType(\"Heavy\");\r\n" + 
        		"    }      \r\n" + 
        		"\r\n" + 
        		"    public AI clone() {\r\n" + 
        		"        return new MyNewAI_3(utt, pf);\r\n" + 
        		"    }\r\n" + 
        		"\r\n" + 
        		"    /*\r\n" + 
        		"        This is the main function of the AI. It is called at each game cycle with the most up to date game state and\r\n" + 
        		"        returns which actions the AI wants to execute in this cycle.\r\n" + 
        		"        The input parameters are:\r\n" + 
        		"        - player: the player that the AI controls (0 or 1)\r\n" + 
        		"        - gs: the current game state\r\n" + 
        		"        This method returns the actions to be sent to each of the units in the gamestate controlled by the player,\r\n" + 
        		"        packaged as a PlayerAction.\r\n" + 
        		"     */\r\n" + 
        		"    public PlayerAction getAction(int player, GameState gs) {\r\n" + 
        		"        PhysicalGameState pgs = gs.getPhysicalGameState();\r\n" + 
        		"        Player p = gs.getPlayer(player);\r\n" + 
        		"//        System.out.println(\"HeavyRushAI for player \" + player + \" (cycle \" + gs.getTime() + \")\");\r\n" + 
        		"\r\n" + 
        		"        // behavior of bases:\r\n" + 
        		"        for (Unit u : pgs.getUnits()) {\r\n" + 
        		"            if (u.getType() == baseType\r\n" + 
        		"                    && u.getPlayer() == player\r\n" + 
        		"                    && gs.getActionAssignment(u) == null) {\r\n" + 
        		"                baseBehavior(u, p, pgs);\r\n" + 
        		"            }\r\n" + 
        		"        }\r\n" + 
        		"\r\n" + 
        		"        // behavior of barracks:\r\n" + 
        		"        for (Unit u : pgs.getUnits()) {\r\n" + 
        		"            if (u.getType() == barracksType\r\n" + 
        		"                    && u.getPlayer() == player\r\n" + 
        		"                    && gs.getActionAssignment(u) == null) {\r\n" + 
        		"                barracksBehavior(u, p, pgs);\r\n" + 
        		"            }\r\n" + 
        		"        }\r\n" + 
        		"\r\n" + 
        		"        // behavior of melee units:\r\n" + 
        		"        for (Unit u : pgs.getUnits()) {\r\n" + 
        		"            if (u.getType().canAttack && !u.getType().canHarvest\r\n" + 
        		"                    && u.getPlayer() == player\r\n" + 
        		"                    && gs.getActionAssignment(u) == null) {\r\n" + 
        		"                meleeUnitBehavior(u, p, gs);\r\n" + 
        		"            }\r\n" + 
        		"        }\r\n" + 
        		"\r\n" + 
        		"        // behavior of workers:\r\n" + 
        		"        List<Unit> workers = new LinkedList<Unit>();\r\n" + 
        		"        for (Unit u : pgs.getUnits()) {\r\n" + 
        		"            if (u.getType().canHarvest\r\n" + 
        		"                    && u.getPlayer() == player) {\r\n" + 
        		"                workers.add(u);\r\n" + 
        		"            }\r\n" + 
        		"        }\r\n" + 
        		"        workersBehavior(workers, p, pgs);\r\n" + 
        		"\r\n" + 
        		"        // This method simply takes all the unit actions executed so far, and packages them into a PlayerAction\r\n" + 
        		"        return translateActions(player, gs);\r\n" + 
        		"    }\r\n" + 
        		"\r\n" + 
        		"    public void baseBehavior(Unit u, Player p, PhysicalGameState pgs) {\r\n" + 
        		"        int nworkers = 0;\r\n" + 
        		"        for (Unit u2 : pgs.getUnits()) {\r\n" + 
        		"            if (u2.getType() == workerType\r\n" + 
        		"                    && u2.getPlayer() == p.getID()) {\r\n" + 
        		"                nworkers++;\r\n" + 
        		"            }\r\n" + 
        		"        }\r\n" + 
        		"        if (nworkers < 1 && p.getResources() >= workerType.cost) {\r\n" + 
        		"            train(u, workerType);\r\n" + 
        		"        }\r\n" + 
        		"    }\r\n" + 
        		"\r\n" + 
        		"    public void barracksBehavior(Unit u, Player p, PhysicalGameState pgs) {\r\n" + 
        		"        if (p.getResources() >= heavyType.cost) {\r\n" + 
        		"            train(u, heavyType);\r\n" + 
        		"        }\r\n" + 
        		"    }\r\n" + 
        		"\r\n" + 
        		"    public void meleeUnitBehavior(Unit u, Player p, GameState gs) {\r\n" + 
        		"        PhysicalGameState pgs = gs.getPhysicalGameState();\r\n" + 
        		"        Unit closestEnemy = null;\r\n" + 
        		"        int closestDistance = 0;\r\n" + 
        		"        for (Unit u2 : pgs.getUnits()) {\r\n" + 
        		"            if (u2.getPlayer() >= 0 && u2.getPlayer() != p.getID()) {\r\n" + 
        		"                int d = Math.abs(u2.getX() - u.getX()) + Math.abs(u2.getY() - u.getY());\r\n" + 
        		"                if (closestEnemy == null || d < closestDistance) {\r\n" + 
        		"                    closestEnemy = u2;\r\n" + 
        		"                    closestDistance = d;\r\n" + 
        		"                }\r\n" + 
        		"            }\r\n" + 
        		"        }\r\n" + 
        		"        if (closestEnemy != null) {\r\n" + 
        		"//            System.out.println(\"HeavyRushAI.meleeUnitBehavior: \" + u + \" attacks \" + closestEnemy);\r\n" + 
        		"            attack(u, closestEnemy);\r\n" + 
        		"        }\r\n" + 
        		"    }\r\n" + 
        		"\r\n" + 
        		"    public void workersBehavior(List<Unit> workers, Player p, PhysicalGameState pgs) {\r\n" + 
        		"        int nbases = 0;\r\n" + 
        		"        int nbarracks = 0;\r\n" + 
        		"\r\n" + 
        		"        int resourcesUsed = 0;\r\n" + 
        		"        List<Unit> freeWorkers = new LinkedList<Unit>();\r\n" + 
        		"        freeWorkers.addAll(workers);\r\n" + 
        		"\r\n" + 
        		"        if (workers.isEmpty()) {\r\n" + 
        		"            return;\r\n" + 
        		"        }\r\n" + 
        		"\r\n" + 
        		"        for (Unit u2 : pgs.getUnits()) {\r\n" + 
        		"            if (u2.getType() == baseType\r\n" + 
        		"                    && u2.getPlayer() == p.getID()) {\r\n" + 
        		"                nbases++;\r\n" + 
        		"            }\r\n" + 
        		"            if (u2.getType() == barracksType\r\n" + 
        		"                    && u2.getPlayer() == p.getID()) {\r\n" + 
        		"                nbarracks++;\r\n" + 
        		"            }\r\n" + 
        		"        }\r\n" + 
        		"\r\n" + 
        		"        List<Integer> reservedPositions = new LinkedList<Integer>();\r\n" + 
        		"        if (nbases == 0 && !freeWorkers.isEmpty()) {\r\n" + 
        		"            // build a base:\r\n" + 
        		"            if (p.getResources() >= baseType.cost + resourcesUsed) {\r\n" + 
        		"                Unit u = freeWorkers.remove(0);\r\n" + 
        		"                buildIfNotAlreadyBuilding(u,baseType,u.getX(),u.getY(),reservedPositions,p,pgs);\r\n" + 
        		"                resourcesUsed += baseType.cost;\r\n" + 
        		"            }\r\n" + 
        		"        }\r\n" + 
        		"\r\n" + 
        		"        if (nbarracks == 0) {\r\n" + 
        		"            // build a barracks:\r\n" + 
        		"            if (p.getResources() >= barracksType.cost + resourcesUsed && !freeWorkers.isEmpty()) {\r\n" + 
        		"                Unit u = freeWorkers.remove(0);\r\n" + 
        		"                buildIfNotAlreadyBuilding(u,barracksType,u.getX(),u.getY(),reservedPositions,p,pgs);\r\n" + 
        		"            	resourcesUsed += barracksType.cost;\r\n" + 
        		"            }\r\n" + 
        		"        }\r\n" + 
        		"\r\n" + 
        		"\r\n" + 
        		"        // harvest with all the free workers:\r\n" + 
        		"        for (Unit u : freeWorkers) {\r\n" + 
        		"            Unit closestBase = null;\r\n" + 
        		"            Unit closestResource = null;\r\n" + 
        		"            int closestDistance = 0;\r\n" + 
        		"            for (Unit u2 : pgs.getUnits()) {\r\n" + 
        		"                if (u2.getType().isResource) {\r\n" + 
        		"                    int d = Math.abs(u2.getX() - u.getX()) + Math.abs(u2.getY() - u.getY());\r\n" + 
        		"                    if (closestResource == null || d < closestDistance) {\r\n" + 
        		"                        closestResource = u2;\r\n" + 
        		"                        closestDistance = d;\r\n" + 
        		"                    }\r\n" + 
        		"                }\r\n" + 
        		"            }\r\n" + 
        		"            closestDistance = 0;\r\n" + 
        		"            for (Unit u2 : pgs.getUnits()) {\r\n" + 
        		"                if (u2.getType().isStockpile && u2.getPlayer()==p.getID()) {\r\n" + 
        		"                    int d = Math.abs(u2.getX() - u.getX()) + Math.abs(u2.getY() - u.getY());\r\n" + 
        		"                    if (closestBase == null || d < closestDistance) {\r\n" + 
        		"                        closestBase = u2;\r\n" + 
        		"                        closestDistance = d;\r\n" + 
        		"                    }\r\n" + 
        		"                }\r\n" + 
        		"            }\r\n" + 
        		"            if (closestResource != null && closestBase != null) {\r\n" + 
        		"                AbstractAction aa = getAbstractAction(u);\r\n" + 
        		"                if (aa instanceof Harvest) {\r\n" + 
        		"                    Harvest h_aa = (Harvest)aa;\r\n" + 
        		"                    if (h_aa.getTarget() != closestResource || h_aa.getBase()!=closestBase) harvest(u, closestResource, closestBase);\n" + 
        		"                } else {\r\n" + 
        		"                    harvest(u, closestResource, closestBase);\r\n" + 
        		"                }\r\n" + 
        		"            }\r\n" + 
        		"        }\r\n" + 
        		"    }\r\n" + 
        		"\r\n" + 
        		"\r\n" + 
        		"    @Override\r\n" + 
        		"    public List<ParameterSpecification> getParameters()\r\n" + 
        		"    {\r\n" + 
        		"        List<ParameterSpecification> parameters = new ArrayList<>();\r\n" + 
        		"        \r\n" + 
        		"        parameters.add(new ParameterSpecification(\"PathFinding\", PathFinding.class, new AStarPathFinding()));\r\n" + 
        		"\r\n" + 
        		"        return parameters;\r\n" + 
        		"    }" + 
        		"}");
        
        editor4.setText("package myAI;\r\n" + 
        		"import ai.abstraction.AbstractAction;\r\n" + 
        		"import ai.abstraction.AbstractionLayerAI;\r\n" + 
        		"import ai.abstraction.Harvest;\r\n" + 
        		"import ai.abstraction.pathfinding.AStarPathFinding;\r\n" + 
        		"import ai.abstraction.pathfinding.PathFinding;\r\n" + 
        		"import ai.core.AI;\r\n" + 
        		"import ai.core.ParameterSpecification;\r\n" + 
        		"\r\n" + 
        		"import java.util.ArrayList;\r\n" + 
        		"import java.util.LinkedList;\r\n" + 
        		"import java.util.List;\r\n" + 
        		"import java.util.Random;\r\n" + 
        		"\r\n" + 
        		"import rts.GameState;\r\n" + 
        		"import rts.PhysicalGameState;\r\n" + 
        		"import rts.Player;\r\n" + 
        		"import rts.PlayerAction;\r\n" + 
        		"import rts.units.Unit;\r\n" + 
        		"import rts.units.UnitType;\r\n" + 
        		"import rts.units.UnitTypeTable;"
        		+ "\n"
        		+ "public class MyNewAI_4 extends AbstractionLayerAI {\n" + 
        		"Random r = new Random();\r\n" + 
        		"    protected UnitTypeTable utt;\r\n" + 
        		"    UnitType workerType;\r\n" + 
        		"    UnitType baseType;\r\n" + 
        		"    UnitType barracksType;\r\n" + 
        		"    UnitType heavyType;\r\n" + 
        		"\r\n" + 
        		"    // These strategies behave similarly to WD,\r\n" + 
        		"    //with  the  difference  being  that  the  defense  line  is  formed  by\r\n" + 
        		"    //heavy units for HD, respectively. \r\n" + 
        		"\r\n" + 
        		"    public MyNewAI_4(UnitTypeTable a_utt) {\r\n" + 
        		"        this(a_utt, new AStarPathFinding());\r\n" + 
        		"    }\r\n" + 
        		"    \r\n" + 
        		"    \r\n" + 
        		"    public MyNewAI_4(UnitTypeTable a_utt, PathFinding a_pf) {\r\n" + 
        		"        super(a_pf);\r\n" + 
        		"        reset(a_utt);\r\n" + 
        		"    }\r\n" + 
        		"\t//Construtor necessario para criacao de classe em tempo de execucao\n" + 
        		"\tpublic MyNewAI_4() {\n" + 
        		"\t\tsuper(new AStarPathFinding());\n" + 
        		"\t\treset(Context.getInstance().getUtt());\r\n" +
        		"\t}\n" +
        		"\r\n" + 
        		"    public void reset() {\r\n" + 
        		"    	super.reset();\r\n" + 
        		"    }\r\n" + 
        		"    \r\n" + 
        		"    public void reset(UnitTypeTable a_utt)  \r\n" + 
        		"    {\r\n" + 
        		"        utt = a_utt;\r\n" + 
        		"        workerType = utt.getUnitType(\"Worker\");\r\n" + 
        		"        baseType = utt.getUnitType(\"Base\");\r\n" + 
        		"        barracksType = utt.getUnitType(\"Barracks\");\r\n" + 
        		"        heavyType = utt.getUnitType(\"Heavy\");\r\n" + 
        		"    }   \r\n" + 
        		"    \r\n" + 
        		"\r\n" + 
        		"    public AI clone() {\r\n" + 
        		"        return new MyNewAI_4(utt, pf);\r\n" + 
        		"    }\r\n" + 
        		"\r\n" + 
        		"    /*\r\n" + 
        		"        This is the main function of the AI. It is called at each game cycle with the most up to date game state and\r\n" + 
        		"        returns which actions the AI wants to execute in this cycle.\r\n" + 
        		"        The input parameters are:\r\n" + 
        		"        - player: the player that the AI controls (0 or 1)\r\n" + 
        		"        - gs: the current game state\r\n" + 
        		"        This method returns the actions to be sent to each of the units in the gamestate controlled by the player,\r\n" + 
        		"        packaged as a PlayerAction.\r\n" + 
        		"     */\r\n" + 
        		"    public PlayerAction getAction(int player, GameState gs) {\r\n" + 
        		"        PhysicalGameState pgs = gs.getPhysicalGameState();\r\n" + 
        		"        Player p = gs.getPlayer(player);\r\n" + 
        		"//        System.out.println(\"LightRushAI for player \" + player + \" (cycle \" + gs.getTime() + \")\");\r\n" + 
        		"\r\n" + 
        		"        // behavior of bases:\r\n" + 
        		"        for (Unit u : pgs.getUnits()) {\r\n" + 
        		"            if (u.getType() == baseType\r\n" + 
        		"                    && u.getPlayer() == player\r\n" + 
        		"                    && gs.getActionAssignment(u) == null) {\r\n" + 
        		"                baseBehavior(u, p, pgs);\r\n" + 
        		"            }\r\n" + 
        		"        }\r\n" + 
        		"\r\n" + 
        		"        // behavior of barracks:\r\n" + 
        		"        for (Unit u : pgs.getUnits()) {\r\n" + 
        		"            if (u.getType() == barracksType\r\n" + 
        		"                    && u.getPlayer() == player\r\n" + 
        		"                    && gs.getActionAssignment(u) == null) {\r\n" + 
        		"                barracksBehavior(u, p, pgs);\r\n" + 
        		"            }\r\n" + 
        		"        }\r\n" + 
        		"\r\n" + 
        		"        // behavior of melee units:\r\n" + 
        		"        for (Unit u : pgs.getUnits()) {\r\n" + 
        		"            if (u.getType().canAttack && !u.getType().canHarvest\r\n" + 
        		"                    && u.getPlayer() == player\r\n" + 
        		"                    && gs.getActionAssignment(u) == null) {\r\n" + 
        		"                meleeUnitBehavior(u, p, gs);\r\n" + 
        		"            }\r\n" + 
        		"        }\r\n" + 
        		"\r\n" + 
        		"        // behavior of workers:\r\n" + 
        		"        List<Unit> workers = new LinkedList<Unit>();\r\n" + 
        		"        for (Unit u : pgs.getUnits()) {\r\n" + 
        		"            if (u.getType().canHarvest\r\n" + 
        		"                    && u.getPlayer() == player) {\r\n" + 
        		"                workers.add(u);\r\n" + 
        		"            }\r\n" + 
        		"        }\r\n" + 
        		"        workersBehavior(workers, p, pgs);\r\n" + 
        		"\r\n" + 
        		"        // This method simply takes all the unit actions executed so far, and packages them into a PlayerAction\r\n" + 
        		"        return translateActions(player, gs);\r\n" + 
        		"    }\r\n" + 
        		"\r\n" + 
        		"    public void baseBehavior(Unit u, Player p, PhysicalGameState pgs) {\r\n" + 
        		"        int nworkers = 0;\r\n" + 
        		"        for (Unit u2 : pgs.getUnits()) {\r\n" + 
        		"            if (u2.getType() == workerType\r\n" + 
        		"                    && u2.getPlayer() == p.getID()) {\r\n" + 
        		"                nworkers++;\r\n" + 
        		"            }\r\n" + 
        		"        }\r\n" + 
        		"        if (nworkers < 1 && p.getResources() >= workerType.cost) {\r\n" + 
        		"            train(u, workerType);\r\n" + 
        		"        }\r\n" + 
        		"    }\r\n" + 
        		"\r\n" + 
        		"    public void barracksBehavior(Unit u, Player p, PhysicalGameState pgs) {\r\n" + 
        		"        if (p.getResources() >= heavyType.cost) {\r\n" + 
        		"            train(u, heavyType);\r\n" + 
        		"        }\r\n" + 
        		"    }\r\n" + 
        		"\r\n" + 
        		"    public void meleeUnitBehavior(Unit u, Player p, GameState gs) {\r\n" + 
        		"        PhysicalGameState pgs = gs.getPhysicalGameState();\r\n" + 
        		"        Unit closestEnemy = null;\r\n" + 
        		"        int closestDistance = 0;\r\n" + 
        		"        int mybase = 0;\r\n" + 
        		"        for (Unit u2 : pgs.getUnits()) {\r\n" + 
        		"            if (u2.getPlayer() >= 0 && u2.getPlayer() != p.getID()) {\r\n" + 
        		"                int d = Math.abs(u2.getX() - u.getX()) + Math.abs(u2.getY() - u.getY());\r\n" + 
        		"                if (closestEnemy == null || d < closestDistance) {\r\n" + 
        		"                    closestEnemy = u2;\r\n" + 
        		"                    closestDistance = d;\r\n" + 
        		"                }\r\n" + 
        		"            }\r\n" + 
        		"        else if(u2.getPlayer()==p.getID() && u2.getType() == baseType)\r\n" + 
        		"            {\r\n" + 
        		"                mybase = Math.abs(u2.getX() - u.getX()) + Math.abs(u2.getY() - u.getY());\r\n" + 
        		"            }\r\n" + 
        		"        }\r\n" + 
        		"        if (closestEnemy!=null && (closestDistance < pgs.getHeight()/2 || mybase < pgs.getHeight()/2)) {\r\n" + 
        		"            attack(u,closestEnemy);\r\n" + 
        		"        }\r\n" + 
        		"        else\r\n" + 
        		"        {\r\n" + 
        		"            attack(u, null);\r\n" + 
        		"        }\r\n" + 
        		"    }\r\n" + 
        		"\r\n" + 
        		"    public void workersBehavior(List<Unit> workers, Player p, PhysicalGameState pgs) {\r\n" + 
        		"        int nbases = 0;\r\n" + 
        		"        int nbarracks = 0;\r\n" + 
        		"\r\n" + 
        		"        int resourcesUsed = 0;\r\n" + 
        		"        List<Unit> freeWorkers = new LinkedList<Unit>();\r\n" + 
        		"        freeWorkers.addAll(workers);\r\n" + 
        		"\r\n" + 
        		"        if (workers.isEmpty()) {\r\n" + 
        		"            return;\r\n" + 
        		"        }\r\n" + 
        		"\r\n" + 
        		"        for (Unit u2 : pgs.getUnits()) {\r\n" + 
        		"            if (u2.getType() == baseType\r\n" + 
        		"                    && u2.getPlayer() == p.getID()) {\r\n" + 
        		"                nbases++;\r\n" + 
        		"            }\r\n" + 
        		"            if (u2.getType() == barracksType\r\n" + 
        		"                    && u2.getPlayer() == p.getID()) {\r\n" + 
        		"                nbarracks++;\r\n" + 
        		"            }\r\n" + 
        		"        }\r\n" + 
        		"\r\n" + 
        		"        List<Integer> reservedPositions = new LinkedList<Integer>();\r\n" + 
        		"        if (nbases == 0 && !freeWorkers.isEmpty()) {\r\n" + 
        		"            // build a base:\r\n" + 
        		"            if (p.getResources() >= baseType.cost + resourcesUsed) {\r\n" + 
        		"                Unit u = freeWorkers.remove(0);\r\n" + 
        		"                buildIfNotAlreadyBuilding(u,baseType,u.getX(),u.getY(),reservedPositions,p,pgs);\r\n" + 
        		"                resourcesUsed += baseType.cost;\r\n" + 
        		"            }\r\n" + 
        		"        }\r\n" + 
        		"\r\n" + 
        		"        if (nbarracks == 0) {\r\n" + 
        		"            // build a barracks:\r\n" + 
        		"            if (p.getResources() >= barracksType.cost + resourcesUsed && !freeWorkers.isEmpty()) {\r\n" + 
        		"                Unit u = freeWorkers.remove(0);\r\n" + 
        		"                buildIfNotAlreadyBuilding(u,barracksType,u.getX(),u.getY(),reservedPositions,p,pgs);\r\n" + 
        		"                resourcesUsed += barracksType.cost;\r\n" + 
        		"            }\r\n" + 
        		"        }\r\n" + 
        		"\r\n" + 
        		"\r\n" + 
        		"        // harvest with all the free workers:\r\n" + 
        		"        for (Unit u : freeWorkers) {\r\n" + 
        		"            Unit closestBase = null;\r\n" + 
        		"            Unit closestResource = null;\r\n" + 
        		"            int closestDistance = 0;\r\n" + 
        		"            for (Unit u2 : pgs.getUnits()) {\r\n" + 
        		"                if (u2.getType().isResource) {\r\n" + 
        		"                    int d = Math.abs(u2.getX() - u.getX()) + Math.abs(u2.getY() - u.getY());\r\n" + 
        		"                    if (closestResource == null || d < closestDistance) {\r\n" + 
        		"                        closestResource = u2;\r\n" + 
        		"                        closestDistance = d;\r\n" + 
        		"                    }\r\n" + 
        		"                }\r\n" + 
        		"            }\r\n" + 
        		"            closestDistance = 0;\r\n" + 
        		"            for (Unit u2 : pgs.getUnits()) {\r\n" + 
        		"                if (u2.getType().isStockpile && u2.getPlayer()==p.getID()) {\r\n" + 
        		"                    int d = Math.abs(u2.getX() - u.getX()) + Math.abs(u2.getY() - u.getY());\r\n" + 
        		"                    if (closestBase == null || d < closestDistance) {\r\n" + 
        		"                        closestBase = u2;\r\n" + 
        		"                        closestDistance = d;\r\n" + 
        		"                    }\r\n" + 
        		"                }\r\n" + 
        		"            }\r\n" + 
        		"            if (closestResource != null && closestBase != null) {\r\n" + 
        		"                AbstractAction aa = getAbstractAction(u);\r\n" + 
        		"                if (aa instanceof Harvest) {\r\n" + 
        		"                    Harvest h_aa = (Harvest)aa;\r\n" + 
        		"                    if (h_aa.getTarget() != closestResource || h_aa.getBase()!=closestBase) harvest(u, closestResource, closestBase);\r\n" + 
        		"                } else {\r\n" + 
        		"                    harvest(u, closestResource, closestBase);\r\n" + 
        		"                }\r\n" + 
        		"            }\r\n" + 
        		"        }\r\n" + 
        		"    }\r\n" + 
        		"\r\n" + 
        		"    \r\n" + 
        		"    @Override\r\n" + 
        		"    public List<ParameterSpecification> getParameters()\r\n" + 
        		"    {\r\n" + 
        		"        List<ParameterSpecification> parameters = new ArrayList<>();\r\n" + 
        		"        \r\n" + 
        		"        parameters.add(new ParameterSpecification(\"PathFinding\", PathFinding.class, new AStarPathFinding()));\r\n" + 
        		"\r\n" + 
        		"        return parameters;\r\n" + 
        		"    }    " + 
        		"}");
        
        JScrollPane scrollScript0 = new JScrollPane(editor0);
        JScrollPane scrollScript1 = new JScrollPane(editor1);
        JScrollPane scrollScript2 = new JScrollPane(editor2);
        JScrollPane scrollScript3 = new JScrollPane(editor3);
        JScrollPane scrollScript4 = new JScrollPane(editor4);
        tabbedPane.addTab("Script 0", scrollScript0);
        tabbedPane.addTab("Script 1", scrollScript1);
        tabbedPane.addTab("Script 2", scrollScript2);
        tabbedPane.addTab("Script 3", scrollScript3);
        tabbedPane.addTab("Script 4", scrollScript4);
        ladoDireito.add(tabbedPane);
        JPanel botoes = new JPanel(); //Armazena os botões
        botoes.setLayout(new GridLayout(1,2));
        JButton save = new JButton("Save");
        save.setActionCommand("save");
        save.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				System.out.println("Save");
				Context.getInstance().setSaveClicado(true);
				Context.getInstance().setScript(editor0.getText(), 0);
				Context.getInstance().setScript(editor1.getText(), 1);
				Context.getInstance().setScript(editor2.getText(), 2);
				Context.getInstance().setScript(editor3.getText(), 3);
				Context.getInstance().setScript(editor4.getText(), 4);
			} 
		});
        JButton run = new JButton("Run");
        run.setActionCommand("run");
        run.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				System.out.println("Run");
		        Context.getInstance().setRunClicado(true);
			} 
		});
        botoes.add(save);
        botoes.add(run);
        ladoDireito.add(botoes);
        
        JPanel checkBoxes = new JPanel(); //Armazena os checkboxes
        JCheckBox checkBox_0 = new JCheckBox("Script 0");
        JCheckBox checkBox_1 = new JCheckBox("Script 1");
        JCheckBox checkBox_2 = new JCheckBox("Script 2");
        JCheckBox checkBox_3 = new JCheckBox("Script 3");
        JCheckBox checkBox_4 = new JCheckBox("Script 4");
        checkBox_0.setSelected(true);
        checkBox_1.setSelected(true);
        checkBox_2.setSelected(true);
        checkBox_3.setSelected(true);
        checkBox_4.setSelected(true);
        checkBox_0.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED) {
                    Context.getInstance().setCheckBoxScript(0, true);
                }
                else {
                	Context.getInstance().setCheckBoxScript(0, false);
                }
            }
        });
        checkBox_1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED) {
                	System.out.println("Fui selecionado");
                	Context.getInstance().setCheckBoxScript(1, true);
                }
                else {
                	System.out.println("Nao Fui selecionado");
                	Context.getInstance().setCheckBoxScript(1, false);
                }
            }
        });
        checkBox_2.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED) {
                	Context.getInstance().setCheckBoxScript(2, true);
                }
                else {
                	Context.getInstance().setCheckBoxScript(2, false);
                }
            }
        });
        checkBox_3.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED) {
                	Context.getInstance().setCheckBoxScript(3, true);
                }
                else {
                	Context.getInstance().setCheckBoxScript(3, false);
                }
            }
        });
        checkBox_4.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED) {
                	Context.getInstance().setCheckBoxScript(4, true);
                }
                else {
                	Context.getInstance().setCheckBoxScript(4, false);
                }
            }
        });
        checkBoxes.add(checkBox_0);
        checkBoxes.add(checkBox_1);
        checkBoxes.add(checkBox_2);
        checkBoxes.add(checkBox_3);
        checkBoxes.add(checkBox_4);
        
        
        ladoDireito.add(checkBoxes); //Adiciona as checBoxes ao pane lateral
        
        add(ladoDireito); //Adiciona o pane direito à janela
        pack();
        setResizable(false);
        setSize(dx,dy);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    public myPhysicalGameStatePanel getPanel() {
        return panel;
    }
    
    public void setStateCloning(GameState gs) {
        panel.setStateCloning(gs);
    }
            
    public void setStateDirect(GameState gs) {
        panel.setStateDirect(gs);
    }
    
    protected JComponent makeTextPanel(String text) {
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        return panel;
    }
    
}
