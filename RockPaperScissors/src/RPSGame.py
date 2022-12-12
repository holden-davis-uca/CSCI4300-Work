# Holden Davis
# CSCI 4300 - CRN 22807
# Dr. Sun - Fall 2022 - Final Project
# RPSGame.py

# <!--Class for a specific instance of the RPS game itself--!>
# > Contains information such as the game ID, number of wins and moves, and states of each player's movement
class RPSGame:
    # Class constructor, requires only unique id assigned by server
    def __init__(self, game_id: int):
        self.id: int = game_id
        # Vars for the state of each player (whether they have moved yet or not)
        self.p1Went: bool = False
        self.p2Went: bool = False
        # Ready boolean to indicate that the game is ready to be played (both players are connected)
        # Ended boolean to indicate that the game is over (players not rematching)
        self.ended: bool = False
        self.ready: bool = False
        # List of moves denoted by characters, list of wins denoted by integers, ties denoted by integer
        self.moves: list = [None, None]
        self.wins: list = [0, 0]
        self.ties: int = 0

    # Gets the move of a single player from the list, accessed by array (player 1 = index 0, player 2 =  index 1)
    def get_player_move(self, player: int) -> str:
        return self.moves[player]

    # Actual function called when player moves; sets the move in the list and updates the player's state accordingly
    def play(self, player: int, move: str):
        self.moves[player] = move
        if player == 0:
            self.p1Went = True
        else:
            self.p2Went = True

    # Getter for ready state of game
    def connected(self) -> bool:
        return self.ready

    # Getter for whether both players have moved
    def bothWent(self) -> bool:
        return self.p1Went and self.p2Went

    # Determine the winner of a game; -1 for tie, 0 for player 1, and 1 for player 2
    def winner(self) -> int:
        p1 = self.moves[0].upper()[0]
        p2 = self.moves[1].upper()[0]

        winner = -1
        # Evaluation logic
        if p1 == "R" and p2 == "S":
            winner = 0
        elif p1 == "S" and p2 == "R":
            winner = 1
        elif p1 == "P" and p2 == "R":
            winner = 0
        elif p1 == "R" and p2 == "P":
            winner = 1
        elif p1 == "S" and p2 == "P":
            winner = 0
        elif p1 == "P" and p2 == "S":
            winner = 1

        return winner

    # Reset the game state for a rematch; indicates both players accepted the rematch
    def resetWent(self):
        self.p1Went = False
        self.p2Went = False

    # End the game by setting the states; indicates a player declined the rematch and disconnected
    def end(self):
        self.ended = True
