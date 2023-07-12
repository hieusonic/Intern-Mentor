package com.example.demo2.until;


import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/*
class lưu lại những method để đưa ra những điều kiện để so sánh với các data được truyền vào
 */
public class StringUtils {

    public static boolean isNumber(String data) {
// kiểm tra chuỗi đầu vào có phải là một chuỗi rỗng không
        if (data.matches("")) {
            return false;
        }
        // chuyển đổi chuỗi data đầu vào thành một số nguyên
        try {
            int page = Integer.parseInt(data);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public static boolean isLocalDate(String data) {
        // kiểm tra xem chuỗi đầu vào có phải là một chuỗi rỗng không
        if (data.matches("")) {
            return false;
        }
        // chuyển đổi chuỗi data đầu vào thành kiểu dữ liệu về thời gian
        //nếu chuỗi hợp lệ cho thời gian "2023-07-09", phương thức parse() của lớp LocalDate sẽ chuyển đổi chuỗi thành một đối tượng LocalDate
        try {
            LocalDate.parse(data);
        } catch (Exception e) {
            return false;
        }

        return true;
    }
        // method đưa ra điều kiện mà tên email bắt buộc phải tuân theo
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        // tạo ra method để so sánh với teenn email
    public static boolean validate(String emailStr) { // truyền vào email
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr); // so sánh
        return matcher.find();
    }
    // phương thức tạo mật khẩu có độ dài n
    public static String getNewPass(int n) {

        // choose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int) (AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }
}
