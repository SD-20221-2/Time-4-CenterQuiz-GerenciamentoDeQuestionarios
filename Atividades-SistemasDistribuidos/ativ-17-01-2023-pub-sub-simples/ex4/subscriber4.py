import zmq
import json

def main():
    context = zmq.Context()

    subscriber = context.socket(zmq.SUB)
    subscriber.connect("tcp://localhost:5555")
    subscriber.setsockopt(zmq.SUBSCRIBE, b"peso")

    sub2 = context.socket(zmq.SUB)
    sub2.connect("tcp://172.31.94.28:2021")
    sub2.setsockopt(zmq.SUBSCRIBE, b"peso")

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

            altura = x["altura"]
            sexo = x["sexo"]

            if (sexo == "masculino"):
                pesoIdeal = ((72.7 * altura) - 58)
                print("Peso ideal = " + str(pesoIdeal))
            else:
                pesoIdeal = (62.1 * altura) - 44.7
                print("Peso ideal = " + str(pesoIdeal))

        if sub2 in socks:
            [address2, contents2] = sub2.recv_multipart()
            y = json.loads(contents2)
            altura = y["altura"]
            sexo = y["sexo"]

            if (sexo == "masculino"):
                pesoIdeal = ((72.7 * altura) - 58)
                print("Peso ideal = " + str(pesoIdeal))
            else:
                pesoIdeal = (62.1 * altura) - 44.7
                print("Peso ideal = " + str(pesoIdeal))

    subscriber.close()
    context.term()

if __name__ == "__main__":
    main()