# Holden Davis
# CSCI 4300 - CRN 22807
# Dr. Sun - Fall 2022 - Final Project
# RPSClient.py

# OS is Python's powerful library for interactions with the operating system
import os
# Sys is a different module with similar abilities
import sys
# Pygame is a Python library for elementary game development; used for easy starting point!
import pygame
# Tkinter is Python's standard elementary GUI library, this is used to display the manager
import tkinter as tk
# Import the RPSGame object to be used client side and to match server's game object for consistency
import RPSGame
# Import the RPSClientNetwork object to be used for communication between client and server
from RPSClientNetwork import RPSClientNetwork

# Global variables for dimensions of client game window
width = 700
height = 700


# Helper function to allow bundling of move images into exe
def resource_path(relative_path: str) -> str:
    try:
        base_path = sys._MEIPASS
    except Exception:
        base_path = os.path.abspath(".")
    return os.path.join(base_path, relative_path)


# Self-contained class for the move button drawn on screen as well as its behavior (Rock/Paper/Scissors)
class RPSMoveButton:
    def __init__(self, text: str, x: int, y: int):
        self.text = text
        # Load correct image based on move name
        if self.text == "Rock":
            self.image = pygame.image.load(resource_path("rock.png"))
        elif self.text == "Paper":
            self.image = pygame.image.load(resource_path("paper.png"))
        else:
            self.image = pygame.image.load(resource_path("scissors.png"))
        self.x = x
        self.y = y
        self.width = 150
        self.height = 100

    # Draws the button on the game screen
    def draw(self, win: pygame.Surface):
        win.blit(self.image, (self.x, self.y))

    # Handles a user clicking on the image (true if clicked on a button, false otherwise)
    def click(self, pos: tuple) -> bool:
        x1 = pos[0]
        y1 = pos[1]
        # Clicked within image
        if self.x <= x1 <= self.x + self.image.get_width() and self.y <= y1 <= self.y + self.image.get_height():
            return True
        # Clicked elsewhere
        else:
            return False


# Updates the game window to match current game state
def redrawWindow(win: pygame.Surface, game: RPSGame.RPSGame, p: int):
    win.fill((128, 128, 128))
    if not (game.connected()):
        # Opponent is not connected, draw waiting status on screen
        font = pygame.font.SysFont("impact regular", 80)
        text = font.render("Waiting for Enemy...", True, (145, 0, 0), True)
        win.blit(text, (width / 2 - text.get_width() / 2, height / 2 - text.get_height() / 2))
    else:
        # Both players are connected - start the actual game screen
        font = pygame.font.SysFont("impact regular", 60)
        text = font.render("Your Move", True, (145, 0, 0))
        win.blit(text, (80, 200))

        text = font.render("Enemy Move", True, (145, 0, 0))
        win.blit(text, (380, 200))

        move1 = game.get_player_move(0)
        move2 = game.get_player_move(1)
        if game.bothWent():
            # Render moves on screen in text form
            text1 = font.render(move1, True, (0, 0, 0))
            text2 = font.render(move2, True, (0, 0, 0))
        else:
            # Logic to draw player movements based on game state
            if game.p1Went and p == 0:
                text1 = font.render(move1, True, (0, 0, 0))
            elif game.p1Went:
                text1 = font.render("Locked In", True, (0, 0, 0))
            else:
                text1 = font.render("Waiting...", True, (0, 0, 0))

            if game.p2Went and p == 1:
                text2 = font.render(move2, True, (0, 0, 0))
            elif game.p2Went:
                text2 = font.render("Locked In", True, (0, 0, 0))
            else:
                text2 = font.render("Waiting...", True, (0, 0, 0))

        # Drawing moves if both players went
        if p == 1:
            win.blit(text2, (100, 350))
            win.blit(text1, (400, 350))
        else:
            win.blit(text1, (100, 350))
            win.blit(text2, (400, 350))

        # Drawing move buttons on screen
        for btn in Buttons:
            btn.draw(win)

    # Mandatory pygame function to update all changes to screen/window
    pygame.display.update()


# List of previously defined button objects, one for each type of move
Buttons: list = [
    RPSMoveButton("Rock", 50, 500),
    RPSMoveButton("Scissors", 250, 500),
    RPSMoveButton("Paper", 450, 500)]


# Main client function; draws window and handles server connection as well as game processing
def main(n: RPSClientNetwork, win: pygame.Surface):

    # Initialization of variables for pygame behavior and player number
    pygame.font.init()
    run = True
    clock = pygame.time.Clock()
    player = int(n.getP())
    print("You are player", player)
    pygame.display.set_caption("Client - Player " + str(player))
    # Core gameplay loop
    while run:
        clock.tick(60)
        try:
            game = n.send("get")
        except:
            break
        if game.bothWent():
            # Both players went, depict winner loser on screen
            redrawWindow(win, game, player)
            pygame.time.delay(500)
            font = pygame.font.SysFont("impact regular", 90)
            if (game.winner() == 1 and player == 1) or (game.winner() == 0 and player == 0):
                text = font.render("VICTORY!", True, (60, 60, 60))
            elif game.winner() == -1:
                text = font.render("TIE!", True, (60, 60, 60))
            else:
                text = font.render("DEFEAT!", True, (60, 60, 60))
            win.blit(text, (width / 2 - text.get_width() / 2, height / 6 - text.get_height() / 2))
            pygame.display.update()
            try:
                # Rematch accepted, close prompt window and reset game state
                def yes():
                    try:
                        root_rematch.destroy()
                        n.send("reset")
                    except:
                        # Other player disconnected - send notification prompt and close game
                        def okay():
                            root_disc.destroy()
                            pygame.quit()
                        root_disc = tk.Tk()
                        root_disc.geometry("300x300")
                        root_disc.resizable(False, False)
                        root_disc.title("Notification - Player " + str(player))
                        status_label_inner = tk.Label(root_disc, text="Opponent Disconnected - Connection closed")
                        ok_button = tk.Button(root_disc, text="Okay", command=okay)
                        ok_button.grid(row=1, column=2)
                        status_label_inner.grid(row=0, column=2)
                        root_disc.mainloop()

                # Rematch declined, close prompt window, notify server and close game
                def no():
                    n.send("quit")
                    root_rematch.destroy()
                    quit()

                # Prompt for rematch
                root_rematch = tk.Tk()
                root_rematch.geometry("300x300")
                root_rematch.resizable(False, False)
                root_rematch.title("Game Over - Player " + str(player))
                status_label = tk.Label(root_rematch, text="Rematch?")
                yes_button = tk.Button(root_rematch, text="Yes", command=yes)
                no_button = tk.Button(root_rematch, text="No", command=no)
                yes_button.grid(row=1, column=1)
                no_button.grid(row=1, column=3)
                status_label.grid(row=0, column=2)
                root_rematch.mainloop()
            except:
                print("Couldn't get game")
                break

        # Pygame event handler for button clicks (on moves) and game endings
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                run = False
                pygame.quit()
            if event.type == pygame.MOUSEBUTTONDOWN:
                pos = pygame.mouse.get_pos()
                for btn in Buttons:
                    if btn.click(pos) and game.connected():
                        if player == 0:
                            if not game.p1Went:
                                n.send(btn.text)
                        else:
                            if not game.p2Went:
                                n.send(btn.text)
        # Out previously made function for updating the screen
        redrawWindow(win, game, player)


# Displays main screen and executes main() function
def menu_screen(n: RPSClientNetwork):
    win = pygame.display.set_mode((width, height))
    pygame.display.set_caption("Client - Player " + str(n.getP()))
    main(n, win)


# Displays pop-up window prompting player to enter connection parameters to connect to server
def connectionPrompt():
    # Function to attempt to connect to server
    def connect():
        try:
            n = RPSClientNetwork(ip.get(), int(port.get()))
            root_conn.destroy()
            menu_screen(n)
        except:
            status_label.config(text="Could not connect!")

    # Setup GUI for connection prompt
    root_conn = tk.Tk()
    root_conn.geometry("300x300")
    root_conn.resizable(False, False)
    root_conn.title("Connect")
    ip = tk.StringVar()
    port = tk.StringVar()
    status_label = tk.Label(root_conn, text="Connect to server:")
    ip_label = tk.Label(root_conn, text="IP Address:")
    ip_field = tk.Entry(root_conn, textvariable=ip)
    port_label = tk.Label(root_conn, text="Port:")
    port_field = tk.Entry(root_conn, textvariable=port)
    connect_button = tk.Button(root_conn, text="Connect", command=connect)
    status_label.grid(row=0, column=1)
    ip_label.grid(row=1, column=0)
    ip_field.grid(row=1, column=1)
    port_label.grid(row=2, column=0)
    port_field.grid(row=2, column=1)
    connect_button.grid(row=3, column=1)
    root_conn.mainloop()


# Start program by prompting for server parameters
connectionPrompt()
