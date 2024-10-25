package enumClass;

public enum BoatStaffEnum {
	GET_BOATS(0), MODIFY_BOAT(1);

	private int typeReq;

	public int getTypeReq() {
		return typeReq;
	}

	private BoatStaffEnum(int typeReq) {
		this.typeReq = typeReq;
	}
}
