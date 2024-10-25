package enumClass;

public enum RaceMemberEnum {
	GET_RACE(0), GET_ALL_RACE(1), GET_RACE_WON(3), JOIN_A_RACE(2);

	private int typeReq;

	public int getTypeReq() {
		return typeReq;
	}

	private RaceMemberEnum(int typeReq) {
		this.typeReq=typeReq;
	}
}
