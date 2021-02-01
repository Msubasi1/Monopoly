import java.util.*;
public class Players {
	private String player_name;
	private int money;
	private boolean is_jail;
	private int number_jail;
	private int location;
	private ArrayList<String> lands;
	private ArrayList<String> railroads;
	private ArrayList<String> companies;
	private ArrayList<String> allarea;
	
	public Players(String player_name, int money, boolean is_jail, int number_jail, int location, ArrayList<String> lands, ArrayList railroads, ArrayList companies,ArrayList allarea) {
		this.player_name = player_name;
		this.money = money;
		this.is_jail= is_jail;
		this.number_jail = number_jail;
		this.location = location;
		this.lands  = new ArrayList<String>();
		this.railroads = new ArrayList<String>();
		this.companies = new ArrayList<String>();
		this.allarea = new ArrayList<String>();
	}

	public String getPlayer_name() {
		return player_name;
	}
	public void setPlayer_name(String player_name) {
		this.player_name = player_name;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = this.money+money;
	}
	public boolean isIs_jail() {
		return is_jail;
	}
	public void setIs_jail(boolean is_jail) {
		this.is_jail = is_jail;
	}
	public int getNumber_jail() {
		return number_jail;
	}
	public void setNumber_jail(Players o,int numberof_jail) {
		if(o.isIs_jail()==true) {
			this.number_jail++;
		}
		else {
			this.number_jail = numberof_jail;
		}
	}
	public int getLocation() {
		return location;
	}
	public void setLocation(int location) {
		this.location = location;
	}
	public ArrayList<String> getLands() {
		return this.lands;
	}
	public void setLands(String land_name) {
		getLands().add(land_name);
	}
	public ArrayList<String> getRailroads() {
		return railroads;
	}
	public void setRailroads(String railroad_name) {
		getRailroads().add(railroad_name);	
	}
	public ArrayList<String> getCompanies() {
		return companies;
	}
	public void setCompanies(String company_name) {
		getCompanies().add(company_name);
	}
	public ArrayList<String> getAllarea(){
		return allarea;
	}
	public void setAllare(String area_name) {
		getAllarea().add(area_name);
	}
	public void go(Players o) {
		setMoney(+200);
		o.setLocation(1);
	}
	
}
