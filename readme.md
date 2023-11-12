## Overview

This Java application demonstrates a simple implementation of a messaging system with encryption and decryption functionalities, using a Swing graphical user interface (GUI). The application is divided into two main parts: `Receiver` and `Sender`, each represented as separate classes in the package `Application`. Additionally, it utilizes a custom UI component `VerticalFlowLayout` from the `UIs` package for layout management.

### Components

1. **Receiver**: Handles the display and decryption of received messages and files.
   - Displays decrypted data, key, and plaintext in text areas.
   - Uses a `BufferedReader` to read decrypted data from a file.

2. **Sender**: Manages sending messages and files with encryption.
   - Provides a GUI with two tabs: one for sending messages and another for sending files.
   - Implements file selection and encryption/decryption through `JFileChooser` and custom encryption logic.

3. **Key_generator_UI**: A utility class for generating and displaying encryption keys (not fully detailed in the provided code).

4. **Key_generator**: A utility class for encryption key management (assumed based on context).

5. **Locker**: A class used for file encryption and decryption (assumed based on context).

### GUI Layout

- The `Receiver` and `Sender` classes each create a `JFrame` with various components like `JLabel`, `TextArea`, and `JButton`.
- `VerticalFlowLayout` is used to arrange components vertically.
- In `Sender`, a `JTabbedPane` is used to separate the message sending and file sending functionalities.

### Encryption & Decryption Process

- The application seems to involve a process of encrypting and decrypting messages and files, although the specifics of the encryption algorithm are not detailed in the provided code.
- `Key_generator` is assumed to play a crucial role in managing encryption keys.

## Setup and Execution

1. **Prerequisites**: Ensure Java is installed on your system.
2. **Compilation**: Compile the Java files in the `Application` and `UIs` packages.
3. **Running the Application**: Run the `Sender` class to start the application. It will also instantiate the `Receiver` part of the application.


