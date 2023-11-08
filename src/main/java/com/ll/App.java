package com.ll;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    Scanner scanner;
    int lastQuotationId;
    List<Quotation> quotations;

    App() {
        scanner = new Scanner(System.in);
        lastQuotationId = 0;
        quotations = new ArrayList<>();
    }

    void run() {
        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.print("명령) ");

            String cmd = scanner.nextLine();
            Rq rq = new Rq(cmd);

            if (cmd.equals("종료")) {
                break;
            } else if (cmd.equals("등록")) {
                actionWrite();
            } else if (cmd.equals("목록")) {
                actionList();
            } else if(cmd.startsWith("삭제?")) {
                actionRemove(rq);
            } else if(cmd.startsWith("수정?")) {
                actionModify(rq);
            }
        }
    }

    void actionModify(Rq rq) {
        int id = rq.getParamAsInt("id",0);

        if(id==0) {
            System.out.println("id를 정확히 입력해주세요.");
            return;
        }
        int index = findIndex(id);

        if(index == -1) {
            System.out.printf("%d번 명언은 존재하지 않습니다.\n",id);
            return;
        }
        Quotation quotation = quotations.get(index);

        System.out.printf("명언(기존) : %s\n", quotation.content);
        System.out.print("명언 : ");
        String content = scanner.nextLine();

        System.out.printf("작가(기존) : %s\n",quotation.authorName);
        System.out.print("작가 : ");
        String authorName = scanner.nextLine();

        quotation.content = content;
        quotation.authorName = authorName;

        System.out.printf("%d번 명언을 수정하였습니다.\n",id);
    }

    int findIndex(int id) {
        for(int i=0; i<quotations.size(); i++) {
            Quotation quotation = quotations.get(i);

            if(quotation.id==id) {
                return i;
            }
        }
        return -1;
    }

    void actionRemove(Rq rq) {
        int id = rq.getParamAsInt("id",0);

        if(id==0) {
            System.out.println("id를 정확히 입력해주세요.");
            return;
        }
        int index = getIndex(id);

        if(index==-1) {
            System.out.printf("%d번 명언은 존재하지 않습니다.\n",id);
            return;
        }
        quotations.remove(index);
        System.out.printf("%d번 명언은 삭제되었습니다.\n",id);
    }

    int getIndex(int id) {
        for(int i=0; i<quotations.size(); i++) {
            Quotation quotation = quotations.get(i);

            if(quotation.id==id) {
                return i;
            }
        }
        return -1;
    }

    void actionWrite() {
        System.out.print("명언 : ");
        String content = scanner.nextLine();

        System.out.print("작가 : ");
        String authorName = scanner.nextLine();

        lastQuotationId++;
        int id = lastQuotationId;

        Quotation quotation = new Quotation(id, content, authorName);
        quotations.add(quotation);

        System.out.printf("%d번 명언이 등록되었습니다.\n", lastQuotationId);
    }

    void actionList() {
        System.out.println("번호 / 작가 / 명언");

        System.out.println("----------------------");

        if (quotations.isEmpty())
            System.out.println("등록된 명언이 없습니다.");

        for (int i = quotations.size() - 1; i >= 0; i--) {
            Quotation quotation = quotations.get(i);
            System.out.printf("%d / %s / %s\n", quotation.id, quotation.authorName, quotation.content);
        }
    }
}