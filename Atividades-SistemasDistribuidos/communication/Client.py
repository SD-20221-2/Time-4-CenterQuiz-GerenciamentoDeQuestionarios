import zmq

HOST = "localhost"
PORT = "5555"

context = zmq.Context()
s = context.socket(zmq.SUB)
p = "tcp://" + HOST + ":" + PORT
s.connect(p)
s.setsockopt_string(zmq.SUBSCRIBE, "TIME")

for i in range(5):
    time = s.recv_string()
    print(time)
