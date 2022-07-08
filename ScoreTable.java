import java.util.HashMap;

public class ScoreTable {
private HashMap<String, int[]> table = new HashMap<>();

 ScoreTable(){
 // (F)
 // key=랭크: value=점수 쌍의 테이블을 생성한다.
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
 // (G)
 //card 의 점수를 리턴한다. A의 경우 1 또는 11이며, 이 경우에
//해당 점수들로 구성된 length가 2인 정수배열이 리턴된다.
	else {
		return null;
	}
}
}
