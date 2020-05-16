package com.appatstudio.epicdungeontactics2;

final class CommunicationApi {

    boolean checkNetworkConnection() {
        return AndroidLauncher.isInternetConnectionUp;
    }

    void generateInterstitialAd() {
        AndroidLauncher.isAdWanted = true;
    }

    void startBanner() {AndroidLauncher.startBanner();}
    void stopBanner() {AndroidLauncher.stopBanner();}
}
