package edu.kh.array2.practice;

import java.util.Scanner;

public class Array2Practice {
    Scanner sc = new Scanner(System.in);

    public void practice1() {
        String[][] arr = new String[3][3];

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++)
                arr[i][j] = sc.nextLine();
        }

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++)
                System.out.print(arr[i][j]);
            System.out.println();
        }
    }

    public void practice2() {
        int[][] arr = new int[4][4];

        int val = 1;

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++)
                arr[i][j] = val++;
        }

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++)
                System.out.printf("%3d", arr[i][j]);
            System.out.println();
        }
    }

    public void practice3() {
        int[][] arr = new int[4][4];

        int val = 16;

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++)
                arr[i][j] = val--;
        }

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++)
                System.out.printf("%3d", arr[i][j]);
            System.out.println();
        }
    }

    public void practice4() {
        int[][] arr = new int[4][4];

        for (int i = 0; i < arr.length - 1; i++) {
            int sum = 0;
            for (int j = 0; j < arr[0].length - 1; j++) {
                arr[i][j] = (int) (Math.random() * 10 + 1);
                sum += arr[i][j];
            }
            arr[i][3] = sum;
        }

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr[0].length - 1; j++) {
                arr[3][i] += arr[j][i];
            }
        }
        arr[3][3] = arr[3][0] + arr[3][1] + arr[3][2];

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++)
                System.out.printf("%3d", arr[i][j]);
            System.out.println();
        }
    }

    public void practice5() {
        int row;
        int col;

        while (true) {
            System.out.print("행 크기 : ");
            row = sc.nextInt();
            System.out.print("열 크기 : ");
            col = sc.nextInt();

            if ((row > 1 && row < 10) && (col > 1 && col < 10)) break;

            System.out.println("반드시 1 ~ 10 사이의 정수를 입력해야 합니다.");
        }

        char[][] arr = new char[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++)
                arr[i][j] = (char) (Math.random() * 26 + 65);
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++)
                System.out.printf("%3c", arr[i][j]);
            System.out.println();
        }
    }

    public void practice6() {
        System.out.print("행 크기 : ");
        int row = sc.nextInt();

        char[][] arr = new char[row][];
        char data = 'a';

        for (int i = 0; i < row; i++) {
            System.out.print(i + "열 크기 : ");
            int col = sc.nextInt();

            arr[i] = new char[col];

            for (int j = 0; j < col; j++)
                arr[i][j] = data++;
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < arr[i].length; j++)
                System.out.print(arr[i][j] + "  ");
            System.out.println();
        }
    }

    public void practice7() {
        String[] students = {"강건강", "남나나", "도대담", "류라라", "문미미", "박보배", "송성실", "윤예의",
                "진재주", "차천축", "피풍표", "홍하하"};

        String[][] arr1 = new String[3][2];
        String[][] arr2 = new String[3][2];

        int count = 0;

        System.out.println("== 1분단 ==");
        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr1[0].length; j++)
                arr1[i][j] = students[count++];
        }
        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr1[0].length; j++)
                System.out.print(arr1[i][j] + " ");
            System.out.println();
        }


        System.out.println("== 2분단 ==");
        for (int i = 0; i < arr2.length; i++) {
            for (int j = 0; j < arr2[0].length; j++)
                arr2[i][j] = students[count++];
        }
        for (int i = 0; i < arr2.length; i++) {
            for (int j = 0; j < arr2[0].length; j++)
                System.out.print(arr2[i][j] + " ");
            System.out.println();
        }
    }

    public void practice8() {
        String[] students = {"강건강", "남나나", "도대담", "류라라", "문미미", "박보배", "송성실", "윤예의",
                "진재주", "차천축", "피풍표", "홍하하"};

        String[][] arr1 = new String[3][2];
        String[][] arr2 = new String[3][2];

        int count = 0;

        System.out.println("== 1분단 ==");
        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr1[0].length; j++)
                arr1[i][j] = students[count++];
        }
        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr1[0].length; j++)
                System.out.print(arr1[i][j] + " ");
            System.out.println();
        }


        System.out.println("== 2분단 ==");
        for (int i = 0; i < arr2.length; i++) {
            for (int j = 0; j < arr2[0].length; j++)
                arr2[i][j] = students[count++];
        }
        for (int i = 0; i < arr2.length; i++) {
            for (int j = 0; j < arr2[0].length; j++)
                System.out.print(arr2[i][j] + " ");
            System.out.println();
        }

        System.out.println("============================");
        System.out.print("검색할 학생 이름을 입력하세요 : ");
        String name = sc.next();

        int part = 0;
        int line = 0;
        String direct = "";

        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr1[i].length; j++) {
                if (arr1[i][j].equals(name)) {
                    part = 1;
                    line = i + 1;
                    direct = (j == 0) ? "왼쪽" : "오른쪽";

                } else if (arr2[i][j].equals(name)) {
                    part = 2;
                    line = i + 1;
                    direct = (j == 0) ? "왼쪽" : "오른쪽";
                }
            }
        }

        if (part == 0) System.out.println("검색 결과가 없습니다.");
        else {
            System.out.print("검색하신 " + name + " 학생은 ");
            System.out.println(part + "분단 " + line + "번째 줄 " + direct + "에 있습니다.");
        }
    }

    public void practice9() {
        String[][] arr = new String[6][6];

        arr[0][0] = "";
        for (int i = 1; i < arr.length; i++) arr[i][0] = String.valueOf(i - 1);
        for (int i = 1; i < arr[0].length; i++) arr[0][i] = String.valueOf(i - 1);

        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j < arr[0].length; j++)
                arr[i][j] = "";
        }

        System.out.print("행 인덱스 입력 : "); int row = sc.nextInt();
        System.out.print("열 인덱스 입력 : "); int col = sc.nextInt();

        arr[row + 1][col + 1] = " X";

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++)
                System.out.printf("%3s", arr[i][j]);
            System.out.println();
        }
    }

    public void practice10() {
        String[][] arr = new String[6][6];

        arr[0][0] = "";
        for (int i = 1; i < arr.length; i++) arr[i][0] = String.valueOf(i - 1);
        for (int i = 1; i < arr[0].length; i++) arr[0][i] = String.valueOf(i - 1);

        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j < arr[0].length; j++)
                arr[i][j] = "";
        }
        while (true) {
            System.out.print("행 인덱스 입력 : "); int row = sc.nextInt();
            if (row == 99) break;
            System.out.print("열 인덱스 입력 : "); int col = sc.nextInt();

            arr[row + 1][col + 1] = "X";

            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[0].length; j++)
                    System.out.printf("%3s", arr[i][j]);
                System.out.println();
            }
        }
        System.out.println("프로그램 종료");
    }

    public void bingoGame() {
        System.out.print("빙고판 크기 지정 : ");
        int bingoSize = sc.nextInt();
        String[][] arr = new String[bingoSize][bingoSize];
        String[] ranNum = new String[bingoSize * bingoSize];


        for (int i = 0; i < ranNum.length; i++) {
            ranNum[i] = String.valueOf((int) (Math.random() * (bingoSize * bingoSize) + 1));
            for (int j = 0; j < i; j++) {
                if (ranNum[i].equals(ranNum[j])) {
                    i--;
                    break;
                }
            }
        }

        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++)
                arr[i][j] = ranNum[count++];
        }

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++)
                System.out.printf("%4s", arr[i][j]);
            System.out.println();
        }

        System.out.println("=============빙고게임 시작=============");

        while (true) {
            int num;

            while (true) {
                System.out.print("정수를 입력하시오 : ");
                num = sc.nextInt();

                if (num > 0 && num <= bingoSize * bingoSize) break;

                System.out.println("다시 입력해주세요.");
            }

            for (int i = 0; i < arr.length; i++)
                for (int j = 0; j < arr[0].length; j++)
                    if (arr[i][j].equals(String.valueOf(num)))
                        arr[i][j] = "★";

            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[0].length; j++)
                    System.out.printf("%4s", arr[i][j]);
                System.out.println();
            }

            int left = 0;
            int right = 0;
            int bingoCount = 0;

            for (int i = 0; i < bingoSize; i++) {
                int row = 0;
                int col = 0;

                for (int j = 0; j < bingoSize; j++) {
                    if (arr[i][j].equals("★")) col++;
                    if (arr[j][i].equals("★")) row++;
                    if (i == j && arr[i][j].equals("★")) left++;
                    if ((i + j) == bingoSize - 1 && arr[i][j].equals("★")) right++;
                }

                if (col == bingoSize) bingoCount++;
                if (row == bingoSize) bingoCount++;
            }

            if (left == bingoSize) bingoCount++;
            if (right == bingoSize) bingoCount++;

            System.out.println("현재 " + bingoCount + "빙고");

            if (bingoCount >= 3) {
                System.out.println("***** BINGO!!! *****");
                break;
            }
        }
    }
}
