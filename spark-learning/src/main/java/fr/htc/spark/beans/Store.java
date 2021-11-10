package fr.htc.spark.beans;

public class Store {
	private int id;
	private String type;
	private int regionId;
	private String name;
	private int number;
	private String streetAddress;
	private String city;
	private String state;
	private int postalCode;
	private String country;
	private String manager;
	private String phone;
	private String fax;
	private String firstOpenedDate;
	private String lastRemodelDate;
	private int sqft;
	private int grocerySqft;
	private int frozenSqft;
	private int meatSqft;
	private boolean coffeeBar;
	private boolean videoStore;
	private boolean saladBar;
	private boolean preparedFood;
	private boolean florist;

	public Store(int id, String type, int regionId, String name, int number, String streetAddress, String city,
			String state, int postalCode, String country, String manager, String phone, String fax,
			String firstOpenedDate, String lastRemodelDate, int sqft, int grocerySqft, int frozenSqft, int meatSqft,
			boolean coffeeBar, boolean videoStore, boolean saladBar, boolean preparedFood, boolean florist) {
		this.id = id;
		this.type = type;
		this.regionId = regionId;
		this.name = name;
		this.number = number;
		this.streetAddress = streetAddress;
		this.city = city;
		this.state = state;
		this.postalCode = postalCode;
		this.country = country;
		this.manager = manager;
		this.phone = phone;
		this.fax = fax;
		this.firstOpenedDate = firstOpenedDate;
		this.lastRemodelDate = lastRemodelDate;
		this.sqft = sqft;
		this.grocerySqft = grocerySqft;
		this.frozenSqft = frozenSqft;
		this.meatSqft = meatSqft;
		this.coffeeBar = coffeeBar;
		this.videoStore = videoStore;
		this.saladBar = saladBar;
		this.preparedFood = preparedFood;
		this.florist = florist;
	}

	public static Store parse(String storeAsString, String sep) {
		String[] split = storeAsString.split(sep);
		return new Store(Integer.valueOf(split[0]), String.valueOf(split[1]), Integer.valueOf(split[2]),
				String.valueOf(split[3]), Integer.valueOf(split[4]), String.valueOf(split[5]), String.valueOf(split[6]),
				String.valueOf(split[7]), Integer.valueOf(split[8]), String.valueOf(split[9]),
				String.valueOf(split[10]), String.valueOf(split[11]), String.valueOf(split[12]),
				String.valueOf(split[13]), String.valueOf(split[14]), Integer.valueOf(split[15]),
				Integer.valueOf(split[16]), Integer.valueOf(split[17]), Integer.valueOf(split[18]),
				!split[19].equals("0"), !split[20].equals("0"), !split[21].equals("0"), !split[22].equals("0"),
				!split[23].equals("0"));

	}

	public static Store parse(String storeAsString) {
		return Store.parse(storeAsString, ";");
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getRegionId() {
		return regionId;
	}

	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getFirstOpenedDate() {
		return firstOpenedDate;
	}

	public void setFirstOpenedDate(String firstOpenedDate) {
		this.firstOpenedDate = firstOpenedDate;
	}

	public String getLastRemodelDate() {
		return lastRemodelDate;
	}

	public void setLastRemodelDate(String lastRemodelDate) {
		this.lastRemodelDate = lastRemodelDate;
	}

	public int getSqft() {
		return sqft;
	}

	public void setSqft(int sqft) {
		this.sqft = sqft;
	}

	public int getGrocerySqft() {
		return grocerySqft;
	}

	public void setGrocerySqft(int grocerySqft) {
		this.grocerySqft = grocerySqft;
	}

	public int getFrozenSqft() {
		return frozenSqft;
	}

	public void setFrozenSqft(int frozenSqft) {
		this.frozenSqft = frozenSqft;
	}

	public int getMeatSqft() {
		return meatSqft;
	}

	public void setMeatSqft(int meatSqft) {
		this.meatSqft = meatSqft;
	}

	public boolean isCoffeeBar() {
		return coffeeBar;
	}

	public void setCoffeeBar(boolean coffeeBar) {
		this.coffeeBar = coffeeBar;
	}

	public boolean isVideoStore() {
		return videoStore;
	}

	public void setVideoStore(boolean videoStore) {
		this.videoStore = videoStore;
	}

	public boolean isSaladBar() {
		return saladBar;
	}

	public void setSaladBar(boolean saladBar) {
		this.saladBar = saladBar;
	}

	public boolean isPreparedFood() {
		return preparedFood;
	}

	public void setPreparedFood(boolean preparedFood) {
		this.preparedFood = preparedFood;
	}

	public boolean isFlorist() {
		return florist;
	}

	public void setFlorist(boolean florist) {
		this.florist = florist;
	}

	
	
}