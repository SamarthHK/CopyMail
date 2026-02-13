import utils 
import keyboard
import pyperclip
import time
if __name__ == "__main__":
    if utils.configCheck() == "missing":
        print("You dont have any configurations setup.\n Setting up configs...")
        utils.configCreate(*utils.configInput())
    temp = True
    while True:
        if keyboard.is_pressed(utils.configReader("key")) and temp:
            utils.email(utils.configReader("send"),utils.configReader("receive"),pyperclip.paste(),utils.configReader("password"))
            temp = False
        elif not  keyboard.is_pressed(utils.configReader("key")):
            temp = True
    time.sleep(0.1)


        




            
