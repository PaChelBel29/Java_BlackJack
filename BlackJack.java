package Black_Jack_Project;

import java.util.Scanner;
import java.util.Vector;
import 프로젝트.Card;

public class BlackJack {
static final Scanner scanner = new Scanner(System.in);
public static int computeScoreUser(ScoreTable table, Vector<
Card> cards) {
// (H)
//table을 이용해 유저의 card 들의 점수 총합을 리턴. 키보드
//입력을 받아 ACE의 점수를 결정하는 것 또한 수행한다.
}
public static int computeScoreDealer(ScoreTable table, Vector
<Card> cards) {
// (I)
//table을 이용해 딜러의 card 들의 점수 총합을 리턴. ACE의 점수
//계산은 세부규칙을 참고.
}
public static boolean is_bust(int score) {
// (J)
//점수를 받아 21점이 초과하는지 검사
}
public static boolean checkBlackjack(Vector<Card> cards) {
// (K)
//카드들을 받아 블랙잭인지 아닌지 리턴
}
public static void sleep(int time) {
// (L)
//time 만큼 pause 후 재개. (단위: 밀리세컨드)
}
public static void main(String[] args) {
// (M)
//메인 실행 부분
}
}
