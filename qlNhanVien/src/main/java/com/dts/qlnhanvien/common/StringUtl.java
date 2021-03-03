/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dts.qlnhanvien.common;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author User
 */
public class StringUtl {
    public static String getAnsiString(String input) {
        try {
            String temp = Normalizer.normalize(input, Normalizer.Form.NFD);
            Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
            return pattern.matcher(temp)
                    .replaceAll("").toLowerCase()
                    .replaceAll("đ", "d");
        } catch (Exception e) {
        }
        return "";
    }

    public static String stardarPhoneNumber(String msisdn) {
        if (msisdn.startsWith("0"))
            return "84" + msisdn.substring(1);
        else if (msisdn.startsWith("84"))
            return msisdn;
        else if (msisdn.startsWith("+84"))
            return msisdn.substring(1);
        else
            return "84" + msisdn;
    }

    public static String stardarPhoneNumberVclip(String msisdn) {
        if (msisdn.startsWith("0"))
            return "V84" + msisdn.substring(1);
        else if (msisdn.startsWith("84"))
            return "V" + msisdn;
        else if (msisdn.startsWith("+84"))
            return "V" + msisdn.substring(1);
        else
            return "V84" + msisdn;
    }

    public static String formatMobile(String mobile) {
        Pattern p = Pattern.compile("[0-9]{11}|[0-9]{10}");
        Pattern ps = Pattern.compile("^\\s*(?:\\+?([0|84]\\d{1,11}))");
        Matcher matcher = p.matcher(mobile);
        String response = null;

        while (matcher.find()) {
            String m = matcher.group();
            Matcher matchers = ps.matcher(m);
            while (matchers.find()) {
                if (matchers.group().substring(0, 2).equals("84")) {
                    String s = matchers.group().substring(0, 8) + "***";
                    response = mobile.replace(matchers.group(), s);
                } else if (matchers.group().substring(0, 1).equals("0")) {
                    String s = matchers.group().substring(0, 7) + "***";
                    response = mobile.replace(matchers.group(), s);
                }
            }
        }
        if (response == null || response.isEmpty()) {
            response = mobile;
        }
        return response;
    }

    public static String removeAccent(String strIn, List<String> strFind) {
        if (strIn != null && !strIn.isEmpty()) {
            strIn = strIn.trim().replaceAll(" +", " ");

            String strExe = removeAccent(strIn.toLowerCase());

            List<String> strings = new ArrayList<>();

            for (String str : strFind) {
                Pattern pattern = Pattern.compile(str);
                Matcher matcher = pattern.matcher(strExe);
                while (matcher.find()) {
                    String s = strIn.substring(matcher.start(), matcher.end());
                    strings.add(s);
                }
            }
            String strOut = strIn;
            for (String s : strings) {
                strOut = strOut.replace(s, "***");
            }
            return strOut;
        } else {
            return strIn;
        }
    }


    public static String removeAccent(String s) {
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < sb.length(); i++) {
            sb.setCharAt(i, removeAccent(sb.charAt(i)));
        }

        String temp = Normalizer.normalize(sb.toString(), Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("");
    }

    private static char[] SOURCE_CHARACTERS = {'À', 'Á', 'Â', 'Ã', 'È', 'É',
            'Ê', 'Ì', 'Í', 'Ò', 'Ó', 'Ô', 'Õ', 'Ù', 'Ú', 'Ý', 'à', 'á', 'â',
            'ã', 'è', 'é', 'ê', 'ì', 'í', 'ò', 'ó', 'ô', 'õ', 'ù', 'ú', 'ý',
            'Ă', 'ă', 'Đ', 'đ', 'Ĩ', 'ĩ', 'Ũ', 'ũ', 'Ơ', 'ơ', 'Ư', 'ư', 'Ạ',
            'ạ', 'Ả', 'ả', 'Ấ', 'ấ', 'Ầ', 'ầ', 'Ẩ', 'ẩ', 'Ẫ', 'ẫ', 'Ậ', 'ậ',
            'Ắ', 'ắ', 'Ằ', 'ằ', 'Ẳ', 'ẳ', 'Ẵ', 'ẵ', 'Ặ', 'ặ', 'Ẹ', 'ẹ', 'Ẻ',
            'ẻ', 'Ẽ', 'ẽ', 'Ế', 'ế', 'Ề', 'ề', 'Ể', 'ể', 'Ễ', 'ễ', 'Ệ', 'ệ',
            'Ỉ', 'ỉ', 'Ị', 'ị', 'Ọ', 'ọ', 'Ỏ', 'ỏ', 'Ố', 'ố', 'Ồ', 'ồ', 'Ổ',
            'ổ', 'Ỗ', 'ỗ', 'Ộ', 'ộ', 'Ớ', 'ớ', 'Ờ', 'ờ', 'Ở', 'ở', 'Ỡ', 'ỡ',
            'Ợ', 'ợ', 'Ụ', 'ụ', 'Ủ', 'ủ', 'Ứ', 'ứ', 'Ừ', 'ừ', 'Ử', 'ử', 'Ữ',
            'ữ', 'Ự', 'ự',};

    // Mang cac ky tu thay the khong dau
    private static char[] DESTINATION_CHARACTERS = {'A', 'A', 'A', 'A', 'E',
            'E', 'E', 'I', 'I', 'O', 'O', 'O', 'O', 'U', 'U', 'Y', 'a', 'a',
            'a', 'a', 'e', 'e', 'e', 'i', 'i', 'o', 'o', 'o', 'o', 'u', 'u',
            'y', 'A', 'a', 'D', 'd', 'I', 'i', 'U', 'u', 'O', 'o', 'U', 'u',
            'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A',
            'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'E', 'e',
            'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E',
            'e', 'I', 'i', 'I', 'i', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o',
            'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O',
            'o', 'O', 'o', 'U', 'u', 'U', 'u', 'U', 'u', 'U', 'u', 'U', 'u',
            'U', 'u', 'U', 'u',};

    public static char removeAccent(char ch) {
        int index = Arrays.binarySearch(SOURCE_CHARACTERS, ch);
        if (index >= 0) {
            ch = DESTINATION_CHARACTERS[index];
        }
        return ch;
    }

}
