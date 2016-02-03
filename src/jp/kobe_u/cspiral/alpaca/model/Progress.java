package jp.kobe_u.cspiral.alpaca.model;

public class Progress {
	private String UCName;
	private double ProgressRate;

	public Progress(String UCName,double ProgressRate){
		this.UCName = UCName;
		this.ProgressRate = ProgressRate;
	}

	public String getUCName() {
		return UCName;
	}
	public void setUCName(String uCName) {
		UCName = uCName;
	}
	public double getProgressRate() {
		return ProgressRate;
	}
	public void setProgressRate(double progressRate) {
		ProgressRate = progressRate;
	}

}
