import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class RiotAPI {
	public String APIkey; 
	
	public RiotAPI (String S){
		APIkey = S; 
	}

	public int getSummonerid(String summonername) throws Exception{
			summonername = summonername.toLowerCase(); 
			String url = "https://na.api.pvp.net/api/lol/na/v1.4/summoner/by-name/";
			url = url+summonername+"?api_key="+APIkey; 
			URL obj= new URL(url);
			HttpURLConnection urlconnection = (HttpURLConnection)obj.openConnection(); 
			urlconnection.setRequestMethod("GET");
			int responseCode = urlconnection.getResponseCode();
			System.out.println("\nSending 'GET' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);
			
			//read info here
			String thingtoParse = httpResult(urlconnection);
			
			//parse here
			int index1 = thingtoParse.indexOf("\"id\":");
			int index2 = thingtoParse.indexOf(",");
			String stringID = thingtoParse.substring(index1+5,index2);
			return Integer.parseInt(stringID);
			
		}
	
	public Summoner createSummoner(int summonerid) throws Exception{
		//Parameters to pass
		String League,Tier,Division; 
		int Leaguepoints; 
		
		
		String url = "https://na.api.pvp.net/api/lol/na/v2.5/league/by-summoner/";
		url = url+summonerid+"/entry?api_key="+APIkey; 
		URL obj= new URL(url);
		HttpURLConnection urlconnection = (HttpURLConnection)obj.openConnection(); 
		urlconnection.setRequestMethod("GET");
		int responseCode = urlconnection.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);
		
		//read info here
		String thingtoParse = httpResult(urlconnection);
		System.out.println(thingtoParse);
		
		//parse here
		int League_index1 = thingtoParse.indexOf("name");
		int League_index2 = thingtoParse.indexOf(",",League_index1);
		League = thingtoParse.substring(League_index1+7, League_index2-1);
		
		int Tier_index1 = thingtoParse.indexOf("tier");
		int Tier_index2 = thingtoParse.indexOf(",",Tier_index1);
		Tier = thingtoParse.substring(Tier_index1+7,Tier_index2-1);
		
		int Division_index1 = thingtoParse.indexOf("division");
		int Division_index2 = thingtoParse.indexOf(",",Division_index1);
		Division = thingtoParse.substring(Division_index1+11,Division_index2-1);
		
		int Leaguepoints_index1 = thingtoParse.indexOf("leaguePoints");
		int Leaguepoints_index2 = thingtoParse.indexOf(",",Leaguepoints_index1);
		Leaguepoints = Integer.parseInt(thingtoParse.substring(Leaguepoints_index1+14,Leaguepoints_index2));
		
		Summoner toReturn = new Summoner(League,Tier,Division,Leaguepoints);
		return toReturn; 
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
	
	
	
}
