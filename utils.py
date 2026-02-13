import json
import smtplib
from email.message import EmailMessage

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