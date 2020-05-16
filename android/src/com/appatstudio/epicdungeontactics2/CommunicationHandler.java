package com.appatstudio.epicdungeontactics2;

public class CommunicationHandler implements AndroidCommunication {

    private final CommunicationApi communicationApi;

    CommunicationHandler() {
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

    @Override
    public void startBanner() {communicationApi.startBanner();};

    @Override
    public void stopBanner() {communicationApi.stopBanner();};
}
