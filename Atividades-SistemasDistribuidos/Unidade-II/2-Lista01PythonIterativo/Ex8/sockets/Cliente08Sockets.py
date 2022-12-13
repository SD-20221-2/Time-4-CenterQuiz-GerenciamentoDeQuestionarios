import socket
import pickle
import traceback

class Cliente05Sockets:
    try:
        server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        server.connect(("localhost", 8080))

        nome = input("Digite o nome: ")
        saldo = input("Digite o saldo: ")

        data = [float(saldo)]
        data_string = pickle.dumps(data)

        server.send(data_string)

        received_data = server.recv(1024)
        received_data = received_data.decode()

        print("Olá ", nome)
        print("\n", received_data)

    except:
        print("Ocorreu uma exceção")
        traceback.print_exc()