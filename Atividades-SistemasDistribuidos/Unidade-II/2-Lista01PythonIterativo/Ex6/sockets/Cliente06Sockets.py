import socket
import pickle
import traceback

class Cliente05Sockets:
    try:
        server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        server.connect(("localhost", 8080))

        nome = input("Digite o nome: ")
        nivel = input("Digite o nível (A, B, C ou D): ")
        salarioBruto = input("Digite o salário bruto: ")
        numeroDependentes = input("Digite o número de dependentes: ")

        data = [nivel, float(salarioBruto), int(numeroDependentes)]
        data_string = pickle.dumps(data)

        server.send(data_string)

        received_data = server.recv(1024)
        received_data = received_data.decode()

        print("Olá ", nome
              ,"\nVocê pertence ao nível \"", nivel
              , "\" e seu salário líquido é = R$ ", received_data)
    except:
        print("Ocorreu uma exceção")
        traceback.print_exc()