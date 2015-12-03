public class main {

	public static void main(String[] args) {
		RiotAPI testing = new RiotAPI("00e82d10-743e-4adf-a206-2c967817a311");//Makes RiotAPI and gives API key
		try{
			Summoner Kumori_Yuki =testing.createSummoner("kumoriyuki");//Creates Summoner
			Kumori_Yuki.fillAnalysis("RANKED_SOLO_5x5");//Filter what games you want: Use "Ranked_SOLO_5x5", "Ranked_Team_5x5",etc.
			
			//Testing creation of summoners
			System.out.println("\nSummoner: "+Kumori_Yuki.getSummonername());
			System.out.println("League: "+Kumori_Yuki.getLeague());
			System.out.println("Tier: "+Kumori_Yuki.getTier());
			System.out.println("Division: "+Kumori_Yuki.getDivision());
			System.out.println("LP: "+Kumori_Yuki.getLP());
			
			//Example of getting other summoners in a match
			int[] matches = Kumori_Yuki.getMatches(); 
			String[] summonersingame= testing.getSummoners(matches[0]);
			for(int i=0;i<10;i++){
				System.out.println("Summoner in most recent ranked game: "+summonersingame[i]);
			}
			
			//Getting MMR Test
			
		}
		catch(Exception e){
			
		}
	}

}
