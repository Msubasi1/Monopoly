public class Companies extends Rentable{

	public Companies(String land_name, int cost, boolean is_free, String owner_name, int rent_amount,int location) {
		super(land_name,cost,is_free,owner_name,rent_amount,location);
	}
	
	public static void create_companies(String name,int cost, int location ) {
		Rentable x = new Companies(name,cost,true,null,0,location);
		Rentable.rentables[location] = x;
	}
	
	public int setRent_amount(int dice) {
		return 4*dice;
	}
}
