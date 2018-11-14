package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Event {

	public static final long PROGRAMMERS_SIZE = 1000;
	
	private Programmer root;
	
	private Participant head;
	
	private Programmer rootByPos;
	
	private File file;
	
	private int sizePart;
	
	private long timeProgrammer;
	private long timeParticipant;
	
	public Event() {
		this.root = null;
		this.head = null;
		this.file = null;
	}

	
	public File getFile() {
		return file;
	}


	public void setFile(File file) {
		this.file = file;
	}


	public void loadProgrammers() {
		

        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {
        	if(file!=null)
            br = new BufferedReader(new FileReader(file));
        	line = br.readLine();
            int k = 0;
        	while ((line = br.readLine()) != null) {

            	
            	// use comma as separator
                String[] data = line.split(cvsSplitBy);
                String id = data[0];
                String first_name = data[1];
                String last_name = data[2];
                String email = data[3];
                String gender = data[4];
                String avatar = data[5];
                
                
                
                
                
                
                Programmer programmer = new Programmer(id, first_name, last_name, email, gender, avatar,k);
                Programmer programmer1 = new Programmer(id, first_name, last_name, email, gender, avatar,k);
                createTree(programmer, this.root, null,false,false);
                k++;               
                saveByPos(programmer1, this.rootByPos);
                
            }
          selectParticipants(0);
          System.out.println(""+sizePart);
          Participant participant = head;
          int contador = 1;
			while(participant!=null) {
				System.out.println(""+contador +" "+participant.getId());
				participant = participant.getNext();
				contador++;
			}
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

		
		
		
	}
	
	
	public void saveByPos(Programmer programmer, Programmer root) {
		if(rootByPos==null) {
			rootByPos = programmer;
		}else {
			if(programmer!=null) {
				programmer.setRight(rootByPos);
				rootByPos = programmer;
			}
		}
	}
	
	public void saveParticipants(int number, int times, Participant participant) {
		if(this.sizePart < PROGRAMMERS_SIZE ) {
			if(number==1) {
				if(head==null) {
					head = participant;
					this.sizePart++;
					System.out.println(participant.getId());
				}else {
					participant.setNext(head);
					head = participant;
					this.sizePart++;
					System.out.println(participant.getId());
				}
			}
		}
	}
	
	
	public void createTree(Programmer programmer, Programmer root, Programmer previous, boolean right, boolean left) {
		if(this.root == null) {
			this.root = programmer;
		}else {
			if(root==null) {
				if(right) previous.setRight(programmer);
				if(left) previous.setLeft(programmer);
			}else if(root.compareTo(programmer) < 0) {
				previous = root;
				root = root.getRight();
				createTree(programmer, root,previous,true,false);
			}else if(root.compareTo(programmer) > 0) {
				previous = root;
				root = root.getLeft();
				createTree(programmer,root, previous,false,true);
			}
		}
	}
	
	
	public Programmer searchProgrammer(String id, Programmer root) {
		timeProgrammer = System.currentTimeMillis();
		Programmer toReturn = null;
		if(root!=null) {
			if(root.getId().compareToIgnoreCase(id) == 0) {
				toReturn =  root;
			}else {
				if(root.getId().compareToIgnoreCase(id) < 0) {
					root = root.getRight();
					toReturn = searchProgrammer(id, root);
				}else if(root.getId().compareToIgnoreCase(id) > 0) {
					root = root.getLeft();
					toReturn = searchProgrammer(id, root);
				}
			}
		}
		
		timeProgrammer = System.currentTimeMillis() - timeProgrammer;
		
		return toReturn;
	}
	
	
	public int countProgrammers(Programmer root) {
		int size = 1;
		
		if(root.getLeft() != null) {
			size += countProgrammers(root.getLeft());
		}
		
		if(root.getRight() != null) {
			size += countProgrammers(root.getRight());
		}
		
		
		return size;
		
	}
	

	
	public Programmer inOrden(Programmer root, int pos) {
		Programmer temp = null;
		if(root!=null) {
			if(root.getIndex()==pos) {
				temp = root;
			}else {
				root = root.getRight();
				temp = inOrden(root,pos);
			}
		}
		return temp;
	}
	
	
	public void selectParticipants(int sizePart) {
		int sizeProgra = (int)PROGRAMMERS_SIZE;
		if(sizePart<(sizeProgra/2)) {
			int randomPos = (int)(Math.random() * sizeProgra-7) + 1;
			Programmer tempPro = inOrden(this.rootByPos,randomPos);
			if(tempPro!=null) {
				Participant temp = new Participant(tempPro.getId(),tempPro.getFirst_name(),tempPro.getLast_name(),tempPro.getEmail(),tempPro.getGender(),tempPro.getAvatar(),-1);
				loadParticipants(temp);
				selectParticipants(this.sizePart);
			}
		}
		
	}
	
	
	public void loadParticipants(Participant temp) {
		if(head == null) {
			head = temp;
			this.sizePart++;
		}else {
			if(searchParticipant(temp.getId(),this.head) == null) {
				temp.setNext(head);	
				head = temp;
				this.sizePart++;
			}
		}
	}
	
	
	public Participant searchParticipant(String id, Participant head) {
		timeParticipant = System.currentTimeMillis();
		Participant toReturn = null;
		if(head != null) {
			if(head.getId().compareToIgnoreCase(id) == 0) {
				toReturn = head;
			}else {
				head = head.getNext();
				toReturn = searchParticipant(id, head);
			}
		}
		timeParticipant = System.currentTimeMillis() - timeParticipant;
		return toReturn;
	}


	public long getTimeProgrammer() {
		return timeProgrammer;
	}


	public void setTimeProgrammer(long timeProgrammer) {
		this.timeProgrammer = timeProgrammer;
	}


	public long getTimeParticipant() {
		return timeParticipant;
	}


	public void setTimeParticipant(long timeParticipant) {
		this.timeParticipant = timeParticipant;
	}


	public Programmer getRoot() {
		return root;
	}


	public void setRoot(Programmer root) {
		this.root = root;
	}


	public Participant getHead() {
		return head;
	}


	public void setHead(Participant head) {
		this.head = head;
	}


	public int getSizePart() {
		return sizePart;
	}


	public void setSizePart(int sizePart) {
		this.sizePart = sizePart;
	}
	
	
	
}
