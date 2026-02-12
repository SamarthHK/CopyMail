import json
def configReader(requirement: str):
    with open("config.json","r") as config:
        data = config.read()
        load = json.loads(data)
        match requirement:
            case "send":
                return load["email"]["sender"]
            case "recieve":
                return load["email"]["receiver"]
            case "pwsd":
                return load["email"]["app_password"]
            case "key":
                return load["hotkey"]