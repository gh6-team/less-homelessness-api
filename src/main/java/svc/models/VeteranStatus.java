package svc.models;

public enum VeteranStatus {
	NO,
	YES,
	DOES_NOT_KNOW,
	REFUSED,
	NO_DATA,
	OTHER;
	
	public static VeteranStatus fromColumn(int status)
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
	
	public int toColumnValue()
	{
		switch(this)
		{
		case NO: return 0;
		case YES: return 1;
		case DOES_NOT_KNOW: return 8;
		case REFUSED: return 9;
		case NO_DATA:
			default:
				return 99;
		}
	}

}
