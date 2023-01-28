import zmq
import json

def main():
    context = zmq.Context()

    subscriber = context.socket(zmq.SUB)
    subscriber.connect("tcp://localhost:5555")
    subscriber.setsockopt(zmq.SUBSCRIBE, b"aluno")

    sub2 = context.socket(zmq.SUB)
    sub2.connect("tcp://172.31.94.28:2021")
    sub2.setsockopt(zmq.SUBSCRIBE, b"aluno")

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

            n1 = x["n1"]
            n2 = x["n2"]
            n3 = x["n3"]

            M = (n1 + n2) / 2

            if (M >= 7.0):
                print("O aluno está aprovado")
            elif (M > 3.0 and M < 7.0):
                if ((M + n3)/2 >= 5.0):
                    print("O aluno está aprovado")
                else:
                    print("O aluno não está aprovado")
            else:
                print("O aluno não está aprovado")

        if sub2 in socks:
            [address2, contents2] = sub2.recv_multipart()
            y = json.loads(contents2)
            n1 = y["n1"]
            n2 = y["n2"]
            n3 = y["n3"]

            M = (n1 + n2) / 2

            if (M >= 7.0):
                print("O aluno está aprovado")
            elif (M > 3.0 and M < 7.0):
                if ((M + n3) / 2 >= 5.0):
                    print("O aluno está aprovado")
                else:
                    print("O aluno não está aprovado")
            else:
                print("O aluno não está aprovado")

    subscriber.close()
    context.term()

if __name__ == "__main__":
    main()