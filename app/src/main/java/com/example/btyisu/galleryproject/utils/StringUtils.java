package com.example.btyisu.galleryproject.utils;

import android.text.*;

import java.io.*;
import java.util.regex.*;

public class StringUtils {
    /**
     * 3자리 마다 콤마 추가
     * @param num 대상문자열
     * @return 추가된 문자열
     */
    public static String addComma(String num) {
//    	boolean isLimitLength = false;
//    	int length = num.length();
//    	if(length > 4){
//    		num = num.substring(0, length - 4);
//    		isLimitLength = true;
//        }
//
//        if(num != null && !TextUtils.isEmpty(num)){
//            num = num.replaceAll(",", "");
//        } else {
//            return "";
//        }
//
//        StringBuffer numStringBuffer = new StringBuffer(num);
//        StringBuffer reverseStringBuffer = new StringBuffer();
//        numStringBuffer = numStringBuffer.reverse();
//        int point = 0;
//
//        for(int i = 0; i < num.length(); i++) {
//            point = i % 3;
//            if(i > 0){
//                if(point == 0){
//                    reverseStringBuffer.append(",");
//                }
//            }
//
//            reverseStringBuffer.append(numStringBuffer.substring(i, (i + 1)));
//
//        }
//        String resultString = (reverseStringBuffer.reverse()).toString();
//
//        return isLimitLength ? resultString + "만+" : resultString;
        if(num != null && !TextUtils.isEmpty(num)){
            num = num.replaceAll(",", "");
        } else {
            return "";
        }

        StringBuffer numStringBuffer = new StringBuffer(num);
        StringBuffer reverseStringBuffer = new StringBuffer();
        numStringBuffer = numStringBuffer.reverse();
        int point = 0;

        for(int i = 0; i < num.length(); i++) {
            point = i % 3;
            if(i > 0){
                if(point == 0){
                    reverseStringBuffer.append(",");
                }
            }

            reverseStringBuffer.append(numStringBuffer.substring(i, (i + 1)));
        }
        return (reverseStringBuffer.reverse()).toString();
    }
    /**
     * 콤마 제거
     *
     * @param num 대상문자열
     * @return 제거된 문자열
     */
    public static String removeComma(String num) {

        if(num != null && !TextUtils.isEmpty(num)){
            return num.replaceAll(",", "");
        } else {
            return "";
        }

    }

    /**
     *
     * @Method  : getFilterAlphaNum
     * @Author  : hjkim
     * @Since   : 2014. 9. 23. 오후 8:08:37
     * 영문만 허용 (숫자 포함)
     * @return
     */
    public static InputFilter getFilterAlphaNum(){
        InputFilter filter = new InputFilter() {

            @Override
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {
                Pattern ps = Pattern.compile("^[a-zA-Z0-9]+$");

                if (!ps.matcher(source).matches()) {
                    return "";
                }
                return null;
            }
        };
        return filter;
    }

    /**
     * 문자열의 바이트를 구한다.
     * @param str
     * @param encoding
     * @return
     */
    public static int getStringBytes(String str, String encoding) {
        int bytes = 0;
        try {
            // 서버에서는 닉네임을 utf-8 기준 25바이트까지 입력허용하기 때문에, 글자수로 체크할 수는 없고, utf-8로 인코딩해서 바이트 값을 얻어온다.
            bytes = str.getBytes(encoding).length;
            // 마지막에 천지인 . 또는 ..이 들어있을 경우, 바이트 수 에서 제외한다. (euc-kr = 1바이트, utf-8 = 3바이트)
            if(isLastCharacterChunjiin(str)) {
                bytes = bytes - (TextUtils.equals(encoding, "euc-kr") ? 1 : 3);
            }
        } catch (UnsupportedEncodingException e) {
        }
        return bytes;
    }
    /**************************************************************************
     * 문자열에서 찾을 문자(열) 수 카운트
     *
     * @param str : 문자열
     * @return boolean : ".", ".." 이면 true
     **************************************************************************/
    public static boolean isLastCharacterChunjiin(String str) {
        boolean bRetVal = false;
        // 예외 처리
        if(TextUtils.isEmpty(str)) {
            return false;
        }
        char lastChar = str.charAt(str.length() - 1);

        if (lastChar == 4510) {         // .
            bRetVal = true;
        } else if (lastChar == 4514) {  // ..
            bRetVal = true;
        }
        return bRetVal;
    }

    /**
     * HTML의 모든 태그를 제거하고 내용만 가져옴 필요에 따라서 부분적인 제거가 가능함
     *
     * @param html string
     * @return html string removed all tags
     */
    public static String stripTag(String szHtml) {
        final Pattern HTML_TAG = Pattern.compile("\\<.*?\\>", Pattern.CASE_INSENSITIVE);
        szHtml = HTML_TAG.matcher(szHtml).replaceAll("");
        szHtml = szHtml.trim();
        return szHtml;
    }
    /**
     * 문자로 표현된 특수기호 escape
     * @param str
     * @return
     */
    public static String escape(String str) {
        String[][] escapeArry = {
                {"&quot;", "\""},
                {"&amp;", "&"},
                {"&apos;", "'"},
                {"&lt;", "<"},
                {"&gt;", ">"}
        };

        for(int i = 0, arryLength = escapeArry.length; i < arryLength; i++)
        {
            str = str.replace(escapeArry[i][0], escapeArry[i][1]);
        }

        return str;
    }

    /**
     * 키워드를 가지는 단어값인지 체크한다.
     * @param value
     * @param searchKeyword
     * @return
     */
    public static boolean isExistInitial(String value, String searchKeyword) {
        return isExistInitial(value, searchKeyword, false);
    }

    public static final int HANGUL_BEGIN_UNICODE = 44032; // 가
    public static final int HANGUL_END_UNICODE = 55203; // 힣
    public static final int HANGUL_BASE_UNIT = 588;

    public static final int[] INITIAL_SOUND_UNICODE = { 12593, 12594, 12596,
            12599, 12600, 12601, 12609, 12610, 12611, 12613, 12614, 12615,
            12616, 12617, 12618, 12619, 12620, 12621, 12622 };

    public static final char[] INITIAL_SOUND = { 'ㄱ', 'ㄲ', 'ㄴ', 'ㄷ', 'ㄸ', 'ㄹ',
            'ㅁ', 'ㅂ', 'ㅃ', 'ㅅ', 'ㅆ', 'ㅇ', 'ㅈ', 'ㅉ', 'ㅊ', 'ㅋ', 'ㅌ', 'ㅍ', 'ㅎ' };

    /**
     * 키워드를 가지는 단어값인지 체크한다.
     * @param value
     * @param searchKeyword
     * @param separateLowerUpperCase 대소문자를 구분한다.
     * @return
     */
    public static boolean isExistInitial(String value, String searchKeyword, boolean separateLowerUpperCase) {

        String convertedValue = "";
        String convertedSearchKeyword = "";

        if (!separateLowerUpperCase) {
            convertedValue = value.toLowerCase();
            convertedSearchKeyword = searchKeyword.toLowerCase();
        } else {
            convertedValue = value;
            convertedSearchKeyword = searchKeyword;
        }

        String result = getHangulInitialSound(convertedValue, convertedSearchKeyword);
        if (result.indexOf(convertedSearchKeyword) >= 0) {
            return true;
        } else {
            return false;
        }
    }
    public static String getHangulInitialSound(String value,
                                               String searchKeyword) {

        return getHangulInitialSound(value, getIsChoSungList(searchKeyword));
    }

    public static String getHangulInitialSound(String value, boolean[] isChoList) {

        StringBuffer result = new StringBuffer();

        int[] unicodeList = convertStringToUnicode(value);
        for (int idx = 0; idx < unicodeList.length; idx++) {
            int unicode = unicodeList[idx];

            if (isChoList != null && idx <= (isChoList.length - 1)) {
                if (isChoList[idx]) {
                    if (HANGUL_BEGIN_UNICODE <= unicode
                            && unicode <= HANGUL_END_UNICODE) {
                        int tmp = (unicode - HANGUL_BEGIN_UNICODE);
                        int index = tmp / HANGUL_BASE_UNIT;
                        result.append(INITIAL_SOUND[index]);
                    } else {
                        result.append(convertUnicodeToChar(unicode));
                    }
                } else {
                    result.append(convertUnicodeToChar(unicode));
                }
            } else {
                result.append(convertUnicodeToChar(unicode));
            }
        }

        return result.toString();
    }
    public static boolean[] getIsChoSungList(String name) {
        if (name == null) {
            return null;
        }

        boolean[] choList = new boolean[name.length()];

        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);

            boolean isCho = false;
            for (char cho : INITIAL_SOUND) {
                if (c == cho) {
                    isCho = true;
                    break;
                }
            }

            choList[i] = isCho;

        }

        return choList;
    }

    /**
     * 문자열을 유니코드(10진수)로 변환 후 반환 한다.
     *
     * @param str
     * @return
     */
    public static int[] convertStringToUnicode(String str) {

        int[] unicodeList = null;

        if (str != null) {
            unicodeList = new int[str.length()];
            for (int i = 0; i < str.length(); i++) {
                unicodeList[i] = convertCharToUnicode(str.charAt(i));
            }
        }

        return unicodeList;
    }

    /**
     * 문자를 유니코드(10진수)로 변환 후 반환 한다.
     *
     * @param ch
     * @return
     */
    public static int convertCharToUnicode(char ch) {
        return (int) ch;
    }

    /**
     * 유니코드(16진수)를 문자로 변환 후 반환 한다.
     *
     * @param hexUnicode
     * @return
     */
    public static char convertUnicodeToChar(String hexUnicode) {
        return (char) Integer.parseInt(hexUnicode, 16);
    }

    /**
     * 유니코드(10진수)를 문자로 변환 후 반환 한다.
     *
     * @param unicode
     * @return
     */
    public static char convertUnicodeToChar(int unicode) {
        return convertUnicodeToChar(toHexString(unicode));
    }

    private static String toHexString(int decimal) {
        Long intDec = Long.valueOf(decimal);
        return Long.toHexString(intDec);
    }

    /**
     * 바이트를 체크한다. 기준보다 크면 false, 작거나 같으면 true
     * 한글 / 영문 /특문 체큰
     * @param txt 체크할 text
     * @param standardByte 기준 바이트 수
     * @return
     */
    public static boolean getByteCheck(String txt, int standardByte) {
        if (TextUtils.isEmpty(txt)) { return true; }

        try{
            if (txt.getBytes("euc-kr").length > standardByte) {
                return false;
            } else {
                return true;
            }
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return false;
    }

}
