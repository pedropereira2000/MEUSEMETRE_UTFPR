# -*- coding: utf-8 -*- 

import socket
import hashlib
import Crypto
import base64
import rsa
from simplecrypt import encrypt, decrypt
from Crypto.PublicKey import RSA
from Crypto.Util.randpool import RandomPool
from Crypto import Random
from Crypto.Cipher import PKCS1_OAEP 
from Crypto.PublicKey import RSA 
from binascii import hexlify
from Crypto.Signature import PKCS1_v1_5
from Crypto.Hash import SHA512, SHA384, SHA256, SHA, MD5
from Crypto import Random
from base64 import b64encode, b64decode

hash = "SHA-256"

private_key = RSA.generate(1024)
public_key = private_key.publickey() 



private_pem = private_key.export_key().decode() 
public_pem = public_key.export_key().decode() 
#print (type (private_pem), type (public_pem))
#print (private_pem) 
#print (public_pem)

with open('Ser_private_pem.pem', 'w') as pr: 
    pr.write(private_pem) 
with open('Ser_public_pem.pem', 'w') as pu : 
    pu.write(public_pem) 

pr_key = RSA.import_key(open('Ser_private_pem.pem', 'r').read())
pu_key = RSA.import_key(open('Ser_public_pem.pem', 'r').read())
#print(type(pr_key), type(pu_key))

#cipher = PKCS1_OAEP.new(key=pu_key)
#cipher_text = cipher.encrypt(text)
#print(cipher_text)

#decrypt = PKCS1_OAEP.new(key=pr_key)
#decrypted_message = decrypt.decrypt(cipher_text)
#print(decrypted_message)

# Assinando
def sign(message, priv_key, hashAlg="SHA-256"):
    global hash
    hash = hashAlg
    signer = PKCS1_v1_5.new(priv_key)
    if (hash == "SHA-512"):
        digest = SHA512.new()
    elif (hash == "SHA-384"):
        digest = SHA384.new()
    elif (hash == "SHA-256"):
        digest = SHA256.new()
    elif (hash == "SHA-1"):
        digest = SHA.new()
    else:
        digest = MD5.new()
    digest.update(message)
    return signer.sign(digest)

# Verificando assinatura
def verify(message, signature, pub_key):
    signer = PKCS1_v1_5.new(pub_key)
    if (hash == "SHA-512"):
        digest = SHA512.new()
    elif (hash == "SHA-384"):
        digest = SHA384.new()
    elif (hash == "SHA-256"):
        digest = SHA256.new()
    elif (hash == "SHA-1"):
        digest = SHA.new()
    else:
        digest = MD5.new()
    digest.update(message)
    return signer.verify(digest, signature)


kSim = '9i0j1k2l3m4n5o6p'

def server(host = 'localhost', port=5535):
    data_payload = 2048 #The maximum amount of data to be received at once
    # Create a TCP socket
    sock = socket.socket(socket.AF_INET,  socket.SOCK_STREAM)
    # Enable reuse address/port 
    sock.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
    # Bind the socket to the port
    server_address = (host, port)
    print ("Starting up echo server  on %s port %s" % server_address)
    sock.bind(server_address)
    # Listen to clients, argument specifies the max no. of queued connections
    sock.listen(5) 
    name = raw_input("Enter the User Name to be Used\n>>")
    client, address = sock.accept() 
    #recebendo chave publica
    data = client.recv(2048) 
    #lendo chave publica
    Cli_pu_key = RSA.import_key(data)
    #Enviando caminho da chave publica
    client.sendall(public_pem)
    
    while 1:
        #Recebendo Key criptografada
        data = client.recv(2048) 
        decrypt1 = PKCS1_OAEP.new(key=pr_key)
        decrypted_Key = decrypt1.decrypt(data)
        #Recebendo hash da Key
        data = client.recv(2048)
        # Verifica se é possivel estabelecer uma conexão "segura"
        if verify(decrypted_Key, data, Cli_pu_key):
            # Se os hashs forem iguais Criptografando a key simetrica com key pub do servidor
            cipher_cli = PKCS1_OAEP.new(key=Cli_pu_key)
            cipher_key = cipher_cli.encrypt(kSim)
            client.sendall(cipher_key)
            # Fazendo o hash e Criptografando com key priv do client
            digitalSign = sign(kSim, pr_key,'SHA-256')
            client.sendall(digitalSign)
            
            # Recebendo a mensagem
            data = client.recv(2048)
            msgDecrypt = decrypt(kSim, data)
            data = client.recv(2048)
            # Verifica autenticidade da mensagem
            if verify(msgDecrypt, data, Cli_pu_key):
                # Se a mensagem for exit encerra a comunicação
                if msgDecrypt == "exit" or msgDecrypt == "Exit":
                    print "Closing Connection..."
                    message = "Exit"
                    msgCrypt = encrypt(decrypted_Key, message)
                    client.sendall(msgCrypt)
                    hashedMsg = hashlib.sha256(message).hexdigest()
                    client.sendall(hashedMsg)
                    client.close()
                    break
                else:
                    # Se a mensagem não for exit 
                    print msgDecrypt
                    message = raw_input("Enter with the massage \n>>")
                    message = name + ": " + message
                    msgCrypt = encrypt(decrypted_Key, message)
                    client.sendall(msgCrypt)
                    hashedMsg = sign(message, pr_key,'SHA-256')
                    client.sendall(hashedMsg)
            else:
                # Se a mensagem não for autentica avisa e encerra a comunicação
                print "A mensagem de " + name + "chegou com problemas de integridade"
                message = name + "Exit"
                msgCrypt = encrypt(decrypted_Key, message)
                client.sendall(msgCrypt)
                client.close()
                break           
        else:
            print "Não foi possível estabelecer uma cominicação segura"
        
server()