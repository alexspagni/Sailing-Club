package enumClass;

public enum FeeMemberEnum {
	GET_FEE_MEMBERSHIP(0), GET_FEE_STORAGE(3), PAY_FEE_MEMBERSHIP(1), PAY_FEE_STORAGE(2), PAY_FEE_RACE(5);

	private int typeReq;

	public int getTypeReq() {
		return typeReq;
	}

	private FeeMemberEnum(int typeReq) {
		this.typeReq=typeReq;
	}
}
