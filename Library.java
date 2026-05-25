
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Library {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int i;

        do {
            i = 0;
            boolean isValid = false;

            System.out.println("操作を選んでください。");
            System.out.println("1:本の検索");
            System.out.println("2:本の貸出");
            System.out.println("3:本の返却");
            System.out.println("0:終了");

            while (!isValid) {
                try {
                    i = scanner.nextInt();
                    if (i >= 0 && i <= 3) {
                        isValid = true;
                    } else {
                        System.out.println("0~3の範囲で入力してください");
                    }
                } catch (InputMismatchException e) { //整数入力の例外処理
                    System.out.println("整数を入力してください");
                    scanner.next();
                }
            }

            switch (i) {
                case 1:
                    System.out.println("検索する本のタイトルまたは著者名を入力してください。");
                    String name = scanner.next();
                    //検索関数
                    try {
                        LibraryDAO dao = new LibraryDAO();

                        List<Object> result = dao.searchBook(name);

                        if (result != null) {
                            System.out.println("ID : " + result.get(0));
                            System.out.println("タイトル : " + result.get(1));
                            System.out.println("著者 : " + result.get(2));
                            System.out.println("貸出状態 : " + result.get(3));
                        } else {
                            System.out.println("本が見つかりません");
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    System.out.println("貸出する本のidを入力してください。");
                    int brrowid = scanner.nextInt();
                    try {
                        LibraryService.checkout(brrowid);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //貸出関数
                    break;
                case 3:
                    System.out.println("返却する本のidを入力してください。");
                    int returnid = scanner.nextInt();
                    try {
                        LibraryService.checkin(returnid);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //返却関数
                    break;
            }
        } while (i != 0);

        System.out.println("ご利用ありがとうございます。");
    }
}
