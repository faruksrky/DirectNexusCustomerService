package com.example.DirectNexus.config;

import javax.xml.soap.*;

public class KargoSoapClient {

    private final String endpoint;

    public KargoSoapClient(String endpoint) {
        this.endpoint = endpoint;
    }

    public SOAPMessage call(String soapAction, SOAPMessage request) throws Exception {
        // SOAP Connection Factory
        SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
        SOAPConnection soapConnection = soapConnectionFactory.createConnection();

        // Gönder ve Yanıt Al
        SOAPMessage response = soapConnection.call(request, endpoint);

        // Bağlantıyı Kapat
        soapConnection.close();

        return response;
    }

    public static SOAPMessage createKargoTakipHareketDetayiRequest(String cariKodu, String sifre, String webSiparisKodu) throws Exception {
        // SOAP Mesaj Fabrikası
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();

        // SOAP Zarfı (Envelope)
        SOAPPart soapPart = soapMessage.getSOAPPart();
        String namespace = "http://tempuri.org/";

        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration("tem", namespace);

        // SOAP Gövdesi (Body)
        SOAPBody soapBody = envelope.getBody();
        SOAPElement soapBodyElement = soapBody.addChildElement("KargoTakipHareketDetayi", "tem");
        SOAPElement cariKoduElement = soapBodyElement.addChildElement("CariKodu", "tem");
        cariKoduElement.addTextNode(cariKodu);
        SOAPElement sifreElement = soapBodyElement.addChildElement("Sifre", "tem");
        sifreElement.addTextNode(sifre);
        SOAPElement webSiparisKoduElement = soapBodyElement.addChildElement("WebSiparisKodu", "tem");
        webSiparisKoduElement.addTextNode(webSiparisKodu);

        // Mesajı Kaydet
        soapMessage.saveChanges();

        return soapMessage;
    }
}