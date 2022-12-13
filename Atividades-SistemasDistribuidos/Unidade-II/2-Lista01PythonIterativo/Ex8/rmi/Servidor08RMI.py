import Pyro4

@Pyro4.expose
class Servidor08RMI(object):

    def credito(self, saldo):
        resposta = ""
        if (saldo <= 200):
            resposta = "Saldo Medio: ", saldo, "\nNenhum Credito"
        elif (saldo <= 400):
            resposta = "Saldo Medio: ", saldo, "\nValor do Credito: ", saldo * 0.2
        elif (saldo <= 600):
            resposta = "Saldo Medio: ", saldo, "\nValor do Credito: ", saldo * 0.3
        else:
            resposta = "Saldo Medio: ", saldo, "\nValor do Credito: ", saldo * 0.4
        return resposta

daemon = Pyro4.Daemon.serveSimple({Servidor08RMI: 'Servidor08RMI', }, host="localhost", port=8000, ns=False, verbose=True)
ns = Pyro4.locateNS()
server = Servidor08RMI()
uri = daemon.register(server)
ns.register('serverRMI', uri)
print('Server ...')
daemon.requestLoop()