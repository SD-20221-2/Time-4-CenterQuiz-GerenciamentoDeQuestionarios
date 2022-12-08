import Pyro4

class ExpressionParser(object):
    # startHosts()
    n1 = input("n1: ")
    n2 = input("n2: ")


    obj = Pyro4.core.Proxy('PYRO:Calculator@' + 'localhost' + ':8000')

    print(obj.add(float(n1), float(n2)))