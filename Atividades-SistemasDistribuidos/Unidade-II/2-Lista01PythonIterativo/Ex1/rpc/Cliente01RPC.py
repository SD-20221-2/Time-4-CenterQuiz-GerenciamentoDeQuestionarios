import xmlrpc.client

nome = input("Digite o nome: ")
cargo = input("Digite o cargo: ")
salario = input("Digite o salário: ")

with xmlrpc.client.ServerProxy("http://localhost:8080/") as proxy:
    print("Olá ", nome, "\nseu salário reajustado é: R$ ", proxy.salarioReajustado(cargo, float(salario)))