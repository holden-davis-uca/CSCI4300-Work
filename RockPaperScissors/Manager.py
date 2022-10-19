import socket
import tkinter as tk


def getip():
    dummy = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    dummy.connect(("8.8.8.8", 80))
    return dummy.getsockname()[0]


def run():
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.bind((getip(), 6969))
    s.listen()
    print("Listening on 6969...")
    connection, client = s.accept()
    print(client, "connected!")
    data = connection.recv(1024)
    print("Received", data)
    # print("STARTING MANAGER")
    # port = input("Enter port: ")
    # s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    # s.bind((getip(), int(port)))
    # s.listen()
    # print("Listening on", port, "...")
    # connection, client = s.accept()
    # print(client, "connected!")
    # data = connection.recv(1024)
    # print("Received", data)
    # print("STOPPING MANAGER")


if __name__ == '__main__':
    root = tk.Tk()
    here = tk.Button(text="lmao", command=run)
    here.pack()
    root.mainloop()
