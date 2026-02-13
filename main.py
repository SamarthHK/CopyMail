import utils 
import keyboard
import smtplib
import pyperclip
temp = True
while True:
    if keyboard.is_pressed(utils.configReader("key")) and temp:
        utils.email(utils.configReader("send"),utils.configReader("receive"),pyperclip.paste(),utils.configReader("password"))
        temp = False
    elif not  keyboard.is_pressed(utils.configReader("key")):
        temp = True
        




            
