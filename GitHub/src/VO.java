
public class VO {
	private String Hanja;
	private String Ksound;
	private String Kmean;
	private String Jsound;
	private String Jmean;
	private int no;

	public VO(String Hanja, String Ksound, String Kmean, String Jsound, String Jmean, int no) {
		this.Hanja = Hanja;
		this.Ksound = Ksound;
		this.Kmean = Kmean;
		this.Jsound = Jsound;
		this.Jmean = Jmean;
		this.no = no;

	}

	public String getHanja() {
		return Hanja;
	}

	public void setHanja(String hanja) {
		Hanja = hanja;
	}

	public String getKsound() {
		return Ksound;
	}

	public void setKsound(String ksound) {
		Ksound = ksound;
	}

	public String getKmean() {
		return Kmean;
	}

	public void setKmean(String kmean) {
		Kmean = kmean;
	}

	public String getJsound() {
		return Jsound;
	}

	public void setJsound(String jsound) {
		Jsound = jsound;
	}

	public String getJmean() {
		return Jmean;
	}

	public void setJmean(String jmean) {
		Jmean = jmean;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

}
