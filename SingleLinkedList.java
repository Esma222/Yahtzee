

public class SingleLinkedList {
    Node head;
    
    public void sortedAdd(Player player) {//büyükten küçüðe sýralayarak ekleme yapar.
    	if(head==null) {
			Node newNode= new Node(player.getScore());
			head=newNode;
		}
    	else if((int)player.getScore() > (int)head.getData()) {
    		Node newNode= new Node(player.getScore());
    		newNode.setLink(head);
			head=newNode;
    	}
    	else if((int)player.getScore()==(int)head.getData()) {
    		Node temp=head;
    		Node previous=null;
			while(temp!=null &&(int) player.getScore()<=(int)temp.getData()) {
				previous=temp;
				temp=temp.getLink();
			}
			if(temp==null) {
				Node newNode= new Node(player.getScore());
				previous.setLink(newNode);
				}
			else {
				Node newNode= new Node(player.getScore());
					newNode.setLink(temp);
					previous.setLink(newNode);
				}
    	}
		else {
			Node temp=head;
			Node previous=null;
		
		
			while(temp!=null && (int)player.getScore()<=(int)temp.getData()) {
				previous=temp;
				temp=temp.getLink();
			}
			if(temp==null) {
				Node newNode= new Node(player.getScore());
			    previous.setLink(newNode);
			}
			else {
				Node newNode= new Node(player.getScore());
				newNode.setLink(temp);
				previous.setLink(newNode);
			}
		}
    }
    
    
    
	public void add(Object data) {//ekleme methodu
		if(head==null) {
			Node newNode= new Node(data);
			head=newNode;
		}
		else {
			Node temp=head;
			while(temp.getLink()!=null) {
				temp=temp.getLink();
			}
			Node newNode=new Node(data);
			temp.setLink(newNode);
			
		}
	}
	

	
	public Object nameScore(int score, Object nameOrScore ) {//Data sý Player olan list te 
		//Player ýn score u gelen score a eþit olursa Player silinir,
		//nameOrScore deðiþkenine göre  ya isim ve score döndürülür ,ya da sadece isim döndürülür.
		Object name=" ";
		boolean flag=false;
		if(head==null) {
			System.out.println("Linked list is empty.");
			return "empty";
		}else {
			Node temp=head;
			
			while(temp!=null && !flag) {
				Player p =(Player)temp.getData();
				if(p.getScore()==score) {
					name=p.getName();
					remove(temp.getData());
					flag=true;
					if(nameOrScore=="nameAndScore") {
						return name+" "+score;
					}
					else if(nameOrScore=="name") {
						return name;
					}
					
				}
				temp=temp.getLink();
				
			}
			return "empty";
		}
	}
	
	

	public int size() {
		if(head==null) {
			return 0;
		}
		else {
			int count=0;
			Node temp=head;
			while (temp!=null) {
				temp=temp.getLink();
				count++;
			}
			return count;
		}
	}
	public void display() {
		if(head==null) {
			System.out.println("List is empty.");
			
		}else {
			Node temp=head;
			while(temp!=null) {
				System.out.print(temp.getData()+"  ");
				temp=temp.getLink();
			}
		}
	}
	
	public void displayHighScoreTable() {//High score tabloya giren ilk  10 player ý ekrana bastýrýr.
		if(head==null) {
			System.out.println("List is empty.");
			
		}else {
			int counter=0;
			Node temp=head;
			while(temp!=null && counter<10) {
				System.out.println(temp.getData()+"  ");
				temp=temp.getLink();
				counter++;
			}
		}
	}
	
	public void remove(Object dataToDelete) {
		
		if(head==null) {
			System.out.println("Linked list is empty.");
		}else {
			boolean flag=false;
			while(!flag && head.getData()==dataToDelete)
				{head=head.getLink();
				flag=true;}
			
			Node temp=head;
			Node previous=null;
			while(!flag && temp!=null ) {
			if(temp.getData()==dataToDelete) {
				previous.setLink(temp.getLink());
				temp=previous;
				flag=true;
			}if(flag) {break;}
			previous=temp;
			temp=temp.getLink();
		  }
			
		}
	}
	
	
	public boolean search(Object data) {
		if(head==null) {
			System.out.println("Linked list is empty.");
			return false;
		}else {
			Node temp=head;
			while(temp!=null) {
				if((int)temp.getData()==(int)data) {
					return true;
				}
				temp=temp.getLink();
			}
			return false;
		}
	}
	public int count4(int number, boolean flag) {// Score döndürür, yahtzee durumunu kontrol eder 
		int counterSameNum=0;
		int addScore=0;
		Node temp=head;
		int min=4;
		if(head==null) {
			System.out.println("Linked list is empty.");
			return addScore;
		}else {
		while(temp!=null) {//kaç tane bulunduðuna bakar
			if((int)temp.getData()==number) {
				counterSameNum++;
			}
			temp=temp.getLink();
		}
		if(flag) {min=3;}//Large straight gerçekleþtiyse sayýnýn 1 kez silindiði göz önünde bulundurulur
		if(counterSameNum>=min) {
			addScore=10;
			for(int i=0;i<min;i++)
			{remove(number);}
			return addScore;
		}
		else {
			return addScore;
		}
	  }
	}
	
}

