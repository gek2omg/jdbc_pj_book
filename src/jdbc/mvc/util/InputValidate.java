package jdbc.mvc.util;

import java.util.Scanner;

public class InputValidate {

    public static int readMenuInput(Scanner scanner, int min, int max) {
        while (true) {
            try {
                String input = scanner.nextLine();
                int menuNo = Integer.parseInt(input);
                if (menuNo < min || menuNo > max) {
                    System.out.printf("%d부터 %d 사이 숫자를 입력해주세요.\n", min, max);
                } else {
                    return menuNo;
                }
            } catch (NumberFormatException e) {
                System.out.println("숫자만 입력해주세요.");
            }
        }
    }
}
