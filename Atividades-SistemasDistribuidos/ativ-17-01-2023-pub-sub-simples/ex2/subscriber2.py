import zmq
import json


def main():

    context = zmq.Context()

    subscriber = context.socket(zmq.SUB)
    subscriber.connect("tcp://localhost:5555")
    subscriber.setsockopt(zmq.SUBSCRIBE, b"maioridade")

    sub2 = context.socket(zmq.SUB)
    sub2.connect("tcp://172.31.94.28:2021")
    sub2.setsockopt(zmq.SUBSCRIBE, b"maioridade")

    poller = zmq.Poller()
    poller.register(subscriber, zmq.POLLIN)
    poller.register(sub2, zmq.POLLIN)

    while True:

        try:
            socks = dict(poller.poll())
        except KeyboardInterrupt:
            exit()

        if subscriber in socks:
            [address, contents] = subscriber.recv_multipart()
            x = json.loads(contents)
            sexo = x["sexo"]
            idade = x["idade"]
            if ( (sexo == "masculino" and idade >= 18) or
                 (sexo == "feminino" and idade >= 21 )):
                resposta = "Atingiu a maior idade"
            else :
                resposta = "Nao Atingiu a maior idade"
            print(x["nome"], resposta)


        if sub2 in socks:
            [address2, contents2] = sub2.recv_multipart()
            y = json.loads(contents2)
            sexo = y["sexo"]
            idade = y["idade"]
            if ( (sexo == "masculino" and idade >= 18) or
                 (sexo == "feminino" and idade >= 21 )):
                resposta2 = "Atingiu a maior idade"
            else :
                resposta2 = "Nao Atingiu a maior idade"
            print(y["nome"], resposta2)

    subscriber.close()
    context.term()

if __name__ == "__main__":
    main()