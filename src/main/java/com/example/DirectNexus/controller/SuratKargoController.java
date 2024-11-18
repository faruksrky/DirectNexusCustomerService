package com.example.DirectNexus.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.DirectNexus.config.KargoSoapClient;

import javax.xml.soap.SOAPMessage;

@RestController
@RequestMapping("/suratkargo")
public class SuratKargoController {

    @PostMapping("/kargodurumu")
    public String getKargoDurumu(
            @RequestParam String cariKodu,
            @RequestParam String sifre,
            @RequestParam String webSiparisKodu) {

        try {
            // SOAP Client Oluştur
            String endpoint = "https://webservices.suratkargo.com.tr/services.asmx";
            KargoSoapClient soapClient = new KargoSoapClient(endpoint);

            // SOAP İsteği Oluştur
            SOAPMessage request = KargoSoapClient.createKargoTakipHareketDetayiRequest(cariKodu, sifre, webSiparisKodu);

            // SOAPAction ve İsteği Gönder
            String soapAction = "http://tempuri.org/KargoTakipHareketDetayi";
            SOAPMessage response = soapClient.call(soapAction, request);

            // Yanıtı XML formatında döndür
            return response.getSOAPBody().getTextContent();

        } catch (Exception e) {
            e.printStackTrace();
            return "Kargo sorgulamada hata oluştu: " + e.getMessage();
        }
    }
}