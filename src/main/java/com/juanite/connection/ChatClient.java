package com.juanite.connection;

import com.juanite.model.domain.Message;
import util.XMLManager;

import java.io.*;
import java.net.Socket;

public class ChatClient {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public ChatClient(String serverAddress, int serverPort) {
        try {
            socket = new Socket(serverAddress, serverPort);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String sender, String content) {
        // Crear un objeto XML para el mensaje y convertirlo a una cadena XML
        String xmlMessage = buildXMLMessage(sender, content);
        out.println(xmlMessage);
    }

    private String buildXMLMessage(String sender, String content) {
        // Aquí debes construir la cadena XML usando un enfoque como StringBuilder
        // Puedes usar bibliotecas como JAXB para facilitar esta tarea.
        // Aquí se muestra un ejemplo simple de construcción de XML:
        StringBuilder xmlBuilder = new StringBuilder();
        xmlBuilder.append("<chatMessage>");
        xmlBuilder.append("<sender>").append(sender).append("</sender>");
        xmlBuilder.append("<content>").append(content).append("</content>");
        xmlBuilder.append("</chatMessage>");
        return xmlBuilder.toString();
    }

    public void start() {
        // Aquí puedes implementar la lógica para recibir mensajes del servidor en XML
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


        // Hacer algo con los datos del mensaje, por ejemplo, mostrarlo en la interfaz de usuario
        System.out.println("Mensaje recibido de " + msg.getUser().getUsername() + ": " + msg.getMessage());
    }

    public static void main(String[] args) {
        String serverAddress = "172.16.15.165"; // Cambia esto a la dirección IP del servidor si es necesario
        int serverPort = 8080; // El mismo puerto en el que el servidor está escuchando

        ChatClient chatClient = new ChatClient(serverAddress, serverPort);
        chatClient.start();
    }
}