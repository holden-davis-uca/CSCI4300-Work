# Holden Davis
# CSCI 4300 - CRN 22807
# Dr. Sun - Fall 2022 - Final Project
# RPSClientNetwork.py

# Socket is Python's low-level socket library; this is used for elementary communication between client and server
import socket
# Pickle is Python's standard library for object serialization to and from bytes for sending and receiving data
import pickle


# <!--Class for the networking that an instance of the game client will require to communicate with the server--!>
# > Contains connection objects and methods to interface with server
class RPSClientNetwork:
    # Class constructor, requires ip and port of server
    def __init__(self, ip: str, port: int):
        self.client = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.server = ip
        self.port = int(port)
        self.addr = (self.server, self.port)
        self.p = self.connect()

    # Getter for the player number assigned by the server
    def getP(self) -> str:
        return self.p

    # Attempt to connect to the server, receiving and returning an encoded object from the server
    def connect(self) -> any:
        try:
            self.client.connect(self.addr)
            return self.client.recv(2048).decode()
        except socket.error as e:
            print(e)

    # Send data to the server and receive a response
    def send(self, data: any) -> any:
        try:
            self.client.send(str.encode(data))
            # No point in attempting to receive data if client has disconnected
            if "quit" not in data:
                return pickle.loads(self.client.recv(2048 * 2))
        except socket.error as e:
            print(e)
