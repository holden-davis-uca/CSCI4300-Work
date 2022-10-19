import socket


def run():
    print("STARTING MANAGER")
    dummy = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    dummy.connect(("8.8.8.8", 80))
    ip = dummy.getsockname()[0]
    port = input("Enter port: ")
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.bind((ip, int(port)))
    s.listen()
    print("Listening on", port, "...")
    connection, client = s.accept()
    print(client, "connected!")
    data = connection.recv(1024)
    print("Received", data)
    print("STOPPING MANAGER")


if __name__ == '__main__':
    run()
