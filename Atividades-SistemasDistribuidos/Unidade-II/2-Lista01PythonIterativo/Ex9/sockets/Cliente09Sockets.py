import socket
import pickle
import traceback

class Cliente05Sockets:
    try:
        server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        server.connect(("localhost", 8080))

        valor = input("Digite o valor da carta: ")
        naipe = input("Digite o naipe da carta: ")

        data = [int(valor), int(naipe)]
        data_string = pickle.dumps(data)

        server.send(data_string)

        received_data = server.recv(1024)
        received_data = received_data.decode()

        print("Nome da carta: ", received_data)

    except:
        print("Ocorreu uma exceção")
        traceback.print_exc()