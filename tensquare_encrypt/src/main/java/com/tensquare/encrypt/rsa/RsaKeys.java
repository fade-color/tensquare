package com.tensquare.encrypt.rsa;

/**
 * rsa加解密用的公钥和私钥
 * @author Administrator
 *
 */
public class RsaKeys {

	//生成秘钥对的方法可以参考这篇帖子
	//https://www.cnblogs.com/yucy/p/8962823.html

//	//服务器公钥
//	private static final String serverPubKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA6Dw9nwjBmDD/Ca1QnRGy"
//											 + "GjtLbF4CX2EGGS7iqwPToV2UUtTDDemq69P8E+WJ4n5W7Iln3pgK+32y19B4oT5q"
//											 + "iUwXbbEaAXPPZFmT5svPH6XxiQgsiaeZtwQjY61qDga6UH2mYGp0GbrP3i9TjPNt"
//											 + "IeSwUSaH2YZfwNgFWqj+y/0jjl8DUsN2tIFVSNpNTZNQ/VX4Dln0Z5DBPK1mSskd"
//											 + "N6uPUj9Ga/IKnwUIv+wL1VWjLNlUjcEHsUE+YE2FN03VnWDJ/VHs7UdHha4d/nUH"
//											 + "rZrJsKkauqnwJsYbijQU+a0HubwXB7BYMlKovikwNpdMS3+lBzjS5KIu6mRv1GoE"
//											 + "vQIDAQAB";
//
//	//服务器私钥(经过pkcs8格式处理)
//	private static final String serverPrvKeyPkcs8 = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDoPD2fCMGYMP8J"
//				 								 + "rVCdEbIaO0tsXgJfYQYZLuKrA9OhXZRS1MMN6arr0/wT5YniflbsiWfemAr7fbLX"
//				 								 + "0HihPmqJTBdtsRoBc89kWZPmy88fpfGJCCyJp5m3BCNjrWoOBrpQfaZganQZus/e"
//				 								 + "L1OM820h5LBRJofZhl/A2AVaqP7L/SOOXwNSw3a0gVVI2k1Nk1D9VfgOWfRnkME8"
//				 								 + "rWZKyR03q49SP0Zr8gqfBQi/7AvVVaMs2VSNwQexQT5gTYU3TdWdYMn9UeztR0eF"
//				 								 + "rh3+dQetmsmwqRq6qfAmxhuKNBT5rQe5vBcHsFgyUqi+KTA2l0xLf6UHONLkoi7q"
//				 								 + "ZG/UagS9AgMBAAECggEBANP72QvIBF8Vqld8+q7FLlu/cDN1BJlniReHsqQEFDOh"
//				 								 + "pfiN+ZZDix9FGz5WMiyqwlGbg1KuWqgBrzRMOTCGNt0oteIM3P4iZlblZZoww9nR"
//				 								 + "sc4xxeXJNQjYIC2mZ75x6bP7Xdl4ko3B9miLrqpksWNUypTopOysOc9f4FNHG326"
//				 								 + "0EMazVaXRCAIapTlcUpcwuRB1HT4N6iKL5Mzk3bzafLxfxbGCgTYiRQNeRyhXOnD"
//				 								 + "eJox64b5QkFjKn2G66B5RFZIQ+V+rOGsQElAMbW95jl0VoxUs6p5aNEe6jTgRzAT"
//				 								 + "kqM2v8As0GWi6yogQlsnR0WBn1ztggXTghQs2iDZ0YkCgYEA/LzC5Q8T15K2bM/N"
//				 								 + "K3ghIDBclB++Lw/xK1eONTXN+pBBqVQETtF3wxy6PiLV6PpJT/JIP27Q9VbtM9UF"
//				 								 + "3lepW6Z03VLqEVZo0fdVVyp8oHqv3I8Vo4JFPBDVxFiezygca/drtGMoce0wLWqu"
//				 								 + "bXlUmQlj+PTbXJMz4VTXuPl1cesCgYEA6zu5k1DsfPolcr3y7K9XpzkwBrT/L7LE"
//				 								 + "EiUGYIvgAkiIta2NDO/BIPdsq6OfkMdycAwkWFiGrJ7/VgU+hffIZwjZesr4HQuC"
//				 								 + "0APsqtUrk2yx+f33ZbrS39gcm/STDkVepeo1dsk2DMp7iCaxttYtMuqz3BNEwfRS"
//				 								 + "kIyKujP5kfcCgYEA1N2vUPm3/pNFLrR+26PcUp4o+2EY785/k7+0uMBOckFZ7GIl"
//				 								 + "FrV6J01k17zDaeyUHs+zZinRuTGzqzo6LSCsNdMnDtos5tleg6nLqRTRzuBGin/A"
//				 								 + "++xWn9aWFT+G0ne4KH9FqbLyd7IMJ9R4gR/1zseH+kFRGNGqmpi48MS61G0CgYBc"
//				 								 + "PBniwotH4cmHOSWkWohTAGBtcNDSghTRTIU4m//kxU4ddoRk+ylN5NZOYqTxXtLn"
//				 								 + "Tkt9/JAp5VoW/41peCOzCsxDkoxAzz+mkrNctKMWdjs+268Cy4Nd09475GU45khb"
//				 								 + "Y/88qV6xGz/evdVW7JniahbGByQhrMwm84R9yF1mNwKBgCIJZOFp9xV2997IY83S"
//				 								 + "habB/YSFbfZyojV+VFBRl4uc6OCpXdtSYzmsaRcMjN6Ikn7Mb9zgRHR8mPmtbVfj"
//				 								 + "B8W6V1H2KOPfn/LAM7Z0qw0MW4jimBhfhn4HY30AQ6GeImb2OqOuh3RBbeuuD+7m"
//				 								 + "LpFZC9zGggf9RK3PfqKeq30q";

	//服务器公钥
	private static final String serverPubKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAuucw9fCIocS79f8Ny5n7" +
			"/o2X7v9NvOK4rx8p3lJnTcGA9+uCLxnQAMQwDXwL5Ok2UjBoMci9EbLgjPv6vMX3" +
			"b/7+2cQRkbQSvYyuQ2pY1i/KdIGnoiHXR9KMfWsiRw/LjZ5TOaBVMw12ac2HCk4I" +
			"MgZZNvGotf7dpeb6sjuHPr81vmuGVEpCbpqllhXdbEerKXUGsH1nRRUXzvqjyPPl" +
			"A546cc58z/QPDv9rX6WtvXm8FaySIvrHe+hyTnU+YfQ5Xdf/dCv+KAuz+Nqmt8PG" +
			"0rhneOgFHTjKS6cwnB+RpNxAOXuEtOpbjlVeE8xQcJAhkkWTPvLPEj2GkERfw//z" +
			"SQIDAQAB";

	//服务器私钥(经过pkcs8格式处理)
	private static final String serverPrvKeyPkcs8 = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC65zD18IihxLv1" +
			"/w3Lmfv+jZfu/0284rivHyneUmdNwYD364IvGdAAxDANfAvk6TZSMGgxyL0RsuCM" +
			"+/q8xfdv/v7ZxBGRtBK9jK5DaljWL8p0gaeiIddH0ox9ayJHD8uNnlM5oFUzDXZp" +
			"zYcKTggyBlk28ai1/t2l5vqyO4c+vzW+a4ZUSkJumqWWFd1sR6spdQawfWdFFRfO" +
			"+qPI8+UDnjpxznzP9A8O/2tfpa29ebwVrJIi+sd76HJOdT5h9Dld1/90K/4oC7P4" +
			"2qa3w8bSuGd46AUdOMpLpzCcH5Gk3EA5e4S06luOVV4TzFBwkCGSRZM+8s8SPYaQ" +
			"RF/D//NJAgMBAAECggEAPYjqdL6ZrXEBVU+A8jyjW76wfA5wQLUAix+egETvIAmK" +
			"dZWDShCci/QLwhTKMbaanNq1I1bs8MvM5H39onMnMSdqLrY/9pGr7d5IbYew8evQ" +
			"IyWg1F+B6nKmsJA6O902bkznJ7zi8CkJiHITeNASIK44Zz+PBUSBIyL1/QJeV8i5" +
			"b8FWI5UGr2u9KzOe9+pD6voAwItYJ2K/6IGONvsugRtHZvZnm4rWnw4fKIKb+ifo" +
			"BZ7DfWLtjaZpL1HPpau6kF7EKqn3xtIGjaKDhKFYwYXh8DKHeF/bYg3wp6tFxpaz" +
			"N/LRrSByHrqxx++zeXYUT8Ze482/LrF+YgBSOpFwgQKBgQD0pgPMq0ZlQneyTIoB" +
			"HkG0FBEJqLQEtRPVIR4p5hjmqhxsm67jtT+yZfR7hYXXoFt/Q0C2DF5QySPbCXU9" +
			"URm4ZTJmY3JNPk6wd0/oeL+LPTBnNZ7iEEP1wgBqDAFHV6tiShtHXC67yDr+zyVa" +
			"PF6PFlqifqbDSJIJXbeEI+5uMQKBgQDDk0O901uD/l7Dmsjy424T+IBiIhC60FLN" +
			"NLITZPTpZL7LF8WcbU/I5rqu2dScGWTgakP7XH/6Td9uKYuvZZja1OtCgkFI+IYZ" +
			"tWCKE4jooRFc0RWgEV23FEi9vYxdGLVoSn6nG3aX9wXB6fRQNLiRqslF433OTQBw" +
			"F9vQNoOYmQKBgQCgHXcnIIF2DEZifw1jJfZCcU+lTrBi1FAikQY4zctGS9dGja2s" +
			"ghcqHugfX4f1U5IOfGmBvB6OKNY6qLn+Y3G96R1PPY61Vy4Q+EVoFH7oFifzpHhT" +
			"4+lWkp3KygicNJiKP44S6hX7+FQEKHZ91Dze0PaImvNxo7FQR/GvngSSQQKBgHVH" +
			"ZiP7DrIEY8dglogwod4P8szmKKoeKskrhG3k6IjdBRy80J+RXXrYAbLApaJGCBwb" +
			"HVEqkwQClq109HLltdfkPn/PpF6hyai4/39NX5hAnizOpO0r/MrAuy4s8HMTUhWM" +
			"No73BRLz6tvx87P1KYkQpUnntZV0z4JojEQ7rIZhAoGAWirBqBUvXpM4quM0iYq3" +
			"xvTWpB+6pM2eTLDzU2Qk6WWPW6ivZ63w9k9WVDvIxE5SPq3wR3d+GHUUyBJV46nx" +
			"Ivm0La5qS3zirXsc2f+HVtTbUnAAo3HwhR/UMITJ0cDT3AXS+0pmJm1itRvBWQ6d" +
			"1opUqrz1tLROn97lme4AB0E=";

	public static String getServerPubKey() {
		return serverPubKey;
	}

	public static String getServerPrvKeyPkcs8() {
		return serverPrvKeyPkcs8;
	}
	
}
