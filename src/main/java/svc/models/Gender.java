package svc.models;

public enum Gender {
	FEMALE,
	MALE,
	TRANSGENDER_FEMALE,
	TRANSGENDER_MALE,
	NO_IDENTITY,
	DOES_NOT_KNOW,
	REFUSED,
	NO_DATA,
	OTHER;
	
	public static Gender fromColumns(int gender, boolean otherGender)
	{
		if(otherGender)
		{
			return OTHER;
		}
		
		switch(gender)
		{
		case 0: return FEMALE;
		case 1: return MALE;
		case 2: return TRANSGENDER_FEMALE;
		case 3: return TRANSGENDER_MALE;
		case 4: return NO_IDENTITY;
		case 8: return DOES_NOT_KNOW;
		case 9: return REFUSED;
		case 99:
		default:
			return NO_DATA;
		}
	}
	
	public int getOtherGender()
	{
		return this == OTHER ? 1 : 0;
	}
	
	public int getGender()
	{
		switch(this)
		{
		case FEMALE: return 0;
		case MALE: return 1;
		case TRANSGENDER_FEMALE: return 2;
		case TRANSGENDER_MALE: return 3;
		case NO_IDENTITY: return 4;
		case DOES_NOT_KNOW: return 8;
		case REFUSED: return 9;
		case NO_DATA:
			default:
			return 99;
		}
	}
}
