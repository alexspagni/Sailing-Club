package enumClass;

public enum NotificationMemberEnum {
	GET_NOTIFICATION(0), REMOVE_NOTIFICATION(1);

	private int typeReq;

	public int getTypeReq() {
		return typeReq;
	}

	private NotificationMemberEnum(int typeReq) {
		this.typeReq = typeReq;
	}
}
