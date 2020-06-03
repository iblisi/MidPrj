
public class KVO {
private int No;
private String KMean;
private String KSound;
	
	public KVO(){
	
}
	
	public KVO(int No,String KMean, String KSound) {
		this.No=No;
		this.KMean=KMean;
		this.KSound=KSound;
	}
	
	public int getNo() {
		return No;
	}
	
	public String getKMean() {
		return KMean;
	}
	
	public String getKSound() {
		return KSound;
	}
}
