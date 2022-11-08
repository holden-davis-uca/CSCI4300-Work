package RockPaperScissors;

public class RPSGame2 {
	public char[][] rounds;
	public char[] round1;
	public char[] round2;
	public char[] round3;
	public char p1move1;
	public char p1move2;
	public char p1move3;
	public char p2move1;
	public char p2move2;
	public char p2move3;
	public int p1wins;
	public int p2wins;
	public int finalwinner;
	public RPSPlayer p1;
	public RPSPlayer p2;

	public RPSGame2() {
		round1[0] = p1move1;
		round1[1] = p2move1;
		rounds[0] = round1;
		round2[0] = p1move2;
		round2[1] = p2move2;
		rounds[1] = round2;
		round3[0] = p1move3;
		round3[1] = p2move3;
		rounds[2] = round3;
	}
	
	//0 if first move won, 1 if second move won, -1 if draw
	public int getWinner(char move1, char move2) {
		if (move1 == 'R')
		{
			if (move2 == 'R')
				return -1;
			else if (move2 == 'S')
				return 0;
			else
				return 1;
		}
		else if (move1 == 'P')
		{
			if (move2 == 'P')
				return -1;
			else if (move2 == 'R')
				return 0;
			else
				return 1;
		}
		else
		{
			if (move2 == 'S')
				return -1;
			else if (move2 == 'R')
				return 0;
			else
				return 1;
		}
	}
	
	//Set p1 and p2 wins respectively based on all rounds
	public int getFinalWinner()
	{
		for (int i = 0; i < rounds.length; i++) {
			if (getWinner(rounds[i][0], rounds[i][1]) == 0)
				p1wins++;
			else if (getWinner(rounds[i][0], rounds[i][1]) == 1)
				p2wins++;
		}
		if (p1wins > p2wins)
			finalwinner = 1;
		else finalwinner = 2;
		return finalwinner;
	}
}
