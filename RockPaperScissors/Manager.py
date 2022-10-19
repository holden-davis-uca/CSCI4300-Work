import socket


def run():
    print("STARTING MANAGER")
    port = input("Enter port: ")
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.bind(("127.0.0.1", int(port)))
    s.listen()
    print("Listening on", port, "...")
    connection, client = s.accept()
    print(client, "connected!")
    data = connection.recv(1024)
    print("Received", data)
    print("STOPPING MANAGER")


if __name__ == '__main__':
    run()
