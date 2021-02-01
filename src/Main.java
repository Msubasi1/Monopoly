import java.io.*; 
import java.util.*; 
import org.json.simple.*;
import org.json.simple.JSONArray;
import org.json.simple.parser.*; 

public class Main {
	public static String banker;
	public static int banker_money = 100000;
	public static int counter_lands = 0;
	public static int counter_companies =0;
	public static int counter_railroads=0;

	public static void main(String[] args) throws Exception{
		////get properties
	try {
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(new FileReader("property.json"));
		JSONObject jsonObject = (JSONObject) obj;
		JSONArray lands = (JSONArray) jsonObject.get("1");
		Iterator<JSONObject> iterator1 = lands.iterator();
	    while (iterator1.hasNext()) {
	    	JSONObject o1 = iterator1.next();
	        String name = (String) o1.get("name");
	        String loca = (String) o1.get("id");
	        String costs = (String) o1.get("cost");
	        int location = Integer.parseInt(loca);
	        int cost = Integer.parseInt(costs);
	        Lands.create_land(name,cost,location);
	    }
	    JSONArray  railroads =(JSONArray) jsonObject.get("2");
		Iterator<JSONObject> iterator2 = railroads.iterator();
		while (iterator2.hasNext()) {
			JSONObject o2 = iterator2.next();
	        String name = (String) o2.get("name");
	        String loca  = (String) o2.get("id");
	        String costs = (String) o2.get("cost");
	        int location = Integer.parseInt(loca);
	        int cost = Integer.parseInt(costs);
	        Railroads.create_railroad(name,cost,location);
	    }
		JSONArray companies = (JSONArray) jsonObject.get("3");
		Iterator<JSONObject> iterator3 = companies.iterator();
		while(iterator3.hasNext()) {
			JSONObject o3 = iterator3.next();
			String name = (String) o3.get("name");
			String loca  = (String) o3.get("id");
	        String costs = (String) o3.get("cost");
	        int location = Integer.parseInt(loca);
	        int cost = Integer.parseInt(costs);
			Companies.create_companies(name,cost,location);
		}
		JSONParser parsek = new JSONParser();
		Object obje = parser.parse(new FileReader("list.json"));
		JSONObject jsonobjecte = (JSONObject) obje;
		JSONArray chance_list = (JSONArray) jsonobjecte.get("chanceList");
		Iterator<JSONObject> iterator4 = chance_list.iterator();
		while(iterator4.hasNext()) {
			JSONObject o4 = iterator4.next();
			String card = (String) o4.get("item");
			ChanceCards.createChanceCards(card);
		}
		JSONArray community_list = (JSONArray) jsonobjecte.get("communityChestList");
		Iterator<JSONObject> iterator5 = community_list.iterator();
		while(iterator5.hasNext()){
			JSONObject o5 = iterator5.next();
			String card = (String) o5.get("item");
			CommunityChest.createCommunityCards(card);
		}
	}catch(FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    } catch (ParseException e) {
        e.printStackTrace();
    }	
	Players obj1 = new Player1("Player 1",15000,false,0,1,null,null,null,null);
	Players obj2 = new Player2("Player 2",15000,false,0,1,null,null,null,null);
	Rentable x = new Rentable("GO",0,false,"go",0,0);
	Rentable.rentables[1] =x;
	File f = new File("command.txt");
	if(!f.exists()) {
		System.out.println("there isn't any commands file");	
	}
	else {
		try {
			PrintWriter dosyayaz =new PrintWriter("output.txt");
			Scanner line = new Scanner(f);
			while(line.hasNext()) {
				String lines = line.next();
				if(lines.compareTo("Player")==0) {
					String linea = line.next();
					//implements
					String[] game = linea.split(";");
					int player_no = Integer.parseInt(game[0]);
					int dice = Integer.parseInt(game[1]);
					
					if(player_no==1 && obj1.isIs_jail()==false) {
						obj1.setLocation(dice+obj1.getLocation());
						
						if(obj1.getLocation()>40) {
							//go implements
							obj1.setLocation(obj1.getLocation()- 40);
							if(obj1.getLocation()==11){

							}
							else{
								obj1.setMoney(200);
								banker_money =banker_money-200;
								if(obj1.getLocation()==1){
									dosyayaz.println("Player 1\t"+dice+"\t"+obj1.getLocation()+"\t"+obj1.getMoney()+"\t"+obj2.getMoney()+"\t"+"Player 1 is in GO square");	
								}
							}
						}
						if(obj1.getLocation()==31) {
							//jail implements
							obj1.setLocation(11);
							dosyayaz.println("Player 1\t"+dice+"\t"+obj1.getLocation()+"\t"+obj1.getMoney()+"\t"+obj2.getMoney()+"\t"+"Player 1 went to jail");
							obj1.setIs_jail(true);
						}
						else if(obj1.getLocation()==11) {
							//jail implements	
							dosyayaz.println("Player 1\t"+dice+"\t"+obj1.getLocation()+"\t"+obj1.getMoney()+"\t"+obj2.getMoney()+"\t"+"Player 1 went to jail");
							obj1.setIs_jail(true);
						}
						else if(obj1.getLocation()==21) {
							dosyayaz.println("Player 1\t"+dice+"\t"+obj1.getLocation()+"\t"+obj1.getMoney()+"\t"+obj2.getMoney()+"\t"+"Player 1 is in Free parking");
						}
						else if(obj1.getLocation()==5 || obj1.getLocation()==39) {
							if(obj1.getMoney()<100) {
								dosyayaz.println("Player 1\t"+dice+"\t"+obj1.getLocation()+"\t"+obj1.getMoney()+"\t"+obj2.getMoney()+"\t"+"Player 1 goes bankrupt");
							}
							else {
								obj1.setMoney(-100);
								banker_money = banker_money+100;
								dosyayaz.println("Player 1\t"+dice+"\t"+obj1.getLocation()+"\t"+obj1.getMoney()+"\t"+obj2.getMoney()+"\t"+"Player 1 paid Tax");
							}
						}
						else if(obj1.getLocation()==3 || obj1.getLocation()==18 || obj1.getLocation()==34) {
							String out = CommunityChest.give_community_card(obj1,obj2);
							if(out.compareTo("Player 1 goes bankrupt")==0 || out.compareTo("Player 2 goes bankrupt")==0 ) {
								dosyayaz.println("Player 1\t"+dice+"\t"+obj1.getLocation()+"\t"+obj1.getMoney()+"\t"+obj2.getMoney()+"\t"+"Player 1 goes bankrupt"+out);
								break;
							}
							else {
								dosyayaz.println("Player 1\t"+dice+"\t"+obj1.getLocation()+"\t"+obj1.getMoney()+"\t"+obj2.getMoney()+"\t"+"Player 1 draw " +out);
							}
						}
						else if(obj1.getLocation()==8 || obj1.getLocation()==23 || obj1.getLocation()==37) {
							String out = ChanceCards.give_chance_card(obj1, obj2);
							if(out.compareTo("Player 1 goes bankrupt")==0 ||out.compareTo("Player 2 goes bankrupt")==0 ) {
								dosyayaz.println("Player 1\t"+dice+"\t"+obj1.getLocation()+"\t"+obj1.getMoney()+"\t"+obj2.getMoney()+"\t"+"Player 1 goes bankrupt"+out);
								break;
							}
							else {
								dosyayaz.println("Player 1\t"+dice+"\t"+obj1.getLocation()+"\t"+obj1.getMoney()+"\t"+obj2.getMoney()+"\t"+"Player 1 draw "+out);
							}
						}
						else {
							//buy or rent for area
							
							if(Rentable.rentables[obj1.getLocation()].isFree()==true) {
								//buy
								if(Rentable.rentables[obj1.getLocation()].getCost()>obj1.getMoney()) {
									dosyayaz.println("Player 1\t"+dice+"\t"+obj1.getLocation()+"\t"+obj1.getMoney()+"\t"+obj2.getMoney()+"\t"+"Player 1 goes bankrupt");
									break;
								}
								else {
									Rentable.rentables[obj1.getLocation()].setFree(false);
									Rentable.rentables[obj1.getLocation()].setOwner_name("Player 1");
									if(Rentable.rentables[obj1.getLocation()] instanceof Lands){
										obj1.setMoney(- Rentable.rentables[obj1.getLocation()].getCost());
										banker_money = banker_money+Rentable.rentables[obj1.getLocation()].getCost();
										dosyayaz.println("Player 1\t"+dice+"\t"+obj1.getLocation()+"\t"+obj1.getMoney()+"\t"+obj2.getMoney()+"\t"+"Player 1 bought "+ Rentable.rentables[obj1.getLocation()].getLand_name());
										obj1.setLands(Rentable.rentables[obj1.getLocation()].getLand_name());
										obj1.setAllare(Rentable.rentables[obj1.getLocation()].getLand_name());
									}
									else if(Rentable.rentables[obj1.getLocation()] instanceof Companies) {
										obj1.setMoney(- Rentable.rentables[obj1.getLocation()].getCost());
										banker_money = banker_money+Rentable.rentables[obj1.getLocation()].getCost();
										dosyayaz.println("Player 1\t"+dice+"\t"+obj1.getLocation()+"\t"+obj1.getMoney()+"\t"+obj2.getMoney()+"\t"+"Player 1 bought "+ Rentable.rentables[obj1.getLocation()].getLand_name());
										obj1.setCompanies(Rentable.rentables[obj1.getLocation()].getLand_name());
										obj1.setAllare(Rentable.rentables[obj1.getLocation()].getLand_name());
									}
									else if(Rentable.rentables[obj1.getLocation()] instanceof Railroads) {
										obj1.setMoney(- Rentable.rentables[obj1.getLocation()].getCost());
										banker_money = banker_money+Rentable.rentables[obj1.getLocation()].getCost();
										dosyayaz.println("Player 1\t"+dice+"\t"+obj1.getLocation()+"\t"+obj1.getMoney()+"\t"+obj2.getMoney()+"\t"+"Player 1 bought "+ Rentable.rentables[obj1.getLocation()].getLand_name());
										obj1.setRailroads(Rentable.rentables[obj1.getLocation()].getLand_name());
										obj1.setAllare(Rentable.rentables[obj1.getLocation()].getLand_name());
										}
									}
								}
							else {
								//rent
								if((Rentable.rentables[obj1.getLocation()].getOwner_name()).compareTo("Player 1")==0) {
									dosyayaz.println("Player 1\t"+dice+"\t"+obj1.getLocation()+"\t"+obj1.getMoney()+"\t"+obj2.getMoney()+"\t"+"Player 1 has "+ Rentable.rentables[obj1.getLocation()].getLand_name());
								}
								else if(Rentable.rentables[obj1.getLocation()] instanceof Lands) {
									int rent = Rentable.rentables[obj1.getLocation()].setRent_amount(Rentable.rentables[obj1.getLocation()].getCost());
									if(rent>obj1.getMoney()) {
										dosyayaz.println("Player 1\t"+dice+"\t"+obj1.getLocation()+"\t"+obj1.getMoney()+"\t"+obj2.getMoney()+"\t"+"Player 1 goes bankrupt");
										break;
									}
									else {
										obj1.setMoney(-rent);
										obj2.setMoney(+rent);
										dosyayaz.println("Player 1\t"+dice+"\t"+obj1.getLocation()+"\t"+obj1.getMoney()+"\t"+obj2.getMoney()+"\t"+"Player 1 paid rent for "+ Rentable.rentables[obj1.getLocation()].getLand_name());
									}
								}
								else if(Rentable.rentables[obj1.getLocation()] instanceof Companies) {
									int rent = Rentable.rentables[obj1.getLocation()].setRent_amount(dice);
									if(rent>obj1.getMoney()) {
										dosyayaz.println("Player 1\t"+dice+"\t"+obj1.getLocation()+"\t"+obj1.getMoney()+"\t"+obj2.getMoney()+"\t"+"Player 1 goes bankrupt");
										break;
									}
									else {
										obj1.setMoney(-rent);
										obj2.setMoney(+rent);
										dosyayaz.println("Player 1\t"+dice+"\t"+obj1.getLocation()+"\t"+obj1.getMoney()+"\t"+obj2.getMoney()+"\t"+"Player 1 paid rent for "+ Rentable.rentables[obj1.getLocation()].getLand_name());	
									}
								}
								else if(Rentable.rentables[obj1.getLocation()] instanceof Railroads) {
									int rent  = obj2.getRailroads().size()*25;
									if(rent>obj1.getMoney()) {
										dosyayaz.println("Player 1\t"+dice+"\t"+obj1.getLocation()+"\t"+obj1.getMoney()+"\t"+obj2.getMoney()+"\t"+"Player 1 goes bankrupt");
										break;
									}
									else {
										obj1.setMoney(-rent);
										obj2.setMoney(+rent);
										dosyayaz.println("Player 1\t"+dice+"\t"+obj1.getLocation()+"\t"+obj1.getMoney()+"\t"+obj2.getMoney()+"\t"+"Player 1 paid rent for "+ Rentable.rentables[obj1.getLocation()].getLand_name());
									}
								}
								else {
									//
								}
							}	
						}
					}
					else if (player_no==1 && obj1.isIs_jail() == true){
						//jail implement for player1
						if(obj1.getNumber_jail()!=2) {
							obj1.setNumber_jail(obj1,0);
							dosyayaz.println("Player 1\t"+dice+"\t"+obj1.getLocation()+"\t"+obj1.getMoney()+"\t"+obj2.getMoney()+"\t"+"Player 1 in jail (count ="+(obj1.getNumber_jail())+")");
						}
						else {
							obj1.setNumber_jail(obj1,0);
							dosyayaz.println("Player 1\t"+dice+"\t"+obj1.getLocation()+"\t"+obj1.getMoney()+"\t"+obj2.getMoney()+"\t"+"Player 1 in jail (count ="+(obj1.getNumber_jail())+")");
							obj1.setIs_jail(false);
							obj1.setNumber_jail(obj1,0);						}
					}
					else if(player_no==2 && obj2.isIs_jail() == false){
						//player2 impelementation
						obj2.setLocation(obj2.getLocation()+dice);
						if(obj2.getLocation()>40) {
							//go implements
							obj2.setLocation(obj2.getLocation()- 40);
							if(obj1.getLocation()==11){

							}
							else{
								obj2.setMoney(200);
								banker_money = banker_money-200;
								if(obj2.getLocation()==1){
									dosyayaz.println("Player 2\t"+dice+"\t"+obj2.getLocation()+"\t"+obj1.getMoney()+"\t"+obj2.getMoney()+"\t"+"Player 2 is in GO square");
								}
							}
						}
						if(obj2.getLocation()==31) {
							obj2.setLocation(11);
							dosyayaz.println("Player 2\t"+dice+"\t"+obj2.getLocation()+"\t"+obj1.getMoney()+"\t"+obj2.getMoney()+"\t"+"Player 2 went to jail");
							obj2.setIs_jail(true);
							
						}
						else if(obj2.getLocation()==11) {
							dosyayaz.println("Player 2\t"+dice+"\t"+obj2.getLocation()+"\t"+obj1.getMoney()+"\t"+obj2.getMoney()+"\t"+"Player 2 went to jail");
							obj2.setIs_jail(true);
						}
						else if(obj2.getLocation()==21) {
							dosyayaz.println("Player 2\t"+dice+"\t"+obj2.getLocation()+"\t"+obj1.getMoney()+"\t"+obj2.getMoney()+"\t"+"Player 2 is in Free parking");
						}
						else if(obj2.getLocation()==5 || obj2.getLocation()==39) {
							if(obj2.getMoney()<100) {
								dosyayaz.println("Player 2\t"+dice+"\t"+obj2.getLocation()+"\t"+obj1.getMoney()+"\t"+obj2.getMoney()+"\t"+"Player 2 goes bankrupt");
							}
							else {
								obj2.setMoney(-100);
								banker_money = banker_money+100;
								dosyayaz.println("Player 2\t"+dice+"\t"+obj2.getLocation()+"\t"+obj1.getMoney()+"\t"+obj2.getMoney()+"\t"+"Player 2 paid Tax");
							}
						}
						else if(obj2.getLocation()==3 || obj2.getLocation()==18 || obj2.getLocation()==34) {			
							String out = CommunityChest.give_community_card(obj2,obj1);
							if(out.compareTo("Player 1 goes bankrupt")==0 ||out.compareTo("Player 2 goes bankrupt")==0 ) {
								dosyayaz.println("Player 2\t"+dice+"\t"+obj2.getLocation()+"\t"+obj1.getMoney()+"\t"+obj2.getMoney()+"\t"+"Player 2 goes bankrupt"+out);
								break;
							}
							else {
								dosyayaz.println("Player 2\t"+dice+"\t"+obj2.getLocation()+"\t"+obj1.getMoney()+"\t"+obj2.getMoney()+"\t"+"Player 2 draw " +out);
							}	
						}
						else if(obj2.getLocation()==8 || obj2.getLocation()==23 || obj2.getLocation()==37) {
							String out = ChanceCards.give_chance_card(obj2, obj1);
							if(out.compareTo("Player 1 goes bankrupt")==0 ||out.compareTo("Player 2 goes bankrupt")==0 ) {
								dosyayaz.println("Player 2\t"+dice+"\t"+obj2.getLocation()+"\t"+obj1.getMoney()+"\t"+obj2.getMoney()+"\t"+"Player 2 goes bankrupt"+out);
								break;
							}
							else {
								dosyayaz.println("Player 2\t"+dice+"\t"+obj2.getLocation()+"\t"+obj1.getMoney()+"\t"+obj2.getMoney()+"\t"+"Player 2 draw "+out);
							}
						}
						else {
							//buy or rent for area
							if(Rentable.rentables[obj2.getLocation()].isFree()==true) {
								//buy
								if(Rentable.rentables[obj2.getLocation()].getCost()>obj2.getMoney()) {
									dosyayaz.println("Player 2\t"+dice+"\t"+obj2.getLocation()+"\t"+obj1.getMoney()+"\t"+obj2.getMoney()+"\t"+"Player 2 goes bankrupt");
									break;
								}
								else {
								Rentable.rentables[obj2.getLocation()].setFree(false);
								Rentable.rentables[obj2.getLocation()].setOwner_name("Player 2");
								if(Rentable.rentables[obj2.getLocation()] instanceof Lands){
									obj2.setMoney(-Rentable.rentables[obj2.getLocation()].getCost());
									banker_money = banker_money+Rentable.rentables[obj2.getLocation()].getCost();
									dosyayaz.println("Player 2\t"+dice+"\t"+obj2.getLocation()+"\t"+obj1.getMoney()+"\t"+obj2.getMoney()+"\t"+"Player 2 bought "+ Rentable.rentables[obj2.getLocation()].getLand_name());
									obj2.setLands(Rentable.rentables[obj2.getLocation()].getLand_name());
									obj2.setAllare(Rentable.rentables[obj2.getLocation()].getLand_name());
								}
								else if(Rentable.rentables[obj2.getLocation()] instanceof Companies) {
									obj2.setMoney(-Rentable.rentables[obj2.getLocation()].getCost());
									banker_money = banker_money+Rentable.rentables[obj2.getLocation()].getCost();
									dosyayaz.println("Player 2\t"+dice+"\t"+obj2.getLocation()+"\t"+obj1.getMoney()+"\t"+obj2.getMoney()+"\t"+"Player 2 bought "+ Rentable.rentables[obj2.getLocation()].getLand_name());
									obj2.setCompanies(Rentable.rentables[obj2.getLocation()].getLand_name());
									obj2.setAllare(Rentable.rentables[obj2.getLocation()].getLand_name());
								}
								else if(Rentable.rentables[obj2.getLocation()] instanceof Railroads) {
									obj2.setMoney(-Rentable.rentables[obj2.getLocation()].getCost());
									banker_money = banker_money+Rentable.rentables[obj2.getLocation()].getCost();
									dosyayaz.println("Player 2\t"+dice+"\t"+obj2.getLocation()+"\t"+obj1.getMoney()+"\t"+obj2.getMoney()+"\t"+"Player 2 bought "+ Rentable.rentables[obj2.getLocation()].getLand_name());
									obj2.setRailroads(Rentable.rentables[obj2.getLocation()].getLand_name());
									obj2.setAllare(Rentable.rentables[obj2.getLocation()].getLand_name());
									}
								}
							}
							else {
								//rent
								if(Rentable.rentables[obj2.getLocation()].getOwner_name().compareTo("Player 2")==0) {
									dosyayaz.println("Player 2\t"+dice+"\t"+obj2.getLocation()+"\t"+obj1.getMoney()+"\t"+obj2.getMoney()+"\t"+"Player 2 has "+ Rentable.rentables[obj2.getLocation()].getLand_name());
								}
								else if(Rentable.rentables[obj2.getLocation()] instanceof Lands) {
									int rent = Rentable.rentables[obj2.getLocation()].setRent_amount(Rentable.rentables[obj2.getLocation()].getCost());
									if(rent>obj2.getMoney()) {
										dosyayaz.println("Player 2\t"+dice+"\t"+obj2.getLocation()+"\t"+obj1.getMoney()+"\t"+obj2.getMoney()+"\t"+"Player 2 goes bankrupt");
										break;
									}
									else {
										obj1.setMoney(+rent);
										obj2.setMoney(-rent);
										dosyayaz.println("Player 2\t"+dice+"\t"+obj2.getLocation()+"\t"+obj1.getMoney()+"\t"+obj2.getMoney()+"\t"+"Player 2 paid rent for "+ Rentable.rentables[obj2.getLocation()].getLand_name());
									}
								}
								else if(Rentable.rentables[obj2.getLocation()] instanceof Companies) {
									int rent = Rentable.rentables[obj2.getLocation()].setRent_amount(dice);
									if(rent>obj2.getMoney()) {
										dosyayaz.println("Player 2\t"+dice+"\t"+obj2.getLocation()+"\t"+obj1.getMoney()+"\t"+obj2.getMoney()+"\t"+"Player 2 goes bankrupt");
										break;
									}
									else {
										obj1.setMoney(+rent);
										obj2.setMoney(-rent);
										dosyayaz.println("Player 2\t"+dice+"\t"+obj2.getLocation()+"\t"+obj1.getMoney()+"\t"+obj2.getMoney()+"\t"+"Player 2 paid rent for "+ Rentable.rentables[obj2.getLocation()].getLand_name());
									}
								}
								else if(Rentable.rentables[obj2.getLocation()] instanceof Railroads) {
									int rent  = obj1.getRailroads().size()*25;
									if(rent>obj2.getMoney()) {
										dosyayaz.println("Player 2\t"+dice+"\t"+obj2.getLocation()+"\t"+obj1.getMoney()+"\t"+obj2.getMoney()+"\t"+"Player 2 goes bankrupt");
										break;
									}
									else {
										obj1.setMoney(+rent);
										obj2.setMoney(-rent);
										dosyayaz.println("Player 2\t"+dice+"\t"+obj2.getLocation()+"\t"+obj1.getMoney()+"\t"+obj2.getMoney()+"\t"+"Player 2 paid rent for "+ Rentable.rentables[obj2.getLocation()].getLand_name());
									}
								}
								else {
									//
								}
							}	
						}
					}
					else if(player_no==2 && obj2.isIs_jail()==true) {
						//jail implement for player2
						if(obj2.getNumber_jail()!=2) {
							obj2.setNumber_jail(obj2,0);
							dosyayaz.println("Player 2\t"+dice+"\t"+obj2.getLocation()+"\t"+obj1.getMoney()+"\t"+obj2.getMoney()+"\t"+"Player 2 in jail (count ="+(obj2.getNumber_jail())+")");
						}
						else {
							obj2.setNumber_jail(obj2,0);
							dosyayaz.println("Player 2\t"+dice+"\t"+obj2.getLocation()+"\t"+obj1.getMoney()+"\t"+obj2.getMoney()+"\t"+"Player 2 in jail (count ="+(obj2.getNumber_jail())+")");
							obj2.setIs_jail(false);
							obj2.setNumber_jail(obj2,0);
						}
					}
					else {
						dosyayaz.println("unknown player");
					}
				}
				else if(lines.compareTo("show()")==0){
					dosyayaz.println("-----------------------------------------------------------------------------------------------------------");
					//implements
					dosyayaz.print("Player 1 \t" + obj1.getMoney() +"\t have: ");				
					for(int i=0;i<obj1.getAllarea().size();i++) {
						dosyayaz.print(obj1.getAllarea().get(i));
						if(i!=obj1.getAllarea().size()-1) {
							dosyayaz.print(", ");
						}
					}
					dosyayaz.println();
					dosyayaz.print("Player 2 \t" + obj2.getMoney() +"\t have: ");
					for(int i=0;i<obj2.getAllarea().size();i++) {
						dosyayaz.print(obj2.getAllarea().get(i));
						if(i!=obj2.getAllarea().size()-1) {
							dosyayaz.print(", ");
						}
					}
					dosyayaz.println();
					dosyayaz.println("Banker \t"+banker_money);
					String out = null;
					if(obj1.getMoney()>obj2.getMoney()) {
						out = "Player 1";
					}
					else {
						out = "Player 2";
					}
					dosyayaz.println("Winner "+out);
					dosyayaz.println("-----------------------------------------------------------------------------------------------------------");
				}
				else {
					dosyayaz.println("unknown command");
				}
			}
			if(line.hasNext()==false) {
				dosyayaz.println("-----------------------------------------------------------------------------------------------------------");
				//implements
				dosyayaz.print("Player 1 \t" + obj1.getMoney() +"\t have: ");				
				for(int i=0;i<obj1.getAllarea().size();i++) {
					dosyayaz.print(obj1.getAllarea().get(i));
					if(i!=obj1.getAllarea().size()-1) {
						dosyayaz.print(", ");
					}
				}
				dosyayaz.println();
				dosyayaz.print("Player 2 \t" + obj2.getMoney() +"\t have: ");
				for(int i=0;i<obj2.getAllarea().size();i++) {
					dosyayaz.print(obj2.getAllarea().get(i));
					if(i!=obj2.getAllarea().size()-1) {
						dosyayaz.print(", ");
					}
				}
				dosyayaz.println();
				dosyayaz.println("Banker \t"+banker_money);
				String out = null;
				if(obj1.getMoney()>obj2.getMoney()) {
					out = "Player 1";
				}
				else {
					out = "Player 2";
				}
				dosyayaz.println("Winner "+out);
				dosyayaz.println("-----------------------------------------------------------------------------------------------------------");
			}
			else {
				dosyayaz.println("-----------------------------------------------------------------------------------------------------------");
				//implements
				dosyayaz.print("Player 1 \t" + obj1.getMoney() +"\t have: ");				
				for(int i=0;i<obj1.getAllarea().size();i++) {
					dosyayaz.print(obj1.getAllarea().get(i));
					if(i!=obj1.getAllarea().size()-1) {
						dosyayaz.print(", ");
					}
				}
				dosyayaz.println();
				dosyayaz.print("Player 2 \t" + obj2.getMoney() +"\t have: ");
				for(int i=0;i<obj2.getAllarea().size();i++) {
					dosyayaz.print(obj2.getAllarea().get(i));
					if(i!=obj2.getAllarea().size()-1) {
						dosyayaz.print(", ");
					}
				}
				dosyayaz.println();
				dosyayaz.println("Banker \t"+banker_money);
				String out = null;
				if(obj1.getMoney()>obj2.getMoney()) {
					out = "Player 1";
				}
				else {
					out = "Player 2";
				}
				dosyayaz.println("Winner "+out);
				dosyayaz.println("-----------------------------------------------------------------------------------------------------------");
			}
		dosyayaz.close();	
	}catch(Exception e) {
		e.printStackTrace();
			}
    	}
	}
}
