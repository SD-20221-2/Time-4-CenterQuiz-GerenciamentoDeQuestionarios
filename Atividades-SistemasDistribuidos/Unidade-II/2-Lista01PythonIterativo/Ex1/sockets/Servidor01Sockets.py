import socket
import pickle
import traceback

from Ex1.home01.Salario import Salario


class Servidor01Sockets:
    try:

        s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        s.bind(("localhost", 7777))
        s.listen()
        print("Servidor esperando por conexoes...")
        conn, addr = s.accept()

        print(addr, " conectado")
        while True:
            data = conn.recv(1024)
            if not data:
                print("Fechando Sockets.")
                conn.close()
                break

            data_array = pickle.loads(data)

            data = "Error"

            salarioReajustado = Salario.reajustar(data_array[0], float(data_array[1]))

            conn.sendall(str.encode(str(salarioReajustado)))
    except:
        print("Ocorreu uma exceção")
        traceback.print_exc()
