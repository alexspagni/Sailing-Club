package enumClass;

public enum FeeStaffEnum {
	GET_ALL_MEMBERSHIP_PAYMENT(0), GET_ALL_STORAGE_PAYMENT(1), GET_ALL_RACE_PAYMENT(2),
	GET_MEMBERS_MUST_PAY_MEMBERSHIP_FEE(0), GET_MEMBERS_MUST_PAY_STORAGE_FEE(3), ADD_MEMBERS_MUST_PAY_MEMBERSHIP_FEE(1),
	ADD_MEMBERS_MUST_PAY_STORAGE_FEE(2);

	private int typeReq;

	public int getTypeReq() {
		return typeReq;
	}

	private FeeStaffEnum(int typeReq) {
		this.typeReq = typeReq;
	}
}
