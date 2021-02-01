public class Rentable {
	private String land_name;
	private int cost;
	private boolean free;
	private String owner_name;
	private int rent_amount;
	private int location;
	public static Rentable[] rentables = new Rentable[41];
	public Rentable(String land_name,int cost, boolean free,String owner_name,int rent_amount,int location) {
		this.land_name = land_name;
		this.cost = cost;
		this.free = free;
		this.owner_name = owner_name;
		this.rent_amount = rent_amount;
		this.location = location;
	}

	public String getLand_name() {
		return land_name;
	}

	public void setLand_name(String land_name) {
		this.land_name = land_name;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public boolean isFree() {
		return free;
	}

	public void setFree(boolean free) {
		this.free = free;
	}

	public String getOwner_name() {
		return owner_name;
	}

	public void setOwner_name(String owner_name) {
		this.owner_name = owner_name;
	}

	public int getRent_amount() {
		return rent_amount;
	}
	public int setRent_amount(int cost) {
		return rent_amount;
	}
}
