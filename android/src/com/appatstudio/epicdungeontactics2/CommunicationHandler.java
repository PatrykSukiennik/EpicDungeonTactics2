package com.appatstudio.epicdungeontactics2;

public class CommunicationHandler implements AndroidCommunication {

    private final CommunicationApi communicationApi;

    public CommunicationHandler() {
        communicationApi = new CommunicationApi();
    }

    @Override
    public boolean checkInternetConnection() {
        return communicationApi.checkNetworkConnection();
    }

    @Override
    public void generateInterstitialAd() {
        communicationApi.generateInterstitialAd();
    }
}
