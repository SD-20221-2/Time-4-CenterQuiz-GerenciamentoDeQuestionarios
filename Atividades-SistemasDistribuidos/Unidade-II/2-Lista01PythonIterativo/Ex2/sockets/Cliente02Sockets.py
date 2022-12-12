import socket
import pickle
import traceback

class Cliente01Sockets:
    try:
        server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        server.connect(("localhost", 8080))

        nome = input("Digite o nome: ")
        sexo = input("Digite o sexo: ")
        idade = input("Digite a idade: ")

        data = [sexo, int(idade)]
        data_string = pickle.dumps(data)

        server.send(data_string)

        received_data = server.recv(1024)
        received_data = received_data.decode()

        print("Olá ", nome)
        if (eval(received_data) == True):
            print("\nvocê atingiu a maioridade!")
        else:
            print("\nvocê não atingiu a maioridade!")
    except:
        print("Ocorreu uma exceção")
        traceback.print_exc()