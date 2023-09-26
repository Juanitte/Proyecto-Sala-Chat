package com.juanite.connection;

import com.juanite.model.domain.Message;
import util.XMLManager;

import java.io.*;
import java.net.Socket;

public class ClientHandler extends Thread {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            String xmlMessage;
            while ((xmlMessage = in.readLine()) != null) {
                // Procesar el mensaje XML recibido
                processXMLMessage(xmlMessage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processXMLMessage(String xmlMessage) {
        // Aquí debes analizar la cadena XML y extraer los datos (sender, content, timestamp)
        // Puedes usar bibliotecas como JAXB para facilitar esta tarea.
        // Aquí se muestra un ejemplo simple de análisis de XML:
        // (Asume que tienes métodos para extraer datos de XML)
        Message msg = XMLManager.readMessageXML("msg_temp.xml");

        // Hacer algo con los datos del mensaje, por ejemplo, reenviarlo a otros clientes
        // Puedes mantener una lista de clientes conectados y enviar el mensaje a todos ellos.
    }
}