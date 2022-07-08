import java.util.*;

public class Player{
 private Vector<Card> hand = new Vector<Card>();

 public void addCard(Card card) {
//(A)
hand.add(card);
}
 
public Card get(int item) {
	return hand.elementAt(item);
 // (B)
 // item 번째 카드를 리턴한다.
 }
 public Vector<Card> getHand() {
	return hand;
 // (C)
 // 가지고 있는 패를 모두 리턴한다.
 }
}
