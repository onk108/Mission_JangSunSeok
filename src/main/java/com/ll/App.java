package com.ll;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    Scanner scanner;
    int number = 0;
    List<Quotation> quotationList;
    App() {
        scanner = new Scanner(System.in);
        quotationList = new ArrayList<>();
    }
    public void run() {
        System.out.println("== 명언 앱 ==");
        while (true) {
            System.out.print("명령) ");
            String cmd = scanner.nextLine();

            if(cmd.equals("종료")) {
                break;
            } else if(cmd.equals("등록")) {
                Write();
            } else if(cmd.equals("목록")) {
                List();
            } else if(cmd.startsWith("삭제?")) {
                Remove(cmd);
            } else if(cmd.startsWith("수정?")) {
                Modify(cmd);
            }
        }
    }

    private void Modify(String cmd) {
        String idStr = cmd.replace("수정?id=","");
        int id = Integer.parseInt(idStr);
        int index = getIndex(id);

        if(index<0) {
            System.out.printf("%d번 명언은 존재하지 않습니다.\n",id);
            return;
        }
        Quotation quotation = quotationList.get(index);

        System.out.printf("명언(기존) : %s\n", quotation.content);
        System.out.print("명언 : ");
        String content = scanner.nextLine();

        System.out.printf("작가(기존) : %s\n",quotation.authorName);
        System.out.print("작가 : ");
        String authorName = scanner.nextLine();

        quotation.content = content;
        quotation.authorName = authorName;

        System.out.printf("%d번 명언을 수정하였습니다.\n",index+1);
    }

    private void Remove(String cmd) {
        String idStr = cmd.replace("삭제?id=","");
        int id = Integer.parseInt(idStr);
        int index = getIndex(id);

        if(index<0) {
            System.out.printf("%d번 명언은 존재하지 않습니다.\n",id);
            return;
        }

        quotationList.remove(index);
        System.out.printf("%d번 명언이 삭제되었습니다.\n",index+1);
    }

    int getIndex(int id) {
        for (int i = 0; i < quotationList.size(); i++) {
            Quotation quotation = quotationList.get(i);

            if (quotation.number == id) {
                return i;
            }
        }
        return -1;
    }

    private void Write() {
        System.out.print("명언 : ");
        String content = scanner.nextLine();

        System.out.print("작가 : ");
        String authorName = scanner.nextLine();

        number++;
        Quotation quotation = new Quotation(number, content, authorName);
        quotationList.add(quotation);

        System.out.printf("%d번 명언이 등록되었습니다.\n",number);
    }

    private void List() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("-------------------");

        for(int i=0; i<quotationList.size(); i++) {
            Quotation quotation = quotationList.get(i);

            System.out.printf("%d / %s / %s\n",quotation.number, quotation.content, quotation.authorName);
        }
    }
}
