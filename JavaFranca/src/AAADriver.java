
public class AAADriver {
	
	public static void main(String[] args) {
		GraphObject music = new GraphObject("Music");
		music.addNick("Songs");
		GraphObject artists = new GraphObject("Artists");
		artists.add("make", music);
		System.out.println(artists.toSentence());
	}

}
