import random
import time
import json

from paho.mqtt import client as mqtt_client

broker = 'broker.emqx.io'
port = 1883
topic = "Tópico do exercício 4"
# gerar ID do cliente com prefixo de pub aleatoriamente
client_id = f'python-mqtt-{random.randint(0, 1000)}'
username = 'mqttbrokertest'
password = 'mqttbrokertest'

def connect_mqtt():
    def on_connect(client, userdata, flags, rc):
        if rc == 0:
            print("Publisher conectado ao MQTT Broker! Tópico: ", topic)
        else:
            print("Falha de conexão, retorno %d\n", rc)

    client = mqtt_client.Client(client_id)
    client.username_pw_set(username, password)
    client.on_connect = on_connect
    client.connect(broker, port)
    return client

def publish(client):
    while True:
        time.sleep(2.5)
        msg = { "altura":1.71, "sexo":"masculino"}
        data_out=json.dumps(msg)
        result = client.publish(topic, data_out)
        status = result[0]
        if status == 0:
            print(f"Mensagem enviada:  `{data_out}` no tópico `{topic}`")
        else:
            print(f"Falha ao enviar mensagem no tópico {topic}")

        msg = { "altura":1.45, "sexo":"feminino"}
        data_out = json.dumps(msg)
        result = client.publish(topic, data_out)
        status = result[0]
        if status == 0:
            print(f"Mensagem enviada:  `{data_out}` no tópico `{topic}`")
        else:
            print(f"Falha ao enviar mensagem no tópico {topic}")

        msg = { "altura":1.63, "sexo":"masculino"}
        data_out = json.dumps(msg)
        result = client.publish(topic, data_out)
        status = result[0]
        if status == 0:
            print(f"Mensagem enviada:  `{data_out}` no tópico `{topic}`")
        else:
            print(f"Falha ao enviar mensagem no tópico {topic}")


def run():
    client = connect_mqtt()
    client.loop_start()
    publish(client)


if __name__ == '__main__':
    run()