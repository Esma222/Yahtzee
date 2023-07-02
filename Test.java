
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;


public class Test {
	
	public static void game(SingleLinkedList sll, Player player){
	 int r;
   	 for (int i = 0; i < 3; i++) {
           r = (int) (Math.random() * 6 + 1);
           sll.add(r);
       }
   	
   	System.out.print(player.getName()+": ");
   	sll.display();
   	System.out.print("Score: "+player.getScore());
   	System.out.print("\n");
   
	int counter=1;
	int counterSix=0;
	boolean flag=false;//Large Straight ve yahtzee ayn� andaysa kontrol� sa�lar
	
	while(counter<7) {
		if(sll.search(counter)) {
			counterSix++;
		}
		counter++;
	}
	if(counterSix==6) {//Large straight
		player.setScore(player.getScore()+30);
		for(int i=0;i<6;i++)
		{sll.remove(i+1);}
		flag=true;
	}
	 counter=1;
	while(counter<7) {
		if(sll.search(counter)) {
			player.setScore(player.getScore()+sll.count4(counter,flag));//yahtzee or zero
			
		}
		counter++;
	}
	
   }	
	
	public static void main(String[] args) throws InterruptedException, IOException {

		
		Scanner scanner = new Scanner(System.in);
		System.out.print("Please enter first players name:");
		String name1=scanner.nextLine();
		System.out.print("Please enter second players name:");
		String name2=scanner.nextLine();
		Player player1=new Player(name1,0);
		Player player2=	new Player(name2,0);
		
		SingleLinkedList sll1 = new SingleLinkedList();//Player1 in zar de�erlerini tutar
		SingleLinkedList sll2 = new SingleLinkedList();//Player2 nin zar de�erlerini tutar
		SingleLinkedList sllName = new SingleLinkedList();//isimler s�ral� tutulur
		SingleLinkedList sllScore = new SingleLinkedList();//score lar s�ral� tutulur.
		SingleLinkedList sllPlayer = new SingleLinkedList();//playerlar s�ras�z tutulur
		SingleLinkedList sllPlayer2 = new SingleLinkedList();//playerlar s�ras�z tutulur
		SingleLinkedList sllSortedNameAndScore = new SingleLinkedList();//S�ral� olarak isim ve score lar� tutar
		SingleLinkedList sllSorted = new SingleLinkedList();//S�ral� olarak score lar� tutar
		
		int turnCounter=1;
		
        while(turnCounter<11) {
        	System.out.println("Turn: "+turnCounter);
        	game( sll1, player1);
        	game( sll2,player2);
		    turnCounter++;
		    System.out.println();
      }
    	//winner �n belirlenmesi durumu
        System.out.println("\nGame is over.\n");
        Object winnerName=" ";
        Object winnerScore=0;
        if(player1.getScore()>player2.getScore()) {
        	System.out.println("The winner is "+name1);
        	winnerName=player1.getName();
        	winnerScore=player1.getScore();
        }else if(player1.getScore()==player2.getScore()) {
        	System.out.println("tie");
        	winnerName=player1.getName();
        	winnerScore=player1.getScore();
        }else {
        	System.out.println("The winner is "+name2);
        	winnerName=player2.getName();
        	winnerScore=player2.getScore();
        }
        
        //ilk olarak kazanan text e eklenir.
        //textteki t�m playerlar okunup list e eklenir
        //textin tamam� silinip  ilk 10 yazd�r�l�r.
        
        FileWriter fileWriter = new FileWriter("HighScoreTable.txt" , true);
        fileWriter.write((String)winnerName);
        fileWriter.write("\n"+winnerScore);
        fileWriter.close();
        
        //text okunur
        File highScoreTableFile = new File("HighScoreTable.txt");
        Scanner lineReader = new Scanner(highScoreTableFile);  
        int lineCounter = 1;
        Object name=" ";
        while (lineReader.hasNextLine()) {
              if(lineCounter%2==1) {
            	 name=lineReader.nextLine();  
              }
              else {
            	  Player temp= new Player(name,Integer.parseInt(lineReader.nextLine()));
            	  sllSorted.sortedAdd(temp);//sortedAdd methodu scorelar� s�ral� olarak sllSorted a ekler
            	  sllPlayer.add(temp);//i�inde playerlar name ve score olarak obje �eklinde
            	  sllPlayer2.add(temp);
              }
        	lineCounter++;
        }
        lineReader.close();
        
       
        //isimler ve skorlar�n birlikte sortlanm�� hali sllSortedNameAndScore a ekleniyor.
        //en ba�taki skora e�it isimler s�rayla bulunup sllPlayer dan silinir.�sim ve skor olarak sllSortedNameAndScore e eklenir
        //sonra her d�ng�de en  ba�taki skor sllSorted tan silinir
        while(sllSorted.size()>0) {
        	sllSortedNameAndScore.add(sllPlayer.nameScore((int)sllSorted.head.getData(),"nameAndScore"));
        	sllName.add(sllPlayer2.nameScore((int)sllSorted.head.getData(),"name"));//highScoreTable daki s�ral� isimler
      	    sllScore.add((int)sllSorted.head.getData());//highScoreTable daki s�ral� score lar
        	sllSorted.remove(sllSorted.head.data);
       }
       int counter=0;
       FileWriter fileWriter2 = new FileWriter("HighScoreTable.txt" ,false);//text in i�indekiler silindi
       fileWriter2.close();
       FileWriter fileWriter3 = new FileWriter("HighScoreTable.txt" ,true);
       while(sllName.size()>1 && counter<10) {//s�ral� olarak ilk 10 player isim ve skor �eklinde eklenir.
    	   fileWriter3.write(sllName.head.getData()+"\n") ;
    	   sllName.remove(sllName.head.getData());
           fileWriter3.write(sllScore.head.getData()+"\n") ;
      	   sllScore.remove(sllScore.head.getData());
      	   counter++;
       }
      
       fileWriter3.close();
       
        System.out.println("\nHigh Score Table\n");
        sllSortedNameAndScore.displayHighScoreTable();
    
	}
}

