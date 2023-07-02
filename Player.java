

public class Player {
   private Object name;
   private int score;
   
   public Player(Object name, int score) {
	   this.name=name;
	   this.score=score;
	   
   }

public Object getName() {
	return name;
}

public int getScore() {
	return score;
}

public void setScore(int score) {
	this.score = score;
}

public void addScore(int score) {
    this.score += score;
}
   
}

