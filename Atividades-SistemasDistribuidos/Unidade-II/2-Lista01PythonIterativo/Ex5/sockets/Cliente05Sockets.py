import socket
import pickle
import traceback

class Cliente05Sockets:
    try:
        server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        server.connect(("localhost", 8080))

        nome = input("Digite o nome: ")
        idade = input("Digite a idade: ")

        data = [int(idade)]
        data_string = pickle.dumps(data)

        server.send(data_string)

        received_data = server.recv(1024)
        received_data = received_data.decode()

        print("Olá ", nome, "\nVocê se enquadra na categoria \"" + received_data
                            + "\" de nadadores")
    except:
        print("Ocorreu uma exceção")
        traceback.print_exc()