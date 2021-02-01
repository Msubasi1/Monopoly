public class CommunityChest {
	private String commu_card;
	public static CommunityChest[] communitycards = new CommunityChest[12];
	public static int counter = 0,temp=0;
	public static int[] community_location = {3,18,34};
	
	public CommunityChest(String commu_card) {
		this.commu_card = commu_card;
	}

	public String getCommu_card() {
		return commu_card;
	}
	public void setCommu_card(String commu_card) {
		this.commu_card = commu_card;
	}
	public static void createCommunityCards(String card) {
		CommunityChest x = new CommunityChest(card);
		CommunityChest.communitycards[counter] = x;
		counter++;
	}
	public static String give_community_card(Players o,Players o2) {
		if (temp==0) {
			o.go(o);
			String out =CommunityChest.communitycards[temp].getCommu_card();
			Main.banker_money= Main.banker_money-200;
			temp++;
			return out; 
		}
		else if(temp==1) {
			o.setMoney(+75);
			Main.banker_money= Main.banker_money-75;
			String out = CommunityChest.communitycards[temp].getCommu_card();
			temp++;
			return out;
		}
		else if(temp==2) {
			if(o.getMoney()<50) {
				String out = (o.getPlayer_name()+ " goes bankrupt");
				temp++;
				return out;
			}
			else {
				o.setMoney(-50);
				Main.banker_money= Main.banker_money+50;
				String out = CommunityChest.communitycards[temp].getCommu_card();
				temp++;
				return out;
			}
		}
		else if(temp==3) {
			if(o2.getMoney()<10) {
				String out  =(o2.getPlayer_name()+" goes bankrupt");
				temp++;
				return out;
			}
			else {
				o.setMoney(+10);
				o2.setMoney(-10);
				String out = CommunityChest.communitycards[temp].getCommu_card();
				temp++;
				return out;
			}
		}
		else if(temp==4) {
			if(o2.getMoney()<50) {
				String out  =(o2.getPlayer_name()+" goes bankrupt");
				temp++;
				return out;
			}
			else {
				o.setMoney(+50);
				o2.setMoney(-50);
				String out = CommunityChest.communitycards[temp].getCommu_card();
				temp++;
				return out;
			}
		}
		else if(temp==5) {
			o.setMoney(+20);
			Main.banker_money= Main.banker_money-20;
			String out = CommunityChest.communitycards[temp].getCommu_card();
			temp++;
			return out;
		}
		else if(temp==6) {
			o.setMoney(+100);
			Main.banker_money= Main.banker_money-100;
			String out = CommunityChest.communitycards[temp].getCommu_card();
			temp++;
			return out;
		}
		else if(temp==7) {
			o.setMoney(+100);
			Main.banker_money= Main.banker_money-100;
			String out = CommunityChest.communitycards[temp].getCommu_card();
			temp++;
			return out;
		}
		else if(temp==8) {
			if(o.getMoney()<50) {
				String out  =(o.getPlayer_name()+" goes bankrupt");
				temp++;
				return out;
			}
			else {
				o.setMoney(-50);
				Main.banker_money= Main.banker_money+50;
				String out = CommunityChest.communitycards[temp].getCommu_card();
				temp++;
				return out;
			}
		}
		else if(temp==9) {
			o.setMoney(+100);
			Main.banker_money= Main.banker_money-100;
			String out = CommunityChest.communitycards[temp].getCommu_card();
			temp++;
			return out;
		}
		else if(temp==10) {
			o.setMoney(+50);
			Main.banker_money= Main.banker_money-50;
			String out = CommunityChest.communitycards[temp].getCommu_card();
			temp = 0;
			return out;
		}
		else {
			return null;
		}
		
	}
}
