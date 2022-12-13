import socket
import pickle
import traceback

from Ex7.home07.Aposentadoria import Aposentadoria


class Servidor07Sockets:
    try:

        s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        s.bind(("localhost", 8080))
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

            retorno = Aposentadoria.verificar(int(data_array[0]), int(data_array[1]))

            conn.sendall(str.encode(str(retorno)))
    except:
        print("Ocorreu uma exceção")
        traceback.print_exc()
