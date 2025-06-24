package untils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductSorter {
    public static boolean isSortedAZ(List<String> productList) {
        // Tạo bản sao của danh sách để không làm thay đổi danh sách gốc
        List<String> sortedList = new ArrayList<>(productList);

        // Sắp xếp bản sao theo thứ tự từ A đến Z (tăng dần, không phân biệt hoa thường)
        Collections.sort(sortedList, String.CASE_INSENSITIVE_ORDER);

        // So sánh danh sách gốc với bản đã sắp xếp
        return productList.equals(sortedList);
    }
}
