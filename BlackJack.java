import java.util.*;

public class BlackJack {
static final Scanner scanner = new Scanner(System.in);
public static int computeScoreUser(ScoreTable table, Vector<Card> cards) {
	int score=0;
	int ace=0;
	int tt[];
	for(int i=0;i<cards.size();i++) {
		if(cards.get(i).getRank().equals("A")) {
			ace++;
		}
		else {
			score+=table.score(cards.get(i))[0];
		}
	}
	if(ace==1) {
		if(score+11>21) {
			score+=table.score(new Card("","A"))[0];
		}
		else {
			score+=table.score(new Card("","A"))[1];
		}
	}
	else if(ace==2) {
		if(score+12>21) {
			score+=score+=table.score(new Card("","A"))[0]*2;
		}
		else {
		score=score+table.score(new Card("","A"))[0]+table.score(new Card("","A"))[1];
		}
	}
	else if(ace==3) {
		if(score+13>21) {
			score+=score+=table.score(new Card("","A"))[0]*3;
		}
		else {
			score=score+table.score(new Card("","A"))[0]*2+table.score(new Card("","A"))[1];
		}
	}
	else if(ace==4) {
		if(score+14>21) {
			score+=score+=table.score(new Card("","A"))[0]*4;
		}
		else {
			score=score+table.score(new Card("","A"))[0]*3+table.score(new Card("","A"))[1];
		}
	}
	
	return score;
}
// (H)
//table을 이용해 유저의 card 들의 점수 총합을 리턴. 키보드
//입력을 받아 ACE의 점수를 결정하는 것 또한 수행한다.

public static int computeScoreDealer(ScoreTable table, Vector<Card> cards) {
	int score=0;
	int ace=0;
	int tt[];
	for(int i=0;i<cards.size();i++) {
		if(cards.get(i).getRank().equals("A")) {
			ace++;
		}
		else {
			score+=table.score(cards.get(i))[0];
		}
	}
	if(ace==1) {
		if(score+11>21) {
			score+=table.score(new Card("","A"))[0];
		}
		else {
			score+=table.score(new Card("","A"))[1];
		}
	}
	else if(ace==2) {
		if(score+12>21) {
			score+=score+=table.score(new Card("","A"))[0]*2;
		}
		else {
		score=score+table.score(new Card("","A"))[0]+table.score(new Card("","A"))[1];
		}
	}
	else if(ace==3) {
		if(score+13>21) {
			score+=score+=table.score(new Card("","A"))[0]*3;
		}
		else {
			score=score+table.score(new Card("","A"))[0]*2+table.score(new Card("","A"))[1];
		}
	}
	else if(ace==4) {
		if(score+14>21) {
			score+=score+=table.score(new Card("","A"))[0]*4;
		}
		else {
			score=score+table.score(new Card("","A"))[0]*3+table.score(new Card("","A"))[1];
		}
	}
	return score;
// (I)
//table을 이용해 딜러의 card 들의 점수 총합을 리턴. ACE의 점수
//계산은 세부규칙을 참고.
}
public static boolean is_bust(int score) {
	if(score>21) {
		return true;
	}
	else {
	return false;
	}
// (J)
//점수를 받아 21점이 초과하는지 검사
}
public static boolean checkBlackjack(Vector<Card> cards) {
	int is_A = 0;
	int is_JQK = 0;
	for(int i=0;i<cards.size();i++) {
		if(cards.get(i).getRank().equals("A")) {
			is_A++;
		}
		if(cards.get(i).getRank().equals("J")||(cards.get(i).getRank().equals("Q")||(cards.get(i).getRank().equals("K")))){
			is_JQK++;
		}
	}
	if(is_A==1&&is_JQK==1) {
		return true;
	}
	
	if(computeScoreUser(new ScoreTable(),cards)==21) {
		return true;
	}
	else {
		return false;
	}
// (K)
//카드들을 받아 블랙잭인지 아닌지 리턴
}
public static void sleep(int time) throws InterruptedException {
	Thread.sleep(time);
// (L)
//time 만큼 pause 후 재개. (단위: 밀리세컨드)
}
public static void main(String[] args) {
	Scanner scanner = new Scanner(System.in);
	int rand=(int) (Math.random()*2);
	System.out.print("사용할 덱의 개수를 입력해주세요. >> ");
	int deck_count=scanner.nextInt();
	System.out.println(deck_count+"개의 덱,"+deck_count*54+"개의 카드를 사용합니다.\n");
	CardPool cp = new CardPool(2);
	Player player = new Player();
	Player dealer = new Player();
	player.addCard(cp.drawCard());
	player.addCard(cp.drawCard());
	dealer.addCard(cp.drawCard());
	dealer.addCard(cp.drawCard());
	System.out.println("당신의 카드는 [ "+player.get(0)+", "+player.get(1) +" ]입니다.");
	if(checkBlackjack(player.getHand())) {
		System.out.println("당신의 BlackJack으로 승리했습니다.");
		System.exit(0);
	}
	System.out.println("딜러의 카드는 "+dealer.get(rand) +"입니다.\n");
	while(true) {
	System.out.println("당신의 차례입니다!");
	while(true) {
		System.out.print("카드를 더 받으시겠습니까? (Hit / Stand) >> ");
		String com=scanner.next();
	if(com.equals("Hit")) {
		player.addCard(cp.drawCard());
		System.out.println(player.get(player.getHand().size()-1)+"를 받았습니다.\n");
		
		if(is_bust(computeScoreDealer(new ScoreTable(), player.getHand()))) {
			System.out.println("당신의 Bust로 패배했습니다.");
			System.exit(0);
		}
	}
	else if(com.equals("Stand")) {
		System.out.println("당신의 차례가 끝났습니다.\n");
		break;
	}
	}
	System.out.println("딜러의 차례입니다!");
	System.out.println("딜러의 카드는 "+dealer.get(0) +", "+dealer.get(1)+"입니다.\n");
	
	if(checkBlackjack(dealer.getHand())) {
		System.out.println("딜러의 BlackJack으로 패배했습니다.");
		System.exit(0);
	}
	while(true) {
		try {
			sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(computeScoreDealer(new ScoreTable(), dealer.getHand())<17){
			dealer.addCard(cp.drawCard());
			System.out.println("딜러는 "+dealer.get(dealer.getHand().size()-1)+"를 받았습니다.\n");
			
			if(checkBlackjack(dealer.getHand())) {
				System.out.println("딜러의 BlackJack으로 패배했습니다.");
				System.exit(0);
			}
			if(is_bust(computeScoreDealer(new ScoreTable(), dealer.getHand()))) {
				System.out.println("딜러의 Bust로 승리했습니다.");
				System.exit(0);
			}
			
		}
		else {
			if(is_bust(computeScoreDealer(new ScoreTable(), dealer.getHand()))) {
				System.out.println("딜러의 Bust로 승리했습니다.\n");
				System.exit(0);
			}
			else {
				break;
			}	
		}
		
}
	System.out.println("플레이어 : "+ computeScoreUser(new ScoreTable(), player.getHand()) +", 딜러 : "+computeScoreDealer(new ScoreTable(), dealer.getHand())+"\n");
	if(computeScoreUser(new ScoreTable(), player.getHand())==computeScoreDealer(new ScoreTable(), dealer.getHand())) {
		System.out.println("동점으로 무승부입니다.");
		System.exit(0);
	}
	else if (21-computeScoreUser(new ScoreTable(), player.getHand())>21-computeScoreDealer(new ScoreTable(), dealer.getHand())) {
		System.out.println("딜러의 승리입니다.");
		System.exit(0);
	}
	else if (21-computeScoreUser(new ScoreTable(), player.getHand())<21-computeScoreDealer(new ScoreTable(), dealer.getHand())) {
		System.out.println("당신의 승리입니다.");
		System.exit(0);
	}
// (M)
//메인 실행 부분
}
}
}
