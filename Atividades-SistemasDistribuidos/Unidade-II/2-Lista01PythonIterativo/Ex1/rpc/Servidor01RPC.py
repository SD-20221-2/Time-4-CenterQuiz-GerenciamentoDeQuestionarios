from xmlrpc.server import SimpleXMLRPCServer

def salarioReajustado(cargo, salario):
    if cargo.lower() == "operador":
        return salario * 1.2
    elif (cargo.lower() == "programador"):
        return salario * 1.18
    else:
        return salario

server = SimpleXMLRPCServer(("localhost", 8080))
print("Servidor esperando por conexoes...")
server.register_function(salarioReajustado, "salarioReajustado")
server.serve_forever()