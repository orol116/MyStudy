package edu.kh.array.practice;

import java.util.*;

public class ArrayPractice {
    Scanner sc = new Scanner(System.in);

    public void practice1() {
        int[] arr = new int[9];
        int sum = 0;

        for (int i = 1; i <= 9; i++) {
            arr[i - 1] = i;
            System.out.print(i + " ");

            if ((i - 1) % 2 == 0)
                sum += i;
        }
        System.out.println("\n짝수 번째 인덱스 합 : " + sum);
    }

    public void practice2() {
        int[] arr = new int[9];
        int sum = 0;

        for (int i = 9; i >= 1; i--) {
            arr[i - 1] = i;
            System.out.print(i + " ");

            if ((i - 1) % 2 == 1)
                sum += i;
        }
        System.out.println("\n홀수 번째 인덱스 합 : " + sum);
    }

    public void practice3() {
        System.out.print("양의 정수 : ");
        int num = sc.nextInt();
        int[] arr = new int[num];

        for (int i = 1; i <= arr.length; i++) {
            arr[i - 1] = i;
            System.out.print(i + " ");
        }
    }

    public void practice4() {
        int[] arr = new int[5];

        for (int i = 0; i < arr.length; i++) {
            System.out.print("입력 " + i + " : " );
            arr[i] = sc.nextInt();
        }

        System.out.print("검색할 값 : ");
        int findNum = sc.nextInt();

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == findNum) {
                System.out.print("인덱스 : " + i);
                return;
            }
        }
        System.out.print("일치하는 값이 존재하지 않습니다.");
    }

    public void practice5() {
        System.out.print("문자열 : ");
        String str = sc.next();
        char[] ch = new char[str.length()];

        System.out.print("문자 : ");
        String str2 = sc.next();
        char ch2 = str2.charAt(0);

        int count = 0;

        System.out.print(str + "에 " + ch2 + "가 존재하는 위치(인덱스) : ");
        for (int i = 0; i < str.length(); i++) {
            ch[i] = str.charAt(i);

            if (str.charAt(i) == ch2) {
                System.out.print(i + " ");
                count++;
            }
        }
        System.out.println("\n" + ch2 + " 개수 : " + count);
    }

    public void practice6() {
        System.out.print("정수 : "); int num = sc.nextInt();
        int[] arr = new int[num];
        int sum = 0;

        for (int i = 0; i < arr.length; i++) {
            System.out.print("배열 " + i + "번째 인덱스에 넣을 값 : ");
            arr[i] = sc.nextInt();
            sum += arr[i];
        }
        for (int i = 0; i < arr.length; i++) System.out.print(arr[i] + " ");

        System.out.println("\n총 합 : " + sum);
    }

    public void practice7() {
        System.out.print("주민등록번호(-포함) : ");
        String str = sc.next();

        char[] ch = new char[str.length()];
        for (int i = 0; i < str.length(); i++) {
            if (i > 7) ch[i] = '*';
            else ch[i] = str.charAt(i);

            System.out.print(ch[i]);
        }
    }

    public void practice8(){
        int num = 0;

        while (true) {
            System.out.print("정수 : ");
            num = sc.nextInt();
            if (num < 3 || num % 2 == 0) System.out.print("다시 입력하세요.\n");
            else break;
        }

        int[] arr = new int[num];
        for (int i = 1; i <= num / 2 + 1; i++) {
            arr[i - 1] = i;
            System.out.print(i + ", ");
        }
        for (int i = num / 2; i >= 1; i--) {
            arr[i - 1] = i;
            if (i == 1) System.out.print(i);
            else System.out.print(i + ", ");
        }
    }

    public void practice9() {
        int[] arr = new int[10];

        System.out.print("발생한 난수 : ");
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random() * 10 + 1);
            System.out.print(arr[i] + " ");
        }
    }

    public void practice10() {
        int[] arr = new int[10];
        int max = 0;
        int min = 11;

        System.out.print("발생한 난수 : ");
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random() * 10 + 1);
            System.out.print(arr[i] + " ");

            if (max < arr[i]) max = arr[i];
            else if (min > arr[i]) min = arr[i];
        }
        System.out.print("\n최대값 : " + max);
        System.out.print("\n최소값 : " + min);
    }

    public void practice11() {
        int[] arr = new int[10];

        System.out.print("발생한 난수 : ");
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random() * 10 + 1);
            for(int j = 0; j < i; j++)
                if(arr[i] == arr[j]) i--;
        }

        for (int i = 0; i < arr.length; i++) System.out.print(arr[i] + " ");
    }

    public void practice12() {
        int[] arr = new int[6];
        Random random = new Random();

        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(45) + 1;

            for (int j = 0; j < i; j++)
                if (arr[i] == arr[j]) i--;
        }

        Arrays.sort(arr);

        for (int i = 0; i < arr.length; i++)
            System.out.print(arr[i] + " ");
    }

    public void practice13() {
        System.out.print("문자열 : ");
        String str = sc.next();

        List<Character> ch = new ArrayList<Character>();

        for (int i = 0; i < str.length(); i++)
            if (!ch.contains(str.charAt(i)))
                ch.add(str.charAt(i));

        System.out.print("문자열에 있는 문자 : ");
        for (int i = 0; i < ch.size(); i++) {
            if (i == ch.size()) System.out.print(ch.get(i));
            else System.out.print(ch.get(i) + ", ");
        }
        System.out.print("\n문자 개수 : " + ch.size());
    }

    public void practice14() {
        System.out.print("배열의 크기를 입력하세요 : ");
        String size = sc.nextLine();
        int realSize = Integer.parseInt(size);
        String[] str = new String[realSize];

        for (int i = 0; i < realSize; i++) {
            System.out.print((i + 1) + "번째 문자열 : ");
            str[i] = sc.nextLine();
        }

        String[] newArr = new String[0];

        while (true) {
            System.out.print("더 값을 입력하시겠습니까?(Y/N) : ");
            String yesOrNo = sc.nextLine();


            if (yesOrNo.equals("n")) break;

            System.out.print("더 입력하고 싶은 개수 : ");
            size = sc.nextLine();
            int plusSize = Integer.parseInt(size);

            newArr = Arrays.copyOf(str, plusSize + realSize);

            for (int i = realSize; i < plusSize + realSize; i++) {
                System.out.print((i + 1) + "번째 문자열 : ");
                newArr[i] = sc.nextLine();
            }
        }

        System.out.println(Arrays.toString(newArr));
    }
}
