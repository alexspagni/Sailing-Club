package enumClass;

public enum MembersEnum {
	SIGN_IN(1), SIGN_UP(0);

	private int typeReq;

	public int getTypeReq() {
		return typeReq;
	}

	private MembersEnum(int typeReq) {
		this.typeReq = typeReq;
	}
}
