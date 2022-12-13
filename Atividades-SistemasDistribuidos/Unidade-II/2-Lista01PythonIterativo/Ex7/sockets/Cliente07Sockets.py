import socket
import pickle
import traceback

class Cliente05Sockets:
    try:
        server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        server.connect(("localhost", 8080))

        nome = input("Digite o nome: ")
        idade = input("Digite a idade: ")
        tempoServico = input("Digite o tempo de serviço: ")

        data = [int(idade), int(tempoServico)]
        data_string = pickle.dumps(data)

        server.send(data_string)

        received_data = server.recv(1024)
        received_data = received_data.decode()

        print("Olá ", nome)
        if (eval(received_data) == True):
            print("\nvocê pode se aponsentar!")
        else:
            print("\nvocê ainda não pode se aposentar!")

    except:
        print("Ocorreu uma exceção")
        traceback.print_exc()