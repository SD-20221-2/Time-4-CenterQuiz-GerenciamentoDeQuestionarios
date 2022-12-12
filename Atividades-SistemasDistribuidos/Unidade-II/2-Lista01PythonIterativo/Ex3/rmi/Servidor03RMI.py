import Pyro4

@Pyro4.expose
class Servidor03RMI(object):

    def verificarAprovado(self, n1, n2, n3):
        M = float(n1 + n2) / 2

        if (M >= 7.0):
            return True
        elif (M > 3.0 and M < 7.0):
            if ((M + n3) / 2 >= 5.0):
                return True

        return False


daemon = Pyro4.Daemon.serveSimple({Servidor03RMI: 'Servidor03RMI', }, host="localhost", port=8000, ns=False, verbose=True)
ns = Pyro4.locateNS()
server = Servidor03RMI()
uri = daemon.register(server)
ns.register('serverRMI', uri)
print('Server ...')
daemon.requestLoop()