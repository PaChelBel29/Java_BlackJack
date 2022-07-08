# Java_BlackJack

##### 개요

방학동안 진행할 첫번째 프로젝트 
담당 교수님께서 주어주신 과제, 블랙 잭이라고 하는 카드 게임을 Java 프로그램으로 변환해서 구현한다.

2022.06.27-2022.07.06
##### 목표

카드게임 BlackJack을 Java로 구현한다.

****

첫 단계는 Black Jack 카드게임을 이해해봐야 한다. 

교수님이 주신 자료는 너무 이해하기 힘들었다.
**********

● 조커 카드를 제외한 트럼프 카드를 사용한다.

● J,Q,K는 10점으로 판정한다.

● A는 1 또는 11로 한다.
2부터 10까지는 각 숫자 대응으로 한다.


● 유저와 딜러는 시작 카드 2장을 받고, 딜러는 1장을 유저에게 공개한다. 시작
카드를 받고 나면 진행 순서는 유저 -> 딜러 순으로 단 한 번씩만 진행

● Hit: 카드를 한 장 더 받는다. 원하는 만큼 Hit를 하고 나면 Stand 해야 한다.

● Stand: 더 이상 카드를 받지 않는다.

● Bust: 가지고 있는 카드 점수의 총합이 21점을 초과할 시 패

●  BlackJack-유저: 시작 시 받은 카드 2장 중에 1장이 A, 나머지 1장이 JQK
중 하나일 경우, BlackJack이며, 게임에서 승리한다.

●  Black 딜러: 시작 시 공개한 카드 1장과 자신의 차례에 공개되는 1장의 카드
가 BlackJack 일 시 승리한다.

●  ACE의 점수 계산: 유저는 새로운 카드를 받을 때마다 패에 랭크가 ACE
인 카드가 있다면, 각각 ACE 카드가 계산될 점수 1점과 11점 중 하나로 선
택한다.

(딜러도 유저와 같은 점수 계산법을 사용하도록 했다.)

● 유저는 자신의 차례에 원하는 만큼 Hit 한 후 Stand 한다.

● 딜러는 자신의 차례에 16점 이하일 시 Hit, 17점 이상일 때 Stand 한다.

#

게임 시나리오
  1. 유저가 몇 개의 덱을 사용할 것인지 정하고 카드를 섞는다.
  2. 각자 카드를 2장씩 나눠 갖고, 컴퓨터 딜러는 한 장을 공개한 후 유저의 차례가
  된다. 유저의 카드를 화면에 표시하고, 세부 규칙의 BlackJack을 검사한다.
  3. 유저는 Hit를 원하는 만큼 입력하고, 유저는 카드를 받을 때마다 화면에서
  자신의 카드를 확인할 수 있다. Stand 로 자기 차례를 마무리한다.
  4. 딜러는 자신의 차례에 나머지 한 장을 공개한다. 이때 세부 규칙의 BlackJack
  을 검사한다. 딜러는 17점 이상일 때까지 3초 간격으로 Hit 하다 Stand 한다.
  5. 중간 과정에서 Bust 된 쪽은 게임에서 패배한다.
  6. 딜러와 유저 모두 Stand 상태일 때는 점수를 비교하여 승패를 결정한다. 점
  수가 같다면 무승부 처리한다.

메인 함수의 기초가 된다.

# 
기초적인 프로젝트의 소스코드 구성은 교수님이 제공해 주셨다.

Card, Player, CardPool, ScoreTable, BlackJack 5종류의 파일을 제공 받았다.
#

``` Java
Card.java
public class Card {
  private String rank, suit;

  Card(String suit, String rank) {
    this.suit = suit;
    this.rank = rank;
  }

 public String getSuit(){return suit;}
 public String getRank(){return rank;}
 public String toString(){
 return String.format("[%s:%s]", suit, rank)
```
#
그냥 사용하면 된다

#

``` Java
Player.java
  public class Player{
  private Vector<Card> hand = new Vector<Card>();

  public void addCard(Card card) {
  //(A)
  // 카드를 추가한다.
  }
  public Card get(int item) {
  // (B)
  // item 번째 카드를 리턴한다.
  }
  public Vector<Card> getHand() {
  // (C)
  // 가지고 있는 패를 모두 리턴한다.
  }
}
```
#
#### (A)

AddCard 는 Card 형태를 Vector인 Hand에 add 한다.

```Java
public void addCard(Card card) {
  hand.add(card);
  }
```
#### (B)

get은 Hand라고 하는 vector의 item번째의 Card를 반환한다.


```Java
public Card get(int item) {
	return hand.elementAt(item);
 }
```

#### (C)

getHand는 패를 전부 반환한다. Vector 형태로 반환한다.

```Java
 public Vector<Card> getHand() {
	return hand;
 }
```

#

```Java
CardPool.java
public class CardPool {
  private Stack<Card> cards = new Stack<Card>();

  CardPool(int num_decks){
  // (D)
  // num_decks 개의 덱을 생성하고 셔플 한 후 추가한다.
  }
  public Card drawCard(){
  // (E)
  // 카드들로부터 카드를 한장 뽑는다.
  }
}
```
#

### (D)

num_decks 만큼 덱을 추가한다. A~K까지 4개의 모양이 있으니 52개가 1세트다.
ArrayList로 모양과 계급의 기본적인 정보를 가진다.
그리고 또 다른 ArrayList로 잘 정렬된 덱을 num_decks 갯수만큼 생성한다.

이제 Stack 으로 되어있는 Cards에 push하고 arraylist에 있던 Card를 제거한다.

```Java
 CardPool(int num_decks){
	 ArrayList<String> suits_list = new ArrayList<>(Arrays.asList("Spade","Diamond","Heart","Clover"));
	 ArrayList<String> rank_list = new ArrayList<>(Arrays.asList("A","2","3","4","5","6","7","8","9","10","J","Q","K"));
	 ArrayList<Card> deck = new ArrayList<>();
	 for(int i=0;i<num_decks;i++) {
		 for(int s=0;s<4;s++) {
			 for(int r=0;r<13;r++) {
				 deck.add(new Card(suits_list.get(s),rank_list.get(r)));
			 }
		 }
	 }
	 for(int i = 0 ; i<52*num_decks;i++) {
		 int rand=(int) (Math.random()*deck.size());
		 cards.push(deck.get(rand));
		 deck.remove(rand);
	 }
 }
```
#

### (E)

단순히 카드를 한장 뽑는다. Stack의 pop기능을 사용한다.

```Java
public Card drawCard(){
	return cards.pop();
  }

```

#

```Java
ScoreTable.java
public class ScoreTable {
  private HashMap<String, int[]> table = new HashMap<>();

  ScoreTable(){
  // (F)
  // key=랭크: value=점수 쌍의 테이블을 생성한다.
  }
  public int[] score(Card card) {
  // (G)
  //card 의 점수를 리턴한다. A의 경우 1 또는 11이며, 이 경우에
  해당 점수들로 구성된 length가 2인 정수배열이 리턴된다.
  }
}
```

#
### (F)

Hashmap이라는 컬렉션을 처음 마주했다.
HashMap은 key와 value를 가지고, key를 입력하면 value 값을 리턴한다.

```Java
 ScoreTable(){
	table.put("A",score(new Card("","A")));
	table.put("2",score(new Card("","2")));
	table.put("3",score(new Card("","3")));
	table.put("4",score(new Card("","4")));
	table.put("5",score(new Card("","5")));
	table.put("6",score(new Card("","6")));
	table.put("7",score(new Card("","7")));
	table.put("8",score(new Card("","8")));
	table.put("9",score(new Card("","9")));
	table.put("10",score(new Card("","10")));
	table.put("J",score(new Card("","J")));
	table.put("Q",score(new Card("","Q")));
	table.put("K",score(new Card("","K")));
 }
```

#
### (G)

A는 1 또는 11을 반환해서 int형태로 된 크기 2를 가진 list를 반환해야 한다.

```Java
 public int[] score(Card card) {
	if(card.getRank().equals("A")){
		int i[]= {1,11};
		return i;
	}
	else if(card.getRank().equals("2")){
		int i[]= {2};
		return i;
	}
	else if(card.getRank().equals("3")){
		int i[]= {3};
		return i;
	}
	else if(card.getRank().equals("4")){
		int i[]= {4};
		return i;
	}
	else if(card.getRank().equals("5")){
		int i[]= {5};
		return i;
	}
	else if(card.getRank().equals("6")){
		int i[]= {6};
		return i;
	}
	else if(card.getRank().equals("7")){
		int i[]= {7};
		return i;
	}
	else if(card.getRank().equals("8")){
		int i[]= {8};
		return i;
	}
	else if(card.getRank().equals("9")){
		int i[]= {9};
		return i;
	}
	else if(card.getRank().equals("10")){
		int i[]= {10};
		return i;
	}
	else if(card.getRank().equals("J")){
		int i[]= {1,11};
		return i;
	}
	else if(card.getRank().equals("Q")){
		int i[]= {10};
		return i;
	}
	else if(card.getRank().equals("K")){
		int i[]= {10};
		return i;
	}
	else {
		return null;
	}
}
}
```

#

```Java
BlackJack.java
public class BlackJack {
  static final Scanner scanner = new Scanner(System.in);
  public static int computeScoreUser(ScoreTable table, Vector<Card> cards) {
  // (H)
  //table을 이용해 유저의 card 들의 점수 총합을 리턴. 키보드 입력을 받아 ACE의 점수를 결정하는 것 또한 수행한다.
  }
  public static int computeScoreDealer(ScoreTable table, Vector<Card> cards) {
  // (I)
  //table을 이용해 딜러의 card 들의 점수 총합을 리턴. ACE의 점수 계산은 세부규칙을 참고.
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
```

# 가장 오래걸린 부분으로 A를 판단하는 방법을 고민하느라 오래 걸린 것 같다.

#
### (H)
A를 제외한 모든 점수를 일단 계산하고, A가 있다면 ace라는 int 정수를 증가시킨다.


A를 입력 받았을 때의 특수 케이스를 보겠다
  i) ace=1
    - 점수와 11을 더했을 때 21을 넘는다면, A의 점수는 1이 된다.
    - 점수와 11을 더했을 때 21이하라고 하면, A의 점수는 11이 된다.
  ii) ace=2
    A가 두개 다 11이면 22가 넘는다. 그러므로 ace=2 케이스에서는 최대 11+1 = 12 인 크기로 판단이 가능하다는 것을 알 수 있다.
      - 점수와 12를 더했을 때 21을 넘는다면 A의 점수는 1이 된다.
      - 점수와 12를 더했을 때 21이하라고 하면 A의 점수는 1인 점수 하나와 11 하나가 된다.
  
  ...
  
  (사실 덱의 갯수를 증가시키면 11+1*10의 경우의 수 까지 작성해야 한다. 그러나 일반적으로 4개 이상을 받기 어렵기 때문에 ace가 4개인 케이스까지만 구현했다.)
  
```Java
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
```

#
### (I)

원래 딜러는 3개의 패 이상을 받았을 때 A를 1로 받아야 하지만 다른 블랙잭에서는 그런 룰이 없어 과감히 없에버렸다.

```Java
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
}
```

#
### (J)

21초과면 참, 이하면 거짓인 bool 함수

```Java
public static boolean is_bust(int score) {
	if(score>21) {
		return true;
	}
	else {
	return false;
	}
}
```

#
### (K)

A와 J, Q, K가 같이 있을 때 BlackJack으로 특수 승리 할 수 있는지 판단한다.
a와 J, Q, K가 하나씩만 있을 때 참, 아니라면 거짓으로 반환한다.

```Java
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
}
```

#
### (L)

그냥 대기함수.

```Java
public static void sleep(int time) throws InterruptedException {
	Thread.sleep(time);
}
```

#
### (M)

... 설명하기 너무 복잡하다. 게임의 진행 방법에 따라 적절히 구현했다. 그리고 가장 오래 걸렸다.

```Java
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
}
}
```

****
결론적으로 프로젝트를 하나 끝냈다.
교수님께 해당 프로그램을 작성해서 보냈고 피드백을 받았다.

```Text
과제 진행 수고많았고
아래 간단히 리뷰어 코멘트 보내니 참고바랍니다.

=====================
1. 코드 간결성
코드 식별에 큰 문제는 없습니다.
1) 다중 if 문의 과도한 사용 : 개인적 의견으로는 3항 이상의 if 문에는 switch 문의 사용을 강력히 권장합니다.

2. 코드 구현
확인된 문제는 없습니다.
기초적인 Blackjact 규칙은 구현되어 있습니다 다만, split이나, 그외 다른 세부 규칙은 적용되지 않았습니다.
```

#
교수님께서는 switch 문을 사용하라고 하셨다. 
물론 내가 if문을 너무 좋아한다는 사실은 부정할 수 없다. 

그렇기에 switch를 사용하는 방법에 대해서 고민해야겠다.

spilt 이라고 하는 것은 무슨 규칙인지 알아봐야겠다.



