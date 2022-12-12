import socket
import pickle
import traceback

class Cliente01Sockets:
    try:
        server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        server.connect(("localhost", 7777))

        nome = input("Digite o nome: ")
        cargo = input("Digite o cargo: ")
        salario = input("Digite o salário: ")

        data = [cargo, salario]
        data_string = pickle.dumps(data)

        server.send(data_string)

        received_data = server.recv(1024)
        received_data = received_data.decode()

        print("Olá ", nome, "\nseu salário reajustado é: R$ ", received_data)
    except:
        print("Ocorreu uma exceção")
        traceback.print_exc()