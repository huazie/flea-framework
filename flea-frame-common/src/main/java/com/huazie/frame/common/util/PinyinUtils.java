package com.huazie.frame.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huazie.frame.common.PinyinEnum;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * <p>汉字转拼音工具类，用于获取汉字对应的简拼或全拼</p>
 *
 * <p>调用示例：</p>
 * <pre>
 *    PinyinUtils.getJianPin("中国", PinyinEnum.LOWER_CASE.getType());
 *    PinyinUtils.getJianPin("中国", PinyinEnum.UPPER_CASE.getType());
 *    PinyinUtils.getQuanPin("中国", PinyinEnum.LOWER_CASE.getType());
 *    PinyinUtils.getQuanPin("中国", PinyinEnum.UPPER_CASE.getType()); 
 * </pre>
 * 
 * @author huazie
 * @version v1.0.0
 * @since 1.0.0
 * @date 2017年3月6日
 *
 */
public class PinyinUtils {
	private final static Logger LOGGER = LoggerFactory.getLogger(PinyinUtils.class);

	/**
	 * 
	 * 获取中文的简拼
	 * 
	 * @version v1.0.0
	 * @date 2017年3月6日
	 *
	 * @param chinese
	 *            指定的中文字符
	 * @return
	 */
	public static String getJianPin(String chinese, int caseType) {
		String name = getPinyin(chinese, caseType, PinyinEnum.JIANPIN.getType());
		if(PinyinUtils.LOGGER.isDebugEnabled()){
			PinyinUtils.LOGGER.debug("PinyinUtils##converterToFirstSpell() JianPin : {}", name);
		}
		return name;
	}

	/**
	 * 
	 * 获取中文的全拼
	 * 
	 * @version v1.0.0
	 * @date 2017年3月6日
	 *
	 * @param chinese
	 *            指定的中文字符
	 * @return
	 */
	public static String getQuanPin(String chinese, int caseType) {
		String name = getPinyin(chinese, caseType, PinyinEnum.QUANPIN.getType());
		if(PinyinUtils.LOGGER.isDebugEnabled()){
			PinyinUtils.LOGGER.debug("PinyinUtils##converterToAllSpell() QuanPin : {}", name);
		}
		return name;
	}
	
	/**
	 * 
	 * 获取拼音
	 * 
	 * @version v1.0.0
	 * @date 2017年3月6日
	 *
	 * @param chinese
	 *            指定的中文字符
	 * @param caseType
	 *            0：大写 1： 小写
	 * @param howManyCharacter
	 *            0 ：简拼 1： 全拼
	 * @return
	 */
	public static String getPinyin(String chinese, int caseType, int howManyCharacter) {
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		defaultFormat.setCaseType(PinyinEnum.UPPER_CASE.getType() == caseType ? HanyuPinyinCaseType.UPPERCASE : HanyuPinyinCaseType.LOWERCASE);

		String pinyinName = "";
		char[] nameChar = chinese.trim().toCharArray();
		for (int i = 0; i < nameChar.length; i++) {
			if (nameChar[i] > '') {
				try {
					String[] pinyin = PinyinHelper.toHanyuPinyinStringArray(nameChar[i], defaultFormat);
					if ((pinyin != null) && (pinyin.length > 0)) {
						if (howManyCharacter == PinyinEnum.JIANPIN.getType()) {
							pinyinName = pinyinName + pinyin[0].charAt(0);
						} else if (howManyCharacter == PinyinEnum.QUANPIN.getType()) {
							pinyinName = pinyinName + pinyin[0];
						}
					}
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					PinyinUtils.LOGGER.error("Exception: ", e);
				}
			} else {
				pinyinName = pinyinName + nameChar[i];
			}
		}
		return pinyinName;
	}
	
}
