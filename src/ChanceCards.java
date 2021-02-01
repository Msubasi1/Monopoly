public class ChanceCards {
	private String chan_card;
	public static ChanceCards[] chancecards = new ChanceCards[6];
	public static int counter = 0,temp1=0;
	public static int[] chance_location = {8,23,37};
	
	public ChanceCards(String chan_card) {
		this.chan_card = chan_card;
	}
	
	public String getChan_card() {
		return chan_card;
	}
	public void setChan_card(String chan_card) {
		this.chan_card = chan_card;
	}
	public static void createChanceCards(String item) {
		ChanceCards x = new ChanceCards(item);
		ChanceCards.chancecards[counter] = x;
		counter++;
	}
	
	public static String give_chance_card(Players o,Players o2) {
		if (temp1==0) {
			o.go(o);
			Main.banker_money= Main.banker_money-200;
			String out =ChanceCards.chancecards[temp1].getChan_card();
			temp1++;
			return out; 
		}
		else if(temp1==1) {
			o.setLocation(27);
			if(Rentable.rentables[o.getLocation()].isFree()==true) {
				o.setLands(Rentable.rentables[o.getLocation()].getLand_name());
				o.setAllare(Rentable.rentables[o.getLocation()].getLand_name());
				Rentable.rentables[o.getLocation()].setFree(false);
				Rentable.rentables[o.getLocation()].setOwner_name(o.getPlayer_name());
				o.setMoney(-Rentable.rentables[o.getLocation()].getCost());
				Main.banker_money = Main.banker_money+Rentable.rentables[o.getLocation()].getCost();				String out  = (ChanceCards.chancecards[temp1].getChan_card()+"\t"+o.getPlayer_name()+" bought "+Rentable.rentables[o.getLocation()].getLand_name());
				temp1++;
				return out;
			}
			else {
				int rent = Rentable.rentables[o.getLocation()].setRent_amount(Rentable.rentables[o.getLocation()].getCost());
				if(rent>o.getMoney()) {
					String out = (o.getPlayer_name()+" is goes bankrupt");
					temp1++;
					return out;
				}
				else {
					o.setMoney(-rent);
					o2.setMoney(+rent);
					String out = (ChanceCards.chancecards[temp1].getChan_card()+"\t"+o.getPlayer_name()+" paid rent for "+Rentable.rentables[o.getLocation()].getLand_name());
					temp1++;
					return out;	
				}	
			}
		}
		else if(temp1==2) {
			o.setLocation(o.getLocation()-3);
			if(o.getLocation()==5) {
				String out = ChanceCards.chancecards[temp1].getChan_card()+"\t"+ o.getPlayer_name() +"has paid Tax";
				o.setMoney(-100);
				Main.banker_money = Main.banker_money+100;
				temp1++;
				return out;
			}
			else if(o.getLocation()==20) {
				if(Rentable.rentables[o.getLocation()].isFree()==true) {
					if(Rentable.rentables[o.getLocation()].getCost()>o.getMoney()) {
						String out = (o.getPlayer_name()+" is goes bankrupt");
						temp1++;
						return out;
					}
					else {
						Rentable.rentables[o.getLocation()].setFree(false);
						Rentable.rentables[o.getLocation()].setOwner_name(o.getPlayer_name());
						o.setMoney(-Rentable.rentables[o.getLocation()].getCost());
						Main.banker_money = Main.banker_money+Rentable.rentables[o.getLocation()].getCost();
						String out = ChanceCards.chancecards[temp1].getChan_card() +"\t"+ o.getPlayer_name()+" bought "+Rentable.rentables[o.getLocation()].getLand_name();
						o.setLands(Rentable.rentables[o.getLocation()].getLand_name());
						o.setAllare(Rentable.rentables[o.getLocation()].getLand_name());
						temp1++;
						return out;
				}
				
				}
				else {
					if(Rentable.rentables[o.getLocation()].getOwner_name().compareTo(o.getPlayer_name())==0) {
						String out = ChanceCards.chancecards[temp1].getChan_card()+"\t"+"Player 2 has "+ Rentable.rentables[o.getLocation()].getLand_name();
						temp1++;
						return out;
					}
					else {
						int rent = Rentable.rentables[o.getLocation()].setRent_amount(Rentable.rentables[o.getLocation()].getCost());
						if(rent>o.getMoney()) {
							String out = (o.getPlayer_name()+" is goes bankrupt");
							temp1++;
							return out;
						}
						else {
							o.setMoney(+rent);
							o2.setMoney(-rent);
							String out = ChanceCards.chancecards[temp1].getChan_card()+"\t"+"Player 2 paid rent for "+ Rentable.rentables[o.getLocation()].getLand_name();
							temp1++;
							return out;
						}
					}
				}
			}
			else {
				String out1 = CommunityChest.give_community_card(o,o2);
				if(out1.compareTo("Player 1 goes bankrupt")==0 ||out1.compareTo("Player 2 goes bankrupt")==0 ) {
					String out  = (o.getPlayer_name()+" is goes bankrupt");
					temp1++;
					return out;
				}
				else {
					String out = ChanceCards.chancecards[temp1].getChan_card()+ out1;
					temp1++;
					return out;
				}	
			}
		}	
		else if(temp1==3) {
			if(o.getMoney()<15) {
				String out = (o.getPlayer_name()+" goes bankrupt");
				temp1++;
				return out;
			}
			else {
				o.setMoney(-15);
				Main.banker_money= Main.banker_money+15;
				String out = ChanceCards.chancecards[temp1].getChan_card();
				temp1++;
				return out;
			}
		}
		else if(temp1==4) {
			o.setMoney(+150);
			Main.banker_money= Main.banker_money-150;
			String out = ChanceCards.chancecards[temp1].getChan_card();
			temp1++;
			return out;
		}
		else if(temp1==5) {
			o.setMoney(+100);
			Main.banker_money= Main.banker_money-100;
			String out = ChanceCards.chancecards[temp1].getChan_card();
			temp1 =0;
			return out;
		}
		else {
			return null;
		}
	}
	
}
