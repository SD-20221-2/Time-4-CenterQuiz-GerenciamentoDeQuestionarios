import zmq
import json

def main():
    context = zmq.Context()

    subscriber = context.socket(zmq.SUB)
    subscriber.connect("tcp://localhost:5555")
    subscriber.setsockopt(zmq.SUBSCRIBE, b"esporte")

    sub2 = context.socket(zmq.SUB)
    sub2.connect("tcp://172.31.94.28:2021")
    sub2.setsockopt(zmq.SUBSCRIBE, b"esporte")

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

            idade = x["idade"]

            if (idade >= 5 and idade <= 7):
                print("Categoria infantil A")
            elif(idade >= 8 and idade <= 10):
                print("Categoria infantil B")
            elif(idade >= 11 and idade <= 13):
                print("Categoria juvenil A")
            elif (idade >= 14 and idade <= 17):
                print("Categoria juvenil B")
            elif(idade > 18):
                print("Categoria adulto")

        if sub2 in socks:
            [address2, contents2] = sub2.recv_multipart()
            y = json.loads(contents2)

            idade = y["idade"]

            if (idade >= 5 and idade <= 7):
                print("Categoria infantil A")
            elif (idade >= 8 and idade <= 10):
                print("Categoria infantil B")
            elif (idade >= 11 and idade <= 13):
                print("Categoria juvenil A")
            elif (idade >= 14 and idade <= 17):
                print("Categoria juvenil B")
            elif (idade > 18):
                print("Categoria adulto")

    subscriber.close()
    context.term()

if __name__ == "__main__":
    main()