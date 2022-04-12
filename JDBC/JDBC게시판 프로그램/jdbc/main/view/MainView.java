package edu.kh.jdbc.main.view;

import edu.kh.jdbc.member.model.vo.Member;
import edu.kh.jdbc.member.view.MemberView;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainView { // 메인 메뉴(메뉴 선택용/입력 화면)

    private Scanner sc = new Scanner(System.in);

    // 로그인한 회원의 정보가 저장된 객체를 참조할 변수
    private Member loginMember = null;

    // 회원 관련 기능 화면을 모아둔 객체를 참조할 변수
    private MemberView memberView = new MemberView();


    /**
     * 메인 메뉴 출력 메서드
     */
    public void displayMenu() {

        int menuNum = -1; // 메뉴 선택 값 저장 변수
                          // 초기 값을 -1로 지정하여, 반복문 첫 바퀴에서 오류 발생 시 종료되는 것을 방지

        do {

            try {

                if (loginMember == null) { // 로그인이 되어있지 않은 경우

                    System.out.println("\n********** 회원제 게시판 프로그램 **********\n");

                    System.out.println("1. 로그인");
                    System.out.println("2. 회원 가입");
                    System.out.println("0. 프로그램 종료");

                    System.out.print("메뉴를 선택해주세요 >> ");
                    menuNum = sc.nextInt(); // InputMismatchException 발생 가능성 있음
                    sc.nextLine();

                    switch (menuNum) {
                        case 1: loginMember = memberView.login(); break;
                        case 2: memberView.signUp(); break;
                        case 0: System.out.println("\n--- 프로그램 종료 ---"); break;
                        default: System.out.println("\n메뉴에 작성된 번호를 입력해주세요.");
                    }

                } else { // 로그인이 되어있는 경우

                    // - 게시글 삽입
                    // - 게시판 목록 조회
                    // - 게시글 상세조회(댓글 X)
                    // - 게시글 상세조회(+ 댓글 조회, 삽입, 수정, 삭제)
                    // - 게시글 수정
                    // - 게시글 삭제

                    System.out.println("\n********** 회원 메뉴 **********\n");
                    // - 로그인한 회원 정보 조회
                    // - 회원 목록 조회
                    // - 회원 정보 수정(이름, 성별)
                    // - 비밀번호 변경
                    // - 회원 탈퇴

                    System.out.println("1. 내 정보 조회");
                    System.out.println("2. 가입된 회원 목록 조회");
                    System.out.println("3. 내 정보 수정(이름, 성별)");
                    System.out.println("4. 비밀번호 변경");
                    System.out.println("5. 회원 탈퇴");
                    System.out.println("9. 로그아웃");
                    System.out.print("메뉴를 선택해주세요 >> ");
                    menuNum = sc.nextInt();
                    sc.nextLine();
                    System.out.println();

                    switch (menuNum) {
                        case 1: memberView.myInfo(loginMember); break;
                        case 2: memberView.selectAll(); break;
                        case 3: memberView.updateMyInfo(loginMember); break;
                        case 4: memberView.updatePw(loginMember); break;
                        case 5:
                            int result = memberView.secession(loginMember);
                            if (result > 0) loginMember = null;
                            break;
                        case 9: loginMember = null; break;
                        default: System.out.print("메뉴에 작성된 번호를 입력해주세요.");
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("\n입력 형식이 올바르지 않습니다. 다시 시도해주세요.");
                sc.nextLine(); // 입력 버퍼에 남은 잘못된 문자열 제거
            }

        } while (menuNum != 0);
    }
}
