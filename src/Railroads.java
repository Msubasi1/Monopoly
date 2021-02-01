public class Railroads extends Rentable{

	public Railroads(String land_name, int cost, boolean is_free, String owner_name, int rent_amount,int location) {
		super(land_name,cost,is_free,owner_name,rent_amount,location);
	}
	public static void create_railroad(String name,int cost, int location ) {
		Rentable x = new Railroads(name,cost,true,null,0,location);
		Rentable.rentables[location] = x;	
	}
}
