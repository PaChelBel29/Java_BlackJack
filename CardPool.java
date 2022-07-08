import java.util.*;

public class CardPool {
private Stack<Card> cards = new Stack<Card>();

 CardPool(int num_decks){
	 ArrayList<String> suits_list = new ArrayList<>(Arrays.asList("Spade","Diamond","Heart","Clover"));
	 ArrayList<String> rank_list = new ArrayList<>(Arrays.asList("A","2","3","4","5","6","7","8","9","10","J","Q","K"));
 // (D)
 // num_decks 개의 덱을 생성하고 셔플 한 후 추가한다.
	 ArrayList<Card> deck = new ArrayList<>();
	 for(int i=0;i<num_decks;i++) {
		 for(int s=0;s<4;s++) {
			 for(int r=0;r<13;r++) {
				 //cards.push(new Card(suits_list.get(s),rank_list.get(r)));
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
 public Card drawCard(){
	return cards.pop();
 // (E)
 // 카드들로부터 카드를 한장 뽑는다.
 }
}
