# Holden Davis
# CSCI 4300 - CRN 22807
# Dr. Sun - Fall 2022 - Final Project
# RPSServer.py

# Socket is Python's low-level socket library; this is used for elementary communication between client and server
import socket
# Pickle is Python's standard library for object serialization to and from bytes for sending and receiving data
import pickle
# Tkinter is Python's standard elementary GUI library, this is used to display the manager
import tkinter
# _thread is the low-level threading library for Python, this is used for the less intensive threads for each client
from _thread import *
# Threading is a higher-level module that wraps around _thread, this is used for greater control of the main thread
from threading import Thread
# Import the RPSGame object to be instantiated every connection iteration (1 game per two players)
from RPSGame import RPSGame

# Global variables for various purposes, server string for IP, socket s, int and bool for state for tracking states
server: str = ""
s: socket.socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
connected: set = set()
games: dict = {}
idCount: int = 0
running: bool = False
t1: any = None


# This function will be called as its own thread for each player/client; handles serverside player/game interaction
def threaded_client(conn: socket.socket, p: int, game_id: int):
    global idCount
    # Upon connection by the client, send back their player number
    conn.send(str.encode(str(p)))
    # Continuously receive, decode, modify, and resend the game back to client
    while True:
        try:
            # Receive data from RPSClient
            data = conn.recv(4096).decode()
            if game_id in games:
                game = games[game_id]
                if not data:
                    break
                else:
                    # Case 1 - players have accepted rematch, reset the game state
                    if data == "reset":
                        Status.config(text="Rematch!")
                        game.resetWent()
                    # Case 2 - players have declined rematch and quit, set the game state to ended and close each connection
                    elif data == "quit":
                        P0.config(text="Waiting for P0")
                        P1.config(text="Waiting for P1")
                        game.end()
                        conn.close()
                    # Case 3 - no specific behavior, simply play the game
                    elif data != "get":
                        game.play(p, data)
                    conn.sendall(pickle.dumps(game))
            else:
                break
        except error as e:
            print(e)
            break
    Status.config(text="Players Disconnected")
    try:
        del games[game_id]
        Status.config(text="Closing game " + str(game_id))
    except error as e:
        print(e)
        pass
    # Remove the id of the player from use and close the connection, terminating the thread
    idCount -= 1
    conn.close()


# Function called when server start/stop button is pressed; spawns or kills main server thread
def toggleServer():
    global running
    global t1
    if not running:
        # Server is not running, flip the boolean, adjust server gui, and start the main thread
        running = True
        BUTTON.config(text="Stop")
        ENTRY.config(state="readonly")
        root.title("RPS Server - Running")
        t1 = Thread(target=startServer)
        t1.start()
    else:
        # Server is running, flip the boolean, adjust server gui, and join the thread
        running = False
        BUTTON.config(text="Start")
        ENTRY.config(state="normal")
        root.title("RPS Server - Stopped")
        t1.join()


# Called in created main server thread; starts server, handles initial connections, and spawns individual client threads
def startServer():
    try:
        # Start the server
        s.bind((server, int(port.get())))
        Status.config(text="RPS Server started on " + server + "," + port.get())
    except socket.error as e:
        str(e)
    # Listen for 2 connections max
    s.listen(2)
    global idCount
    while True:
        # Accept a connection, increment the count of players to give a unique id, and assign a game id
        conn, addr = s.accept()
        idCount += 1
        p = 0
        game_id = (idCount - 1) // 2
        # If first player, assign number 0
        if idCount % 2 == 1:
            P0.config(text="P0 Connected")
            games[game_id] = RPSGame(game_id)
        # If second player, assign number 1 and set game state to ready (both players are connected, game can begin)
        else:
            P1.config(text="P1 Connected")
            games[game_id].ready = True
            p = 1
        # Start thread for newly connected client
        start_new_thread(threaded_client, (conn, p, game_id))


# 'Quick and dirty' function to get IP of machine to host server on rather than hard coding
def getIP() -> str:
    s2 = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    s2.connect(("8.8.8.8", 80))
    return s2.getsockname()[0]


# Setting up GUI
if __name__ == "__main__":
    server = getIP()
    root = tkinter.Tk()
    root.title("RPS Server - Stopped")
    root.geometry("400x200")
    port = tkinter.StringVar()
    LABEL = tkinter.Label(root, text="Port:")
    ENTRY = tkinter.Entry(root, textvariable=port)
    BUTTON = tkinter.Button(root, text="Start", command=toggleServer)
    P0 = tkinter.Label(root, text="Waiting for P0")
    P1 = tkinter.Label(root, text="Waiting for P1")
    Status = tkinter.Label(root, text="...")
    LABEL.pack()
    ENTRY.pack()
    BUTTON.pack()
    P0.pack()
    P1.pack()
    Status.pack()
    root.mainloop()
