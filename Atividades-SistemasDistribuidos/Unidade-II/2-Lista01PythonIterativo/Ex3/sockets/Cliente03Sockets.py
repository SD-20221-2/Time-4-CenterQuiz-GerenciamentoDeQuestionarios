import socket
import pickle
import traceback

class Cliente01Sockets:
    try:
        server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        server.connect(("localhost", 8080))

        nome = input("Digite o nome: ")
        n1 = input("Digite a n1: ")
        n2 = input("Digite a n2: ")
        n3 = input("Digite a n3: ")

        data = [float(n1), float(n2), float(n3)]
        data_string = pickle.dumps(data)

        server.send(data_string)

        received_data = server.recv(1024)
        received_data = received_data.decode()

        print("Olá ", nome)
        if (eval(received_data) == True):
            print("\nvocê está aprovado!")
        else:
            print("\nvocê não está aprovado!")
    except:
        print("Ocorreu uma exceção")
        traceback.print_exc()