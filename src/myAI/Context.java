package myAI;

import rts.units.UnitTypeTable;

public class Context {
	private final static Context instance = new Context();

    public static Context getInstance() {
        return instance;
    }
    
    private boolean saveFoiClicado = false;
    private boolean runFoiClicado = false;
    private String[] script = new String[5];
    private UnitTypeTable utt = new UnitTypeTable();
    
    public boolean getSaveClicado() {
    	return saveFoiClicado;
    }
    
    public void setSaveClicado(boolean a) {
    	saveFoiClicado = a;
    }
    
    public boolean getRunClicado() {
    	return runFoiClicado;
    }
    
    public void setRunClicado(boolean a) {
    	runFoiClicado = a;
    }

	public String getScript(int i) {
		return script[i];
	}

	public void setScript(String script, int i) {
		this.script[i] = script;
	}

	public UnitTypeTable getUtt() {
		return utt;
	}

	public void setUtt(UnitTypeTable utt) {
		this.utt = utt;
	}
    
    
}
