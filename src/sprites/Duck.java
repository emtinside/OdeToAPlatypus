package sprites;

import java.util.Scanner;

import displays.Display;

public class Duck extends Sprite{
	
	private boolean endOfGame;
	private Scanner scanner;
	public static final int START_TEXT =0;
	public static final String[] START_TEXT_ARRAY = {"Hey there Platy!",
			"Let's talk about the goals of this game","First of all, I'll be talking to you through console",
			"Sorry if it's a little janky... it's too much work to code an actual GUI :(",
			"That aside, you're going to have press the \"T\" key to talk to me if you're touching me",
			"Your goal is to collect all those pieces of memories laying out and about",
			"Seems easy right?", "All you gotta do it run into the memory and BOOM!", "You collected it :)",
			"Later you'll need to decipher the messages sent by each memory",
			"You can check on your collected memories by pressing the \"I\" key, opening your inventory",
			"At the end, come back to me with all your memories you've deciphered",
			"And maybe you'll receive something in return!","Good luck!"};

	public static final int MISSING_EVIDENCE_TEXT =1;
	public static final String[] MISSING_EVIDENCE_TEXT_ARRAY= {"Hmmm...", "Is it just me or are you missing memories?", 
			"I understand we have nanobytes for brains...","But let's try to collect all the memories"};
	public static final int ALL_EVIDENCE_FOUND_TEXT =2;
	public static final String[] ALL_EVIDENCE_FOUND_TEXT_ARRAY= {"Hey, good job!","You collected all the memories",
			"Did you happen to understand what they all mean though?", "Please enter the unknowns for each of the following items, in order, separated by commas:" ,"A Golden Crown, A Letter, A CD & Sleeve, A Lovely Photo"};
	public static final String[] WRONG_PASSWORD_TEXT= {"Oh no.. that wasn't right", "Think a little harder"};
	public static final String[] RIGHT_PASSWORD_TEXT= {"Ah... it seems that you got it right",
			"Maybe it wasn't challenging enough?","That being said, I'm giving you more questions"};
	public static final int ALMOST_THERE = 3;
	public static final String[] ALMOST_THERE_TEXT= {"Damn, ok you got it", "I mean I guess it's easy because you also have multiple tries", "Again, coding laziness: idk how many tries you actually took",
			"Okok another question:"};
	public static final int FINAL_QUESTION=4;
	public static final String[] FINAL_QUESTION_TEXT= {" "," "," ","Hey... you were supposed to get tired and quit console :/", 
			"I guess if you actually answered that question correctly...","You probably already know what I'm trying to say",
			"I hope this doesn't make anything weird", "I'm like dying internally writing this oml",
			"See the real question I wanted to bring up in your letter was... well just that", "Not making anything awkward by sending out the wrong message", 
			"Oh something funny, after reading that I perhaps internally screamed", "That's a good thing by the way",
			"Continuing on", "Not even that long ago I think it was the 2 AM highness getting to you",
			"But you mentioned something about the last couple days of COSMOS",
			"Now, the way you phrased it was a little confusing so I kinda...", "Brushed off your message because I didn't want to interpret it wrong",
			"I'd have to say my last few days at COSMOS was the same as you", "I'd like to think I know you better now",
			"I think getting to know you better has only furthered the degree of it","Fuckkkk I've literally never done this I am at a loss", 
			"I'm actually kinda scared you actually got this far because somewhere in me I feel like I made the questions too easy",
			"idk.. is it mutual?",
			" "," "," ",
			"Aarav, you probably already know this","In fact, if the way I've literally typed this up does not give it away, maybe you're as dense as me", 
			"In general terms, the phrasing would be \"I like you as more than just a friend\"",
			"But really, what does that even mean???", "I don't personally know so how I am gonna say it is this:",
			"You're such an amazing friend and individual person, and if I could get to know anyone even better, it would be you",
			"From learning the fundamental idea of neural nets from you", "to sitting in front of Tamarack studying", 
			"to sneaking out and (almost) getting caught by Bianca", "to talking until 4 AM over text","AND to you talking to me during your walk",
			"The way you spam away embarrassing pictures and respond sometimes is really funny","And on many occassions, I've internally screamed because it's honestly really cute",
			"All these are moments I've really cherished",
			"And that's why I made this in the first place, because what better way to reflect on something than to code a long ass program lol",
			"I don't know where to go from here","But I just thought you should know",
			"But yeah, if this has just made things awkward, I guess that's just how it is","Uh, if you do go missing for a while I'll just assume from there, so uh yeah!",
			"Just thanks for being there for as long as now."};
	public static final String[] SHORTER_ADDITION= {"As someone who also conceals emotions", "it's really nice to have a person I don't really have to do that around"};
	public static final String[] WRONG_FINAL_TEXT= {
			"You know something interesting?", "After 1 month post COSMOS, I have to say you are still very much a type of person I strive to be", "The conversations we have never cease to amuse me",
			"And I hope I'm not boring you with my replies","You're extremely hardworking","I mean we all are when deadlines come up (i'm looking at you research paper!!)", "But you're still hardworking regardless of if there's a deadline",
			"It just always seems like there's something you're doing, and that's really admirable", "I'm trying to emulate that in a way, first by busying myself with this for you",
			"That being said, honestly I'm so grateful to have you as a friend","Having you as a positive influence has really helped me get my life gradually back on track",
			"Even if I've only known you for three months", "Wait... three months oh wow that's actually pretty cool","But yeah! Thanks for starting it all by talking to me first :)",
			"Oh and before you exit out", "Because I am saying I don't conceal emotions around you, I'll give you a hint for this so-called game","Your answer to the last question?",
			"I mean, its one of two things","Maybe you'll find something interesting if you know the second thing", "Ok byeeee"};
	
	public Duck(float x, float y, double width, double height, String img) {
		super(x, y, width, height, img);
		scanner = new Scanner(System.in);
		endOfGame = false;
		// TODO Auto-generated constructor stub
	}
	
	public void talk(Display d) throws InterruptedException {
		if (d.getDuckSpeakCount() == START_TEXT) {
			for(String s: START_TEXT_ARRAY) {
				System.out.println(s);
				Thread.sleep((long)(1.5*1000));
			}
		}
		else if (d.getDuckSpeakCount() == MISSING_EVIDENCE_TEXT) {
			for(String s:MISSING_EVIDENCE_TEXT_ARRAY) {
				System.out.println(s);
				Thread.sleep((long)(1.5*1000));
			}
		}
		else if (d.getDuckSpeakCount() == ALL_EVIDENCE_FOUND_TEXT) {
			for(String s: ALL_EVIDENCE_FOUND_TEXT_ARRAY) {
				System.out.println(s);
				Thread.sleep((long)(1.5*1000));
			}
			String password = scanner.nextLine();
			String[] passwordArr = password.split(",");
			
			boolean rightPassword = false;
			if((passwordArr[0].contains("7")||(passwordArr[0].contains("july")||passwordArr[0].contains("July")) && passwordArr[0].contains("30"))) {
				if(passwordArr[1].toLowerCase().contains("ravioli") && !passwordArr[1].toLowerCase().contains("aarav")) {
					if(passwordArr[2].toLowerCase().contains("headlights") && !passwordArr[2].toLowerCase().contains("on") && !passwordArr[2].toLowerCase().contains("me")){
						if(passwordArr[3].toLowerCase().contains("the") && passwordArr[3].toLowerCase().contains("spot")) {
							for(String s: RIGHT_PASSWORD_TEXT) {
								rightPassword = true;
								System.out.println(s);
								Thread.sleep((long)(1000));
							}
							d.setDuckSpeakCount(ALMOST_THERE);
						}
					}
				}
			}
			if(!rightPassword) {
				for(String s: WRONG_PASSWORD_TEXT) {
					System.out.println(s);
					Thread.sleep((long)(1000));
				}
			}
			if(d.getDuckSpeakCount()== ALMOST_THERE) {
				System.out.print("What's the (subjectively) best FindingHope song? ");
				String findingHopeSong = scanner.nextLine();
				if(findingHopeSong.equalsIgnoreCase("crush on you")) {
					for(String s: ALMOST_THERE_TEXT) {
						System.out.println(s);
						Thread.sleep((long)(1000));
					}
					d.setDuckSpeakCount(FINAL_QUESTION);
				}
				else {
					for(String s: WRONG_PASSWORD_TEXT) {
						System.out.println(s);
						Thread.sleep((long)(1000));
					}
				}
			}
			if(d.getDuckSpeakCount() == FINAL_QUESTION) {
				System.out.println("So the letter object here represents something, right?");
				Thread.sleep((long)(1000));
				System.out.println("With that said, I asked you a question about it");
				Thread.sleep((long)(1000));
				System.out.println("What was that question about?");
				String letterPart = scanner.nextLine();
				letterPart.toLowerCase();
				if(  (letterPart.contains("awkward") || letterPart.contains("weird")) && letterPart.contains("wrong") && (letterPart.contains("message") || letterPart.contains("signals")) ) {
					for(String s: FINAL_QUESTION_TEXT) {
						if(s.length()==0) {
							Thread.sleep((long)2*1000);
						}
						System.out.println(s);
						Thread.sleep((long)(2000));
					}
				}
				else if(letterPart.contains("emotions") && letterPart.contains("conceal")) {
					for(String s:SHORTER_ADDITION) {
						System.out.println(s);
						Thread.sleep((long)(2000));
					}
					for(String s: WRONG_FINAL_TEXT) {
						System.out.println(s);
						Thread.sleep((long)(2000));
					}
				}
				else {
					for(String s: WRONG_PASSWORD_TEXT) {
						System.out.println(s);
						Thread.sleep((long)(1000));
					}
				}
			}
		}
		else if(d.getDuckSpeakCount()== ALMOST_THERE) {
			System.out.print("What's the (subjectively) best FindingHope song? ");
			String findingHopeSong = scanner.nextLine();
			if(findingHopeSong.equalsIgnoreCase("crush on you")) {
				for(String s: ALMOST_THERE_TEXT) {
					System.out.println(s);
					Thread.sleep((long)(1000));
				}
				d.setDuckSpeakCount(FINAL_QUESTION);
			}
			else {
				for(String s: WRONG_PASSWORD_TEXT) {
					System.out.println(s);
					Thread.sleep((long)(1000));
				}
			}
			if(d.getDuckSpeakCount() == FINAL_QUESTION) {
				System.out.println("So the letter object here represents something, right?");
				Thread.sleep((long)(1000));
				System.out.println("With that said, I asked you a question about it");
				Thread.sleep((long)(1000));
				System.out.println("What was that question about?");
				String letterPart = scanner.nextLine();
				letterPart.toLowerCase();
				if(  (letterPart.contains("awkward") || letterPart.contains("weird")) && letterPart.contains("wrong") && (letterPart.contains("message") || letterPart.contains("signals")) ) {
					for(String s: FINAL_QUESTION_TEXT) {
						if(s.length()==0) {
							Thread.sleep((long)2*1000);
						}
						System.out.println(s);
						Thread.sleep((long)(2000));
					}
					endOfGame = true;
				}
				else if(letterPart.contains("emotions") && letterPart.contains("conceal")) {
					for(String s:SHORTER_ADDITION) {
						System.out.println(s);
						Thread.sleep((long)(2000));
					}
					for(String s: WRONG_FINAL_TEXT) {
						System.out.println(s);
						Thread.sleep((long)(2000));
					}
					endOfGame = true;
				}
				else {
					for(String s: WRONG_PASSWORD_TEXT) {
						System.out.println(s);
						Thread.sleep((long)(1000));
					}
				}
				
			}
			
		}
		else if(d.getDuckSpeakCount() == FINAL_QUESTION) {
			System.out.println("So the letter object here represents something, right?");
			Thread.sleep((long)(1000));
			System.out.println("With that said, I asked you a question about it");
			Thread.sleep((long)(1000));
			System.out.println("What was that question about?");
			String letterPart = scanner.nextLine();
			letterPart.toLowerCase();
			if(  (letterPart.contains("awkward") || letterPart.contains("weird")) && letterPart.contains("wrong") && (letterPart.contains("message") || letterPart.contains("signals")) ) {
				for(String s: FINAL_QUESTION_TEXT) {
					if(s.length()==0) {
						Thread.sleep((long)2*1000);
					}
					System.out.println(s);
					Thread.sleep((long)(2000));
				}
				endOfGame = true;
			}
			else if(letterPart.contains("emotions") && letterPart.contains("conceal")) {
				for(String s:SHORTER_ADDITION) {
					System.out.println(s);
					Thread.sleep((long)(2000));
				}
				for(String s: WRONG_FINAL_TEXT) {
					System.out.println(s);
					Thread.sleep((long)(2000));
				}
				endOfGame = true;
			}
			else {
				for(String s: WRONG_PASSWORD_TEXT) {
					System.out.println(s);
					Thread.sleep((long)(1000));
				}
			}
			
		}
		System.out.println();
	}
	
	public boolean getEndOfGameStatus() {
		return endOfGame;
	}
}
