import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Summoner {
	private String APIkey; 
	private String League,Tier,Division;
	private int Leaguepoints, Summonerid; 
	private int[] matchAnalysis = new int[10];
	
	
	
	public Summoner(String key, String L, String T,String D, int LP, int S){
		APIkey = key; 
		League = L; 
		Tier = T; 
		Division = D; 
		Leaguepoints = LP; 
		Summonerid = S; 
	}
	
	//Acceptable Values: "RANKED_SOLO_5x5", "RANKED_TEAM_3x3", "RANKED_TEAM_5x5"
	public void fillAnalysis(String type) throws Exception{
		String url = "https://na.api.pvp.net/api/lol/na/v2.2/matchlist/by-summoner/"+Summonerid+"?rankedQueues=";
		url = url+type+"&seasons=PRESEASON2016&beginIndex=0&endIndex=10&api_key="+APIkey; 
		
		URL obj= new URL(url);
		HttpURLConnection urlconnection = (HttpURLConnection)obj.openConnection(); 
		urlconnection.setRequestMethod("GET");
		int responseCode = urlconnection.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);
		
		//read info here
		String thingtoParse = httpResult(urlconnection);
		int index=0; 
		for(int i=0;i<10;i++){
			int index1 = thingtoParse.indexOf("matchId",index);
			int index2 = thingtoParse.indexOf(",",index1);
			index = index2; 
			matchAnalysis[i]=Integer.parseInt(thingtoParse.substring(index1+9, index2));
			System.out.println("Match "+i+": "+matchAnalysis[i]);
		}
		
		
	}

	private String httpResult(HttpURLConnection connection) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		return response.toString(); 
	}
	
	//*******************************************GRABBING VALUES****************************************
	
	public String getLeague(){
		return League; 
	}

	public String getTier(){
		return Tier; 
	}
	
	public String getDivision(){
		return Division; 
	}

	public int getLP(){
		return Leaguepoints; 
	}
	
	public int getID(){
		return Summonerid; 
	}
	
	public int[] getMatches(){
		return matchAnalysis; 
	}

}
