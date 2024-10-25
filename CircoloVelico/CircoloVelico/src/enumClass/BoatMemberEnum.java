package enumClass;

public enum BoatMemberEnum {
	ADD_BOAT(0), GET_BOAT(1), REMOVE_BOAT(2), GET_BOAT_RACE(3);

	private int typeReq;

	public int getTypeReq() {
		return typeReq;
	}

	private BoatMemberEnum(int typeReq) {
		this.typeReq=typeReq;
	}
}
