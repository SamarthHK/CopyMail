import json
import smtplib
from email.message import EmailMessage
from pathlib import Path
import keyboard
import re

def configReader(key:str):
    with open("config.json","r") as config:
        load = json.load(config)
        match key:
            case "send":
                return load["email"]["sender"]
            case "receive":
                return load["email"]["receiver"]
            case "password":
                return load["email"]["app_password"]
            case "key":
                return load["hotkey"]
            case _:
                return None 
def email(sender:str,receiver:str,copyMail:str,password:str):
    msg = EmailMessage()
    msg["Subject"] = "Copy Mail"
    msg["From"] = sender 
    msg["To"] = receiver
    msg.set_content(copyMail)

    with smtplib.SMTP("smtp.gmail.com", 587) as server:
        server.starttls()  
        server.login(sender, password)
        server.send_message(msg)
def configCheck():
    configPath = Path("config.json")
    if configPath.exists():
        return "found"
    else:
        return "missing"
def configCreate(sender:str,receiver:str,password:str,keybind:str):
    config = {
        "email": {
            "sender": sender,
            "receiver": receiver,
            "app_password": password
        },
        "hotkey": keybind
    }
    with open("config.json","w") as configFile:
        json.dump(config, configFile, indent=4)
def configInput():
    sender = senderEmailCheck()
    password = input("Provide the app password for the email you just inputted.\n")
    receiver = recieverEmailCheck()
    print("Press the keys you wish to use for your keybinds.\n Press escape to stop.")
    keybind = keybindReader()
    return sender,receiver,password,keybind
def keybindReader():
    events = keyboard.record(until='escape', suppress=False)
    pressedKeys = set()
    for event in events:
        if event.event_type == "down" and event.name != "escape":
            pressedKeys.add(event.name)
    pressedKeys.remove("esc")
    keybind = "+".join(sorted(pressedKeys))
    return keybind
def emailCheck(email):
    pattern = r"^[\w\.-]+@[\w\.-]+\.\w+$"
    return re.match(pattern, email) is not None
def senderEmailCheck():
    sender = input("Input the email you will use to send an email.\n")
    while not emailCheck(sender):
        sender = input("Input a valid email you will use to send an email.\n")
    return sender 
def recieverEmailCheck():
    receiver = input("Input the email you will use to receive the email.\n")
    while not emailCheck(receiver):
        receiver = input("Input a valid email you will use to receive the email.\n")
    return receiver 