import socket
import pickle
import traceback

class Cliente04Sockets:
    try:
        server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        server.connect(("localhost", 8080))

        nome = input("Digite o nome: ")
        altura = input("Digite a altura: ")
        sexo = input("Digite o sexo: ")

        data = [float(altura), sexo]
        data_string = pickle.dumps(data)

        server.send(data_string)

        received_data = server.recv(1024)
        received_data = received_data.decode()

        print("Olá ", nome, "\nSeu peso ideal é " + received_data)
    except:
        print("Ocorreu uma exceção")
        traceback.print_exc()