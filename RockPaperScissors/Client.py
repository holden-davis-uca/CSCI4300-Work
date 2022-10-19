import socket


def run():
    print("STARTING CLIENT")
    ip = input("Enter address: ")
    port = input("Enter port: ")
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.connect((ip, int(port)))
    data = b"TEST"
    print("Connected, sending", data)
    s.sendall(data)
    print("STOPPING CLIENT")


if __name__ == '__main__':
    run()
