import zmq
import json


def main():
    context = zmq.Context()

    subscriber = context.socket(zmq.SUB)
    subscriber.connect("tcp://localhost:5555")
    subscriber.setsockopt(zmq.SUBSCRIBE, b"funcionario")

    sub2 = context.socket(zmq.SUB)
    sub2.connect("tcp://172.31.94.28:2021")
    sub2.setsockopt(zmq.SUBSCRIBE, b"funcionario")

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
            salario = x["salario"]
            cargo = x["cargo"]
            if ( cargo == "programador"):
                salario = x["salario"]*1.18
            else:
                salario = x["salario"]*1.2

            print("Nome : ",x["nome"],",","Salario final : ",salario)


        if sub2 in socks:
            [address2, contents2] = sub2.recv_multipart()
            y = json.loads(contents2)
            salario2 = y["salario"]
            cargo2 = y["cargo"]
            if ( cargo2 == "programador"):
                salario = y["salario"]*1.18
            else:
                salario = y["salario"]*1.2

            print("Nome : ",y["nome"],",","Salario final : ",salario)

    subscriber.close()
    context.term()

if __name__ == "__main__":
    main()