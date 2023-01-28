import random
import json

from paho.mqtt import client as mqtt_client

broker = 'broker.emqx.io'
port = 1883
topic = "Tópico do exercício 3"
# gerar ID do cliente com prefixo de pub aleatoriamente
client_id = f'python-mqtt-{random.randint(0, 100)}'
username = 'mqttbrokertest'
password = 'mqttbrokertest'

def connect_mqtt() -> mqtt_client:
    def on_connect(client, userdata, flags, rc):
        if rc == 0:
            print("Subscriber conectado ao MQTT Broker! Tópico: ", topic)
        else:
            print("Falha de conexão, retorno %d\n", rc)

    client = mqtt_client.Client(client_id)
    client.username_pw_set(username, password)
    client.on_connect = on_connect
    client.connect(broker, port)
    return client


def subscribe(client: mqtt_client):
    def on_message(client, userdata, msg):
            m_decode = str(msg.payload.decode("utf-8", "ignore"))
            if is_json(m_decode): # garantindo que a mensagem é uma string de json
                m_in = json.loads(m_decode)  # decode json data

                n1 = m_in["n1"]
                n2 = m_in["n2"]
                n3 = m_in["n3"]

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

    client.subscribe(topic)
    client.on_message = on_message

def is_json(myjson):
  try:
    json.loads(myjson)
  except ValueError as e:
    return False
  return True


def run():
    client = connect_mqtt()
    subscribe(client)
    client.loop_forever()


if __name__ == '__main__':
    run()