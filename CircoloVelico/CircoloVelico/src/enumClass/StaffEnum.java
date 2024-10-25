package enumClass;

public enum StaffEnum {
	SIGN_UP(2), GET_MEMBERS(0), MODIFY_MEMBERS_CREDENTIAL(1), REMOVE_MEMBER(2);

	private int typeReq;

	public int getTypeReq() {
		return typeReq;
	}

	private StaffEnum(int typeReq) {
		this.typeReq = typeReq;
	}
}
