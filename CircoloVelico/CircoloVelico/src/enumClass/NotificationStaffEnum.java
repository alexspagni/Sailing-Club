package enumClass;

public enum NotificationStaffEnum {
	GET_MEMBERS_TO_NOTIFY_MEMBERSHIP_FEE(4), GET_MEMBERS_TO_NOTIFY_STORAGE_FEE(5), SEND_MEMBERSHIP_NOTIFICATION(0),
	SEND_STORAGE_NOTIFICATION(1);

	private int typeReq;

	public int getTypeReq() {
		return typeReq;
	}

	private NotificationStaffEnum(int typeReq) {
		this.typeReq = typeReq;
	}
}
