package com.hndfsj.springboot.framework.utils;

import java.util.Locale;

public class PinyinUtils {

//	public static void main(String[] args) {
//		System.err.println(Arrays.toString(stringToPinyin("郑 惺 荐")));
//	}
//
//	private static String pinyinToHanzi(String pinyin) {
//		try {
//			Document doc = addHeader(Jsoup.connect("https://www.sogou.com/sogou?query="+pinyin+"&ie=utf8&_ast=1466754304&_asf=null&w=01029901&pid=sogou-clse-3eae62bba9ddf64f&duppid=1&cid=&cid="))
//							.timeout(1000*10).get();
//			return doc.select("div[id=common_qc_container]  em").text();
//		} catch (IOException e) {
//			System.err.println(e.getMessage());
//		}
//		return pinyin;
//	}
//
//	public static Connection addHeader(Connection connection) {
//		connection.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
//		connection.header("Accept-Encoding	", "gzip, deflate, br");
//		connection.header("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
//		connection.header("Connection", "keep-alive");
//		connection.header("Cache-Control", "max-age=0");
//		connection.header("Cookie",
//				"ABTEST=0|1466753107|v17; SNUID=A016D710CBCFFDCADB3D761CCB192B6D; IPLOC=CN4101; SUID=6ADC1CDA2E08990A00000000576CE053"
//				+"; ld=Akllllllll2gbnxFlllllVN7iSwlllllLP8tzkllllZlllllpllll5@@@@@@@@@@; SUV=1466753104023446; browerV"
//				+"=2; osV=1; sct=3; ad=myllllllll2gbnxzlllllVN7cdUlllllLP8tzkllllwlllllpVxlw@@@@@@@@@@@; sst0=899; taspeed"
//				+"=taspeedexist; pgv_pvi=9050597376; pgv_si=s1443671040");
//		connection.header("Referer",
//				"https://www.sogou.com/sogou?query=ZhongGuoWuBaiLuoHanTuDian&ie=utf8&_ast=1466753152&_asf=null&w=01029901"
//				+"&pid=sogou-clse-3eae62bba9ddf64f&duppid=1&cid=&cid=");
//		connection.header("User-Agent",
//				"Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:47.0) Gecko/20100101 Firefox/47.0");
//		connection.header("X-Requested-With", "XMLHttpRequest");
//		return connection;
//	}
//	
//	/**
//	 * 
//	 * 将字符串转换成拼音数组
//	 * 
//	 * 
//	 * @param src
//	 * 
//	 * @return
//	 */
//
//	public static String[] stringToPinyin(String src) {
//
//		return stringToPinyin(src, false, null);
//
//	}
//
//	/**
//	 * 
//	 * 
//	 * 将字符串转换成拼音数组
//	 * 
//	 * 
//	 * 
//	 * @param src
//	 * @return
//	 * 
//	 */
//
//	public static String[] stringToPinyin(String src, String separator) {
//		return stringToPinyin(src, true, separator);
//	}
//
//	/**
//	 * 将字符串转换成拼音数组
//	 * 
//	 * @param sr
//	 * @param isPolyphone
//	 *            是否查出多音字的所有拼音
//	 * 
//	 * @param separator
//	 * 
//	 *            多音字拼音之间的分隔符
//	 * 
//	 * @return
//	 * 
//	 */
//
//	public static String[] stringToPinyin(String src, boolean isPolyphone, String separator) {
//
//		// 判断字符串是否为空
//
//		if ("".equals(src) || null == src) {
//			return null;
//
//		}
//		char[] srcChar = src.toCharArray();
//
//		int srcCount = srcChar.length;
//
//		String[] srcStr = new String[srcCount];
//
//		for (int i = 0; i < srcCount; i++) {
//
//			srcStr[i] = charToPinyin(srcChar[i], isPolyphone, separator);
//
//		}
//
//		return srcStr;
//
//	}
//
//	/**
//	 * 
//	 * 将单个字符转换成拼音
//	 * 
//	 * 
//	 * 
//	 * @param src
//	 * 
//	 * @return
//	 * 
//	 */
//
//	public static String charToPinyin(char src, boolean isPolyphone,
//
//			String separator) {
//
//		// 创建汉语拼音处理类
//
//		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
//
//		// 输出设置，大小写，音标方式
//
//		defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
//
//		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
//
//		StringBuffer tempPinying = new StringBuffer();
//
//		// 如果是中文
//
//		if (src > 128) {
//
//			try {
//
//				// 转换得出结果
//
//				String[] strs = PinyinHelper.toHanyuPinyinStringArray(src,
//
//						defaultFormat);
//
//				// 是否查出多音字，默认是查出多音字的第一个字符
//
//				if (isPolyphone && null != separator) {
//
//					for (int i = 0; i < strs.length; i++) {
//						tempPinying.append(strs[i]);
//
//						if (strs.length != (i + 1)) {
//
//							// 多音字之间用特殊符号间隔起来
//
//							tempPinying.append(separator);
//
//						}
//
//					}
//
//				} else {
//
//					tempPinying.append(strs[0]);
//
//				}
//
//			} catch (BadHanyuPinyinOutputFormatCombination e) {
//
//				e.printStackTrace();
//
//			}
//
//		} else {
//
//			tempPinying.append(src);
//
//		}
//
//		return tempPinying.toString();
//
//	}
//
//	public static String hanziToPinyin(String hanzi) {
//
//		return hanziToPinyin(hanzi, " ");
//
//	}
//
//	/**
//	 * 
//	 * 
//	 * 将汉字转换成拼音
//	 * 
//	 * @param hanzi
//	 * 
//	 * @param separator
//	 * 
//	 * @return
//	 * 
//	 * 
//	 */
//
//	@SuppressWarnings("deprecation")
//
//	public static String hanziToPinyin(String hanzi, String separator) {
//
//		// 创建汉语拼音处理类
//
//		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
//		// 输出设置，大小写，音标方式
//
//		defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
//
//		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
//		String pinyingStr = "";
//
//		try {
//			pinyingStr = PinyinHelper.toHanyuPinyinString(hanzi, defaultFormat, separator);
//
//		} catch (BadHanyuPinyinOutputFormatCombination e) {
//
//			e.printStackTrace();
//
//		}
//
//		return pinyingStr;
//	}
//
//	/**
//	 * 将字符串数组转换成字符串
//	 * 
//	 * @param str
//	 * 
//	 * @param separator
//	 *            各个字符串之间的分隔符
//	 * 
//	 * @return
//	 * 
//	 */
//
//	public static String stringArrayToString(String[] str, String separator) {
//		StringBuffer sb = new StringBuffer();
//
//		for (int i = 0; i < str.length; i++) {
//
//			sb.append(str[i]);
//
//			if (str.length != (i + 1)) {
//
//				sb.append(separator);
//
//			}
//
//		}
//
//		return sb.toString();
//
//	}
//
//	/**
//	 * 简单的将各个字符数组之间连接起来
//	 * 
//	 * @param str
//	 * 
//	 * @return
//	 * 
//	 */
//
//	public static String stringArrayToString(String[] str) {
//
//		return stringArrayToString(str, "");
//
//	}
//
//	/**
//	 * 
//	 * 
//	 * 将字符数组转换成字符串
//	 * 
//	 * 
//	 * @param str
//	 * 
//	 * @param separator
//	 *            各个字符串之间的分隔符
//	 * 
//	 * 
//	 * @return
//	 * 
//	 */
//
//	public static String charArrayToString(char[] ch, String separator) {
//
//		StringBuffer sb = new StringBuffer();
//
//		for (int i = 0; i < ch.length; i++) {
//
//			sb.append(ch[i]);
//
//			if (ch.length != (i + 1)) {
//
//				sb.append(separator);
//
//			}
//
//		}
//
//		return sb.toString();
//
//	}
//
//	/**
//	 * 
//	 * 
//	 * 将字符数组转换成字符串
//	 * 
//	 * @param str
//	 * 
//	 * @return
//	 * 
//	 */
//
//	public static String charArrayToString(char[] ch) {
//		return charArrayToString(ch, " ");
//
//	}
//
//	/**
//	 * 取汉字的首字母
//	 * 
//	 * @param src
//	 * @param isCapital
//	 *            是否是大写
//	 * 
//	 * @return
//	 * 
//	 */
//
//	public static char[] getHeadByChar(char src, boolean isCapital) {
//
//		// 如果不是汉字直接返回
//		if (src <= 128) {
//			return new char[] { src };
//
//		}
//
//		// 获取所有的拼音
//
//		String[] pinyingStr = PinyinHelper.toHanyuPinyinStringArray(src);
//		// 创建返回对象
//		int polyphoneSize = pinyingStr.length;
//
//		char[] headChars = new char[polyphoneSize];
//
//		int i = 0;
//
//		// 截取首字符
//
//		for (String s : pinyingStr) {
//
//			char headChar = s.charAt(0);
//
//			// 首字母是否大写，默认是小写
//			if (isCapital) {
//
//				headChars[i] = Character.toUpperCase(headChar);
//
//			} else {
//
//				headChars[i] = headChar;
//
//			}
//
//			i++;
//
//		}
//
//		return headChars;
//
//	}
//
//	/**
//	 * 
//	 * 取汉字的首字母(默认是大写)
//	 * 
//	 * @param src
//	 * 
//	 * @return
//	 * 
//	 */
//
//	public static char[] getHeadByChar(char src) {
//
//		return getHeadByChar(src, true);
//
//	}
//
//	/**
//	 * 查找字符串首字母
//	 * 
//	 * @param src
//	 * 
//	 * @return
//	 * 
//	 */
//
//	public static String[] getHeadByString(String src) {
//
//		return getHeadByString(src, true);
//
//	}
//
//	/**
//	 * 
//	 * 查找字符串首字母
//	 * 
//	 * @param src
//	 * 
//	 * @param isCapital
//	 *            是否大写
//	 * 
//	 * @return
//	 * 
//	 * 
//	 */
//
//	public static String[] getHeadByString(String src, boolean isCapital) {
//
//		return getHeadByString(src, isCapital, null);
//
//	}
//
//	/**
//	 * 
//	 * 查找字符串首字母
//	 * 
//	 * @param src
//	 * 
//	 * @param isCapital
//	 *            是否大写
//	 * 
//	 * @param separator
//	 *            分隔符
//	 * 
//	 * @return
//	 * 
//	 */
//
//	public static String[] getHeadByString(String src, boolean isCapital, String separator) {
//
//		char[] chars = src.toCharArray();
//
//		String[] headString = new String[chars.length];
//
//		int i = 0;
//		for (char ch : chars) {
//			char[] chs = getHeadByChar(ch, isCapital);
//			StringBuffer sb = new StringBuffer();
//			if (null != separator) {
//
//				int j = 1;
//				for (char ch1 : chs) {
//
//					sb.append(ch1);
//
//					if (j != chs.length) {
//
//						sb.append(separator);
//
//					}
//
//					j++;
//
//				}
//
//			} else {
//
//				sb.append(chs[0]);
//
//			}
//
//			headString[i] = sb.toString();
//
//			i++;
//
//		}
//
//		return headString;
//
//	}
	// 按照声母表示，这个表是在GB2312中的出现的第一个汉字，也就是说“啊”是代表首字母a的第一个汉字。
	// i, u, v都不做声母, 自定规则跟随前面的字母
	private static int BEGIN = 45217; 
	private static int END = 63486;
	private static char[] chartable = { '啊', '芭', '擦', '搭', '蛾', '发', '噶', '哈',
	        '哈', '击', '喀', '垃', '妈', '拿', '哦', '啪', '期', '然', '撒', '塌', '塌',
	        '塌', '挖', '昔', '压', '匝', };

	// 二十六个字母区间对应二十七个端点
	// GB2312码汉字区间十进制表示
	private static int[] table = new int[27];

	// 对应首字母区间表
	private static char[] initialtable = { 'a', 'b', 'c', 'd', 'e', 'f', 'g',
	        'h', 'h', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
	        't', 't', 'w', 'x', 'y', 'z', };

	// 初始化
	static {
	    for (int i = 0; i < 26; i++) {
	        table[i] = gbValue(chartable[i]);// 得到GB2312码的首字母区间端点表，十进制。
	    }
	    table[26] = END;// 区间表结尾
	}

	// ------------------------public方法区------------------------
	/**
	 * 根据一个包含汉字的字符串返回一个汉字拼音首字母的字符串 最重要的一个方法，思路如下：一个个字符读入、判断、输出
	 */
	public static String cn2py(String SourceStr) {
	    String Result = "";
	    int StrLength = SourceStr.length();
	    int i;
	    try {
	        for (i = 0; i < StrLength; i++) {
	            Result += Char2Initial(SourceStr.charAt(i));
	        }
	    } catch (Exception e) {
	        Result = "";
	    }
	    return Result;
	}
	
	
	public static String cnAllpy(String SourceStr) {
	    String Result = "";
	    int StrLength = SourceStr.length();
	    int i;
	    try {
	        for (i = 0; i < StrLength; i++) {
	            Result += Char2Initial(SourceStr.charAt(i));
	        }
	    } catch (Exception e) {
	        Result = "";
	    }
	    return Result;
	}

	// ------------------------private方法区------------------------
	/**
	 * 输入字符,得到他的声母,英文字母返回对应的大写字母,其他非简体汉字返回 '0'
	 * 
	 */
	private static char Char2Initial(char ch) {
	    // 对英文字母的处理：小写字母转换为大写，大写的直接返回
	    if (ch >= 'a' && ch <= 'z') {
            return ch;
        }
	    if (ch >= 'A' && ch <= 'Z') {
            return (char) (ch + 'a' - 'A');
        }

	    // 对非英文字母的处理：转化为首字母，然后判断是否在码表范围内，
	    // 若不是，则直接返回。
	    // 若是，则在码表内的进行判断。
	    int gb = gbValue(ch);// 汉字转换首字母

	    if ((gb < BEGIN) || (gb > END))// 在码表区间之前，直接返回
        {
            return ch;
        }

	    int i;
	    for (i = 0; i < 26; i++) {// 判断匹配码表区间，匹配到就break,判断区间形如“[,)”
	            if ((gb >= table[i]) && (gb < table[i+1])) {
                    break;
                }
	    }

	    if (gb==END) {//补上GB2312区间最右端
	        i=25;
	    }
	    return initialtable[i]; // 在码表区间中，返回首字母
	}

	/**
	 * 取出汉字的编码 cn 汉字
	 */
	private static int gbValue(char ch) {// 将一个汉字（GB2312）转换为十进制表示。
	    String str = new String();
	    str += ch;
	    try {
	        byte[] bytes = str.getBytes("GB2312");
	        if (bytes.length < 2) {
                return 0;
            }
	        return (bytes[0] << 8 & 0xff00) + (bytes[1] & 0xff);
	    } catch (Exception e) {
	        return 0;
	    }
	}
	
	
	/*----------------------------------------------------------------------*/
	private static int[] pyvalue = new int[] { -20319, -20317, -20304, -20295,
            -20292, -20283, -20265, -20257, -20242, -20230, -20051, -20036,
            -20032, -20026, -20002, -19990, -19986, -19982, -19976, -19805,
            -19784, -19775, -19774, -19763, -19756, -19751, -19746, -19741,
            -19739, -19728, -19725, -19715, -19540, -19531, -19525, -19515,
            -19500, -19484, -19479, -19467, -19289, -19288, -19281, -19275,
            -19270, -19263, -19261, -19249, -19243, -19242, -19238, -19235,
            -19227, -19224, -19218, -19212, -19038, -19023, -19018, -19006,
            -19003, -18996, -18977, -18961, -18952, -18783, -18774, -18773,
            -18763, -18756, -18741, -18735, -18731, -18722, -18710, -18697,
            -18696, -18526, -18518, -18501, -18490, -18478, -18463, -18448,
            -18447, -18446, -18239, -18237, -18231, -18220, -18211, -18201,
            -18184, -18183, -18181, -18012, -17997, -17988, -17970, -17964,
            -17961, -17950, -17947, -17931, -17928, -17922, -17759, -17752,
            -17733, -17730, -17721, -17703, -17701, -17697, -17692, -17683,
            -17676, -17496, -17487, -17482, -17468, -17454, -17433, -17427,
            -17417, -17202, -17185, -16983, -16970, -16942, -16915, -16733,
            -16708, -16706, -16689, -16664, -16657, -16647, -16474, -16470,
            -16465, -16459, -16452, -16448, -16433, -16429, -16427, -16423,
            -16419, -16412, -16407, -16403, -16401, -16393, -16220, -16216,
            -16212, -16205, -16202, -16187, -16180, -16171, -16169, -16158,
            -16155, -15959, -15958, -15944, -15933, -15920, -15915, -15903,
            -15889, -15878, -15707, -15701, -15681, -15667, -15661, -15659,
            -15652, -15640, -15631, -15625, -15454, -15448, -15436, -15435,
            -15419, -15416, -15408, -15394, -15385, -15377, -15375, -15369,
            -15363, -15362, -15183, -15180, -15165, -15158, -15153, -15150,
            -15149, -15144, -15143, -15141, -15140, -15139, -15128, -15121,
            -15119, -15117, -15110, -15109, -14941, -14937, -14933, -14930,
            -14929, -14928, -14926, -14922, -14921, -14914, -14908, -14902,
            -14894, -14889, -14882, -14873, -14871, -14857, -14678, -14674,
            -14670, -14668, -14663, -14654, -14645, -14630, -14594, -14429,
            -14407, -14399, -14384, -14379, -14368, -14355, -14353, -14345,
            -14170, -14159, -14151, -14149, -14145, -14140, -14137, -14135,
            -14125, -14123, -14122, -14112, -14109, -14099, -14097, -14094,
            -14092, -14090, -14087, -14083, -13917, -13914, -13910, -13907,
            -13906, -13905, -13896, -13894, -13878, -13870, -13859, -13847,
            -13831, -13658, -13611, -13601, -13406, -13404, -13400, -13398,
            -13395, -13391, -13387, -13383, -13367, -13359, -13356, -13343,
            -13340, -13329, -13326, -13318, -13147, -13138, -13120, -13107,
            -13096, -13095, -13091, -13076, -13068, -13063, -13060, -12888,
            -12875, -12871, -12860, -12858, -12852, -12849, -12838, -12831,
            -12829, -12812, -12802, -12607, -12597, -12594, -12585, -12556,
            -12359, -12346, -12320, -12300, -12120, -12099, -12089, -12074,
            -12067, -12058, -12039, -11867, -11861, -11847, -11831, -11798,
            -11781, -11604, -11589, -11536, -11358, -11340, -11339, -11324,
            -11303, -11097, -11077, -11067, -11055, -11052, -11045, -11041,
            -11038, -11024, -11020, -11019, -11018, -11014, -10838, -10832,
            -10815, -10800, -10790, -10780, -10764, -10587, -10544, -10533,
            -10519, -10331, -10329, -10328, -10322, -10315, -10309, -10307,
            -10296, -10281, -10274, -10270, -10262, -10260, -10256, -10254 };
    public static String[] pystr = new String[] { "a", "ai", "an", "ang", "ao",
            "ba", "bai", "ban", "bang", "bao", "bei", "ben", "beng", "bi",
            "bian", "biao", "bie", "bin", "bing", "bo", "bu", "ca", "cai",
            "can", "cang", "cao", "ce", "ceng", "cha", "chai", "chan", "chang",
            "chao", "che", "chen", "cheng", "chi", "chong", "chou", "chu",
            "chuai", "chuan", "chuang", "chui", "chun", "chuo", "ci", "cong",
            "cou", "cu", "cuan", "cui", "cun", "cuo", "da", "dai", "dan",
            "dang", "dao", "de", "deng", "di", "dian", "diao", "die", "ding",
            "diu", "dong", "dou", "du", "duan", "dui", "dun", "duo", "e", "en",
            "er", "fa", "fan", "fang", "fei", "fen", "feng", "fo", "fou", "fu",
            "ga", "gai", "gan", "gang", "gao", "ge", "gei", "gen", "geng",
            "gong", "gou", "gu", "gua", "guai", "guan", "guang", "gui", "gun",
            "guo", "ha", "hai", "han", "hang", "hao", "he", "hei", "hen",
            "heng", "hong", "hou", "hu", "hua", "huai", "huan", "huang", "hui",
            "hun", "huo", "ji", "jia", "jian", "jiang", "jiao", "jie", "jin",
            "jing", "jiong", "jiu", "ju", "juan", "jue", "jun", "ka", "kai",
            "kan", "kang", "kao", "ke", "ken", "keng", "kong", "kou", "ku",
            "kua", "kuai", "kuan", "kuang", "kui", "kun", "kuo", "la", "lai",
            "lan", "lang", "lao", "le", "lei", "leng", "li", "lia", "lian",
            "liang", "liao", "lie", "lin", "ling", "liu", "long", "lou", "lu",
            "lv", "luan", "lue", "lun", "luo", "ma", "mai", "man", "mang",
            "mao", "me", "mei", "men", "meng", "mi", "mian", "miao", "mie",
            "min", "ming", "miu", "mo", "mou", "mu", "na", "nai", "nan",
            "nang", "nao", "ne", "nei", "nen", "neng", "ni", "nian", "niang",
            "niao", "nie", "nin", "ning", "niu", "nong", "nu", "nv", "nuan",
            "nue", "nuo", "o", "ou", "pa", "pai", "pan", "pang", "pao", "pei",
            "pen", "peng", "pi", "pian", "piao", "pie", "pin", "ping", "po",
            "pu", "qi", "qia", "qian", "qiang", "qiao", "qie", "qin", "qing",
            "qiong", "qiu", "qu", "quan", "que", "qun", "ran", "rang", "rao",
            "re", "ren", "reng", "ri", "rong", "rou", "ru", "ruan", "rui",
            "run", "ruo", "sa", "sai", "san", "sang", "sao", "se", "sen",
            "seng", "sha", "shai", "shan", "shang", "shao", "she", "shen",
            "sheng", "shi", "shou", "shu", "shua", "shuai", "shuan", "shuang",
            "shui", "shun", "shuo", "si", "song", "sou", "su", "suan", "sui",
            "sun", "suo", "ta", "tai", "tan", "tang", "tao", "te", "teng",
            "ti", "tian", "tiao", "tie", "ting", "tong", "tou", "tu", "tuan",
            "tui", "tun", "tuo", "wa", "wai", "wan", "wang", "wei", "wen",
            "weng", "wo", "wu", "xi", "xia", "xian", "xiang", "xiao", "xie",
            "xin", "xing", "xiong", "xiu", "xu", "xuan", "xue", "xun", "ya",
            "yan", "yang", "yao", "ye", "yi", "yin", "ying", "yo", "yong",
            "you", "yu", "yuan", "yue", "yun", "za", "zai", "zan", "zang",
            "zao", "ze", "zei", "zen", "zeng", "zha", "zhai", "zhan", "zhang",
            "zhao", "zhe", "zhen", "zheng", "zhi", "zhong", "zhou", "zhu",
            "zhua", "zhuai", "zhuan", "zhuang", "zhui", "zhun", "zhuo", "zi",
            "zong", "zou", "zu", "zuan", "zui", "zun", "zuo" };

    private static StringBuilder buffer;
    private static String resource;
    public String getResource() {

        return resource;
    }

    public void setResource(String resource) {

        PinyinUtils.resource = resource;
    }

    /**
     * 汉字转成ASCII码
     * @param chs 汉字字符串
     * @return 
     */
    private static int getChsAscii(String chs) {

        int asc = 0;
        try {
            byte[] bytes = chs.getBytes("gb2312");
            if (bytes == null || bytes.length > 2 || bytes.length <= 0) {
                throw new RuntimeException("illegal resource string");
            }
            if (bytes.length == 1) {
                asc = bytes[0];
            }
            if (bytes.length == 2) {
                int hightByte = 256 + bytes[0];
                int lowByte = 256 + bytes[1];
                asc = (256 * hightByte + lowByte) - 256 * 256;
            }
        }
        catch (Exception e) {
            System.out
                    .println("ERROR:ChineseSpelling.class-getChsAscii(String chs)"
                            + e);
        }
        return asc;
    }

    /**
     * 单字解析 
     * @param str
     * @return
     */
    public static String convert(String str) {

        String result = null;
        int ascii = getChsAscii(str);
        if (ascii > 0 && ascii < 160) {
            result = String.valueOf((char) ascii);
        }
        else {
            for (int i = (pyvalue.length - 1); i >= 0; i--) {
                if (pyvalue[i] <= ascii) {
                    result = pystr[i];
                    break;
                }
            }
        }
        return result;
    }

    /**
     * 获取汉字字符串的拼音
     * @param chs
     * @return
     */
    public static String getSpelling(String chs) {

        chs = chs.toLowerCase(Locale.getDefault());
        String key, value;
        buffer = new StringBuilder();
        for (int i = 0; i < chs.length(); i++) {
            key = chs.substring(i, i + 1);
            value = getSpellingByWord(key);
            buffer.append(value);
        }
        return buffer.toString();
    }

    public String getSpelling() {

        return getSpelling(this.getResource());
    }

    /**
     * 获取汉字字符串的首字母
     * @param chs
     * @return
     */
    public static String getSpellingHeadChar(String chs) {

        chs = chs.toLowerCase(Locale.getDefault());
        String key, value;
        buffer = new StringBuilder();
        for (int i = 0; i < chs.length(); i++) {
            key = chs.substring(i, i + 1);
            value = getSpellingByWord(key);
            buffer.append(value.charAt(0));
        }
        return buffer.toString();
    }

    /**
     * 获取一个汉字的拼音
     * @param word
     * @return
     */
    private static String getSpellingByWord(String word) {

        String value;
        if (word.getBytes().length >= 2) {
            value = (String) convert(word);
            if (value == null) {
                value = "unknown";
            }
        }
        else {
            value = word;
        }
        return value;
    }

}
