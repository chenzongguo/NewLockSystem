package cn.njthl.HotelClean.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ValidateUtils {
	private static String RegNumber = new String("^[0-9]+$");

	public static boolean IsNum(String inputData)
	{
		Pattern pattern = Pattern.compile(RegNumber);
		Matcher match = pattern.matcher(inputData);
		return match.matches();
	}

	public static boolean IsWorld(String syllable)
	{
		String regex = "^[A-Z]+$";
		return !RegexOperation(syllable, regex);
	}

	public static boolean IsNumber(String syllable)
	{
		String regex = "^[A-Za-z]+$";
		return RegexOperation(syllable, regex);
	}

	public static boolean IsChin(String syllable)
	{
		 char[] nickchar = syllable.toCharArray();
		 for (int i = 0; i < nickchar.length; i++) {
			 if (!isChinese(nickchar[i])) {
				 return false;
			 }
		 }
//		String regex = "[\u4e00-\u9fa5}";
		return true;
	}
	
	private static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }


	private static boolean RegexOperation(String syllable, String regex)
	{
		int options = Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.COMMENTS;
		Pattern pattern = Pattern.compile(regex, options);
		Matcher match = pattern.matcher(syllable);
		return match.matches();
	}


	public static boolean DateChecking(String _birth)
	{
		return BirthChecking(TimeUtils.DateTimeToString(_birth));
	}

	public static boolean BirthChecking(String _birth)
	{
		boolean result = false;
		if (_birth!=null&&!_birth.equals(""))
		{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
			try {
				sdf.parse(_birth);
				result = true;
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		return result;
	}



	public static boolean CheckIDCard(String Id)
	{
		boolean result;
		if (Id.length() == 18)
		{
			boolean flag = ValidateUtils.CheckIDCard18(Id);
			result = flag;
		}
		else if (Id.length() == 15)
		{
			boolean flag = ValidateUtils.CheckIDCard15(Id);
			result = flag;
		}
		else
		{
			result = false;
		}
		return result;
	}

	public static boolean CheckIDCard18(String Id)
	{
		long num = 0L;
		boolean result = false;

		try {
			num = Long.valueOf(Id.substring(0,17));
			if ((double)num < Math.pow(10.0, 16.0))
				result = false;
			else
				Long.valueOf(Id.replace('x', '0').replace('X', '0'));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			result = false;
		}


			StringBuilder sb = new StringBuilder(Id.substring(6, 14));
			sb.insert(6, "-");
			sb.insert(4, "-");
			String birth = sb.toString();
			if (!ValidateUtils.BirthChecking(birth))
			{
				result = false;
			}
			else
			{
				String[] array = "1,0,x,9,8,7,6,5,4,3,2".split(",");
				String[] array2 = "7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2".split(",");
				char[] array3 = Id.substring(0,17).toCharArray();
				int num2 = 0;
				for (int i = 0; i < 17; i++)
				{
					num2 += Integer.valueOf(array2[i]) * Integer.valueOf(String.valueOf(array3[i]));
				}
				int num3 = -1;
				num3 = num2 % 11;
				result = array[num3].equals(Id.substring(17).toLowerCase());
			}


		return result;
	}

	public static boolean CheckIDCard15(String Id)
	{
		long num = 0L;
		boolean result;

		num = Long.valueOf(Id);

		if (num < Math.pow(10.0, 14.0))
		{
			result = false;
		}
		else
		{
			StringBuilder sb = new StringBuilder(Id.substring(6, 6));
			sb.insert(4, "-");
			sb.insert(2, "-");
			String birth = sb.toString();
			result = ValidateUtils.BirthChecking(birth);
		}
		return result;
	}
}
