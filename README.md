CopyMail
CopyMail is a Java utility that automatically sends the text you copy to your email with just a hotkey.
This project started as a personal learning experiment — every morning before school, I hosted live coding sessions and was annoyed that I had to log into my email every time I wanted to send text. CopyMail solves that problem: now, with a hotkey, it instantly sends whatever is in your clipboard to your email, saving precious time.

Features:
  Automatically sends clipboard content via email
  Configurable hotkey to trigger sending
  Stores sender and receiver email addresses securely in a JSON config file
  Handles missing or corrupted configs by auto-reinitializing
  Easy-to-use: press Escape to exit the program

How It Works:
  Start the program: java -cp target/classes com.viveka01.copymail.App
  Configure your sender email, receiver email, app password, and hotkey (only once).
  Copy any text to your clipboard.
  Press your configured hotkey — the program sends the clipboard content via email.
  Press Escape to stop the program.

Project Structure:
  App.java — main entry point; handles the hotkey loop
  ClipBoardReader.java — reads text from system clipboard
  ConfigManager.java & ConfigStructure.java — singleton configuration manager with JSON storage
  ConfigInput.java & DefaultInputReturnStructure.java — handles user input for configuration
  Email.java — sends emails via SMTP (Gmail)
  HotKeyInput.java & KeyListener.java — manages hotkey input using JNativeHook
  Timer.java — utility class to measure key-hold duration

Learning Notes:
  This project was my first real Java project, and I learned a lot:
  Java Singletons and managing global state
  Maven project structure and dependencies
  Git commands, branching, and version control strategies
  TLS and email protocols (SMTP, Gmail authentication)
  How verbose Java can be compared to Python
  File structuring and JSON config handling with Jackson
  Future Improvements
  Add email validation and error reporting
  Support HTML emails or attachments
  Improve hotkey recording for complex key combinations
  Auto-refresh email session if configuration changes

Disclaimer:
  This project is for personal learning and automation purposes.
Do not use real passwords in shared environments. Use app-specific passwords for Gmail or equivalent services.
