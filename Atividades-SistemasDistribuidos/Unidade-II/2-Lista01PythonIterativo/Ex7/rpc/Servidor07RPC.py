from xmlrpc.server import SimpleXMLRPCServer


def verificar(idade, tempoServico):
    if (idade >= 65
            and tempoServico >= 30
            and idade >= 60
            and tempoServico >= 25):
        return True
    else:
        return False

server = SimpleXMLRPCServer(("localhost", 8080))
print("Servidor esperando por conexoes...")
server.register_function(verificar, "verificar")
server.serve_forever()