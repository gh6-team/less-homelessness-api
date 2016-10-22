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
}
