public class Lands extends Rentable{
	
	public Lands(String land_name, int cost, boolean is_free, String owner_name, int rent_amount,int location) {
		super(land_name,cost,is_free,owner_name,rent_amount,location);
		
	}
	
	public int setRent_amount(int cost) {
		if(cost>3000) {
			return (cost*35)/100;
		}
		else if (cost>2000) {
			return (cost*30)/100;
		}
		else {
			return (cost*40)/100;
		}
	}
	public static void create_land(String name,int cost, int location ) {
		Rentable x = new Lands(name,cost,true,null,0,location);
		Rentable.rentables[location] = x;
		
	}

}
