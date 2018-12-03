/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import myAI.Context;
import rts.GameState;

/**
 *
 * @author santi
 */
public class myPhysicalGameStateJFrame extends JFrame {
    myPhysicalGameStatePanel panel = null;
    JTextArea editor1 = new JTextArea();
    JTextArea editor2 = new JTextArea();
    JTextArea editor3 = new JTextArea();
    JTextArea editor4 = new JTextArea();
    JTextArea editor5 = new JTextArea();
    
    public myPhysicalGameStateJFrame(String title, int dx, int dy, myPhysicalGameStatePanel a_panel) {
        super(title);
        panel = a_panel;
        setLayout(new GridLayout(1,2));
        add(panel); //Adiciona o mapa ao lado esquerdo
        JPanel ladoDireito = new JPanel();
        ladoDireito.setLayout(new GridLayout(2,1));
        
        JTabbedPane tabbedPane = new JTabbedPane();
               
        editor1.setTabSize(4);
        editor2.setTabSize(4);
        editor3.setTabSize(4);
        editor4.setTabSize(4);
        editor5.setTabSize(4);
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
        editor5.setBorder(BorderFactory.createCompoundBorder(
                panel.getBorder(), 
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        editor1.setText("package myAI;\n" +
        		"import ai.core.AI;\n" + 
        		"import ai.core.AIWithComputationBudget;\n" + 
        		"import ai.core.ParameterSpecification;\n" + 
        		"\n" + 
        		"import java.util.ArrayList;\n" + 
        		"import java.util.List;\n" + 
        		"\n" + 
        		"import rts.GameState;\n" + 
        		"import rts.PlayerAction;\n" + 
        		"import rts.units.UnitTypeTable;\n"
        		+ "\n"
        		+ "public class MyNewAI_0 extends AIWithComputationBudget {\n" + 
        		"\tUnitTypeTable m_utt = null;\n" + 
        		"\n" +
        		"\t//Construtor necessario para criacao de classe em tempo de execucao\n" + 
        		"\tpublic MyNewAI_0() {\n" + 
        		"\t\tsuper(-1,-1);\n" + 
        		"\t}\n" + 
        		"\t// This is the default constructor that microRTS will call:\n" + 
        		"\tpublic MyNewAI_0(UnitTypeTable utt) {\n" + 
        		"\t\tsuper(-1,-1);\n" + 
        		"\t\tm_utt = utt;\n" + 
        		"\t}\n" + 
        		"\n" + 
        		"\t// This will be called by microRTS when it wants to create new instances of this bot (e.g., to play multiple games).\n" + 
        		"\tpublic AI clone() {\n" + 
        		"\t\treturn new MyNewAI_0(m_utt);\n" + 
        		"\t}\n" + 
        		"\n" + 
        		"\t// This will be called once at the beginning of each new game:    \n" + 
        		"\tpublic void reset() {\n" + 
        		"\t}\n" + 
        		"\n" + 
        		"\t// Called by microRTS at each game cycle.\n" + 
        		"\t// Returns the action the bot wants to execute.\n" + 
        		"\tpublic PlayerAction getAction(int player, GameState gs) {\n" + 
        		"\t\tPlayerAction pa = new PlayerAction();\n" + 
        		"\t\tpa.fillWithNones(gs, player, 10);\n" + 
        		"\t\treturn pa;\n" + 
        		"\t}\n" + 
        		"\n" + 
        		"\t// This will be called by the microRTS GUI to get the\n" + 
        		"\t// list of parameters that this bot wants exposed\n" + 
        		"\t// in the GUI.\n" + 
        		"\tpublic List<ParameterSpecification> getParameters()\n" + 
        		"\t{\n" + 
        		"\t\treturn new ArrayList<>();\n" + 
        		"\t}\n" + 
        		"}");
        editor2.setText("package myAI;\n" +
        		"import ai.core.AI;\n" + 
        		"import ai.core.AIWithComputationBudget;\n" + 
        		"import ai.core.ParameterSpecification;\n" + 
        		"\n" + 
        		"import java.util.ArrayList;\n" + 
        		"import java.util.List;\n" + 
        		"\n" + 
        		"import rts.GameState;\n" + 
        		"import rts.PlayerAction;\n" + 
        		"import rts.units.UnitTypeTable;\n"
        		+ "\n"
        		+ "public class MyNewAI_1 extends AIWithComputationBudget {\n" + 
        		"\tUnitTypeTable m_utt = null;\n" + 
        		"\n" +
        		"\t//Construtor necessario para criacao de classe em tempo de execucao\n" + 
        		"\tpublic MyNewAI_1() {\n" + 
        		"\t\tsuper(-1,-1);\n" + 
        		"\t}\n" + 
        		"\t// This is the default constructor that microRTS will call:\n" + 
        		"\tpublic MyNewAI_1(UnitTypeTable utt) {\n" + 
        		"\t\tsuper(-1,-1);\n" + 
        		"\t\tm_utt = utt;\n" + 
        		"\t}\n" + 
        		"\n" + 
        		"\t// This will be called by microRTS when it wants to create new instances of this bot (e.g., to play multiple games).\n" + 
        		"\tpublic AI clone() {\n" + 
        		"\t\treturn new MyNewAI_1(m_utt);\n" + 
        		"\t}\n" + 
        		"\n" + 
        		"\t// This will be called once at the beginning of each new game:    \n" + 
        		"\tpublic void reset() {\n" + 
        		"\t}\n" + 
        		"\n" + 
        		"\t// Called by microRTS at each game cycle.\n" + 
        		"\t// Returns the action the bot wants to execute.\n" + 
        		"\tpublic PlayerAction getAction(int player, GameState gs) {\n" + 
        		"\t\tPlayerAction pa = new PlayerAction();\n" + 
        		"\t\tpa.fillWithNones(gs, player, 10);\n" + 
        		"\t\treturn pa;\n" + 
        		"\t}\n" + 
        		"\n" + 
        		"\t// This will be called by the microRTS GUI to get the\n" + 
        		"\t// list of parameters that this bot wants exposed\n" + 
        		"\t// in the GUI.\n" + 
        		"\tpublic List<ParameterSpecification> getParameters()\n" + 
        		"\t{\n" + 
        		"\t\treturn new ArrayList<>();\n" + 
        		"\t}\n" + 
        		"}");
        editor3.setText("package myAI;\n" +
        		"import ai.core.AI;\n" + 
        		"import ai.core.AIWithComputationBudget;\n" + 
        		"import ai.core.ParameterSpecification;\n" + 
        		"\n" + 
        		"import java.util.ArrayList;\n" + 
        		"import java.util.List;\n" + 
        		"\n" + 
        		"import rts.GameState;\n" + 
        		"import rts.PlayerAction;\n" + 
        		"import rts.units.UnitTypeTable;\n"
        		+ "\n"
        		+ "public class MyNewAI_2 extends AIWithComputationBudget {\n" + 
        		"\tUnitTypeTable m_utt = null;\n" + 
        		"\n" +
        		"\t//Construtor necessario para criacao de classe em tempo de execucao\n" + 
        		"\tpublic MyNewAI_2() {\n" + 
        		"\t\tsuper(-1,-1);\n" + 
        		"\t}\n" + 
        		"\t// This is the default constructor that microRTS will call:\n" + 
        		"\tpublic MyNewAI_2(UnitTypeTable utt) {\n" + 
        		"\t\tsuper(-1,-1);\n" + 
        		"\t\tm_utt = utt;\n" + 
        		"\t}\n" + 
        		"\n" + 
        		"\t// This will be called by microRTS when it wants to create new instances of this bot (e.g., to play multiple games).\n" + 
        		"\tpublic AI clone() {\n" + 
        		"\t\treturn new MyNewAI_2(m_utt);\n" + 
        		"\t}\n" + 
        		"\n" + 
        		"\t// This will be called once at the beginning of each new game:    \n" + 
        		"\tpublic void reset() {\n" + 
        		"\t}\n" + 
        		"\n" + 
        		"\t// Called by microRTS at each game cycle.\n" + 
        		"\t// Returns the action the bot wants to execute.\n" + 
        		"\tpublic PlayerAction getAction(int player, GameState gs) {\n" + 
        		"\t\tPlayerAction pa = new PlayerAction();\n" + 
        		"\t\tpa.fillWithNones(gs, player, 10);\n" + 
        		"\t\treturn pa;\n" + 
        		"\t}\n" + 
        		"\n" + 
        		"\t// This will be called by the microRTS GUI to get the\n" + 
        		"\t// list of parameters that this bot wants exposed\n" + 
        		"\t// in the GUI.\n" + 
        		"\tpublic List<ParameterSpecification> getParameters()\n" + 
        		"\t{\n" + 
        		"\t\treturn new ArrayList<>();\n" + 
        		"\t}\n" + 
        		"}");
        editor4.setText("package myAI;\n" +
        		"import ai.core.AI;\n" + 
        		"import ai.core.AIWithComputationBudget;\n" + 
        		"import ai.core.ParameterSpecification;\n" + 
        		"\n" + 
        		"import java.util.ArrayList;\n" + 
        		"import java.util.List;\n" + 
        		"\n" + 
        		"import rts.GameState;\n" + 
        		"import rts.PlayerAction;\n" + 
        		"import rts.units.UnitTypeTable;\n"
        		+ "\n"
        		+ "public class MyNewAI_3 extends AIWithComputationBudget {\n" + 
        		"\tUnitTypeTable m_utt = null;\n" + 
        		"\n" +
        		"\t//Construtor necessario para criacao de classe em tempo de execucao\n" + 
        		"\tpublic MyNewAI_3() {\n" + 
        		"\t\tsuper(-1,-1);\n" + 
        		"\t}\n" + 
        		"\t// This is the default constructor that microRTS will call:\n" + 
        		"\tpublic MyNewAI_3(UnitTypeTable utt) {\n" + 
        		"\t\tsuper(-1,-1);\n" + 
        		"\t\tm_utt = utt;\n" + 
        		"\t}\n" + 
        		"\n" + 
        		"\t// This will be called by microRTS when it wants to create new instances of this bot (e.g., to play multiple games).\n" + 
        		"\tpublic AI clone() {\n" + 
        		"\t\treturn new MyNewAI_3(m_utt);\n" + 
        		"\t}\n" + 
        		"\n" + 
        		"\t// This will be called once at the beginning of each new game:    \n" + 
        		"\tpublic void reset() {\n" + 
        		"\t}\n" + 
        		"\n" + 
        		"\t// Called by microRTS at each game cycle.\n" + 
        		"\t// Returns the action the bot wants to execute.\n" + 
        		"\tpublic PlayerAction getAction(int player, GameState gs) {\n" + 
        		"\t\tPlayerAction pa = new PlayerAction();\n" + 
        		"\t\tpa.fillWithNones(gs, player, 10);\n" + 
        		"\t\treturn pa;\n" + 
        		"\t}\n" + 
        		"\n" + 
        		"\t// This will be called by the microRTS GUI to get the\n" + 
        		"\t// list of parameters that this bot wants exposed\n" + 
        		"\t// in the GUI.\n" + 
        		"\tpublic List<ParameterSpecification> getParameters()\n" + 
        		"\t{\n" + 
        		"\t\treturn new ArrayList<>();\n" + 
        		"\t}\n" + 
        		"}");
        editor5.setText("package myAI;\n" +
        		"import ai.core.AI;\n" + 
        		"import ai.core.AIWithComputationBudget;\n" + 
        		"import ai.core.ParameterSpecification;\n" + 
        		"\n" + 
        		"import java.util.ArrayList;\n" + 
        		"import java.util.List;\n" + 
        		"\n" + 
        		"import rts.GameState;\n" + 
        		"import rts.PlayerAction;\n" + 
        		"import rts.units.UnitTypeTable;\n"
        		+ "\n"
        		+ "public class MyNewAI_4 extends AIWithComputationBudget {\n" + 
        		"\tUnitTypeTable m_utt = null;\n" + 
        		"\n" +
        		"\t//Construtor necessario para criacao de classe em tempo de execucao\n" + 
        		"\tpublic MyNewAI_4() {\n" + 
        		"\t\tsuper(-1,-1);\n" + 
        		"\t}\n" + 
        		"\t// This is the default constructor that microRTS will call:\n" + 
        		"\tpublic MyNewAI_4(UnitTypeTable utt) {\n" + 
        		"\t\tsuper(-1,-1);\n" + 
        		"\t\tm_utt = utt;\n" + 
        		"\t}\n" + 
        		"\n" + 
        		"\t// This will be called by microRTS when it wants to create new instances of this bot (e.g., to play multiple games).\n" + 
        		"\tpublic AI clone() {\n" + 
        		"\t\treturn new MyNewAI_4(m_utt);\n" + 
        		"\t}\n" + 
        		"\n" + 
        		"\t// This will be called once at the beginning of each new game:    \n" + 
        		"\tpublic void reset() {\n" + 
        		"\t}\n" + 
        		"\n" + 
        		"\t// Called by microRTS at each game cycle.\n" + 
        		"\t// Returns the action the bot wants to execute.\n" + 
        		"\tpublic PlayerAction getAction(int player, GameState gs) {\n" + 
        		"\t\tPlayerAction pa = new PlayerAction();\n" + 
        		"\t\tpa.fillWithNones(gs, player, 10);\n" + 
        		"\t\treturn pa;\n" + 
        		"\t}\n" + 
        		"\n" + 
        		"\t// This will be called by the microRTS GUI to get the\n" + 
        		"\t// list of parameters that this bot wants exposed\n" + 
        		"\t// in the GUI.\n" + 
        		"\tpublic List<ParameterSpecification> getParameters()\n" + 
        		"\t{\n" + 
        		"\t\treturn new ArrayList<>();\n" + 
        		"\t}\n" + 
        		"}");
        JScrollPane scrollScript1 = new JScrollPane(editor1);
        JScrollPane scrollScript2 = new JScrollPane(editor2);
        JScrollPane scrollScript3 = new JScrollPane(editor3);
        JScrollPane scrollScript4 = new JScrollPane(editor4);
        JScrollPane scrollScript5 = new JScrollPane(editor5);
        tabbedPane.addTab("Script 1", scrollScript1);
        tabbedPane.addTab("Script 2", scrollScript2);
        tabbedPane.addTab("Script 3", scrollScript3);
        tabbedPane.addTab("Script 4", scrollScript4);
        tabbedPane.addTab("Script 5", scrollScript5);
        ladoDireito.add(tabbedPane);
        JPanel botoes = new JPanel(); //Armazena os botões
        botoes.setLayout(new GridLayout(1,2));
        JButton save = new JButton("Save");
        save.setActionCommand("save");
        save.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				System.out.println("Save");
				Context.getInstance().setSaveClicado(true);
				Context.getInstance().setScript(editor1.getText(), 0);
				Context.getInstance().setScript(editor2.getText(), 1);
				Context.getInstance().setScript(editor3.getText(), 2);
				Context.getInstance().setScript(editor4.getText(), 3);
				Context.getInstance().setScript(editor5.getText(), 4);
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
