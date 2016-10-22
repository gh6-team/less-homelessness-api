package svc.models;

public enum VeteranStatus {
	NO,
	YES,
	DOES_NOT_KNOW,
	REFUSED,
	NO_DATA,
	OTHER;
	
	static VeteranStatus fromColumn(int status)
	{	
		switch(status)
		{
		case 0: return NO;
		case 1: return YES;
		case 8: return DOES_NOT_KNOW;
		case 9: return REFUSED;
		case 99:
		default:
			return NO_DATA;
		}
	}

}
