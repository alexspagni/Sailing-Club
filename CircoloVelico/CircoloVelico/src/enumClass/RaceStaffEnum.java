package enumClass;

public enum RaceStaffEnum {
	ADD_RACE(0), GET_RACE(1), MODIFY_RACE(2), GET_RACE_TO_SIMULATE(3), SIMULATE_RACE(4), GET_ALL_RACE_AND_WINNERS(5);

	private int typeReq;

	public int getTypeReq() {
		return typeReq;
	}

	private RaceStaffEnum(int typeReq) {
		this.typeReq = typeReq;
	}
}
