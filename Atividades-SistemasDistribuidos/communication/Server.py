import zmq, time

HOST = "*"
PORT = "5555"

context = zmq.Context()
s = context.socket(zmq.PUB)
p = "tcp://" + HOST + ":" + PORT
s.bind(p)
while True:
    time.sleep(5)
    s.send_string("TIME" + time.asctime())