public class Card {
 private String rank, suit;

 Card(String suit, String rank) {
 this.suit = suit;
 this.rank = rank;
 }

 public String getSuit(){return suit;}
 public String getRank(){return rank;}
 public String toString(){
 return String.format("[%s:%s]", suit, rank);
 }
}
