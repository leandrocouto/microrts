package tests;

import ai.core.AI;
import ai.core.AIWithComputationBudget;
import ai.evaluation.EvaluationFunction;
import ai.evaluation.SimpleEvaluationFunction;
import ai.puppet.PuppetSearchMCTS;
import ai.puppet.SingleChoiceConfigurableScript;
import botCompeticao.ai.competition.tiamat.Tiamat;
import ai.*;
import ai.abstraction.HeavyRush;
import ai.abstraction.LightRush;
import ai.abstraction.RangedRush;
import ai.abstraction.WorkerRush;
import ai.abstraction.pathfinding.BFSPathFinding;
import ai.abstraction.pathfinding.FloodFillPathFinding;
import ai.abstraction.pathfinding.PathFinding;
import gui.PhysicalGameStatePanel;
import gui.myPhysicalGameStatePanel;
import myAI.Context;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import rts.GameState;
import rts.PhysicalGameState;
import rts.PlayerAction;
import rts.units.UnitTypeTable;



public class mySimulation {
	
	public static EvaluationFunction getEvaluationFunction() {
        return new SimpleEvaluationFunction();
        //return new SimpleOptEvaluationFunction();
        //return new LanchesterEvaluationFunction();
    }
	
	public static PathFinding getPathFinding() {
		//return new BFSPathFinding();
		//return new AStarPathFinding();
        return new FloodFillPathFinding();
    }
	
    private static int TIME = 100;
    private static int MAX_PLAYOUTS = -1;
    private static int PLAYOUT_TIME = 100;

    private static int PUPPET_PLAN_TIME = 5000;
    private static int PUPPET_PLAN_PLAYOUTS = -1;
	
    public static void main(String args[]) throws Exception {
        UnitTypeTable utt = new UnitTypeTable();
        Context.getInstance().setUtt(utt);
        PhysicalGameState pgs = PhysicalGameState.load("..\\maps\\8x8\\basesWorkers8x8.xml", utt);
        //PhysicalGameState pgs = PhysicalGameState.load("..\\maps\\16x16\\basesWorkers16x16.xml", utt);
        //PhysicalGameState pgs = PhysicalGameState.load("..\\maps\\24x24\\basesWorkers24x24.xml", utt);

        GameState gs = new GameState(pgs, utt);
        int MAXCYCLES = 5000;
        int PERIOD = 20;
        boolean gameover = false;
        
        //AI ai1 = new WorkerRush(utt, new BFSPathFinding());   
        AI ai1 = new LightRush(utt, new BFSPathFinding());
        //AI ai1 = new Tiamat(utt);
        //AI ai2 = new RandomBiasedAI();
        
        JFrame w = myPhysicalGameStatePanel.newVisualizer(gs,1280,640,false,PhysicalGameStatePanel.COLORSCHEME_BLACK);
        
        while(Context.getInstance().getSaveClicado() == false)
        	Thread.sleep(1000);
        //Botão Save foi clicado, então deve-se criar os objetos das IAs
        
        AIWithComputationBudget[] minhaIA = new AIWithComputationBudget[5];// = new AIWithComputationBudget(0,0);
        for(int i = 0; i < 5; i++)
        	minhaIA[i] = new AIWithComputationBudget(0,0);
        
        for(int i = 0; i < 5; i++) {
	        File myScriptJava = new File("myAI/MyNewAI_"+ i +".java");
	        if (myScriptJava.getParentFile().exists() || myScriptJava.getParentFile().mkdirs()) {
	        	
	        	try {
	                Writer writer = null;
	                try {
	                    writer = new FileWriter(myScriptJava);
	                    writer.write(Context.getInstance().getScript(i));
	                    writer.flush();
	                } finally {
	                    try {
	                        writer.close();
	                    } catch (Exception e) {
	                    }
	                }
	                
			        // Compilation Requirements
			        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
			        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
			        StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, null, null);
			
			        // This sets up the class path that the compiler will use.
			        // I've added the .jar file that contains the DoStuff interface within in it...
			        List<String> optionList = new ArrayList<String>();
			        optionList.add("-classpath");
			        optionList.add(System.getProperty("java.class.path") + ";dist/mySimulation.jar");
			        Iterable<? extends JavaFileObject> compilationUnit
					        = fileManager.getJavaFileObjectsFromFiles(Arrays.asList(myScriptJava));
					JavaCompiler.CompilationTask task = compiler.getTask(
					    null, 
					    fileManager, 
					    diagnostics, 
					    optionList, 
					    null, 
					    compilationUnit);
					
					//Compilation Requirements
	                if (task.call()) {
	                    //Load and execute
	                    // Create a new custom class loader, pointing to the directory that contains the compiled
	                    // classes, this should point to the top of the package structure!
	                    URLClassLoader classLoader = new URLClassLoader(new URL[]{new File("./").toURI().toURL()});
	                    // Load the class from the classloader by name....
	                    Class<?> loadedClass = classLoader.loadClass("myAI.MyNewAI_"+i);
	                    // Create a new instance...
	                    Object obj = loadedClass.newInstance();
	                    // Santity check
	                    if (obj instanceof AIWithComputationBudget) {
	                        // Cast to AIWithComputationBudget
	                    	minhaIA[i] = (AIWithComputationBudget)obj;
	                    }
	                    //Load and execute
	                } else {
	                    for (Diagnostic<? extends JavaFileObject> diagnostic : diagnostics.getDiagnostics()) {
	                        System.out.format("Error on line %d in %s%n",
	                                diagnostic.getLineNumber(),
	                                diagnostic.getSource().toUri());
	                    }
	                }
	                fileManager.close();
		        }
		    	catch (IOException | ClassNotFoundException | InstantiationException | IllegalAccessException exp) {
		            exp.printStackTrace();
		        }
	        	
	        }
        }	
        
        
        AI ai2 = new PuppetSearchMCTS(
                TIME, MAX_PLAYOUTS,
                PUPPET_PLAN_TIME, PUPPET_PLAN_PLAYOUTS,
                PLAYOUT_TIME, PLAYOUT_TIME,
                new RandomBiasedAI(),
                new SingleChoiceConfigurableScript(getPathFinding(),
                        new AI[]{minhaIA[0],minhaIA[1],minhaIA[2],minhaIA[3], minhaIA[4]}),
                getEvaluationFunction());
        
        while(Context.getInstance().getRunClicado() == false)
        	Thread.sleep(1000);
        
        	
        long nextTimeToUpdate = System.currentTimeMillis() + PERIOD;
        do{
            if (System.currentTimeMillis()>=nextTimeToUpdate) {
                PlayerAction pa1 = ai1.getAction(0, gs);
                PlayerAction pa2 = ai2.getAction(1, gs);
                gs.issueSafe(pa1);
                gs.issueSafe(pa2);

                // simulate:
                gameover = gs.cycle();
                w.repaint();
                nextTimeToUpdate+=PERIOD;
            } else {
                try {
                    Thread.sleep(1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }while(!gameover && gs.getTime()<MAXCYCLES);
        ai1.gameOver(gs.winner());
        ai2.gameOver(gs.winner());
        
        System.out.println("Game Over");
    }    
}
