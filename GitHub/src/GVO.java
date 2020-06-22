package Game;

public class GVO {
private String Jmean;
private String Jsound;
private int No;
private String Hanja;
private String Kmean;
private String Ksound;

public GVO(String Jmean, String Jsound, int No,String Hanja,String Kmean,String Ksound) {
	this.Jmean=Jmean;
	this.Jsound=Jsound;
	this.No=No;
	this.Hanja=Hanja;
	this.Kmean=Kmean;
	this.Ksound=Ksound;
}

public String getKmean() {
	return Kmean;
}

public void setKmean(String kmean) {
	Kmean = kmean;
}

public String getKsound() {
	return Ksound;
}

public void setKsound(String ksound) {
	Ksound = ksound;
}

public String getHanja() {
	return Hanja;
}

public void setHanja(String hanja) {
	Hanja = hanja;
}

public String getJmean() {
	return Jmean;
}

public void setJmean(String jmean) {
	Jmean = jmean;
}

public String getJsound() {
	return Jsound;
}

public void setJsound(String jsound) {
	Jsound = jsound;
}

public int getNo() {
	return No;
}

public void setNo(int no) {
	No = no;
}

}
